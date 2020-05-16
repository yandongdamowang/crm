<template>
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

      <el-divider content-position="left">关联业务</el-divider>
    </el-form>
  </div>
</template>


<script>

import { rebateBillAdd } from '@/api/contractManagement/rebateBill'
export default {
  name: 'DialogPayment',
  components: {
  },
  props: {
    status: {
      type: Boolean,
      default: undefined
    },
    data: {
      type: Object,
      default: undefined
    }
  },

  data() {
    return {
      tableData: [{
        date: '2016-05-03',
        address: '设计清单'
      }],


      dialogVisible: false,
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
      }
    }
  },


  computed: {
  },


  mounted() {
    console.log('rebateDetailData', this.data)
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
  },
  methods: {


    createContact() {
      console.log(this.form)
      rebateBillAdd({ ...this.form })
        .then(res => {
          console.log(res)
          this.$message({
            type: 'success',
            message: '添加成功!'
          })
          this.$router.push('/contract/rebate')
        //   this.pmpContractData = res.pmpContract
        //   this.contactListSubData = res.data.list
        })
        .catch((e) => {
          console.log(e)
        })
    }



  }
}
</script>

<style lang="scss" scoped>
.box {
  height: 100%;
  width: 100%;
  background-color: #fff;
  padding: 10px;
}
</style>
