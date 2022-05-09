<template>
  <div class="app-container">
    <el-tabs  v-model="activeGroup" @tab-click="handleClickGroup">
      <el-tab-pane v-for="(item,index) in memberGroupOptions" :label="item.name" :name="item.id+''">
      </el-tab-pane>
      <el-tabs v-model="activeName" type="border-card" @tab-click="handleClick">
        <el-tab-pane label="提现设置" name="first">
          <el-form ref="withdrawForm" :model="withdrawConfig" label-width="190px" label-position="right">
            <el-form-item label="是否开启提现功能" prop="withdrawStatus" :rules="[{ required: true, message: '请选择是否开启', trigger: 'change' }]">
                <el-switch :active-value="1" :inactive-value="0" v-model="withdrawConfig.withdrawStatus"></el-switch>
                <span class="tips">*是否开启提现功能，关闭大家都不能提现哦</span>
            </el-form-item>
            <el-form-item label="是否允许佣金转入账户余额" prop="missionToBalance" :rules="[{ required: true, message: '请选择是否开启', trigger: 'change' }]">
                <el-switch :active-value="1" :inactive-value="0" v-model="withdrawConfig.missionToBalance"></el-switch>
                <span class="tips">*选是，可以转入余额，选否则不允许账户佣金转入账户余额</span>
            </el-form-item>
            <el-form-item label="允许提现资金类型" prop="withdrawType" :rules="[{ required: true, message: '请选择提现资金类型', trigger: 'change' }]">
                <el-radio-group v-model="withdrawConfig.withdrawType" size="mini">
                  <el-radio-button :label="0">全部</el-radio-button>
                  <el-radio-button :label="1">账户余额</el-radio-button>
                  <el-radio-button :label="2">账户佣金</el-radio-button>
                </el-radio-group>
                <span class="tips">*可以选择仅允许账户余额提现或账户佣金提现或全部</span>
            </el-form-item>
            <el-form-item label="允许提现付款方式" prop="withdrawMethod" :rules="[{ required: true, message: '请选择允许提现付款方式', trigger: 'change' }]">
                <el-checkbox-group v-model="withdrawMethodArr" @change="withdrawConfig.withdrawMethod=withdrawMethodArr.join(',')">
                    <el-checkbox label="1" :key="1">支付宝</el-checkbox>
                    <el-checkbox label="2" :key="2">微信</el-checkbox>
                    <el-checkbox label="3" :key="3">银行</el-checkbox>
                </el-checkbox-group>
            </el-form-item>
            <el-form-item label="是否实名认证才可提现" prop="needRealname" :rules="[{ required: true, message: '请选择是否开启', trigger: 'change' }]">
                <el-switch :active-value="1" :inactive-value="0" v-model="withdrawConfig.needRealname"></el-switch>
                <span class="tips">*是否要通过实名认证的用户才可以提现</span>
            </el-form-item>
            <el-form-item label="是否银行认证才可提现" prop="needBank" :rules="[{ required: true, message: '请选择是否开启', trigger: 'change' }]">
                <el-switch :active-value="1" :inactive-value="0" v-model="withdrawConfig.needBank"></el-switch>
                <span class="tips">*是否要通过银行认证的用户才可以提现</span>
            </el-form-item>
            <el-form-item label="是否要接收手机验证码" prop="needPhoneCode" :rules="[{ required: true, message: '请选择是否开启', trigger: 'change' }]">
                <el-switch :active-value="1" :inactive-value="0" v-model="withdrawConfig.needPhoneCode"></el-switch>
                <span class="tips">*是否要接收手机验证码验证后才可以提现</span>
            </el-form-item>
            <el-form-item label="账户余额提现最低金额" prop="minBalanceWithdrawAmount" :rules="[{ required: true, message: '请填写最低金额', trigger: 'blur' }]">
                <el-input v-model="withdrawConfig.minBalanceWithdrawAmount" style="width: 100px;"></el-input> 元
                <span class="tips">*账户余额提现允许最低提现的金额多少钱</span>
            </el-form-item>
            <el-form-item label="账户余额提现最高金额" prop="maxBalanceWithdrawAmount" :rules="[{ required: true, message: '请填写最高金额', trigger: 'blur' }]">
                <el-input v-model="withdrawConfig.maxBalanceWithdrawAmount" style="width: 100px;"></el-input> 元
                <span class="tips">*账户余额提现允许最高提现的金额多少钱</span>
            </el-form-item>
            <el-form-item label="账户佣金提现最低金额" prop="minMissionWithdrawAmount" :rules="[{ required: true, message: '请填写最低金额', trigger: 'blur' }]">
                <el-input v-model="withdrawConfig.minMissionWithdrawAmount" style="width: 100px;"></el-input> 元
                <span class="tips">*账户佣金提现允许最低提现的金额多少钱</span>
            </el-form-item>
            <el-form-item label="账户佣金提现最高金额" prop="maxMissionWithdrawAmount" :rules="[{ required: true, message: '请填写最高金额', trigger: 'blur' }]">
                <el-input v-model="withdrawConfig.maxMissionWithdrawAmount" style="width: 100px;"></el-input> 元
                <span class="tips">*账户佣金提现允许最高提现的金额多少钱</span>
            </el-form-item>
            <el-form-item label="每天提现几次" prop="withdrawDayNum" :rules="[{ required: true, message: '每天提现次数', trigger: 'blur' }]">
                <el-input v-model="withdrawConfig.withdrawDayNum" style="width: 100px;"></el-input> 次
                <span class="tips">*每天提现几次</span>
            </el-form-item>
            <!-- <el-form-item label="向你提现的支付宝账号" prop="zfbAccount" :rules="[{ required: true, message: '请填写支付宝账号', trigger: 'blur' }]">
                <el-input v-model="withdrawConfig.zfbAccount" style="width: 200px;"></el-input>
                &nbsp;&nbsp;姓名 <el-input v-model="withdrawConfig.zfbRealname" style="width: 150px;"></el-input>
                <span class="tips">*用户是在提现页面中向此账号发起收款提现，然后你再登录支付宝同意打款</span>
            </el-form-item> -->
            <el-form-item label="支付宝提现手续费" prop="zfbRate" :rules="[{ required: true, message: '请填写提现手续费', trigger: 'blur' }]">
                <el-input v-model="withdrawConfig.zfbRate" style="width: 100px;"></el-input> %
                &nbsp;&nbsp;单笔最低手续费 <el-input v-model="withdrawConfig.zfbMinFee" style="width: 100px;"></el-input> 元
                <span class="tips">*如果不想扣手续费，二个框填写0</span>
            </el-form-item>
            <el-form-item label="微信提现手续费" prop="wxRate" :rules="[{ required: true, message: '请填写提现手续费', trigger: 'blur' }]">
                <el-input v-model="withdrawConfig.wxRate" style="width: 100px;"></el-input> %
                &nbsp;&nbsp;单笔最低手续费 <el-input v-model="withdrawConfig.wxMinFee" style="width: 100px;"></el-input> 元
                <span class="tips">*如果不想扣手续费，二个框填写0</span>
            </el-form-item>
            <el-form-item label="银行提现手续费" prop="bankRate" :rules="[{ required: true, message: '请填写提现手续费', trigger: 'blur' }]">
                <el-input v-model="withdrawConfig.bankRate" style="width: 100px;"></el-input> %
                &nbsp;&nbsp;单笔最低手续费 <el-input v-model="withdrawConfig.bankMinFee" style="width: 100px;"></el-input> 元
                <span class="tips">*如果不想扣手续费，二个框填写0</span>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="updateConfig">保存</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
        <el-tab-pane label="实名认证设置" name="fourth">
          <el-form ref="realnameForm" :model="realnameConfig" label-width="210px" label-position="right">
            <el-form-item label="是否开启实名认证" prop="realnameStatus" :rules="[{ required: true, message: '请选择是否开启', trigger: 'change' }]">
                <el-switch :active-value="1" :inactive-value="0" v-model="realnameConfig.realnameStatus"></el-switch>
                <span class="tips">*开启后，实名认证页面可以提交资料认证，关闭则不可以提交</span>
            </el-form-item>
            <el-form-item label="实名时是否需要手机短信验证" prop="realnameNeedPhoneCode" :rules="[{ required: true, message: '请选择是否开启', trigger: 'change' }]">
                <el-switch :active-value="1" :inactive-value="0" v-model="realnameConfig.realnameNeedPhoneCode"></el-switch>
                <span class="tips">*开启后，实名认证页面会要求先发送手机短信验证码验证，防止用户恶意提交产生不必要开支</span>
            </el-form-item>
            <el-form-item label="实名时是否需要上传手持相片" prop="needHandImg" :rules="[{ required: true, message: '请选择是否开启', trigger: 'change' }]">
                <el-switch :active-value="1" :inactive-value="0" v-model="realnameConfig.needHandImg"></el-switch>
                <span class="tips">*开启后，实名认证页面会要求上传手持相片，关闭则不需要上传</span>
            </el-form-item>
            <el-form-item label="是否开启银行认证" prop="bankStatus" :rules="[{ required: true, message: '请选择是否开启', trigger: 'change' }]">
                <el-switch :active-value="1" :inactive-value="0" v-model="realnameConfig.bankStatus"></el-switch>
                <span class="tips">*开启后，银行认证页面可以提交资料认证，关闭则不可以提交</span>
            </el-form-item>
            <el-form-item label="认证时是否需要手机短信验证" prop="bankNeedPhoneCode" :rules="[{ required: true, message: '请选择是否开启', trigger: 'change' }]">
                <el-switch :active-value="1" :inactive-value="0" v-model="realnameConfig.bankNeedPhoneCode"></el-switch>
                <span class="tips">*开启后，银行认证页面会要求先发送手机短信验证码验证，防止用户恶意提交产生不必要开支</span>
            </el-form-item>
            <el-form-item label="银行认证通过也同步实名通过" prop="synRealname" :rules="[{ required: true, message: '请选择是否开启', trigger: 'change' }]">
                <el-switch :active-value="1" :inactive-value="0" v-model="realnameConfig.synRealname"></el-switch>
                <span class="tips">*开启后，当还没有通过实名认证，先提交银行认证通过后，也会同步实名认证通过（银行认证本来也是实名认证的一种）</span>
            </el-form-item>
            <el-form-item label="第三方接口实时实名认证" prop="thirdRealnameStatus" :rules="[{ required: true, message: '请选择是否开启', trigger: 'change' }]">
                <el-switch :active-value="1" :inactive-value="0" v-model="realnameConfig.thirdRealnameStatus"></el-switch>
                <span class="tips">*开启后，用户提交后即时验证返回结果，不需要上传证件图片，如果关闭则需要上传证件，后台人工审核</span>
            </el-form-item>
            <el-form-item label="第三方接口实时银行认证" prop="thirdBankStatus" :rules="[{ required: true, message: '请选择是否开启', trigger: 'change' }]">
                <el-switch :active-value="1" :inactive-value="0" v-model="realnameConfig.thirdBankStatus"></el-switch>
                <span class="tips">*开启后，用户提交后即时验证返回结果，如果关闭则需要后台人工审核</span>
            </el-form-item>
            <!-- <el-form-item label="是否验证银行预留手机号" prop="bankPhoneStatus" :rules="[{ required: true, message: '请选择是否开启', trigger: 'change' }]">
                <el-switch :active-value="1" :inactive-value="0" v-model="realnameConfig.bankPhoneStatus"></el-switch>
                <span class="tips">*开启后，认证的手机号码必需是银行预留的手机号，并且要接收短信验证码认证，如果关闭则不验证银行预留手机号</span>
            </el-form-item> -->
            <el-form-item label="第三方接口实时认证收费" prop="chargeNum" :rules="[{ required: true, message: '请填写第三方接口实时认证收费', trigger: 'blur' }]">
                第 <el-input v-model="realnameConfig.chargeNum" style="width: 100px;"></el-input> 次起收费
                &nbsp;&nbsp; <el-input v-model="realnameConfig.chargeFee" style="width: 100px;"></el-input> 元/次
                <span class="tips">*向平台会员收费的，建议从第2次起收费1元一次，若不想收费，两个输入框填写0</span>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="updateConfig">保存</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
        <el-tab-pane label="邀请注册设置" name="five">

          <el-form ref="inviteCodeForm" :model="inviteCodeConfig" label-width="210px" label-position="right">
            <el-form-item label="是否允许注册" prop="registerStatus" :rules="[{ required: true, message: '请选择是否开启', trigger: 'change' }]">
                <el-switch :active-value="1" :inactive-value="0" v-model="inviteCodeConfig.registerStatus"></el-switch>
                <span class="tips">*关闭后，如果注册默认为该会员组时, 则无法注册</span>
            </el-form-item>
             <el-form-item label="是否允许邀请" prop="inviteStatus" :rules="[{ required: true, message: '请选择是否开启', trigger: 'change' }]">
                <el-switch :active-value="1" :inactive-value="0" v-model="inviteCodeConfig.inviteStatus"></el-switch>
                <span class="tips">*开启后，该用户组的会员才有权邀请，关闭则不能邀请</span>
            </el-form-item>
            <el-form-item label="是否必须邀请码才能注册" prop="regNeedInvite" :rules="[{ required: true, message: '请选择是否开启', trigger: 'change' }]">
                <el-switch :active-value="1" :inactive-value="0" v-model="inviteCodeConfig.regNeedInvite"></el-switch>
                <span class="tips">*开启后，注册必须使用邀请码注册</span>
            </el-form-item>
            <!-- <el-form-item label="邀请码使用期限" prop="useDays" :rules="[{ required: true, message: '请填写邀请码使用期限', trigger: 'blur' }]">
                <el-input v-model.number="inviteCodeConfig.useDays" style="width: 100px;"></el-input> 天
                <span class="tips">*邀请码有效天数</span>
            </el-form-item>
            <el-form-item label="邀请码可使用次数" prop="useTimes" :rules="[{ required: true, message: '请填写邀请码可使用次数', trigger: 'blur' }]">
                <el-input v-model.number="inviteCodeConfig.useTimes" style="width: 100px;"></el-input> 次
                <span class="tips">*邀请码使用次数</span>
            </el-form-item>
            <el-form-item label="每天申请邀请码数量" prop="dayApplyNum" :rules="[{ required: true, message: '请填写每天申请邀请码数量', trigger: 'blur' }]">
                <el-input v-model.number="inviteCodeConfig.dayApplyNum" style="width: 100px;"></el-input> 个
                <span class="tips">*每天可申请邀请码数量</span>
            </el-form-item> -->
            <el-form-item>
              <el-button type="primary" @click="updateConfig">保存</el-button>
            </el-form-item>
          </el-form>

        </el-tab-pane>
      </el-tabs>
    </el-tabs>

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
import { getMemberGroupSelector } from '@/api/member'
import { getParams, updateParams } from '@/api/config'

