<template>
    <el-upload
      :data="dataObj"
      :multiple="false"
      :show-file-list="false"
      :on-success="handleImageSuccess"
      class="avatar-uploader"
      :action="uploadUrl"
    >
        <el-image
            v-if="imageUrl && imageUrl !='NVL' && imageUrl.length>1"
            :src="imageUrl"
            fit="contain"
            class="avatar"></el-image>
        <!-- <img v-if="imageUrl && imageUrl !='NVL' && imageUrl.length>1" :src="imageUrl" class="avatar"> -->
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
    </el-upload>
    <!-- <div class="image-preview">
      <div v-show="imageUrl && imageUrl !='NVL' && imageUrl.length>1" class="image-preview-wrapper">
        <img :src="imageUrl">
        <div class="image-preview-action">
          <i class="el-icon-delete" @click="rmImage" />
        </div>
      </div>
    </div> -->
</template>

<script>

export default {
  name: 'SingleImageUpload',
  props: {
    value: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      tempUrl: '',
			uploadUrl:process.env.VUE_APP_BASE_API+"/system/upload",
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
      this.emitInput(result.data.imgUrl)
    }
  }
}
</script>
<style scoped="scoped">

.avatar-uploader ::v-deep .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader ::v-deep .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 100px;
    height: 100px;
    line-height: 100px;
    text-align: center;
  }
  .avatar {
    width: 100px;
    height: 100px;
    display: block;
    object-fit: contain;
  }

</style>
