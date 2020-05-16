<template>
  <div style="height:100%">
    <div class="ls-header">
      仪表盘
      <div class="ls-header-r">
        <el-date-picker
          v-model="datetime"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          value-format="yyyy-MM-dd"
        />
      </div>

      <div class="ls-header-r">
        <el-button type="primary" plain @click="getDate('day')">今日</el-button>
        <el-button type="primary" plain @click="getDate('week')">本周</el-button>
        <el-button type="primary" plain @click="getDate('month')">本月</el-button>
      </div>
      <!-- {{ datetime }} -->
      <div class="ls-header-r">
        <el-select v-model="headerGrep.project" placeholder="选择项目">
          <el-option label="项目一" value="shanghai" />
          <el-option label="项目二" value="beijing" />
        </el-select>
      </div>
    </div>

    <div class="ls-box">
      <el-row :gutter="12">
        <el-col :span="24">
          <el-card shadow="hover">
            <div class="ls-datashow">
              <span style="font-size:28px">数据概览</span>
            </div>
            <div class="content">
              <span class="logo" style="color:rgb(164,204,100)">
                <i class="el-icon-document-copy" />
              </span>
              <span style="font-size:26px">{{ contractDashboardData.contractCount }}</span>
              <span>个</span>
              <span style="font-size:14px">总合同数</span>
            </div>

            <div class="content">
              <span class="logo" style="color:rgb(0,183,255)">
                <i class="el-icon-document-checked" />
              </span>
              <span style="font-size:30px">{{ contractDashboardData.contractCount }}</span>
              <span>个</span>
              <span style="font-size:14px">完成合同数</span>
            </div>

            <div class="content">
              <span class="logo" style="color:rgb(226,100,115)">
                <i class="el-icon-money" />
              </span>
              <span style="font-size:30px">{{ contractDashboardData.waitPayment }}</span>
              <span>元</span>
              <span style="font-size:14px">预计付款金额</span>
            </div>

            <div class="content">
              <span class="logo" style="color:rgb(205,205,73)">
                <i class="el-icon-bank-card" />
              </span>
              <span style="font-size:30px">{{ contractDashboardData.cumulativePayment }}</span>
              <span>元</span>
              <span style="font-size:14px">总支付累计付款金额</span>
            </div>
          </el-card>
        </el-col>
      </el-row>

      <div style="margin: 10px 0 0 0">
        <el-row :gutter="12">
          <el-col :span="16">
            <el-card shadow="hover">
              <div id="axismain" style="width: 100%;height:300px;" />
            </el-card>
          </el-col>
          <el-col :span="8">
            <el-card shadow="hover">
              <div class="table-title">
                <span>参与的企业</span>
                <span style="float: right; display: inline-block;">16 个</span>
              </div>

              <el-divider />
              <el-table :data="workbenchTableData" style="width: 100%">
                <el-table-column type="index" label="排名" width="80" />
                <el-table-column prop="a" label="供应商" width="180" />
                <el-table-column prop="b" label="合同数" />
                <el-table-column prop="c" label="合同金额" />
              </el-table>
            </el-card>
          </el-col>
        </el-row>
      </div>
    </div>
  </div>
</template>

<script>

