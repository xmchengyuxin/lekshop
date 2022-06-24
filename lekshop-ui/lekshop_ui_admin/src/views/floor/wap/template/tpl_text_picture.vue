
<template>
  <div class="layout grid grid-c-4 grid-g10 b-radius-10 bg-color-w wrap-tuijian padding-10">
    <div v-for="(item,index) in res.list" class="flex f-c f-a-c">
    	<span class="t-color-y f15-size f-w-500">{{item.title}}</span>
      <img style="width: 100%;" class="margin-t10 b-radius-5 w100" :src="item.img" alt />
    </div>
  </div>
</template>

<script>
export default {
  title: "文字图片模板",
  props: ["res"]
};
</script>
<style lang="scss" scoped>
@import "./tpl.scss";
</style>
