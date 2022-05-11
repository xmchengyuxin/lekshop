<template>
  <div class="login-container flex f-a-s f-j-c" style="padding-bottom: 50px;">
    <Nav></Nav>
    <el-card :body-style="{padding:0}">
      <div class="flex f16-size">
        <div @click="active=1;" class="flex f-a-c flex-1 f-j-c h-60 w-230 cursor" :class="active == 1 || active==3 ? '' : 'bg-color'">登 录</div>
        <div @click="active=2;getCode();" class="flex f-a-c flex-1 f-j-c h-60  w-230 cursor" :class="active == 2 ? '' : 'bg-color'">注 册</div>
      </div>
      <div class="padding-15">
      <div class="padding-30">
        <template v-if="active==1">
        <div class="padding-15 login-tip ">
          <p>测试账号：admin 密码:123456 </p>
          <p class="margin-t10">如果体验入驻流程请注册新会员后进入 [用户中心] - [商家控制台] 进入入驻流程</p>
        </div>
        <div class="margin-t10"></div>
        <el-form ref="loginForm" size="medium" label-position="top" label-width="80px" class="" :model="loginForm" :rules="loginRules"  auto-complete="on">
          <el-form-item prop="username" label="账号">
            <el-input
              ref="username"
              v-model="loginForm.username"
              :placeholder="$t('login.username')"
              name="username"
              type="text"
              auto-complete="on"
            />
          </el-form-item>

          <el-form-item prop="password"  label="密码">
            <el-input
              ref="password"
              v-model="loginForm.password"
              :type="passwordType"
              :placeholder="$t('login.password')"
              name="password"
              auto-complete="on"
              @keyup.enter.native="handleLogin"
            />
            <span class="show-pwd" @click="showPwd">
              <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
            </span>
          </el-form-item>
          <div class="padding-10"></div>
          <div class="flex f-a-c f-j-e">
            <el-link @click="active=3;getCode()">修改密码</el-link>
          </div>
          <div class="padding-10"></div>
          <el-button :loading="loading" type="primary" style="width:100%;" size="medium" @click.native.prevent="handleLogin">
            登录
          </el-button>
        </el-form>
        </template>
        <el-form ref="registerForm" v-if="active == 2"  size="medium" label-position="top" label-width="80px" class="" :model="registerForm" :rules="registerRules"  auto-complete="on">
          <el-form-item prop="username" label="用户名">
            <el-input
              ref="username"
              v-model="registerForm.username"
              placeholder="用户名"
              name="username"
              type="text"
            />
          </el-form-item>

          <el-form-item prop="password"  label="密码">
            <el-input
              ref="password1"
              v-model="registerForm.password"
              :type="passwordType"
              :placeholder="$t('login.password')"
              name="password"
            />
            <span class="show-pwd" @click="showPwd">
              <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
            </span>
          </el-form-item>
          <el-form-item prop="confirmPassword"  label="确认密码">
            <el-input
              ref="confirmPassword"
              v-model="registerForm.confirmPassword"
              :type="passwordType"
              placeholder="确认密码"
              name="confirmPassword"
            />
            <span class="show-pwd" @click="showPwd">
              <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
            </span>
          </el-form-item>
          <el-form-item prop="phone" label="手机号">
            <div class="flex f-a-c">
              <el-input
                ref="phone"
                v-model="registerForm.phone"
                placeholder="手机号"
                name="phone"
                type="phone"
              />
            </div>
          </el-form-item>
          <el-form-item prop="imgCode" label="图形验证码">
            <div class="flex">
            <el-input
              ref="imgCode"
              v-model="registerForm.imgCode"
              placeholder="图形验证码"
              name="imgCode"
              type="text"
            />
              <img @click="getCode()" v-if="code != ''" class="h-36" :src="'data:image/png;base64,'+code.img" alt="">
            </div>
          </el-form-item>
          <el-form-item prop="code" label="验证码">
            <div class="flex">
            <el-input
              ref="code"
              v-model="registerForm.code"
              placeholder="验证码"
              name="code"
              type="text"
              class="margin-r12"
            />
              <el-button @click="getPhoneCode()">{{smsTxt}}</el-button>
            </div>
          </el-form-item>

          <div class="padding-10"></div>
          <el-button :loading="loading" type="primary" style="width:100%;" size="medium" @click.native.prevent="handleRegister">
            登录
          </el-button>
        </el-form>

        <el-form ref="changeForm" v-if="active == 3"  size="medium" label-position="top" label-width="80px" class="" :model="registerForm" :rules="registerRules"  auto-complete="on">
          <el-form-item prop="phone" label="手机号">
            <div class="flex f-a-c">
              <el-input
                ref="phone"
                v-model="registerForm.phone"
                placeholder="手机号"
                name="phone"
                type="phone"
              />
            </div>
          </el-form-item>
          <el-form-item prop="password"  label="密码">
            <el-input
              ref="password1"
              v-model="registerForm.password"
              :type="passwordType"
              :placeholder="$t('login.password')"
              name="password"
            />
            <span class="show-pwd" @click="showPwd">
              <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
            </span>
          </el-form-item>
          <el-form-item prop="confirmPassword"  label="确认密码">
            <el-input
              ref="confirmPassword"
              v-model="registerForm.confirmPassword"
              :type="passwordType"
              placeholder="确认密码"
              name="confirmPassword"
            />
            <span class="show-pwd" @click="showPwd">
              <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
            </span>
          </el-form-item>

          <el-form-item prop="imgCode" label="图形验证码">
            <div class="flex">
            <el-input
              ref="imgCode"
              v-model="registerForm.imgCode"
              placeholder="图形验证码"
              name="imgCode"
              type="text"
            />
              <img @click="getCode()" v-if="code != ''" class="h-36" :src="'data:image/png;base64,'+code.img" alt="">
            </div>
          </el-form-item>
          <el-form-item prop="code" label="验证码">
            <div class="flex">
            <el-input
              ref="code"
              v-model="registerForm.code"
              placeholder="验证码"
              name="code"
              type="text"
              class="margin-r12"
            />
              <el-button @click="getPhoneCode()">{{smsTxt}}</el-button>
            </div>
          </el-form-item>

          <div class="padding-10"></div>
          <el-button :loading="loading" type="primary" style="width:100%;" size="medium" @click.native.prevent="changePassword">
            修改密码
          </el-button>
        </el-form>
      </div>
      </div>
    </el-card>
    <div></div>
		<!-- <p style="color:#eee;text-align: center; font-size: 12px;">厦门诚禹信信息科技提供技术支持</p> -->
    <Footer></Footer>
  </div>
