<template>
  <div class="app-container">
    <el-row class="tac">
      <el-col :span="2">
        <el-menu
          :default-active="defaultActiveId"
          class="el-menu-vertical-demo">

          <el-menu-item :index="item.id + ''" v-for="item in storageOptions" @click="chooseStorage(item.id)" >
            <span>{{item.name}}</span>
          </el-menu-item>

        </el-menu>
      </el-col>

      <el-col :span="21" v-if="!storageOptions || storageOptions.length == 0">
        <el-result icon="warning" title="警告提示" subTitle="请先添加仓库再进行此操作">
              <template slot="extra">
                <el-button type="primary" size="small" @click="
                    $store.dispatch('tagsView/delView', $route); $router.back(-1)">返回</el-button>
              </template>
            </el-result>
      </el-col>

      <el-col :span="21" style="margin-left: 10px;" v-if="storageOptions && storageOptions.length > 0">

    <div class="filter-container">
      <el-input v-model="listQuery.goodsName" clearable placeholder="商品名称" style="width: 200px;" class="filter-item" @keyup.enter.native="getList()" />
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" circle @click="getList"></el-button>
      <br/>
      <el-button class="filter-item" type="primary" size="mini" @click="$router.push('/stock/log')">
        进出库记录
      </el-button>
		</div>
		<el-table
      :key="tableKey"
      v-loading="listLoading"
      :data="list"
      border
      fit
      highlight-current-row
      style="width: 100%;"
    >
      <!-- <el-table-column label="货位编号" align="center">
        <template slot-scope="scope">
      		<span>{{ scope.row.spaceNo}}</span>
        </template>
      </el-table-column> -->

      <el-table-column label="货位" align="center">
        <template slot-scope="scope">
      		<span>{{ scope.row.spaceName}}</span>
        </template>
      </el-table-column>

      <el-table-column label="商品类目" align="center">
        <template slot-scope="scope">
      		<span>{{ scope.row.cateName}}</span>
        </template>
      </el-table-column>

			<el-table-column label="商品标题"  align="center">
			  <template slot-scope="scope">
          <el-tooltip class="item" effect="dark" :content="scope.row.goodsName" placement="top">
            <span class="line1">
              {{ scope.row.goodsName }}</span>
          </el-tooltip>
			  </template>
			</el-table-column>

      <el-table-column label="规格" align="center">
        <template slot-scope="scope">
      		<span>{{ scope.row.skuName}}</span>
        </template>
      </el-table-column>


			<el-table-column label="库存"  align="center">
			  <template slot-scope="scope">
          <span>{{ scope.row.stock }}</span>
			  </template>
			</el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />


      </el-col>
    </el-row>
  </div>
</template>

<script>
import Vue from 'vue'
import {storageGet, storageStockGet, storageStockGetLog} from '@/api/trade'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime } from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

const typeOptions = [
  { key: 1, text: '普通' },
  { key: 2, text: '秒杀' },
  { key: 3, text: '拼团' },
]

// arr to obj ,such as { CN : "China", US : "USA" }
const typeKeyValue = typeOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

const statusOptions = [
  { key: 1, text: '上架' },
  { key: 2, text: '仓库' },
]

// arr to obj ,such as { CN : "China", US : "USA" }
const statusKeyValue = statusOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

export default {
  name: 'storageStock',
  components: { Pagination },
  directives: { waves },
	filters: {
	  typeFilter(status) {
	    return typeKeyValue[status]
	  },
    statusFilter(type) {
	    return statusKeyValue[type]
	  },
	},
  data() {
    return {
      props:{ label: 'name', value : 'id', children: 'children', checkStrictly: true, multiple :true },
      tableKey: 0,
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        pageSize: 20,
        status: 1
      },
      couponConfig: {},
      dialogFormVisible: false,
      dialogType: '',
			multipleSelection: [],
			typeOptions,
			activeName: 'second',
      goodsOptions:[],
      shopCateOptions:[],
      storageOptions: [],
      defaultActiveId: null,
      batchSet: false,
      temp: {}
    }
  },
  created() {
    this.getShopList()
  },
  methods: {
    getShopList(){
      storageGet({page: 1, pageSize: 9999}).then(response => {
        this.storageOptions = response.data.list
        if(this.storageOptions && this.storageOptions.length > 0){
          this.defaultActiveId = this.storageOptions[0].id + ""
          this.chooseStorage(this.defaultActiveId)
        }
      })
    },
    chooseStorage(storageId){
      this.listQuery.storageId = storageId;
      this.getList()
    },
    getList() {
      this.listLoading = true
      storageStockGet(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.total
				this.listLoading = false
      })
    },

  }
}
</script>

<style lang="scss" scoped>

  .el-radio{
    margin-right: 0px !important;
  }
  .el-select-dropdown{
    span{white-space: pre !important;}
  }
  .el-select-dropdown__item{
    white-space: pre !important;
  }

</style>
