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
        >

          <!-- <router-link :to="item.path">
          </router-link> -->

        </el-tab-pane>
      </el-tabs>

    </div>
    <keep-alive>
      <router-view />
    </keep-alive>
  </div>

</template>

<script>
export default {
  name: 'AppMain',
  data() {
    return {
      tableList: [],
      tableListTemp: [],

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
  created() {
    this.addTab()
  },
  methods: {
    unique(arr) {
      return Array.from(new Set(arr))
    },
    addTab() {
      this.tableListTemp.push(this.$route.matched[1])
      this.tableList = this.unique(this.tableListTemp)
      this.editableTabsValue = this.$route.matched[1].meta.title

      //   console.log(this.tableListTemp)
      //   const list = this.tableListTemp.filter((item, index) => {
      //     return item.meta.title != this.$route.matched[1].meta.title
      //   })

    //   console.log(this.unique(this.tableListTemp))


      //   console.log(this.$route.matched)
      //   this.tableList.filter((item, index) => {
      // if (this.$route.matched[1] != item) {
      //   console.log(this.$route.matched)
      //   this.tableList.push(this.$route.matched[1])
      //   this.editableTabsValue = this.$route.matched[1].meta.title
      // }

      // } else {
      //   this.tableList.push(this.$route.matched[1])
      //   this.editableTabsValue = this.$route.matched[1].meta.title
      // }
      //   })
    //   console.log(this.tableList)
    },
    removeTab(targetName) {
      this.tableList.forEach((item, index) => {
        if (item.meta.title === targetName) {
          this.tableList.splice(index, 1)
        }
      })
      console.log(this.tableList)
    },
    clickTab(tab) {
      console.log(tab)
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
