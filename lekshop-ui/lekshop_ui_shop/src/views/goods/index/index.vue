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
			<el-button class="filter-item" size="mini" type="primary" icon="el-icon-plus"  @click="$router.push('/goods/publish')">发布宝贝</el-button>
			<el-button class="filter-item" size="mini" type="danger" icon="el-icon-delete" @click="handleDelete">删除</el-button>
      <el-button class="filter-item" type="primary" size="mini" icon="el-icon-upload2" @click="handleShangjia">上架</el-button>
      <el-button class="filter-item" type="info" size="mini" icon="el-icon-download" @click="handleXiajia">下架</el-button>
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
			<el-table-column type="selection" width="45" align="center"></el-table-column>
      <el-table-column label="ID" prop="id" align="center" width="55">
        <template slot-scope="scope">
          <span>{{ scope.row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column label="商品类目" width="100px"  align="center">
        <template slot-scope="scope">
      		<span>{{ scope.row.cateName}}</span>
        </template>
      </el-table-column>
			<el-table-column label="宝贝标题" min-width="200px"  align="center">
			  <template slot-scope="scope">
          <el-tooltip class="item" effect="dark" :content="scope.row.title" placement="top">
              <span class="line1 link-type" @click="$router.push('/goods/edit/'+scope.row.id)">
              <span v-if="scope.row.type != 1" :style="scope.row.type == 2 ? 'color:red;' : 'color:green;' ">[{{ scope.row.type | typeFilter}}]</span>
              {{ scope.row.title }}</span>
          </el-tooltip>
			  </template>
			</el-table-column>
			<el-table-column label="宝贝主图" width="80px"  align="center">
			  <template slot-scope="scope">
          <el-image
              style="height: 30px"
              :src="scope.row.mainImg"
              :preview-src-list="[scope.row.mainImg]">
            </el-image>
			  </template>
			</el-table-column>
      <el-table-column label="宝贝相册" width="165px">
        <template slot-scope="scope">
          <div class="line1">
            <el-image
                v-for="item in scope.row.goodsImg.split('|')"
                style="height: 30px;margin-left: 5px;"
                :src="item"
                :preview-src-list="scope.row.goodsImg.split('|')">
              </el-image>
          </div>
        </template>
      </el-table-column>
			<el-table-column label="店铺内类目" width="100px"  align="center">
			  <template slot-scope="scope">
					 <span>{{ scope.row.shopCateName }}</span>
			  </template>
			</el-table-column>
			<el-table-column label="价格" width="70px"  align="center">
			  <template slot-scope="scope">
					 <span>{{ scope.row.price | moneyFormat}}</span>
			  </template>
			</el-table-column>
      <el-table-column label="点击" width="70px"  align="center">
        <template slot-scope="scope">
      		 <span>{{ scope.row.pointNum}}</span>
        </template>
      </el-table-column>
      <el-table-column label="销量" width="70px"  align="center">
        <template slot-scope="scope">
      		 <span>{{ scope.row.sellNum}}</span>
        </template>
      </el-table-column>
      <el-table-column label="付款人数" width="70px"  align="center">
        <template slot-scope="scope">
      		 <span>{{ scope.row.payNum}}</span>
        </template>
      </el-table-column>
      <el-table-column label="评论" width="70px"  align="center">
        <template slot-scope="scope">
      		 <span>{{ scope.row.commentNum}}</span>
        </template>
      </el-table-column>
      <el-table-column label="好评" width="70px"  align="center">
        <template slot-scope="scope">
      		 <span>{{ scope.row.goodCommentNum}}</span>
        </template>
      </el-table-column>
      <el-table-column label="收藏" width="70px"  align="center">
        <template slot-scope="scope">
      		 <span>{{ scope.row.collectionNum}}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" width="150px" align="center" prop="addTime" >
        <template slot-scope="scope">
          <span>{{ scope.row.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" width="100px"  align="center">
        <template slot-scope="scope">
      		 <el-tag :type="scope.row.status == 1 ? 'success' : 'danger' ">{{ scope.row.status | statusFilter}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.actions')" class-name="small-padding" fixed="right" width="100px">
        <template slot-scope="scope">
          <el-button-group>
            <el-tooltip class="item" effect="dark" content="参数" placement="top">
               <el-button  type="primary" icon="el-icon-date" size="mini" @click="handleViewGoodsQuality(scope.row)"></el-button>
            </el-tooltip>
            <el-tooltip class="item" effect="dark" content="编辑" placement="top">
              <router-link :to="'/goods/edit/'+scope.row.id">
                <el-button type="primary" icon="el-icon-edit" size="mini">
                </el-button>
              </router-link>
            </el-tooltip>
          </el-button-group>

        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />

<!--商品属性框-->
		<el-dialog title="商品参数" :visible.sync="dialogFormVisible">

			<el-form ref="dataForm" :model="goodsQuality" label-position="left">
			  <el-form-item label="添加商品参数" prop="name">
					<el-input style="width:20%" v-model="goodsQuality.name" placeholder="请输入商品参数"/>
					<el-input style="width:30%" v-model="goodsQuality.value" placeholder="请输入参数值"/>
					<el-input-number style="width:20%;" v-model="goodsQuality.sort" :min="1" label="修改排序"></el-input-number>
					<el-button style="margin-left: 10px;" type="primary" size="mini" @click="addGoodsQuality()">添加</el-button>
			  </el-form-item>
			</el-form>

		    <el-table :data="goodsQualityList" border fit highlight-current-row style="width: 100%;">
					<el-table-column label="排序" align="center">
						<template slot-scope="scope">
							<span>{{scope.row.sort}}</span>
						</template>
					</el-table-column>
		    	<el-table-column label="参数名称" align="center">
		    		<template slot-scope="scope">
		    			<span>{{scope.row.name}}</span>
		    		</template>
		    	</el-table-column>
		    	<el-table-column label="参数值"  align="center">
		    		<template slot-scope="scope">
		    			<span>{{scope.row.value}}</span>
		    		</template>
		    	</el-table-column>
		    	<el-table-column label="操作" width="200px" >
		    		<template slot-scope="scope">
		    			<el-button size="mini" type="danger" icon="el-icon-delete" @click="handleDeleteGoodsQuality(scope.row.id)">删除</el-button>
		    		</template>
		    	</el-table-column>
		    </el-table>
		</el-dialog>

  </div>
</template>

<script>
import {getGoodsList, updateGoods, shangjiaGoods, xiajiaGoods, deleteGoods, getGoodsQualityList, addGoodsQuality, deleteGoodsQuality } from '@/api/goods'
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
  name: 'goodsList',
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
      goodsQuality:{},
      goodsQualityList:[],
    }
  },
  created() {
    if(this.$route.path == '/goods/outlist'){
      this.listQuery.status = 2
      this.activeName = 'third'
    }
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      getGoodsList(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.total
				this.listLoading = false
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
    handleShangjia(){
    	const delVals = this.multipleSelection;
    	if(!delVals || delVals.length <= 0){
    		this.$message({
    		  message: '请至少选择一条数据',
    		  type: 'error',
    			duration: 2000
    		})
    		return;
    	}
    	this.$confirm('您确定要上架这些宝贝?', '提醒', {
    	  confirmButtonText: '确定',
    	  cancelButtonText: '取消',
    	  type: 'warning'
    	}).then(async() => {
    		  let values = [];
    			delVals.forEach(val =>{
    				values.push(val.id);
    			})
    	    shangjiaGoods(values.join(',')).then(() => {
    	    	this.getList()
    	      this.$notify({
    	        title: '成功',
    	        message: '批量上架成功',
    	        type: 'success',
    	        duration: 2000
    	      })
    	    })
    	  })
    	  .catch(err => { console.error(err) })
    },
    handleXiajia(){
    	const delVals = this.multipleSelection;
    	if(!delVals || delVals.length <= 0){
    		this.$message({
    		  message: '请至少选择一条数据',
    		  type: 'error',
    			duration: 2000
    		})
    		return;
    	}
    	this.$confirm('您确定要下架这些宝贝?', '提醒', {
    	  confirmButtonText: '确定',
    	  cancelButtonText: '取消',
    	  type: 'warning'
    	}).then(async() => {
    		  let values = [];
    			delVals.forEach(val =>{
    				values.push(val.id);
    			})
    	    xiajiaGoods(values.join(',')).then(() => {
    	    	this.getList()
    	      this.$notify({
    	        title: '成功',
    	        message: '批量下架成功',
    	        type: 'success',
    	        duration: 2000
    	      })
    	    })
    	  })
    	  .catch(err => { console.error(err) })
    },
    getGoodsQualityList(goodsId){
    	getGoodsQualityList(goodsId).then((result) => {
    		this.goodsQualityList = result.data;
    	})
    },
    handleViewGoodsQuality(row) {
      this.dialogFormVisible = true
    	this.tempGoodsId = row.id
    	this.goodsQuality = {}
    	this.getGoodsQualityList(this.tempGoodsId)
    },
    handleDeleteGoodsQuality(id){
    	this.$confirm('您确定要删除这条记录?', '提醒', {
    	  confirmButtonText: '确定',
    	  cancelButtonText: '取消',
    	  type: 'warning'
    	}).then(async() => {
    	    deleteGoodsQuality(id).then(() => {
    				this.getGoodsQualityList(this.tempGoodsId)
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
    addGoodsQuality() {
      let formData = {
    		goodsId: this.tempGoodsId,
    		name: this.goodsQuality.name,
    		value: this.goodsQuality.value,
    		sort: this.goodsQuality.sort
    	}

    	addGoodsQuality(formData).then(() => {
    		this.goodsQuality = {}
    		this.getGoodsQualityList(this.tempGoodsId)
    	  this.$notify({
    	    title: '成功',
    	    message: '商品参数添加成功',
    	    type: 'success',
    	    duration: 2000
    	  })
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
