<template>
  <div class="app-container">
		<code>
		  本系统客服系统为融云智齿客服系统,你需要到第三方平台融云，注册开发账户，并配置好信息~
		</code>
<el-form ref="form" :rules="rules" :model="config" v-loading="loading" label-width="120px" style="display: inline-block; width:500px;">
  <el-form-item label="APPID" prop="appId">
    <el-input v-model="config.appId"></el-input>
  </el-form-item>
	<el-form-item label="APPSECRET" prop="appSecret">
	  <el-input v-model="config.appSecret"></el-input>
	</el-form-item>
	<el-form-item label="客服ID" prop="kefuId">
	  <el-input v-model="config.kefuId"></el-input>
	</el-form-item>
  <el-form-item label="客服默认头像" prop="csIcon" style="display: inline-block; width:800px;">
     <Upload v-model="config.csIcon" />
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="updateConfig">更新配置</el-button>
  </el-form-item>
</el-form>
</div>
</template>

<script>
import { getConfig, updateConfig } from '@/api/kefu'
import Upload from '@/components/Upload/singleImage'

export default {
  name: 'kefuConfig',
	components: { Upload },
  data() {
    return {
			config:{},
			loading : true,
      rules: {
        appId: [{ required: true, message: '请填写APPID', trigger: 'blur' }],
        appSecret: [{ required: true, message: '请填写APPSECRET', trigger: 'blur' }],
        kefuId: [{ required: true, message: '请填写客服ID', trigger: 'blur' }],
				csIcon: [{ required: true, message: '请填写客服默认头像', trigger: 'blur' }]
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
