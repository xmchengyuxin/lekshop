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
			<el-table-column label="模板名称" width="200px"  align="center">
			  <template slot-scope="scope">
			   <span>{{ scope.row.name }}</span>
			  </template>
			</el-table-column>
      <el-table-column label="模板标识" width="200px"  align="center">
        <template slot-scope="scope">
         <span>{{ scope.row.cate }}</span>
        </template>
      </el-table-column>
      <el-table-column label="模板展示图" width="200px"  align="center">
        <template slot-scope="scope">
         <el-image
             style="height: 100px; width: 100px;"
             :src="scope.row.img"
             >
           </el-image>
        </template>
      </el-table-column>
      <el-table-column label="内容">
        <template slot-scope="scope">
         <span>{{ scope.row.content }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.actions')" class-name="small-padding" fixed="right">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" icon="el-icon-edit" @click="handleUpdate(scope.row)">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />

		<!--添加编辑框-->
		<el-dialog title="编辑" :visible.sync="dialogFormVisible">
		    <el-form ref="dataForm" :rules="rules" :model="template" label-width="80px" label-position="left" style="width: 400px; margin-left:50px;">
		      <el-form-item label="模板名称" prop="name">
		        <el-input disabled v-model="template.name" placeholder="请输入文案标题" disabled/>
		      </el-form-item>
          <el-form-item label="展示图片" prop="img">
            <Upload v-model="template.img" />
            <p style="color:#ff4949;font-size:12px; ">图片建议大小为200px*200px</p>
          </el-form-item>
          <el-form-item label="模板内容" prop="content">
            <el-input type="textarea" v-model="template.content" placeholder="请输入模板内容" />
          </el-form-item>
          <el-form-item label="是否跳转" prop="type">
             <el-radio-group v-model="template.type">
                <el-radio :label="'0'">不跳转</el-radio>
                <el-radio :label="'1'">跳转</el-radio>
              </el-radio-group>
          </el-form-item>
          <el-form-item v-if="template.type == '1' " label="跳转页面" prop="turnPage">
            <el-input v-model="template.turnPage" placeholder="请输入跳转内容" />
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
import {getNewsTemplateList, updateNewsTemplate} from '@/api/news'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime } from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
import Upload from '@/components/Upload/singleImage'

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
  name: 'templateTable',
  components: { Pagination, Tinymce, Upload },
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
      template: {},
      dialogFormVisible: false,
      dialogType: '',
			multipleSelection: [],
			rules: {
			  img: [{ required: true, message: '请上传展示图片', trigger: 'blur' }],
			  content: [{ required: true, message: '请填写通知内容', trigger: 'blur' }],
        type: [{ required: true, message: '请选择是否跳转', trigger: 'change' }],
        turnPage: [{ required: true, message: '请填写跳转模块', trigger: 'blur' }],
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
      getNewsTemplateList(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.total
				this.listLoading = false
      })
    },
		handleUpdate(row) {
		  this.template = Object.assign({}, row) // copy obj
		  this.dialogStatus = 'edit'
		  this.dialogFormVisible = true
		  this.$nextTick(() => {
		    this.$refs['dataForm'].clearValidate()
		  })
		},
		confirm() {
			this.$refs['dataForm'].validate((valid) => {
			  if (valid) {
			    let formData =Object.assign({}, this.template)
					formData.addTime = null
					formData.updTime = null
			    updateNewsTemplate(formData).then(() => {
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
    }

  }
}
</script>
