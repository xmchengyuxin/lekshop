<template>
  <div style="padding: 10px 10px 10px 10px;">
  <el-form ref="dataForm" :model="config" label-width="140px" label-position="right">
     <el-descriptions class="margin-top" :column="1" size="small" border>
        <el-descriptions-item>
          <template slot="label">
           网站名称
          </template>
            <el-input v-model="config.web_name"></el-input>
        </el-descriptions-item>

        <el-descriptions-item>
          <template slot="label">
           网站LOGO
          </template>
          <Upload v-model="config.logo"/>
          <ImageSource :mul="false" v-model="config.logo" />
        </el-descriptions-item>

        <el-descriptions-item>
          <template slot="label">
           默认头像
          </template>
          <Upload v-model="config.default_head_img"/>
          <ImageSource :mul="false" v-model="config.default_head_img" />
        </el-descriptions-item>

        <el-descriptions-item>
          <template slot="label">
           客服电话
          </template>
            <el-input v-model="config.kefu_tel"></el-input>
        </el-descriptions-item>

        <!-- <el-descriptions-item>
          <template slot="label">
           在线客服网址
          </template>
            <el-input v-model="config.kefu_url"></el-input>
        </el-descriptions-item> -->

        <el-descriptions-item>
          <template slot="label">
           主站备案号
          </template>
            <el-input v-model="config.icp_no"></el-input>
        </el-descriptions-item>

        <el-descriptions-item>
          <template slot="label">
           高强度网站维护
          </template>
            <el-switch
              active-value="1" inactive-value="0"
              v-model="config.weihu_no_login">
            </el-switch>
            <span class="tips">*开启后, 会员只能进行登录, 无法进行其他操作</span>
        </el-descriptions-item>

        <el-descriptions-item>
          <template slot="label">
           低强度网站维护
          </template>
            <el-switch
              active-value="1" inactive-value="0"
              v-model="config.weihu_no_take_task">
            </el-switch>
            <span class="tips">*开启后, 会员能进行登录和提现, 无法进行其他操作</span>
        </el-descriptions-item>

        <el-descriptions-item>
          <template slot="label">
           上架审核显示
          </template>
            <el-switch
              active-value="1" inactive-value="0"
              v-model="config.caner_status">
            </el-switch>
        </el-descriptions-item>
        
        <el-descriptions-item>
          <template slot="label">
           逛逛是否开启
          </template>
            <el-switch
              active-value="1" inactive-value="0"
              v-model="config.guangguang_status">
            </el-switch>
        </el-descriptions-item>

        <el-descriptions-item>
          <template slot="label">
           国际语言
          </template>
          <el-select v-model="config.language" placeholder="请选择">
              <el-option
                v-for="item in languageOptions"
                :key="item.key"
                :label="item.text"
                :value="item.key">
              </el-option>
            </el-select>
            <span class="tips">*修改语言设置仅能修改用户小程序，H5，App端的语言，后台和商家后台仅支持中文</span>
        </el-descriptions-item>


        <el-descriptions-item>
          <template slot="label">
           操作
          </template>
            <el-button type="primary" @click="confirm">保存</el-button>
        </el-descriptions-item>
      </el-descriptions>

  </el-form>
  </div>
</template>
<style scoped="scoped">
  ::v-deep .line {
    text-align: center;
  }
</style>

<script>
import {getConfigList, updateConfig} from '@/api/params'
import Upload from '@/components/Upload/singleImage'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime } from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
import ImageSource from '@/components/Upload/imageSource'

const languageOptions = [
  { key: 'all', text: '用户自主选择' },
  { key: 'folowSystem', text: '跟随手机系统' },
  { key: 'zh_CN', text: '简体中文' },
  { key: 'zh_TW', text: '繁体中文' },
  { key: 'en_US', text: '英文' },
]

export default {
  name: 'configTable',
  components: { Pagination, Upload, ImageSource },
  directives: { waves },
  data() {
    return {
      tableKey: 0,
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        pageSize: 20
      },
      config: {},
      dialogFormVisible: false,
      dialogType: '',
			multipleSelection: [],
			dialogImageUrl:'',
			dialogVisible:false,
      uploadUrl:process.env.VUE_APP_BASE_API+"/system/uploadSyjapk",
      languageOptions
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      getConfigList(this.listQuery).then(response => {
        this.config = response.data
				this.listLoading = false
      })
    },
		confirm() {
			this.$refs['dataForm'].validate((valid) => {
			  if (valid) {
			    let formData =Object.assign({}, this.config)
					formData.addTime = null
					formData.updTime = null
			    updateConfig(formData).then(() => {
			      this.$notify({
			        title: '成功',
			        message: '参数保存成功',
			        type: 'success',
			        duration: 2000
			      })
			    })
			  }
			})
		},
    submitUpload() {
      this.$refs.upload.submit();
    },
    uploadSus(response, file, fileList){
        const res = response.data
        if (res.code !== 200) {
          this.$notify({
            title: '失败',
            message: '上传失败',
            type: 'error',
            duration: 2000
          })
        } else {
          this.$notify({
            title: '成功',
            message: '上传成功',
            type: 'success',
            duration: 2000
          })
        }
    },
    uploadFail(response, file, fileList){
        const res = response.data
        if (res.code !== 200) {
          this.$notify({
            title: '失败',
            message: '上传失败',
            type: 'error',
            duration: 2000
          })
        } else {
          this.$notify({
            title: '成功',
            message: '上传成功',
            type: 'success',
            duration: 2000
          })
        }
    }

  }
}
</script>
