<template>

  <div>
    <div class="button">
      <el-button type="primary">转 移</el-button>
      <el-button type="primary">编 辑</el-button>

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
      </el-popover>
    </div>

    <el-form
      ref="form"
      :model="form"
      label-width="120px"
    >
      <el-row>
        <el-col :span="12">
          <el-form-item label="所属里程碑节点">
            <span>设计阶段</span>
          </el-form-item>

          <el-form-item label="DRP采购单号">
            <span>180913-REC-S2P5</span>
          </el-form-item>

          <el-form-item label="合同编号">
            <span> 1191111-REC-工程-S1-珠海一键</span>
          </el-form-item>

          <el-form-item label="合同名称">
            <span> 1191111-REC-工程-S1-珠海一键 </span>
          </el-form-item>

          <el-form-item label="承包商">
            <span> 佛山市均林人防工程有限公司 </span>
          </el-form-item>

          <el-form-item label="合同采购时间">
            <span> 2020-03-21-2020-03-29 </span>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="签署时间">
            <span> 2020-01-01 </span>
          </el-form-item>

          <el-form-item label="合同金额">
            <span> 1161178.00 </span>
          </el-form-item>

          <el-form-item label="合同开始时间">
            <span> 2020-01-01 </span>
          </el-form-item>

          <el-form-item label="合同到期时间">
            <span> 2020-01-01 </span>
          </el-form-item>

          <el-form-item label="经办人">
            <span> 王某某 </span>
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item label="合同支付金额：">

        <el-table
          :data="tableData"
          style="width: 100%"
        >
          <el-table-column
            prop="date"
            label="付款明细"
            width="180"
          />
          <el-table-column
            prop="name"
            label="附件"
            width="180"
          />
          <el-table-column
            prop="address"
            label="回款信息"
          />
          <el-table-column
            prop="address"
            label="操作记录"
          />
          <el-table-column
            prop="address"
            label="审批记录"
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
  mounted() {

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
.button {
  //   background: red;
  text-align: right;
}
</style>
