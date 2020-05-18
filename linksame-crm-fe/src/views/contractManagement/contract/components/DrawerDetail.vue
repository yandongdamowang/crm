<template>
  <div>
    <div class="ls-drawertitle">
      <div class="ls-drawertitle-l">{{ pmpContractData.contractNumber }}</div>
      <div class="ls-drawertitle-r">
        <el-dropdown class="ls-drawertitle-dropdown">
          <span>
            <i class="el-icon-more" />
          </span>

          <el-dropdown-menu slot="dropdown">
            <!-- <el-dropdown-item>转 移</el-dropdown-item> -->

            <el-dropdown-item>
              <Upload v-if="batchId != ''" :key="menuKey" :batch-id="batchId" />
            </el-dropdown-item>

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
            <el-form-item label="合同类型：">
              <span>1191111-REC-工程-S1-珠海一键</span>
            </el-form-item>

            <el-form-item label="合同采购时间：">
              <span>{{ pmpContractData.contractPurchaseStartTime }}</span>
            </el-form-item>
            <caption />

            <el-form-item label="合同编号：">
              <span>{{ pmpContractData.contractNumber }}</span>
            </el-form-item>

            <el-form-item label="经办人：">
              <span>{{ pmpContractData.agent }}</span>
            </el-form-item>

            <el-form-item label="里程碑：">
              <span>{{ pmpContractData.agent }}</span>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="合同名称：">
              <span>{{ pmpContractData.contractName }}</span>
            </el-form-item>

            <el-form-item label="承包商：">
              <span>{{ pmpContractData.supplierId }}</span>
            </el-form-item>

            <el-form-item label="DRP采购单号：">
              <span>{{ pmpContractData.purchaseOrderNumber }}</span>
            </el-form-item>

            <el-form-item label="合同到期时间：">
              <span>{{ pmpContractData.contractPurchaseEndTime }}</span>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="签署时间：">
              <span>{{ pmpContractData.actualSigningTime }}</span>
            </el-form-item>

            <el-form-item label="合同金额：">
              <span>{{ pmpContractData.money }}</span>
            </el-form-item>

            <el-form-item label="合同开始时间：">
              <span>{{ pmpContractData.contractPurchaseStartTime }}</span>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </div>
    <div class="ls-drawerbox">
      <el-tabs v-model="activeName">
        <el-tab-pane label="附件" name="annex">
          <el-table :data="fileTableData" style="height: 50%">
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

        <el-tab-pane label="付款明细" name="first">
          <el-table :data="paymentTableData" style="width: 100%">
            <el-table-column prop="paymentTime" label="预计付款时间" width="180" />
            <el-table-column prop="paymentClause" label="款项" width="180" />
            <el-table-column prop="advanceRatio" label="比例" />
            <el-table-column prop="amountAdvanced" label="金额" />
            <el-table-column prop="practicalRatio" label="实际支付比例" />
            <el-table-column prop="f" label="实际支付金额" />
            <el-table-column prop="g" label="关联项目任务" />
            <el-table-column prop="tradeStatus" label="状态" />
            <el-table-column prop="remark" label="备注" />
          </el-table>
        </el-tab-pane>

        <el-tab-pane label="回款信息" name="third">
          <el-table :data="receivableTableData" style="width: 100%">
            <el-table-column prop="collectingCompany" label="客户" />
            <el-table-column prop="collectionAccount" label="收款账户" />
            <el-table-column prop="collectionBank" label="开户行" width="150" />
            <el-table-column prop="collectionNumber" label="收款银行卡号" width="150" />
            <el-table-column prop="receivableAmount" label="回款金额" width="150" />
            <el-table-column prop="createdTime" label="回款时间" width="150" />
            <el-table-column prop="paymentMethod" label="付款方式" width="150" />
            <el-table-column prop="agent" label="经办人" width="150" />
            <el-table-column prop="remark" label="备注" width="150" />
          </el-table>
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
</template>

<script>
import { contactDetail, contactDelete } from '@/api/contractManagement/contacts'
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
      pageSize: 5,
      pageTotal: 0,
      pmpContractData: {},
      batchId: '',
      activeName: 'annex',
      paymentTableData: [],
      receivableTableData: [],
      recordTableData: [],
      fileTableData: [],
      examineTableData: []


    }
  },
  computed: {
  },

  //   watch: {
  //     drawerData() {
  //       console.log(123, this.drawerData)
  //     }
  //   },

  mounted() {
    this.retriveContactDetail()
    this.retrivePaymentDetail()
    this.retriveReceivableDetail()
    this.retriveRecordDetail()
    this.retriveExamineDetail()
    ++this.menuKey
    console.log('rowData', this.rowData)
  },
  methods: {
    closeDrawer() {
      this.$emit('drawerStatus', false)
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


    deleteContract() {
      this.$confirm('此操作将永久删除该合同, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        contactDelete({
          contractIds: this.drawerData

        })
          .then(res => {
            this.$message({
              type: 'success',
              message: '删除成功!'
            })
            console.log(res)

            this.$router.push('/contract/contract')
            //   this.contactListData = res.data.list
            //   this.contactListSubData = res.data.list
          })
          .catch(() => {

          })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消操作'
        })
      })
    },







    retriveContactDetail() {
      contactDetail({
        contractId: this.drawerData
      })
        .then(res => {
          console.log('合同详情', res)
          this.pmpContractData = res.pmpContract
          this.batchId = this.pmpContractData.batchId

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
        .catch(() => {

        })
    },


    retrivePaymentDetail() {
      this.$request.post('/pmpContractPayment/queryPaymentDetail', {
        contractId: this.drawerData
      })
        .then(res => {
          console.log('付款明细', res)
          this.paymentTableData = res.paymentDetails
        })
        .catch(() => {

        })
    },



    retriveReceivableDetail() {
      this.$request.post('/pmpReceivableRecords/queryByContractId', {
        contractId: this.drawerData
      })
        .then(res => {
          console.log('回款明细', res)
          this.receivableTableData = res.data
        })
        .catch(() => {

        })
    },

    retriveRecordDetail() {
      this.$request.post('/CrmRecord/queryRecordList', {
        actionId: this.drawerData,
        types: 6
      })
        .then(res => {
          console.log('操作记录', res)
          this.recordTableData = res.data
        })
        .catch(() => {

        })
    },

    retriveExamineDetail() {
      this.$request.post('/examineRecord/queryExamineLogList', {
        recordId: this.rowData.examineRecordId
      })
        .then(res => {
          console.log('审批记录', res)
          this.examineTableData = res.data
        })
        .catch(() => {

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
</style>
