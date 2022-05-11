<template>
  <div class="app-container">
   <el-tabs v-model="locationActiveName" @tab-click="handleClickLocationTab">
    	  <el-tab-pane label="全部" name="first"></el-tab-pane>
    		<el-tab-pane label="个人" name="second"></el-tab-pane>
        <el-tab-pane label="企业" name="third"></el-tab-pane>
        <el-tab-pane label="旗舰" name="four"></el-tab-pane>
    </el-tabs>
      <div class="filter-container">
        <el-input v-model="listQuery.name" clearable placeholder="店铺名称" style="width: 200px;" class="filter-item" @keyup.enter.native="getList" />
        <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" circle @click="getList"></el-button>
        <br>
			<el-button-group>
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
      <el-table-column label="店铺LOGO" align="center">
        <template slot-scope="scope">
          <el-image
              style="height: 30px"
              :src="scope.row.logo"
              :preview-src-list="[scope.row.logo]">
            </el-image>
        </template>
      </el-table-column>
			<el-table-column label="店铺类型" align="center">
			  <template slot-scope="scope">
           <el-tag>{{ scope.row.type | typeFilter}}</el-tag>
			  </template>
			</el-table-column>
			<el-table-column label="店铺名称"  align="center">
			  <template slot-scope="scope">
			    <span>{{ scope.row.name}}</span>
			  </template>
			</el-table-column>
      <el-table-column label="自营店"  align="center">
        <template slot-scope="scope">
          <el-switch :active-value="1" :inactive-value="0" v-model="scope.row.selfStatus" @change="selfStatusChange(scope.row)" ></el-switch>
        </template>
      </el-table-column>
      <el-table-column label="所属用户"  align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.memberName}}</span>
        </template>
      </el-table-column>
      <el-table-column label="省市"  align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.province}}{{ scope.row.city}}{{ scope.row.area}}</span>
        </template>
      </el-table-column>
      <el-table-column label="宝贝描述"  align="center" width="70">
        <template slot-scope="scope">
          <span>{{ scope.row.goodsStar}}</span>
        </template>
      </el-table-column>
      <el-table-column label="卖家服务"  align="center" width="70">
        <template slot-scope="scope">
          <span>{{ scope.row.sellerStar}}</span>
        </template>
      </el-table-column>
      <el-table-column label="物流服务"  align="center" width="70">
        <template slot-scope="scope">
          <span>{{ scope.row.logisticStar}}</span>
        </template>
      </el-table-column>
      <el-table-column label="收藏/喜欢"  align="center" width="80">
        <template slot-scope="scope">
          <span>{{ scope.row.likeNum}}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="addTime" >
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
		<el-dialog title="编辑" :visible.sync="dialogFormVisible">
		    <el-form ref="dataForm" :model="shop" label-width="100px" label-position="right" style="width: 100%; margin-left: 50px;">
          <el-form-item label="所属会员" prop="memberName" :rules="[{ required: true, message: '请填写所属会员', trigger: 'blur' }]">
              <el-input disabled v-model="shop.memberName" style="width: 60%"></el-input>
          </el-form-item>
          <el-form-item label="店铺名称" prop="name" :rules="[{ required: true, message: '请填写店铺名称', trigger: 'blur' }]">
              <el-input v-model="shop.name" style="width: 60%"></el-input>
          </el-form-item>
          <el-form-item label="关键字" prop="keyword" :rules="[{ required: true, message: '请填写关键字', trigger: 'blur' }]">
              <el-input v-model="shop.keyword" style="width: 60%"></el-input>
          </el-form-item>
          <el-form-item label="描述" prop="produce" :rules="[{ required: true, message: '请填写描述', trigger: 'blur' }]">
              <el-input type="textarea" v-model="shop.produce" style="width: 60%"></el-input>
          </el-form-item>
          <el-form-item label="服务(多选)" prop="checkedService">
            <el-select v-model="checkedService" multiple clearable placeholder="请选择" class="filter-item" style="width: 60%">
                <el-option
                  v-for="item in serviceOptions"
                  :key="item.id +''"
                  :label="item.name"
                  :value="item.id +''">
                </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="店铺LOGO" prop="logo" :rules="[{ required: true, message: '请上传LOGO', trigger: 'blur' }]">
            <Upload v-model="shop.logo" />
            <ImageSource :mul="false" v-model="shop.logo" />
          </el-form-item>
          <el-form-item label="店铺类型" prop="type" :rules="[{ required: true, message: '请选择店铺类型', trigger: 'blur' }]">
            <el-select v-model="shop.type" class="filter-item" placeholder="请选择" style="width: 60%">
              <el-option v-for="item in typeOptions" :key="item.key" :label="item.text" :value="item.key" />
            </el-select>
          </el-form-item>
          <el-form-item label="店铺等级" prop="level" :rules="[{ required: true, message: '请填写店铺等级', trigger: 'blur' }]">
              <el-input v-model="shop.level" style="width: 60%"></el-input>
          </el-form-item>
          <el-form-item label="店铺简介" prop="remark" :rules="[{ required: true, message: '请填写店铺简介', trigger: 'blur' }]">
              <el-input type="textarea" v-model="shop.remark" style="width: 60%"></el-input>
          </el-form-item>
          <el-form-item label="省市" prop="pca" :rules="[{ required: true, message: '请选择省市', trigger: 'blur' }]">
            <el-cascader
              v-model="shop.pca"
              size="small"
              style="width:60%;"
              :options="addressOptions"
              clearable
            />
          </el-form-item>
          <el-form-item label="收藏/喜欢" prop="likeNum" :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
              <el-input v-model="shop.likeNum" style="width: 60%"></el-input>
          </el-form-item>
          <el-form-item label="宝贝描述" prop="goodsStar" :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
              <el-input v-model="shop.goodsStar" style="width: 60%"></el-input>
          </el-form-item>
          <el-form-item label="卖家服务" prop="sellerStar" :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
              <el-input v-model="shop.sellerStar" style="width: 60%"></el-input>
          </el-form-item>
          <el-form-item label="物流服务" prop="logisticStar" :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
              <el-input v-model="shop.logisticStar" style="width: 60%"></el-input>
          </el-form-item>
          <el-form-item label="权重" prop="weight" :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
              <el-input v-model="shop.weight" style="width: 60%"></el-input>
          </el-form-item>
          <el-form-item label="排序" prop="sort" :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
          	<el-input-number style="width:200px;" v-model="shop.sort" :min="1" label="修改排序"></el-input-number>
          </el-form-item>
		      <el-form-item label="状态" prop="status" :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
		        <el-select v-model="shop.status" class="filter-item" placeholder="请选择" style="width: 60%">
		          <el-option v-for="item in statusOptions" :key="item.key" :label="item.text" :value="item.key" />
		        </el-select>
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
import {getShopList, updateShop, deleteShop, getShopServiceSelector} from '@/api/shop'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime } from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
import Upload from '@/components/Upload/singleImage'
import ImageSource from '@/components/Upload/imageSource'
import {
  provinceAndCityDataPlus, regionData, CodeToText
} from 'element-china-area-data'


