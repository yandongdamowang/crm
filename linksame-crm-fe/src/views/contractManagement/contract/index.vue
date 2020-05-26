<template>
  <div style="height:100%">
    <div class="ls-header">
      合同管理
      <div class="ls-header-r">
        <!-- <el-select v-model="grep" placeholder="选择项目">
          <el-option label="项目一" value="shanghai" />
          <el-option label="项目二" value="beijing" />
        </el-select>-->
      </div>
    </div>

    <div class="ls-box">
      <el-form ref="form" :inline="true">
        <el-form-item label="合同编号：">
          <el-input v-model="search.contractNumber" placeholder="请输入内容" />
        </el-form-item>

        <el-form-item label="客户/承包商：">
          <el-input v-model="search.customerId" placeholder="请输入内容" />
        </el-form-item>

        <el-form-item>
          <span>
            <el-button type="primary" @click="retriveContactList">查询</el-button>
          </span>
          <span>
            <el-button @click="searchReset">重置</el-button>
          </span>
        </el-form-item>
      </el-form>

      <div>
        <span>
          <el-button type="primary" @click="dialogCreate">新建合同</el-button>
        </span>
        <span>
          <el-dropdown>
            <el-button>批量操作</el-button>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>
                <div @click="deleteContract">批量删除</div>
              </el-dropdown-item>

              <el-dropdown-item>
                <div @click="dialogPayment">批量修改支付比例</div>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </span>

        <span>
          <el-button>导出</el-button>
        </span>
        <span>
          <el-button icon="el-icon-refresh-right" @click="retriveContactList" />
        </span>
      </div>

      <el-table
        :data="contactListData"
        :header-cell-style="{background:'#F5F7FA',color:'#606266'}"
        border
        style="margin: 20px 0 0 0"
        height="75%"
        @selection-change="selectTable"
      >
        <el-table-column type="selection" width="55" />
        <el-table-column prop="contractNumber" label="合同编号" width="150">
          <template slot-scope="scope">
            <el-button
              type="text"
              size="small"
              @click="drawerDetail(scope.row)"
            >{{ scope.row.contractNumber }}</el-button>
          </template>
        </el-table-column>
        <el-table-column prop="contractName" label="合同名称" width="120" />
        <el-table-column prop="customerId" label="客户/承包商" width="120" />
        <el-table-column prop="money" label="合同总金额(￥)" width="120" />
        <el-table-column prop="requiredSigningTime" label="要求签署时间" width="150" />
        <el-table-column prop="actualSigningTime" label="实际签署时间" width="150" />
        <el-table-column prop="purchaseOrderNumber" label="DRP采购单号" width="120" />
        <el-table-column prop="ownerUserName" label="经办人" width="120" />

        <el-table-column
          v-for="(item,index) in contactListSubData"
          :key="index"
          :label="item"
          align="center"
        >
          <el-table-column label="支付比例" width="120">
            <template slot-scope="scope">
              <div>{{ scope.row.billList[item].paymentRatio + "%" }}</div>
            </template>
          </el-table-column>
          <el-table-column label="支付金额" width="120">
            <template slot-scope="scope">
              <div>{{ "￥" + scope.row.billList[item].paymentAmount }}</div>
            </template>
          </el-table-column>

          <el-table-column label="累计支付比例" width="120">
            <template slot-scope="scope">
              <div>{{ scope.row.billList[item].cumulativePaymentRatio + "%" }}</div>
            </template>
          </el-table-column>

          <el-table-column label="累计支付金额" width="120">
            <template slot-scope="scope">
              <div>{{ "￥" + scope.row.billList[item].cumulativepayment }}</div>
            </template>
          </el-table-column>
        </el-table-column>
      </el-table>

      <div class="ls-pagination">
        <el-pagination
          :current-page="pageCurrent"
          :page-sizes="[2, 10, 20]"
          :page-size="pageSize"
          :total="pageTotal"
          layout="total, sizes, prev, pager, next, jumper"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        />
      </div>

      <!-- 弹框 -->
      <el-dialog :visible.sync="dialogPaymentStatus" :title="dialogTitle" :width="dialogwidth">
        <el-form ref="form" :model="formData" label-width="120px">
          <el-form-item label="选择时间">
            <el-date-picker v-model="formData.time" type="month" placeholder="选择时间" />
          </el-form-item>

          <el-form-item label="调整后金额：">
            <el-input v-model="formData.a" placeholder="请输入内容">
              <template slot="prepend">￥</template>
            </el-input>
          </el-form-item>

          <el-form-item label="调整金额：">
            <span v-if="formData.a">￥{{ formData.a-formData.money }}</span>
          </el-form-item>

          <!-- <el-form-item label=""> -->
          <el-table :data="paymentListData" style="width: 100%">
            <el-table-column prop="contractName" label="合同名称" width="180" />
            <el-table-column prop="money" label="支付金额" width="180" />
            <el-table-column prop="costPercentage" label="支付比例" />
            <el-table-column label="调整后支付金额">
              <template slot-scope="scope">
                <el-input v-model="formData.b" placeholder="请输入金额" />
              </template>
            </el-table-column>
            <el-table-column prop="address" label="调整后支付比例" />
          </el-table>
          <!-- </el-form-item> -->
        </el-form>

        <span slot="footer">
          <el-button @click="dialogPaymentStatus = false">取 消</el-button>
          <el-button type="primary" @click="createPayment">确 定</el-button>
        </span>
      </el-dialog>

      <el-dialog :visible.sync="dialogCreateStatus" :title="dialogTitle" fullscreen width="75%">
        <el-divider content-position="left">基本信息</el-divider>
        <el-form ref="form" :model="form" :inline="true" label-width="120px">
          <el-row>
            <el-col :span="8">
              <el-form-item label="选择项目">
                <el-select
                  v-model="form.pmpContract.projectId"
                  placeholder="请选项目"
                  @change="selectChange()"
                >
                  <el-option
                    v-for="(item,index) in projectData"
                    :key="index"
                    :label="item.name"
                    :value="item.workId"
                  />
                </el-select>
              </el-form-item>

              <el-form-item label="合同类型">
                <el-select v-model="form.pmpContract.contractType" placeholder="请选合同类型">
                  <el-option
                    v-for="(item,index) in contactTypeData"
                    :key="index"
                    :label="item.templateName"
                    :value="item.templateId"
                  />
                </el-select>
              </el-form-item>

              <el-form-item label="合同采购时间">
                <el-date-picker
                  v-model="form.pmpContract.contractPurchaseStartTime"
                  type="date"
                  value-format="yyyy-MM-dd"
                  placeholder="选择日期"
                  align="right"
                />
              </el-form-item>

              <el-form-item label="里程碑">
                <el-select v-model="form.pmpContract.milestoneNodes" placeholder="请选里程碑">
                  <el-option
                    v-for="(item,index) in milestoneData"
                    :key="index"
                    :label="item.className"
                    :value="item.classId"
                  />
                </el-select>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="合同名称">
                <el-input v-model="form.pmpContract.contractName" placeholder="请输入合同名称" />
              </el-form-item>

              <el-form-item label="合同开始日期">
                <el-date-picker
                  v-model="form.pmpContract.beginTime"
                  type="date"
                  value-format="yyyy-MM-dd"
                  placeholder="选择日期"
                  align="right"
                />
              </el-form-item>

              <el-form-item label="经办人">
                <el-select v-model="form.pmpContract.ownerUserId" placeholder="请选合同经办人">
                  <el-option
                    v-for="(item,index) in userData"
                    :key="index"
                    :label="item.parentName"
                    :value="item.parentId"
                  />
                </el-select>
              </el-form-item>

              <el-form-item label="承包商">
                <el-select v-model="form.pmpContract.customerId" placeholder="请选合同承包商">
                  <el-option
                    v-for="(item,index) in customerData"
                    :key="index"
                    :label="item.customerName"
                    :value="item.customerId"
                  />
                </el-select>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="合同编号">
                <el-input v-model="form.pmpContract.contractNumber" placeholder="请输入合同编号" />
              </el-form-item>

              <el-form-item label="合同结束日期">
                <el-date-picker
                  v-model="form.pmpContract.endTime"
                  type="date"
                  value-format="yyyy-MM-dd"
                  placeholder="选择日期"
                  align="right"
                />
              </el-form-item>

              <el-form-item label="DRP采购单号">
                <el-input v-model="form.pmpContract.drpNumber" placeholder="请输入单号" />
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>

        <el-divider content-position="left">合同金额</el-divider>

        <el-form ref="form" :model="form" :inline="true" label-width="120px">
          <el-form-item label="合同总金额">
            <el-input v-model="form.pmpContract.money" placeholder="请输入金额">
              <template slot="append">￥</template>
            </el-input>
          </el-form-item>

          <el-divider content-position="left">支付基数</el-divider>

          <div v-for="(itemNumber,indexNumber) in form.contractCardinalNumbers" :key="indexNumber">
            <el-form-item label>
              <el-input
                v-model="form.contractCardinalNumbers[indexNumber].cardinalNumberName"
                placeholder="请输入基数名称"
                style="width:300px"
              />
            </el-form-item>

            <el-form-item label>
              <el-input
                v-model="form.contractCardinalNumbers[indexNumber].cardinalNumberMoney"
                placeholder="请输入基数金额"
                style="width:300px"
                @input="computeCardinalNumber()"
              >
                <template slot="append">￥</template>
              </el-input>
            </el-form-item>

            <el-form-item label>
              <el-button
                v-if="indexNumber == 0"
                type="primary"
                circle
                icon="el-icon-plus"
                @click="detailpmpCardinalNumber('add')"
              />

              <el-button
                v-if="indexNumber != 0"
                type="danger"
                icon="el-icon-delete"
                circle
                @click="detailpmpCardinalNumber('delete',indexNumber)"
              />
            </el-form-item>
          </div>
        </el-form>

        <el-divider content-position="left">支付方式</el-divider>

        <el-form
          v-for="(item,index) in form.pmpContractPayments"
          ref="form"
          :key="index"
          :model="form"
          :inline="true"
          label-width="130px"
        >
          <el-row>
            <el-col :span="24" class="crm-contract-form">
              <el-form-item label>
                <el-input v-model="form.pmpContractPayments[index].paymentName" placeholder="款项" />
              </el-form-item>

              <el-form-item label>
                <el-select
                  v-model.number="form.pmpContractPayments[index].cardinalNumberId"
                  placeholder="请选支付基数"
                >
                  <el-option
                    v-for="(item,index) in form.contractCardinalNumbers"
                    :key="index"
                    :label="item.cardinalNumberName"
                    :value="item.cardinalNumberIndexe"
                  />
                </el-select>
              </el-form-item>

              <el-form-item label>
                <el-input
                  v-model="form.pmpContractPayments[index].costPercentage"
                  placeholder="支付比例"
                  @input="computeMoney(form.pmpContractPayments[index].costPercentage,index)"
                >
                  <template slot="append">%</template>
                </el-input>
              </el-form-item>

              <el-form-item label>
                <el-input
                  v-model="form.pmpContractPayments[index].money"
                  disabled
                  placeholder="支付金额随比例变动"
                >
                  <template slot="append">￥</template>
                </el-input>
              </el-form-item>

              <el-form-item label>
                <el-date-picker
                  v-model="form.pmpContractPayments[index].paymentNode"
                  type="date"
                  value-format="yyyy-MM-dd"
                  placeholder="支付时间"
                  align="right"
                />
              </el-form-item>

              <el-button
                v-if="index == 0"
                type="primary"
                circle
                icon="el-icon-plus"
                @click="detailpmpContractPayments('add')"
              />

              <el-button
                v-if="index != 0"
                type="danger"
                icon="el-icon-delete"
                circle
                @click="detailpmpContractPayments('delete',index)"
              />
            </el-col>
          </el-row>

          <!-- <task style="height:900px" /> -->
        </el-form>

        <el-divider content-position="left">任务模板</el-divider>

        <el-form ref="form" :model="form" :inline="true" label-width="120px">
          <el-button type="text" @click="detailpmpTaskTemplate('add',index)">添加模板</el-button>
          <el-card v-for="(item,index) in form.tasks" :key="index">
            <div slot="header">
              <el-input
                v-model="form.tasks[index].name"
                placeholder="请输入任务模板名"
                style="width:300px"
              />

              <el-button type="text" @click="detailpmpTaskTemplate('delete',index)">删除模板</el-button>

              <div style="float: right; padding: 3px 0">
                <el-date-picker
                  v-model="form.tasks[index].starttime"
                  style="width:150px"
                  type="date"
                  value-format="yyyy-MM-dd"
                  placeholder="开始时间"
                  align="right"
                />

                <el-date-picker
                  v-model="form.tasks[index].stopTime"
                  style="width:150px"
                  type="date"
                  value-format="yyyy-MM-dd"
                  placeholder="截止时间"
                  align="right"
                />

                <el-select
                  v-model="form.tasks[index].mainUserId"
                  placeholder="负责人"
                  style="width:120px"
                >
                  <el-option
                    v-for="(item,index) in ownerRoleData"
                    :key="index"
                    :label="item.realname"
                    :value="item.userId"
                  />
                </el-select>
              </div>
            </div>

            <el-form ref="form" :model="form" :inline="true" label-width="130px">
              <el-row>
                <el-col :span="24">
                  <div v-for="(itemTask,indexTask) in form.tasks[index].task" :key="indexTask">
                    <el-form-item label>
                      <el-input
                        v-model="form.tasks[index].task[indexTask].name"
                        placeholder="任务名称"
                      />
                    </el-form-item>

                    <el-form-item label>
                      <el-select
                        v-model.number="form.tasks[index].task[indexTask].taskId"
                        placeholder="绑定款项"
                      >
                        <el-option
                          v-for="(item,index) in form.pmpContractPayments"
                          :key="index"
                          :label="item.paymentName"
                          :value="item.paymentClause"
                        />
                      </el-select>
                    </el-form-item>

                    <el-form-item label>
                      <el-date-picker
                        v-model="form.tasks[index].task[indexTask].starttime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="开始时间"
                        align="right"
                      />
                    </el-form-item>

                    <el-form-item label>
                      <el-date-picker
                        v-model="form.tasks[index].task[indexTask].stopTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="截止时间"
                        align="right"
                      />
                    </el-form-item>

                    <el-form-item label>
                      <el-select
                        v-model="form.tasks[index].task[indexTask].mainUserId"
                        placeholder="负责人"
                      >
                        <el-option
                          v-for="(item,index) in ownerRoleData"
                          :key="index"
                          :label="item.realname"
                          :value="item.userId"
                        />
                      </el-select>
                    </el-form-item>

                    <el-button
                      v-if="indexTask == 0"
                      type="primary"
                      circle
                      icon="el-icon-plus"
                      @click="detailpmpTask('add',index,indexTask)"
                    />

                    <el-button
                      v-if="indexTask != 0"
                      type="danger"
                      icon="el-icon-delete"
                      circle
                      @click="detailpmpTask('delete',index,indexTask)"
                    />
                  </div>
                </el-col>
              </el-row>
            </el-form>
          </el-card>

          <!-- <el-form-item label="合同总金额">
            <el-input v-model="form.pmpContract.money" placeholder="请输入金额">
              <template slot="append">￥</template>
            </el-input>
          </el-form-item>

          <el-divider content-position="left">支付基数</el-divider>
          <el-form-item label="支付基数">
            <el-input v-model="form.pmpContract.money" placeholder="请输入金额">
              <template slot="append">￥</template>
            </el-input>
          </el-form-item>-->
        </el-form>

        <el-divider content-position="left">关联业务</el-divider>
        <related-business :all-data="allData" :margin-left="'0'" @checkInfos="checkInfos" />

        <span slot="footer">
          <el-button @click="dialogCreateStatus = false">取 消</el-button>
          <el-button type="primary" @click="createContact">确 定</el-button>
        </span>
      </el-dialog>

      <el-drawer
        :title="drawerTitle"
        :visible.sync="drawer"
        :with-header="false"
        :destroy-on-close="true"
        size="70%"
        @open="drawerDetail()"
      >
        <component
          :is="drawerComponents"
          :drawer-data="drawerData"
          :row-data="rowData"
          @drawerStatus="drawerStatus"
        />
      </el-drawer>
    </div>
  </div>
