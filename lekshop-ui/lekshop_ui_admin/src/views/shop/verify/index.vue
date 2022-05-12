<template>
  <div class="app-container">
    <el-tabs v-model="locationActiveName" @tab-click="handleClickLocationTab">
     	  <el-tab-pane label="全部" name="first"></el-tab-pane>
     		<el-tab-pane label="已提交资质" name="second"></el-tab-pane>
        <el-tab-pane label="完善店铺" name="third"></el-tab-pane>
        <el-tab-pane label="审核中" name="four"></el-tab-pane>
        <el-tab-pane label="通过" name="five"></el-tab-pane>
        <el-tab-pane label="未通过" name="six"></el-tab-pane>
     </el-tabs>
    <div class="filter-container">
      <el-input v-model="listQuery.name" clearable placeholder="店铺名称" style="width: 200px;" class="filter-item" @keyup.enter.native="getList" />
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" circle @click="getList"></el-button>
      <br>
			<el-button-group>
			<el-button class="filter-item" size="mini" type="danger" icon="el-icon-delete" @click="handleDelete">删除</el-button>
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
			<el-table-column type="selection" width="55" align="center"></el-table-column>
			<el-table-column label="企业名/姓名" align="center">
			  <template slot-scope="scope">
         <span>{{ scope.row.realname }}</span>
			  </template>
			</el-table-column>
			<el-table-column label="证件"  align="center">
			  <template slot-scope="scope">
          <el-image
              style="height: 30px"
              :src="scope.row.cardImg"
              :preview-src-list="[scope.row.cardImg]">
            </el-image>
			  </template>
			</el-table-column>
      <el-table-column label="店铺类型"  align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.type == 2" style="color: red;">{{ scope.row.type | typeFilter }}</span>
          <span v-if="scope.row.type == 3" style="color: green;">{{ scope.row.type | typeFilter }}</span>
          <span v-else>{{ scope.row.type | typeFilter }}</span>
        </template>
      </el-table-column>
      <el-table-column label="店铺名称"  align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="店铺头像"  align="center">
        <template slot-scope="scope">
          <el-image
              style="height: 30px"
              :src="scope.row.logo"
              :preview-src-list="[scope.row.logo]">
            </el-image>
        </template>
      </el-table-column>
      <el-table-column label="统一信用/身份证号"  align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.idcard }}</span>
        </template>
      </el-table-column>
      <el-table-column label="手机号"  align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.phone }}</span>
        </template>
      </el-table-column>
      <el-table-column label="微信号"  align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.weixin }}</span>
        </template>
      </el-table-column>
      <el-table-column label="审核状态"  align="center">
        <template slot-scope="scope">
          <!-- 0已提交资质>>1完善店铺>>2审核中>>3通过>>4驳回 -->
          <el-tag v-if="scope.row.status == 0" >{{ scope.row.status | statusFilter}}</el-tag>
          <el-tag v-if="scope.row.status == 1" type="info">{{ scope.row.status | statusFilter}}</el-tag>
          <el-tag v-if="scope.row.status == 2" type="warning">{{ scope.row.status | statusFilter}}</el-tag>
          <el-tag v-if="scope.row.status == 3" type="success">{{ scope.row.status | statusFilter}}</el-tag>
          <el-tag v-if="scope.row.status == 4" type="danger">{{ scope.row.status | statusFilter}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.actions')" class-name="small-padding" fixed="right" min-width="130">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-view" size="mini" @click="handleView(scope.row)">查看</el-button>
          <el-button v-if="scope.row.status == 2" type="primary" icon="el-icon-circle-check" size="mini" @click="handleVerify(scope.row)">审核</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />

		<!--添加编辑框-->
		<el-dialog title="详情" :visible.sync="dialogFormVisible">
		    <el-descriptions title="" :column="1" border>
		      <el-descriptions-item label="店铺类型">
          <span v-if="shopInfo.type == 2" style="color: red;">{{ shopInfo.type | typeFilter }}</span>
          <span v-if="shopInfo.type == 3" style="color: green;">{{ shopInfo.type | typeFilter }}</span>
          <span v-else>{{ shopInfo.type | typeFilter }}</span>
          </el-descriptions-item>
		      <el-descriptions-item label="企业名/姓名">{{shopInfo.realname}}</el-descriptions-item>
		      <el-descriptions-item label="统一信用/身份证号" >{{shopInfo.idcard}}</el-descriptions-item>
		      <el-descriptions-item label="证件">
		        <el-image
		            style="height: 30px"
		            :src="shopInfo.cardImg"
		            :preview-src-list="[shopInfo.cardImg]">
		          </el-image>
		      </el-descriptions-item>
          <el-descriptions-item label="商标证书" v-if="shopInfo.brandImg">
            <el-image
                style="height: 30px"
                :src="shopInfo.brandImg"
                :preview-src-list="[shopInfo.brandImg]">
              </el-image>
          </el-descriptions-item>
		      <el-descriptions-item label="手机号" >{{shopInfo.phone}}</el-descriptions-item>
          <el-descriptions-item label="微信号" >{{shopInfo.weixin}}</el-descriptions-item>
          <el-descriptions-item label="店铺名称" >{{shopInfo.name}}</el-descriptions-item>
          <el-descriptions-item label="店铺头像" >
            <el-image
		            style="height: 30px"
		            :src="shopInfo.logo"
		            :preview-src-list="[shopInfo.logo]">
		          </el-image></el-descriptions-item>
          <el-descriptions-item label="店铺简介" >{{shopInfo.remark}}</el-descriptions-item>
          <el-descriptions-item label="店铺描述" >{{shopInfo.produce}}</el-descriptions-item>
          <el-descriptions-item label="省市" >{{shopInfo.province}}{{shopInfo.city}}{{shopInfo.area}}</el-descriptions-item>
          <el-descriptions-item label="状态" >
          <el-tag v-if="shopInfo.status == 0" >{{ shopInfo.status | statusFilter}}</el-tag>
          <el-tag v-if="shopInfo.status == 1" type="info">{{ shopInfo.status | statusFilter}}</el-tag>
          <el-tag v-if="shopInfo.status == 2" type="warning">{{ shopInfo.status | statusFilter}}</el-tag>
          <el-tag v-if="shopInfo.status == 3" type="success">{{ shopInfo.status | statusFilter}}</el-tag>
          <el-tag v-if="shopInfo.status == 4" type="danger">{{ shopInfo.status | statusFilter}}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="驳回原因" v-if="shopInfo.reason">{{shopInfo.reason}}</el-descriptions-item>
		    </el-descriptions>
        <br/>
		    <div style="text-align:right;">
		      <el-button type="danger" @click="dialogFormVisible=false">取消</el-button>
		    </div>
		</el-dialog>

    <!--审核框-->
    <el-dialog title="审核" :visible.sync="dialogVerifyVisible">
        <el-form ref="dataForm" :rules="rules" :model="verifyForm" label-width="80px" label-position="right">
    			<el-form-item label="审核结果" prop="status">
    				 <el-radio-group v-model="verifyForm.status">
    				  <el-radio :label="3" border>通过</el-radio>
    				  <el-radio :label="4" border>不通过</el-radio>
    				</el-radio-group>
    			</el-form-item>
    			<el-divider>拒绝原因</el-divider>
    			<el-form-item label="拒绝原因" prop="reasonCheck">
    			  <el-select v-model="verifyForm.reasonCheck" placeholder="请选择" style="width: 100%;" @change="$set(verifyForm,'remark',verifyForm.reasonCheck);" clearable >
    			      <el-option
    			        key="0"
    			        value="企业名填写错误"
    			        label="企业名填写错误">
    			      </el-option>
                <el-option
                  key="1"
                  value="统一信用填写错误"
                  label="统一信用填写错误">
                </el-option>
                <el-option
                  key="2"
                  value="上传图片不清晰"
                  label="上传图片不清晰">
                </el-option>
                <el-option
                  key="3"
                  value="上传图片违规"
                  label="上传图片违规">
                </el-option>
    			      <el-option
    			        key="10"
    			        value="其他"
    			        label="其他">
    			      </el-option>
    			    </el-select>
    			</el-form-item>
    			<el-form-item label="填写原因" prop="remark">
    			  <el-input type="textarea" v-model="verifyForm.remark" placeholder="请输入拒绝理由" :rows="6"/>
    			</el-form-item>
        </el-form>
        <div style="text-align:right;">
          <el-button type="danger" @click="dialogVerifyVisible=false">取消</el-button>
          <el-button type="primary" @click="confirm">确定</el-button>
        </div>
    </el-dialog>

  </div>
</template>

<script>
import {getShopInfoList, verifyShopInfo, deleteShopInfo} from '@/api/shop'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime, moneyFormat } from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

