<template>
  <div class="app-container">
       <el-tabs>
        <el-tab-pane v-model="activeName" label="系统收款账户设置" name="first"></el-tab-pane>
      </el-tabs>
      <el-card shadow="hover">
           <div slot="header" class="clearfix">
            <span>网银</span>
          </div>
         <el-form ref="wyForm" :model="wyConfig" label-width="240px" label-position="right">
             <el-form-item label="是否开启网银汇款充值" prop="status" :rules="[{ required: true, message: '请选择开关', trigger: 'blur' }]">
                 <el-switch :active-value="1" :inactive-value="0" v-model="wyConfig.status"></el-switch>
                 <span class="tips">*是否开启网银汇款充值，如果关闭，用户就不能通过网银汇款充值了</span>
             </el-form-item>
             <el-form-item label="开户银行名称" prop="bank" :rules="[{ required: true, message: '请填写开户银行名称', trigger: 'blur' }]">
                 <el-input v-model="wyConfig.bank" style="width: 200px;"></el-input>
                 <span class="tips">*开户银行名称，如中国银行深圳分行营业部</span>
             </el-form-item>
             <el-form-item label="银行账号" prop="account" :rules="[{ required: true, message: '请填写银行账号', trigger: 'blur' }]">
                 <el-input v-model="wyConfig.account" style="width: 200px;"></el-input>
                 <span class="tips">*用户给你打款的银行账号</span>
             </el-form-item>
             <el-form-item label="开户姓名" prop="accountName" :rules="[{ required: true, message: '请填写开户姓名', trigger: 'blur' }]">
                 <el-input v-model="wyConfig.accountName" style="width: 200px;"></el-input>
                 <span class="tips">*开户姓名</span>
             </el-form-item>
             <el-form-item>
               <el-button type="primary" @click="updateWyConfig">保存</el-button>
             </el-form-item>
         </el-form>
       </el-card>
<br/>
      <el-card shadow="hover">
           <div slot="header" class="clearfix">
            <span>微信</span>
          </div>
         <el-form ref="wxForm" :model="wxConfig" label-width="240px" label-position="right">
             <el-form-item label="是否开启微信扫码充值" prop="status" :rules="[{ required: true, message: '请选择开关', trigger: 'blur' }]">
                 <el-switch :active-value="1" :inactive-value="0" v-model="wxConfig.status"></el-switch>
                 <span class="tips">*是否开启微信扫码充值，如果关闭，用户就不能通过微信扫码充值了</span>
             </el-form-item>
             <el-form-item label="微信号" prop="account" :rules="[{ required: true, message: '请填写微信号', trigger: 'blur' }]">
                 <el-input v-model="wxConfig.account" style="width: 200px;"></el-input>
                 <span class="tips">*微信号，用户可以通过此微信号加你为好友</span>
             </el-form-item>
             <el-form-item label="微信收款姓名" prop="accountName">
                 <el-input v-model="wxConfig.accountName" style="width: 200px;"></el-input>
                 <span class="tips">*微信收款账号的真实姓名，如果不想让用户看到，可不填写</span>
             </el-form-item>
             <el-form-item label="微信二维码" prop="qrCode" :rules="[{ required: true, message: '请上传微信二维码', trigger: 'blur' }]">
                 <Upload v-model="wxConfig.qrCode" />
                 <ImageSource :mul="false" v-model="wxConfig.qrCode" />
             </el-form-item>
             <el-form-item>
               <el-button type="primary" @click="updateWxConfig">保存</el-button>
             </el-form-item>
         </el-form>
       </el-card>
       <br/>
       <el-card shadow="hover">
            <div slot="header" class="clearfix">
             <span>财付通</span>
           </div>
          <el-form ref="cftForm" :model="cftConfig" label-width="240px" label-position="right">
              <el-form-item label="是否开启财付通充值" prop="status" :rules="[{ required: true, message: '请选择开关', trigger: 'blur' }]">
                  <el-switch :active-value="1" :inactive-value="0" v-model="cftConfig.status"></el-switch>
                  <span class="tips">*是否开启财付通充值，如果关闭，用户就不能通过财付通转账充值了</span>
              </el-form-item>
              <el-form-item label="财付通收款账号" prop="account" :rules="[{ required: true, message: '请填写财付通收款账号', trigger: 'blur' }]">
                  <el-input v-model="cftConfig.account" style="width: 200px;"></el-input>
                  <span class="tips">*用户充值给你打款的收款账号，也是自动充值软件的财付通登录账号</span>
              </el-form-item>
              <el-form-item label="财付通收款账号姓名" prop="accountName">
                  <el-input v-model="cftConfig.accountName" style="width: 200px;"></el-input>
                  <span class="tips">*财付通收款账号的真实姓名，如果不想让用户看到，可不填写</span>
              </el-form-item>
              <el-form-item label="财付通二维码" prop="qrCode" :rules="[{ required: true, message: '请上传财付通二维码', trigger: 'blur' }]">
                  <Upload v-model="cftConfig.qrCode" />
                  <ImageSource :mul="false" v-model="cftConfig.qrCode" />
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="updateCftConfig">保存</el-button>
              </el-form-item>
          </el-form>
        </el-card>
