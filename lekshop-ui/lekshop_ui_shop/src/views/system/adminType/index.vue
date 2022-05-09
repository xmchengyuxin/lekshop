<template>
  <div class="app-container">
    <div class="filter-container">
			<el-button-group>
			<el-button class="filter-item" size="mini" type="primary" icon="el-icon-edit" @click="handleCreate">新增角色</el-button>
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
			<el-table-column label="角色名称" width="200px"  align="center">
			  <template slot-scope="scope">
			    <span class="link-type">{{ scope.row.name }}</span>
			  </template>
			</el-table-column>
      <el-table-column label="创建时间" width="200px" align="center" prop="addTime" >
        <template slot-scope="scope">
          <span>{{ scope.row.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
			<el-table-column label="修改时间" width="200px" align="center" prop="updTime">
			  <template slot-scope="scope">
			    <span v-if="scope.row.updTime != null">{{ scope.row.updTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
			  </template>
			</el-table-column>
      <el-table-column :label="$t('table.actions')" class-name="small-padding">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" icon="el-icon-edit" @click="handleUpdate(scope.row)">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />

		<!--添加编辑框-->
		<el-dialog :title="dialogType==='edit'?'编辑角色':'新增角色'" :visible.sync="dialogFormVisible">
		    <el-form :model="role" label-width="80px" label-position="left" style="width: 400px; margin-left:50px;">
		      <el-form-item label="角色名称">
		        <el-input v-model="role.name" placeholder="请输入角色名称" />
		      </el-form-item>
          <!-- <el-form-item label="选择品牌">
            <el-select v-model="role.brandId" placeholder="品牌" filterable clearable class="filter-item">
              <el-option v-for="item in brandOptions" :key="item.id" :label="item.name" :value="item.id">
              </el-option>
            </el-select>
           </el-form-item> -->
		      <el-form-item label="选择菜单">
		        <el-tree ref="tree" :check-strictly="checkStrictly" :data="routesData"
						:props="defaultProps" show-checkbox node-key="id" :default-expanded-keys="expandedKeys"
						:default-checked-keys="checkedKeys" class="permission-tree" />
		      </el-form-item>
		    </el-form>
		    <div style="text-align:right;">
		      <el-button type="danger" @click="dialogFormVisible=false">取消</el-button>
		      <el-button type="primary" @click="confirmRole">确定</el-button>
		    </div>
		</el-dialog>

  </div>
</template>

<script>
import {getRoleList, getRoutes, updateRole, deleteRole} from '@/api/role'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime } from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

export default {
  name: 'roleTable',
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
        pageSize: 20
      },
      role: {},
      dialogFormVisible: false,
      dialogType: '',
			checkStrictly: false,
			defaultProps: {
			  children: 'children',
			  label: 'name'
			},
			routesData:{},
			expandedKeys:[],
			checkedKeys:[],
			multipleSelection: [],
      brandOptions:[]
    }
  },
  created() {
    this.getList()
		this.getRoutes()
  },
  methods: {
    getList() {
      this.listLoading = true
      getRoleList(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.total
				this.listLoading = false
      })
    },
		async getRoutes() {
			getRoutes().then(response => {
			  this.routesData = response.data
			})
		},
		handleCreate() {
		  this.dialogStatus = 'add'
		  this.dialogFormVisible = true
			this.role = {}
			this.$refs.tree.setCheckedKeys([]);
		},
		handleUpdate(row) {
		  this.role = Object.assign({}, row) // copy obj
		  this.dialogStatus = 'edit'
		  this.dialogFormVisible = true
			this.checkStrictly = true
		  this.$nextTick(() => {
				const keys = this.role.menus.split(',');
				this.$refs.tree.setCheckedKeys(keys);
				this.expandedKeys = keys
				this.checkStrictly = false
		  })
		},
		confirmRole() {
			let menuIdList = this.$refs.tree.getCheckedKeys();
			let harfMenuIdList = this.$refs.tree.getHalfCheckedKeys()
			menuIdList = menuIdList.concat(harfMenuIdList)
			let menuIds = menuIdList.join(',');
			let formData = {
				id : this.role.id,
				name : this.role.name,
				menuIds : menuIds,
        brandId: this.role.brandId
			}
			updateRole(formData).then(() => {
			  this.dialogFormVisible = false
				this.getList()
			  this.$notify({
			    title: '成功',
			    message: '角色保存成功',
			    type: 'success',
			    duration: 2000
			  })
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
			    deleteRole(values.join(',')).then(() => {
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
