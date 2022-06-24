
<template>
  <div class="layout bg-color-w b-radius-5 padding-10 grid grid-c-2 grid-g10">
    <img class="image-mode" :src="res.list[0].img" />
    <div class="flex f-c">
      <div class="flex flex-1 ">
        <img class="image-mode" :src="res.list[1].img" alt />
      </div>
      <div class="flex flex-1 margin-t10">
        <img class="image-mode" :src="res.list[2].img" alt />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  title: "左一右二",
  props: ["res"]
};
</script>
<style lang="scss" scoped>
@import "./tpl.scss";

</style>
