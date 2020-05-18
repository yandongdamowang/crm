<template>
  <div class="ls-upload">
    <span>
      <span @click="dialogLocalStatus = true">{{ versionData === undefined? '上传文件' :'替换版本' }}</span>
    </span>

    <el-dialog
      :visible.sync="dialogLocalStatus"
      :append-to-body="true"
      title="本地上传"
      width="40%"
      @close="dialogClose"
    >
      <el-form ref="form" label-width="120px">
        <el-tree
          ref="tree"
          :data="folderListData"
          :props="defaultProps"
          :default-expand-all="false"
          style="margin: 0 0 20px 30px"
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
        <el-form-item label="选择文件夹：">{{ folderName }}</el-form-item>

        <el-form-item v-if="versionData != undefined" label="版本备注：">
          <el-input v-model="fileRemark" placeholder="请输入备注" />
          <!-- {{ fileRemark }} -->
        </el-form-item>

        <el-form-item label="选择文件类型：">
          <el-select v-model="fileTypeId" placeholder="请选择文件类型" @change="selectChanged">
            <el-option
              v-for="(item,index) in fileTypeList"
              :key="index"
              :label="item.typeName"
              :value="item.typeId"
            />
          </el-select>
          <!-- {{ fileTypeId }} -->
        </el-form-item>

        <el-form-item label="上传文件：">
          <el-upload
            :headers="uploadHeaders"
            :action="uploadAction"
            :before-upload="beforeUpload"
            :on-success="onSuccess"
            class="upload-demo"
            drag
            multiple
          >
            <i class="el-icon-upload" />
            <div class="el-upload__text">
              将文件拖到此处，或
              <em>点击上传</em>
            </div>

            <!-- <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div> -->
          </el-upload>
        </el-form-item>

        <!--
        <el-upload
          :headers="uploadHeaders"
          :action="uploadActionVersion"
          :before-upload="beforeUpload"
          class="upload-demo"
          drag
          multiple
        >
          <i class="el-icon-upload" />
          <div class="el-upload__text">
            将文件拖到此处，或
            <em>上传版本</em>
          </div>
        </el-upload>-->

        <!-- <span slot="footer" class="dialog-footer">
        <el-button @click="dialogLocalStatus = false">取 消</el-button>
        <el-button type="primary">确 定</el-button>
        </span>-->
      </el-form>
    </el-dialog>
  </div>
</template>

<script>

import Lockr from 'lockr'

export default {
  name: 'App',


  components: {

  },
  props: {
    versionData: {
      type: Object,
      default: undefined
    },

    projectId: {
      type: Number,
      default: undefined
    },


    folderId: {
      type: Number,
      default: undefined
    },

    batchId: {
      type: String,
      default: undefined
    }

  },

  data() {
    return {
      folderListData: [],
      defaultProps: {
        children: 'list',
        label: 'folderName'
      },
      dialogLocalStatus: false,
      dialogNetdiskStatus: false,
      folderName: '',
      fileTypeId: undefined,
      fileTypeList: [],
      fileRemark: undefined,
      uploadAction: '',
      uploadActionVersion: '',
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
    console.log('versionData', this.versionData)
    console.log('batchId', this.batchId)
    this.retriveFolderList()
    this.retriveTypeList()
  },


  methods: {
    handleNodeClick(data) {
    //   ++this.menuKey
      console.log('选中的文件夹', data)
      //   console.log('workId', this.workId)
      //   this.folderSelect.batchId = data.batchId
      this.folderId = data.folderId
      this.folderName = data.folderName
      //   this.folderSelect.folderUploadPid = data.folderId
      //   this.workIdUpload = this.workId
      this.retriveFileList()
    },

    retriveTypeList() {
      this.$request
        .post(`/type/queryList?pageType=0`)
        .then(res => {
          console.log('附件类型列表', res)
          this.fileTypeList = res.data
        }).catch(e => {
          console.log('retriveTypeList err', e)
        })
    },

    localUpload() {
      console.log('workID', this.$route.params.id)

      this.versionData === undefined
        ? this.uploadAction = process.env.BASE_API + `file/upload?folderId=${this.folderId}&typeId=${this.fileTypeId}&workId=${this.$route.params.id}&batchId=${this.batchId}`
        : this.uploadAction = process.env.BASE_API + `file/changeVersion?fileId=${this.versionData.fileId}&folderId=${this.versionData.folderId}&fileRemark=${this.fileRemark}&workId=${this.$route.params.id}&batchId=${this.batchId}`

      this.dialogLocalStatus = true
      console.log('上传地址', this.uploadAction)
    },

    beforeUpload(file) {
    //   console.log(file)
      if (this.folderId == null) {
        this.$message.error('请选择上传的文件夹')
        return false
      } else if (this.fileTypeId == undefined) {
        this.$message.error('请选择文件类型')
        return false
      }
    },
    dialogClose() {
    //   this.fileTypeId = undefined
    },

    selectChanged(value) {
      if (this.versionData !== undefined && this.fileRemark === undefined) { this.$message.error('请填写备注') }
      this.fileTypeId = value
      this.localUpload()
    },
    onSuccess() {
      console.log('成功')
      this.$emit('uploadStatus', false)
      this.dialogLocalStatus = false
      this.$message.success('上传成功')
      this.fileRemark = undefined
      this.fileTypeId = undefined
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
    }



    // netdiskUpload() {
    //   this.dialogNetdiskStatus = true
    // }

    // beforeUpload(file) {
    //   const fd = new Formdata()
    //   fd.append('key', file, 'fileName')

    //   // 自己上传文件 想加什么都可以
    //   this.$request.post(this.uploadAction, fd, {

    //     headers: this.uploadHeaders
    //   })
    //   return false // 返回false不会自动上传
    // },









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
