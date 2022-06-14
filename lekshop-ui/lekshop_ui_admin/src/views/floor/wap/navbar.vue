<template>
  <!-- 预览保存 -->
  <div class="model-title">
    <div>商城装修</div>
    <div class="btns">
      <el-button @click="clickBtn(item)" size="small" v-for="(item, index) in way" :key="index"
        :type="item.selected ? 'primary' : ''">
        {{ item.title }}
      </el-button>
    </div>
    <div class="model-title-view-btn">
      <!-- TODO 后期会补全 目前版本暂无 -->
      <!-- <Poptip placement="bottom" width="100">
        <Button size="default" @click="creatQrCode">预览模板</Button>
        <div slot="content" class="default-view-content">
          <div>临时预览</div>
          <div ref="qrCodeUrl"></div>
        </div>
      </Poptip> -->
      <el-button size="mini" type="danger" @click="handleSpinShow">保存模板</el-button>

      <el-dialog :visible.sync="saveDialog" title="保存中" :closable="true" :mask-closable="false" :footer-hide="true"
        width="20%" center>
        <el-form label-position="right" label-width="80px">
          <div v-if="progress">
            <el-form-item label="模板名称">
              <el-input v-model="submitWay.name"></el-input>
            </el-form-item>
            <el-form-item label="立即发布">
              <el-switch v-model="submitWay.pageShow" active-value="1" inactive-value="0">
              </el-switch>
            </el-form-item>

          <el-form-item label="">
            <el-button size="mini" type="primary" @click="save()">保存</el-button>
          </el-form-item>
          </div>
          <el-form-item label="" v-else>
            <Progress  :percent="num" status="active" />
          </el-form-item>
      </el-form>
    </el-dialog>
  </div>
  </div>
</template>
<script>
  import {
    getFloorDataList,
    updateFloorData
  } from '@/api/floor'

  export default {
    data() {
      return {
        progress: true, // 展示进度
        num: 20, // 提交进度
        saveDialog: false, // 加载状态
        way: [
          // 装修tab栏切换
          {
            title: "首页",
            name: "index",
            selected: true,
          },
          // {
          //   title: "全屏广告",
          //   name: "advertising",
          //   selected: false,
          // },
          // {
          //   title: "弹窗广告",
          //   name: "alertAdvertising",
          //   selected: false,
          // },
        ],

        submitWay: {
          // 表单信息
          pageShow: this.$route.query.type || 0,
          name: this.$route.query.name || "模板名称",
          pageClientType: "H5",
        },
      };
    },
    watch: {},
    mounted() {},
    methods: {
      clickBtn(val) {
        this.way.forEach((item, index) => {
          item.selected = false;
        });
        val.selected = true;
        this.$emit("selected", val.name);
      },

      /**
       * 加载，并保存模板
       */
      handleSpinShow() {
        this.saveDialog = true;
      },

      // 填写是否发布，模板名称之后保存
      save() {
        if (this.$store.state.styleStore == void 0) {
          this.$message.error("请装修楼层");
          return false;
        }
        this.submitWay.pageData = JSON.stringify(this.$store.state.styleStore);
        this.submitWay.pageType = "INDEX";
        this.update();
      },

      // 更新
      update() {
        this.progress = false;
        updateFloorData({
            templateId: this.$route.query.id,
            pageData: JSON.stringify(this.$store.state.styleStore.list),
            name: this.submitWay.name,
            status: this.submitWay.pageShow,
            type: this.$route.query.type,
            oriData: JSON.stringify(this.$store.state.styleStore)
          })
          .then((res) => {
            this.num = 50;
            if (res.code == 200) {
              this.num = 80;
              /**制作保存成功动画¸ */
              setTimeout(() => {
                this.saveDialog = false;
                this.$message.success("修改成功");
                this.goback();
              }, 1000);
            } else {
              this.saveDialog = false;
              this.$message.error("修改失败，请稍后重试");
            }
            console.log(res);
          })
          .catch((error) => {});
      },

      // 返回查询数据页面
      goback() {
        // 调用全局挂载的方法
        this.$store.dispatch('tagsView/delView', this.$route)
        this.$router.push({
          path: "/floor/mobile",
        });
      },
    },
  };
</script>
<style scoped lang="scss">
  .model-item {
    width: 100%;
    padding: 10px 0;
    display: flex;
    align-items: center;

    >* {
      margin: 0 8px;
    }
  }

  .model-title {
    height: 70px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    color: #333;
    font-size: 14px;

    >.model-title-view-btn {
      >* {
        margin: 0 10px;
      }
    }
  }

  .btns {
    * {
      margin: 0 4px;
    }
  }
</style>
