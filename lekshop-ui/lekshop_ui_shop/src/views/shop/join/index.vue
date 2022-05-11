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
        	  <el-form-item prop="name" :label="form.type == 1? '姓名' : '企业名称'"  :rules="[{ required: true, message: '请输入'+form.type == 1? '姓名' : '企业名称', trigger: 'blur' }]">
        	    <el-input v-model="form.name"></el-input>
        	  </el-form-item>
        	  <el-form-item prop="idcardNo" :label="form.type == 1? '身份证号码' : '统一信用代码'" :rules="[{ required: true, message: '请输入'+form.type == 1? '身份证号码' : '统一信用代码', trigger: 'blur' }]">
        	    <el-input v-model="form.idcardNo"></el-input>
        	  </el-form-item>
        	  <el-form-item  :label="form.type == 1? '手持身份证' : '营业执照'" :rules="[{ required: true, message: '请上传'+form.type == 1? '手持身份证' : '营业执照', trigger: 'blur' }]">
              <Upload v-model="form.idcardImg" />
        	  </el-form-item>
        	  <el-form-item v-if="form.type == 3" label="商标证书" :rules="[{ required: true, message: '请上传商标证书', trigger: 'blur' }]">
        	    <Upload v-model="form.shopLogo" />
        	  </el-form-item>
        	  <el-form-item prop="weixin" label="微信"  :rules="[{ required: true, message: '请输入微信', trigger: 'blur' }]">
        	    <el-input v-model="form.weixin"></el-input>
        	  </el-form-item>
        	  <el-form-item label="手机号">
        	    <el-input disabled v-model="form.phone"></el-input>
        	  </el-form-item>

        	  <el-form-item>
        	    <el-button type="primary" >下一步</el-button>
        	  </el-form-item>
        	</el-form>
        </el-card>
        <el-card v-if="active == 2" class="margin-t20">
        	<div slot="header" class="flex f-a-c  f18-size">
        	    <span>填写店铺信息</span>
        	  </div>
        	<el-form ref="form" :model="form" label-width="200px">
        	  <el-form-item label="店铺名" prop="name" :rules="[{ required: true, message: '请输入店铺名', trigger: 'blur' }]">
        	    <el-input v-model="form.name"></el-input>
        	  </el-form-item>
        	  <el-form-item label="店铺图标" prop="name" :rules="[{ required: true, message: '请上传店铺图标', trigger: 'blur' }]">
        	    <Upload v-model="form.logo" />
        	  </el-form-item>
        	    <el-form-item label="店铺简介" prop="desc" :rules="[{ required: true, message: '请输入店铺简介', trigger: 'blur' }]">
        	      <el-input type="textarea" v-model="form.desc"></el-input>
        	    </el-form-item>
        	  <el-form-item label="经营地址" prop="address" :rules="[{ required: true, message: '请选择省市', trigger: 'blur' }]">
        	    <el-cascader
        	      v-model="form.address"
        	      size="small"
        	      style="width:100%;"
        	      :options="addressOptions"
        	      clearable
        	    />
        	  </el-form-item>
        	  <el-form-item label="店铺介绍">
              <Tinymce ref="editor" v-model="form.info" :height="400" />
        	  </el-form-item>
        	  <el-form-item>
        		   <el-button>上一步</el-button>
        	    <el-button type="primary" >提交审核</el-button>
        	  </el-form-item>
        	</el-form>
        </el-card>
        <el-card v-if="active == 3" class="margin-t20">
        	<div slot="header" class="flex f-a-c f-j-c f18-size">
        	    <span>资料已提交</span>
        	  </div>
        	 <div class="f15-size" style="border: 1px solid #f4f4f4;">
        		 <div class="flex h-34 padding-lr12"  style="border-bottom: 1px solid #f4f4f4;">
        			 <div class="flex f-a-c flex-1 f-w-b" style="border-right: 1px solid #f4f4f4;">主体名称（企业）</div>
        			 <div class="flex f-a-c flex-1 f-w-b f-j-c">主体名称（企业）</div>
        		 </div>
        		 <div class="flex h-34 padding-lr12"  style="border-bottom: 1px solid #f4f4f4;">
        		 					 <div class="flex f-a-c flex-1 f-w-b" style="border-right: 1px solid #f4f4f4;">主体证件</div>
        		 					 <div class="flex f-a-c flex-1 f-w-b f-j-c">主体名称（企业）</div>
        		 </div>
        		 <div class="flex h-34 padding-lr12"  style="border-bottom: 1px solid #f4f4f4;">
        		 					 <div class="flex f-a-c flex-1 f-w-b" style="border-right: 1px solid #f4f4f4;">手机号</div>
        		 					 <div class="flex f-a-c flex-1 f-w-b f-j-c">19859222512</div>
        		 </div>
        		 <div class="flex h-34 padding-lr12"  style="border-bottom: 1px solid #f4f4f4;">
        		 					 <div class="flex f-a-c flex-1 f-w-b" style="border-right: 1px solid #f4f4f4;">店铺名</div>
        		 					 <div class="flex f-a-c flex-1 f-w-b f-j-c">11</div>
        		 </div>
        		 <div class="flex h-34 padding-lr12"  style="border-bottom: 1px solid #f4f4f4;">
        		 					 <div class="flex f-a-c flex-1 f-w-b" style="border-right: 1px solid #f4f4f4;">店铺简介</div>
        		 					 <div class="flex f-a-c flex-1 f-w-b f-j-c">11</div>
        		 </div>
        		 <div class="flex h-34 padding-lr12"  style="border-bottom: 1px solid #f4f4f4;">
        		 					 <div class="flex f-a-c flex-1 f-w-b" style="border-right: 1px solid #f4f4f4;">经营地区</div>
        		 					 <div class="flex f-a-c flex-1 f-w-b f-j-c">安徽省/芜湖市/弋江区</div>
        		 </div>
        		 <div class="flex h-34 padding-lr12" >
        		 					 <div class="flex f-a-c flex-1 f-w-b" style="border-right: 1px solid #f4f4f4;">审核状态</div>
        		 					 <div class="flex f-a-c flex-1 f-w-b f-j-c">正在审核中..</div>
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
  import Nav from '@/views/login/components/nav.vue'
  import Footer from '@/views/login/components/footer.vue'
  import Tinymce from '@/components/Tinymce'
  import Upload from '@/components/Upload/singleImage'
  import ImageSource from '@/components/Upload/imageSource'
  import {
    provinceAndCityDataPlus, regionData, CodeToText
  } from 'element-china-area-data'

	export default {
    name: 'joinShop',
    components: {Upload, ImageSource,Tinymce,Nav,Footer },
		data() {
			return {
        addressOptions: regionData,
        active: 1,
        form: {
        	type: '1',
                  name: '',
                  idcardNo: '',
                  idcardImg: '',
                  weixin: '',
                  shopLog: '',
                  phone: '',
                  desc: '',
                  info: '',
                  address: '',
                  logo: '',

                }
      };
		},
		methods: {

			init() {
        this.$store.dispatch('user/getUserInfo').then((res) => {
          console.log(res);
        }).catch(() => {
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
