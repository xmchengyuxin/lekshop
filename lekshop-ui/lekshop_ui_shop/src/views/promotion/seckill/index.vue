<template>
  <div class="app-container">
		<!-- <el-tabs v-model="activeName" @tab-click="handleClickTab">
			  <el-tab-pane label="全部宝贝" name="first"></el-tab-pane>
				<el-tab-pane label="秒杀" name="second"></el-tab-pane>
				<el-tab-pane label="拼团" name="third"></el-tab-pane>
		</el-tabs> -->
    <div class="filter-container">
      <el-input v-model="listQuery.title" clearable placeholder="宝贝名称" style="width: 200px;" class="filter-item" @keyup.enter.native="getList()" />
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" circle @click="getList"></el-button>
      <br>
			<el-button-group>
			<el-button class="filter-item" size="mini" type="primary" icon="el-icon-plus"  @click="handleCreate">添加秒杀</el-button>
			<el-button class="filter-item" size="mini" type="danger" icon="el-icon-delete" @click="handleDelete">删除</el-button>
      <!-- <el-button class="filter-item" type="primary" size="mini" icon="el-icon-upload2" @click="handleShangjia">上架</el-button>
      <el-button class="filter-item" type="info" size="mini" icon="el-icon-download" @click="handleXiajia">下架</el-button> -->
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
            <span class="line1">
              <span v-if="scope.row.type != 1" :style="scope.row.type == 2 ? 'color:red;' : 'color:green;' ">[{{ scope.row.type | typeFilter}}]</span>
              {{ scope.row.title }}</span>
          </el-tooltip>
			  </template>
			</el-table-column>
			<el-table-column label="宝贝主图" width="80px"  align="center">
			  <template slot-scope="scope">
          <el-image
              v-if="scope.row.mainImg"
              style="height: 30px"
              :src="scope.row.mainImg"
              :preview-src-list="[scope.row.mainImg]">
            </el-image>
			  </template>
			</el-table-column>
      <el-table-column label="宝贝相册" width="165px">
        <template slot-scope="scope">
          <div class="line1" v-if="scope.row.goodsImg">
            <el-image
                v-for="item in scope.row.goodsImg.split('|')"
                style="height: 30px;margin-left: 5px;"
                :src="item"
                :preview-src-list="scope.row.goodsImg.split('|')">
              </el-image>
          </div>
        </template>
      </el-table-column>
			<!-- <el-table-column label="店铺内类目" width="100px"  align="center">
			  <template slot-scope="scope">
					 <span>{{ scope.row.shopCateName }}</span>
			  </template>
			</el-table-column> -->
      <el-table-column label="秒杀时段" width="250px"  align="center">
        <template slot-scope="scope">
      		 <span style="color: red;">{{ scope.row.seckillBeginTime | parseTime('{y}-{m}-{d} {h}:{i}') }} 至 {{ scope.row.seckillEndTime | parseTime('{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>
			<el-table-column label="秒杀价" width="70px"  align="center">
			  <template slot-scope="scope">
					 <span>{{ scope.row.price | moneyFormat}}</span>
			  </template>
			</el-table-column>
      <el-table-column label="限购" width="70px"  align="center">
        <template slot-scope="scope">
      		 <span>{{ scope.row.seckillLimitNum}}</span>
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
      <!-- <el-table-column label="评论" width="70px"  align="center">
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
      </el-table-column> -->
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
            <!-- <el-tooltip class="item" effect="dark" content="参数" placement="top">
               <el-button  type="primary" icon="el-icon-date" size="mini" @click="handleViewGoodsQuality(scope.row)"></el-button>
            </el-tooltip> -->
            <el-tooltip class="item" effect="dark" content="编辑" placement="top">
              <el-button  type="primary" icon="el-icon-edit" size="mini" @click="handleUpdate(scope.row)"></el-button>
            </el-tooltip>
          </el-button-group>

        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />

		<el-dialog title="秒杀活动" :visible.sync="dialogFormVisible">

			<el-form ref="dataForm" :model="seckillForm" label-position="right">
			  <el-form-item  prop="seckillDateRange" label="秒杀时间" :rules="[{ required: true, message: '请选择', trigger: 'blur' }]" >
			    <el-date-picker
			      style="width: 60%;"
			      v-model="seckillForm.seckillDateRange"
			      type="datetimerange"
			      range-separator="至"
			      start-placeholder="开始日期"
			      end-placeholder="结束日期">
			    </el-date-picker>
			  </el-form-item>
			  <el-form-item label="秒杀限购" prop="seckillLimitNum" :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
			    <el-input v-model.number="seckillForm.seckillLimitNum"style="width: 60%;" placeholder="请输入秒杀限购数量" />
			  </el-form-item>
        <el-form-item label="选择商品" prop="goodsIds" v-if="dialogType == 'add' ">
          <!-- 绑定商品选择器回调已选择的商品 -->
          <div>
            <el-button
              @click="bindGoodsId()"
              size="mini"
              plain
              type="danger"
              >选择商品</el-button
            >
            <el-tooltip placement="top" effect="light" v-if="goodsList && goodsList.length > 0">
              <div slot="content">
                <div
                  v-for="(bindGoods, bindGoodsIndex) in goodsList"
                  :key="bindGoodsIndex"
                >
                  {{ bindGoods.title }}  &nbsp;<span class="link-type" @click="deleteGoods(bindGoodsIndex)">删除</span>
                </div>
              </div>
              <el-button size="mini">查看已选商品</el-button>
            </el-tooltip>
          </div>

        </el-form-item>

        <el-form-item label="秒杀价格" prop="price" v-if="priceFormList && priceFormList.length > 0">
        <el-table
          :data="priceFormList"
          border
          :header-cell-style="{background:'#d9edf7',color:'#3498db', font:'18px'}"
          style="width: 60%;margin-left: 64px;margin-top: 10px;"
        >
          <el-table-column label="单位"  align="center">
        		<template slot-scope="scope">
        			{{ scope.row.attrSymbolName }}
            </template>
          </el-table-column>
        	<el-table-column label="原价" align="center">
        	  <template slot-scope="scope">
              {{ scope.row.oriPrice }}
        	  </template>
        	</el-table-column>
          <el-table-column label="秒杀价" align="center">
            <template slot-scope="scope">
              <el-input class="edit-input" size="mini" v-model="scope.row.price"/>
            </template>
          </el-table-column>
        </el-table>
        </el-form-item>

			</el-form>

      <div style="text-align:right;">
        <el-button type="danger" @click="dialogFormVisible=false">取消</el-button>
        <el-button type="primary" @click="confirm">确定</el-button>
      </div>
		</el-dialog>

    <lekDialog
      ref="lekDialog"
      @selectedGoodsData="selectedGoodsData"
    ></lekDialog>

  </div>
</template>

<script>
import {getGoodsList, updateSeckill, deleteSeckill, getSkuList} from '@/api/goods'
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
  name: 'seckill',
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
        type: "2"
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
      seckillForm: {},
      goodsList: [],
      priceFormList: []
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
			    deleteSeckill(values.join(',')).then(() => {
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
    getGoods(id) {
      this.priceFormList = []
      getSkuList({
        goodsId: id
      }).then(response => {
        let skuList = response.data;
        skuList.forEach(item => {
          this.priceFormList.push(
            {
              skuId: item.id,
              price: item.price,
              oriPrice: item.oriPrice,
              attrSymbolName: item.attrSymbolName
            }
          );
        })
      }).catch(err => {
        console.log(err)
      })
    },
    handleCreate() {
      this.priceFormList = []
      this.dialogType = "add"
    	this.dialogFormVisible = true
    	this.seckillForm = {}
    	this.$nextTick(() => {
    	  this.$refs['dataForm'].clearValidate()
    	})
    },
    handleUpdate(row) {
      this.priceFormList = []
      this.dialogType = "edit"
      this.seckillForm = Object.assign({}, row) // copy obj
      this.seckillForm.seckillDateRange = [row.seckillBeginTime, row.seckillEndTime]
      this.getGoods(row.id)
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    confirm() {
    	this.$refs['dataForm'].validate((valid) => {
    	  if (valid) {
    	    let formData = Object.assign({}, this.seckillForm)
    			if(formData.seckillDateRange){
    				formData.seckillBeginTime = renderTime(formData.seckillDateRange[0])
    				formData.seckillEndTime = renderTime(formData.seckillDateRange[1])
    			}
          formData.priceFormList = JSON.stringify(this.priceFormList);
    			formData.addTime = null
    			formData.updTime = null
    	    updateSeckill(formData).then(() => {
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
    // 绑定商品
    bindGoodsId() {
      this.lekDialogFlag(true);
    },
    lekDialogFlag(flag) {
      this.$refs.lekDialog.clearGoodsSelected()
      this.$refs.lekDialog.singleGoods()
      this.$refs.lekDialog.goodsFlag = flag;
      this.$refs.lekDialog.flag = true;
    },
    // 回调的商品信息
    selectedGoodsData(val) {
      if (!val) return false;
      let data = val.map((item) => {
        return {
          id: item.id,
          img: item.mainImg,
          title: item.title,
        };
      });
      this.goodsList = data;
      this.seckillForm.id = this.goodsList[0].id
      this.getGoods(this.seckillForm.id)
    },
    deleteGoods(index) {
      this.goodsList.splice(index, 1)
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
