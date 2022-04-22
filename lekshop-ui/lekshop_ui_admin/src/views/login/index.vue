<template>
  <div class="login-container flex f-a-c f-j-c">
    <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form" auto-complete="on" label-position="left">

     <el-menu
      default-active="1"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
      mode="horizontal">
        <el-menu-item index="1" style="width: 100%;text-align: center;">{{title}}</el-menu-item>
      </el-menu>

     <!-- <div class="title-container">
         <img src="./img/logo-login.png" alt="" style="height: 100px;margin-bottom: 30px; margin-left: 90px;border-radius: 10px;">
      </div> -->

     <!-- <el-form-item prop="brandName">
        <span class="svg-container">
          <svg-icon icon-class="guide" />
        </span>
        <el-input
          ref="brandName"
          v-model="loginForm.brandName"
          placeholder="请填写商户名称"
          name="brandName"
          type="text"
          auto-complete="on"
        />
      </el-form-item> -->

      <el-form-item prop="username">
        <span class="svg-container">
          <svg-icon icon-class="user" />
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
          <svg-icon icon-class="password" />
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

      <el-button :loading="loading" type="primary" style="width:100%;margin-bottom:30px;" @click.native.prevent="handleLogin">
        登录
      </el-button>

    </el-form>
		<!-- <p style="color:#eee;text-align: center; font-size: 12px;">厦门诚禹信信息科技提供技术支持</p> -->

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
        brandName: ''
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
      title: process.env.VUE_APP_TITLE
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
          this.$store.dispatch('user/login', this.loginForm).then(() => {
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

  $bg:#283443;
  $light_gray:#000;
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
      border: 1px solid #E5E5E5;
      background: #fff;
      border-radius: 5px;
      color: #454545;
    }
    .svg-container {
      color: #000!important;
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

<style rel="stylesheet/scss" lang="scss" scoped>
$bg:#2d3a4b;
$dark_gray:#889aa4;
$light_gray:#eee;

.login-container {
  min-height: 100%;
  width: 100%;
  background:url(img/background.jpg) center no-repeat;
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
    background: #FEC41B;
    border-color: #FEC41B;
    font-size: 14px;
  }
}
</style>
