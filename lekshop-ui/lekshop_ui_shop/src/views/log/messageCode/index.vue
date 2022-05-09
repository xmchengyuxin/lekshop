<template>
  <div class="app-container">
    <div class="filter-container">
			<el-input v-model="listQuery.name" clearable placeholder="验证号码" style="width: 200px;" class="filter-item" @keyup.enter.native="getList" />
			<el-select v-model="listQuery.type" placeholder="验证类型" clearable class="filter-item" style="width: 130px">
			  <el-option v-for="item in typeOptions" :key="item.key" :label="item.text" :value="item.key" />
			</el-select>
			
			<el-checkbox :indeterminate="isIndeterminate" v-model="checkAll" @change="handleCheckAllChange">全选</el-checkbox>
			<el-checkbox-group style="display: inline-block;" v-model="checkedStatus" @change="handleCheckedStatusChange">
				<el-checkbox style="margin-right:10px;" v-for="status in statusList" :label="status" :key="status">{{status | statusFilter}}</el-checkbox>
			</el-checkbox-group>
			<el-button v-waves class="filter-item" type="primary" icon="el-icon-search" circle @click="getList"></el-button>
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
      <el-table-column label="验证号码" prop="name" align="center" width="200">
        <template slot-scope="scope">
          <span>{{ scope.row.name }}</span>
        </template>
      </el-table-column>
			<el-table-column label="验证码" width="200px"  align="center">
			  <template slot-scope="scope">
			    <span>{{ scope.row.code }}</span>
			  </template>
			</el-table-column>
			<el-table-column label="验证类型" width="100"  align="center">
			  <template slot-scope="scope">
			    <el-tag>{{ scope.row.type | typeFilter }}</el-tag>
			  </template>
			</el-table-column>
			<el-table-column label="发送状态" width="150"  align="center">
			  <template slot-scope="scope">
			    <el-tag>{{ scope.row.sendStatus | statusFilter}}</el-tag>
			  </template>
			</el-table-column>
      <el-table-column label="发送时间" prop="addTime" >
        <template slot-scope="scope">
          <span>{{ scope.row.sendTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
		</el-table>
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />
		
  </div>
</template>

<script>
import {getverifyCodeLog} from '@/api/log'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime } from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

const statusOptions = [
  { key: '0', text: '未发送' },
  { key: '1', text: '已发送' },
  { key: '2', text: '发送失败' }
]

const typeOptions = [
  { key: '1', text: '手机' },
  { key: '2', text: '邮箱' }
]
// arr to obj ,such as { CN : "China", US : "USA" }
const statusKeyValue = statusOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

const typeKeyValue = typeOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

export default {
  name: 'messageLogTable',
  components: { Pagination },
  directives: { waves },
	filters: {
	  statusFilter(status) {
	    return statusKeyValue[status]
	  },
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
			statusOptions,
			typeOptions,
			checkAll: true,
			checkedStatus: ['0','1', '2'],
			isIndeterminate: false,
			statusList: ['0','1', '2']
    }
  },
  created() {
    this.getList()
  },
  methods: {
		handleCheckAllChange(val) {
			this.checkedStatus = val ? ['0','1', '2'] : [];
			this.isIndeterminate = false;
		},
		handleCheckedStatusChange(value) {
			let checkedCount = value.length;
			this.checkAll = checkedCount === this.statusList.length;
			this.isIndeterminate = checkedCount > 0 && checkedCount < this.statusList.length;
		},
    getList() {
      this.listLoading = true
			if(this.checkedStatus && this.checkedStatus.length > 0){
				this.listQuery.statusArr = this.checkedStatus.join(",")
			}
      getverifyCodeLog(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.total
				this.listLoading = false
      })
    }
		
  }
}
</script>
