<template>
  <el-container>
    <el-header class="nav-container">
      <navbar
        :nav-index="3"
        @nav-items-click="navClick"
      />
    </el-header>
    <el-container>
      <el-aside
        width="auto"
        class="aside-container"
      >
        <sidebar
          :items="contractRouters.children"
          :add-offset="quickAddOffset"
          main-router="contract"
        >
          <!-- <div slot="add" class="quick-add">
            <div class="quick-add-content">
              <p v-for="(item, index) in quickAddList" :key="index" @click="addSkip(item)">
                <i :class="'wukong-' + item.icon" class="wukong" />
                <span>{{ item.label }}</span>
              </p>
            </div>
          </div> -->
        </sidebar>
      </el-aside>
      <!-- <el-main id="crm-main-container"> -->
      <el-main>

        <app-main />
      </el-main>
    </el-container>
    <c-r-m-create-view
      v-if="isCreate"
      :crm-type="createCRMType"
      @save-success="createSaveSuccess"
      @hiden-view="isCreate=false"
    />
  </el-container>
</template>

<script>
import CRMCreateView from '@/views/contractManagement/components/CRMCreateView'
import { mapGetters } from 'vuex'
import { Navbar, Sidebar, AppMain } from './components'

export default {
  name: 'Layout',

  components: {
    Navbar,
    Sidebar,
    AppMain,
    CRMCreateView
  },

  data() {
    return {
      isCreate: false,
      createCRMType: '',
      levelList: []
    }
  },

  computed: {

    ...mapGetters(['contract', 'contractRouters', 'headerName']),
    // 快捷添加
    quickAddList() {
      var addItems = []

      if (this.contract.contract && this.contract.contract.save) {
        addItems.push({
          icon: 'contract',
          index: 'contract',
          label: '合同台账'
        })
      }
      if (this.contract.receivables && this.contract.receivables.save) {
        addItems.push({
          icon: 'money',
          index: 'money',
          label: '审批'
        })
      }

      return addItems
    },
    quickAddOffset() {
      return Math.round(this.quickAddList.length / 2) * 25
    }
  },


  created() {
    this.getcrmMessagNum()
    this.getcrmSettingConfig()
  },

  mounted() {},

  methods: {
    navClick(index) {},

    addSkip(item) {
      let type = item.index
      if (type == 'money') {
        type = 'receivables'
      } else if (type == 'clue') {
        type = 'leads'
      }
      this.createCRMType = type
      this.isCreate = true
    },

    /**
     * 获取消息数
     */
    getcrmMessagNum() {
      this.$store
        .dispatch('GetMessageNum')
        .then(res => {})
        .catch(() => {})
    },

    /**
     * 获取客户管理配置信息
     */
    getcrmSettingConfig() {
      this.$store.dispatch('CRMSettingConfig')
    },



    /**
     * 新建客户同时新建联系人
     */
    // 创建数据页面 保存成功
    createSaveSuccess(data) {
      if (data && data.saveAndCreate) {
        if (data.type == 'customer') {
          this.createCRMType = 'contacts'
          this.createActionInfo = {
            type: 'relative',
            crmType: 'customer',
            data: {}
          }
          this.createActionInfo.data['customer'] = data.data
          this.isCreate = true
        }
      }
    }
  }
}
</script>

<style lang="scss" scoped>
@import "./styles/common.scss";
.el-container {
  min-height: 0;
}
.aside-container {
  position: relative;
  background-color: #2d3037;
  box-sizing: border-box;
  //   border-right: solid 1px red;
}

.nav-container {
  padding: 0;
  box-shadow: 0px 1px 2px #dbdbdb;
  z-index: 100;
  min-width: 1200px;
}

// .appmain {
//   padding: 10px;
//   height: calc(100% - 50px);
// }
.ls-header {
  height: 40px;
  width: 100%;
  background: white;
}
</style>
