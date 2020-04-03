<template>
  <div class="box">

    <el-form
      ref="form"
      :model="formData"
      :inline="true"
    >

      <el-form-item label="合同编号：">
        <el-input
          v-model="formData.a"
          placeholder="请输入内容"
        />
      </el-form-item>

      <el-form-item label="客户/承包商：">
        <el-input
          v-model="formData.b"
          placeholder="请输入内容"
        />
      </el-form-item>

      <el-form-item>
        <span>
          <el-button type="primary">查询</el-button>
        </span>
        <span>
          <el-button>重置</el-button>
        </span>
      </el-form-item>
    </el-form>

    <div>
      <span>
        <el-button
          type="primary"
          @click="dialogCreate"
        >新建</el-button>
      </span>
      <span>
        <el-button>批量操作</el-button>
      </span>
      <span>
        <el-button>导出</el-button>
      </span>
      <span>
        <el-button
          type="primary"
          @click="dialogPayment"
        >批量修改支付比例</el-button>
      </span>
    </div>

    <el-table
      :data="tableData"
      border
      style="width: 100%"
    >
      <el-table-column
        prop="date"
        label="合同编号"
        width="150"
      />
      <el-table-column
        prop="name"
        label="合同名称"
        width="120"
      />
      <el-table-column
        prop="province"
        label="客户/承包商"
        width="120"
      />
      <el-table-column
        prop="city"
        label="合同总金额"
        width="120"
      />
      <el-table-column
        prop="address"
        label="要求签署时间"
        width="300"
      />
      <el-table-column
        prop="zip"
        label="实际签署时间"
        width="120"
      />
      <el-table-column
        prop="zip"
        label="DRP采购单号"
        width="120"
      />
      <el-table-column
        prop="zip"
        label="经办人"
        width="120"
      />
      <el-table-column
        fixed="right"
        label="操作"
        width="100"
      >
        <template slot-scope="scope">
          <el-button
            type="text"
            size="small"
            @click="handleClick(scope.row)"
          >查看</el-button>
          <el-button
            type="text"
            size="small"
          >编辑</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 弹框 -->
    <el-dialog
      :visible.sync="dialogVisible"
      :title="dialogTitle"
      :fullscreen="true"
      width="30%"
    >
      <component :is="dialogComponents" />

      <span slot="footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button
          type="primary"
          @click="dialogVisible = false"
        >确 定</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>

export default {
  name: 'ContractIndex',
  components: {
    DialogPayment: () => import('./components/DialogPayment'),
    DialogCreate: () => import('./components/DialogCreate')
  },

  data() {
    return {
      dialogComponents: '',
      dialogTitle: '',
      dialogVisible: false,
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
  mounted() {},
  methods: {
    dialogPayment() {
      this.dialogVisible = true
      this.dialogTitle = '支付比例调整'
      this.dialogComponents = 'DialogPayment'
    },
    dialogCreate() {
      this.dialogVisible = true
      this.dialogTitle = '新建合同'
      this.dialogComponents = 'DialogCreate'
    }
  }
}
</script>

<style lang="scss" scoped>
@import "../styles/table.scss";
.box {
  height: 100%;
  width: 100%;
  background-color: #fff;
  padding: 10px;
}
</style>
