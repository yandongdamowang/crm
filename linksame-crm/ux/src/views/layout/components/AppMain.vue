<template>

  <div style="height: calc(100% - 50px);">
    <div class="breadcrumb">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item
          v-for="item in levelList"
          :key="item.path"
          :to="item.path"
        >{{ item.meta.title }}</el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <router-view />

  </div>

</template>

<script>
export default {
  name: 'AppMain',
  computed: {},

  watch: {
    $route() {
      this.getBreadcrumb()
    }
  },
  created() {
    this.getBreadcrumb()
  },
  methods: {
    getBreadcrumb() {
      console.log(this.$route.matched)

      this.levelList = this.$route.matched
    }
  }
}
</script>

<style lang="scss" scoped>
.breadcrumb {
  height: 50px;
  //   position: relative; /*脱离文档流*/
  //   top: 50%; /*偏移*/
  width: 100%;
  background-color: #fff;
  .el-breadcrumb {
    font-size: 14px;
    line-height: 50px;
    margin: 0 0 0 20px;
  }
}
</style>
