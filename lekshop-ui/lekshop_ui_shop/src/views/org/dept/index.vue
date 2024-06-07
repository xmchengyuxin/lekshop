<template>
  <div class="app-container">
    <div class="filter-container">
      <el-button-group>
        <el-button class="filter-item" type="primary" size="mini" icon="el-icon-plus" @click="handleCreate">添加
        </el-button>
        <el-button class="filter-item" type="primary" size="mini" icon="el-icon-edit" @click="handleInit">初始化内置部门
        </el-button>
      </el-button-group>
    </div>

    <br/>
    <div style="padding-left: 15px; text-align: left;">
      <el-button type="text" size="mini"  @click="expandAllList(true)">
        展开
      </el-button>
      <el-button type="text" size="mini" @click="expandAllList(false)">
        折叠
      </el-button>
    </div>
    <!-- <span class="link-type" @click="expandAllList(true)">展开</span> / <span class="link-type" @click="expandAllList(false)">折叠</span> -->
    <span class="mytree flex">
    	<el-tree ref="myTreeList" :data="list" @node-click="handleNodeClickDept" node-key="id"
    	  :expand-on-click-node="false" :default-expand-all="true" :props="props"
    	  :highlight-current="true" :indent="0">

        <span class="custom-tree-node" slot-scope="{ node, data }">
          <span>{{ node.label }}</span>
          <span @click.stop>
            <el-button type="text" size="mini" @click="() => handleCreate(data)">
              添加
            </el-button>
            <el-button type="text" size="mini" @click="() => handleUpdate(data)">
              编辑
            </el-button>
            <el-button type="text" size="mini" @click="() => handleDelete(data)">
              删除
            </el-button>
          </span>
        </span>

    	</el-tree>
    </span>

    <!-- <el-table :data="list" row-key="id" :key="tableKey" v-loading="listLoading" border fit highlight-current-row
      style="width: 100%;" @selection-change="handleSelectionChange" default-expand-all
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}">

      <el-table-column type="selection" width="55" align="center"></el-table-column>
      <el-table-column label="部门名称" prop="name">
        <template slot-scope="scope">
          <span>{{ scope.row.deptName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" width="200px" align="center" prop="addTime">
        <template slot-scope="scope">
          <span>{{ scope.row.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="开启状态" align="center" width="150px" prop="status">
        <template slot-scope="scope">
          <el-switch :active-value="1" :inactive-value="0" v-model="scope.row.status" @change="statusChange(scope.row)">
          </el-switch>
        </template>
      </el-table-column>
      <el-table-column label="排序" width="150px" align="center" prop="sort">
        <template slot-scope="scope">
          <el-input-number size="mini" style="width:100px;" v-model="scope.row.sort" @change="sortChange(scope.row)"
            :min="1" label="修改排序"></el-input-number>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.actions')" class-name="small-padding" fixed="right" width="200px">
        <template slot-scope="scope">
          <el-button-group>
            <el-button type="primary" size="mini" icon="el-icon-edit" @click="handleUpdate(scope.row)">编辑</el-button>
            <el-button size="mini" type="danger" icon="el-icon-delete" @click="handleDelete(scope.row)">删除</el-button>
          </el-button-group>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize"
      @pagination="getList" /> -->

    <!--添加编辑框-->
    <el-dialog :title="dialogType==='edit'?'编辑':'新增'" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="dept" label-width="130px" label-position="right"
        style="width: 100%; ">
        <el-form-item label="上级部门" prop="ptid">
            <el-input v-model="dept.parentName" placeholder="请选择上级部门" style="width: 60%;" :disabled="true"/>
            <div v-if="!dept.id && dept.showTree">
              <br/>
              <span class="link-type" @click="expandAll(true)">展开</span> / <span class="link-type" @click="expandAll(false)">折叠</span>
              <span class="mytree flex">
                <el-tree
                  ref="myTree"
                  style="width: 60%;font-size: 12px;margin-top: 10px;"
                  :data="deptOptions"
                  @node-click="handleNodeClick"
                  node-key="id"
                  :expand-on-click-node="false"
                  :default-expand-all	="defaultExpandAll"
                  :indent="16"
                  :props="props"
                  :highlight-current="true"
                  :current-node-key="dept.id">
                </el-tree>
              </span>
            </div>
        </el-form-item>
        <el-form-item label="部门名称" prop="deptName">
          <el-input v-model="dept.deptName" placeholder="请输入名称" style="width: 60%;" />
        </el-form-item>
        <el-form-item label="显示顺序" prop="sort">
          <el-input-number style="width:200px;" v-model="dept.sort" :min="1" label="修改排序"></el-input-number>
        </el-form-item>
      </el-form>
      <div style="text-align:right;">
          <el-button type="danger" @click="dialogFormVisible=false">取消</el-button>
          <el-button type="primary" @click="confirm">确定</el-button>
      </div>
    </el-dialog>

  </div>
</template>
<style scoped="scoped">
  ::v-deep .el-table .el-table__expand-icon {
    margin-right: 0px;
  }
  ::v-deep .el-tree-node__label {
      font-size: 13px;
  }
  .custom-tree-node {
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: space-between;
    font-size: 13px;
    padding-right: 8px;
  }
</style>

<script>
  import {
    getDeptList,
    updateDept,
    deleteDept,
    initDept
  } from '@/api/org'
  import waves from '@/directive/waves' // Waves directive
  import {
    parseTime,
    renderTime
  } from '@/utils'
  import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

//类型>>1公司>>2店铺>>3事业部>>4其他
const typeOptions = [{
      key: 1,
      text: '公司'
    },
    {
      key: 2,
      text: '店铺'
    },
    {
      key: 3,
      text: '事业部'
    },
    {
      key: 4,
      text: '其他'
    }
  ]

  export default {
    name: 'deptList',
    components: {
      Pagination
    },
    directives: {
      waves
    },
    data() {
      return {
        props: {
          label: 'deptName',
          value: 'id',
          children: 'children',
          checkStrictly: true
        },
        tableKey: 0,
        list: [],
        total: 0,
        listLoading: true,
        listQuery: {
          page: 1,
          pageSize: 20
        },
        dept: {},
        dialogFormVisible: false,
        dialogType: '',
        rules: {
          deptName: [{
            required: true,
            message: '部门名称不能为空',
            trigger: 'blur'
          }],
          sort: [{
            required: true,
            message: '显示顺序不能为空',
            trigger: 'blur'
          }],
        },
        deptOptions: [],
        defaultExpandAll:false,
        companyAttrOptions: [],
        deptAttrOptions: [],
        typeOptions
      }
    },
    created() {
      this.getList();
    },
    methods: {
      getList() {
        this.listLoading = true
        getDeptList(this.listQuery).then(response => {
          this.list = response.data
          this.listLoading = false
        })
      },
      handleCreate(row) {
        this.dialogStatus = 'add'
        this.dialogFormVisible = true
        if(row && row.id){
          this.dept = {
            sort: 1,
            deptAttr: 'other',
            parentName: row.deptName,
            parentId: row.id,
            showTree: false
          }
        }else{
          this.dept = {
            sort: 1,
            deptAttr: 'other',
            showTree: true
          }
          this.getDeptSelector()
        }
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      },
      handleUpdate(row) {
        this.dept = Object.assign({}, row) // copy obj
        this.dialogStatus = 'edit'
        this.dialogFormVisible = true
        this.getDeptSelector()

        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      },
      getDeptSelector() {
        getDeptList().then(response => {
          this.deptOptions = response.data
        })
      },
      confirm() {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            let formData = Object.assign({}, this.dept)
            formData.addTime = null
            formData.updTime = null
            updateDept(formData).then(() => {
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
      handleDelete(row) {
        this.$confirm('删除此记录需要先删除它的下级部门,您确定要删除这条记录?', '提醒', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(async () => {
            deleteDept(row.id).then(() => {
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
      sortChange(row) {
        let formData = {
          sort: row.sort,
          id: row.id
        }
        updateDept(formData).then(() => {
          this.$notify({
            title: '成功',
            message: '显示顺序修改成功',
            type: 'success',
            duration: 2000
          })
          this.getList()
        })
      },
      statusChange(row) {
        let formData = {
          status: row.status,
          id: row.id
        }
        updateDept(formData).then(() => {
          this.$notify({
            title: '成功',
            message: '修改成功',
            type: 'success',
            duration: 2000
          })
        })
      },
      handleInit(){
        initDept().then(() => {
          this.$notify({
            title: '成功',
            message: '初始化成功',
            type: 'success',
            duration: 2000
          })
          this.getList()
        })
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      handleNodeClick(data) {
        this.$set(this.dept, 'parentName', data.deptName);
        this.$set(this.dept, 'parentId', data.id);
      } ,
      // 展开/折叠
      expandAll(isExpand) {
        for (var i = 0; i < this.$refs.myTree.store._getAllNodes().length; i++) {
          // 根据isExpand， tree展开或折叠
          this.$refs.myTree.store._getAllNodes()[i].expanded = isExpand
        }
      },
      expandAllList(isExpand) {
        for (var i = 0; i < this.$refs.myTreeList.store._getAllNodes().length; i++) {
          // 根据isExpand， tree展开或折叠
          this.$refs.myTreeList.store._getAllNodes()[i].expanded = isExpand
        }
      },
      handleNodeClickDept(data) {
        this.dept = Object.assign({}, data) // copy obj
        this.dialogStatus = 'edit'
        this.dialogFormVisible = true
        // this.getDeptSelector()

        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      },

    }
  }
</script>
