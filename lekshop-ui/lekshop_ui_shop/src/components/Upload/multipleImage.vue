<template>
	<div>
		<el-upload
    :data="dataObj"
    :multiple="true"
    list-type="picture-card"
    :on-preview="handlePictureCardPreview"
		:on-success="handleImageSuccess"
    :on-remove="handleRemove"
    :file-list="fileList"
    class="image-uploader"
    :action="uploadUrl">
			<i class="el-icon-plus" />
		</el-upload>
		<el-dialog :visible.sync="dialogVisible">
			<img width="100%" :src="dialogImageUrl" alt="" >
		</el-dialog>
	</div>
</template>

<script>
	export default {
		name: 'MultipleImageUpload',
		props: {
			value: {
				type: String,
				default: ''
			}
		},
		data() {
			return {
				// fileList: [],
				tempFile:[],
				times: 0,
				tempUrl: '',
				uploadUrl: process.env.VUE_APP_BASE_API + "common/upload",
				dialogImageUrl: '',
				dialogVisible: false,
				dataObj: {
					token: '',
					key: ''
				},
			}
		},
		created() {
			this.initFileList()
		},
		computed: {
			fileList : {
				get: function(){
					return this.initFileList()
				},
				set: function(){
				}
			}
		},
		methods: {
			initFileList(){
				let val = []
				if (this.value) {
					let imgList = this.value.split("|");
					imgList.forEach(function(item, index) {
						let imgForm = {
							name: index,
							url: item
						};
						val.push(imgForm);
					})
				}
				return val
			},
			emitInput(val) {
				this.$emit('input', val)
			},
			handleRemove(file, fileList) {
				let temp = []
				fileList.forEach(function(item){
					temp.push(item)
				})
				// this.fileList = temp

				this.emitInput(this.arraysToString(temp));
			},
			handleImageSuccess(result, file, fileList) {
				   let curTimes = fileList.length
					 let oldTimes = this.fileList.length
					 let temp = this.fileList
					 file.url = result.data.imgUrl
					 temp.push(file)
					if(curTimes == (oldTimes+1)){
						this.emitInput(this.arraysToString(temp));
					}
			},
			arraysToString(fileList){
				if(fileList && fileList.length > 0){
					let temp = ''
					fileList.forEach(function(item){
						temp = temp + "|" + item.url
					})
					return temp.substring(1)
				}
			},
			handlePictureCardPreview(file) {
				this.dialogImageUrl = file.url;
				this.dialogVisible = true;
			}
		}
	}
</script>

<style scoped="scoped">

::v-deep .image-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .image-uploader ::v-deep .el-upload:hover {
    border-color: #409EFF;
  }
  ::v-deep .el-upload--picture-card {
    font-size: 28px;
    background-color: #fff;
    width: 100px;
    height: 100px;
    line-height: 100px;
    text-align: center;
  }
   ::v-deep .el-upload-list--picture-card .el-upload-list__item {
    width: 100px;
    height: 100px;
    object-fit: contain;
  }

</style>
