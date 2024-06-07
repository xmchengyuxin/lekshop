<template>
  <div class="createPost-container" style="width: 100%;margin: 20px auto;padding: 30px 30px 30px 30px;">
    <el-alert title="将素材添加进素材管理,可以节省CDN流量资源" type="info" show-icon :closable="false" style="height: 60px;">
    </el-alert>
    <br>

      <div class="filter-container">
        <el-input v-model="cate" clearable placeholder="分组名称" style="width: 200px;" class="filter-item" />
        <el-button-group>
          <el-button @click="addSourceCate" class="filter-item" type="primary" size="mini" icon="el-icon-edit">
            添加分组
          </el-button>
        </el-button-group>
      </div>

    <el-tabs closable type="border-card" tab-position="top" v-model="activeName" @tab-click="handleClickTab"
      @tab-remove="removeTab">
      <el-tab-pane v-for="item in cateOptions" :label="item.name" :name="item.id + ''">

        <div class="flex f-w">
          <MultipleSource @successCBK="imageSuccessCBK" />
        <div  class="image-preview flex f-s-0"
                v-if="list"
                v-for="item in list">
          <div class="image-preview-wrapper">
            <el-image
                fit="scale-down"
                style="width: 148px; height:148px; margin: 10px; border: 1px dashed #d9d9d9;"
                :src="item.source"
                :key="item.id"
               >
              </el-image>
            <div class="image-preview-action flex f-j-c f-a-c">
              <el-tooltip content="删除" placement="top">
                <i class="el-icon-delete" style="margin-right: 10px;" @click="deleteSource(item.id)"/>
              </el-tooltip>
              <el-tooltip content="移动分组" placement="top">
                <i class="el-icon-s-unfold" @click="moveCate(item)"/>
              </el-tooltip>
            </div>
          </div>
        </div>
        </div>



        <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize"
          @pagination="getList" />

      </el-tab-pane>

      <el-dialog title="移动分组" :visible.sync="dialogFormVisible">
          <el-radio-group v-model="moveCateId">
            <el-radio-button v-for="item in cateOptions" :label="item.id">{{item.name}}</el-radio-button>
          </el-radio-group>

          <div slot="footer" class="dialog-footer">
            <el-button  @click="dialogFormVisible = false">{{ $t('table.cancel') }}</el-button>
            <el-button  type="primary" @click="moveCateSubmit">{{ $t('table.confirm') }}</el-button>
          </div>
      </el-dialog>

    </el-tabs>
  </div>
</template>

<style scoped="scoped">
  ::v-deep .line {
    text-align: center;
  }

  .item-target {
    margin-top: 10px;
    margin-right: 40px;
  }
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
  .image-preview-action:hover {
      opacity: 1;
  }

</style>
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
    name: 'sourceTable',
    components: {
      MultipleSource,
      Pagination
    },
    data() {
      return {
        tableKey: 0,
        list: null,
        total: 0,
        listLoading: true,
        cateId: null,
        cate: null,
        type: null,
        cateOptions: [],
        skuMap: {},
        activeName: null,
        title: null,
        id: null,
        listQuery: {
          page: 1,
          pageSize: 50
        },
        sources: '',
        dialogFormVisible:false,
        moveCateId: null,
        moveSource:{}
      }
    },
    created() {
      this.getSourceCateList()
    },
    methods: {
      getList() {
        this.listLoading = true
        this.listQuery.cateId = this.cateId
        getSourceList(this.listQuery).then(response => {
          this.list = response.data.list
          this.total = response.data.total
          this.listLoading = false
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
      handleSelectionChange(val) {
        this.multipleSelection = val;
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
      deleteSource(id){
        deleteSource(id).then(() => {
          this.getList()
          this.$notify({
            title: '成功',
            message: '删除成功',
            type: 'success',
            duration: 2000
          })
        })
      },
      moveCate(item){
        this.dialogFormVisible = true
        this.moveSource = {id:item.id}
      },
      moveCateSubmit(){
        if(!this.moveCateId){
          this.$message({
            message: '请选择要移动的分组',
            type: 'error',
          	duration: 2000
          })
          return;
        }

        this.moveSource.cateId = this.moveCateId
        moveCate(this.moveSource).then(response => {
          this.$message({
            message: "移动分组成功",
            type: 'success'
          })
          this.dialogFormVisible = false
          this.getList()
        })
      }

    }
  }
</script>
