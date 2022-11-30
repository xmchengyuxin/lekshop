<template>
  <div class="app-container">
    <div class="filter-container">
      <el-button-group>
      <el-button class="filter-item" size="mini" type="primary" icon="el-icon-plus" @click="handleCreate">注册</el-button>
      </el-button-group>
    </div>
    <el-table ref="table" :key="tableKey" v-loading="listLoading" :data="list" border fit highlight-current-row
      style="width: 100%;"
      @row-click="rowClick"
      >
      <el-table-column type="selection" align="center" width="55" />
      <el-table-column label="账号名称" width="200" align="center" fixed="left" prop="code">
        <template slot-scope="scope">
            <router-link :to="'/org/accountDetail?accountId='+scope.row.id">
            <span class="link-type">{{ scope.row.username }}</span>
          </router-link>
        </template>
      </el-table-column>
      <el-table-column label="姓名" width="150" align="center" prop="code">
        <template slot-scope="scope">
            <span class="link-type">{{ scope.row.realname }}</span>
          </router-link>
        </template>
      </el-table-column>
      <el-table-column label="状态" width="150" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.status == '1'"
            style="font-size: 17px; color: rgb(64, 158, 255); font-weight: bold;">正常</span>
          <span v-else style="font-size: 17px; color: rgb(245, 108, 108); font-weight: bold;">停用</span>
          <el-button v-if="scope.row.status=='1'" size="mini" type="danger" @click="handleModifyStatus(scope.row,'-1')">
            停用
          </el-button>
          <el-button v-if="scope.row.status=='-1'" size="mini" type="success"
            @click="handleModifyStatus(scope.row,'1')">
            启用
          </el-button>
        </template>
      </el-table-column>
      <el-table-column label="当前角色" width="200" align="center" prop="deptName">
        <template slot-scope="scope">
          <span>{{ scope.row.deptName}}-{{scope.row.roleName}}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" width="200px">
        <template slot-scope="scope">
          <span>{{scope.row.remark}}</span>
        </template>
      </el-table-column>
      <el-table-column label="注册时间" width="150" align="center" prop="addTime">
        <template slot-scope="scope">
          <span>{{ scope.row.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="注册IP" width="150" align="center" prop="registerIp">
        <template slot-scope="scope">
          <span>{{ scope.row.registerIp}}</span>
        </template>
      </el-table-column>
      <el-table-column label="最后登录IP" width="150" align="center" prop="lastLoginIp">
        <template slot-scope="scope">
          <span>{{ scope.row.lastLoginIp}}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.actions')" class-name="small-padding" min-width="100" fixed="right">
        <template slot-scope="scope">
            <router-link :to="'/org/accountDetail?accountId='+scope.row.id">
            <el-button style="display: block;margin-top: 5px; margin-left: 5px;" icon="el-icon-view" size="mini"
              type="primary">
              详情
            </el-button>
          </router-link>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize"
      @pagination="getList" />

  <!--注册框-->
  <el-dialog title="注册" :visible.sync="dialogFormVisible" style="min-width: 800px;">
      <el-form ref="dataForm" :rules="rules" :model="registerForm" label-width="80px" label-position="right">
  			<el-form-item label="姓名" prop="realname">
  			  <el-input v-model="registerForm.realname" placeholder="请输入姓名" />
  			</el-form-item>
  			<el-form-item label="账号" prop="username">
          <el-input v-model="registerForm.username" placeholder="请输入账号" />
          <p class="tips">* 账号前面默认会添加"主账号:", 如"13444555666:客服小美"</p>
        </el-form-item>
        <el-form-item label="手机" prop="phone">
          <el-input v-model="registerForm.phone" placeholder="请输入手机" />
        </el-form-item>
  			<el-form-item label="密码" prop="password">
  			  <el-input v-model="registerForm.password" placeholder="请输入密码" type="password"/>
  			</el-form-item>
      </el-form>
      <div style="text-align:right;">
        <el-button type="danger" @click="dialogFormVisible=false">取消</el-button>
        <el-button type="primary" @click="register">确定</el-button>
      </div>
  </el-dialog>


  </div>
</template>


<script>
  import {
    getShopAccountList,
    getShopAccountDetail,
    updateShopAccount,
    register,
    getDeptList
  } from '@/api/org'
  import waves from '@/directive/waves' // Waves directive
  import {
    parseTime,
    renderTime,
    moneyFormat,
    deepClone
  } from '@/utils'
  import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

  const statusOptions = [{
      key: '1',
      text: '正常'
    },
    {
      key: '-1',
      text: '停用'
    },
    {
      key: '-2',
      text: '注销'
    }
  ]

  // arr to obj ,such as { CN : "China", US : "USA" }
  const statusKeyValue = statusOptions.reduce((acc, cur) => {
    acc[cur.key] = cur.text
    return acc
  }, {})

  export default {
    name: 'accountList',
    components: {
      Pagination
    },
    directives: {
      waves
    },
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
          queryType: 'username'
        },
        dialogFormVisible: false,
        multipleSelection: [],
        downloadLoading: false,
        rules: {
          username: [{ required: true, message: '用户名不能为空', trigger: 'blur' }],
          password: [{ required: true, message: '密码不能为空', trigger: 'blur' }],
        	realname: [{ required: true, message: '姓名不能为空', trigger: 'change' }],
          phone: [{ required: true, message: '手机不能为空', trigger: 'blur' }],
        },
        registerForm:{},
        deptProps: {
          children: 'children',
          label: 'deptName',
          value: 'id',
        },
        deptList: [],

      }
    },
    created() {
      this.getList()
      this.getDeptList()
    },
    methods: {
      async getDeptList() {
        getDeptList().then(response => {
          this.deptList = response.data
        })
      },
      getList() {
        this.listLoading = true
        this.listQuery.dateFrom = renderTime(this.listQuery.startTime)
        this.listQuery.dateTo = renderTime(this.listQuery.endTime)
        let queryParams = Object.assign({}, this.listQuery);
        queryParams.keyword = null
        queryParams.username = null
        queryParams.phone = null
        queryParams.email = null
        queryParams.nickname = null
        queryParams.realname = null
        queryParams[this.listQuery.queryType] = this.listQuery.keyword
        getShopAccountList(queryParams).then(response => {
          this.list = response.data.list
          this.total = response.data.total
          this.listLoading = false
        })
      },
      handleModifyStatus(row, status) {
        updateShopAccount({
          id: row.id,
          status: status
        }).then(response => {
          const message = status == '1' ? '启用成功' : '停用成功';
          this.$message({
            message: message,
            type: 'success'
          })
          row.status = status
        })
      },
      rowClick(row){
        this.$router.push('/org/accountDetail?accountId='+row.id)
      },
      async getDownloadList(query) {
        let list;
        await getShopAccountList(query).then(response => {
          list = response.data.list
        })
        if (!list || list.length <= 0) {
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
          this.getDownloadList(query).then(res => {
            list = res;
            let table = this.$refs['table'];
            const tHeader = [];
            const filterVal = [];
            table.columns.forEach(item => {
              if (item.label == '用户名称') {
                tHeader.push(item.label);
                filterVal.push(item.property)

                tHeader.push('姓名');
                filterVal.push('realname')
              } else if (item.label == '注册IP' || item.label == '最后登录IP' || item.label == '会员组') {
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
        const data = jsonData.map(v => filterVal.map((j, index) => {
          return v[j]
        }))
        /* let sums = this.getSummaries({columns:this.$refs['table'].columns, data:jsonData});
         data.push(sums); */
        return data;
      },
      handleCreate() {
        this.dialogStatus = 'add'
        this.dialogFormVisible = true
      	this.registerForm = {}
      	this.$nextTick(() => {
      	  this.$refs['dataForm'].clearValidate()
      	})
      },
      register() {
      	this.$refs['dataForm'].validate((valid) => {
      	  if (valid) {
      	    let formData =Object.assign({}, this.registerForm)
      			formData.addTime = null
      			formData.updTime = null
      	    register(formData).then(() => {
      				this.getList()
      	      this.dialogFormVisible = false
      	      this.$notify({
      	        title: '成功',
      	        message: '注册成功',
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

  .info {
    position: relative;
    top: 0;
    right: 0;
    min-width: 0;
  }

  ::v-deep::-webkit-scrollbar {
    width: 7px;
    height: 5px;
    border-radius: 15px;
    -webkit-border-radius: 15px;
  }

  ::v-deep::-webkit-scrollbar-track-piece {
    background-color: #ffff;
    border-radius: 15px;
    -webkit-border-radius: 15px;
  }

  ::v-deep::-webkit-scrollbar-thumb:vertical {
    height: 7px;
    background-color: rgba(144, 147, 153, 0.5);
    border-radius: 15px;
    -webkit-border-radius: 15px;
  }

  ::v-deep::-webkit-scrollbar-thumb:horizontal {
    width: 7px;
    background-color: rgba(144, 147, 153, 0.5);
    border-radius: 15px;
    -webkit-border-radius: 15px;
  }
</style>
