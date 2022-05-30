<template>
  <div class="app-container">
    <el-tabs v-model="activeName" @tab-click="handleClickTab">
    	  <el-tab-pane label="日报统计" name="first"></el-tab-pane>
    		<el-tab-pane label="月报统计" name="second"></el-tab-pane>
    </el-tabs>
    <div class="filter-container">
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
			<el-table-column label="统计时间" align="center">
			  <template slot-scope="scope">
					<el-tag  effect="plain" v-if="activeName == 'first'">{{ scope.row.day | parseTime('{y}-{m}-{d}') }}</el-tag>
          <el-tag  effect="plain"  v-if="activeName == 'second'">{{ scope.row.month}}</el-tag>
			  </template>
			</el-table-column>
			<el-table-column label="总人数" prop="totalNum" align="center">
			  <template slot-scope="scope">
			    <span style="color:red">{{ scope.row.totalNum}}</span>
			  </template>
			</el-table-column>
      <el-table-column label="活跃人数" prop="activiteNum" align="center">
        <template slot-scope="scope">
          <span style="color:red">{{ scope.row.activiteNum}}</span>
        </template>
      </el-table-column>
      <el-table-column label="人数" prop="registerNum" align="center">
        <template slot-scope="scope">
          <p>增长人数:<span style="color:red"> {{ scope.row.registerNum}}人</span></p>
          <p>下单人数:<span style="color:red"> {{ scope.row.orderNum}}人</span></p>
        </template>
      </el-table-column>
      <el-table-column label="小程序" prop="registerNum" align="center">
        <template slot-scope="scope">
          <p>增长人数:<span style="color:red"> {{ scope.row.xcxNum}}人</span></p>
          <p>下单人数:<span style="color:red"> {{ scope.row.xcxOrderNum}}人</span></p>
        </template>
      </el-table-column>
      <el-table-column label="APP" prop="registerNum" align="center">
        <template slot-scope="scope">
          <p>增长人数:<span style="color:red"> {{ scope.row.appNum}}人</span></p>
          <p>下单人数:<span style="color:red"> {{ scope.row.appOrderNum}}人</span></p>
        </template>
      </el-table-column>
      <el-table-column label="H5" prop="registerNum" align="center">
        <template slot-scope="scope">
          <p>增长人数:<span style="color:red"> {{ scope.row.linkNum}}人</span></p>
          <p>下单人数:<span style="color:red"> {{ scope.row.linkOrderNum}}人</span></p>
        </template>
      </el-table-column>
			<el-table-column label="新老客户">
			  <template slot-scope="scope">
					<p>新客下单:<span style="color:red"> {{ scope.row.newOrderNum}}单</span></p>
					<p>老客下单:<span style="color:red"> {{ scope.row.oldOrderNum}}单</span></p>
				</template>
			</el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />


  </div>
</template>

<script>
import {getMemberReportDayList, getMemberReportMonthList} from '@/api/report'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime } from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

export default {
  name: 'memberReport',
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
			brandOptions:[],
      activeName: 'first',
			dialogImageVisible : false,
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
  },
  methods: {
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
      getMemberReportDayList(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.total
				this.listLoading = false
      })
    },
    getMonthList() {
      this.listLoading = true
      getMemberReportMonthList(this.listQuery).then(response => {
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
