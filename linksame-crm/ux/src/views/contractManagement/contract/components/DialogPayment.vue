<template>

  <div>
    <el-form
      ref="form"
      :model="form"
      label-width="120px"
    >

      <el-form-item label="选择时间">
        <el-date-picker
          v-model="formData.b"
          type="month"
          placeholder="选择时间"
        />
      </el-form-item>

      <el-form-item label="合同金额：">
        <div>8,000,000.00</div>
      </el-form-item>

      <el-form-item label="调整后金额：">
        <el-input
          v-model="formData.a"
          placeholder="请输入内容"
        >
          <template slot="prepend">￥</template>
        </el-input>
      </el-form-item>

      <el-form-item label="调整金额：">
        <span>2,000,000.00</span>
      </el-form-item>

      <el-form-item label="调整金额：">
        <span>合同支付金额：</span>

        <el-table
          :data="tableData"
          style="width: 100%"
        >
          <el-table-column
            prop="date"
            label="日期"
            width="180"
          />
          <el-table-column
            prop="name"
            label="姓名"
            width="180"
          />
          <el-table-column
            prop="address"
            label="地址"
          />
        </el-table>
      </el-form-item>

    </el-form>
  </div>

</template>


<script>
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
      tableData: [],
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
    console.log(this.status)
  },
  methods: {
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
