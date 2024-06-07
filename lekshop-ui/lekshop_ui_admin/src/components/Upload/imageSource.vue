<template>
  <div class="upload-container">
  <el-button @click="chooseImage" style="width: 100px;" size="mini">素材库选择>></el-button>

<el-dialog title="选择图片" :visible.sync="dialogFormVisible" append-to-body>

  <div class="filter-container">
    <el-input v-model="cate" clearable placeholder="分组名称" style="width: 200px;" class="filter-item" />
    <el-button-group>
      <el-button @click="addSourceCate" class="filter-item" type="primary" size="mini" icon="el-icon-edit">
        添加分组
      </el-button>
    </el-button-group>
  </div>

    <el-tabs closable type="border-card" tab-position="top" v-model="activeName" @tab-click="handleClickTab" @tab-remove="removeTab">
      <el-tab-pane v-for="item in cateOptions" :label="item.name" :name="item.id + ''">

        <div class="flex f-w">
          <MultipleSource @successCBK="imageSuccessCBK" />
        <div  class="image-preview flex f-s-0"
                v-if="list"
                v-for="(item,index) in list">
          <div class="image-preview-wrapper">
            <el-image
                fit="scale-down"
                style="width: 148px; height:148px; margin: 10px; border: 1px dashed #d9d9d9;"
                :src="item.source"
                :key="item.id"
               >
              </el-image>
            <div class="image-preview-action flex f-j-c f-a-c" :class="item.ischoose ? 'on' : ''" @click="changeImage(index)">
              <i v-if="item.ischoose" class="el-icon-success"/>
              <!-- <el-tooltip content="删除" placement="top">
                <i class="el-icon-delete" style="margin-right: 10px;" @click="deleteSource(item.id)"/>
              </el-tooltip>
              <el-tooltip content="移动分组" placement="top">
                <i class="el-icon-s-unfold" @click="moveCate(item)"/>
              </el-tooltip> -->
            </div>
          </div>
        </div>
        </div>

        <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize"
          @pagination="getList" />

        <div style="text-align:right;">
          <el-button type="danger" @click="dialogFormVisible=false">取消</el-button>
          <el-button type="primary" @click="confirm">确定</el-button>
        </div>

      </el-tab-pane>
    </el-tabs>

</el-dialog>
</div>

</template>
<script>

import {
    getSourceCateList,
    updateSourceCate,
    deleteSourceCate,
    getSourceList,
    updateSource,
    deleteSource,
    moveCate
  } from '@/api/source'
  import MultipleSource from '@/components/Upload/multipleSource'
  import Pagination from '@/components/Pagination' // Secondary package based on el-pagination


export default {
  name: 'ImageSource',
  components: {
    Pagination, MultipleSource
  },
  props: {
    mul: {
      type: Boolean,
      default: false
    },
    value:{
      type: String,
      default: ''
    }
  },
  data() {
    return {
      tableKey: 0,
      list: null,
      total: 0,
      listLoading: true,
      cateId: null,
      cate: null,
      cateOptions: [],
      activeName: null,
      listQuery: {
        page: 1,
        pageSize: 10
      },
      dialogFormVisible: false,
      checkedList:[],
      checkedUrlList:[]

    }
  },
  methods: {
    changeImage(index) {
      let data = this.list[index];
      if(data.ischoose){
        data.ischoose = false;
      }else{
        if(this.mul){
          data.ischoose = true;
        }else{
          this.list.forEach((ele,index) => {
            ele.ischoose = false;
          })
          data.ischoose = true;
        }
      }
      if(this.mul){
        if(data.ischoose){
          this.checkedList.push(data.id)
          this.checkedUrlList.push(data.source)
        }else{
             this.checkedList.splice(this.checkedList.indexOf(data.id),1)
             this.checkedUrlList.splice(this.checkedUrlList.indexOf(data.source),1)
        }
      }else{
        this.checkedList = []
        this.checkedList.push(data.id)
        this.checkedUrlList.push(data.source)
      }
      this.$set(this.list,index,data);
    },
    confirm() {
      if(!this.checkedList || this.checkedList.length <= 0){
        this.$message({
          message: '请至少选择一张图片',
          type: 'error',
        	duration: 2000
        })
        return;
      }
      let image = this.arraysToString();
      if(this.mul && this.value){
        image = this.value + "|" + image;
      }
      this.$emit('input', image)
      this.dialogFormVisible = false
    },
    arraysToString(){
    	if(this.checkedUrlList && this.checkedUrlList.length > 0){
    		let temp = ''
    		this.checkedUrlList.forEach(function(item){
    			temp = temp + "|" + item
    		})
    		return temp.substring(1)
    	}
    },
    created() {
      this.getSourceCateList()
    },
    getList() {
      this.listLoading = true
      this.listQuery.cateId = this.cateId
      getSourceList(this.listQuery).then(response => {
        this.list = response.data.list
        if(this.list && this.list.length > 0 && this.checkedList && this.checkedList.length > 0){
          this.list.forEach((ele,index) => {
            if(this.checkedList.indexOf(ele.id) >= 0){
              ele.ischoose = true;
            }
          })
        }
        this.total = response.data.total
        this.listLoading = false
      })
    },
    addSourceCate() {
      if(this.cate == null || this.cate == '') {
        this.$message({
          message: "请填写分组名称",
          type: 'error'
        })
        return;
      }
      updateSourceCate({
        name: this.cate
      }).then(response => {
        this.$message({
          message: "添加成功",
          type: 'success'
        })

        this.getSourceCateList()
      })
    },
    getSourceCateList() {
      getSourceCateList().then(response => {
        this.cateOptions = response.data
        if (!this.cateOptions || this.cateOptions.length == 0) return;
        this.cateId = this.cateOptions[0].id
        this.activeName = this.cateId + ""
        this.getList()
      })
    },
    removeTab(targetName) {
      this.$confirm('删除分组,分组内的图片将被全部清除,您确定要删除吗?', '提醒', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(async () => {
          deleteSourceCate(targetName).then(() => {
            this.getSourceCateList()
            this.$notify({
              title: '成功',
              message: '删除成功',
              type: 'success',
              duration: 2000
            })
          })
        })
        .catch(err => {
          console.error(err)
        })
    },
    handleClickTab(tab, event) {
      this.cateId = tab.name
      this.getList()
    },
    imageSuccessCBK(arr) {
      const _this = this
      let sources = []
      arr.forEach(v => {
        sources.push(v.url)
      })
    
        updateSource({
          sources: sources.join("|"),
          cateId: this.cateId,
          type: '1'
        }).then(response => {
          this.$message({
            message: "添加成功",
            type: 'success'
          })
    
          this.getList()
        })
    
    },
    chooseImage(){
      this.dialogFormVisible = true
      this.checkedList = []
      this.checkedUrlList = []
      this.getSourceCateList()
    }
  }
}
</script>
<style scoped="scoped">
 .image-preview-wrapper {
    position: relative;
  }
  .image-preview{

  }
  .image-preview-action {
    position: absolute;
    width: 148px;
    height: 148px;
    left: 0;
    top: 0;
    right: 0;
    bottom: 0;
    margin: auto;
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
    z-index: 111;

  }
  .el-icon-delete {
    font-size: 20px;
  }
  .image-preview-action.on {
    opacity: 1;
  }
  .image-preview-action:hover {
      opacity: 1;
  }
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
    width: 148px;
    height: 148px;
    line-height: 148px;
    text-align: center;
  }
  .avatar {
    width: 148px;
    height: 148px;
    display: block;
    object-fit: contain;
  }

</style>
