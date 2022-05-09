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
      <el-table-column label="用户名" prop="memberName" align="center" width="100">
        <template slot-scope="scope">
          <router-link :to="'/member/detail?memberId='+scope.row.memberId">
            <p class="link-type">{{ scope.row.memberName }}</p>
          </router-link>
        </template>
      </el-table-column>
      <el-table-column label="真实姓名" prop="memberName" align="center" width="100">
        <template slot-scope="scope">
          <p>{{ scope.row.realname }}</p>
        </template>
      </el-table-column>
      <el-table-column label="身份证号码" prop="idCard" align="center" width="150">
        <template slot-scope="scope">
          <span>{{ scope.row.idCard}}</span>
        </template>
      </el-table-column>
      <el-table-column label="身份证正反面" prop="idcardFace" align="center" width="140">
        <template slot-scope="scope">
          <el-image
             style="width: 50px; height:50px;"
             fit="contain"
             :src="scope.row.idcardFace"
             :preview-src-list="[scope.row.idcardFace, scope.row.idcardBack, scope.row.idcardFaceHand]"
            >
            </el-image>

            <el-image
               style="width: 50px; height:50px;"
               fit="contain"
               :src="scope.row.idcardBack"
               :preview-src-list="[scope.row.idcardBack, scope.row.idcardFace, scope.row.idcardFaceHand]"
              >
              </el-image>
        </template>
      </el-table-column>
      <!-- <el-table-column label="身份证反面" prop="idcardBack" align="center" width="100">
        <template slot-scope="scope">
          <el-image
             style="width: 50px; height:50px;"
             fit="contain"
             :src="scope.row.idcardBack"
             :preview-src-list="[scope.row.idcardBack, scope.row.idcardFace, scope.row.idcardFaceHand]"
            >
            </el-image>
        </template>
      </el-table-column> -->
      <el-table-column label="手持身份证" prop="idcardFaceHand" align="center" width="150">
        <template slot-scope="scope">
          <span class="link-type" v-if="scope.row.idcardFaceHand != null" @click="viewVideo(scope.row)">查看视频</span>
        </template>
      </el-table-column>
      <el-table-column label="申请时间" width="150" align="center" prop="addTime">
        <template slot-scope="scope">
          <span>{{ scope.row.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="拒绝原因" align="center" prop="remark">
        <template slot-scope="scope">
          <span>{{ scope.row.reason }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" prop="status" align="center" width="70" fixed="right" >
        <template slot-scope="scope">
          <el-tag :type="scope.row.status == 1 ? 'success' : 'danger' ">{{ scope.row.status | statusFilter }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.actions')" class-name="small-padding" fixed="right" >
        <template slot-scope="scope">
          <el-button-group>
            <el-button v-if="scope.row.status == 0" type="primary" icon="el-icon-circle-check" size="mini" @click="handleVerify(scope.row)">审核</el-button>
            <el-button type="primary" icon="el-icon-edit" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
          </el-button-group>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />

 <!--视频弹窗-->
    <el-dialog title="视频" :visible.sync="dialogVideoVisible">
        	<video id="playVideos" width="30%" height="100%" webkit-playsinline="true" preload="auto" controls="controls">
    					 <source :src="videoUrl" type="video/mp4">
    			</video>
    </el-dialog>

     <!--审核框-->
     <el-dialog title="审核" :visible.sync="dialogFormVisible">
         <el-form ref="dataForm" :rules="rules" :model="verifyForm" label-width="120px" label-position="right" >
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
     			        value="请查看身份证绑定要求(打开个人中心界面--新手教学--5账号绑定--绑定身份证及要求)"
     			        label="请查看身份证绑定要求(打开个人中心界面--新手教学--5账号绑定--绑定身份证及要求)">
     			      </el-option>
                <el-option
                  key="1"
                  value="身份证照片模糊,反光或光线太暗,请重拍"
                  label="身份证照片模糊,反光或光线太暗,请重拍">
                </el-option>
                <el-option
                  key="2"
                  value="身份证拍反了,请重拍"
                  label="身份证拍反了,请重拍">
                </el-option>
                <el-option
                  key="3"
                  value="照片中脸部不能遮挡"
                  label="照片中脸部不能遮挡">
                </el-option>
                <el-option
                  key="4"
                  value="该地区注册人数已达上限, 暂停注册"
                  label="该地区注册人数已达上限, 暂停注册">
                </el-option>
                <el-option
                  key="5"
                  value="身份证姓名与平台注册姓名不一致"
                  label="身份证姓名与平台注册姓名不一致">
                </el-option>
                <el-option
                  key="6"
                  value="年龄不符合注册要求(仅限出生年月1965年1月-1998年12月注册)"
                  label="年龄不符合注册要求(仅限出生年月1965年1月-1998年12月注册)">
                </el-option>
                <el-option
                  key="7"
                  value="该身份证信息已在平台注册过账号,不能重复注册"
                  label="该身份证信息已在平台注册过账号,不能重复注册">
                </el-option>
                <el-option
                  key="8"
                  value="该身份证信息核实不到"
                  label="该身份证信息核实不到">
                </el-option>
                <el-option
                  key="9"
                  value="虚假资料"
                  label="虚假资料">
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
           <el-form-item label="真实姓名" prop="realname">
             <el-input v-model="editForm.realname" placeholder="请输入真实姓名" />
           </el-form-item>
           <el-form-item label="身份证" prop="idCard">
             <el-input v-model="editForm.idCard" placeholder="请输入身份证" />
           </el-form-item>
           <el-form-item label="身份证正面" prop="idcardFace">
             <Upload v-model="editForm.idcardFace" />
           </el-form-item>
           <el-form-item label="身份证反面" prop="idcardBack">
             <Upload v-model="editForm.idcardBack" />
           </el-form-item>
           <!-- <el-form-item label="手持身份证" prop="idcardFaceHand">
             <Upload v-model="editForm.idcardFaceHand" />
           </el-form-item> -->
     			<!-- <el-form-item label="审核" prop="status">
     				 <el-radio-group v-model="editForm.status">
     				  <el-radio :label="1" >通过</el-radio>
     				  <el-radio :label="2" >不通过</el-radio>
     				</el-radio-group>
     			</el-form-item>
     			<el-form-item label="备注" prop="remark">
     			  <el-input type="textarea" v-model="editForm.remark" placeholder="请输入拒绝理由"/>
     			</el-form-item> -->
         </el-form>
         <div style="text-align:right;">
           <el-button type="danger" @click="dialogEditVisible=false">取消</el-button>
           <el-button type="primary" @click="editSubmit">确定</el-button>
         </div>
     </el-dialog>


  </div>
</template>

<script>
import {getMemberRealnameList, verifyMemberRealname, updateMemberRealname, deleteMemberRealname} from '@/api/member'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime } from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
import Upload from '@/components/Upload/singleImage'

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

const healthTypeOptions = [
  { key: 1, text: '卡制' },
  { key: 2, text: '本制' },
  { key: 3, text: '纸质' },
  { key: 4, text: '电子版' },
]

// arr to obj ,such as { CN : "China", US : "USA" }
const healthTypeKeyValue = healthTypeOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

export default {
  name: 'realnameVerify',
  components: { Pagination, Upload},
  directives: { waves },
	filters: {
	  statusFilter(status) {
	    return statusKeyValue[status]
	  },
    typeFilter(status) {
      return typeKeyValue[status]
    },
    healthTypeFilter(status) {
      return healthTypeKeyValue[status]
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
			statusOptions,
      dialogFormVisible: false,
      dialogEditVisible: false,
      verifyForm:{},
      editForm:{},
      rules: {
      	status: [{ required: true, message: '状态不能为空', trigger: 'change' }]
      },
      activeName:'first',
      dialogVideoVisible:false,
      videoUrl:'',
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
        getMemberRealnameList(this.listQuery).then(response => {
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
     	    verifyMemberRealname(formData).then((response) => {
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
     	    deleteMemberRealname(values.join(',')).then(() => {
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
       this.$nextTick(() => {
         this.$refs['editForm'].clearValidate()
       })
     },
     editSubmit() {
     	this.$refs['editForm'].validate((valid) => {
     	  if (valid) {
     	    let formData =  Object.assign({}, this.editForm)
     			formData.addTime = null
     			formData.updTime = null
     	    updateMemberRealname(formData).then(() => {
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
   viewVideo(row){
   	this.videoUrl = ''
   	this.videoUrl = row.idcardFaceHand
     this.dialogVideoVisible = true
   	document.getElementById("playVideos").src = row.idcardFaceHand;
   },
   closeVideoDialog(){
   	document.getElementById("playVideos").src=''
   	this.dialogVideoVisible = false
   },

  }
}
</script>
