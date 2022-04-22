<template>
  <div class="app-container">
    <el-tabs  v-model="activeGroup" @tab-click="handleClickGroup">
      <el-tab-pane v-for="(item,index) in memberGroupOptions" :label="item.name" :name="item.id+''">
      </el-tab-pane>
    </el-tabs>


      <el-form :inline="true" ref="form" :model="missionConfig" label-width="240px" label-position="right">
        <el-card class="box-card" shadow="hover" v-if="activeBuyerOrShop == 1">
          <div slot="header" class="clearfix">
            <el-form-item label="买家任务佣金发放" prop="missionType" :rules="[{ required: true, message: '请选择是否开启', trigger: 'change' }]">
                 <el-radio-group v-model="missionConfig.missionType" size="small">
                    <el-radio :label="1" border>按百分比</el-radio>
                    <el-radio :label="2" border>按固定金额</el-radio>
                  </el-radio-group>
                <span class="tips">*请在运营前确认好佣金模式, 当有待完成的订单数据时请谨慎切换</span>
            </el-form-item>
          </div>
          <div class="text item">
              <el-form-item label="仅有1级推荐人(上线)的参数" prop="buyerFinishRateOne" :rules="[{ required: true, message: '请填写奖励', trigger: 'blur' }]">
                  <el-input v-model="missionConfig.buyerFinishRateOne" style="width: 100px;"></el-input> {{missionConfig.missionType == 1 ? '%' : '元'}}
              </el-form-item>
              <el-form-item label="仅有2级推荐人(上上线)的参数" prop="buyerFinishRateTwo" :rules="[{ required: true, message: '请填写奖励', trigger: 'blur' }]">
                  <el-input v-model="missionConfig.buyerFinishRateTwo" style="width: 100px;"></el-input> {{missionConfig.missionType == 1 ? '%' : '元'}}
              </el-form-item>
              <el-form-item label="3级以上推荐人(≥3级上线)的参数" prop="buyerFinishRateThree" :rules="[{ required: true, message: '请填写奖励', trigger: 'blur' }]">
                  <el-input v-model="missionConfig.buyerFinishRateThree" style="width: 100px;"></el-input> {{missionConfig.missionType == 1 ? '%' : '元'}}
              </el-form-item>
              <el-form-item label="推荐有效天数" prop="buyerValidDay" :rules="[{ required: true, message: '请填写有效天数', trigger: 'blur' }]">
                  <el-input v-model="missionConfig.buyerValidDay" style="width: 100px;"></el-input> 天
                  <span class="tips">*在推荐有效期内完成任务才能获得奖励</span>
              </el-form-item>
          </div>
        </el-card>
        <el-card class="box-card" shadow="hover" v-if="activeBuyerOrShop == 2">
          <div slot="header" class="clearfix">
            <!-- <span>发布刷单任务奖励参数</span> -->
            <el-form-item label="商家完成任务佣金发放" prop="missionType" :rules="[{ required: true, message: '请选择是否开启', trigger: 'change' }]">
                 <el-radio-group v-model="missionConfig.missionType" size="small">
                    <el-radio :label="1" border>按百分比</el-radio>
                    <el-radio :label="2" border>按固定金额</el-radio>
                  </el-radio-group>
                <span class="tips">*请在运营前确认好佣金模式, 当有待完成的订单数据时请谨慎切换</span>
            </el-form-item>
          </div>
          <div class="text item">
              <el-form-item label="仅有1级推荐人(上线)的参数" prop="sellerFinishRateOne" :rules="[{ required: true, message: '请填写奖励', trigger: 'blur' }]">
                  <el-input v-model="missionConfig.sellerFinishRateOne" style="width: 100px;"></el-input> {{missionConfig.missionType == 1 ? '%' : '元'}}
              </el-form-item>
              <el-form-item label="仅有2级推荐人(上上线)的参数" prop="sellerFinishRateTwo" :rules="[{ required: true, message: '请填写奖励', trigger: 'blur' }]">
                  <el-input v-model="missionConfig.sellerFinishRateTwo" style="width: 100px;"></el-input> {{missionConfig.missionType == 1 ? '%' : '元'}}
              </el-form-item>
              <el-form-item label="3级以上推荐人(≥3级上线)的参数" prop="sellerFinishRateThree" :rules="[{ required: true, message: '请填写奖励', trigger: 'blur' }]">
                  <el-input v-model="missionConfig.sellerFinishRateThree" style="width: 100px;"></el-input> {{missionConfig.missionType == 1 ? '%' : '元'}}
              </el-form-item>
              <el-form-item label="推荐有效天数" prop="sellerValidDay" :rules="[{ required: true, message: '请填写有效天数', trigger: 'blur' }]">
                  <el-input v-model="missionConfig.sellerValidDay" style="width: 100px;"></el-input> 天
                  <span class="tips">*在推荐有效期内完成任务才能获得奖励</span>
              </el-form-item>
          </div>
        </el-card>
        <div style="text-align:center;margin-top: 5px;">
            <el-button type="primary" @click="updateConfig">保存</el-button>
        </div>
          <br/>
            <el-alert
                title="说明：可以根据会员组，分别设置奖励不同的买家最终任务佣金；（不想奖励，输入0即可）"
                type="error"
                description=""
                >
                 <br/>
                <p class="tips">
                下面以“订单完成后佣金发放为百分比方式”的参数解析举例：
                </p>
                <br/>
                <p class="tips">
                例如“仅有1级推荐人(上线)的参数”中的设置举个例子：10%    表示含任务奖励该任务的买家最终得到的佣金的10%金额给上线推荐人(假如1000元商品对应给买手最终佣金是20元，20*10%=2元给推荐人)；
                </p>
                <br/>
                <p class="tips">
                再如“仅有2级推荐人(上上线)的参数”中的设置例子：50%    上上线推荐佣金2*50%=1元；
                </p>
                <br/>
                <p class="tips">
                再如“3级推荐人的参数”中的设置例子：20%   上上上线推荐人奖1*20%=0.2元
                </p>
                <br/>
                <p class="tips">
                商家奖励参数设置与买家奖励基本一样了；
                </p>
                <br/>
                <p class="tips">
                注意：最多只奖励前三级佣金，法律上超过三级定性为传销了。
                </p>
              </el-alert>
      </el-form>


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
import { getMemberGroupSelector } from '@/api/member'
import { getParams, updateParams, getShopCateSelector } from '@/api/config'

