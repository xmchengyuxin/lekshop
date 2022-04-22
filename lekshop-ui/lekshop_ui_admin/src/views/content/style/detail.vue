<template>
  <div class="createPost-container" style="position:relative;left:-100px;width: 1000px;margin: 20px auto;padding: 30px 100px 250px 70px; border: 1px solid #f4f4f4;">
    <div class="wrap-page">
      <div class="bg ">
        <div class="" :style="{'background-image':'url('+config.indexBackground+')'}"></div>
      </div>
      <div class="wrap-contain">
        <div class="wrap-swiper">
          <el-carousel indicator-position="outside" arrow="never">
            <el-carousel-item v-for="item in bannerOptions" :key="item.id">
              <div class="swiper-item b-radius-5 bg-color-w">
                <img :src="item.img" />
              </div>
            </el-carousel-item>
          </el-carousel>
        </div>
        <div class="wrap-menu b-radius-5 bg-color-w">
          <div class="menu-contain flex f-c f-j-s">
            <div class="flex">
              <div class="menu-1 flex f-s-0 flex-1 f-a-c f-j-c" :style="{'background-image':'url('+config.orderImg+')'}">
                <span class="menu-1-type">A</span>
                <Upload v-model="config.orderImg" />
              </div>
              <div class=" flex f-c flex-1 f-j-s">
                <div class="flex menu-2 f-a-c f-j-c" :style="{'background-image':'url('+config.walletImg+')'}">
                  <span class="menu-1-type">B</span>
                  <Upload v-model="config.walletImg" />
                </div>
                <div class="flex menu-3 f-a-c f-j-c" :style="{'background-image':'url('+config.giveImg+')'}">
                  <span class="menu-1-type">C</span>
                  <Upload v-model="config.giveImg" />
                </div>
              </div>
            </div>
            <div class="flex f-j-s">
              <div class="menu-4 flex f-s-0 f-a-c f-j-c" :style="{'background-image':'url('+config.bookingOrderImg+')'}">
                <span class="menu-1-type">D</span>
                <Upload v-model="config.bookingOrderImg" />
              </div>
              <div class="menu-4 flex f-s-0 f-a-c f-j-c" :style="{'background-image':'url('+config.groupBuyImg+')'}">
                <span class="menu-1-type">E</span>
                <Upload v-model="config.groupBuyImg" />
              </div>
              <div class="menu-4 flex f-s-0 f-a-c f-j-c" :style="{'background-image':'url('+config.inviteImg+')'}">
                <span class="menu-1-type">F</span>
                <Upload v-model="config.inviteImg" />
              </div>
              <div class="menu-4 flex f-s-0 f-a-c f-j-c" :style="{'background-image':'url('+config.businessImg+')'}">
                <span class="menu-1-type">G</span>
                <Upload v-model="config.businessImg" />
              </div>
            </div>
          </div>
        </div>
        <div class="wrap-tuijian bg-color-w">
          <div class="flex f-j-s f-a-c wrap-title">
            <span class="title f16-size f-w-b">为您推荐</span>
            <span class="f12-size f-w-b" :style="{'color':config.systemColor}">换一组</span>
          </div>
          <div class="flex f-j-s">
            <div class="goods-tuijian flex f-c f-s-0">
              <div class="tuijian-img b-radius-5 bg-color">
              </div>
              <div class="flex f-a-c f-j-s wrap-tuijian-price">
                <span class="t-color-p f12-size f-w-b">¥7.5</span>
                <div class="add flex f-a-c f-j-c b-radius-30 t-color-w" :style="{'background-color':config.systemColor}"></div>
              </div>
            </div>

            <div class="goods-tuijian flex f-c f-s-0">
              <div class="tuijian-img b-radius-5 bg-color">
              </div>
              <div class="flex f-a-c f-j-s wrap-tuijian-price">
                <span class="t-color-p f12-size f-w-b">¥7.5</span>
                <div class="add flex f-a-c f-j-c b-radius-30 t-color-w" :style="{'background-color':config.systemColor}"></div>
              </div>
            </div>

            <div class="goods-tuijian flex f-c f-s-0">
              <div class="tuijian-img b-radius-5 bg-color">
              </div>
              <div class="flex f-a-c f-j-s wrap-tuijian-price">
                <span class="t-color-p f12-size f-w-b">¥7.5</span>
                <div class="add flex f-a-c f-j-c b-radius-30 t-color-w" :style="{'background-color':config.systemColor}"></div>
              </div>
            </div>
          </div>
        </div>

      </div>
      <div class="nav-tabs">
        <div class="tab-list flex flex-1">
          <span class="tab-text" :style="{'color':config.systemColor}">首页</span>
          <!-- <img src="./img/index-on.png" alt="" class="iconPath"> -->
          <i class="iconfont" :style="{'color':config.systemColor}">&#xe639;</i>
        </div>
        <div class="tab-list flex flex-1">
          <span class="tab-text">菜单</span>
          <img src="./img/menu.png" alt="" class="iconPath">
        </div>
        <div class="tab-list flex flex-1">
          <span class="tab-text">订单</span>
          <img src="./img/order.png" alt="" class="iconPath">
        </div>
        <div class="tab-list flex flex-1">
          <span class="tab-text">购物车</span>
          <img src="./img/car.png" alt="" class="iconPath">
        </div>
        <div class="tab-list flex flex-1">
          <span class="tab-text">我的</span>
          <img src="./img/user.png" alt="" class="iconPath">
        </div>
      </div>

    </div>
    <el-form class="wrap-color" ref="dataForm" :model="config" label-width="100px" label-position="right">
      <el-form-item  class="flex f-a-c" label="系统主题颜色" prop="systemColor">
        <el-color-picker style="margin-left: 0;" v-model="config.systemColor"></el-color-picker>
      </el-form-item>
      <el-form-item  class="flex f-a-c" label="系统字体颜色" prop="fontColor">
        <el-color-picker style="margin-left: 0;" v-model="config.fontColor"></el-color-picker>
      </el-form-item>
      <el-form-item  class="flex f-a-c" label="A区页面跳转" prop="orderUrl">
        <el-input style="width: 250px;" v-model="config.orderUrl"></el-input>
        <span style="font-size: 12px;">A区图片建议大小<span style="color: red;">240px*196px</span></span>
      </el-form-item>
      <el-form-item  class="flex f-a-c" label="B区页面跳转" prop="walletUrl">
        <el-input style="width: 250px;" v-model="config.walletUrl"></el-input>
        <span style="font-size: 12px;">B区图片建议大小<span style="color: red;">220px*94px</span></span>
      </el-form-item>
      <el-form-item  class="flex f-a-c" label="C区页面跳转" prop="giveUrl">
        <el-input style="width: 250px;" v-model="config.giveUrl"></el-input>
        <span style="font-size: 12px;">C区图片建议大小<span style="color: red;">220px*94px</span></span>
      </el-form-item>
      <el-form-item  class="flex f-a-c" label="D区页面跳转" prop="bookingOrderUrl">
        <el-input style="width: 250px;" v-model="config.bookingOrderUrl"></el-input>
        <span style="font-size: 12px;">D区建议大小<span style="color: red;">112px*126px</span></span>
      </el-form-item>
      <el-form-item  class="flex f-a-c" label="E区页面跳转" prop="groupBuyUrl">
        <el-input style="width: 250px;" v-model="config.groupBuyUrl"></el-input>
        <span style="font-size: 12px;">E区建议大小<span style="color: red;">112px*126px</span></span>
      </el-form-item>
      <el-form-item  class="flex f-a-c" label="F区页面跳转" prop="inviteUrl">
        <el-input style="width: 250px;" v-model="config.inviteUrl"></el-input>
        <span style="font-size: 12px;">F区建议大小<span style="color: red;">112px*126px</span></span>
      </el-form-item>
      <el-form-item  class="flex f-a-c" label="G区页面跳转" prop="businessUrl">
        <el-input style="width: 250px;" v-model="config.businessUrl"></el-input>
        <span style="font-size: 12px;">G区建议大小<span style="color: red;">112px*126px</span></span>
      </el-form-item>
      <el-form-item class="flex f-a-c" label="首页背景图片" prop="indexBackground">
        	<Upload v-model="config.indexBackground"/>
          <ImageSource :mul="false" v-model="config.indexBackground" />
          <span style="font-size: 12px;">首页背景图片建议大小<span style="color: red;">810px*540px</span></span>
      </el-form-item>
      <el-form-item class="flex f-a-c" label="菜单背景图片" prop="menuBackground">
        	<Upload v-model="config.menuBackground"/>
          <ImageSource :mul="false" v-model="config.menuBackground" />
          <span style="font-size: 12px;">菜单购物车背景建议大小<span style="color: red;">750px*397px</span></span>
      </el-form-item>
      <el-form-item class="flex f-a-c" style="margin-left: 0;">
        <el-button style="margin-left: 5px;"  type="primary" size="mini" @click="confirm">更新系统风格</el-button>
      </el-form-item>
      <!-- <el-form-item label="现在下单图片" prop="orderImg">
        	<Upload v-model="config.orderImg"/>
      </el-form-item>
      <el-form-item label="早餐钱包图片" prop="walletImg">
        	<Upload v-model="config.walletImg"/>
      </el-form-item>
      <el-form-item label="预约下单图片" prop="bookingOrderImg">
        	<Upload v-model="config.bookingOrderImg"/>
      </el-form-item>
      <el-form-item label="团购图片" prop="groupBuyImg">
        	<Upload v-model="config.groupBuyImg"/>
      </el-form-item>
      <el-form-item label="邀请好友图片" prop="inviteImg">
        	<Upload v-model="config.inviteImg"/>
      </el-form-item>
      <el-form-item label="招商合作图片" prop="businessImg">
        	<Upload v-model="config.businessImg"/>
      </el-form-item> -->


    </el-form>
    <div style="padding-top: 60px;"></div>
  </div>
