<template>
  <div class="app-container">
    <svg id="gantt" @click="click()" />
  </div>
</template>

<script>
import Gantt from 'frappe-gantt' // 引入模块
// import 'dhtmlx-gantt/codebase/dhtmlxgantt.css'
// import 'dhtmlx-gantt/codebase/locale/locale_cn' // 本地化
export default {
  name: 'DhtmlxGantt',
  data() {
    return {
      tasks: [
        {
          id: 'Task 1',
          name: 'Redesign website',
          start: '2016-12-28',
          end: '2016-12-31',
          progress: 20,
          dependencies: 'Task 2, Task 3'
        }

      ]

    }
  },
  mounted() {
    // 初始化
    // gantt.init(this.$refs.gantt)
    const gantt = new Gantt('#gantt', this.tasks)
    // 数据解析
    gantt.parse(this.tasks)
    gantt.config.details_on_dblclick = true
  },
  methods: {
    click() {
      console.log(123)
    }
  }
}
</script>

<style scoped lang="scss">
.left-container {
  height: 600px;
}
</style>
