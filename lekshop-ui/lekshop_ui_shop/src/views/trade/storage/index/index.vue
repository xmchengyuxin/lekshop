<template>
  <div class="app-container">
    <div class="filter-container">
      <el-button-group>
        <el-button class="filter-item" type="primary" size="mini" icon="el-icon-plus" @click="handleCreate">添加
        </el-button>
        <el-button class="filter-item" size="mini" type="danger" icon="el-icon-delete" @click="handleDelete">删除
        </el-button>
      </el-button-group>
    </div>
    <el-table :data="list" row-key="id" :key="tableKey" v-loading="listLoading" border fit highlight-current-row
    @selection-change="handleSelectionChange"
      style="width: 100%;">

      <el-table-column type="selection" width="55" align="center"></el-table-column>
      <el-table-column label="仓库名称" prop="name" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="用途" prop="remark" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.remark }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建人" prop="addBy" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.addBy }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" width="200px" align="center" prop="addTime">
        <template slot-scope="scope">
          <span>{{ scope.row.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.actions')" class-name="small-padding" fixed="right" align="center">
        <template slot-scope="scope">
          <el-button-group>
            <el-button type="primary" size="mini" icon="el-icon-edit" @click="handleUpdate(scope.row)">编辑</el-button>
          </el-button-group>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize"
      @pagination="getList" />

    <!--添加编辑框-->
    <el-dialog :title="dialogType==='edit'?'编辑':'新增'" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="dataForm" label-width="90px" label-position="right"
        style="width: 100%;">
        <el-form-item label="仓库名称" prop="name">
          <el-input v-model="dataForm.name" placeholder="请输入名称" style="width: 60%;" />
        </el-form-item>
        <el-form-item label="用途" prop="remark">
          <el-input type="textarea" v-model="dataForm.remark" placeholder="请输入用途" style="width: 60%;" />
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
</style>

<script>
  import {
    storageGet,
    storageAdd,
    storageDel
  } from '@/api/trade'
  import waves from '@/directive/waves' // Waves directive
  import {
    parseTime,
    renderTime
  } from '@/utils'
  import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

  export default {
    name: 'storage',
    components: {
      Pagination
    },
    directives: {
      waves
    },
    data() {
      return {
        tableKey: 0,
        list: [],
        total: 0,
        listLoading: true,
        listQuery: {
          page: 1,
          pageSize: 20
        },
        dataForm: {},
        dialogFormVisible: false,
        dialogType: '',
        rules: {
          name: [{
            required: true,
            message: '仓库名称不能为空',
            trigger: 'blur'
          }],
          remark: [{
            required: true,
            message: '用途不能为空',
            trigger: 'blur'
          }],
        },
        activeName: "1"
      }
    },
    created() {
      this.getList()
    },
    methods: {
      getList() {
        this.listLoading = true
        storageGet(
          this.listQuery).then(response => {
          this.list = response.data.list
          this.total = response.data.total
          this.listLoading = false
        })
      },
      handleCreate() {
        this.dialogStatus = 'add'
        this.dialogFormVisible = true
        this.dataForm = {
        }
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      },
      handleUpdate(row) {
        this.dataForm = Object.assign({}, row) // copy obj
        this.dialogStatus = 'edit'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      },
      confirm() {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            let formData = Object.assign({}, this.dataForm)
            formData.addTime = null
            formData.updTime = null
            storageAdd(formData).then(() => {
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
            storageDel({ids:values.join(',')}).then(() => {
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

    }
  }
</script>
