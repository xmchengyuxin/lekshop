<template>
  <el-card shadow="hover" style="margin: 10px;">
  <el-tabs v-model="activeName" @tab-click="handleClickTab">
  	  <el-tab-pane label="京东万象" name="jdwx"></el-tab-pane>
  		<el-tab-pane label="淘发客" name="taofake"></el-tab-pane>
  </el-tabs>
        <el-alert
            class="warn-content"
            title="温馨提示"
            type="success"
            show-icon
            :closable="false">
               本接口使用{{configName}}, 详情请查阅<a :href="docUrl" target="_blank">《相关文档》</a>
          </el-alert>
          <br/>
      <el-form ref="form" :rules="rules" :model="params" v-loading="loading" label-width="120px" label-position="right" style="width: 60%;">
        <el-form-item label="appId" prop="appId">
          <el-input v-model="params.appId"></el-input>
        </el-form-item>
        <el-form-item label="密钥" prop="secret" v-if="activeName == 'taofake' ">
          <el-input type="password" v-model="params.secret"></el-input>
        </el-form-item>
        <el-form-item label="请求链接" prop="apiUrl">
          <el-input v-model="params.apiUrl"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="updateConfig">更新配置</el-button>
        </el-form-item>
      </el-form>
  </el-card>
</template>

<style scoped="scoped">
  ::v-deep .line {
    text-align: center;
  }
</style>
<script>
import { getInterfaceConfig, updateInterfaceConfig } from '@/api/third'

const configOptions = [
  { key: 'jdwx', value: '京东万象', docUrl: 'https://wx.jdcloud.com/' },
  { key: 'taofake', value: '淘发客', docUrl: 'https://www.taofake.com/apistore/'  },
]

export default {
  name: 'interface',
  data() {
    return {
			config:{},
      params: {},
			loading : true,
      rules: {
        appId: [{ required: true, message: '请填写appId', trigger: 'blur' }],
        secret: [{ required: true, message: '请填写密钥', trigger: 'blur' }],
        apiUrl: [{ required: true, message: '请填写请求链接', trigger: 'blur' }],
      },
      activeName:'jdwx',
      configName:'',
      docUrl:''
    }
  },
  created() {
    this.get()
  },
  methods: {
    getOption(key){
      var option;
      configOptions.forEach((item) => {
          if(item.key == key){
            option = item;
          }
      })
      return option;
    },
    get() {
      getInterfaceConfig({nid:this.activeName}).then(response => {
        this.config = response.data
        if(!this.config){
          this.config = {}
          this.params = {}
        }else{
          this.params = JSON.parse(this.config.content)
        }
        var configOption = this.getOption(this.activeName);
        this.configName = configOption.value;
        this.docUrl = configOption.docUrl;
    		this.loading = false
      })
    },
    updateConfig() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
    			var formData = {
            id: this.config.id,
            nid: this.activeName,
            content: JSON.stringify(this.params)
          }
          updateInterfaceConfig(formData).then(() => {
            this.$notify({
              title: '成功',
              message: '更改配置成功',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    },
    handleClickTab(tab, event) {
    	this.activeName = tab.name
      this.get();
    },

  }
}
</script>
