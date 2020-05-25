<template>
  <div style="height:100%">
    <div class="ls-header">
      标签管理
      <span class="grep">
        <!-- 1 -->
      </span>
    </div>

    <div class="ls-box">
      <div>
        <span>
          <el-button type="primary" @click="dialogCreate = true">新建</el-button>
        </span>
      </div>

      <el-table
        :data="markList"
        :header-cell-style="{background:'#F5F7FA',color:'#606266'}"
        border
        height="80%"
        style="margin: 20px 0 0 0"
      >
        <!-- @selection-change="selectTable" -->
        <!-- <el-table-column prop="typeId" label="ID" /> -->
        <el-table-column prop="name" label="名称" />
        <el-table-column prop="color" label="颜色" />
        <el-table-column prop="status" label="状态" />
        <el-table-column prop label="操作">
          <template slot-scope="scope">
            <span style="color:#409EFF" @click="deleteFileType(scope.row)">启用</span>
            <span style="color:#606266">丨</span>
            <span style="color:#F56C6C" @click="deleteFileType(scope.row)">删除</span>
          </template>
        </el-table-column>
      </el-table>

      <div class="ls-pagination">
        <el-pagination
          :current-page="pageCurrent"
          :page-sizes="[2, 10, 20]"
          :page-size="pageSize"
          :total="pageTotal"
          layout="total, sizes, prev, pager, next, jumper"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        />
      </div>

      <el-dialog :visible.sync="dialogCreate" title="新建类型" width="30%">
        <el-form ref="form" :model="form" label-width="80px">
          <el-form-item label="标签名称：">
            <el-input v-model="form.typeName" placeholder="请输入文件类型" style="width:200px" />
          </el-form-item>
          <el-form-item label="标签颜色：">
            <el-input v-model="form.typeCode" placeholder="请输入文件 Code" style="width:200px" />
          </el-form-item>
        </el-form>

        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogCreate = false">取 消</el-button>
          <el-button type="primary" @click="createFileType">确 定</el-button>
        </span>
      </el-dialog>
    </div>
  </div>
</template>

<script>



export default {
  name: 'App',
  components: {

  },

  data() {
    return {
      markList: [],

      pageCurrent: 1,
      pageTotal: 1,
      pageSize: 10,

      dialogCreate: false,
      form: {
        typeName: undefined,
        typeCode: undefined
      }
    }
  },
  watch: {
    // filterText(val) {
    //   this.$refs.tree.filter(val)
    // }
  },
  created() {

  },

  mounted() {
    this.retriveTypeList()
  },

  methods: {
    retriveTypeList() {
      this.$request
        .post(`/label/queryList?pageType=1`, {
          page: this.pageCurrent,
          limit: this.papgeSize
        })
        .then(res => {
          console.log('标签列表', res)
          this.markList = res.data.list
          this.pageTotal = res.data.totalRow
        }).catch(e => {
          console.log('retriveTypeList err', e)
        })
    },

    createFileType() {
      this.$request.post(`/type/setType?typeName=${this.form.typeName}&typeCode=${this.form.typeCode}`, {

      }).then(res => {
        console.log(res)
        this.dialogCreate = false
        this.retriveTypeList()
      }).catch(e => {
        console.log(e)
      })
    },

    deleteFileType(row) {
      this.$confirm('此操作将永久删除该文件类型, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$request.post(`/type/delType?typeId=${row.typeId}`, {

        }).then(res => {
          this.retriveTypeList()
        }).catch(e => {
          console.log(e)
        })

        this.$message({
          type: 'success',
          message: '删除成功!'
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },




    handleCurrentChange(val) {
      console.log(`当前页: ${val}`)
      this.pageCurrent = val
      this.retriveTypeList()
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`)
      this.pageSize = val
      this.retriveTypeList()
    }

  }
}
</script>

<style scoped lang="scss">
.tree_menu {
  position: fixed;
  display: block;
  z-index: 20000;
  background-color: #fff;
  padding: 5px 0;
  border: 1px solid #ebeef5;
  border-radius: 4px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);

  ul {
    margin: 0;
    padding: 0;
  }
  ul li {
    list-style: none;
    margin: 0;
    padding: 0 15px;
    font-size: 14px;
    line-height: 30px;
    cursor: pointer;
  }
  ul li:hover {
    background-color: #ebeef5;
  }
}
/* 表头 */
/deep/ .el-table th {
  background-color: rgb(247, 247, 250);
}

/deep/ .el-table__header-wrapper thead div {
  background-color: rgb(247, 247, 250);
}

/deep/ .el-tree-node__expand-icon.is-leaf {
  //   color: rgb(144, 147, 153);
  color: transparent;
}
/deep/ .el-tree-node__expand-icon {
  color: rgb(144, 147, 153);
}

/deep/ .el-tree-node__content {
  height: 40px;
  font-size: 14px;
  font-family: Microsoft YaHei;
}

.ls-tree-l {
  background: white;
  height: 100%;
  width: 20%;
  float: left;
  margin: 10px 10px 0 10px;
  padding: 30px 20px 0 20px;
}

.ls-tree-r {
  background: white;
  float: right;
  width: 78%;
  height: 100%;
  margin: 10px 10px 0 0px;
  .ls-tree-r-header {
    width: 100%;
    height: 60px;
    line-height: 60px;
    margin: 10px 10px 0 10px;
  }
  .ls-tree-r-content {
    height: calc(100% - 80px);
    // overflow-y: scroll;
    padding: 0px 20px 20px 20px;
  }
}
</style>
