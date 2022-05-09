<template>
    <el-upload
      :multiple="false"
      :show-file-list="false"
      :on-success="handleImageSuccess"
      class="avatar-uploader"
      :action="uploadUrl"
    >
		<img v-if="imageUrl" :src="imageUrl" class="avatar">
		<i v-if="imageUrl" class="close el-icon-circle-close" @click.stop="handleClose()"></i>
		<i v-else class="el-icon-plus avatar-uploader-icon"></i>
    </el-upload>
</template>

<script>

export default {
  name: 'SingleUpload',
  props: {
    value: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
			uploadUrl:process.env.VUE_APP_BASE_API+"system/upload"
    }
  },
	computed: {
	  imageUrl() {
	    return this.value
	  }
	},
  methods: {
		emitInput(val) {
		  this.$emit('input', val)
		},
    handleClose() {
			 this.emitInput('')
    },
    handleImageSuccess(result) {
			this.emitInput(result.data.imgUrl)
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
    @import "src/styles/mixin.scss";
   .avatar-uploader .el-upload {
		 width: 100%;
		 height: 100%;
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
	.avatar-uploader {
		width: 178px;
		height: 178px;
		margin: auto;
		position: relative;
	}
	.avatar-uploader .close {
		position: absolute;
		top: 0;
		right: 0;
		font-size: 25px;
	}
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    text-align: center;
  }
  .avatar {
    width: 100%;
    height: 100%;
    display: block;
  }
</style>