import echarts from 'echarts'
import moment from 'moment'
import { contractDashboard } from '@/api/contractManagement/contacts'
import { prepaymentReport, paymentReport } from '@/api/contractManagement/workbench'
export default {
  name: 'Main',
  components: {

  },
  filters: {},
  data() {
    return {
      datetime: ['2020-01-01', '2020-05-29'],
      echartXData: [],
      echartPrepaymentItemData: [],
      echartPaymentItemData: [],
      headerGrep: {
        project: '',
        type: []
      },

      contractDashboardData: {},
      workbenchTableData: [{
        'a': '中核华兴工程有限公司',
        'b': '24',
        'c': '1,284'
      },
      {
        'a': '中核华兴工程有限公司',
        'b': '24',
        'c': '1,284'
      },
      {
        'a': '中核华兴工程有限公司',
        'b': '24',
        'c': '1,284'
      }]
    }
  },
  computed: {

  },

  watch: {
    datetime(val) {
      this.retriveContractDashboard()
    }
  },
  mounted() {
    // this.$store.commit('SET_HEADERNAME', '仪表盘')
    this.retriveContractDashboard()
    this.retrivePrepaymentReport()
    this.retrivePaymentReport()
  },
  methods: {

    getDate(date) {
      const weekOfday = parseInt(moment().format('d')) // 计算今天是这周第几天 周日为一周中的第一天
      if (date == 'day') {
        this.datetime = [moment().format('YYYY-MM-DD'), moment().format('YYYY-MM-DD')]
        // console.log(this.datetime)
      } else if (date == 'week') {
        this.datetime = [moment().subtract(weekOfday - 1, 'days').format('YYYY-MM-DD'), moment().add(7 - weekOfday, 'days').format('YYYY-MM-DD')]
      } else if (date == 'month') {
        this.datetime = [moment().add('month', 0).format('YYYY-MM') + '-01', moment(moment().add('month', 0).format('YYYY-MM') + '-01').add('month', 1).add('days', -1).format('YYYY-MM-DD')]
      }
    },

    // 预付款
    retrivePrepaymentReport() {
      const xData = []
      const itemData = []
      prepaymentReport({
        startTime: this.datetime[0],
        endTime: this.datetime[1]
      })
        .then(res => {
          console.log('retrivePrepaymentReport', res)
          res.dashboard.forEach((item, index) => {
            xData.push(item.paymentNode)
            itemData.push(item.money)
          })
          this.echartXData = xData
          this.echartPrepaymentItemData = itemData
          this.initAxis()
        })

        .catch((e) => {
          console.log(e)
        })
    },

    // 付款
    retrivePaymentReport() {
      const xData = []
      const itemData = []
      paymentReport({
        startTime: this.datetime[0],
        endTime: this.datetime[1]
      })
        .then(res => {
          console.log('paymentReport', res)
          res.dashboard.forEach((item, index) => {
            xData.push(item.paymentTime)
            itemData.push(item.practicaAdvanced)
          })
          this.echartXData = xData
          this.echartPaymentItemData = itemData
          this.initAxis()
        })

        .catch((e) => {
          console.log(e)
        })
    },






    retriveContractDashboard() {
      contractDashboard({
        startTime: this.datetime[0],
        endTime: this.datetime[1]
      })
        .then(res => {
          this.contractDashboardData = res
        })
        .catch(() => {

        })
    },



    initAxis() {
      console.log('initAxis', this.echartXData)
      //   console.log('initAxis', this.echartPrepaymentItemData)
      var axisChart = echarts.init(document.getElementById('axismain'))
      var option = {
        title: {
          // 表格标题
          text: '付款金额对比',
          // 表格标题
          textStyle: {
            fontWeight: 'normal',
            fontSize: 16
          }
        },
        grid: {
          top: '80px',
          left: '10px',
          //   right: '20px',
          bottom: '10px',
          containLabel: true,
          borderColor: 'red'
        },

        // 设置折线图颜色
        // color: ['red', 'pink', 'green'],
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            // 坐标轴指示器，鼠标移上去的效果
            // 'line' | 'shadow'
            type: 'shadow'
          }
        },

        // 设置图例
        legend: {
          // 必须和 series 的 name 一致
          data: ['预付款', '付款'],
          // 这个字段控制形状 类型包括 circle，rect ，roundRect，triangle，diamond，pin，arrow，none
          icon: 'circle',
          // 设置宽度
          itemWidth: 10,
          // 设置高度
          itemHeight: 10,
          // 设置间距
          itemGap: 40
        },


        series: [
          {
            name: '预付款',
            // 图表类型
            // type: 'bar',
            type: 'line',
            yAxisIndex: 0,
            // 设置颜色
            color: '#0097F5',
            // 图表数据
            data: this.echartPrepaymentItemData
          },
          {
            name: '付款',
            type: 'line',
            yAxisIndex: 0,
            color: '#52C41A',
            data: this.echartPaymentItemData
          }
        ],


        // X 轴设置
        xAxis: [
          {
            type: 'category',
            name: '时间',
            data: this.echartXData,
            axisTick: {
              alignWithLabel: true,
              lineStyle: { width: 0 }
            },
            axisLabel: {
              color: '#BDBDBD'
            },
            // X 轴的轴线设置
            axisLine: {
              // 设置轴线的颜色
              lineStyle: { color: '#BDBDBD' }
            },
            splitLine: {
              show: false
            }
          }
        ],
        // Y 轴设置
        yAxis: [
          {
            type: 'value',
            // Y 轴的名字
            name: '金额',
            data: [],
            axisTick: {
              alignWithLabel: true,
              lineStyle: { width: 0 }
            },
            axisLabel: {
              color: '#BDBDBD',
              formatter: '{value} 元'
            },
            // Y 轴的轴线设置
            axisLine: {
              lineStyle: { color: '#BDBDBD' }
            },
            splitLine: {
              show: false
            }
          }
        ]

      }

      axisChart.setOption(option, true)
      this.axisOption = option
      this.axisChart = axisChart
    }
  }
}
</script>

<style lang="scss" scoped>
.logo {
  font-size: 26px;
}
.content {
  width: 25%;
  margin: 30px 0px;
  font-size: 26px;
  float: left;
}

.table-title {
  text-align: justify;
  font-weight: 600;
  font-size: 20px;
  // height: calc(100% - 50px);
}
.ls-box {
  background-color: rgb(245, 246, 249) !important;
  padding: 0px 0px;
}
.ls-datashow {
  width: 100%;
  height: 30px;
  border-bottom: 1px solid rgba(220, 223, 230, 1);
  padding: 0 0 50px 0px;

  font-size: 18px;
  font-family: Microsoft YaHei;
  font-weight: 500;

  color: rgba(48, 49, 51, 1);
  //   letter-spacing: 4px;
}
</style>
