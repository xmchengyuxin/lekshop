<template>
	<div class="app-container">
    <el-alert
        class="warn-content"
        title="温馨提示"
        type="success"
        show-icon
        :closable="false">
           <p>运费模板不会自动与商品关联，您可以在发布或者修改商品信息时选择运费模板</p>
      </el-alert>
      <br/>

		<el-form ref="postForm" :model="postForm" :rules="rules" label-position="right">
      <el-form-item style="margin-bottom: 40px;" prop="name">
      	<MDinput v-model="postForm.name" :maxlength="100" name="name" required>
      		模板名称
      	</MDinput>
      </el-form-item>
      <el-form-item label="发货地址" prop="pca" >
        <el-cascader
          v-model="postForm.pca"
          size="small"
          style="width:60%;"
          :options="addressOptions"
          clearable
        />
      </el-form-item>
      <el-form-item prop="deliveryTime" label="发货时间">
      	<el-select v-model="postForm.deliveryTime" placeholder="发货时间" clearable style="width: 40%;">
      		<el-option label="8小时内" value="8小时内"></el-option>
      		<el-option label="12小时内" value="12小时内"></el-option>
      		<el-option label="16小时内" value="16小时内"></el-option>
      		<el-option label="20小时内" value="20小时内"></el-option>
      		<el-option label="1天内" value="1天内"></el-option>
      		<el-option label="2天内" value="2天内"></el-option>
      		<el-option label="3天内" value="3天内"></el-option>
      		<el-option label="5天内" value="5天内"></el-option>
      		<el-option label="7天内" value="7天内"></el-option>
      		<el-option label="8天内" value="8天内"></el-option>
      		<el-option label="10天内" value="10天内"></el-option>
      	</el-select>
      	<span class="tips">&nbsp;如实设定宝贝的发货时间，不仅可避免发货咨询和纠纷，还能促进成交！</span>
      </el-form-item>
      <el-form-item prop="type" label="模板类型">
      	<el-radio-group v-model="postForm.type" size="mini">
      		<el-radio :label="1" border>买家承担运费</el-radio>
      		<el-radio :label="2" border>卖家包邮</el-radio>
      	</el-radio-group>
      </el-form-item>
      <template v-if="postForm.type == 1">
        <el-form-item prop="valuteType" label="计价方式" size="mini">
          <el-radio-group v-model="postForm.valuteType">
            <el-radio :label="1" border>按件数</el-radio>
            <el-radio :label="2" border>按重量</el-radio>
            <el-radio :label="3" border>按体积</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item prop="defaultFirstNum" label="默认运费" >
          <el-input v-model.number="postForm.defaultFirstNum" placeholder="" style="width: 200px;"/>
          <span class="tips">件内，</span>
          <el-input v-model.number="postForm.defaultFirstPrice" placeholder="" style="width: 200px;"/>
          <span class="tips">元， 每增加</span>
          <el-input v-model.number="postForm.defaultSecondNum" placeholder="" style="width: 200px;"/>
          <span class="tips">件， 增加运费</span>
          <el-input v-model.number="postForm.defaultSecondPrice" placeholder="" style="width: 200px;"/>
          <span class="tips">元</span>
        </el-form-item>
        <el-form-item prop="defaultFirstPrice" style="margin: 0;">
        </el-form-item>
        <el-form-item prop="defaultSecondNum" style="margin: 0;">
        </el-form-item>
        <el-form-item prop="defaultSecondPrice" style="margin: 0;">
        </el-form-item>

        <el-table :data="templateAreaList" border fit highlight-current-row style="width: 100%;">
          <el-table-column label="运送到" min-width="300px">
            <template slot-scope="scope">
              <el-cascader
                size="mini"
                style="width:400px;"
                :options="tempAddressOptions"
                v-model="scope.row.address"
                clearable
                >
              </el-cascader>
              <el-button size="mini" type="danger" icon="el-icon-delete" @click="handleDelete(scope.$index)">删除</el-button>
            </template>
          </el-table-column>
          <el-table-column label="首件数" width="150px" align="center">
            <template slot-scope="scope">
              <el-input class="edit-input" size="mini" v-model.number="scope.row.firstNum" />
            </template>
          </el-table-column>
          <el-table-column label="运费(元)" width="150px" align="center">
            <template slot-scope="scope">
              <el-input class="edit-input" size="mini" v-model.number="scope.row.firstPrice" />
            </template>
          </el-table-column>
          <el-table-column label="续件数" width="150px" align="center">
            <template slot-scope="scope">
              <el-input class="edit-input" size="mini" v-model.number="scope.row.secondNum" />
            </template>
          </el-table-column>
          <el-table-column label="续费(元)" width="150px" align="center">
            <template slot-scope="scope">
              <el-input class="edit-input" size="mini" v-model.number="scope.row.secondPrice" />
            </template>
          </el-table-column>
        </el-table>
        <el-form-item>
          <span class="link-type" @click="handleCreateTemplateArea" style="font-size: 13px;">为指定地区城市设置运费</span>
        </el-form-item>
      </template>
      <el-form-item>
        <el-button v-loading="loading"  type="primary" @click="submitForm">
          保存
        </el-button>
      </el-form-item>
		</el-form>
	</div>
</template>

