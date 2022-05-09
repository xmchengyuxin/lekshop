<template>
  <div class="app-container">
    <el-tabs v-model="activeName" @tab-click="handleClickTab">
        <el-tab-pane label="全部" name="first"></el-tab-pane>
    	  <el-tab-pane label="待处理" name="second"></el-tab-pane>
        <el-tab-pane label="处理中" name="five"></el-tab-pane>
    		<el-tab-pane label="已审核" name="third"></el-tab-pane>
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
        <el-button v-if="activeName == 'first' || activeName == 'second'" class="filter-item" type="primary" size="mini" icon="el-icon-circle-check" @click="setDoingWithdraw">批量设处理中</el-button>
        <el-button v-if="activeName == 'first' || activeName == 'five'" class="filter-item" type="primary" size="mini" icon="el-icon-circle-check" @click="handleVerify">批量审核</el-button>
      	<el-button class="filter-item" type="info" :loading="downloadLoading" size="mini" icon="el-icon-download" @click="handleDownload(2)">导出支付宝</el-button>
        <el-button class="filter-item" type="info" :loading="downloadLoading" size="mini" icon="el-icon-download" @click="handleDownload(1)">导出银行</el-button>
        <el-button class="filter-item" type="info" :loading="downloadLoading" size="mini" icon="el-icon-download" @click="handleDownload(3)">导出微信</el-button>
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
      show-summary
      :summary-method="getSummaries"
    >
      <el-table-column type="selection" width="55" align="center" ></el-table-column>
      <el-table-column label="状态" width="150"  align="center" prop="status" >
        <template slot-scope="scope">
          <el-tag :type="scope.row.status == 1 ? 'success' : '' ">{{ scope.row.status | statusFilter}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="订单号" prop="orderNo" align="center" width="150">
        <template slot-scope="scope">
          <span>{{ scope.row.orderNo }}</span>
        </template>
      </el-table-column>
			<el-table-column label="用户名" width="100"  align="center" prop="memberName">
			  <template slot-scope="scope">
			    <router-link :to="'/member/detail?memberId='+scope.row.memberId">
			      <p class="link-type">{{ scope.row.memberName }}</p>
			    </router-link>
			  </template>
			</el-table-column>
      <el-table-column label="出款类型" width="100" align="center" prop="method">
        <template slot-scope="scope">
          <p>{{ scope.row.method == 1 ? '账户余额' : '佣金余额'}}</p>
        </template>
      </el-table-column>
      <el-table-column label="提现方式" width="100" align="center" prop="type">
        <template slot-scope="scope">
          <p>{{ scope.row.type | typeFilter}}</p>
        </template>
      </el-table-column>
      <el-table-column label="姓名" width="100" align="center" prop="realname">
        <template slot-scope="scope">
          <p>{{ scope.row.realname}}</p>
        </template>
      </el-table-column>
			<el-table-column label="开户银行" width="150" align="center" prop="bank">
			  <template slot-scope="scope">
			    <p v-if="scope.row.type == 1">{{ scope.row.bank}}</p>
			  </template>
			</el-table-column>
      <el-table-column label="开户账号" width="180" align="center" prop="bankAccount">
        <template slot-scope="scope">
          <p v-if="scope.row.type == 1">{{ scope.row.bankAccount}}</p>
        </template>
      </el-table-column>
      <el-table-column label="支付宝账号" width="180" align="center" prop="bankAccount">
        <template slot-scope="scope">
          <p v-if="scope.row.type == 2 && scope.row.bankAccount">{{ scope.row.bankAccount}}</p>
        </template>
      </el-table-column>
      <el-table-column label="二维码" width="150" align="center" prop="bankAccount">
        <template slot-scope="scope">
          <el-image
             v-if="scope.row.type == 3 && scope.row.bankAccount"
             style="width: 100px; "
             :src="scope.row.bankAccount"
             :preview-src-list="[scope.row.bankAccount]"
            >
            </el-image>
        </template>
      </el-table-column>
			<el-table-column label="申请提现金额" width="100" align="center"  prop="totalAmount">
			  <template slot-scope="scope">
			    <p style="color:red;">{{ scope.row.totalAmount  | moneyFormat }}</p>
			  </template>
			</el-table-column>
      <el-table-column label="提现手续费" width="100" align="center" prop="fee">
        <template slot-scope="scope">
          <p style="color:red;">{{ scope.row.fee  | moneyFormat }}</p>
        </template>
      </el-table-column>
      <el-table-column label="实际到账金额" width="100" align="center" prop="amount">
        <template slot-scope="scope">
          <p style="color:red;">{{ scope.row.amount  | moneyFormat }}</p>
        </template>
      </el-table-column>
      <el-table-column label="申请/审核时间" width="200" align="center" prop="addTime" >
        <template slot-scope="scope">
          <span v-if="scope.row.addTime">申请时间: {{ scope.row.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
          <br/>
          <span v-if="scope.row.updTime && scope.row.status == '1'">审核时间: {{ scope.row.updTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
			<el-table-column label="备注" prop="remark" min-width="150">
			  <template slot-scope="scope">
			    <span>{{ scope.row.remark}}</span>
			  </template>
			</el-table-column>
      <el-table-column :label="$t('table.actions')" class-name="small-padding" fixed="right" min-width="120">
        <template slot-scope="scope">
          <el-button v-if="scope.row.status == '3'" type="primary" icon="el-icon-circle-check" size="mini" @click="handleVerify(scope.row)">审核</el-button>
        </template>
      </el-table-column>
		</el-table>
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />

    <!--审核框-->
    <el-dialog title="审核提现" :visible.sync="dialogFormVisible">
        <el-form ref="dataForm" :rules="rules" :model="withdraw" label-width="80px" label-position="right">
    			<el-form-item label="审核结果" prop="status">
    				 <el-radio-group v-model="withdraw.status">
    				  <el-radio label="1" border>通过</el-radio>
    				  <el-radio label="2" border>不通过</el-radio>
    				</el-radio-group>
    			</el-form-item>
    			<el-divider>拒绝原因</el-divider>
    			<el-form-item label="拒绝原因" prop="reasonCheck">
    			  <el-select v-model="withdraw.reasonCheck" placeholder="请选择" style="width: 100%;" @change="$set(withdraw,'remark',withdraw.reasonCheck);" clearable >
    			      <el-option
    			        key="0"
    			        value="银行卡填写错误"
    			        label="银行卡填写错误">
    			      </el-option>
    			      <el-option
    			        key="10"
    			        value="其他"
    			        label="其他">
    			      </el-option>
    			    </el-select>
    			</el-form-item>
    			<el-form-item label="填写原因" prop="remark">
    			  <el-input type="textarea" v-model="withdraw.remark" placeholder="请输入拒绝理由" :rows="6"/>
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
import {getWithdrawList, countWithdraw, verifyWithdraw, setDoingWithdraw} from '@/api/fund'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime } from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

const statusOptions = [
  { key: '0', text: '待审核' },
  { key: '1', text: '通过' },
  { key: '2', text: '不通过' },
  { key: '3', text: '处理中' }
]

const typeOptions = [
  { key: 1, text: '银行' },
  { key: 2, text: '支付宝' },
  { key: 3, text: '微信' }
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
  name: 'withdraw',
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
			countAmount:{
        amount: 0,
        fee:0,
        totalAmount: 0
      },
			statusOptions,
			typeOptions,
      downloadLoading:false,
      dialogFormVisible: false,
      withdraw:{},
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
      getWithdrawList(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.total
				this.listLoading = false
      })
      countWithdraw(this.listQuery).then(response => {
        this.countAmount = response.data
        if(!this.countAmount){
          this.countAmount = {
            amount: 0,
            fee:0,
            totalAmount: 0
          }
        }
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
      this.withdraw = {status:'1', ids: id}// copy obj
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    confirm() {
    	this.$refs['dataForm'].validate((valid) => {
    	  if (valid) {
    	    let formData ={
    				ids: this.withdraw.ids,
    				status: this.withdraw.status,
    				remark: this.withdraw.remark
    			}
    	    verifyWithdraw(formData).then((response) => {
    				this.getList()
    	      this.dialogFormVisible = false
    				if(response.description && response.description != ''){
              let message = response.description.replaceAll('|', '\n')
    					this.$notify({
    					  title: '失败',
    					  message: message,
    					  type: 'error',
    					  duration: 2000
    					})
              return ;
    				}
    	      this.$notify({
    	        title: '成功',
    	        message: '审核提现成功',
    	        type: 'success',
    	        duration: 2000
    	      })
    	    })
    	  }
    	})
    },
    setDoingWithdraw(row) {
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
      let formData ={
      	ids: id,
      }
      setDoingWithdraw(formData).then((response) => {
      	this.getList()
        this.$notify({
          title: '成功',
          message: response.data,
          type: 'success',
          duration: 2000
        })
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
      }else if(tab.name == 'five'){
    		this.listQuery.status = '3'
      }
    	this.getList()
    },
    getSummaries(param) {
        const { columns, data } = param;
        const sums = ['', '合计', '', '', '', '', '', '', '', '', '', this.countAmount.totalAmount, this.countAmount.fee, this.countAmount.amount];

        return sums;
    },
    async getDownloadList(query) {
       let list;
       await getWithdrawList(query).then(response => {
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
      handleDownload(method) {
        this.downloadLoading = true
        import('@/vendor/Export2Excel').then(excel => {
          let list = [];
          let query = Object.assign({}, this.listQuery) // copy obj
          query.page = 1
          query.pageSize = 1000
          query.type = method
          if(this.listQuery.startTime && this.listQuery.endTime){
            query.dateFrom = renderTime(this.listQuery.startTime)
            query.dateTo = renderTime(this.listQuery.endTime)
          }
          this.getDownloadList(query).then(res=>{
             list = res;
             let table = this.$refs['table'];
             let tHeader = [];
             let multiHeader = [];
             let filterVal = [];
             let merges = [] //合并单元格的数据
             let data;
             let title = '';
             if(method == 1){
               title = '银行';
               tHeader = ['订单号','用户名', '姓名', '开户银行', '开户账号','金额', '备注']
               filterVal = ['orderNo','memberName', 'realname', 'bank', 'bankAccount','totalAmount', 'remark'];
               data =  list.map(v => filterVal.map((j, index) => {
                   return v[j]
               }))
             }else if(method == 2){
                title = '支付宝'
                multiHeader = [['支付宝批量付款文件模板（前面两行请勿删除）', '', '', '', '']];
                merges = ['A1:B1:C1:D1:E1'] //合并单元格的数据
                tHeader = ['序号(必填)','收款方支付宝账号(必填)', '收款方姓名(必填)', '金额(必填,单位,元)', '备注(选填)']
                filterVal = ['orderNo','bankAccount', 'realname', 'totalAmount', 'remark'];
                data =  list.map(v => filterVal.map((j, index) => {
                    return v[j]
                }))
             }else{
               title = '微信'
               tHeader = ['订单号','用户名', '姓名', '二维码', '金额', '备注']
               filterVal = ['orderNo','memberName', 'realname', 'bankAccount','totalAmount', 'remark'];
               data =  list.map(v => filterVal.map((j, index) => {
                   return v[j]
               }))
             }
             excel.export_json_to_excel({
               multiHeader: multiHeader,
               header: tHeader,
               data,
               filename: title+'提现记录',
               merges: merges,
               autoWidth: true,
               bookType: 'xls'
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
      let sums = this.getSummaries({columns:this.$refs['table'].columns, data:jsonData});
       data.push(sums);
       return data;
    },

  }
}
</script>
