<template>
  <div class="app-container">
    <el-alert
        class="warn-content"
        title="系统收款账户说明"
        type="success"
        :closable="false">
       一般用户充值都是使用微信支付宝充值，如果想使用线下充值可配置线下充值通道
      </el-alert>
      <br/>
	  <el-tabs v-model="activeName" @tab-click="handleClickTab">
	  		<el-tab-pane label="网银" name="four"></el-tab-pane>
	      <el-tab-pane label="支付宝" name="first"></el-tab-pane>
	  	  <el-tab-pane label="微信" name="second"></el-tab-pane>
	      <el-tab-pane label="财付通" name="third"></el-tab-pane>
	  </el-tabs>
    <div class="filter-container">
			<el-button-group>
			<el-button class="filter-item" size="mini" type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button>
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
			<el-table-column label="收款账号" align="center">
			  <template slot-scope="scope">
         <span>{{ scope.row.account }}</span>
			  </template>
			</el-table-column>
			<el-table-column label="收款名称"  align="center">
			  <template slot-scope="scope">
			    <span>{{ scope.row.accountName }}</span>
			  </template>
			</el-table-column>
      <el-table-column label="收款二维码" align="center">
        <template slot-scope="scope">
          <el-image
            v-if="scope.row.qrCode"
             style="width: 50px; "
             :src="scope.row.qrCode"
             :preview-src-list="[scope.row.qrCode]"
            >
            </el-image>
        </template>
      </el-table-column>
      <el-table-column label="开户银行"  align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.bank }}</span>
        </template>
      </el-table-column>
      <el-table-column label="总收款"  align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.totalIncome | moneyFormat}}</span>
        </template>
      </el-table-column>
      <el-table-column label="今日预计收款"  align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.todayIncome | moneyFormat}}</span>
        </template>
      </el-table-column>
      <el-table-column label="开启状态"  align="center">
        <template slot-scope="scope">
          <el-switch :active-value="1" :inactive-value="0" v-model="scope.row.status" @change="statusChange(scope.row)" ></el-switch>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.actions')" class-name="small-padding" fixed="right" min-width="120">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />

		<!--添加编辑框-->
		<el-dialog :title="dialogType==='edit'?'编辑':'新增'" :visible.sync="dialogFormVisible">
		    <el-form ref="dataForm":model="account" label-width="150px" label-position="right" style="width: 100%; ">
          <el-form-item label="开启关闭" prop="status" :rules="[{ required: true, message: '请选择是否开启', trigger: 'change' }]">
            <el-switch v-model="account.status"
              :active-value="1"
              :inactive-value="0">
            </el-switch>
          </el-form-item>
					<el-form-item label="收款平台" prop="type" :rules="[{ required: true, message: '请选择收款平台', trigger: 'change' }]">
            <el-select v-model="account.type" placeholder="请选择" >
                <el-option label="支付宝" :value="1"> </el-option>
                <el-option label="微信" :value="2"> </el-option>
                <el-option label="财付通" :value="3"> </el-option>
                <el-option label="网银" :value="4"> </el-option>
              </el-select>
		      </el-form-item>

           <div v-if="account.type == 1">
             <el-form-item label="支付宝收款账号" prop="account" :rules="[{ required: true, message: '请填写支付宝收款账号', trigger: 'blur' }]">
                 <el-input v-model="account.account" style="width: 200px;"></el-input>
                 <span class="tips">*用户充值给你打款的收款账号，也是自动充值软件的支付宝登录账号</span>
             </el-form-item>
             <el-form-item label="支付宝收款账号姓名" prop="accountName" :rules="[{ required: true, message: '请填写支付宝收款账号姓名', trigger: 'blur' }]">
                 <el-input v-model="account.accountName" style="width: 200px;"></el-input>
                 <span class="tips">*支付宝收款账号的真实姓名，如果不想让用户看到，可不填写</span>
             </el-form-item>
             <el-form-item label="支付宝二维码" prop="qrCode" :rules="[{ required: true, message: '请上传支付宝二维码', trigger: 'blur' }]">
                 <Upload v-model="account.qrCode" />
                 <ImageSource :mul="false" v-model="account.qrCode" />
             </el-form-item>
           </div>

           <div v-if="account.type == 2">
             <el-form-item label="微信号" prop="account" :rules="[{ required: true, message: '请填写微信号', trigger: 'blur' }]">
                 <el-input v-model="account.account" style="width: 200px;"></el-input>
                 <span class="tips">*微信号，用户可以通过此微信号加你为好友</span>
             </el-form-item>
             <el-form-item label="微信收款姓名" prop="accountName">
                 <el-input v-model="account.accountName" style="width: 200px;"></el-input>
                 <span class="tips">*微信收款账号的真实姓名，如果不想让用户看到，可不填写</span>
             </el-form-item>
             <el-form-item label="微信二维码" prop="qrCode" :rules="[{ required: true, message: '请上传微信二维码', trigger: 'blur' }]">
                 <Upload v-model="account.qrCode" />
                 <ImageSource :mul="false" v-model="account.qrCode" />
             </el-form-item>
           </div>

           <div v-if="account.type == 3">
             <el-form-item label="财付通收款账号" prop="account" :rules="[{ required: true, message: '请填写财付通收款账号', trigger: 'blur' }]">
                 <el-input v-model="account.account" style="width: 200px;"></el-input>
                 <span class="tips">*用户充值给你打款的收款账号</span>
             </el-form-item>
             <el-form-item label="财付通收款账号姓名" prop="accountName">
                 <el-input v-model="account.accountName" style="width: 200px;"></el-input>
                 <span class="tips">*财付通收款账号的真实姓名，如果不想让用户看到，可不填写</span>
             </el-form-item>
             <el-form-item label="财付通二维码" prop="qrCode" :rules="[{ required: true, message: '请上传财付通二维码', trigger: 'blur' }]">
                 <Upload v-model="account.qrCode" />
                 <ImageSource :mul="false" v-model="account.qrCode" />
             </el-form-item>
           </div>


          <div v-if="account.type == 4">
            <el-form-item label="开户银行名称" prop="bank" :rules="[{ required: true, message: '请填写开户银行名称', trigger: 'blur' }]">
                <el-input v-model="account.bank" style="width: 200px;"></el-input>
                <span class="tips">*开户银行名称，如中国银行深圳分行营业部</span>
            </el-form-item>
            <el-form-item label="银行账号" prop="account" :rules="[{ required: true, message: '请填写银行账号', trigger: 'blur' }]">
                <el-input v-model="account.account" style="width: 200px;"></el-input>
                <span class="tips">*用户给你打款的银行账号</span>
            </el-form-item>
            <el-form-item label="开户姓名" prop="accountName" :rules="[{ required: true, message: '请填写开户姓名', trigger: 'blur' }]">
                <el-input v-model="account.accountName" style="width: 200px;"></el-input>
                <span class="tips">*开户姓名</span>
            </el-form-item>
          </div>
		    </el-form>
		    <div style="text-align:right;">
		      <el-button type="danger" @click="dialogFormVisible=false">取消</el-button>
		      <el-button type="primary" @click="confirm">确定</el-button>
		    </div>
		</el-dialog>

  </div>
