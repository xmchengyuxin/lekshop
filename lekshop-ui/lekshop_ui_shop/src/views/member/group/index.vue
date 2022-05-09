<template>
  <div class="app-container">
    <el-tabs v-model="activeName" @tab-click="handleClickTab">
    	  <el-tab-pane label="买家组别" name="first"></el-tab-pane>
    		<el-tab-pane label="卖家组别" name="second"></el-tab-pane>
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
      <el-table-column label="组别类型" width="150px"  align="center">
        <template slot-scope="scope">
          <el-tag>{{ scope.row.type == 1 ? '买家组别' : '卖家组别' }}</el-tag>
        </template>
      </el-table-column>
			<el-table-column label="组别名称" width="150px"  align="center">
			  <template slot-scope="scope">
			   <el-tag type="danger" v-if=" scope.row.defaultStatus == 1 ">默认</el-tag>
         <span>{{ scope.row.name }}</span>
			  </template>
			</el-table-column>
      <el-table-column label="组别图标" width="150px"  align="center">
        <template slot-scope="scope">
          <el-image
                v-if="scope.row.icon"
                style=" height: 20px"
                :src="scope.row.icon"
                fit="scale-down"></el-image>
        </template>
      </el-table-column>
			<el-table-column label="等级" width="100px"  align="center">
			  <template slot-scope="scope">
			    <span>{{ scope.row.level }}</span>
			  </template>
			</el-table-column>
      <el-table-column label="初始金额" width="100px"  align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.initAmount | moneyFormat}}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="初始发布点" width="100px"  align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.initMission | moneyFormat}}</span>
        </template>
      </el-table-column> -->
      <el-table-column label="开启状态" width="150px" align="center">
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
		    <el-form ref="dataForm" :rules="rules" :model="memberGroup" label-width="120px" label-position="right" style="width: 100%; ">
          <el-form-item label="组别类型" prop="type">
            <el-radio-group v-model="memberGroup.type">
                <el-radio :label="1">买家组别</el-radio>
                <el-radio :label="2">卖家组别</el-radio>
              </el-radio-group>
          </el-form-item>
					<el-form-item label="组别名称" prop="name">
		        <el-input v-model="memberGroup.name" placeholder="请输入分组名称" />
		      </el-form-item>
          <el-form-item label="组别icon" prop="icon">
            <Upload v-model="memberGroup.icon" />
            <ImageSource :mul="false" v-model="memberGroup.icon" />
          </el-form-item>
          <el-form-item label="是否默认" prop="defaultStatus">
            <el-switch v-model="memberGroup.defaultStatus"
              :active-value="1"
              :inactive-value="0">
            </el-switch>
          </el-form-item>
          <el-form-item label="级别" prop="level">
            <el-input v-model="memberGroup.level" placeholder="请输入级别"/>
          </el-form-item>
          <el-form-item label="初始金额" prop="initAmount">
            <el-input v-model="memberGroup.initAmount" placeholder="请输入初始金额"/>
          </el-form-item>
          <!-- <el-form-item label="初始发布点" prop="initMission">
            <el-input v-model="memberGroup.initMission" placeholder="请输入初始发布点"/>
          </el-form-item> -->
          <el-form-item label="排序" prop="sort">
          	<el-input-number style="width:200px;" v-model="memberGroup.sort" :min="1" label="修改排序"></el-input-number>
          </el-form-item>
          <el-form-item label="开启关闭" prop="status">
            <el-switch v-model="memberGroup.status"
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
import {getMemberGroupList, updateMemberGroup, deleteMemberGroup, updateMemberGroupStatus} from '@/api/member'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime, moneyFormat } from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
import Upload from '@/components/Upload/singleImage'
import ImageSource from '@/components/Upload/imageSource'

export default {
  name: 'group',
  components: { Pagination , Upload, ImageSource },
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
        type: 1
      },
      memberGroup: {},
      dialogFormVisible: false,
      dialogType: '',
			multipleSelection: [],
			rules: {
        type: [{ required: true, message: '请选择组别类型', trigger: 'change' }],
			  name: [{ required: true, message: '组别名称不能为空', trigger: 'blur' }],
        level: [{ required: true, message: '请填写级别', trigger: 'blur' }],
        status: [{ required: true, message: '请选择开关状态', trigger: 'change' }],
			},
      activeName:'first',
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      let queryParams = this.listQuery
      getMemberGroupList(queryParams).then(response => {
        this.list = response.data.list
        this.total = response.data.total
				this.listLoading = false
      })
    },
		handleCreate() {
		  this.dialogType = 'add'
		  this.dialogFormVisible = true
			this.memberGroup = {defaultStatus: 0, initAmount:0, initMission: 0, sort : 1, status : 1}
			this.$nextTick(() => {
			  this.$refs['dataForm'].clearValidate()
			})
		},
		handleUpdate(row) {
      this.dialogType = 'edit'
      this.dialogFormVisible = true
      this.memberGroup = Object.assign({}, row) // copy obj
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
		},
		confirm() {
			this.$refs['dataForm'].validate((valid) => {
			  if (valid) {
			    let formData =  Object.assign({}, this.memberGroup)
					formData.addTime = null
					formData.updTime = null
			    updateMemberGroup(formData).then(() => {
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
			    deleteMemberGroup(values.join(',')).then(() => {
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
    	updateMemberGroupStatus(formData).then(() => {
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
    	}
    	this.getList()
    },

  }
}
</script>
