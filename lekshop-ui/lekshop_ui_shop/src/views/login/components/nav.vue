<template>
  <div class="">
    <div class="wrap-nav flex">
      <div class="flex f-j-s nav-content f15-size t-color-w padding-lr15">
        <h1>LEKSHOP</h1>
        <!-- <img @click="$router.push('/show')" class="h-34 cursor" src="../img/logo-login.png" alt=""> -->
        <div class="flex">
          <div @click="$router.push('/show')" class="flex f-a-c padding-lr15 cursor t-color-w" style="font-size: 14px; color: #aaa;">首页</div>
          <div @click="$router.push('/join')" class="flex f-a-c padding-lr15 cursor t-color-w" style="font-size: 14px; color: #aaa;">商家入驻</div>
          <div  v-if="$store.state.user.name == ''" @click="$router.push('/login')" class="flex f-a-c padding-lr15 cursor t-color-w"  style="font-size: 14px; color: #aaa;">登录</div>
          <el-dropdown  v-if="$store.state.user.name != ''">
            <div  class="flex f-a-c h100 cursor">
              <el-image :src="$store.state.user.avatar" class="w-34 h-34 bg-color-9 flex f-a-c f-j-c margin-r4 b-radius">
                <div slot="error" class="image-slot">
                  <div class="w-34 h-34 b-radius" style="background-color: #F7f7f7"></div>
                </div>
              </el-image>
              &nbsp;&nbsp;
              <span class="t-color-w flex f-a-c"  style="font-size: 14px; color: #aaa;">{{$store.state.user.name}}</span>
            </div>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item >
                <span @click="logout">退出登录</span>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>

        </div>
      </div>
    </div>
  </div>
</template>
<style scoped="scoped" type="text/css">
  .wrap-nav {
    position: fixed;
    top: 0;
    right: 0;
    left: 0;
    z-index: 1030;
    /* background-color: #e74c3c; */
    min-height: 50px;
    min-width: 1200px;
    opacity: .8;
  }

  .nav-content {
    width: 1200px;
    margin: auto;
  }
</style>
<script>
  import {
   logout
  } from '@/api/login'
  export default {
    name: 'Nav',
    data() {
      return {};
    },
    methods: {
      async logout() {
        await this.$store.dispatch('user/logout')
        this.$router.push(`/login?redirect=${this.$route.fullPath}`)
      },
      loginout() {
        console.log('111')
        logout().then((res) => {

        })
      },
      init() {},


    },
    created() {
      this.init();
    },
    mounted() {},
    destroyed() {},
    components: {}
  }
</script>