const statusOptions = [
  { key: 0, text: '已提交资质' },
  { key: 1, text: '完善店铺' },
  { key: 2, text: '审核中' },
  { key: 3, text: '通过' },
  { key: 4, text: '驳回' }
]

// arr to obj ,such as { CN : "China", US : "USA" }
const statusKeyValue = statusOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})


const typeOptions = [
  { key: 1, text: '个人' },
  { key: 2, text: '企业' },
  { key: 3, text: '旗舰' },
]

// arr to obj ,such as { CN : "China", US : "USA" }
const typeKeyValue = typeOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

export default {
  name: 'verifyShop',
  components: { Pagination },
  directives: { waves },
  filters: {
    statusFilter(status) {
      return statusKeyValue[status]
    },
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
        pageSize: 20,
      },
      shopInfo: {},
      dialogFormVisible: false,
      dialogType: '',
			multipleSelection: [],
      locationActiveName:'first',
      dialogVerifyVisible: false,
      verifyForm: {},
      rules: {
      	status: [{ required: true, message: '状态不能为空', trigger: 'change' }]
      },
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      let queryParams = this.listQuery
      getShopInfoList(queryParams).then(response => {
        this.list = response.data.list
        this.total = response.data.total
				this.listLoading = false
      })
    },
		handleView(row) {
		  this.dialogFormVisible = true
     this.shopInfo = Object.assign({}, row)
		},
		handleVerify(row) {
			let id = ''
			if(row && row.id){
				id = row.id
			}else{
				const verVals = this.multipleSelection;
				if(!verVals || verVals.length <= 0){
					this.$message({
					  message: '请至少选择一条数据',
					  type: 'error',
						duration: 2000
					})
					return;
				}
				let values = [];
				verVals.forEach(val =>{
					values.push(val.id);
				})
				id = values.join(",")
			}
		  this.verifyForm = {status:3, id: id}// copy obj
		  this.dialogVerifyVisible = true
		  this.$nextTick(() => {
		    this.$refs['dataForm'].clearValidate()
		  })
		},
		confirm() {
			this.$refs['dataForm'].validate((valid) => {
			  if (valid) {
			    let formData = {
            shopInfoId: this.verifyForm.id,
            status: this.verifyForm.status,
            reason: this.verifyForm.remark
          }
			    verifyShopInfo(formData).then(() => {
						this.getList()
			      this.dialogVerifyVisible = false
			      this.$notify({
			        title: '成功',
			        message: '审核成功',
			        type: 'success',
			        duration: 2000
			      })
			    })
			  }
			})
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
			    deleteShopInfo(values.join(',')).then(() => {
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
    statusChange(row) {
      let formData = {
    		status: row.status,
    		id: row.id
    	}
    	updateShopService(formData).then(() => {
    	  this.$notify({
    	    title: '成功',
    	    message: '修改成功',
    	    type: 'success',
    	    duration: 2000
    	  })
        this.getList()
    	})
    },
    handleClickLocationTab(tab, event) {
    	this.listQuery.page = 1
    	this.listQuery.pageSize = 20
    	this.locationActiveName = tab.name
    	if(tab.name == 'first'){
    		this.listQuery.status = null
    	}else if(tab.name == 'second'){
    		this.listQuery.status = 0
    	}else if(tab.name == 'third'){
    		this.listQuery.status = 1
    	}else if(tab.name == 'four'){
    		this.listQuery.status = 2
    	}else if(tab.name == 'five'){
    		this.listQuery.status = 3
    	}else if(tab.name == 'six'){
    		this.listQuery.status = 4
    	}
    	this.getList()
    },

  }
}
</script>
