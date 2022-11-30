<template>
  <div class="app-container">
    <div class="filter-container">
			<el-input v-model="listQuery.orderNo" clearable placeholder="订单号" style="width: 200px;" class="filter-item" @keyup.enter.native="search" />
			<el-input v-model="listQuery.goodsName" clearable placeholder="商品" style="width: 200px;" class="filter-item" @keyup.enter.native="search" />
      <el-select v-model="listQuery.storageId" clearable placeholder="选择仓库" style="width: 200px;" class="filter-item">
        <el-option v-for="item in storageOptions" :key="item.id" :label="item.name" :value="item.id" />
      </el-select>
      <el-input v-model="listQuery.spaceName" clearable placeholder="货位" style="width: 200px;" class="filter-item" @keyup.enter.native="search" />
			<el-date-picker class="filter-item" v-model="listQuery.startTime" type="datetime" format="yyyy-MM-dd HH:mm:ss" placeholder="选择开始时间" />
			-
			<el-date-picker  class="filter-item"  v-model="listQuery.endTime" type="datetime" format="yyyy-MM-dd HH:mm:ss" placeholder="选择结束时间" />

			<el-select v-model="listQuery.inOut" placeholder="入库/出库" clearable class="filter-item" style="width: 130px">
			  <el-option v-for="item in inOutOptions" :key="item.key" :label="item.text" :value="item.key" />
			</el-select>
			<el-button v-waves class="filter-item" type="primary" icon="el-icon-search" circle @click="search"></el-button>
      <br>
      <el-button class="filter-item" type="info" :loading="downloadLoading" size="mini" icon="el-icon-download" @click="handleDownload">导出当前</el-button>
		</div>
		<el-table
      :key="tableKey"
      ref="table"
      v-loading="listLoading"
      :data="list"
      border
      fit
      highlight-current-row
      style="width: 100%;"
    >
      <el-table-column label="订单号" prop="orderNo" align="center" width="150">
        <template slot-scope="scope">
          <span>{{ scope.row.orderNo }}</span>
        </template>
      </el-table-column>
			<el-table-column label="入库/出库" width="100"  align="center" prop="inOut">
			  <template slot-scope="scope">
			    <el-tag :type="scope.row.inOut == -1 ? 'danger' : 'success' ">{{ scope.row.inOut | inOutFilter }}</el-tag>
			  </template>
			</el-table-column>
      <el-table-column label="仓库" prop="storageName" align="center" width="100">
        <template slot-scope="scope">
          <span>{{ scope.row.storageName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="货位" prop="spaceName" align="center" width="100">
        <template slot-scope="scope">
          <span>{{ scope.row.spaceName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="商品" prop="goodsName" align="center" width="150">
        <template slot-scope="scope">
          <span>{{ scope.row.goodsName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="规格" prop="skuName" align="center" width="120">
        <template slot-scope="scope">
          <span>{{ scope.row.skuName }}</span>
        </template>
      </el-table-column>
			<el-table-column label="入库" width="100"  align="center" prop="num">
			  <template slot-scope="scope">
			    <span style="color:red;" v-if="scope.row.inOut == 1">+{{ scope.row.num }}</span>
			  </template>
			</el-table-column>
      <el-table-column label="出库" width="100"  align="center" prop="num">
        <template slot-scope="scope">
          <span style="color:red;" v-if="scope.row.inOut == -1">-{{ scope.row.num }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作前数量" width="100"  align="center" prop="numAfter">
        <template slot-scope="scope">
          <span style="color:red;">{{ scope.row.numBefore}}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作后数量" width="100"  align="center" prop="numAfter">
        <template slot-scope="scope">
          <span style="color:red;">{{ scope.row.numAfter}}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作时间" width="150" align="center" prop="addTime" >
        <template slot-scope="scope">
          <span>{{ scope.row.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
			<el-table-column label="备注" prop="remark" min-width="200">
			  <template slot-scope="scope">
			    <span>{{ scope.row.remark}}</span>
			  </template>
			</el-table-column>
		</el-table>
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />

  </div>
</template>

<script>
import {storageStockGetLog, storageGet} from '@/api/trade'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime, moneyFormat } from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

const inOutOptions = [
  { key: 1, text: '入库' },
  { key: -1, text: '出库' }
]
const inOutKeyValue = inOutOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

export default {
  name: 'stockLog',
  components: { Pagination },
  directives: { waves },
	filters: {
		inOutFilter(status) {
		  return inOutKeyValue[status]
		}
	},
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
			countMap:{},
      statusOptions: [],
      checkedStatus: [],
      statusKeyValue: null,
			inOutOptions,
      downloadLoading:false,
      storageOptions: []
    }
  },
  created() {
    this.getList()
    this.getStorageList()
  },
  methods: {
    getStorageList(){
      storageGet({page: 1, pageSize: 9999}).then(response => {
        this.storageOptions = response.data.list
      })
    },
    getList() {
      this.listLoading = true
			this.listQuery.dateFrom = renderTime(this.listQuery.startTime)
			this.listQuery.dateTo = renderTime(this.listQuery.endTime)
      storageStockGetLog(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.total
				this.listLoading = false
      })
    },
    search(){
      this.getList()
      // this.count()
    },
    async getDownloadList(query) {
       let list;
       await storageStockGetLog(query).then(response => {
          list = response.data.list
        })
       if(!list || list.length <= 0){
       	this.$notify({
       	  title: '失败',
       	  message: "暂无数据,导出失败",
       	  type: 'error',
       	  duration: 2000
       	})
       	this.downloadLoading = false
       	return;
       }
        return list;
      },
      handleDownload() {
        this.downloadLoading = true
        import('@/vendor/Export2Excel').then(excel => {
          let list = [];
          let query = Object.assign({}, this.listQuery) // copy obj
          query.page = 1
          query.pageSize = 10000
          if(this.listQuery.startTime && this.listQuery.endTime){
            query.dateFrom = renderTime(this.listQuery.startTime)
            query.dateTo = renderTime(this.listQuery.endTime)
          }
          this.getDownloadList(query).then(res=>{
             list = res;
             let table = this.$refs['table'];
             const tHeader = [];
             const filterVal = [];
             table.columns.forEach(item=>{
                 tHeader.push(item.label);
                 filterVal.push(item.property)
             })

             const data = this.formatJson(filterVal, list)
             excel.export_json_to_excel({
               header: tHeader,
               data,
               filename: '出库入库记录',
               autoWidth: true,
               bookType: 'xlsx'
             })
             this.downloadLoading = false
          });

         })
    },
    formatJson(filterVal, jsonData) {
      const data =  jsonData.map(v => filterVal.map((j, index) => {
         if(j == 'addTime'){
           return parseTime(v.addTime, '{y}-{m}-{d} {h}:{i}:{s}')
         }else if(j == 'inOut'){
           return v[j] == -1 ? '出库' : '入库'
         }else{
          return v[j]
         }
      }))
       return data;
    },

  }
}
</script>
