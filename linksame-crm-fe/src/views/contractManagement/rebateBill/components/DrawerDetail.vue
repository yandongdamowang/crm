<template>
  <div>
    <div class="ls-drawertitle">
      <div class="ls-drawertitle-l">{{ rebateBillData.receivableType }}</div>
      <div class="ls-drawertitle-r">
        <el-dropdown class="ls-drawertitle-dropdown">
          <span>
            <i class="el-icon-more" />
          </span>

          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>
              <Upload v-if="batchId != ''" :key="menuKey" :batch-id="batchId" />
            </el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
        <!-- @uploadStatus="uploadStatus" -->

        <span>丨</span>
        <span @click="closeDrawer">
          <i class="el-icon-close" />
        </span>
      </div>
    </div>

    <div class="ls-drawerform">
      <div class="ls-drawerform-header">
        <i class="el-icon-notebook-1" />&nbsp;&nbsp;&nbsp;账户信息
      </div>

      <el-form ref="form" label-position="left">
        <el-row>
          <el-col :span="8">
            <el-form-item label="收款账户：">
              <span>{{ rebateBillData.collectionAccount }}</span>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="开户行：">
              <span>{{ rebateBillData.collectionBank }}</span>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="收款账号：">
              <span>{{ rebateBillData.collectionNumber }}</span>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>

      <div class="ls-drawerform-header">
        <i class="el-icon-notebook-1" />
        &nbsp;&nbsp;&nbsp;回款信息
        <el-form ref="form" label-width="120px" label-position="left">
          <el-row>
            <el-col :span="8">
              <el-form-item label="款项：">
                <span>{{ rebateBillData.receivableType }}</span>
              </el-form-item>
              <el-form-item label="回款时间：">
                <span>{{ rebateBillData.collectingTime }}</span>
              </el-form-item>
              <el-form-item label="备注：">
                <span>{{ rebateBillData.remark }}</span>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="客户：">
                <span>{{ rebateBillData.money }}</span>
              </el-form-item>
              <el-form-item label="付款方式：">
                <span>{{ rebateBillData.paymentMethod }}</span>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="回款金额：">
                <span>{{ '￥' + rebateBillData.receivableAmount }}</span>
              </el-form-item>
              <el-form-item label="经办人：">
                <span>{{ rebateBillData.agent }}</span>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </div>

      <!-- <div class="ls-drawerform-header">
        <i class="el-icon-paperclip" />&nbsp;&nbsp;&nbsp;关联业务
      </div>-->
    </div>

    <div class="ls-drawerform-header">
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="附件" name="annex">
          <el-table :data="fileTableData" style="height: 92%">
            >
            <el-table-column fixed prop="oldName" label="文件名">
              <template slot-scope="scope">
                <span @click="retriveFileDetail(scope.row)">
                  <img v-if="scope.row.suffix == 'pdf'" src="@/assets/filetype/pdf.png" />
                  <img v-else-if="scope.row.suffix == 'dwg'" src="@/assets/filetype/dwg.png" />
                  <img
                    v-else-if="scope.row.suffix == 'zip' || scope.row.suffix == 'rar'"
                    src="@/assets/filetype/zip.png"
                  />
                  <img
                    v-else-if="scope.row.suffix == 'xls' || scope.row.suffix == 'xlsx'"
                    src="@/assets/filetype/excel.png"
                  />
                  <img
                    v-else-if="scope.row.suffix == 'png' || scope.row.suffix == 'jpg' "
                    src="@/assets/filetype/jpg.png"
                  />
                  <img
                    v-else-if="scope.row.suffix == 'docx' || scope.row.suffix == 'doc'"
                    src="@/assets/filetype/word.png"
                  />
                  <img v-else src="@/assets/filetype/unknown.png" />
                  {{ scope.row.oldName }}
                </span>
              </template>
            </el-table-column>

            <el-table-column prop="compositionName" label="附件类型文件名" />

            <el-table-column prop="size" label="大小" width="100" />

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
        </el-tab-pane>

        <el-tab-pane label="操作记录" name="record">
          <el-table :data="recordTableData" style="width: 100%">
            <el-table-column prop="createTime" label="操作时间" />
            <el-table-column prop="content" label="记录" />
            <el-table-column prop="realname" label="操作人员" />
          </el-table>
        </el-tab-pane>

        <el-tab-pane label="审批记录" name="approve">
          <el-table :data="examineTableData" style="width: 100%">
            <el-table-column prop="realname" label="审核人" />
            <el-table-column prop="remarks" label="备注" />
            <el-table-column prop="examineTime" label="审核时间" />
          </el-table>
        </el-tab-pane>
      </el-tabs>
    </div>
    <!-- 弹框 -->
    <!-- <el-dialog
      :visible.sync="dialogStatus"
      :destroy-on-close="true"
      :modal="false"
      title="修改回款"
      width="50%"
    >
      <component ref="updateDialog" :is="dialogComponents" :data="rebateBillDetailData" />

      <span slot="footer">
        <el-button @click="dialogStatus = false">取 消</el-button>
        <el-button type="primary" @click="commitData">确 定</el-button>
      </span>
    </el-dialog>-->
  </div>
