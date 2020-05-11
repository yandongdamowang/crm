<template>
  <div>
    <el-form ref="form" :model="formData" label-width="120px">
      <el-form-item label="选择时间">
        <el-date-picker v-model="formData.time" type="month" placeholder="选择时间" />
      </el-form-item>

      <el-form-item label="合同金额：">
        <div>{{ formData.money }}</div>
      </el-form-item>

      <el-form-item label="调整后金额：">
        <el-input v-model="formData.a" placeholder="请输入内容">
          <template slot="prepend">￥</template>
        </el-input>
      </el-form-item>

      <el-form-item label="调整金额：">
        <span v-if="formData.a">￥{{ formData.a-formData.money }}</span>
      </el-form-item>

      <!-- <el-form-item label=""> -->
      <el-table :data="paymentListData" style="width: 100%">
        <el-table-column prop="contractName" label="合同名称" width="180" />
        <el-table-column prop="money" label="支付金额" width="180" />
        <el-table-column prop="costPercentage" label="支付比例" />
        <el-table-column label="调整后支付金额">
          <template slot-scope="scope">
            <el-input v-model="formData.b" placeholder="请输入金额" />
          </template>
        </el-table-column>
        <el-table-column prop="address" label="调整后支付比例" />
      </el-table>
      <!-- </el-form-item> -->
    </el-form>
  </div>
</template>


<script>
import { paymentList } from '@/api/contractManagement/contacts'
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
    //   dialogVisiblePayment: false,
      paymentListData: [],
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
    console.log(123)
    this.retrivePaymentList()
  },
  methods: {
    retrivePaymentList() {
      paymentList({
        'month': '2020-04'
      })
        .then(res => {
          console.log(res)
          this.paymentListData = res.list
          this.formData = res
        })
        .catch(() => {

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
