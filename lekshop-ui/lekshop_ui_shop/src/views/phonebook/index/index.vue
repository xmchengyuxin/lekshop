<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="listQuery.inviteCode" clearable placeholder="用户名" style="width: 200px;" class="filter-item" @keyup.enter.native="search" />
      <el-input v-model="listQuery.phoneId" clearable placeholder="手机唯一标识" style="width: 200px;" class="filter-item" @keyup.enter.native="search" />
      <el-input v-model="listQuery.phoneModel" clearable placeholder="手机型号" style="width: 200px;" class="filter-item" @keyup.enter.native="search" />
      <el-input v-model="listQuery.thisPhone" clearable placeholder="本机号码" style="width: 200px;" class="filter-item" @keyup.enter.native="search" />
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" circle @click="search" />
      <!-- <br>
      <el-button-group>
      	<el-button class="filter-item" type="info" :loading="downloadLoading" size="mini" icon="el-icon-download" @click="handleDownload">导出当前</el-button>
      </el-button-group> -->
      <br>
      <el-button-group v-if="isAdmin == 1">
          <el-button class="filter-item" size="mini" type="danger" icon="el-icon-delete" @click="handleDelete">删除</el-button>
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
      @selection-change="handleSelectionChange"
    >
    <el-table-column type="selection" width="55" align="center"></el-table-column>
      <el-table-column label="手机唯一标识" prop="phoneId" align="center" >
        <template slot-scope="scope">
          <span>{{ scope.row.phoneId }}</span>
        </template>
      </el-table-column>
      <el-table-column label="手机型号" prop="phoneModel" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.phoneModel }}</span>
        </template>
      </el-table-column>
      <el-table-column label="用户名" prop="inviteCode" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.inviteCode }}</span>
        </template>
      </el-table-column>
      <el-table-column label="获取时间" align="center" prop="addTime">
        <template slot-scope="scope">
          <span>{{ scope.row.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
	  <!-- <el-table-column label="当前定位" align="center" prop="addBy">
	    <template slot-scope="scope">
	      <a v-if="scope.row.addBy" class="link-type" @click="viewMap(scope.row.addBy)">{{ scope.row.addBy }}</a>
	    </template>
	  </el-table-column> -->
      <el-table-column :label="$t('table.actions')" class-name="small-padding" fixed="right" min-width="120">
        <template slot-scope="scope">
          <router-link :to="'/member/phonebook/viewLog/'+scope.row.id">
            <el-button type="info" icon="el-icon-view" size="mini">查看通讯录</el-button>
          </router-link>
          <!-- <router-link :to="'/system/phonebook/viewMsg/'+scope.row.id">
            <el-button type="info" icon="el-icon-view" size="mini">查看短信</el-button>
          </router-link> -->
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />

    <el-dialog title="查看定位地址" :visible.sync="dialogFormVisible">
            <div id="container" ref="container"></div>
            <div id="panel" ref="panel"></div>
        <div style="text-align:right;">
          <el-button type="danger" @click="dialogFormVisible=false">取消</el-button>
        </div>
    </el-dialog>

  </div>
</template>

<script>
import { getPhonebookList, deletePhonebook } from '@/api/phonebook'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime, moneyFormat } from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

var map;
var placeSearch ;
var circle;
var circleEditor;
export default {
  name: 'phonebook',
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
      dialogFormVisible: false,
      isAdmin : this.$store.state.user.name == '超管'
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      getPhonebookList(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.total
        this.listLoading = false
      })
    },
    search() {
      this.getList()
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
    	    deletePhonebook(values.join(',')).then(() => {
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
    viewMap(latlng) {
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.initMap(latlng.split(",")[1], latlng.split(",")[0])
      })
    },
    initMap(lng, lat){
      const self  = this;
      map = new AMap.Map(self.$refs['container'], {
        center: [lng, lat],
        zoom: 8,
        resizeEnable: true
      });
      var viaMarker = new AMap.Marker({
           position: new AMap.LngLat(lng, lat),
           // 将一张图片的地址设置为 icon
           // 设置了 icon 以后，设置 icon 的偏移量，以 icon 的 [center bottom] 为原点
           offset: new AMap.Pixel(-13, -30)
       });
       map.add([viaMarker]);
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

   .image-preview-action {
       position: absolute;
       width: 100%;
       height: 100%;
       left: 0;
       top: 0;
       cursor: default;
       text-align: center;
       color: #fff;
       font-size: 15px;
       background-color: rgba(0, 0, 0, .5);
       transition: opacity .3s;
       cursor: pointer;
       text-align: center;
       line-height: 200px;
       .el-icon-delete {
         font-size: 36px;
       }
     }

</style>
