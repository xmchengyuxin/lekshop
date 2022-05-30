<template>
  <div class="createPost-container" style="position:relative;left:-100px;width: 1000px;margin: 20px auto;padding: 30px 100px 50px 70px; border: 1px solid #f4f4f4;">
  <el-alert
      title="顾客实际转化报表"
      type="info"
      show-icon
      :closable="false"
      style="height: 60px;">
    </el-alert>
    <br>

      <div class="filter-container">
    <el-date-picker
    	v-model="queryTime"
      style="vertical-align: middle;margin-bottom: 10px;"
    	type="daterange"
    	align="right"
    	unlink-panels
    	range-separator="-"
    	start-placeholder="开始日期"
    	end-placeholder="结束日期"
    	:picker-options="pickerOptions">
      </el-date-picker>

    		<el-button class="filter-item" type="primary" icon="el-icon-search" circle @click="get"></el-button>
      </div>

    <el-row :gutter="32">
      <el-col :xs="24" :sm="24" :lg="20">
        <div class="chart-wrapper">
          <pie-chart :pie-data="pieData"/>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :lg="20">
        <div class="chart-wrapper">
          <span style="color: #909399;">转化率:<span style="color: red;">{{config.transRate}}%</span></span>
        </div>
      </el-col>
    </el-row>

</div>
</template>

<style scoped="scoped">
  /deep/ .line {
    text-align: center;
  }
</style>
<script>
import { getConversionReportList } from '@/api/report'
import { parseTime, renderTime } from '@/utils'
import PieChart from './components/PieChart'

export default {
  name: 'conversionReport',
  components: {
    PieChart
  },
  data() {
    return {
      listQuery:{},
			config:{transRate:0},
      pieData:{},
			loading : true,
      queryTime: null,
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
    }
  },
  created() {
    this.get()
  },
  methods: {
    get() {
      if(this.queryTime){
      	this.listQuery.dateFrom = renderTime(this.queryTime[0])
      	this.listQuery.dateTo = renderTime(this.queryTime[1])
      }else{
        this.listQuery.dateFrom = null
        this.listQuery.dateTo = null
      }
      getConversionReportList(this.listQuery).then(response => {
        this.config = response.data
        if(this.config){
          this.pieData = {xcxOpenNum : this.config.xcxOpenNum, appOpenNum: this.config.appOpenNum, linkOpenNum: this.config.linkOpenNum}
        }else{
          this.config = {transRate:0}
          this.pieData = {xcxOpenNum : 0, appOpenNum: 0, linkOpenNum: 0}
        }
				this.loading = false
      })
    },
  }
}
</script>
