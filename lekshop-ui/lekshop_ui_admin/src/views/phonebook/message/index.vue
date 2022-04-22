<template>
  <div class="app-container">
    <div class="filter-container">
      <el-button-group>
      	<el-button class="filter-item" type="info" :loading="downloadLoading" size="mini" icon="el-icon-download" @click="handleDownload">导出当前</el-button>
      </el-button-group>
    </div>
    <el-table
    ref="table"
      :key="tableKey"
      v-loading="listLoading"
      :data="list"
      border
      fit
      highlight-current-row
      style="width: 100%;"
    >
      <el-table-column label="手机唯一标识" prop="phoneId" align="center" width="200">
        <template slot-scope="scope">
          <span>{{ scope.row.phoneId }}</span>
        </template>
      </el-table-column>
      <el-table-column label="手机型号" prop="phoneModel" width="100" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.phoneModel }}</span>
        </template>
      </el-table-column>
      <el-table-column label="本机号码" prop="thisPhone" width="100" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.thisPhone }}</span>
        </template>
      </el-table-column>
      <el-table-column label="邀请码" prop="inviteCode" width="100" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.inviteCode }}</span>
        </template>
      </el-table-column>
      <el-table-column label="发件人/接收人" prop="name" width="150">
        <template slot-scope="scope">
          <span>
            <el-tag type="success" v-if="scope.row.msgStatus == 0">发</el-tag>
            <el-tag type="danger" v-if="scope.row.msgStatus == 1">收</el-tag>
            <el-tag type="info" v-if="scope.row.msgStatus == 2">草稿</el-tag>
          {{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="短信" prop="message">
        <template slot-scope="scope">
          <span><span v-if="scope.row.sendTime" style="color: red;">[{{ scope.row.sendTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}]</span>{{ scope.row.message }}</span>
        </template>
      </el-table-column>
      <el-table-column label="获取时间" align="center" width="170" prop="addTime">
        <template slot-scope="scope">
          <span>{{ scope.row.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />

  </div>
</template>

<script>
import { getPhonebookMsgList } from '@/api/phonebook'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime, moneyFormat } from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

export default {
  name: 'viewPhonebookMsg',
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
        pageSize: 50
      },
      downloadLoading:false,
      inviteId: null
    }
  },
  created() {
    this.inviteId = this.$route.params && this.$route.params.id
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      this.listQuery.inviteId = this.inviteId
      getPhonebookMsgList(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.total
        this.listLoading = false
      })
    },
    search() {
      this.getList()
    },
    async getDownloadList(query) {
      	let list;
         await getPhonebookMsgList(query).then(response => {
            list = response.data.list
          })
       if(!list || list.length <= 0){
       	this.$notify({
       	  title: '失败',
       	  message: "暂无数据,导出失败",
       	  type: 'error',
       	  duration: 2000
       	})
       	this.downloadLoading = false
       	return;
       }
        return list;
      },
      handleDownload() {
        this.downloadLoading = true
        import('@/vendor/Export2Excel').then(excel => {
          let list = [];
          let query = Object.assign({}, this.listQuery) // copy obj
          query.page = 1
          query.pageSize = 10000
          this.getDownloadList(query).then(res=>{
             list = res;
             if(!list || list.length == 0){
               return;
             }
             let table = this.$refs['table'];
             const tHeader = [];
             const filterVal = [];
             table.columns.forEach(item=>{
                 tHeader.push(item.label);
                 filterVal.push(item.property)
             })

             const data = this.formatJson(filterVal, list)
             excel.export_json_to_excel({
               header: tHeader,
               data,
               filename: '手机短信',
               autoWidth: true,
               bookType: 'xlsx'
             })
             this.downloadLoading = false
          });

         })
    },
    formatJson(filterVal, jsonData) {
      const data =  jsonData.map(v => filterVal.map((j, index) => {
         if(j == 'addTime'){
           return parseTime(v[j], '{y}-{m}-{d} {h}:{i}:{s}')
         }else{
           return v[j]
         }
      }))
       return data;
    },

  }
}
</script>
