<template>
  <div class="createPost-container" style="position:relative;left:-100px;width: 1000px;margin: 20px auto;padding: 30px 100px 50px 70px; border: 1px solid #f4f4f4;">
		<code>
      短信服务使用阿里云短信服务通道,详细细心请查阅<a href="https://www.aliyun.com/product/sms" target="_blank">《阿里云短信服务官方文档》</a>
		</code>
<el-form ref="form" :rules="rules" :model="config" v-loading="loading" label-width="120px" label-position="right">
  <el-form-item label="账号" prop="account">
    <el-input v-model="config.account"></el-input>
  </el-form-item>
  <el-form-item label="密码" prop="password">
    <el-input v-model="config.password" type="password"></el-input>
  </el-form-item>
  <el-form-item label="请求URL" prop="sendUrl">
    <el-input v-model="config.sendUrl"></el-input>
  </el-form-item>
  <el-form-item label="端口号" prop="serverPort">
    <el-input v-model="config.serverPort"></el-input>
  </el-form-item>
  <el-form-item label="短信签名" prop="signName">
    <el-input v-model="config.signName"></el-input>
  </el-form-item>
  <el-form-item label="通用模板ID" prop="commonTemplateId">
    <el-input v-model="config.commonTemplateId"></el-input>
  </el-form-item>
  <el-form-item label="注册模板ID" prop="registerTemplateId">
    <el-input v-model="config.registerTemplateId"></el-input>
  </el-form-item>
  <el-form-item label="绑定模板ID" prop="bindTemplateId">
    <el-input v-model="config.bindTemplateId"></el-input>
  </el-form-item>
  <el-form-item label="忘记密码模板ID" prop="forgetTemplateId">
    <el-input v-model="config.forgetTemplateId"></el-input>
  </el-form-item>
  <el-form-item label="密码通知模板ID" prop="passwordTemplateId">
    <el-input v-model="config.passwordTemplateId" />
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
import { getSmsConfig, updateSmsConfig } from '@/api/third'

export default {
  name: 'smsConfig',
  data() {
    return {
			config:{},
			loading : true,
      rules: {
        account: [{ required: true, message: '请填写账号', trigger: 'blur' }],
        password: [{ required: true, message: '请填写密码', trigger: 'blur' }],
        sendUrl: [{ required: true, message: '请填写请求URL', trigger: 'blur' }],
      	serverPort: [{ required: true, message: '请填写请求端口', trigger: 'blur' }],
      	signName: [{ required: true, message: '请填写应用签名', trigger: 'blur' }]
      }
    }
  },
  created() {
    this.get()
  },
  methods: {
    get() {
      getSmsConfig().then(response => {
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
          updateSmsConfig(formData).then(() => {
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
