<template>
  <div class="app-container">
    <div class="filter-container">
       <el-button-group>
       	<el-button class="filter-item" type="primary" size="mini" icon="el-icon-circle-check" @click="handleCreate">添加</el-button>
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
      <el-table-column label="真实姓名" prop="accountName" align="center">
        <template slot-scope="scope">
          <p>{{ scope.row.accountName }}</p>
        </template>
      </el-table-column>
      <el-table-column label="银行" prop="bank" align="center" >
        <template slot-scope="scope">
          <span>{{ scope.row.bank}}{{ scope.row.bankSub}}</span>
        </template>
      </el-table-column>
      <el-table-column label="银行卡号" prop="bankAccount" align="center" >
        <template slot-scope="scope">
          <span>{{ scope.row.bankAccount}}</span>
        </template>
      </el-table-column>
      <el-table-column label="支付宝账号" prop="bankAccount" align="center" >
        <template slot-scope="scope">
          <span>{{ scope.row.zfbAccount}}</span>
        </template>
      </el-table-column>
      <el-table-column label="微信收款码" prop="wxErweima" align="center" >
        <template slot-scope="scope">
          <el-image
              v-if="scope.row.wxErweima"
              style="height: 30px"
              :src="scope.row.wxErweima"
              :preview-src-list="[scope.row.wxErweima]">
            </el-image>
        </template>
      </el-table-column>
      <el-table-column label="创建时间"  align="center" prop="addTime">
        <template slot-scope="scope">
          <span>{{ scope.row.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.actions')" class-name="small-padding" fixed="right" width="60">
        <template slot-scope="scope">
          <el-button-group>
            <el-tooltip class="item" effect="dark" content="编辑" placement="top">
               <el-button type="primary" size="mini" icon="el-icon-edit" @click="handleUpdate(scope.row)"></el-button>
            </el-tooltip>
          </el-button-group>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />


     <!--编辑框-->
     <el-dialog title="添加编辑提现账户" :visible.sync="dialogEditVisible">
         <el-form ref="editForm" :rules="rules" :model="editForm" label-width="120px" label-position="right" style="width: 100%;">
           <el-form-item label="真实姓名" prop="accountName">
             <el-input v-model="editForm.accountName" placeholder="请输入真实姓名" style="width: 60%;"/>
           </el-form-item>
           <el-form-item label="开户银行" prop="bank">
             <el-select v-model="editForm.bank" placeholder="银行列表" filterable clearable class="filter-item" style="width: 60%;">
               <el-option v-for="item in bankOptions" :key="item.key" :label="item.text" :value="item.text">
               </el-option>
             </el-select>
           </el-form-item>
           <el-form-item v-if="editForm.bank == '支付宝'" label="支付宝账号" prop="zfbAccount">
             <el-input v-model="editForm.zfbAccount" placeholder="请输入支付宝账号" style="width: 60%;"></el-input>
           </el-form-item>
           <el-form-item v-else-if="editForm.bank == '微信'" label="微信收款码" prop="wxErweima">
             <Upload v-model="editForm.wxErweima" />
           </el-form-item>
           <el-form-item v-else label="银行卡号" prop="bankAccount">
             <el-input v-model="editForm.bankAccount" placeholder="请输入银行卡号" style="width: 60%;"></el-input>
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
import {getMemberBankList, updateMemberBank, deleteMemberBank} from '@/api/member'
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
  { key: 'ZFB', text: '支付宝' },
  { key: 'WX', text: '微信' },
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
  name: 'bank',
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
      	accountName: [{ required: true, message: '持卡人姓名不能为空', trigger: 'blur' }],
        bank: [{ required: true, message: '请选择银行', trigger: 'change' }],
        bankAccount: [{ required: true, message: '账号不能为空', trigger: 'blur' }],
        zfbAccount: [{ required: true, message: '支付宝账号不能为空', trigger: 'blur' }],
        wxErweima: [{ required: true, message: '微信收款码不能为空', trigger: 'blur' }],
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
     handleCreate(row) {
       this.dialogEditVisible = true
       this.editForm = {} // copy obj
       this.$nextTick(() => {
         this.$refs['editForm'].clearValidate()
       })
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
