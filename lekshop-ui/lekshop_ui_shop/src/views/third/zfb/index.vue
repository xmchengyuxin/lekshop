<template>
  <div class="createPost-container" style="position:relative;left:-100px;width: 1000px;margin: 20px auto;padding: 30px 100px 50px 70px; border: 1px solid #f4f4f4;">
		<code>
      支付宝支付请查阅<a href="https://open.alipay.com" target="_blank">《支付宝开放平台》</a>
		</code>
<el-form ref="form" :rules="rules" :model="config" v-loading="loading" label-width="140px" label-position="right">
  <el-divider>骑手端</el-divider>
  <el-form-item label="骑手端商户号" prop="appId">
    <el-input v-model="config.appId"></el-input>
  </el-form-item>
  <el-form-item label="骑手端支付宝公钥" prop="zfbPublicKey">
    <el-input v-model="config.zfbPublicKey" type="textarea"></el-input>
  </el-form-item>
  <el-form-item label="骑手端应用公钥" prop="appPublicKey">
    <el-input v-model="config.appPublicKey"  type="textarea"></el-input>
  </el-form-item>
  <el-form-item label="骑手端应用密钥" prop="appPrivateKey">
    <el-input v-model="config.appPrivateKey"  type="textarea"></el-input>
  </el-form-item>
  <el-divider>商家端</el-divider>
  <el-form-item label="商家商户号" prop="shopAppId">
    <el-input v-model="config.shopAppId"></el-input>
  </el-form-item>
  <el-form-item label="商家端支付宝公钥" prop="shopZfbPublicKey">
    <el-input v-model="config.shopZfbPublicKey" type="textarea"></el-input>
  </el-form-item>
  <el-form-item label="商家端应用公钥" prop="shopAppPublicKey">
    <el-input v-model="config.shopAppPublicKey"  type="textarea"></el-input>
  </el-form-item>
  <el-form-item label="商家端应用密钥" prop="shopAppPrivateKey">
    <el-input v-model="config.shopAppPrivateKey"  type="textarea"></el-input>
  </el-form-item>
  <el-divider>支付回调</el-divider>
  <el-form-item label="支付回调" prop="payNotifyUrl">
    <el-input v-model="config.payNotifyUrl"  type="textarea"></el-input>
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="updateConfig">更新配置</el-button>
  </el-form-item>
</el-form>
</div>
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
        shopAppId: [{ required: true, message: '请填写商户号', trigger: 'blur' }],
        shopZfbPublicKey: [{ required: true, message: '请填写支付宝公钥', trigger: 'blur' }],
        shopAppPublicKey: [{ required: true, message: '请填写应用公钥', trigger: 'blur' }],
        shopAppPrivateKey: [{ required: true, message: '请填写应用密钥', trigger: 'blur' }],
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
