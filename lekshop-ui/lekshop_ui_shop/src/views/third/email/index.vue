<template>
  <div class="app-container">
		<code>
		  请配置好邮箱基础信息~
		</code>
<el-form ref="form" :rules="rules" :model="config" v-loading="loading" label-width="120px" style="display: inline-block; width:500px;">
  <el-form-item label="邮箱服务器" prop="emailSmtp">
    <el-input v-model="config.emailSmtp"></el-input>
  </el-form-item>
	<el-form-item label="邮箱端口" prop="emailPort">
	  <el-input v-model="config.emailPort" ></el-input>
	</el-form-item>
	<el-form-item label="发送邮箱" prop="emailAccount">
	  <el-input v-model="config.emailAccount"></el-input>
	</el-form-item>
	<el-form-item label="邮箱密码" prop="emailPassword">
	  <el-input v-model="config.emailPassword" type="password"></el-input>
	</el-form-item>
	<el-form-item label="发送者昵称" prop="emailNickname">
	  <el-input v-model="config.emailNickname"></el-input>
	</el-form-item>
	<el-form-item label="标题" prop="title">
	  <el-input v-model="config.title"></el-input>
	</el-form-item>
	<el-form-item label="通用模板" prop="commonContent">
	  <el-input v-model="config.commonContent"></el-input>
	</el-form-item>
	<el-form-item label="注册模板" prop="registerContent">
	  <el-input v-model="config.registerContent"></el-input>
	</el-form-item>
	<el-form-item label="绑定模板" prop="bindContent">
	  <el-input v-model="config.bindContent"></el-input>
	</el-form-item>
	<el-form-item label="忘记密码模板" prop="forgetContent">
	  <el-input v-model="config.forgetContent"></el-input>
	</el-form-item>
  <el-form-item>
    <el-button type="primary" @click="updateConfig">更新配置</el-button>
  </el-form-item>
</el-form>
</div>
</template>

<script>
import { getConfig, updateConfig } from '@/api/email'

export default {
  name: 'qiniuConfig',
  data() {
    return {
			config:{},
			loading : true,
      rules: {
        emailSmtp: [{ required: true, message: '请填写邮箱服务器', trigger: 'blur' }],
        emailPort: [{ required: true, message: '请填写邮箱端口', trigger: 'blur' }],
        emailAccount: [{ required: true, message: '请填写发送邮箱', trigger: 'blur' }],
				emailPassword: [{ required: true, message: '请填写邮箱密码', trigger: 'blur' }],
				emailNickname: [{ required: true, message: '请填写发送者昵称', trigger: 'blur' }],
				title:[{ required: true, message: '请填写发送标题', trigger: 'blur' }]
      }
    }
  },
  created() {
    this.get()
  },
  methods: {
    get() {
      getConfig().then(response => {
        this.config = response.data
				this.loading = false
      })
    },
    updateConfig() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
					var formData = Object.assign({}, this.config);
					formData.addTime = null
					formData.updTime = null
          updateConfig(formData).then(() => {
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
