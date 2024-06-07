<template>
  <div class="app-container">
      <div class="filter-container">
        <el-input v-model="listQuery.name" clearable placeholder="店铺名称" style="width: 200px;" class="filter-item" @keyup.enter.native="getList" />
        <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" circle @click="getList"></el-button>
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
			<el-table-column type="selection" width="55" align="center"></el-table-column>
      <el-table-column label="店铺LOGO" align="center">
        <template slot-scope="scope">
          <el-image
              fit="contain"
              style="height: 40px; width:40px;"
              :src="scope.row.logo"
              :preview-src-list="[scope.row.logo]">
            </el-image>
        </template>
      </el-table-column>
			<el-table-column label="店铺名称"  align="center">
			  <template slot-scope="scope">
			    <span>{{ scope.row.name}}</span>
			  </template>
			</el-table-column>
      <el-table-column label="所属用户"  align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.memberName}}</span>
        </template>
      </el-table-column>
      <!-- 会员数、商品数、SKU数、总订单额、订单笔数 -->
      <!-- <el-table-column label="会员数"  align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.memberNum}}</span>
        </template>
      </el-table-column> -->
      <el-table-column label="商品数"  align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.goodsNum}}</span>
        </template>
      </el-table-column>
      <el-table-column label="SKU数"  align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.goodsSkuNum}}</span>
        </template>
      </el-table-column>
      <el-table-column label="总订单额"  align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.businessAmount}}</span>
        </template>
      </el-table-column>
      <el-table-column label="订单笔数"  align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.orderNum}}</span>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />

  </div>
</template>

<script>
import {getShopDataList} from '@/api/shop'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime } from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
import Upload from '@/components/Upload/singleImage'
import ImageSource from '@/components/Upload/imageSource'
import {
  provinceAndCityDataPlus, regionData, CodeToText
} from 'element-china-area-data'


const statusOptions = [
  { key: 1, text: '正常' },
  { key: 0, text: '停用' }
]

// arr to obj ,such as { CN : "China", US : "USA" }
const statusKeyValue = statusOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

const typeOptions = [
  { key: 1, text: '个人' },
  { key: 2, text: '企业' },
  { key: 3, text: '旗舰' },
]

// arr to obj ,such as { CN : "China", US : "USA" }
const typeKeyValue = typeOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

export default {
  name: 'shopDataList',
  components: { Pagination, Upload, ImageSource },
  directives: { waves },
	filters: {
	  statusFilter(status) {
	    return statusKeyValue[status]
	  },
    typeFilter(status) {
      return typeKeyValue[status]
    }
	},
  data() {
    return {
      addressOptions: regionData,
      tableKey: 0,
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        pageSize: 20,
      },
      shop: {},
      dialogFormVisible: false,
      dialogType: '',
			multipleSelection: [],
			statusOptions,
			activeName:'first',
      locationActiveName:'first',
      typeOptions,
      checkedService:[],
      serviceOptions:null
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      getShopDataList(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.total
				this.listLoading = false
      })
    },

  }
}
</script>
