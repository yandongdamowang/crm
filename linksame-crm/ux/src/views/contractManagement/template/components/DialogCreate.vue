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
        <el-input v-model="form.name" />
      </el-form-item>

      <el-form-item label="合同名称">
        <div class="block">

          <el-cascader
            :options="options"
            :clearable="true"
            placeholder="搜索"
            filterable
          />
        </div>
      </el-form-item>
      <el-form-item label="合同编号">
        <el-input
          v-model="form.name"
          placeholder="请输入合同编号"
        />
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
        <el-input
          v-model="form.name"
          placeholder="请输入单号"
        />
      </el-form-item>
      <el-form-item label="合同金额">
        <el-input
          v-model="form.name"
          placeholder="请输入金额"
        />
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
      title="关联业务"
      data
      width="30%"
      append-to-body
    >

      <el-input
        v-model="form.b"
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
    </el-dialog>

    <el-divider content-position="left">选择任务模板</el-divider>

    <el-cascader
      :options="options"
      :clearable="true"
      placeholder="选择里程碑节点"
      filterable
    />

    <el-button
      type="text"
      @click="dialogVisibleMilestone = true"
    >+</el-button>

    <el-dialog
      :visible.sync="dialogVisibleMilestone"
      :before-close="handleClose"
      title="新建里程碑节点"
      data
      width="300px"
      append-to-body
    >

      <el-input
        v-model="form.b"
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
