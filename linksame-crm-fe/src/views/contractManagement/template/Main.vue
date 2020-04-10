<template>
  <div class="box">

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
            @click="drawerDetail( scope.row)"
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

    <!-- <el-drawer
      :visible.sync="drawer"
      :title="drawerTitle"
      size="70%"
    >
      <component :is="drawerComponents" />
    </el-drawer>  -->

    <!-- </el-form> -->
  </div>
</template>

<script>

export default {
  name: 'ContractIndex',
  components: {
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
    dialogCreate() {
      this.dialogVisible = true
      this.dialogTitle = '新建合同模板'
      this.dialogComponents = 'DialogCreate'
      this.dialogwidth = '90%'
    },
    drawerDetail(row) {
      // console.log(row)
      this.drawer = true
      this.drawerTitle = '详情'
      this.drawerComponents = 'DrawerDetail'
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
</style>
