<template>
  <div class="createPost-container" style="position:relative;left:-100px;width: 1000px;margin: 20px auto;padding: 30px 100px 50px 70px; border: 1px solid #f4f4f4;">
		<code v-if="config.platform == 1">
      本接口使用礼品网, 详情请查阅<a href="http://kbw.koo49.com/shop/index" target="_blank">《礼品网相关文档》</a>
		</code>
    <code v-if="config.platform == 2">
      本接口使用大禹电商, 详情请查阅<a href="http://da.tmlipin.net" target="_blank">《大禹电商相关文档》</a>
    </code>
    <code v-if="config.platform == 3">
      本接口使用快递礼品网, 详情请查阅<a href="http://hei.kd1212.com" target="_blank">《快递礼品网相关文档》</a>
    </code>
<el-form ref="form" :rules="rules" :model="config" v-loading="loading" label-width="140px" label-position="right">
  <el-form-item label="平台" prop="platform">
    <el-select v-model="config.platform" placeholder="请选择">
        <el-option label="小乐空包网" :value="1"></el-option>
        <el-option label="大禹电商" :value="2"></el-option>
        <el-option label="快递礼品网" :value="3"></el-option>
      </el-select>
  </el-form-item>
  <el-form-item label="appId" prop="appId">
    <el-input v-model="config.appId"></el-input>
  </el-form-item>
  <el-form-item label="密码" prop="password">
    <el-input type="password" v-model="config.password"></el-input>
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
import { getGiftConfig, updateGiftConfig, getGiftLeft } from '@/api/third'

export default {
  name: 'gift',
  data() {
    return {
			config:{},
			loading : true,
      rules: {
        platform: [{ required: true, message: '请选择礼品平台', trigger: 'change' }],
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
      getGiftConfig().then(response => {
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
          updateGiftConfig(formData).then(() => {
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
      getGiftLeft().then(response => {
        this.leftNum = response.data
      })
    },
  }
}
</script>
