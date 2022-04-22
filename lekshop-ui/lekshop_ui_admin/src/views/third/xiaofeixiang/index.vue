<template>
  <div class="createPost-container" style="position:relative;left:-100px;width: 1000px;margin: 20px auto;padding: 30px 100px 50px 70px; border: 1px solid #f4f4f4;">
		<code>
      本接口使用小飞象开放平台, 详情请查阅<a href="http://open.xiaofx.info" target="_blank">《小飞象开放平台相关文档》</a>
		</code>
<el-form ref="form" :rules="rules" :model="config" v-loading="loading" label-width="140px" label-position="right">
  <el-form-item label="appId" prop="appId">
    <el-input v-model="config.appId"></el-input>
  </el-form-item>
  <el-form-item label="密钥" prop="secret">
    <el-input type="password" v-model="config.secret"></el-input>
  </el-form-item>
  <!-- <el-form-item label="账户可用次数" prop="leftNum">
    <span class="tips">{{leftNum}}</span>
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
import { getXiaofeixiangConfig, updateXiaofeixiangConfig, getXiaofeixiangLeft } from '@/api/third'

export default {
  name: 'xiaofeixiang',
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
      getXiaofeixiangConfig().then(response => {
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
          updateXiaofeixiangConfig(formData).then(() => {
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
      getXiaofeixiangLeft().then(response => {
        this.leftNum = response.data
      })
    },
  }
}
</script>
