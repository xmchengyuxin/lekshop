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
			<el-table-column label="积分等级" align="center">
			  <template slot-scope="scope">
         <span>{{ scope.row.level }}</span>
			  </template>
			</el-table-column>
      <el-table-column label="积分范围" align="center">
        <template slot-scope="scope">
         <span>{{ scope.row.minPoint }} ~ {{ scope.row.maxPoint }}</span>
        </template>
      </el-table-column>
      <el-table-column label="最高接单金额" align="center">
        <template slot-scope="scope">
         <span>{{ scope.row.maxAmount | moneyFormat}}</span>
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
		    <el-form ref="dataForm" :rules="rules" :model="configPoint" label-width="120px" label-position="right" style="width: 100%; ">
          <el-form-item label="积分等级" prop="level">
          	<el-input-number style="width:200px;" v-model="configPoint.level" :min="1" label="修改等级"></el-input-number>
          </el-form-item>
					<el-form-item label="积分范围" prop="minPoint">
		        <el-input v-model="configPoint.minPoint" placeholder="最低积分" style="width: 200px;"/> - <el-input v-model="configPoint.maxPoint" placeholder="最高积分" style="width: 200px;"/>
		      </el-form-item>
          <el-form-item label="最高接单金额" prop="maxAmount">
            <el-input v-model="configPoint.maxAmount" placeholder="请输入最高接单金额" />
          </el-form-item>
          <el-form-item label="开启关闭" prop="status">
            <el-switch v-model="configPoint.status"
              :active-value="1"
              :inactive-value="0">
            </el-switch>
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
import {getConfigPointList, updateConfigPoint, deleteConfigPoint} from '@/api/config'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime, moneyFormat } from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

export default {
  name: 'configPoint',
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
      configPoint: {},
      dialogFormVisible: false,
      dialogType: '',
			multipleSelection: [],
			rules: {
			  level: [{ required: true, message: '积分等级不能为空', trigger: 'blur' }],
        minPoint: [{ required: true, message: '请填写该等级的积分范围', trigger: 'blur' }],
        maxAmount: [{ required: true, message: '请填写该等级的最高接单范围', trigger: 'blur' }],
        status: [{ required: true, message: '请选择开关状态', trigger: 'change' }],
			},
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      let queryParams = this.listQuery
      getConfigPointList(queryParams).then(response => {
        this.list = response.data.list
        this.total = response.data.total
				this.listLoading = false
      })
    },
		handleCreate() {
		  this.dialogType = 'add'
		  this.dialogFormVisible = true
			this.configPoint = {status : 1}
			this.$nextTick(() => {
			  this.$refs['dataForm'].clearValidate()
			})
		},
		handleUpdate(row) {
      this.dialogType = 'edit'
      this.dialogFormVisible = true
      this.configPoint = Object.assign({}, row) // copy obj
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
		},
		confirm() {
			this.$refs['dataForm'].validate((valid) => {
			  if (valid) {
			    let formData =  Object.assign({}, this.configPoint)
					formData.addTime = null
					formData.updTime = null
			    updateConfigPoint(formData).then(() => {
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
			    deleteConfigPoint(values.join(',')).then(() => {
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
    	updateConfigPoint(formData).then(() => {
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
