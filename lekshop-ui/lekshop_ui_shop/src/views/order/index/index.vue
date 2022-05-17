<template>
  <div class="app-container">
		<el-tabs v-model="activeName" @tab-click="handleClickTab">
			  <el-tab-pane label="全部" name="first"></el-tab-pane>
				<el-tab-pane label="待付款" name="second"></el-tab-pane>
				<el-tab-pane label="待发货" name="third"></el-tab-pane>
				<el-tab-pane label="待收货" name="fourth"></el-tab-pane>
				<el-tab-pane label="已完成" name="five"></el-tab-pane>
				<el-tab-pane label="已取消" name="six"></el-tab-pane>
		</el-tabs>
    <div class="filter-container">
			 <el-input v-model="listQuery.orderNo" clearable placeholder="订单号" style="width: 200px;" class="filter-item" @keyup.enter.native="getList" />
			 <el-input v-model="listQuery.memberName" clearable placeholder="买家" style="width: 200px;" class="filter-item" @keyup.enter.native="getList" />
			 <el-select v-model="listQuery.type" placeholder="订单类型" clearable class="filter-item" style="width: 130px">
			   <el-option v-for="item in orderTypeOptions" :key="item.key" :label="item.text" :value="item.key" />
			 </el-select>
			 <el-date-picker class="filter-item" v-model="listQuery.startTime" type="datetime" format="yyyy-MM-dd HH:mm:ss" placeholder="选择开始时间" />
			 -
			 <el-date-picker  class="filter-item"  v-model="listQuery.endTime" type="datetime" format="yyyy-MM-dd HH:mm:ss" placeholder="选择结束时间" />

			 <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" circle @click="getList"></el-button>
			 <!-- <br>
			 <el-button-group>
					<el-button class="filter-item" type="info" :loading="downloadLoading" size="mini" icon="el-icon-download" @click="handleDownload">导出当前</el-button>
			 </el-button-group> -->
		</div>
		<el-table
      :key="tableKey"
      v-loading="listLoading"
      :data="list"
      highlight-current-row
      style="width: 100%;"
			@selection-change="handleSelectionChange"
    >
			<el-table-column type="selection" width="50" align="center" ></el-table-column>
			<el-table-column label="商品" prop="shopName">
			  <template slot-scope="scope">
          <el-card id="miaosha"  class="box-card" shadow="hover">
            <div slot="header" class="clearfix">
              <span>订单号： {{ scope.row.order.orderNo }}</span>
              <span style="margin-left: 20px;">下单时间：{{ scope.row.order.buyTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}')  }}</span>
              <div style="float: right; padding: 3px 10px">
                <el-button-group>
                   <!-- <el-tooltip class="item" effect="dark" content="删除" placement="top">
                      <el-button v-if="[0,4].indexOf(scope.row.order.status) > 0"  type="danger" icon="el-icon-delete" size="mini" @click="handleDelete(scope.row.order)">
                      </el-button>
                   </el-tooltip> -->
                  <el-tooltip class="item" effect="dark" content="发货" placement="top">
                    <el-button v-if="scope.row.order.status == 1" type="primary" size="mini">
                       <svg-icon icon-class="logistic" />
                    </el-button>
                  </el-tooltip>
                  <el-tooltip class="item" effect="dark" content="修改价格" placement="top">
                    <el-button v-if="scope.row.order.status == 0" type="info" icon="el-icon-edit" size="mini" @click="handleEditPrice(scope.row.order)"></el-button>
                  </el-tooltip>
                  <el-tooltip class="item" effect="dark" content="打印出库单" placement="top">
                    <el-button v-if="scope.row.order.status == 1" type="success"size="mini">
                        <svg-icon icon-class="pdf" />
                    </el-button>
                  </el-tooltip>
                </el-button-group>

              </div>
            </div>

            <div  class="flex  padding-tb10" :class="index < scope.row.orderDetailList.length-1 ? 'bottom-b':''" v-for="(item,index) in scope.row.orderDetailList">
              <el-image
              class="flex f-s-0 margin-r12"
                  style="height: 60px"
                  :src="item.goodsMainImg"
                  :preview-src-list="[item.goodsMainImg]">
                </el-image>
                <div class="flex f-c">
                  <span class="line1">{{item.goodsName}}</span>
                  <span>{{item.goodsParamName}}</span>
                  <span>¥{{ item.buyPrice | moneyFormat}} * {{item.buyNum}} = ¥{{item.buyTotalPrice | moneyFormat}}</span>
                </div>
                <span class="order-role flex f-a-c f-j-c" v-if="scope.row.order.type == 3">拼</span>
                <span class="order-role flex f-a-c f-j-c" v-if="scope.row.order.type == 2">秒</span>
            </div>
          </el-card>


			  </template>
			</el-table-column>
			<el-table-column label="买家" prop="memberName" align="center" width="150">
			  <template slot-scope="scope">
			    <span>{{ scope.row.order.memberName }}</span>
			  </template>
			</el-table-column>
			<el-table-column label="实际支付" prop="price" width="150">
			  <template slot-scope="scope">
			    <p><span style="color:red; font-size: 18px; font-weight: bold;">¥{{ scope.row.order.payPrice | moneyFormat}}</span></p>
					<p>(含运费:<span style="color:red">¥{{ scope.row.order.freightFee | moneyFormat}}</span>)</p>
			  </template>
			</el-table-column>
			<el-table-column label="状态" width="100"  align="center">
			  <template slot-scope="scope">
          <el-tag effect="plain" v-if="scope.row.order.status == 0" type="danger">{{ scope.row.order.status | statusFilter }}</el-tag>
          <el-tag effect="plain" v-if="scope.row.order.status == 1" type="warning">{{ scope.row.order.status | statusFilter }}</el-tag>
          <el-tag effect="plain" v-if="scope.row.order.status == 2" type="info">{{ scope.row.order.status | statusFilter }}</el-tag>
          <el-tag effect="plain" v-if="scope.row.order.status == 3" type="success">{{ scope.row.order.status | statusFilter }}</el-tag>
          <el-tag effect="plain" v-if="scope.row.order.status == 4" type="danger">{{ scope.row.order.status | statusFilter }}</el-tag>
          <br/>
          <a  class="link-type" @click="handleOrderDetail(scope.row)">订单详情</a>
          <br/>
          <a v-if="[2,3].indexOf(scope.row.order.status) > 0" class="link-type">查看物流</a>
			  </template>
			</el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />

  <!--订单详情-->
		<el-dialog title="订单详情" :visible.sync="dialogDetailVisible" >
      <el-steps :active="order.status" align-center>
        <el-step title="下单" :description="order.buyTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}')"></el-step>
        <el-step title="付款" :description="order.payTime ? (order.payTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}')) : ''"></el-step>
        <el-step title="发货" :description="order.deliveryTime ? (order.deliveryTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}')) : ''"></el-step>
        <el-step title="收货" :description="order.finishTime ? (order.finishTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}')) : ''"></el-step>
        <el-step title="完成" :description="order.finishTime ? (order.finishTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}')) : ''"></el-step>
      </el-steps>
      <br/>
      <el-card class="box-card-dialog" shadow="hover">
        <div slot="header" class="clearfix">
          <span>商品信息</span>
        </div>
        <div style="margin-bottom: 10px;">
          <span>订单号： {{order.orderNo}}</span>
          <span style="margin-left: 20px;">买家：{{order.memberName}}</span>
        </div>

        <el-table
            :data="orderDetailList"
            border
            style="width: 100%">
            <el-table-column label="主图" prop="goodsImg" align="center">
              <template slot-scope="scope">
                <el-image
                    style="height: 50px"
                    :src="scope.row.goodsMainImg"
                    :preview-src-list="[scope.row.goodsMainImg]">
                  </el-image>
              </template>
            </el-table-column>
            <el-table-column label="商品名称" prop="goodsName" align="center">
              <template slot-scope="scope">
                <span class="line1">{{ scope.row.goodsName }}</span>
              </template>
            </el-table-column>
            <el-table-column label="购买规格" prop="goodsParamName" align="center">
              <template slot-scope="scope">
                <span>{{ scope.row.goodsParamName }}</span>
              </template>
            </el-table-column>
            <el-table-column label="数量" prop="buyNum" align="center" width="70">
              <template slot-scope="scope">
                <span>{{ scope.row.buyNum }}</span>
              </template>
            </el-table-column>
            <el-table-column label="单价" prop="buyPrice" align="center" width="70">
              <template slot-scope="scope">
                <span>{{ scope.row.buyPrice | moneyFormat}}</span>
              </template>
            </el-table-column>
            <el-table-column label="总价" prop="buyTotalPrice" align="center" width="70">
              <template slot-scope="scope">
                <span>{{ scope.row.buyTotalPrice | moneyFormat }}</span>
              </template>
            </el-table-column>
          </el-table>
          <div style="margin-top: 10px; font-weight: bold; font-size: 15px; float: right;">
            <span >商品总价： <span style="color: red;">￥ {{order.totalPrice | moneyFormat}}</span></span>
            <span style="margin-left: 20px;">运费： <span style="color: red;">￥ {{order.freightFee | moneyFormat}}</span></span>
            <span style="margin-left: 20px;">优惠金额： <span style="color: red;">￥ {{order.couponAmount | moneyFormat}}</span></span>
            <span style="margin-left: 20px;">实际支付： <span style="color: red;">￥ {{order.payPrice | moneyFormat}}</span></span>
          </div>
          <div style="margin-top: 10px;">
            <p style="height: 30px;">交易状态：
              <el-tag effect="plain" v-if="order.status == 0" type="danger">{{ order.status | statusFilter }}</el-tag>
              <el-tag effect="plain" v-if="order.status == 1" type="warning">{{ order.status | statusFilter }}</el-tag>
              <el-tag effect="plain" v-if="order.status == 2" type="info">{{ order.status | statusFilter }}</el-tag>
              <el-tag effect="plain" v-if="order.status == 3" type="success">{{ order.status | statusFilter }}</el-tag>
              <el-tag effect="plain" v-if="order.status == 4" type="danger">{{ order.status | statusFilter }}</el-tag>
            </p>
            <p style="height: 20px;">支付方式： {{order.payMethod}}</p>
            <p style="height: 20px;">配送方式： {{order.deliveryType}}</p>
          </div>
      </el-card>
      <br>
      <el-card class="box-card-dialog" shadow="hover">
        <div slot="header" class="clearfix">
          <span>收货地址</span>
        </div>
        <el-descriptions title="" :column="1" border>
            <el-descriptions-item>
              <template slot="label">
                收货人
              </template>
              {{order.receiveName}}
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                联系电话
              </template>
              {{order.receivePhone}}
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                收货地址
              </template>
              {{order.receiveAddress}}
            </el-descriptions-item>
          </el-descriptions>
      </el-card>
      <br>
      <el-card class="box-card-dialog" shadow="hover">
        <div slot="header" class="clearfix">
          <span>付款信息</span>
        </div>
        <el-descriptions title="" :column="1" border>
            <el-descriptions-item>
              <template slot="label">
                交易号
              </template>
              {{order.payOrderNo}}
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                支付方式
              </template>
              {{order.payMethod}}
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                订单号
              </template>
               {{order.orderNo}}
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                付款状态
              </template>
                {{order.status == 0 || order.status == 4 ? '未支付' : '已支付'}}
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                付款时间
              </template>
              <span v-if="order.payTime">{{ order.payTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}')  }}</span>
            </el-descriptions-item>
          </el-descriptions>
      </el-card>

      <br>
      <el-card class="box-card-dialog" shadow="hover">
        <div slot="header" class="clearfix">
          <span>订单物流</span>
        </div>
        <el-timeline>
        		<el-timeline-item
        			v-for="(activity, index) in logisticDetail"
        			:key="index"
        			:timestamp="activity.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') ">
        			{{activity.detail}}
        		</el-timeline-item>
        	</el-timeline>
      </el-card>
		</el-dialog>

		<!--发货框-->
		<el-dialog title="发货" :visible.sync="dialogFahuoVisible">
		    <el-form ref="dataForm" :rules="rules" :model="order" label-width="80px" label-position="left" style="width: 400px; margin-left:50px;">
		      <el-form-item label="订单编号" prop="orerNo">
						<el-input v-model="order.orderNo" disabled />
		      </el-form-item>
					<el-form-item label="订单类型" prop="type">
							<el-tag>{{order.type | typeFilter }}</el-tag>
					</el-form-item>
					<el-form-item v-if="order.type == '3'" label="拼团进度" prop="progress">
							<el-progress :width="100" type="circle" :percentage="assemblePercent"></el-progress>
							<img v-for="item in assembleMemberList" :src="item.headImg" class="avatar" style="height:100px;width:100px;border-radius: 50%;">
							<br>
							<span style="color:red;">*只有拼团进度为100%时才能发货</span>
					</el-form-item>
					<el-form-item label="发货方式" prop="logisticType">
							<el-select v-model="order.logisticType" class="filter-item" placeholder="请选择">
							  <el-option v-for="item in logisticTypeOptions" :key="item.key" :label="item.text" :value="item.key" />
							</el-select>
					</el-form-item>
					<template v-if="order.logisticType == '2' ">
						<el-form-item label="选择物流" prop="logisticsId">
							<el-select v-model="order.logisticsId" class="filter-item" placeholder="请选择">
								<el-option v-for="item in logisticsOptions" :key="item.id" :label="item.name" :value="item.id" />
							</el-select>
						</el-form-item>
						<el-form-item label="物流单号" prop="logisticsNo">
							<el-input v-model="order.logisticsNo" placeholder="请输入物流单号" />
						</el-form-item>
				</template>
					</el-form>
		    <div style="text-align:right;">
		      <el-button type="danger" @click="dialogFahuoVisible=false">取消</el-button>
		      <el-button type="primary" @click="fahuoConfirm">确定</el-button>
		    </div>
		</el-dialog>

		<!--修改价格框-->
		<el-dialog title="修改价格" :visible.sync="dialogPriceVisible">
		    <el-form ref="dataForm" :rules="editPriceRules" :model="order" label-width="80px" label-position="left" style="width: 400px; margin-left:50px;">
		      <el-form-item label="订单编号" prop="orerNo">
						<el-input v-model="order.orderNo" disabled />
		      </el-form-item>
					<el-form-item label="订单总价" prop="totalAmount">
						<el-input v-model="order.totalAmount" disabled />
					</el-form-item>
					<el-form-item label="修改价格" prop="editPrice">
						<el-input v-model="order.editPrice" />
					</el-form-item>
			</el-form>
		    <div style="text-align:right;">
		      <el-button type="danger" @click="dialogPriceVisible=false">取消</el-button>
		      <el-button type="primary" @click="editPriceConfirm">确定</el-button>
		    </div>
		</el-dialog>

		<!--价格记录框-->
		<el-dialog title="价格修改记录" :visible.sync="dialogPriceLogVisible">
							<el-timeline>
								<el-timeline-item  v-for="(price, index) in orderPriceDetail" placement="top">
									<el-card>
										<h4>订单原价:¥{{price.oldPrice | moneyFormat}},修改订单价格为:¥{{price.nowPrice | moneyFormat}}</h4>
										<p>{{price.editName}} 提交于 {{price.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</p>
									</el-card>
								</el-timeline-item>
						</el-timeline>
		</el-dialog>

		<!--订单物流框-->
		<el-dialog title="物流详情" :visible.sync="dialogWuliuVisible">
					<el-form ref="dataForm" :model="orderLogistic" label-width="100px" label-position="left" style="width: 400px; margin-left:50px;">
					  <el-form-item label="新增物流信息" prop="logisticContent">
							<el-input style="width:70%" v-model="orderLogistic.logisticContent" />
							<el-button style="margin-left: 10px;" type="primary" size="mini" @click="handleAddLogistic()">添加</el-button>
					  </el-form-item>
					</el-form>
		      <el-timeline>
							<el-timeline-item
								v-for="(activity, index) in logisticDetail"
								:key="index"
								:timestamp="activity.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') ">
								{{activity.detail}}
							</el-timeline-item>
						</el-timeline>
		</el-dialog>

  </div>
</template>

<style>
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 100%;
  }
	.inline_table td,.inline_table th,.inline_table tr,.inline_table thead,.inline_table table,.inline_table th.is-leaf,.inline_table td {
		border: none;
	}
	.inline_table.el-table::before {
		height: 0;
		}
    .box-card {
      position: relative;
    }
    .order-role {
      position: absolute;
      right: 0px;
      top: 0px;
      width: 20px;
      height: 20px;
      background-color: #E65D6E;
      color: #fff;
      border-radius: 5%;
    }
    .box-card-dialog {
      font-size: 13px;
    }
    .box-card-dialog .el-card__header {
      color: #333333;
      background-color: #fafafa;
      border-color: #ddd;
    }
</style>

<script>
import {getOrderList, getFreightList, getOrder, delivery} from '@/api/order'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime, moneyFormat} from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

//0待支付>>1待发货>>2待收货>>3已完成>>4已取消
const statusOptions = [
  { key: 0, text: '待付款' },
  { key: 1, text: '待发货' },
  { key: 2, text: '待收货' },
  { key: 3, text: '已完成' },
  { key: 4, text: '已取消' }
]

const logisticTypeOptions = [
  { key: '1', text: '无需物流' },
  { key: '2', text: '第三方物流' }
]

const statusKeyValue = statusOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

const orderTypeOptions = [
  { key: 1, text: '普通订单' },
  { key: 2, text: '秒杀订单' },
  { key: 3, text: '拼团订单' },
]

// arr to obj ,such as { CN : "China", US : "USA" }
const orderTypeKeyValue = orderTypeOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

export default {
  name: 'orderList',
  components: { Pagination },
  directives: { waves },
	filters: {
	  statusFilter(status) {
	    return statusKeyValue[status]
	  },
    typeFilter(type) {
      return orderTypeKeyValue[type]
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
			order:{},
      orderDetailList:[],
			orderLogistic:{},
			activeName:'first',
      dialogDetailVisible: false,
      dialogFahuoVisible: false,
			dialogWuliuVisible: false,
			dialogPriceVisible: false,
			dialogPriceLogVisible: false,
			multipleSelection: [],
			rules: {
			  logisticType: [{ required: true, message: '请选择发货方式', trigger: 'change' }]
			},
			editPriceRules: {
			  editPrice: [{ required: true, message: '请填写修改价格', trigger: 'blur' }]
			},
			logisticTypeOptions,
			logisticsOptions:[],
			logisticDetail:[],
			orderPriceDetail:[],
			checkAll: true,
			checkedStatus: ['1', '2', '3', '4', '5'],
			isIndeterminate: false,
			statusList: ['1', '2', '3', '4', '5'],
			orderTypeOptions,
			assembleMemberList:[],
			assemblePercent: 0,
      downloadLoading: false
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
			this.listQuery.dateFrom = renderTime(this.listQuery.startTime)
			this.listQuery.dateTo = renderTime(this.listQuery.endTime)
      getOrderList(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.total
				this.listLoading = false
      })
    },
		handleSelectionChange(val) {
			this.multipleSelection = val;
     },
    handleOrderDetail(row){
       this.order = row.order;
       this.orderDetailList = row.orderDetailList;
       getFreightList({orderId:this.order.id}).then((result) => {
       	this.assembleMemberList = result.data.assembleMemberList;
       	this.assemblePercent = Number(result.data.assemblePercent);
       })
       this.dialogDetailVisible = true;
    },
		handleFahuo(row){
			this.order = Object.assign({}, row) // copy obj
			this.dialogFahuoVisible = true
			if(this.order.type == '3'){
				getAssembleMemberList({orderNo:this.order.orderNo}).then((result) => {
					this.logisticDetail = result.data.freightDetailList;
				})
			}
			getLogisticSelector().then((result) => {
				this.logisticsOptions = result.data;
			})
			this.$nextTick(() => {
			  this.$refs['dataForm'].clearValidate()
			})
		},
		fahuoConfirm() {
			this.$refs['dataForm'].validate((valid) => {
			  if (valid) {
			    let formData = {
						id : this.order.id,
						logisticsId: this.order.logisticsId,
						logisticsNo: this.order.logisticsNo,
						logisticType: this.order.logisticType
					}
			    fahuo(formData).then(() => {
						this.getList()
			      this.dialogFahuoVisible = false
			      this.$notify({
			        title: '成功',
			        message: '该订单已发货成功,请及时处理物流信息',
			        type: 'success',
			        duration: 2000
			      })
			    })
			  }
			})
		},
		handleEditPrice(row){
			this.order = Object.assign({}, row) // copy obj
			this.dialogPriceVisible = true
			this.$nextTick(() => {
			  this.$refs['dataForm'].clearValidate()
			})
		},
		editPriceConfirm() {
			this.$refs['dataForm'].validate((valid) => {
			  if (valid) {
			    let formData = {
						id : this.order.id,
						editPrice: this.order.editPrice
					}
			    editPrice(formData).then(() => {
						this.getList()
			      this.dialogPriceVisible = false
			      this.$notify({
			        title: '成功',
			        message: '该订单价格修改成功!',
			        type: 'success',
			        duration: 2000
			      })
			    })
			  }
			})
		},
		viewOrderPriceLog(row){
			orderPriceLog(row.id).then((result) => {
				this.orderPriceDetail = result.data;
				this.dialogPriceLogVisible = true
			})
		},
		viewOrderLogistics(row){
			this.orderLogistic = Object.assign({}, row) // copy obj
			orderLogisticsDetail(row.id).then((result) => {
				this.logisticDetail = result.data;
				this.dialogWuliuVisible = true
			})
		},
		handleAddLogistic(){
			let formData = {
				orderId : this.orderLogistic.id,
				detail : this.orderLogistic.logisticContent
			}
			addOrderLogistic(formData).then((result) => {
					this.logisticDetail = result.data;
					this.orderLogistic.logisticContent = ''
			})
		},
		handleClickTab(tab, event) {
			this.listQuery = {
			  page: 1,
			  pageSize: 20
			}
			this.activeName = tab.name
			if(tab.name == 'first'){
				this.listQuery.status = null
			}else if(tab.name == 'second'){
				this.listQuery.status = 0
			}else if(tab.name == 'third'){
				this.listQuery.status = 1
			}else if(tab.name == 'fourth'){
				this.listQuery.status = 2
			}else if(tab.name == 'five'){
				this.listQuery.status = 3
			}else if(tab.name == 'six'){
				this.listQuery.status = 4
			}
			this.getList()
		},

  }
}
</script>
