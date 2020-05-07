<template>
  <div style="height:100%">
    <div class="ls-header">
      网盘管理
      <span class="grep">
        <!-- 1 -->
      </span>
    </div>

    <div class="ls-tree-l">
      <div class="ls-tree-search">
        <el-button type="primary" @click="createFolder(folderCreateNameInner,0)">创建</el-button>
        <el-input v-model="folderCreateNameInner" placeholder="请输入内容" style="width:200px" />
      </div>

      <div class="ls-tree-content">
        <el-tree
          ref="tree"
          :data="folderListData"
          :props="defaultProps"
          :filter-node-method="filterNode"
          :default-expand-all="false"
          class="filter-tree"
          @node-click="handleNodeClick"
          @node-contextmenu="handleContextmenu"
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

      <div v-if="tmDisplay" id="perTreeMenu" :style="{...rightMenu}" class="tree_menu">
        <ul>
          <li>
            <span @click="dialogRenameStatus = true">重命名</span>
          </li>
          <li>
            <span @click="dialogMoveStatus = true ">移动</span>
          </li>

          <li>
            <span @click="dialogCreateStatus = true">创建</span>
          </li>
          <li>
            <span @click="deleteFolder ">删除</span>
          </li>
        </ul>
      </div>
    </div>

    <div class="ls-tree-r">
      <div class="ls-tree-r-header">
        <Upload :key="menuKey" :folder-id="folderSelect.folderId" @uploadStatus="uploadStatus" />
        <el-button @click="renameFile">重命名</el-button>
        <el-button @click="dialogMoveFileStatus = true">移 动</el-button>
        <el-button @click="deleteFile">删 除</el-button>
      </div>

      <el-dialog :visible.sync="dialogMoveFileStatus" title="移动附件" width="30%">
        <el-tree
          ref="tree"
          :data="folderListData"
          :props="defaultProps"
          :filter-node-method="filterNode"
          :default-expand-all="false"
          style="margin:20px 0 0 0"
          class="filter-tree"
          @node-click="handleNodeMoveFileClick"
        >
          <span slot-scope="{node,data}">
            <span style="color:black">
              <img src="@/assets/filetype/folder.png" />
              {{ data.folderName }}
            </span>
          </span>
        </el-tree>

        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogMoveFileStatus = false">取 消</el-button>
          <el-button type="primary" @click="moveFile">确 定</el-button>
        </span>
      </el-dialog>

      <el-dialog :visible.sync="dialogRenameFileStatus" title="重命名" width="50%">
        <el-input v-model="fileRenameOld" :disabled="true" placeholder="请输入内容" style="width:300px" />
        <span>重命名</span>
        <el-input v-model="fileRenameNew" placeholder="请输入内容" style="width:300px" />

        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogRenameStatus = false">取 消</el-button>
          <el-button type="primary" @click="renameFileCommit">确 定</el-button>
        </span>
      </el-dialog>

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

          <el-table-column prop="compositionName" label="附件类型文件名" />

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

    <el-dialog :visible.sync="dialogCreateStatus" title="创建文件夹" width="15%">
      <el-input v-model="folderCreateNameOut" placeholder="请输入内容" style="width:200px" />
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogCreateStatus = false">取 消</el-button>
        <el-button type="primary" @click="createFolder(folderCreateNameOut,folderCreateNamePid)">确 定</el-button>
      </span>
    </el-dialog>

    <el-dialog :visible.sync="dialogMoveStatus" title="移动文件夹" width="30%">
      <el-input
        v-model="folderSelect.folderName"
        :disabled="true"
        placeholder="请输入内容"
        style="width:200px"
      />
      <span>移动至</span>
      <el-input v-model="folderSelect.folderMoveName" placeholder="请输入内容" style="width:200px" />

      <el-tree
        ref="tree"
        :data="folderListData"
        :props="defaultProps"
        :filter-node-method="filterNode"
        style="margin:20px 0 0 0"
        class="filter-tree"
        @node-click="handleNodeMoveClick"
      >
        <span slot-scope="{node,data}">
          <span style="color:black">
            <img src="@/assets/filetype/folder.png" />
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
      <el-input
        v-model="folderSelect.folderName"
        :disabled="true"
        placeholder="请输入内容"
        style="width:200px"
      />
      <span>重命名</span>
      <el-input v-model="folderSelect.folderReName" placeholder="请输入内容" style="width:200px" />

      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogRenameStatus = false">取 消</el-button>
        <el-button type="primary" @click="updateFolder">确 定</el-button>
      </span>
    </el-dialog>

    <el-drawer :visible.sync="drawer" :with-header="false" :destroy-on-close="true" size="70%">
      <div style="padding: 30px">
        <div class="ls-drawertitle">
          <div class="ls-drawertitle-l">
            所属文件夹：/{{ filePath }}/{{ fileSelect.compositionName }}
            <!-- <tag-index :placement="'bottom'" :task-data="taskData"> -->
            <!-- <span>
              <tag-index :placement="'bottom'">
                <div slot="editIndex">
                  <span>标签</span>
                </div>
              </tag-index>
            </span>-->
          </div>

          <!-- <div class="particulars-priority-copy">
            <template v-show="taskData.labelList.length != 0">
              <span
                v-for="(item, index) in taskData.labelList"
                :style="{'background': item.color ? item.color : '#ccc'}"
                :key="index"
                class="item-color"
              >{{ item.labelName }}</span>
            </template>
            <div class="add-tag">
              <tag-index :placement="'right'" :task-data="taskData">
                <div slot="editIndex">
                  <span class="el-icon-plus" />
                  <span class="label">标签</span>
                </div>
              </tag-index>
            </div>
          </div>-->

          <div class="ls-drawertitle-r">
            <!-- <span>
            <i class="el-icon-edit" />
          </span>&nbsp;&nbsp;&nbsp;
          <span>
            <i class="el-icon-folder-opened" />
          </span>
            &nbsp;&nbsp;&nbsp;-->
            <!-- <el-dropdown class="ls-drawertitle-dropdown">
            <span>
              <i class="el-icon-upload2" />
            </span>
            &nbsp;&nbsp;&nbsp;
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>
                <el-upload
                  ref="upload"
                  :auto-upload="false"
                  :on-success="submitUploadSuccess"
                  :on-error="submitUploadError"
                  action="https://jsonplaceholder.typicode.com/posts/"
                >
                  <div slot="trigger" size="small" type="primary">本地上传</div>
                </el-upload>
              </el-dropdown-item>

              <el-dropdown-item>
                <el-upload
                  ref="upload"
                  :auto-upload="false"
                  :on-success="submitUploadSuccess"
                  :on-error="submitUploadError"
                  action="https://jsonplaceholder.typicode.com/posts/"
                >
                  <div size="small" type="primary" @click="submitUpload">网盘上传</div>
                </el-upload>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>

          <el-dropdown class="ls-drawertitle-dropdown">
            <span>
              <i class="el-icon-more" />
            </span>

            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>转 移</el-dropdown-item>
              <el-dropdown-item>
                <span @click="deleteContract">删 除</span>
              </el-dropdown-item>
            </el-dropdown-menu>
            </el-dropdown>-->

            <!-- <el-button type="primary" @click="updateVersion">更换版本</el-button> -->
            <Upload
              :key="menuKey"
              :folder-id="folderSelect.folderId"
              :version-data="versionData"
              @uploadStatus="uploadStatus"
            />
            <el-button type="primary" @click="downloadFile">下 载</el-button>

            <span>丨</span>
            <span @click="drawer=false">
              <i class="el-icon-close" />
            </span>
          </div>
        </div>

        <div class="ls-drawerform">
          <div class="ls-drawerform-header">
            <i class="el-icon-notebook-1" />&nbsp;&nbsp;&nbsp;基本信息
          </div>

          <el-form ref="form" label-width="120px" label-position="left">
            <el-row>
              <el-col :span="8">
                <el-form-item label="创建人：">
                  <span>{{ fileSelect.createName }}</span>
                </el-form-item>

                <el-form-item label="文件名：">
                  <span>{{ fileSelect.fileName }}</span>
                </el-form-item>
              </el-col>

              <el-col :span="8">
                <el-form-item label="版本号：">
                  <span>{{ fileSelect.fileVersion }}</span>
                </el-form-item>

                <el-form-item label="文件大小：">
                  <span>{{ fileSelect.size }}</span>
                </el-form-item>
              </el-col>

              <el-col :span="8">
                <el-form-item label="文件类型：">
                  <span>{{ fileSelect.fileType }}</span>
                </el-form-item>

                <el-form-item label="文件类型名：">
                  <span>{{ fileSelect.compositionName }}</span>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
        </div>

        <related-business :all-data="allData" :margin-left="'0'" @checkInfos="checkInfos" />

        <el-tabs v-model="activeName" @tab-click="handleClick">
          <el-tab-pane label="操作日志" name="first">
            <el-table :data="fileDetailLogTable" border style="width: 100%">
              <el-table-column prop="content" label="操作记录" width />
              <el-table-column prop="createTime" label="操作时间" width="300" />
            </el-table>
          </el-tab-pane>

          <!-- :icon="activity.icon"
              :type="activity.type"
              :color="activity.color"
          :size="activity.size"-->
          <el-tab-pane label="历史版本" name="second">
            <!-- {{ historyVersionData }} -->
            <el-timeline>
              <el-timeline-item
                v-for="(activity, index) in historyVersionData"
                :key="index"
                :timestamp="activity.createTime"
              >
                <!-- {{ activity }} -->
                <div>文件名：{{ activity.oldName }}</div>
                <div>附件名：{{ activity.compositionName }}</div>
                <div>文件版本：{{ activity.fileVersion }}</div>
                <div>备注：{{ activity.fileRemark }}</div>
                <!-- <div type="primary" @click="moveFolder">确 定</div> -->
              </el-timeline-item>
            </el-timeline>
          </el-tab-pane>
        </el-tabs>
      </div>
    </el-drawer>
  </div>
