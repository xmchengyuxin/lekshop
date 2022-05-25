<template>
  <div>
    <div class="wrap-bottom-men wanl-chat-mini flex f-a-c f-j-c"  @click="handleIm">
      <div class="water1"></div>
      <div class="water2"></div>
      <div class="water3"></div>
      <img src="./chat_mini.png">
    </div>
    <el-dialog title="" :visible.sync="imDialog" width="20%" height="40%">
      <!-- <iframe src="http://im.chyuxin.cn/service/login/index.html" scrolling="auto" frameborder="0" security="restricted"
        sandbox="allow-same-origin allow-top-navigation allow-forms allow-scripts" id="iframe">
        您当前的浏览器不支持页面上的功能，请升级您当前的浏览器版本或使用谷歌浏览器访问当前页面
      </iframe> -->
      <el-alert
          title="说明：本系统集成了第三方客服系统"
          type="error"
          description=""
          :closable="false"
          >
          <p class="tips">
          第三方客服系统适用于总后台管理员和商家使用
          </p>
         <br/>
         <p class="tips">
          平台总管理员可以在第三方客服系统直接创建多客服坐席
         </p>
         <br/>
          <p class="tips">
          测试账号: lekshop
          </p>
          <br/>
          <p class="tips">
          测试密码: 123456
          </p>
        </el-alert>
        <br>
        <a href="http://im.chyuxin.cn/service" target="_blank"><el-button type="primary" size="mini" >立即登录</el-button></a>
    </el-dialog>
  </div>


</template>

<script>

  export default {
    components: {
    },
    data() {
      return {
        imDialog: false,
      }
    },
    methods: {
      handleIm() {
      	this.imDialog = true
      },
    },
  }
</script>

<style lang="scss" scoped>
  .wrap-bottom-men {
    position: fixed;
    bottom: 0;
    right: 0;
    width: 120px;
    height: 120px;
    z-index: 9999;

    img {
      position: relative;
      width: 55px;
      height: 55px;
      z-index: 11;
      border-radius: 50%;
    }
  }

  .wanl-chat-mini .water1,
  .wanl-chat-mini .water2,
  .wanl-chat-mini .water3 {
    padding: 20%;
    position: absolute;
    left: 35px;
    top: 36px;
    box-shadow: 0 0 100px 10px #ff1010 inset;
    border-radius: 100%;
    z-index: 1;
    opacity: 0;
  }

  .wanl-chat-mini .water1 {
    animation: wateranimate 4s 3s ease-out infinite;
  }

  .wanl-chat-mini .water2 {
    animation: wateranimate 4s 2s ease-out infinite;
  }

  .wanl-chat-mini .water3 {
    animation: wateranimate 4s 1s ease-out infinite;
  }



  @-webkit-keyframes wateranimate {
    0% {
      -webkit-transform: scale(0);
      opacity: 0.8;
    }

    100% {
      -webkit-transform: scale(2);
      opacity: 0;
    }
  }

  @keyframes wateranimate {
    0% {
      -webkit-transform: scale(0);
      transform: scale(0);
      opacity: 0.8;
    }

    100% {
      -webkit-transform: scale(2);
      transform: scale(2);
      opacity: 0;
    }
  }
</style>
