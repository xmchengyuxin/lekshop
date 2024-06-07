<template>
  <div class="app-container">
    <el-alert
        title="合并订单>>生成配货单>>进行配货>>进行分拣>>前往发货"
        type="error"
        effect="dark">
      </el-alert>
		<el-tabs v-model="activeName" @tab-click="handleClickTab">
			  <el-tab-pane label="全部" name="-1"></el-tab-pane>
        <el-tab-pane label="已合并" name="1"></el-tab-pane>
				<el-tab-pane label="已配货" name="2"></el-tab-pane>
				<el-tab-pane label="已分拣" name="3"></el-tab-pane>
        <el-tab-pane label="已取消" name="4"></el-tab-pane>
		</el-tabs>
    <div class="filter-container">
			 <el-input v-model="listQuery.mergeOrderNo" clearable placeholder="配货单号" style="width: 200px;" class="filter-item" @keyup.enter.native="getList" />
			 <el-date-picker class="filter-item" v-model="listQuery.startTime" type="datetime" format="yyyy-MM-dd HH:mm:ss" placeholder="选择开始时间" />
			 -
			 <el-date-picker  class="filter-item"  v-model="listQuery.endTime" type="datetime" format="yyyy-MM-dd HH:mm:ss" placeholder="选择结束时间" />
			 <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" circle @click="getList"></el-button>
       </br>
       <el-button-group>
         <el-button class="filter-item" type="primary" size="mini" @click="$router.push('/stock/waitMerge')">
           合并订单
         </el-button>
       </el-button-group>
		</div>
		<el-table
      :key="tableKey"
      v-loading="listLoading"
      :data="list"
      border
      fit
      highlight-current-row
      style="width: 100%;"
			@selection-change="handleSelectionChange"
    >
    <el-table-column label="配货单号" prop="mergeOrderNo" align="center">
      <template slot-scope="scope">
        <span>{{ scope.row.mergeOrderNo }}</span>
      </template>
    </el-table-column>
      <el-table-column label="操作人" prop="accountName" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.accountName }}</span>
        </template>
      </el-table-column>
			<el-table-column label="创建时间"  align="center">
			  <template slot-scope="scope">
			    <span>{{ scope.row.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}')  }}</span>
			  </template>
			</el-table-column>

			<el-table-column label="状态" align="center">
			  <template slot-scope="scope">
          <el-tag effect="plain" v-if="scope.row.status == 0" type="danger">{{ scope.row.status | statusFilter }}</el-tag>
          <el-tag effect="plain" v-if="scope.row.status == 1" type="warning">{{ scope.row.status | statusFilter }}</el-tag>
          <el-tag effect="plain" v-if="scope.row.status == 2" type="info">{{ scope.row.status | statusFilter }}</el-tag>
          <el-tag effect="plain" v-if="scope.row.status == 3" type="info">{{ scope.row.status | statusFilter }}</el-tag>
          <el-tag effect="plain" v-if="scope.row.status == 4" type="danger">{{ scope.row.status | statusFilter }}</el-tag>
          <el-tag effect="plain" v-if="scope.row.status == 5" type="success">{{ scope.row.status | statusFilter }}</el-tag>
          <el-tag effect="plain" v-if="scope.row.status == 6" type="danger">{{ scope.row.status | statusFilter }}</el-tag>
			  </template>
			</el-table-column>
			<el-table-column label="操作" class-name="small-padding">
			  <template slot-scope="scope">
          <el-tooltip class="item" effect="dark" content="查看详情" placement="top">
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleDetail(scope.row)"></el-button>
          </el-tooltip>
          <el-popconfirm
            v-if="scope.row.status == 1"
            confirm-button-text='好的'
            cancel-button-text='不用了'
            icon="el-icon-info"
            icon-color="red"
            title="确认将该笔配货单置为已配货吗？"
            @confirm="orderMergeToDis(scope.row)"
          >
            <el-button slot="reference"  type="success" icon="el-icon-circle-check" size="mini">
              已配货
            </el-button>
          </el-popconfirm>
          <el-popconfirm
            v-if="scope.row.status == 1"
            confirm-button-text='好的'
            cancel-button-text='不用了'
            icon="el-icon-info"
            icon-color="red"
            title="确认取消该合并订单吗？"
            @confirm="cancel(scope.row)"
          >
            <el-button slot="reference"  type="danger" icon="el-icon-circle-check" size="mini">
              取消
            </el-button>
          </el-popconfirm>
          <el-popconfirm
            v-if="scope.row.status == 2"
            confirm-button-text='好的'
            cancel-button-text='不用了'
            icon="el-icon-info"
            icon-color="red"
            title="确认将该笔配货单置为已分拣吗？"
            @confirm="orderMergeToSortOut(scope.row)"
          >
            <el-button slot="reference"  type="success" icon="el-icon-circle-check" size="mini">
              已分拣
            </el-button>
          </el-popconfirm>
			  </template>
			</el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />


    <!--订单详情-->
    	<el-dialog title="详情" :visible.sync="dialogDetailVisible" width="60%">

        <el-card class="box-card-dialog" shadow="hover" v-for="item in mergeDetailList">
          <div slot="header" class="clearfix">
            <span>买家: {{item.memberName}}</span>
          </div>

          <div v-for="orderItem in item.orderResultList">

              <el-card id="miaosha"  class="box-card" shadow="hover">
                <div slot="header" class="clearfix">
                  <span>订单号： {{ orderItem.order.orderNo }}</span>
                  <span style="margin-left: 20px;">下单时间：{{ orderItem.order.buyTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}')  }}</span>
                    <span style="margin-left: 20px; color: #f90647; font-weight: bold;" v-if="orderItem.order.type == 3 && orderItem.order.groupId != -1 && orderItem.orderGroup">
                      {{ orderItem.orderGroup.groupNum}}人团
                      <span v-if="orderItem.orderGroup.haveGroupNum < orderItem.orderGroup.groupNum">, 距离拼团成功还差{{orderItem.orderGroup.groupNum-orderItem.orderGroup.haveGroupNum}}人</span>
                      <span v-else>, 拼团成功</span>
                    </span>
                  </span>
                </div>

                <div  class="flex  padding-tb10" :class="index < orderItem.orderDetailList.length-1 ? 'bottom-b':''" v-for="(item,index) in orderItem.orderDetailList">
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
                    <span class="order-role flex f-a-c f-j-c" v-if="orderItem.order.type == 3 && orderItem.order.groupId != -1">拼</span>
                    <span class="order-role flex f-a-c f-j-c miaosha" v-if="orderItem.order.type == 2">秒</span>
                </div>
              </el-card>
          </br>
        </div>

            <!-- <div style="margin-top: 10px; font-weight: bold; font-size: 15px; float: right;">
              <span >退款金额： <span style="color: red;">￥ {{mergeDetail.refundAmount | moneyFormat}}</span></span>
            </div> -->
            <div style="margin-top: 10px;">
              <p style="height: 40px;">状态：
                <el-tag effect="plain" v-if="mergeDetail.status == 0" type="danger">{{ mergeDetail.status | statusFilter }}</el-tag>
                <el-tag effect="plain" v-if="mergeDetail.status == 1" type="warning">{{ mergeDetail.status | statusFilter }}</el-tag>
                <el-tag effect="plain" v-if="mergeDetail.status == 2" type="info">{{ mergeDetail.status | statusFilter }}</el-tag>
                <el-tag effect="plain" v-if="mergeDetail.status == 3" type="success">{{ mergeDetail.status | statusFilter }}</el-tag>
                <el-tag effect="plain" v-if="mergeDetail.status == 4" type="danger">{{ mergeDetail.status | statusFilter }}</el-tag>
                <el-tag  v-if="mergeDetail.status == 5" type="success">{{ mergeDetail.status | statusFilter }}</el-tag>
                <el-tag  v-if="mergeDetail.status == 6" type="danger">{{ mergeDetail.status | statusFilter }}</el-tag>
              </p>
              <p style="height: 30px;">申请时间:  &nbsp;&nbsp;&nbsp;{{ mergeDetail.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}')  }}</p>
            </div>
        </el-card>
        <br>
        <el-card class="box-card-dialog" shadow="hover">
          <div slot="header" class="clearfix">
            <span>订单记录</span>
          </div>
          <el-table
              :data="mergeLogList"
              style="width: 100%">
              <el-table-column label="" prop="title" width="150">
                <template slot-scope="scope">
                  <span>{{ scope.row.title }}</span>
                </template>
              </el-table-column>
              <el-table-column label="" prop="remark">
                <template slot-scope="scope">
                  <span>{{ scope.row.remark }}</span>
                </template>
              </el-table-column>
              <el-table-column label="" prop="addTime" align="center" width="150">
                <template slot-scope="scope">
                  <span>{{ scope.row.addTime | parseTime }}</span>
                </template>
              </el-table-column>
            </el-table>
        </el-card>
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
	.inline_table td,.inline_table th,.inline_table tr,.inline_table thead,.inline_table table,.inline_table th.is-leaf,.inline_table td {
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
    .miaosha{
      background-color: #ffaa00;
    }
</style>

<script>
import {getOrderList, getOrderMergeList, getOrderMergeDetail, mergeOrder, distributionOrder, sortOutOrder, cancelMerge} from '@/api/order'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime, moneyFormat} from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

const statusOptions = [
  { key: 1, text: '已合并' },
  { key: 2, text: '已配货' },
  { key: 3, text: '已分拣' },
  { key: 4, text: '已取消' },
]

const statusKeyValue = statusOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

const typeOptions = [
  { key: 1, text: '卖家责任' },
  { key: 2, text: '买家责任' },
  { key: 3, text: '运输责任' }
]

const typeKeyValue = typeOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

export default {
  name: 'merged',
  components: { Pagination },
  directives: { waves },
	filters: {
	  statusFilter(status) {
	    return statusKeyValue[status]
	  },
		typeFilter(status) {
		  return typeKeyValue[status]
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
        pageSize: 20,
      },
      deliveryTableKey: 0,
      deliveryListLoading: true,
      deliveryOrderList: [],
			multipleSelection: [],
      multipleDeliverySelection: [],
			activeName:'-1',
			dialogFormVisible: false,
      dialogDetailVisible: false,
      dialogDeliveryVisible: false,
			verifyForm:{},
      mergeDetail: {},
      mergeLogList: [],
      mergeDetailList: [],
      deliveryForm: {},
      lineOptions: [],
      currentLine: null,
      addressOptions: [],
      addressLoading: false,
      lineTimeOptions: []
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
			this.listQuery.dateFrom = renderTime(this.listQuery.startTime)
			this.listQuery.dateTo = renderTime(this.listQuery.endTime)

      getOrderMergeList(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.total
				this.listLoading = false
      })
    },
		handleSelectionChange(val) {
			this.multipleSelection = val;
     },
     handleSelectionDeliveryChange(val) {
     	this.multipleDeliverySelection = val;
      },
		handleClickTab(tab, event) {
			this.listQuery = {
			  page: 1,
			  pageSize: 20
			}
			this.activeName = tab.name
			if(tab.name == '-1'){
				this.listQuery.status = null
			}else {
				this.listQuery.status = tab.name
			}
			this.getList()
		},
    handleDetail(row){
      this.mergeDetailList = []
       getOrderMergeDetail({orderMergeId:row.id}).then((result) => {
         this.mergeDetail = result.data.orderMerge;
         this.mergeLogList = result.data.processList;
         this.mergeDetailList = result.data.mergeDetailList
       })
       this.dialogDetailVisible = true;
    },
    orderMergeToDis(row){
      distributionOrder({orderMergeId: row.id}).then((response) => {
        	this.getList()
          this.$notify({
            title: '成功',
            message: '已配货',
            type: 'success',
            duration: 2000
          })
        })
    },
    orderMergeToSortOut(row){
      sortOutOrder({orderMergeId: row.id}).then((response) => {
        	this.getList()
          this.$notify({
            title: '成功',
            message: '已分拣',
            type: 'success',
            duration: 2000
          })
        })
    },
    cancel(row){
      cancelMerge({orderMergeId: row.id}).then((response) => {
        	this.getList()
          this.$notify({
            title: '成功',
            message: '已取消',
            type: 'success',
            duration: 2000
          })
        })
    },
  }
}
</script>
