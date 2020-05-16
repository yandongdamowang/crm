<template>
  <div>
    <div class="ls-drawertitle">
      <div class="ls-drawertitle-l">{{ rebateBillData.receivableType }}</div>
      <div class="ls-drawertitle-r">
        <el-dropdown class="ls-drawertitle-dropdown">
          <span>
            <i class="el-icon-more" />
          </span>

          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>转 移</el-dropdown-item>
            <el-dropdown-item>
              <span @click="deleteContract">删 除</span>
            </el-dropdown-item>
            <el-dropdown-item>
              <span @click="updateRebate">修 改</span>
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
        <i class="el-icon-notebook-1" />&nbsp;&nbsp;&nbsp;账户信息
      </div>

      <el-form ref="form" label-width="120px" label-position="left">
        <el-row>
          <el-col :span="8">
            <el-form-item label="收款账户：">
              <span>{{ rebateBillData.collectionAccount }}</span>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="开户行：">
              <span>{{ rebateBillData.collectionBank }}</span>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="收款账号：">
              <span>{{ rebateBillData.collectionNumber }}</span>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>

      <div class="ls-drawerform-header">
        <i class="el-icon-notebook-1" />
        &nbsp;&nbsp;&nbsp;回款信息
        <el-form ref="form" label-width="120px" label-position="left">
          <el-row>
            <el-col :span="8">
              <el-form-item label="款项：">
                <span>{{ rebateBillData.receivableType }}</span>
              </el-form-item>
              <el-form-item label="回款时间：">
                <span>{{ rebateBillData.collectingTime }}</span>
              </el-form-item>
              <el-form-item label="备注：">
                <span>{{ rebateBillData.remark }}</span>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="客户：">
                <span>{{ rebateBillData.money }}</span>
              </el-form-item>
              <el-form-item label="付款方式：">
                <span>{{ rebateBillData.paymentMethod }}</span>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="回款金额：">
                <span>{{ '￥' + rebateBillData.receivableAmount }}</span>
              </el-form-item>
              <el-form-item label="经办人：">
                <span>{{ rebateBillData.agent }}</span>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
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

    <!-- 弹框 -->
    <el-dialog
      :visible.sync="dialogStatus"
      :destroy-on-close="true"
      :modal="false"
      title="修改回款"
      width="50%"
    >
      <component ref="updateDialog" :is="dialogComponents" :data="rebateBillDetailData" />

      <span slot="footer">
        <el-button @click="dialogStatus = false">取 消</el-button>
        <el-button type="primary" @click="commitData">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { rebateBillDetail } from '@/api/contractManagement/rebateBill'
export default {
  name: 'DialogPayment',
  components: {
    DialogCreate: () => import('./DialogCreate')
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
      rebateBillData: {},
      dialogStatus: false,
      activeName: 'first',
      dialogComponents: '',
      rebateBillDetailData: '',
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
    console.log('receivableRecordsId', this.drawerData)
    this.retriveRebateBillDetail()
  },
  methods: {
    closeDrawer() {
      this.$emit('drawerStatus', false)
    },

    commitData() {
      this.$refs.updateDialog.createContact()
      this.dialogVisible = false
    },


    updateRebate() {
      this.dialogStatus = true
      this.dialogComponents = 'DialogCreate'
    },
    deleteContract() {

    },


    retriveRebateBillDetail() {
      rebateBillDetail({
        receivableRecordsId: this.drawerData
      })
        .then(res => {
          console.log('rebateBillDetail', res)
          this.rebateBillData = res.data
          this.rebateBillDetailData = res.data
          //   this.contactListSubData = res.data.list
        })
        .catch((e) => {
          console.log('rebateBillDetail', e)
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