const statusOptions = [
  { key: 1, text: '显示' },
  { key: 0, text: '隐藏' }
]

// arr to obj ,such as { CN : "China", US : "USA" }
const statusKeyValue = statusOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

const typeOptions = [
  { key: 1, text: '个人' },
  { key: 2, text: '企业' },
  { key: 3, text: '旗舰' },
]

// arr to obj ,such as { CN : "China", US : "USA" }
const typeKeyValue = typeOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

export default {
  name: 'shopList',
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
      addressOptions: regionData,
      tableKey: 0,
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        pageSize: 20,
      },
      shop: {},
      dialogFormVisible: false,
      dialogType: '',
			multipleSelection: [],
			statusOptions,
			activeName:'first',
      locationActiveName:'first',
      typeOptions,
      checkedService:[],
      serviceOptions:null
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      getShopList(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.total
				this.listLoading = false
      })
    },
		handleCreate() {
		  this.dialogStatus = 'add'
		  this.dialogFormVisible = true
			this.shop = {}
      this.checkedService = []
      this.getShopServiceSelector();
			this.$nextTick(() => {
			  this.$refs['dataForm'].clearValidate()
			})
		},
		handleUpdate(row) {
		  this.shop = Object.assign({}, row) // copy obj
      this.shop.pca = [this.shop.provinceCode, this.shop.cityCode, this.shop.areaCode]
		  this.dialogStatus = 'edit'
      this.checkedService = []
      if(this.shop.service){
        this.checkedService = this.shop.service.split(",")
      }
		  this.dialogFormVisible = true
      this.getShopServiceSelector();
		  this.$nextTick(() => {
		    this.$refs['dataForm'].clearValidate()
		  })
		},
    getShopServiceSelector(){
      if(!this.serviceOptions){
        getShopServiceSelector().then(response => {
          this.serviceOptions = response.data
        })
      }
    },
		confirm() {
			this.$refs['dataForm'].validate((valid) => {
			  if (valid) {
			    let formData =Object.assign({}, this.shop)
          if(this.checkedService && this.checkedService.length > 0){
          	formData.service = this.checkedService.join(",")
          }
          if (this.shop.pca && this.shop.pca.length > 0) {
            formData.provinceCode = this.shop.pca[0]
            formData.cityCode = this.shop.pca[1]
            formData.areaCode = this.shop.pca[2]
            formData.province = CodeToText[formData.provinceCode]
            formData.city = CodeToText[formData.cityCode]
            formData.area = CodeToText[formData.areaCode]
          }
					formData.addTime = null
					formData.updTime = null
			    updateShop(formData).then(() => {
						this.getList()
			      this.dialogFormVisible = false
			      this.$notify({
			        title: '成功',
			        message: '保存成功',
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
			    deleteShop(values.join(',')).then(() => {
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
			updateShop(formData).then(() => {
			  this.$notify({
			    title: '成功',
			    message: '显示顺序修改成功',
			    type: 'success',
			    duration: 2000
			  })
				this.getList()
			})
		},
    statusChange(row) {
      let formData = {
    		status: row.status,
    		id: row.id
    	}
    	updateShop(formData).then(() => {
    	  this.$notify({
    	    title: '成功',
    	    message: '显示隐藏修改成功',
    	    type: 'success',
    	    duration: 2000
    	  })
    		this.getList()
    	})
    },
    selfStatusChange(row) {
      let formData = {
    		selfStatus: row.selfStatus,
    		id: row.id
    	}
    	updateShop(formData).then(() => {
    	  this.$notify({
    	    title: '成功',
    	    message: '修改成功',
    	    type: 'success',
    	    duration: 2000
    	  })
    		this.getList()
    	})
    },
    handleClickLocationTab(tab, event) {
    	this.listQuery.page = 1
    	this.listQuery.pageSize = 20
    	this.locationActiveName = tab.name
    	if(tab.name == 'first'){
    		this.listQuery.type = null
    	}else if(tab.name == 'second'){
    		this.listQuery.type = 1
    	}else if(tab.name == 'third'){
    		this.listQuery.type = 2
    	}else if(tab.name == 'four'){
    		this.listQuery.type = 3
    	}
    	this.getList()
    },

  }
}
</script>
