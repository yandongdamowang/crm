<template>
  <div style="height:100%">
    <div class="ls-header"> 仪表盘
      <div class="ls-header-r">
        <el-date-picker
          v-model="headerGrep.datetime"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          value-format="yyyy-MM-dd"
        />
      </div>

      <div class="ls-header-r">
        <el-radio-group v-model="headerGrep.type">
          <el-radio-button label="今日">今日</el-radio-button>
          <el-radio-button label="本周" />
          <el-radio-button label="本月" />
          <el-radio-button label="全年" />
        </el-radio-group>

      </div>
      <!-- {{headerGrep.type}} -->

      <div class="ls-header-r">
        <el-select
          v-model="headerGrep.project"
          placeholder="选择项目"
        >
          <el-option
            label="项目一"
            value="shanghai"
          />
          <el-option
            label="项目二"
            value="beijing"
          />
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

              <span
                class="logo"
                style="color:rgb(164,204,100)"
              > <i class="el-icon-document-copy" /> </span>
              <span style="font-size:26px"> {{ contractDashboardData.contractCount }} </span>
              <span> 个 </span>
              <span style="font-size:14px"> 总合同数 </span>
            </div>

            <div class="content">
              <span
                class="logo"
                style="color:rgb(0,183,255)"
              > <i class="el-icon-document-checked" /></span>
              <span style="font-size:30px"> {{ contractDashboardData.contractCount }}</span>
              <span> 个 </span>
              <span style="font-size:14px"> 完成合同数 </span>
            </div>

            <div class="content">
              <span
                class="logo"
                style="color:rgb(226,100,115)"
              > <i class="el-icon-money" /></span>
              <span style="font-size:30px"> {{ contractDashboardData.waitPayment }} </span>
              <span> 元 </span>
              <span style="font-size:14px"> 预计付款金额 </span>
            </div>

            <div class="content">
              <span
                class="logo"
                style="color:rgb(205,205,73)"
              > <i class="el-icon-bank-card" /></span>
              <span style="font-size:30px"> {{ contractDashboardData.cumulativePayment }}</span>
              <span> 元 </span>
              <span style="font-size:14px"> 总支付累计付款金额 </span>
            </div>

          </el-card>
        </el-col>

      </el-row>

      <div style="margin: 10px 0 0 0">
        <el-row :gutter="12">
          <el-col :span="16">
            <el-card shadow="hover">
              <div
                id="axismain"
                style="width: 600px;height:300px;"
              />
            </el-card>
          </el-col>
          <el-col :span="8">
            <el-card shadow="hover">
              <div class="table-title">
                <span>参与的企业</span>
                <span style="float: right; display: inline-block;">16 个</span>
              </div>

              <el-divider />
              <el-table
                :data="workbenchTableData"
                style="width: 100%"
              >
                <el-table-column
                  type="index"
                  label="排名"
                  width="80"
                />
                <el-table-column
                  prop="a"
                  label="供应商"
                  width="180"
                />
                <el-table-column
                  prop="b"
                  label="合同数"
                />
                <el-table-column
                  prop="c"
                  label="合同金额"
                />

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
import { contractDashboard } from '@/api/contractManagement/contacts'
export default {
  name: 'Main',
  components: {

  },
  filters: {},
  data() {
    return {
      headerGrep: {
        datetime: ['2020-01-01', '2020-05-29'],
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
    this.$store.commit('SET_HEADERNAME', '仪表盘')
    this.retriveContractDashboard()
    this.initAxis()
  },
  methods: {
    retriveContractDashboard() {
    //   console.log(123)
      contractDashboard({
        startTime: this.headerGrep.datetime[0],
        endTime: this.headerGrep.datetime[1]
      })
        .then(res => {
          console.log(res)
          this.contractDashboardData = res
        })
        .catch(() => {

        })
    },



    initAxis() {
      var axisChart = echarts.init(document.getElementById('axismain'))

      var option = {
        title: {
          text: '付款金额对比',
          textStyle: {
            fontWeight: 'normal',
            fontSize: 16
          }
        },
        color: ['#6ca2ff', '#6ac9d7', '#ff7474'],
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            // 坐标轴指示器，坐标轴触发有效
            type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        legend: {
          data: ['计划付款金额', '实际付款金额', 'XXX'],
          bottom: '0px',
          itemWidth: 14
        },
        grid: {
          top: '80px',
          left: '20px',
          right: '20px',
          bottom: '20px',
          containLabel: true,
          borderColor: '#fff'
        },
        xAxis: [
          {
            type: 'category',
            data: [
              '1月',
              '2月',
              '3月',
              '4月',
              '5月',
              '6月',
              '7月',
              '8月',
              '9月',
              '10月',
              '11月',
              '12月'
            ],
            axisTick: {
              alignWithLabel: true,
              lineStyle: { width: 0 }
            },
            axisLabel: {
              color: '#BDBDBD'
            },
            /** 坐标轴轴线相关设置 */
            axisLine: {
              lineStyle: { color: '#BDBDBD' }
            },
            splitLine: {
              show: false
            }
          }
        ],
        yAxis: [
          {
            type: 'value',
            name: '合同金额',
            data: [
              '1月',
              '2月',
              '3月',
              '4月'

            ],
            axisTick: {
              alignWithLabel: true,
              lineStyle: { width: 0 }
            },
            axisLabel: {
              color: '#BDBDBD',
              formatter: '{value} 元'
            },
            /** 坐标轴轴线相关设置 */
            axisLine: {
              lineStyle: { color: '#BDBDBD' }
            },
            splitLine: {
              show: false
            }
          },
          {
            type: 'value',
            name: '完成率',
            axisTick: {
              alignWithLabel: true,
              lineStyle: { width: 0 }
            },
            position: 'right',
            axisLabel: {
              color: '#BDBDBD',
              formatter: '{value} %'
            },
            /** 坐标轴轴线相关设置 */
            axisLine: {
              lineStyle: { color: '#BDBDBD' }
            },
            splitLine: {
              show: false
            }
          }
        ],
        series: [
          {
            name: '合同金额',
            type: 'bar',
            yAxisIndex: 0,
            barWidth: 15,
            data: [1, 2, 3, 4, 5]
          },
          {
            name: '目标',
            type: 'bar',
            yAxisIndex: 0,
            barWidth: 15,
            data: [2, 3, 4, 5, 6]
          },
          {
            name: '完成率',
            type: 'line',
            yAxisIndex: 1,
            data: [6, 7, 8, 9, 10]
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
