<template>
  <div>

    <div class="box">
      <el-row :gutter="12">
        <el-col :span="8">
          <el-card shadow="hover">
            <div
              class="logo"
              style="background:rgb(164,204,100)"
            >
              <i class="el-icon-circle-check" />
            </div>

            <div class="content">
              <div> 合同数量 </div>
              <span style="font-size:30px"> 1,236 </span>
              <span> 个 </span>
            </div>

          </el-card>
        </el-col>
        <el-col :span="8">
          <el-card shadow="hover">

            <div
              class="logo"
              style="background:rgb(226,100,115)"
            >
              <i class="el-icon-alarm-clock" />
            </div>

            <div class="content">
              <div> 预计付款金额 </div>
              <span style="font-size:30px"> 1,236 </span>
              <span> 万 </span>
            </div>

          </el-card>
        </el-col>
        <el-col :span="8">
          <el-card shadow="hover">

            <div
              class="logo"
              style="background:rgb(205,205,73)"
            >
              <i class="el-icon-document-add" />
            </div>

            <div class="content">
              <div> 总支付累计付款金额 </div>
              <span style="font-size:30px"> 1,236 </span>
              <span> 万 </span>
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

export default {

  name: 'Main',
  components: {

  },
  filters: {},
  data() {
    return {
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
  mounted() {
    this.initAxis()
  },
  methods: {
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
  height: 80px;
  width: 150px;
  /* position: relative; */
  display: inline-block;
  font-size: 50px;
  text-align: center;
  line-height: 80px;
  margin: 0 10px 0 0px;
  border-radius: 2px;
  color: white;
}
.content {
  display: inline-block;
  left: 50%;
  top: 20px;
  //   float: left;
}
.box {
  height: 100%;
  width: 100%;
  background-color: rgb(245, 246, 249);
  padding: 10px;
  //   margin: 20px;
}
.table-title {
  text-align: justify;
  font-weight: 600;
  font-size: 20px;
  // height: calc(100% - 50px);
}
</style>
