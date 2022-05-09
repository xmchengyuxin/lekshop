<template>
  <div class="app-container">
		<el-table
      :key="tableKey"
      v-loading="listLoading"
      :data="list"
      border
      fit
      highlight-current-row
      style="width: 100%;"
    >
			<el-table-column label="反馈时间" width="180px" align="center" prop="addTime" >
			  <template slot-scope="scope">
			    <span>{{ scope.row.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
			  </template>
			</el-table-column>
			<el-table-column label="用户" width="200px"  align="center">
			  <template slot-scope="scope">
			    <span>{{ scope.row.memberName }}</span>
			  </template>
			</el-table-column>
			<!-- <el-table-column label="反馈类型" width="100px"  align="center">
			  <template slot-scope="scope">
					<el-tag type="danger">{{ scope.row.type | typeFilter}}</el-tag>
			  </template>
			</el-table-column>
			<el-table-column label="手机" width="200px"  align="center">
			  <template slot-scope="scope">
			    <span>{{ scope.row.phone }}</span>
			  </template>
			</el-table-column> -->
      <el-table-column label="反馈内容" min-width="200px" prop="addTime" >
        <template slot-scope="scope">
          <span class="link-type">{{ scope.row.content }}</span>
        </template>
      </el-table-column>
      <el-table-column label="反馈图片" min-width="300px"  prop="imgs" >
        <template slot-scope="scope">
          <el-image
              v-if="scope.row.imgs"
              v-for="item in scope.row.imgs.split('|')"
              style="width: 100px;padding: 10px;"
              :src="item"
              :preview-src-list="scope.row.imgs.split('|')">
            </el-image>
            <br/>
            <span class="link-type" v-if="scope.row.video != null" @click="viewVideo(scope.row)">查看反馈视频</span>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />

    <!--视频弹窗-->
    <el-dialog title="视频" :visible.sync="dialogVideoVisible">
        	<video id="playVideos" width="30%" height="100%" webkit-playsinline="true" preload="auto" controls="controls">
    					 <source :src="videoUrl" type="video/mp4">
    			</video>
    </el-dialog>

  </div>
</template>

<script>
import {getFeedbackList} from '@/api/feedback'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime } from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

const typeOptions = [
  { key: '1', text: '遇到问题' },
  { key: '2', text: '功能建议' }
]

// arr to obj ,such as { CN : "China", US : "USA" }
const typeKeyValue = typeOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

export default {
  name: 'feedbackTable',
  components: { Pagination },
  directives: { waves },
	filters: {
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
        pageSize: 20
      },
			typeOptions,
      dialogVideoVisible:false,
      videoUrl:'',
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      getFeedbackList(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.total
				this.listLoading = false
      })
    },
    viewVideo(row){
    	this.videoUrl = ''
    	this.videoUrl = row.video
      this.dialogVideoVisible = true
    	document.getElementById("playVideos").src = row.video;
    },
    closeVideoDialog(){
    	document.getElementById("playVideos").src=''
    	this.dialogVideoVisible = false
    },

  }
}
</script>
