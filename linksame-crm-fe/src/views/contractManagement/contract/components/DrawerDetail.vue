<template>
  <div>
    <div class="ls-drawertitle">
      <div class="ls-drawertitle-l">{{ pmpContractData.contractNumber }}</div>
      <div class="ls-drawertitle-r">
        <!-- <span>
          <i class="el-icon-edit" />
        </span>&nbsp;&nbsp;&nbsp;
        <span>
          <i class="el-icon-folder-opened" />
        </span>
        &nbsp;&nbsp;&nbsp;
        <el-dropdown class="ls-drawertitle-dropdown">
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
            <el-form-item label="合同类型：">
              <span>1191111-REC-工程-S1-珠海一键</span>
            </el-form-item>

            <el-form-item label="合同采购时间：">
              <span>{{ pmpContractData.contractPurchaseStartTime }}</span>
            </el-form-item>
            <caption />

            <el-form-item label="合同编号：">
              <span>{{ pmpContractData.contractNumber }}</span>
            </el-form-item>

            <el-form-item label="经办人：">
              <span>{{ pmpContractData.agent }}</span>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="合同名称：">
              <span>{{ pmpContractData.contractName }}</span>
            </el-form-item>

            <el-form-item label="承包商：">
              <span>{{ pmpContractData.supplierId }}</span>
            </el-form-item>

            <el-form-item label="DRP采购单号：">
              <span>{{ pmpContractData.purchaseOrderNumber }}</span>
            </el-form-item>

            <el-form-item label="合同到期时间：">
              <span>{{ pmpContractData.contractPurchaseEndTime }}</span>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="签署时间：">
              <span>{{ pmpContractData.actualSigningTime }}</span>
            </el-form-item>

            <el-form-item label="合同金额：">
              <span>{{ pmpContractData.money }}</span>
            </el-form-item>

            <el-form-item label="合同开始时间：">
              <span>{{ pmpContractData.contractPurchaseStartTime }}</span>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </div>
    <div class="ls-drawerbox">
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="付款明细" name="first">
          <el-table :data="tableData" style="width: 100%">
            <el-table-column prop="a" label="预计付款时间" width="180" />
            <el-table-column prop="b" label="款项" width="180" />
            <el-table-column prop="c" label="比例" />
            <el-table-column prop="d" label="金额" />
            <el-table-column prop="e" label="实际支付比例" />
            <el-table-column prop="f" label="实际支付金额" />
            <el-table-column prop="g" label="关联项目任务" />
            <el-table-column prop="h" label="状态" />
            <el-table-column prop="i" label="备注" />
          </el-table>
        </el-tab-pane>
        <el-tab-pane label="附件" name="second">
          <div>附件</div>
        </el-tab-pane>
        <el-tab-pane label="回款信息" name="third">
          <div>回款信息</div>
        </el-tab-pane>
        <el-tab-pane label="操作记录" name="fourth">
          <div>操作记录</div>
        </el-tab-pane>
        <el-tab-pane label="审批记录" name="fifth">
          123
          <!-- <div style="height: 300px;">
            <el-steps :active="1" direction="vertical">
              <el-step title="发起审批" />
              <el-step title="审批节点1" />
              <el-step
                title="审批节点2"
                description="
                  创建人：王某某
                  审批类型：付款申请"
              />
              <el-step title="审批节点3" description="
                待处理" />
            </el-steps>
          </div>-->
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script>
import { contactDetail, contactDelete } from '@/api/contractManagement/contacts'
export default {
  name: 'DialogPayment',
  components: {
  },
  props: {
    drawerData: {
      type: Object,
      default: undefined
    },
    drawerStatus: {
      type: Boolean,
      default: undefined
    }
  },

  data() {
    return {
      pmpContractData: {},
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

  //   watch: {
  //     drawerData() {
  //       console.log(123, this.drawerData)
  //     }
  //   },

  mounted() {
    // console.log(111, this.drawerData)
    // console.log(222, this.drawerStatus)
    this.retriveContactDetail()
  },
  methods: {
    closeDrawer() {
      this.$emit('drawerStatus', false)
    },

    submitUpload() {
      this.$refs.upload.submit()
    },
    submitUploadSuccess() {
      alert(123)
    },
    submitUploadError() {
      alert(456)
    },




    deleteContract() {
      this.$confirm('此操作将永久删除该合同, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        contactDelete({
          contractIds: this.drawerData

        })
          .then(res => {
            this.$message({
              type: 'success',
              message: '删除成功!'
            })
            console.log(res)

            this.$router.push('/contract/contract')
            //   this.contactListData = res.data.list
            //   this.contactListSubData = res.data.list
          })
          .catch(() => {

          })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消操作'
        })
      })
    },





    retriveContactDetail() {
      contactDetail({
        contractId: this.drawerData
      })
        .then(res => {
          console.log(res)
          this.pmpContractData = res.pmpContract
        //   this.contactListSubData = res.data.list
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
</style>
