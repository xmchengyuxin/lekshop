<template>
   <div class="app-container">
         <el-tabs  v-model="currentActive">
           <el-tab-pane name="1">
             <span slot="label"><i class="el-icon-info"></i> 基础信息</span>
           </el-tab-pane>
           <el-tab-pane name="2">
             <span slot="label"><i class="el-icon-coin"></i> 资金信息</span>
           </el-tab-pane>
           <el-tab-pane name="3">
             <span slot="label"><i class="el-icon-s-custom"></i> 认证信息</span>
           </el-tab-pane>
        </el-tabs>

        <div v-if="currentActive == 1">
            <el-form style="border: 1px solid #f4f4f4;" ref="dataForm" label-width="120px" label-position="right">
              <br/>
              <el-form-item label="头像" prop="headImg">
                	<Upload v-model="memberDetail.headImg" />
                  <ImageSource :mul="false" v-model="memberDetail.headImg" />
              </el-form-item>
             <el-form-item label="用户名" prop="code">
               <el-input v-model="memberDetail.code" style="width: 300px;" disabled=""></el-input>
             </el-form-item>
             <el-form-item label="用户昵称" prop="nickname">
               <el-input v-model="memberDetail.nickname" style="width: 300px;" ></el-input>
             </el-form-item>
             <el-form-item label="会员组别" prop="groupId">
               <el-select v-model="memberDetail.groupId" placeholder="请选择组别" class="filter-item" clearable filterable style="width: 300px;" >
                   <el-option
                     v-for="item in groupOptions"
                     :key="item.id"
                     :label="item.name"
                     :value="item.id">
                   </el-option>
                 </el-select>
             </el-form-item>
             <el-form-item label="当前身份" prop="type">
               <el-radio-group v-model="memberDetail.type">
                   <el-radio :label="1">买家</el-radio>
                   <el-radio :label="2">卖家</el-radio>
                 </el-radio-group>
             </el-form-item>
             <el-form-item label="佣金组别" prop="missionConfigId">
               <el-select v-model="memberDetail.missionConfigId" placeholder="请选择组别" class="filter-item" clearable filterable style="width: 300px;">
                   <el-option
                     v-for="item in missionGroupOptions"
                     :key="item.id"
                     :label="item.name"
                     :value="item.id">
                   </el-option>
                 </el-select>
             </el-form-item>
             <el-form-item label="淘宝返款服务费" prop="tbServiceFee">
               <el-input v-model="memberDetail.tbServiceFee" style="width: 300px;" ></el-input> 元
               <span class="tips">*平台返款时, 收取的返款费用</span>
             </el-form-item>
             <el-form-item label="京东返款服务费" prop="jdServiceFee">
               <el-input v-model="memberDetail.jdServiceFee" style="width: 300px;" ></el-input> 元
               <span class="tips">*平台返款时, 收取的返款费用</span>
             </el-form-item>
             <el-form-item label="拼多多返款服务费" prop="pddServiceFee">
               <el-input v-model="memberDetail.pddServiceFee" style="width: 300px;" ></el-input> 元
               <span class="tips">*平台返款时, 收取的返款费用</span>
             </el-form-item>
             <el-form-item label="抖音返款服务费" prop="dyServiceFee">
               <el-input v-model="memberDetail.dyServiceFee" style="width: 300px;" ></el-input> 元
               <span class="tips">*平台返款时, 收取的返款费用</span>
             </el-form-item>
             <el-form-item label="折扣" prop="discount">
               <el-input v-model="memberDetail.discount" style="width: 300px;" ></el-input> %
               <span class="tips">*折扣只针对基础佣金，附加功能佣金不打折, 85折请填写85</span>
             </el-form-item>
             <el-form-item label="密保问题" prop="secret"  >
               <el-select v-model="memberDetail.secret" placeholder="请选择密保问题" class="filter-item" clearable filterable style="width: 300px;">
                   <el-option
                     v-for="item in secretOptions"
                     :key="item.key"
                     :label="item.question"
                     :value="item.key">
                   </el-option>
                 </el-select>
             </el-form-item>
             <el-form-item label="密码答案" prop="secretAnswer">
               <el-input v-model="memberDetail.secretAnswer" style="width: 300px;" ></el-input>
             </el-form-item>
             <el-form-item label="登录密码" prop="password">
               <el-input type="password" v-model="memberDetail.password" style="width: 300px;" ></el-input>
             </el-form-item>
             <el-form-item label="二级密码" prop="payPassword">
               <el-input type="password" v-model="memberDetail.payPassword" style="width: 300px;" ></el-input>
             </el-form-item>
             <el-form-item label="性别" prop="gender">
               <el-radio-group v-model="memberDetail.gender">
                   <el-radio :label="0">保密</el-radio>
                   <el-radio :label="1">男</el-radio>
                   <el-radio :label="2">女</el-radio>
                 </el-radio-group>
             </el-form-item>
             <el-form-item label="生日" prop="birthday">
               <el-date-picker
               style="width: 300px;"
                     v-model="memberDetail.birthday"
                     type="date"
                     placeholder="选择日期">
                   </el-date-picker>
             </el-form-item>
             <el-form-item label="QQ" prop="qq">
               <el-input v-model="memberDetail.qq" style="width: 300px;" ></el-input>
             </el-form-item>
             <el-form-item label="邮箱" prop="email">
               <el-input v-model="memberDetail.email" style="width: 300px;" ></el-input>
             </el-form-item>
             <el-form-item label="备注" prop="remark">
               <el-input type="textarea" v-model="memberDetail.remark" style="width: 300px;" :rows="4"></el-input>
             </el-form-item>
             <el-form-item>
               <el-button-group>
                 <el-button type="primary" @click="updateMemberInfo">保存<i class="el-icon-check el-icon--right"></i></el-button>
               </el-button-group>
             </el-form-item>
             </el-form>
        </div>

        <div v-if="currentActive == 2" >
         <el-form style="border: 1px solid #f4f4f4;" ref="dataForm" label-width="120px" label-position="right">
           <el-form-item label="账户资金" prop="amount">
            <code >
                <span>账户余额:￥{{memberAccount.amount}}</span>
                <!-- <br>
                <span>发布点数:￥{{memberAccount.mission}}</span> -->
                <br>
                <span>推广佣金:￥{{memberAccount.spmission}}</span>
                <br>
                <span>商保金额:￥{{memberAccount.shangbaoAmount}}</span>
              </code>
          </el-form-item>
          <el-form-item label="增减余额" prop="amount">
            <el-input v-model="accountForm.amount" placeholder="请填写金额" style="width: 300px;" ></el-input>
            <span class="tips">*增加金额请填写正数,减少金额请填写负数</span>
          </el-form-item>
          <!-- <el-form-item label="增减发布点" prop="publishPoint">
            <el-input v-model="accountForm.publishPoint" placeholder="请填写发布点" style="width: 300px;" ></el-input>
            <span class="tips">*增加发布点请填写正数,减少发布点请填写负数</span>
          </el-form-item> -->
          <el-form-item label="增减推广佣金" prop="spmission">
            <el-input v-model="accountForm.spmission" placeholder="请填写金额" style="width: 300px;" ></el-input>
            <span class="tips">*增加推广佣金请填写正数,减少推广佣金请填写负数</span>
          </el-form-item>
            <el-form-item>
              <el-button-group>
                <el-button type="primary" @click="updateMemberAccount">保存<i class="el-icon-check el-icon--right"></i></el-button>
              </el-button-group>
            </el-form-item>
         </el-form>
        </div>

      <div v-if="currentActive == 3" class="createPost-container" >
          <el-form style="border: 1px solid #f4f4f4;" ref="dataForm" label-width="120px" label-position="right">
            <br/>
           <el-form-item label="手机号码" prop="phone">
             <el-input v-model="memberDetail.phone" style="width: 300px;" ></el-input>
           </el-form-item>
           <el-form-item label="手机认证" prop="phoneStatus">
               <el-switch :active-value="1" :inactive-value="0" v-model="memberDetail.phoneStatus"></el-switch>
               <span class="tips">*是否通过手机认证</span>
           </el-form-item>
           <el-form-item label="实名认证" prop="realnameStatus">
               <el-switch :active-value="1" :inactive-value="0" v-model="memberDetail.realnameStatus"></el-switch>
               <span class="tips">*是否通过实名认证</span>
           </el-form-item>
           <el-form-item label="银行认证" prop="bankStatus">
               <el-switch :active-value="1" :inactive-value="0" v-model="memberDetail.bankStatus"></el-switch>
               <span class="tips">*是否通过银行认证</span>
           </el-form-item>
           <el-form-item label="视频认证" prop="videoStatus">
               <el-switch :active-value="1" :inactive-value="0" v-model="memberDetail.videoStatus"></el-switch>
               <span class="tips">*是否通过视频认证</span>
           </el-form-item>
           <el-form-item label="关注公众号" prop="gzhFocusStatus">
               <el-switch :active-value="1" :inactive-value="0" v-model="memberDetail.gzhFocusStatus"></el-switch>
               <span class="tips">*是否关注公众号</span>
           </el-form-item>
           <el-form-item>
             <el-button-group>
               <el-button type="primary" @click="updateMemberInfo">保存<i class="el-icon-check el-icon--right"></i></el-button>
             </el-button-group>
           </el-form-item>
         </el-form>
      </div>


