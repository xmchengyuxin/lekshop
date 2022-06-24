<template>
  <div class="layout">
    <div class="grid grid-c-4 h-40 margin-b10">
    	<div  @click="handleClickTitle(title, index)" :class="{ selected: selected.index == index }" v-for="(title, index) in res.list[0].titleWay" class="flex f-s-0 f-c f-a-c f-j-c h100 ">
    		<span :class="selected.index == index ? 't-color-y f18-size' : 'f15-size'" class=" flex f-a-c h-24 f-w-500">{{title.title}}</span>
    		<span class="f11-size t-color-9">{{title.desc}}</span>
    	</div>
    </div>

    <div class="goods-list ">
      <div
        v-if="
          item.___index != undefined
            ? selected.index == item.___index
            : selected.val == item.type
        "
        class="goods-item"
        v-for="(item, item_index) in res.list[0].listWay"
        :key="item_index"
      >
        <div class="goods-img">
          <i @click="closeGoods(item, item_index)"
            style="font-size: 20px;"
            class="el-icon-error"></i>

          <img :src="item.mainImg" alt />
        </div>
        <div class="goods-desc">
          <div class="goods-title">
            {{ item.title }}
          </div>
          <div class="goods-bottom margin-t4">
            <div class="text-price f16-size f-w-b t-color-y">{{ item.price }}</div>
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
    // 切换商品列表
    handleClickTitle(val, index) {
      this.selected.index = index;
      this.selected.val = val.title;
    },
  },
};
</script>
<style lang="scss" scoped>
@import "./tpl.scss";
.selected {
  > h4 {
    color: #000 !important;
  }
  > div {
    font-weight: bold;
    color: #000000 !important;
  }
}
.goods-cell-title {
  padding: 10px;
  transition: 0.35s;
  display: flex;
  cursor: pointer;
  > .goods-item-title {
    flex: 1;
    text-align: center;
    > h4 {
      font-size: 16px;
      color: #666;
    }
    > div {
      color: #999;
      font-size: 12px;
    }
  }
}
.goods-list {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
}
.goods-item {
  width: 48%;
  margin-right: 4%;
  margin-bottom: 10px;
  border-radius: 0.4em;
  overflow: hidden;
}
.goods-item:nth-child(2n+2) {
  margin-right: 0;
}
.goods-img {
  position: relative;
  margin: 0 auto;
  height: 150px;
  border-top-left-radius: 0.4em;
  border-top-right-radius: 0.4em;
  overflow: hidden;
  > img {
    width: 100%;
    height: 100%;
  }
  .el-icon-error {
    position: absolute;
    right: 10px;
    top: 2px;
  }
}
.goods-desc {
  padding: 12px;
  border-bottom-left-radius: 0.4em;
  border-bottom-right-radius: 0.4em;
  background: #fff;
  margin: 0 auto;
  > .goods-title {
    display: -webkit-box;
    font-weight: 500;
    -webkit-box-orient: vertical;

    -webkit-line-clamp: 2;

    overflow: hidden;
  }
  > .goods-bottom {
    display: flex;
    > .goods-price {
    }
  }
}
.goods-icon {
  right: 5px;
  top: 5px;
  position: absolute;
}
</style>
