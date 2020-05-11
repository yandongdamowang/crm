<template>
  <div>
    <div class="ls-drawertitle">
      <div class="ls-drawertitle-l">{{ pmpPrepaymentData.contractNumber }}</div>
      <div class="ls-drawertitle-r">
        <!-- <span>
          <i class="el-icon-edit" />
        </span>&nbsp;&nbsp;&nbsp;-->
        <span />
        &nbsp;&nbsp;&nbsp;
        <!-- <el-dropdown class="ls-drawertitle-dropdown">
          <span>
            <i class="el-icon-upload2" />
          </span>
          &nbsp;&nbsp;&nbsp;
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>
              <el-upload
                ref="upload"
                :auto-upload="false"
                :on-success="submitUploadSuccess"
                :on-error="submitUploadError"
                action="https://jsonplaceholder.typicode.com/posts/"
              >
                <div slot="trigger" size="small" type="primary">本地上传</div>
              </el-upload>
            </el-dropdown-item>

            <el-dropdown-item>
              <el-upload
                ref="upload"
                :auto-upload="false"
                :on-success="submitUploadSuccess"
                :on-error="submitUploadError"
                action="https://jsonplaceholder.typicode.com/posts/"
              >
                <div size="small" type="primary" @click="submitUpload">网盘上传</div>
              </el-upload>
            </el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>-->

        <el-dropdown class="ls-drawertitle-dropdown">
          <span>
            <i class="el-icon-more" />
          </span>

          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>
              <el-dropdown class="ls-drawertitle-dropdown" @command="handleCommand">
                <span>
                  <!-- <i class="el-icon-folder-opened" /> -->
                  优先级
                </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item :command="1">高</el-dropdown-item>
                  <el-dropdown-item :command="2">中</el-dropdown-item>
                  <el-dropdown-item :command="3">低</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </el-dropdown-item>

            <el-dropdown-item>转 移</el-dropdown-item>

            <el-dropdown-item>
              <span @click="deleteContract">删 除</span>
            </el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>

        <span>丨</span>
        <span @click="closeDrawer">
          <i class="el-icon-close" />
        </span>
      </div>
    </div>

    <div class="ls-drawerform">
      <div class="ls-drawerform-header">
        <i class="el-icon-notebook-1" />&nbsp;&nbsp;&nbsp;基本信息
      </div>

      <el-form ref="form" label-width="120px" label-position="left">
        <el-row>
          <el-col :span="8">
            <el-form-item label="合同编号：">
              <span>{{ pmpPrepaymentData.contractNumber }}</span>
            </el-form-item>

            <el-form-item label="预付比例：">
              <span>{{ pmpPrepaymentData.contractName }}</span>
            </el-form-item>

            <el-form-item label="承包商：">
              <span>{{ pmpPrepaymentData.supplierName }}</span>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="合同金额：">
              <span>{{ '￥' + pmpPrepaymentData.money }}</span>
            </el-form-item>

            <el-form-item label="预付金额：">
              <span>{{ '￥' + pmpPrepaymentData.contractMoney }}</span>
            </el-form-item>

            <el-form-item label="业主：">
              <span>{{ pmpPrepaymentData.proprietor }}</span>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="款项：">
              <span>{{ pmpPrepaymentData.paymentName }}</span>
            </el-form-item>

            <el-form-item label="计划付款日期：">
              <span>{{ pmpPrepaymentData.paymentNode }}</span>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>

      <div class="ls-drawerform-header">
        <i class="el-icon-notebook-1" />
        &nbsp;&nbsp;&nbsp;优先级
        <div style="margin: 20px 0 0 20px">
          <span v-if="pmpPrepaymentData.priority == '1'">
            <el-tag type="danger">高</el-tag>
          </span>
          <span v-else-if="pmpPrepaymentData.priority == '2'">
            <el-tag type="warning">中</el-tag>
          </span>
          <span v-else-if="pmpPrepaymentData.priority == '3'">
            <el-tag type="success">低</el-tag>
          </span>

          <!-- <el-tag type="success">标签二</el-tag>
          <el-tag type="info">标签三</el-tag>
          <el-tag type="warning">标签四</el-tag>
          <el-tag type="danger">标签五</el-tag>-->
        </div>
      </div>

      <div class="ls-drawerform-header">
        <i class="el-icon-paperclip" />&nbsp;&nbsp;&nbsp;关联业务
      </div>
    </div>
    <div class="ls-drawerbox">
      <el-tab-pane label="操作记录" name="fourth">
        <div>操作记录</div>
      </el-tab-pane>

      <el-tab-pane label="附件" name="second">
        <div>附件</div>
      </el-tab-pane>

      <el-tab-pane label="审批记录" name="fifth">
        <div style="height: 300px;">
          <el-steps :active="1" direction="vertical">
            <el-step title="发起审批" />
            <el-step title="审批节点1" />
            <el-step
              title="审批节点2"
              description="
                  创建人：王某某
                  审批类型：付款申请"
            />
            <el-step title="审批节点3" description="待处理" />
          </el-steps>
        </div>
      </el-tab-pane>
    </div>
  </div>
