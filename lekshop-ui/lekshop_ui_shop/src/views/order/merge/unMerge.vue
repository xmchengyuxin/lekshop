<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="listQuery.orderNo" clearable placeholder="订单号" style="width: 200px;" class="filter-item"
        @keyup.enter.native="getList" />
      <el-input v-model="listQuery.memberName" clearable placeholder="买家" style="width: 200px;" class="filter-item"
        @keyup.enter.native="getList" />
      <el-date-picker class="filter-item" v-model="listQuery.startTime" type="datetime" format="yyyy-MM-dd HH:mm:ss"
        placeholder="选择开始时间" />
      -
      <el-date-picker class="filter-item" v-model="listQuery.endTime" type="datetime" format="yyyy-MM-dd HH:mm:ss"
        placeholder="选择结束时间" />

      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" circle @click="getList"></el-button>
      <br>
      <el-button-group>
        <el-button class="filter-item" size="mini" type="primary" icon="el-icon-s-promotion" @click="handlMerge">合并
        </el-button>
      </el-button-group>
      <!-- <br>
			 <el-button-group>
					<el-button class="filter-item" type="info" :loading="downloadLoading" size="mini" icon="el-icon-download" @click="handleDownload">导出当前</el-button>
			 </el-button-group>-->
    </div>
    <el-table :key="tableKey" v-loading="listLoading" :data="list" highlight-current-row style="width: 100%;"
      @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="50" align="center"></el-table-column>
      <el-table-column label="商品" prop="shopName">
        <template slot-scope="scope">
          <el-card id="miaosha"  class="box-card" shadow="hover">
            <div slot="header" class="clearfix">
              <span>订单号： {{ scope.row.order.orderNo }}</span>
              <span style="margin-left: 20px;">下单时间：{{ scope.row.order.buyTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}')  }}</span>
                <span style="margin-left: 20px; color: #f90647; font-weight: bold;" v-if="scope.row.order.type == 3 && scope.row.order.groupId != -1 && scope.row.orderGroup">
                  {{ scope.row.orderGroup.groupNum}}人团
                  <span v-if="scope.row.orderGroup.haveGroupNum < scope.row.orderGroup.groupNum">, 距离拼团成功还差{{scope.row.orderGroup.groupNum-scope.row.orderGroup.haveGroupNum}}人</span>
                  <span v-else>, 拼团成功</span>
                </span>
              </span>
            </div>

            <div  class="flex  padding-tb10" :class="index < scope.row.orderDetailList.length-1 ? 'bottom-b':''" v-for="(item,index) in scope.row.orderDetailList">
              <el-image
              class="flex f-s-0 margin-r12"
                  style="height: 60px"
                  :src="item.goodsMainImg"
                  :preview-src-list="[item.goodsMainImg]">
                </el-image>
                <div class="flex f-c">
                  <span class="line1">{{item.goodsName}}</span>
                  <span>{{item.goodsParamName}}</span>
                  <span>¥{{ item.buyPrice | moneyFormat}} * {{item.buyNum}} = ¥{{item.buyTotalPrice | moneyFormat}}</span>
                </div>
                <span class="order-role flex f-a-c f-j-c" v-if="scope.row.order.type == 3 && scope.row.order.groupId != -1">拼</span>
                <span class="order-role flex f-a-c f-j-c miaosha" v-if="scope.row.order.type == 2">秒</span>
            </div>
          </el-card>


        </template>
      </el-table-column>
      <el-table-column label="买家" prop="memberName" align="center" width="150">
        <template slot-scope="scope">
          <span>{{ scope.row.order.memberName }}</span>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize"
      @pagination="getList" />


    <el-dialog title="合并订单" :visible.sync="dialogFormVisible">

      <el-descriptions title="" :column="1" border size="medium">
        <el-descriptions-item v-for="item in mergeList" :label="item.goodsName">
          {{item.num}}
        </el-descriptions-item>
      </el-descriptions>


      <div style="text-align:right;margin-top: 10px;">
        <el-button type="danger" @click="dialogFormVisible=false">取消</el-button>
        <el-button type="primary" @click="mergeConfirm()">确定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<style>
  .demo-table-expand {
    font-size: 0;
  }

  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }

  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 100%;
  }

  .inline_table td,
  .inline_table th,
  .inline_table tr,
  .inline_table thead,
  .inline_table table,
  .inline_table th.is-leaf,
  .inline_table td {
    border: none;
  }

  .inline_table.el-table::before {
    height: 0;
  }

  .box-card {
    position: relative;
  }

  .order-role {
    position: absolute;
    right: 0px;
    top: 0px;
    width: 20px;
    height: 20px;
    background-color: #E65D6E;
    color: #fff;
    border-radius: 5%;
  }

  .box-card-dialog {
    font-size: 13px;
  }

  .box-card-dialog .el-card__header {
    color: #333333;
    background-color: #fafafa;
    border-color: #ddd;
  }

  .miaosha {
    background-color: #ffaa00;
  }
  .my-label {
    background: #E1F3D8 !important;
  }

  .my-content {
    background: #FDE2E2;
  }
