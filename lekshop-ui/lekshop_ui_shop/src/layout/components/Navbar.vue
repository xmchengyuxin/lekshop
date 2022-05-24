<template>
  <div class="navbar">
    <!-- <div class="flex f-a-c fixed-name">
      <el-avatar shape="square" class="fixed-logo flex f-a-c f-j-c" :src="logo"></el-avatar>
      <h1 class=" flex f-a-c f-j-c">{{title}} </h1>
    </div> -->


    <hamburger :is-active="sidebar.opened" class="hamburger-container" @toggleClick="toggleSideBar" />


    <breadcrumb class="breadcrumb-container"/>


    <div class="right-menu">
      <template v-if="device!=='mobile'">
        <search class="right-menu-item" />

        <error-log class="errLog-container right-menu-item hover-effect" />

        <screenfull class="right-menu-item hover-effect" />

        <el-tooltip :content="$t('navbar.size')" effect="dark" placement="bottom">
          <size-select class="right-menu-item hover-effect" />
        </el-tooltip>

        <notice class="right-menu-item"/>
        <!-- <i class="el-icon-message-solid hover-effect right-menu-item " id="message-icon" style="line-height: 50px;" @click="goControl"></i>

        <i class="el-icon-question hover-effect right-menu-item " id="exception-icon" style="line-height: 50px;" @click="goException"></i> -->

        <!-- <lang-select class="right-menu-item hover-effect" /> -->

      </template>

      <el-dropdown class="avatar-container right-menu-item hover-effect" trigger="click">
        <div class="avatar-wrapper">
          <img :src="avatar+'?imageView2/1/w/80/h/80'" class="user-avatar">
          <i class="el-icon-caret-bottom" />
        </div>
        <el-dropdown-menu slot="dropdown">
          <router-link to="/show">
            <el-dropdown-item>
              {{ $t('navbar.dashboard') }}
            </el-dropdown-item>
          </router-link>
          <router-link to="/shop/info">
            <el-dropdown-item divided>
              个人中心
            </el-dropdown-item>
          </router-link>
          <el-dropdown-item divided>
            <span style="display:block;" @click="logout">{{ $t('navbar.logOut') }}</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import Breadcrumb from '@/components/Breadcrumb'
import Hamburger from '@/components/Hamburger'
import ErrorLog from '@/components/ErrorLog'
import Screenfull from '@/components/Screenfull'
import SizeSelect from '@/components/SizeSelect'
import LangSelect from '@/components/LangSelect'
import Search from '@/components/HeaderSearch'
import Notice from './Notice'

export default {
  components: {
    Breadcrumb,
    Hamburger,
    ErrorLog,
    Screenfull,
    SizeSelect,
    LangSelect,
    Search,
    Notice
  },
  computed: {
    ...mapGetters([
      'sidebar',
      'name',
      'avatar',
      'device'
    ])
  },
  data() {
    return {
      title: this.$store.state.user.brandName ? this.$store.state.user.brandName : this.$store.state.system.webName,
      logo: this.$store.state.user.brandLogo ? this.$store.state.user.brandLogo : this.$store.state.system.logo
    }
  },
  methods: {
    toggleSideBar() {
      this.$store.dispatch('app/toggleSideBar')
    },
    async logout() {
      await this.$store.dispatch('user/logout')
      this.$router.push(`/login?redirect=${this.$route.fullPath}`)
    },
    goControl(){
      document.getElementById('message-icon').classList.remove('show-animate');
      this.$router.push(`/control/controlCenter`)
    },
    goException(){
      document.getElementById('exception-icon').classList.remove('show-animate');
      this.$router.push(`/control/all?active=eight`)
    },
  }
}
</script>

<style lang="scss" scoped>
  ::v-deep .el-badge__content.is-fixed {
    top: 16px;
  }
  .fixed-logo {
    width: 40px;
    height: 40px;
    z-index: 1111;
    margin-right: 10px;
  }

  .fixed-name  {
    position: fixed;
    left: 120px;
    right: 0;
    top: 0;
    width: 300px;
    height: 50px;
    margin: auto;
    z-index: 1111;
  }
  .fixed-name h1 {
    font-size: 18px;
    color: rgba(0, 0, 0, 0.45);
  }
.navbar {
  height: 50px;
  overflow: hidden;
  position: relative;
  background: #e74c3c;//#fff;
  box-shadow: 0 1px 4px rgba(0,21,41,.08);

  .hamburger-container {
    line-height: 46px;
    height: 100%;
    float: left;
    cursor: pointer;
    transition: background .3s;
    -webkit-tap-highlight-color:transparent;

    &:hover {
      background: rgba(0, 0, 0, .025)
    }
  }

  .breadcrumb-container {
    float: left;
  }

  .errLog-container {
    display: inline-block;
    vertical-align: top;
  }

  .right-menu {
    float: right;
    height: 100%;
    line-height: 50px;

    &:focus {
      outline: none;
    }

    .right-menu-item {
      display: inline-block;
      padding: 0 8px;
      height: 100%;
      font-size: 18px;
      color: #fff;
      vertical-align: text-bottom;

      &.hover-effect {
        cursor: pointer;
        transition: background .3s;

        &:hover {
          background: rgba(0, 0, 0, .025)
        }
      }
    }

    .avatar-container {
      margin-right: 30px;

      .avatar-wrapper {
        margin-top: 5px;
        position: relative;

        .user-avatar {
          cursor: pointer;
          width: 40px;
          height: 40px;
          border-radius: 10px;
        }

        .el-icon-caret-bottom {
          cursor: pointer;
          position: absolute;
          right: -20px;
          top: 25px;
          font-size: 12px;
        }
      }
    }
  }
  .el-menu-item {
    background-color: #fff!important;
  }
  .show-animate {
    animation: show infinite 1s;
  }
  @keyframes show {
    0% {
      opacity: 0;
    }
    50% {
      opacity: 1;
    }
    70% {
      opacity: 0;
    }
    100% {
      opacity: 0;
    }
   }
}
</style>
