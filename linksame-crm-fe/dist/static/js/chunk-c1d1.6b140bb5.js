(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-c1d1"],{"g+Rs":function(t,e,a){},nqXz:function(t,e,a){"use strict";a.r(e);var s=a("7Tog"),r={name:"DialogPayment",components:{},props:{drawerData:{type:Number,default:void 0}},data:function(){return{pmpPrepaymentData:{},activeName:"first",gridData:[{date:"2016-05-02",name:"王小虎",address:"上海市普陀区金沙江路 1518 弄"}],formData:{a:"",b:"",c:""}}},computed:{},mounted:function(){this.retrivePrepaymentBillDetail()},methods:{closeDrawer:function(){this.$emit("drawerStatus",!1)},submitUploadSuccess:function(){alert(123)},submitUploadError:function(){alert(456)},deleteContract:function(){},retrivePrepaymentBillDetail:function(){var t=this;Object(s.a)({billId:this.drawerData}).then(function(e){t.pmpPrepaymentData=e.data,console.log("prepaymentBillDetail",t.pmpPrepaymentData)}).catch(function(){})}}},l=(a("xHRo"),a("KHd+")),n=Object(l.a)(r,function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("div",{staticClass:"ls-drawertitle"},[a("div",{staticClass:"ls-drawertitle-l"},[t._v(t._s(t.pmpPrepaymentData.contractNumber)+"\n    ")]),t._v(" "),a("div",{staticClass:"ls-drawertitle-r"},[t._m(0),t._v("   \n      "),t._m(1),t._v("   \n\n      "),a("el-dropdown",{staticClass:"ls-drawertitle-dropdown"},[a("span",[a("i",{staticClass:"el-icon-upload2"})]),t._v("   \n\n        "),a("el-dropdown-menu",{attrs:{slot:"dropdown"},slot:"dropdown"},[a("el-dropdown-item",[a("el-upload",{ref:"upload",attrs:{"auto-upload":!1,"on-success":t.submitUploadSuccess,"on-error":t.submitUploadError,action:"https://jsonplaceholder.typicode.com/posts/"}},[a("div",{attrs:{slot:"trigger",size:"small",type:"primary"},slot:"trigger"},[t._v("本地上传")])])],1),t._v(" "),a("el-dropdown-item",[a("el-upload",{ref:"upload",attrs:{"auto-upload":!1,"on-success":t.submitUploadSuccess,"on-error":t.submitUploadError,action:"https://jsonplaceholder.typicode.com/posts/"}},[a("div",{attrs:{size:"small",type:"primary"},on:{click:t.submitUpload}},[t._v("网盘上传")])])],1)],1)],1),t._v(" "),a("el-dropdown",{staticClass:"ls-drawertitle-dropdown"},[a("span",[a("i",{staticClass:"el-icon-more"})]),t._v(" "),a("el-dropdown-menu",{attrs:{slot:"dropdown"},slot:"dropdown"},[a("el-dropdown-item",[t._v("转 移")]),t._v(" "),a("el-dropdown-item",[a("span",{on:{click:t.deleteContract}},[t._v("删 除")])])],1)],1),t._v(" "),a("span",[t._v("丨")]),t._v(" "),a("span",{on:{click:t.closeDrawer}},[a("i",{staticClass:"el-icon-close"})])],1)]),t._v(" "),a("div",{staticClass:"ls-drawerform"},[t._m(2),t._v(" "),a("el-form",{ref:"form",attrs:{"label-width":"120px","label-position":"left"}},[a("el-row",[a("el-col",{attrs:{span:8}},[a("el-form-item",{attrs:{label:"合同编号："}},[a("span",[t._v(t._s(t.pmpPrepaymentData.contractNumber)+" ")])]),t._v(" "),a("el-form-item",{attrs:{label:"预付比例："}},[a("span",[t._v(t._s(t.pmpPrepaymentData.contractName)+" ")])]),t._v(" "),a("el-form-item",{attrs:{label:"承包商："}},[a("span",[t._v(t._s(t.pmpPrepaymentData.supplierName)+" ")])])],1),t._v(" "),a("el-col",{attrs:{span:8}},[a("el-form-item",{attrs:{label:"合同金额："}},[a("span",[t._v(t._s("￥"+t.pmpPrepaymentData.money)+" ")])]),t._v(" "),a("el-form-item",{attrs:{label:"预付金额："}},[a("span",[t._v(t._s("￥"+t.pmpPrepaymentData.contractMoney)+" ")])]),t._v(" "),a("el-form-item",{attrs:{label:"业主："}},[a("span",[t._v(t._s(t.pmpPrepaymentData.proprietor)+" ")])])],1),t._v(" "),a("el-col",{attrs:{span:8}},[a("el-form-item",{attrs:{label:"款项："}},[a("span",[t._v(t._s(t.pmpPrepaymentData.paymentName)+" ")])]),t._v(" "),a("el-form-item",{attrs:{label:"计划付款日期："}},[a("span",[t._v(t._s(t.pmpPrepaymentData.paymentNode)+" ")])])],1)],1)],1),t._v(" "),a("div",{staticClass:"ls-drawerform-header"},[a("i",{staticClass:"el-icon-notebook-1"}),t._v("   优先级\n\n      "),a("div",{staticStyle:{margin:"20px 0 0 20px"}},["1"==t.pmpPrepaymentData.priority?a("span",[a("el-tag",{attrs:{type:"danger"}},[t._v("高")])],1):"2"==t.pmpPrepaymentData.priority?a("span",[a("el-tag",{attrs:{type:"warning"}},[t._v("中")])],1):"3"==t.pmpPrepaymentData.priority?a("span",[a("el-tag",{attrs:{type:"success"}},[t._v("低")])],1):t._e()])]),t._v(" "),t._m(3)],1),t._v(" "),a("div",{staticClass:"ls-drawerbox"},[a("el-tab-pane",{attrs:{label:"操作记录",name:"fourth"}},[a("div",[t._v("操作记录")])]),t._v(" "),a("el-tab-pane",{attrs:{label:"附件",name:"second"}},[a("div",[t._v("附件")])]),t._v(" "),a("el-tab-pane",{attrs:{label:"审批记录",name:"fifth"}},[a("div",{staticStyle:{height:"300px"}},[a("el-steps",{attrs:{active:1,direction:"vertical"}},[a("el-step",{attrs:{title:"发起审批"}}),t._v(" "),a("el-step",{attrs:{title:"审批节点1"}}),t._v(" "),a("el-step",{attrs:{title:"审批节点2",description:"\n                创建人：王某某\n                审批类型：付款申请"}}),t._v(" "),a("el-step",{attrs:{title:"审批节点3",description:"\n              待处理"}})],1)],1)])],1)])},[function(){var t=this.$createElement,e=this._self._c||t;return e("span",[e("i",{staticClass:"el-icon-edit"})])},function(){var t=this.$createElement,e=this._self._c||t;return e("span",[e("i",{staticClass:"el-icon-folder-opened"})])},function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticClass:"ls-drawerform-header"},[e("i",{staticClass:"el-icon-notebook-1"}),this._v("   基本信息")])},function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticClass:"ls-drawerform-header"},[e("i",{staticClass:"el-icon-paperclip"}),this._v("   关联业务")])}],!1,null,"4c691e78",null);n.options.__file="DrawerDetail.vue";e.default=n.exports},xHRo:function(t,e,a){"use strict";var s=a("g+Rs");a.n(s).a}}]);