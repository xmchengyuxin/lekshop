<template>
  <div class="app-container">
		<el-tabs  v-model="activeName" @tab-click="handleClickTab">
    <el-tab-pane :label="'未读消息('+unreadNum+')'" name="first">
				
				<div class="filter-container">
					<el-button-group>
						<el-button class="filter-item" size="mini" type="primary"  @click="handleReadAll">全部标为已读</el-button>
					</el-button-group>
				</div>
				
				<el-table
				  :key="tableKey"
				  v-loading="listLoading"
				  :data="list"
				  fit
				  highlight-current-row
				  style="width: 100%;"
				>
					<el-table-column>
					  <template slot-scope="scope">
					    <span class="link-type">{{ scope.row.title }}|</span>
							<span class="link-type">{{ scope.row.content }}</span>
					  </template>
					</el-table-column>
				  <el-table-column  width="200px" align="center" prop="sendTime" >
				    <template slot-scope="scope">
				      <span>{{ scope.row.sendTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
				    </template>
				  </el-table-column>
				  <el-table-column class-name="small-padding" fixed="right" width="200px">
				    <template slot-scope="scope">
				      <el-button type="primary" size="mini" @click="handleRead(scope.row)">标为已读</el-button>
				    </template>
				  </el-table-column>
				</el-table>
				
				<pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />
				
		
		</el-tab-pane>
    <el-tab-pane :label="'已读消息('+readedNum+')'" name="second">
			
				<div class="filter-container">
					<el-button-group>
						<el-button class="filter-item" size="mini" type="danger"  @click="handleDeleteAll">全部删除</el-button>
					</el-button-group>
				</div>
				
				<el-table
				  :key="tableKey"
				  v-loading="listLoading"
				  :data="list"
				  fit
				  highlight-current-row
				  style="width: 100%;"
				>
					<el-table-column>
					  <template slot-scope="scope">
					    <span class="link-type">{{ scope.row.title }}|</span>
							<span class="link-type">{{ scope.row.content }}</span>
					  </template>
					</el-table-column>
				  <el-table-column  width="200px" align="center" prop="sendTime" >
				    <template slot-scope="scope">
				      <span>{{ scope.row.sendTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
				    </template>
				  </el-table-column>
				  <el-table-column class-name="small-padding" fixed="right" width="200px">
				    <template slot-scope="scope">
				      <el-button type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>
				    </template>
				  </el-table-column>
				</el-table>
				
				<pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />
				
		</el-tab-pane>
  </el-tabs>
  </div>
</template>

<script>
import {getNewsList, readNews, readAll, deleteNews} from '@/api/news'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime } from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

export default {
  name: 'newsTable',
  components: { Pagination },
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
			activeName: 'first',
			readedNum: '0',
			unreadNum: '0'
    }
  },
  created() {
    this.getList()
  },
  methods: {
		handleClickTab(){
			this.getList();
		},
    getList() {
      this.listLoading = true
			if(this.activeName == 'first'){
				this.listQuery.readStatus = '0'
			}else{
				this.listQuery.readStatus = '1'
			}
      getNewsList(this.listQuery).then(response => {
        this.list = response.data.newsPage.items
        this.total = response.data.newsPage.totalSize
				this.readedNum = response.data.readedNum
				this.unreadNum = response.data.unreadNum
				this.listLoading = false
      })
    },
		handleReadAll() {
			readAll().then(() => {
				this.getList();
			})
		},
		handleRead(row) {
			readNews(row.id).then(() => {
				this.getList();
			})
		},
		handleDelete(row){
			let id = row ? row.id : ''
			this.$confirm('您确定要删除这些记录?', '提醒', {
			  confirmButtonText: '确定',
			  cancelButtonText: '取消',
			  type: 'warning'
			}).then(async() => {
			    deleteNews(id).then(() => {
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
		handleDeleteAll(){
			this.handleDelete()
		}
		
  }
}
</script>
