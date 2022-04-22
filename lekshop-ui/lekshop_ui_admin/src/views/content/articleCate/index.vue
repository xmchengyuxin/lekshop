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
			<el-table-column label="分类名称" width="150px"  align="center">
			  <template slot-scope="scope">
			    <span>{{ scope.row.name }}</span>
			  </template>
			</el-table-column>
			<el-table-column label="是否显示" width="150px"  align="center">
			  <template slot-scope="scope">
			    <el-tag :type="scope.row.status == '1' ? 'success' : 'danger' ">{{ scope.row.status | statusFilter}}</el-tag>
			  </template>
			</el-table-column>
			<el-table-column label="排序" width="150px"  align="center">
			  <template slot-scope="scope">
			  <el-input-number size="mini" style="width:100px;" v-model="scope.row.sort" @change="sortChange(scope.row)" :min="1" label="修改排序"></el-input-number>
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
		    <el-form ref="dataForm" :rules="rules" :model="cate" label-width="80px" label-position="right" style="width: 400px; margin-left:50px;">
					<el-form-item label="分类名称" prop="name">
					  <el-input v-model="cate.name" placeholder="请输入名称" />
					</el-form-item>
				<el-form-item label="是否显示" prop="status">
				  <el-select v-model="cate.status" class="filter-item" placeholder="请选择">
				    <el-option v-for="item in statusOptions" :key="item.key" :label="item.text" :value="item.key" />
				  </el-select>
				</el-form-item>
					<el-form-item label="排序" prop="sort">
						<el-input-number style="width:200px;" v-model="cate.sort" :min="1" label="修改排序"></el-input-number>
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
import {getArticleCateList, updateArticleCate, deleteArticleCate} from '@/api/article'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime } from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

const statusOptions = [
  { key: '1', text: '显示' },
  { key: '0', text: '隐藏' }
]

// arr to obj ,such as { CN : "China", US : "USA" }
const statusKeyValue = statusOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

export default {
  name: 'articleCate',
  components: { Pagination},
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
      cate: {},
      dialogFormVisible: false,
      dialogType: '',
			multipleSelection: [],
			rules: {
			  name: [{ required: true, message: '分类名称不能为空', trigger: 'blur' }],
				sort: [{ required: true, message: '显示顺序不能尾款', trigger: 'change' }],
				status: [{ required: true, message: '是否显示不能为空', trigger: 'change' }]
			},
			statusOptions
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      getArticleCateList(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.total
				this.listLoading = false
      })
    },
		handleCreate() {
		  this.dialogType = 'add'
		  this.dialogFormVisible = true
			this.cate = {}
			this.$nextTick(() => {
			  this.$refs['dataForm'].clearValidate()
			})
		},
		handleUpdate(row) {
		  this.cate = Object.assign({}, row) // copy obj
		  this.dialogType = 'edit'
		  this.dialogFormVisible = true
		  this.$nextTick(() => {
		    this.$refs['dataForm'].clearValidate()
		  })
		},
		confirm() {
			this.$refs['dataForm'].validate((valid) => {
			  if (valid) {
			    let formData =Object.assign({}, this.cate)
					formData.addTime = null
					formData.updTime = null
			    updateArticleCate(formData).then(() => {
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
			    deleteArticleCate(values.join(',')).then(() => {
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
    sortChange(row) {
      let formData = {
    		sort: row.sort,
    		id: row.id
    	}
    	updateArticleCate(formData).then(() => {
    	  this.$notify({
    	    title: '成功',
    	    message: '显示顺序修改成功',
    	    type: 'success',
    	    duration: 2000
    	  })
    		this.getList()
    	})
    },

  }
}
</script>
