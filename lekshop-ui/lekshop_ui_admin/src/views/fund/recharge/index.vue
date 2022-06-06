<template>
  <div class="app-container">
    <el-tabs v-model="activeName" @tab-click="handleClickTab">
        <el-tab-pane label="全部" name="first"></el-tab-pane>
    	  <el-tab-pane label="待审核" name="second"></el-tab-pane>
    		<el-tab-pane label="已通过" name="third"></el-tab-pane>
    		<el-tab-pane label="已驳回" name="four"></el-tab-pane>
    </el-tabs>
    <div class="filter-container">
			<el-input v-model="listQuery.orderNo" clearable placeholder="订单号" style="width: 200px;" class="filter-item" @keyup.enter.native="getList" />
			<el-input v-model="listQuery.memberName" clearable placeholder="用户名" style="width: 200px;" class="filter-item" @keyup.enter.native="getList" />
			<el-date-picker class="filter-item" v-model="listQuery.startTime" type="datetime" format="yyyy-MM-dd HH:mm:ss" placeholder="选择开始时间" />
			-
			<el-date-picker  class="filter-item"  v-model="listQuery.endTime" type="datetime" format="yyyy-MM-dd HH:mm:ss" placeholder="选择结束时间" />

			<el-button v-waves class="filter-item" type="primary" icon="el-icon-search" circle @click="getList"></el-button>
      <br>
      <el-button-group>
      	<el-button class="filter-item" type="primary" size="mini" icon="el-icon-circle-check" @click="handleVerify">批量审核</el-button>
      	<el-button class="filter-item" type="info" :loading="downloadLoading" size="mini" icon="el-icon-download" @click="handleDownload">导出当前</el-button>
      </el-button-group>
    </div>
		<el-table
      ref="table"
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
      <el-table-column label="订单号" prop="orderNo" align="center" width="150">
        <template slot-scope="scope">
          <span>{{ scope.row.orderNo }}</span>
        </template>
      </el-table-column>
			<el-table-column label="用户名" width="100"  align="center" prop="realname">
			  <template slot-scope="scope">
			    <router-link :to="'/member/detail?memberId='+scope.row.memberId">
			      <p class="link-type">{{ scope.row.memberName }}</p>
			    </router-link>
			  </template>
			</el-table-column>
      <el-table-column label="支付方式" width="70" align="center" prop="paymethod">
        <template slot-scope="scope">
          <span>{{ scope.row.paymethod | typeFilter}}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="付款截图" width="120" align="center" prop="img">
        <template slot-scope="scope">
          <el-image
             style="width: 100px; "
             :src="scope.row.img"
             :preview-src-list="[scope.row.img]"
            >
            </el-image>
        </template>
      </el-table-column> -->
			<el-table-column label="充值金额" width="70" align="center"  prop="amount">
			  <template slot-scope="scope">
			    <p style="color:red;">{{ scope.row.amount  | moneyFormat }}</p>
			  </template>
			</el-table-column>
      <!-- <el-table-column label="银行卡号" width="120" align="center" prop="remitAccount">
        <template slot-scope="scope">
          <span><span  style="color:red;">{{scope.row.remitAccount}}</span></span>
        </template>
      </el-table-column>
      <el-table-column label="打款人" width="100" align="center" prop="remitRealname">
        <template slot-scope="scope">
          <span>{{scope.row.remitRealname}}</span>
        </template>
      </el-table-column>
      <el-table-column label="打款银行" width="100" align="center" prop="remitBank">
        <template slot-scope="scope">
          <span>{{scope.row.remitBank}}</span>
        </template>
      </el-table-column> -->
      <el-table-column label="充值时间" width="150" align="center" prop="addTime" >
        <template slot-scope="scope">
          <span v-if="scope.row.addTime">{{ scope.row.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
			<el-table-column label="状态" width="70"  align="center" prop="status" >
			  <template slot-scope="scope">
			    <el-tag v-if="scope.row.status == '0'" type="danger">{{ scope.row.status | statusFilter}}</el-tag>
          <el-tag v-if="scope.row.status == '1'" type="success">{{ scope.row.status | statusFilter}}</el-tag>
          <el-tag v-if="scope.row.status == '2'" type="danger">{{ scope.row.status | statusFilter}}</el-tag>
          <el-tag v-if="scope.row.status == '3'" type="info">{{ scope.row.status | statusFilter}}</el-tag>
			  </template>
			</el-table-column>
			<el-table-column label="备注" prop="remark">
			  <template slot-scope="scope">
			    <span>{{ scope.row.remark}}</span>
			  </template>
			</el-table-column>
      <el-table-column :label="$t('table.actions')" class-name="small-padding" fixed="right">
        <template slot-scope="scope">
          <el-button v-if="scope.row.status == '0'" type="primary" icon="el-icon-circle-check" size="mini" @click="handleVerify(scope.row)">审核</el-button>
        </template>
      </el-table-column>
		</el-table>
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />

    <!--审核框-->
    <el-dialog title="审核充值" :visible.sync="dialogFormVisible">
        <el-form ref="dataForm" :rules="rules" :model="recharge" label-width="80px" label-position="right">
    			<el-form-item label="审核结果" prop="status">
    				 <el-radio-group v-model="recharge.status">
    				  <el-radio label="1" border>通过</el-radio>
    				  <el-radio label="2" border>不通过</el-radio>
    				</el-radio-group>
    			</el-form-item>
    			<el-divider>拒绝原因</el-divider>
    			<el-form-item label="拒绝原因" prop="reasonCheck">
    			  <el-select v-model="recharge.reasonCheck" placeholder="请选择" style="width: 100%;" @change="$set(recharge,'remark',recharge.reasonCheck);" clearable >
    			      <el-option
    			        key="0"
    			        value="未收到款项"
    			        label="未收到款项">
    			      </el-option>
    			      <el-option
    			        key="10"
    			        value="其他"
    			        label="其他">
    			      </el-option>
    			    </el-select>
    			</el-form-item>
    			<el-form-item label="填写原因" prop="remark">
    			  <el-input type="textarea" v-model="recharge.remark" placeholder="请输入拒绝理由" :rows="6"/>
    			</el-form-item>
        </el-form>
        <div style="text-align:right;">
          <el-button type="danger" @click="dialogFormVisible=false">取消</el-button>
          <el-button type="primary" @click="confirm">确定</el-button>
        </div>
    </el-dialog>

  </div>
</template>

<style scoped="scoped">
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
  .el-table--small p {
    margin: 0;
  }
</style>

<script>
import {getRechargeLog, verifyRecharge} from '@/api/fund'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime } from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

const statusOptions = [
  { key: '0', text: '待审核' },
  { key: '1', text: '通过' },
  { key: '2', text: '不通过' },
  { key: '3', text: '已取消' }
]

const typeOptions = [
  { key: 'zfbPay', text: '支付宝' },
  { key: 'wxPay', text: '微信' },
  { key: 'wyPay‘', text: '网银' }
]
// arr to obj ,such as { CN : "China", US : "USA" }
const statusKeyValue = statusOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

const typeKeyValue = typeOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

export default {
  name: 'recharge',
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
			statusOptions,
			typeOptions,
      downloadLoading:false,
      dialogFormVisible: false,
      recharge:{},
      rules: {
      	status: [{ required: true, message: '状态不能为空', trigger: 'change' }]
      },
      activeName:'first',
    }
  },
  created() {
    const memberName = this.$route.query && this.$route.query.memberName
    if(memberName){
      this.listQuery.memberName = memberName;
    }
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
			this.listQuery.dateFrom = renderTime(this.listQuery.startTime)
			this.listQuery.dateTo = renderTime(this.listQuery.endTime)
      getRechargeLog(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.total
				this.listLoading = false
      })
    },
    handleSelectionChange(val) {
    	this.multipleSelection = val;
    },
    handleVerify(row) {
    	let id = ''
    	if(row && row.id){
    		id = row.id
    	}else{
    		const verVals = this.multipleSelection;
    		if(!verVals || verVals.length <= 0){
    			this.$message({
    			  message: '请至少选择一条数据',
    			  type: 'error',
    				duration: 2000
    			})
    			return;
    		}
    		let values = [];
    		verVals.forEach(val =>{
    			values.push(val.id);
    		})
    		id = values.join(",")
    	}
      this.recharge = {status:'1', ids: id}// copy obj
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    confirm() {
    	this.$refs['dataForm'].validate((valid) => {
    	  if (valid) {
    	    let formData ={
    				ids: this.recharge.ids,
    				status: this.recharge.status,
    				remark: this.recharge.remark
    			}
    	    verifyRecharge(formData).then((response) => {
    				this.getList()
    	      this.dialogFormVisible = false
    				if(response.description && response.description != ''){
    					this.$notify({
    					  title: '失败',
    					  message: response.description,
    					  type: 'error',
    					  duration: 2000
    					})
    				}
    	      this.$notify({
    	        title: '成功',
    	        message: '审核成功',
    	        type: 'success',
    	        duration: 2000
    	      })
    	    })
    	  }
    	})
    },
    handleClickTab(tab, event) {
    	this.listQuery.page = 1
      this.listQuery.pageSize = 20
    	this.activeName = tab.name
    	if(tab.name == 'first'){
        this.listQuery.status = null
    	}else if(tab.name == 'second'){
        this.listQuery.status = '0'
    	}else if(tab.name == 'third'){
    		this.listQuery.status = '1'
    	}else if(tab.name == 'four'){
    		this.listQuery.status = '2'
      }
    	this.getList()
    },
    async getDownloadList(query) {
       let list;
       await getRechargeLog(query).then(response => {
          list = response.data.list
        })
       if(!list || list.length <= 0){
       	this.$notify({
       	  title: '失败',
       	  message: "暂无数据,导出失败",
       	  type: 'error',
       	  duration: 2000
       	})
       	this.downloadLoading = false
       	return;
       }
        return list;
      },
      handleDownload() {
        this.downloadLoading = true
        import('@/vendor/Export2Excel').then(excel => {
          let list = [];
          let query = Object.assign({}, this.listQuery) // copy obj
          query.page = 1
          query.pageSize = 1000
          if(this.listQuery.startTime && this.listQuery.endTime){
            query.dateFrom = renderTime(this.listQuery.startTime)
            query.dateTo = renderTime(this.listQuery.endTime)
          }
          this.getDownloadList(query).then(res=>{
             list = res;
             let table = this.$refs['table'];
             const tHeader = [];
             const filterVal = [];
             table.columns.forEach(item=>{
                 tHeader.push(item.label);
                 filterVal.push(item.property)
             })

             const data = this.formatJson(filterVal, list)
             excel.export_json_to_excel({
               header: tHeader,
               data,
               filename: '充值记录',
               autoWidth: true,
               bookType: 'xlsx'
             })
             this.downloadLoading = false
          });

         })
    },
    formatJson(filterVal, jsonData) {
      const data =  jsonData.map(v => filterVal.map((j, index) => {
         if(j == 'addTime'){
           return parseTime(v.addTime, '{y}-{m}-{d} {h}:{i}:{s}')
         }else if(j == 'status'){
           return statusKeyValue[v[j]]
         }else{
          return v[j]
         }
      }))
       return data;
    },

  }
}
</script>
