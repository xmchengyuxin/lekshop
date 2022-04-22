<template>
  <div class="app-container">
		<el-table
      :key="tableKey"
      v-loading="listLoading"
      :data="list"
      border
      fit
      highlight-current-row
      style="width: 100%;"
    >
			<el-table-column type="selection" width="55" align="center"></el-table-column>
      <el-table-column label="提醒类型" width="150px"  align="center">
        <template slot-scope="scope">
          <el-tag>{{ scope.row.cate | typeFilter}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="模板ID" width="350px"  align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.templateId}}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.actions')" class-name="small-padding" fixed="right" min-width="120">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" icon="el-icon-edit" @click="handleUpdate(scope.row)">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>

		<!--添加编辑框-->
		<el-dialog title="编辑" :visible.sync="dialogFormVisible">
		    <el-form ref="dataForm" :rules="rules" :model="template" label-width="80px" label-position="right" style="width: 400px; margin-left:50px;">
          <el-form-item label="提醒类型" prop="cate">
            <el-select disabled v-model="template.cate" class="filter-item" placeholder="请选择">
              <el-option v-for="item in typeOptions" :key="item.key" :label="item.text" :value="item.key" />
            </el-select>
          </el-form-item>
					<el-form-item label="模板ID" prop="templateId">
						<el-input type="textarea" v-model="template.templateId"></el-input>
		      </el-form-item>

          <!-- 派单成功 -->
          <!-- <div v-if="template.cate == '1' ">
            <el-form-item label="取餐码" prop="key1">
              <el-input v-model="template.key1" placeholder="请输入微信订阅消息的KEY值"></el-input>
            </el-form-item>
            <el-form-item label="订单金额" prop="key2">
              <el-input v-model="template.key2" placeholder="请输入微信订阅消息的KEY值"></el-input>
            </el-form-item>
            <el-form-item label="取餐时间" prop="key3">
              <el-input v-model="template.key3" placeholder="请输入微信订阅消息的KEY值"></el-input>
            </el-form-item>
            <el-form-item label="取餐门店" prop="key4">
              <el-input v-model="template.key4" placeholder="请输入微信订阅消息的KEY值"></el-input>
            </el-form-item>
            <el-form-item label="温馨提示" prop="key5">
              <el-input v-model="template.key5" placeholder="请输入微信订阅消息的KEY值"></el-input>
            </el-form-item>
          </div> -->

          <!-- 取餐完成 -->
          <!-- <div v-if="template.cate == '2' ">
            <el-form-item label="订单号" prop="key1">
              <el-input v-model="template.key1" placeholder="请输入微信订阅消息的KEY值"></el-input>
            </el-form-item>
            <el-form-item label="订单金额" prop="key2">
              <el-input v-model="template.key2" placeholder="请输入微信订阅消息的KEY值"></el-input>
            </el-form-item>
            <el-form-item label="门店名称" prop="key3">
              <el-input v-model="template.key3" placeholder="请输入微信订阅消息的KEY值"></el-input>
            </el-form-item>
            <el-form-item label="温馨提示" prop="key4">
              <el-input v-model="template.key4" placeholder="请输入微信订阅消息的KEY值"></el-input>
            </el-form-item>
          </div> -->

            <!-- 取餐超时 -->
            <!-- <div v-if="template.cate == '3' ">
              <el-form-item label="取餐号码" prop="key1">
                <el-input v-model="template.key1" placeholder="请输入微信订阅消息的KEY值"></el-input>
              </el-form-item>
              <el-form-item label="取餐门店" prop="key2">
                <el-input v-model="template.key2" placeholder="请输入微信订阅消息的KEY值"></el-input>
              </el-form-item>
              <el-form-item label="温馨提示" prop="key3">
                <el-input v-model="template.key3" placeholder="请输入微信订阅消息的KEY值"></el-input>
              </el-form-item>
          </div> -->

          <!-- 订单配送 -->
            <!-- <div v-if="template.cate == '4' ">
              <el-form-item label="订单编号" prop="key1">
                <el-input v-model="template.key1" placeholder="请输入微信订阅消息的KEY值"></el-input>
              </el-form-item>
              <el-form-item label="商家名称" prop="key2">
                <el-input v-model="template.key2" placeholder="请输入微信订阅消息的KEY值"></el-input>
              </el-form-item>
              <el-form-item label="骑手信息" prop="key3">
                <el-input v-model="template.key3" placeholder="请输入微信订阅消息的KEY值"></el-input>
              </el-form-item>
              <el-form-item label="温馨提示" prop="key4">
                <el-input v-model="template.key4" placeholder="请输入微信订阅消息的KEY值"></el-input>
              </el-form-item>
          </div> -->

          <!-- 商品送达 -->
            <!-- <div v-if="template.cate == '5' ">
              <el-form-item label="订单编号" prop="key1">
                <el-input v-model="template.key1" placeholder="请输入微信订阅消息的KEY值"></el-input>
              </el-form-item>
              <el-form-item label="商户名称" prop="key2">
                <el-input v-model="template.key2" placeholder="请输入微信订阅消息的KEY值"></el-input>
              </el-form-item>
              <el-form-item label="收货地址" prop="key3">
                <el-input v-model="template.key3" placeholder="请输入微信订阅消息的KEY值"></el-input>
              </el-form-item>
              <el-form-item label="送达时间" prop="key4">
                <el-input v-model="template.key4" placeholder="请输入微信订阅消息的KEY值"></el-input>
              </el-form-item>
              <el-form-item label="温馨提示" prop="key5">
                <el-input v-model="template.key5" placeholder="请输入微信订阅消息的KEY值"></el-input>
              </el-form-item>
          </div> -->

          <!-- 优惠券到期 -->
            <!-- <div v-if="template.cate == '6' ">
              <el-form-item label="优惠券名称" prop="key1">
                <el-input v-model="template.key1" placeholder="请输入微信订阅消息的KEY值"></el-input>
              </el-form-item>
              <el-form-item label="温馨提示" prop="key2">
                <el-input v-model="template.key2" placeholder="请输入微信订阅消息的KEY值"></el-input>
              </el-form-item>
          </div> -->

          <!-- 积分到期 -->
            <!-- <div v-if="template.cate == '7' ">
              <el-form-item label="用户名" prop="key1">
                <el-input v-model="template.key1" placeholder="请输入微信订阅消息的KEY值"></el-input>
              </el-form-item>
              <el-form-item label="变动积分" prop="key2">
                <el-input v-model="template.key2" placeholder="请输入微信订阅消息的KEY值"></el-input>
              </el-form-item>
              <el-form-item label="变动原因" prop="key3">
                <el-input v-model="template.key3" placeholder="请输入微信订阅消息的KEY值"></el-input>
              </el-form-item>
              <el-form-item label="温馨提示" prop="key4">
                <el-input v-model="template.key4" placeholder="请输入微信订阅消息的KEY值"></el-input>
              </el-form-item>
          </div> -->

          <!-- 礼品卡券退回 -->
            <!-- <div v-if="template.cate == '8' ">
              <el-form-item label="活动标题" prop="key1">
                <el-input v-model="template.key1" placeholder="请输入微信订阅消息的KEY值"></el-input>
              </el-form-item>
              <el-form-item label="备注" prop="key2">
                <el-input v-model="template.key2" placeholder="请输入微信订阅消息的KEY值"></el-input>
              </el-form-item>
          </div> -->

          <!-- 礼品卡券领取 -->
            <!-- <div v-if="template.cate == '9' ">
              <el-form-item label="好友昵称" prop="key1">
                <el-input v-model="template.key1" placeholder="请输入微信订阅消息的KEY值"></el-input>
              </el-form-item>
              <el-form-item label="领取时间" prop="key2">
                <el-input v-model="template.key2" placeholder="请输入微信订阅消息的KEY值"></el-input>
              </el-form-item>
              <el-form-item label="温馨提示" prop="key3">
                <el-input v-model="template.key3" placeholder="请输入微信订阅消息的KEY值"></el-input>
              </el-form-item>
          </div> -->

		    </el-form>
		    <div style="text-align:right;">
		      <el-button type="danger" @click="dialogFormVisible=false">取消</el-button>
		      <el-button type="primary" @click="confirm">确定</el-button>
		    </div>
		</el-dialog>

  </div>