export default {
  name: 'configMember',
  data() {
    return {
      activeName:'first',
			withdrawConfig:{},
      blackConfig:{},
      shangbaoConfig:{},
      realnameConfig:{},
      inviteCodeConfig:{},
			loading : true,
      memberGroupOptions:[],
      withdrawMethodArr:[''],
      activeGroup: null
    }
  },
  created() {
    this.getMemberGroupSelector()
  },
  methods: {
    getMemberGroupSelector(){
      getMemberGroupSelector().then(response => {
        this.memberGroupOptions = response.data
        if(this.memberGroupOptions && this.memberGroupOptions.length > 0){
          this.activeGroup = this.memberGroupOptions[0].id + ''
          this.get(this.activeGroup)
        }
      })
    },
    get(groupId) {
      getParams({key: 'config_withdraw', groupId: groupId}).then(response => {
        this.withdrawConfig = response.data
        if(this.withdrawConfig){
          this.$nextTick(() => {
            this.withdrawMethodArr = this.withdrawConfig.withdrawMethod.split(',')
          })
        }else{
          this.withdrawMethodArr = []
          this.withdrawConfig = {}
        }
      })

      getParams({key: 'config_black', groupId: groupId}).then(response => {
        this.blackConfig = response.data
        if(!this.blackConfig){
          this.blackConfig = {}
        }
      })

      getParams({key: 'config_shangbao', groupId: groupId}).then(response => {
         let params = response.data
        if(params && params.length > 0){
          let str = ''
          params.forEach((item) =>{
            var temp = item.amount+"="+item.takeOverAmount
            str = str + "\n" + temp;
          })
          this.shangbaoConfig = {priceParams: str.substring(1)}
        }else{
          this.shangbaoConfig = {}
        }
      })

      getParams({key: 'config_realname', groupId: groupId}).then(response => {
        this.realnameConfig = response.data
        if(!this.realnameConfig){
          this.realnameConfig = {}
        }
      })

      getParams({key: 'config_invite_code', groupId: groupId}).then(response => {
        this.inviteCodeConfig = response.data
        if(!this.inviteCodeConfig){
          this.inviteCodeConfig = {}
        }
      })
    },
    updateConfig() {
      if(this.activeName == 'first'){
          this.$refs['withdrawForm'].validate((valid) => {
            if (valid) {
              let formData =Object.assign({}, this.withdrawConfig)
              formData.groupId = this.activeGroup
              formData.addTime = null
              formData.updTime = null
              updateParams({key: 'config_withdraw', jsonData: JSON.stringify(formData)}).then(() => {
                this.$notify({
                  title: '成功',
                  message: '保存成功',
                  type: 'success',
                  duration: 2000
                })
                this.get(this.activeGroup)
              })
            }
          })
      }else if(this.activeName == 'second'){
            this.$refs['blackForm'].validate((valid) => {
                    if (valid) {
                      let formData =Object.assign({}, this.blackConfig)
                      formData.groupId = this.activeGroup
                      formData.addTime = null
                      formData.updTime = null
                      updateParams({key: 'config_black', jsonData: JSON.stringify(formData)}).then(() => {
                        this.$notify({
                          title: '成功',
                          message: '保存成功',
                          type: 'success',
                          duration: 2000
                        })
                        this.get(this.activeGroup)
                      })
                    }
                  })
      }else if(this.activeName == 'third'){
            this.$refs['shangbaoForm'].validate((valid) => {
                    if (valid) {
                      let arr = [];
                      this.shangbaoConfig.priceParams.split("\n").forEach(item=>{
                        arr.push({groupId: this.activeGroup, amount: item.split("=")[0], takeOverAmount: item.split("=")[1]})
                      })
                      updateParams({key: 'config_shangbao', jsonData: JSON.stringify(arr)}).then(() => {
                        this.$notify({
                          title: '成功',
                          message: '保存成功',
                          type: 'success',
                          duration: 2000
                        })
                        this.get(this.activeGroup)
                      })
                    }
                  })
      }else if(this.activeName == 'fourth'){
            this.$refs['realnameForm'].validate((valid) => {
                    if (valid) {
                      let formData =Object.assign({}, this.realnameConfig)
                      formData.groupId = this.activeGroup
                      formData.addTime = null
                      formData.updTime = null
                      updateParams({key: 'config_realname', jsonData: JSON.stringify(formData)}).then(() => {
                        this.$notify({
                          title: '成功',
                          message: '保存成功',
                          type: 'success',
                          duration: 2000
                        })
                        this.get(this.activeGroup)
                      })
                    }
                  })
      }else if(this.activeName == 'five'){
          this.$refs['inviteCodeForm'].validate((valid) => {
                    if (valid) {
                      let formData =Object.assign({}, this.inviteCodeConfig)
                      formData.groupId = this.activeGroup
                      formData.addTime = null
                      formData.updTime = null
                      updateParams({key: 'config_invite_code', jsonData: JSON.stringify(formData)}).then(() => {
                        this.$notify({
                          title: '成功',
                          message: '保存成功',
                          type: 'success',
                          duration: 2000
                        })
                        this.get(this.activeGroup)
                      })
                    }
                  })
      }
    },
    handleClick(tab, event){
      this.activeName = tab.name
    },
    handleClickGroup(){
      this.get(this.activeGroup)
    }
  }
}
</script>
