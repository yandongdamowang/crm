<template>
  <div style="height:100%">
    <div class="ls-header">
      付款账单
      <span class="grep">
        <!-- <el-date-picker
          v-model="search.datetime"
          style="float: right;margin: 8px 50px 0 0"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          value-format="yyyy-MM-dd"
        />-->
      </span>
    </div>

    <div class="ls-box">
      <el-form ref="form" :inline="true">
        <el-form-item label="付款编号：">
          <el-input v-model="search.paymentNumber" placeholder="请输入内容" />
        </el-form-item>

        <el-form-item label="客户：">
          <el-input v-model="search.supplierId" placeholder="请输入内容" />
        </el-form-item>

        <el-form-item>
          <span>
            <el-button type="primary" @click="retrivePaymentBillList">查询</el-button>
          </span>
          <span>
            <el-button @click="searchReset">重置</el-button>
          </span>
        </el-form-item>
      </el-form>

      <el-table
        :data="paymentBillListData"
        :header-cell-style="{background:'#F5F7FA',color:'#606266'}"
        border
        height="80%"
        style="margin: 20px 0 0 0"
        @selection-change="selectTable"
      >
        <!-- style="width: 100%; margin:20px 0 0 0" -->

        <el-table-column type="index" label="序号" width="55" />

        <el-table-column prop="paymentNumber" label="付款编号" width="150">
          <template slot-scope="scope">
            <el-button
              type="text"
              size="small"
              @click="drawerDetail(scope.row)"
            >{{ scope.row.paymentNumber }}</el-button>
          </template>
        </el-table-column>

        <el-table-column prop="supplierId" label="客户/承包商" />
        <el-table-column prop="amountAdvanced" label="预付金额" />
        <el-table-column prop="practicalRatio" label="实付比例" width="150" />
        <el-table-column prop="practicaAdvanced" label="实付金额" width="150" />
        <el-table-column prop="paymentTime" label="实际付款时间" width="150" />

        <el-table-column prop="paymentType" label="付款方式" />
        <el-table-column prop="responsiblePerson" label="经办人" />
        <el-table-column prop="remark" label="备注" />
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

      <el-dialog :visible.sync="dialogVisible" :title="dialogTitle" :width="dialogwidth">
        <component ref="child" :is="dialogComponents" />

        <span slot="footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="commitData">确 定</el-button>
        </span>
      </el-dialog>

      <el-drawer
        :title="drawerTitle"
        :with-header="false"
        :visible.sync="drawer"
        :destroy-on-close="true"
        size="70%"
        @open="drawerDetail()"
      >
        <component :is="drawerComponents" :drawer-data="drawerData" />
      </el-drawer>
    </div>
  </div>
</template>

<script>
import { paymentBillList } from '@/api/contractManagement/paymentBill'
export default {
  name: 'ContractIndex',
  components: {
    DialogPaymentApply: () => import('./components/DialogPaymentApply'),
    DialogCreate: () => import('./components/DialogCreate'),
    DrawerDetail: () => import('./components/DrawerDetail')
  },

  data() {
    return {
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
      paymentBillListData: [],

      search: {
        paymentNumber: '',
        supplierId: '',
        datetime: ['2020-04-10', '2020-10-10']
      },
      deleteData: undefined
    }
  },
  computed: {

  },
  mounted() {
    this.retrivePaymentBillList()
  },
  methods: {
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`)
      this.pageCurrent = val
      this.retrivePaymentBillList()
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`)
      this.pageSize = val
      this.retrivePaymentBillList()
    },



    retrivePaymentBillList() {
      paymentBillList({
        startTime: this.search.datetime[0],
        endTime: this.search.datetime[1],
        page: this.pageCurrent,
        limit: this.pageSize,
        ...this.search
      })
        .then(res => {
          console.log(res)
          this.paymentBillListData = res.data.list
          this.pageTotal = res.data.totalRow
        //   this.paymentBillListSubData = res.data.list
        })
        .catch(() => {

        })
    },
    searchReset() {
      this.search = {
        contractNumber: '',
        supplierId: ''
      }
      this.retrivePaymentBillList()
    },

    commitData() {
      this.$refs.child.createContact()
      this.dialogVisible = false
    },

    dialogPaymentApply() {
      this.dialogVisible = true
      this.dialogTitle = '申请付款'
      this.dialogComponents = 'DialogPaymentApply'
      this.dialogwidth = '50%'
    },
    dialogCreate() {
      this.dialogVisible = true
      this.dialogTitle = '新建合同'
      this.dialogComponents = 'DialogCreate'
      this.dialogwidth = '90%'
    },
    drawerDetail(row) {
      this.drawer = true
      //   this.drawerTitle = row.contractName
      this.drawerComponents = 'DrawerDetail'
      console.log('drawerDetail', row)
      this.drawerData = row.paymentRecordId
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
