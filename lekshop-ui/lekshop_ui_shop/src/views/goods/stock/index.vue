<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="listQuery.title" clearable placeholder="商品名称" style="width: 200px;" class="filter-item" @keyup.enter.native="getList()" />
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" circle @click="getList"></el-button>
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-refresh" @click="synStock">同步仓库库存</el-button>
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-refresh" @click="refresh">刷新</el-button>
      <div style="margin-bottom: 5px;">
        <a v-if="!batchSet" class="link-type" @click="batchSet = true">是否批量设置?</a>
        <a v-else class="link-type" @click="batchSet = false">取消批量设置?</a>
        <br>
        <div v-if="batchSet">
          <el-input placeholder="批量设置库存" v-model="temp.stock" style="width: 250px;margin-top: 10px;">
              <template slot="prepend">库存</template>
             <el-button slot="append" @click="batchSetParams(temp.stock, 'stock')">批量设置</el-button>
          </el-input>
          <!-- <br>
          <el-input placeholder="批量设置预警库存" v-model="temp.warnStock" style="width: 250px;margin-top: 10px;">
              <template slot="prepend">预警</template>
             <el-button slot="append" @click="batchSetParams(temp.warnStock, 'warnStock')">批量设置</el-button>
          </el-input> -->
        </div>
      </div>

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
      		<span>{{ scope.row.attrSymbolName}}</span>
        </template>
      </el-table-column>
			<el-table-column label="库存"  align="center">
			  <template slot-scope="scope">
          <el-input v-model="scope.row.stock"></el-input>
			  </template>
			</el-table-column>


      <el-table-column :label="$t('table.actions')" class-name="small-padding" fixed="right" width="100px">
        <template slot="header" slot-scope="scope">
          <el-button type="primary" size="mini" @click="updateStock">
            保存
          </el-button>
        </template>

        <template slot-scope="scope">
        </template>
      </el-table-column>


    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />
  </div>
</template>

<script>
import Vue from 'vue'
import {getStockList, updateStock, synStock} from '@/api/goods'
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
  name: 'goodsStock',
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
      shopOptions: [],
      defaultActiveId: null,
      batchSet: false,
      temp: {}
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      getStockList(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.total
				this.listLoading = false
      })
    },
		batchSetParams(val, key){
		  this.list.forEach(item=>{
		    Vue.set(item, key, val);
		  })
		},
    updateStock(){
      let stockList = [];
      this.list.forEach(item => {
        stockList.push({
          id: item.id,
          stock: item.stock,
        })
      })
      let stockJson = JSON.stringify(stockList);
      updateStock({stockJson: stockJson}).then(() => {
        this.$notify({
          title: '成功',
          message: '库存更新成功',
          type: 'success',
          duration: 2000
        })
      })
    },
    refresh() {
      let { fullPath } = this.$route
      this.$nextTick(() => {
        this.$router.replace({
          path: '/redirect' + fullPath
        })
      })
    },
    synStock(){
      this.listLoading = true
      synStock().then(() => {
        this.$notify({
          title: '成功',
          message: '同步成功',
          type: 'success',
          duration: 2000
        })
        this.getList()
      })
    }

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
