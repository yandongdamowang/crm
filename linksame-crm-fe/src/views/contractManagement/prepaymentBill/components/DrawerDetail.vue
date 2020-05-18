<template>
  <div>
    <div class="ls-drawertitle">
      <div class="ls-drawertitle-l">{{ pmpPrepaymentData.contractNumber }}</div>
      <div class="ls-drawertitle-r">
        <el-dropdown class="ls-drawertitle-dropdown">
          <span>
            <i class="el-icon-more" />
          </span>

          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>
              <el-dropdown class="ls-drawertitle-dropdown" @command="handleCommand">
                <span>
                  <!-- <i class="el-icon-folder-opened" /> -->
                  优先级
                </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item :command="1">高</el-dropdown-item>
                  <el-dropdown-item :command="2">中</el-dropdown-item>
                  <el-dropdown-item :command="3">低</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </el-dropdown-item>

            <!-- <el-dropdown-item>转 移</el-dropdown-item> -->
            <el-dropdown-item>
              <Upload v-if="batchId != ''" :key="menuKey" :batch-id="batchId" />
            </el-dropdown-item>
            <!-- @uploadStatus="uploadStatus" -->

            <el-dropdown-item>
              <!-- <span @click="deleteContract">删 除</span> -->
            </el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>

        <span>丨</span>
        <span @click="closeDrawer">
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
            <el-form-item label="合同编号：">
              <span>{{ pmpPrepaymentData.contractNumber }}</span>
            </el-form-item>

            <el-form-item label="预付比例：">
              <span>{{ pmpPrepaymentData.contractName }}</span>
            </el-form-item>

            <el-form-item label="承包商：">
              <span>{{ pmpPrepaymentData.supplierName }}</span>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="合同金额：">
              <span>{{ '￥' + pmpPrepaymentData.money }}</span>
            </el-form-item>

            <el-form-item label="预付金额：">
              <span>{{ '￥' + pmpPrepaymentData.contractMoney }}</span>
            </el-form-item>

            <el-form-item label="业主：">
              <span>{{ pmpPrepaymentData.proprietor }}</span>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="款项：">
              <span>{{ pmpPrepaymentData.paymentName }}</span>
            </el-form-item>

            <el-form-item label="计划付款日期：">
              <span>{{ pmpPrepaymentData.paymentNode }}</span>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>

      <div class="ls-drawerform-header">
        <i class="el-icon-notebook-1" />
        &nbsp;&nbsp;&nbsp;优先级
        <div style="margin: 20px 0 0 20px">
          <span v-if="pmpPrepaymentData.priority == '1'">
            <el-tag type="danger">高</el-tag>
          </span>
          <span v-else-if="pmpPrepaymentData.priority == '2'">
            <el-tag type="warning">中</el-tag>
          </span>
          <span v-else-if="pmpPrepaymentData.priority == '3'">
            <el-tag type="success">低</el-tag>
          </span>

          <!-- <el-tag type="success">标签二</el-tag>
          <el-tag type="info">标签三</el-tag>
          <el-tag type="warning">标签四</el-tag>
          <el-tag type="danger">标签五</el-tag>-->
        </div>
      </div>

      <div class="ls-drawerform-header">
        <i class="el-icon-paperclip" />&nbsp;&nbsp;&nbsp;关联业务
      </div>

      <div class="ls-drawerform-header">
        <i class="el-icon-paperclip" />
        &nbsp;&nbsp;&nbsp;附件
        <el-tabs v-model="activeName">
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
    </div>
  </div>
</template>

<script>
import { prepaymentBillDetail, prepaymentSetPriority } from '@/api/contractManagement/prepaymentBill'
export default {
  name: 'DialogPayment',
  components: {
    Upload: () => import('@/views/annexManagement/netdisk/components/Upload')
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
      pmpPrepaymentData: {},
      recordTableData: [],
      fileTableData: [],
      activeName: 'annex',
      batchId: '',
      examineTableData: [],
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
    console.log(123, this.rowData)
    this.retrivePrepaymentBillDetail(this.drawerData)
    this.retriveRecordDetail()
    this.retriveExamineDetail()
    ++this.menuKey
  },
  methods: {
    closeDrawer() {
      this.$emit('drawerStatus', false)
    },


    uploadStatus(status) {
      console.log('关闭弹窗', status)
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


    handleCommand(command) {
      prepaymentSetPriority({
        billId: this.drawerData,
        priority: command
      }).then(res => {
        // this.pmpPrepaymentData.priority = command
        this.retrivePrepaymentBillDetail(this.drawerData)
        console.log(res)
      })
        .catch((e) => {
          console.log('prepaymentSetPriority', e)
        })
    //   this.$message('click on item ' + command)
    },




    retriveFileList() {
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
    },


    retrivePrepaymentBillDetail() {
      prepaymentBillDetail({
        billId: this.drawerData
      })
        .then(res => {
          this.pmpPrepaymentData = res.data
          //   this.contactListSubData = res.data.list
          this.batchId = this.pmpPrepaymentData.batchId
          this.retriveFileList()
          console.log('预付款账单详情', this.pmpPrepaymentData)
        })
        .catch((e) => {
          console.log(e)
        })
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
    retriveExamineDetail() {
      this.rowData.examineRecordId == null
        ? this.$request.post('/examineRecord/queryExamineLogList', {
          recordId: this.rowData.examineRecordId
        })
          .then(res => {
            console.log('审批记录', res)
            this.examineTableData = res.data
          })
          .catch(() => {

          }) : this.$message.error('examineRecordId 为 null')
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
