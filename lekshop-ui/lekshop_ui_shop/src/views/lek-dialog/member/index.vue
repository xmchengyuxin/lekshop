<template>
  <div class="">

    <div class="filter-container">
      <el-input v-model="listQuery.nickname" clearable placeholder="昵称" style="width: 200px;" class="filter-item" @keyup.enter.native="getList" />
        <el-select v-model="listQuery.salesId" placeholder="请选择业务员" class="filter-item" clearable filterable style="width: 200px;">
          <el-option
            v-for="item in salesOptions"
            :key="item.id"
            :label="item.realname"
            :value="item.id"
          />
        </el-select>

      <el-date-picker class="filter-item" v-model="listQuery.startTime" type="date" format="yyyy-MM-dd" placeholder="选择开始时间" />
      -
      <el-date-picker  class="filter-item"  v-model="listQuery.endTime" type="date" format="yyyy-MM-dd" placeholder="选择结束时间" />

      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" circle @click="getList"></el-button>
      <el-select
        class="filter-item"
        @change="changeTarget"
         clearable
         style="width: 250px;"
         v-model="checkTargets"
         multiple
         collapse-tags
         placeholder="标签">
         <el-option
           v-for="item in targetOptions"
           :key="item.id"
           :label="item.name"
           :value="item.id">
         </el-option>
       </el-select>
		</div>
		<el-table
      ref="memberTable"
      :key="tableKey"
      v-loading="listLoading"
      :data="list"
      border
      fit
      highlight-current-row
      style="width: 100%;"
      height="580"
			@selection-change="handleSelectionChange"
    >
			<el-table-column type="selection" width="45" align="center"></el-table-column>
      <el-table-column label="用户名称"  align="center" prop="code" >
      	  <template slot-scope="scope">
            <!-- <el-avatar shape="square" size="large" :src="scope.row.headImg"></el-avatar>
      			<br> -->
              <span class="link-type">{{ scope.row.code }}</span>
              <br>
              <span class="link-type">{{ scope.row.realname }}</span>
      	  </template>
      	</el-table-column>
      	<el-table-column label="会员级别" align="center" prop="level">
      	  <template slot-scope="scope">
      	    <span>{{scope.row.level}} 级会员</span>
      	  </template>
      	</el-table-column>
        <el-table-column label="业务员" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.salesName}}</span>
          </template>
        </el-table-column>
        <el-table-column label="注册时间"  align="center" prop="addTime" >
          <template slot-scope="scope">
            <span>{{ scope.row.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
          </template>
        </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />

      <div style="text-align:right;margin-top: 10px;">
      <el-button @click="clickClose" size="mini">取 消</el-button>
      <el-button type="primary" size="mini" @click="clickOK">确 定</el-button>
    </div>

  </div>



</template>

<script>
import {getMemberSimpleList, getMemberTargetList } from '@/api/member'
import { getSalesList} from '@/api/sales'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime } from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

export default {
  name: 'memberDialog',
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
      dialogFormVisible: false,
      dialogType: '',
			dialogMultipleSelection: [],
      flag: false,
      salesOptions: [],
      targetOptions: [],
      checkTargets: [],
      checkTargetNames: [],
    }
  },
  created() {
    this.getList()
    this.getSalesList()
    this.getMemberTargetList()
  },
  methods: {
    getSalesList(){
      getSalesList({page:1, pageSize: 9999}).then(response => {
        this.salesOptions = response.data.list
      })
    },
    getMemberTargetList(){
      getMemberTargetList({page:1, pageSize: 9999}).then(response => {
        this.targetOptions = response.data.list
      })
    },
    getList() {
      this.listLoading = true
      if(this.checkTargets && this.checkTargets.length > 0) {
        this.listQuery.targetIds = this.checkTargets.join(",")
      }else{
        this.listQuery.targetIds = null
      }
      getMemberSimpleList(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.total
				this.listLoading = false
      })
    },
		handleSelectionChange(val) {
			this.dialogMultipleSelection = val;
     },
     // 点击确认
     clickOK() {
       this.$emit("selected", this.dialogMultipleSelection);
       this.clickClose();
     },
     // 关闭弹窗
     clickClose() {
       this.close();
     },
     // 打开组件方法
     open(type, mutiple) {
       this.flag = true;
     },
     // 关闭组件
     close() {
       this.$emit("close");
     },
     changeTarget(){
       this.getList();
     },
     clearChecked(){
       this.dialogMultipleSelection = [];
       this.$refs.memberTable.clearSelection();
     }

  }
}
</script>

<style lang="scss" scoped>

  .el-radio{
    margin-right: 0px !important;
  }
  .el-select-dropdown{
    span{white-space: pre !important;}
  }
  .el-select-dropdown__item{
    white-space: pre !important;
  }

</style>