</template>

<script>
import { rebateBillDetail } from '@/api/contractManagement/rebateBill'

export default {
  name: 'DialogPayment',
  components: {
    Upload: () => import('@/views/annexManagement/netdisk/components/Upload')
    // DialogCreate: () => import('./DialogCreate')
  },
  props: {
    drawerData: {
      type: Number,
      default: undefined
    },
    rowData: {
      type: Object,
      default: undefined
    },
    drawerStatus: {
      type: Boolean,
      default: undefined
    }
  },

  data() {
    return {

      menuKey: 1,
      pageCurrent: 1,
      pageSize: 10,
      pageTotal: 0,

      batchId: '',
      recordTableData: [],
      fileTableData: [],


      rebateBillData: {},
      dialogStatus: false,
      activeName: 'annex',
      dialogComponents: '',
      rebateBillDetailData: '',
      formData: {
        a: '',
        b: '',
        c: ''
      }
    }
  },
  computed: {
  },



  mounted() {
    console.log('receivableRecordsId', this.drawerData)
    this.retriveRebateBillDetail()
    console.log('batchId', this.batchId)
    this.retriveRecordDetail()

    ++this.menuKey
  },
  methods: {
    closeDrawer() {
      this.$emit('drawerStatus', false)
    },

    // commitData() {
    //   this.$refs.updateDialog.createContact()
    //   this.dialogVisible = false
    // },


    // updateRebate() {
    //   this.dialogStatus = true
    //   this.dialogComponents = 'DialogCreate'
    // },
    deleteContract() {

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


    retriveRecordDetail() {
      this.$request.post('/CrmRecord/queryRecordList', {
        actionId: this.drawerData,
        types: 10
      })
        .then(res => {
          console.log('操作记录', res)
          this.recordTableData = res.data
        })
        .catch(() => {

        })
    },


    retriveRebateBillDetail() {
      rebateBillDetail({
        receivableRecordsId: this.drawerData
      })
        .then(res => {
          console.log('回款详情', res)
          this.rebateBillData = res.data
          this.rebateBillDetailData = res.data
          this.batchId = this.rebateBillDetailData.batchId
          this.$request
            .post(`/file/getFileList?batchId=${this.batchId}&delFlag=0&orderBy=2`, {
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
        })
        .catch((e) => {
          console.log('rebateBillDetail', e)
        })
    }


  }
}
</script>

<style lang="scss" scoped>
.button {
  text-align: right;
}
.logo {
  display: inline-block;
  height: 60px;
  width: 60px;
  border-radius: 3px;
  background: rgb(131, 131, 243);
  text-align: center;
}
.title {
  font-size: 20px;
  line-height: 60px;
}
</style>
