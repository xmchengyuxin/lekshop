<template>
  <div class="app-container">
    <div class="filter-container">
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
			<el-table-column label="关联商品" prop="shopName">
			  <template slot-scope="scope">
			    <el-card id="miaosha"  class="box-card" shadow="hover">
			      <div slot="header" class="clearfix">
			        <span>名称： {{ scope.row.relate.name }}</span>
			        <span style="margin-left: 20px;">优先级：			  <el-input-number size="mini" style="width:100px;" v-model="scope.row.relate.sort" @change="sortChange(scope.row.relate)" :min="1" label="修改排序"></el-input-number></span>
			        </span>
               <el-button type="primary" icon="el-icon-edit" size="mini" @click="handleUpdate(scope.row.relate)">编辑</el-button>
               <span class="tips">优先级1为最高、99为最低</span>
			      </div>

			      <div  class="flex  padding-tb10" :class="index < scope.row.goodsList.length-1 ? 'bottom-b':''" v-for="(item,index) in scope.row.goodsList">
			        <el-image
			        class="flex f-s-0 margin-r12"
			            style="height: 60px"
			            :src="item.mainImg"
			            :preview-src-list="[item.mainImg]">
			          </el-image>
			          <div class="flex f-c">
			            <span class="line1">{{item.title}}</span>
			          </div>
			          <!-- <span class="order-role flex f-a-c f-j-c" v-if="item.type == 3">拼</span>
			          <span class="order-role flex f-a-c f-j-c miaosha" v-if="item.type == 2">秒</span> -->
			      </div>
			    </el-card>


			  </template>
			</el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />

		<!--添加编辑框-->
		<el-dialog :title="dialogType==='edit'?'编辑':'新增'" :visible.sync="dialogFormVisible">
		    <el-form ref="dataForm" :rules="rules" :model="relate" label-width="80px" label-position="right">
					<el-form-item label="关联名称" prop="name">
					  <el-input style="width: 60%;" v-model="relate.name" placeholder="请输入名称" />
					</el-form-item>
          <el-form-item label="优先级" prop="sort">
          	<el-input-number style="width: 60%;" v-model="relate.sort" :min="1" label="修改排序"></el-input-number>
          </el-form-item>
          <el-form-item label="选择商品" prop="goodsIds">
            <!-- 绑定商品选择器回调已选择的商品 -->
              <div
                v-for="(bindGoods, bindGoodsIndex) in goodsList"
                :key="bindGoodsIndex"
              >
                {{ bindGoods.title }}  &nbsp;<span class="link-type" @click="delteGoods(bindGoodsIndex)">删除</span>
              </div>
            <div>
              <el-button
                @click="bindGoodsId()"
                size="mini"
                plain
                type="danger"
                >选择商品</el-button
              >
            </div>

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
import {addGoodsRelate, updateRelateSort, getGoodsRelateList, deleteGoodsRelate} from '@/api/goods'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime } from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

const statusOptions = [
  { key: '1', text: '显示' },
  { key: '0', text: '隐藏' }
]

// arr to obj ,such as { CN : "China", US : "USA" }
const statusKeyValue = statusOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

export default {
  name: 'goodsRelation',
  components: { Pagination},
  directives: { waves },
	filters: {
	  statusFilter(status) {
	    return statusKeyValue[status]
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
      relate: {},
      dialogFormVisible: false,
      dialogType: '',
			multipleSelection: [],
			rules: {
			  name: [{ required: true, message: '分类名称不能为空', trigger: 'blur' }],
				sort: [{ required: true, message: '显示顺序不能为空', trigger: 'change' }],
				status: [{ required: true, message: '是否显示不能为空', trigger: 'change' }]
			},
			statusOptions,
      goodsList: []
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      getGoodsRelateList(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.total
				this.listLoading = false
      })
    },
		handleCreate() {
		  this.dialogType = 'add'
		  this.dialogFormVisible = true
			this.relate = {}
			this.$nextTick(() => {
			  this.$refs['dataForm'].clearValidate()
			})
		},
		handleUpdate(row) {
		  this.relate = Object.assign({}, row) // copy obj
		  this.dialogType = 'edit'
		  this.dialogFormVisible = true
		  this.$nextTick(() => {
		    this.$refs['dataForm'].clearValidate()
		  })
		},
		confirm() {
			this.$refs['dataForm'].validate((valid) => {
			  if (valid) {
			    let formData =Object.assign({}, this.relate)
          let goodsIds = []
          this.goodsList.forEach(item=>{
            goodsIds.push(item.id);
          })
          formData.goodsIds = goodsIds.join(",")
					formData.addTime = null
					formData.updTime = null
			    addGoodsRelate(formData).then(() => {
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
						values.push(val.relate.id);
					})
			    deleteGoodsRelate(values.join(',')).then(() => {
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
    		relateId: row.id
    	}
    	updateRelateSort(formData).then(() => {
    	  this.$notify({
    	    title: '成功',
    	    message: '优先级修改成功',
    	    type: 'success',
    	    duration: 2000
    	  })
    		this.getList()
    	})
    },
    // 绑定商品
    bindGoodsId() {
      this.lekDialogFlag(true);
    },
    lekDialogFlag(flag) {
      this.$refs.lekDialog.clearGoodsSelected()
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
    },
    delteGoods(index) {
      this.goodsList.splice(index, 1)
    }

  }
}
</script>
