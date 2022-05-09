<template>
  <div class="app-container">
		<el-table
      :key="tableKey"
      v-loading="listLoading"
      :data="list"
      fit
      style="width: 100%;"
    >
			<el-table-column label="品牌" width="200">
			  <template slot-scope="scope">
			   <span>{{ scope.row.name }}</span>
			  </template>
			</el-table-column>

      <el-table-column >
        <template slot-scope="scope">
          <router-link :to="'/content/style/edit/'+scope.row.id">
            <span class="link-type">>>前往设置风格</span>
         </router-link>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import Tinymce from '@/components/Tinymce'
import {getStyleList} from '@/api/style'
import waves from '@/directive/waves' // Waves directive
import { parseTime, renderTime } from '@/utils'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

export default {
  name: 'styleTable',
  components: { Pagination, Tinymce },
  directives: { waves },
  data() {
    return {
      tableKey: 0,
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        pageSize: 20
      },
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      getStyleList(this.listQuery).then(response => {
        this.list = response.data
				this.listLoading = false
      })
    },

  }
}
</script>
