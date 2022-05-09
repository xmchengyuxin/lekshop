<template>
  <div class="createPost-container" style="position:relative;left:-100px;width: 1000px;margin: 20px auto;padding: 30px 100px 50px 70px; border: 1px solid #f4f4f4;">
		<code>
      本系统使用到
      <a href="https://mp.weixin.qq.com/" target="_blank">《微信公众平台-小程序和公众号》</a>
      <a href="https://open.weixin.qq.com/" target="_blank">《微信开放平台-APP》</a>
      <a href="https://pay.weixin.qq.com/index.php" target="_blank">《微信商户平台-微信支付》</a>
		</code>
<el-form ref="form" :rules="rules" :model="config" v-loading="loading" label-width="130px" label-position="right">
  <!-- <h3>五代店微信配置</h3>
  <el-form-item label="小程序原始Id" prop="fiveOriginalId">
    <el-input v-model="config.fiveOriginalId"></el-input>
  </el-form-item>
  <el-form-item label="小程序AppId" prop="fiveXcxAppId">
    <el-input v-model="config.fiveXcxAppId"></el-input>
  </el-form-item>
  <el-form-item label="小程序Secret" prop="fiveXcxSecret">
    <el-input type="password" autocomplete="off" v-model="config.fiveXcxSecret"></el-input>
  </el-form-item> -->
 <!-- <el-form-item label="小程序原始Id" prop="originalId">
    <el-input v-model="config.originalId"></el-input>
  </el-form-item>
  <el-form-item label="小程序AppId" prop="xcxAppId">
    <el-input v-model="config.xcxAppId"></el-input>
  </el-form-item>
  <el-form-item label="小程序Secret" prop="xcxSecret">
    <el-input type="password" autocomplete="off" v-model="config.xcxSecret"></el-input>
  </el-form-item> -->
  <!-- <el-form-item label="骑手端AppId" prop="kfAppId">
    <el-input v-model="config.kfAppId"></el-input>
  </el-form-item>
  <el-form-item label="骑手端Secret" prop="kfSecret">
    <el-input type="password" autocomplete="off" v-model="config.kfSecret"></el-input>
  </el-form-item>
  <el-form-item label="商家端AppId" prop="kfAppId">
    <el-input v-model="config.shopKfAppId"></el-input>
  </el-form-item>
  <el-form-item label="商家端Secret" prop="kfSecret">
    <el-input type="password" autocomplete="off" v-model="config.shopKfSecret"></el-input>
  </el-form-item> -->
 <el-form-item label="公众号AppId" prop="gzhAppId">
    <el-input v-model="config.gzhAppId"></el-input>
  </el-form-item>
  <el-form-item label="公众号Secret" prop="gzhSecret">
    <el-input type="password" autocomplete="off" v-model="config.gzhSecret"></el-input>
  </el-form-item>
 <!-- <el-form-item label="微信商户号" prop="mchId">
    <el-input v-model="config.mchId"></el-input>
  </el-form-item>
  <el-form-item label="微信商户密钥" prop="mchKey">
    <el-input type="password" autocomplete="off" v-model="config.mchKey"></el-input>
  </el-form-item>
  <el-form-item label="支付回调" prop="payNotifyUrl">
    <el-input v-model="config.payNotifyUrl"></el-input>
  </el-form-item>
  <el-form-item label="API安全证书" prop="certPath">
    <el-input v-model="config.certPath"></el-input>
  </el-form-item> -->
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
import { getWeixinConfig, updateWeixinConfig } from '@/api/third'

export default {
  name: 'weixinConfig',
  data() {
    return {
			config:{},
			loading : true,
      rules: {
        fiveOriginalId: [{ required: true, message: '请填写五代店小程序原始Id', trigger: 'blur' }],
        fiveXcxAppId: [{ required: true, message: '请填写五代店小程序AppId', trigger: 'blur' }],
        fiveXcxSecret: [{ required: true, message: '请填写五代店小程序密钥', trigger: 'blur' }],
      	originalId: [{ required: true, message: '请填写小程序原始Id', trigger: 'blur' }],
      	xcxAppId: [{ required: true, message: '请填写小程序AppId', trigger: 'blur' }],
        xcxSecret: [{ required: true, message: '请填写小程序密钥', trigger: 'blur' }],
        kfAppId: [{ required: true, message: '请填写开放平台AppId', trigger: 'blur' }],
        kfSecret: [{ required: true, message: '请填写开放平台密钥', trigger: 'blur' }],
        shopKfAppId: [{ required: true, message: '请填写开放平台AppId', trigger: 'blur' }],
        shopKfSecret: [{ required: true, message: '请填写开放平台密钥', trigger: 'blur' }],
        gzhAppId: [{ required: true, message: '请填写公众号AppId', trigger: 'blur' }],
        gzhSecret: [{ required: true, message: '请填写公众号密钥', trigger: 'blur' }],
        mchId: [{ required: true, message: '请填写微信商户号', trigger: 'blur' }],
        mchKey: [{ required: true, message: '请填写微信商户密钥', trigger: 'blur' }],
        payNotifyUrl: [{ required: true, message: '请填写支付回调地址', trigger: 'blur' }],
        certPath: [{ required: true, message: '请填写API安全证书', trigger: 'blur' }],
      }
    }
  },
  created() {
    this.get()
  },
  methods: {
    get() {
      getWeixinConfig().then(response => {
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
          updateWeixinConfig(formData).then(() => {
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
