<template>
  <div class="login-container flex f-a-c f-j-e">
    <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form" auto-complete="on" label-position="left">
    <div class="login_title">
      <div class="title_sub">HELLO!</div>
      <div class="title_box">欢迎登录{{ title }}</div>
    </div>
      <el-form-item prop="username">
        <span class="svg-container">
          <svg-icon class="wuroom_color" icon-class="wr_username" />
        </span>
        <el-input
          ref="username"
          v-model="loginForm.username"
          :placeholder="$t('login.username')"
          name="username"
          type="text"
          auto-complete="on"
        />
      </el-form-item>

      <el-form-item prop="password">
        <span class="svg-container">
          <svg-icon class="wuroom_color" icon-class="wr_password" />
        </span>
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
      <!-- <div class="info_box">
         <el-checkbox v-model="loginForm.checked">一周内免登录</el-checkbox>
      </div> -->
      <el-button :loading="loading" type="primary"  style="width:100%;margin: 58px 0 48px;" @click.native.prevent="handleLogin">
        登录
      </el-button>
      <!-- <div class="login_bottom_img">
        <img :src="wuroomImg">
      </div> -->
    </el-form>
  </div>
</template>

<script>
import { validUsername } from '@/utils/validate'
import LangSelect from '@/components/LangSelect'
import SocialSign from './socialsignin'

