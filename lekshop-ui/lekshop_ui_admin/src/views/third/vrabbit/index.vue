<template>
  <div class="createPost-container" style="position:relative;left:-100px;width: 1000px;margin: 20px auto;padding: 30px 100px 50px 70px; border: 1px solid #f4f4f4;">
		<code>
      本接口使用V兔工具, 详情请查阅<a href="http://doc.gongju.taoxiaomao.top/taobao" target="_blank">《V兔工具相关文档》</a>
		</code>
<el-form ref="form" :rules="rules" :model="config" v-loading="loading" label-width="140px" label-position="right">
  <el-form-item label="appId" prop="appId">
    <el-input v-model="config.appId"></el-input>
  </el-form-item>
  <el-form-item label="密钥" prop="secret">
    <el-input type="password" v-model="config.secret"></el-input>
  </el-form-item>
  <el-form-item label="账户余额" prop="balance">
    <span class="tips">{{leftNumForm.balance}}</span>
  </el-form-item>
  <el-form-item label="旺旺打标剩余次数" prop="searchpushNum">
    <span class="tips">{{leftNumForm.searchpushNum}}</span>
  </el-form-item>
  <el-form-item label="淘宝详情剩余次数" prop="iteminfoNum">
    <span class="tips">{{leftNumForm.iteminfoNum}}</span>
  </el-form-item>
  <el-form-item label="拼多多详情剩余次数" prop="pddGoodsInfoNum">
    <span class="tips">{{leftNumForm.pddGoodsInfoNum}}</span>
  </el-form-item>
  <el-form-item label="淘口令解析剩余次数" prop="taoTokenAnalysisNum">
    <span class="tips">{{leftNumForm.taoTokenAnalysisNum}}</span>
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
import { getVrabbitConfig, updateVrabbitConfig, getVrabbitLeft } from '@/api/third'

export default {
  name: 'vrabbit',
  data() {
    return {
			config:{},
			loading : true,
      rules: {
        appId: [{ required: true, message: '请填写appId', trigger: 'blur' }],
        secret: [{ required: true, message: '请填写密钥', trigger: 'blur' }],
      },
      leftNumForm: {}
    }
  },
  created() {
    this.get()
    this.getVrabbitLeft()
  },
  methods: {
    get() {
      getVrabbitConfig().then(response => {
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
          updateVrabbitConfig(formData).then(() => {
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
    getVrabbitLeft() {
      getVrabbitLeft().then(response => {
        this.leftNumForm = response.data
      })
    },
  }
}
</script>