<script>
	import Vue from 'vue'
	import MDinput from '@/components/MDinput'
	import Sticky from '@/components/Sticky' // 粘性header组件
	import {
		provinceAndCityDataPlus, regionData, CodeToText
	} from 'element-china-area-data'
	import {
		getFreight,
		updateFreight
	} from '@/api/shop'
	import {
		renderTime
	} from '@/utils'

	const defaultForm = {
    type: 1,
		valuteType: 1,
		name: '',
		defaultFirstNum: '',
		defaultFirstPrice: '',
		defaultSecondNum: '',
		defaultSecondPrice: '',
		address: []
	}

	export default {
		name: 'FreightDetail',
		components: {
			MDinput,
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
				addressOptions: regionData,
				tempAddressOptions: provinceAndCityDataPlus,
				templateAreaList:[],
				tempArea:{},
				postForm: Object.assign({}, defaultForm),
				loading: false,
				rules: {
					name: [{
						required: true,
						message: '请输入模板名称',
						trigger: 'blur'
					}],
          pca: [{ required: true, message: '请选择发货地', trigger: 'blur' }],
					deliveryTime: [{
						required: true,
						message: '请选择发货时间',
						trigger: 'change'
					}],
          type: [{
          	required: true,
          	message: '请选择模板类型',
          	trigger: 'change'
          }],
					valuteType: [{
						required: true,
						message: '请输入计价方式',
						trigger: 'change'
					}],
					defaultFirstNum: [{
							required: true,
							message: '请完整填写默认运费',
							trigger: 'blur'
						},
						{
							type: 'number',
							min: 0,
							message: '请输入大于0的数字'
						}
					],
					defaultFirstPrice: [{
							required: true,
							message: '请完整填写默认运费',
							trigger: 'blur'
						},
						{
							type: 'number',
							message: '请输入大于0的数字'
						}
					],
					defaultSecondNum: [{
							required: true,
							message: '请完整填写默认运费',
							trigger: 'blur'
						},
						{
							type: 'number',
							min: 0,
							message: '请输入大于0的数字'
						}
					],
					defaultSecondPrice: [{
							required: true,
							message: '请完整填写默认运费',
							trigger: 'blur'
						},
						{
							type: 'number',
							min: 0,
							message: '请输入大于0的数字'
						}
					]
				},
				tempRoute: {}
			}
		},
		created() {
			if (this.isEdit) {
				const id = this.$route.params && this.$route.params.id
				this.getFreight(id)
			} else {
				this.postForm = Object.assign({}, defaultForm)
			}
			// Why need to make a copy of this.$route here?
			// Because if you enter this page and quickly switch tag, may be in the execution of the setTagsViewTitle function, this.$route is no longer pointing to the current page
			// https://github.com/PanJiaChen/vue-element-admin/issues/1221
			this.tempRoute = Object.assign({}, this.$route)
		},
		methods: {
			getFreight(id) {
				getFreight({id:id}).then(response => {
					this.postForm = response.data.template
					this.postForm.pca = [this.postForm.provinceCode, this.postForm.cityCode, this.postForm.areaCode]
					this.templateAreaList = response.data.areaList
					this.templateAreaList.forEach(function(item){
						item.address = [item.provinceCode, item.cityCode]
					})
				}).catch(err => {
					console.log(err)
				})
			},
			submitForm() {
				this.$refs.postForm.validate(valid => {
					if (valid) {
						this.loading = true
						let formData = Object.assign({}, this.postForm)
						if (this.postForm.pca && this.postForm.pca.length > 0) {
						  formData.provinceCode = this.postForm.pca[0]
						  formData.cityCode = this.postForm.pca[1]
						  formData.areaCode = this.postForm.pca[2]
						  formData.province = CodeToText[formData.provinceCode]
						  formData.city = CodeToText[formData.cityCode]
						  formData.area = CodeToText[formData.areaCode]
						}
						formData.addTime = null
						formData.updTime = null
						if(this.templateAreaList && this.templateAreaList.length > 0){
							this.templateAreaList.forEach(function(item){
								item.country = 'china';
								item.provinceCode = item.address[0];
								item.cityCode = item.address[1];
                item.province = CodeToText[item.provinceCode]
                item.city = CodeToText[item.cityCode]
							})
							formData.areaJson = JSON.stringify(this.templateAreaList);
						}
						updateFreight(formData).then((response) => {
							this.postForm.id = response.data
							this.$notify({
								title: '成功',
								message: '运费模板保存成功',
								type: 'success',
								duration: 2000
							})
							this.loading = false
              // 调用全局挂载的方法
              this.$store.dispatch('tagsView/delView', this.$route)
              // 返回上一步路由
              this.$router.push('/freight/list')
						})
					} else {
						console.log('error submit!!')
						return false
					}
				})
			},
			handleCreateTemplateArea(){
				this.tempArea = {
					address : [],
					firstNum : undefined,
					firstPrice: undefined,
					secondNum: undefined,
					secondPrice: undefined,
					country: 'china',
					province: '',
					city: ''
				}
				this.templateAreaList.push(this.tempArea)
			},
			handleDelete(index){
				this.templateAreaList.splice(index, 1);
			}

		}
	}
</script>

<style lang="scss" scoped>
	@import "~@/styles/mixin.scss";

	.createPost-container {
		position: relative;

		.createPost-main-container {
			padding: 40px 45px 20px 50px;

			.postInfo-container {
				position: relative;
				@include clearfix;
				margin-bottom: 10px;

				.postInfo-container-item {
					float: left;
				}
			}
		}

		.word-counter {
			width: 40px;
			position: absolute;
			right: -10px;
			top: 0px;
		}
	}

  .el-radio{
    margin-right: 0px !important;
  }
</style>
