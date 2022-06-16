<template>
  <div class="app-container">
    <div class="filter-container">
      <el-select v-model="listQuery.cateId" placeholder="文章分类" filterable clearable class="filter-item">
         <el-option v-for="item in cateOptions" :key="item.id" :label="item.name" :value="item.id">
         </el-option>
       </el-select>
       <el-input v-model="listQuery.title" clearable placeholder="标题" style="width: 200px;" class="filter-item" @keyup.enter.native="getList" />
       <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" circle @click="getList"></el-button>
       <el-pagination
       class="filter-item"
         small
         layout="prev, pager, next"
         :total="total"
         :current-page.sync="listQuery.page"
         :page-size.sync="listQuery.pageSize"
         @current-change="getList"
         >
       </el-pagination>
  	</div>
  	<el-table
      :key="tableKey"
      v-loading="listLoading"
      :data="list"
      fit
      highlight-current-row
      style="width: 100%;"
    >
      <el-table-column label="文章分类" width="100px" prop="cateName" >
        <template slot-scope="scope">
          <span>{{ scope.row.cateName}}</span>
        </template>
      </el-table-column>
      <el-table-column label="标题" min-width="300px" prop="title" >
        <template slot-scope="scope">
            <span class="link-type">{{ scope.row.title }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.actions')" class-name="small-padding" width="90px"  fixed="right">
        <template slot-scope="scope">
          <el-button v-if="scope.row.checked" type="primary" size="mini" icon="el-icon-check">
            <span>已选</span>
          </el-button>
  			  <el-button v-else type="primary" size="mini" plain @click="callbackArticle(scope.row)">
            <span>选择</span>
  			  </el-button>
  			</template>
      </el-table-column>
    </el-table>

    <!-- <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" /> -->

  </div>

</template>
<script>
  import {getArticleList, getArticleCateSelector} from '@/api/article'
  import waves from '@/directive/waves' // Waves directive
  import { parseTime, renderTime } from '@/utils'
  import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

  const statusOptions = [
    { key: 1, text: '显示' },
    { key: 0, text: '草稿' }
  ]

  // arr to obj ,such as { CN : "China", US : "USA" }
  const statusKeyValue = statusOptions.reduce((acc, cur) => {
    acc[cur.key] = cur.text
    return acc
  }, {})

export default {
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
        pageSize: 20,
        type: 1
      },
    	multipleSelection: [],
    	statusOptions,
      cateOptions:[],
      activeName:'first',
    }
  },
  created() {
    this.getArticleCateSelector()
    this.getList()
  },
  methods: {
    callbackArticle(val) {
      this.list.forEach(item=>{
        item.checked = false;
      })
      val.checked = true;
      val.___type = "pages";

      val.___path = "/pages/passport/article";

      this.$emit("selected", [val]);
    },
    getArticleCateSelector(){
      getArticleCateSelector().then(response => {
        this.cateOptions = response.data
      })
    },
    getList() {
      this.listLoading = true
      getArticleList(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.total
    		this.listLoading = false
      })
    },
    handleSelectionChange(val) {
    	this.multipleSelection = val;
     },
  },
};
</script>
<style lang="scss" scoped>
::v-deep .ivu-card-body {
  height: 414px;
  overflow: auto;
}
.ivu-table-wrapper ivu-table-wrapper-with-border {
  height: 300px !important;
}
.list {
  margin: 0 1.5%;
  height: 400px;
  overflow: auto;
  > .list-item {
    padding: 10px;
    transition: 0.35s;
    cursor: pointer;
  }
  .list-item:hover {
    background: #ededed;
  }
}
.list {
  flex: 2;
  width: auto;
}
.content {
  overflow: hidden;
  flex: 8;
  height: 431px;
}
.active {
  background: #ededed;
}
.wrapper {
  height: 416px;
  overflow: hidden;
}
::v-deep .ivu-table {
  height: 300px !important;
  overflow: auto;
}
::v-deep .ivu-card-body {
  padding: 0;
  height: auto;
}
</style>
