<template>

  <div style="height: calc(100% - 50px);">
    <div class="tabs">

      <el-tabs
        v-model="editableTabsValue"
        type="card"
        closable
        @tab-remove="removeTab"
        @tab-click="clickTab"
      >
        <el-tab-pane
          v-for="(item) in tableList"
          :key="item.path"
          :label="item.meta.title"
          :name="item.meta.title"
        />
      </el-tabs>

    </div>
    <keep-alive>
      <router-view />
    </keep-alive>
  </div>

</template>

<script>
import { workWorkReadAPI } from '@/api/projectManagement/project'

export default {
  name: 'AppMain',
  data() {
    return {
      tableList: [],
      tableListTemp: [],
      workIdData: undefined,
      editableTabsValue: ''

    //   tabIndex: ''
    }
  },
  computed: {},

  watch: {
    $route() {
      this.addTab()
    }
  },
  //   created() {
  //     this.addTab()
  //   },
  mounted() {
    this.addTab()
  },
  methods: {
    unique(arr) {
      return Array.from(new Set(arr))
    },


    async addTab() {
      if (this.$route.params.id != undefined) {
        await workWorkReadAPI({ workId: this.$route.params.id }).then(res => {
          this.workIdData = res.data
        })

        // console.log(123, this.workIdData)
        this.$route.matched[1].path = `/project/list/${this.$route.params.id}`
        this.$route.matched[1].meta.title = this.workIdData.name
        // console.log(this.workIdData.name)
      }
      this.tableListTemp.push(this.$route.matched[1])
      this.tableList = this.unique(this.tableListTemp)
      this.editableTabsValue = this.$route.matched[1].meta.title
    //   console.log(111, this.tableList)
    //   console.log(222, this.tableListTemp)
    },
    removeTab(targetName) {
      this.tableList.forEach((item, index) => {
        if (item.meta.title === targetName) {
          this.tableList.splice(index, 1)
          this.tableListTemp = this.tableList
          this.$router.push({ path: this.tableList[index - 1].path })
        //   this.editableTabsValue = this.tableList[index - 1].meta.title
        //   this.$router.push({ path: item.path })
        }
      })
    },
    clickTab(tab) {
      console.log(tab)
      //   this.$router.push('/project/archive-project')
      this.tableList.forEach((item, index) => {
        if (item.meta.title === tab.paneName) {
          this.$router.push({ path: item.path })
        }
      })
      //   this.editableTabsValue = this.$route.matched[1].meta.title
    }
  }
}
</script>

<style lang="scss" scoped>
.tabs {
  background: white;
}
</style>
