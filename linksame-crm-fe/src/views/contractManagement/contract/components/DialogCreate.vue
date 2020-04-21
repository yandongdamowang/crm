<template>

  <div>
    <el-divider content-position="left">基本信息</el-divider>
    <el-form
      ref="form"
      :model="form"
      :inline="true"
      label-width="120px"
    >
      <el-form-item label="所属合同类型">
        <el-select
          v-model="form.contractType"
          placeholder="请选合同类型"
        >
          <el-option
            label="类型一"
            value="shanghai"
          />
          <el-option
            label="类型二"
            value="beijing"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="合同名称">
        <el-input v-model="form.contractName" />
        <!-- <div class="block">

          <el-cascader
            :options="options"
            :clearable="true"
            placeholder="搜索"
            filterable
          />
        </div> -->
      </el-form-item>
      <el-form-item label="合同编号">
        <el-input
          v-model="form.contractNumber"
          placeholder="请输入合同编号"
        />
      </el-form-item>

      <el-form-item label="合同采购时间">
        <el-date-picker
          v-model="form.contractPurchaseStartTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择日期"
          align="right"
        />
      </el-form-item>
      <el-form-item label="合同开始日期">
        <el-date-picker
          v-model="form.beginTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择日期"
          align="right"
        />
      </el-form-item>
      <el-form-item label="合同结束日期">
        <el-date-picker
          v-model="form.endTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择日期"
          align="right"
        />
      </el-form-item>
      <el-form-item label="承包商">

        <el-select
          v-model="form.supplierId"
          placeholder="请选合同承包商"
        >
          <el-option
            label="承包商一"
            value="11"
          />
          <el-option
            label="承包商二"
            value="11"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="经办人">

        <el-select
          v-model="form.agent"
          placeholder="请选合同经办人"
        >
          <el-option
            label="经办人一"
            value="12"
          />
          <el-option
            label="经办人二"
            value="12"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="DRP采购单号">
        <el-input
          v-model="form.drpNumber"
          placeholder="请输入单号"
        />
      </el-form-item>
      <el-form-item label="合同金额">
        <el-input
          v-model="form.money"
          placeholder="请输入金额"
        />
      </el-form-item>
    </el-form>

    <el-divider content-position="left">支付方式</el-divider>
    <el-form
      v-for="(item,index) in form.pmpContractPayments"
      ref="form"
      :key="index"
      :model="form"
      :inline="true"
      label-width=" 25px"
    >
      <el-form-item label="">
        <el-input
          v-model="form.pmpContractPayments[index].payment_name"
          placeholder="款项"
        />
      </el-form-item>

      <el-form-item label="--">
        <el-input
          v-model="form.pmpContractPayments[index].cost_percentage"
          placeholder="支付比例"
        />
      </el-form-item>

      <el-form-item label="--">
        <el-input
          v-model="form.pmpContractPayments[index].money"
          placeholder="支付金额"
          @input="computeMoney(form.pmpContractPayments[index].money)"
        />
      </el-form-item>

      <el-form-item label="--">
        <el-date-picker
          v-model="form.pmpContractPayments[index].payment_node"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择日期"
          align="right"
        />
      </el-form-item>

      <el-form-item label="">
        <el-button
          type="danger"
          icon="el-icon-delete"
          circle
          @click="detailpmpContractPayments('delete',index)"
        />
      </el-form-item>
      <!-- <el-form-item label="--">
        <el-input v-model="form.n" />
      </el-form-item> -->
    </el-form>

    <el-button
      type="primary"
      @click="detailpmpContractPayments('add')"
    >增加付款方式</el-button>

    <!-- <el-divider content-position="left">关联业务</el-divider>
    <el-button
      type="text"
      @click="dialogVisible = true"
    >添加关联</el-button> -->
    <!--
    <el-dialog
      :visible.sync="dialogVisible"
      :before-close="handleClose"
      title="关联业务"
      data
      width="30%"
      append-to-body
    >

      <el-input
        v-model="form.o"
        placeholder="请输入内容"
      />

      <el-button type="primary">查询</el-button>

      <span
        slot="footer"
        class="dialog-footer"
      >
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button
          type="primary"
          @click="dialogVisible = false"
        >确 定</el-button>
      </span>
    </el-dialog> -->

    <!-- <el-divider content-position="left">选择任务模板</el-divider> -->

    <!-- <el-table
      ref="multipleTable"
      :data="tableData"
      tooltip-effect="dark"
      style="width: 100%"
      @selection-change="handleSelectionChange"
    >
      <el-table-column
        type="selection"
        width="55"
      />

      <el-table-column
        prop="address"
        label="合同采购"
      />

      <el-table-column
        prop="date"
        label=""
      />
    </el-table>

    <el-table
      ref="multipleTable"
      :data="tableData"
      tooltip-effect="dark"
      style="width: 100%"
      @selection-change="handleSelectionChange"
    >
      <el-table-column
        type="selection"
        width="55"
      />

      <el-table-column
        prop="address"
        label="设计阶段"
      />

      <el-table-column
        prop="date"
        label=""
      />
    </el-table> -->

    <!-- <el-cascader
      :options="options"
      :clearable="true"
      placeholder="选择里程碑节点"
      filterable
    /> -->

    <!-- <el-button
      type="text"
      @click="dialogVisibleMilestone = true"
    >+</el-button> -->

    <el-dialog
      :visible.sync="dialogVisibleMilestone"
      :before-close="handleClose"
      title="新建里程碑节点"
      data
      width="300px"
      append-to-body
    >

      <el-input
        v-model="form.p"
        placeholder="请输入节点名称"
      />

      <span
        slot="footer"
        class="dialog-footer"
      >

        <el-button
          type="primary"
          @click="dialogVisible = false"
        >创 建</el-button>
      </span>
    </el-dialog>

  </div>

