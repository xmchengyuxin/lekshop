<template>
  <div class="app-container">
   <!-- <el-tabs v-model="locationActiveName" @tab-click="handleClickLocationTab">
    	  <el-tab-pane label="首页" name="first"></el-tab-pane>
    		<el-tab-pane label="菜单" name="second"></el-tab-pane>
        <el-tab-pane label="购物车" name="third"></el-tab-pane>
    </el-tabs> -->
      <div class="filter-container">
        <!-- <el-select v-if="activeName=='first'" v-model="listQuery.brandId" placeholder="选择品牌" filterable clearable class="filter-item">
          <el-option v-for="item in brandOptions" :key="item.id" :label="item.name" :value="item.id">
          </el-option>
        </el-select> -->
       <!-- <el-select v-model="listQuery.location" placeholder="选择位置" filterable clearable class="filter-item">
          <el-option v-for="item in typeOptions" :key="item.key" :label="item.text" :value="item.key">
          </el-option>
        </el-select> -->
        <!-- <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" circle @click="getList"></el-button>
        <br> -->
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
      <!-- <el-table-column label="所属品牌" width="150px"  align="center" v-if="activeName=='first'">
        <template slot-scope="scope">
          <el-tag>{{ scope.row.brandName}}</el-tag>
        </template>
      </el-table-column> -->
      <el-table-column label="轮播图位置" width="150px"  align="center">
        <template slot-scope="scope">
          <el-tag>{{ scope.row.location | typeFilter}}</el-tag>
        </template>
      </el-table-column>
			<el-table-column label="图片" width="300px"  align="center">
			  <template slot-scope="scope">
          <el-image
              style="height: 100px"
              :src="scope.row.img"
              :preview-src-list="[scope.row.img]">
            </el-image>
			  </template>
			</el-table-column>
			<el-table-column label="是否显示" width="150px"  align="center">
			  <template slot-scope="scope">
			    <el-tag :type="scope.row.status == '1' ? 'success' : 'danger' ">{{ scope.row.status | statusFilter}}</el-tag>
			  </template>
			</el-table-column>
      <el-table-column label="创建时间" width="170px" align="center" prop="addTime" >
        <template slot-scope="scope">
          <span>{{ scope.row.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
			<el-table-column label="排序" width="150px"  align="center">
			  <template slot-scope="scope">
			  <el-input-number size="mini" style="width:100px;" v-model="scope.row.sort" @change="sortChange(scope.row)" :min="1" label="修改排序"></el-input-number>
			  </template>
			</el-table-column>
      <el-table-column :label="$t('table.actions')" class-name="small-padding" fixed="right" min-width="120">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" icon="el-icon-edit" @click="handleUpdate(scope.row)">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />

		<!--添加编辑框-->
		<el-dialog :title="dialogType==='edit'?'编辑':'新增'" :visible.sync="dialogFormVisible">
		    <el-form ref="dataForm" :rules="rules" :model="banner" label-width="80px" label-position="right" style="width: 400px; margin-left:50px;">
		      <el-form-item label="是否显示" prop="status">
		        <el-select v-model="banner.status" class="filter-item" placeholder="请选择">
		          <el-option v-for="item in statusOptions" :key="item.key" :label="item.text" :value="item.key" />
		        </el-select>
		      </el-form-item>
          <el-form-item label="轮播位置" prop="location">
            <el-select v-model="banner.location" class="filter-item" placeholder="请选择">
              <el-option v-for="item in typeOptions" :key="item.key" :label="item.text" :value="item.key" />
            </el-select>
          </el-form-item>
          <!-- <el-form-item v-if="activeName=='first'" label="选择品牌" prop="brandId">
            <el-select v-model="banner.brandId" placeholder="品牌" filterable clearable class="filter-item">
              <el-option v-for="item in brandOptions" :key="item.id" :label="item.name" :value="item.id">
              </el-option>
            </el-select>
           </el-form-item> -->
					<el-form-item label="排序" prop="sort">
						<el-input-number style="width:200px;" v-model="banner.sort" :min="1" label="修改排序"></el-input-number>
		      </el-form-item>
					<el-form-item label="跳转链接" prop="url">
            <el-select
                style="width:300px;"
                v-model="banner.url"
                filterable
                allow-create
                default-first-option
                placeholder="请选择跳转模块或输入跳转链接">
                <el-option
                  v-for="item in urlOptions"
                  :key="item.key"
                  :label="item.text"
                  :value="item.key">
                </el-option>
              </el-select>
					  <!-- <el-input v-model="banner.url" placeholder="请输入跳转链接" /> -->
					</el-form-item>
          <!-- <el-form-item v-if="banner.url == 'shop' " prop="shopId" label="选择门店">
            <el-select style="width: 150%;" filterable v-model="banner.shopId" placeholder="请选择">
              <el-option v-for="item in shopOptions" :key="item.id" :label="item.name" :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item v-if="banner.url == 'buyFood' " label="选择商品" prop="foodId">
            <el-select
                style="width:300px;"
                v-model="banner.foodId"
                filterable
                placeholder="请选择商品">
                <el-option
                  v-for="item in foodsOptions"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
                </el-option>
              </el-select>
             <el-input v-model="banner.url" placeholder="请输入跳转链接" />
          </el-form-item> -->
					<el-form-item label="图片" prop="img">
					  <Upload v-model="banner.img" />
            <ImageSource :mul="false" v-model="banner.img" />
            <p style="color:#ff4949;font-size:12px; ">图片建议大小为710px*380px</p>
					</el-form-item>
		    </el-form>
		    <div style="text-align:right;">
		      <el-button type="danger" @click="dialogFormVisible=false">取消</el-button>
		      <el-button type="primary" @click="confirm">确定</el-button>
		    </div>
		</el-dialog>

  </div>
</template>

<script>
import {getBannerList, updateBanner, deleteBanner} from '@/api/banner'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime } from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
import Upload from '@/components/Upload/singleImage'
import ImageSource from '@/components/Upload/imageSource'


const statusOptions = [
  { key: '1', text: '显示' },
  { key: '0', text: '隐藏' }
]

// arr to obj ,such as { CN : "China", US : "USA" }
const statusKeyValue = statusOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

const typeOptions = [
  { key: '1', text: '首页' },
]

// arr to obj ,such as { CN : "China", US : "USA" }
const typeKeyValue = typeOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

const urlOptions = [
  /* { key: 'buyVoucher', text: '购买早餐券' },
  { key: 'sendVoucher', text: '送Ta早餐券' },
  { key: 'buyGiftCard', text: '购买礼品卡' },
  { key: 'inviteFriend', text: '邀请好友' },
  { key: 'shop', text: '店铺' },
  { key: 'buyFood', text: '购买商品' }, 
  { key: 'foodMenu', text: '菜单' } */
]

export default {
  name: 'bannerTable',
  components: { Pagination, Upload, ImageSource },
  directives: { waves },
	filters: {
	  statusFilter(status) {
	    return statusKeyValue[status]
	  },
    typeFilter(status) {
      return typeKeyValue[status]
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
        pageSize: 20,
				cate: '7',
        location: '1'
      },
      banner: {},
      dialogFormVisible: false,
      dialogType: '',
			multipleSelection: [],
			rules: {
			  status: [{ required: true, message: '请选择是否显示状态', trigger: 'change' }],
			  sort: [{ required: true, message: '排序不能为空', trigger: 'blur' }],
				img: [{ required: true, message: '请上传图片', trigger: 'blur' }],
        brandId: [{ required: true, message: '请选择品牌', trigger: 'change' }],
        location: [{ required: true, message: '请选择轮播位置', trigger: 'change' }],
        url: [{ required: true, message: '请选择跳转链接', trigger: 'change' }],
        foodId: [{ required: true, message: '请选择商品', trigger: 'change' }],
        shopId: [{ required: true, message: '请选择店铺', trigger: 'change' }],
			},
			statusOptions,
			activeName:'first',
      locationActiveName:'first',
      shopOptions:[],
      foodsOptions:[],
      typeOptions,
      urlOptions
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      getBannerList(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.total
				this.listLoading = false
      })
    },
    getShopSelector(){
    	getShopSelector().then(response => {
    	  this.shopOptions = response.data
    	})
    },
    getFoodSelectorByBrandId(brandId) {
      this.foodsOptions = []
      let type = this.activeName == 'first' ? 7 : 5;
      getFoodSelectorByBrandId({
        brandId: brandId,
        type: type
      }).then(response => {
        this.foodsOptions = response.data
      })
    },
		handleCreate() {
		  this.dialogStatus = 'add'
		  this.dialogFormVisible = true
			this.banner = {url:'#'}
      this.foodsOptions = []
			this.$nextTick(() => {
			  this.$refs['dataForm'].clearValidate()
			})
		},
		handleUpdate(row) {
		  this.banner = Object.assign({}, row) // copy obj
      if(this.banner.url && this.banner.url.indexOf('buyFood') >= 0 ){
        let url = this.banner.url
        this.banner.url = url.split('/')[0]
        this.banner.foodId = parseInt(url.split('/')[1])
      }
      if(this.banner.url && this.banner.url.indexOf('shop') >= 0 ){
        let url = this.banner.url
        this.banner.url = url.split('/')[0]
        this.banner.shopId = parseInt(url.split('/')[1])
      }
		  this.dialogStatus = 'edit'
		  this.dialogFormVisible = true
		  this.$nextTick(() => {
		    this.$refs['dataForm'].clearValidate()
		  })
		},
		confirm() {
			this.$refs['dataForm'].validate((valid) => {
			  if (valid) {
			    let formData =Object.assign({}, this.banner)
          if(this.banner.url && this.banner.url.indexOf('buyFood') >= 0){
            formData.url = this.banner.url+'/'+this.banner.foodId
          }
          if(this.banner.url && this.banner.url.indexOf('shop') >= 0){
            formData.url = this.banner.url+'/'+this.banner.shopId
          }
					formData.addTime = null
					formData.updTime = null
			    updateBanner(formData).then(() => {
						this.getList()
			      this.dialogFormVisible = false
			      this.$notify({
			        title: '成功',
			        message: '轮播图保存成功',
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
			    deleteBanner(values.join(',')).then(() => {
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
		sortChange(row) {
		  let formData = {
				sort: row.sort,
				id: row.id
			}
			updateBanner(formData).then(() => {
			  this.$notify({
			    title: '成功',
			    message: '显示顺序修改成功',
			    type: 'success',
			    duration: 2000
			  })
				this.getList()
			})
		},
		filterTag(value, row) {
			this.listQuery.status = value
			this.getList()
		},
		handleClickTab(tab, event) {
			this.listQuery.page = 1
			this.listQuery.pageSize = 20
			this.activeName = tab.name
			if(tab.name == 'first'){
				this.listQuery.cate = '7'
			}else if(tab.name == 'second'){
				this.listQuery.cate = '5'
			}
			this.getList()
		},
    handleClickLocationTab(tab, event) {
    	this.listQuery.page = 1
    	this.listQuery.pageSize = 20
    	this.locationActiveName = tab.name
    	if(tab.name == 'first'){
    		this.listQuery.location = '1'
    	}else if(tab.name == 'second'){
    		this.listQuery.location = '2'
    	}else if(tab.name == 'third'){
    		this.listQuery.location = '3'
    	}
    	this.getList()
    },

  }
}
</script>
