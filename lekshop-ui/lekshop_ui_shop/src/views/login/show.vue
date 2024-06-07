<template>
  <div class="h100 wrap-page main-info">
    <Nav></Nav>
    <div class="main-cover flex f-c f-j-c padding-30">

      <section class="jsx-1902787817 documents">
        <p class="jsx-1902787817 title">新一代潮流网购社区</p>
        <p class="jsx-1902787817 subtitle">秒杀拼团 种草社区 B2B2C</p>
        <p class="jsx-1902787817 explain">小程序 · iOS · Android · H5</p>
        <div class="flex f-a-c margin-t16" style="margin-left: 3.967vw;">
          <el-button @click="$router.push('/join')" type="primary" round size="big"> 商家入驻 </el-button>
        </div>
      </section>

      <!-- <div style="margin-left:250px;">
        <h1 style="color: #fff">LEKSHOP</h1>
        <h3 style="color: #fff">Version 1.0.6</h3>
        <p style="line-height:30px;width: 60%; margin-top: 20px; color: #fff;">
          LEKSHOP B2B2C 社区种草电商（自营+商家入驻）、多终端（iOS+Android+H5+小程序）、提供专业电商解决方案。
        </p>
        <div class="flex f-a-c margin-t16">
          <el-button @click="$router.push('/join')" type="primary" round size="big">商家入驻</el-button>
        </div>
        <div class="padding-30"></div>
        <div class="padding-30"></div>
        <div class="padding-30"></div>
        <div class="padding-30"></div>
        <div class="padding-30"></div>
      </div> -->
      <Footer></Footer>
    </div>



  </div>
</template>
<script>
  import Nav from './components/nav.vue'
  import Footer from './components/ifooter.vue'
  export default {
    name: 'Show',
    components: {
      Nav,
      Footer
    },
    data() {
      return {};
    },
    methods: {
      init() {},
    },
    created() {
      this.init();
    },
    mounted() {},
    destroyed() {},
  }
</script>
<style scoped="scoped">
  .wrap-page {
    /* background-color: #fff; */
    /* background: url(https://stc.wanlshop.com/0060lm7Tly1fvblyjxs3dj31hc0u0tc1.jpg); */
    /* background-size: cover;
        background-image: linear-gradient(to right, #5556ff, #a540ff); */
  }

  h1 {
    font-size: 50px;
    margin-bottom: 0;
  }

  h3 {
    font-size: 24px;
    font-weight: 200;
    margin-bottom: 0;
  }

  .main-info {
    position: relative;
    width: 100%;
    margin: auto;
    /* height: 100%; */
    min-height: 100vh;
    z-index: 2;

    background-image: url(./img/banner.jpeg);
    background-position-x: center;
    background-repeat: no-repeat;
    background-size: cover;
  }

  .main-cover {
    /* background: #000;
    opacity: .7; */
    background:rgba(0, 0, 0, 0.7);
    position: absolute;
    width: 100%;
    height: 100%;
    left: 0;
    top: 0;
  }

  .documents.jsx-1902787817 {
      color: #fff;
      margin-left: 6.667vw;
      position: relative;
  }

  .documents.jsx-1902787817 .title.jsx-1902787817 {
      padding-left: 3.933vw;
      font-size: 3.333vw;
      background-size: 3.333vw 3.333vw;
      background-repeat: no-repeat;
      background-position: 0;
      font-weight: bold;
  }

  .documents.jsx-1902787817 .subtitle.jsx-1902787817 {
      font-size: 2.361vw;
      font-weight: 500;
      -webkit-letter-spacing: 2px;
      -moz-letter-spacing: 2px;
      -ms-letter-spacing: 2px;
      letter-spacing: 2px;
      margin-left: 3.967vw;
  }

  .documents.jsx-1902787817 .explain.jsx-1902787817 {
      margin-top: 1.214vh;
      font-size: 1.389vw;
      font-weight: 500;
      margin-left: 4vw;
  }

  .jsx-1902787817 .download.jsx-1902787817 div.jsx-1902787817 {
      width: 9.93vw;
      height: 0;
      position: relative;
      padding-bottom: 3.194vw;
  }
</style>
