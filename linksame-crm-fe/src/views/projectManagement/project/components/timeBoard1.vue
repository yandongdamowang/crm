<template>
  <div class="app-container">
    <el-input v-model="filterText" placeholder="输入关键字进行过滤" />

    <el-tree
      ref="tree"
      :data="data"
      :props="defaultProps"
      :filter-node-method="filterNode"
      class="filter-tree"
      default-expand-all
    />
  </div>
</template>

<script>
// import gantt from 'dhtmlx-gantt' // 引入模块
export default {

  data() {
    return {
      filterText: '',
      data: [],
      defaultProps: {
        children: 'list',
        label: 'name'
      }
    }
  },
  watch: {
    filterText(val) {
      this.$refs.tree.filter(val)
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
      this.data = res.data
    }).catch(e => {
      console.log(e)
    })
  },


  methods: {
    filterNode(value, data) {
      if (!value) return true
      return data.label.indexOf(value) !== -1
    }
  }
}

</script>

<style scoped lang="scss">
.left-container {
  height: 600px;
}
</style>
