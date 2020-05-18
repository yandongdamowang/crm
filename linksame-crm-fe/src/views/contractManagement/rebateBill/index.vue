<template>
  <div style="height:100%">
    <div class="ls-header">
      回款账单
      <div class="ls-header-r" />
    </div>

    <div class="ls-box">
      <el-form ref="form" :inline="true">
        <el-form-item label="客户：">
          <el-input v-model="search.supplierId" placeholder="请输入内容" />
        </el-form-item>

        <el-form-item label="支付方式：">
          <el-input v-model="search.paymentNumber" placeholder="请输入内容" />
        </el-form-item>

        <el-form-item label="回款时间：">
          <el-date-picker
            v-model="datetime"
            type="daterange"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            value-format="yyyy-MM-dd"
          />
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

      <el-form ref="form" :inline="true">
        <el-button type="primary" @click="createRebate">新增回款</el-button>
        <el-button>导出</el-button>

        <el-button>
          <i class="el-icon-refresh-right" />
        </el-button>
      </el-form>

      <el-table
        :data="prepaymentBillListData"
        :header-cell-style="{background:'#F5F7FA',color:'#606266'}"
        border
        height="75%"
        style="margin: 20px 0 0 0"
      >
        <el-table-column type="index" label="序号" width="55" />
        <el-table-column prop="receivableType" label="款项类型" width="150">
          <template slot-scope="scope">
            <el-button
              type="text"
              size="small"
              @click="drawerDetail(scope.row)"
            >{{ scope.row.receivableType }}</el-button>
          </template>
        </el-table-column>

        <el-table-column prop="collectingCompany" label="客户" />
        <el-table-column prop="collectionAccount" label="收款账户" />
        <el-table-column prop="collectionBank" label="开户行" width="150" />
        <el-table-column prop="collectionNumber" label="收款银行卡号" width="150" />
        <el-table-column prop="receivableAmount" label="回款金额" width="150" />
        <el-table-column prop="createdTime" label="回款时间" width="150" />
        <el-table-column prop="paymentMethod" label="付款方式" width="150" />
        <el-table-column prop="agent" label="经办人" width="150" />
        <el-table-column prop="remark" label="备注" width="150" />

        <!-- <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button type="text" size="small" @click="dialogPaymentApply(scope.row)">申请付款</el-button>

            <el-button type="text" size="small" @click>确认收款</el-button>
          </template>
        </el-table-column>-->
      </el-table>

      <!-- 弹框 -->
      <el-dialog
        :visible.sync="dialogVisible"
        :destroy-on-close="true"
        :title="dialogTitle"
        :width="dialogwidth"
      >
        <!-- <component ref="child" :is="dialogComponents" /> -->
        <div>
          <el-divider content-position="left">账户信息</el-divider>

          <el-form ref="form" :inline="true">
            <el-form-item label="收款账户：">{{ "机器人公司" }}</el-form-item>
            <el-form-item label="开户行：">{{ "中国银行" }}</el-form-item>
            <el-form-item label="收款账号：">{{ "44353401040012174" }}</el-form-item>
          </el-form>

          <el-divider content-position="left">回款信息</el-divider>

          <el-form ref="form" :inline="true">
            <el-form-item label="款项：">
              <el-input v-model="form.receivableType" placeholder="请输入内容" />
            </el-form-item>

            <el-form-item label="客户：">
              <el-input v-model="form.customerId" placeholder="请输入内容" />
            </el-form-item>

            <el-form-item label="回款时间：">
              <el-date-picker v-model="form.collectingTime" type="date" value-format="yyyy-MM-dd" />
              <!-- {{ form.collectingTime }} -->
            </el-form-item>

            <el-form-item label="回款金额：">
              <el-input v-model.number="form.receivableAmount" placeholder="请输入内容">
                <template slot="prepend">￥</template>
              </el-input>
              <!-- {{ form.receivableAmount }} -->
            </el-form-item>

            <el-form-item label="付款方式：">
              <el-input v-model="form.paymentMethod" placeholder="请输入内容" />
            </el-form-item>

            <el-form-item label="经办人：">
              <el-input v-model="form.agent" placeholder="请输入内容" />
            </el-form-item>

            <div>
              <el-form-item label="备注：">
                <el-input
                  v-model="form.remark"
                  :autosize="{ minRows: 2, maxRows: 4}"
                  type="textarea"
                  placeholder="请输入内容"
                />
              </el-form-item>
            </div>

            <!-- <el-divider content-position="left">关联业务</el-divider> -->
          </el-form>
        </div>

        <span slot="footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="commitData">确 定</el-button>
        </span>
      </el-dialog>

      <el-drawer
        :title="drawerTitle"
        :visible.sync="drawer"
        :with-header="false"
        :destroy-on-close="true"
        size="70%"
        @open="drawerDetail()"
      >
        <keep-alive :include="[]">
          <component :is="drawerComponents" :drawer-data="drawerData" @drawerStatus="drawerStatus" />
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
import { rebateBillList } from '@/api/contractManagement/rebateBill'
import { rebateBillAdd } from '@/api/contractManagement/rebateBill'

