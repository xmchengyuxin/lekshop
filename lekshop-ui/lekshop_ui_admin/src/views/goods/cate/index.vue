<template>
  <div class="app-container">
    <div class="filter-container">
			<el-button-group>
				<el-button class="filter-item" type="primary" size="mini" icon="el-icon-edit" @click="handleCreate">添加</el-button>
			</el-button-group>
		</div>
		<el-table
      :data="list"
      row-key="id"
      :key="tableKey"
      v-loading="listLoading"
      border
      fit
      highlight-current-row
      style="width: 100%;"
      @selection-change="handleSelectionChange"
      default-expand-all
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
      <el-table-column type="selection" width="55" align="center"></el-table-column>
     <!-- <el-table-column label="ID" width="55" align="center" prop="id">
        <template slot-scope="scope">
        </template>
      </el-table-column> -->
			<el-table-column label="分类名称" prop="name">
			  <template slot-scope="scope">
          <span v-if="scope.row.level == 2"> ┗</span>
          <span v-if="scope.row.level >= 3"> ┝</span>
			    <span>{{ scope.row.name }}</span>
			  </template>
			</el-table-column>
			<el-table-column label="图片" width="200px"  align="center" prop="img">
			  <template slot-scope="scope">
          <el-image
              v-if="scope.row.img"
              style="height: 30px"
              :src="scope.row.img"
              :preview-src-list="[scope.row.img]">
            </el-image>
			  </template>
			</el-table-column>
			<el-table-column label="创建时间" width="200px"  align="center" prop="addTime">
			  <template slot-scope="scope">
			    <span>{{ scope.row.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
			  </template>
			</el-table-column>
      <el-table-column label="开启状态"  align="center" width="150px" prop="status">
        <template slot-scope="scope">
          <el-switch :active-value="1" :inactive-value="0" v-model="scope.row.status" @change="statusChange(scope.row)" ></el-switch>
        </template>
      </el-table-column>
			<el-table-column label="排序" width="150px"  align="center" prop="sort">
			  <template slot-scope="scope">
			  <el-input-number size="mini" style="width:100px;" v-model="scope.row.sort" @change="sortChange(scope.row)" :min="1" label="修改排序"></el-input-number>
			  </template>
			</el-table-column>
      <el-table-column :label="$t('table.actions')" class-name="small-padding" fixed="right" width="200px">
        <template slot-scope="scope">
						<el-button type="primary" size="mini" icon="el-icon-edit" @click="handleUpdate(scope.row)">编辑</el-button>
						<el-button size="mini" type="danger" icon="el-icon-delete" @click="handleDelete(scope.row)">删除</el-button>
          </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />

		<!--添加编辑框-->
		<el-dialog :title="dialogType==='edit'?'编辑':'新增'" :visible.sync="dialogFormVisible">
		    <el-form ref="dataForm" :rules="rules" :model="goodsCate" label-width="80px" label-position="right" style="width: 100%; margin-left: 50px;">
					<el-form-item label="父ID" prop="ptid">
          <el-cascader-panel v-model="goodsCate.ptid" :options="goodsCateOptions" :props="props" style="width: 60%;"></el-cascader-panel>
					</el-form-item>
          <el-form-item label="分类名称" prop="name">
            <el-input v-model="goodsCate.name" placeholder="请输入名称" style="width: 60%;"/>
          </el-form-item>
					<el-form-item label="分类图片" prop="img" style="width: 60%;">
					  <Upload v-model="goodsCate.img" />
            <ImageSource :mul="false" v-model="goodsCate.img" />
					</el-form-item>
					<el-form-item label="排序" prop="sort">
						<el-input-number style="width:200px;" v-model="goodsCate.sort" :min="1" label="修改排序"></el-input-number>
					</el-form-item>
		    </el-form>
		    <div style="text-align:right;">
		      <el-button type="danger" @click="dialogFormVisible=false">取消</el-button>
		      <el-button type="primary" @click="confirm">确定</el-button>
		    </div>
		</el-dialog>

  </div>
</template>
<style scoped="scoped">
  ::v-deep .el-table .el-table__expand-icon{
    margin-right: 0px;
  }
</style>

<script>
import {getGoodsCateList, addGoodsCate, updateGoodsCate, deleteGoodsCate} from '@/api/goods'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime } from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
import Upload from '@/components/Upload/singleImage'
import ImageSource from '@/components/Upload/imageSource'

export default {
  name: 'goodsCate',
  components: { Pagination, Upload, ImageSource },
  directives: { waves },
  data() {
    return {
      props:{ label: 'name', value : 'id', children: 'children', checkStrictly: true },
      tableKey: 0,
      list: [],
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        pageSize: 20
      },
      goodsCate: {},
      dialogFormVisible: false,
      dialogType: '',
			rules: {
			  name: [{ required: true, message: '分类名称不能为空', trigger: 'blur' }],
				sort: [{ required: true, message: '显示顺序不能为空', trigger: 'blur' }]
			},
			goodsCateOptions:[]
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      getGoodsCateList(this.listQuery).then(response => {
        this.list = response.data
				this.listLoading = false
      })
    },
		handleCreate() {
			this.dialogStatus = 'add'
			this.dialogFormVisible = true
			this.goodsCate = {}
			this.getGoodsCateSelector()
			this.$nextTick(() => {
			  this.$refs['dataForm'].clearValidate()
			})
		},
		handleUpdate(row) {
		  this.goodsCate = Object.assign({}, row) // copy obj
		  this.dialogStatus = 'edit'
		  this.dialogFormVisible = true
			this.getGoodsCateSelector()
      if(this.goodsCate.level == 2){
          this.goodsCate.ptid = [this.goodsCate.pid]
      }else if(this.goodsCate.level == 3){
          this.goodsCate.ptid = [this.goodsCate.topId, this.goodsCate.pid]
      }

		  this.$nextTick(() => {
		    this.$refs['dataForm'].clearValidate()
		  })
		},
		getGoodsCateSelector(){
			getGoodsCateList().then(response => {
				this.goodsCateOptions = response.data
			})
		},
		confirm() {
			this.$refs['dataForm'].validate((valid) => {
			  if (valid) {
			    let formData =Object.assign({}, this.goodsCate)
					if(this.goodsCate.ptid){
            formData.pid = this.goodsCate.ptid[this.goodsCate.ptid.length-1]
					}
					formData.addTime = null
					formData.updTime = null
			    updateGoodsCate(formData).then(() => {
						this.getList()
			      this.dialogFormVisible = false
			      this.$notify({
			        title: '成功',
			        message: '保存成功',
			        type: 'success',
			        duration: 2000
			      })
			    })
			  }
			})
		},
		handleDelete(row){
			this.$confirm('删除此记录需要先删除它的子分类,您确定要删除这条记录?', '提醒', {
			  confirmButtonText: '确定',
			  cancelButtonText: '取消',
			  type: 'warning'
			}).then(async() => {
			    deleteGoodsCate(row.id).then(() => {
			    	this.getList()
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
		sortChange(row) {
			let formData = {
				sort: row.sort,
				id: row.id
			}
			updateGoodsCate(formData).then(() => {
				this.$notify({
					title: '成功',
					message: '显示顺序修改成功',
					type: 'success',
					duration: 2000
				})
				this.getList()
			})
		},
    statusChange(row) {
      let formData = {
    		status: row.status,
    		id: row.id
    	}
    	updateGoodsCate(formData).then(() => {
    	  this.$notify({
    	    title: '成功',
    	    message: '修改成功',
    	    type: 'success',
    	    duration: 2000
    	  })
    	})
    },
    handleSelectionChange(val) {
    	this.multipleSelection = val;
     },
     /* load(tree, treeNode, resolve) {
        const queryParams = {
          cateId: tree.id,
          level: tree.level
        }
        getLeafGoodsCateList(queryParams).then((result) => {
          resolve(result.data);
        })
      }, */

  }
}
</script>
