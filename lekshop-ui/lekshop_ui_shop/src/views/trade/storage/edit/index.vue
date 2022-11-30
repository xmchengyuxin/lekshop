<template>
  <div class="app-container">
		<!-- <el-tabs v-model="activeName" @tab-click="handleClickTab">
			  <el-tab-pane label="全部" name="-1"></el-tab-pane>
				<el-tab-pane label="待审核" name="0"></el-tab-pane>
				<el-tab-pane label="已完成" name="1"></el-tab-pane>
				<el-tab-pane label="已驳回" name="2"></el-tab-pane>
        <el-tab-pane label="已取消" name="3"></el-tab-pane>
		</el-tabs> -->
    <div class="filter-container">
			 <el-input v-model="listQuery.goodsName" clearable placeholder="商品名称" style="width: 200px;" class="filter-item" @keyup.enter.native="getList" />
			 <el-date-picker class="filter-item" v-model="listQuery.startTime" type="datetime" format="yyyy-MM-dd HH:mm:ss" placeholder="选择开始时间" />
			 -
			 <el-date-picker  class="filter-item"  v-model="listQuery.endTime" type="datetime" format="yyyy-MM-dd HH:mm:ss" placeholder="选择结束时间" />
			 <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" circle @click="getList"></el-button>
       <br/>
       <el-button-group>
         <el-button class="filter-item" type="primary" size="mini" icon="el-icon-edit" @click="handleEdit">修改库存
         </el-button>
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
      <el-table-column label="修改人" prop="addBy" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.addBy }}</span>
        </template>
      </el-table-column>
			<el-table-column label="修改商品" prop="goodsInfo" align="center">
			  <template slot-scope="scope">
          <span class="line2">{{scope.row.goodsName}}</span>
			  </template>
			</el-table-column>
      <el-table-column label="规格" prop="skuName" align="center">
        <template slot-scope="scope">
          <span class="line2">{{scope.row.skuName}}</span>
        </template>
      </el-table-column>
      <el-table-column label="修改货位" prop="spaceName" align="center">
        <template slot-scope="scope">
      		<span style="color:red">{{ scope.row.spaceName}}</span>
        </template>
      </el-table-column>
			<el-table-column label="修改数量" prop="editNum" align="center">
			  <template slot-scope="scope">
					<span style="color:red">{{ scope.row.editNum}}</span>
			  </template>
			</el-table-column>
			<el-table-column label="修改时间"  align="center">
			  <template slot-scope="scope">
			    <span>{{ scope.row.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}')  }}</span>
			  </template>
			</el-table-column>

			<!-- <el-table-column label="状态" align="center">
			  <template slot-scope="scope">
          <el-tag effect="plain" v-if="scope.row.status == 0" type="danger">{{ scope.row.status | statusFilter }}</el-tag>
          <el-tag effect="plain" v-if="scope.row.status == 1" type="warning">{{ scope.row.status | statusFilter }}</el-tag>
          <el-tag effect="plain" v-if="scope.row.status == 2" type="info">{{ scope.row.status | statusFilter }}</el-tag>
          <el-tag effect="plain" v-if="scope.row.status == 3" type="success">{{ scope.row.status | statusFilter }}</el-tag>
			  </template>
			</el-table-column> -->
			<el-table-column label="操作" class-name="small-padding" fixed="right" width="220">
			  <template slot-scope="scope">
          <el-tooltip class="item" effect="dark" content="查看详情" placement="top">
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handlestockEditDetail(scope.row)"></el-button>
          </el-tooltip>
          <!-- <el-popconfirm
            v-if="scope.row.status == 0"
            confirm-button-text='好的'
            cancel-button-text='不用了'
            icon="el-icon-info"
            icon-color="red"
            title="确定同意修改吗？"
            @confirm="agree(scope.row)"
          >
            <el-button slot="reference"  type="success" icon="el-icon-circle-check" size="mini">
              同意
            </el-button>
          </el-popconfirm>
          <el-button v-if="scope.row.status == 0" type="danger" icon="el-icon-close" size="mini" @click="handleVerify(scope.row)">
            拒绝
          </el-button> -->
			  </template>
			</el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />


		<el-dialog title="拒绝" :visible.sync="dialogFormVisible">
		    <el-form ref="verifyForm" :model="verifyForm" label-width="80px" label-position="left" style="width: 100%;">
					<el-form-item label="拒绝原因" prop="reason" :rules="[{ required: true, message: '请输入拒绝原因', trigger: 'blur' }]">
						<el-input :rows="4" type="textarea" v-model="verifyForm.reason" placeholder="请输入拒绝原因"></el-input>
					</el-form-item>
		    </el-form>
		    <div style="text-align:right;">
		      <el-button type="danger" @click="dialogFormVisible=false">取消</el-button>
		      <el-button type="primary" @click="refuse">确定</el-button>
		    </div>
		</el-dialog>

    <!--订单详情-->
    	<el-dialog title="详情" :visible.sync="dialogDetailVisible" width="60%">
        <el-card class="box-card-dialog" shadow="hover">
          <div slot="header" class="clearfix">
            <span>商品信息</span>
          </div>

          <el-table
              :data="stockEditDetailList"
              border
              style="width: 100%">
              <el-table-column label="品名"  align="center">
                <template slot-scope="scope">
                  <el-tooltip class="item" effect="dark" :content="scope.row.goodsName" placement="top">
                    <span class="line1">
                      {{ scope.row.goodsName }}</span>
                  </el-tooltip>
                </template>
              </el-table-column>
              <el-table-column label="规格"  align="center">
                <template slot-scope="scope">
                  <span>
                    {{ scope.row.skuName }}</span>
                </template>
              </el-table-column>
              <el-table-column label="仓库" align="center">
                <template slot-scope="scope">
                  {{ scope.row.storageName }}
                </template>
              </el-table-column>
              <el-table-column label="货位名称"  align="center">
                <template slot-scope="scope">
                  {{ scope.row.spaceName }}
                </template>
              </el-table-column>
              <el-table-column label="修改数量" prop="refundNum" align="center">
                <template slot-scope="scope">
                  <span>{{ scope.row.editNum}}</span>
                </template>
              </el-table-column>
            </el-table>
            <div style="margin-top: 10px;">
              <p style="height: 30px;">申请时间:  &nbsp;&nbsp;&nbsp;{{ stockEditDetail.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}')  }}</p>
            </div>
        </el-card>
        <br>
        <el-card class="box-card-dialog" shadow="hover">
          <div slot="header" class="clearfix">
            <span>申请记录</span>
          </div>
          <el-table
              :data="stockEditLogList"
              style="width: 100%">
              <el-table-column label="" prop="title" width="150">
                <template slot-scope="scope">
                  <span>{{ scope.row.title }}</span>
                </template>
              </el-table-column>
              <el-table-column label="" prop="remark">
                <template slot-scope="scope">
                  <span>{{ scope.row.remark }}</span>
                </template>
              </el-table-column>
              <el-table-column label="" prop="addBy">
                <template slot-scope="scope">
                  <span>{{ scope.row.addBy }}</span>
                </template>
              </el-table-column>
              <el-table-column label="" prop="addTime" align="center" width="150">
                <template slot-scope="scope">
                  <span>{{ scope.row.addTime | parseTime }}</span>
                </template>
              </el-table-column>
            </el-table>
        </el-card>
    	</el-dialog>



      <!--修改库存-->
      <el-dialog title="修改库存" :visible.sync="dialogEditVisible">
        <el-form ref="dataForm" :model="dataForm" label-width="90px" label-position="right"
          style="width: 100%;">
          <el-form-item label="选择仓库" prop="storageId" :rules="[{ required: true, message: '请选择仓库', trigger: 'blur' }]">
            <el-select @change="getStorageGoods(dataForm.storageId)" v-model="dataForm.storageId" style="width: 60%;" >
              <el-option v-for="item in storageOptions" :key="item.id" :label="item.name" :value="item.id" />
            </el-select>
          </el-form-item>
          <el-form-item label="选择商品" prop="skuId" :rules="[{ required: true, message: '请选择商品', trigger: 'blur' }]">
            <el-select @change="changeGoods" v-model="dataForm.skuId" style="width: 60%;" >
              <el-option v-for="item in goodsOptions" :key="item.skuId" :label="item.goodsName" :value="item.skuId">
                <span style="float: left">{{ item.goodsName }}</span>
                <span style="float: right; color: #8492a6; font-size: 13px">{{ item.skuName }}</span>
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="修改货位" prop="spaceName" :rules="[{ required: true, message: '请填写修改货位', trigger: 'blur' }]">
            <el-input v-model="dataForm.spaceName" placeholder="请填写修改货位" style="width: 60%;" />
          </el-form-item>
          <el-form-item label="修改数量" prop="editNum" :rules="[{ required: true, message: '请填写修改数量', trigger: 'blur' }]">
            <el-input v-model="dataForm.editNum" placeholder="请输入修改数量" style="width: 60%;" />
            <p class="tips">* 加库存使用正数, 减库存使用负数</p>
          </el-form-item>
        </el-form>

        <div style="text-align:right;">
          <el-button type="danger" @click="dialogEditVisible=false">取消</el-button>
          <el-button type="primary" @click="confirmEdit">确定</el-button>
        </div>
      </el-dialog>

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
    .box-card {
      position: relative;
    }
    .order-role {
      position: absolute;
      right: 0px;
      top: 0px;
      width: 20px;
      height: 20px;
      background-color: #E65D6E;
      color: #fff;
      border-radius: 5%;
    }
    .box-card-dialog {
      font-size: 13px;
    }
    .box-card-dialog .el-card__header {
      color: #333333;
      background-color: #fafafa;
      border-color: #ddd;
    }
    .miaosha{
      background-color: #ffaa00;
    }
</style>

<script>
import {stockEditGetDetail, stockEditGet, stockEditAdd, stockEditReject, stockEditVerifySus, storageGet, storageStockGet} from '@/api/trade'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime, moneyFormat} from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

const statusOptions = [
  { key: 0, text: '待审核' },
  { key: 1, text: '已通过' },
  { key: 2, text: '已驳回' },
  { key: 3, text: '已取消' }
]

const statusKeyValue = statusOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

export default {
  name: 'editStock',
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
      },
			multipleSelection: [],
			activeName:'-1',
			dialogFormVisible: false,
      dialogDetailVisible: false,
      dialogEditVisible: false,
			verifyForm:{},
      dataForm:{},
      stockEditDetail: {},
      stockEditLogList: [],
      stockEditDetailList: [],
      storageOptions: [],
      goodsOptions: []
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getStorageList(){
      storageGet({page: 1, pageSize: 9999}).then(response => {
        this.storageOptions = response.data.list
      })
    },
    getList() {
      this.listLoading = true
			this.listQuery.dateFrom = renderTime(this.listQuery.startTime)
			this.listQuery.dateTo = renderTime(this.listQuery.endTime)

      stockEditGet(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.total
				this.listLoading = false
      })
    },
    getStorageGoods(storageId){
      this.dataForm.goodsId = null
      this.goodsOptions = []
      storageStockGet({storageId: storageId}).then(response => {
        this.goodsOptions = response.data.list
      })
    },
    changeGoods(){
      let selectedGoods = this.goodsOptions.filter((item) => this.dataForm.skuId == item.skuId);
      // this.dataForm.spaceName = selectedGoods[0].spaceName
      this.$set(this.dataForm, 'spaceName', selectedGoods[0].spaceName)
      this.$set(this.dataForm, 'stockId', selectedGoods[0].id)
      this.$forceUpdate();
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
			if(tab.name == '-1'){
				this.listQuery.status = null
			}else {
				this.listQuery.status = tab.name
			}
			this.getList()
		},
		handleVerify(row){
			this.dialogFormVisible = true
			this.verifyForm = {stockEditId:row.id, type: this.getType(row)}
		},
    agree(row){
      stockEditVerifySus({stockEditId: row.id, type: this.getType(row)}).then((response) => {
        	this.getList()
          this.$notify({
            title: '成功',
            message: '您已同意',
            type: 'success',
            duration: 2000
          })
        })
    },
    refuse(){
      this.$refs['verifyForm'].validate((valid) => {
        if (valid) {
          stockEditReject(this.verifyForm).then((response) => {
            	this.getList()
              this.dialogFormVisible = false
              this.$notify({
                title: '成功',
                message: '您已拒绝',
                type: 'success',
                duration: 2000
              })
            })
        }
      })
    },
    getType(row){
      let type;
      if(row.managerStatus == 0){
        type = 1;
      }else if(row.financeStatus == 0){
        type = 2;
      }
      return type;
    },
    handlestockEditDetail(row){
      this.stockEditDetailList=[]
       stockEditGetDetail({id:row.id}).then((result) => {
         this.stockEditDetail = result.data.stockEdit;
         this.stockEditLogList = result.data.processList;
         this.stockEditDetailList.push(this.stockEditDetail)
       })
       this.dialogDetailVisible = true;
    },
    handleEdit() {
      this.dialogEditVisible = true
      this.dataForm = {}
      this.storageOptions = []
      this.getStorageList()
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    confirmEdit() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          stockEditAdd(this.dataForm).then(() => {
            this.getList()
            this.dialogEditVisible = false
            this.$notify({
              title: '成功',
              message: '保存成功',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    },
  }
}
</script>
