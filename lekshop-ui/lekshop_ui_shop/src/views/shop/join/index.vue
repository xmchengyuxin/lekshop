<template>
  <div class="wrap-page">
    <div class="app-container">
      <Nav></Nav>
      <el-card>
        <div slot="header" class="flex f-a-c f-j-c f18-size">
          <span>商城申请入驻</span>
        </div>
        <div class="padding-lr15">
          <el-steps :active="active">
            <el-step title="注册账号"></el-step>
            <el-step title="主体资质"></el-step>
            <el-step title="店铺信息"></el-step>
            <el-step title="提交审核"></el-step>
          </el-steps>
        </div>

      </el-card>
      <el-card v-if="active == 1" class="margin-t20">
        <div slot="header" class="flex f-a-c  f18-size">
          <span>主体资质认证</span>
        </div>
        <el-form ref="form" :model="form" label-width="200px">
          <el-form-item label="店铺类型">
            <el-radio-group v-model="form.type">
              <el-radio label="1">个人</el-radio>
              <el-radio label="2">企业</el-radio>
              <el-radio label="3">旗舰店</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item prop="realname" :label="form.type == 1? '姓名' : '企业名称'"
            :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
            <el-input v-model="form.realname"></el-input>
          </el-form-item>
          <el-form-item prop="idcard" :label="form.type == 1? '身份证号码' : '统一信用代码'"
            :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
            <el-input v-model="form.idcard"></el-input>
          </el-form-item>
          <el-form-item prop="cardImg" :label="form.type == 1? '手持身份证' : '营业执照'"
            :rules="[{ required: true, message: '请上传'+form.type == 1? '手持身份证' : '营业执照', trigger: 'blur' }]">
            <Upload v-model="form.cardImg" />
          </el-form-item>
          <el-form-item prop="brandImg" v-if="form.type == 3" label="商标证书"
            :rules="[{ required: true, message: '请上传商标证书', trigger: 'blur' }]">
            <Upload v-model="form.brandImg" />
          </el-form-item>
          <el-form-item prop="weixin" label="微信" :rules="[{ required: true, message: '请输入微信', trigger: 'blur' }]">
            <el-input v-model="form.weixin"></el-input>
          </el-form-item>
          <el-form-item label="手机号">
            <el-input disabled v-model="form.phone"></el-input>
          </el-form-item>

          <el-form-item>
            <el-button @click="submitZizhi" type="primary">下一步</el-button>
          </el-form-item>
        </el-form>
      </el-card>
      <el-card v-if="active == 2" class="margin-t20">
        <div slot="header" class="flex f-a-c  f18-size">
          <span>填写店铺信息</span>
        </div>
        <el-form ref="formShop" :model="form" label-width="200px">
          <el-form-item></el-form-item>
 <el-form-item label="店铺名" prop="name" :rules="[{ required: true, message: '请输入店铺名', trigger: 'blur' }]">
            <el-input v-model="form.name" type="text"></el-input>
          </el-form-item>
          <el-form-item label="店铺图标" prop="logo" :rules="[{ required: true, message: '请上传店铺图标', trigger: 'blur' }]">
            <Upload v-model="form.logo" />
          </el-form-item>

          <el-form-item label="店铺简介" prop="remark" :rules="[{ required: true, message: '请输入店铺简介', trigger: 'blur' }]">
            <el-input type="textarea" v-model="form.remark"></el-input>
          </el-form-item>
          <el-form-item label="经营地区" prop="addressArr" :rules="[{ required: true, message: '请选择省市', trigger: 'blur' }]">
            <el-cascader v-model="form.addressArr" size="small" style="width:100%;" :options="addressOptions" clearable />
          </el-form-item>
          <el-form-item label="经营地址" prop="address" :rules="[{ required: true, message: '请输入店铺地址', trigger: 'blur' }]">
            <el-input type="textarea" v-model="form.address"></el-input>
          </el-form-item>
          <el-form-item label="店铺介绍">
            <el-input type="textarea" v-model="form.produce"></el-input>
            <!-- <Tinymce ref="editor" v-model="form.produce" :height="400" /> -->
          </el-form-item>
          <el-form-item>
            <el-button @click="active=1">上一步</el-button>
            <el-button @click="submitShopInfo" type="primary">提交审核</el-button>
          </el-form-item>
        </el-form>
      </el-card>
      <el-card v-if="active == 3||active==4" class="margin-t20">
        <div slot="header" class="flex f-a-c f-j-c f18-size">
          <span>资料已提交</span>
        </div>
        <div class="f15-size" style="border: 1px solid #f4f4f4;">
          <div class="flex  " style="border-bottom: 1px solid #f4f4f4;">
            <div class="flex f-a-c flex-1 f-w-b padding-12" style="border-right: 1px solid #f4f4f4;">{{form.type==1?'姓名': '主体名称（企业）'}}</div>
            <div class="flex f-a-c flex-1 f-w-b f-j-c padding-12">{{form.realname}}</div>
          </div>
          <div class="flex  " style="border-bottom: 1px solid #f4f4f4;">
            <div class="flex f-a-c flex-1 f-w-b padding-12" style="border-right: 1px solid #f4f4f4;">{{form.type==1?'身份证件': '主体证件'}}</div>
            <div class="flex f-a-c flex-1 f-w-b f-j-c padding-12">
               <el-image
                  style="width: 100px; height: 100px"
                  :src="form.cardImg"
                  :preview-src-list="[form.cardImg]">
                </el-image>
            </div>
          </div>
          <div class="flex  " style="border-bottom: 1px solid #f4f4f4;">
            <div class="flex f-a-c flex-1 f-w-b padding-12" style="border-right: 1px solid #f4f4f4;">手机号</div>
            <div class="flex f-a-c flex-1 f-w-b f-j-c padding-12">{{form.phone}}</div>
          </div>
          <div class="flex  " style="border-bottom: 1px solid #f4f4f4;">
            <div class="flex f-a-c flex-1 f-w-b padding-12" style="border-right: 1px solid #f4f4f4;">店铺名</div>
            <div class="flex f-a-c flex-1 f-w-b f-j-c padding-12" >{{form.name}}</div>
          </div>
          <div class="flex  " style="border-bottom: 1px solid #f4f4f4;">
            <div class="flex f-a-c flex-1 f-w-b padding-12" style="border-right: 1px solid #f4f4f4;">店铺简介</div>
            <div class="flex f-a-c flex-1 f-w-b f-j-c padding-12">{{form.remark}}</div>
          </div>
          <div class="flex  " style="border-bottom: 1px solid #f4f4f4;">
            <div class="flex f-a-c flex-1 f-w-b padding-12" style="border-right: 1px solid #f4f4f4;">经营地区</div>
            <div class="flex f-a-c flex-1 f-w-b f-j-c padding-12">{{form.province}}/{{form.city}}/{{form.area}}</div>
          </div>
          <div class="flex  ">
            <div class="flex f-a-c flex-1 f-w-b padding-12" style="border-right: 1px solid #f4f4f4;">审核状态</div>
            <div class="flex f-a-c flex-1 f-w-b f-j-c padding-12"  v-if="form.status == 1">
              <el-button @click="active = 2" >上一步</el-button>
              <el-button @click="submit" type="primary">提交审核</el-button>
            </div>
            <div class="flex f-a-c flex-1 f-w-b f-j-c padding-12" style="color: #409EFF;" v-if="form.status == 2">正在审核中..</div>
            <div class="flex f-a-c flex-1 f-w-b f-j-c padding-12" style="color: #67C23A;" v-if="form.status == 3">
              审核通过
              <el-button style="margin-left: 10px;" type="primary" @click="$router.push('/dashboard')" >前往后台</el-button>
            </div>
            <div class="flex f-a-c flex-1 f-w-b f-j-c padding-12" style="color: #F56C6C;" v-if="form.status == 4">
            {{form.reason}}
            <el-button style="margin-left: 10px;" @click="active = 2" >重新编辑</el-button>
            </div>
          </div>
        </div>
      </el-card>
      <Footer></Footer>
    </div>
  </div>
