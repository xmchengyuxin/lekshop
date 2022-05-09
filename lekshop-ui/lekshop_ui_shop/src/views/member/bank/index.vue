<template>
  <div class="app-container">
    <el-tabs v-model="activeName" @tab-click="handleClickTab">
    	  <el-tab-pane label="全部" name="first"></el-tab-pane>
    		<el-tab-pane label="待审核" name="second"></el-tab-pane>
        <el-tab-pane label="已通过" name="third"></el-tab-pane>
        <el-tab-pane label="已驳回" name="four"></el-tab-pane>
    </el-tabs>
    <div class="filter-container">
       <el-input v-model="listQuery.keyword" clearable placeholder="关键词" style="width: 200px;" class="filter-item"/>
       <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" circle @click="getList"></el-button>
       <br>
       <el-button-group>
       	<el-button class="filter-item" type="primary" size="mini" icon="el-icon-circle-check" @click="handleVerify">批量审核</el-button>
        <el-button class="filter-item" size="mini" type="danger" icon="el-icon-delete" @click="handleDelete">删除</el-button>
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
    <el-table-column type="selection" width="55" align="center"></el-table-column>
      <el-table-column label="用户名" prop="memberName" align="center">
        <template slot-scope="scope">
          <router-link :to="'/member/detail?memberId='+scope.row.memberId">
            <p class="link-type">{{ scope.row.memberName }}</p>
          </router-link>
        </template>
      </el-table-column>
      <el-table-column label="真实姓名" prop="accountName" align="center">
        <template slot-scope="scope">
          <p>{{ scope.row.accountName }}</p>
        </template>
      </el-table-column>
      <el-table-column label="身份证号码" prop="idCard" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.idCard}}</span>
        </template>
      </el-table-column>
      <el-table-column label="开户行" prop="bank" align="center" >
        <template slot-scope="scope">
          <span>{{ scope.row.bank}}{{ scope.row.bankSub}}</span>
        </template>
      </el-table-column>
      <el-table-column label="银行卡号" prop="bankAccount" align="center" >
        <template slot-scope="scope">
          <span>{{ scope.row.bankAccount}}</span>
        </template>
      </el-table-column>
      <el-table-column label="地区" prop="address" align="center" >
        <template slot-scope="scope">
          <span>{{ scope.row.province}}{{ scope.row.city}}{{ scope.row.area}}</span>
        </template>
      </el-table-column>
      <el-table-column label="申请时间"  align="center" prop="addTime">
        <template slot-scope="scope">
          <span>{{ scope.row.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" prop="status" align="center" width="80">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status == 1 ? 'success' : 'danger' ">{{ scope.row.status | statusFilter }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="拒绝原因" align="center" prop="remark">
        <template slot-scope="scope">
          <span>{{ scope.row.remark }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.actions')" class-name="small-padding" fixed="right">
        <template slot-scope="scope">
          <el-button-group>
            <el-button v-if="scope.row.status == 0" type="primary" icon="el-icon-circle-check" size="mini" @click="handleVerify(scope.row)">审核</el-button>
            <el-button type="primary" icon="el-icon-edit" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
          </el-button-group>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />


     <!--审核框-->
     <el-dialog title="审核" :visible.sync="dialogFormVisible">
         <el-form ref="dataForm" :rules="rules" :model="verifyForm" label-width="120px" label-position="right">
     			<el-form-item label="审核结果" prop="status">
     				 <el-radio-group v-model="verifyForm.status">
     				  <el-radio :label="1" border>通过</el-radio>
     				  <el-radio :label="2" border>不通过</el-radio>
     				</el-radio-group>
     			</el-form-item>
     			<el-divider>拒绝原因</el-divider>
     			<el-form-item label="拒绝原因" prop="reasonCheck">
     			  <el-select v-model="verifyForm.reasonCheck" placeholder="请选择" style="width: 100%;" @change="$set(verifyForm,'remark',verifyForm.reasonCheck);" clearable >
     			      <el-option
     			        key="0"
     			        value="此卡被没收,请于发卡方联系"
     			        label="此卡被没收,请于发卡方联系">
     			      </el-option>
     			      <el-option
     			        key="1"
     			        value="持卡人认证失败"
     			        label="持卡人认证失败">
     			      </el-option>
     			      <el-option
     			        key="2"
     			        value="无效卡号"
     			        label="无效卡号">
     			      </el-option>
     			      <el-option
     			        key="3"
     			        value="此卡无对应发卡方"
     			        label="此卡无对应发卡方">
     			      </el-option>
     			      <el-option
     			        key="4"
     			        value="该卡未初始化或睡眠卡"
     			        label="该卡未初始化或睡眠卡">
     			      </el-option>
     			      <el-option
     			        key="5"
     			        value="作弊卡,吞卡"
     			        label="作弊卡,吞卡">
     			      </el-option>
     			      <el-option
     			        key="6"
     			        value="发卡方不支持的交易"
     			        label="发卡方不支持的交易">
     			      </el-option>
     			      <el-option
     			        key="7"
     			        value="此卡已经挂失"
     			        label="此卡已经挂失">
     			      </el-option>
     			      <el-option
     			        key="8"
     			        value="持卡人信息有误"
     			        label="持卡人信息有误">
     			      </el-option>
     			      <el-option
     			        key="9"
     			        value="银行卡号码有误"
     			        label="银行卡号码有误">
     			      </el-option>
     			      <el-option
     			        key="10"
     			        value="其他"
     			        label="其他">
     			      </el-option>
     			    </el-select>
     			</el-form-item>
     			<el-form-item label="填写原因" prop="remark">
     			  <el-input type="textarea" v-model="verifyForm.remark" placeholder="请输入拒绝理由" :rows="6"/>
     			</el-form-item>
         </el-form>
         <div style="text-align:right;">
           <el-button type="danger" @click="dialogFormVisible=false">取消</el-button>
           <el-button type="primary" @click="confirm">确定</el-button>
         </div>
     </el-dialog>

     <!--编辑框-->
     <el-dialog title="编辑" :visible.sync="dialogEditVisible">
         <el-form ref="editForm" :rules="rules" :model="editForm" label-width="120px" label-position="right" style="width: 400px; margin-left:50px;">
           <el-form-item label="真实姓名" prop="accountName">
             <el-input v-model="editForm.accountName" placeholder="请输入真实姓名" />
           </el-form-item>
           <el-form-item label="身份证" prop="idCard">
             <el-input v-model="editForm.idCard" placeholder="请输入身份证" />
           </el-form-item>
           <el-form-item  prop="pca" label="开户地址">
             <el-cascader
               size="small"
               style="width:100%;"
               :options="addressOptions"
               v-model="editForm.pca"
               clearable
               >
             </el-cascader>
           </el-form-item>
           <el-form-item label="开户银行" prop="bank">
             <el-select v-model="editForm.bank" placeholder="银行列表" filterable clearable class="filter-item">
               <el-option v-for="item in bankOptions" :key="item.key" :label="item.text" :value="item.text">
               </el-option>
             </el-select>
           </el-form-item>
           <el-form-item label="支行名称" prop="bankSub">
             <el-input v-model="editForm.bankSub" placeholder="请输入支行名称"></el-input>
           </el-form-item>
           <el-form-item label="银行卡号" prop="bankAccount">
             <el-input v-model="editForm.bankAccount" placeholder="请输入银行卡号"></el-input>
           </el-form-item>
     			<el-form-item label="审核" prop="status">
     				 <el-radio-group v-model="editForm.status">
     				  <el-radio :label="1" >通过</el-radio>
     				  <el-radio :label="2" >不通过</el-radio>
     				</el-radio-group>
     			</el-form-item>
     			<el-form-item label="备注" prop="remark">
     			  <el-input type="textarea" v-model="editForm.remark" placeholder="请输入拒绝理由"/>
     			</el-form-item>
         </el-form>
         <div style="text-align:right;">
           <el-button type="danger" @click="dialogEditVisible=false">取消</el-button>
           <el-button type="primary" @click="editSubmit">确定</el-button>
         </div>
     </el-dialog>


  </div>
</template>

<script>
import {getMemberBankList, verifyMemberBank, updateMemberBank, deleteMemberBank} from '@/api/member'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime } from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
import Upload from '@/components/Upload/singleImage'
import {
		provinceAndCityDataPlus, regionData, CodeToText
	} from 'element-china-area-data'

const statusOptions = [
  { key: 0, text: '待审核' },
  { key: 1, text: '已通过' },
  { key: 2, text: '已驳回' },
]

// arr to obj ,such as { CN : "China", US : "USA" }
const statusKeyValue = statusOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})


const bankOptions = [
  { key: 'ICBC', text: '中国工商银行' },
  { key: 'ABC', text: '中国农业银行' },
  { key: 'BOC', text: '中国银行' },
  { key: 'CCB', text: '中国建设银行' },
  { key: 'CIB', text: '兴业银行' },
  { key: 'SPDB', text: '浦东发展银行' },
  { key: 'BCM', text: '交通银行' },
  { key: 'ECITIC', text: '中信银行' },
  { key: 'CEB', text: '中国光大银行' },
  { key: 'CMB', text: '招商银行' },
  { key: 'GDB', text: '广发银行' },
  { key: 'PSBC', text: '中国邮政储蓄银行' },
  { key: 'PAB', text: '平安银行' },
  { key: 'CMSB', text: '民生银行' }
]

const bankKeyValue = bankOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

export default {
  name: 'bankVerify',
  components: { Pagination, Upload},
  directives: { waves },
	filters: {
	  statusFilter(status) {
	    return statusKeyValue[status]
	  },
    bankFilter(status) {
      return bankKeyValue[status]
    }
	},
  data() {
    return {
      addressOptions: regionData,
      tableKey: 0,
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        pageSize: 20,
      },
			multipleSelection: [],
			statusOptions,
      dialogFormVisible: false,
      dialogEditVisible: false,
      verifyForm:{},
      editForm:{},
      rules: {
      	status: [{ required: true, message: '状态不能为空', trigger: 'change' }]
      },
      activeName:'first',
      bankOptions,
    }
  },
  created() {
    const memberName = this.$route.query && this.$route.query.memberName
    if(memberName){
      this.listQuery.keyword = memberName;
    }
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
        getMemberBankList(this.listQuery).then(response => {
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
       this.verifyForm = {status:1, ids: id}// copy obj
       this.dialogFormVisible = true
       this.$nextTick(() => {
         this.$refs['dataForm'].clearValidate()
       })
     },
     confirm() {
     	this.$refs['dataForm'].validate((valid) => {
     	  if (valid) {
     	    let formData ={
     				ids: this.verifyForm.ids,
     				status: this.verifyForm.status,
     				remark: this.verifyForm.remark
     			}
     	    verifyMemberBank(formData).then((response) => {
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
     handleDelete(){
     	const delVals = this.multipleSelection;
     	if(!delVals || delVals.length <= 0){
     		this.$message({
     		  message: '请至少选择一条数据',
     		  type: 'error',
     			duration: 2000
     		})
     		return;
     	}
     	this.$confirm('您确定要删除这些记录?', '提醒', {
     	  confirmButtonText: '确定',
     	  cancelButtonText: '取消',
     	  type: 'warning'
     	}).then(async() => {
     		  let values = [];
     			delVals.forEach(val =>{
     				values.push(val.id);
     			})
     	    deleteMemberBank(values.join(',')).then(() => {
     	    	this.getList()
     	      this.$notify({
     	        title: '成功',
     	        message: '删除成功',
     	        type: 'success',
     	        duration: 2000
     	      })
     	    })
     	  })
     	  .catch(err => { console.error(err) })
     },
     handleUpdate(row) {
       this.dialogEditVisible = true
       this.editForm = Object.assign({}, row) // copy obj
       this.editForm.pca = [this.editForm.provinceCode, this.editForm.cityCode, this.editForm.areaCode]
       this.$nextTick(() => {
         this.$refs['editForm'].clearValidate()
       })
     },
     editSubmit() {
     	this.$refs['editForm'].validate((valid) => {
     	  if (valid) {
     	    let formData =  Object.assign({}, this.editForm)
          if(this.editForm.pca && this.editForm.pca.length > 0){
          	formData.provinceCode = this.editForm.pca[0];
          	formData.cityCode = this.editForm.pca[1];
          	formData.areaCode = this.editForm.pca[2];
            formData.province = CodeToText[formData.provinceCode]
            formData.city = CodeToText[formData.cityCode]
            formData.area = CodeToText[formData.areaCode]
          }
     			formData.addTime = null
     			formData.updTime = null
     	    updateMemberBank(formData).then(() => {
     				this.getList()
     	      this.dialogEditVisible = false
     	      this.$notify({
     	        title: '成功',
     	        message: '保存成功',
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
   		 this.listQuery.status = 0
   	}else if(tab.name == 'third'){
   		 this.listQuery.status = 1
   	}else if(tab.name == 'four'){
   		 this.listQuery.status = 2
   	}
   	this.getList()
   },

  }
}
</script>
