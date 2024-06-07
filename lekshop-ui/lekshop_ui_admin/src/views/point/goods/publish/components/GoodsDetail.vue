<template>
  <div class="app-container flex">
    <!-- <el-alert class="warn-content" title="温馨提示" type="success" show-icon :closable="false">
      <p>运费模板不会自动与商品关联，您可以在发布或者修改商品信息时选择运费模板</p>
    </el-alert>
    <br /> -->

    <el-tabs v-model="activeName" tab-position="left" class="flex f-s-0 f-c" @tab-click="handleClickTab">
      <el-tab-pane label="基础信息" name="jichu"></el-tab-pane>
      <el-tab-pane label="图文描述" name="tuwen"></el-tab-pane>
      <el-tab-pane label="支付信息" name="zhifu"></el-tab-pane>
      <el-tab-pane label="售后信息" name="shouhou"></el-tab-pane>
    </el-tabs>
    <div id="wrap-form" style="height:84vh;overflow-y: scroll;" class="flex flex-1 f-c">
    <el-form class="flex flex-1 f-c" ref="postForm" :model="postForm"  label-position="right" style="width: 90%;">
      <el-card id="jichu" class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <span>基础信息</span>
        </div>
        <el-form-item style="margin-bottom: 40px;" prop="title" :rules="[{ required: true, message: '请输入宝贝标题', trigger: 'blur' }]">
          <MDinput v-model="postForm.title" :maxlength="100" name="title" required>
            宝贝标题
          </MDinput>
        </el-form-item>
        <el-form-item prop="goodsCateId" label="商品类目" :rules="[{ required: true, message: '请选择', trigger: 'change' }]">
          <el-cascader
              :props="props"
              style="width: 60%;"
              v-model="postForm.goodsCateId"
              placeholder="试试搜索"
              :options="goodsCateOptions"
              filterable></el-cascader>
        </el-form-item>
        <el-form-item prop="goodsCode" label="商品编码" :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
          <el-input v-model="postForm.goodsCode" placeholder="" style="width: 60%;" />
        </el-form-item>
        <el-form-item prop="oriPrice" label="商品价值" :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
          <el-input v-model="postForm.oriPrice" placeholder="" style="width: 60%;" />
        </el-form-item>
        <el-form-item label="兑换方式" prop="exchangeMethod" :rules="[{ required: true, message: '请选择', trigger: 'change' }]">
            <el-radio-group v-model="postForm.exchangeMethod" size="small">
              <el-radio :label="1" border>积分兑换</el-radio>
              <el-radio :label="2" border>积分+现金兑换</el-radio>
            </el-radio-group>
        </el-form-item>
        <el-form-item prop="point" label="所需积分" :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
          <el-input v-model="postForm.point" placeholder="" style="width: 60%;" />
        </el-form-item>
        <el-form-item v-if="postForm.exchangeMethod == 2" prop="price" label="所需现金" :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
          <el-input v-model="postForm.price" placeholder="" style="width: 60%;" />
        </el-form-item>
        <el-form-item prop="limitExchange" label="单笔限购" :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
          <el-input v-model="postForm.limitExchange" placeholder="" style="width: 60%;" />
        </el-form-item>
        <el-form-item prop="stock" label="商品库存" :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
          <el-input v-model="postForm.stock" placeholder="" style="width: 60%;" />
        </el-form-item>
      </el-card>
      <br>
      <el-card id="tuwen" class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <span>图文描述</span>
        </div>
        <el-form-item prop="mainImg" label="宝贝主图" :rules="[{ required: true, message: '请上传宝贝主图', trigger: 'blur' }]">
          <Upload v-model="postForm.mainImg" />
          <ImageSource style="margin-left: 72px;margin-top: -5px;" :mul="false" v-model="postForm.mainImg" />
        </el-form-item>
        <el-form-item prop="goodsImg" label="宝贝相册" :rules="[{ required: true, message: '请上传宝贝相册', trigger: 'blur' }]">
          <MultipleUpload :value="postForm.goodsImg" v-model="postForm.goodsImg" />
          <ImageSource style="margin-left: 72px;margin-top: 5px;" :mul="true" v-model="postForm.goodsImg" />
        </el-form-item>
        <el-form-item label="* 商品视频" prop="mainVideo" >
            <SingleVideoUpload v-model="postForm.mainVideo" />
        </el-form-item>
        <el-form-item prop="description" label="商品描述" :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
          <el-input type="textarea" v-model="postForm.description" placeholder="" style="width: 60%;" />
        </el-form-item>
        <el-form-item prop="detail" label="商品详情" :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
          <Tinymce ref="editor" v-model="postForm.detail" :height="400" style="margin-left: 72px;width: 60%;"/>
        </el-form-item>
      </el-card>
      <br>
      <el-card id="zhifu" class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <span>支付信息</span>
        </div>
        <el-form-item label="库存计算方式" prop="stockType" :rules="[{ required: true, message: '请选择', trigger: 'change' }]">
            <el-radio-group v-model="postForm.stockType" size="small">
              <el-radio :label="1" border>下单减库存</el-radio>
              <el-radio :label="2" border>付款减库存</el-radio>
            </el-radio-group>
        </el-form-item>
      </el-card>
      <br>
      <el-card id="shouhou" class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <span>售后信息</span>
        </div>
        <el-form-item label="上架状态" prop="status" :rules="[{ required: true, message: '请选择', trigger: 'change' }]">
            <el-radio-group v-model="postForm.status" size="small">
              <el-radio :label="1" border>出售中</el-radio>
              <el-radio :label="2" border>仓库中</el-radio>
            </el-radio-group>
        </el-form-item>
      </el-card>
      <br>
      <sticky :z-index="10">
      <el-form-item>
          <el-button v-loading="loading" type="primary" @click="submitForm">
            保存
          </el-button>
      </el-form-item>
      </sticky>
    </el-form>
    </div>
  </div>