</template>

<script>
import {getWeixinTemplate, updateWeixinTemplate} from '@/api/third'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime } from '@/utils'

const typeOptions = [
  { key: '1', text: '派单成功通知' },
  { key: '2', text: '订单失败通知' },
  { key: '3', text: '催收货通知' },
  { key: '4', text: '催评价通知' },
  { key: '5', text: '评价审核不成功' },
  { key: '6', text: '未付款订单即将关闭提醒' },
  { key: '7', text: '平台处罚通知' },
  { key: '8', text: '提现成功通知' },
  { key: '9', text: '提现失败通知' },
]

// arr to obj ,such as { CN : "China", US : "USA" }
const typeKeyValue = typeOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

export default {
  name: 'weixinTemplateTable',
  directives: { waves },
	filters: {
    typeFilter(status) {
      return typeKeyValue[status]
    }
	},
  data() {
    return {
      tableKey: 0,
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {},
      template: {},
      dialogFormVisible: false,
      dialogType: '',
			multipleSelection: [],
			rules: {
			  cate: [{ required: true, message: '请选择提醒类型', trigger: 'change' }],
			  templateId: [{ required: true, message: '模板ID不能为空', trigger: 'blur' }],
        key1: [{ required: true, message: '订阅消息KEY值不能为空', trigger: 'blur' }],
        key2: [{ required: true, message: '订阅消息KEY值不能为空', trigger: 'blur' }],
        key3: [{ required: true, message: '订阅消息KEY值不能为空', trigger: 'blur' }],
        key4: [{ required: true, message: '订阅消息KEY值不能为空', trigger: 'blur' }],
        key5: [{ required: true, message: '订阅消息KEY值不能为空', trigger: 'blur' }]
			},
			activeName:'first',
      typeOptions,
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      getWeixinTemplate(this.listQuery).then(response => {
        this.list = response.data
				this.listLoading = false
      })
    },
		handleUpdate(row) {
      this.template = {}
		  this.template = Object.assign({}, row) // copy obj
      if(this.template.content != null){
        let keyArr = this.template.content.split(",");
        this.$set(this.template, "key1", keyArr.length > 0 ? keyArr[0] : "");
        this.$set(this.template, "key2", keyArr.length > 1 ? keyArr[1] : "");
        this.$set(this.template, "key3", keyArr.length > 2 ? keyArr[2] : "");
        this.$set(this.template, "key4", keyArr.length > 3 ? keyArr[3] : "");
        this.$set(this.template, "key5", keyArr.length > 4 ? keyArr[4] : "");
      }
		  this.dialogStatus = 'edit'
		  this.dialogFormVisible = true
		  this.$nextTick(() => {
		    this.$refs['dataForm'].clearValidate()
		  })
		},
		confirm() {
			this.$refs['dataForm'].validate((valid) => {
			  if (valid) {
			    let formData =Object.assign({}, this.template)
          if(formData.cate == '1'){
            formData.content = formData.key1+","+formData.key2+","+formData.key3+","+formData.key4+","+formData.key5
          }else if(formData.cate == '2'){
            formData.content = formData.key1+","+formData.key2+","+formData.key3+","+formData.key4
          }else if(formData.cate == '3'){
            formData.content = formData.key1+","+formData.key2+","+formData.key3+","+formData.key4
          }else if(formData.cate == '4'){
            formData.content = formData.key1+","+formData.key2+","+formData.key3+","+formData.key4
          }else if(formData.cate == '5'){
            formData.content = formData.key1+","+formData.key2+","+formData.key3+","+formData.key4+","+formData.key5
          }else if(formData.cate == '6'){
            formData.content = formData.key1+","+formData.key2
          }else if(formData.cate == '7'){
            formData.content = formData.key1+","+formData.key2+","+formData.key3+","+formData.key4
          }else if(formData.cate == '8'){
            formData.content = formData.key1+","+formData.key2
          }else if(formData.cate == '9'){
            formData.content = formData.key1+","+formData.key2+","+formData.key3
          }else if(formData.cate == '12'){
            formData.content = formData.key1+","+formData.key2+","+formData.key3+","+formData.key4+","+formData.key5
          }
					formData.addTime = null
					formData.updTime = null
			    updateWeixinTemplate(formData).then(() => {
						this.getList()
			      this.dialogFormVisible = false
			      this.$notify({
			        title: '成功',
			        message: '模板消息更新成功',
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
