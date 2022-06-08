<template>
  <div class="app-container">
    <!-- <div class="filter-container">
			 <el-input v-model="listQuery.adminName" clearable placeholder="操作人" style="width: 200px;" class="filter-item" @keyup.enter.native="getList" />
			 <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" circle @click="getList"></el-button>
		</div> -->
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
			<el-table-column type="selection" width="55" align="center" ></el-table-column>
			<el-table-column label="登录用户" prop="adminName" align="center">
			  <template slot-scope="scope">
			    <span>{{ scope.row.memberName }}</span>
			  </template>
			</el-table-column>
			<el-table-column label="登录IP" prop="ip" align="center">
			  <template slot-scope="scope">
			    <a class="link-type" :href="'https://www.ip.cn/?ip='+scope.row.ip" target="_blank">{{ scope.row.ip }}</a>
          <span>{{ scope.row.address}}</span>
			  </template>
			</el-table-column>
			<el-table-column label="登录时间" align="center">
			  <template slot-scope="scope">
					<span style="color:red">{{ scope.row.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
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
    width: 100%;
  }
	.inline_table td,.inline_table th,.inline_table tr,.inline_table thead,.inline_table table,.inline_table th.is-leaf,.inline_table td {
		border: none;
	}
	.inline_table.el-table::before {
		height: 0;
		}
</style>

<script>
import {getLoginLoginLog} from '@/api/log'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime, moneyFormat} from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

export default {
  name: 'loginLogTable',
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
      getLoginLoginLog(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.total
				this.listLoading = false
      })
    },
		handleSelectionChange(val) {
			this.multipleSelection = val;
     },

  }
}
</script>
