<template>
  <div class="app-container flex">
    <!-- <el-alert class="warn-content" title="温馨提示" type="success" show-icon :closable="false">
      <p>运费模板不会自动与商品关联，您可以在发布或者修改商品信息时选择运费模板</p>
    </el-alert>
    <br /> -->

    <el-tabs v-model="activeName" tab-position="left" class="flex f-s-0 f-c" @tab-click="handleClickTab">
      <el-tab-pane label="基础信息" name="jichu"></el-tab-pane>
      <el-tab-pane label="秒杀拼团" name="miaosha"></el-tab-pane>
      <el-tab-pane label="销售信息" name="xiaoshou"></el-tab-pane>
      <el-tab-pane label="图文描述" name="tuwen"></el-tab-pane>
      <el-tab-pane label="支付信息" name="zhifu"></el-tab-pane>
      <el-tab-pane label="物流信息" name="wuliu"></el-tab-pane>
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
        <el-form-item prop="shopCateId" label="店铺类目" :rules="[{ required: true, message: '请选择', trigger: 'change' }]">
          <el-cascader
              :props="props"
              style="width: 60%;"
              v-model="postForm.shopCateId"
              placeholder="试试搜索"
              :options="shopCateOptions"
              filterable></el-cascader>
          <el-button @click="$router.push('/cate/cateList')"size="mini" type="danger">新建分类</el-button>
        </el-form-item>
      </el-card>
      <br>
      <el-card id="miaosha" class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <span>秒杀拼团</span>
        </div>
        <el-form-item label="商品类型" prop="type" :rules="[{ required: true, message: '请选择', trigger: 'change' }]">
            <el-radio-group v-model="postForm.type" size="small" :disabled="postForm.id != null">
              <el-radio :label="1" border>普通商品</el-radio>
              <el-radio :label="2" border>秒杀商品</el-radio>
              <el-radio :label="3" border>拼团商品</el-radio>
            </el-radio-group>
        </el-form-item>
        <!--秒杀商品-->
        <div v-if="postForm.type == 2 ">
          <el-form-item  prop="seckillDateRange" label="秒杀时间" :rules="[{ required: true, message: '请选择', trigger: 'blur' }]" >
            <el-date-picker
              style="width: 60%;"
              v-model="postForm.seckillDateRange"
              type="datetimerange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="秒杀限购" prop="seckillLimitNum" :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
            <el-input v-model.number="postForm.seckillLimitNum"style="width: 60%;" placeholder="请输入秒杀限购数量" />
          </el-form-item>
          </div>
          <!--拼团商品-->
          <div v-if="postForm.type == 3 ">
            <el-form-item label="拼团类型" prop="groupType" :rules="[{ required: true, message: '请选择', trigger: 'change' }]">
                <el-radio-group v-model="postForm.groupType" size="small">
                  <el-radio :label="1" border>普通拼团</el-radio>
                  <el-radio :label="2" border>阶梯拼团</el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item v-if="postForm.groupType == 2" label="* 阶梯配置" prop="groupConfig" >
                <span style="font-size: 13px;font-weight: 550;color: #18bc9c;">成团人数</span>
                <span style="font-size: 13px;font-weight: 550;margin-left: 100px; color: #18bc9c;">优惠阶梯%</span>
                <div v-for="(item,index)  in groupList" style="margin-top: 3px;margin-left: 70px;">
                    <el-input v-model.number="item.num" placeholder="请输入成团人数" style="width: 150px;"></el-input>
                    <el-input v-model="item.discounts" placeholder="请输入优惠阶梯" style="width: 150px;"></el-input>
                    <el-button v-if="index != 0" type="danger"  size="mini"  @click="deleteGroup(index)" icon="el-icon-delete"></el-button>
                </div>

                <el-form-item style="margin-left: 70px;">
                  <el-button-group>
                    <el-button type="success"  size="mini"  @click="addGroup" >追加</el-button>
                  </el-button-group>
                </el-form-item>

            </el-form-item>
            <el-form-item v-if="postForm.groupType == 1" label="成团人数" prop="groupNum" :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
              <el-input v-model.number="postForm.groupNum"style="width: 60%;" placeholder="请输入组团成团人数" />
            </el-form-item>
            <el-form-item label="组团限时" prop="groupLimitHours" :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
              <el-input v-model.number="postForm.groupLimitHours"style="width: 60%;" placeholder="请输入组团限时(小时)" />
              <span class="tips">单位:小时</span>
            </el-form-item>
            <el-form-item label="个人限购" prop="groupLimitBuy" :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
              <el-input v-model.number="postForm.groupLimitBuy"style="width: 60%;" placeholder="请输入个人购买限制" />
              <span class="tips">单位:次数</span>
            </el-form-item>
            <el-form-item label="是否单买" prop="groupSingleBuy" :rules="[{ required: true, message: '请选择', trigger: 'change' }]">
                <el-radio-group v-model="postForm.groupSingleBuy" size="small">
                  <el-radio :label="1" border>开启单购</el-radio>
                  <el-radio :label="2" border>关闭单购</el-radio>
                </el-radio-group>
            </el-form-item>
        </div>
      </el-card>
      <br>
      <el-card id="xiaoshou" class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <span>销售信息</span>
        </div>
        <el-form-item  label="产品属性">
          <el-input v-model="attrKey" placeholder="多个产品属性以空格隔开" style="width: 300px;" />
          <el-button type="warning" class="button-new-tag" size="mini" @click="addAttrKey" icon="el-icon-circle-plus">新增属性</el-button>

          <div class="b-color-d margin-t12" style="margin-left: 64px;width: 60%;"  v-for="(item, index) in attrKeyList">
            <div class="flex f-a-c f-j-s  h-40 bg-color-5 padding-lr12">
              <p class="flex f-a-c" style="font-weight: bold;">{{item.attrKey}}</p>
              <i class="el-icon el-icon-error" @click="handleCloseKey(index)"></i>
            </div>
            <div class="flex padding-12">
              <div class="flex f-a-s f-s-0 margin-r20">
                <div class="flex">
                  <el-input v-model="item.attrValue" placeholder="多规格以空格隔开" class="w-200">
                  </el-input>
                  &nbsp;
                  <el-button type="info" size="mini" @click="addAttrValue(item)" icon="el-icon-plus"></el-button>
                </div>

              </div>
              <div class="flex f-w">
                <el-tag
                size="large"
                class="margin-t2 margin-r10"
                  :key="child"
                  v-for="(itemValue, child) in item.attrValueList"
                  closable
                  :disable-transitions="false"
                  @close="handleCloseValue(item, itemValue,index)">
                  {{itemValue}}
                </el-tag>
              </div>
            </div>
          </div>

          			<el-table
                  v-if="attrKeyList && attrKeyList.length > 0"
          			  :data="skuList"
          			  border
                  :header-cell-style="{background:'#d9edf7',color:'#3498db', font:'18px'}"
          			  style="width: 60%;margin-left: 64px;margin-top: 10px;"
          			>
          			  <el-table-column v-for="(attr, index) in attrKeyList" :key="attr.keyId" :label="attr.attrKey">
          					<template slot-scope="scope">
          						{{ scope.row[index] }}
          			    </template>
          			  </el-table-column>
          				<el-table-column label="原价"  width="80" align="center">
          				  <template slot-scope="scope">
          				    <el-input class="edit-input" size="mini" v-model="scope.row[attrLength]"/>
          				  </template>
          				</el-table-column>
                  <el-table-column label="现价"  width="80" align="center">
                    <template slot-scope="scope">
                      <el-input class="edit-input" size="mini" v-model="scope.row[attrLength+1]"/>
                    </template>
                  </el-table-column>
                  <el-table-column label="库存" width="80"  align="center">
                    <template slot-scope="scope">
                      <el-input class="edit-input" size="mini" v-model.number="scope.row[attrLength+2]"/>
                    </template>
                  </el-table-column>
          				<el-table-column label="重量(kg)" width="80"  align="center">
          				  <template slot-scope="scope">
          				    <el-input  class="edit-input" size="mini" v-model="scope.row[attrLength+3]"/>
          				  </template>
          				</el-table-column>
          				<el-table-column label="编码" width="80"  align="center">
          				  <template slot-scope="scope">
          				    <el-input class="edit-input" size="mini" v-model="scope.row[attrLength+4]"/>
          				  </template>
          				</el-table-column>
          				<el-table-column label="图片" width="80" align="center">
          				  <template slot-scope="scope">
                      <SingleUpload class="flex f-a-c f-j-c" style="height:50px;width:50px;" v-model="scope.row[attrLength+5]"/>
          				  </template>
          				</el-table-column>
          			</el-table>

                <div v-if="attrKeyList && attrKeyList.length > 0" style="margin-left: 65px;font-size: 13px;">
                  <a v-if="!batchSet" class="link-type" @click="batchSet = true">是否批量设置?</a>
                  <a v-else class="link-type" @click="batchSet = false">取消批量设置?</a>
                  <br>
                    <div v-if="batchSet">
                      <el-input placeholder="批量设置原价" v-model="temp.oriPrice" style="width: 30%;">
                          <template slot="prepend">原价</template>
                         <el-button slot="append" @click="batchSetParams(temp.oriPrice, attrLength)">批量设置</el-button>
                      </el-input>
                      <br>
                      <el-input placeholder="批量设置现价" v-model="temp.price" style="width: 30%;margin-top: 10px;">
                          <template slot="prepend">现价</template>
                         <el-button slot="append" @click="batchSetParams(temp.price, attrLength+1)">批量设置</el-button>
                      </el-input>
                      <br>
                      <el-input placeholder="批量设置库存" v-model="temp.stock" style="width: 30%;margin-top: 10px;">
                          <template slot="prepend">库存</template>
                         <el-button slot="append" @click="batchSetParams(temp.stock, attrLength+2)">批量设置</el-button>
                      </el-input>
                      <br>
                      <el-input placeholder="批量设置重量" v-model="temp.weight" style="width: 30%;margin-top: 10px;">
                          <template slot="prepend">重量</template>
                         <el-button slot="append" @click="batchSetParams(temp.weight, attrLength+3)">批量设置</el-button>
                      </el-input>
                      <br>
                      <el-input placeholder="批量设置编码" v-model="temp.skuNo" style="width: 30%;margin-top: 10px;">
                          <template slot="prepend">编码</template>
                         <el-button slot="append" @click="batchSetParams(temp.skuNo, attrLength+4)">批量设置</el-button>
                      </el-input>
                    </div>
                </div>

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
        <el-form-item label="商品视频" prop="mainVideo" >
            <SingleVideoUpload v-model="postForm.mainVideo" />
        </el-form-item>
        <el-form-item prop="description" label="商品描述" :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
          <el-input type="textarea" v-model="postForm.description" placeholder="" style="width: 60%;" />
        </el-form-item>
        <el-form-item prop="detail" label="商品详情" :rules="[{ required: true, message: '必填', trigger: 'blur' }]">
          <Tinymce ref="editor" v-model="postForm.detail" :height="400" style="margin-left: 65px;width: 60%;"/>
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
      <el-card id="wuliu" class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <span>物流信息</span>
        </div>
        <el-form-item prop="freightTemplateId" label="运费模板" :rules="[{ required: true, message: '请选择', trigger: 'change' }]">
          <el-select v-model="postForm.freightTemplateId" filterable clearable placeholder="请选择" style="width: 60%;">
              <el-option
                v-for="item in freightOptions"
                :key="item.id"
                :label="item.name"
                :value="item.id">
              </el-option>
            </el-select>
            <el-button @click="$router.push('/freight/list')"size="mini" type="danger">新建模板</el-button>
        </el-form-item>
      </el-card>
      <br>
      <el-card id="shouhou" class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <span>售后信息</span>
        </div>
        <el-form-item label="上架状态" prop="status" :rules="[{ required: true, message: '请选择', trigger: 'change' }]">
            <el-radio-group v-model="postForm.status" size="small">
              <el-radio :label="1" border>出售中宝贝</el-radio>
              <el-radio :label="2" border>仓库中宝贝</el-radio>
            </el-radio-group>
        </el-form-item>
      </el-card>
      <br>
      <el-form-item>
        <el-button v-loading="loading" type="primary" @click="submitForm">
          保存
        </el-button>
      </el-form-item>
    </el-form>
    </div>
  </div>
