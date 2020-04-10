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
        > + 新建</el-button>
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
      style="width: 100%; margin:20px 0 0 0"
    >

      <el-table-column
        type="selection"
        width="55"
      />
      <el-table-column
        prop="id"
        label="合同编号"
        width="150"
      >
        <template slot-scope="scope">
          <el-button
            type="text"
            size="small"
            @click="drawerDetail(scope.row)"
          >{{ scope.row.id }}</el-button>
        </template>
      </el-table-column>
      <el-table-column
        prop="b"
        label="合同名称"
        width="120"
      />
      <el-table-column
        prop="c"
        label="客户/承包商"
        width="120"
      />
      <el-table-column
        prop="d"
        label="合同总金额"
        width="120"
      />
      <el-table-column
        prop="e"
        label="要求签署时间"
        width="120"
      />
      <el-table-column
        prop="f"
        label="实际签署时间"
        width="120"
      />
      <el-table-column
        prop="g"
        label="DRP采购单号"
        width="120"
      />
      <el-table-column
        prop="h"
        label="经办人"
        width="120"
      />

    </el-table>

    <!-- 弹框 -->
    <el-dialog
      :visible.sync="dialogVisible"
      :title="dialogTitle"
      :width="dialogwidth"
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

    <el-drawer
      :visible.sync="drawer"
      :title="drawerTitle"
      size="70%"
      @open="drawerDetail()"
    >
      <component
        :is="drawerComponents"
        :drawer-data="drawerData"
      />
    </el-drawer>

  </div>
</template>

<script>

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
      drawerData: '123',
      tableData: [{
        id: 100,
        b: '测试合同',
        c: '上海测试公司',
        d: '50.000.00',
        e: '2017-10-01',
        f: '2017-10-01',
        g: '191111-REC-工程-S1-珠海一键-智能化改造',
        h: '胡彦斌'
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
  mounted() {},
  methods: {
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
      this.drawerTitle = ''
      this.drawerComponents = 'DrawerDetail'
      this.drawerData = row.g
    }

  }
}
</script>

<style lang="scss" scoped>
.box {
  height: calc(100% - 20px);
  width: calc(100% - 20px);
  background-color: #fff;
  padding: 20px 30px;
  margin: 10px;
}

/deep/ .el-dialog__body {
  border: 1px solid rgb(233, 233, 233);
  padding: 30px 50px 30px 50px;
}

/deep/ .el-drawer__body {
  border: 1px solid rgb(233, 233, 233);
  padding: 20px 50px 20px 50px;
}
</style>