</template>
<!-- <style scoped="scoped" src="../../css/index/task.css"></style> -->
<style scoped="scoped">
  .app-container {
    max-width: 1200px;
    margin: auto;
  }

  .wrap-page {
    background: #f5f5f5;
    padding: 60px 0 50px 0;
    min-height: 100%;
  }
</style>
<script>
  import {
    getShopInfo,
    submitShopCert,
    submitShopDetail,
    submitShop
  } from '@/api/shop'
  import Nav from '@/views/login/components/nav.vue'
  import Footer from '@/views/login/components/footer.vue'
  import Tinymce from '@/components/Tinymce'
  import Upload from '@/components/Upload/singleImage'
  import ImageSource from '@/components/Upload/imageSource'
  import {
    provinceAndCityDataPlus,
    regionData,
    CodeToText
  } from 'element-china-area-data'

  export default {
    name: 'joinShop',
    components: {
      Upload,
      ImageSource,
      Tinymce,
      Nav,
      Footer
    },
    data() {
      return {
        addressOptions: regionData,
        active: 1,
        form: {
          id: '',
          type: '1',
          realname: '',
          idcard: '',
          cardImg: '',
          weixin: '',
          brandImg: '',
          phone: '',
          produce: '',
          logo: '',
          address: '',
          logo: '',
          status: '',
          name: '',
          remark: '',
          addressArr: '',
          province: '',
          provinceCode: '',
          city: '',
          cityCode: '',
          area: '',
          areaCode: '',
        },
        shop: {},
      };
    },
    methods: {
      submit() {
        submitShop(this.form).then((res) => {
          if (res.code == 200) {
            this.form.status = 2;
          }
        })
      },
      submitShopInfo() {
        // console.log(this.form.addressArr,CodeToText[this.form.addressArr[0]])
        this.$refs.formShop.validate(valid => {
          if (valid) {
            this.form.province = CodeToText[this.form.addressArr[0]];
            this.form.city = CodeToText[this.form.addressArr[1]];
            this.form.area = CodeToText[this.form.addressArr[2]];
            this.form.provinceCode = this.form.addressArr[0];
            this.form.cityCode = this.form.addressArr[1];
            this.form.areaCode = this.form.addressArr[2];
            submitShopDetail(this.form).then((res) => {
              if (res.code == 200) {
                this.active = 3;
                this.form.status = 1;
              }
            })
           console.log('suc submit!!')
          } else {
            console.log('error submit!!')
            return false
          }
        })
      },
      submitZizhi() {
        if(this.form.status != '' && this.form.status == 0) {
          this.active = 2;
          return;
        }
        this.$refs.form.validate(valid => {
          if (valid) {
            submitShopCert(this.form).then(res => {
              if (res.code == 200) {
                this.form.id = res.data;
                this.active = 2;
              }

            })
          } else {
            console.log('error submit!!')
            return false
          }
        })

      },
      init() {
        this.$store.dispatch('user/getUserInfo').then((res) => {
          this.form.phone = res.code
        }).catch(() => {})
        getShopInfo().then((res) => {
          if (res.code == 200 && res.data) {
            res.data.type = String(res.data.type);
            this.form = res.data;
            this.form.addressArr = [this.form.provinceCode,this.form.cityCode,this.form.areaCode];
            this.active = res.data.status == 0 ? 2 : 3;
            if(res.data.status == 3) {
              this.active= 4;
            }
          }
        })
      },


    },
    created() {
      this.init();
    },
    mounted() {},
    destroyed() {},
  }
</script>
