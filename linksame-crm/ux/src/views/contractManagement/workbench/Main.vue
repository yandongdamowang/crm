<template>
  <div class="box">
    仪表盘
  </div>
</template>

<script>


export default {
  /** 客户管理下的工作台 */
  name: 'CustomerWorkbench',
  components: {
    // CustomerDash,
    // DuplicateCheck,
    // membersDep, // 员工部门
    // timeTypeSelect
  },
  filters: {},
  data() {
    return {
    }
  },
  computed: {
    // ...mapGetters(['userInfo'])
  },
  mounted() {
    // this.users.push(this.userInfo)
    // this.dashData = {
    //   users: this.users,
    //   strucs: [],
    //   timeTypeValue: this.timeTypeValue
    // }
    // this.filtersInfo = this.userInfo
  },
  methods: {
    // 类型选择
    timeTypeChange(data) {
      this.timeTypeValue = data
      this.dashData = {
        users: this.users,
        strucs: this.strucs,
        timeTypeValue: this.timeTypeValue
      }
    },
    // 更改筛选条件
    popoverSubmit(users, strucs) {
      this.users = users
      this.strucs = strucs
      if (this.users.length === 1 && this.strucs.length === 0) {
        this.dashData = {
          users: this.users,
          strucs: this.strucs,
          timeTypeValue: this.timeTypeValue
        }
        this.filtersInfo = {
          realname: this.users[0].realname,
          img: this.users[0].img
        }
      } else if (this.users.length === 0 && this.strucs.length === 0) {
        this.users = [this.userInfo]
        this.dashData = {
          users: this.users,
          strucs: this.strucs,
          timeTypeValue: this.timeTypeValue
        }
        this.filtersInfo = {
          realname: this.userInfo.realname,
          img: this.userInfo.img
        }
      } else {
        this.dashData = {
          users: this.users,
          strucs: this.strucs,
          timeTypeValue: this.timeTypeValue
        }
        var name = ''
        if (this.users.length > 0) {
          name = this.users.length + '个员工'
        }
        if (this.strucs.length > 0) {
          if (this.users.length > 0) {
            name = name + ','
          }
          name = name + this.strucs.length + '个部门'
        }
        this.filtersInfo = {
          realname: name,
          img: require('@/assets/img/crm_multiuser.png')
        }
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.box {
  height: 100%;
  width: 100%;
  background-color: #fff;
}
</style>
