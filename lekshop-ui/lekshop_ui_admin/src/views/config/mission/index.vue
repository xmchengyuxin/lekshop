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
			<el-table-column label="佣金组名称" align="center">
			  <template slot-scope="scope">
          <el-tag type="danger" v-if=" scope.row.defaultStatus == 1 ">默认</el-tag>
         <span>{{ scope.row.name }}</span>
			  </template>
			</el-table-column>
			<el-table-column label="佣金组描述"  align="center">
			  <template slot-scope="scope">
			    <span>{{ scope.row.remark }}</span>
			  </template>
			</el-table-column>
      <el-table-column label="奖励的佣金存入"  align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.inAccount == 1 ? '佣金账户' : '余额账户'}}</span>
        </template>
      </el-table-column>
      <el-table-column label="排序" align="center">
        <template slot-scope="scope">
        <el-input-number size="mini" style="width:100px;" v-model="scope.row.sort" @change="sortChange(scope.row)" :min="1" label="修改排序"></el-input-number>
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
          <router-link :to="'/config/mission/edit/'+scope.row.id">
            <el-button type="info" icon="el-icon-edit" size="mini">设置佣金</el-button>
          </router-link>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />

		<!--添加编辑框-->
		<el-dialog :title="dialogType==='edit'?'编辑':'新增'" :visible.sync="dialogFormVisible">
		    <el-form ref="dataForm" :rules="rules" :model="configMission" label-width="120px" label-position="right" style="width: 100%; ">
					<el-form-item label="佣金组名称" prop="name">
		        <el-input v-model="configMission.name" placeholder="请输入佣金组名称" />
		      </el-form-item>
          <el-form-item label="佣金组描述" prop="remark">
            <el-input type="textarea" :row="3" v-model="configMission.remark" placeholder="请输入佣金组描述" />
          </el-form-item>
          <el-form-item label="奖励存入" prop="inAccount">
            <el-radio-group v-model="configMission.inAccount">
                <el-radio :label="1">佣金账户</el-radio>
                <!-- <el-radio :label="2">余额账户</el-radio> -->
              </el-radio-group>
          </el-form-item>
          <el-form-item label="排序" prop="sort">
          	<el-input-number style="width:200px;" v-model="configMission.sort" :min="1" label="修改排序"></el-input-number>
          </el-form-item>
          <el-form-item label="是否默认" prop="defaultStatus">
            <el-switch v-model="configMission.defaultStatus"
              :active-value="1"
              :inactive-value="0">
            </el-switch>
          </el-form-item>
          <el-form-item label="开启关闭" prop="status">
            <el-switch v-model="configMission.status"
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
import {getConfigMissionList, updateConfigMission, deleteConfigMission, updateConfigMissionParams} from '@/api/config'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime, moneyFormat } from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

export default {
  name: 'configMission',
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
      configMission: {},
      dialogFormVisible: false,
      dialogType: '',
			multipleSelection: [],
			rules: {
			  name: [{ required: true, message: '佣金组名称不能为空', trigger: 'blur' }],
        remark: [{ required: true, message: '佣金组描述不能为空', trigger: 'change' }],
        inAccount: [{ required: true, message: '请选择奖励的佣金存入账户', trigger: 'blur' }],
        defaultStatus: [{ required: true, message: '请选择默认状态', trigger: 'blur' }],
        sort: [{ required: true, message: '请填写排序', trigger: 'blur' }],
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
      getConfigMissionList(queryParams).then(response => {
        this.list = response.data.list
        this.total = response.data.total
				this.listLoading = false
      })
    },
		handleCreate() {
		  this.dialogType = 'add'
		  this.dialogFormVisible = true
			this.configMission = {sort : 1, status : 1, defaultStatus: 0}
			this.$nextTick(() => {
			  this.$refs['dataForm'].clearValidate()
			})
		},
		handleUpdate(row) {
      this.dialogType = 'edit'
      this.dialogFormVisible = true
      this.configMission = Object.assign({}, row) // copy obj
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
		},
		confirm() {
			this.$refs['dataForm'].validate((valid) => {
			  if (valid) {
			    let formData =  Object.assign({}, this.configMission)
					formData.addTime = null
					formData.updTime = null
			    updateConfigMission(formData).then(() => {
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
			    deleteConfigMission(values.join(',')).then(() => {
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
    	updateConfigMissionParams(formData).then(() => {
    	  this.$notify({
    	    title: '成功',
    	    message: '修改成功',
    	    type: 'success',
    	    duration: 2000
    	  })
    	})
    },
    sortChange(row) {
      let formData = {
    		sort: row.sort,
    		id: row.id
    	}
    	updateConfigMissionParams(formData).then(() => {
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
