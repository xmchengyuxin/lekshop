<template>
  <div class="app-container">
       <el-alert
           class="warn-content"
           title="温馨提示"
           type="success"
           show-icon
           :closable="false">
              <p>1.店铺开通后请先设置好店铺配置, 再进行运营</p>
              <p>2.不使用云打印或没有和快递公司签约，云打印配置、面单参数无需配置</p>
         </el-alert>
         <br/>
     <el-card shadow="hover">

         <el-tabs v-model="activeName">
          <el-tab-pane label="商家店铺配置" name="first">
            <el-form ref="form1" :model="config" label-width="120px" label-position="right">
                <el-form-item label="店铺类目样式" prop="cateStyle" :rules="[{ required: true, message: '请选择', trigger: 'blur' }]">
                    <el-radio-group v-model="config.cateStyle" size="small">
                      <el-radio :label="1" border>一级类目-大图</el-radio>
                      <el-radio :label="2" border>一级类目-九宫格</el-radio>
                      <el-radio :label="3" border>二级类目</el-radio>
                      <el-radio :label="4" border>多级类目样式</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="运费组合策略" prop="deliveryFeeType" :rules="[{ required: true, message: '请选择', trigger: 'blur' }]">
                    <el-radio-group v-model="config.deliveryFeeType" size="small">
                      <el-radio :label="1" border>运费叠加</el-radio>
                      <el-radio :label="2" border>以最低结算</el-radio>
                      <el-radio :label="3" border>以最高结算</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="updateConfig('form1')">保存</el-button>
               </el-form-item>
            </el-form>
          </el-tab-pane>

          <el-tab-pane label="快递100云打印配置" name="second">
            <el-form ref="form2" :model="config" label-width="140px" label-position="right">
                <el-form-item label="是否开启云打印	" prop="kdPrint" :rules="[{ required: true, message: '请选择开关', trigger: 'change' }]">
                    <el-switch :active-value="1" :inactive-value="0" v-model="config.kdPrint"></el-switch>
                    <span class="tips">*云打印开关</span>
                </el-form-item>

                <el-form-item label="是否云打印自动发货	" prop="kdDelivery" :rules="[{ required: true, message: '请选择开关', trigger: 'change' }]">
                    <el-switch :active-value="1" :inactive-value="0" v-model="config.kdDelivery"></el-switch>
                    <span class="tips">*云打印后是否自动发货</span>
                </el-form-item>

                <el-form-item label="Secret" prop="kdSecret" :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
                  <el-input v-model="config.kdSecret"></el-input>
                </el-form-item>

                <el-form-item label="授权KEY	" prop="kdKey" :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
                  <el-input v-model="config.kdKey"></el-input>
                </el-form-item>

                <el-form-item label="面单账号" prop="kdAccount" :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
                  <el-input v-model="config.kdAccount"></el-input>
                </el-form-item>

                <el-form-item label="面单密码" prop="kdPassword" :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
                  <el-input v-model="config.kdPassword"></el-input>
                </el-form-item>

                <el-form-item>
                  <el-button type="primary" @click="updateConfig('form2')">保存</el-button>
               </el-form-item>
            </el-form>
          </el-tab-pane>

          <el-tab-pane label="面单参数" name="three">
            <el-form ref="form3" :model="config" label-width="140px" label-position="right">
                <el-form-item label="打印设备码" prop="kdEquipmentId" :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
                  <el-input v-model="config.kdEquipmentId"></el-input>
                </el-form-item>

                <el-form-item label="模板ID" prop="kdTemplateId" :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
                  <el-input v-model="config.kdTemplateId"></el-input>
                </el-form-item>

                <el-form-item>
                  <el-button type="primary" @click="updateConfig('form3')">保存</el-button>
               </el-form-item>
            </el-form>
          </el-tab-pane>


          <el-tab-pane label="寄件人信息" name="four">
            <el-form ref="form4" :model="config" label-width="140px" label-position="right">
                <el-form-item label="姓名(店铺名)" prop="sendName" :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
                  <el-input v-model="config.sendName"></el-input>
                </el-form-item>

                <el-form-item label="固话/手机" prop="sendPhone" :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
                  <el-input v-model="config.sendPhone"></el-input>
                </el-form-item>

                <el-form-item label="寄件地址" prop="sendAddress" :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
                  <el-input type="textarea" v-model="config.sendAddress"></el-input>
                </el-form-item>

                <el-form-item>
                  <el-button type="primary" @click="updateConfig('form4')">保存</el-button>
               </el-form-item>
            </el-form>
          </el-tab-pane>


          <el-tab-pane label="退货信息" name="five">
            <el-form ref="form5" :model="config" label-width="140px" label-position="right">
                <el-form-item label="姓名(店铺名)" prop="returnName" :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
                  <el-input v-model="config.returnName"></el-input>
                </el-form-item>

                <el-form-item label="固话/手机" prop="returnPhone" :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
                  <el-input v-model="config.returnPhone"></el-input>
                </el-form-item>

                <el-form-item label="退货地址" prop="returnAddress" :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
                  <el-input type="textarea" v-model="config.returnAddress"></el-input>
                </el-form-item>

                <el-form-item>
                  <el-button type="primary" @click="updateConfig('form5')">保存</el-button>
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
import { getShopConfig, updateShopConfig } from '@/api/shop'

export default {
  name: 'shopConfig',
  components: {},
  data() {
    return {
			config:{},
			loading : true,
      activeName: 'first'
    }
  },
  created() {
    this.get()
  },
  methods: {
    get() {
      getShopConfig().then(response => {
        this.config = response.data
        if(!this.config){
          this.config = {}
        }
      })
    },
    updateConfig(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            let formData =Object.assign({}, this.config)
            formData.addTime = null
            formData.updTime = null
            updateShopConfig(formData).then(() => {
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