export default {
  name: 'editConfigMission',
  data() {
    return {
			missionConfig:{},
			loading : true,
      memberGroupOptions:[],
      shopCateOptions:[],
      activeGroup: null,
      missionConfigId: null,
      activeBuyerOrShop: 1
    }
  },
  created() {
    this.missionConfigId = this.$route.params && this.$route.params.id
    this.getMemberGroupSelector()
  },
  methods: {
    getMemberGroupSelector(){
      getMemberGroupSelector().then(response => {
        this.memberGroupOptions = response.data
        if(this.memberGroupOptions && this.memberGroupOptions.length > 0){
          this.activeGroup = this.memberGroupOptions[0].id + ''
        }
        this.get(this.activeGroup)
      })
    },
    get(groupId) {
      getParams({key: 'config_mission', groupId: groupId,  missionConfigId: this.missionConfigId}).then(response => {
        this.missionConfig = response.data
        if(!this.missionConfig){
          this.missionConfig = {}
        }
      })
    },
    updateConfig() {
        this.$refs['form'].validate((valid) => {
          if (valid) {
            let formData =Object.assign({}, this.missionConfig)
            formData.groupId = this.activeGroup
            formData.missionConfigId = this.missionConfigId
            formData.addTime = null
            formData.updTime = null
            updateParams({key: 'config_mission', jsonData: JSON.stringify(formData)}).then(() => {
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
    handleClickGroup(){
      this.get(this.activeGroup)
      this.getMemberGroupType(this.activeGroup)
    },
    getMemberGroupType(groupId){
      this.memberGroupOptions.forEach((item)=>{
        if(groupId == item.id){
          this.activeBuyerOrShop = item.type
        }
      })
    },
  }
}
</script>
