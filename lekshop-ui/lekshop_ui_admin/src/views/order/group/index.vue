<template>
  <div class="app-container">
		<el-tabs v-model="activeName" @tab-click="handleClickTab">
			  <el-tab-pane label="全部" name="first"></el-tab-pane>
				<el-tab-pane label="进行中" name="second"></el-tab-pane>
				<el-tab-pane label="成功" name="third"></el-tab-pane>
				<el-tab-pane label="失败" name="fourth"></el-tab-pane>
		</el-tabs>
    <div class="filter-container">
       <el-input v-model="listQuery.shopName" clearable placeholder="卖家" style="width: 200px;" class="filter-item" @keyup.enter.native="getList" />
			 <el-date-picker class="filter-item" v-model="listQuery.startTime" type="datetime" format="yyyy-MM-dd HH:mm:ss" placeholder="选择开始时间" />
			 -
			 <el-date-picker  class="filter-item"  v-model="listQuery.endTime" type="datetime" format="yyyy-MM-dd HH:mm:ss" placeholder="选择结束时间" />
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
			@selection-change="handleSelectionChange"
    >
			<el-table-column type="selection" width="55" align="center" ></el-table-column>
      <el-table-column label="店铺名" prop="shopName" align="center" width="130">
        <template slot-scope="scope">
          <span>{{ scope.row.shopName }}</span>
        </template>
      </el-table-column>
			<el-table-column label="所属订单" prop="orderNo" align="center" width="130">
			  <template slot-scope="scope">
			    <span>{{ scope.row.orderNo }}</span>
			  </template>
			</el-table-column>
			<el-table-column label="团长" prop="memberName" align="center" width="100">
			  <template slot-scope="scope">
			    <span>{{ scope.row.memberName }}</span>
			  </template>
			</el-table-column>
			<el-table-column label="商品信息" prop="goodsInfo" width="250">
			  <template slot-scope="scope">
          <div  class="flex  padding-tb10">
            <el-image
            class="flex f-s-0 margin-r12"
                style="height: 50px"
                :src="scope.row.goodsMainImg"
                :preview-src-list="[scope.row.goodsMainImg]">
              </el-image>
              <div class="flex f-c">
                <span class="line1">{{scope.row.goodsName}}</span>
                <span>
                  <span style="font-size: 16px; color: #A94442;">¥{{ scope.row.groupPrice | moneyFormat}}</span>
                  &nbsp;
                  <span style="text-decoration: line-through;">¥{{ scope.row.originalPrice | moneyFormat}}</span>
                </span>
              </div>
          </div>
			  </template>
			</el-table-column>
			<el-table-column label="已团/拼团" width="170">
			  <template slot-scope="scope">
            <span>{{scope.row.haveGroupNum}}/{{scope.row.groupNum}}</span>
            <span v-if="scope.row.haveGroupNum < scope.row.groupNum">距离拼团成功还差{{scope.row.groupNum-scope.row.haveGroupNum}}人</span>
					  <el-progress style="width: 150px;" status="exception" :text-inside="true" :stroke-width="15" :percentage="parseInt((scope.row.haveGroupNum/scope.row.groupNum*100).toFixed(2))">
            </el-progress>
						<!-- <img v-for="item in scope.row.memberList" :src="item.memberHeadImg" class="avatar" style="height:50px;width:50px;border-radius: 50%;"> -->
			  </template>
			</el-table-column>
      <el-table-column label="成员" min-width="150" align="center">
        <template slot-scope="scope">
           <el-avatar shape="square" size="small" v-for="item in scope.row.memberList" :src="item.memberHeadImg"></el-avatar>
        </template>
      </el-table-column>
			<el-table-column label="参团时间" width="150"  align="center">
			  <template slot-scope="scope">
			    <span>{{ scope.row.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}')  }}</span>
			  </template>
			</el-table-column>
			<el-table-column label="到期时间" width="150"  align="center">
			  <template slot-scope="scope">
			    <span>{{ scope.row.endTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}')  }}</span>
			  </template>
			</el-table-column>
			<el-table-column label="状态" width="75"  align="center">
			  <template slot-scope="scope">
					<el-tag>{{ scope.row.status | statusFilter }}</el-tag>
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
import {getGroupList, getGroupMemberList} from '@/api/order'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime, moneyFormat} from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

const statusOptions = [
  { key: '1', text: '进行中' },
  { key: '2', text: '成功' },
  { key: '3', text: '失败' }
]

const statusKeyValue = statusOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

export default {
  name: 'groupList',
  components: { Pagination },
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
			multipleSelection: [],
			activeName:'first'
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
			this.listQuery.dateFrom = renderTime(this.listQuery.startTime)
			this.listQuery.dateTo = renderTime(this.listQuery.endTime)

      getGroupList(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.total
				this.listLoading = false
      })
    },
		handleSelectionChange(val) {
			this.multipleSelection = val;
     },
		handleClickTab(tab, event) {
			this.listQuery = {
			  page: 1,
			  pageSize: 20
			}
			this.activeName = tab.name
			if(tab.name == 'first'){
			}else if(tab.name == 'second'){
				this.listQuery.status = 1
			}else if(tab.name == 'third'){
				this.listQuery.status = 2
			}else if(tab.name == 'fourth'){
				this.listQuery.status = 3
			}
			this.getList()
		}


  }
}
</script>
