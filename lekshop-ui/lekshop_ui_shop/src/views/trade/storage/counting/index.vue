<template>
  <div class="app-container">
    <div class="filter-container">
        <el-date-picker class="filter-item" v-model="listQuery.startTime" type="datetime" format="yyyy-MM-dd HH:mm:ss" placeholder="选择开始时间" />
        -
        <el-date-picker  class="filter-item"  v-model="listQuery.endTime" type="datetime" format="yyyy-MM-dd HH:mm:ss" placeholder="选择结束时间" />
        <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" circle @click="getList"></el-button>
        <br/>
      <el-button-group>
        <el-button class="filter-item" type="primary" size="mini" icon="el-icon-plus" @click="handleCreate">添加盲盘
        </el-button>
      </el-button-group>
    </div>
    <el-table :data="list" row-key="id" :key="tableKey" v-loading="listLoading" border fit highlight-current-row
    @selection-change="handleSelectionChange"
      style="width: 100%;">

      <el-table-column label="操作人" prop="addBy" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.addBy }}</span>
        </template>
      </el-table-column>
      <el-table-column label="盲盘时间" width="200px" align="center" prop="addTime">
        <template slot-scope="scope">
          <span>{{ scope.row.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="商品总数" prop="totalNum" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.totalNum }}</span>
        </template>
      </el-table-column>
      <el-table-column label="正确数量" prop="rightNum" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.rightNum }}</span>
        </template>
      </el-table-column>
      <el-table-column label="错误数量" prop="errorNum" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.errorNum }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" prop="inNum" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status != 1 ? 'danger' : 'success' ">{{ scope.row.status == 1 ? "成功" : "失败"}}</el-tag>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize"
      @pagination="getList" />

    <!--添加编辑框-->
    <el-dialog title="添加盲盘" :visible.sync="dialogFormVisible">

    <el-form ref="dataForm" :model="dataForm" label-width="90px" label-position="right"
        style="width: 100%;">

      <el-form-item label="商品" prop="type">

        <div class="filter-container">
          <el-button-group>
            <el-button class="filter-item" size="mini" type="primary" icon="el-icon-refresh"  @click="searchStock">开始盲盘</el-button>
          </el-button-group>
        </div>

        <el-table :data="goodsList" border fit highlight-current-row style="width: 100%;" max-height="400"
        >
          <el-table-column label="品名"  align="center">
            <template slot-scope="scope">
              <el-tooltip class="item" effect="dark" :content="scope.row.goodsName" placement="top">
                <span class="line1">
                  {{ scope.row.goodsName }}</span>
              </el-tooltip>
            </template>
          </el-table-column>
          <el-table-column label="单位"  align="center">
            <template slot-scope="scope">
              <span>
                {{ scope.row.skuName }}</span>
            </template>
          </el-table-column>
          <el-table-column label="仓库" align="center">
            <template slot-scope="scope">
              {{ scope.row.storageName }}
            </template>
          </el-table-column>
          <el-table-column label="货位名称"  align="center">
            <template slot-scope="scope">
              {{ scope.row.spaceName }}
            </template>
          </el-table-column>
          <el-table-column label="盲盘库存"  align="center">
            <template slot-scope="scope">
              <el-input v-model="scope.row.countingStock" placeholder="" />
            </template>
          </el-table-column>
        </el-table>
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
    storageCountingGet,
    storageCountingAdd,
    storageCountingGetDetail,
    storageStockGet
  } from '@/api/trade'
  import waves from '@/directive/waves' // Waves directive
  import {
    parseTime,
    renderTime
  } from '@/utils'
  import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

  export default {
    name: 'counting',
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
        activeName: "1",
        currentShopId: 0,
        goodsList: []
      }
    },
    created() {
      this.getList()
    },
    methods: {
      getList() {
        this.listLoading = true
        this.listQuery.dateFrom = renderTime(this.listQuery.startTime)
        this.listQuery.dateTo = renderTime(this.listQuery.endTime)
        storageCountingGet(
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
        this.goodsList = []
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      },
      confirm() {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            storageCountingAdd({countingJson: JSON.stringify(this.goodsList)}).then(() => {
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
      searchStock(){
        this.goodsList = [];
        storageStockGet({page: 1, pageSize: 99999}).then(response => {
          this.goodsList = response.data.list
        })
      },

    }
  }
</script>
