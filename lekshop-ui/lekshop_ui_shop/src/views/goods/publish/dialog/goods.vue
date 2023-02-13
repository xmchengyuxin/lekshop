<template>
  <el-dialog :styles="{ top: '120px', width: '1160px' }" width="1160" @close="clickClose"  :visible.sync="flag" :mask-closable="false" scrollable>
  <div class="app-container">
    <el-row class="tac">
      <el-col :span="5">
        <div style="border-right: 1px solid #f4f4f4;margin-right: 20px;  ">
          <div style="padding-right: 8px; text-align: right;">
            <el-button type="text" size="mini" @click="listQuery.cateId = null;getList()">
              全部
            </el-button>
            <el-button type="text" size="mini" @click="expandAll(true)">
              展开
            </el-button>
            <el-button type="text" size="mini" @click="expandAll(false)">
              折叠
            </el-button>
          </div>

          <el-tree ref="myTree" :data="cateList" @node-click="handleNodeClickCate" node-key="id" :expand-on-click-node="false"
            :default-expand-all="false" :indent="16" :props="cateProps" :highlight-current="true">
            </el-tree>

          <!-- <span class="mytree flex">
          	<el-tree ref="myTree" :data="cateList" @node-click="handleNodeClickCate" node-key="id"
          	  :expand-on-click-node="false" :default-expand-all="true" :props="cateProps"
          	  :highlight-current="true" :indent="0">
          	</el-tree>
          </span> -->

        </div>
      </el-col>

      <el-col :span="17" style="margin-left: 10px;">

    <div class="filter-container">
      <el-input v-model="listQuery.title" clearable placeholder="商品名称" style="width: 200px;" class="filter-item" @keyup.enter.native="getList()" />
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
			<!-- <el-table-column type="selection" width="45" align="center"></el-table-column> -->
      <el-table-column label="商品类目"  align="center">
        <template slot-scope="scope">
      		<span>{{ scope.row.cateName}}</span>
        </template>
      </el-table-column>
			<el-table-column label="商品标题"  align="center">
			  <template slot-scope="scope">
          <el-tooltip class="item" effect="dark" :content="scope.row.title" placement="top">
            <span class="line1">
              {{ scope.row.title }}</span>
          </el-tooltip>
			  </template>
			</el-table-column>
			<el-table-column label="商品主图" width="80px"  align="center">
			  <template slot-scope="scope">
			    <el-image
			        v-if="scope.row.mainImg"
			        style="height: 30px"
			        :src="scope.row.mainImg"
			        :preview-src-list="[scope.row.mainImg]">
			      </el-image>
			  </template>
			</el-table-column>
      <el-table-column label="售价"  align="center">
        <template slot-scope="scope">
      		 <span>{{ scope.row.price | moneyFormat }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.actions')" class-name="small-padding" align="center">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="clickOK(scope.row)">选择</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />


      </el-col>
    </el-row>
  </div>

  <div slot="footer" class="dialog-footer">
      <el-button @click="clickClose" size="mini">取 消</el-button>
      <!-- <el-button type="primary" size="mini" @click="clickOK">确 定</el-button> -->
    </div>
  </el-dialog>
</template>

<script>
import {getPlatformGoodsList, getGoodsCateList } from '@/api/goods'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime } from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

const typeOptions = [
  { key: 1, text: '普通' },
  { key: 2, text: '秒杀' },
  { key: 3, text: '拼团' },
]

// arr to obj ,such as { CN : "China", US : "USA" }
const typeKeyValue = typeOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

const statusOptions = [
  { key: 1, text: '上架' },
  { key: 2, text: '仓库' },
]

// arr to obj ,such as { CN : "China", US : "USA" }
const statusKeyValue = statusOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

export default {
  name: 'tsGoods',
  components: { Pagination },
  directives: { waves },
	filters: {
	  typeFilter(status) {
	    return typeKeyValue[status]
	  },
    statusFilter(type) {
	    return statusKeyValue[type]
	  },
	},
  data() {
    return {
      cateProps: {
        children: 'children',
        label: 'name',
        value: 'id',
        checkStrictly: true
      },
      tableKey: 0,
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        pageSize: 20,
        status: 1
      },
      dialogFormVisible: false,
      dialogType: '',
			multipleSelection: [],
			typeOptions,
			activeName: 'second',
      goodsOptions:[],
      shopCateOptions:[],
      shopOptions: [],
      defaultActiveId: null,
      cateList: [],
      flag: true,
    }
  },
  props: {
  },
  created() {
    this.getCateList()
    this.getList()
  },
  methods: {
    getCateList(){
      getGoodsCateList().then(response => {
        this.cateList = response.data
      })
    },
    getList() {
      this.listLoading = true
      getPlatformGoodsList(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.total
				this.listLoading = false
      })
    },
		handleSelectionChange(val) {
			this.multipleSelection = val;
     },
     // 展开/折叠
     expandAll(isExpand) {
       for (var i = 0; i < this.$refs.myTree.store._getAllNodes().length; i++) {
         // 根据isExpand， tree展开或折叠
         this.$refs.myTree.store._getAllNodes()[i].expanded = isExpand
       }
     },
     handleNodeClickCate(data) {
       this.listQuery.cateId = data.id
       this.getList();
     },
     // 点击确认
     clickOK(goods) {
       this.$emit("selected", goods.id);
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
