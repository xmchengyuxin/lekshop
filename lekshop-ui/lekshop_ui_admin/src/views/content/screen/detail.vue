<template>
  <div class="createPost-container" style="position:relative;left:-100px;width: 1000px;margin: 20px auto;padding: 30px 100px 50px 70px; border: 1px solid #f4f4f4;">
  <el-alert
      title="关于推送首页开屏/生日弹窗的配置"
      type="info"
      show-icon
      :closable="false"
      style="height: 60px;">
    </el-alert>
    <br>
    <el-tabs v-model="activeName" @tab-click="handleClickTab">
    	  <el-tab-pane label="首页开屏" name="first"></el-tab-pane>
    		<el-tab-pane label="生日弹窗" name="second"></el-tab-pane>
    </el-tabs>

<el-form v-if="activeName == 'first'" ref="indexForm" :rules="indexRules" :model="indexScreen" v-loading="loading" label-width="120px" label-position="right">
  <el-form-item label="开屏图片" prop="img">
    	<Upload v-model="indexScreen.img"/>
      <ImageSource :mul="false" v-model="indexScreen.img" />
  </el-form-item>
  <el-form-item label="跳转链接" prop="url">
    <el-select
        style="width:300px;"
        v-model="indexScreen.url"
        filterable
        allow-create
        default-first-option
        placeholder="请选择跳转模块或输入跳转链接">
        <el-option
          v-for="item in urlOptions"
          :key="item.key"
          :label="item.text"
          :value="item.key">
        </el-option>
      </el-select>
  </el-form-item>
  <el-form-item v-if="indexScreen.url == 'shop' " prop="shopId" label="选择门店">
    <el-select style="width: 150%;" filterable v-model="indexScreen.shopId" placeholder="请选择">
      <el-option v-for="item in shopOptions" :key="item.id" :label="item.name" :value="item.id">
      </el-option>
    </el-select>
  </el-form-item>
  <el-form-item v-if="indexScreen.url == 'buyFood' " label="选择商品" prop="foodId">
    <el-select
        style="width:300px;"
        v-model="indexScreen.foodId"
        filterable
        placeholder="请选择商品">
        <el-option
          v-for="item in foodsOptions"
          :key="item.id"
          :label="item.name"
          :value="item.id">
        </el-option>
      </el-select>
    <!-- <el-input v-model="banner.url" placeholder="请输入跳转链接" /> -->
  </el-form-item>
  <el-form-item label="显示隐藏" prop="status">
    	 <el-switch
         active-value="1" inactive-value="0"
    	   v-model="indexScreen.status">
    	 </el-switch>
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="updateIndexConfig">保存</el-button>
  </el-form-item>
</el-form>

<el-form v-if="activeName == 'second'"  ref="birthdayForm" :rules="birthdayRules" :model="birthdayScreen" v-loading="loading" label-width="120px" label-position="right">
  <el-form-item label="生日弹窗" prop="img">
    	<Upload v-model="birthdayScreen.img"/>
      <ImageSource :mul="false" v-model="birthdayScreen.img" />
  </el-form-item>
  <el-form-item label="赠送商品" prop="foodIdArr" style="width: 700px;">
    <el-transfer filterable filter-placeholder="请输入商品名称" v-model="birthdayScreen.foodIdArr" :titles="['待选商品', '已选商品']" :data="foodList">
    </el-transfer>
  </el-form-item>
  <el-form-item label="有效天数" prop="validateDays">
  	<el-input-number style="width:200px;" v-model="birthdayScreen.validateDays" :min="1"></el-input-number>
  </el-form-item>
  <el-form-item label="显示隐藏" prop="status">
    	 <el-switch
         active-value="1" inactive-value="0"
    	   v-model="birthdayScreen.status">
    	 </el-switch>
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="updateBirthdayConfig">保存</el-button>
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
import { getIndexScreen, updateIndexScreen, getBirthdayScreen, updateBirthdayScreen } from '@/api/screen'
import {getFoodSelectorByBrandId} from '@/api/food'
import {
    getShopSelector
  } from '@/api/shop'
import Upload from '@/components/Upload/singleImage'
import ImageSource from '@/components/Upload/imageSource'

