<template>

  <div>

    <div class="title">
      <!-- <div class="logo">
        <i
          class="wukong wukong-contract"
          style="font-size: 30px;color: white;"
        />
      </div>
      {{ pmpContractData.contractName }} -->
      <el-button type="primary"><i class="el-icon-edit" /></el-button>
      <el-button type="primary"><i class="el-icon-folder-opened" /></el-button>
      <el-button type="primary">附件<i class="el-icon-upload el-icon--right" /></el-button>

      <el-dropdown>
        <el-button icon="el-icon-more" />

        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>转 移</el-dropdown-item>
          <!-- <el-dropdown-item>编 辑</el-dropdown-item> -->
          <el-dropdown-item>
            <span @click="deleteContract">删 除</span>
          </el-dropdown-item>

        </el-dropdown-menu>
      </el-dropdown>
    </div>

    <div class="button">

      <!--
      <el-popover
        placement="bottom"
        width="400"
        trigger="click"
      >
        <el-table :data="gridData">
          <el-table-column
            width="150"
            property="date"
            label="日期"
          />
          <el-table-column
            width="100"
            property="name"
            label="姓名"
          />
          <el-table-column
            width="300"
            property="address"
            label="地址"
          />
        </el-table>
        <el-button slot="reference">更 多</el-button>
      </el-popover> -->
    </div>

    <div class="ls-drawerform">

      <div class="ls-drawerform-header"><i class="el-icon-notebook-1" />&nbsp;&nbsp;&nbsp;基本信息</div>

      <el-form
        ref="form"
        label-width="120px"
        label-position="left"
      >
        <el-row>
          <el-col :span="12">

            <el-form-item label="合同类型">
              <span> 1191111-REC-工程-S1-珠海一键</span>
            </el-form-item>

            <el-form-item label="合同采购时间">
              <span>{{ pmpContractData.contractPurchaseStartTime }} </span>
            </el-form-item>
            <caption />

            <el-form-item label="合同编号">
              <span>{{ pmpContractData.contractNumber }} </span>
            </el-form-item>

            <el-form-item label="合同名称">
              <span>{{ pmpContractData.contractName }} </span>
            </el-form-item>

            <el-form-item label="承包商">
              <span>{{ pmpContractData.supplierId }} </span>
            </el-form-item>

            <el-form-item label="DRP采购单号">
              <span>{{ pmpContractData.purchaseOrderNumber }} </span>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="签署时间">
              <span>{{ pmpContractData.actualSigningTime }} </span>
            </el-form-item>

            <el-form-item label="合同金额">
              <span>{{ pmpContractData.money }} </span>
            </el-form-item>

            <el-form-item label="合同开始时间">
              <span>{{ pmpContractData.contractPurchaseStartTime }} </span>
            </el-form-item>

            <el-form-item label="合同到期时间">
              <span>{{ pmpContractData.contractPurchaseEndTime }} </span>
            </el-form-item>

            <el-form-item label="经办人">
              <span>{{ pmpContractData.agent }} </span>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </div>

    <el-tabs
      v-model="activeName"
      @tab-click="handleClick"
    >
      <el-tab-pane
        label="付款明细"
        name="first"
      >
        <el-table
          :data="tableData"
          style="width: 100%"
        >
          <el-table-column
            prop="a"
            label="预计付款时间"
            width="180"
          />
          <el-table-column
            prop="b"
            label="款项"
            width="180"
          />
          <el-table-column
            prop="c"
            label="比例"
          />
          <el-table-column
            prop="d"
            label="金额"
          />
          <el-table-column
            prop="e"
            label="实际支付比例"
          />
          <el-table-column
            prop="f"
            label="实际支付金额"
          />
          <el-table-column
            prop="g"
            label="关联项目任务"
          />
          <el-table-column
            prop="h"
            label="状态"
          />
          <el-table-column
            prop="i"
            label="备注"
          />
        </el-table>
      </el-tab-pane>
      <el-tab-pane
        label="附件"
        name="second"
      >
        <div>附件</div>
      </el-tab-pane>
      <el-tab-pane
        label="回款信息"
        name="third"
      >
        <div>回款信息</div>
      </el-tab-pane>
      <el-tab-pane
        label="操作记录"
        name="fourth"
      >
        <div>操作记录</div>
      </el-tab-pane>
      <el-tab-pane
        label="审批记录"
        name="fifth"
      >
        <div style="height: 300px;">
          <el-steps
            :active="1"
            direction="vertical"
          >
            <el-step title="发起审批" />
            <el-step title="审批节点1" />
            <el-step
              title="审批节点2"
              description="
                  创建人：王某某
                  审批类型：付款申请"
            />
            <el-step
              title="审批节点3"
              description="
                待处理"
            />
          </el-steps>
        </div>
      </el-tab-pane>
    </el-tabs>

  </div>

</template>

<script>
import { contactDetail, contactDelete } from '@/api/contractManagement/contacts'
export default {
  name: 'DialogPayment',
  components: {
  },
  props: {
    drawerData: {
      type: Object,
      default: undefined
    }
  },

  data() {
    return {
      pmpContractData: {},
      activeName: 'first',

      gridData: [{
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }],
      formData: {
        a: '',
        b: '',
        c: ''
      }
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
    // console.log(111, this.drawerData)
    this.retriveContactDetail()
  },
  methods: {
    deleteContract() {
      console.log(123, this.drawerData)
      this.$confirm('此操作将永久删除该合同, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'

      }).then(() => {
        contactDelete({
          contractIds: this.drawerData
        // ...this.search
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
          console.log(res)
          this.pmpContractData = res.pmpContract
        //   this.contactListSubData = res.data.list
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
.title {
  font-size: 20px;
  line-height: 60px;
}
</style>
