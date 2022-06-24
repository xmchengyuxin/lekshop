<template>
  <div class="layout bg-color-w wrap-tuijian  over-h">
    <div   class="flex padding-lr12 h-40 f-a-c f-j-s">
    	<div class="flex f-a-c">
    		<span class="f-w-b margin-r8" style="color: rgb(173, 78, 0);">{{res.list[0].titleWay[0].title}}</span>
    		<span class="f10-size" style="color: rgb(199, 90, 0);">{{res.list[0].titleWay[0].desc}}</span>
    	</div>
    	<div class="flex f-a-c">
    		<span class="f12-size t-color-9 margin-r2">更多 ></span>
    	</div>
    </div>
    <div class="flex padding-lr12 ">
    	<div @click="go('/pages/shops/detail?id='+item.id)" v-for="(goods,index) in res.list[0].listWay" class="flex  f-s-0 margin-r12 f-c pintuan-item">

    		<div :style="{'background-image': 'url('+goods.mainImg+')'}" class="padding50 bg-img b-radius-8">
          <i @click="closeGoods(goods, index)"
            style="font-size: 20px;"
            class="el-icon-error"></i>
        </div>
    		<div class="f12-size line1 margin-t4">{{goods.title}}</div>
    		<div class="flex f-a-c f-j-s margin-t4">
    			<div class="text-price f16-size t-color-y">{{goods.price}}</div>
    			<div class="bg-color-linear-r h-16 t-color-w f10-size padding-lr5 b-radius-30">拼</div>
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