export default {
  name: 'ContractIndex',
  components: {

    DrawerDetail: () => import('./components/DrawerDetail')
  },

  data() {
    return {
      datetime: ['2020-01-01', '2020-05-29'],
      dialogComponents: '',
      dialogTitle: '',
      dialogwidth: '',
      dialogVisible: false,
      drawerComponents: '',
      drawer: false,
      drawerTitle: '',
      drawerData: '',

      pageCurrent: 1,
      pageTotal: 1,
      pageSize: 10,


      dialogVisibleMilestone: false,

      form: {
        'contractId': 1,
        'receivableType': '',
        'customerId': 1,
        // 'receivableNumber': '回款编号',
        'receivableAmount': undefined,
        'remark': '',
        'state': '1',
        'paymentMethod': '支付收款',
        'collectingCompany': '收款公司名',
        'collectionBank': '收款开户行',
        'collectionAccount': '收款账户',
        'collectingTime': '',
        'agent': '2',
        'batchId': '批次号'
      },

      prepaymentBillListData: [],
      grep: '',
      search: {
        contractNumber: '',
        supplierId: ''
      },
      deleteData: undefined
    }
  },
  computed: {

  },

  watch: {
    datetime(val) {
      this.retrivePrepaymentBillList()
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
      rebateBillList({
        page: this.pageCurrent,
        limit: this.pageSize,
        collectingStarttime: this.datetime[0],
        collectingEndtime: this.datetime[1],
        ...this.search
      })
        .then(res => {
          console.log('rebateBillList', res)
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


    createRebate() {
      this.dialogVisible = true
      if (this.data != undefined) {
        this.form = {
          'receivableRecordsId': this.data.receivableRecordsId,
          'contractId': this.data.contractId,
          'receivableType': this.data.receivableType,
          'customerId': this.data.customerId,

          'receivableAmount': parseInt(this.data.receivableAmount),
          'remark': this.data.remark,
          'state': this.data.state,
          'paymentMethod': this.data.paymentMethod,
          'collectingCompany': this.data.collectingCompany,
          'collectionBank': this.data.collectionBank,
          'collectionAccount': this.data.collectionAccount,
          'collectingTime': this.data.collectingTime,
          'agent': this.data.agent,
          'batchId': this.data.batchId
        }
      }

      this.dialogTitle = '申请付款'
      this.dialogwidth = '50%'
    },




    commitData() {
      console.log(this.form)
      rebateBillAdd({ ...this.form })
        .then(res => {
          console.log(res)
          this.$message({
            type: 'success',
            message: '添加成功!'
          })
          this.$router.push('/contract/rebate')
        })
        .catch((e) => {
          console.log(e)
        })
      this.dialogVisible = false
    },


    drawerDetail(row) {
      this.drawer = true
      //   this.drawerTitle = row.contractNumber
      this.drawerComponents = 'DrawerDetail'
      //   console.log('drawerDetailr', row)
      this.drawerData = row.receivableRecordsId
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
