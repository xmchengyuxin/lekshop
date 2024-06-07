<template>
  <div class="app-container">
		<el-tabs v-model="activeName" @tab-click="handleClickTab">
			  <el-tab-pane label="全部商品" name="first"></el-tab-pane>
				<el-tab-pane label="出售中" name="second"></el-tab-pane>
				<el-tab-pane label="仓库中" name="third"></el-tab-pane>
		</el-tabs>
    <div class="filter-container">
      <el-input v-model="listQuery.title" clearable placeholder="商品名称" style="width: 200px;" class="filter-item" @keyup.enter.native="getList()" />
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" circle @click="getList"></el-button>
      <br>
			<el-button-group>
			<el-button class="filter-item" size="mini" type="primary" icon="el-icon-plus"  @click="$router.push('/point/goods/publish')">发布商品</el-button>
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
      <el-table-column label="商品编号" width="100px"  align="center">
        <template slot-scope="scope">
      		<span>{{ scope.row.goodsCode}}</span>
        </template>
      </el-table-column>
      <el-table-column label="商品类目" width="100px"  align="center">
        <template slot-scope="scope">
      		<span>{{ scope.row.cateName}}</span>
        </template>
      </el-table-column>
			<el-table-column label="商品标题" min-width="200px"  align="center">
			  <template slot-scope="scope">
          <el-tooltip class="item" effect="dark" :content="scope.row.title" placement="top">
            <span class="line1">
              {{ scope.row.title }}</span>
          </el-tooltip>
			  </template>
			</el-table-column>
			<el-table-column label="商品主图" width="80px"  align="center">
			  <template slot-scope="scope">
          <el-image
              style="height: 30px"
              :src="scope.row.mainImg"
              :preview-src-list="[scope.row.mainImg]">
            </el-image>
			  </template>
			</el-table-column>
      <el-table-column label="商品相册" width="165px">
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
			<el-table-column label="商品价值" width="70px"  align="center">
			  <template slot-scope="scope">
					 <span>{{ scope.row.oriPrice | moneyFormat}}</span>
			  </template>
			</el-table-column>
      <el-table-column label="所需现金" width="70px"  align="center">
        <template slot-scope="scope">
      		 <span style="color: red;" >{{ scope.row.price | moneyFormat}}</span>
        </template>
      </el-table-column>
      <el-table-column label="所需积分" width="70px"  align="center">
        <template slot-scope="scope">
      		 <span style="color: red;" >{{ scope.row.point | moneyFormat}}</span>
        </template>
      </el-table-column>
      <el-table-column label="限购" width="70px"  align="center">
        <template slot-scope="scope">
      		 <span>{{ scope.row.limitExchange}}</span>
        </template>
      </el-table-column>
      <el-table-column label="库存" width="70px"  align="center">
        <template slot-scope="scope">
      		 <span style="font-size: 17px; color: rgb(245, 108, 108); font-weight: bold;">{{ scope.row.stock}}</span>
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
            <el-tooltip class="item" effect="dark" content="编辑" placement="top">
              <router-link :to="'/point/goods/edit/'+scope.row.id">
                <el-button type="primary" icon="el-icon-edit" size="mini">
                </el-button>
              </router-link>
            </el-tooltip>
          </el-button-group>

        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />

  </div>
</template>

<script>
import {getGoodsList, updateGoods, shangjiaGoods, xiajiaGoods, deleteGoods } from '@/api/point'
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
  name: 'pointGoodsList',
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
    	this.$confirm('您确定要上架这些商品?', '提醒', {
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
    	this.$confirm('您确定要下架这些商品?', '提醒', {
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
