<template>
  <div class="app-container">
    <div ref="gantt" class="left-container" />
  </div>
</template>

<script>
import gantt from 'dhtmlx-gantt' // 引入模块
import 'dhtmlx-gantt/codebase/dhtmlxgantt.css'
// import 'dhtmlx-gantt/codebase/locale/locale_cn' // 本地化
export default {
  name: 'DhtmlxGantt',
  data() {
    return {
      tasks: {
        data: [
          // { id: 1, text: '任务1', start_date: '15-04-2020', end_date: '15-08-2020', personName: '张总', duration: 3, progress: 0.6 },
          // { id: 2, text: '任务2', start_date: '18-04-2020', personName: '李总', duration: 3, progress: 0.4 },
          // { id: 3, text: '任务1', start_date: '20-04-2020', personName: '赵总', duration: 3, progress: 0.4, parent: 2 },
          // { id: 4, text: '任务1', type: gantt.config.types.project, open: true },
          // { id: 5, text: '任务1', type: gantt.config.types.task, start_date: '12-04-2020', duration: 3, parent: 1 },
          // { id: 6, text: '任务1', type: gantt.config.types.milestone, parent: 1, start_date: '14-04-2020' }
          { id: 11, text: '合同采购', type: gantt.config.types.project, open: true },
          { id: 12, text: '建筑设计合同', type: gantt.config.types.task, start_date: '12-01-2020', end_date: '15-04-2020', duration: 3, parent: 11 },
          { id: 13, text: '精装修设计合同', type: gantt.config.types.task, start_date: '12-03-2020', end_date: '15-07-2020', duration: 3, parent: 11 },
          { id: 14, text: '景观园林设计合同', type: gantt.config.types.task, start_date: '12-06-2020', end_date: '15-10-2020', duration: 3, parent: 11 },
          { id: 15, text: '弱电智能化设计合同', type: gantt.config.types.task, start_date: '12-09-2020', end_date: '15-12-2020', duration: 3, parent: 11 },

          { id: 21, text: '决策阶段', type: gantt.config.types.project, open: true },
          { id: 22, text: '启动四叶草BCD设计', type: gantt.config.types.task, start_date: '12-01-2020', end_date: '15-04-2020', duration: 3, parent: 21 },
          { id: 23, text: '完成弱电施工图设计且提交预算', type: gantt.config.types.task, start_date: '12-03-2020', end_date: '15-07-2020', duration: 3, parent: 21 },
          { id: 24, text: '完成玻璃幕墙施工图设计且提交预算', type: gantt.config.types.task, start_date: '12-06-2020', end_date: '15-10-2020', duration: 3, parent: 21 },
          { id: 25, text: '提交全套燃气设计图纸', type: gantt.config.types.task, start_date: '12-09-2020', end_date: '15-12-2020', duration: 3, parent: 21 }




        ],
        links: [
          // { id: 1, source: 1, target: 2, type: '0' }
        ]
      }
    }
  },
  mounted() {
    this.$request({
      url: '/work/queryTaskByWorkId',
      method: 'post',
      data: {
        'workId': '1',
        'orderType': 4
      },
      headers: {
        'Content-Type': 'application/json'
        // '123': '456'
      }
    }).then(res => {
      console.log('任务列表', res)
      //   this.data = res.data
      //   let layerA = []
      //   res.data.forEach((itemA, indexA) => {
      //     itemA.list.forEach((itemB, indexB) => {
      //       console.log(itemB)
      //     })
      //   })

      console.log(this.flatten(res.data))
    }).catch(e => {
      console.log(e)
    })


    //
    gantt.config.columns = [
      { name: 'text', label: '任务阶段', width: '*', tree: true }
      // { name: 'start_date', label: 'Start time1', align: 'center' },
      // { name: 'duration', label: 'Duration1', align: 'center' },
      // { name: 'personName', label: 'personName', align: 'center' },
      // { name: 'add', label: '', width: 44 }
    ]
    // gantt.config.details_on_dblclick = true
    gantt.config.fit_tasks = true
    gantt.config.scales = [
      { unit: 'year', step: 1, format: '%Y' },
      { unit: 'month', step: 3, format: '%F' }
      // { unit: 'day', step: 1, format: '%j, %D' }
    ]
    // 在时间线上增加一行年份显示
    // gantt.config.subscales = [
    //   {
    //     unit: 'year',
    //     step: 1,
    //     date: '%Y'
    //   }
    // ]
    gantt.init(this.$refs.gantt)
    gantt.parse(this.tasks)
  },
  methods: {


    flatten(arr) {
      var res = []
      for (var i = 0; i < arr.length; i++) {
        if (Array.isArray(arr[i])) {
          res = res.concat(this.flatten(arr[i]))
        } else {
          res.push(arr[i])
        }
      }
      return res
    }



  }

}
</script>

<style scoped lang="scss">
.left-container {
  height: 600px;
}
</style>
