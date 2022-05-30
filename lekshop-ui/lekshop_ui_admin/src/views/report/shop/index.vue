<template>
  <div class="app-container">
    <el-tabs v-model="activeName" @tab-click="handleClickTab">
    	  <el-tab-pane label="日报统计" name="first"></el-tab-pane>
    		<el-tab-pane label="月报统计" name="second"></el-tab-pane>
    </el-tabs>
    <div class="filter-container">
  <el-select v-model="listQuery.shopId" placeholder="店铺" filterable clearable class="filter-item">
     <el-option v-for="item in shopOptions" :key="item.id" :label="item.name" :value="item.id">
     </el-option>
   </el-select>
	<el-date-picker
    v-if="activeName == 'first' "
		v-model="queryDayTime"
    style="vertical-align: middle;margin-bottom: 10px;"
		type="daterange"
		align="right"
		unlink-panels
		range-separator="-"
		start-placeholder="开始日期"
		end-placeholder="结束日期"
		:picker-options="pickerOptions">
    </el-date-picker>

    <el-date-picker
      v-if="activeName == 'second' "
    	style="vertical-align: middle;margin-bottom: 10px;"
    	v-model="queryMonthTime"
    	type="monthrange"
    	align="right"
    	unlink-panels
    	range-separator="-"
    	start-placeholder="开始月份"
    	end-placeholder="结束月份"
    	:picker-options="monthPickerOptions">
      </el-date-picker>

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
      <el-table-column label="统计时间" align="center" width="120">
        <template slot-scope="scope">
      		<el-tag  effect="plain" v-if="activeName == 'first'">{{ scope.row.day | parseTime('{y}-{m}-{d}') }}</el-tag>
          <el-tag  effect="plain"  v-if="activeName == 'second'">{{ scope.row.month}}</el-tag>
        </template>
      </el-table-column>
			<el-table-column label="店铺" align="center" width="150">
			  <template slot-scope="scope">
			    <p>{{ scope.row.shopName }}</p>
			  </template>
			</el-table-column>
      <el-table-column label="销售额" prop="goodsAmount" align="center">
        <template slot-scope="scope">
          <span style="color:red">{{ scope.row.goodsAmount | moneyFormat}}</span>
        </template>
      </el-table-column>
			<el-table-column label="实际收款" prop="businessAmount" align="center">
			  <template slot-scope="scope">
			    <span style="color:red">{{ scope.row.businessAmount | moneyFormat}}</span>
			  </template>
			</el-table-column>
      <el-table-column label="有效订单" align="center">
        <template slot-scope="scope">
      		<p><span style="color:red">{{ scope.row.orderNum}}</span></p>
      	</template>
      </el-table-column>
      <el-table-column label="取消订单" align="center">
        <template slot-scope="scope">
      		<p><span style="color:red">{{ scope.row.cancelNum}}</span></p>
      	</template>
      </el-table-column>
      <el-table-column label="申请退款" align="center">
        <template slot-scope="scope">
      		<p><span style="color:red">{{ scope.row.refundNum}}</span></p>
      	</template>
      </el-table-column>
      <el-table-column label="退单率" align="center">
        <template slot-scope="scope">
      		<p><span style="color:red">{{ scope.row.refundRate}}%</span></p>
      	</template>
      </el-table-column>
			<el-table-column label="下单人数" align="center">
			  <template slot-scope="scope">
					<p><span style="color:red">{{ scope.row.memberNum}}</span></p>
				</template>
			</el-table-column>
      <el-table-column label="好评数量" align="center">
        <template slot-scope="scope">
          <p><span style="color:red"> {{ scope.row.goodCommentNum}}</span></p>
      	</template>
      </el-table-column>
      <el-table-column label="差评数量" align="center">
        <template slot-scope="scope">
          <p><span style="color:red"> {{ scope.row.badCommentNum}}</span></p>
      	</template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />


  </div>
</template>

<script>
import {getShopReportDayList, getShopReportMonthList} from '@/api/report'
import {getShopList} from '@/api/shop'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime } from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

export default {
  name: 'shopReport',
  components: { Pagination },
  directives: { waves },
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
			multipleSelection: [],
      shopOptions:[],
      activeName: 'first',
      queryDayTime: [new Date() - 3600 * 1000 * 24, new Date()],
      queryMonthTime: [new Date(), new Date()],
			pickerOptions: {
          shortcuts: [{
            text: '最近一周',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近一个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近三个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit('pick', [start, end]);
            }
          }]
        },
        monthPickerOptions: {
          shortcuts: [{
            text: '本月',
            onClick(picker) {
              picker.$emit('pick', [new Date(), new Date()]);
            }
          }, {
            text: '今年至今',
            onClick(picker) {
              const end = new Date();
              const start = new Date(new Date().getFullYear(), 0);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近六个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setMonth(start.getMonth() - 6);
              picker.$emit('pick', [start, end]);
            }
          }]
        },
    }
  },
  created() {
    this.getList()
    this.getShopList()
  },
  methods: {
    getShopList(){
      getShopList({page:1, pageSize:9999, status: 1}).then(response => {
        this.shopOptions = response.data.list
      })
    },
    getList(){
      if(this.activeName == 'first'){
        if(this.queryDayTime){
          this.listQuery.dateFrom = renderTime(this.queryDayTime[0])
          this.listQuery.dateTo = renderTime(this.queryDayTime[1])
        }else{
          this.listQuery.dateFrom = null
          this.listQuery.dateTo = null
        }
        this.getDayList()
      }else if(this.activeName == 'second'){
        if(this.queryMonthTime){
          this.listQuery.dateFrom = renderTime(this.queryMonthTime[0])
          this.listQuery.dateTo = renderTime(this.queryMonthTime[1])
        }else{
          this.listQuery.dateFrom = null
          this.listQuery.dateTo = null
        }
        this.getMonthList()
      }
    },
    getDayList() {
      this.listLoading = true
      getShopReportDayList(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.total
				this.listLoading = false
      })
    },
    getMonthList() {
      this.listLoading = true
      getShopReportMonthList(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.total
    		this.listLoading = false
      })
    },
		handleSelectionChange(val) {
			this.multipleSelection = val;
     },
     handleClickTab(tab, event) {
     	this.activeName = tab.name
      this.getList()
     },
  }
}
</script>
