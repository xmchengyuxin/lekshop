<template>
  <div class="app-container">
    <div class="filter-container">
      <el-select v-model="listQuery.queryType" placeholder="请选择" class="filter-item" filterable style="width: 100px;">
          <el-option label="用户名" value="code"> </el-option>
          <el-option label="手机" value="phone"> </el-option>
          <el-option label="邮箱" value="email"> </el-option>
          <el-option label="昵称" value="nickname"> </el-option>
          <el-option label="QQ" value="qq"> </el-option>
          <el-option label="注册IP" value="registerIp"> </el-option>
        </el-select>
      <el-input v-model="listQuery.keyword" clearable placeholder="关键词" style="width: 200px;" class="filter-item" @keyup.enter.native="getList" />
      <el-select v-model="listQuery.groupId" placeholder="请选择小组" class="filter-item" clearable filterable>
          <el-option
            v-for="item in groupOptions"
            :key="item.id"
            :label="item.name"
            :value="item.id">
          </el-option>
        </el-select>
			<el-date-picker class="filter-item" v-model="listQuery.startTime" type="datetime" format="yyyy-MM-dd HH:mm:ss" placeholder="选择开始时间" />
			-
			<el-date-picker  class="filter-item"  v-model="listQuery.endTime" type="datetime" format="yyyy-MM-dd HH:mm:ss" placeholder="选择结束时间" />

      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" circle @click="getList"></el-button>
			<br>
      <el-button class="filter-item" type="info" :loading="downloadLoading" size="mini" icon="el-icon-download" @click="handleDownload">导出全部</el-button>
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
			<el-table-column type="selection" align="center" width="55" />
      <el-table-column label="id" width="70"  align="center" fixed="left">
        <template slot-scope="scope">
          <router-link :to="'/member/detail?uid='+scope.row.uid">
            <span class="link-type">{{ scope.row.uid }}</span>
          </router-link>
        </template>
      </el-table-column>
			<el-table-column label="用户名称" width="130"  align="center" fixed="left" prop="code" >
			  <template slot-scope="scope">
          <!-- <el-avatar shape="square" size="large" :src="scope.row.headImg"></el-avatar>
					<br> -->
          <router-link :to="'/member/detail?uid='+scope.row.uid">
            <span class="link-type">{{ scope.row.code }}</span>
            <br>
            <span class="link-type">{{ scope.row.realname }}</span>
          </router-link>
			  </template>
			</el-table-column>
      <el-table-column label="状态" width="150" align="center">
        <template slot-scope="scope">
            <span v-if="scope.row.status == '1'" style="font-size: 17px; color: rgb(64, 158, 255); font-weight: bold;">正常</span>
            <span v-else style="font-size: 17px; color: rgb(245, 108, 108); font-weight: bold;">停用</span>
           <el-button  v-if="scope.row.status=='1'"  size="mini" type="danger" @click="handleModifyStatus(scope.row,'-1')">
             停用
           </el-button>
           <el-button  v-if="scope.row.status=='-1'" size="mini" type="success" @click="handleModifyStatus(scope.row,'1')">
             启用
           </el-button>
        </template>
      </el-table-column>
			<el-table-column label="会员组" width="100" align="center" prop="groupName">
			  <template slot-scope="scope">
			    <span>{{scope.row.groupName}}</span>
			  </template>
			</el-table-column>
      <el-table-column label="备注" align="center" prop="remark">
        <template slot-scope="scope">
          <span>{{scope.row.remark}}</span>
        </template>
      </el-table-column>
        <el-table-column label="余额" align="center" width="80px">
          <template slot-scope="scope">
            <span>{{ scope.row.account.amount | moneyFormat }}</span>
          </template>
        </el-table-column>
        <el-table-column label="积分" align="center" width="80px">
          <template slot-scope="scope">
            <span>{{ scope.row.account.point | moneyFormat }}</span>
          </template>
        </el-table-column>
        <el-table-column label="佣金" align="center" width="80px">
          <template slot-scope="scope">
            <span>{{ scope.row.account.spmission | moneyFormat }}</span>
          </template>
        </el-table-column>
	  <el-table-column label="推荐关系" width="100"  prop="tjrNum" align="center">
	    <template slot-scope="scope">
	  		<p class="link-type" @click="handleXiaji(scope.row)">我的直推</p>
	      <p class="link-type" @click="handleShangji(scope.row)">我的上级</p>
	    </template>
	  </el-table-column>
        <el-table-column label="实名认证" width="80px" align="center">
          <template slot-scope="scope">
            <svg v-if="scope.row.realnameStatus == '0'" class="svg-icon" style="width: 25px; height: 25px;"><use xlink:href="#icon-error"></use></svg>
            <svg v-else class="svg-icon" style="width: 25px; height: 25px;"><use xlink:href="#icon-success"></use></svg>
          </template>
        </el-table-column>
        <el-table-column label="银行认证" width="80px" align="center">
          <template slot-scope="scope">
            <svg v-if="scope.row.bankStatus == '0'" class="svg-icon" style="width: 25px; height: 25px;"><use xlink:href="#icon-error"></use></svg>
            <svg v-else class="svg-icon" style="width: 25px; height: 25px;"><use xlink:href="#icon-success"></use></svg>
          </template>
        </el-table-column>
      <el-table-column label="注册时间" width="150" align="center" prop="addTime" >
        <template slot-scope="scope">
          <span>{{ scope.row.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="注册IP" width="150" align="center" prop="registerIp" >
        <template slot-scope="scope">
          <span>{{ scope.row.registerIp}}</span>
        </template>
      </el-table-column>
      <el-table-column label="最后登录IP" width="150" align="center" prop="lastLoginIp" >
        <template slot-scope="scope">
          <span>{{ scope.row.lastLoginIp}}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.actions')" class-name="small-padding" width="100" fixed="right">
        <template slot-scope="scope">
            <router-link :to="'/member/detail?uid='+scope.row.uid">
              <el-button style="display: block;margin-top: 5px; margin-left: 5px;"  icon="el-icon-view" size="mini" type="primary">
                详情
              </el-button>
            </router-link>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />


    <!--下级弹出框-->
        <el-dialog title="直推下级" :visible.sync="xiajiVisible">
          <el-table
            v-loading="xiajiListLoading"
            :data="xiajiList"
            border
            fit
            highlight-current-row
            style="width: 100%;"
          >
          	<el-table-column label="用户名称" width="150px"  align="center" fixed="left">
          		  <template slot-scope="scope">
          	       <el-avatar shape="square" size="large" :src="scope.row.headImg"></el-avatar>
          				<br>
          		    <span class="link-type">{{ scope.row.code }}</span>
          				<br>
          				<span class="link-type">{{ scope.row.realname }}</span>
          		  </template>
          		</el-table-column>
          		<el-table-column label="手机号" width="150px">
          		  <template slot-scope="scope">
          	     <span>{{scope.row.phone}}</span>
          		  </template>
          		</el-table-column>
          		<el-table-column label="会员组" width="100px" align="center">
          		  <template slot-scope="scope">
          		    <span>{{scope.row.groupName}}</span>
          		  </template>
          		</el-table-column>
          	  <el-table-column label="用户状态" width="100px" align="center">
          	    <template slot-scope="scope">
          	       <el-tag :type="scope.row.status == '-1' ? 'danger' : 'success' " >{{ scope.row.status | statusFilter}} </el-tag>
          	    </template>
          	  </el-table-column>
          	    <el-table-column label="实名认证" width="80px" align="center">
          	      <template slot-scope="scope">
          	        <p><el-tag :type="scope.row.realnameStatus == '0' ? 'danger' : 'success' " >{{ scope.row.realnameStatus == 0 ? '未认证' : '已认证'}} </el-tag></p>
          	      </template>
          	    </el-table-column>
          	    <el-table-column label="手机认证" width="80px" align="center">
          	      <template slot-scope="scope">
          	        <p><el-tag :type="scope.row.phoneStatus == '0' ? 'danger' : 'success'" >{{ scope.row.phoneStatus == 0 ? '未认证' : '已认证'}} </el-tag></p>
          	      </template>
          	    </el-table-column>
          	    <el-table-column label="银行认证" width="80px" align="center">
          	      <template slot-scope="scope">
          	        <p><el-tag :type="scope.row.bankStatus == '0' ? 'danger' : 'success'" >{{ scope.row.bankStatus == 0 ? '未认证' : '已认证'}} </el-tag></p>
          	      </template>
          	    </el-table-column>
          	  <el-table-column label="注册时间" align="center" prop="addTime" >
          	    <template slot-scope="scope">
          	      <span>{{ scope.row.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
          	    </template>
          	  </el-table-column>
          </el-table>

          <pagination v-show="xiajiTotal>0" :total="xiajiTotal" :page.sync="xiajiListQuery.page" :limit.sync="xiajiListQuery.pageSize" @pagination="getXiajiList" />

        </el-dialog>


      <!--上级弹出框-->
      <el-dialog title="我的上级" :visible.sync="shangjiVisible">
        <el-table
          v-loading="shangjiListLoading"
          :data="shangjiList"
          border
          fit
          highlight-current-row
          style="width: 100%;"
        >
          <el-table-column label="推荐等级" prop="id" align="center" width="100" fixed="left">
            <template slot-scope="scope">
              <span>#{{ scope.row.level }}级</span>
            </template>
          </el-table-column>
        	<el-table-column label="用户名称" width="150px"  align="center" fixed="left">
        		  <template slot-scope="scope">
        	       <el-avatar shape="square" size="large" :src="scope.row.headImg"></el-avatar>
        				<br>
        		    <span class="link-type">{{ scope.row.code }}</span>
        				<br>
        				<span class="link-type">{{ scope.row.realname }}</span>
        		  </template>
        		</el-table-column>
        		<el-table-column label="会员组" width="100px" align="center">
        		  <template slot-scope="scope">
        		    <span>{{scope.row.groupName}}</span>
        		  </template>
        		</el-table-column>
        	  <el-table-column label="用户状态" width="100px" align="center">
        	    <template slot-scope="scope">
        	       <el-tag :type="scope.row.status == '-1' ? 'danger' : 'success' " >{{ scope.row.status | statusFilter}} </el-tag>
        	    </template>
        	  </el-table-column>
        	    <el-table-column label="实名认证" width="80px" align="center">
        	      <template slot-scope="scope">
        	        <p><el-tag :type="scope.row.realnameStatus == '0' ? 'danger' : 'success' " >{{ scope.row.realnameStatus == 0 ? '未认证' : '已认证'}} </el-tag></p>
        	      </template>
        	    </el-table-column>
        	    <el-table-column label="手机认证" width="80px" align="center">
        	      <template slot-scope="scope">
        	        <p><el-tag :type="scope.row.phoneStatus == '0' ? 'danger' : 'success'" >{{ scope.row.phoneStatus == 0 ? '未认证' : '已认证'}} </el-tag></p>
        	      </template>
        	    </el-table-column>
        	    <el-table-column label="银行认证" width="80px" align="center">
        	      <template slot-scope="scope">
        	        <p><el-tag :type="scope.row.bankStatus == '0' ? 'danger' : 'success'" >{{ scope.row.bankStatus == 0 ? '未认证' : '已认证'}} </el-tag></p>
        	      </template>
        	    </el-table-column>
        	  <el-table-column label="注册时间" align="center" prop="addTime" >
        	    <template slot-scope="scope">
        	      <span>{{ scope.row.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        	    </template>
        	  </el-table-column>
        </el-table>
      </el-dialog>

  </div>
</template>


<script>
import {getMemberList, getMember, updateMember, updateMemberAccount, getMemberGroupSelector, getZtList, getSjList} from '@/api/member'
import {getConfigList} from '@/api/params'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime, moneyFormat, deepClone  } from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

const statusOptions = [
  { key: '1', text: '正常' },
  { key: '-1', text: '停用' },
  { key: '-2', text: '注销' }
]

// arr to obj ,such as { CN : "China", US : "USA" }
const statusKeyValue = statusOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

export default {
  name: 'memberList',
  components: { Pagination },
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
        queryType: 'code'
      },
      dialogFormVisible: false,
      multipleSelection: [],
      groupOptions:[],
      xiajiVisible: false,
      shangjiVisible: false,
      xiajiList: null,
      xiajiTotal: 0,
      xiajiListLoading: true,
      xiajiListQuery: {
        page: 1,
        pageSize: 20
      },
      shangjiList: null,
      shangjiTotal: 0,
      shangjiListLoading: true,
      shangjiListQuery: {
        page: 1,
        pageSize: 20
      },
      config:{},
       downloadLoading:false,
    }
  },
  created() {
    this.getList()
    this.getConfig()
    this.getMemberGroupSelector()
  },
  methods: {
    getConfig(){
      this.listLoading = true
      getConfigList({}).then(response => {
        this.config = response.data
      })
    },
    getMemberGroupSelector(){
      getMemberGroupSelector().then(response => {
        this.groupOptions = response.data
      })
    },
    getList() {
      this.listLoading = true
			this.listQuery.dateFrom = renderTime(this.listQuery.startTime)
			this.listQuery.dateTo = renderTime(this.listQuery.endTime)
      let queryParams = Object.assign({}, this.listQuery) ;
      queryParams.keyword = null
      queryParams.code = null
      queryParams.phone = null
      queryParams.email = null
      queryParams.nickname = null
      queryParams.qq = null
      queryParams.registerIp = null
      queryParams[this.listQuery.queryType] = this.listQuery.keyword
      getMemberList(queryParams).then(response => {
        this.list = response.data.list
        this.total = response.data.total
				this.listLoading = false
      })
    },
    handleModifyStatus(row, status) {
			updateMember({id: row.id, status: status}).then(response => {
				const message = status ==  '1' ? '启用成功' : '停用成功';
				this.$message({
				  message: message,
				  type: 'success'
				})
				row.status = status
			})
    },
    handleXiaji(row) {
    	this.xiajiListLoading = true
    	this.xiajiListQuery.memberId = row.id
    	this.getXiajiList();
      this.xiajiVisible = true
    },
    getXiajiList(){
    	getZtList(this.xiajiListQuery).then(response => {
    	  this.xiajiList = response.data.list
    	  this.xiajiTotal = response.data.total
    		this.xiajiListLoading = false
    	})
    },
    handleShangji(row) {
    	this.shangjiListLoading = true
    	this.shangjiListQuery.memberId = row.id
    	this.getShangjiList();
      this.shangjiVisible = true
    },
    getShangjiList(){
    	getSjList(this.shangjiListQuery).then(response => {
    	  this.shangjiList = response.data
    		this.shangjiListLoading = false
    	})
    },
    async getDownloadList(query) {
     let list;
     await getMemberList(query).then(response => {
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
          this.listQuery.dateFrom = renderTime(this.listQuery.startTime)
          this.listQuery.dateTo = renderTime(this.listQuery.endTime)
          let query = Object.assign({}, this.listQuery) // copy obj
          query.page = 1
          query.pageSize = 10000

          query.keyword = null
          query.code = null
          query.phone = null
          query.email = null
          query.nickname = null
          query.qq = null
          query.registerIp = null
          query[this.listQuery.queryType] = this.listQuery.keyword
          this.getDownloadList(query).then(res=>{
             list = res;
             let table = this.$refs['table'];
             const tHeader = [];
             const filterVal = [];
             table.columns.forEach(item=>{
                 if(item.label == '用户名称'){
                   tHeader.push(item.label);
                   filterVal.push(item.property)

                   tHeader.push('姓名');
                   filterVal.push('realname')
                 }else if(item.label == '注册IP' || item.label == '最后登录IP' || item.label == '会员组'){
                   tHeader.push(item.label);
                   filterVal.push(item.property)
                 }
             })

             const data = this.formatJson(filterVal, list)
             excel.export_json_to_excel({
               header: tHeader,
               data,
               filename: '用户列表',
               autoWidth: true,
               bookType: 'xlsx'
             })
             this.downloadLoading = false
          });

         })
    },
    formatJson(filterVal, jsonData) {
      const data =  jsonData.map(v => filterVal.map((j, index) => {
           return v[j]
      }))
      /* let sums = this.getSummaries({columns:this.$refs['table'].columns, data:jsonData});
       data.push(sums); */
       return data;
    },
  }
}
</script>

<style scoped="scoped">
  ::v-deep .line {
    text-align: center;
  }
  #container {
      width: 40vw;
      height: 50vh;
    }
    #panel {
      position: absolute;
      background-color: white;
      max-height: 90%;
      overflow-y: auto;
      top: 30px;
      right: 0px;
      width: 280px;
  }
  .input-card {
     position: fixed;
     left: 14vw;
     top: 100px;
     z-index: 111;
     width: 400px;
     height: 80vh;
     overflow-y: scroll;
     background-color: #fff;
   }
   ::v-deep.amap-marker-label {
     position: absolute;
     z-index: 2;
     border: 0px solid #00f;
     background-color: #fff;
     white-space: nowrap;
     cursor: default;
     padding: 10px;
     font-size: 12px;
     line-height: 14px;
   }
  .amap-icon img {
       width: 25px;
       height: 34px;
   }
   .info{
       position: relative;
       top: 0;
       right: 0;
       min-width: 0;
   }
    ::v-deep::-webkit-scrollbar {
     width: 7px;
     height: 5px;
     border-radius:15px;
     -webkit-border-radius:  15px;
   }
    ::v-deep::-webkit-scrollbar-track-piece {
     background-color: #ffff;
     border-radius:15px;
     -webkit-border-radius:  15px;
   }
    ::v-deep::-webkit-scrollbar-thumb:vertical {
     height: 7px;
     background-color: rgba(144, 147, 153, 0.5);
     border-radius: 15px;
     -webkit-border-radius:  15px;
   }
    ::v-deep::-webkit-scrollbar-thumb:horizontal {
     width: 7px;
     background-color: rgba(144, 147, 153, 0.5);
     border-radius:  15px;
     -webkit-border-radius: 15px;
   }

</style>