</div>
</template>
<style scoped="scoped">
  ::v-deep .line {
    text-align: center;
  }
  .tips{
    font-size: 12px;
    color:#999;
    margin-left: 5px;
  }
</style>

<script>
import {getMember, updateMember, updateMemberAccount, getMemberGroupSelector} from '@/api/member'
import {getConfigMissionSelector, getSecretSelector} from '@/api/config'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime } from '@/utils'
import Upload from '@/components/Upload/singleImage'
import ImageSource from '@/components/Upload/imageSource'

const bankOptions = [
  { key: 'ICBC', text: '中国工商银行' },
  { key: 'ABC', text: '中国农业银行' },
  { key: 'BOC', text: '中国银行' },
  { key: 'CCB', text: '中国建设银行' },
  { key: 'CIB', text: '兴业银行' },
  { key: 'SPDB', text: '浦东发展银行' },
  { key: 'BCM', text: '交通银行' },
  { key: 'ECITIC', text: '中信银行' },
  { key: 'CEB', text: '中国光大银行' },
  { key: 'CMB', text: '招商银行' },
  { key: 'GDB', text: '广发银行' },
  { key: 'PSBC', text: '中国邮政储蓄银行' },
  { key: 'PAB', text: '平安银行' },
  { key: 'CMSB', text: '民生银行' }
]