</template>

<script>
import { prepaymentBillDetail, prepaymentSetPriority } from '@/api/contractManagement/prepaymentBill'
export default {
  name: 'DialogPayment',
  components: {
  },
  props: {
    drawerData: {
      type: Number,
      default: undefined
    },
    drawerStatus: {
      type: Boolean,
      default: undefined
    }
  },

  data() {
    return {
      pmpPrepaymentData: {},
      activeName: 'first',

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
    console.log(111, this.drawerData)
    this.retrivePrepaymentBillDetail(this.drawerData)
  },
  methods: {
    closeDrawer() {
      this.$emit('drawerStatus', false)
    },

    handleCommand(command) {
      prepaymentSetPriority({
        billId: this.drawerData,
        priority: command
      }).then(res => {
        // this.pmpPrepaymentData.priority = command
        this.retrivePrepaymentBillDetail(this.drawerData)
        console.log(res)
      })
        .catch((e) => {
          console.log('prepaymentSetPriority', e)
        })
    //   this.$message('click on item ' + command)
    },

    submitUploadSuccess() {
      alert(123)
    },
    submitUploadError() {
      alert(456)
    },

    deleteContract() {
    //   console.log(123, this.drawerData)
    //   this.$confirm('此操作将永久删除该合同, 是否继续?', '提示', {
    //     confirmButtonText: '确定',
    //     cancelButtonText: '取消',
    //     type: 'warning'

    //   }).then(() => {
    //     contactDelete({
    //       contractIds: this.drawerData
    //     // ...this.search
    //     })
    //       .then(res => {
    //         this.$message({
    //           type: 'success',
    //           message: '删除成功!'
    //         })
    //         console.log(res)

    //         this.$router.push('/contract/contract')
    //         //   this.contactListData = res.data.list
    //         //   this.contactListSubData = res.data.list
    //       })
    //       .catch(() => {

    //       })
    //   }).catch(() => {
    //     this.$message({
    //       type: 'info',
    //       message: '已取消操作'
    //     })
    //   })
    },

    retrivePrepaymentBillDetail() {
      prepaymentBillDetail({
        billId: this.drawerData
      })
        .then(res => {
          this.pmpPrepaymentData = res.data
          //   this.contactListSubData = res.data.list
          console.log('prepaymentBillDetail', this.pmpPrepaymentData)
        })
        .catch(() => {

        })
    }


  }
}
</script>

<style lang="scss" scoped>
.button {
  text-align: right;
}
.logo {
  display: inline-block;
  height: 60px;
  width: 60px;
  border-radius: 3px;
  background: rgb(131, 131, 243);
  text-align: center;
}
.title {
  font-size: 20px;
  line-height: 60px;
}
</style>
