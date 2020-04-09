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

      <el-form-item label="">

        <el-tabs
          v-model="activeName"
          @tab-click="handleClick"
        >
          <el-tab-pane
            label="付款明细"
            name="first"
          >
            <el-table
              :data="tableData"
              style="width: 100%"
            >
              <el-table-column
                prop="a"
                label="预计付款时间"
                width="180"
              />
              <el-table-column
                prop="b"
                label="款项"
                width="180"
              />
              <el-table-column
                prop="c"
                label="比例"
              />
              <el-table-column
                prop="d"
                label="金额"
              />
              <el-table-column
                prop="e"
                label="实际支付比例"
              />
              <el-table-column
                prop="f"
                label="实际支付金额"
              />
              <el-table-column
                prop="g"
                label="关联项目任务"
              />
              <el-table-column
                prop="h"
                label="状态"
              />
              <el-table-column
                prop="i"
                label="备注"
              />
            </el-table>
          </el-tab-pane>
          <el-tab-pane
            label="附件"
            name="second"
          >
            <div>附件</div>
          </el-tab-pane>
          <el-tab-pane
            label="回款信息"
            name="third"
          >
            <div>回款信息</div>
          </el-tab-pane>
          <el-tab-pane
            label="操作记录"
            name="fourth"
          >
            <div>操作记录</div>
          </el-tab-pane>
          <el-tab-pane
            label="审批记录"
            name="fifth"
          >
            <div style="height: 300px;">
              <el-steps
                :active="1"
                direction="vertical"
              >
                <el-step title="发起审批" />
                <el-step title="审批节点1" />
                <el-step
                  title="审批节点2"
                  description="
                  创建人：王某某
                  审批类型：付款申请"
                />
                <el-step
                  title="审批节点3"
                  description="
                待处理"
                />
              </el-steps>
            </div>
          </el-tab-pane>
        </el-tabs>

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
