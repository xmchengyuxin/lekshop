<template>
  <el-card shadow="hover" style="margin: 10px;">
        <el-alert
            class="warn-content"
            title="温馨提示"
            type="success"
            show-icon
            :closable="false">
               本接口使用万维易源, 详情请查阅<a href="https://www.showapi.com" target="_blank">《万维易源相关文档》</a>
          </el-alert>
          <br/>
      <el-form ref="form" :rules="rules" :model="config" v-loading="loading" label-width="120px" label-position="right" style="width: 60%;">
        <el-form-item label="appId" prop="appId">
          <el-input v-model="config.appId"></el-input>
        </el-form-item>
        <el-form-item label="密钥" prop="secret">
          <el-input type="password" v-model="config.secret"></el-input>
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
import { getApproveConfig, updateApproveConfig } from '@/api/third'

export default {
  name: 'realnameApi',
  data() {
    return {
			config:{},
			loading : true,
      rules: {
        appId: [{ required: true, message: '请填写appId', trigger: 'blur' }],
        secret: [{ required: true, message: '请填写密钥', trigger: 'blur' }],
      }
    }
  },
  created() {
    this.get()
  },
  methods: {
    get() {
      getApproveConfig().then(response => {
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
          updateApproveConfig(formData).then(() => {
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
