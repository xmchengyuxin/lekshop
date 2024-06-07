<template>
  <div class="app-container">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>商品入库</span>
        <el-button style="float: right;margin-left:10px;" type="danger" @click="dataForm={type:1}">
          清空
        </el-button>
        <el-button style="float: right; " type="primary" @click="confirm">
          保存
        </el-button>
      </div>
    <el-form ref="dataForm" :model="dataForm" label-width="90px" label-position="right"
        style="width: 100%;">
          <el-form-item label="采购单号" prop="buyOrderNo" :rules="[{ required: true, message: '请填写采购单号', trigger: 'blur' }]">
            <el-input v-model="dataForm.buyOrderNo" placeholder="请输入采购单号" style="width:60%" />
          </el-form-item>
          <!-- <el-form-item label="供应商ID" prop="supplierId" :rules="[{ required: true, message: '请填写供应商ID', trigger: 'blur' }]">
            <el-input v-model="dataForm.supplierId" placeholder="请输入供应商ID" style="width:60%" />
          </el-form-item>
          <el-form-item label="供应商" prop="supplier" :rules="[{ required: true, message: '请填写供应商', trigger: 'blur' }]">
            <el-input v-model="dataForm.supplier" placeholder="请输入供应商" style="width:60%" />
          </el-form-item> -->
          <el-form-item label="供应商" prop="supplier" :rules="[{ required: true, message: '请选择供应商', trigger: 'blur' }]">
            <el-select v-model="dataForm.supplier" value-key="name" @change="changeSupplier" placeholder="请选择供应商" clearable class="filter-item" style="width:60%">
              <el-option v-for="item in supplierOptions" :key="item.id" :label="item.name" :value="item" />
            </el-select>
            <el-button @click="$router.push('/config/supplier')"size="mini" type="danger">新建供应商</el-button>
          </el-form-item>
          <el-form-item label="订单金额" prop="orderAmount" :rules="[{ required: true, message: '请填写订单金额', trigger: 'blur' }]">
            <el-input v-model="dataForm.orderAmount" placeholder="请输入订单金额" style="width:60%" />
          </el-form-item>
          <el-form-item label="实付金额" prop="realAmount" :rules="[{ required: true, message: '请填写实付金额', trigger: 'blur' }]">
            <el-input v-model="dataForm.realAmount" placeholder="请输入实付金额" style="width:60%" />
          </el-form-item>
          <el-form-item label="欠款" prop="oweAmount" :rules="[{ required: true, message: '请填写欠款', trigger: 'blur' }]">
            <el-input v-model="dataForm.oweAmount" placeholder="请输入欠款" style="width:60%" />
          </el-form-item>
          <el-form-item label="是否到票" prop="ticketStatus" :rules="[{ required: true, message: '请选择是否到票', trigger: 'blur' }]">
            <el-radio-group v-model="dataForm.ticketStatus" size="small">
              <el-radio :label="0" border>否</el-radio>
              <el-radio :label="1" border>是</el-radio>
            </el-radio-group>
          </el-form-item>


      <el-form-item label="商品" prop="type">

        <div class="filter-container">
          <el-button-group>
            <el-button class="filter-item" size="mini" type="primary" icon="el-icon-plus"  @click="openGoodsDialog">选择商品</el-button>
          </el-button-group>
        </div>

        <el-table :data="goodsList" border fit highlight-current-row style="width: 100%;"
        >
          <el-table-column label="商品"  align="center">
            <template slot-scope="scope">
              <el-tooltip class="item" effect="dark" :content="scope.row.goodsName" placement="top">
                <span class="line1">
                  {{ scope.row.goodsName }}</span>
              </el-tooltip>
            </template>
          </el-table-column>
          <el-table-column label="规格" align="center">
            <template slot-scope="scope">
              <span>
                {{ scope.row.skuName }}</span>
            </template>
          </el-table-column>
          <el-table-column label="选择仓库" width="150px"  align="center">
            <template slot-scope="scope">
              <el-select v-model="scope.row.storageId"  :rules="[{ required: true, message: '请选择仓库', trigger: 'blur' }]">
                <el-option v-for="item in storageOptions" :key="item.id" :label="item.name" :value="item.id" />
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="货位名称" width="120px"  align="center">
            <template slot-scope="scope">
              <el-input v-model="scope.row.spaceName" placeholder="" />
            </template>
          </el-table-column>
          <el-table-column label="进货数量" width="120px"  align="center">
            <template slot-scope="scope">
              <el-input v-model="scope.row.inNum" placeholder="" />
            </template>
          </el-table-column>
          <el-table-column label="进价" width="120px"  align="center">
            <template slot-scope="scope">
              <el-input v-model="scope.row.buyPrice" placeholder="" />
            </template>
          </el-table-column>
          <el-table-column label="折扣%" width="120px"  align="center">
            <template slot-scope="scope">
              <el-input v-model="scope.row.discount" placeholder="" />
            </template>
          </el-table-column>
          <el-table-column label="总价" width="120px"  align="center">
            <template slot-scope="scope">
              <el-input v-model="scope.row.totalPrice" placeholder="" />
            </template>
          </el-table-column>
          <el-table-column label="操作" width="100px"  align="center">
            <template slot-scope="scope">
              <el-button class="filter-item" size="mini" type="danger" icon="el-icon-delete" @click="handleDeleteGoods(scope.$index)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-form-item>
    </el-form>
   </el-card>

