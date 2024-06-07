<template>
  <div class="app-container">
    <div class="filter-container">
			<el-input v-model="listQuery.verifyCode" clearable placeholder="核销码" style="width: 200px;" class="filter-item" @keyup.enter.native="getList" />
			<el-button v-waves class="filter-item" type="primary" icon="el-icon-search" circle @click="getList"></el-button>
      <br>
      <el-button-group>
      <el-button class="filter-item" size="mini" type="primary" icon="el-icon-full-screen" @click="handleCreate">核销</el-button>
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
    >
      <el-table-column label="核销码" align="center" >
        <template slot-scope="scope">
          <span class="red">{{ scope.row.verifyCode }}</span>
        </template>
      </el-table-column>
      <el-table-column label="订单号" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.orderNo }}</span>
        </template>
      </el-table-column>
      <el-table-column label="订单总价" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.buyTotalPrice | moneyFormat }}</span>
        </template>
      </el-table-column>
			<el-table-column label="买家" align="center">
			  <template slot-scope="scope">
			    <span>{{ scope.row.memberName }}</span>
			  </template>
			</el-table-column>
      <el-table-column label="核销人" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.accountName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="核销时间" align="center" prop="addTime">
        <template slot-scope="scope">
          <span>{{ scope.row.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />

		<!--核销-->
		<el-dialog title="核销" :visible.sync="dialogFormVisible" style="min-width: 800px;">
		    <el-form ref="dataForm" :rules="rules" :model="formData" label-width="80px" label-position="right" style="width: 400px; margin-left:50px;">
					<el-form-item label="核销码" prop="verifyCode">
					  <el-input v-model="formData.verifyCode" placeholder="请输入核销码" />
					</el-form-item>
		    </el-form>
		    <div style="text-align:right;">
		      <el-button type="danger" @click="dialogFormVisible=false">取消</el-button>
		      <el-button type="primary" @click="confirm">确定</el-button>
		    </div>
		</el-dialog>


  </div>
</template>

<script>
import {getWriteOffList, writeOff} from '@/api/order'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime } from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

export default {
  name: 'orderFinance',
  components: { Pagination },
	filters: {
		filterFun: function (value) {
		if(value && value.length > 20) {
		 value= value.substring(0,20)+ '...';
		}
		 return value;
		},
	},
  directives: { waves },
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
      rules: {
        verifyCode: [{ required: true, message: '核销码不能为空', trigger: 'blur' }],
      },
      formData: {},
      dialogFormVisible: false,
      dialogType: '',
			multipleSelection: [],
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      getWriteOffList(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.total
				this.listLoading = false
      })
    },
		handleCreate() {
		  this.dialogType = 'add'
		  this.dialogFormVisible = true
			this.formData = {}
			this.$nextTick(() => {
			  this.$refs['dataForm'].clearValidate()
			})
		},
		confirm() {
			this.$refs['dataForm'].validate((valid) => {
			  if (valid) {
			    let formData =Object.assign({}, this.formData)
			    writeOff(formData).then(() => {
						this.getList()
			      this.dialogFormVisible = false
			      this.$notify({
			        title: '成功',
			        message: '核销成功',
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
