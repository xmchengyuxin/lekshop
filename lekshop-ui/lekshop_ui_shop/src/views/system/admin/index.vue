<template>
  <div class="app-container">
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
      <el-table-column :label="$t('table.id')" prop="id" align="center" width="65">
        <template slot-scope="scope">
          <span>{{ scope.row.id }}</span>
        </template>
      </el-table-column>
			<el-table-column label="头像" width="150px"  align="center">
			  <template slot-scope="scope">
			    <img :src="scope.row.headImg" class="avatar" style="height:50px;width:50px;">
			  </template>
			</el-table-column>
			<el-table-column label="账号" width="150px"  align="center">
			  <template slot-scope="scope">
			    <span>{{ scope.row.code }}</span>
			  </template>
			</el-table-column>
			<el-table-column label="名称" width="200px"  align="center">
			  <template slot-scope="scope">
			    <span class="link-type">{{ scope.row.name }}</span>
			  </template>
			</el-table-column>
			<el-table-column label="角色" width="150px"  align="center">
			  <template slot-scope="scope">
			    <span>{{ scope.row.typeName }}</span>
			  </template>
			</el-table-column>
			<el-table-column label="状态" width="100px"  align="center">
			  <template slot-scope="scope">
					<el-tag :type="scope.row.status == '-1' ? 'danger' : 'success' ">{{ scope.row.status | statusFilter}}</el-tag>
			  </template>
			</el-table-column>
      <el-table-column label="创建时间" width="170px" align="center" prop="addTime" >
        <template slot-scope="scope">
          <span>{{ scope.row.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
			<el-table-column label="修改时间" width="170px" align="center" prop="updTime">
			  <template slot-scope="scope">
			    <span>{{ scope.row.updTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
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
		<el-dialog :title="dialogType==='edit'?'编辑':'新增'" :visible.sync="dialogFormVisible" style="min-width: 800px;">
		    <el-form ref="dataForm" :rules="rules" :model="admin" label-width="80px" label-position="right" style="width: 400px; margin-left:50px;">
		      <el-form-item label="头像" prop="headImg" style="min-width: 800px;">
		        <Upload v-model="admin.headImg" />
             <ImageSource :mul="false" v-model="admin.headImg" />
		      </el-form-item>
					<el-form-item label="账号" prop="code">
		        <el-input v-model="admin.code" placeholder="请输入账号" />
		      </el-form-item>
					<el-form-item label="名称" prop="name">
					  <el-input v-model="admin.name" placeholder="请输入名称" />
					</el-form-item>
					<el-form-item label="密码" prop="password">
					  <el-input v-model="admin.password" placeholder="请输入密码" type="password"/>
					</el-form-item>
					<el-form-item label="状态" prop="status">
					  <el-select v-model="admin.status" class="filter-item" placeholder="请选择">
					    <el-option v-for="item in statusOptions" :key="item.key" :label="item.text" :value="item.key" />
					  </el-select>
					</el-form-item>
					<el-form-item label="角色" prop="typeId">
					  <el-select v-model="admin.typeId" class="filter-item" placeholder="请选择">
					    <el-option v-for="item in adminTypeList" :key="item.id" :label="item.name" :value="item.id" />
					  </el-select>
					</el-form-item>
		    </el-form>
		    <div style="text-align:right;">
		      <el-button type="danger" @click="dialogFormVisible=false">取消</el-button>
		      <el-button type="primary" @click="confirm">确定</el-button>
		    </div>
		</el-dialog>

  </div>
</template>

<script>
import {getAdminList, updateAdmin, deleteAdmin} from '@/api/admin'
import {getRoleList} from '@/api/role'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime } from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
import Upload from '@/components/Upload/singleImage'
import ImageSource from '@/components/Upload/imageSource'

const statusOptions = [
  { key: '1', text: '正常' },
  { key: '-1', text: '锁定' }
]

// arr to obj ,such as { CN : "China", US : "USA" }
const statusKeyValue = statusOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

export default {
  name: 'adminTable',
  components: { Pagination, Upload, ImageSource },
  directives: { waves },
	filters: {
	  statusFilter(status) {
	    return statusKeyValue[status]
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
        pageSize: 20
      },
      admin: {},
      dialogFormVisible: false,
      dialogType: '',
			multipleSelection: [],
			rules: {
			  code: [{ required: true, message: '账号不能为空', trigger: 'blur' }],
			  name: [{ required: true, message: '名称不能为空', trigger: 'blur' }],
				typeId: [{ required: true, message: '请选择角色', trigger: 'change' }],
				status: [{ required: true, message: '状态不能为空', trigger: 'change' }]
			},
			adminTypeList: {},
			statusOptions
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      getAdminList(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.total
				this.listLoading = false
      })
    },
		handleCreate() {
		  this.dialogStatus = 'add'
		  this.dialogFormVisible = true
			this.admin = {}
			getRoleList().then((result) => {
				this.adminTypeList = result.data.list;
			})
			this.$nextTick(() => {
			  this.$refs['dataForm'].clearValidate()
			})
		},
		handleUpdate(row) {
		  this.admin = Object.assign({}, row) // copy obj
			this.admin.password = ''
		  this.dialogStatus = 'edit'
		  this.dialogFormVisible = true
			getRoleList().then((result) => {
				this.adminTypeList = result.data.list;
			})
		  this.$nextTick(() => {
		    this.$refs['dataForm'].clearValidate()
		  })
		},
		confirm() {
			this.$refs['dataForm'].validate((valid) => {
			  if (valid) {
			    let formData =Object.assign({}, this.admin)
					formData.addTime = null
					formData.updTime = null
			    updateAdmin(formData).then(() => {
						this.getList()
			      this.dialogFormVisible = false
			      this.$notify({
			        title: '成功',
			        message: '管理员保存成功',
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
			    deleteAdmin(values.join(',')).then(() => {
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
		}

  }
}
</script>
