<template>
  <div style="padding: 10px 10px 10px 10px;">
  <el-form ref="dataForm" :model="config" label-width="140px" label-position="right">
    <el-divider>用户端</el-divider>
    <el-descriptions class="margin-top" :column="1" size="small" border>
       <el-descriptions-item>
         <template slot="label">
          安卓强制更新
         </template>
          <el-switch
            active-value="1" inactive-value="0"
            v-model="config.android_update_status">
          </el-switch>
       </el-descriptions-item>

       <el-descriptions-item>
         <template slot="label">
          安卓版本号
         </template>
           <el-input v-model="config.android_version"></el-input>
       </el-descriptions-item>

       <el-descriptions-item>
         <template slot="label">
          安卓下载链接
         </template>
           <el-input v-model="config.android_app_download_url"></el-input>
       </el-descriptions-item>

       <el-descriptions-item>
         <template slot="label">
          安卓更新内容
         </template>
           <el-input type="textarea" v-model="config.android_update_content"></el-input>
       </el-descriptions-item>

        <template slot="label">
          IOS强制更新
         </template>
          <el-switch
            active-value="1" inactive-value="0"
            v-model="config.ios_update_status">
          </el-switch>
       </el-descriptions-item>

       <el-descriptions-item>
         <template slot="label">
          IOS版本号
         </template>
           <el-input v-model="config.ios_version"></el-input>
       </el-descriptions-item>

       <el-descriptions-item>
         <template slot="label">
          IOS下载链接
         </template>
           <el-input v-model="config.ios_app_download_url"></el-input>
       </el-descriptions-item>

       <el-descriptions-item>
         <template slot="label">
          IOS更新内容
         </template>
           <el-input type="textarea" v-model="config.ios_update_content"></el-input>
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
      uploadUrl:process.env.VUE_APP_BASE_API+"/system/uploadSyjapk"
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
