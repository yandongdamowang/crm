<template>
  <div style="height:100%">
    <div class="ls-header">
      预付款账单
      <div class="ls-header-r">
        <el-select v-model="grep" placeholder="选择项目">
          <el-option label="项目一" value="shanghai" />
          <el-option label="项目二" value="beijing" />
        </el-select>
      </div>
    </div>

    <div class="ls-box">
      <el-form ref="form" :inline="true">
        <el-form-item label="合同编号：">
          <el-input v-model="search.contractNumber" placeholder="请输入内容" />
        </el-form-item>

        <el-form-item label="客户：">
          <el-input v-model="search.supplierId" placeholder="请输入内容" />
        </el-form-item>

        <el-form-item>
          <span>
            <el-button type="primary" @click="retrivePrepaymentBillList">查询</el-button>
          </span>
          <span>
            <el-button @click="searchReset">重置</el-button>
          </span>
        </el-form-item>
      </el-form>

      <el-table
        :data="prepaymentBillListData"
        :header-cell-style="{background:'#F5F7FA',color:'#606266'}"
        border
        height="80%"
        style="margin: 20px 0 0 0"
      >
        <!-- style="width: 100%; margin:20px 0 0 0" -->

        <el-table-column type="index" label="序号" width="55" />
        <el-table-column prop="contractNumber" label="合同编号" width="150">
          <template slot-scope="scope">
            <el-button
              type="text"
              size="small"
              @click="drawerDetail(scope.row)"
            >{{ scope.row.contractNumber }}</el-button>
          </template>
        </el-table-column>
        <el-table-column prop="paymentName" label="款项" />
        <el-table-column prop="customerName" label="客户/承包商" />
        <el-table-column prop="money" label="预付金额" />
        <el-table-column prop="costPercentage" label="预付比例" width="150" />
        <el-table-column prop="paymentNode" label="计划付款时间" width="150" />
        <!-- <el-table-column
        prop="purchaseOrderNumber"
        label="关联任务"
        />-->
        <el-table-column prop="priority" label="优先级" align="center">
          <template slot-scope="scope">
            <!-- {{ if(scope.row.priority =}} -->
            <span v-if="scope.row.priority == '1'">
              <el-tag type="danger">高</el-tag>
            </span>
            <span v-else-if="scope.row.priority == '2'">
              <el-tag type="warning">中</el-tag>
            </span>
            <span v-else-if="scope.row.priority == '3'">
              <el-tag type="success">低</el-tag>
            </span>
          </template>
        </el-table-column>
        <el-table-column prop="tradeStatus" label="状态" align="center">
          <template slot-scope="scope">
            <!-- "tradeStatus": "2",     交易状态   2 待交易 3 带审批 4 延期 -->
            <span v-if="scope.row.tradeStatus == '1'">
              <el-tag type="success">交易完成</el-tag>
            </span>
            <span v-else-if="scope.row.tradeStatus == '2'">
              <el-tag type="warning">待交易</el-tag>
            </span>
            <span v-else-if="scope.row.tradeStatus == '3'">
              <el-tag type="danger">待审批</el-tag>
            </span>
            <span v-else-if="scope.row.tradeStatus == '4'">
              <el-tag type="info">延期</el-tag>
            </span>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button type="text" size="small" @click="dialogPaymentApply(scope.row)">申请付款</el-button>

            <el-button type="text" size="small" @click="dialogPaymentCommit(scope.row)">确认付款</el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 弹框 -->
      <el-dialog :visible.sync="dialogVisible" :title="dialogTitle" :width="dialogwidth">
        <!-- <component ref="child" :is="dialogComponents" :dialog-data="dialogData" /> -->
        <el-form ref="form" label-width="120px">
          <el-divider content-position="left">基本信息</el-divider>

          <el-row>
            <el-col :span="8">
              <el-form-item label="合同编号：">
                <div>{{ dialogData.contractNumber }}</div>
              </el-form-item>

              <el-form-item label="计划付款日期：">
                <div>{{ dialogData.paymentNode }}</div>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="款项 ：">
                <div>{{ dialogData.paymentName }}</div>
              </el-form-item>

              <el-form-item label="承包商：">
                <div>{{ dialogData.customerName }}</div>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="预付金额：">
                <div>{{ dialogData.money }}</div>
              </el-form-item>

              <el-form-item label="业主：">
                <div>{{ dialogData.proprietor }}</div>
              </el-form-item>
            </el-col>
          </el-row>

          <el-divider content-position="left">申请金额</el-divider>

          <el-row>
            <el-col :span="12">
              <el-form-item label="合同金额 ：">
                <div>{{ dialogData.contractMoney }}</div>
              </el-form-item>

              <el-form-item label="本次申请金额：">
                <el-input v-model="formData.appliedAmountBefore" placeholder="请输入内容" />
              </el-form-item>
            </el-col>

            <el-col :span="12">
              <el-form-item label="申请付款时间 ：">
                <div>{{ dialogData.paymentNode }}</div>
              </el-form-item>

              <el-form-item label="累计付款金额：">{{ appliedAmount }}</el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="24">
              <el-form-item label="申请原因概述：">
                <el-input
                  v-model="formData.pmpContractPayment.remark"
                  type="textarea"
                  placeholder="请输入内容"
                />
              </el-form-item>
            </el-col>
          </el-row>

          <el-divider content-position="left">信息审核</el-divider>

          <el-row>
            <el-col :span="8">
              <el-form-item label="申请单位：">
                <el-input
                  v-model="formData.pmpContractPayment.applicantCompany"
                  placeholder="请输入内容"
                />
              </el-form-item>

              <el-form-item label="审批人：">
                <el-select v-model="formData.checkUserId" placeholder="请选择">
                  <el-option
                    v-for="(item,index) in dialogUserData"
                    :key="index"
                    :label="item.realname"
                    :value="item.userId "
                  />
                </el-select>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="项目负责人：">
                <div>{{ dialogData.ownerUserName }}</div>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>

        <span slot="footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="commitData">确 定</el-button>
        </span>
      </el-dialog>

      <!-- <el-dialog
        :visible.sync="dialogPaymentCommitStatus"
        :title="dialogTitle"
        :width="dialogwidth"
      >

        <el-form ref="form" label-width="120px">
          <el-divider content-position="left">基本信息</el-divider>

          <el-row>
            <el-col :span="8">
              <el-form-item label="合同编号：">
                <div>{{ dialogData.contractNumber }}</div>
              </el-form-item>

              <el-form-item label="计划付款日期：">
                <div>{{ dialogData.paymentNode }}</div>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="款项 ：">
                <div>{{ dialogData.paymentName }}</div>
              </el-form-item>

              <el-form-item label="承包商：">
                <div>{{ dialogData.customerName }}</div>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="预付金额：">
                <div>{{ dialogData.money }}</div>
              </el-form-item>

              <el-form-item label="业主：">
                <div>{{ dialogData.proprietor }}</div>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>

        <span slot="footer">
          <el-button @click="dialogPaymentCommitStatus = false">取 消</el-button>
          <el-button type="primary" @click="commitData">确 定</el-button>
        </span>
      </el-dialog>-->

      <el-drawer
        :title="drawerTitle"
        :visible.sync="drawer"
        :with-header="false"
        :destroy-on-close="true"
        size="70%"
        @open="drawerDetail()"
      >
        <keep-alive :include="[]">
          <component
            :is="drawerComponents"
            :row-data="rowData"
            :drawer-data="drawerData"
            @drawerStatus="drawerStatus"
          />
        </keep-alive>
      </el-drawer>

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
</template>

<script>
import { prepaymentBillList } from '@/api/contractManagement/prepaymentBill'
export default {
  name: 'ContractIndex',
  components: {
    // DialogPaymentApply: () => import('./components/DialogPaymentApply'),
    // DialogCreate: () => import('./components/DialogCreate'),
    DrawerDetail: () => import('./components/DrawerDetail')

  },

  data() {
    return {
      dialogComponents: '',
      dialogTitle: '',
      dialogwidth: '',
      dialogVisible: false,
      dialogPaymentCommitStatus: false,
      drawerComponents: '',
      drawer: false,
      drawerTitle: '',
      drawerData: '',
      dialogData: '',
      dialogUserData: '',
      rowData: '',
      pageCurrent: 1,
      pageTotal: 1,
      pageSize: 10,

      prepaymentBillListData: [],

      grep: '',
      search: {
        contractNumber: '',
        supplierId: ''
      },

      formData: {
        appliedAmountBefore: 0,
        'pmpContractPayment': {
          'billId': '',
          'ownerUserId': '',
          'remark': '',
          'applicantCompany': ''
        },
        'checkUserId': undefined
      },
      deleteData: undefined
    }
  },
  computed: {
    appliedAmount() {
      return parseInt(this.formData.appliedAmountBefore) + parseInt(this.dialogData.totalPayment == null ? 0 : this.dialogData.totalPayment)
    }
  },
  mounted() {
    this.retrivePrepaymentBillList()
  },
  methods: {
    drawerStatus(status) {
      console.log('drawerStatus', status)
      this.drawer = status
      this.retrivePrepaymentBillList()
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`)
      this.pageCurrent = val
      this.retrivePrepaymentBillList()
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`)
      this.pageSize = val
      this.retrivePrepaymentBillList()
    },

    retrivePrepaymentBillList() {
      prepaymentBillList({
        page: this.pageCurrent,
        limit: this.pageSize,
        ...this.search
      })
        .then(res => {
          console.log(res)
          this.prepaymentBillListData = res.data.list
          this.pageTotal = res.data.totalRow
        //   this.prepaymentBillListSubData = res.data.list
        })
        .catch(() => {

        })
    },






    searchReset() {
      this.search = {
        contractNumber: '',
        supplierId: ''
      }
      this.retrivePrepaymentBillList()
    },


    commitData() {
      this.$request({
        // url: `/examineRecord/addExamine`,

        url: `/pmpContractPayment/requestPayment`,
        method: 'post',
        data: {
          'pmpContractPayment': {
          //  预付款ID
            'billId': this.dialogData.billId,
            //   负责人ID
            'ownerUserId': this.dialogData.ownerUserId,
            //   累计付款金额
            'appliedAmount': parseInt(this.appliedAmount),
            'remark': this.formData.pmpContractPayment.remark,
            // 申请单位
            'applicantCompany': this.formData.pmpContractPayment.applicantCompany
          },
          'checkUserId': this.formData.checkUserId
        },
        headers: {
          'Content-Type': 'application/json;charset=UTF-8'
        }})
        .then(res => {
          console.log('提交申请付款', res)
          this.$message.success('提交申请付款成功')
          this.formData = {
            appliedAmountBefore: 0,
            'pmpContractPayment': {
              'billId': '',
              'ownerUserId': '',
              'remark': '',
              'applicantCompany': ''
            },
            'checkUserId': undefined
          }
          this.dialogVisible = false
        })
        .catch(() => {
          this.formData = {
            appliedAmountBefore: 0,
            'pmpContractPayment': {
              'billId': '',
              'ownerUserId': '',
              'remark': '',
              'applicantCompany': ''
            },
            'checkUserId': undefined
          }
          this.dialogVisible = false
        })
    },

    dialogPaymentApply(row) {
      this.dialogVisible = true
      this.dialogTitle = '申请付款'
      this.dialogwidth = '50%'

      console.log(row)

      this.$request({
        url: `/system/user/queryUserList`,
        method: 'post',
        data: {
          roleId: 8,
          pageType: 0
        }})
        .then(res => {
          console.log('审批人', res.data)
          this.dialogUserData = res.data
          //   this.prepaymentBillListData = res.data.list
        })
        .catch(() => {

        })

      this.$request({
        url: `/pmpContractPayment/queryAdvanceBybillId`,
        method: 'post',
        data: {
          'billId': row.billId
        },
        headers: {
          'Content-Type': 'application/json;charset=UTF-8'
        }}).then(res => {
        console.log('预付款详情', res)
        this.dialogData = res.data
        //   this.prepaymentBillListData = res.data.list
        //   this.pageTotal = res.data.totalRow
        // this.dialogVisible = false
        //   this.prepaymentBillListSubData = res.data.list
      })
        .catch((e) => {
          console.log(e)
        })
    },

    dialogPaymentCommit(row) {
      console.log(row)
      this.$confirm('此操作将确认收款, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$request({
          url: `/pmpContractPayment/confirmPayment`,
          method: 'post',
          data: {
            'billId': row.billId
          },
          headers: {
            'Content-Type': 'application/json;charset=UTF-8'
          }}).then(res => {
          console.log('确认收款', res)

          this.$message({
            type: 'success',
            message: `${res.msg}`
          })
        })
          .catch((e) => {
            console.log(e)
          })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消确认收款'
        })
      })
    },

    // dialogCreate() {
    //   this.dialogVisible = true
    //   this.dialogTitle = '新建合同'
    //   this.dialogComponents = 'DialogCreate'
    //   this.dialogwidth = '90%'
    // },

    drawerDetail(row) {
      this.drawer = true
      //   this.drawerTitle = row.contractNumber
      this.drawerComponents = 'DrawerDetail'
      //   console.log('drawerDetailr', row)
      this.drawerData = row.billId
      this.rowData = row
    }

  }
}
</script>

<style lang="scss" scoped>
.box {
  height: calc(100% - 20px);
  width: calc(100% - 20px);
  background-color: #fff;
  padding: 20px 30px;
  margin: 10px;
}

/deep/ .el-dialog__body {
  border: 1px solid rgb(233, 233, 233);
  padding: 30px 50px 30px 50px;
}

/deep/ .el-drawer__body {
  border: 1px solid rgb(233, 233, 233);
  padding: 20px 50px 20px 50px;
}
</style>