</style>

<script>
  import {
    getOrderList,
    mergeOrder
  } from '@/api/order'
  import waves from '@/directive/waves' // Waves directive
  import {
    parseTime,
    renderTime,
    moneyFormat
  } from '@/utils'
  import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

  //0待接单>>1待发货>>2待收货>>3已完成>>4已取消
  const statusOptions = [{
      key: 0,
      text: '待接单'
    },
    {
      key: 1,
      text: '待发货'
    },
    {
      key: 2,
      text: '待收货'
    },
    {
      key: 3,
      text: '已完成'
    },
    {
      key: 4,
      text: '已取消'
    },
    {
      key: 5,
      text: '已退款'
    },
    {
      key: 6,
      text: '已驳回'
    }
  ]

  const logisticTypeOptions = [{
      key: '1',
      text: '无需物流'
    },
    {
      key: '2',
      text: '第三方物流'
    }
  ]

  const statusKeyValue = statusOptions.reduce((acc, cur) => {
    acc[cur.key] = cur.text
    return acc
  }, {})

  const orderTypeOptions = [{
      key: 1,
      text: '常规订单'
    },
    {
      key: 2,
      text: '加急订单'
    },
    {
      key: 3,
      text: '大宗订单'
    },
  ]

  // arr to obj ,such as { CN : "China", US : "USA" }
  const orderTypeKeyValue = orderTypeOptions.reduce((acc, cur) => {
    acc[cur.key] = cur.text
    return acc
  }, {})

  const payMethodOptions = [{
      key: 'wxPay',
      text: '微信支付'
    },
    {
      key: 'aliPay',
      text: '支付宝'
    },
    {
      key: 'balance',
      text: '余额'
    },
  ]

  // arr to obj ,such as { CN : "China", US : "USA" }
  const payMethodKeyValue = payMethodOptions.reduce((acc, cur) => {
    acc[cur.key] = cur.text
    return acc
  }, {})

  export default {
    name: 'tsMergeList',
    components: {
      Pagination
    },
    directives: {
      waves
    },
    filters: {
      statusFilter(status) {
        return statusKeyValue[status]
      },
      typeFilter(type) {
        return orderTypeKeyValue[type]
      },
      payMethodFilter(type) {
        return payMethodKeyValue[type]
      }
    },
    data() {
      return {
        tableKey: 0,
        list: null,
        total: 0,
        listLoading: true,
        listQuery: {
          page: 1,
          pageSize: 50,
          status: 1,
          mergeStatus: 0,
        },
        order: {},
        orderDetailList: [],
        orderLogistic: {},
        activeName: 'first',
        dialogFormVisible: false,
        multipleSelection: [],
        rules: {
          status: [{
            required: true,
            message: '状态不能为空',
            trigger: 'change'
          }]
        },
        orderTypeOptions,
        downloadLoading: false,
        deliveryTypeOptions: [],
        deliveryForm: {},
        deliveryList: [],
        activeNum: 0,
        verifyForm: {},
        mergeList: {},
        checkOrderIds: null
      }
    },
    created() {
      this.getList()
    },
    methods: {
      getList() {
        this.listLoading = true
        let query = Object.assign({}, this.listQuery)
        query.dateFrom = renderTime(this.listQuery.startTime)
        query.dateTo = renderTime(this.listQuery.endTime)
        getOrderList(query).then(response => {
          this.list = response.data.list
          this.total = response.data.total
          this.listLoading = false
        })
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      handlMerge() {
        let valList = [];
        var map = {};
        const delVals = this.multipleSelection;
        if (!delVals || delVals.length <= 0) {
          this.$message({
            message: '请至少选择一条数据',
            type: 'error',
            duration: 2000
          })
          return;
        }
        delVals.forEach(val => {
          valList.push(val.order.id);
          val.orderDetailList.forEach(item =>{
            if(map == null || !map[item.goodsId]){
              map[item.goodsId] = {goodsName : item.goodsName, num : item.buyNum}
            }else{
              map[item.goodsId].num += item.buyNum;
            }
          })

        })
        this.mergeList = map;
        console.log(map);
        this.checkOrderIds = valList.join(",")
        this.dialogFormVisible = true
      },
      mergeConfirm() {
        mergeOrder({
          orderIds: this.checkOrderIds
        }).then((response) => {
          this.getList()
          this.dialogFormVisible = false
          this.$notify({
            title: '成功',
            message: "合并成功, 请前往已配货订单查看",
            type: 'success',
            duration: 2000
          })
        })
      },


    }
  }
</script>
