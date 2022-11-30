<template>
  <div class="app-container">
       <el-alert
           class="warn-content"
           title=""
           type="success"
           :closable="false">
              账户： {{shop.memberName}}
         </el-alert>
         <br/>
     <el-card shadow="hover">
        <el-form ref="form" :model="shop" label-width="120px" label-position="right">
          <el-form-item style="margin-bottom: 40px;" label-width="0px"prop="name">
            <MDinput v-model="shop.name" :maxlength="100" name="name" required>
              店铺名称
            </MDinput>
          </el-form-item>
          <el-form-item label="店铺LOGO" prop="logo" :rules="[{ required: true, message: '请上传LOGO', trigger: 'blur' }]">
            <Upload v-model="shop.logo" />
            <ImageSource :mul="false" v-model="shop.logo" />
          </el-form-item>
          <el-form-item label="主页轮播" prop="banner">
             <MultipleUpload :value="shop.banner" v-model="shop.banner" />
            <ImageSource :mul="true" v-model="shop.banner" />
          </el-form-item>
          <el-form-item label="关键字" prop="keyword" :rules="[{ required: true, message: '请填写关键字', trigger: 'blur' }]">
              <el-input v-model="shop.keyword" style="width: 60%"></el-input>
          </el-form-item>
          <el-form-item label="描述" prop="produce" :rules="[{ required: true, message: '请填写描述', trigger: 'blur' }]">
              <el-input type="textarea" v-model="shop.produce" style="width: 60%"></el-input>
          </el-form-item>
          <el-form-item label="服务(多选)" prop="checkedService">
            <el-select v-model="checkedService" multiple clearable placeholder="请选择" class="filter-item" style="width: 60%">
                <el-option
                  v-for="item in serviceOptions"
                  :key="item.id +''"
                  :label="item.name"
                  :value="item.id +''">
                </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="店铺简介" prop="remark" :rules="[{ required: true, message: '请填写店铺简介', trigger: 'blur' }]">
              <el-input type="textarea" v-model="shop.remark" style="width: 60%"></el-input>
          </el-form-item>
          <el-form-item label="店铺所在地" prop="pca" :rules="[{ required: true, message: '请选择省市', trigger: 'blur' }]">
            <el-cascader
              v-model="shop.pca"
              size="small"
              style="width:60%;"
              :options="addressOptions"
              clearable
            />
          </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="updateShop()">保存</el-button>
           </el-form-item>
        </el-form>
        </el-form>

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
import { getShopDetail, updateShop, getShopServiceSelector } from '@/api/shop'
import MDinput from '@/components/MDinput'
import Upload from '@/components/Upload/singleImage'
import ImageSource from '@/components/Upload/imageSource'
import MultipleUpload from '@/components/Upload/multipleImage'
import {
  provinceAndCityDataPlus, regionData, CodeToText
} from 'element-china-area-data'


export default {
  name: 'shopInfo',
  components: {MDinput, Upload, ImageSource, MultipleUpload},
  data() {
    return {
      addressOptions: regionData,
			shop:{},
			loading : true,
      serviceOptions:null,
      checkedService: []
    }
  },
  created() {
    this.get()
  },
  methods: {
    getShopServiceSelector(){
      if(!this.serviceOptions){
        getShopServiceSelector().then(response => {
          this.serviceOptions = response.data
        })
      }
    },
    get() {
      this.getShopServiceSelector();
      getShopDetail().then(response => {
        this.shop = response.data
        if(!this.shop){
          this.shop = {}
        }
         this.shop.pca = [this.shop.provinceCode, this.shop.cityCode, this.shop.areaCode]
        this.checkedService = []
        if(this.shop.service){
          this.checkedService = this.shop.service.split(",")
        }
      })
    },
    updateShop() {
        this.$refs['form'].validate((valid) => {
          if (valid) {
            let formData =Object.assign({}, this.shop)
            if(this.checkedService && this.checkedService.length > 0){
            	formData.service = this.checkedService.join(",")
            }
            if (this.shop.pca && this.shop.pca.length > 0) {
              formData.provinceCode = this.shop.pca[0]
              formData.cityCode = this.shop.pca[1]
              formData.areaCode = this.shop.pca[2]
              formData.province = CodeToText[formData.provinceCode]
              formData.city = CodeToText[formData.cityCode]
              formData.area = CodeToText[formData.areaCode]
            }
            formData.addTime = null
            formData.updTime = null
            updateShop(formData).then(() => {
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
