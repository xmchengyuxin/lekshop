<template>
  <div class="wrapper">
    <div class="wap-content">
      <div class="query-wrapper">
        <div class="query-item">
          <el-select v-model="goodsParams.type" placeholder="全部商品" class="filter-item" filterable style="width: 100px;" @change="()=>{goodsData=[];goodsParams.page =1; getQueryGoodsList();}">
              <el-option label="全部商品" value=""> </el-option>
              <el-option label="秒杀商品" value="2"> </el-option>
              <el-option label="拼团商品" value="3"> </el-option>
            </el-select>
          <el-input placeholder="商品名称" @on-clear="goodsData=[]; goodsParams.title=''; goodsParams.page = 1; getQueryGoodsList()" @on-enter="()=>{goodsData=[];goodsParams.page =1; getQueryGoodsList();}" icon="ios-search" clearable
            style="width: 150px" v-model="goodsParams.title" />
        </div>
        <!-- <div class="query-item">
          <Cascader v-model="category" placeholder="请选择商品分类" style="width: 250px" :data="skuList"></Cascader>
        </div> -->
        <div class="query-item">
          <el-button type="primary" @click="goodsData=[]; getQueryGoodsList();" icon="ios-search">搜索</el-button>
          <el-pagination
            small
            layout="prev, pager, next"
            :total="total"
            :current-page.sync="goodsParams.page"
            :page-size.sync="goodsParams.pageSize"
            @current-change="getQueryGoodsList"
            >
          </el-pagination>
          <!-- <pagination v-show="total>0" :total="total" :page.sync="goodsParams.page" :limit.sync="goodsParams.pageSize" @pagination="getQueryGoodsList" /> -->
        </div>
      </div>
      <div style="positon:retavle;">
        <div  class="wap-content-list flex f-w" style="height: 500px;">
          <div class="wap-content-item f-s-0" :class="{ active: item.selected }" @click="checkedGoods(item, index)" v-for="(item, index) in goodsData" :key="index">
            <div>
              <img :src="item.mainImg" alt="" />
            </div>
            <div class="wap-content-desc">
              <div class="wap-content-desc-title">
                <span class="pintuan" v-if="item.type == 3">拼</span>
                <span class="miaosha" v-if="item.type == 2">秒</span>
                {{ item.title }}
              </div>
              <div class="wap-content-desc-bottom">
                <div>￥{{ item.price }}</div>
              </div>
            </div>
          </div>
          <div v-loading="loading" size="large" fix v-if="loading"></div>

          <div v-if="goodsData.length == 0" class="empty">暂无商品信息</div>
        </div>

      </div>
    </div>
  </div>
</template>
<script>
import * as API_Goods from "@/api/goods";
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
export default {
  components: { Pagination },
  data() {
    return {
      type: "multiple", //单选或者多选 single  multiple
      skuList: [], // 商品sku列表
      total: 0,  // 商品总数
      goodsParams: { // 商品请求参数
        page: 1,
        pageSize: 20,
        goodsName: "",
        sn: "",
        categoryPath: "",
        marketEnable: "UPPER",
        authFlag: "PASS",
      },
      category: [], // 分类
      goodsData: [], // 商品数据
      empty: false, // 空数据
      loading: false, // 加载状态
    };
  },
  props: {
    selectedWay: {
      type: Array,
      default: function(){
        return new Array()
      }
    }
  },
  watch: {
    category(val) {
      this.goodsParams.categoryPath = val[2];
    },
    selectedWay: {
      handler() {
        this.$emit("selected", this.selectedWay);
      },
      deep: true,
      immediate: true
    },
    "goodsParams.categoryPath": {
      handler: function () {
        this.goodsData = [];
        (this.goodsParams.page = 0), this.getQueryGoodsList();
      },
      deep: true,
    },
  },
  mounted() {
    this.init();
  },
  methods: {
    // 触底加载更多方法
    handleReachBottom() {
      setTimeout(() => {
        if (
          this.goodsParams.page * this.goodsParams.pageSize <=
          this.total
        ) {
          this.goodsParams.page++;
          this.getQueryGoodsList();
        }
      }, 1500);
    },
    // 获取商品列表
    getQueryGoodsList() {
      API_Goods.getGoodsList(this.goodsParams).then((res) => {
        this.initGoods(res);
      });
    },
    // 获取列表方法
    initGoods(res) {
      if (res.data.list.length !=0) {
        res.data.list.forEach((item) => {
          item.selected = false;
          item.___type = "goods"; //设置为goods让pc wap知道标识
          this.selectedWay.forEach(e => {
            if (e.id && e.id === item.id) {
              item.selected = true
            }
          })
        });
        /**
         * 解决数据请求中，滚动栏会一直上下跳动
         */
        this.total = res.data.total;
        this.goodsData = res.data.list;
        // this.goodsData.push(...res.data.list);

      } else {
        this.empty = true;
      }
    },
    // 查询商品
    init() {
      API_Goods.getGoodsList(this.goodsParams).then((res) => {
        // 商品
        this.initGoods(res);
      });
      /* if (localStorage.getItem('category')) {
        this.deepGroup(JSON.parse(localStorage.getItem('category')))
      } else {
        setTimeout(() => {
          this.deepGroup(JSON.parse(localStorage.getItem('category')))
        },3000)
      } */
    },

    deepGroup(val) {
      val.forEach((item) => {
        let childWay = []; //第二级
        // 第二层
        if (item.children) {
          item.children.forEach((child) => {
            // // 第三层
            if (child.children) {
              child.children.forEach((grandson, index, arr) => {
                arr[index] = {
                  value: grandson.id,
                  label: grandson.name,
                  children: "",
                };
              });
            }
            let children = {
              value: child.id,
              label: child.name,
              children: child.children,
            };
            childWay.push(children);
          });
        }
        // 第一层
        let way = {
          value: item.id,
          label: item.name,
          children: childWay,
        };

        this.skuList.push(way);
      });
    },

    /**
     * 点击商品
     */
    checkedGoods(val, index) {
      // 如果单选的话
      if (this.type != "multiple") {
        this.goodsData.forEach((item) => {
          item.selected = false;
        });
        this.selectedWay = [];
        val.selected = true;
        this.selectedWay.push(val);

        return false;
      }

      if (val.selected == false) {
        val.selected = true;
        this.selectedWay.push(val);
      } else {
        val.selected = false;
        for (let i = 0; i<this.selectedWay.length; i++ ) {
          if (this.selectedWay[i].id===val.id) {
            this.selectedWay.splice(i,1)
            break;
          }
        }
      }
    },
  },
};
</script>
<style scoped lang="scss">
@import "./style.scss";
.wap-content {
  width: 100%;
}
.empty {
  text-align: center;
  padding: 8px 0;
  width: 100%;
}
.wap-content {
  flex: 1;
  padding: 0;
}
.wap-content-list {
  position: relative;
}
.wap-content-item {
  width: 210px;
  margin: 10px 7px;
  padding: 6px 0;
}
// .wap-content-item{

// }
.active {
  background: url("../../assets/selected.png") no-repeat;
  background-position: right;
  background-size: 10%;
}

.pintuan {
    background-color: #E65D6E;
    color: #fff;
    border-radius: 5%;
    font-size: 12px;
  }
  .miaosha{
    background-color: #5555ff;
    color: #fff;
    border-radius: 5%;
  }
</style>