</template>


<script>

import { contactAdd } from '@/api/contractManagement/contacts'
export default {
  name: 'DialogPayment',
  components: {
  },
  props: {
    status: {
      type: Boolean,
      default: undefined
    }
  },

  data() {
    return {
      tableData: [{
        date: '2016-05-03',
        address: '设计清单'
      }],
      options: [{
        value: 'zhinan',
        label: 'DP设计类',
        children: [{
          value: 'shejiyuanze',
          label: '建筑设计'
        }, {
          value: 'daohang',
          label: '精装修设计'
        },
        {
          value: 'daohang',
          label: '燃气设计'
        }]
      }],

      dialogVisible: false,
      dialogVisibleMilestone: false,
      moneyMax: 0,
      form: {
        'contractType': '',
        'contractName': '',
        'contractNumber': '',
        'contractPurchaseStartTime': '',
        'contractPurchaseEndTime': '',
        'beginTime': '',
        'endTime': '',
        'supplierId': undefined,
        'agent': undefined,
        'drpNumber': '',
        'money': 1000000,
        'pmpContractPayments': [
          {
            'payment_name': '',
            'cost_percentage': undefined,
            'money': 1000000,
            'payment_node': ''
          }

        ]
      }
    }
  },


  computed: {
  },

  watch: {
    moneyMax() {
      console.log(this.moneyMax)
    }
  },
  mounted() {

  },
  methods: {
    computeMoney(params) {
    //   console.log(params)
      this.moneyMax += parseInt(params)
    },


    createContact() {
      console.log(this.form)
      contactAdd(JSON.stringify(this.form))
        .then(res => {
          console.log(res)
          this.$message({
            type: 'success',
            message: '添加成功!'
          })
          this.$router.push('/contract/contract')
        //   this.pmpContractData = res.pmpContract
        //   this.contactListSubData = res.data.list
        })
        .catch(() => {

        })
    },
    detailpmpContractPayments(pararm, index) {
      console.log(pararm)
      pararm == 'delete' ? this.form.pmpContractPayments.splice(index, 1) : this.form.pmpContractPayments.push({
        'payment_name': '',
        'cost_percentage': 50,
        'money': undefined,
        'payment_node': ''
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
