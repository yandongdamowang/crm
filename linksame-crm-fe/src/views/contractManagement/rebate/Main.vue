<template>
  <div class="box">
    回款管理
    <!-- <el-form
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
            @click="dialogDetail( scope.row)"
          >{{ scope.row.id }}</el-button>
        </template>
      </el-table-column>
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

    </el-table>

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

    <el-drawer
      :visible.sync="drawer"
      :title="drawerTitle"
      size="70%"
    >
      <component :is="drawerComponents" />
    </el-drawer> -->

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
      dialogVisible: false,
      drawerComponents: '',
      drawer: false,
      drawerTitle: '',
      tableData: [{
        id: 100,
        name: '测试'
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
    },
    dialogCreate() {
      this.dialogVisible = true
      this.dialogTitle = '新建合同'
      this.dialogComponents = 'DialogCreate'
    },
    dialogDetail(row) {
      // console.log(row)
      this.drawer = true
      this.drawerTitle = '详情'
      this.drawerComponents = 'DrawerDetail'
    }
  }
}
</script>

<style lang="scss" scoped>
// @import "../styles/table.scss";
.box {
  height: 100%;
  width: 100%;
  background-color: #fff;
  padding: 10px;
  //   margin: 20px;
}
</style>
