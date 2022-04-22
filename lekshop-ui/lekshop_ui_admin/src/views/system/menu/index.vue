<template>

  <div class="createPost-container" style="position:relative;left:-100px;width: 1000px;margin: 20px auto;padding: 30px 30px 50px 30px; border: 1px solid #f4f4f4;">

		<el-form ref="postForm" :model="menu" :rules="rules" class="form-container">
				<div class="createPost-main-container">
					<div class="postInfo-container">
						<template>
              <el-alert
                  title="菜单URL必须与前端路由path一致"
                  type="info"
                  show-icon
                  :closable="false"
                  style="height: 60px;">
                </el-alert>
						</template>

					</div>
					<el-row>
						<el-col :span="12">
							<div style="border-right: 1px solid #f4f4f4;margin-right: 20px;">
								<el-tree
									:data="menuList"
									@node-click="handleNodeClick"
									node-key="id"
									:expand-on-click-node="false"
									:props="defaultProps">

									<span class="custom-tree-node" slot-scope="{ node, data }">
											<span>{{ node.label }}</span>
											<span @click.stop>
												<el-button
													type="text"
													size="mini"
													@click="() => append(data)">
													添加
												</el-button>
												<el-button
													type="text"
													size="mini"
													@click="() => remove(node, data)">
													删除
												</el-button>
											</span>
										</span>
								</el-tree>
								<el-button style="margin-top:20px;" type="primary" size="mini" @click="handleCreate()">添加顶级菜单</el-button>
							</div>
						</el-col>
						<el-col :span="10">
							<el-form-item label="上级菜单:">
							<el-input style="width:300px" class="filter-item" v-model="menu.topName" disabled placeholder="直接添加顶级菜单"/>
							</el-form-item>
							<el-form-item prop="name" label="菜单名称:">
							<el-input style="width:300px" class="filter-item" v-model="menu.name" placeholder="请输入菜单名称" />
							</el-form-item>
							<el-form-item prop="url" label="菜单路径:">
							<el-input style="width:300px" class="filter-item" v-model="menu.url" placeholder="请输入菜单路径" />
							</el-form-item>
							<el-form-item prop="sort" label="菜单排序:" class="postInfo-container-item">
								<el-input-number style="width:300px;" v-model="menu.sort" :min="1" label="修改排序"></el-input-number>
							</el-form-item>
							<el-button type="primary" size="mini" @click="handleSave()">保存</el-button>
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
    font-size: 14px;
    padding-right: 8px;
  }
</style>

<script>
import {getMenuList, getMenu, saveMenu, deleteMenu} from '@/api/menu'

export default {
  name: 'menuDetail',
  data() {
    return {
			postForm:{},
			menuList: [],
			defaultProps: {
				children: 'children',
				label: 'name'
			},
			menu:{
				topName:'直接添加顶级菜单'
			},
			rules: {
			  name: [{ required: true, message: '菜单名称不能为空', trigger: 'blur' }],
			  url: [{ required: true, message: '菜单url不能为空', trigger: 'blur' }],
				sort: [{ required: true, message: '菜单排序不能为空', trigger: 'blur' }]
			},

     }

  },
  created() {
		this.getMenuList()
  },
  methods: {
		getMenuList(){
			getMenuList().then((result) => {
				this.menuList = result.data;
			})
		},
		handleNodeClick(data) {
			getMenu(data.id).then((result) => {
				this.menu = result.data.menu;
        this.menu.topName = result.data.topName;
			})
		} ,
		handleCreate() {
			this.menu = {
					topName:'直接添加顶级菜单'
			}
		} ,
	 handleSave(){
		 this.$refs['postForm'].validate((valid) => {
		   if (valid) {
		     let formData = Object.assign({}, this.menu)
		     formData.addTime = null
		     formData.updTime = null
		     saveMenu(formData).then((result) => {
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
			 pid: data.id,
			 topName: data.name
		 }
		},
		remove(node, data) {
			this.$confirm('您确定要删除这些记录?', '提醒', {
			  confirmButtonText: '确定',
			  cancelButtonText: '取消',
			  type: 'warning'
			}).then(async() => {
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
			  .catch(err => { console.error(err) })

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
