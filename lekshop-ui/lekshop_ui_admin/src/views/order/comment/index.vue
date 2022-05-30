<template>
  <div class="app-container">
    <el-tabs v-model="activeName" @tab-click="handleClickTab">
    	  <el-tab-pane label="全部" name="first"></el-tab-pane>
    		<el-tab-pane label="好评" name="second"></el-tab-pane>
    		<el-tab-pane label="中评" name="third"></el-tab-pane>
    		<el-tab-pane label="差评" name="fourth"></el-tab-pane>
    </el-tabs>
    <div class="filter-container">
      <el-input v-model="listQuery.memberName" clearable placeholder="买家" style="width: 200px;" class="filter-item" @keyup.enter.native="getList" />
      <el-input v-model="listQuery.shopName" clearable placeholder="卖家" style="width: 200px;" class="filter-item" @keyup.enter.native="getList" />
			<el-input v-model="listQuery.goodsName" clearable placeholder="商品名称" style="width: 200px;" class="filter-item" @keyup.enter.native="getList" />
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
			<el-table-column type="selection" width="45" align="center"></el-table-column>
      <!-- <el-table-column :label="$t('table.id')" prop="id" align="center" width="55">
        <template slot-scope="scope">
          <span>{{ scope.row.id }}</span>
        </template>
      </el-table-column> -->
			<el-table-column label="买家" align="center" width="100">
			  <template slot-scope="scope">
			    <span>{{ scope.row.memberName }}</span>
			  </template>
			</el-table-column>
      <el-table-column label="店铺名" prop="shopName" align="center" width="130">
        <template slot-scope="scope">
          <span>{{ scope.row.shopName }}</span>
        </template>
      </el-table-column>
			<el-table-column label="商品" align="center" min-width="150">
			  <template slot-scope="scope">
          <div  class="flex  padding-tb10">
            <el-image
            class="flex f-s-0 margin-r12"
                style="height: 40px"
                :src="scope.row.goodsMainImg"
                :preview-src-list="[scope.row.goodsMainImg]">
              </el-image>
              <div class="flex f-c">
                <span class="line2">{{scope.row.goodsName}}</span>
              </div>
          </div>
			  </template>
			</el-table-column>
			<el-table-column label="评论内容"  align="center" min-width="150">
			  <template slot-scope="scope">
			    <span>{{ scope.row.content | filterFun}}</span>
			  </template>
			</el-table-column>
      <el-table-column label="评论图片"  align="center" min-width="100">
        <template slot-scope="scope">
          <div v-if="scope.row.img">
          <el-image
              v-for="item in scope.row.img.split('|')"
              style="height: 30px;margin-left: 5px;"
              :src="item"
              :preview-src-list="scope.row.img.split('|')">
            </el-image>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="评价" align="center" width="70">
        <template slot-scope="scope">
      		<span>{{ scope.row.goodsComment | goodsCommentFilter}}</span>
        </template>
      </el-table-column>
      <el-table-column label="综合评分" align="center" width="70">
        <template slot-scope="scope">
      		<span>{{ scope.row.avgStarNum}}</span>
        </template>
      </el-table-column>
      <el-table-column label="描述相符"  align="center" width="70">
        <template slot-scope="scope">
      		<span>{{ scope.row.goodsStarNum}}</span>
        </template>
      </el-table-column>
      <el-table-column label="发货速度"  align="center" width="70">
        <template slot-scope="scope">
      		<span>{{ scope.row.deliveryStarNum}}</span>
        </template>
      </el-table-column>
      <el-table-column label="服务态度"  align="center" width="70">
        <template slot-scope="scope">
      		<span>{{ scope.row.shopStarNum}}</span>
        </template>
      </el-table-column>
      <el-table-column label="物流服务"  align="center" width="70">
        <template slot-scope="scope">
      		<span>{{ scope.row.logisticStarNum}}</span>
        </template>
      </el-table-column>
      <el-table-column label="评论时间" align="center" prop="addTime" width="150">
        <template slot-scope="scope">
          <span>{{ scope.row.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
			<el-table-column :label="$t('table.actions')" class-name="small-padding" fixed="right" width="60">
			  <template slot-scope="scope">
          <el-tooltip class="item" effect="dark" content="查看" placement="top">
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleView(scope.row)"></el-button>
          </el-tooltip>
			  </template>
			</el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />

		<!--查看评论框-->
		<el-dialog title="查看评论" :visible.sync="dialogFormVisible">
		    <el-form ref="dataForm" :model="comment" label-position="left" style="margin-left:50px;">
		      <el-collapse v-model="actName" accordion>
							<el-collapse-item name="1">
								<template slot="title">
									<i class="el-icon-time"></i>
                  &nbsp;{{comment.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}')}}
                  &nbsp;<span style="font-weight: 600;">{{comment.memberName}}</span>
								</template>
								<div style="margin-left:10px;">{{comment.content}}</div>
								<div>
                  <el-image
                      style="height:150px;margin-left:10px;"
                      v-for="img in imgList" :src="img"
                      :preview-src-list="imgList">
                    </el-image>
                </div>
							</el-collapse-item>
							<el-collapse-item v-for="item in leftCommentList" :name="item.id">
								<template slot="title" @click.stop>
									<i class="el-icon-time"></i>
                  &nbsp;{{item.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}')}}
                  &nbsp;<span style="font-weight: 600;">{{item.type == 1 ? '掌柜回复' : item.memberName}}</span>
									<!-- &nbsp;&nbsp;&nbsp;&nbsp;<span v-if="item.type == 1" style="color:red;" @click="handleDeleteComment(item.id)"><i class="el-icon-delete"></i>&nbsp;删除</span> -->
								</template>
								<div>{{item.content}}</div>
							</el-collapse-item>
							</el-collapse-item>
						</el-collapse>

						<!-- <el-form-item style="margin-top:10px;" prop="reviewContent">
							<el-input type="textarea" :rows="4" v-model="comment.reviewContent" />
							<el-button type="primary" size="mini" @click="handleReview()">回复评论</el-button>
						</el-form-item> -->
		    </el-form>
		</el-dialog>

  </div>
</template>

<script>
import {getCommentList, getLeftCommentList, answerComment, deleteLeftComment, deleteComment} from '@/api/order'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime } from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

const goodsCommentOptions = [
  { key: 1, text: '好评' },
  { key: 2, text: '中评' },
  { key: 3, text: '差评' },
]

// arr to obj ,such as { CN : "China", US : "USA" }
const goodsCommentKeyValue = goodsCommentOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

export default {
  name: 'commentList',
  components: { Pagination },
	filters: {
		filterFun: function (value) {
		if(value && value.length > 20) {
		 value= value.substring(0,20)+ '...';
		}
		 return value;
		},
    goodsCommentFilter(status) {
      return goodsCommentKeyValue[status]
    },
	},
  directives: { waves },
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
      comment: {},
			activeName: 'first',
      dialogFormVisible: false,
      dialogType: '',
			multipleSelection: [],
			leftCommentList : [],
			imgList:[],
      actName: ''
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      getCommentList(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.total
				this.listLoading = false
      })
    },
		handleView(row) {
		  this.comment = Object.assign({}, row)
			if(this.comment.img && this.comment.img != ''){
				this.imgList = this.comment.img.split('|')
			}
			this.actName = '1'
			this.getLeftCommentList();
		  this.dialogFormVisible = true
		},
		handleReview() {
			this.$refs['dataForm'].validate((valid) => {
			  if (valid) {
			    let formData = {
						 commentId: this.comment.id,
						 content: this.comment.reviewContent
					}
			    answerComment(formData).then(() => {
			      this.$notify({
			        title: '成功',
			        message: '回复评论成功',
			        type: 'success',
			        duration: 2000
			      })

						this.comment.reviewContent = '';
            this.getLeftCommentList();
			    })
			  }
			})
		},
    getLeftCommentList(){
      getLeftCommentList({commentId:this.comment.id}).then(response => {
        this.leftCommentList = response.data
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
			    deleteComment(values.join(',')).then(() => {
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
		handleDeleteComment(id){
			this.$confirm('您确定要删除这条记录?', '提醒', {
			  confirmButtonText: '确定',
			  cancelButtonText: '取消',
			  type: 'warning'
			}).then(async() => {
			    deleteLeftComment({leftCommentId:id}).then(() => {
			      this.$notify({
			        title: '成功',
			        message: '删除成功',
			        type: 'success',
			        duration: 2000
			      })

						this.getLeftCommentList();
			    })
			  })
			  .catch(err => { console.error(err) })
		},
		statusChange(row) {
		  let formData = {
				status: row.status,
				id: row.id
			}
			updateComment(formData).then(() => {
			  this.$notify({
			    title: '成功',
			    message: '评论内容开关成功',
			    type: 'success',
			    duration: 2000
			  })
			})
		},
    handleClickTab(tab, event) {
    	this.listQuery = {
    	  page: 1,
    	  pageSize: 20
    	}
    	this.activeName = tab.name
    	if(tab.name == 'first'){
    		this.listQuery.goodsComment = null
    	}else if(tab.name == 'second'){
    		this.listQuery.goodsComment = 1
    	}else if(tab.name == 'third'){
    		this.listQuery.goodsComment = 2
    	}else if(tab.name == 'fourth'){
    		this.listQuery.goodsComment = 3
    	}
    	this.getList()
    },

  }
}
</script>
