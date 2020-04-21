<template>

  <div>
    <el-form
      ref="form"
      :model="formData"
      label-width="120px"
    >

      <!-- <el-form-item label="合同金额：">
        <div>待开发</div>
      </el-form-item> -->

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
        //   this.contactListSubData = res.data.list
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
