<template>
  <div class="app-container">
    <div class="filter-container">
			<el-input v-model="listQuery.orderNo" clearable placeholder="订单号" style="width: 200px;" class="filter-item" @keyup.enter.native="search" />
			<el-input v-model="listQuery.memberName" clearable placeholder="用户名" style="width: 200px;" class="filter-item" @keyup.enter.native="search" />
			<el-date-picker class="filter-item" v-model="listQuery.startTime" type="datetime" format="yyyy-MM-dd HH:mm:ss" placeholder="选择开始时间" />
			-
			<el-date-picker  class="filter-item"  v-model="listQuery.endTime" type="datetime" format="yyyy-MM-dd HH:mm:ss" placeholder="选择结束时间" />

			<el-select v-model="listQuery.inOut" placeholder="收入/支出" clearable class="filter-item" style="width: 130px">
			  <el-option v-for="item in inOutOptions" :key="item.key" :label="item.text" :value="item.key" />
			</el-select>
      <el-select v-model="checkedStatus" multiple clearable collapse-tags placeholder="请选择" class="filter-item" style="width: 300px">
          <el-option
            v-for="item in statusOptions"
            :key="item.value"
            :label="item.name"
            :value="item.value">
          </el-option>
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
			<el-table-column label="用户名" width="120"  align="center" prop="memberName">
			  <template slot-scope="scope">
			    <router-link :to="'/member/detail?memberId='+scope.row.memberId">
			      <p class="link-type">{{ scope.row.memberName }}</p>
			    </router-link>
			  </template>
			</el-table-column>
			<el-table-column label="收入/支出" width="100"  align="center" prop="inOut">
			  <template slot-scope="scope">
			    <el-tag :type="scope.row.inOut == '-1' ? 'danger' : 'success' ">{{ scope.row.inOut | inOutFilter }}</el-tag>
			  </template>
			</el-table-column>
			<el-table-column label="资金类型" width="150"  align="center" prop="type">
			  <template slot-scope="scope">
			    <el-tag>{{ scope.row.typeName}}</el-tag>
			  </template>
			</el-table-column>
			<el-table-column label="收入" width="100"  align="center" prop="amount">
			  <template slot-scope="scope">
			    <span style="color:red;" v-if="scope.row.inOut == '1'">{{ scope.row.amount | moneyFormat }}</span>
			  </template>
			</el-table-column>
      <el-table-column label="支出" width="100"  align="center" prop="amount">
        <template slot-scope="scope">
          <span style="color:red;" v-if="scope.row.inOut == '-1'">-{{ scope.row.amount | moneyFormat }}</span>
        </template>
      </el-table-column>
      <el-table-column label="剩余" width="100"  align="center" prop="amountAfter">
        <template slot-scope="scope">
          <span style="color:red;">{{ scope.row.amountAfter | moneyFormat }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作时间" width="150" align="center" prop="addTime" >
        <template slot-scope="scope">
          <span>{{ scope.row.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
	  <el-table-column label="贡献人" width="150" align="center" prop="devoteName" >
	    <template slot-scope="scope">
        <router-link :to="'/member/detail?memberId='+scope.row.devoteId">
          <p class="link-type">{{ scope.row.devoteName }}</p>
        </router-link>
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
import {getSpmissionLogList, getAccountTypeSelector} from '@/api/fund'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime, moneyFormat } from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

const inOutOptions = [
  { key: '1', text: '收入' },
  { key: '-1', text: '支出' }
]

const inOutKeyValue = inOutOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

export default {
  name: 'spmission',
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
    }
  },
  created() {
    const memberName = this.$route.query && this.$route.query.memberName
    if(memberName){
      this.listQuery.memberName = memberName;
    }
   this.getAccountTypeSelector()
  },
  methods: {
		getAccountTypeSelector(){
		  getAccountTypeSelector({type: 3}).then(response => {
		    this.statusOptions = response.data;
		    this.statusKeyValue = this.statusOptions.reduce((acc, cur) => {
		      acc[cur.value] = cur.name
		      return acc
		    }, {})
		    this.getList()
		  })
		},
		getAccountTypeName(value){
		  return this.statusKeyValue[value];
		},
    getList() {
      this.listLoading = true
			this.listQuery.dateFrom = renderTime(this.listQuery.startTime)
			this.listQuery.dateTo = renderTime(this.listQuery.endTime)
			if(this.checkedStatus && this.checkedStatus.length > 0){
				this.listQuery.types = this.checkedStatus.join(",")
			}
      getSpmissionLogList(this.listQuery).then(response => {
        this.list = response.data.list
        this.list.forEach((item)=>{
          item.typeName = this.getAccountTypeName(item.type);
        })
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
       await getSpmissionLogList(query).then(response => {
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
          query.pageSize = 1000
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
               filename: '推广佣金明细',
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
         }else if(j == 'type'){
           return this.getAccountTypeName(v[j])
         }else if(j == 'inOut'){
           return v[j] == '-1' ? '支出' : '收入'
         }else{
          return v[j]
         }
      }))
       return data;
    },

  }
}
</script>
