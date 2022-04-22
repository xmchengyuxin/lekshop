<template>
  <div class="createPost-container" style="position:relative;left:-100px;width: 1000px;margin: 20px auto;padding: 30px 100px 50px 70px; border: 1px solid #f4f4f4;">
		<code>
      图片存储、视频存储均使用七牛云,详情请查阅<a href="https://developer.qiniu.com/kodo" target="_blank">《七牛云官方文档》</a>
		</code>
<el-form ref="form" :rules="rules" :model="config" v-loading="loading" label-width="120px" label-position="right">
  <el-form-item label="accessKey" prop="accessKey">
    <el-input type="textarea" v-model="config.accessKey"></el-input>
  </el-form-item>
	<el-form-item label="secretKey" prop="secretKey">
	  <el-input type="textarea" v-model="config.secretKey"></el-input>
	</el-form-item>
  <el-form-item label="图片存储区域" prop="imgZone">
    <el-select v-model="config.imgZone" placeholder="请选择图片存储区域">
      <el-option label="华东" value="z0"></el-option>
      <el-option label="华北" value="z1"></el-option>
			<el-option label="华南" value="z2"></el-option>
			<el-option label="北美" value="na0"></el-option>
			<el-option label="东南亚" value="as0"></el-option>
    </el-select>
  </el-form-item>
  <el-form-item label="图片空间名称" prop="imgBucket">
    <el-input v-model="config.imgBucket"></el-input>
  </el-form-item>
	<el-form-item label="图片下载域名" prop="imgViewUrl">
	  <el-input v-model="config.imgViewUrl"></el-input>
	</el-form-item>
  <el-form-item label="视频存储区域" prop="videoZone">
    <el-select v-model="config.videoZone" placeholder="请选择视频存储区域">
      <el-option label="华东" value="z0"></el-option>
      <el-option label="华北" value="z1"></el-option>
  		<el-option label="华南" value="z2"></el-option>
  		<el-option label="北美" value="na0"></el-option>
  		<el-option label="东南亚" value="as0"></el-option>
    </el-select>
  </el-form-item>
  <el-form-item label="视频空间名称" prop="videoBucket">
    <el-input v-model="config.videoBucket"></el-input>
  </el-form-item>
  <el-form-item label="视频下载域名" prop="videoViewUrl">
    <el-input v-model="config.videoViewUrl"></el-input>
  </el-form-item>
  <!-- <el-form-item label="读取方式" prop="isPublic">
    <el-radio-group v-model="config.isPublic">
      <el-radio :label="'1'" >私密读取</el-radio>
      <el-radio :label="'0'" >公共读取</el-radio>
    </el-radio-group>
  </el-form-item> -->
  <el-form-item>
    <el-button type="primary" @click="updateConfig">更新配置</el-button>
  </el-form-item>
</el-form>
</div>
</template>

<style scoped="scoped">
  ::v-deep .line {
    text-align: center;
  }
</style>
<script>
import { getQiniuConfig, updateQiniuConfig } from '@/api/third'

export default {
  name: 'qiniuConfig',
  data() {
    return {
			config:{},
			loading : true,
      rules: {
        accessKey: [{ required: true, message: '请填写accessKey', trigger: 'blur' }],
        secretKey: [{ required: true, message: '请填写secretKey', trigger: 'blur' }],
        imgZone: [{ required: true, message: '请填写图片存储区域', trigger: 'change' }],
				imgBucket: [{ required: true, message: '请填写图片空间名称', trigger: 'blur' }],
				imgViewUrl: [{ required: true, message: '请填写图片下载域名', trigger: 'blur' }],
        videoZone: [{ required: true, message: '请填写视频存储区域', trigger: 'change' }],
        videoBucket: [{ required: true, message: '请填写视频空间名称', trigger: 'blur' }],
        videoViewUrl: [{ required: true, message: '请填写视频下载域名', trigger: 'blur' }],
      }
    }
  },
  created() {
    this.get()
  },
  methods: {
    get() {
      getQiniuConfig().then(response => {
        this.config = response.data
				this.loading = false
      })
    },
    updateConfig() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
					var formData = {
						id: this.config.id,
						accessKey: this.config.accessKey,
						secretKey: this.config.secretKey,
						imgZone: this.config.imgZone,
						imgBucket: this.config.imgBucket,
						imgViewUrl: this.config.imgViewUrl,
            videoZone: this.config.videoZone,
            videoBucket: this.config.videoBucket,
            videoViewUrl: this.config.videoViewUrl,
					}
          updateQiniuConfig(formData).then(() => {
            this.$notify({
              title: '成功',
              message: '更改配置成功',
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
