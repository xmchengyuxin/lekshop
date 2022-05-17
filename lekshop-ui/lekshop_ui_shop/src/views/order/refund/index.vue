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
			<el-table-column type="selection" width="55" align="center" ></el-table-column>
			<el-table-column label="退款用户" prop="memberName" align="center" width="150">
			  <template slot-scope="scope">
			    <span>{{ scope.row.memberName }}</span>
			  </template>
			</el-table-column>
			<el-table-column label="退款商品" prop="goodsInfo" align="center" min-width="250">
			  <template class="flex"  slot-scope="scope">
					<img  :src="scope.row.goodsMainImg" class="avatar flex f-s-0" style="height:100px;width:100px;float: left;">
					<p style="margin-left: 20px;margin-top: 0;text-align: left;" class="">{{scope.row.goodsName }}</p>
			  </template>
			</el-table-column>
			<el-table-column label="退款金额" prop="price" width="200">
			  <template slot-scope="scope">
			    <p>购买价格:<span style="color:red">¥{{ scope.row.originalAmount | moneyFormat }}</span></p>
					<p>退款金额:<span style="color:red">¥{{ scope.row.refundAmount | moneyFormat }}</span></p>
			  </template>
			</el-table-column>
			<el-table-column label="退款原因" width="300" align="center">
			  <template slot-scope="scope">
						<p>{{scope.row.reason}}</p>
						<p>{{scope.row.remark}}</p>
						<span class="link-type" @click="viewImg(scope.row)">查看退款凭证</span>
			  </template>
			</el-table-column>
			<el-table-column label="申请退款时间" width="170"  align="center">
			  <template slot-scope="scope">
			    <span>{{ scope.row.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}')  }}</span>
			  </template>
			</el-table-column>
			<el-table-column label="退款类型" width="150"  align="center">
			  <template slot-scope="scope">
					<el-tag>{{ scope.row.type | typeFilter }}</el-tag>
					<p v-if="scope.row.type == '2' ">{{scope.row.logisticsMame}}:{{scope.row.logisticsNo}}</p>
			  </template>
			</el-table-column>
			<el-table-column v-if="activeName == 'second' || activeName == 'third' " label="状态" width="100"  align="center">
			  <template slot-scope="scope">
					<el-tag>{{ scope.row.status | statusFilter }}</el-tag>
			  </template>
			</el-table-column>
			<el-table-column v-if="activeName == 'first' | activeName == 'fourth' " label="操作" width="100"  align="center" class-name="small-padding" fixed="right" min-width="100">
			  <template slot-scope="scope">
					<el-button v-if="scope.row.status == '0'" type="primary" icon="el-icon-circle-check" size="mini" @click="handleVerify(scope.row)">
					  审核
					</el-button>
			  </template>
			</el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />


		<!--审核框-->
		<el-dialog title="审核" :visible.sync="dialogFormVisible">
		    <el-form ref="dataForm" :model="verifyForm" label-width="80px" label-position="left" style="width: 400px; margin-left:50px;">
					<el-form-item label="审核" prop="status">
					  <el-radio-group v-model="verifyForm.status">
					    <el-radio :label="'1'" >通过</el-radio>
					    <el-radio :label="'2'" >驳回</el-radio>
					  </el-radio-group>
					</el-form-item>
					<el-form-item label="备注" prop="verifyReason">
						<el-input type="textarea" v-model="verifyForm.verifyReason" placeholder="请备注原因"></el-input>
					</el-form-item>
		    </el-form>
		    <div style="text-align:right;">
		      <el-button type="danger" @click="dialogFormVisible=false">取消</el-button>
		      <el-button type="primary" @click="verifyConfirm">确定</el-button>
		    </div>
		</el-dialog>

		<!--查看图片框-->
		<el-dialog title="查看退款证明" :visible.sync="dialogImageVisible">
			<el-form :inline="true"  class="demo-form-inline">
				<el-form-item v-for="item in imgList">
					<img @click="handlePictureCardPreview(item)"  :src="item" class="avatar" style="height:200px;">
				</el-form-item>
			</el-form>
		</el-dialog>

		<el-dialog :visible.sync="dialogBigImgVisible">
			<img width="100%" :src="bigImageUrl" alt="">
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
			dialogImageVisible:false,
			imgList:[],
			dialogBigImgVisible:false,
			bigImageUrl:'',
			dialogFormVisible: false,
			verifyForm:{}
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
        this.list = response.data.items
        this.total = response.data.totalSize
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
		viewImg(row){
			this.dialogImageVisible = true
			if(row.img){
				this.imgList = row.img.split("|")
			}
		},
		handlePictureCardPreview(file) {
			this.bigImageUrl = file;
			this.dialogBigImgVisible = true;
		},
		handleVerify(row){
			this.dialogFormVisible = true
			this.verifyForm = {status:'1', refundId:row.id}
		},
		verifyConfirm(){
			this.$confirm('您确定要审核这条数据?', '提醒', {
			  confirmButtonText: '确定',
			  cancelButtonText: '取消',
			  type: 'warning'
			}).then(async() => {
				if(this.verifyForm.status == '1'){
					agreeRefund(this.verifyForm).then((response) => {
					  	this.getList()
							this.dialogFormVisible = false
					    this.$notify({
					      title: '成功',
					      message: '审核成功',
					      type: 'success',
					      duration: 2000
					    })
					  })
				}else{
					refuseRefund(this.verifyForm).then((response) => {
					  	this.getList()
							this.dialogFormVisible = false
					    this.$notify({
					      title: '成功',
					      message: '审核成功',
					      type: 'success',
					      duration: 2000
					    })
					  })
				}
				})
			 .catch(err => { console.error(err) })
		},


  }
}
</script>
