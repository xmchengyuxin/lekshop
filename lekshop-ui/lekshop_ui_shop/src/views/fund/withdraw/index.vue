<template>
  <div class="app-container">
    <el-tabs v-model="activeName" @tab-click="handleClickTab">
        <el-tab-pane label="全部" name="first"></el-tab-pane>
    	  <el-tab-pane label="申请中" name="second"></el-tab-pane>
        <el-tab-pane label="待打款" name="five"></el-tab-pane>
    		<el-tab-pane label="成功" name="third"></el-tab-pane>
    		<el-tab-pane label="已拒绝" name="four"></el-tab-pane>
    </el-tabs>
    <div class="filter-container">
			<el-input v-model="listQuery.orderNo" clearable placeholder="订单号" style="width: 200px;" class="filter-item" @keyup.enter.native="getList" />
			<el-date-picker class="filter-item" v-model="listQuery.startTime" type="datetime" format="yyyy-MM-dd HH:mm:ss" placeholder="选择开始时间" />
			-
			<el-date-picker  class="filter-item"  v-model="listQuery.endTime" type="datetime" format="yyyy-MM-dd HH:mm:ss" placeholder="选择结束时间" />

			<el-button v-waves class="filter-item" type="primary" icon="el-icon-search" circle @click="getList"></el-button>
      <br>
      <el-button-group>
        <el-button class="filter-item" type="danger" size="mini">余额 ￥{{balance}}</el-button>
        <el-button class="filter-item" type="primary" size="mini" icon="el-icon-circle-check" @click="handleWithdraw">立即提现</el-button>
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
      <el-table-column label="状态" width="80"  align="center" prop="status" >
        <template slot-scope="scope">
          <el-tag :type="scope.row.status == 1 ? 'success' : '' ">{{ scope.row.status | statusFilter}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="订单号" prop="orderNo" align="center" width="130">
        <template slot-scope="scope">
          <span>{{ scope.row.orderNo }}</span>
        </template>
      </el-table-column>
      <el-table-column label="出款类型" width="80" align="center" prop="method">
        <template slot-scope="scope">
          <p>{{ scope.row.method == 1 ? '账户余额' : '佣金余额'}}</p>
        </template>
      </el-table-column>
      <el-table-column label="提现方式" width="80" align="center" prop="type">
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
			    <p>{{ scope.row.bank}}</p>
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
             style="width: 30px; "
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
      <!-- <el-table-column :label="$t('table.actions')" class-name="small-padding" fixed="right" min-width="120">
        <template slot-scope="scope">
          <el-button v-if="scope.row.status == '3'" type="primary" icon="el-icon-circle-check" size="mini" @click="handleVerify(scope.row)">审核</el-button>
        </template>
      </el-table-column> -->
		</el-table>
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />

    <el-dialog title="提现" :visible.sync="dialogFormVisible">
        <el-form ref="dataForm" :rules="rules" :model="withdraw" label-width="120px" label-position="right" style="width: 100%;">
          <el-form-item label="" prop="amount">
            <MDinput v-model="withdraw.amount" :maxlength="100" name="amount" required style="width: 60%;">
              提现金额
            </MDinput>
            <p class="tips">可用余额  ￥{{balance}}</p>
          </el-form-item>

    			<el-form-item label="选择银行" prop="bankId">
    				 <el-select v-model="withdraw.bankId" placeholder="请选择" style="width: 60%;" @change="changeBank">
    				     <el-option
    				       v-for="(item,index) in bankOptions"
    				       :key="item.id"
    				       :label="item.bank"
    				       :value="index">
                   <span style="float: left; margin-right: 50px; font-weight: 550;">{{item.accountName}}</span>
    				       <span style="float: left; color: #8492a6; font-size: 13px">{{ item.bank }}</span>
    				       <span style="float: right; color: #8492a6; font-size: 13px">
                      <span v-if="item.bank == '支付宝'">{{ item.zfbAccount }}</span>
                      <span v-else-if="item.bank == '微信'">
                        <el-image
                          style="height: 30px"
                          :src="item.wxErweima"
                          :preview-src-list="[item.wxErweima]">
                        </el-image>
                      </span>
                      <span v-else>{{ item.bankAccount }}</span>
                  </span>
    				     </el-option>
    				   </el-select>
    			</el-form-item>
          <el-form-item label="真实姓名" prop="accountName">
            <el-input disabled v-model="withdraw.accountName" placeholder="请输入真实姓名" style="width: 60%;"/>
          </el-form-item>
          <el-form-item label="开户银行" prop="bank">
            <el-input disabled v-model="withdraw.bank" placeholder="请输入开户银行" style="width: 60%;"/>
          </el-form-item>
          <el-form-item v-if="withdraw.bank == '支付宝'" label="支付宝账号" prop="zfbAccount">
            <el-input disabled v-model="withdraw.zfbAccount" placeholder="请输入支付宝账号" style="width: 60%;"></el-input>
          </el-form-item>
          <el-form-item v-else-if="withdraw.bank == '微信'" label="微信收款码" prop="qrCode">
            <el-image
              style="height: 50px"
              :src="withdraw.qrCode"
              :preview-src-list="[withdraw.qrCode]">
            </el-image>
          </el-form-item>
          <el-form-item v-else label="银行卡号" prop="bankAccount">
            <el-input disabled v-model="withdraw.bankAccount" placeholder="请输入银行卡号" style="width: 60%;"></el-input>
          </el-form-item>
          <!-- <el-form-item label="提现金额" prop="amount">
            <el-input v-model="withdraw.amount" placeholder="请输入提现金额" style="width: 60%;"/>
            <p class="tips">可用余额  ￥{{balance}}</p>
          </el-form-item> -->
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
import {getWithdrawList, countWithdraw, applyWithdraw} from '@/api/fund'
import {getMemberAccount, getMemberBankList} from '@/api/member'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime } from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
import MDinput from '@/components/MDinput'