</template>

<script>

import relatedBusiness from '@/components/relatedBusiness'

import { paymentList } from '@/api/contractManagement/contacts'
import task from '@/views/projectManagement/task/index'

import { contactList, contactDelete } from '@/api/contractManagement/contacts'
export default {
  name: 'ContractIndex',
  components: {
    DrawerDetail: () => import('./components/DrawerDetail'),
    task,
    relatedBusiness
  },

  data() {
    return {
      initId: 1,
      initTaskId: 1,
      initCardinalId: 1,
      allData: {},
      relevanceAll: {},


      dialogVisibleMilestone: false,
      customerData: [],
      userData: [],
      moneyMax: 0,
      //   form: {},
      form: {
        'pmpContract': {
          'contractType': '',
          'contractName': undefined,
          'contractNumber': '',
          'contractPurchaseStartTime': undefined,
          'contractPurchaseEndTime': undefined,
          'beginTime': '',
          'endTime': '',
          'customerId': undefined,
          'ownerUserId': undefined,
          'drpNumber': '',
          'projectId': undefined,
          'milestoneNodes': undefined,
          'money': 0
        },
        // 基数
        'contractCardinalNumbers': [{
          'cardinalNumberIndexe': 1,
          'cardinalNumberName': '基数 ' + this.initCardinalId++,
          'cardinalNumberPercentage': 30,
          'cardinalNumberMoney': 0
        }],

        'tasks': [{
          'mainUserId': undefined,
          'name': '采购任务',
          'starttime': '',
          'stopTime': '',
          'task': [{
            'taskId': undefined,
            'mainUserId': undefined,
            'name': '',
            'starttime': '',
            'stopTime': ''

          }]
        },
        {
          'mainUserId': undefined,
          'name': '施工任务',
          'starttime': '',
          'stopTime': '',
          'task': [{
            'taskId': undefined,
            'mainUserId': undefined,
            'name': '',
            'starttime': '',
            'stopTime': ''
          }]
        },
        {
          'mainUserId': undefined,
          'name': '设计任务',
          'starttime': '',
          'stopTime': '',
          'task': [{
            'taskId': undefined,
            'mainUserId': undefined,
            'name': '',
            'starttime': '',
            'stopTime': ''
          }]
        }, {
          'mainUserId': undefined,
          'name': '变更任务',
          'starttime': '',
          'stopTime': '',
          'task': [{
            'taskId': undefined,
            'mainUserId': undefined,
            'name': '',
            'starttime': '',
            'stopTime': ''
          }]
        }],
        'pmpContractPayments': [{
          'cardinalNumberId': undefined,
          'paymentName': '款项' + this.initId,
          'costPercentage': undefined,
          'money': undefined,
          'paymentClause': this.initId,
          'paymentNode': ''
        }]
      },
      paymentListData: [],
      formData: {
        a: '',
        b: '',
        c: ''
      },
      dialogComponents: '',
      dialogTitle: '',
      dialogwidth: '',
      dialogPaymentStatus: false,
      dialogCreateStatus: false,
      drawerComponents: '',
      drawer: false,
      drawerTitle: '',
      drawerData: '',
      rowData: '',
      contactListData: [],
      contactTypeData: [],
      milestoneData: [],
      projectData: [],
      ownerRoleData: [],

      contactListSubData: [],

      pageCurrent: 1,
      pageTotal: 1,
      pageSize: 10,

      value: '',
      grep: '',
      search: {
        contractNumber: '',
        customerId: ''
      },
      deleteData: undefined
    }
  },
  computed: {

  },

  watch: {
    // watchCardinalNumber(val) {
    //   console.log(123123)
    // //   form.contractCardinalNumbers[indexNumber].cardinalNumberMoney
    // }
  },



  mounted() {
    this.retriveContactList()
    this.retriveCustomerList()
    this.retriveUserList()
    // this.retriveOwnerRoleList()
    this.retrivePaymentList()
    // this.retriveMilestone()
    this.retriveContractTypeList()
    this.retriveProject()
  },
  methods: {
    selectTable(select) {
      const deleteContract = []
      select.forEach((item, index) => {
        // console.log(item.contractId)
        deleteContract.push(item.contractId)
      })
      this.deleteData = deleteContract.join(',')
    },
    drawerStatus(status) {
      this.drawer = status
    },

    handleCurrentChange(val) {
      console.log(`当前页: ${val}`)
      this.pageCurrent = val
      this.retriveContactList()
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`)
      this.pageSize = val
      this.retriveContactList()
    },

    retriveProject() {
      this.$request.post('/work/queryWorkNameList')
        .then(res => {
          console.log('项目', res)
          this.projectData = res.data
        })
        .catch(() => {

        })
    },


    retriveCustomerList() {
      this.$request({
        url: '/CrmCustomer/queryList?pageType=0',
        method: 'post'
      }).then(res => {
        console.log('承包商', res.data)
        this.customerData = res.data
      }).catch(e => {
        console.log(e)
      })
    },

    retriveContractTypeList() {
      this.$request({
        url: '/pmpContractTemplate/queryList?pageType=0',
        method: 'post'
      }).then(res => {
        console.log('合同类型', res.data)
        this.contactTypeData = res.data
      }).catch(e => {
        console.log(e)
      })
    },



    retrivePaymentList() {
      paymentList({
        'month': '2020-04'
      })
        .then(res => {
          console.log(res)
          this.paymentListData = res.list
          this.formData = res
        })
        .catch(() => {

        })
    },

    retriveUserList() {
      this.$request({
        url: '/system/user/queryUserList?pageType=0&roleId=8',
        method: 'post'
      }).then(res => {
        console.log('经办人', res.data)
        this.userData = res.data
      }).catch(e => {
        console.log(e)
      })
    },


    computeMoney(data, index) {
      // form.pmpContractPayments[index].cardinalNumberId
      this.form.contractCardinalNumbers.forEach(item => {
        if (item.cardinalNumberIndexe == this.form.pmpContractPayments[index].cardinalNumberId) {
        //   console.log(111, item.cardinalNumberMoney)
          this.form.pmpContractPayments[index].money = parseInt(item.cardinalNumberMoney) * data / 100
        }
      })
    //   this.form.pmpContractPayments[index].money = this.form.pmpContract.money * data / 100
    },


    computeCardinalNumber() {
      let all = 0
      this.form.contractCardinalNumbers.forEach(item => {
        all = all + parseInt(item.cardinalNumberMoney)
      })
      if (all > parseInt(this.form.pmpContract.money)) {
        this.$message.error('支付基数不能大于合同总金额')
      }
    },


    selectChange() {
      this.$request({
        url: `/work/queryOwnerRoleList`,
        method: 'post',
        data: {
          workId: this.form.pmpContract.projectId
        }
      }).then(res => {
        console.log('负责人', res.data)
        this.ownerRoleData = res.data
      }).catch(e => {
        console.log(e)
      })

      this.$request.post('/work/queryTaskByWorkId', {
        orderType: 4,
        workId: this.form.pmpContract.projectId
      }, { headers: {
        'Content-Type': 'application/json;charset=UTF-8'
      }})
        .then(res => {
          console.log('里程碑', res)
          this.milestoneData = res.data
        })
        .catch(() => {

        })
    },

    // selectCardinalNumber() {
    //     this.contractCardinalNumbers.forEach(item=>{

    //     })
    // },



    createPayment() {
      this.$request({
        url: 'pmpContractPayment/updates',
        method: 'post',
        data: this.form,
        headers: {
          'Content-Type': 'application/json;charset=UTF-8'
        }
      }).then(res => {
        console.log(res)
        this.dialogCreateStatus = false

        this.$message({
          type: 'success',
          message: '添加成功!'
        })
      })
        .catch((e) => {
          console.log('contactAdd', e)
        })
    },

    createContact() {
      console.log('创建合同', this.form)
      let moneyTotal = 0
      this.form.pmpContractPayments.forEach((item, index) => {
        moneyTotal = moneyTotal + parseInt(item.costPercentage)
      })
      console.log('总和', moneyTotal)
      if (moneyTotal != 100) {
        this.$message.error('支付比例不等于 100%')
      } else {
        this.dialogCreateStatus = false
      }

      this.$request({
        url: 'pmpContract/add',
        method: 'post',
        data: this.form,
        headers: {
          'Content-Type': 'application/json;charset=UTF-8'
        }
      }).then(res => {
        console.log(res)
        this.dialogCreateStatus = false
        this.retriveContactList()
        this.$message({
          type: 'success',
          message: '添加成功!'
        })
      })
        .catch((e) => {
          console.log('contactAdd', e)
        })
    },


    detailpmpContractPayments(pararm, index) {
    //   console.log(pararm)
      this.initId++
      pararm == 'delete' ? this.form.pmpContractPayments.splice(index, 1) : this.form.pmpContractPayments.push({
        'cardinalNumberId': undefined,
        'paymentName': '款项' + this.initId,
        'costPercentage': undefined,
        'money': undefined,
        'paymentClause': this.initId,
        'paymentNode': ''
      })
    },


    detailpmpCardinalNumber(pararm, indexNumber) {
    //   console.log(pararm)
    //   this.initId++
      pararm == 'delete' ? this.form.contractCardinalNumbers.splice(indexNumber, 1) : this.form.contractCardinalNumbers.push({
        'cardinalNumberIndexe': this.initCardinalId++,
        'cardinalNumberName': '基数 ' + this.initCardinalId++,
        'cardinalNumberPercentage': 30,
        'cardinalNumberMoney': 0
      })
    },

    detailpmpTaskTemplate(pararm, index) {
    //   console.log(pararm)
    //   this.initId++
      pararm == 'delete' ? this.form.tasks.splice(index, 1) : this.form.tasks.push({
        'mainUserId': undefined,
        'name': '',
        'starttime': '',
        'stopTime': '',
        'task': [{
          'taskId': undefined,
          'mainUserId': undefined,
          'name': '',
          'starttime': '',
          'stopTime': ''
        }]
      })
    },


    detailpmpTask(pararm, index, indexTask) {
    //   console.log(pararm)
    //   this.initId++
      pararm == 'delete' ? this.form.tasks[index].task.splice(index, 1) : this.form.tasks[index].task.push({
        'taskId': undefined,
        'mainUserId': undefined,
        'name': '',
        'starttime': '',
        'stopTime': ''
      })
    },




    deleteContract() {
      this.$confirm('此操作将永久选中合同, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'

      }).then(() => {
        contactDelete({
          contractIds: this.deleteData
        // ...this.search
        })
          .then(res => {
            this.$message({
              type: 'success',
              message: '删除成功!'
            })

            this.retriveContactList()
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

    retriveContactList() {
      contactList({
        // productId: this.id
        page: this.pageCurrent,
        limit: this.pageSize,
        ...this.search
      })
        .then(res => {
          console.log('合同列表', res.data)
          this.contactListData = res.data.list
          this.pageTotal = res.data.totalRow
          res.data.list.forEach(item => {
            this.contactListSubData = Object.keys(item.billList)
          })
        })
        .catch(() => {

        })
    },





    searchReset() {
      this.search = {
        contractNumber: '',
        customerId: ''
      }
      this.retriveContactList()
    },

    // commitData() {
    //   this.$refs.child.createContact()
    //   this.dialogVisible = false
    // },

    dialogPayment() {
      this.dialogPaymentStatus = true
      this.dialogTitle = '支付比例调整'
      //   this.dialogComponents = 'DialogPayment'
      this.dialogwidth = '50%'
    },
    dialogCreate() {
      this.dialogCreateStatus = true
      this.dialogTitle = '新建合同'

      this.dialogwidth = '70%'
      this.form = {
        'pmpContract': {
          'contractType': '',
          'contractName': undefined,
          'contractNumber': '',
          'contractPurchaseStartTime': undefined,
          'contractPurchaseEndTime': undefined,
          'beginTime': '',
          'endTime': '',
          'customerId': undefined,
          'ownerUserId': undefined,
          'drpNumber': '',
          'projectId': undefined,
          'milestoneNodes': undefined,
          'money': 0
        },
        // 基数
        'contractCardinalNumbers': [{
          'cardinalNumberIndexe': 1,
          'cardinalNumberName': '基数 ' + this.initCardinalId++,
          'cardinalNumberPercentage': 30,
          'cardinalNumberMoney': 0
        }],

        'tasks': [{
          'mainUserId': undefined,
          'name': '采购任务',
          'starttime': '',
          'stopTime': '',
          'task': [{
            'taskId': undefined,
            'mainUserId': undefined,
            'name': '',
            'starttime': '',
            'stopTime': ''

          }]
        },
        {
          'mainUserId': undefined,
          'name': '施工任务',
          'starttime': '',
          'stopTime': '',
          'task': [{
            'taskId': undefined,
            'mainUserId': undefined,
            'name': '',
            'starttime': '',
            'stopTime': ''
          }]
        },
        {
          'mainUserId': undefined,
          'name': '设计任务',
          'starttime': '',
          'stopTime': '',
          'task': [{
            'taskId': undefined,
            'mainUserId': undefined,
            'name': '',
            'starttime': '',
            'stopTime': ''
          }]
        }, {
          'mainUserId': undefined,
          'name': '变更任务',
          'starttime': '',
          'stopTime': '',
          'task': [{
            'taskId': undefined,
            'mainUserId': undefined,
            'name': '',
            'starttime': '',
            'stopTime': ''
          }]
        }],
        'pmpContractPayments': [{
          'cardinalNumberId': undefined,
          'paymentName': '款项' + this.initId,
          'costPercentage': undefined,
          'money': undefined,
          'paymentClause': this.initId,
          'paymentNode': ''
        }]
      }
    },

    checkInfos(val) {
      this.relevanceAll = val
      console.log('checkInfos', val)
    },

    drawerDetail(row) {
      this.drawer = true
      this.drawerTitle = row.contractName
      this.drawerComponents = 'DrawerDetail'
      console.log(row)
      this.drawerData = row.contractId
      this.rowData = row
    }

  }

}
</script>

<style lang="scss" scoped>
.crm-contract-form {
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




