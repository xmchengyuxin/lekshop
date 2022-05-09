<template>
  <div class="createPost-container">
    <el-form ref="postForm" :model="postForm" :rules="rules" class="form-container">
      <sticky :z-index="10" :class-name="'sub-navbar '+postForm.status">
        <!-- <CommentDropdown v-model="postForm.comment_disabled" />
        <PlatformDropdown v-model="postForm.platforms" />
        <SourceUrlDropdown v-model="postForm.source_uri" /> -->
        <el-button v-loading="loading" style="margin-left: 10px;" type="success" @click="submitForm">
          发布
        </el-button>
        <el-button v-loading="loading" type="warning" @click="draftForm">
          草稿
        </el-button>
      </sticky>

      <div class="createPost-main-container">
        <el-row>
          <Warning />

          <el-col :span="24">
            <el-form-item style="margin-bottom: 40px;" prop="title">
              <MDinput v-model="postForm.title" :maxlength="100" name="name" required>
                标题
              </MDinput>
            </el-form-item>

            <div class="postInfo-container">
              <el-row>
                <el-col :span="8">
                  <el-form-item label="类型" prop="type" class="postInfo-container-item">
                    <el-select v-model="postForm.type" placeholder="类型" filterable clearable>
                      <el-option v-for="item in typeOptions" :key="item.key" :label="item.text" :value="item.key">
                      </el-option>
                    </el-select>
                   </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="8">
                  <el-form-item label="分类" prop="cateId" class="postInfo-container-item">
                    <el-select v-model="postForm.cateId" placeholder="分类" filterable clearable>
                      <el-option v-for="item in cateOptions" :key="item.id" :label="item.name" :value="item.id">
                      </el-option>
                    </el-select>
                   </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="6">
                  <el-form-item label="排序" class="postInfo-container-item">
                    <el-input-number style="width:200px;" v-model="postForm.sort" :min="1" label="修改排序"></el-input-number>
                  </el-form-item>
                </el-col>
              </el-row>
            </div>
          </el-col>
        </el-row>

        <el-form-item style="margin-bottom: 40px;" label-width="45px" label="摘要:">
          <el-input v-model="postForm.produce" :rows="1" type="textarea" class="article-textarea" autosize placeholder="请输入内容" />
          <span v-show="contentShortLength" class="word-counter">{{ contentShortLength }}字</span>
        </el-form-item>

				<el-form-item prop="image" style="margin-bottom: 30px;">
          <Upload v-model="postForm.image" />
          <ImageSource :mul="false" v-model="postForm.image" />
        </el-form-item>

        <el-form-item prop="content" style="margin-bottom: 30px;">
          <Tinymce ref="editor" v-model="postForm.content" :height="400" />
        </el-form-item>

      </div>
    </el-form>
  </div>
</template>

<script>
import Tinymce from '@/components/Tinymce'
import Upload from '@/components/Upload/singleImage'
import MDinput from '@/components/MDinput'
import Sticky from '@/components/Sticky' // 粘性header组件
import { validURL } from '@/utils/validate'
import { updateArticle, getArticle, getArticleCateSelector } from '@/api/article'
import Warning from './Warning'
import { renderTime } from '@/utils'
import { CommentDropdown, PlatformDropdown, SourceUrlDropdown } from './Dropdown'
import ImageSource from '@/components/Upload/imageSource'

const typeOptions = [
  { key: 1, text: '买家' },
  { key: 2, text: '商家' },
]

const defaultForm = {
  status: 'draft',
  title: '', // 文章题目
  content: '', // 文章内容
  produce: '', // 文章摘要
  image: '', // 文章图片
  addTime: undefined, // 前台展示时间
  id: undefined,
  comment_disabled: false,
  importance: 0
}

export default {
  name: 'ArticleDetail',
  components: { Tinymce, MDinput, Upload, Sticky, Warning, CommentDropdown, PlatformDropdown, SourceUrlDropdown, ImageSource },
  props: {
    isEdit: {
      type: Boolean,
      default: false
    }
  },
  data() {
    const validateRequire = (rule, value, callback) => {
      if (value === '') {
        this.$message({
          message: rule.field + '为必传项',
          type: 'error'
        })
        callback(new Error(rule.field + '为必传项'))
      } else {
        callback()
      }
    }
    return {
      postForm: Object.assign({}, defaultForm),
      loading: false,
      rules: {
        title: [{ validator: validateRequire }],
        content: [{ validator: validateRequire }],
        brandId: [{ validator: validateRequire }],
        cateId: [{ validator: validateRequire }],
        type:  [{ validator: validateRequire }],
      },
      tempRoute: {},
      cateOptions:[],
      typeOptions
    }
  },
  computed: {
    contentShortLength() {
      return this.postForm.produce.length
    },
    lang() {
      return this.$store.getters.language
    }
  },
  created() {
    if (this.isEdit) {
      const id = this.$route.params && this.$route.params.id
      this.getArticle(id)
    } else {
      this.postForm = Object.assign({}, defaultForm)
    }
     this.getArticleCateSelector()
    // Why need to make a copy of this.$route here?
    // Because if you enter this page and quickly switch tag, may be in the execution of the setTagsViewTitle function, this.$route is no longer pointing to the current page
    // https://github.com/PanJiaChen/vue-element-admin/issues/1221
    this.tempRoute = Object.assign({}, this.$route)
  },
  methods: {
    getArticleCateSelector(){
      getArticleCateSelector().then(response => {
        this.cateOptions = response.data
      })
    },
    getArticle(id) {
      getArticle(id).then(response => {
        this.postForm = response.data
        // Set tagsview title
        // this.setTagsViewTitle()
      }).catch(err => {
        console.log(err)
      })
    },
//     setTagsViewTitle() {
//       const title = this.lang === 'zh' ? '编辑文章' : 'Edit Article'
//       const route = Object.assign({}, this.tempRoute, { title: `${title}-${this.postForm.id}` })
//       this.$store.dispatch('tagsView/updateVisitedView', route)
//     },
    submitForm() {
      this.$refs.postForm.validate(valid => {
        if (valid) {
          this.loading = true
				 let formData =Object.assign({}, this.postForm)
				 formData.addTime = renderTime(this.postForm.addTime)
				 formData.updTime = null
				 formData.status = '1'
					updateArticle(formData).then(() => {
					  this.$notify({
					    title: '成功',
					    message: '发布文章成功',
					    type: 'success',
					    duration: 2000
					  })
						this.postForm.status = 'published'
						this.loading = false
					})
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    draftForm() {
      if (this.postForm.content.length === 0 || this.postForm.title.length === 0) {
        this.$message({
          message: '请填写必要的标题和内容',
          type: 'warning'
        })
        return
      }
			let formData =Object.assign({}, this.postForm)
			formData.status = '0'
			formData.addTime = renderTime(this.postForm.addTime)
			formData.updTime = null
			updateArticle(formData).then(() => {
			  this.$notify({
			    title: '成功',
			    message: '保存草稿成功',
			    type: 'success',
			    duration: 2000
			  })
				this.postForm.status = 'draft'
				this.loading = false
			})
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
</style>
