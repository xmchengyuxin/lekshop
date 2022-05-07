<template>
  <el-card shadow="hover" style="margin: 10px;">
    <el-alert
        class="warn-content"
        title="订单时间配置"
        type="success"
        :closable="false">
       用于订单相关操作时间限制
      </el-alert>
      <br/>
    <el-form ref="form" :rules="rules" :model="config" label-width="140px" label-position="right" >
      <el-form-item label="未支付自动取消时间" prop="autoCancelMinutes" :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
        <el-input v-model="config.autoCancelMinutes" style="width: 200px;"></el-input>
        <span class="tips">*订单多久未支付自动取消，单位：分钟</span>
      </el-form-item>
      <el-form-item label="自动收货时间" prop="autoReceiveDay" :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
        <el-input v-model="config.autoReceiveDay" style="width: 200px;"></el-input>
        <span class="tips">*订单发货后多久自动确认收货，单位：天</span>
      </el-form-item>
      <el-form-item label="自动评论时间" prop="autoCommentDay" :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
        <el-input v-model="config.autoCommentDay" style="width: 200px;"></el-input>
        <span class="tips">*订单完成后多久自动好评，单位：天</span>
      </el-form-item>
      <el-form-item label="最后售后时间" prop="lastRefundDay" :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
        <el-input v-model="config.lastRefundDay" style="width: 200px;"></el-input>
        <span class="tips">*订单完成后多久不再申请售后，单位：天</span>
      </el-form-item>
      <el-form-item label="自动同意售后时间" prop="autoAgreeRefundDay" :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
        <el-input v-model="config.autoAgreeRefundDay" style="width: 200px;"></el-input>
        <span class="tips">*买家申请售后后，卖家多久未处理自动同意，单位：天</span>
      </el-form-item>
      <el-form-item label="买家退货时间" prop="buyerRefundDay" :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
        <el-input v-model="config.buyerRefundDay" style="width: 200px;"></el-input>
        <span class="tips">*卖家同意售后后，买家多久未进行退货则售后取消，单位：天</span>
      </el-form-item>
      <el-form-item label="卖家收货时间" prop="sellerReceiveDay" :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
        <el-input v-model="config.sellerReceiveDay" style="width: 200px;"></el-input>
        <span class="tips">*买家进行退货后，卖家多久未处理则自动同意，单位：天</span>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="updateConfig">保存</el-button>
      </el-form-item>
    </el-form>
</el-card>
</template>

<style scoped="scoped">
  ::v-deep .line {
    text-align: center;
  }
  .tips{
    font-size: 12px;
    color:#999;
    margin-left: 5px;
  }
</style>
<script>
import { getParams, updateParams } from '@/api/config'

export default {
  name: 'smsConfig',
  data() {
    return {
			config:{},
      rules: {
        account: [{ required: true, message: '请填写账号', trigger: 'blur' }],
        password: [{ required: true, message: '请填写密码', trigger: 'blur' }],
        sendUrl: [{ required: true, message: '请填写请求URL', trigger: 'blur' }],
      	serverPort: [{ required: true, message: '请填写请求端口', trigger: 'blur' }],
      	signName: [{ required: true, message: '请填写应用签名', trigger: 'blur' }]
      }
    }
  },
  created() {
    this.get()
  },
  methods: {
    get() {
      getParams({key: 'config_order'}).then(response => {
        this.config = response.data
        if(!this.config){
          this.config = {}
        }
      })
    },
    updateConfig() {
        this.$refs['form'].validate((valid) => {
          if (valid) {
            let formData =Object.assign({}, this.config)
            formData.addTime = null
            formData.updTime = null
            updateParams({key: 'config_order', jsonData: JSON.stringify(formData)}).then(() => {
              this.$notify({
                title: '成功',
                message: '保存成功',
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
