<template>
  <div class="app-container">
    <div class="filter-container">
      <el-button-group>
        <el-button class="filter-item" size="mini" type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button>
        <el-button class="filter-item" size="mini" type="danger" icon="el-icon-delete" @click="handleDelete">删除</el-button>
      </el-button-group>
    </div>
    <el-table :key="tableKey" v-loading="listLoading" :data="list" border fit highlight-current-row style="width: 100%;"
      @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"></el-table-column>
      <el-table-column label="名称" width="150px" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.name}}</span>
        </template>
      </el-table-column>
      <el-table-column label="展示图片" width="300px" align="center">
        <template slot-scope="scope">
          <el-image fit="contain" style="height: 100px" :src="scope.row.img" :preview-src-list="[scope.row.img]">
          </el-image>
        </template>
      </el-table-column>
      <el-table-column label="是否显示" width="150px" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status == '1' ? 'success' : 'danger' ">{{ scope.row.status | statusFilter}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" width="170px" align="center" prop="addTime">
        <template slot-scope="scope">
          <span>{{ scope.row.addTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="排序" width="150px" align="center">
        <template slot-scope="scope">
          <el-input-number size="mini" style="width:100px;" v-model="scope.row.sort" @change="sortChange(scope.row)"
            :min="1" label="修改排序"></el-input-number>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.actions')" class-name="small-padding" fixed="right" min-width="120">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" icon="el-icon-edit" @click="handleUpdate(scope.row)">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize"
      @pagination="getList" />

    <!--添加编辑框-->
    <el-dialog :title="dialogType==='edit'?'编辑':'新增'" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="indexCate" label-width="80px" label-position="right" style="width: 400px; margin-left:50px;">
        <el-form-item label="是否显示" prop="status">
          <el-select v-model="indexCate.status" class="filter-item" placeholder="请选择">
            <el-option v-for="item in statusOptions" :key="item.key" :label="item.text" :value="item.key" />
          </el-select>
        </el-form-item>
        <el-form-item label="板块名称" prop="name">
          <el-input v-model="indexCate.name" placeholder="请输入板块名称" />
        </el-form-item>
        <el-form-item label="展示顺序" prop="sort">
          <el-input-number style="width:200px;" v-model="indexCate.sort" :min="1" label="修改排序"></el-input-number>
        </el-form-item>
        <el-form-item label="展示图片" prop="img">
          <Upload v-model="indexCate.img" />
           <ImageSource :mul="false" v-model="indexCate.img" />
          <p style="color:#ff4949;font-size:12px; ">图片建议大小为375px*62px</p>
        </el-form-item>
        <el-form-item label="展示商品" prop="foodIds" style="width: 700px;">
          <el-transfer filterable filter-placeholder="请输入商品名称" v-model="foodIds" :titles="['待选商品', '已选商品']" :data="foodList">
          </el-transfer>
        </el-form-item>
      </el-form>
      <div style="text-align:right;">
        <el-button type="danger" @click="dialogFormVisible=false">取消</el-button>
        <el-button type="primary" @click="confirm">确定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
  import {
    getIndexCateList,
    updateIndexCate,
    updateIndexCateSort,
    deleteIndexCate,
    getIndexFoodList
  } from '@/api/indexCate'
  import {
    getFoodSelectorByBrandId
  } from '@/api/food'
  import waves from '@/directive/waves' // Waves directive
  import {
    parseTime,
    renderTime
  } from '@/utils'
  import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
  import Upload from '@/components/Upload/singleImage'
  import ImageSource from '@/components/Upload/imageSource'

  const statusOptions = [{
      key: '1',
      text: '显示'
    },
    {
      key: '0',
      text: '隐藏'
    }
  ]

  // arr to obj ,such as { CN : "China", US : "USA" }
  const statusKeyValue = statusOptions.reduce((acc, cur) => {
    acc[cur.key] = cur.text
    return acc
  }, {})

  export default {
    name: 'indexCateTable',
    components: {
      Pagination,
      Upload,
      ImageSource
    },
    directives: {
      waves
    },
    filters: {
      statusFilter(status) {
        return statusKeyValue[status]
      }
    },
    data() {
      return {
        tableKey: 0,
        list: null,
        total: 0,
        listLoading: true,
        listQuery: {
          page: 1,
          pageSize: 20,
          type: '7'
        },
        indexCate: {},
        dialogFormVisible: false,
        dialogType: '',
        multipleSelection: [],
        rules: {
          status: [{
            required: true,
            message: '请选择是否显示状态',
            trigger: 'change'
          }],
          sort: [{
            required: true,
            message: '排序不能为空',
            trigger: 'blur'
          }],
          img: [{
            required: true,
            message: '请上传图片',
            trigger: 'blur'
          }],
          brandId: [{
            required: true,
            message: '请选择品牌',
            trigger: 'change'
          }],
          name: [{
            required: true,
            message: '请填写板块名称',
            trigger: 'blur'
          }],
        },
        statusOptions,
        activeName: 'first',
        brandOptions: [],
        foodList: [],
        foodIds: []
      }
    },
    created() {
      this.getList()
    },
    methods: {
      getList() {
        this.listLoading = true
        getIndexCateList(this.listQuery).then(response => {
          this.list = response.data.list
          this.total = response.data.total
          this.listLoading = false
        })
      },
      getFoodSelectorByBrandId(brandId) {
        this.foodList = []
        let type = this.activeName == 'first' ? 7 : 5;
        getFoodSelectorByBrandId({
          brandId: brandId,
          type: type
        }).then(response => {
          let list = response.data
          list.forEach((item, index) => {
            this.foodList.push({
              label: item.name,
              key: item.id
            });
          });
        })
      },
      handleCreate() {
        this.dialogStatus = 'add'
        this.dialogFormVisible = true
        this.indexCate = {}
        this.foodList = []
        if (this.activeName == 'first') {
          this.indexCate.type = 7
        } else {
          this.indexCate.type = 5
          this.getFoodSelectorByBrandId()
        }
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      },
      handleUpdate(row) {
        this.dialogStatus = 'edit'
        this.dialogFormVisible = true
        this.foodList = []
        this.indexCate = Object.assign({}, row) // copy obj
        this.getFoodSelectorByBrandId()
        getIndexFoodList({
          cateId: this.indexCate.id
        }).then(response => {
          this.foodIds = response.data
          //this.$set(foodIds,response.data);
        })
        setTimeout(() => {

          this.indexCate.brandId = 0
          this.indexCate.brandId = row.brandId
        }, 1000)
        console.log(this.foodIds)
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      },
      confirm() {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            let formData = Object.assign({}, this.indexCate)
            formData.foodIds = JSON.stringify(this.foodIds);
            formData.addTime = null
            formData.updTime = null
            updateIndexCate(formData).then(() => {
              this.getList()
              this.dialogFormVisible = false
              this.$notify({
                title: '成功',
                message: '首页自定义保存成功',
                type: 'success',
                duration: 2000
              })
            })
          }
        })
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      handleDelete() {
        const delVals = this.multipleSelection;
        if (!delVals || delVals.length <= 0) {
          this.$message({
            message: '请至少选择一条数据',
            type: 'error',
            duration: 2000
          })
          return;
        }
        this.$confirm('您确定要删除这些记录?', '提醒', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(async () => {
            let values = [];
            delVals.forEach(val => {
              values.push(val.id);
            })
            deleteIndexCate(values.join(',')).then(() => {
              this.getList()
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
      sortChange(row) {
        let formData = {
          sort: row.sort,
          id: row.id
        }
        updateIndexCateSort(formData).then(() => {
          this.$notify({
            title: '成功',
            message: '显示顺序修改成功',
            type: 'success',
            duration: 2000
          })
          this.getList()
        })
      },

    }
  }
</script>