export default {
  name: 'Login',
  components: { LangSelect, SocialSign },
  data() {
    const validateUsername = (rule, value, callback) => {
      if (!validUsername(value)) {
        callback(new Error('请输入正确的用户名'))
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
    const validateBrandId = (rule, value, callback) => {
      if (value == null) {
        callback(new Error('请填写商户名称'))
      } else {
        callback()
      }
    }
    return {
      loginForm: {
        username: '',
        password: '',
        checked: true
      },
      loginRules: {
        brandName:  [{ required: true, trigger: 'blur', message:'请填写商户名称'}],
        username: [{ required: true, trigger: 'blur', validator: validateUsername }],
        password: [{ required: true, trigger: 'blur', validator: validatePassword }]
      },
      passwordType: 'password',
      loading: false,
      showDialog: false,
      redirect: undefined,
      title: process.env.VUE_APP_TITLE,
      hello: '早上好！',
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
    let time = new Date()
    if(time.getHours() >= 0 && time.getHours() <= 10) {
      this.hello ='早上好！'
    } else if(time.getHours() >= 11 && time.getHours() <= 14) {
      this.hello ='中午好！'
    } else if(time.getHours() >= 15 && time.getHours() <= 18) {
      this.hello ='下午好！'
    } else if(time.getHours() >= 19 && time.getHours() <= 24) {
      this.hello ='晚上好！'
    }

    if (this.loginForm.username === '') {
      this.$refs.username.focus()
    } else if (this.loginForm.password === '') {
      this.$refs.password.focus()
    }
  },
  destroyed() {
    // window.removeEventListener('hashchange', this.afterQRScan)
  },
  methods: {
    showPwd() {
      if (this.passwordType === 'password') {
        this.passwordType = ''
      } else {
        this.passwordType = 'password'
      }
      this.$nextTick(() => {
        this.$refs.password.focus()
      })
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          this.$store.dispatch('user/login', this.loginForm).then(res => {
            this.loading = false
            this.$router.push({ path: this.redirect || '/' })
          }).catch(() => {
            this.loading = false
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    afterQRScan() {
      // const hash = window.location.hash.slice(1)
      // const hashObj = getQueryObject(hash)
      // const originUrl = window.location.origin
      // history.replaceState({}, '', originUrl)
      // const codeMap = {
      //   wechat: 'code',
      //   tencent: 'code'
      // }
      // const codeName = hashObj[codeMap[this.auth_type]]
      // if (!codeName) {
      //   alert('第三方登录失败')
      // } else {
      //   this.$store.dispatch('LoginByThirdparty', codeName).then(() => {
      //     this.$router.push({ path: '/' })
      //   })
      // }
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss">
  /* 修复input 背景不协调 和光标变色 */
  /* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

  $bg:#3662EC;
  $light_gray:#333;
  $cursor: #fff;

  @supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
    .login-container .el-input input{
      color: $cursor;
      &::first-line {
        color: $light_gray;
      }
    }
  }

  /* reset element-ui css */
  .login-container {
    .el-input {
      display: inline-block;
      height: 47px;
      width: 85%;
      input {
        background: transparent;
        border: 0px;
        -webkit-appearance: none;
        border-radius: 0px;
        padding: 12px 5px 12px 15px;
        color: #000;
        height: 47px;
        caret-color: #000;
        &:-webkit-autofill {
          box-shadow: 0 0 0px 1000px $bg inset !important;
          -webkit-text-fill-color: #000 !important;
        }
      }
    }
    .el-form-item {
      border: 1px solid $bg;
      border-radius: 23px;
      background: #fff;
      color: #454545;
      &:hover {
        border-bottom-color: $bg;
      }
      &:active {
        border-bottom-color: $bg;
      }
    }
    .svg-container {
      color: $bg!important;
    }
    .el-input input:-webkit-autofill {
      caret-color:#000!important;
      box-shadow: 0 0 0px 1000px #fff inset !important;
      -webkit-text-fill-color: #000!important;
    }
    .el-input input::first-line {
      // color: #000;
    }
     .el-input input {
       color: #000;
       caret-color: #000;
     }
    input::-webkit-input-placeholder { /* WebKit browsers */
    color: #CCCCCC!important;
    }
    input:-moz-placeholder { /* Mozilla Firefox 4 to 18 */
    color: #CCCCCC!important;
    }
    input::-moz-placeholder { /* Mozilla Firefox 19+ */
    color: #CCCCCC!important;
    }
    input:-ms-input-placeholder { /* Internet Explorer 10+ */
    color: #CCCCCC!important;
    }
  }
</style>
<style>
  .info_box .el-checkbox__inner:hover {
    border-color:#3662EC;
  }
  .info_box .el-checkbox__input.is-checked .el-checkbox__inner {
    border-color:#3662EC;
    background-color:#3662EC;
  }
  .info_box .el-checkbox__input.is-checked+.el-checkbox__label {
    color:#3662EC
  }
  .info_box .el-checkbox__input .el-checkbox__label {
    color:#333
  }
  .wuroom_color {
    color:#3662EC
  }
</style>
<style rel="stylesheet/scss" lang="scss" scoped>
$bg:#3662EC;
$dark_gray:#889aa4;
$light_gray:#eee;

.login-container {
  min-height: 100%;
  width: 100%;
  // background:url(img/login_bg.png) center no-repeat;
  background-size: cover;
  overflow: hidden;
  .login-form {
    position: relative;
    width: 1200px;
    height: 760px;
    box-sizing: border-box;
    max-width: 100%;
    border-radius: 8px;
    box-shadow: 0px 0px 18px 2px rgba(0,0,0,0.2);
    margin:0 auto;
    padding: 0 123px 0 777px;
    // padding: 88px 64px;
    // margin-right: 278px;
    // margin: 0 auto;
    background:#fff url('img/login.png') -95px top no-repeat;
  }
  .login_bottom_img {
    text-align: center;
  }
  .login_title {
    line-height: 41px;
    margin:150px 0 88px;
    .title_box {
      font-size :30px;
      color:#333;
    }
    .title_sub {
      font-size: 36px;
      color:#3662EC;
      font-weight: bold;
    }
  }
  // .login-form::after {
  //   content: '';
  //   position: absolute;
  //   bottom: -9px;
  //   left: 0;
  //   width: 420px;
  //   height: 100%;
  //   /* background: url(img/login-icon.png) bottom  left no-repeat; */
  //   background-size: 400px;
  // }
  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;
    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }
  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }
  .title-container {
    position: relative;
    .title {
      font-size: 26px;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }
    .set-language {
      color: #fff;
      position: absolute;
      top: 3px;
      font-size:18px;
      right: 0px;
      cursor: pointer;
    }
  }
  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }
  .thirdparty-button {
    position: absolute;
    right: 0;
    bottom: 6px;
  }
  .wrap-brand-select .el-form-item__content {
    position: relative;
  }
  .wrap-brand-select .svg-container {
    position: absolute;
    left: 0;
    top: 0;
    bottom: 0;
  }

  .wrap-brand-select .el-select {
    position: relative;
    width: 420px;
    left: 30px;
  }
   .wrap-brand-select ::v-deep .el-input,.login-container .wrap-brand-select ::v-deep .el-input input {
    width: 100%!important;
  }
 ::v-deep .el-input,.wrap-brand-select .el-input  {
    width: 70%!important;
  }
  .wrap-brand-select .el-select {
    width: 81%;
    margin-left: 23px;
  }
  ::v-deep .el-button {
    height: 50px;
    background: $bg;
    border-color: $bg;
    font-size: 14px;
    box-shadow: 0px 8px 10px 0px rgba(29,54,148,0.2);
    border-radius: 23px;
  }
}
</style>
