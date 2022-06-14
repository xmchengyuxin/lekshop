<template>
  <div class="wrapper">
    <div class="list">
      <div
        class="list-item"
        v-for="(item, index) in Object.keys(promotionList)"
        :key="index"
        @click="clickPromotion(item, index)"
        :class="{ active: selectedIndex == index }"
      >
        {{ typeOption(item).title }}
      </div>

      <!-- <div class="list-item" >暂无活动</div> -->
    </div>
    <div class="content">
      <div v-if="showPromotionList">
        <!-- <div class="search-views">
          <Input v-model="value11" disabled class="search">
          <span slot="prepend">店铺名称</span>
          </Input>
          <Button type="primary">选择</Button>

        </div> -->

        <div class="tables">
          <el-table
            height="350"
            border
            tooltip
            :loading="loading"
            :columns="activeColumns"
            :data="showPromotionList"
          ></el-table>

          <pagination v-show="totals>0" :total="totals" :page.sync="params.page" :limit.sync="params.pageSize" @pagination="init" />
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import {
  getShopCouponList
} from "@/api/promotion";
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

export default {
  components: { Pagination },
  data() {
    return {
      totals: 0, // 总数
      loading: true, //表格请求数据为true
      promotionList: [], // 活动列表
      selectedIndex: 0, //左侧菜单选择
      promotions: "", //选中的活动key
      index: 999, // 已选下标
      params: {
        // 请求参数
        page: 1,
        pageSize: 10,
      },

      activeColumns: [], // 活动表头

      columns: [
        {
          title: "活动标题",
          key: "title",
          tooltip: true,
          width: 200,
        },
        {
          title: "商品名称",
          key: "goodsName",
          tooltip: true,
        },
        {
          title: "活动开始时间",
          key: "startTime",
          tooltip: true,
        },
        {
          title: "活动结束时间",
          key: "endTime",
          tooltip: true,
        },
        {
          title: "操作",
          key: "action",
          fixed: "right",
          width: 100,
          render: (h, params) => {
            return h("div", [
              h(
                "Button",
                {
                  props: {
                    // type: this.index == params.index ? "primary" : "",
                    size: "small",
                  },
                  on: {
                    click: () => {
                      this.selectedPromotion(params);
                    },
                  },
                },
                this.index == params.index ? "已选" : "选择"
              ),
            ]);
          },
        },
      ],

      promotionData: "", //商品集合

      showPromotionList: [], //显示当前促销的商品
    };
  },
  mounted() {
    this.init();
  },
  watch: {
    params: {
      handler() {
        this.index = 999;
        this.typeOption(this.promotions) &&
          this.typeOption(this.promotions).methodsed();
      },
      deep: true,
    },
  },

  methods: {
    sortGoods(type) {
      this.loading = false;
      this.params.pageNumber - 1;
      this.showPromotionList = this.promotionList[type];
    },
    typeOption(type) {
      // 活动选项
      switch (type) {
        case "COUPON":
          return {
            title: "优惠券",
            methodsed: () => {
              this.showPromotionList = [];
              this.sortGoods("COUPON");
            },
          };
        default:
          return {};
      }
    },
    // 选择活动
    selectedPromotion(val) {
      val.row.___type = "marketing";
      val.row.___promotion = this.promotions;
      this.$emit("selected", [val.row]);

      this.index = val.index;
    },
    // 获取所有营销的活动
    async init() {
      let res = await getShopCouponList();
      if (res.code = 200) {
        this.loading = false;
        this.getPromotion(res);
        // this.clickPromotion(this.typeOption[Object.keys(res.result)[0]], 0);
      } else {
        this.loading = false;
      }
    },
    getPromotion(res) {
      if (res.data) {
        this.promotionList = res.data.list;
        this.typeOption(Object.keys(res.data.list)[0]).methodsed();
      }

      // if (Object.keys(res.result).length) {
      //   this.typeOption[Object.keys(res.result)[0]].methodsed(
      //     this.promotionList[Object.keys(res.result)[0]].id
      //   );
      // }
    },

    // 点击某个活动查询活动列表
    clickPromotion(val, i) {
      this.promotions = val;
      this.selectedIndex = i;
      this.params.page = 1;
      this.typeOption(val) &&
        this.typeOption(val).methodsed(this.promotionList[val].id);
    },
  },
};
</script>
<style lang="scss" scoped>
img {
  max-width: 100% !important;
}
.search {
  width: 300px;
}
.page {
  margin-top: 2vh;
  text-align: right;
}
.time {
  font-size: 12px;
}
.tables {
  height: 400px;
  margin-top: 20px;
  overflow: auto;
  width: 100%;
}
::v-deep .ivu-table-wrapper {
  width: 100%;
}
.list {
  margin: 0 1.5%;
  height: 400px;
  overflow: auto;
  > .list-item {
    padding: 10px;
    transition: 0.35s;
    cursor: pointer;
  }
  .list-item:hover {
    background: #ededed;
  }
}
.list {
  flex: 1;
  width: auto;
}
.content {
  overflow: hidden;
  flex: 4;
}
.active {
  background: #ededed;
}
.wrapper {
  overflow: hidden;
}
.search-views {
  display: flex;
  > * {
    margin: 0 4px;
  }
}
</style>