const marriageOptions = [
  { key: '1', text: '已婚' },
  { key: '2', text: '未婚' },
  { key: '3', text: '离异' },
]

const marriageKeyValue = marriageOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

const healthTypeOptions = [
  { key: 1, text: '卡制' },
  { key: 2, text: '本制' },
  { key: 3, text: '纸质' },
  { key: 4, text: '电子版' },
]

// arr to obj ,such as { CN : "China", US : "USA" }
const healthTypeKeyValue = healthTypeOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

export default {
  name: 'editMember',
  directives: { waves },
  components: { Upload, ImageSource },
  filters: {
    marriageFilter(status) {
      return marriageKeyValue[status]
    },
    healthTypeFilter(status) {
      return healthTypeKeyValue[status]
    }
  },
  data() {
    return {
      memberDetail: {},
      memberRealname:{},
      memberAccount:{},
      accountForm: {},
      currentActive: '1',
      bankOptions,
      groupOptions:[],
      missionGroupOptions: [],
      secretOptions: []
    }
  },
  created() {
    const id = this.$route.params && this.$route.params.id
    this.getMember(id)
    this.getMemberGroupSelector()
    this.getConfigMissionSelector()
    this.getSecretSelector()
  },
  methods: {
    getMemberGroupSelector(){
      getMemberGroupSelector().then(response => {
        this.groupOptions = response.data
      })
    },
    getConfigMissionSelector(){
      getConfigMissionSelector().then(response => {
        this.missionGroupOptions = response.data
      })
    },
    getSecretSelector(){
      getSecretSelector().then(response => {
        this.secretOptions = response.data
      })
    },
    getMember(id) {
      getMember({memberId: id}).then(response => {
        let result = response.data
        this.memberDetail = result.member
        this.memberDetail.password = null
        this.memberDetail.payPassword = null
        this.memberRealname = result.realname
        this.memberAccount = result.memberAccount
      }).catch(err => {
        console.log(err)
      })
    },
    updateMemberInfo() {
      this.memberDetail.addTime = null
      this.memberDetail.updTime = null
      this.memberDetail.birthday = renderTime(this.memberDetail.birthday)
        updateMember(this.memberDetail).then(response => {
          this.$notify({
            title: '成功',
            message: '保存成功',
            type: 'success',
            duration: 2000
          })
        })
    },
    updateMemberAccount() {
       this.accountForm.memberId = this.memberDetail.id
        updateMemberAccount(this.accountForm).then(response => {
          this.memberAccount = response.data
          this.$notify({
            title: '成功',
            message: '保存成功',
            type: 'success',
            duration: 2000
          })
        })
    },

  }
}
</script>
