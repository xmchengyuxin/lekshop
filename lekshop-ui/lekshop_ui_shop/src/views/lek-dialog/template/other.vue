<template>
  <div>
    <el-row :gutter="30">
      <el-col
        :span="4"
        v-for="(item, index) in linkList"
        :key="index"
        v-if="
          (item.title !== '拼团频道' && item.title !== '签到') ||
          $route.name !== 'renovation'
        "
      >
        <div
          class="card"
          :class="{ active: selectedIndex == index }"
          @click="handleLink(item, index)"
        >
          <svg-icon style="font-size: 20px;" :icon-class="item.icon" />
          <p>{{ item.title }}</p>
        </div>
      </el-col>
      <!-- 外部链接，只有pc端跳转 -->
      <el-col :span="4">
        <div
          class="card"
          :class="{ active: selectedIndex == linkList.length }"
          @click="handleLink(linkItem, linkList.length)"
        >
          <svg-icon style="font-size: 20px;" :icon-class="linkItem.icon" />
          <p>{{ linkItem.title }}</p>
        </div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
export default {
  data() {
    return {
      linkList: [
        // 链接列表
        {
          title: "首页",
          icon: "dashboard",
          ___ptype: "home",
        },
        {
          title: "购物车",
          icon: "shop-list",
          ___ptype: "cart",
        },
        {
          title: "收藏商品",
          icon: "food-list",
          ___ptype: "collection",
        },
        {
          title: "我的订单",
          icon: "order-list",
          ___ptype: "order",
        },
        {
          title: "个人中心",
          icon: "user",
          ___ptype: "user",
        },
        {
          title: "拼团频道",
          icon: "lock",
          ___ptype: "group",
        },
        {
          title: "秒杀频道",
          icon: "list",
          ___ptype: "seckill",
        },
        {
          title: "领券中心",
          icon: "promotion-coupon",
          ___ptype: "coupon",
        },
        {
          title: "签到",
          icon: "report-brand",
          ___ptype: "sign",
        },
        /* {
          title: "小程序直播",
          icon: "ios-videocam",
          ___type: "live",
        },
        {
          title: "砍价",
          icon: "md-share-alt",
          ___type: "kanjia",
        },
        {
          title: "积分商城",
          icon: "ios-basket",
          ___type: "point",
        }, */
      ],
      linkItem: {
        title: "外部链接",
        icon: "link",
        ___ptype: "link",
        url: "",
      },
      linkVisible: false, // 是否显示外部链接
      selectedIndex: 9999999, // 已选index
    };
  },
  methods: {
    handleLink(val, index) {
      val = { ...val, ___type: "other" };
      this.selectedIndex = index;
      this.$emit("selected", [val]);
    },
  },
};
</script>
<style lang="scss" scoped>
@import "../style.scss";
.card {
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-content: center;
  padding: 20px 0;
  margin: 10px 0;
  text-align: center;
  transition: 0.35s;
  cursor: pointer;
  ::v-deep p {
    margin: 10px 0;
  }
  border: 1px solid #ededed;
}
.card:hover {
  background: #ededed;
}
.active {
  background: #ededed;
}
</style>
