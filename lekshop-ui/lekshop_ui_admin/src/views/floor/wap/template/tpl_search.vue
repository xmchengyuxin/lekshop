<template>
  <div>
    <div class="layout bg-color-linear-y">
        <div class="search h-44 flex f-a-c">
          <div class="flex f-a-c f-j-c w-36 f20-size el-icon-full-screen t-color-w"></div>
          <div class="flex flex-1 f-a-c bg-color-w b-radius-30 t-color-9 h-34 padding-lr12 margin-r10">
            <i class="el-icon-search margin-r12 f20-size" /> {{res.list[0].title}}
          </div>
          <div class="flex f-a-c f-j-c f20-size el-icon-s-ticket t-color-w"></div>
          <div class="flex f-a-c f-j-c f20-size w-36 el-icon-menu t-color-w"></div>
        </div>
        <div class="h-30 flex padding-lr12">
          <div  class="flex f-s-0  f-a-c h100 margin-r20 t-color-w  f-w-500">推荐</div>
          <div  class="flex f-s-0  f-a-c h100 margin-r20 t-color-w  f-w-500">女装</div>
          <div  class="flex f-s-0  f-a-c h100 margin-r20 t-color-w  f-w-500">女装</div>
          <div  class="flex f-s-0  f-a-c h100 margin-r20 t-color-w  f-w-500">女装</div>
          <div  class="flex f-s-0  f-a-c h100 margin-r20 t-color-w  f-w-500">女装</div>
          <div  class="flex f-s-0  f-a-c h100 margin-r20 t-color-w  f-w-500">女装</div>
        </div>
    </div>
    <div class="h-44"></div>
    <div class="h-30"></div>
  </div>
</template>
<script>
export default {
    props: ['res']
}
</script>
<style lang="scss" scoped>
@import "./tpl.scss";
.layout{
    position: absolute;
    z-index: 1;
    width: 100%;
    overflow-x: hidden;
    border-radius: 0;
}
</style>
