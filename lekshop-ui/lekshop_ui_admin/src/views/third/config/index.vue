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
      <el-table-column label="调用接口名称" width="200px"  align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.name }}</span>
        </template>
      </el-table-column>
			<el-table-column label="调用接口标识" width="180px" align="center" >
			  <template slot-scope="scope">
			    <span>{{ scope.row.nid}}</span>
			  </template>
			</el-table-column>
      <el-table-column label="接口切换" >
        <template slot-scope="scope">
          <el-radio-group v-model="scope.row.value" size="mini" @change="changeValue(scope.row)">
            <el-radio label="wwyw" border v-if="scope.row.platforms.indexOf('wwyw') >= 0">万维易网</el-radio>
            <el-radio label="aliapprove" border v-if="scope.row.platforms.indexOf('aliapprove') >= 0">阿里认证</el-radio>
            <el-radio label="qiniu" border v-if="scope.row.platforms.indexOf('qiniu') >= 0">七牛云</el-radio>
            <el-radio label="local" border v-if="scope.row.platforms.indexOf('local') >= 0">本地储存</el-radio>
            <el-radio label="alioss" border v-if="scope.row.platforms.indexOf('alioss') >= 0">阿里OSS</el-radio>
            <el-radio label="jdwx" border v-if="scope.row.platforms.indexOf('jdwx') >= 0">京东万象</el-radio>
            <el-radio label="tfk" border v-if="scope.row.platforms.indexOf('tfk') >= 0">淘发客</el-radio>
          </el-radio-group>
        </template>
      </el-table-column>
    </el-table>

  </div>
</template>

<script>
import {getThirdConfigList, updateThirdConfig} from '@/api/third'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime } from '@/utils'

export default {
  name: 'thirdConfig',
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
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      getThirdConfigList(this.listQuery).then(response => {
        this.list = response.data
				this.listLoading = false
      })
    },
    changeValue(row){
      let formData = {
        id : row.id,
        value: row.value
      }
      updateThirdConfig(formData).then(response => {
        this.$notify({
          title: '成功',
          message: '切换成功',
          type: 'success',
          duration: 2000
        })
      })
    }

  }
}
</script>
