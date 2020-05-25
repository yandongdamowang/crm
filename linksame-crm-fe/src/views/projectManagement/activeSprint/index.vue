<template>
  <div style="height:100%">
    <div class="ls-header">
      <el-row :gutter="100">
        <el-col :span="20">活跃冲刺</el-col>

        <el-col :span="4">
          <el-button type="primary" @click="dialogActive = true">新建冲刺</el-button>
        </el-col>
      </el-row>
    </div>

    <div class="ls-activebox">
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="全部冲刺" name="first">
          <el-collapse v-for="(item,index) in taskSprintData" v-model="activeNames" :key="index">
            <el-collapse-item :name="item.sprintId">
              <!-- @click.native="retriveTaskList(item.sprintId,index)" -->
              <template slot="title">
                <el-row style="width: 100%;" type="flex" justify="space-between">
                  <el-col :span="14">
                    <div>
                      <span style="font-size:18px">{{ item.name }}</span>
                    </div>
                  </el-col>
                  <el-col :span="7">
                    <div>
                      <el-tag type="info">{{ item.startTime }} ~ {{ item.endTime }}</el-tag>
                    </div>
                  </el-col>

                  <el-col :span="3">
                    <div>
                      <el-button
                        v-if="item.status != 1"
                        type="primary"
                        plain
                        @click="handleStartActive(item.sprintId)"
                      >开启冲刺</el-button>
                      <el-button
                        v-if="item.status == 1"
                        type="success"
                        plain
                        @click="handleDoneActive(item.sprintId)"
                      >完成冲刺</el-button>
                      <el-button type="danger" plain @click="handleDeleteActive(item.sprintId)">删除冲刺</el-button>
                    </div>
                  </el-col>
                </el-row>
              </template>

              <div
                v-for="(item,index) in taskSprintData[index].list"
                :key="index"
                style="margin:10px 0 0 0"
              >
                <el-row type="flex" justify="space-between">
                  <el-col :span="14">
                    <span v-if="item.status == 0">
                      <el-tag type="warning">未开始</el-tag>
                    </span>
                    <span v-if="item.status == 1">
                      <el-tag type="warning">进行中</el-tag>
                    </span>
                    <span v-if="item.status == 2">
                      <el-tag type="warning">完成冲刺</el-tag>
                    </span>
                    <el-divider direction="vertical" />
                    <span>{{ item.name }}</span>
                  </el-col>
                  <el-col :span="7">
                    <el-tag type="info">{{ item.startTime }} ~ {{ item.stopTime }}</el-tag>
                  </el-col>
                  <el-col :span="3">
                    <el-avatar :size="30" style="background: rgb(36,134,228);">
                      <span style="font-size:10px">{{ item.mainRealname }}</span>
                    </el-avatar>
                  </el-col>
                </el-row>
              </div>
            </el-collapse-item>
          </el-collapse>
        </el-tab-pane>
        <el-tab-pane label="进行中的冲刺" name="second">
          <div class="crm-doing">
            <!-- justify="space-between" -->
            <el-row type="flex">
              <el-col :span="4" class="crm-doing-col">
                <div class="crm-doing-header">未开始</div>
                <draggable :list="notStarted" class="list-group" group="people" @change="log">
                  <div
                    v-for="(element, index) in notStarted"
                    :key="index"
                    class="crm-list-group-item"
                  >{{ element.name }}</div>
                </draggable>
              </el-col>

              <el-col :span="4" class="crm-doing-col">
                <div class="crm-doing-header">进行中</div>
                <draggable :list="processing" class="list-group" group="people" @change="log">
                  <div
                    v-for="(element, index) in processing"
                    :key="index"
                    class="crm-list-group-item"
                  >{{ element.name }}</div>
                </draggable>
              </el-col>

              <el-col :span="4" class="crm-doing-col">
                <div class="crm-doing-header">已完成</div>
                <draggable :list="completed" class="list-group" group="people" @change="log">
                  <div
                    v-for="(element, index) in completed"
                    :key="index"
                    class="crm-list-group-item"
                  >{{ element.name }}</div>
                </draggable>
              </el-col>
            </el-row>
          </div>
        </el-tab-pane>

        <el-tab-pane label="冲刺统计" name="third">
          <el-row :gutter="12">
            <el-col :span="8">
              <el-card shadow="hover" style="height:300px">
                <div slot="header">
                  <!-- <span>活跃的冲刺</span> -->
                  <span>{{ activeSprint.name }}</span>
                </div>
                <!-- <div style="margin: 0 0 10px 0">冲刺任务: {{ activeSprint.name }}</div> -->
                <div style="margin: 0 0 10px 0">参与人</div>
                <el-avatar
                  v-for="(itemUser,index) in activeSprint.mainUserList"
                  :key="index"
                  :size="30"
                  style="background: rgb(36,134,228);"
                >
                  <span style="font-size:10px">{{ itemUser.realname }}</span>
                </el-avatar>
                <div style="margin: 10px 0">共 {{ activeSprint.taskCount }} 个任务</div>
                <div style="margin: 10px 0; font-weight:600">冲刺目标: {{ activeSprint.aimsName }}</div>
                <el-tag
                  type="info"
                  style="margin: 10px 0"
                >{{ activeSprint.startTime }} ~ {{ activeSprint.endTime }}</el-tag>
              </el-card>
            </el-col>

            <el-col :span="8">
              <el-card shadow="hover" style="height:300px">
                <div slot="header">
                  <span>状态分布</span>
                </div>
                <el-row :gutter="12">
                  <el-col :span="8">
                    <div id="axismain" style="width: 500px;height:200px;" />
                  </el-col>
                </el-row>
              </el-card>
            </el-col>

            <el-col :span="8">
              <el-card shadow="hover" style="height:300px">
                <div slot="header">
                  <span>距离冲刺结束</span>
                </div>
                <!-- <div id="axismainOver" style="width: 500px;height:200px;" /> -->
                <div style="margin:40px 0 0 130px">
                  <el-progress :percentage="25" :format="format" type="circle" />
                </div>
              </el-card>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="24">
              <el-card shadow="hover" style="margin: 10px 0;width: 100%;">
                <div slot="header">
                  <span>任务燃尽图</span>
                </div>
                <div id="axismainBurndown" style="width: 800px;height:350px;" />
              </el-card>
            </el-col>
          </el-row>
        </el-tab-pane>
      </el-tabs>

      <el-dialog :visible.sync="dialogActive" :modal="false" title="开启冲刺" width="50%">
        <el-form>
          <el-form-item label="冲刺目标">
            <el-input v-model="formStartActive.name" placeholder="请输入内容" style="width:300px" />
          </el-form-item>

          <el-form-item label="开始时间">
            <el-date-picker
              v-model="formStartActive.startTime"
              type="datetime"
              placeholder="选择日期时间"
              default-time="12:00:00"
              value-format="yyyy-MM-dd HH:mm:ss"
            />
          </el-form-item>
          <el-form-item label="结束时间">
            <el-date-picker
              v-model="formStartActive.endTime"
              type="datetime"
              placeholder="选择日期时间"
              default-time="12:00:00"
              value-format="yyyy-MM-dd HH:mm:ss"
            />
          </el-form-item>
        </el-form>

        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogActive = false">取 消</el-button>
          <el-button type="primary" @click="createStartActice">确 定</el-button>
        </span>
      </el-dialog>
    </div>
  </div>
