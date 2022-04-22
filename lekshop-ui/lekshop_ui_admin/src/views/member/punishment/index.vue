<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="listQuery.memberName" clearable placeholder="用户名" style="width: 200px;" class="filter-item"/>
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
			<el-table-column label="处罚用户"  align="center"  width="150">
			  <template slot-scope="scope">
			    <router-link :to="'/member/detail?memberId='+scope.row.memberId">
			      <p class="link-type">{{ scope.row.memberName }}</p>
			    </router-link>
			  </template>
			</el-table-column>
      <el-table-column label="处罚天数" align="center"  width="100">
        <template slot-scope="scope">
          <span>{{ scope.row.days}} 天</span>
        </template>
      </el-table-column>
      <el-table-column label="处罚时间" width="200px" align="center" prop="addTime">
        <template slot-scope="scope">
          <span>{{ scope.row.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="到期时间" width="200px" align="center" prop="addTime">
        <template slot-scope="scope">
          <span>{{ scope.row.expiredTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="处罚内容">
        <template slot-scope="scope">
          <span>{{ scope.row.content}}</span>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />


    <el-dialog title="添加处罚" :visible.sync="dialogFormVisible">
        <el-form ref="dataForm" :rules="rules" :model="addForm" label-width="120px" label-position="right" style="width: 400px; margin-left:50px;">
    			<el-form-item label="处罚账号" prop="memberName">
    				  <el-input v-model="addForm.memberName" placeholder="请输入处罚账号"/>
    			</el-form-item>
          <el-form-item label="处罚天数" prop="days">
          	  <el-input v-model.number="addForm.days" placeholder="请输入处罚天数"/>
          </el-form-item>
    			<el-form-item label="处罚内容" prop="content">
    			  <el-input type="textarea" :rows="6" v-model="addForm.content" placeholder="请输入处罚内容"/>
            <span class="tips">*进行处罚后, 用户将无法提现和接单</span>
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
import {getMemberPunishmentList, addMemberPunishment, deleteMemberPunishment} from '@/api/member'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime, moneyFormat } from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

const typeOptions = [
  { key: 1, text: '身份证' },
  { key: 2, text: '手机号' },
  { key: 3, text: 'QQ' },
  { key: 4, text: '旺旺号' },
]

// arr to obj ,such as { CN : "China", US : "USA" }
const typeKeyValue = typeOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

export default {
  name: 'memberPunishment',
  components: { Pagination},
  directives: { waves },
  filters: {
    typeFilter(status) {
      return typeKeyValue[status]
    },
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
      typeOptions,
      addForm:{},
      rules: {
      	memberName: [{ required: true, message: '请输入处罚账号', trigger: 'change' }],
        days: [{ required: true, message: '请输入处罚天数', trigger: 'change' }],
        content: [{ required: true, message: '请输入处罚内容', trigger: 'blur' }]
      },
      dialogFormVisible:false
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      let queryParams = this.listQuery
      getMemberPunishmentList(queryParams).then(response => {
        this.list = response.data.list
        this.total = response.data.total
				this.listLoading = false
      })
    },
		handleSelectionChange(val) {
			this.multipleSelection = val;
     },
     handleCreate() {
       this.dialogFormVisible = true
     	this.addForm = {}
     	this.$nextTick(() => {
     	  this.$refs['dataForm'].clearValidate()
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
			    deleteMemberPunishment(values.join(',')).then(() => {
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
    confirm() {
    	this.$refs['dataForm'].validate((valid) => {
    	  if (valid) {
    	    addMemberPunishment(this.addForm).then((response) => {
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
    	        message: '添加成功',
    	        type: 'success',
    	        duration: 2000
    	      })
    	    })
    	  }
    	})
    },
  }
}
</script>
