<template>
  <div class="app-container">
		<el-tabs v-model="activeName" @tab-click="handleClickTab">
			  <el-tab-pane label="全部" name="0"></el-tab-pane>
				<el-tab-pane label="短视频" name="1"></el-tab-pane>
				<el-tab-pane label="上新" name="2"></el-tab-pane>
        <el-tab-pane label="种草" name="3"></el-tab-pane>
        <el-tab-pane label="买家秀" name="4"></el-tab-pane>
		</el-tabs>
    <div class="filter-container">
      <el-input v-model.number="listQuery.walkMemberUid" clearable placeholder="创作号" style="width: 200px;" class="filter-item" @keyup.enter.native="getList()" />
      <!-- <el-select v-model="listQuery.type" placeholder="类型" class="filter-item" clearable filterable>
          <el-option
            v-for="item in typeOptions"
            :key="item.key"
            :label="item.text"
            :value="item.key">
          </el-option>
        </el-select> -->
        <el-select v-model="listQuery.status" placeholder="状态" class="filter-item" clearable filterable>
            <el-option
              v-for="item in statusOptions"
              :key="item.key"
              :label="item.text"
              :value="item.key">
            </el-option>
          </el-select>
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" circle @click="getList"></el-button>
      <br>
			<el-button-group>
			<el-button class="filter-item" size="mini" type="primary" icon="el-icon-s-promotion"  @click="handleCreate">发布</el-button>
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
          <span>{{ scope.row.walkTrends.id }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创作号" width="120px"  align="center">
        <template slot-scope="scope">
      		<span>{{ scope.row.walkTrends.walkMemberUid}}</span>
        </template>
      </el-table-column>
      <el-table-column label="类型" width="100px"  align="center">
        <template slot-scope="scope">
      		<span v-if="scope.row.walkTrends.type == 1" style="color: #C03639;">{{ scope.row.walkTrends.type | typeFilter}}</span>
          <span v-if="scope.row.walkTrends.type == 2" style="color: #00bcbc;">{{ scope.row.walkTrends.type | typeFilter}}</span>
          <span v-if="scope.row.walkTrends.type == 3" style="color: #c885c8;">{{ scope.row.walkTrends.type | typeFilter}}</span>
          <span v-if="scope.row.walkTrends.type == 4" style="color: #00cb00;">{{ scope.row.walkTrends.type | typeFilter}}</span>
        </template>
      </el-table-column>
      <el-table-column label="图片" min-width="150px">
        <template slot-scope="scope">
          <div v-if="scope.row.walkTrends.images">
            <el-image
                v-for="item in scope.row.walkTrends.images.split('|')"
                style="height: 30px;margin-left: 5px;"
                :src="item"
                :preview-src-list="scope.row.walkTrends.images.split('|')">
              </el-image>
          </div>
          <div v-if="scope.row.walkTrends.videoUrl">
            <el-image
                style="height: 30px;margin-left: 5px;"
                :src="scope.row.walkTrends.videoUrl+'?vframe/jpg/offset/0' "
                :preview-src-list="[scope.row.walkTrends.videoUrl+'?vframe/jpg/offset/0']">
              </el-image>
          </div>
        </template>
      </el-table-column>
			<el-table-column label="浏览量" width="70"  align="center">
			  <template slot-scope="scope">
					 <span>{{ scope.row.walkTrends.viewNum }}</span>
			  </template>
			</el-table-column>
      <el-table-column label="点赞" width="70"  align="center">
        <template slot-scope="scope">
      		 <span>{{ scope.row.walkTrends.collectionNum }}</span>
        </template>
      </el-table-column>
      <el-table-column label="评论" width="70"  align="center">
        <template slot-scope="scope">
      		 <span>{{ scope.row.walkTrends.commentNum }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" width="170px" align="center" prop="addTime" >
        <template slot-scope="scope">
          <span>{{ scope.row.walkTrends.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" width="100px"  align="center">
        <template slot-scope="scope">
      		 <el-tag :type="scope.row.walkTrends.status == 1 ? 'success' : 'danger' ">{{ scope.row.walkTrends.status | statusFilter}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.actions')" class-name="small-padding" fixed="right" width="150px">
        <template slot-scope="scope">
          <el-button-group>
            <el-tooltip class="item" effect="dark" content="查看" placement="top">
               <el-button  type="primary" icon="el-icon-s-promotion" size="mini" @click="handleViewTrends(scope.row)"></el-button>
            </el-tooltip>
            <el-tooltip class="item" effect="dark" content="播放" placement="top" v-if="scope.row.walkTrends.type == 1">
               <el-button  type="primary" icon="el-icon-video-camera-solid" size="mini" @click="viewVideo(scope.row.walkTrends)"></el-button>
            </el-tooltip>
            <el-tooltip class="item" effect="dark" content="查看评论" placement="top">
               <el-button  type="primary" icon="el-icon-s-comment" size="mini" @click="handleViewComment(scope.row.walkTrends.id)"></el-button>
            </el-tooltip>
          </el-button-group>

        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />

    <!--发布-->
		<el-dialog title="发布" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :model="trendsForm" label-width="100px" label-position="right" style="width: 100%; ">
          <el-form-item label="类型" prop="type" :rules="[{ required: true, message: '请选择类型', trigger: 'blur' }]">
          	<el-radio-group v-model="trendsForm.type" >
          		<el-radio :label="1" border>短视频</el-radio>
          		<el-radio :label="2" border>宝贝上新</el-radio>
              <el-radio :label="3" border>种草</el-radio>
              <!-- <el-radio :label="4" border>买家秀</el-radio> -->
          	</el-radio-group>
          </el-form-item>
		      <el-form-item label="文案" prop="content" :rules="[{ required: true, message: '请填写文案', trigger: 'blur' }]">
		        <el-input type="textarea" :rows="5" v-model="trendsForm.content" placeholder="添加文案,提升曝光率,获得更多赞赏~" style="width: 60%;"/>
		      </el-form-item>
          <el-form-item prop="images" label="上传图片" :rules="[{ required: true, message: '请上传图片', trigger: 'blur' }]" v-if="trendsForm.type == 3">
            <MultipleUpload :value="trendsForm.images" v-model="trendsForm.images" />
            <ImageSource :mul="true" v-model="trendsForm.images" />
          </el-form-item>
          <el-form-item label="上传视频" prop="videoUrl" :rules="[{ required: true, message: '请上传视频', trigger: 'blur' }]" v-if="trendsForm.type == 1">
              <SingleVideoUpload v-model="trendsForm.videoUrl" />
          </el-form-item>
          <el-form-item label="选择商品" prop="goodsId" :rules="[{ required: true, message: '请选择商品', trigger: 'change' }]" v-if="trendsForm.type == 2">
            <el-select
                v-model="trendsForm.goodsId"
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
          <el-form-item label="选择商品" prop="goodsId" v-if="trendsForm.type == 1">
            <el-select
                v-model="trendsForm.goodsId"
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
		    </el-form>
		    <div style="text-align:right;">
		      <el-button type="danger" @click="dialogFormVisible=false">取消</el-button>
		      <el-button type="primary" @click="confirm">确定</el-button>
		    </div>
		</el-dialog>

    <!--视频弹窗-->
    <el-dialog title="视频" :visible.sync="dialogVideoVisible" :destroy-on-close="true">
      <video id="playVideos" width="100%" height="100%" webkit-playsinline="true" preload="auto" controls="controls">
           <source :src="videoUrl" type="video/mp4">
      </video>
    </el-dialog>

    <!--查看详情-->
    <el-dialog title="查看" :visible.sync="dialogViewVisible" :destroy-on-close="true">
      <div class="flex">
        <el-image
           class="flex f-s-0 margin-r8"
          style="height: 40px"
          :src="trendsDetail.walkMemberHeadImg">
        </el-image>
        <div class="flex f-c">
          <span class="link-type" style="font-size: 12px;">{{ trendsDetail.walkMemberName}}</span>
          <span class="tips-class margin-t6">创作号: {{ trendsDetail.walkMemberUid}}</span>
        </div>
        <div class="flex f-j-e flex-1">
          <span class="tips-class" style="float: right;">{{ trendsDetail.addTime | parseTime}}</span>
         </div>
      </div>

      <div>
       <p v-text="trendsDetail.content" style="white-space: pre-wrap; font-size: 13px; padding: 10px;"></p>
       <p v-if="trendsDetail.images">
         <el-image
             v-for="item in trendsDetail.images.split('|')"
             style="width: 30%;margin-left: 5px;border-radius: 5px;"
             :src="item"
             :preview-src-list="trendsDetail.images.split('|')">
           </el-image>
       </p>
       <p v-if="trendsDetail.videoUrl">
           <video id="playVideos" width="50%" height="100%" webkit-playsinline="true" preload="auto" controls="controls">
                <source :src="trendsDetail.videoUrl" type="video/mp4">
           </video>
       </p>
      </div>
      <div style="margin-top: 20px;">
          <span class="tips-class margin-r10" style="font-size: 13px;">浏览量({{trendsDetail.viewNum}})</span>
           <span class="tips-class margin-r10" style="font-size: 13px;"><i class="el-icon-s-comment"></i>评论({{trendsDetail.commentNum}})</span>
           <span class="tips-class margin-r10" style="font-size: 13px;"><i class="el-icon-star-on"></i>点赞({{trendsDetail.collectionNum}})</span>
      </div>
    </el-dialog>

    <!--评论弹窗-->
    <el-dialog title="评论" :visible.sync="dialogCommentVisible" >
        <el-empty description="暂无评论" v-if="!commentList || commentList.length == 0"></el-empty>

        <div v-for="item in commentList" class="comment comment-item">
            <div class="flex">
              <el-avatar class="flex f-s-0 margin-r8 margin-t8"
                size="small"
                 :src="item.walkTrendsComment.viewMemberHeadImg"></el-avatar>
              <div class="flex flex-1 f-c margin-t12 padding-8">
                <span style="font-size: 13px;font-weight: bold;">{{item.walkTrendsComment.viewMemberName}}</span>
                <span style="padding: 10px 0 10px 0; font-size: 13px;">{{item.walkTrendsComment.content}}</span>
                <span class="tips-class flex f-j-s flex-1 w100" style="float: right;">
                  {{ item.walkTrendsComment.addTime | parseTime}}
                  <span style="font-size: 13px;"><i class="el-icon-star-on"></i>{{item.walkTrendsComment.likeNum}}</span>
                </span>

              </div>
            </div>

          <div v-for="item2 in item.chilidCommentList" class="sub-item">
            <div class="flex">
                <el-avatar class="flex f-s-0 margin-r8 margin-t8"
                  size="small" :src="item2.viewMemberHeadImg"></el-avatar>
              <div class="flex flex-1 f-c margin-t12 padding-8">
                <span style="font-size: 13px;font-weight: bold;">{{item2.viewMemberName}}</span>
                <span style="padding: 10px 0 10px 0; font-size: 13px;">{{item2.content}}</span>
                <span class="tips-class flex f-j-s flex-1 w100" style="float: right;">{{ item2.addTime | parseTime}}
                <span style="font-size: 13px;"><i class="el-icon-star-on"></i>{{item2.likeNum}}</span>
                </span>

              </div>
            </div>
          </div>

        </div>
      <pagination v-show="commentTotal>0" :total="commentTotal" :page.sync="commentListQuery.page" :limit.sync="commentListQuery.pageSize" @pagination="getCommentList" />
    </el-dialog>

  </div>
</template>

<style>
  .tips-class {
    font-size: 12px;
    color: #999;
  }
  .comment-item {
    padding: 20px;
    border-radius: 5px;
    background-color: #f9f9f9;
    margin-bottom: 12px;
  }
   .comment .sub-item {
    padding: 10px;
    background-color: #e8ecef;
    border-radius: 5px;
    margin-top: 5px;
    margin-left: 34px;
  }
</style>

<script>
import {getTrendsList, publishTrends, deleteTrends, getCommentList } from '@/api/trends'
import {getGoodsList} from '@/api/goods'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime } from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
import ImageSource from '@/components/Upload/imageSource'
import MultipleUpload from '@/components/Upload/multipleImage'
import SingleVideoUpload from '@/components/Upload/singleVideo'

const typeOptions = [
  { key: 1, text: '短视频' },
  { key: 2, text: '上新' },
  { key: 3, text: '种草' },
  /* { key: 4, text: '买家秀' }, */
]

// arr to obj ,such as { CN : "China", US : "USA" }
const typeKeyValue = typeOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

const statusOptions = [
  { key: 0, text: '待审核' },
  { key: 1, text: '发布成功' },
  { key: 2, text: '不通过' },
  { key: 3, text: '草稿' },
]

// arr to obj ,such as { CN : "China", US : "USA" }
const statusKeyValue = statusOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

export default {
  name: 'trendsList',
  components: { Pagination, ImageSource,
      MultipleUpload,
      SingleVideoUpload, },
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
      },
      trendsForm: {},
      dialogFormVisible: false,
      dialogVideoVisible: false,
      dialogViewVisible: false,
      dialogCommentVisible: false,
      dialogType: '',
			multipleSelection: [],
			typeOptions,
      statusOptions,
			activeName: '0',
      goodsOptions:[],
      videoUrl:'',
      trendsDetail: {},
      commentList: [],
      commentTotal: 0,
      commentListLoading: true,
      commentListQuery: {
        page: 1,
        pageSize: 20,
      },
      activeNames: []
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getGoodsSelector(){
    	getGoodsList({page:1, pageSize: 999}).then(response => {
    		this.goodsOptions = response.data.list
    	})
    },
    getList() {
      this.listLoading = true
      getTrendsList(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.total
				this.listLoading = false
      })
    },
		handleSelectionChange(val) {
			this.multipleSelection = val;
     },
     handleCreate() {
       this.trendsForm = {}
       this.dialogStatus = 'add'
       this.dialogFormVisible = true
       this.getGoodsSelector()
     	this.$nextTick(() => {
     	  this.$refs['dataForm'].clearValidate()
     	})
     },
     confirm() {
     	this.$refs['dataForm'].validate((valid) => {
     	  if (valid) {
     	    let formData =Object.assign({}, this.trendsForm)
     			formData.addTime = null
     			formData.updTime = null
           if(formData.goodsId){
             formData.goodsIds = formData.goodsId.join(",")
           }
     	    publishTrends(formData).then(() => {
     				this.getList()
     	      this.dialogFormVisible = false
     	      this.$notify({
     	        title: '成功',
     	        message: '发布成功',
     	        type: 'success',
     	        duration: 2000
     	      })
     	    })
     	  }
     	})
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
						values.push(val.walkTrends.id);
					})
			    deleteTrends(values.join(',')).then(() => {
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
      this.listQuery.type = this.activeName == '0' ? null : this.activeName
			this.getList()
		},
    viewVideo(row){
    	this.videoUrl = ''
    	this.videoUrl = row.videoUrl
      this.dialogVideoVisible = true
    	document.getElementById("playVideos").src = row.videoUrl;
    },
    closeVideoDialog(){
    	document.getElementById("playVideos").src=''
    	this.dialogVideoVisible = false
    },
    handleViewTrends(row){
      this.trendsDetail = row.walkTrends
      this.dialogViewVisible = true
    },
    handleViewComment(trendsId){
      this.commentListQuery.trendsId = trendsId
      this.getCommentList()
      this.dialogCommentVisible = true
    },
    getCommentList(){
      getCommentList(this.commentListQuery).then(response => {
        this.commentList = response.data.list
        this.commentTotal = response.data.total
        this.commentListLoading = false
      })
    }

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
