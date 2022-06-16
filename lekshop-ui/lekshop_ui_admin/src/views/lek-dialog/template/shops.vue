<template>
  <div class="shop">
    <div class="wap-content">
      <div class="query-wrapper">
        <div class="query-item">
          <el-input placeholder="请输入店铺名称" @on-clear="shopsData=[]; params.name=''; params.page =1; init()" @on-enter="()=>{shopsData=[]; params.name =''; init();}" icon="ios-search" clearable style="width: 150px"
            v-model="params.name" />
        </div>

        <div class="query-item">
          <el-button type="primary" size="mini" @click="shopsData=[];params.page =1; init();" >搜索</el-button>
          <el-pagination
            small
            layout="prev, pager, next"
            :total="total"
            :current-page.sync="params.page"
            :page-size.sync="params.pageSize"
            @current-change="init"
            >
          </el-pagination>
        </div>
      </div>
      <div>
        <div class="wap-content-list flex f-w">
          <div class="wap-content-item f-s-0" @click="clickShop(item,index)" :class="{ active:selected == index }" v-for="(item, index) in shopsData" :key="index">
            <div>
              <img class="shop-logo" :src="item.logo" alt="" />
            </div>
            <div class="wap-content-desc">
              <div class="wap-content-desc-title">{{ item.name }}</div>

              <div class="self-operated" :class="{'theme_color':item.selfStatus }">{{ item.selfStatus == 1 ? '自营' : '非自营' }}</div>
              <div class="wap-sku" :class="{'theme_color':(item.status === 1 ? true : false) }">{{ item.status === 1 ? '开启中' : '未开启' }}</div>
            </div>
          </div>
          <div  v-loading="loading" size="large" fix v-if="loading"></div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { getShopList } from "@/api/shop";
export default {
  data() {
    return {
      loading: false, // 加载状态
      total: 0, // 总数
      params: { // 请求参数
        page: 1,
        pageSize: 10,
        name: "",
      },
      shopsData: [], // 店铺数据
      selected: 9999999999, //设置一个不可能选中的index
    };
  },
  watch: {},

  created() {
    this.init();
  },
  methods: {
    handleReachBottom() {
      setTimeout(() => {
        if (this.params.page * this.params.pageSize <= this.total) {
          this.params.page++;
          this.init();
        }
      }, 1500);
    },
    init() {
      this.loading = true;
      getShopList(this.params).then((res) => {
        if (res.code == 200) {
          /**
           * 解决数据请求中，滚动栏会一直上下跳动
           */
          this.total = res.data.total;
          
          this.shopsData = res.data.list;
          
          // this.shopsData.push(...res.data.list);

          this.loading = false;
        }
      });
    },
    clickShop(val, i) {
      this.selected = i;
      val = { ...val, ___type: "shops" };
      this.$emit("selected", [val]);
    },
  },
};
</script>
<style lang="scss" scoped>
@import "../style.scss";
.shop {
  display: flex;
}
.self-operated {
  font-size: 12px;
  color: #999;
}
.wap-content-list {
  flex-wrap: wrap;
}
.shop-logo {
  object-fit: cover;
}
.wap-content-item {
}
.active {
  background: url("../../../assets/selected.png") no-repeat;
  background-position: right;
  background-size: 10%;
}
</style>
