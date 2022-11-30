<template>

  <div class="createPost-container">

    <el-form ref="postForm" :model="menu" :rules="rules" class="form-container" label-position="right" label-width="100px">
      <div class="createPost-main-container">
        <div class="postInfo-container">
          <template>
            <el-alert title="通过菜单权限标识控制菜单权限" type="info" show-icon :closable="false"
              style="height: 60px;">
            </el-alert>
          </template>
        </div>

        <el-row>
          <el-col :span="6">
            <div style="border-right: 1px solid #f4f4f4;margin-right: 20px;  ">
              <div style="padding-right: 8px; text-align: right;">
                <el-button type="text" size="mini"  @click="expandAll(true)">
                  展开
                </el-button>
                <el-button type="text" size="mini" @click="expandAll(false)">
                  折叠
                </el-button>
              </div>
              <el-tree ref="myTree" :data="menuList" @node-click="handleNodeClick" node-key="id" :expand-on-click-node="false"
                :default-expand-all="false" :indent="16" :props="defaultProps" :highlight-current="true">

                <span class="custom-tree-node" slot-scope="{ node, data }">
                  <span>{{ node.label }}</span>
                  <span @click.stop>
                    <el-button type="text" size="mini" @click="() => append(data)">
                      添加
                    </el-button>
                    <el-button type="text" size="mini" @click="() => remove(node, data)">
                      删除
                    </el-button>
                  </span>
                </span>
              </el-tree>
            </div>
          </el-col>
          <el-col :span="10">
            <el-form-item label="上级菜单:">
              <el-input style="width:300px" class="filter-item" v-model="menu.parentName" disabled
                placeholder="直接添加顶级菜单" />
            </el-form-item>
            <el-form-item prop="menuName" label="菜单名称:">
              <el-input style="width:300px" class="filter-item" v-model="menu.menuName" placeholder="请输入菜单名称" />
            </el-form-item>
            <!-- <el-form-item prop="url" label="菜单路径:">
              <el-input style="width:300px" class="filter-item" v-model="menu.url" placeholder="请输入菜单路径" />
            </el-form-item> -->
            <el-form-item prop="menuType" label="菜单类型:">
              <el-radio-group v-model="menu.menuType" size="mini">
                <!-- <el-radio label="M" border>目录</el-radio> -->
                <el-radio label="C" border>菜单</el-radio>
                <el-radio label="F" border>按钮</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item prop="perms" label="权限标识:">
              <el-input style="width:300px" class="filter-item" v-model="menu.perms" placeholder="请输入菜单权限" />
            </el-form-item>
            <el-form-item prop="sort" label="菜单排序:" class="postInfo-container-item">
              <el-input-number style="width:300px;" v-model="menu.sort" :min="1" label="修改排序"></el-input-number>
            </el-form-item>
            <el-form-item label="">
              <el-button type="primary" size="mini" @click="handleCreate()">添加顶级菜单</el-button>
              <el-button type="primary" size="mini" @click="handleSave()">保存</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </div>
    </el-form>
  </div>
</template>

<style>
  .custom-tree-node {
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: space-between;
    font-size: 13px;
    padding-right: 8px;
  }
</style>

<script>
  import {
    getMenuList,
    updateMenu,
    deleteMenu
  } from '@/api/shopMenu'

  export default {
    name: 'shopMenuList',
    data() {
      return {
        postForm: {},
        menuList: [],
        defaultProps: {
          children: 'children',
          label: 'menuName',
          value: 'id',
          checkStrictly: true
        },
        menu: {
          parentName: '直接添加顶级菜单'
        },
        rules: {
          menuName: [{
            required: true,
            message: '菜单名称不能为空',
            trigger: 'blur'
          }],
          url: [{
            required: true,
            message: '菜单url不能为空',
            trigger: 'blur'
          }],
          sort: [{
            required: true,
            message: '菜单排序不能为空',
            trigger: 'blur'
          }],
          menuType: [{
            required: true,
            message: '菜单类型不能为空',
            trigger: 'blur'
          }],
          perms: [{
            required: true,
            message: '权限标识不能为空',
            trigger: 'blur'
          }]
        },

      }

    },
    created() {
      this.getMenuList()
    },
    methods: {
      getMenuList() {
        getMenuList({
        }).then((result) => {
          this.menuList = result.data;
        })
      },
      handleNodeClick(data) {
        this.menu = data;
      },
      handleCreate() {
        this.menu = {
          parentName: '直接添加顶级菜单'
        }
      },
      handleSave() {
        this.$refs['postForm'].validate((valid) => {
          if (valid) {
            let formData = Object.assign({}, this.menu)
            formData.addTime = null
            formData.updTime = null
            updateMenu(formData).then((result) => {
              this.getMenuList()
              this.$notify({
                title: '成功',
                message: '菜单保存成功',
                type: 'success',
                duration: 2000
              })
            })
          }
        })

      },
      append(data) {
        this.menu = {
          parentId: data.id,
          parentName: data.menuName
        }
      },
      remove(node, data) {
        this.$confirm('您确定要删除这些记录?', '提醒', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(async () => {
            deleteMenu(data.id).then(() => {
              this.getMenuList()
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
      // 展开/折叠
      expandAll(isExpand) {
        for (var i = 0; i < this.$refs.myTree.store._getAllNodes().length; i++) {
          // 根据isExpand， tree展开或折叠
          this.$refs.myTree.store._getAllNodes()[i].expanded = isExpand
        }
      },

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