<br/>
       <el-card shadow="hover">
            <div slot="header" class="clearfix">
             <span>支付宝</span>
           </div>
          <el-form ref="zfbForm" :model="zfbConfig" label-width="240px" label-position="right">
              <el-form-item label="是否开启支付宝充值" prop="status" :rules="[{ required: true, message: '请选择开关', trigger: 'blur' }]">
                  <el-switch :active-value="1" :inactive-value="0" v-model="zfbConfig.status"></el-switch>
                  <span class="tips">*是否开启支付宝充值，如果关闭，用户就不能通过支付宝转账充值了</span>
              </el-form-item>
              <el-form-item label="支付宝收款账号" prop="account" :rules="[{ required: true, message: '请填写支付宝收款账号', trigger: 'blur' }]">
                  <el-input v-model="zfbConfig.account" style="width: 200px;"></el-input>
                  <span class="tips">*用户充值给你打款的收款账号，也是自动充值软件的支付宝登录账号</span>
              </el-form-item>
              <el-form-item label="支付宝收款账号姓名" prop="accountName" :rules="[{ required: true, message: '请填写支付宝收款账号姓名', trigger: 'blur' }]">
                  <el-input v-model="zfbConfig.accountName" style="width: 200px;"></el-input>
                  <span class="tips">*支付宝收款账号的真实姓名，如果不想让用户看到，可不填写</span>
              </el-form-item>
              <el-form-item label="支付宝二维码" prop="qrCode" :rules="[{ required: true, message: '请上传支付宝二维码', trigger: 'blur' }]">
                  <Upload v-model="zfbConfig.qrCode" />
                  <ImageSource :mul="false" v-model="zfbConfig.qrCode" />
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="updateZfbConfig">保存</el-button>
              </el-form-item>
          </el-form>
        </el-card>

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
import { getParams, updateParams } from '@/api/config'
import Upload from '@/components/Upload/singleImage'
import ImageSource from '@/components/Upload/imageSource'

export default {
  name: 'configAccount',
  components: { Upload, ImageSource },
  data() {
    return {
			zfbConfig:{},
      wxConfig:{},
      cftConfig:{},
      wyConfig:{},
			loading : true,
      activeName: 'first'
    }
  },
  created() {
    this.get()
  },
  methods: {
    get() {
      getParams({key: 'config_account', accountType: 1}).then(response => {
        this.zfbConfig = response.data
        if(!this.zfbConfig){
          this.zfbConfig = {}
        }
      })

      getParams({key: 'config_account', accountType: 2}).then(response => {
        this.wxConfig = response.data
        if(!this.wxConfig){
          this.wxConfig = {}
        }
      })

      getParams({key: 'config_account', accountType: 3}).then(response => {
        this.cftConfig = response.data
        if(!this.cftConfig){
          this.cftConfig = {}
        }
      })

      getParams({key: 'config_account', accountType: 4}).then(response => {
        this.wyConfig = response.data
        if(!this.wyConfig){
          this.wyConfig = {}
        }
      })
    },
    updateZfbConfig() {
        this.$refs['zfbForm'].validate((valid) => {
          if (valid) {
            let formData =Object.assign({}, this.zfbConfig)
            formData.type = 1
            formData.addTime = null
            formData.updTime = null
            updateParams({key: 'config_account', jsonData: JSON.stringify(formData)}).then(() => {
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
    updateWxConfig() {
        this.$refs['wxForm'].validate((valid) => {
          if (valid) {
            let formData =Object.assign({}, this.wxConfig)
            formData.type = 2
            formData.addTime = null
            formData.updTime = null
            updateParams({key: 'config_account', jsonData: JSON.stringify(formData)}).then(() => {
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
    updateCftConfig() {
        this.$refs['cftForm'].validate((valid) => {
          if (valid) {
            let formData =Object.assign({}, this.cftConfig)
            formData.type = 3
            formData.addTime = null
            formData.updTime = null
            updateParams({key: 'config_account', jsonData: JSON.stringify(formData)}).then(() => {
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
    updateWyConfig() {
        this.$refs['wyForm'].validate((valid) => {
          if (valid) {
            let formData =Object.assign({}, this.wyConfig)
            formData.type = 4
            formData.addTime = null
            formData.updTime = null
            updateParams({key: 'config_account', jsonData: JSON.stringify(formData)}).then(() => {
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