</template>

<script>

import moment from 'moment'
import echarts from 'echarts'

import draggable from 'vuedraggable'



export default {
  name: 'App',
  components: {
    draggable

  },

  props: {
    workId: [String, Number]

  },

  data() {
    return {
      activeName: 'first',
      activeNames: '',
      status: false,
      taskSprintData: [],
      dialogActive: false,
      activeSprint: '',
      formStartActive: {
        sprintId: this.activeNames,
        name: '',
        startTime: '',
        endTime: ''
      },
      notStartedList: [

      ],
      processingList: [
      ],
      completedList: []
    }
  },



  watch: {
    // filterText(val) {
    //   this.$refs.tree.filter(val)
    // },
    // workId() {
    //   this.retriveFolderList()
    // }
  },

  created() {

  },

  mounted() {
    this.retriveTaskSprintList()
    this.retriveActiveList()
    this.retriveActiving()
    this.initAxisBurndown()
  },


  methods: {


    add: function() {
      this.list.push({ name: 'Juan' })
    },
    replace: function() {
      this.list = [{ name: 'Edgard' }]
    },
    clone: function(el) {
      return {
        name: el.name + ' cloned'
      }
    },
    log: function(evt) {
      window.console.log(evt)
    },




    format(percentage) {
      const currentTime = moment(moment().format('YYYY-MM-DD HH:mm:ss')).diff(moment(this.activeSprint.endTime), 'days')
      const fullTime = moment(this.activeSprint.endTime).diff(moment(this.activeSprint.endTime), 'days')
      console.log('距离时间', currentTime)
      console.log('总时间', fullTime)
      if (fullTime == 0) {
        return `剩余 ${0} 天`
      } else {
        return `剩余 ${currentTime / fullTime} 天`
      }
    },

    retriveActiving() {
      this.$request
        .post(`/taskSprint/queryCountAndTask`)
        .then(res => {
          console.log('进行中的冲刺', res)
          this.notStarted = res.data.notStarted.notStartedList
          this.processing = res.data.processing.processingList
          this.completed = res.data.completed.completedList
        }).catch(e => {
          console.log(e)
        })
    },



    retriveTaskSprintList() {
      const data = []
      this.$request
        .post(`/taskSprint/queryList?pageType=0`)
        .then(res => {
          console.log('冲刺列表', res)
          //   setTimeout(() => { console.log(123) }, 10000)
          res.data.forEach((item, index) => {
            // console.log(item.sprintId)
            data.push(item)
            this.$request
              .post(`/task/queryList?pageType=0&sprintId=${item.sprintId}`)
              .then(res => {
                // console.log('冲刺列表任务', res)
                // this.taskListData[index] = res.data
                data[index].list = res.data
                data[index].long = res.data.length
              }).catch(e => {
                console.log(e)
              })
          })
        }).catch(e => {
          console.log(e)
        })

      console.log('处理数据', data)
      this.taskSprintData = data
    },
    handleClick() {
      this.retriveTaskSprintList()
      this.retriveActiveList()
      this.initAxis()
    //   this.initAxisOver()
    },

    handleStartActive(sprintId) {
      this.$request
        .post(`/taskSprint/setSprint`, {
          sprintId: sprintId,
          status: 1
        })
        .then(res => {
        //   console.log('开启冲刺', res)
          this.$message.success('开启冲刺成功')
          this.retriveTaskSprintList()
        }).catch(e => {
          console.log(e)
        })
    },
    handleDoneActive(sprintId) {
      this.$request
        .post(`/taskSprint/setSprint`, {
          sprintId: sprintId,
          status: 2
        })
        .then(res => {
        //   console.log('完成冲刺', res)
          this.$message.success('完成冲刺成功')
          this.retriveTaskSprintList()
        }).catch(e => {
          console.log(e)
        })
    },
    handleDeleteActive(sprintId) {
      console.log('删除id', sprintId)
      this.$request
        .post(`/taskSprint/delSprint`, {
          sprintId: sprintId
        })
        .then(res => {
        //   console.log('删除冲刺', res)
          this.$message.success('删除冲刺成功')
          this.retriveTaskSprintList()
        }).catch(e => {
          console.log(e)
        })
    },

    createStartActice() {
      this.$request
        .post(`/taskSprint/setSprint`, {
          ...this.formStartActive,
          status: 0
        })
        .then(res => {
          console.log('新建冲刺', res)
          this.$message.success('新建冲刺成功')
          this.dialogActive = false
          this.retriveTaskSprintList()
        }).catch(e => {
          console.log(e)
        })
    },



    retriveTaskList(val, index) {
    //   console.log(val)
      this.$request
        .post(`/task/queryList?pageType=0&sprintId=${val}`)
        .then(res => {
          console.log('冲刺列表任务', res)
          this.taskListData[index] = res.data
        }).catch(e => {
          console.log(e)
        })
    },



    retriveActiveList(val, index) {
    //   console.log(val)
      this.$request
        .post(`/taskSprint/queryList?pageType=0&status=1`)
        .then(res => {
          console.log('活跃的冲刺', res)
          this.activeSprint = res.data[0]
        //   this.taskListData[index] = res.data
        }).catch(e => {
          console.log(e)
        })
    },







    initAxis() {
      this.$request
        .post(`/taskSprint/queryCountAndTask`)
        .then(res => {
          //   第一个就是待处理  第二个就是处理中  第三个是已完成
          console.log('任务个数', res)
          var axisChart = echarts.init(document.getElementById('axismain'))
          var option = {
            tooltip: {
              trigger: 'item',
              formatter: '{a} <br/>{b}: {c} ({d})'
            },
            legend: {
              orient: 'vertical',
              left: 10,
              data: ['待开始', '进行中', '已完成']
            },
            series: [
              {
                name: '任务状态',
                type: 'pie',
                radius: ['50', '70'],
                avoidLabelOverlap: false,
                label: {
                  show: false,
                  position: 'center'
                },
                emphasis: {
                  label: {
                    show: true,
                    fontSize: '24',
                    fontWeight: 'bold'
                  }
                },
                labelLine: {
                  show: false
                },
                data: [
                  { value: res.data.notStarted.notStartedCount, name: '待开始' },
                  { value: res.data.processing.processingList, name: '进行中' },
                  { value: res.data.completed.completedCount, name: '已完成' }
                ]
              }
            ]
          }


          axisChart.setOption(option, true)
          this.axisOption = option
          this.axisChart = axisChart
        }).catch(e => {
          console.log(e)
        })
    },



    initAxisBurndown() {
      this.$request
        .post(`/taskSprint/querySprintInfo?sprintId=1`)
        .then(res => {
          console.log('燃尽图', res)
          var axisChart = echarts.init(document.getElementById('axismainBurndown'))
          var option = {
            title: {
              // 表格标题
              //   text: '付款金额对比',
              //   // 表格标题
              //   textStyle: {
              //     fontWeight: 'normal',
              //     fontSize: 16
              //   }
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
              data: ['期望值', '剩余值'],
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
                name: '期望值',
                // 图表类型
                // type: 'bar',
                type: 'line',
                yAxisIndex: 0,
                // 设置颜色
                color: '#0097F5',
                // 图表数据
                data: res.data.expectList
              },
              {
                name: '剩余值',
                type: 'line',
                yAxisIndex: 0,
                color: '#52C41A',
                data: res.data.remainingList
              }
            ],


            // X 轴设置
            xAxis: [
              {
                type: 'category',
                name: '时间',
                data: res.data.dateList,
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
                name: '任务数',
                data: [],
                axisTick: {
                  alignWithLabel: true,
                  lineStyle: { width: 0 }
                },
                axisLabel: {
                  color: '#BDBDBD',
                  formatter: '{value} 个'
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
        }).catch(e => {
          console.log(e)
        })
    }

  }
}
</script>

<style scoped lang="scss">
.tree_menu {
  position: fixed;
  display: block;
  z-index: 20000;
  background-color: #fff;
  padding: 5px 0;
  border: 1px solid #ebeef5;
  border-radius: 4px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);

  ul {
    margin: 0;
    padding: 0;
  }
  ul li {
    list-style: none;
    margin: 0;
    padding: 0 15px;
    font-size: 14px;
    line-height: 30px;
    cursor: pointer;
  }
  ul li:hover {
    background-color: #ebeef5;
  }
}
/deep/ path {
  fill: transparent !important;
}

/deep/.el-tabs__nav-wrap::after {
  background-color: transparent !important;
}

/deep/ .el-collapse-item__header {
  border-bottom: 0px solid #ebeef5 !important;
}

/deep/ .el-collapse {
  border-top: 0px solid #ebeef5 !important;
  border-bottom: 1px solid #ebeef5 !important;
}

/* 表头 */
/deep/ .el-table th {
  background-color: rgb(247, 247, 250);
}

/deep/ .el-table__header-wrapper thead div {
  background-color: rgb(247, 247, 250);
}

/deep/ .el-tree-node__expand-icon.is-leaf {
  //   color: rgb(144, 147, 153);
  color: transparent;
}
/deep/ .el-tree-node__expand-icon {
  color: rgb(144, 147, 153);
}

/deep/ .el-tree-node__content {
  height: 40px;
  font-size: 14px;
  font-family: Microsoft YaHei;
}
::-webkit-scrollbar-track-piece {
  background: transparent !important;
}

.ls-activebox {
  background: white;
  margin: 10px;
  padding: 20px 20px 20px 30px;
}

.crm-doing {
  width: 100%;
  max-height: 100%;
  padding: 10px;
  vertical-align: top;
  border-radius: 4px;
  background: #fff;
  margin-right: 14px;
  position: relative;
}
.crm-doing-col {
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  margin: 0 20px 0 0px;
  width: 250px;
}
.crm-list-group-item {
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  padding: 50px 8px 0 10px;
  margin: 10px 10px 10px 10px;
  /* margin-bottom: 10px; */
  /* margin-top: 1px; */
  /* margin-left: 1px; */
  height: 100px;
  border-radius: 3px;
  border-left: 2px solid transparent;
  cursor: pointer;
  overflow: hidden;
  position: relative;
}
.crm-doing-header {
  padding: 20px 0px 15px 15px;
  color: #333;
  font-size: 18px;
}
</style>
