<template>
  <div class="createPost-container" style="position:relative;left:-100px;width: 1000px;margin: 20px auto;padding: 30px 100px 50px 70px; border: 1px solid #f4f4f4;">
  <el-alert
      title="修改个人信息"
      type="info"
      show-icon
      :closable="false"
      style="height: 60px;">
    </el-alert>
    <br>
<el-form ref="form" :rules="rules" :model="admin" v-loading="loading" label-width="100px" label-position="right">
    <el-form-item label="头像" prop="headImg" style="min-width: 800px;">
      <Upload v-model="admin.headImg" />
    </el-form-item>
  	<el-form-item label="账号" prop="code">
      <el-input v-model="admin.code" placeholder="请输入账号" disabled />
    </el-form-item>
  	<el-form-item label="名称" prop="name">
  	  <el-input v-model="admin.name" placeholder="请输入名称" />
  	</el-form-item>
  	<el-form-item label="密码" prop="password">
  	  <el-input v-model="admin.password" placeholder="请输入密码" type="password"/>
  	</el-form-item>
  	<el-form-item label="状态" prop="status">
  	  <el-select disabled v-model="admin.status" class="filter-item" placeholder="请选择">
  	    <el-option v-for="item in statusOptions" :key="item.key" :label="item.text" :value="item.key" />
  	  </el-select>
  	</el-form-item>
  	<el-form-item label="角色" prop="typeName">
  	    <el-input v-model="admin.typeName" disabled/>
  	</el-form-item>
  <el-form-item>
    <el-button type="primary" @click="updateAdmin">保存</el-button>
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
import { updateAdmin, getAdmin } from '@/api/admin'
import Upload from '@/components/Upload/singleImage'

const statusOptions = [
  { key: '1', text: '正常' },
  { key: '-1', text: '锁定' }
]
export default {
  name: 'adminCenter',
  components: {Upload },
  data() {
    return {
			admin:{},
			loading : true,
      rules: {
        name: [{ required: true, message: '名称不能为空', trigger: 'blur' }],
      },
      statusOptions
    }
  },
  created() {
    this.get()
  },
  methods: {
    get() {
      getAdmin().then(response => {
        this.admin = response.data
				this.loading = false
      })
    },
    updateAdmin() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          let formData =Object.assign({}, this.admin)
          formData.addTime = null
          formData.updTime = null
          updateAdmin(formData).then(() => {
            this.$notify({
              title: '成功',
              message: '个人信息修改成功',
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
