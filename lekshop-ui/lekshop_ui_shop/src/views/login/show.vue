<template>
  <div class="h100 wrap-page">
    <Nav></Nav>
    <div id="video_wrapper">
      <video autoplay="" muted="" loop="">
        <source src="https://stc.wanlshop.com/0060lm7Tly1fvblyjxs3dj31hc0u0tc1.mp4" type="video/mp4">
      </video>
    </div>

    <div class="main-info flex f-c f-j-c padding-30">
      <h1 style="color: #ffffff">LEKSHOP</h1>
      <h3 style="color: #ffffff">Version 1.0.2</h3>
      <p style="line-height:30px;width: 60%; margin-top: 20px;color: #ffffff;">
        LEKSHOP B2B2C 社区种草电商（自营+商家入驻）、多终端（iOS+Android+H5+小程序）、提供专业电商解决方案。
      </p>
      <div class="flex f-a-c margin-t16">
        <el-button @click="$router.push('/join')" type="primary">商家入驻</el-button>
      </div>
      <div class="padding-30"></div>
      <div class="padding-30"></div>
      <div class="padding-30"></div>
      <div class="padding-30"></div>
      <div class="padding-30"></div>
    </div>



    <Footer></Footer>
  </div>
</template>
<script>
  import Nav from './components/nav.vue'
  import Footer from './components/footer.vue'
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
    background: url(https://stc.wanlshop.com/0060lm7Tly1fvblyjxs3dj31hc0u0tc1.jpg);
        background-size: cover;
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
    width: 1200px;
    margin: auto;
    height: 100%;
    z-index: 2;
  }

  #video_wrapper video {
    position: fixed;
    top: 50%;
    left: 50%;
    z-index: 1;
    min-width: 100%;
    min-height: 100%;
    width: auto;
    height: auto;
    transform: translate(-50%, -50%);
  }
</style>
