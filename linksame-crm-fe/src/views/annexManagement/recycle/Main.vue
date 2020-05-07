<template>
  <div style="height:100%">
    <div class="ls-header">
      回收站
      <span class="grep">
        <!-- 1 -->
      </span>
    </div>

    <div class="ls-box">
      <div>
        <span>
          <el-button type="primary" @click="rollbackFileType">还 原</el-button>
          <el-button type="primary" @click="retriveFileList">刷 新</el-button>
        </span>
      </div>
      <!--
      <el-table
        :data="fileTypeList"
        :header-cell-style="{background:'#F5F7FA',color:'#606266'}"
        border
        height="80%"
        style="margin: 20px 0 0 0"
      >

        <el-table-column prop="typeId" label="ID" />
        <el-table-column prop="typeName" label="文件类型名" />
        <el-table-column prop="typeCode" label="文件 Code" />
        <el-table-column prop label="操作">
          <template slot-scope="scope">
            <span @click="deleteFileType(scope.row)">删除</span>
          </template>
        </el-table-column>
      </el-table>-->

      <el-table
        :data="fileTableData"
        :header-cell-style="{background:'#F5F7FA',color:'#606266'}"
        border
        height="80%"
        style="margin: 20px 0 0 0"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55" />
        <el-table-column fixed prop="oldName" label="文件名">
          <template slot-scope="scope">
            <span @click="retriveFileDetail(scope.row)">{{ scope.row.oldName }}</span>
          </template>
        </el-table-column>

        <el-table-column prop="compositionName" label="附件类型文件名" />

        <el-table-column prop="size" label="大小" width="100" />
        <el-table-column prop="createTime" label="更新时间" width="200" />

        <el-table-column prop="createUserName" label="上传者" width="100" />

        <!-- <el-table-column prop label="操作">
          <template slot-scope="scope">
            <span @click="rollbackFileType(scope.row)">还原</span>
          </template>
        </el-table-column>-->
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
        <div>
          文件类型：
          <el-input v-model="form.typeName" placeholder="请输入文件类型" style="width:200px" />
        </div>
        <br />
        <div>
          文件 Code：
          <el-input v-model="form.typeCode" placeholder="请输入文件 Code" style="width:200px" />
        </div>

        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogCreate = false">取 消</el-button>
          <!-- <el-button type="primary" @click="createFileType">确 定</el-button> -->
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
      fileTableData: [],


      fileSelect: {
        fileIds: undefined
      },
      fileSelectData: [],

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
    this.retriveFileList()
  },

  methods: {
    retriveFileList() {
      this.$request
        .post(`/file/getFileList?delFlag=1&orderBy=2`, {
          page: this.pageCurrent,
          limit: this.papgeSize
        })
        .then(res => {
          console.log('查询附件文件', res)
          this.fileTableData = res.data.list
          this.pageTotal = res.data.totalRow
        }).catch(e => {
          console.log('/file/getFileList err', e)
        })
    },

    handleSelectionChange(val) {
      this.fileSelectData = val
    },


    rollbackFileType() {
      const fileIds = []
      this.fileSelectData.forEach((item, index) => {
        fileIds.push(item.fileId)
      })
      this.fileSelect.fileIds = fileIds.join(',')
      console.log('还原文件', fileIds.join(','))

      this.$request
        .post(`/file/removeRecycleByIds?fileIds=${this.fileSelect.fileIds}`, {})
        .then(res => {
          console.log('还原文件', res)
          this.retriveFileList()
        }).catch(e => {
          console.log('/file/getFileList err', e)
        })
    },


    handleCurrentChange(val) {
      console.log(`当前页: ${val}`)
      this.pageCurrent = val
      this.retriveFileList()
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`)
      this.pageSize = val
      this.retriveFileList()
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