</template>

<script>
  import Vue from 'vue'
  import MDinput from '@/components/MDinput'
  import {
    getGoodsCateList, getGoods, updateGoods
  } from '@/api/point'
  import {
    renderTime
  } from '@/utils'
  import Upload from '@/components/Upload/singleImage'
  import ImageSource from '@/components/Upload/imageSource'
  import MultipleUpload from '@/components/Upload/multipleImage'
  import Tinymce from '@/components/Tinymce'
  import SingleVideoUpload from '@/components/Upload/singleVideo'
  import SingleUpload from '@/components/Upload/singleUpload'
  import Sticky from '@/components/Sticky' // 粘性header组件

  const defaultForm = {
    type: 1,
    status: 1,
    stockType: 1,
    exchangeMethod: 1
  }

  export default {
    name: 'GoodsDetail',
    components: {
      MDinput,
      Upload,
      ImageSource,
      MultipleUpload,
      Tinymce,
      SingleVideoUpload,
      SingleUpload,
      Sticky
    },
    props: {
      isEdit: {
        type: Boolean,
        default: false
      }
    },
    data() {
      return {
        props:{ label: 'name', value : 'id', children: 'children'},
        postForm: Object.assign({}, defaultForm),
        loading: false,
        tempRoute: {},
        goodsCateOptions:[],
        shopCateOptions:[],
        freightOptions: [],
        activeName: 'jichu',
        groupList: [],
        singleGroup: {num: null, discounts: null},
        attrKey: '',
        attrKeyList: [],
        skuList:[],
        batchSet: false,
        temp: {},
        attrLength: 0,
        thirdGoodsUrl: '',
        autoGetLoading: false
      }
    },
    created() {
      this.init()
      if (this.isEdit) {
        const id = this.$route.params && this.$route.params.id
        this.getGoods(id)
      } else {
        this.postForm = Object.assign({}, defaultForm)
      }
      // Why need to make a copy of this.$route here?
      // Because if you enter this page and quickly switch tag, may be in the execution of the setTagsViewTitle function, this.$route is no longer pointing to the current page
      // https://github.com/PanJiaChen/vue-element-admin/issues/1221
      this.tempRoute = Object.assign({}, this.$route)
    },
    methods: {
      scrollTo(id) {
         let targetbox = document.getElementById(id);
        let top = targetbox.offsetTop-100;
        document.getElementById('wrap-form').scrollTop = top;
      },
      init(){
        getGoodsCateList().then(response => {
          this.goodsCateOptions = response.data
        }).catch(err => {
          console.log(err)
        })
      },
      getGoods(id) {
        getGoods({
          goodsId: id
        }).then(response => {
          let goods = response.data
          if(goods.cateIds){
            goods.goodsCateId = goods.cateIds.split(",").map(i => parseInt(i, 0))
          }
          this.postForm = goods;
        }).catch(err => {
          console.log(err)
        })
      },
      submitForm() {
        this.$refs.postForm.validate(valid => {
          if (valid) {
            this.loading = true
            let formData = Object.assign({}, this.postForm)
            formData.addTime = null
            formData.updTime = null
            if(formData.goodsCateId){
              formData.goodsCateIds = formData.goodsCateId.join(",")
              formData.goodsCateId = null
            }
            this.loading = true
            updateGoods(formData).then((response) => {
              this.postForm.id = response.data
              this.$notify({
                title: '成功',
                message: '保存成功',
                type: 'success',
                duration: 2000
              })
              this.loading = false
              // 调用全局挂载的方法
              this.$store.dispatch('tagsView/delView', this.$route)
              // 返回上一步路由
              this.$router.push('/point/goods')
            }).catch(err => {
              this.loading = false
              console.log(err)
            })
          } else {
            console.log('error submit!!')
            return false
          }
        })
      },
      handleClickTab(tab, event) {
      	this.activeName = tab.name
        this.scrollTo(this.activeName);
      },
      selectChange(val){
        this.$forceUpdate();
      },

    }
  }
</script>

<style lang="scss" scoped>
  .el-radio {
    margin-right: 0px !important;
  }

  ::v-deep .el-card__header {
    color: #333333;
    background-color: #f5f5f5;
    border-color: #ddd;
  }
  .sku-item {
    position: relative;
  }
  .sku-item i {
    display: none;
    position: absolute;
    top: 0;
    right: 0;
    transform: translateX(50%) translateY(-50%);
  }
  .sku-item::hover {
    display: block;
  }
</style>
