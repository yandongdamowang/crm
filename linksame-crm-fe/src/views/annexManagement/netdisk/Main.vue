<template>
  <!-- <div class="annex"> -->
  <div style="height:100%">
    <!-- <div class="ls-box"> -->

    <div class="ls-tree-l">
      <el-button type="primary" @click="dialogCreateStatus = true">创建</el-button>
      <el-button @click="dialogRenameStatus = true">重命名</el-button>
      <el-button @click="dialogMoveStatus = true ">移动</el-button>
      <el-button @click="deleteFolder ">删除</el-button>

      <el-tree
        ref="tree"
        :data="folderListData"
        :props="defaultProps"
        :filter-node-method="filterNode"
        style="margin:20px 0 0 0"
        class="filter-tree"
        default-expand-all
        @node-click="handleNodeClick"
      >
        <!-- <template slot-scope="scope">{{ scope.row }}</template> -->
        <span slot-scope="{node,data}">
          <span style="color:black">
            <!-- <i class="el-icon-folder" /> -->
            <img src="@/assets/filetype/folder.png" alt />
            {{ data.folderName }}
          </span>
        </span>
      </el-tree>
    </div>

    <div class="ls-tree-r">
      <div class="ls-tree-r-header">
        <Upload />
      </div>

      <div class="ls-tree-r-content">
        <el-table
          :data="fileTableData"
          border
          style="height: 92%"
          @selection-change="handleSelectionChange"
        >
          >
          <el-table-column type="selection" width="55" />
          <el-table-column fixed prop="oldName" label="文件名">
            <template slot-scope="scope">
              <span @click="retriveFileDetail(scope.row)">{{ scope.row.oldName }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="size" label="大小" width="100" />
          <el-table-column prop="createTime" label="更新时间" width="200" />

          <el-table-column prop="createUserName" label="上传者" width="100" />
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
      </div>
    </div>

    <el-dialog :visible.sync="dialogCreateStatus" title="创建文件夹" width="30%">
      <el-input v-model="folderCreateName" placeholder="请输入内容" style="width:200px" />
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogCreateStatus = false">取 消</el-button>
        <el-button type="primary" @click="createFolder">确 定</el-button>
      </span>
    </el-dialog>

    <el-dialog :visible.sync="dialogMoveStatus" title="移动文件夹" width="30%">
      <el-input v-model="folderSelect.folderName" disabled placeholder="请输入内容" style="width:200px" />
      <span>移动至</span>
      <!-- <el-input v-model="folderSelect.folderMoveName" placeholder="请输入内容" style="width:200px" /> -->

      <el-tree
        ref="tree"
        :data="folderListData"
        :props="defaultProps"
        :filter-node-method="filterNode"
        class="filter-tree"
        default-expand-all
        @node-click="handleNodeClick"
      >
        <span slot-scope="{node,data}">
          <span style="color:black">
            <i class="el-icon-folder" />
            {{ data.folderName }}
          </span>
        </span>
      </el-tree>

      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogMoveStatus = false">取 消</el-button>
        <el-button type="primary" @click="moveFolder">确 定</el-button>
      </span>
    </el-dialog>

    <el-dialog :visible.sync="dialogRenameStatus" title="重命名文件夹" width="30%">
      <el-input v-model="folderSelect.folderName" disabled placeholder="请输入内容" style="width:200px" />
      <span>重命名</span>
      <el-input v-model="folderSelect.folderReName" placeholder="请输入内容" style="width:200px" />

      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogRenameStatus = false">取 消</el-button>
        <el-button type="primary" @click="updateFolder">确 定</el-button>
      </span>
    </el-dialog>

    <el-drawer :visible.sync="drawer" :with-header="false" :destroy-on-close="true" size="70%">
      <el-button type="primary" @click="downloadFile">下 载</el-button>
      <a :href="downloadAction">下载模板</a>

      <div>{{ fileSelect.oldName }}</div>
      <div>创建人：{{ fileSelect.createName }}</div>
      <div>文件大小：{{ fileSelect.size }}</div>
      <div>版本号：{{ fileSelect.fileVersion }}</div>
      <div>文件类型：{{ fileSelect.fileType }}</div>

      <related-business :all-data="allData" :margin-left="'0'" @checkInfos="checkInfos" />

      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="操作日志" name="first">
          <el-table :data="fileDetailLogTable" style="width: 100%">
            <el-table-column prop="content" label="操作记录" width />
            <el-table-column prop="createTime" label="操作时间" width="300" />
          </el-table>
        </el-tab-pane>

        <el-tab-pane label="历史版本" name="second">配置管理</el-tab-pane>
      </el-tabs>
    </el-drawer>
  </div>
</template>

<script>
//  <el-form ref="form" label-width="80px">
//     <el-form-item label="">
//       <el-input v-model="fileSelect.createName"></el-input>
//     </el-form-item>
//  </el-form>



export default {
  name: 'App',
  components: {
    Upload: () => import('./upload'),
    relatedBusiness: () => import('@/components/relatedBusiness')
  },

  data() {
    return {
      downloadAction: process.env.BASE_API + `/file/downFile?fileId=72`,
      activeName: 'first',
      pageCurrent: 1,
      papgeSize: 10,
      pageTotal: '',
      allData: {},
      relevanceAll: {},
      drawer: false,
      dialogCreateStatus: false,
      dialogMoveStatus: false,
      dialogRenameStatus: false,
      folderCreateName: undefined,
      folderCreateNamePid: 0,
      batchId: '',
      fileTableData: [],
      fileRowData: {},
      folderSelect: {
        batchId: '',
        createTime: '',
        createUserId: 3,
        folderCode: 'ADMINISTRATION',
        folderId: undefined,
        folderName: '',
        folderMoveName: '',
        folderReName: '',
        folderPid: undefined
      },
      fileSelect: {

      },
      fileDetailLogTable: [],

      filterText: '',
      folderListData: [],
      defaultProps: {
        children: 'list',
        label: 'folderName'
      }
    }
  },
  watch: {
    filterText(val) {
      this.$refs.tree.filter(val)
    }
  },
  created() {
    this.retriveFolderList()
  },

  mounted() {

  },


  methods: {
    getDetail() {
      this.$request.post('/work/getWorkById', {
        workId: this.$route.params.id
      })
        .then(res => {
          this.batchId = res.data.batchId
          //   console.log('batchId', this.batchId)
          this.retriveFolderList()
        })
        .catch(() => {})
    },
    checkInfos(val) {
      this.relevanceAll = val
      console.log('checkInfos', val)
    },


    filterNode(value, data) {
      if (!value) return true
      return data.label.indexOf(value) !== -1
    },



    handleNodeClick(data) {
      console.log('handleNodeClick', data)
      this.folderSelect.batchId = data.batchId
      this.folderSelect.folderId = data.folderId
      this.folderSelect.folderPid = data.folderPid
      this.folderSelect.folderName = data.folderName
      this.folderCreateNamePid = data.folderId
      this.retriveFileList()
    },

    handleSelectionChange(val) {
      console.log('handleSelectionChange', val)
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
    },







    retriveFolderList() {
      this.$request
        .post(`/folder/queryFolder`)
        .then(res => {
          console.log(res)
          this.folderListData = res.data
        }).catch(e => {
          console.log('retriveFolderList err', e)
        })
    },


    createFolder() {
      this.$request
        .post(`/folder/createFolder?folderName=${this.folderCreateName}&folderCode=CONTRACTUAL&folderPid=${this.folderCreateNamePid}&batchId=${this.batchId}`)
        .then(res => {
          console.log(res)
          this.folderName = ''
          this.folderListData = res.data

          this.dialogCreateStatus = false
          this.folderCreateName = ''
          this.retriveFolderList()
        }).catch(e => {
          console.log('retriveFolderList err', e)
        })
    },
    deleteFolder() {
      this.$request
        .post(`/folder/deleteFolder?folderId=${this.folderSelect.folderId}`)
        .then(res => {
          console.log('deleteFolder', res)
          this.retriveFolderList()
        }).catch(e => {
          console.log('retriveFolderList err', e)
        })
    },

    moveFolder(id, pid) {
      this.$request
        .post(`/folder/mobileFolder?folderId=${id}&folderPid=${pid}`)
        .then(res => {
          console.log('moveFolder', res)

          this.retriveFolderList()
        }).catch(e => {
          console.log('moveFolder err', e)
        })
    },



    updateFolder() {
      this.$request
        .post(`/folder/renameFolder?folderId=${this.folderSelect.folderId}&folderName=${this.folderSelect.folderReName}`)
        .then(res => {
          console.log('updateFolder', res)
          this.dialogRenameStatus = false
          this.retriveFolderList()
        }).catch(e => {
          console.log('retriveFolderList err', e)
        })
    },



    retriveFileList() {
      this.$request
        .post(`/file/getFileList?batchId=${this.folderSelect.batchId}&orderBy=2`, {
          page: this.pageCurrent,
          limit: this.papgeSize
        })
        .then(res => {
          console.log('/file/getFileList', res)
          this.fileTableData = res.data.list
          this.pageTotal = res.data.totalRow
        }).catch(e => {
          console.log('/file/getFileList err', e)
        })
    },


    retriveFileDetail(row) {
      this.fileRowData = row
      this.$request
        .post(`/file/queryFileInfo?fileId=${row.fileId}`)
        .then(res => {
          console.log('文件详情', res)
          this.drawer = true
          this.fileSelect = res.data
          this.retriveFileDetailLog()
        }).catch(e => {
          console.log('/file/getFileList err', e)
        })
    },
    retriveFileDetailLog() {
      this.$request
        .post(`/file/queryFileLog?fileId=${this.fileRowData.fileId}`)
        .then(res => {
          console.log('文件操作记录', res)

          this.fileDetailLogTable = res.data.list
        }).catch(e => {
          console.log('/file/getFileList err', e)
        })
    },


    // .post(`/file/downFile?fileId=${this.fileRowData.fileId}`,)
    downloadFile() {
      this.$request({
        method: 'post',
        url: `/file/downFile?fileId=${this.fileRowData.fileId}`,
        responseType: 'blob'
      })
        .then(res => {
          console.log('下载文件', res)
          var blob = new Blob([res.data], { type: res.headers['content-type'] })
          var downloadElement = document.createElement('a')
          var href = window.URL.createObjectURL(blob) // 创建下载的链接
          downloadElement.href = href
          downloadElement.download = 'xxx.xlsx' // 下载后文件名
          document.body.appendChild(downloadElement)
          downloadElement.click() // 点击下载
          document.body.removeChild(downloadElement) // 下载完成移除元素
          window.URL.revokeObjectURL(href) // 释放掉blob对象
          // this.fileDetailLogTable = res.data.list
        }).catch(e => {
          console.log('/file/getFileList err', e)
        })
    }



  }
}
</script>

<style scoped lang="scss">
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
