<template>
  <div class="app-container">
		<code>
		  你需要到第三方平台七牛云，注册七牛账户，并配置好信息~
		</code>
<el-form ref="form" :rules="rules" :model="config" v-loading="loading" label-width="100px" style="display: inline-block; width:500px;">
  <el-form-item label="accessKey" prop="accessKey">
    <el-input v-model="config.accessKey"></el-input>
  </el-form-item>
	<el-form-item label="secretKey" prop="secretKey">
	  <el-input v-model="config.secretKey"></el-input>
	</el-form-item>
  <el-form-item label="存储区域" prop="zone">
    <el-select v-model="config.zone" placeholder="请选择存储区域">
      <el-option label="华东" value="z0"></el-option>
      <el-option label="华北" value="z1"></el-option>
			<el-option label="华南" value="z2"></el-option>
			<el-option label="北美" value="na0"></el-option>
			<el-option label="东南亚" value="as0"></el-option>
    </el-select>
  </el-form-item>
  <el-form-item label="空间名称" prop="bucketName">
    <el-input v-model="config.bucketName"></el-input>
  </el-form-item>
	<el-form-item label="下载域名" prop="viewUrl">
	  <el-input v-model="config.viewUrl"></el-input>
	</el-form-item>
  <el-form-item label="读取方式" prop="isPublic">
    <el-radio-group v-model="config.isPublic">
      <el-radio :label="'1'" >私密读取</el-radio>
      <el-radio :label="'0'" >公共读取</el-radio>
    </el-radio-group>
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="updateConfig">更新配置</el-button>
  </el-form-item>
</el-form>
</div>
</template>

<script>
import { getConfig, updateConfig } from '@/api/qiniu'

export default {
  name: 'qiniuConfig',
  data() {
    return {
			config:{},
			loading : true,
      rules: {
        accessKey: [{ required: true, message: '请填写accessKey', trigger: 'blur' }],
        secretKey: [{ required: true, message: '请填写secretKey', trigger: 'blur' }],
        zone: [{ required: true, message: '请填写存储区域', trigger: 'change' }],
				bucketName: [{ required: true, message: '请填写空间名称', trigger: 'blur' }],
				viewUrl: [{ required: true, message: '请填写下载域名', trigger: 'blur' }],
				isPublic: [{ required: true, message: '请选择读取方式', trigger: 'change' }]
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
					var formData = {
						id: this.config.id,
						accessKey: this.config.accessKey,
						secretKey: this.config.secretKey,
						zone: this.config.zone,
						bucketName: this.config.bucketName,
						viewUrl: this.config.viewUrl,
						isPublic: this.config.isPublic
					}
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