</template>

<script>
  import Vue from 'vue'
  import MDinput from '@/components/MDinput'
  import {
    getShopCateList,
    getShopFreightList
  } from '@/api/shop'
  import {
    getGoodsCateList, getGoods, updateGoods
  } from '@/api/goods'
  import {
    renderTime
  } from '@/utils'
  import Upload from '@/components/Upload/singleImage'
  import ImageSource from '@/components/Upload/imageSource'
  import MultipleUpload from '@/components/Upload/multipleImage'
  import Tinymce from '@/components/Tinymce'
  import SingleVideoUpload from '@/components/Upload/singleVideo'
  import SingleUpload from '@/components/Upload/singleUpload'

  const defaultForm = {
    type: 1,
    status: 1,
    stockType: 1,
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
      SingleUpload
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
        attrLength: 0
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
        getShopCateList().then(response => {
          this.shopCateOptions = response.data
        }).catch(err => {
          console.log(err)
        })

        getShopFreightList().then(response => {
          this.freightOptions = response.data.list
        }).catch(err => {
          console.log(err)
        })

        getGoodsCateList().then(response => {
          this.goodsCateOptions = response.data
        }).catch(err => {
          console.log(err)
        })
        this.groupList.push({num: null, discounts: null})
      },
      getGoods(id) {
        getGoods({
          goodsId: id
        }).then(response => {
          let goods = response.data.goods
          this.groupList = response.data.goodsGroupList
          if(goods.type == 2){
            goods.seckillDateRange = [goods.seckillBeginTime, goods.seckillEndTime]
          }
          if(goods.cateIds){
            goods.goodsCateId = goods.cateIds.split(",").map(i => parseInt(i, 0))
          }
          if(goods.shopCateIds){
            goods.shopCateId = goods.shopCateIds.split(",").map(i => parseInt(i, 0))
          }
          this.postForm = goods;
          this.attrKeyList = JSON.parse(response.data.attrKeyList);
          this.attrKeyList.forEach(item =>{
            item.attrValueList = JSON.parse(item.attrValueList);
          })
          this.skuList = JSON.parse(response.data.skuList);
          this.attrLength = this.attrKeyList.length
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
            if (formData.type == 3 && this.groupList && this.groupList.length > 0) {
              formData.groupJson = JSON.stringify(this.groupList);
            }
            if(formData.seckillDateRange){
            	formData.seckillBeginTime = renderTime(formData.seckillDateRange[0])
            	formData.seckillEndTime = renderTime(formData.seckillDateRange[1])
            }
            if(formData.goodsCateId){
              formData.goodsCateIds = formData.goodsCateId.join(",")
              formData.goodsCateId = null
            }
            formData.shopCateIds = formData.shopCateId
            if(formData.shopCateId && formData.shopCateId.length > 0){
              formData.shopCateIds = formData.shopCateId.join(",")
              formData.shopCateId = null
            }
            //添加SKU
            //['黑', '大', '22', '33', '111', '1', '12', __ob__: Observer]
            /* console.log(this.skuList);
            let attrLength = this.attrLength;
            let tempList = Object.assign([], this.skuIdList);
            this.skuList.forEach(function(item, index){
              let temp = tempList[index];
              temp = [].concat(temp).concat(item[attrLength] ? item[attrLength] : 'NULL');
              temp = [].concat(temp).concat(item[attrLength+1] ? item[attrLength+1] : 'NULL');
              temp = [].concat(temp).concat(item[attrLength+2] ? item[attrLength+2] : 'NULL');
              temp = [].concat(temp).concat(item[attrLength+3] ? item[attrLength+3] : 'NULL');
              temp = [].concat(temp).concat(item[attrLength+4] ? item[attrLength+4] : 'NULL');
              Vue.set(tempList, index, temp);
            })
            let skuId = tempList;
            console.log(this.skuIdList); */
            this.loading = true
            formData.attrKeyList = JSON.stringify(this.attrKeyList)
            formData.skuList = JSON.stringify(this.skuList)
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
              this.$router.push('/goods/list')
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
      addGroup(){
        this.groupList.push({num: null, discounts: null})
      },
      deleteGroup(index){
        this.groupList.splice(index,1)
      },
      handleClickTab(tab, event) {
      	this.activeName = tab.name
        this.scrollTo(this.activeName);
      },
      addAttrKey(){
        if(!this.attrKey){
          this.$message.error('产品属性不能为空');
        }
        this.attrKey.split(" ").forEach((item)=>{
          if(item != '') {
            this.attrKeyList.push({attrKey:item});
          }
        })
        this.attrKey = '';
      },
      addAttrValue(item){
        if(!item.attrValue){
          this.$message.error('产品属性不能为空');
        }
        if(!item.attrValueList){
          item.attrValueList = []
        }
        item.attrValue.split(" ").forEach((result)=>{
          if(result != '') {
            item.attrValueList.push(result);
          }
        })
        item.attrValue = '';
        this.createSkuList()
      },
      handleCloseKey(index) {
        this.attrKeyList.splice(index, 1);
        this.createSkuList()
      },
      handleCloseValue(item, value,index) {
        item.attrValueList.splice(item.attrValueList.indexOf(value), 1);
        this.$set(this.attrKeyList, index, item);
        this.createSkuList()
      },
      createSkuList(){
      	let list = [];
      	this.attrKeyList.forEach(function(item){
      		let attrVal = [];
          if(item.attrValueList){
            item.attrValueList.forEach(function(val){
              attrVal.push(val);
            })
          }
      		if(attrVal && attrVal.length > 0){
      			list.push(attrVal)
      		}
      	})
        console.log("list", list);
      	if(list.length > 1 || list.length == this.attrKeyList.length){
      		this.skuList = this.descartes(list);
      		let tempList = this.skuList;
      		tempList.forEach(function(item, index){
      			if(!Array.isArray(item)){
      				item = [].concat(item);
      				Vue.set(tempList, index, item)
      			}
            item.push('')
            item.push('')
            item.push('')
            item.push('')
            item.push('')
            item.push('')
      		})
      		this.skuList = tempList;
      		this.attrLength = this.attrKeyList.length
          console.log("attrLength", this.attrLength);
      	}
      	console.log("skuList", this.skuList)
      },
      descartes(array){
      	if( array.length < 2 )
      		return array[0] || [];
      	return [].reduce.call(array, function(col, set) {
      		var res = [];
      		col.forEach(function(c) {
      			set.forEach(function(s) {
      				var t = [].concat( Array.isArray(c) ? c : [c] );
      				t.push(s);
      				res.push(t);
      				})
      			});
      		return res;
      		});
      	},
      batchSetParams(val, index){
        this.skuList.forEach(item=>{
          Vue.set(item, index, val);
        })
        // this.$set(this.skuList, index, val);
      },
      selectChange(val){
        this.$forceUpdate();
      }

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