const statusOptions = [
  { key: '0', text: '申请中' },
  { key: '1', text: '成功' },
  { key: '2', text: '已拒绝' },
  { key: '3', text: '待打款' }
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
  components: { Pagination, MDinput },
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
      	bankId: [{ required: true, message: '请选择提现银行', trigger: 'change' }],
        amount: [{ required: true, message: '请填写提现金额', trigger: 'change' }]
      },
      activeName:'first',
      balance : 0,
      bankOptions: []
    }
  },
  created() {
    this.getList()
    this.getBalance();
  },
  methods: {
    getBalance() {
      getMemberAccount().then(response => {
        this.balance = response.data.amount
      })
    },
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
    handleWithdraw() {
      this.withdraw = {}
      getMemberBankList({status : 1, page : 1, pageSize : 9999}).then(response => {
        this.bankOptions = response.data.list
      })
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    confirm() {
    	this.$refs['dataForm'].validate((valid) => {
    	  if (valid) {
          this.withdraw.method = 1
          if(this.withdraw.bank == '支付宝'){
            this.withdraw.type = 2
          }else if(this.withdraw.bank == '微信'){
            this.withdraw.type = 3
          }else{
            this.withdraw.type = 1
          }
    	    applyWithdraw(this.withdraw).then((response) => {
    				this.getList()
            this.getBalance();
    	      this.dialogFormVisible = false
    	      this.$notify({
    	        title: '成功',
    	        message: '申请提现成功,等待处理',
    	        type: 'success',
    	        duration: 2000
    	      })
    	    })
    	  }
    	})
    },
    changeBank(index){
      let item = this.bankOptions[index]
      this.withdraw.accountName = item.accountName;
      this.withdraw.bank = item.bank;
      this.withdraw.bankAccount = item.bankAccount;
      if(this.withdraw.bank == '支付宝'){
        this.withdraw.bankAccount = item.zfbAccount;
        this.withdraw.zfbAccount = item.zfbAccount;
      }
      this.withdraw.qrCode = item.wxErweima;
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
