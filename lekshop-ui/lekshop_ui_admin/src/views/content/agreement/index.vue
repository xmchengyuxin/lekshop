<template>
  <div class="app-container">
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
			<el-table-column label="标题">
			  <template slot-scope="scope">
			   <span>{{ scope.row.title }}</span>
			  </template>
			</el-table-column>
			<!-- <el-table-column label="是否显示" width="200px"  align="center">
			  <template slot-scope="scope">
			    <el-tag :type="scope.row.status == '1' ? 'success' : 'danger' ">{{ scope.row.status | statusFilter}}</el-tag>
			  </template>
			</el-table-column> -->
      <!-- <el-table-column label="创建时间" width="200px" align="center" prop="addTime" >
        <template slot-scope="scope">
          <span>{{ scope.row.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column> -->
			<!-- <el-table-column label="修改时间" width="200px" align="center" prop="updTime">
			  <template slot-scope="scope">
			    <span>{{ scope.row.updTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
			  </template>
			</el-table-column> -->
      <el-table-column :label="$t('table.actions')" class-name="small-padding" fixed="right">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" icon="el-icon-edit" @click="handleUpdate(scope.row)">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />

		<!--添加编辑框-->
		<el-dialog title="编辑" :visible.sync="dialogFormVisible">
		    <el-form ref="dataForm" :rules="rules" :model="agreement" label-width="80px" label-position="right" style="width: 100%;">
		      <el-form-item label="标题" prop="title">
		        <el-input v-model="agreement.title" placeholder="请输入标题" disabled/>
		      </el-form-item>
					<!-- <el-form-item label="是否显示" prop="status">
		        <el-select v-model="advert.status" class="filter-item" placeholder="请选择">
		          <el-option v-for="item in statusOptions" :key="item.key" :label="item.text" :value="item.key" />
		        </el-select>
		      </el-form-item> -->
					<el-form-item v-if="dialogFormVisible"  prop="detail" style="margin-bottom: 30px; width:100%;">
					  <Tinymce ref="editor" v-model="agreement.detail" :height="300" />
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
import Tinymce from '@/components/Tinymce'
import {getAgreementList, updateAgreement} from '@/api/agreement'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime } from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

const statusOptions = [
  { key: 1, text: '显示' },
  { key: 0, text: '隐藏' }
]

// arr to obj ,such as { CN : "China", US : "USA" }
const statusKeyValue = statusOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

export default {
  name: 'agreementTable',
  components: { Pagination, Tinymce },
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
      agreement: {},
      dialogFormVisible: false,
      dialogType: '',
			multipleSelection: [],
			rules: {
			  title: [{ required: true, message: '标题不能为空', trigger: 'blur' }],
			  detail: [{ required: true, message: '文案内容不能为空', trigger: 'blur' }],
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
      getAgreementList(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.total
				this.listLoading = false
      })
    },
		handleUpdate(row) {
		  this.agreement = Object.assign({}, row) // copy obj
		  this.dialogStatus = 'edit'
		  this.dialogFormVisible = true
		  this.$nextTick(() => {
		    this.$refs['dataForm'].clearValidate()
		  })
		},
		confirm() {
			this.$refs['dataForm'].validate((valid) => {
			  if (valid) {
			    let formData =Object.assign({}, this.agreement)
					formData.addTime = null
					formData.updTime = null
			    updateAgreement(formData).then(() => {
						this.getList()
			      this.dialogFormVisible = false
			      this.$notify({
			        title: '成功',
			        message: '文案保存成功',
			        type: 'success',
			        duration: 2000
			      })
			    })
			  }
			})
		},
		handleSelectionChange(val) {
			this.multipleSelection = val;
    }

  }
}
</script>
