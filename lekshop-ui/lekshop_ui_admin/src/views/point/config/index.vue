<template>
  <el-card shadow="hover" style="margin: 10px;">
        <el-alert
            class="warn-content"
            title="温馨提示"
            type="success"
            show-icon
            :closable="false">
               用于设置积分获得的途径及比例
          </el-alert>
          <br/>
      <el-form ref="form" :model="config" v-loading="loading" label-width="120px" label-position="right" style="width: 60%;">
        <!-- <el-form-item label="积分商城入口" prop="pointStatus" :rules="[{ required: true, message: '请选择开关', trigger: 'blur' }]">
            <el-switch :active-value="1" :inactive-value="0" v-model="config.pointStatus"></el-switch>
            <span class="tips">*关闭后, 客户端将不显示积分商城入口</span>
        </el-form-item> -->
        <el-form-item label="签到积分" prop="signPoint" :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
          <el-input v-model="config.signPoint" style="width: 200px;"></el-input>
          <span class="tips">*签到一次获得多少积分</span>
        </el-form-item>
        <el-form-item label="消费积分比例" prop="consumePoint" :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
          <el-input v-model="config.consumePoint" style="width: 200px;"></el-input>
          <span class="tips">*每消费1元获得多少积分</span>
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
import { getConfig, updateConfig } from '@/api/point'

export default {
  name: 'pointConfig',
  data() {
    return {
			config:{},
			loading : true,
    }
  },
  created() {
    this.get()
  },
  methods: {
    get() {
      getConfig().then(response => {
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
