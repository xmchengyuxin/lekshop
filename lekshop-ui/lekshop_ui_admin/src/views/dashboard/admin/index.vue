<template>
  <div class="dashboard-editor-container">

   <el-alert
        title=""
        type="warning"
        show-icon
        >
          <span>
          本次登录IP：{{loginData.loginIp}}
          &nbsp;&nbsp;&nbsp;&nbsp;上次登录IP：<span v-if="loginData.lastLoginIp">{{loginData.lastLoginIp}}</span>
          &nbsp;&nbsp;&nbsp;&nbsp;上次登录时间：<span v-if="loginData.lastLoginTime">{{loginData.lastLoginTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
          </span>
          <p style="margin-top: 10px;">
          温馨提示：后台订单列表仅供查看，如需发货、修改订单信息，或需要添加新商家，请注册账户登录 商家后台 操作
          </p>
      </el-alert>
    <panel-group :panel-data="panelData"/>

    <!-- <el-row :gutter="12" >
      <el-col :span="4" v-for="item in numData" style="margin-bottom:5px;">
        <div @click="$router.push(item.router)">
          <el-card shadow="hover" >
            <div class="card-panel-text" >
              {{item.name}}
            </div>
            <br>
            <count-to style="font-weight: 500;font-size: 20px;" :start-val="0" :end-val="item.num" :duration="2600" class="card-panel-num" />
          </el-card>
        </div>
      </el-col>
    </el-row> -->

    <el-row :gutter="12" >
      <!-- <h3>统计数据</h3> -->
      <el-col :span="6" v-for="item in amountData" style="margin-bottom:10px;">
          <el-card shadow="hover" >
              <div class="card-panel-text" >
            <span style="color: red;font-size: 20px; font-weight: 600;">￥ {{item.amount | moneyFormat}}</span>
              <el-tag
                style="float: right;"
                effect="dark"
                type="success">
                {{item.tips}}
              </el-tag>
              </div>
              <br>
              <span class="tips" style="font-size: 14px;">{{item.name}}</span>
          </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="12">
      <el-col :xs="24" :sm="24" :lg="12">
        <div class="chart-wrapper">
          <line-chart :chart-data="lineChartData"/>
        </div>
      </el-col>
      <el-col :span="4" v-for="item in numData" style="margin-bottom:10px;">
        <div @click="$router.push(item.router)">
          <el-card shadow="hover" style="padding: 10px; background-image: linear-gradient(180deg,#f5f8ff,#fff);
          box-shadow: inset 0 1px 3px 0 hsl(0deg 0% 100% / 50%) " >
            <div class="card-panel-text">
              {{item.name}}
            </div>
            <br>
            <count-to style="font-weight: 500;font-size: 20px;" :start-val="0" :end-val="item.num" :duration="2600" class="card-panel-num" />
          </el-card>
        </div>
      </el-col>
    </el-row>

    <el-row style="margin-bottom:5px;" :gutter="12">
       <el-col style="width: 50%;">
        <h4>本月店铺销售排行</h4>
    		<template>
    			<el-table
    				ref="shopTable"
    				:data="shopData"
    				highlight-current-row
    				style="width: 100%">
    				<el-table-column
    					type="index"
    					width="50"
    					label="排行"
    					align="center">
    				</el-table-column>
    				<el-table-column label="店铺信息" prop="info">
    				  <template slot-scope="scope">
                 <el-popover trigger="hover" placement="top">
                    <p>{{ scope.row.shopAddress }}</p>
                    <div slot="reference" class="name-wrapper">
                      <span class="link-type" size="medium">{{ scope.row.shopName }}</span>
                    </div>
                  </el-popover>
    				  </template>
    				</el-table-column>
    				<el-table-column
    					property="num"
    					label="月销订单"
    					align="center"
              width="100"
    					>
    					<template slot-scope="scope">
    						<span style="color:red">{{ scope.row.num}}</span>
    					</template>
    				</el-table-column>
    				<el-table-column
    					property="totalAmount"
    					label="月销金额"
    					 align="center"
               width="100"
    					>
    					<template slot-scope="scope">
    						<span style="color:red">{{ scope.row.totalAmount | moneyFormat}}</span>
    					</template>
    				</el-table-column>
    			</el-table>
    		</template>
        </el-col>

        <el-col style="width: 50%;">
          <h4>本月商品销售排行</h4>
          	<template>
          		<el-table
          			ref="foodTable"
          			:data="goodsData"
          			highlight-current-row
          			style="width: 100%">
          			<el-table-column
          				type="index"
          				width="50"
          				label="排行"
          				align="center">
          			</el-table-column>
          			<el-table-column label="商品信息" prop="info">
          			  <template slot-scope="scope">
                    <p class="line1">{{ scope.row.goodsName }}</p>
          			  </template>
          			</el-table-column>
          			<el-table-column
          				property="num"
          				label="月销"
          				align="center"
                  width="100"
          				>
          				<template slot-scope="scope">
          					<span style="color:red">{{ scope.row.num}}</span>
          				</template>
          			</el-table-column>
          			<el-table-column
          				property="totalAmount"
          				label="月销金额"
          				 align="center"
                   width="100"
          				>
          				<template slot-scope="scope">
          					<span style="color:red">{{ scope.row.buyTotalPrice | moneyFormat}}</span>
          				</template>
          			</el-table-column>
          		</el-table>
          	</template>
        </el-col>
    </el-row>

    <el-row :gutter="12">
      <el-col :xs="24" :sm="24" :lg="24">
        <div class="chart-wrapper">
          <chart :mix-data="mixData"/>
        </div>
      </el-col>
    </el-row>

    <el-dialog
      title="Java免费开源商城"
      :visible.sync="showTips"
      width="40%"
      center>
      <p class="margin-t10">1. 该项目已完全开源,不留后门,不留核心代码,喜欢的朋友帮忙git上点个star即可</p>
      <p class="margin-t10">2.
        <a class="link-type margin-r10" href="https://github.com/xmchengyuxin/lekshop" target="_blank">Github</a>
        <a class="link-type margin-r10" href="https://gitee.com/chengyuwb/lekshop" target="_blank">Gitee</a>
        <a class="link-type" href="https://www.lekshop.cn/" target="_blank">官网</a>
      </p>
      <p class="margin-t10">3. 如果需要商用或者有问题需要交流, 可以添加我的企业微信
      </p>
      <p class="margin-t10" style="margin-left: 10px;">
       <el-image
           style="height: 150px"
           src="https://www.lekshop.cn/wp-content/uploads/2023/05/企业微信.jpg">
         </el-image>
      </p>
      <p class="margin-t10">4. 目前这套没有买家pc端,但是其实已经开发一半了,因为种种原因停工,希望以后有机会可以开发完再开源出来</p>
      <p class="margin-t10">5. 千万不要拿此项目做违法犯罪的事情,违法必究!!!</p>

    </el-dialog>

  </div>
</template>

<script>
import {countMember, getOrderAddList, countNumber, countAmount, getLoginInfo, getShopSellList, getGoodsSellList, getConversionReport} from '@/api/index'
import PanThumb from '@/components/PanThumb'
import PanelGroup from './components/PanelGroup'
import LineChart from './components/LineChart'
import RaddarChart from './components/RaddarChart'
import PieChart from './components/PieChart'
import BarChart from './components/BarChart'
import TransactionTable from './components/TransactionTable'
import BoxCard from './components/BoxCard'
import CountTo from 'vue-count-to'
import { parseTime, renderTime, moneyFormat} from '@/utils'
import Chart from './components/mixChart'

let interval;
let exceptionInterval;
export default {
  name: 'DashboardAdmin',
  components: {
    PanelGroup,
    PieChart,
    BarChart,
    LineChart,
    TransactionTable,
    BoxCard,
    CountTo,
    Chart
  },
  data() {
    return {
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
      queryTime:[new Date(), new Date()],
			lineChartName:{},
      lineChartData: {},
			panelData:{},
      numData:{},
      amountData:{},
      pieData:{},
      barData:{},
      mixData:[],
      loginData:{},
      platformIncome: 0,
      shopData: [],
      goodsData: [],
      showTips: true
    }
  },
	created() {
	  this.getInfo()
	},
  methods: {
    handleSetLineChartData(type) {
      this.lineChartData = lineChartData[type]
    },
		getInfo(){
      getLoginInfo().then(response => {
      	const result = response.data
        this.loginData = result
      })

			countMember().then(response => {
				const result = response.data
        this.panelData = result
			})

      getOrderAddList().then(response => {
      	const result = response.data
        this.lineChartData = result
      })

      countNumber().then(response => {
    		const result = response.data
        this.numData = result
    	})

      countAmount().then(response => {
      	const result = response.data
        this.amountData = result
      })

      getConversionReport().then(response => {
      	const result = response.data
        this.mixData = result
      })

      getShopSellList().then(response => {
      	const result = response.data
        this.shopData = result
      })

      getGoodsSellList().then(response => {
      	const result = response.data
        this.goodsData = result
      })

		},

  },
  mounted() {
  },
}
</script>

<style lang="scss" scoped>
  .item {
    margin-top: 10px;
    margin-right: 40px;
  }
.dashboard-editor-container {
  padding: 32px;
  background-color: rgb(240, 242, 245);
  position: relative;

  .github-corner {
    position: absolute;
    top: 0px;
    border: 0;
    right: 0;
  }

  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
}
</style>
