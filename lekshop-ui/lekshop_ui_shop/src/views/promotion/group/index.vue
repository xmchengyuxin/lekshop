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
			<el-button class="filter-item" size="mini" type="primary" icon="el-icon-plus"  @click="handleCreate">添加拼团</el-button>
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
			<el-table-column label="拼团类型" width="100px"  align="center">
			  <template slot-scope="scope">
					 <el-tag effect="dark" type="success">{{ scope.row.groupType == 1 ? '普通拼团' : '阶梯拼团' }}</el-tag>
			  </template>
			</el-table-column>
			<el-table-column label="成团人数" width="70px"  align="center">
			  <template slot-scope="scope">
					 <span v-if="scope.row.groupType == 1">{{ scope.row.groupNum}}</span>
					 <span v-else class="link-type">查看</span>
			  </template>
			</el-table-column>
      <el-table-column label="成团限时" width="100px"  align="center">
        <template slot-scope="scope">
      		 <span>{{ scope.row.groupLimitHours}} 小时</span>
        </template>
      </el-table-column>
      <el-table-column label="个人限购" width="70px"  align="center">
        <template slot-scope="scope">
      		 <span>{{ scope.row.groupLimitBuy}}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否支持单买" width="70px"  align="center">
        <template slot-scope="scope">
      		 <span>{{ scope.row.groupSingleBuy == 1 ? "是" : "否"}}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新时间" width="150px" align="center" prop="addTime" >
        <template slot-scope="scope">
          <span>{{ scope.row.updTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
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

		<el-dialog title="拼团活动" :visible.sync="dialogFormVisible">

			<el-form ref="dataForm" :model="groupForm" label-position="right">
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

			  <el-form-item label="拼团类型" prop="groupType" :rules="[{ required: true, message: '请选择', trigger: 'change' }]">
			      <el-radio-group v-model="groupForm.groupType" size="small">
			        <el-radio :label="1" border>普通拼团</el-radio>
			        <el-radio :label="2" border>阶梯拼团</el-radio>
			      </el-radio-group>
			  </el-form-item>
			  <el-form-item v-if="groupForm.groupType == 2" label="* 阶梯配置" prop="groupConfig" >
			      <span style="font-size: 13px;font-weight: 550;color: #18bc9c;">成团人数</span>
			      <span style="font-size: 13px;font-weight: 550;margin-left: 100px; color: #18bc9c;">优惠阶梯%</span>
			      <div v-for="(item,index)  in groupList" style="margin-top: 3px;margin-left: 70px;">
			          <el-input v-model.number="item.num" placeholder="请输入成团人数" style="width: 150px;"></el-input>
			          <el-input v-model="item.discounts" placeholder="请输入优惠阶梯" style="width: 150px;"></el-input>
			          <el-button v-if="index != 0" type="danger"  size="mini"  @click="deleteGroup(index)" icon="el-icon-delete"></el-button>
			      </div>

			      <el-form-item style="margin-left: 70px;">
			        <el-button-group>
			          <el-button type="success"  size="mini"  @click="addGroup" >追加</el-button>
			        </el-button-group>
			      </el-form-item>

			  </el-form-item>
			  <el-form-item v-if="groupForm.groupType == 1" label="成团人数" prop="groupNum" :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
			    <el-input v-model.number="groupForm.groupNum"style="width: 60%;" placeholder="请输入组团成团人数" />
			  </el-form-item>
			  <el-form-item label="组团限时" prop="groupLimitHours" :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
			    <el-input v-model.number="groupForm.groupLimitHours"style="width: 60%;" placeholder="请输入组团限时(小时)" />
			    <span class="tips">单位:小时</span>
			  </el-form-item>
			  <el-form-item label="个人限购" prop="groupLimitBuy" :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
			    <el-input v-model.number="groupForm.groupLimitBuy"style="width: 60%;" placeholder="请输入个人购买限制" />
			    <span class="tips">单位:次数</span>
			  </el-form-item>
			  <el-form-item label="是否单买" prop="groupSingleBuy" :rules="[{ required: true, message: '请选择', trigger: 'change' }]">
			      <el-radio-group v-model="groupForm.groupSingleBuy" size="small">
			        <el-radio :label="1" border>开启单购</el-radio>
			        <el-radio :label="2" border>关闭单购</el-radio>
			      </el-radio-group>
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
import {getGoodsList, updateGroup, deleteGroup, getSkuList, getGoods} from '@/api/goods'
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
  name: 'goodsGroup',
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
        type: "3",
        sort: "upd_time desc"
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
      groupForm: {},
      goodsList: [],
      priceFormList: [],
      groupList: [],
      singleGroup: {num: null, discounts: null},
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
			    deleteGroup(values.join(',')).then(() => {
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
      this.groupList = []
      getGoods({
        goodsId: id
      }).then(response => {
        this.groupList = response.data.goodsGroupList
      }).catch(err => {
        console.log(err)
      })
    },
    handleCreate() {
      this.groupList = []
      this.dialogType = "add"
    	this.dialogFormVisible = true
    	this.groupForm = {}
    	this.$nextTick(() => {
    	  this.$refs['dataForm'].clearValidate()
    	})
    },
    handleUpdate(row) {
      this.groupList = []
      this.dialogType = "edit"
      this.groupForm = Object.assign({}, row) // copy obj
      this.groupForm.seckillDateRange = [row.seckillBeginTime, row.seckillEndTime]
      this.getGoods(row.id)
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    confirm() {
    	this.$refs['dataForm'].validate((valid) => {
    	  if (valid) {
    	    let formData = Object.assign({}, this.groupForm)
    			if(formData.seckillDateRange){
    				formData.seckillBeginTime = renderTime(formData.seckillDateRange[0])
    				formData.seckillEndTime = renderTime(formData.seckillDateRange[1])
    			}
          formData.groupJson = JSON.stringify(this.groupList);
    			formData.addTime = null
    			formData.updTime = null
    	    updateGroup(formData).then(() => {
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
      this.groupForm.id = this.goodsList[0].id
      this.getGoods(this.groupForm.id)
    },
    deleteGoods(index) {
      this.goodsList.splice(index, 1)
    },
    addGroup(){
      this.groupList.push({num: null, discounts: null})
    },
    deleteGroup(index){
      this.groupList.splice(index,1)
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
