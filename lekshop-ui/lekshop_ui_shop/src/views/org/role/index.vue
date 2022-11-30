<template>
  <div class="app-container createPost-container">
    <el-row>
      <el-col :span="8">
        <div style="border-right: 1px solid #f4f4f4;margin-right: 20px;  ">
          <div style="padding-right: 8px; text-align: right;">
            <el-button type="text" size="mini" @click="expandAll(true)">
              展开
            </el-button>
            <el-button type="text" size="mini" @click="expandAll(false)">
              折叠
            </el-button>
          </div>

          <span class="mytree flex">
          	<el-tree ref="myTree" :data="deptList" @node-click="handleNodeClickDept" node-key="id"
          	  :expand-on-click-node="false" :default-expand-all="true" :props="deptProps"
          	  :highlight-current="true" :indent="0">
          	</el-tree>
          </span>


        </div>
      </el-col>

      <el-col :span="16">
        <div class="filter-container">
          <el-button-group>
            <el-button class="filter-item" size="mini" type="primary" icon="el-icon-plus" @click="handleCreate">新增角色
            </el-button>
            <el-button class="filter-item" size="mini" type="danger" icon="el-icon-delete" @click="handleDelete">删除
            </el-button>
            <el-button class="filter-item" size="mini" type="primary" icon="el-icon-search" @click="searchAll">查看全部
            </el-button>
          </el-button-group>
        </div>
        <el-table :key="tableKey" v-loading="listLoading" :data="list" border fit highlight-current-row
          style="width: 100%;" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center"></el-table-column>
          <el-table-column :label="$t('table.id')" prop="id" align="center" width="65">
            <template slot-scope="scope">
              <span>{{ scope.row.id }}</span>
            </template>
          </el-table-column>
          <el-table-column label="角色名称" align="center">
            <template slot-scope="scope">
              <span class="link-type">{{ scope.row.roleName }}</span>
            </template>
          </el-table-column>
          <el-table-column label="所属部门" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.deptName }}</span>
            </template>
          </el-table-column>
          <el-table-column label="创建时间" align="center" prop="addTime">
            <template slot-scope="scope">
              <span>{{ scope.row.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
            </template>
          </el-table-column>
          <el-table-column :label="$t('table.actions')" class-name="small-padding" align="center">
            <template slot-scope="scope">
              <el-button type="primary" size="mini" icon="el-icon-edit" @click="handleUpdate(scope.row)">编辑</el-button>
            </template>
          </el-table-column>
        </el-table>

        <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize"
          @pagination="getList" />

        <!--添加编辑框-->
        <el-dialog :title="dialogType==='edit'?'编辑角色':'新增角色'" :visible.sync="dialogFormVisible">
          <el-form :model="role" label-width="130px" label-position="right" style="width: 70%;">
            <el-form-item label="当前部门">
              <el-input v-model="role.deptName" placeholder="请选择部门"  :disabled="true"/>
            </el-form-item>
            <el-form-item label="角色名称" prop="roleName" :rules="[{ required: true, message: '请填写角色名称', trigger: 'blur' }]">
              <el-input v-model="role.roleName" placeholder="请输入角色名称" />
            </el-form-item>
            <el-form-item label="选择菜单">
              <span>
                <el-alert
                    title="是否开启父子联动"
                    type="info"
                    :closable="false"
                    >
                    <el-switch
                      v-model="checkStrictly"
                      active-color="#dcdfe6"
                      inactive-color="#13ce66">
                    </el-switch>
                    <span class="tips">开启后选中父菜单会同时选中所有子菜单</span>
                  </el-alert>
              </span>
              <el-tree ref="tree" :check-strictly="checkStrictly" :data="currentRoutesData" :props="defaultProps" show-checkbox
                node-key="id" :default-expanded-keys="expandedKeys" :default-checked-keys="checkedKeys"
                class="permission-tree" />
            </el-form-item>
          </el-form>
          <div style="text-align:right;">
            <el-button type="danger" @click="dialogFormVisible=false">取消</el-button>
            <el-button type="primary" @click="confirmRole">确定</el-button>
          </div>
        </el-dialog>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import {
    getRoleList,
    getMenuList,
    updateRole,
    deleteRole,
    getDeptList,
    getRoleMenus
  } from '@/api/org'
  import waves from '@/directive/waves' // Waves directive
  import {
    parseTime,
    renderTime
  } from '@/utils'
  import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

  export default {
    name: 'roleTable',
    components: {
      Pagination
    },
    directives: {
      waves
    },
    data() {
      return {
        deptList: [],
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
        checkStrictly: true,
        deptProps: {
          children: 'children',
          label: 'deptName',
          value: 'id',
          checkStrictly: true
        },
        defaultProps: {
          children: 'children',
          label: 'menuName',
          value: 'id',
        },
        routesData: [],
        currentRoutesData:[],
        expandedKeys: [],
        checkedKeys: [],
        multipleSelection: [],
        currentDept: null
      }
    },
    created() {
      this.getList()
      this.getDeptList()
      this.getRoutes()
    },
    methods: {
      searchAll(){
        this.listQuery.deptId = null
        this.getList()
      },
      getList() {
        this.listLoading = true
        getRoleList(this.listQuery).then(response => {
          this.list = response.data.list
          this.total = response.data.total
          this.listLoading = false
        })
      },
      async getRoutes() {
        getMenuList({
        }).then(response => {
          this.routesData = response.data
        })
      },
      async getCurrentRotes(){
        this.currentRoutesData = this.routesData;
        this.$refs.tree.setCheckedKeys([]);
      },
      async getDeptList() {
        getDeptList().then(response => {
          this.deptList = response.data
        })
      },
      handleCreate() {
        if (!this.currentDept) {
          this.$message({
            message: '请先选择一个部门',
            type: 'error',
            duration: 2000
          })
          return;
        }
        this.checkStrictly = true
        this.checkedKeys = [];
        this.dialogStatus = 'add'
        this.dialogFormVisible = true
        this.role = {
          deptId: this.currentDept.id,
          deptName: this.currentDept.deptName,
          status: 1,
          sort: 1
        }
       this.getCurrentRotes()
      },
      handleUpdate(row) {
        this.role = Object.assign({}, row) // copy obj
        this.checkedKeys = [];
        this.dialogStatus = 'edit'
        this.dialogFormVisible = true
        // this.checkStrictly = true
        this.getCurrentRotes()
        getRoleMenus({roleId: this.role.id}).then(response => {
          this.$nextTick(() => {
            const keys = response.data;
            this.$refs.tree.setCheckedKeys(keys);
            this.expandedKeys = keys
            // this.checkStrictly = false
           })
        })
      },
      confirmRole() {
        let menuIdList = this.$refs.tree.getCheckedKeys();
        let harfMenuIdList = this.$refs.tree.getHalfCheckedKeys()
        menuIdList = menuIdList.concat(harfMenuIdList)
        let menuIds = menuIdList.join(',');
        let formData = {
          id: this.role.id,
          deptId: this.role.deptId,
          roleName: this.role.roleName,
          menuIds: menuIds,
          sort: this.role.sort
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
      handleDelete() {
        const delVals = this.multipleSelection;
        if (!delVals || delVals.length <= 0) {
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
          }).then(async () => {
            let values = [];
            delVals.forEach(val => {
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
          .catch(err => {
            console.error(err)
          })
      },
      // 展开/折叠
      expandAll(isExpand) {
        for (var i = 0; i < this.$refs.myTree.store._getAllNodes().length; i++) {
          // 根据isExpand， tree展开或折叠
          this.$refs.myTree.store._getAllNodes()[i].expanded = isExpand
        }
      },
      handleNodeClickDept(data) {
        this.listQuery.deptId = data.id
        this.currentDept = data
        this.getList();
      },

    }
  }
</script>

<style lang="scss" scoped>
  @import "~@/styles/mixin.scss";

  .createPost-container {
    position: relative;

    .createPost-main-container {
      padding: 40px 45px 20px 50px;

      .postInfo-container {
        position: relative;
        @include clearfix;
        margin-bottom: 10px;

        .postInfo-container-item {
          float: left;
        }
      }
    }

    .word-counter {
      width: 40px;
      position: absolute;
      right: -10px;
      top: 0px;
    }
  }

  ::v-deep .el-tree-node__label {
    font-size: 13px;
  }

</style>
