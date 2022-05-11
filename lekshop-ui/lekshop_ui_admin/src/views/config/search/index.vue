<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="listQuery.keyword" clearable placeholder="关键词" style="width: 200px;" class="filter-item" @keyup.enter.native="getList" />
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" circle @click="getList"></el-button>
      <br>
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
			<el-table-column label="关键字" align="center">
			  <template slot-scope="scope">
         <span>{{ scope.row.name }}</span>
			  </template>
			</el-table-column>
			<el-table-column label="搜索次数"  align="center">
			  <template slot-scope="scope">
			    <span>{{ scope.row.num }}</span>
			  </template>
			</el-table-column>
      <el-table-column label="标签" align="center">
        <template slot-scope="scope">
         <span>{{ scope.row.target }}</span>
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
		    <el-form ref="dataForm":model="config" label-width="150px" label-position="right" style="width: 100%; margin-left: 50px;">
          <el-form-item label="开启关闭" prop="status" :rules="[{ required: true, message: '请选择是否开启', trigger: 'change' }]">
            <el-switch v-model="config.status"
              :active-value="1"
              :inactive-value="0">
            </el-switch>
          </el-form-item>

             <el-form-item label="关键字" prop="name" :rules="[{ required: true, message: '请填写关键字', trigger: 'blur' }]">
                 <el-input v-model="config.name" style="width: 60%;"></el-input>
             </el-form-item>
             <el-form-item label="搜索次数" prop="num" :rules="[{ required: true, message: '请填写搜索次数', trigger: 'blur' }]">
                 <el-input v-model="config.num" style="width: 60%;"></el-input>
             </el-form-item>
             <el-form-item label="标签" prop="target">
                 <el-input v-model="config.target" style="width: 60%;"></el-input>
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
import {getConfigSearchList, updateConfigSearch, deleteConfigSearch} from '@/api/config'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime, moneyFormat } from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

export default {
  name: 'configSearch',
  components: { Pagination },
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
      },
      config: {},
      dialogFormVisible: false,
      dialogType: '',
			multipleSelection: [],
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      let queryParams = this.listQuery
      getConfigSearchList(queryParams).then(response => {
        this.list = response.data.list
        this.total = response.data.total
				this.listLoading = false
      })
    },
		handleCreate() {
		  this.dialogType = 'add'
		  this.dialogFormVisible = true
			this.config = {status : 1}
			this.$nextTick(() => {
			  this.$refs['dataForm'].clearValidate()
			})
		},
		handleUpdate(row) {
      this.dialogType = 'edit'
      this.dialogFormVisible = true
      this.config = Object.assign({}, row) // copy obj
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
		},
		confirm() {
			this.$refs['dataForm'].validate((valid) => {
			  if (valid) {
			    let formData =  Object.assign({}, this.config)
					formData.addTime = null
					formData.updTime = null
			    updateConfigSearch(formData).then(() => {
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
			    deleteConfigSearch(values.join(',')).then(() => {
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
    	updateConfigSearch(formData).then(() => {
    	  this.$notify({
    	    title: '成功',
    	    message: '修改成功',
    	    type: 'success',
    	    duration: 2000
    	  })
    	})
    },

  }
}
</script>
