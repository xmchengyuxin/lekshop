<template>
  <div class="app-container">
    <div class="filter-container">
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
			<el-table-column type="selection" width="55" align="center"></el-table-column>
			<el-table-column label="模板名称" align="center">
			  <template slot-scope="scope">
         <span>{{ scope.row.name }}</span>
			  </template>
			</el-table-column>
			<el-table-column label="店铺ID"  align="center">
			  <template slot-scope="scope">
			    <span>{{ scope.row.shopId }}</span>
			  </template>
			</el-table-column>
      <el-table-column label="所属店铺"  align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.shopName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="发货时间"  align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.deliveryTime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否包邮"  align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.type == 1 ? '自定义运费' : '卖家包邮' }}</span>
        </template>
      </el-table-column>
      <el-table-column label="计价方式"  align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.valuteType | valuteTypeFilter}}</span>
        </template>
      </el-table-column>
      <el-table-column label="默认规则(不包括指定地区)"  align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.defaultFirstNum}}件内{{scope.row.defaultFirstPrice}}元,每超过{{ scope.row.defaultSecondNum}}件增加{{scope.row.defaultSecondPrice}}元</span>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />

  </div>
</template>

<script>
import {getShopFreightList, deleteShopFreight} from '@/api/shop'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime, moneyFormat } from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

const typeOptions = [
  { key: 1, text: '按件数' },
  { key: 2, text: '按重量' },
  { key: 3, text: '按体积' },
]

// arr to obj ,such as { CN : "China", US : "USA" }
const typeKeyValue = typeOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

export default {
  name: 'freightTemplate',
  components: { Pagination },
  directives: { waves },
  filters: {
    valuteTypeFilter(status) {
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
        pageSize: 20,
      },
      config: {},
      dialogFormVisible: false,
      dialogType: '',
			multipleSelection: [],
      locationActiveName:'first',
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      let queryParams = this.listQuery
      getShopFreightList(queryParams).then(response => {
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
			    deleteShopFreight(values.join(',')).then(() => {
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
