<template>
  <div class="decorate">
    <div class="decorate-title">
      {{ res.name }}
      <el-button
        style="margin-left: 20px"
        size="mini"
        ghost
        v-if="
          res.type == 'tpl_ad_list' ||
          res.type == 'tpl_activity_list' ||
          res.drawer
        "
        type="primary"
        @click="selectStyle()"
        >选择风格</el-button
      >
      <el-button
        style="margin-left: 20px"
        size="mini"
        ghost
        v-if="res.type == 'promotions' || res.drawerPromotions"
        type="primary"
        @click="selectPromotions()"
        >选择促销活动</el-button
      >
    </div>

    <!-- 右侧显示抽屉 -->
    <el-drawer title="选择风格" :closable="false" width="400" :visible.sync="styleFlag">
      <div class="drawer">
        <div
          class="drawer-item"
          @click="clickDrawer(item, index)"
          v-for="(item, index) in modelData"
          :key="index"
          v-if="item.drawer"
        >
          <img src alt />
          <span>{{ item.name }}</span>
        </div>
      </div>
    </el-drawer>

    <!-- 右侧显示抽屉 -->
    <el-drawer
      title="选择促销活动(最多只能展示两个活动)"
      :closable="false"
      width="400"
      :visible.sync="promotionsFlag"
    >
      <div class="drawer">
        <div
          class="drawer-item"
          @click="clickDrawer(item, index)"
          v-for="(item, index) in modelData"
          :key="index"
          v-if="item.drawerPromotions"
        >
          <img src alt />
          <span>{{ item.name }}</span>
        </div>
      </div>
    </el-drawer>

    <!-- 卡片集合 -->
    <div
      class="decorate-list"
      v-if="
        (res.type != 'tpl_ad_list' &&
          res.type != 'tpl_activity_list' &&
          res.type != 'promotions') ||
        res.drawer ||
        res.drawerPromotions
      "
    >
      <div
        class="decorate-item"
        v-for="(item, index) in res.options.list"
        :key="index"
      >
        <div class="decorate-item-title">
          <div>卡片</div>

          <i @click="closeDecorate(index)"
            v-if="res.close || res.type == 'promotionDetail'"
            style="font-size: 20px;"
            class="el-icon-error close"></i>
        </div>

        <div class="decorate-item-box">
          <div
            class="decorate-border"
            v-if="item.titleWay"
            v-for="(title_item, title_index) in item.titleWay"
            :key="title_index"
          >
            <div class="decorate-view">
              <div class="decorate-view-title">标题{{ title_index + 1 }}</div>
              <div>
                <el-input v-model="title_item.title" />
              </div>
            </div>
            <div class="decorate-view">
              <div class="decorate-view-title">描述</div>
              <div>
                <el-input v-model="title_item.desc" />
              </div>
            </div>
            <div class="decorate-view">
              <div class="decorate-view-title">绑定商品</div>
              <div
                class="decorate-view-link"
                v-if="res.options.list[0].listWay.length != 0"
              >
                <!-- 绑定商品选择器回调已选择的商品 -->
                <div
                  v-if="
                    title_item.___index == bindGoods.___index ||
                    title_item.title == bindGoods.type
                  "
                  v-for="(bindGoods, bindGoodsIndex) in res.options.list[0]
                    .listWay"
                  :key="bindGoodsIndex"
                >
                  {{ bindGoods.title }},
                </div>
              </div>
              <div>
                <el-button
                  @click="bindGoodsId(title_item)"
                  size="mini"
                  plain
                  type="danger"
                  >选择商品</el-button
                >
              </div>
            </div>
          </div>
          <!-- 选择照片 -->
          <div class="decorate-view" v-if="!res.notImg">
            <div class="decorate-view-title">选择照片</div>
            <div>
              <Upload class="show-image" v-model="item.img" />
              <ImageSource :mul="false" v-model="item.img" />
              <div class="tips" style="margin-top: 5px;">
                建议尺寸{{ item.size }}
              </div>
            </div>
          </div>
          <div
            class="decorate-view"
            v-if="item.title != void 0 && !res.notTitle && res.type == 'title'"
          >
            <div class="decorate-view-title">文字对齐方式</div>
            <div class="card-box">
              <div
                class="card"
                :class="{ active: textAlign == 'left' }"
                @click="changeTextAlign('left')"
              >
                <img
                  :src="require('@/assets/align-text-left.png')"
                  class="align-text"
                  alt=""
                />
              </div>
              <div
                class="card"
                :class="{ active: textAlign == 'center' }"
                @click="changeTextAlign('center')"
              >
                <img
                  :src="require('@/assets/align-text-center.png')"
                  class="align-text"
                  alt=""
                />
              </div>
              <!-- <div
                class="card"
                :class="{ active: textAlign == 'right' }"
                @click="changeTextAlign('right')"
              >
                <img
                  :src="require('@/assets/align-text-right.png')"
                  class="align-text"
                  alt=""
                />
              </div> -->
            </div>
          </div>
          <div
            class="decorate-view"
            v-if="
              item.title != void 0 &&
              !res.notTitle &&
              (res.type == 'title' ||
                res.type == 'notice' ||
                res.type == 'promotionDetail')
            "
          >
            <div class="decorate-view-title">背景颜色</div>
            <div class="decorate-view">
              <el-color-picker v-model="item.bk_color" size="mini"></el-color-picker>
              <el-input v-model="item.bk_color" />
            </div>
          </div>

          <!-- 填写标题 -->
          <div
            class="decorate-view"
            v-if="item.title != void 0 && !res.notTitle && res.type != 'notice'"
          >
            <div class="decorate-view-title">菜单标题</div>
            <div>
              <el-input v-model="item.title" style="width: 200px" />
            </div>
          </div>
          <div
            class="decorate-view"
            v-if="
              item.title != void 0 &&
              !res.notTitle &&
              (res.type == 'title' || res.type == 'notice')
            "
          >
            <div class="decorate-view-title">标题颜色</div>
            <div class="decorate-view">
              <el-color-picker v-model="item.color" size="mini"></el-color-picker>
              <el-input v-model="item.color" />
            </div>
          </div>
          <!-- 填写小标题 -->
          <div
            class="decorate-view"
            v-if="item.title1 != void 0 && !res.notTitle"
          >
            <div class="decorate-view-title">小标题</div>
            <div>
              <el-input v-model="item.title1" style="width: 200px" />
            </div>
          </div>
          <div
            class="decorate-view"
            v-if="item.title1 != void 0 && !res.notTitle"
          >
            <div class="decorate-view-title">小标题颜色</div>
            <div class="decorate-view">
              <el-color-picker v-model="item.color1" size="mini"></el-color-picker>
              <el-input v-model="item.color1" />
            </div>
          </div>
          <div
            class="decorate-view"
            v-if="res.type === 'notice' && !res.notTitle"
          >
            <div class="decorate-view-title">公告内容</div>
            <div>
              <div
                v-for="(t, tindex) in item.title"
                :key="tindex"
                class="decorate-notice"
              >
                <el-input v-model="t.context" style="width: 200px" />
                  <i
                    @click="removeNotice(index)"
                    style="font-size: 20px;"
                    class="el-icon-error close"></i>
              </div>
            </div>
          </div>

          <!-- 填写链接 -->

          <div class="decorate-view" v-if="!res.notLink">
            <div class="decorate-view-title">选择链接</div>
            <div
              v-if="item.url && item.url.length != 0"
              class="decorate-view-link"
            >
              已选链接：

              <span>
                {{
                  ways.find((e) => {
                    return item.url.___type == e.name;
                  }).title
                }}
                -
                <!-- 当选择完链接之后的商品名称 -->
                <span v-if="item.url.___type == 'goods'">
                  {{ item.url.title }}</span
                >
                <!-- 当选择完链接之后的分类回调 -->
                <span v-if="item.url.___type == 'category'">
                  {{ item.url.name }}</span
                >
                <!-- 当选择完链接之后的店铺回调 -->
                <span v-if="item.url.___type == 'shops'">
                  {{ item.url.name }}</span
                >
                <!-- 当选择完链接之后的其他回调 -->
                <span v-if="item.url.___type == 'other'">
                  {{ item.url.title }}</span
                >
                <!-- 当选择完活动之后的其他回调 -->
                <span v-if="item.url.___type == 'marketing'">
                  <span v-if="item.url.___promotion == 'SECKILL'"> 秒杀 </span>
                  <span v-if="item.url.___promotion == 'FULL_DISCOUNT'">
                    满减
                  </span>
                  <span v-if="item.url.___promotion == 'PINTUAN'"> 拼团 </span>
                  {{ item.url.title || item.url.goodsName }}
                </span>
                <!-- 当选择完活动之后的其他回调 -->
                <span v-if="item.url.___type == 'pages'">
                  {{ item.url.title }}</span
                >
              </span>
            </div>

            <div>
              <el-button
                size="mini"
                type="danger"
                plain
                @click="clickLink(item, index)"
                >选择链接</el-button
              >
            </div>
          </div>
          <!-- 链接地址-->
          <div
            class="decorate-view"
            v-if="item.url && item.url.___type == 'other'"
          >
            <div class="decorate-view-title">外部链接</div>
            <div>
              <el-input v-model="item.url.url" style="width: 200px" />
            </div>
          </div>

          <p v-if="item.url && item.url.___type == 'other'">
            (如非同域名下，则在小程序与公众号中无效)
          </p>
        </div>
      </div>
    </div>

    <el-button
      v-if="
        res.type != 'tpl_ad_list' &&
        res.type != 'tpl_activity_list' &&
        !res.notAdd
      "
      type="primary"
      @click="addDecorate(res.type)"
      ghost
      >添加</el-button
    >

    <lekDialog
      ref="lekDialog"
      @selectedLink="selectedLink"
      @selectedGoodsData="selectedGoodsData"
    ></lekDialog>

  </div>