</template>
<style scoped="scoped">
  ::v-deep .line {
    span-align: center;
  }

  @font-face {font-family: "iconfont";
    src: url('./img/iconfont.woff') format('woff') /* IE6-IE8 */

  }

  .iconfont {
    font-family: "iconfont" !important;
    font-size: 16px;
    font-style: normal;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
  }

</style>

<script>
  import {
    getStyle,
    updateStyle
  } from '@/api/style'
  import Upload from '@/components/Upload/singleImage'
  import waves from '@/directive/waves' // Waves directive
  import {
    parseTime,
    renderTime
  } from '@/utils'
  import ImageSource from '@/components/Upload/imageSource'

  export default {
    name: 'styleDetail',
    components: {
      Upload,ImageSource
    },
    directives: {
      waves
    },
    data() {
      return {
        config: {},
        brandId: null,
        listLoading: false,
        bannerOptions:[]
      }
    },
    created() {
      // const id = this.$route.params && this.$route.params.id
      // this.brandId = id
      this.getStyle()
    },
    methods: {
      getStyle() {
        this.listLoading = true
        getStyle().then(response => {
          this.config = response.data.styleResult
          if (!this.config) this.config = {
            systemColor: 'rgba(156, 210, 30, 1)',
            fontColor : 'rgba(156, 210, 30, 1)'
          }
          if (!this.config.systemColor) this.config.systemColor = 'rgba(156, 210, 30, 1)'
          if (!this.config.fontColor) this.config.fontColor = 'rgba(156, 210, 30, 1)'
          this.bannerOptions = response.data.bannerList
          this.listLoading = false
        })
      },
      confirm() {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            let formData = Object.assign({}, this.config)
            formData.addTime = null
            formData.updTime = null
            updateStyle(formData).then(() => {
              this.$notify({
                title: '成功',
                message: '风格设置成功',
                type: 'success',
                duration: 2000
              })
            })
          }
        })
      },

    }
  }
