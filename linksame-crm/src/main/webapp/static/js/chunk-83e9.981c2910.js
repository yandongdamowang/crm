(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-83e9"],{"1VRB":function(t,a,e){"use strict";e.r(a);var o=e("conU"),s={name:"Customer",components:{},data:function(){return{loading:!1,customerData:{type:0,followupDay:0,dealDay:0,putInPoolRemindConfig:0,putInPoolRemindDays:0}}},created:function(){this.customerSettingData()},methods:{customerSettingData:function(){var t=this;this.loading=!0,this.$store.dispatch("CRMSettingConfig").then(function(a){t.loading=!1,t.customerData.type=a.data.customerConfig,t.customerData.followupDay=a.data.followupDay,t.customerData.dealDay=a.data.dealDay,t.customerData.putInPoolRemindConfig=a.data.putInPoolRemindConfig,t.customerData.putInPoolRemindDays=a.data.putInPoolRemindDays}).catch(function(){t.loading=!1})},customerSettingSave:function(){var t=this;this.loading=!0,Object(o.h)(this.customerData).then(function(a){t.loading=!1,t.$message.success("操作成功")}).catch(function(){t.loading=!1})}}},n=(e("dTMD"),e("KHd+")),i=Object(n.a)(s,function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("div",{staticClass:"system-customer"},[e("div",{staticClass:"title"},[t._v("客户管理")]),t._v(" "),e("div",{directives:[{name:"loading",rawName:"v-loading",value:t.loading,expression:"loading"}],staticClass:"customer-content"},[e("div",{staticClass:"system-view-table"},[e("div",{staticClass:"content-title"},[e("span",[t._v("客户公海规则设置")]),t._v(" "),e("el-button",{staticClass:"rt",attrs:{type:"primary",size:"medium"},on:{click:t.customerSettingSave}},[t._v("保存")])],1),t._v(" "),e("div",{staticClass:"customer-setting"},[e("label",{staticClass:"label"},[t._v("客户公海规则设置"),e("el-tooltip",{attrs:{effect:"dark",content:"客户自动进入公海的天数设置",placement:"top"}},[e("i",{staticClass:"wukong wukong-help_tips"}),t._v(">\n        ")])],1),t._v(" "),e("div",{staticClass:"customer-radio"},[e("el-radio-group",{model:{value:t.customerData.type,callback:function(a){t.$set(t.customerData,"type",a)},expression:"customerData.type"}},[e("el-radio",{attrs:{label:0}},[t._v("不启用")]),t._v(" "),e("el-radio",{attrs:{label:1}},[t._v("启用")])],1),t._v(" "),e("br"),t._v(" "),e("el-input-number",{attrs:{min:0,"controls-position":"right",size:"small"},model:{value:t.customerData.followupDay,callback:function(a){t.$set(t.customerData,"followupDay",a)},expression:"customerData.followupDay"}}),t._v(" "),e("span",[t._v(" 天不跟进或 ")]),t._v(" "),e("el-input-number",{attrs:{min:0,"controls-position":"right",size:"small"},model:{value:t.customerData.dealDay,callback:function(a){t.$set(t.customerData,"dealDay",a)},expression:"customerData.dealDay"}}),t._v(" "),e("span",[t._v(" 天未成交")])],1)]),t._v(" "),e("div",{staticClass:"customer-setting"},[e("label",{staticClass:"label"},[t._v("提前提醒设置"),e("el-tooltip",{attrs:{effect:"dark",content:"提醒时间不能大于公海回收时间",placement:"top"}},[e("i",{staticClass:"wukong wukong-help_tips"}),t._v(">\n        ")])],1),t._v(" "),e("div",{staticClass:"customer-radio"},[e("el-radio-group",{model:{value:t.customerData.putInPoolRemindConfig,callback:function(a){t.$set(t.customerData,"putInPoolRemindConfig",a)},expression:"customerData.putInPoolRemindConfig"}},[e("el-radio",{attrs:{label:0}},[t._v("不提醒")]),t._v(" "),e("el-radio",{attrs:{label:1}},[t._v("提醒")])],1),t._v(" "),e("br"),t._v(" "),e("span",[t._v("提前")]),t._v(" "),e("el-input-number",{attrs:{min:0,"controls-position":"right",size:"small"},model:{value:t.customerData.putInPoolRemindDays,callback:function(a){t.$set(t.customerData,"putInPoolRemindDays",a)},expression:"customerData.putInPoolRemindDays"}}),t._v(" "),e("span",[t._v(" 天提醒")])],1)])])])])},[],!1,null,"93b4293a",null);i.options.__file="index.vue";a.default=i.exports},GOOb:function(t,a,e){},dTMD:function(t,a,e){"use strict";var o=e("GOOb");e.n(o).a}}]);