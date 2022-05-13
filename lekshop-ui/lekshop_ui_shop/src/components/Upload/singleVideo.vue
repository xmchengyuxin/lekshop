<template>
  <div class="upload-container">
    <el-upload
      :data="dataObj"
      :multiple="false"
      :show-file-list="false"
      :on-success="handleImageSuccess"
      class="image-uploader"
      drag
			accept=".mp4,.avi,.rm,.rmvb,.mov,.mtv,.dat,.flv,.ogv,.mp3"
      :action="uploadUrl"
    >
      <i class="el-icon-upload" />
      <div class="el-upload__text">将视频拖到此处，或<em>点击上传</em></div>
    </el-upload>
    <div class="image-preview"  v-show="imageUrl && imageUrl !='NVL' && imageUrl.length>1">
      <div class="image-preview-wrapper">
					<video id="video" height="200" controls="controls" :src="imageUrl">
					</video>
        <!-- <div class="image-preview-action">
          <i class="el-icon-delete" @click="rmImage" />
        </div> -->
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: 'SingleVideoUpload',
  props: {
    value: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      tempUrl: '',
			uploadUrl:process.env.VUE_APP_BASE_API+"common/uploadVideo",
      dataObj: { token: '', key: '' }
    }
  },
  computed: {
    imageUrl() {
      return this.value
    }
  },
  methods: {
    rmImage() {
      this.emitInput('')
    },
    emitInput(val) {
      this.$emit('input', val)
    },
    handleImageSuccess(result) {
      this.emitInput(result.data.videoUrl)
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
    @import "src/styles/mixin.scss";
    .upload-container {
        width: 100%;
        position: relative;
        @include clearfix;
        .image-uploader {
            width: 60%;
            float: left;
        }
        .image-preview {
            // width: 200px;
            height: 200px;
            position: relative;
            border: 1px dashed #d9d9d9;
            float: left;
            margin-left: 50px;
						display: flex;
						justify-content: center;
						align-items: center;
						overflow: hidden;
            .image-preview-wrapper {
                position: relative;
                width: 100%;
                // height: 100%;
                img {
                    width: 100%;
                    height: 100%;
                }
            }
            .image-preview-action {
                position: absolute;
                width: 100%;
                height: 100%;
                left: 0;
                top: 0;
                cursor: default;
                text-align: center;
                color: #fff;
                opacity: 0;
                font-size: 20px;
                background-color: rgba(0, 0, 0, .5);
                transition: opacity .3s;
                cursor: pointer;
                text-align: center;
                line-height: 200px;
                .el-icon-delete {
                    font-size: 36px;
                }
            }
            &:hover {
                .image-preview-action {
                    opacity: 1;
                }
            }
        }
    }

</style>
