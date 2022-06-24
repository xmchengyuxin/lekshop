<template>
  <div class="layout bg-color-w padding-tb10">
    <div class="menu-list">
      <div class="menu-item" v-for="(item, index) in res.list" :key="index">
        <div class="h-50 flex f-a-c f-j-c">
          <img class="menu-img" :src="item.img" alt="" />
        </div>
        <div class="menu-title t-color-9">{{ item.title }}</div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  props: ["res"],
};
</script>
<style lang="scss" scoped>
@import "./tpl.scss";
.menu-list {
  display: flex;
  align-items: center;
  justify-content: space-between;
  flex-wrap: wrap;

  > .menu-item {
    text-align: center;
    width: 20%;
  }
}
.menu-img {
  width: 36px;
  height: 36px;
}
.menu-title {
  font-size: 12px;
}
</style>