</template>

<script>
import {getConfigAccountList, updateConfigAccount, deleteConfigAccount} from '@/api/config'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime, moneyFormat } from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
import Upload from '@/components/Upload/singleImage'
import ImageSource from '@/components/Upload/imageSource'

export default {
  name: 'configAccount',
  components: { Pagination,  Upload, ImageSource  },
  directives: { waves },
  data() {
    return {
      tableKey: 0,
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        pageSize: 20,
        type: 4
      },
      account: {},
      dialogFormVisible: false,
      dialogType: '',
			multipleSelection: [],
      activeName: 'four'
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      let queryParams = this.listQuery
      getConfigAccountList(queryParams).then(response => {
        this.list = response.data.list
        this.total = response.data.total
				this.listLoading = false
      })
    },
		handleCreate() {
		  this.dialogType = 'add'
		  this.dialogFormVisible = true
			this.account = {status : 1}
			this.$nextTick(() => {
			  this.$refs['dataForm'].clearValidate()
			})
		},
		handleUpdate(row) {
      this.dialogType = 'edit'
      this.dialogFormVisible = true
      this.account = Object.assign({}, row) // copy obj
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
		},
		confirm() {
			this.$refs['dataForm'].validate((valid) => {
			  if (valid) {
			    let formData =  Object.assign({}, this.account)
					formData.addTime = null
					formData.updTime = null
			    updateConfigAccount(formData).then(() => {
						this.getList()
			      this.dialogFormVisible = false
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
			    deleteConfigAccount(values.join(',')).then(() => {
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
    statusChange(row) {
      let formData = {
    		status: row.status,
    		id: row.id
    	}
    	updateConfigAccount(formData).then(() => {
    	  this.$notify({
    	    title: '成功',
    	    message: '修改成功',
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
        this.listQuery.type = 1
      }else if(tab.name == 'second'){
        this.listQuery.type = 2
      }else if(tab.name == 'third'){
      	this.listQuery.type = 3
      }else if(tab.name == 'four'){
      	this.listQuery.type = 4
      }
    	this.getList()
    },

  }
}
</script>
