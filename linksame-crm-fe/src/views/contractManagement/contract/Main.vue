<template>
  <div style="height:100%">
    <div class="ls-header">
      合同管理
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

        <el-form-item label="客户/承包商：">
          <el-input v-model="search.supplierId" placeholder="请输入内容" />
        </el-form-item>

        <el-form-item>
          <span>
            <el-button type="primary" @click="retriveContactList">查询</el-button>
          </span>
          <span>
            <el-button @click="searchReset">重置</el-button>
          </span>
        </el-form-item>
      </el-form>

      <div>
        <span>
          <el-button type="primary" @click="dialogCreate">新建合同</el-button>
        </span>
        <span>
          <el-dropdown>
            <el-button>批量操作</el-button>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>
                <div @click="deleteContract">批量删除</div>
              </el-dropdown-item>

              <el-dropdown-item>
                <div @click="dialogPayment">批量修改支付比例</div>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </span>

        <span>
          <el-button>导出</el-button>
        </span>
        <span>
          <el-button icon="el-icon-refresh-right" @click="retriveContactList" />
        </span>
      </div>

      <el-table
        :data="contactListData"
        :header-cell-style="{background:'#F5F7FA',color:'#606266'}"
        border
        style="margin: 20px 0 0 0"
        height="75%"
        @selection-change="selectTable"
      >
        <!-- style="width: 100%; margin:20px 0 0 0" -->

        <el-table-column type="selection" width="55" />
        <el-table-column prop="contractNumber" label="合同编号" width="150">
          <template slot-scope="scope">
            <el-button
              type="text"
              size="small"
              @click="drawerDetail(scope.row)"
            >{{ scope.row.contractNumber }}</el-button>
          </template>
        </el-table-column>
        <el-table-column prop="contractName" label="合同名称" width="120" />
        <el-table-column prop="supplierId" label="客户/承包商" width="120" />
        <el-table-column prop="money" label="合同总金额(￥)" width="120" />
        <el-table-column prop="requiredSigningTime" label="要求签署时间" width="150" />
        <el-table-column prop="actualSigningTime" label="实际签署时间" width="150" />
        <el-table-column prop="purchaseOrderNumber" label="DRP采购单号" width="120" />
        <el-table-column prop="agent" label="经办人" width="120" />

        <!-- <el-table-column label="2020年4月">
        <el-table-column
          prop="billList[0].2020年4月.paymentRatio"
          label="支付比例"
          width="120"
        />
      </el-table-column>

      <el-table-column label="2010年6月">
        <el-table-column
          prop="billList[1].2010年6月.paymentRatio"
          label="支付比例"
          width="120"
        />
      </el-table-column>

      <el-table-column label="2000年7月">
        <el-table-column
          prop="billList[2].2000年7月.paymentRatio"
          label="支付比例"
          width="120"
        />
        </el-table-column>-->

        <el-table-column
          v-for="(item,index) in contactListSubData"
          :key="index"
          :label="item"
          align="center"
        >
          <el-table-column label="支付比例" width="120">
            <template slot-scope="scope">
              <div>{{ scope.row.billList[item].paymentRatio + "%" }}</div>
            </template>
          </el-table-column>
          <el-table-column label="支付金额" width="120">
            <template slot-scope="scope">
              <div>{{ "￥" + scope.row.billList[item].paymentAmount }}</div>
            </template>
          </el-table-column>

          <el-table-column label="累计支付比例" width="120">
            <template slot-scope="scope">
              <div>{{ scope.row.billList[item].cumulativePaymentRatio + "%" }}</div>
            </template>
          </el-table-column>

          <el-table-column label="累计支付金额" width="120">
            <template slot-scope="scope">
              <div>{{ "￥" + scope.row.billList[item].cumulativepayment }}</div>
            </template>
          </el-table-column>
        </el-table-column>
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

      <!-- 弹框 -->
      <el-dialog :visible.sync="dialogVisible" :title="dialogTitle" :width="dialogwidth">
        <keep-alive exclude="DialogCreate,DialogPayment">
          <component ref="child" :is="dialogComponents" />
        </keep-alive>

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
        <component :is="drawerComponents" :drawer-data="drawerData" @drawerStatus="drawerStatus" />
      </el-drawer>
    </div>
  </div>
</template>

<script>
import { contactList, contactDelete } from '@/api/contractManagement/contacts'
export default {
  name: 'ContractIndex',
  components: {
    DialogPayment: () => import('./components/DialogPayment'),
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
      contactListData: [],
      contactListSubData: [],

           pageCurrent: 1,
      pageTotal: 1,
      pageSize: 10,


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
  mounted() {
    this.retriveContactList()
  },
  methods: {
    selectTable(select) {
      const deleteContract = []
      select.forEach((item, index) => {
        // console.log(item.contractId)
        deleteContract.push(item.contractId)
      })
      this.deleteData = deleteContract.join(',')
    },
    drawerStatus(status) {
      this.drawer = status
    },

    handleCurrentChange(val) {
      console.log(`当前页: ${val}`)
      this.pageCurrent = val
      this.retriveContactList()
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`)
      this.pageSize = val
      this.retriveContactList()
    },



    deleteContract() {
      this.$confirm('此操作将永久选中合同, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'

      }).then(() => {
        contactDelete({
          contractIds: this.deleteData
        // ...this.search
        })
          .then(res => {
            this.$message({
              type: 'success',
              message: '删除成功!'
            })
            console.log(res)

            this.retriveContactList()
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

    retriveContactList() {
      contactList({
        // productId: this.id
             page: this.pageCurrent,
        limit: this.pageSize,
        ...this.search
      })
        .then(res => {
          console.log(res.data)
          this.contactListData = res.data.list
                 this.pageTotal = res.data.totalRow
          res.data.list.forEach(item => {
            this.contactListSubData = Object.keys(item.billList)
          })
          console.log(this.contactListSubData)
        })
        .catch(() => {

        })
    },
    searchReset() {
      this.search = {
        contractNumber: '',
        supplierId: ''
      }
      this.retriveContactList()
    },

    commitData() {
      this.$refs.child.createContact()
      this.dialogVisible = false
    },

    dialogPayment() {
      this.dialogVisible = true
      this.dialogTitle = '支付比例调整'
      this.dialogComponents = 'DialogPayment'
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
      this.drawerTitle = row.contractName
      this.drawerComponents = 'DrawerDetail'
      console.log(row)
      this.drawerData = row.contractId
    }

  }
}
</script>

<style lang="scss" scoped>
/deep/ .el-dialog__body {
  border: 1px solid rgb(233, 233, 233);
  padding: 30px 50px 30px 50px;
}

/deep/ .el-drawer__body {
  border: 1px solid rgb(233, 233, 233);
  padding: 20px 50px 20px 50px;
}
</style>
