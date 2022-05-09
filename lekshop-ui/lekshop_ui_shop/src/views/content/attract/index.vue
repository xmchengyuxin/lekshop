<template>
  <div class="app-container">
        <!-- <div class="filter-container">
          <el-select v-model="listQuery.brandId" placeholder="选择品牌" filterable clearable class="filter-item">
            <el-option v-for="item in brandOptions" :key="item.id" :label="item.name" :value="item.id">
            </el-option>
          </el-select>
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
    >
			<el-table-column type="selection" width="55" align="center"></el-table-column>
      <el-table-column label="填写时间" width="200px" align="center" prop="addTime" >
        <template slot-scope="scope">
          <span>{{ scope.row.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
			<el-table-column label="招商信息">
			  <template slot-scope="scope">
			   <p>姓名:{{ scope.row.realname }}</p>
         <p>电话:{{ scope.row.phone }}</p>
          <p>地址:{{scope.row.province}}{{scope.row.city}}{{scope.row.area}}{{scope.row.address}}</p>
			  </template>
			</el-table-column>
      <el-table-column :label="$t('table.actions')" class-name="small-padding" fixed="right">
        <template slot-scope="scope">
      		<el-button style="display: block;margin-top: 5px; margin-left: 5px;" v-if="scope.row.status=='0'" icon="el-icon-goods" size="mini" type="danger" @click="handleModifyStatus(scope.row)">
            置为已回访
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />

  </div>
</template>

<script>
import Tinymce from '@/components/Tinymce'
import {getAttractList, dealwithAttractInvest} from '@/api/attract'
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
  name: 'attractTable',
  components: { Pagination, Tinymce },
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
      dialogFormVisible: false,
      dialogType: '',
			multipleSelection: [],
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      getAttractList(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.total
				this.listLoading = false
      })
    },
    handleModifyStatus(row) {
    	dealwithAttractInvest({id:row.id}).then(response => {
    		const message = '已置为已回访';
    		this.$message({
    		  message: message,
    		  type: 'success'
    		})
    		row.status = status
    	})
    },

  }
}
</script>
