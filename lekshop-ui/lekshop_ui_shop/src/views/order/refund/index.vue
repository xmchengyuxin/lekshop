<template>
  <div class="app-container">
		<el-tabs v-model="activeName" @tab-click="handleClickTab">
			  <el-tab-pane label="全部" name="-1"></el-tab-pane>
				<el-tab-pane label="申请退款" name="0"></el-tab-pane>
				<el-tab-pane label="等待买家退货" name="1"></el-tab-pane>
				<el-tab-pane label="买家已退货" name="2"></el-tab-pane>
        <el-tab-pane label="已拒绝" name="3"></el-tab-pane>
        <el-tab-pane label="平台介入" name="4"></el-tab-pane>
        <el-tab-pane label="退款完成" name="5"></el-tab-pane>
        <el-tab-pane label="退款已关闭" name="6"></el-tab-pane>
		</el-tabs>
    <div class="filter-container">
			 <el-input v-model="listQuery.refundNo" clearable placeholder="退款单号" style="width: 200px;" class="filter-item" @keyup.enter.native="getList" />
			 <el-input v-model="listQuery.memberName" clearable placeholder="退款用户" style="width: 200px;" class="filter-item" @keyup.enter.native="getList" />
			 <el-date-picker class="filter-item" v-model="listQuery.startTime" type="datetime" format="yyyy-MM-dd HH:mm:ss" placeholder="选择开始时间" />
			 -
			 <el-date-picker  class="filter-item"  v-model="listQuery.endTime" type="datetime" format="yyyy-MM-dd HH:mm:ss" placeholder="选择结束时间" />
			 <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" circle @click="getList"></el-button>
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
      <el-table-column label="申请人" prop="memberName" align="center" width="130">
        <template slot-scope="scope">
          <span>{{ scope.row.memberName }}</span>
        </template>
      </el-table-column>
			<el-table-column label="退款商品" prop="goodsInfo" align="center" min-width="250">
			  <template slot-scope="scope">
          <div  class="flex  padding-tb10">
            <el-image
            class="flex f-s-0 margin-r12"
                style="height: 40px"
                :src="scope.row.goodsMainImg"
                :preview-src-list="[scope.row.goodsMainImg]">
              </el-image>
              <div class="flex f-c">
                <span class="line2">{{scope.row.goodsName}}</span>
              </div>
          </div>
			  </template>
			</el-table-column>
			<el-table-column label="退款金额" prop="price" width="80" align="center">
			  <template slot-scope="scope">
					<span style="color:red">¥{{ scope.row.refundAmount | moneyFormat }}</span>
			  </template>
			</el-table-column>
      <el-table-column label="退款类型" width="90"  align="center">
        <template slot-scope="scope">
      		<el-tag type="danger">{{ scope.row.refundInd | typeFilter }}</el-tag>
        </template>
      </el-table-column>
			<el-table-column label="退款原因" width="150" align="center">
			  <template slot-scope="scope">
						<p>{{scope.row.reason}}</p>
        </template>
			</el-table-column>
      <el-table-column label="图片" width="200">
        <template slot-scope="scope">
      			<div v-if="scope.row.img">
      			<el-image
      			    v-for="item in scope.row.img.split('|')"
      			    style="height: 30px;margin-left: 5px;"
      			    :src="item"
      			    :preview-src-list="scope.row.img.split('|')">
      			  </el-image>
      			</div>
          </template>
      </el-table-column>
			<el-table-column label="申请退款时间" width="160"  align="center">
			  <template slot-scope="scope">
			    <span>{{ scope.row.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}')  }}</span>
			  </template>
			</el-table-column>

			<el-table-column label="状态" width="120"  align="center">
			  <template slot-scope="scope">
          <el-tag effect="plain" v-if="scope.row.status == 0" type="danger">{{ scope.row.status | statusFilter }}</el-tag>
          <el-tag effect="plain" v-if="scope.row.status == 1" type="warning">{{ scope.row.status | statusFilter }}</el-tag>
          <el-tag effect="plain" v-if="scope.row.status == 2" type="info">{{ scope.row.status | statusFilter }}</el-tag>
          <el-tag effect="plain" v-if="scope.row.status == 3" type="success">{{ scope.row.status | statusFilter }}</el-tag>
          <el-tag effect="plain" v-if="scope.row.status == 4" type="danger">{{ scope.row.status | statusFilter }}</el-tag>
          <el-tag  v-if="scope.row.status == 5" type="success">{{ scope.row.status | statusFilter }}</el-tag>
          <el-tag  v-if="scope.row.status == 6" type="danger">{{ scope.row.status | statusFilter }}</el-tag>
			  </template>
			</el-table-column>
			<el-table-column label="操作" class-name="small-padding" fixed="right" width="220">
			  <template slot-scope="scope">
          <el-tooltip class="item" effect="dark" content="查看详情" placement="top">
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleRefundDetail(scope.row)"></el-button>
          </el-tooltip>
          <el-popconfirm
            v-if="scope.row.status == 0"
            confirm-button-text='好的'
            cancel-button-text='不用了'
            icon="el-icon-info"
            icon-color="red"
            title="同意退款后, 款项会直接退还给买家, 确定同意退款吗？"
            @confirm="agreeRefund(scope.row)"
          >
            <el-button slot="reference"  type="success" icon="el-icon-circle-check" size="mini">
              同意
            </el-button>
          </el-popconfirm>
          <el-button v-if="scope.row.status == 0" type="danger" icon="el-icon-close" size="mini" @click="handleVerify(scope.row)">
            拒绝
          </el-button>
          <el-popconfirm
            v-if="scope.row.status == 2"
            confirm-button-text='好的'
            cancel-button-text='不用了'
            icon="el-icon-info"
            icon-color="red"
            title="确定收到买家退货？确认后此退货订单自动完成退款！"
            @confirm="confirmReceiveByShop(scope.row)"
          >
            <el-button  slot="reference"  type="success" icon="el-icon-circle-check" size="mini">
              确认收货
            </el-button>
          </el-popconfirm>
			  </template>
			</el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />


		<el-dialog title="拒绝退款" :visible.sync="dialogFormVisible">
		    <el-form ref="verifyForm" :model="verifyForm" label-width="80px" label-position="left" style="width: 100%;">
					<el-form-item label="拒绝原因" prop="reason" :rules="[{ required: true, message: '请输入拒绝原因', trigger: 'blur' }]">
						<el-input :rows="4" type="textarea" v-model="verifyForm.reason" placeholder="请输入拒绝原因"></el-input>
					</el-form-item>
		    </el-form>
		    <div style="text-align:right;">
		      <el-button type="danger" @click="dialogFormVisible=false">取消</el-button>
		      <el-button type="primary" @click="refuseRefund">确定</el-button>
		    </div>
		</el-dialog>

    <!--订单详情-->
    	<el-dialog title="退款详情" :visible.sync="dialogDetailVisible" width="60%">
        <el-card class="box-card-dialog" shadow="hover">
          <div slot="header" class="clearfix">
            <span>商品信息</span>
          </div>
          <div style="margin-bottom: 10px;">
            <span>退款单号： {{refundDetail.refundNo}}</span>
            <span style="margin-left: 20px;">买家：<span style="font-weight: 600;">{{refundDetail.memberName}}</span></span>
          </div>

          <el-table
              :data="refundDetailList"
              border
              style="width: 100%">
              <el-table-column label="主图" prop="goodsImg" align="center" width="100">
                <template slot-scope="scope">
                  <el-image
                      style="height: 50px"
                      :src="scope.row.goodsMainImg"
                      :preview-src-list="[scope.row.goodsMainImg]">
                    </el-image>
                </template>
              </el-table-column>
              <el-table-column label="商品名称" prop="goodsName">
                <template slot-scope="scope">
                  <span class="line1">{{ scope.row.goodsName }}</span>
                </template>
              </el-table-column>
              <el-table-column label="购买规格" prop="goodsParamName" align="center" width="100">
                <template slot-scope="scope">
                  <span>{{ scope.row.goodsParamName }}</span>
                </template>
              </el-table-column>
              <el-table-column label="原价" prop="originalAmount" align="center" width="70">
                <template slot-scope="scope">
                  <span>{{ scope.row.originalAmount | moneyFormat}}</span>
                </template>
              </el-table-column>
              <el-table-column label="退款金额" prop="refundAmount" align="center" width="70">
                <template slot-scope="scope">
                  <span>{{ scope.row.refundAmount | moneyFormat }}</span>
                </template>
              </el-table-column>
            </el-table>
            <div style="margin-top: 10px; font-weight: bold; font-size: 15px; float: right;">
              <span >退款金额： <span style="color: red;">￥ {{refundDetail.refundAmount | moneyFormat}}</span></span>
            </div>
            <div style="margin-top: 10px;">
              <p style="height: 40px;">退款状态：
                <el-tag effect="plain" v-if="refundDetail.status == 0" type="danger">{{ refundDetail.status | statusFilter }}</el-tag>
                <el-tag effect="plain" v-if="refundDetail.status == 1" type="warning">{{ refundDetail.status | statusFilter }}</el-tag>
                <el-tag effect="plain" v-if="refundDetail.status == 2" type="info">{{ refundDetail.status | statusFilter }}</el-tag>
                <el-tag effect="plain" v-if="refundDetail.status == 3" type="success">{{ refundDetail.status | statusFilter }}</el-tag>
                <el-tag effect="plain" v-if="refundDetail.status == 4" type="danger">{{ refundDetail.status | statusFilter }}</el-tag>
                <el-tag  v-if="refundDetail.status == 5" type="success">{{ refundDetail.status | statusFilter }}</el-tag>
                <el-tag  v-if="refundDetail.status == 6" type="danger">{{ refundDetail.status | statusFilter }}</el-tag>
              </p>
              <p style="height: 30px;">申请时间:  &nbsp;&nbsp;&nbsp;{{ refundDetail.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}')  }}</p>
              <p style="height: 30px;">退款时间:  &nbsp;&nbsp;&nbsp;{{refundDetail.refundTime | parseTime}}</p>
            </div>
        </el-card>
        <br>
        <el-card class="box-card-dialog" shadow="hover">
          <div slot="header" class="clearfix">
            <span>退款记录</span>
          </div>
          <el-table
              :data="refundLogList"
              style="width: 100%">
              <el-table-column label="" prop="headImg" width="50">
                <template slot-scope="scope">
                  <el-avatar shape="square" size="small" :src="scope.row.headImg"></el-avatar>
                </template>
              </el-table-column>
              <el-table-column label="" prop="operatorName" width="150">
                <template slot-scope="scope">
                  <span>{{ scope.row.operatorName }}</span>
                </template>
              </el-table-column>
              <el-table-column label="" prop="content">
                <template slot-scope="scope">
                  <span>{{ scope.row.content }}</span>
                </template>
              </el-table-column>
              <el-table-column label="" prop="addTime" align="center" width="150">
                <template slot-scope="scope">
                  <span>{{ scope.row.addTime | parseTime }}</span>
                </template>
              </el-table-column>
            </el-table>
        </el-card>
        <br>
        <el-card class="box-card-dialog" shadow="hover">
          <div slot="header" class="clearfix">
            <span>退款详情</span>
          </div>
          <el-descriptions title="" :column="1" border>
              <el-descriptions-item>
                <template slot="label">
                  退款用户
                </template>
                {{refundDetail.memberName}}
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  退款类型
                </template>
                <el-tag type="danger">{{ refundDetail.refundInd | typeFilter }}</el-tag>
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  退款原因
                </template>
                 {{refundDetail.reason}}
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  图片
                </template>
                  <div v-if="refundDetail.img">
                  <el-image
                      v-for="item in refundDetail.img.split('|')"
                      style="height: 30px;margin-left: 5px;"
                      :src="item"
                      :preview-src-list="refundDetail.img.split('|')">
                    </el-image>
                  </div>
              </el-descriptions-item>
            </el-descriptions>
        </el-card>

        <br/>
        <el-card class="box-card-dialog" shadow="hover">
          <div slot="header" class="clearfix">
            <span>快递信息</span>
          </div>
          <el-descriptions title="" :column="1" border>
              <el-descriptions-item>
                <template slot="label">
                  快递方式
                </template>
                {{refundDetail.deliveryType}}
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  快递单号
                </template>
                {{refundDetail.deliveryNo}}
              </el-descriptions-item>
            </el-descriptions>
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
import {getRefundList, getRefund, agreeRefund, refuseRefund, confirmReceiveByShop} from '@/api/order'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime, moneyFormat} from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

const statusOptions = [
  { key: 0, text: '申请退款' },
  { key: 1, text: '等待买家退货' },
  { key: 2, text: '买家已退货' },
  { key: 3, text: '已拒绝' },
  { key: 4, text: '平台介入' },
  { key: 5, text: '退款完成' },
  { key: 6, text: '退款已关闭' },
]

const statusKeyValue = statusOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

const typeOptions = [
  { key: 1, text: '仅退款' },
  { key: 2, text: '退货退款' }
]

const typeKeyValue = typeOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

export default {
  name: 'refundList',
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
			multipleSelection: [],
			activeName:'-1',
			dialogFormVisible: false,
      dialogDetailVisible: false,
			verifyForm:{},
      refundDetail: {},
      refundLogList: [],
      refundDetailList: []
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

      getRefundList(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.total
				this.listLoading = false
      })
    },
		handleSelectionChange(val) {
			this.multipleSelection = val;
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
		handleVerify(row){
			this.dialogFormVisible = true
			this.verifyForm = {refundId:row.id}
		},
    agreeRefund(row){
      agreeRefund({refundId: row.id}).then((response) => {
        	this.getList()
          this.$notify({
            title: '成功',
            message: '您已同意退款',
            type: 'success',
            duration: 2000
          })
        })
    },
    refuseRefund(){
      this.$refs['verifyForm'].validate((valid) => {
        if (valid) {
          refuseRefund(this.verifyForm).then((response) => {
            	this.getList()
              this.dialogFormVisible = false
              this.$notify({
                title: '成功',
                message: '您已拒绝退款',
                type: 'success',
                duration: 2000
              })
            })
        }
      })
    },
    confirmReceiveByShop(row){
      confirmReceiveByShop({refundId: row.id}).then((response) => {
        	this.getList()
          this.$notify({
            title: '成功',
            message: '您已确认收货',
            type: 'success',
            duration: 2000
          })
        })
    },
    handleRefundDetail(row){
      this.refundDetailList = []
       getRefund({refundId:row.id}).then((result) => {
         this.refundDetail = result.data.refund;
         this.refundLogList = result.data.refundLogList;
         this.refundDetailList.push(result.data.refund)
       })
       this.dialogDetailVisible = true;
    },
  }
}
</script>
