<template>
  <div class="app-container">
		<el-tabs v-model="activeName" @tab-click="handleClickTab">
			  <el-tab-pane label="全部" name="first"></el-tab-pane>
				<el-tab-pane label="满减券" name="second"></el-tab-pane>
				<el-tab-pane label="折扣券" name="third"></el-tab-pane>
		</el-tabs>
    <div class="filter-container">
			<el-button-group>
      <!-- <el-button class="filter-item" size="mini" type="primary" icon="el-icon-s-promotion" @click="handlePresent">发券</el-button> -->
      <el-button class="filter-item" size="mini" type="primary" icon="el-icon-s-claim" @click="handleFullSend">满送券</el-button>
			<el-button class="filter-item" size="mini" type="primary" icon="el-icon-plus" @click="handleCreate">添加</el-button>
			<el-button class="filter-item" size="mini" type="danger" icon="el-icon-delete" @click="handleDelete">删除</el-button>
			</el-button-group>
		</div>
		<el-table
      :key="tableKey"
      v-loading="listLoading"
      :data="list"
      border
      fit
      highlight-current-row
      style="width: 100%;"
			@selection-change="handleSelectionChange"
      @row-click="handleUpdate"
    >
			<el-table-column type="selection" width="55" align="center"></el-table-column>
      <el-table-column label="ID" prop="id" align="center" width="65">
        <template slot-scope="scope">
          <span>{{ scope.row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column label="优惠券类型" width="100px"  align="center">
        <template slot-scope="scope">
      		<el-tag :type="scope.row.type == 1 ? 'success' : 'danger'">{{ scope.row.type | typeFilter}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="领取条件" width="130px"  align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.sendCondition == 1">手动领取</span>
          <span v-else-if="scope.row.sendCondition == 2">后台发券</span>
          <span v-else>消费满{{scope.row.fullSendAmount}}元赠送</span>
        </template>
      </el-table-column>
			<el-table-column label="优惠券名称" min-width="200px"  align="center">
			  <template slot-scope="scope">
			    <span>{{ scope.row.name }}</span>
			  </template>
			</el-table-column>
			<el-table-column label="满减条件" width="200px"  align="center">
			  <template slot-scope="scope">
					 <span v-if="scope.row.type == 1">满{{ scope.row.fullAmount }}减{{ scope.row.amount }}</span>
           <span v-if="scope.row.type == 2">满{{ scope.row.fullAmount }}打{{ scope.row.amount }} 折</span>
			  </template>
			</el-table-column>
			<el-table-column label="发放数量" width="100px"  align="center">
			  <template slot-scope="scope">
					 <span>{{ scope.row.totalNum }}</span>
			  </template>
			</el-table-column>
			<el-table-column label="每人限领" width="100px"  align="center">
			  <template slot-scope="scope">
					 <span>{{ scope.row.limitNum }}</span>
			  </template>
			</el-table-column>
      <el-table-column label="可用范围" width="100px"  align="center">
        <template slot-scope="scope">
      		 <span :style="scope.row.useType !=1 ? 'color:red' : '' ">{{ scope.row.useType | useTypeFilter}}</span>
        </template>
      </el-table-column>
			<el-table-column label="有效期" width="200px"  align="center">
			  <template slot-scope="scope">
          <span v-if="scope.row.validityType == 1 ">长期有效</span>
			    <span v-if="scope.row.validityType == 2">领取后当天生效,有效期{{ scope.row.validityDays}}天</span>
					<span v-if="scope.row.validityType == 3 ">
					{{ scope.row.fixedBeginDate | parseTime('{y}-{m}-{d}') }}
					至
					{{ scope.row.fixedEndDate | parseTime('{y}-{m}-{d}') }}</span>
			  </template>
			</el-table-column>
      <el-table-column label="活动时间" width="200px"  align="center">
        <template slot-scope="scope">
      		{{ scope.row.beginDate | parseTime('{y}-{m}-{d}') }}
      		至
      		{{ scope.row.endDate | parseTime('{y}-{m}-{d}') }}
        </template>
      </el-table-column>
      <el-table-column label="创建时间" width="200px" align="center" prop="addTime" >
        <template slot-scope="scope">
          <span>{{ scope.row.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" width="100px"  align="center">
        <template slot-scope="scope">
           <el-tag :type="scope.row.status !=1 ? 'danger' : '' ">{{ scope.row.status | statusFilter}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.actions')" class-name="small-padding" fixed="right" width="120px">
        <template slot-scope="scope">
          <el-button-group>
          <el-tooltip class="item" effect="dark" content="编辑" placement="top">
             <el-button type="primary" size="mini" icon="el-icon-edit" @click.stop="handleUpdate(scope.row)"></el-button>
          </el-tooltip>
          <el-tooltip class="item" effect="dark" content="查看已领人员" placement="top">
             <el-button type="primary" size="mini" icon="el-icon-search" @click.stop="handleViewDraw(scope.row)"></el-button>
          </el-tooltip>
          </el-button-group>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />

		<!--添加编辑框-->
		<el-dialog :title="dialogStatus==='edit'?'编辑':'新增'" :visible.sync="dialogFormVisible">
		    <el-form ref="dataForm" :rules="rules" :model="couponConfig" label-width="100px" label-position="left" style="width: 100%; margin-left:50px;">

          <el-form-item label="选择客户" prop="memberIds" v-if="showSelectCustomer">
            <div>
              <el-button
                @click="bindMemberId()"
                size="mini"
                plain
                type="danger"
                >选择客户</el-button
              >
              <el-tooltip placement="top" effect="light" v-if="memberList && memberList.length > 0">
                <div slot="content">
                  <div
                    v-for="(item, index) in memberList"
                    :key="index"
                  >
                    {{ item.nickname }}  &nbsp;<span class="link-type" @click="deleteMember(index)">删除</span>
                  </div>
                </div>
                <el-button size="mini">查看已选人员</el-button>
              </el-tooltip>
            </div>
          </el-form-item>
          <el-form-item label="赠送条件" prop="fullSendAmount" v-if="couponConfig.sendCondition == 3">
          	<el-input v-model.number="couponConfig.fullSendAmount" style="width: 60%;" placeholder="消费满多少金额才能赠送" />
          </el-form-item>
          <el-form-item label="券类型" prop="type">
          	<el-radio-group v-model="couponConfig.type">
          		<el-radio :label="1" border>满减券</el-radio>
          		<el-radio :label="2" border>折扣券</el-radio>
          	</el-radio-group>
          </el-form-item>
		      <el-form-item label="优惠券名称" prop="name">
		        <el-input v-model="couponConfig.name" placeholder="请输入优惠券名称" style="width: 60%;"/>
		      </el-form-item>
					<el-form-item label="最低消费" prop="fullAmount">
					  <el-input v-model.number="couponConfig.fullAmount" style="width: 60%;" placeholder="请输入满减金额" />
					</el-form-item>
					<el-form-item label="优惠金额" prop="amount" v-if="couponConfig.type == 1">
					  <el-input v-model.number="couponConfig.amount" style="width: 60%;" placeholder="请输入金额" />
					</el-form-item>
          <el-form-item label="优惠折扣" prop="amount"  v-if="couponConfig.type == 2">
            <el-input v-model="couponConfig.amount" style="width: 60%;" placeholder="请输入折扣" /> 折
            <p class="tips">温馨提示：95折直接输入9.5</p>
          </el-form-item>
					<el-form-item label="发放数量" prop="totalNum" v-if="!showSelectCustomer">
					  <el-input v-model.number="couponConfig.totalNum" style="width: 60%;" placeholder="请输入发放数量" />
					</el-form-item>
					<el-form-item label="每人限领" prop="limitNum" v-if="!showSelectCustomer">
					  <el-input v-model.number="couponConfig.limitNum"style="width: 60%;" placeholder="请输入每人限领" />
					</el-form-item>
					<el-form-item label="有效期类型" prop="validityType">
						<el-radio-group v-model="couponConfig.validityType" >
              <el-radio :label="1" border>长期有效</el-radio>
							<el-radio :label="2" border>领取后到期天数</el-radio>
							<el-radio :label="3" border>固定到期时间</el-radio>
						</el-radio-group>
					</el-form-item>
					<el-form-item v-if="couponConfig.validityType == '2' " label="有效期(天)" prop="validityDay">
					  <el-input v-model.number="couponConfig.validityDays" placeholder="领取后当天生效" style="width: 60%;" />
					</el-form-item>
					<el-form-item v-if="couponConfig.validityType == '3' " prop="validityDateRange" label="固定到期时间">
						<el-date-picker
              style="width: 60%;"
							v-model="couponConfig.validityDateRange"
							type="daterange"
							range-separator="至"
							start-placeholder="开始日期"
							end-placeholder="结束日期">
						</el-date-picker>
					</el-form-item>
          <el-form-item prop="activityDateRange" label="活动时间">
          	<el-date-picker
              style="width: 60%;"
          		v-model="couponConfig.activityDateRange"
          		type="daterange"
          		range-separator="至"
          		start-placeholder="开始日期"
          		end-placeholder="结束日期">
          	</el-date-picker>
            <p class="tips">温馨提示：商家可以预先发布优惠券，等到活动时间自动生效</p>
          </el-form-item>
          <el-form-item label="可用范围" prop="useType">
          	<el-radio-group v-model="couponConfig.useType" >
              <el-radio :label="1" border>全部商品</el-radio>
          		<el-radio :label="2" border>指定商品</el-radio>
          		<el-radio :label="3" border>指定分类</el-radio>
          	</el-radio-group>
          </el-form-item>
          <el-form-item label="指定分类" prop="ptid" v-if="couponConfig.useType == 3">
          <!-- <el-cascader-panel v-model="couponConfig.ptid" :options="shopCateOptions" :props="props" style="width: 60%;"></el-cascader-panel> -->
            <el-select
                v-model="couponConfig.ptid"
                multiple
                collapse-tags
                filterable
                style="width: 60%;"
                placeholder="请选择">
                <el-option
                  v-for="item in shopCateOptions"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id +''">
                </el-option>
              </el-select>
          </el-form-item>
          <el-form-item label="指定商品" prop="goodsId" v-if="couponConfig.useType == 2">
            <el-select
                v-model="couponConfig.goodsId"
                multiple
                collapse-tags
                filterable
                style="width: 60%;"
                placeholder="请选择">
                <el-option
                  v-for="item in goodsOptions"
                  :key="item.id"
                  :label="item.title"
                  :value="item.id +''">
                </el-option>
              </el-select>
          </el-form-item>
          <el-form-item label="使用规则" prop="rules">
            <el-input type="textarea" v-model="couponConfig.rules"style="width: 60%;" placeholder="请输入使用规则" />
          </el-form-item>
		    </el-form>
		    <div style="text-align:right;">
		      <el-button type="danger" @click="dialogFormVisible=false">取消</el-button>
		      <el-button type="primary" @click="confirm">确定</el-button>
		    </div>
		</el-dialog>

    <el-dialog title="选择用户"
        :visible.sync="showMember"
        >
        <MemberDialog
          ref="memberDialog"
          @selected="selectedMemberData"
          @close="closeMemberDialog"
        ></MemberDialog>
    </el-dialog>

    <el-dialog title="查看已领人员" :visible.sync="dialogDrawVisible">
      <el-table :data="drawList" border fit highlight-current-row style="width: 100%;"
      >
        <el-table-column label="用户名称"  align="center" prop="code" >
        	  <template slot-scope="scope">
                <span class="link-type">{{ scope.row.memberName }}</span>
        	  </template>
        	</el-table-column>
        	<el-table-column label="优惠券" align="center" prop="level">
        	  <template slot-scope="scope">
        	    <span>{{scope.row.name}}</span>
        	  </template>
        	</el-table-column>
          <el-table-column label="状态" align="center">
            <template slot-scope="scope">
              <el-tag :type="scope.row.status != 0 ? 'danger' : ''">{{ scope.row.status | couponStatusFilter}}</el-tag>
            </template>
          </el-table-column>
          <el-table-column label="领取时间"  align="center" prop="addTime" >
            <template slot-scope="scope">
              <span>{{ scope.row.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
            </template>
          </el-table-column>
      </el-table>
      <pagination v-show="drawTotal>0" :total="drawTotal" :page.sync="drawListQuery.page" :limit.sync="drawListQuery.pageSize" @pagination="getDrawList()" />
    </el-dialog>

  </div>
</template>

<script>
import {getShopCouponList, updateCoupon, deleteShopCoupon, getShopCateList, getAllShopCate, getCouponDrawList, presentCoupon} from '@/api/shop'
import {getGoodsList} from '@/api/goods'
import MemberDialog from '@/views/lek-dialog/member/index'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime } from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

const typeOptions = [
  { key: 1, text: '满减券' },
  { key: 2, text: '折扣券' },
]

// arr to obj ,such as { CN : "China", US : "USA" }
const typeKeyValue = typeOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

const useTypeOptions = [
  { key: 1, text: '全部商品' },
  { key: 2, text: '指定商品' },
  { key: 3, text: '指定分类' },
]

// arr to obj ,such as { CN : "China", US : "USA" }
const useTypeKeyValue = useTypeOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

const statusOptions = [
  { key: 1, text: '发放中' },
  { key: 2, text: '已领完' },
  { key: 3, text: '已过期' },
]

// arr to obj ,such as { CN : "China", US : "USA" }
const statusKeyValue = statusOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

const couponStatusOptions = [
  { key: 0, text: '未使用' },
  { key: 1, text: '已使用' },
  { key: 2, text: '已过期' },
]

// arr to obj ,such as { CN : "China", US : "USA" }
const couponStatusKeyValue = couponStatusOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

export default {
  name: 'coupon',
  components: { Pagination, MemberDialog },
  directives: { waves },
	filters: {
	  typeFilter(status) {
	    return typeKeyValue[status]
	  },
    useTypeFilter(type) {
	    return useTypeKeyValue[type]
	  },
    statusFilter(status){
      return statusKeyValue[status]
    },
    couponStatusFilter(status){
      return couponStatusKeyValue[status]
    }
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
        pageSize: 20
      },
      couponConfig: {},
      dialogFormVisible: false,
      dialogDrawVisible: false,
      dialogStatus: '',
			multipleSelection: [],
			rules: {
			  name: [{ required: true, message: '优惠券名称不能为空', trigger: 'blur' }],
			  type: [{ required: true, message: '请选择优惠券类型', trigger: 'change' }],
				amount: [{ required: true, message: '优惠力度不能为空', trigger: 'blur' }],
				fullAmount: [{ required: true, message: '最低金额不能为空', trigger: 'blur' }],
				fullSendAmount: [{ required: true, message: '满送金额不能为空', trigger: 'blur' }],
				validityType: [{ required: true, message: '有效期类型不能为空', trigger: 'blur' }],
				totalNum: [{ required: true, message: '发放数量不能为空', trigger: 'blur' }],
				limitNum: [{ required: true, message: '每人限领不能为空', trigger: 'blur' }],
				validityDays: [{ required: true, message: '有效期不能为空', trigger: 'blur' }],
				validityDateRange: [{ required: true, message: '有效周期不能为空', trigger: 'blur' }],
        activityDateRange: [{ required: true, message: '活动周期不能为空', trigger: 'blur' }],
        rules: [{ required: true, message: '使用规则不能为空', trigger: 'blur' }],
        useType: [{ required: true, message: '请选择可用范围', trigger: 'blur' }],
        goodsId: [{ required: true, message: '请选择指定商品', trigger: 'blur' }],
        ptid: [{ required: true, message: '请选择指定分类', trigger: 'change' }],

			},
			typeOptions,
			activeName: 'first',
      goodsOptions:[],
      shopCateOptions:[],
      showSelectCustomer: false,
      memberList: [],
      showMember: false,
      drawList: [],
      drawTotal: 0,
      drawListLoading: true,
      drawListQuery: {
        page: 1,
        pageSize: 20,
      },
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getShopCateSelector(){
    	getAllShopCate().then(response => {
    		this.shopCateOptions = response.data
    	})
    },
    getGoodsSelector(){
    	getGoodsList({page:1, pageSize: 999}).then(response => {
    		this.goodsOptions = response.data.list
    	})
    },
    getList() {
      this.listLoading = true
      getShopCouponList(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.total
				this.listLoading = false
      })
    },
		handleCreate() {
      this.showSelectCustomer = false;
		  this.dialogStatus = 'add'
		  this.dialogFormVisible = true
			this.couponConfig = {validityType:1, sendCondition: 1}
      this.getShopCateSelector()
      this.getGoodsSelector()
			this.$nextTick(() => {
			  this.$refs['dataForm'].clearValidate()
			})
		},
    handlePresent() {
      this.showSelectCustomer = true;
      this.dialogStatus = 'add'
      this.dialogFormVisible = true
    	this.couponConfig = {validityType:1, sendCondition: 2}
      this.getShopCateSelector()
      this.getGoodsSelector()
    	this.$nextTick(() => {
    	  this.$refs['dataForm'].clearValidate()
    	})
    },
    handleFullSend(){
      this.showSelectCustomer = false;
      this.dialogStatus = 'add'
      this.dialogFormVisible = true
    	this.couponConfig = {validityType:1, sendCondition: 3}
      this.getShopCateSelector()
      this.getGoodsSelector()
    	this.$nextTick(() => {
    	  this.$refs['dataForm'].clearValidate()
    	})
    },
		handleUpdate(row) {
      this.showSelectCustomer = false;
		  this.couponConfig = Object.assign({}, row) // copy obj
			this.couponConfig.validityDateRange = [this.couponConfig.fixedBeginDate, this.couponConfig.fixedEndDate]
      this.couponConfig.activityDateRange = [this.couponConfig.beginDate, this.couponConfig.endDate]
      if(this.couponConfig.useGoodsIds){
        this.$set(this.couponConfig, 'goodsId', this.couponConfig.useGoodsIds.split(","))
      }
      if(this.couponConfig.useGoodsCateIds){
        this.$set(this.couponConfig, 'ptid', this.couponConfig.useGoodsCateIds.split(","))
      }
		  this.dialogStatus = 'edit'
		  this.dialogFormVisible = true
      this.getShopCateSelector()
      this.getGoodsSelector()
		  this.$nextTick(() => {
		    this.$refs['dataForm'].clearValidate()
		  })
		},
		confirm() {
			this.$refs['dataForm'].validate((valid) => {
			  if (valid) {
			    let formData =Object.assign({}, this.couponConfig)
					formData.addTime = null
					formData.updTime = null
					if(formData.validityDateRange){
						formData.fixedBeginDate = renderTime(formData.validityDateRange[0])
						formData.fixedEndDate = renderTime(formData.validityDateRange[1])
					}
          if(formData.activityDateRange){
          	formData.beginDate = renderTime(formData.activityDateRange[0])
          	formData.endDate = renderTime(formData.activityDateRange[1])
          }
          if(formData.goodsId){
            formData.useGoodsIds = formData.goodsId.join(",")
          }
          if(formData.ptid){
            console.log(formData.ptid)
            formData.useGoodsCateIds = formData.ptid.join(",")
          }
          if(this.showSelectCustomer) {
            if(this.memberList && this.memberList.length > 0) {
              let memberIds = []
              this.memberList.forEach(item=>{
                memberIds.push(item.id);
              })
              formData.memberIds = memberIds.join(",")
            }
            presentCoupon(formData).then(() => {
            	this.getList()
              this.dialogFormVisible = false
              this.$notify({
                title: '成功',
                message: '成功发券'+this.memberList.length+"张",
                type: 'success',
                duration: 2000
              })
            })
          }else {
            updateCoupon(formData).then(() => {
            	this.getList()
              this.dialogFormVisible = false
              this.$notify({
                title: '成功',
                message: '优惠券配置保存成功',
                type: 'success',
                duration: 2000
              })
            })
          }

			  }
			})
		},
		handleSelectionChange(val) {
			this.multipleSelection = val;
     },
		handleDelete(){
			const delVals = this.multipleSelection;
			if(!delVals || delVals.length <= 0){
				this.$message({
				  message: '请至少选择一条数据',
				  type: 'error',
					duration: 2000
				})
				return;
			}
			this.$confirm('您确定要删除这些记录?', '提醒', {
			  confirmButtonText: '确定',
			  cancelButtonText: '取消',
			  type: 'warning'
			}).then(async() => {
				  let values = [];
					delVals.forEach(val =>{
						values.push(val.id);
					})
			    deleteShopCoupon(values.join(',')).then(() => {
			    	this.getList()
			      this.$notify({
			        title: '成功',
			        message: '删除成功',
			        type: 'success',
			        duration: 2000
			      })
			    })
			  })
			  .catch(err => { console.error(err) })
		},
		handleClickTab(tab, event) {
			this.listQuery = {
			  page: 1,
			  pageSize: 20
			}
			this.activeName = tab.name
			if(tab.name == 'first'){
			}else if(tab.name == 'second'){
				this.listQuery.type = 1
			}else if(tab.name == 'third'){
				this.listQuery.type = 2
			}
			this.getList()
		},
    // 绑定客户
    bindMemberId() {
      this.showMember = true;
    },
    // 回调的用户信息
    selectedMemberData(val) {
      if (!val) return false;
      let data = val.map((item) => {
        return {
          id: item.id,
          nickname: item.nickname,
        };
      });
      this.memberList = data;
    },
    closeMemberDialog(){
      this.showMember = false;
    },
    deleteMember(index) {
      this.memberList.splice(index, 1)
    },
    handleViewDraw(row){
      this.drawListQuery.couponConfigId = row.id
      this.drawList = []
      this.getDrawList()
      this.dialogDrawVisible = true;
    },
    getDrawList() {
      this.drawListLoading = true
      getCouponDrawList(this.drawListQuery).then(response => {
        this.drawList = response.data.list
        this.drawTotal = response.data.total
        this.drawListLoading = false
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
