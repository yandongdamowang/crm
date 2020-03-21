/** 自定义组件 共同逻辑 */
export default {
  data() {
    return {
      dataValue: ''
    }
  },
  watch: {
    value: function(val) {
      this.dataValue = val
    }
  },
  props: {
    value: {
      type: [String, Number],
      default: ''
    },
    /** 索引值 用于更新数据 */
    index: Number,
    /** 包含数据源 */
    item: Object,
    disabled: {
      type: Boolean,
      default: false
    }
  },

  mounted() {
    /** 如果有值以传入值为主 如果无值 已默认值为主 */
    this.dataValue = this.value
  },

  methods: {
    // 输入的值
    valueChange(val, planList = []) {
      const data = {
        index: this.index,
        value: val
      }
      if (planList.length > 0) {
        data.plan = planList.filter(item => {
          return item.planId == this.dataValue
        })[0]
      }
      this.$emit('value-change', data)
    }
  }

}
