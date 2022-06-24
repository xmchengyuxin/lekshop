<template>
  <el-card shadow="hover" style="margin: 10px;">
    <el-alert
        class="warn-content"
        title="全局样式"
        type="success"
        :closable="false">
       客户端初始风格，也可以在节日、活动时修改
      </el-alert>
      <br/>
    <el-form ref="form" :model="config" label-width="150px" label-position="right" >
      <el-tabs  v-model="activeName">
          <el-tab-pane label="首页" name="first">
            <el-form-item label="首页背景图" prop="index_background">
              <Upload v-model="config.index_background" />
              <ImageSource :mul="false" v-model="config.index_background" />
            </el-form-item>
            <el-form-item label="首页导航颜色" prop="index_nav_color">
              <el-color-picker
                v-model="config.index_nav_color"
                show-alpha
                :predefine="predefineColors">
              </el-color-picker>
            </el-form-item>
            <el-form-item label="首页导航字体颜色" prop="index_nav_font_color">
              <el-color-picker
                v-model="config.index_nav_font_color"
                show-alpha
                :predefine="predefineColors">
              </el-color-picker>
            </el-form-item>
            <el-form-item label="是否显示扫一扫" prop="index_show_hide_scan" >
                <el-switch active-value="1" inactive-value="0" v-model="config.index_show_hide_scan"></el-switch>
            </el-form-item>
            <el-form-item label="是否显示优惠券入口" prop="index_show_hide_coupon" >
                <el-switch active-value="1" inactive-value="0" v-model="config.index_show_hide_coupon"></el-switch>
            </el-form-item>
            <el-form-item label="是否显示分类入口" prop="index_show_hide_cate" >
                <el-switch active-value="1" inactive-value="0" v-model="config.index_show_hide_cate"></el-switch>
            </el-form-item>
            <el-form-item label="是否显示类目导航" prop="index_show_hide_category" >
                <el-switch active-value="1" inactive-value="0" v-model="config.index_show_hide_category"></el-switch>
            </el-form-item>
          </el-tab-pane>
          <el-tab-pane label="逛逛页样式" name="second">
            <el-form-item label="逛逛导航颜色" prop="discovery_nav_color">
              <el-color-picker
                v-model="config.discovery_nav_color"
                show-alpha
                :predefine="predefineColors">
              </el-color-picker>
            </el-form-item>
            <el-form-item label="逛逛导航字体颜色" prop="discovery_nav_font_color">
              <el-color-picker
                v-model="config.discovery_nav_font_color"
                show-alpha
                :predefine="predefineColors">
              </el-color-picker>
            </el-form-item>
          </el-tab-pane>
          <el-tab-pane label="用户中心样式" name="third">
            <el-form-item label="个人中心背景图" prop="user_center_nav_background">
              <Upload v-model="config.user_center_nav_background" />
              <ImageSource :mul="false" v-model="config.user_center_nav_background" />
            </el-form-item>
          </el-tab-pane>
        </el-tabs>
      <el-form-item>
        <el-button type="primary" size="mini" @click="updateConfig">保存</el-button>
        <el-button type="danger" size="mini" @click="getDefault()">重置</el-button>
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
import { getGlobalStyle, updateGlobalStyle, getDefaultGlobalStyle } from '@/api/floor'
import Upload from '@/components/Upload/singleImage'
import ImageSource from '@/components/Upload/imageSource'

export default {
  name: 'globalStyle',
  components: { Upload, ImageSource },
  data() {
    return {
			config:{},
      activeName: 'first',
      predefineColors: [
        '#ff4500',
        '#ff8c00',
        '#ffd700',
        '#90ee90',
        '#00ced1',
        '#1e90ff',
        '#c71585',
        'rgba(255, 69, 0, 0.68)',
        'rgb(255, 120, 0)',
        'hsv(51, 100, 98)',
        'hsva(120, 40, 94, 0.5)',
        'hsl(181, 100%, 37%)',
        'hsla(209, 100%, 56%, 0.73)',
        '#c7158577'
      ]
    }
  },
  created() {
    this.get()
  },
  methods: {
    get() {
      getGlobalStyle().then(response => {
        this.config = response.data
        if(!this.config){
          this.config = {}
        }
      })
    },
    getDefault(){
      getDefaultGlobalStyle().then(response => {
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
            updateGlobalStyle(formData).then(() => {
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
