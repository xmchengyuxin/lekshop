
<template>
  <div class="layout over-h bg-color-w">
   <img class="image-mode" :src="res.list[0].img" />
    <div class="grid grid-c-2 grid-g10">
      <img class="image-mode" :src="res.list[1].img" alt />
      <img class="image-mode" :src="res.list[2].img" alt />
    </div>
  </div>
</template>

<script>
export default {
  title: "上一下二",
  props: ["res"]
};
</script>
<style lang="scss" scoped>
@import "./tpl.scss";
img {
  width: 100% !important;
  // height: 85px !important;
}
.view-width-100 {
  padding: 1px 0;
  display: flex;
  // height: 85px;
}
.view-height-85 {
  padding: 0 1px;
  width: 50%;
}
</style>
