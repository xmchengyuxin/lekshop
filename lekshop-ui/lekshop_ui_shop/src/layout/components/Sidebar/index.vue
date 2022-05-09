<template>
  <div :class="{'has-logo':showLogo}">
    <logo v-if="showLogo" :collapse="isCollapse" />
    <el-scrollbar wrap-class="scrollbar-wrapper">
      <el-menu
        :default-active="$route.path"
        :collapse="isCollapse"
        :background-color="variables.menuBg"
        :text-color="variables.menuText"
        :active-text-color="variables.menuActiveText"
        :collapse-transition="false"
				:unique-opened="true"
        :default-openeds="openPath"
        mode="vertical"
      >
        <sidebar-item v-if="verifyDataNum != ''" v-for="route in permission_routes" :key="route.path" :item="route" :base-path="route.path" :verifyData="verifyDataNum"/>
      </el-menu>
    </el-scrollbar>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import Logo from './Logo'
import SidebarItem from './SidebarItem'
import variables from '@/styles/variables.scss'
import {countVerifyNum} from '@/api/index'
export default {
  components: { SidebarItem, Logo },
  computed: {
    ...mapGetters([
      'permission_routes',
      'sidebar'
    ]),
    showLogo() {
      return this.$store.state.settings.sidebarLogo
    },
    variables() {
      return variables
    },
    isCollapse() {
      return !this.sidebar.opened
    },
    openPath(){
      let data = this.$store.state.user.menus;
      var list = data.map(function (item) {
          if(item.url == '/buyVerify' || item.url == '/sellerVerify' || item.url == '/member' || item.url == '/taskAdvances'){
            return item.url;
          }
      });
      return list
      },
    },
    data() {
      return {
        verifyData: {},
        verifyDataNum: '',
      }
    },
    created() {
      this.count()
    },
    watch: {
      $route({ path, meta }) {
       const self = this;
       // self.count();
      }
     },
    methods: {
      count(){
        this.verifyDataNum = '';
        countVerifyNum().then(response => {
          const result = response.data
          this.verifyData = result;
          let verifyDataNum = {
            'taobaoVerify': this.verifyData.taobaoNum,
            'huabeiVerify': this.verifyData.huabeiNum,
            'jdVerify': this.verifyData.jdNum,
            'addressVerify': this.verifyData.addressNum,
            'baitiaoVerify': this.verifyData.baitiaoNum,
            'pddVerify': this.verifyData.pddNum,
            'dyVerify': this.verifyData.dyNum,
            'complaintVerify': this.verifyData.complaintVerifyNum,
            'sellerTaskVerify': this.verifyData.verifyTaskNum,
            'sellerVerify': this.verifyData.sellerVerifyNum,
            'rejectUnPayVerify': this.verifyData.unpayRejectTaskNum,
            'rejectUnReceiveVerify': this.verifyData.unRecieveRejectTaskNum,
            'realnameVerify': this.verifyData.idcardVerifyNum,
           }
           this.verifyDataNum = verifyDataNum
        })
      },
     }
}
</script>
