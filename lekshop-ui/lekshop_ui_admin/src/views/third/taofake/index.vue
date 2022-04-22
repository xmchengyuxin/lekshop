<template>
  <div class="createPost-container" style="position:relative;left:-100px;width: 1000px;margin: 20px auto;padding: 30px 100px 50px 70px; border: 1px solid #f4f4f4;">

    <el-form ref="form" v-loading="loading" :rules="rules" :model="config" label-width="160px" label-position="right">
      <el-divider content-position="left">本接口使用淘发客, 详情请查阅<a href="http://www.taofake.com/" target="_blank">《淘发客相关文档》</a></el-divider>
      <el-form-item label="appId" prop="appId">
        <el-input v-model="config.appId" />
      </el-form-item>
      <el-form-item label="密钥" prop="secret">
        <el-input v-model="config.secret" type="password" />
      </el-form-item>
      <el-form-item label="账户可用次数" prop="leftNum">
        <span class="tips">{{ leftNum }}</span>
      </el-form-item>
      <br>
      <el-divider content-position="left">本接口使用订单侠, 详情请查阅<a href="https://www.dingdanxia.com" target="_blank">《订单侠相关文档》</a></el-divider>
      <el-form-item label="密钥" prop="secret">
        <el-input v-model="config.ddxAppKey" type="password" />
      </el-form-item>
      <el-form-item label="剩余点卡" prop="dingdanxiaNum">
        <span class="tips">{{ dingdanxiaNum }}</span>
      </el-form-item>
      <br>
      <el-divider content-position="left">接口使用切换</el-divider>
      <el-form-item label="淘口令识别使用接口" prop="tklMethod">
        <el-radio-group v-model="config.tklMethod" size="mini">
          <el-radio :label="1" border>淘发客</el-radio>
          <el-radio :label="2" border>订单侠</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="商品详情识别使用接口" prop="itemMethod">
        <el-radio-group v-model="config.itemMethod" size="mini">
          <el-radio :label="1" border>淘发客</el-radio>
          <el-radio :label="2" border>订单侠</el-radio>
        </el-radio-group>
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
import { getTaofakeConfig, updateTaofakeConfig, getTaofakeLeft } from '@/api/third'

export default {
  name: 'ValidateApi',
  data() {
    return {
      config: {},
      loading: true,
      rules: {
        appId: [{ required: true, message: '请填写appId', trigger: 'blur' }],
        secret: [{ required: true, message: '请填写密钥', trigger: 'blur' }]
      },
      leftNum: 0,
	  dingdanxiaNum: 0
    }
  },
  created() {
    this.get()
    this.getTaofakeLeft()
  },
  methods: {
    get() {
      getTaofakeConfig().then(response => {
        this.config = response.data
        if (!this.config) this.config = {}
    		this.loading = false
      })
    },
    updateConfig() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
    			var formData = Object.assign({}, this.config)
    			formData.addTime = null
    			formData.updTime = null
          updateTaofakeConfig(formData).then(() => {
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
    getTaofakeLeft() {
      getTaofakeLeft().then(response => {
        this.leftNum = response.data.taofakeNum
        this.dingdanxiaNum = response.data.dingdanxiaNum
      })
    }
  }
}
</script>