</script>
<style scoped="scoped">
  .wrap-page ::v-deep .avatar-uploader-icon {
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    line-height: none;
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .wrap-page ::v-deep .el-upload,.wrap-page ::v-deep .avatar-uploader {
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    overflow: hidden;
  }
  .wrap-page ::v-deep .el-upload img {
    display: none;
  }
  .wrap-page {
    position: relative;
    display: inline-block;
    width: 375px;
    overflow-x: hidden;
    background-color: #f4f4f4;
    padding-bottom: 60px;
    transform: scale(0.75);
    transform-origin: left top;
  }
   .wrap-color ::v-deep .el-form-item__content {
    margin-left: 0!important;
  }

.wrap-color ::v-deep .el-color-picker{
  margin-top: 12px;
}
  .wrap-color {
    position: absolute;
    left: 370px;
    right: 0;
    top: 30px;
  }

  .bg {
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    height: 270px;
    overflow: hidden;
  }

  .bg div {
    width: 830rpx;
    height: 100%;
    transform: translateX(-40rpx);
    margin: auto;
    border-bottom-left-radius: 30px;
    border-bottom-right-radius: 30px;
  }

  ::v-deep .el-carousel__indicators {
    display: none;
  }

  ::v-deep .el-carousel__container {
    margin: 20px 10px 10px;
  }

  ::v-deep .el-carousel__container {
    height: 190px;
    border-radius: 5px;
    overflow: hidden;
  }

  .swiper-item {
    height: 100%;
    background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
  }

  .wrap-contain {
    position: relative;
  }

  .wrap-menu {
    position: relative;
    width: 355px;
    height: 256px;
    margin: auto;
  }
   .wrap-menu div {
     background-position: center;
     background-repeat: no-repeat;
     background-size: cover;
   }

  .wrap-tuijian {
    padding: 10px;
    margin-top: 10px;
  }

  .wrap-title {
    padding: 6px 0 10px 0;
  }

  .goods-tuijian {
    width: 110px;
  }

  .tuijian-img {
    height: 100px;
    background-image: url(img/goods1.png);
    background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
  }
   .goods-tuijian:nth-child(2) .tuijian-img {
     background-image: url(img/goods2.png);
   }
   .goods-tuijian:nth-child(3) .tuijian-img {
     background-image: url(img/goods3.png);
   }

  .wrap-tuijian-price {
    padding: 5px;
  }

  .new-item {
    width: 250px;
    height: 150px;
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
    margin-right: 12px;
  }

  .eat-title {
    padding: 20px 0 6px;
  }

  .eat-title img {
    width: 15px;
  }

  .eat-title span {
    color: #101010;
    margin: 0 8px 0 10px;
  }

  .eat-title-tip {
    color: #888;
  }

  .wrap-goods-col {
    padding: 0 10px;
  }

  .goods-col {
    width: 50%;
    padding-right: 7px;
    margin-top: 15px;
  }

  .goods-col:nth-child(2n+2) {
    padding-left: 7px;
    padding-right: 0;
  }

  .good-img {
    padding: 50%;
    background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
  }

  .goods-col .b-radius-5 {
    overflow: hidden;
  }

  .goods-info {
    padding: 10px 6px 10px 10px;
  }

  .goods-name {
    color: #555;
  }

  .goods-name-english {
    color: #888;
    margin-bottom: 4px;
    font-family: PingFang-SC-Regular;
  }

  .goods-sell {
    color: #999999;
    margin-top: 4px;
  }

  .nav-tabs {
    width: 100%;
    display: flex;
    position: absolute;
    bottom: 0;
    left: 0;
    right: 0;
    background: rgba(255, 255, 255, 1);
    box-shadow: -2px 0px 5px 0px rgba(148, 148, 148, 0.13);
  }

  .tab-list {
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column-reverse;
    height: 50px;
  }

  .tab-text {
    font-size: 12px;
    line-height: 18px;
    color: #555555;
  }

  .iconPath {
    width: 20px;
  }

  .tab-content {
    flex: 1;
  }

  .default {
    line-height: 37px;
    text-align: center;
    flex: 1;
    font-weight: bold;
    font-size: 14px;
  }

  .active {
    line-height: 37px;
    text-align: center;
    flex: 1;
    font-weight: bold;
    font-size: 14px;
  }

  .show {
    display: block;
    flex: 1;
  }

  .hidden {
    display: none;
    flex: 1;
  }

  .tab-list:nth-child(4) img {
    position: relative;
    left: -4px;
  }

  .menu-contain {
    position: absolute;
    top: 10px;
    left: 10px;
    right: 10px;
    bottom: 10px;
  }

  .menu-1 {
    position: relative;
    height: 140px;
    flex-basis: 10px;
    background: #fff;
    border: 1px dashed #ccc;
  }
.menu-1-type {
  position: absolute;
  right: 4px;
  top: 2px;
  z-index: 1;
  color:red;
}
  .menu-2,
  .menu-3 {
    position: relative;
    height: 67px;
    background: #fff;
    border: 1px dashed #ccc;
    margin-left: 6px;
  }

  .menu-3 {}

  .menu-4 {
    position: relative;
    width: 80px;
    height: 90px;
    background: #fff;
    border: 1px dashed #ccc;
  }

  .add {
    position: relative;
    width: 18px;
    height: 18px;
    background-color: red;
  }

  .add::after {
    content: '+';
    transform: scale(1.3);
    position: relative;
    top: 0px;
  }


  .t-color,
  .van-tab-color .van-tab--active {
    color: #90C42F !important;
  }

  .t-color-w {
    color: #fff !important;
  }

  .t-color-5 {
    color: #555 !important;
  }

  .t-color-6 {
    color: #666 !important;
  }

  .t-color-8 {
    color: #888 !important;
  }

  .t-color-9 {
    color: #999 !important;
  }

  .t-color-0 {
    color: #000 !important;
  }

  .bg-color,
  .van-tab-color .van-tabs__line {
    background-color: #8FC52F !important;
  }

  .bg-color-w {
    background-color: #fff !important;
  }

  .bg-color-t {
    background-color: transparent !important;
  }

  .bg-color-p {
    background-color: #F87C32 !important;
  }

  .b-color {
    border-color: #8FC52F !important;
  }

  .b-color-p {
    border-color: #F87C32 !important;
  }

  .t-color-p {
    color: #FB5129 !important;
  }

  .b-radius {
    border-radius: 50% !important;
  }

  .b-radius-30 {
    border-radius: 30px !important;
  }

  .b-radius-2 {
    border-radius: 2px !important;
  }

  .b-radius-5 {
    border-radius: 5px !important;
  }

  .f18-size {
    font-size: 18px !important;
  }

  .f16-size {
    font-size: 16px !important;
  }

  .f13-size {
    font-size: 13px !important;
  }

  .f12-size {
    font-size: 12px !important;
  }

  .f10-size {
    font-size: 10px !important;
  }

  .f-w-500 {
    font-weight: 500 !important;
  }

  .f-w-b {
    font-weight: bold !important;
  }

  .wrap-page img {
    max-width: 100%;
  }
</style>
