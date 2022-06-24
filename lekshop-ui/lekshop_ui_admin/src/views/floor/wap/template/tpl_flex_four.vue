
<template>
  <div class="layout bg-color-w padding-10 grid grid-c-4 grid-g10">
      <img class="image-mode" :src="res.list[0].img" alt="">
      <img class="image-mode" :src="res.list[1].img" alt="">
      <img class="image-mode" :src="res.list[2].img" alt="">
      <img class="image-mode" :src="res.list[3].img" alt="">
  </div>
</template>

<script>
export default {
  title: "四列单行图片模块",
  props: ["res"],

};
</script>
<style lang="scss" scoped>
@import "./tpl.scss";

img{
  width: 100% !important;
}
</style>
