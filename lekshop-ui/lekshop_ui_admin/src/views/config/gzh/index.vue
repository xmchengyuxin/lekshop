<template>
  <div class="app-container">
     <el-card shadow="hover">
         <el-tabs v-model="activeName">
          <el-tab-pane label="公众号设置" name="first">
            <el-form ref="form" :model="gzhConfig" label-width="240px" label-position="right">
                <el-form-item label="公众号名称" prop="gzhName" :rules="[{ required: true, message: '请填写公众号名称', trigger: 'blur' }]">
                    <el-input v-model="gzhConfig.gzhName" style="width: 200px;"></el-input>
                </el-form-item>
                <el-form-item label="公众号二维码" prop="gzhImg" :rules="[{ required: true, message: '请上传公众号二维码', trigger: 'blur' }]">
                    <Upload v-model="gzhConfig.gzhImg" />
                    <ImageSource :mul="false" v-model="gzhConfig.gzhImg" />
                </el-form-item>
                <!-- <el-form-item label="接受任务是否需要关注公众号" prop="buyerStatus" :rules="[{ required: true, message: '请选择开关', trigger: 'blur' }]">
                    <el-switch :active-value="1" :inactive-value="0" v-model="gzhConfig.buyerStatus"></el-switch>
                    <span class="tips">*选是，必须关注公众号才能接单</span>
                </el-form-item>
                <el-form-item label="发布任务是否需要关注公众号" prop="sellerStatus" :rules="[{ required: true, message: '请选择开关', trigger: 'blur' }]">
                    <el-switch :active-value="1" :inactive-value="0" v-model="gzhConfig.sellerStatus"></el-switch>
                    <span class="tips">*选是，必须关注公众号才能发单</span>
                </el-form-item> -->
                <el-form-item label="关注公众号流程" prop="gzhContent" style="width: 70%;"  :rules="[{ required: true, message: '请填写关注公众号流程', trigger: 'blur' }]">
                  <Tinymce ref="editor" v-model="gzhConfig.gzhContent" :height="300" />
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="updateConfig">保存</el-button>
               </el-form-item>
            </el-form>
          </el-tab-pane>
          <el-tab-pane label="短信通知" name="second">
            <el-form ref="form" :model="gzhConfig" label-width="240px" label-position="right">
                <el-form-item label="是否开启短信通知" prop="smsNoticeStatus" :rules="[{ required: true, message: '请选择开关', trigger: 'blur' }]">
                    <el-switch :active-value="1" :inactive-value="0" v-model="gzhConfig.smsNoticeStatus"></el-switch>
                    <span class="tips">*短信通知开关</span>
                </el-form-item>

                <!-- <el-form-item label="派单通知" prop="smsNewTask" :rules="[{ required: true, message: '请选择开关', trigger: 'blur' }]">
                    <el-switch :active-value="1" :inactive-value="0" v-model="gzhConfig.smsNewTask"></el-switch>
                    <span class="tips">*接到新的任务哦！请尽快登录操作！</span>
                </el-form-item>

                <el-form-item label="评价通知" prop="smsNeedComment" :rules="[{ required: true, message: '请选择开关', trigger: 'blur' }]">
                    <el-switch :active-value="1" :inactive-value="0" v-model="gzhConfig.smsNeedComment"></el-switch>
                    <span class="tips">*尊敬的用户，您有一条订单需要评价，请及时登录账号处理。</span>
                </el-form-item>

                <el-form-item label="订单评价驳回" prop="smsCommentReject" :rules="[{ required: true, message: '请选择开关', trigger: 'blur' }]">
                    <el-switch :active-value="1" :inactive-value="0" v-model="gzhConfig.smsCommentReject"></el-switch>
                    <span class="tips">*尊敬的用户，您有一个订单被商家驳回了，请及时登录账号处理。</span>
                </el-form-item>

                <el-form-item label="订单超时提醒" prop="smsOrderWillQuit" :rules="[{ required: true, message: '请选择开关', trigger: 'blur' }]">
                    <el-switch :active-value="1" :inactive-value="0" v-model="gzhConfig.smsOrderWillQuit"></el-switch>
                    <span class="tips">*尊敬的用户，您有一个订单即将超时，请及时登录账号处理。</span>
                </el-form-item> -->

                <el-form-item label="账号禁用" prop="smsCloseAccount" :rules="[{ required: true, message: '请选择开关', trigger: 'blur' }]">
                    <el-switch :active-value="1" :inactive-value="0" v-model="gzhConfig.smsCloseAccount"></el-switch>
                    <span class="tips">*你的账号被禁用，如果疑问，可咨询客服</span>
                </el-form-item>

                <el-form-item label="提现成功通知" prop="smsWithdrawSus" :rules="[{ required: true, message: '请选择开关', trigger: 'blur' }]">
                    <el-switch :active-value="1" :inactive-value="0" v-model="gzhConfig.smsWithdrawSus"></el-switch>
                    <span class="tips">*尊敬的用户，您的提现已经成功到账，请及时查收。</span>
                </el-form-item>

                <el-form-item label="提现失败通知" prop="smsWithdrawFail" :rules="[{ required: true, message: '请选择开关', trigger: 'blur' }]">
                    <el-switch :active-value="1" :inactive-value="0" v-model="gzhConfig.smsWithdrawFail"></el-switch>
                    <span class="tips">*尊敬的用户，您的提现未能完成，请及时登录账号处理。</span>
                </el-form-item>

                <!-- <el-form-item label="订单驳回通知" prop="smsOrderReject" :rules="[{ required: true, message: '请选择开关', trigger: 'blur' }]">
                    <el-switch :active-value="1" :inactive-value="0" v-model="gzhConfig.smsOrderReject"></el-switch>
                    <span class="tips">*尊敬的用户，您有一个订单被商家驳回了，请及时登录账号处理。</span>
                </el-form-item>

                <el-form-item label="确认收货通知" prop="smsConfirmOrder" :rules="[{ required: true, message: '请选择开关', trigger: 'blur' }]">
                    <el-switch :active-value="1" :inactive-value="0" v-model="gzhConfig.smsConfirmOrder"></el-switch>
                    <span class="tips">*尊敬的用户，您有一条订单需要确认收货，请及时登录账号处理。</span>
                </el-form-item> -->
                <el-form-item>
                  <el-button type="primary" @click="updateConfig">保存</el-button>
               </el-form-item>
            </el-form>
          </el-tab-pane>
        </el-tabs>

      </el-card>
</div>
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
  import Tinymce from '@/components/Tinymce'
import { getParams, updateParams } from '@/api/config'
import Upload from '@/components/Upload/singleImage'
import ImageSource from '@/components/Upload/imageSource'

export default {
  name: 'configGzh',
  components: { Upload, ImageSource, Tinymce },
  data() {
    return {
			gzhConfig:{},
			loading : true,
      activeName: 'first'
    }
  },
  created() {
    this.get()
  },
  methods: {
    get() {
      getParams({key: 'config_gzh'}).then(response => {
        this.gzhConfig = response.data
        if(!this.gzhConfig){
          this.gzhConfig = {}
        }
      })
    },
    updateConfig() {
        this.$refs['form'].validate((valid) => {
          if (valid) {
            let formData =Object.assign({}, this.gzhConfig)
            formData.addTime = null
            formData.updTime = null
            updateParams({key: 'config_gzh', jsonData: JSON.stringify(formData)}).then(() => {
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
