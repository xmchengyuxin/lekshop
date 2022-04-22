<template>
  <div class="app-container">
    <div class="filter-container">
      <el-tabs v-model="activeName" @tab-click="handleClickTab">
      		<el-tab-pane label="买家" name="first"></el-tab-pane>
          <el-tab-pane label="商家" name="second"></el-tab-pane>
      </el-tabs>
      <el-select v-model="listQuery.cateId" placeholder="文章分类" filterable clearable class="filter-item">
         <el-option v-for="item in cateOptions" :key="item.id" :label="item.name" :value="item.id">
         </el-option>
       </el-select>
       <el-input v-model="listQuery.title" clearable placeholder="标题" style="width: 200px;" class="filter-item" @keyup.enter.native="getList" />
       <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" circle @click="getList"></el-button>
      <br>
			<el-button-group>
					<el-button @click="$router.push('/content/article/create')" class="filter-item" type="primary" size="mini" icon="el-icon-edit">
						发表文章
					</el-button>
				 <el-button size="mini" class="filter-item" type="danger" icon="el-icon-delete" @click="handleDelete">删除</el-button>
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
      <el-table-column label="文章分类" width="180px" align="center" prop="cateName" >
        <template slot-scope="scope">
          <span>{{ scope.row.cateName}}</span>
        </template>
      </el-table-column>
      <el-table-column label="标题" min-width="300px" prop="title" >
        <template slot-scope="scope">
          <router-link :to="'/content/article/edit/'+scope.row.id">
            <span class="link-type">{{ scope.row.title }}</span>
          </router-link>
        </template>
      </el-table-column>
      <el-table-column label="日期" width="180px" align="center" prop="addTime" >
        <template slot-scope="scope">
          <span>{{ scope.row.updTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" width="100px"  align="center">
        <template slot-scope="scope">
      		<el-tag :type="scope.row.status == '-1' ? 'danger' : 'success' ">{{ scope.row.status | statusFilter}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="排序" width="200px"  align="center">
        <template slot-scope="scope">
        <el-input-number size="mini" style="width:100px;" v-model="scope.row.sort" @change="sortChange(scope.row)" :min="1" label="修改排序"></el-input-number>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.actions')" class-name="small-padding" min-width="120px"  fixed="right">
        <template slot-scope="scope">
				<router-link :to="'/content/article/edit/'+scope.row.id">
				  <el-button type="primary" size="mini" icon="el-icon-edit">
				    编辑
				  </el-button>
				</router-link>
				</template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />

  </div>
</template>

<script>
import {getArticleList, updateArticle, deleteArticle, getArticleCateSelector} from '@/api/article'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime } from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

const statusOptions = [
  { key: 1, text: '显示' },
  { key: 0, text: '草稿' }
]

// arr to obj ,such as { CN : "China", US : "USA" }
const statusKeyValue = statusOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

export default {
  name: 'articleTable',
  components: { Pagination },
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
        pageSize: 20,
        type: 1
      },
			multipleSelection: [],
			statusOptions,
      cateOptions:[],
      activeName:'first',
    }
  },
  created() {
    this.getArticleCateSelector()
    this.getList()
  },
  methods: {
    getArticleCateSelector(){
      getArticleCateSelector().then(response => {
        this.cateOptions = response.data
      })
    },
    getList() {
      this.listLoading = true
      getArticleList(this.listQuery).then(response => {
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
			    deleteArticle(values.join(',')).then(() => {
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
			updateArticle(formData).then(() => {
			  this.$notify({
			    title: '成功',
			    message: '显示顺序修改成功',
			    type: 'success',
			    duration: 2000
			  })
				this.getList()
			})
		},
    handleClickTab(tab, event) {
      this.listQuery.page = 1
      this.listQuery.pageSize = 20
    	this.activeName = tab.name
    	if(tab.name == 'first'){
        this.listQuery.type = 1
    	}else if(tab.name == 'second'){
    		 this.listQuery.type = 2
    	}
    	this.getList()
    },

  }
}
</script>
