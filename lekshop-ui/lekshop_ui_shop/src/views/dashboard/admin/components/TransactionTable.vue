<template>
  <el-table :data="list" style="width: 100%;padding-top: 15px;">
    <el-table-column label="订单编号" width="170">
      <template slot-scope="scope">
        {{ scope.row.orderNo}}
      </template>
    </el-table-column>
    <el-table-column label="用户名" width="195">
      <template slot-scope="scope">
        {{ scope.row.memberName}}
      </template>
    </el-table-column>
    <el-table-column label="备注" min-width="200" >
      <template slot-scope="scope">
        <span :style="scope.row.inOut == '-1' ? 'color:red;' : 'color:green;' ">{{scope.row.inOut == '-1' ? '[支出]' : '[收入]'}}</span>
        {{ scope.row.remark}}
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import { getAccountLog } from '@/api/account'

export default {
  filters: {
    remarkFilter(str) {
      return str.substring(0, 30)
    }
  },
  data() {
    return {
      list: null
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
			let queryForm = {
				page : 1, pageSize: 9
			}
      getAccountLog(queryForm).then(response => {
        this.list = response.data.items
      })
    }
  }
}
</script>