const urlOptions = [
  { key: 'buyVoucher', text: '购买早餐券' },
  { key: 'sendVoucher', text: '送Ta早餐券' },
  { key: 'buyGiftCard', text: '购买礼品卡' },
  { key: 'inviteFriend', text: '邀请好友' },
  { key: 'shop', text: '店铺' },
  /* { key: 'buyFood', text: '购买商品' }, */
  { key: 'foodMenu', text: '菜单' }
]

export default {
  name: 'screenConfig',
  components: { Upload, ImageSource },
  data() {
    return {
			indexScreen:{},
      birthdayScreen:{},
      brandId: null,
			loading : true,
      indexRules: {
        img: [{ required: true, message: '请上传图片', trigger: 'blur' }],
        foodId: [{ required: true, message: '请选择商品', trigger: 'blur' }],
        url: [{ required: true, message: '请填写跳转链接', trigger: 'blur' }],
        status: [{ required: true, message: '请选择状态', trigger: 'blur' }],
      },
      birthdayRules: {
        img: [{ required: true, message: '请上传图片', trigger: 'blur' }],
        foodIdArr: [{ required: true, message: '请选择赠送早餐', trigger: 'blur' }],
        status: [{ required: true, message: '请选择状态', trigger: 'blur' }],
        validateDays: [{ required: true, message: '请输入有效天数', trigger: 'blur' }],
      },
      activeName: 'first',
      urlOptions,
      foodsOptions:[],
      foodList: [],
      shopOptions:[],
    }
  },
  created() {
    this.get()
    this.getShopSelector()
  },
  methods: {
    getShopSelector(){
    	getShopSelector().then(response => {
    	  this.shopOptions = response.data
    	})
    },
    getFoodSelectorByBrandId(brandId) {
      this.foodsOptions = []
      getFoodSelectorByBrandId({
        brandId: brandId,
        type: 7
      }).then(response => {
        this.foodsOptions = response.data
        this.foodsOptions.forEach((item, index) => {
          this.foodList.push({
            label: item.name,
            key: item.id + ""
          });
        });
      })
    },
    get() {
      getIndexScreen().then(response => {
        this.indexScreen = response.data
        if(!this.indexScreen) this.indexScreen = {}
        if(this.indexScreen.url && this.indexScreen.url.indexOf('buyFood') >= 0 ){
          let url = this.indexScreen.url
          this.indexScreen.url = url.split('/')[0]
          this.indexScreen.foodId = parseInt(url.split('/')[1])
        }
        // if(this.indexScreen.url && this.indexScreen.url.indexOf('shop') >= 0 ){
        //   let url = this.indexScreen.url
        //   this.indexScreen.url = url.split('/')[0]
        //   this.indexScreen.shopId = parseInt(url.split('/')[1])
        // }
      })
      getBirthdayScreen().then(response => {
        this.birthdayScreen = response.data
        if(!this.birthdayScreen){
          this.birthdayScreen = {}
        }else{
          let foodIdArr = this.birthdayScreen.foodIds.split(",");
          this.$set(this.birthdayScreen,'foodIdArr',foodIdArr)
        }
      })
      this.loading = false
    },
    updateIndexConfig() {
      this.$refs['indexForm'].validate((valid) => {
        if (valid) {
          let formData =Object.assign({}, this.indexScreen)
          if(this.indexScreen.url && this.indexScreen.url.indexOf('buyFood') >= 0){
            formData.url = this.indexScreen.url+'/'+this.indexScreen.foodId
          }
          // if(this.indexScreen.url && this.indexScreen.url.indexOf('shop') >= 0){
          //   formData.url = this.indexScreen.url+'/'+this.indexScreen.shopId
          // }
          formData.addTime = null
          formData.updTime = null
          updateIndexScreen(formData).then(() => {
            this.$notify({
              title: '成功',
              message: '更改首页开屏成功',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    },
  updateBirthdayConfig() {
      this.$refs['birthdayForm'].validate((valid) => {
        if (valid) {
          let formData =Object.assign({}, this.birthdayScreen)
          formData.foodIds = this.birthdayScreen.foodIdArr.join(",")
          formData.brandId = this.brandId
          formData.addTime = null
          formData.updTime = null
          updateBirthdayScreen(formData).then(() => {
            this.$notify({
              title: '成功',
              message: '更改生日弹窗成功',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    },
    handleClickTab(tab, event) {
    	this.activeName = tab.name
    },
  }
}
</script>
