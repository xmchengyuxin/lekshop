<template>
  <div class="layout  bg-color-w padding-10 grid grid-c-2 grid-g10">
    <img :src="res.list[0].img" alt />
    <img :src="res.list[1].img" alt />
  </div>
</template>
<script>
export default {
  title: "两张横图",
  props: ["res"],
};
</script>
<style lang="scss" scoped>
@import "./tpl.scss";
img {
  width: 100%;
}
</style>
