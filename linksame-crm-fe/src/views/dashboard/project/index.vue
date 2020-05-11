<template>
  <div style="height:100%">
    <div class="ls-header">
      全部项目
      <span class="grep">
        <!-- 1 -->
      </span>
    </div>

    <div class="ls-box">
      <div>
        <span>
          <el-button type="primary" @click="isCreate = true">新建</el-button>
        </span>
      </div>

      <div>
        <add-project v-if="isCreate" @close="isCreate = false" />
      </div>
    </div>
  </div>
</template>

<script>

import AddProject from '@/views/projectManagement/components/addProject'


export default {
  name: 'App',
  components: {
    AddProject
  },

  data() {
    return {
      fileTypeList: [],
      isCreate: false,
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
        .post(`/type/queryList?pageType=1`, {
          page: this.pageCurrent,
          limit: this.papgeSize
        })
        .then(res => {
          console.log('附件类型列表', res)
          this.fileTypeList = res.data.list
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
