<template>
<el-card shadow="hover" style="margin: 10px;">
        <el-alert
            class="warn-content"
            title="温馨提示"
            type="success"
            show-icon
            :closable="false">
               支付宝支付请查阅<a href="https://open.alipay.com" target="_blank">《支付宝开放平台》</a>
          </el-alert>
          <br/>
<el-form ref="form" :rules="rules" :model="config" v-loading="loading" label-width="140px" label-position="right" style="width: 60%;">
  <el-form-item label="商户号" prop="appId">
    <el-input v-model="config.appId"></el-input>
  </el-form-item>
  <el-form-item label="支付宝公钥" prop="zfbPublicKey">
    <el-input v-model="config.zfbPublicKey" type="textarea"></el-input>
  </el-form-item>
  <el-form-item label="应用公钥" prop="appPublicKey">
    <el-input v-model="config.appPublicKey"  type="textarea"></el-input>
  </el-form-item>
  <el-form-item label="应用密钥" prop="appPrivateKey">
    <el-input v-model="config.appPrivateKey"  type="textarea"></el-input>
  </el-form-item>
  <el-divider>支付回调</el-divider>
  <el-form-item label="支付回调" prop="payNotifyUrl">
    <el-input v-model="config.payNotifyUrl"  type="textarea"></el-input>
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="updateConfig">更新配置</el-button>
  </el-form-item>
</el-form>
  </el-card>
</template>

<style scoped="scoped">
  ::v-deep .line {
    text-align: center;
  }
</style>
<script>
import { getZfbConfig, updateZfbConfig } from '@/api/third'

export default {
  name: 'zfbConfig',
  data() {
    return {
			config:{},
			loading : true,
      rules: {
        appId: [{ required: true, message: '请填写商户号', trigger: 'blur' }],
        zfbPublicKey: [{ required: true, message: '请填写支付宝公钥', trigger: 'blur' }],
        appPublicKey: [{ required: true, message: '请填写应用公钥', trigger: 'blur' }],
      	appPrivateKey: [{ required: true, message: '请填写应用密钥', trigger: 'blur' }],
      	payNotifyUrl: [{ required: true, message: '请填写支付回调', trigger: 'blur' }]
      }
    }
  },
  created() {
    this.get()
  },
  methods: {
    get() {
      getZfbConfig().then(response => {
        this.config = response.data
        if(!this.config) this.config = {}
    		this.loading = false
      })
    },
    updateConfig() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
    			var formData = Object.assign({}, this.config);
    			formData.addTime = null
    			formData.updTime = null
          updateZfbConfig(formData).then(() => {
            this.$notify({
              title: '成功',
              message: '更改配置成功',
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
