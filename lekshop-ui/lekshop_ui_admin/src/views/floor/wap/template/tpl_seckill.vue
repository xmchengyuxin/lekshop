<template>
  <div class="layout">
    <div class="join-list">
      <div class="join-title">
        <div>{{ res.list[0].titleWay[0].title }}</div>
        <div>{{ res.list[0].titleWay[0].desc }}</div>
        <div>更多 ></div>
      </div>
      <div class="join-box">
        <div class="join-item" v-for="item in res.list[0].listWay">
          <div class="item-img-box goods-img">
            <i @click="closeGoods(item, item_index)"
              style="font-size: 20px;"
              class="el-icon-error"></i>

            <img :src="item.mainImg" alt />
          </div>
          <div class="item-price">
            <div class="goods-price">￥{{ item.price }}</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      selected: {
        // 已选数据
        index: 0,
      },
    };
  },
  props: ["res"],
  watch: {
    res: {
      handler(val) {
        // 监听父级的值 如果有值将值赋给selected
        if (val) {
          this.selected.val = this.res.list[0].listWay[0].type;
        }
      },
      immediate: true,
    },
  },
  methods: {
    // 删除商品
    closeGoods(val, index) {
      this.res.list[0].listWay.splice(index, 1);
    },
  },
};
</script>
<style lang="scss" scoped>
@import "./tpl.scss";
@import './advertising.scss';

.goods-img {
  position: relative;
  margin: 0 auto;
  width: 158px;
  height: 150px;
  border-top-left-radius: 0.4em;
  border-top-right-radius: 0.4em;
  overflow: hidden;
  > img {
    width: 100%;
    height: 100%;
  }
}

</style>
