<template>
  <div class="app-container">
		<el-tabs v-model="activeName" @tab-click="handleClickTab">
			  <el-tab-pane label="全部宝贝" name="first"></el-tab-pane>
				<el-tab-pane label="出售中的宝贝" name="second"></el-tab-pane>
				<el-tab-pane label="仓库中宝贝" name="third"></el-tab-pane>
		</el-tabs>
    <div class="filter-container">
      <el-input v-model="listQuery.title" clearable placeholder="宝贝名称" style="width: 200px;" class="filter-item" @keyup.enter.native="getList()" />
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" circle @click="getList"></el-button>
      <br>
			<el-button-group>
			<el-button class="filter-item" size="mini" type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button>
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
    >
			<el-table-column type="selection" width="55" align="center"></el-table-column>
      <el-table-column label="ID" prop="id" align="center" width="65">
        <template slot-scope="scope">
          <span>{{ scope.row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column label="商品类目" width="100px"  align="center">
        <template slot-scope="scope">
      		<span>{{ scope.row.cateName}}</span>
        </template>
      </el-table-column>
			<el-table-column label="宝贝标题" width="200px"  align="center">
			  <template slot-scope="scope">
			    <el-tag :type="scope.row.type == 1 ? 'success' : 'danger'">{{ scope.row.type | typeFilter}}</el-tag>
          <span>{{ scope.row.title }}</span>
			  </template>
			</el-table-column>
			<el-table-column label="宝贝主图" width="200px"  align="center">
			  <template slot-scope="scope">
          <el-image
              style="height: 30px"
              :src="scope.row.mainImg"
              :preview-src-list="[scope.row.mainImg]">
            </el-image>
			  </template>
			</el-table-column>
      <el-table-column label="宝贝相册" width="200px"  align="center">
        <template slot-scope="scope">
          <el-image
              v-for="item in scope.row.goodsImg.split('|')"
              style="height: 30px"
              :src="item"
              :preview-src-list="scope.row.goodsImg.split('|')">
            </el-image>
        </template>
      </el-table-column>
			<el-table-column label="店铺内类目" width="100px"  align="center">
			  <template slot-scope="scope">
					 <span>{{ scope.row.shopCateName }}</span>
			  </template>
			</el-table-column>
			<el-table-column label="价格" width="100px"  align="center">
			  <template slot-scope="scope">
					 <span>{{ scope.row.price}}</span>
			  </template>
			</el-table-column>
      <el-table-column label="点击" width="100px"  align="center">
        <template slot-scope="scope">
      		 <span>{{ scope.row.pointNum}}</span>
        </template>
      </el-table-column>
      <el-table-column label="销量" width="100px"  align="center">
        <template slot-scope="scope">
      		 <span>{{ scope.row.sellNum}}</span>
        </template>
      </el-table-column>
      <el-table-column label="付款人数" width="100px"  align="center">
        <template slot-scope="scope">
      		 <span>{{ scope.row.payNum}}</span>
        </template>
      </el-table-column>
      <el-table-column label="评论" width="100px"  align="center">
        <template slot-scope="scope">
      		 <span>{{ scope.row.commentNum}}</span>
        </template>
      </el-table-column>
      <el-table-column label="好评" width="100px"  align="center">
        <template slot-scope="scope">
      		 <span>{{ scope.row.goodCommentNum}}</span>
        </template>
      </el-table-column>
      <el-table-column label="收藏" width="100px"  align="center">
        <template slot-scope="scope">
      		 <span>{{ scope.row.collectionNum}}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" width="200px" align="center" prop="addTime" >
        <template slot-scope="scope">
          <span>{{ scope.row.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" width="100px"  align="center">
        <template slot-scope="scope">
      		 <span>{{ scope.row.status}}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.actions')" class-name="small-padding" fixed="right">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />

  </div>
</template>

<script>
import {getGoodsList, updateGoods, shangjiaGoods, xiajiaGoods, deleteGoods} from '@/api/goods'
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

export default {
  name: 'goodsList',
  components: { Pagination },
  directives: { waves },
	filters: {
	  typeFilter(status) {
	    return typeKeyValue[status]
	  },
    useTypeFilter(type) {
	    return useTypeKeyValue[type]
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
        pageSize: 20
      },
      couponConfig: {},
      dialogFormVisible: false,
      dialogType: '',
			multipleSelection: [],
			rules: {
			  name: [{ required: true, message: '优惠券名称不能为空', trigger: 'blur' }],
			  type: [{ required: true, message: '请选择优惠券类型', trigger: 'change' }],
				amount: [{ required: true, message: '优惠力度不能为空', trigger: 'blur' }],
				fullAmount: [{ required: true, message: '最低金额不能为空', trigger: 'blur' }],
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
			activeName: 'second',
      goodsOptions:[],
      shopCateOptions:[]
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
    getList() {
      this.listLoading = true
      getGoodsList(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.total
				this.listLoading = false
      })
    },
		handleCreate() {
		  this.dialogStatus = 'add'
		  this.dialogFormVisible = true
			this.couponConfig = {validityType:1}
      this.getShopCateSelector()
      this.getGoodsSelector()
			this.$nextTick(() => {
			  this.$refs['dataForm'].clearValidate()
			})
		},
		handleUpdate(row) {
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
			    deleteGoods(values.join(',')).then(() => {
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
				this.listQuery.status = 1
			}else if(tab.name == 'third'){
				this.listQuery.status = 2
			}
			this.getList()
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
