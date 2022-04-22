<template>
  <div class="app-container">
    <div class="filter-container">
			 <el-input v-model="listQuery.memberName" clearable placeholder="操作人" style="width: 200px;" class="filter-item" @keyup.enter.native="getList" />
       <el-input v-model="listQuery.keyword" clearable placeholder="操作内容" style="width: 200px;" class="filter-item" @keyup.enter.native="getList" />
			 <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" circle @click="getList"></el-button>
       <br/>
       <el-button-group>
        <el-button class="filter-item" size="mini" type="danger" plain icon="el-icon-delete" @click="handleDelete(3)">删除3天前日志</el-button>
        <el-button class="filter-item" size="mini" type="danger" plain icon="el-icon-delete" @click="handleDelete(7)">删除7天前日志</el-button>
        <el-button class="filter-item" size="mini" type="danger" plain icon="el-icon-delete" @click="handleDelete(30)">删除30天前日志</el-button>
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
    >
			<el-table-column label="操作用户" prop="memberName" align="center" width="150">
			  <template slot-scope="scope">
			    <router-link :to="'/member/detail?memberId='+scope.row.memberId">
			      <p class="link-type">{{ scope.row.memberName }}</p>
			    </router-link>
			  </template>
			</el-table-column>
      <el-table-column label="操作IP" prop="ip" align="center" width="150">
        <template slot-scope="scope">
          <span>{{ scope.row.ip }}</span>
        </template>
      </el-table-column>
			<el-table-column label="操作时间" align="center" width="200">
			  <template slot-scope="scope">
					<span style="color:red">{{ scope.row.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
				</template>
			</el-table-column>
      <el-table-column label="操作内容" prop="remark">
        <template slot-scope="scope">
          <span>{{ scope.row.remark }}</span>
        </template>
      </el-table-column>
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="操作参数">
              <span>{{ props.row.params }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />

  </div>
</template>

<style>
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }
</style>


<script>
import {getOperationLogList, deleteOperationLog} from '@/api/member'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime, moneyFormat} from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

export default {
  name: 'memberLog',
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
        pageSize: 20,
      },
			multipleSelection: [],
			downloadLoading:false,
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      getOperationLogList(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.total
				this.listLoading = false
      })
    },
		handleSelectionChange(val) {
			this.multipleSelection = val;
     },
     handleDelete(days){
     	this.$confirm('您确定要删除'+days+'天前的日志?', '提醒', {
     	  confirmButtonText: '确定',
     	  cancelButtonText: '取消',
     	  type: 'warning'
     	}).then(async() => {
     	    deleteOperationLog({days: days}).then(() => {
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

  }
}
</script>