</template>
<script>
import { modelData } from "./config";
import ways from "@/views/lek-dialog/wap.js"; // 选择链接的类型
import Upload from '@/components/Upload/singleImage'
import ImageSource from '@/components/Upload/imageSource'

export default {
  components: { Upload, ImageSource },
  data() {
    return {
      ways, // 选择链接的类型
      picModelFlag: false, //图片选择器
      linkType: "goods", // dialog弹窗口类型
      styleFlag: false, //广告魔方开关
      textAlign: this.res.options.list[0].textAlign || "center", //文字对齐方式
      promotionsFlag: false, //广告魔方开关
      selectedLinkIndex: "", //选择链接的索引
      modelData, // 装修数据
      selectedGoods: "", // 已选商品
      selectedLinks: "", // 已选链接
      modelList: "", // 装修列表
    };
  },
  watch: {
    res: {
      handler: (val) => {},
      deep: true,
    },
  },
  props: ["res"],
  methods: {
    // 选择风格
    selectStyle() {
      this.styleFlag = !this.styleFlag;
    },
    selectPromotions() {
      this.promotionsFlag = !this.promotionsFlag;
    },
    // 回调选择的链接
    selectedLink(val) {
      this.selectedLinks.url = val;
    },
    // 回调的商品信息
    selectedGoodsData(val) {
      if (!val) return false;
      let data = val.map((item) => {
        delete item.selected;
        delete item.intro;
        delete item.mobileIntro;
        return {
          img: item.thumbnail,
          title: item.goodsName,
          type: this.selectedGoods.title,
          ___index: this.selectedGoods.___index,
          ...item,
        };
      });
      this.res.options.list[0].listWay.push(...data);
      this.linkType = "";
    },
    // 绑定商品
    bindGoodsId(val) {
      this.selectedGoods = val;
      this.lekDialogFlag(true);
    },
    // 点击抽屉
    clickDrawer(item, index) {
      this.$emit("handleDrawer", item);
      this.styleFlag = false;
    },
    // 打开图片选择器
    lekDialogFlag(flag) {
      this.$refs.lekDialog.clearGoodsSelected()
      this.$refs.lekDialog.goodsFlag = flag;
      this.$refs.lekDialog.flag = true;
    },

    changeTextAlign(val) {
      this.res.options.list[0].textAlign = val;
      this.textAlign = val;
    },
    // 点击链接赋值一个唯一值，并将当前选择的模块赋值
    clickLink(val, index) {
      this.selectedLinks = val;
      this.lekDialogFlag(false);
    },
    //点击图片解析成base64
    changeFile(item, index) {
      const file = document.getElementById("files" + index).files[0];
      if (file == void 0) return false;
      const reader = new FileReader();
      reader.readAsDataURL(file);
      this.$nextTick((res) => {
        reader.onload = (e) => {
          item.img = e.target.result;
        };
      });
    },
    //添加设置
    addDecorate(type) {
      if (type === "notice") {
        this.res.options.list[0].title.push({
          content: "",
        });
      } else {
        let way = {
          img: "https://picsum.photos/id/264/200/200",
          title: "标题",
          link: "",
          url: "",
          size: this.res.options.list[0].size,
        };
        this.res.options.list.push(way);
      }
    },
    // 图片选择器回显
    callbackSelected(val) {
      this.picModelFlag = false;
      this.selectedGoods.img = val.url;
    },
    // 点击选择图片
    handleClickFile(item, index) {
      this.$refs.ossManage.selectImage = true;
      this.selectedGoods = item;
      this.picModelFlag = true;
    },
    removeNotice(index) {
      this.$nextTick(() => {
        this.res.options.list[0].title.splice(index, 1);
      });
    },
    // 关闭
    closeDecorate(index) {
      this.$nextTick(() => {
        this.res.options.list.splice(index, 1);
      });
    },
  },
};
</script>
<style scoped lang="scss">
@import "./decorate.scss";
</style>
