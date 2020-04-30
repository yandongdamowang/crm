<template>
  <div class="ls-upload">
    <el-dropdown>
      <el-button type="primary">上传文件</el-button>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item @click.native="localUpload">本地上传</el-dropdown-item>
        <el-dropdown-item @click.native="netdiskUpload">网盘上传</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
    <!-- :before-upload="beforeUpload" -->
    <el-dialog :visible.sync="dialogLocalStatus" title="本地上传" width="30%">
      <el-upload :headers="uploadHeaders" :action="uploadAction" class="upload-demo" drag multiple>
        <i class="el-icon-upload" />
        <div class="el-upload__text">
          将文件拖到此处，或
          <em>点击上传</em>
        </div>
        <!-- <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div> -->
      </el-upload>

      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogLocalStatus = false">取 消</el-button>
        <el-button type="primary">确 定</el-button>
      </span>
    </el-dialog>

    <el-dialog :visible.sync="dialogNetdiskStatus" title="网盘上传" width="30%">
      <el-upload :headers="uploadHeaders" :action="uploadAction" class="upload-demo" drag multiple>
        <i class="el-icon-upload" />
        <div class="el-upload__text">
          将文件拖到此处，或
          <em>点击上传</em>
        </div>
        <!-- <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div> -->
      </el-upload>

      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogLocalStatus = false">取 消</el-button>
        <el-button type="primary">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>

import Lockr from 'lockr'

export default {
  name: 'App',
  components: {

  },

  data() {
    return {
      dialogLocalStatus: false,
      dialogNetdiskStatus: false,
      uploadAction: process.env.BASE_API + `/file/upload?batchId=b9df6992915b42e19b719fcd70b12836&folderId=16&workId=1&typeId=9`,
      uploadHeaders: {
        'Admin-Token': Lockr.get('Admin-Token')
        // 'Content-Type': 'multipart/form-data;',
        // 'Accept': '*/*',
        // 'Accept-Encoding': 'gzip, deflate, br'

      }

    }
  },
  watch: {

  },
  created() {

  },

  mounted() {
    //   console.log('tokne', Lockr.get('Admin-Token'))
  },


  methods: {

    // beforeUpload(file) {
    //   const fd = new Formdata()
    //   fd.append('key', file, 'fileName')

    //   // 自己上传文件 想加什么都可以
    //   this.$request.post(this.uploadAction, fd, {

    //     headers: this.uploadHeaders
    //   })
    //   return false // 返回false不会自动上传
    // },



    localUpload() {
      this.dialogLocalStatus = true
    },
    netdiskUpload() {
      this.dialogNetdiskStatus = true
    }







    // getDetail() {
    //   this.$request.post('/work/getWorkById', {
    //     workId: this.$route.params.id
    //   })
    //     .then(res => {
    //       this.batchId = res.data.batchId
    //       //   console.log('batchId', this.batchId)
    //       this.retriveFolderList()
    //     })
    //     .catch(() => {})
    // },



    // filterNode(value, data) {
    //   if (!value) return true
    //   return data.label.indexOf(value) !== -1
    // },



    // handleNodeClick(data) {
    //   console.log('handleNodeClick', data)
    //   this.folderSelect.batchId = data.batchId
    //   this.folderSelect.folderId = data.folderId
    //   this.folderSelect.folderPid = data.folderPid
    //   this.folderSelect.folderName = data.folderName
    //   this.retriveFileList(data.batchId)
    // },


    // retriveFolderList() {
    //   this.$request
    //     .post(`/folder/queryFolder?batchId=${this.batchId}`)
    //     .then(res => {
    //       console.log(res)
    //       this.folderListData = res.data
    //     }).catch(e => {
    //       console.log('retriveFolderList err', e)
    //     })
    // },

    // createFolder() {
    //   this.$request
    //     .post(`/folder/createFolder?folderName=${this.folderCreateName}&folderCode=CONTRACTUAL&folderPid=0&batchId=${this.batchId}`)
    //     .then(res => {
    //       console.log(res)
    //       this.folderName = ''
    //       this.folderListData = res.data
    //       this.retriveFolderList()
    //     }).catch(e => {
    //       console.log('retriveFolderList err', e)
    //     })
    // },
    // deleteFolder() {
    //   this.$request
    //     .post(`/folder/deleteFolder?folderId=${this.folderSelect.folderId}`)
    //     .then(res => {
    //       console.log('deleteFolder', res)
    //       this.retriveFolderList()
    //     }).catch(e => {
    //       console.log('retriveFolderList err', e)
    //     })
    // },

    // moveFolder(id, pid) {
    //   this.$request
    //     .post(`/folder/mobileFolder?folderId=${id}&folderPid=${pid}`)
    //     .then(res => {
    //       console.log('moveFolder', res)

    //       this.retriveFolderList()
    //     }).catch(e => {
    //       console.log('moveFolder err', e)
    //     })
    // },



    // updateFolder() {
    //   this.$request
    //     .post(`/folder/renameFolder?folderId=${this.folderSelect.folderId}&folderName=${this.folderSelect.folderReName}`)
    //     .then(res => {
    //       console.log('updateFolder', res)
    //       this.dialogRenameStatus = false
    //       this.retriveFolderList()
    //     }).catch(e => {
    //       console.log('retriveFolderList err', e)
    //     })
    // },



    // retriveFileList(batchId) {
    //   this.$request
    //     .post(`/file/getFileList?batchId=${batchId}&orderBy=2`)
    //     .then(res => {
    //       console.log('/file/getFileList', res)
    //       this.fileTableData = res.data.list
    //     }).catch(e => {
    //       console.log('/file/getFileList err', e)
    //     })
    // }




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

.ls-upload {
  display: inline;
}

.ls-tree-l {
  background: white;
  height: 100%;
  width: 20%;
  float: left;
  margin: 0 20px 0 20px;
}

.ls-tree-r {
  background: white;
  float: right;
  width: 76%;
  height: 100%;
  margin: 0 20px 0 0;
  .ls-tree-r-header {
    width: 100%;
    height: 60px;
    line-height: 60px;
    margin: 0 0 0 20px;
  }
  .ls-tree-r-content {
    height: calc(100% - 80px);
    // overflow-y: scroll;
    padding: 40px 20px 20px 20px;
  }
}
</style>