</br>
<el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>历史记录</span>
      </div>
    <div class="filter-container">
        <el-input v-model="listQuery.buyOrderNo" clearable placeholder="采购单号" style="width: 200px;" class="filter-item" @keyup.enter.native="getList()" />
        <el-input v-model="listQuery.orderNo" clearable placeholder="入库单号" style="width: 200px;" class="filter-item" @keyup.enter.native="getList()" />
        <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" circle @click="getList"></el-button>
        <!-- <br/>
      <el-button-group>
        <el-button class="filter-item" type="primary" size="mini" icon="el-icon-plus" @click="handleCreate">添加入仓
        </el-button>
      </el-button-group> -->
    </div>
    <el-table :data="list" row-key="id" :key="tableKey" v-loading="listLoading" border fit highlight-current-row
    @selection-change="handleSelectionChange"
      style="width: 100%;">

      <el-table-column label="入仓单号" prop="orderNo" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.orderNo }}</span>
        </template>
      </el-table-column>
      <el-table-column label="采购单号" prop="buyOrderNo" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.buyOrderNo }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="供应商ID" prop="supplierId" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.supplierId }}</span>
        </template>
      </el-table-column> -->
      <el-table-column label="供应商" prop="supplier" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.supplier }}</span>
        </template>
      </el-table-column>
      <el-table-column label="订单金额" prop="orderAmount" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.orderAmount | moneyFormat}}</span>
        </template>
      </el-table-column>
      <el-table-column label="实付金额" prop="realAmount" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.realAmount | moneyFormat}}</span>
        </template>
      </el-table-column>
      <el-table-column label="欠款" prop="oweAmount" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.oweAmount | moneyFormat}}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否到票" prop="ticketStatus" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.ticketStatus == 1 ? '已到票' : '未到票'}}</span>
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
      <el-table-column label="操作" class-name="small-padding" fixed="right" width="220">
        <template slot-scope="scope">
          <el-tooltip class="item" effect="dark" content="查看详情" placement="top">
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleDetail(scope.row)"></el-button>
          </el-tooltip>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize"
      @pagination="getList" />

   </el-card>
    <!--订单详情-->
    	<el-dialog title="详情" :visible.sync="dialogDetailVisible" width="60%">
        <el-card class="box-card-dialog" shadow="hover">
          <div slot="header" class="clearfix">
            <span>商品信息</span>
          </div>
          <el-table
              :data="order.orderDetailList"
              border
              style="width: 100%">
              <el-table-column label="品名"  align="center">
                <template slot-scope="scope">
                  <el-tooltip class="item" effect="dark" :content="scope.row.goodsName" placement="top">
                    <span class="line1">
                      {{ scope.row.goodsName }}</span>
                  </el-tooltip>
                </template>
              </el-table-column>
              <el-table-column label="规格" align="center">
                <template slot-scope="scope">
                  <span>
                    {{ scope.row.skuName }}</span>
                </template>
              </el-table-column>
              <el-table-column label="仓库" width="100px"  align="center">
                <template slot-scope="scope">
                  {{ scope.row.storageName }}
                </template>
              </el-table-column>
              <el-table-column label="货位名称" width="100px"  align="center">
                <template slot-scope="scope">
                  {{ scope.row.spaceName }}
                </template>
              </el-table-column>
              <el-table-column label="进货数量" width="80px"  align="center">
                <template slot-scope="scope">
                  {{ scope.row.inNum }}
                </template>
              </el-table-column>
              <el-table-column label="进价" width="80px"  align="center">
                <template slot-scope="scope">
                  {{ scope.row.buyPrice }}
                </template>
              </el-table-column>
              <el-table-column label="折扣%" width="80px"  align="center">
                <template slot-scope="scope">
                  {{ scope.row.discount }}
                </template>
              </el-table-column>
              <el-table-column label="总价" width="80px"  align="center">
                <template slot-scope="scope">
                  {{ scope.row.totalPrice }}
                </template>
              </el-table-column>
            </el-table>
        </el-card>
    	</el-dialog>

    <goodsDialog
     v-if="showGoods"
      ref="goodsDialog"
      @selected="selectedGoods"
      @close="closeGoodsDialog"
    ></goodsDialog>

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
    stockOrderGet,
    stockOrderGetDetail,
    stockOrderAdd,
    storageGet,
    getSupplierList
  } from '@/api/trade'
  import waves from '@/directive/waves' // Waves directive
  import {
    parseTime,
    renderTime
  } from '@/utils'
  import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
  import goodsDialog from '@/views/trade/storage/dialog/goods'

  export default {
    name: 'inStock',
    components: {
      Pagination,
      goodsDialog
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
        dataForm: {type: 1},
        dialogFormVisible: false,
        dialogDetailVisible: false,
        dialogType: '',
        activeName: "1",
        storageOptions: [],
        showGoods: false,
        currentShopId: 0,
        goodsList: [],
        order: {},
        supplierOptions: []
      }
    },
    created() {
      this.getList()
      this.getSupplierList()
      this.getStorageList()
    },
    methods: {
      getSupplierList(){
        getSupplierList({page: 1, pageSize: 9999}).then(response => {
          this.supplierOptions = response.data.list
        })
      },
      getStorageList(){
        storageGet({page: 1, pageSize: 9999}).then(response => {
          this.storageOptions = response.data.list
        })
      },
      getList() {
        this.listLoading = true
        stockOrderGet(
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
          type: 1
        }
        this.goodsList = []
        this.storageOptions = []
        this.getStorageList()
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      },
      confirm() {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            let formData = Object.assign({}, this.dataForm)
            formData.stockJson = JSON.stringify(this.goodsList)
            stockOrderAdd(formData).then(() => {
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
      handleDetail(row){
        this.order = {};
        stockOrderGetDetail({id: row.id}).then(response => {
          this.order = response.data
          this.dialogDetailVisible = true;
        })
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      handleDeleteGoods(index) {
        this.goodsList.splice(index, 1);
      },
      changeType(){
        this.goodsList = []
      },
      openGoodsDialog(){
        this.showGoods = true
      },
      closeGoodsDialog(){
        this.showGoods = false
      },
      // 回调选择的商品
      selectedGoods(goods) {
        console.log(goods)
        if(!goods || goods.length == 0){
          return;
        }
        let selectGoods = [];
        goods.forEach(val =>{
          selectGoods.push({
            skuId: val.id,
            goodsName: val.goodsName,
            skuName: val.attrSymbolName
          });
        })
        this.goodsList = selectGoods;
      },
      changeSupplier(item){
        this.dataForm.supplierId = item.id
        this.dataForm.supplier = item.name
      },
    }
  }
</script>