</template>

<script>
import {register,getCodeImg,getCodePhone,getUserInfo,phoneLogin,setPassword} from '@/api/login'
import { validUsername,validPhone } from '@/utils/validate'
import LangSelect from '@/components/LangSelect'
import SocialSign from './socialsignin'
import Nav from './components/nav.vue'
import Footer from './components/footer.vue'
let codeTimeOut;
export default {
  name: 'Login',
  components: { LangSelect, SocialSign,Nav,Footer },
  data() {
    const validateUsername = (rule, value, callback) => {
      if (!validUsername(value)) {
        callback(new Error('请输入正确的用户名'))
      } else {
        callback()
      }
    }
    const validatePhone = (rule, value, callback) => {
      if(value == '') {
        callback(new Error('请输入手机号码'))
      }else if (validPhone(value)) {
        callback(new Error('请输入正确的手机号码'))
      } else {
        callback()
      }
    }
    const validatePassword = (rule, value, callback) => {
      if (value.length < 5) {
        callback(new Error('密码不能小于5位'))
      } else {
        callback()
      }
    }
    return {
      active: 1,
      loginForm: {
        username: '',
        password: '',
        phone: '',
        code: '',
      },
      loginRules: {
        username: [{ required: true, trigger: 'blur', validator: validateUsername }],
        password: [{ required: true, trigger: 'blur', validator: validatePassword }]
      },
      registerForm: {
        username: '',
        type: 2,//	用户类型>>1用户>>2商家
        password: '',
        confirmPassword: '',
        phone: '',
        code: '',
        imgCode: '',
      },
      registerRules: {
        username: [{ required: true, trigger: 'blur', validator: validateUsername }],
        password: [{ required: true, trigger: 'blur', validator: validatePassword }],
        confirmPassword: [{ required: true, trigger: 'blur', validator: validatePassword }],
        phone: [{ required: true, trigger: 'blur', validator: validatePhone }],
        code: [{ required: true, trigger: 'blur', message: '请输入验证码' }],
        imgCode: [{ required: true, trigger: 'blur', message: '请输入图形验证码' }],
      },
      passwordType: 'password',
      loading: false,
      showDialog: false,
      redirect: undefined,
      title: process.env.VUE_APP_TITLE,
      code: '',
      smsTxt: '获取验证码',
    }
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect
      },
      immediate: true
    }
  },
  created() {
    // window.addEventListener('hashchange', this.afterQRScan)
  },
  mounted() {

  },
  destroyed() {
    // window.removeEventListener('hashchange', this.afterQRScan)
  },
  methods: {
    getPhoneCode() {
      const self = this;
      if(this.registerForm.imgCode == '') {
        this.$message.error('请先输入图形验证码');
        return;
      }
      if(validPhone(this.registerForm.phone)) {
        this.$message.error('请输入正确的手机号码');
        return;
      }
      clearInterval(codeTimeOut);
      if(self.smsTxt != '获取验证码') {
        return
      }
      let postData = {
      	phone: this.registerForm.phone,
      	sendType: this.active == 2 ? 1 : 3,
        cToken: this.code.cToken,
        captcha: this.registerForm.imgCode
      };
      let time = 60;
      self.smsTxt = time + 's后获取';
      codeTimeOut = setInterval(function() {
      	time--;
      	self.smsTxt = time + 's后获取';
      	if (time <= 0) {
      		clearInterval(codeTimeOut);
      		self.smsTxt = '获取验证码';
      	}
      }, 1000);
      getCodePhone(postData).then(res => {
        if(res.code != 200) {
        	clearInterval(codeTimeOut);
        	self.smsTxt = '获取验证码';
        }
      })
    },
    getCode() {
      getCodeImg().then(res => {
        this.code = res.data;
      })
    },
    showPwd() {
      if (this.passwordType === 'password') {
        this.passwordType = ''
      } else {
        this.passwordType = 'password'
      }
    },
    changePassword() {
      this.$refs.changeForm.validate(valid => {
        if(valid) {
          let phoneData = {
            phone: this.registerForm.phone,
            code: this.registerForm.code
          }
         this.$store.dispatch('user/phoneLogin', phoneData).then((res) => {
           let setData = {
             password: this.registerForm.password,
             comfirmPassword: this.registerForm.confirmPassword
           }
           setPassword(setData).then((res) => {
             let member = res.data.member ? res.data.member : '';
             if(member !='' && member.type== 2) {
               this.$router.push('/dashboard')
             }else{
               this.$router.push('/join')
             }
           })
         })
        }
      })
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          this.$store.dispatch('user/login', this.loginForm).then((res) => {
            this.loading = false
            getUserInfo().then((res) => {
              if(res.data.type== 2) {
                this.$router.push('/dashboard')
              }else{
                this.$router.push('/join')
              }
            })
          }).catch(() => {
            this.loading = false
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    handleRegister() {
      this.$refs.registerForm.validate(valid => {
        if (valid) {
          register(this.registerForm).then((res) => {
            this.$store.dispatch('user/login', this.registerForm).then(() => {
              this.$router.push('/join')
            }).catch(() => {
            })
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    }
  }
}
</script>



<style rel="stylesheet/scss" lang="scss" scoped>
$bg:#2d3a4b;
$dark_gray:#889aa4;
$light_gray:#eee;
::v-deep .el-form--label-top .el-form-item__label {
  padding-bottom: 0;
}
::v-deep .el-form-item {
  margin-bottom: 6px;
}
::v-deep .el-form-item__error {
  top: -27px;
  right: 0;
  left: auto;
}
.login-tip {
  width: 370px;
      background-color: #dff0d8;
      border-color: #d1eac8;
      color: #468847;
}
.login-container {
  min-height: 100%;
  width: 100%;
  background-color: #f4f6f8;
  padding-top: 120px;
  // background:url(img/background.jpg) center no-repeat;
  background-size: cover;
  overflow: hidden;
  .login-form {
    position: relative;
    width: 300px;
    box-sizing: content-box;
    max-width: 100%;
    padding: 50px 160px 35px 160px;
    margin: 0 auto;
    // background-color: #fff;
    background-color: rgba(9,9,9,0.4);
    border-radius: 0.5%;
  }
 .show-pwd {
   position: absolute;
   right: 10px;
   top: 0px;
   font-size: 16px;
   color: $dark_gray;
   cursor: pointer;
   user-select: none;
 }
 .el-form--label-top .el-form-item__label {
   padding-bottom: 0;
 }
  .wrap-brand-select .el-form-item__content {
    position: relative;
  }
  .bg-color {
    background-color: #F7f7f7;
  }

}
</style>
