<template>
	<div class="">
      <div class="wrap-nav flex">
        <div class="flex f-j-s nav-content f15-size t-color-w padding-lr15">
            <img @click="$router.push('/show')" class="h-34 cursor" src="../img/logo-login.png" alt="">
            <div class="flex">
              <div @click="$router.push('/show')" class="flex f-a-c padding-lr15 cursor t-color-w">首页</div>
              <div @click="$router.push('/join')" class="flex f-a-c padding-lr15 cursor t-color-w">商家入驻</div>
              <img class="w-34 h-34 margin-r4 b-radius" :src="$store.state.user.avatar" alt="">
              <span class="t-color-w flex f-a-c">{{$store.state.user.name}}</span>
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
        background-color: #222;
        min-height: 50px;
        min-width: 1200px;
  }
  .nav-content {
    width: 1200px;
    margin: auto;
  }
</style>
<script>
	export default {
    name: 'Nav',
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
		components: {}
	}
</script>
