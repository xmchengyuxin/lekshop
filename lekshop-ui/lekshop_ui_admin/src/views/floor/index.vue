<template>
  <div class="app-container">
   <el-tabs v-model="locationActiveName" @tab-click="handleClickLocationTab">
    	  <el-tab-pane label="首页" name="1"></el-tab-pane>
    		<el-tab-pane label="专题" name="2"></el-tab-pane>
    </el-tabs>
      <div class="filter-container">
        <el-button-group>
          <router-link :to="'/floor/mobile/create?type='+locationActiveName">
            <el-button class="filter-item" size="mini" type="primary" icon="el-icon-plus" >添加</el-button>
          </router-link>
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
			<el-table-column label="模板名称"  align="center">
			  <template slot-scope="scope">
			    <span>{{ scope.row.name}}</span>
			  </template>
			</el-table-column>
      <el-table-column label="状态"  align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.status == 1" style="font-size: 17px; color: rgb(64, 158, 255); font-weight: bold;">使用中</span>
          <span v-else class="link-type" @click="statusChange(scope.row)">使用此模板</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="addTime" >
        <template slot-scope="scope">
          <span>{{ scope.row.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.actions')" align="center">
        <template slot-scope="scope">
          <router-link :to="'/floor/mobile/edit?id='+scope.row.id+'&name='+scope.row.name">
            <el-button type="primary" size="mini" icon="el-icon-edit">编辑</el-button>
          </router-link>
          <el-button size="mini" type="danger" icon="el-icon-delete" @click="handleDelete(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />

  </div>
</template>

<script>
import {getFloorList, updateFloor, deleteFloor} from '@/api/floor'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime } from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination


const statusOptions = [
  { key: 1, text: '显示' },
  { key: 0, text: '隐藏' }
]

// arr to obj ,such as { CN : "China", US : "USA" }
const statusKeyValue = statusOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

export default {
  name: 'floorList',
  components: { Pagination},
  directives: { waves },
	filters: {
	  statusFilter(status) {
	    return statusKeyValue[status]
	  },
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
      },
      dialogFormVisible: false,
      dialogType: '',
			multipleSelection: [],
			statusOptions,
      locationActiveName:"1"
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      getFloorList(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.total
				this.listLoading = false
      })
    },
		handleSelectionChange(val) {
			this.multipleSelection = val;
     },
		handleDelete(id){
			this.$confirm('您确定要删除这些记录?', '提醒', {
			  confirmButtonText: '确定',
			  cancelButtonText: '取消',
			  type: 'warning'
			}).then(async() => {
			    deleteFloor(id).then(() => {
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
    statusChange(row) {
      let formData = {
    		status: 1,
    		id: row.id,
        type: row.type
    	}
    	updateFloor(formData).then(() => {
    	  this.$notify({
    	    title: '成功',
    	    message: '模板修改成功',
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
      this.listQuery.type = this.locationActiveName
    	this.getList()
    },

  }
}
</script>
