<template>
  <div class="app-container">
    <el-divider>
      <h3>基本信息</h3>
    </el-divider>

    <div class="contain">
      <div class="bg-color-w b-color-4 wrap-info-table">
        <div class="flex f-a-c h-50 f-j-c bg-color-4">
        </div>
        <div class="flex border-t">
          <div class="flex flex-1 h-50">
            <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10 bg-color-4 border-r">头像</div>
            <div class="flex flex-1 f-j-c f-a-c border-r">
              <el-avatar shape="square" :src="memberDetail.avatar" />
            </div>
          </div>
          <div class="flex flex-1 h-50">
            <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10 bg-color-4 border-r">用户ID</div>
            <div class="flex flex-1 f-j-c f-a-c border-r">{{ memberDetail.id }}</div>
          </div>
          <div class="flex flex-1 h-50">
            <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10 bg-color-4 border-r">用户名</div>
            <div class="flex flex-1 f-j-c f-a-c border-r">{{ memberDetail.username }}</div>
          </div>
          <div class="flex flex-1 h-50">
            <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10 bg-color-4 border-r">手机号码</div>
            <div class="flex flex-1  f-j-c f-a-c border-r">
              <el-input v-model="memberDetail.phone" style="width:90%;" placeholder=""
                @change="updateShopAccount({id:memberDetail.id, phone: memberDetail.phone}, '修改成功')" />
            </div>
          </div>
        </div>
        <div class="flex border-t">
         <!-- <div class="flex flex-1 h-50">
            <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10 bg-color-4 border-r">身份证</div>
            <div class="flex flex-1 f-j-c f-a-c border-r">
            <el-input v-model="memberDetail.idCard" style="width:90%;" placeholder=""
              @change="updateShopAccount({id:memberDetail.id, idCard: memberDetail.idCard}, '修改成功')" />
            </div>
          </div> -->
          <div class="flex flex-1 h-50">
            <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10 bg-color-4 border-r">姓名</div>
            <div class="flex flex-1 f-j-c f-a-c border-r">
              <span>{{ memberDetail.realname }}</span>
            </div>
          </div>
          <!-- <div class="flex flex-1 h-50">
            <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10 bg-color-4 border-r">设备号</div>
            <div class="flex flex-1 f-j-c f-a-c border-r">
              <span>{{ memberDetail.imeId }}</span>
            </div>
          </div> -->
          <div class="flex flex-1 h-50">
            <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10 bg-color-4 border-r">用户状态</div>
            <div class="flex flex-1 f-j-c f-a-c">{{ memberDetail.status | memberStatusFilter }}<span
                v-if="memberDetail.freezeReason">/{{ memberDetail.freezeReason }}</span></div>
          </div>
          
          <div class="flex flex-1 h-50">
            <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10 bg-color-4 border-r"></div>
            <div class="flex flex-1 f-j-c f-a-c border-r">
              <span></span>
            </div>
          </div> 
          <div class="flex flex-1 h-50">
            <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10 bg-color-4 border-r"></div>
            <div class="flex flex-1 f-j-c f-a-c border-r">
              <span></span>
            </div>
          </div> 
        </div>
        <div class="flex border-t">
          <div class="flex flex-1 h-50">
            <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10 bg-color-4 border-r">注册时间</div>
            <div v-if="memberDetail.addTime" class="flex flex-1 f-j-c f-a-c border-r">
              {{ memberDetail.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}
            </div>
          </div>
          <div class="flex flex-1 h-50">
            <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10 bg-color-4 border-r">注册IP</div>
            <div class="flex flex-1 f-j-c f-a-c border-r">
              <span>{{ memberDetail.registerIp }}</span>
            </div>
          </div>
          <div class="flex flex-1 h-50">
            <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10 bg-color-4 border-r">最后登录时间</div>
            <div v-if="memberDetail.lastLoginTime" class="flex flex-1 f-j-c f-a-c border-r">
              {{ memberDetail.lastLoginTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}
            </div>
          </div>
          <div class="flex flex-1 h-50">
            <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10 bg-color-4 border-r">最后登录IP</div>
            <div class="flex flex-1 f-j-c f-a-c border-r">
              <span>{{ memberDetail.lastLoginIp }}</span>
            </div>
          </div>
        </div>
        <div class="flex border-t">
          <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10 bg-color-4 border-r">操作</div>
          <div class="flex flex-1 bg-color-w f-w" style="padding: 0 0px 10px 10px;">
            <el-popconfirm v-if="memberDetail.status == '1' " confirm-button-text="确定" cancel-button-text="不用了"
              icon="el-icon-info" icon-color="red" title="确定冻结用户？" @confirm="dialogFreezeVisible=true">
              <el-button slot="reference" style="margin: 10px 10px 0 0;" size="small" type="primary">冻结用户</el-button>
            </el-popconfirm>
            <el-popconfirm v-if="memberDetail.status == '-1' || memberDetail.status == '-2'" confirm-button-text="确定"
              cancel-button-text="不用了" icon="el-icon-info" icon-color="red" title="确定解冻用户？"
              @confirm="updateShopAccount({id:memberDetail.id, status: '1'}, '解冻成功')">
              <el-button slot="reference" style="margin: 10px 10px 0 0;" size="small" type="primary">解冻用户</el-button>
            </el-popconfirm>
            <el-popconfirm v-if="memberDetail.status != '-2' " confirm-button-text="确定" cancel-button-text="不用了"
              icon="el-icon-info" icon-color="red" title="确定注销用户？"
              @confirm="updateShopAccount({id:memberDetail.id, status: '-2'}, '注销成功')">
              <el-button slot="reference" style="margin: 10px 10px 0 0;" size="small" type="primary">注销用户</el-button>
            </el-popconfirm>
          </div>
        </div>

        <div class="flex border-t">
          <div class="flex flex-1 h-50">
            <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10 bg-color-4 border-r">备注</div>
            <div class="flex flex-1 f-j-c f-a-c ">
              <el-input v-model="memberDetail.remark" style="margin-left: 10px;" size="mini" type="textarea"
                placeholder="" @change="updateShopAccount({id:memberDetail.id, remark: memberDetail.remark}, '修改成功')" />
            </div>
          </div>
          <div class="flex flex-1 h-50" />
          <div class="flex flex-1 h-50" />
          <div class="flex flex-1 h-50">
            <div class="flex f-s-0 f-a-c f-j-c w-120 padding-10  border-r" />
            <div class="flex flex-1 f-j-c f-a-c">
              <el-tooltip class="item" effect="dark" content="初始密码为123456" placement="top-start">
                <el-popconfirm confirm-button-text="确定" cancel-button-text="不用了" icon="el-icon-info" icon-color="red"
                  title="确定将用户重置初始密码, 初始密码为123456？" @confirm="initPassword">
                  <el-button slot="reference" size="small" type="text">初始化登录密码</el-button>
                </el-popconfirm>
              </el-tooltip>
            </div>
          </div>
        </div>
      </div>
    </div>

    <el-dialog title="冻结用户" :visible.sync="dialogFreezeVisible" width="30%" center>
      <el-form ref="freezeForm" :rules="freezeRules" :model="freezeForm" label-width="80px" label-position="right">
        <el-form-item label="冻结理由" prop="freezeReason">
          <el-input v-model="freezeForm.freezeReason" type="textarea" :rows="6" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div style="text-align:right;">
        <el-button type="danger" @click="dialogFreezeVisible=false">取消</el-button>
        <el-button type="primary"
          @click="updateShopAccount({id:memberDetail.id, status: '-1', freezeReason: freezeForm.freezeReason}, '冻结成功'); dialogFreezeVisible=false ">
          确认冻结</el-button>
      </div>
    </el-dialog>

    <br>
    <br>
    <el-divider>
      <h3>所属部门和角色</h3>
    </el-divider>
    <div class="filter-container">
      <el-button-group>
        <el-button class="filter-item" type="primary" size="mini" icon="el-icon-plus" @click="handleCreateRole">
          添加所属部门和角色
        </el-button>
      </el-button-group>
    </div>
    <el-table ref="memberRoleTable" :key="memberRoleTableKey" v-loading="memberRoleListLoading" :data="memberRoleList"
      fit border size="mini" highlight-current-row style="width: 100%;">
      <el-table-column label="所属部门" prop="deptName" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.deptName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="拥有角色" align="center" prop="roleName">
        <template slot-scope="scope">
          <span>{{ scope.row.roleName }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.actions')" class-name="small-padding" fixed="right" align="center">
        <template slot-scope="scope">
          <el-button size="mini" type="danger" icon="el-icon-delete" @click="handleDeleteMemberRole(scope.row)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog title="添加角色" :visible.sync="dialogMemberRoleVisible">

      <div class="flex">
          <span class="mytree flex" style="width: 65%; border-right:  1px solid #f4f4f4; margin-right: 5%;">
          <el-tree ref="myTree" :data="deptList" @node-click="handleNodeClickDept" node-key="id"
            :expand-on-click-node="false" :default-expand-all="true" :indent="16" :props="deptProps"
            :highlight-current="true">
          </el-tree>
          </span>

          <el-table
                :data="roleList"
                style="width: 30%"
                max-height="800px"
                @selection-change="handleSelectionChange"
                >
                <el-table-column type="selection" width="55" align="center"></el-table-column>
                <el-table-column
                  prop="roleName"
                  label="选择角色"
                  width="180">
                  <template slot="header" slot-scope="scope">
                    <el-button-group>
                      <!-- <el-button type="danger" size="mini" @click="dialogMemberRoleVisible=false">取消</el-button> -->
                      <el-button type="primary"  size="mini" @click="confirmMemberRole">保存</el-button>
                      <el-button size="mini" type="primary" icon="el-icon-search" @click="getRoleSelector()">
                        查看全部
                      </el-button>
                    </el-button-group>
                  </template>
                </el-table-column>
              </el-table>
        </div>
        <!-- <el-form-item label="选择角色" prop="memberRoleIds">
          <el-select v-model="memberRole.memberRoleIds" multiple placeholder="请选择" style="width: 100%;" @change="changeRole">
              <el-option
                v-for="item in roleList"
                :key="item.id"
                :label="item.roleName"
                :value="item.id">
              </el-option>
            </el-select>
        </el-form-item> -->
    </el-dialog>

  </div>
</template>
<style scoped="scoped">
  ::v-deep .line {
    text-align: center;
  }

  .tips {
    font-size: 12px;
    color: #999;
    margin-left: 5px;
  }
</style>

<script>
  import {
    getShopAccountDetail,
    updateShopAccount,
    getShopAccountRoleList,
    addShopAccountRole,
    deleteShopAccountRole,
    getDeptList,
    getRoleSelector,
    getJuniorList
  } from '@/api/org'
  import waves from '@/directive/waves' // Waves directive
  import {
    parseTime,
    renderTime
  } from '@/utils'
  import Upload from '@/components/Upload/singleImage'
  import ImageSource from '@/components/Upload/imageSource'
  import {
    provinceAndCityDataPlus,
    regionData,
    CodeToText
  } from 'element-china-area-data'

  const statusOptions = [{
      key: '0',
      text: '待审核'
    },
    {
      key: '1',
      text: '通过'
    },
    {
      key: '2',
      text: '不通过'
    },
    {
      key: '3',
      text: '处理中'
    }
  ]

  // arr to obj ,such as { CN : "China", US : "USA" }
  const statusKeyValue = statusOptions.reduce((acc, cur) => {
    acc[cur.key] = cur.text
    return acc
  }, {})


  const memberStatusOptions = [{
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

  const memberStatusKeyValue = memberStatusOptions.reduce((acc, cur) => {
    acc[cur.key] = cur.text
    return acc
  }, {})

  export default {
    name: 'AccountDetailInfo',
    directives: {
      waves
    },
    components: {
      Upload,
      ImageSource
    },
    filters: {
      statusFilter(status) {
        return statusKeyValue[status]
      },
      typeFilter(status) {
        return typeKeyValue[status]
      },
      memberStatusFilter(status) {
        return memberStatusKeyValue[status]
      },
    },
    data() {
      return {
        memberId: null,
        addressOptions: regionData,
        searchParams: {},
        memberDetail: {},
        memberRealname: {},
        memberAccount: {},
        accountForm: {},
        currentActive: '1',
        groupOptions: [],
        missionGroupOptions: [],
        secretOptions: [],
        visible: false,
        memberRoleTableKey: 0,
        memberRoleList: null,
        memberRoleTotal: 0,
        memberRoleListLoading: false,
        memberRoleListQuery: {},
        dialogMemberRoleVisible: false,
        memberRole: {},
        chargeDeptTableKey: 1,
        chargeDeptList: null,
        chargeDeptTotal: 0,
        chargeDeptListLoading: false,
        chargeDeptListQuery: {},
        dialogChargeDeptVisible: false,
        chargeDept: {},
        dialogFreezeVisible: false,
        freezeRules: {
          freezeReason: [{
            required: true,
            message: '理由不能为空',
            trigger: 'blur'
          }]
        },
        freezeForm: {},
        deptProps: {
          children: 'children',
          label: 'deptName',
          value: 'id',
          checkStrictly: true
        },
        deptList: [],
        roleList: [],
        juniorDeptList: [],
        checkList: []
      }
    },
    created() {
      const memberId = this.$route.query && this.$route.query.accountId
      if (memberId) {
        this.searchParams.accountId = memberId
      }
      this.getMember(true)
    },
    methods: {
      async getDeptList() {
        getDeptList().then(response => {
          this.deptList = response.data
        })
      },
      getMember(isAllRefresh) {
        getShopAccountDetail(this.searchParams).then(response => {
          const result = response.data
          if (result && result.member) {
            this.memberDetail = result.member
            this.searchParams.uid = this.memberDetail.uid
            this.memberId = this.memberDetail.id
            this.memberDetail.password = null
            this.memberDetail.payPassword = null
            if(this.memberDetail.staffAttr){
              this.checkList = this.memberDetail.staffAttr.split(",")
            }
            if (isAllRefresh) {
              this.getMemberRoleList(this.memberDetail.id)
            }
          }
        }).catch(err => {
          console.log(err)
        })
      },
      updateShopAccountInfo() {
        this.memberDetail.addTime = null
        this.memberDetail.updTime = null
        // this.memberDetail.birthday = renderTime(this.memberDetail.birthday)
        updateShopAccount(this.memberDetail).then(response => {
          this.$notify({
            title: '成功',
            message: '保存成功',
            type: 'success',
            duration: 2000
          })
        })
      },
      getMemberRoleList() {
        this.memberRoleListLoading = true
        this.memberRoleListQuery.accountId = this.memberDetail.id
        getShopAccountRoleList(this.memberRoleListQuery).then(response => {
          this.memberRoleList = response.data
          this.memberRoleListLoading = false
        })
      },
      handleCreateRole() {
        this.multipleSelection = []
        this.dialogMemberRoleVisible = true
        this.memberRole = {}
        this.getDeptList()
        this.getRoleSelector(null);
      },
      handleNodeClickDept(data) {
        this.getRoleSelector(data.id);
        // this.memberRole.memberRoleIds = null;
      },
      getRoleSelector(deptId){
        getRoleSelector({deptId: deptId}).then(response => {
          this.roleList = response.data
        })
      },
      handleSelectionChange(val) {
      	this.multipleSelection = val;
       },
      confirmMemberRole() {
        const vals = this.multipleSelection;
        if(!vals || vals.length <= 0){
        	this.$message({
        	  message: '请至少选择一个角色',
        	  type: 'error',
        		duration: 2000
        	})
        	return;
        }
        let values = [];
        vals.forEach(val =>{
        	values.push(val.id);
        })
        this.memberRole.accountId = this.memberDetail.id
        this.memberRole.roleIds = values.join(",")
        // if(this.memberRole.memberRoleIds){
        //   this.memberRole.roleIds = this.memberRole.memberRoleIds.join(",")
        // }
        let formData = Object.assign({}, this.memberRole)
        addShopAccountRole(formData).then(() => {
          this.getMemberRoleList()
          this.dialogMemberRoleVisible = false
          this.$notify({
            title: '成功',
            message: '添加成功',
            type: 'success',
            duration: 2000
          })
        })
      },
      handleDeleteMemberRole(row) {
        this.$confirm('您确定要删除这条记录?', '提醒', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(async () => {
            deleteShopAccountRole({
              accountRoleId: row.id
            }).then(() => {
              this.getMemberRoleList()
              this.$notify({
                title: '成功',
                message: '删除成功',
                type: 'success',
                duration: 2000
              })
            })
          })
          .catch(err => {
            console.error(err)
          })
      },
      initPassword() {
        this.updateShopAccount({
          id: this.memberId,
          password: '123456'
        }, '重置成功')
      },
      updateShopAccount(form, msg) {
        updateShopAccount(form).then(response => {
          this.$notify({
            title: '成功',
            message: msg,
            type: 'success',
            duration: 2000
          })

          this.getMember(false)
        })
      },
      changeRole(){
        this.$forceUpdate()
      }

    }
  }
</script>

<style scoped="scoped">
  .wrap-info-table div {
    word-break: break-all;
    font-size: 12px;
  }

  .h-50 {
    height: 50px;
  }

  .w-120 {
    width: 120px;
    text-align: center;
  }

  .bg-color-4 {
    background-color: #F5F7FA;
  }

  .b-color-4 {
    border: 1px solid #e8ecef;
  }

  .border-l {
    border-left: 1px solid #e8ecef;
  }

  .border-r {
    border-right: 1px solid #e8ecef;
  }

  .border-t {
    border-top: 1px solid #e8ecef;
  }

  .border-b {
    border-bottom: 1px solid #e8ecef;
  }
  ::v-deep .el-tree-node__label {
    font-size: 13px;
  }
</style>
