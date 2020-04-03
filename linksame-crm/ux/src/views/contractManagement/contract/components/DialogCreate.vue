<template>

  <div style="boder:1px soild black">
    <el-divider content-position="left">基本信息</el-divider>
    <el-form
      ref="form"
      :model="form"
      :inline="true"
      label-width="120px"
    >
      <el-form-item label="所属合同类型">
        <el-input v-model="form.name" />
      </el-form-item>

      <el-form-item label="合同名称">
        <el-input v-model="form.name" />
      </el-form-item>
      <el-form-item label="合同编号">
        <el-input v-model="form.name" />
      </el-form-item>

      <el-form-item label="合同采购时间">
        <el-input v-model="form.name" />
      </el-form-item>
      <el-form-item label="合同开始日期">
        <el-input v-model="form.name" />
      </el-form-item>
      <el-form-item label="合同结束日期">
        <el-input v-model="form.name" />
      </el-form-item>
      <el-form-item label="承包商">
        <el-input v-model="form.name" />
      </el-form-item>
      <el-form-item label="经办人">
        <el-input v-model="form.name" />
      </el-form-item>
      <el-form-item label="DRP采购单号">
        <el-input v-model="form.name" />
      </el-form-item>
      <el-form-item label="合同金额">
        <el-input v-model="form.name" />
      </el-form-item>
    </el-form>

    <el-divider content-position="left">支付方式</el-divider>
    <el-form
      ref="form"
      :model="form"
      :inline="true"
      label-width=" 25px"
    >
      <el-form-item label="">
        <el-input
          v-model="form.name"
          placeholder="款项"
        />
      </el-form-item>

      <el-form-item label="--">
        <el-input
          v-model="form.name"
          placeholder="支付比例"
        />
      </el-form-item>

      <el-form-item label="--">
        <el-input
          v-model="form.name"
          placeholder="支付金额"
        />
      </el-form-item>

      <el-form-item label="--">
        <el-input v-model="form.name" />
      </el-form-item>
      <el-form-item label="--">
        <el-input v-model="form.name" />
      </el-form-item>
    </el-form>

    <el-button>增加付款方式</el-button>

    <el-divider content-position="left">关联业务</el-divider>
    <el-button
      type="text"
      @click="dialogVisible = true"
    >添加关联</el-button>

    <el-dialog
      :visible.sync="dialogVisible"
      :before-close="handleClose"
      title="提示"
      width="30%"
      append-to-body
    >
      <span>这是一段信息</span>
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
    </el-dialog>

    <el-divider content-position="left">选择任务模板</el-divider>

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
    </el-table>
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
      tableData: [{
        date: '2016-05-03',
        address: '设计清单'
      }],


      dialogVisible: false,

      form: {
        name: '',
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
</style>