</template>

<script>
//  <el-form ref="form" label-width="80px">
//     <el-form-item label="">
//       <el-input v-model="fileSelect.createName"></el-input>
//     </el-form-item>
//  </el-form>
import TagIndex from '@/views/projectManagement/components/tag/tagIndex'


export default {
  name: 'App',
  components: {
    Upload: () => import('./upload'),
    relatedBusiness: () => import('@/components/relatedBusiness'),
    TagIndex
  },

  data() {
    return {
      menuKey: 1,
      historyVersionData: [
        //       {
        //     content: '支持使用图标',
        //     timestamp: '2018-04-12 20:46',
        //     size: 'large',
        //     type: 'primary',
        //     icon: 'el-icon-more'
        //   }, {
        //     content: '支持自定义颜色',
        //     timestamp: '2018-04-03 20:46',
        //     color: '#0bbd87'
        //   }, {
        //     content: '支持自定义尺寸',
        //     timestamp: '2018-04-03 20:46',
        //     size: 'large'
        //   }, {
        //     content: '默认样式的节点',
        //     timestamp: '2018-04-03 20:46'
        //   }
      ],

      downloadAction: process.env.BASE_API + `/file/downFile?fileId=72`,
      activeName: 'first',
      pageCurrent: 1,
      pageSize: 10,
      pageTotal: 0,
      allData: {},
      relevanceAll: {},
      drawer: false,
      dialogCreateStatus: false,
      dialogMoveStatus: false,
      dialogRenameStatus: false,
      dialogRenameFileStatus: false,
      dialogMoveFileStatus: false,
      folderCreateNameOut: undefined,
      folderCreateNameInner: undefined,
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
        folderMovePid: '',
        folderUploadPid: '',
        folderMoveName: '',
        folderReName: '',
        folderPid: undefined
      },
      fileSelect: {
        fileIds: undefined
      },
      fileSelectData: [],
      fileRenameOld: undefined,
      fileRenameNew: undefined,
      fileMovefolderId: undefined,
      fileDetailLogTable: [],
      filePath: '',
      tmDisplay: false,
      rightMenu: {},
      filterText: '',
      folderListData: [],
      defaultProps: {
        children: 'list',
        label: 'folderName'
      },
      versionData: {}
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

    handleClick() {

    },

    filterNode(value, data) {
      if (!value) return true
      return data.label.indexOf(value) !== -1
    },



    handleNodeClick(data) {
      ++this.menuKey
      console.log('选中的文件夹', data)
      this.folderSelect.batchId = data.batchId
      this.folderSelect.folderId = data.folderId
      this.folderSelect.folderUploadPid = data.folderId
      this.retriveFileList()
    },


    handleNodeMoveClick(data) {
      console.log('选中移动的文件夹', data)
      this.folderMoveName = data.folderName
      this.folderSelect.batchId = data.batchId
      this.folderSelect.folderMovePid = data.folderPid
    },
    handleNodeMoveFileClick(data) {
      console.log('选中移动的文件', data)
      this.fileMovefolderId = data.folderId
    },


    handleContextmenu(e, data, node, comp) {
      console.log('e:', e, 'data', data)
      this.folderSelect.batchId = data.batchId
      this.folderSelect.folderId = data.folderId
      this.folderSelect.folderPid = data.folderPid
      this.folderSelect.folderName = data.folderName
      this.folderCreateNamePid = data.folderId

      this.rightMenu = { top: e.pageY + 'px', left: e.pageX + 'px' }
      this.tmDisplay = true
      const self = this
      document.onclick = function(ev) {
        if (ev.target !== document.getElementById('perTreeMenu')) {
          self.tmDisplay = false
        }
      }
    },

    handleSelectionChange(val) {
      this.fileSelectData = val

    //   val.forEach((item, index) => {
    //     this.fileSelectData. = item.fileId
    //     console.log('选择的附件', this.fileSelectData.)
    //   })
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


    uploadStatus(status) {
      console.log('关闭弹窗', status)
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


    createFolder(folderCreateName, folderCreateNamePid) {
      folderCreateName === undefined ? this.$message.error('请输入文件名')
        : this.$request
          .post(`/folder/createFolder?folderName=${folderCreateName}&folderCode=CONTRACTUAL&folderPid=${folderCreateNamePid}&batchId=${this.batchId}`)
          .then(res => {
            console.log(res)
            this.folderName = ''
            this.folderListData = res.data

            this.dialogCreateStatus = false
            this.folderCreateNameOut = ''
            this.folderCreateNameInner = ''
            this.retriveFolderList()
          }).catch(e => {
            console.log('retriveFolderList err', e)
          })
    },


    deleteFolder() {
      this.$request
        .post(`/folder/deleteFolder?folderId=${this.folderSelect.folderId}`)
        .then(res => {
          console.log('删除文件夹', res)

          this.retriveFolderList()
        }).catch(e => {
          console.log('删除文件夹 err', e)
        })
    },

    moveFolder() {
      this.$request
        .post(`/folder/mobileFolder?folderId=${this.folderSelect.folderId}&folderPid=${this.folderSelect.folderMovePid}`)
        .then(res => {
          console.log('移动文件夹', res)
          this.retriveFolderList()
        }).catch(e => {
          console.log('移动文件夹 err', e)
        })
    },



    updateFolder() {
      this.$request
        .post(`/folder/renameFolder?folderId=${this.folderSelect.folderId}&folderName=${this.folderSelect.folderReName}`)
        .then(res => {
          console.log('updateFolder', res)
          this.dialogRenameStatus = false
          this.folderSelect.folderReName = undefined
          this.retriveFolderList()
        }).catch(e => {
          console.log('retriveFolderList err', e)
        })
    },


    // &batchId=${this.folderSelect.batchId}
    retriveFileList() {
      this.$request
        .post(`/file/getFileList?folderId=${this.folderSelect.folderId}&delFlag=0&orderBy=2,`, {
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


    retriveFileDetail(row) {
      this.fileRowData = row
      this.versionData = row
      this.$request
        .post(`/file/queryFileInfo?fileId=${row.fileId}`)
        .then(res => {
          console.log('文件详情', res)
          this.drawer = true
          this.fileSelect = res.data
          this.historyVersionData = res.data.historyFileList
          this.$request
            .post(`/file/queryFolderPathById?fileId=${row.fileId}`)
            .then(res => {
              console.log('文件路径', res.data)
              this.filePath = res.data
            })
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


    renameFile() {
      this.dialogRenameFileStatus = true
      this.fileRenameOld = this.fileSelectData[0].compositionName
    },

    renameFileCommit() {
      if (this.fileSelectData.length > 1) {
        this.$message.error('请选择一个文件')
      } else {
        // console.log(this.fileSelectData[0])
        this.$request
          .post(`/file/renameFile?fileId=${this.fileSelectData[0].fileId}&compositionName=${this.fileRenameNew}`)
          .then(res => {
            console.log('文件重命名', res)
            this.$message.success('重命名成功')
            this.dialogRenameFileStatus = false
            this.retriveFileList()
          }).catch(e => {
            console.log('renameFile err', e)
          })
      }
    },



    moveFile() {
      const fileIds = []
      this.fileSelectData.forEach((item, index) => {
        fileIds.push(item.fileId)
      })
      this.fileSelect.fileIds = fileIds.join(',')
      console.log('移动附件', fileIds.join(','))

      this.$request

        .post(`/file/mobileFiles?fileIds=${this.fileSelect.fileIds}&folderId=${this.fileMovefolderId}`)
        .then(res => {
          console.log('文件删除', res)
          this.$message.success('移动成功')
          this.dialogMoveFileStatus = false
          this.retriveFileList()
        }).catch(e => {
          console.log('/file/getFileList err', e)
        })
    },




    deleteFile() {
      const fileIds = []
      this.fileSelectData.forEach((item, index) => {
        fileIds.push(item.fileId)
      })
      this.fileSelect.fileIds = fileIds.join(',')
      console.log('加入回收站', fileIds.join(','))
      this.$request
        .post(`/file/addRecycleByIds?fileIds=${this.fileSelect.fileIds}`)
        .then(res => {
          console.log('文件删除', res)
          this.$message.success('删除成功')
          this.retriveFileList()
        }).catch(e => {
          console.log('/file/getFileList err', e)
        })
    },
    // deleteFile() {
    //   this.$request
    //     .post(`/file/removeById?fileId=${this.fileSelect.fileId}`)
    //     .then(res => {
    //       console.log('文件删除', res)
    //       this.$message.success('删除成功')
    //       this.retriveFolderList()
    //     }).catch(e => {
    //       console.log('/file/getFileList err', e)
    //     })
    // },

    downloadFile() {
      if (this.fileRowData.fileId == null) {
        alert(123)
      } else {
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
    },

    downloadMultiFile() {
      if (this.fileRowData.fileId == null) {
        alert(123)
      } else {
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
::-webkit-scrollbar-track-piece {
  background: transparent !important;
}

.ls-tree-l {
  background: white;
  height: calc(100% - 40px);
  width: 20%;
  float: left;
  overflow: hidden;
  margin: 0px 10px 0 10px;
  padding: 20px 20px 10px 20px;
  .ls-tree-search {
    margin: 0px 10px 15px 10px;
  }
  .ls-tree-content {
    height: calc(100% - 40px);

    overflow-y: scroll;
  }
}

.ls-tree-r {
  background: white;
  float: right;
  width: 78%;
  height: calc(100% - 40px);
  margin: 0px 10px 0 0px;
  .ls-tree-r-header {
    width: 100%;
    height: 60px;
    line-height: 60px;
    margin: 10px 10px 0 20px;
  }
  .ls-tree-r-content {
    height: calc(100% - 80px);

    padding: 0px 20px 20px 20px;
  }
}
</style>
