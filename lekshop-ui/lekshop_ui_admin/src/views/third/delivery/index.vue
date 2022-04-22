<template>
  <div class="createPost-container" style="position:relative;left:-100px;width: 1000px;margin: 20px auto;padding: 30px 100px 50px 70px; border: 1px solid #f4f4f4;">
		<el-tabs v-model="activeName" @tab-click="handleClickTab">
			  <el-tab-pane label="顺丰同城配送" name="sf"></el-tab-pane>
				<el-tab-pane label="达达同城配送" name="dd"></el-tab-pane>
		</el-tabs>
<el-form ref="form" :rules="rules" :model="config" v-loading="loading" label-width="50px" label-position="right">
  <el-form-item label="appId" prop="appId">
    <el-input v-model="config.appId"></el-input>
  </el-form-item>
  <el-form-item label="密钥" prop="appSecret">
    <el-input v-model="config.appSecret"></el-input>
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
import { getDeliveryConfig, updateDeliveryConfig } from '@/api/third'

export default {
  name: 'deliveryConfig',
  data() {
    return {
			config:{},
      configMap:{},
			loading : true,
      rules: {
        appId: [{ required: true, message: '请填写APPID', trigger: 'blur' }],
        appSecret: [{ required: true, message: '请填写密钥', trigger: 'blur' }],
      },
      activeName:'sf'
    }
  },
  created() {
    this.get()
  },
  methods: {
    get() {
      getDeliveryConfig().then(response => {
        this.configMap = response.data
        if(!this.configMap){
          this.config = {cate: this.activeName}
        }else{
          this.config = this.configMap[this.activeName]
          if(!this.config){
            this.config = {cate: this.activeName}
          }
        }
    		this.loading = false
      })
    },
    updateConfig() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
    			var formData = Object.assign({}, this.config);
          formData.cate = this.activeName
    			formData.addTime = null
    			formData.updTime = null
          updateDeliveryConfig(formData).then(() => {
            this.get()
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
    handleClickTab(tab, event) {
    	this.activeName = tab.name
      this.config = this.configMap[this.activeName]
    },

  }
}
</script>
