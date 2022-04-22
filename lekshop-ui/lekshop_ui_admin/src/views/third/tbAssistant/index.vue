<template>
  <div class="createPost-container" style="position:relative;left:-100px;width: 1000px;margin: 20px auto;padding: 30px 100px 50px 70px; border: 1px solid #f4f4f4;">
		<code>
      本接口使用淘宝智能助手</a>
		</code>
<el-form ref="form" :rules="rules" :model="config" v-loading="loading" label-width="140px" label-position="right">
  <el-form-item label="appId" prop="appId">
    <el-input v-model="config.appId"></el-input>
  </el-form-item>
  <el-form-item label="密钥" prop="secret">
    <el-input type="password" v-model="config.secret"></el-input>
  </el-form-item>
  <el-form-item label="账户可用次数" prop="leftNum">
    <span class="tips">{{leftNum}}</span>
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
import { getTbAssistantConfig, updateTbAssistantConfig, getTbAssistantLeft } from '@/api/third'

export default {
  name: 'tbAssistant',
  data() {
    return {
			config:{},
			loading : true,
      rules: {
        appId: [{ required: true, message: '请填写appId', trigger: 'blur' }],
        secret: [{ required: true, message: '请填写密钥', trigger: 'blur' }],
      },
      leftNum: 0
    }
  },
  created() {
    this.get()
    this.getLeft()
  },
  methods: {
    get() {
      getTbAssistantConfig().then(response => {
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
          updateTbAssistantConfig(formData).then(() => {
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
    getLeft() {
      getTbAssistantLeft().then(response => {
        this.leftNum = response.data
      })
    },
  }
}
</script>
