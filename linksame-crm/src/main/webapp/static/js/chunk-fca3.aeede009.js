(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-fca3"],{"04Ax":function(t,a,e){},"SP+9":function(t,a,e){"use strict";e.d(a,"d",function(){return r}),e.d(a,"c",function(){return o}),e.d(a,"b",function(){return l}),e.d(a,"a",function(){return s}),e.d(a,"f",function(){return c}),e.d(a,"e",function(){return i});var n=e("t3Un");function r(t){return Object(n.a)({url:"pmpContract/queryList",method:"post",data:t,headers:{"Content-Type":"application/json;charset=UTF-8"}})}function o(t){return Object(n.a)({url:"pmpContract/queryById",method:"post",data:t})}function l(t){return Object(n.a)({url:"pmpContract/delete",method:"post",data:t})}function s(t){return Object(n.a)({url:"pmpContract/add",method:"post",data:t,headers:{"Content-Type":"application/json;charset=UTF-8"}})}function c(t){return Object(n.a)({url:"pmpContractPayment/queryPaymentByMonthly",method:"post",data:t})}function i(t){return Object(n.a)({url:"pmpContract/contractDashboard",method:"post",data:t})}},aj4w:function(t,a,e){"use strict";var n=e("04Ax");e.n(n).a},"i+gp":function(t,a,e){"use strict";e.r(a);var n=e("SP+9"),r={name:"DialogPayment",components:{},props:{drawerData:{type:Object,default:void 0}},data:function(){return{pmpContractData:{},activeName:"first",gridData:[{date:"2016-05-02",name:"王小虎",address:"上海市普陀区金沙江路 1518 弄"}],formData:{a:"",b:"",c:""}}},computed:{},mounted:function(){this.retriveContactDetail()},methods:{deleteContract:function(){var t=this;console.log(123,this.drawerData),this.$confirm("此操作将永久删除该合同, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){Object(n.b)({contractIds:t.drawerData}).then(function(a){t.$message({type:"success",message:"删除成功!"}),console.log(a),t.$router.push("/contract/contract")}).catch(function(){})}).catch(function(){t.$message({type:"info",message:"已取消操作"})})},retriveContactDetail:function(){var t=this;Object(n.c)({contractId:this.drawerData}).then(function(a){console.log(a),t.pmpContractData=a.pmpContract}).catch(function(){})}}},o=(e("aj4w"),e("KHd+")),l=Object(o.a)(r,function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("div",[e("div",{staticClass:"title"},[e("el-button",{attrs:{type:"primary"}},[e("i",{staticClass:"el-icon-edit"})]),t._v(" "),e("el-button",{attrs:{type:"primary"}},[e("i",{staticClass:"el-icon-folder-opened"})]),t._v(" "),e("el-button",{attrs:{type:"primary"}},[t._v("附件"),e("i",{staticClass:"el-icon-upload el-icon--right"})]),t._v(" "),e("el-dropdown",[e("el-button",{attrs:{icon:"el-icon-more"}}),t._v(" "),e("el-dropdown-menu",{attrs:{slot:"dropdown"},slot:"dropdown"},[e("el-dropdown-item",[t._v("转 移")]),t._v(" "),e("el-dropdown-item",[e("span",{on:{click:t.deleteContract}},[t._v("删 除")])])],1)],1)],1),t._v(" "),e("div",{staticClass:"button"}),t._v(" "),e("div",{staticClass:"ls-drawerform"},[t._m(0),t._v(" "),e("el-form",{ref:"form",attrs:{"label-width":"120px","label-position":"left"}},[e("el-row",[e("el-col",{attrs:{span:12}},[e("el-form-item",{attrs:{label:"合同类型"}},[e("span",[t._v(" 1191111-REC-工程-S1-珠海一键")])]),t._v(" "),e("el-form-item",{attrs:{label:"合同采购时间"}},[e("span",[t._v(t._s(t.pmpContractData.contractPurchaseStartTime)+" ")])]),t._v(" "),e("caption"),t._v(" "),e("el-form-item",{attrs:{label:"合同编号"}},[e("span",[t._v(t._s(t.pmpContractData.contractNumber)+" ")])]),t._v(" "),e("el-form-item",{attrs:{label:"合同名称"}},[e("span",[t._v(t._s(t.pmpContractData.contractName)+" ")])]),t._v(" "),e("el-form-item",{attrs:{label:"承包商"}},[e("span",[t._v(t._s(t.pmpContractData.supplierId)+" ")])]),t._v(" "),e("el-form-item",{attrs:{label:"DRP采购单号"}},[e("span",[t._v(t._s(t.pmpContractData.purchaseOrderNumber)+" ")])])],1),t._v(" "),e("el-col",{attrs:{span:12}},[e("el-form-item",{attrs:{label:"签署时间"}},[e("span",[t._v(t._s(t.pmpContractData.actualSigningTime)+" ")])]),t._v(" "),e("el-form-item",{attrs:{label:"合同金额"}},[e("span",[t._v(t._s(t.pmpContractData.money)+" ")])]),t._v(" "),e("el-form-item",{attrs:{label:"合同开始时间"}},[e("span",[t._v(t._s(t.pmpContractData.contractPurchaseStartTime)+" ")])]),t._v(" "),e("el-form-item",{attrs:{label:"合同到期时间"}},[e("span",[t._v(t._s(t.pmpContractData.contractPurchaseEndTime)+" ")])]),t._v(" "),e("el-form-item",{attrs:{label:"经办人"}},[e("span",[t._v(t._s(t.pmpContractData.agent)+" ")])])],1)],1)],1)],1),t._v(" "),e("el-tabs",{on:{"tab-click":t.handleClick},model:{value:t.activeName,callback:function(a){t.activeName=a},expression:"activeName"}},[e("el-tab-pane",{attrs:{label:"付款明细",name:"first"}},[e("el-table",{staticStyle:{width:"100%"},attrs:{data:t.tableData}},[e("el-table-column",{attrs:{prop:"a",label:"预计付款时间",width:"180"}}),t._v(" "),e("el-table-column",{attrs:{prop:"b",label:"款项",width:"180"}}),t._v(" "),e("el-table-column",{attrs:{prop:"c",label:"比例"}}),t._v(" "),e("el-table-column",{attrs:{prop:"d",label:"金额"}}),t._v(" "),e("el-table-column",{attrs:{prop:"e",label:"实际支付比例"}}),t._v(" "),e("el-table-column",{attrs:{prop:"f",label:"实际支付金额"}}),t._v(" "),e("el-table-column",{attrs:{prop:"g",label:"关联项目任务"}}),t._v(" "),e("el-table-column",{attrs:{prop:"h",label:"状态"}}),t._v(" "),e("el-table-column",{attrs:{prop:"i",label:"备注"}})],1)],1),t._v(" "),e("el-tab-pane",{attrs:{label:"附件",name:"second"}},[e("div",[t._v("附件")])]),t._v(" "),e("el-tab-pane",{attrs:{label:"回款信息",name:"third"}},[e("div",[t._v("回款信息")])]),t._v(" "),e("el-tab-pane",{attrs:{label:"操作记录",name:"fourth"}},[e("div",[t._v("操作记录")])]),t._v(" "),e("el-tab-pane",{attrs:{label:"审批记录",name:"fifth"}},[e("div",{staticStyle:{height:"300px"}},[e("el-steps",{attrs:{active:1,direction:"vertical"}},[e("el-step",{attrs:{title:"发起审批"}}),t._v(" "),e("el-step",{attrs:{title:"审批节点1"}}),t._v(" "),e("el-step",{attrs:{title:"审批节点2",description:"\n                创建人：王某某\n                审批类型：付款申请"}}),t._v(" "),e("el-step",{attrs:{title:"审批节点3",description:"\n              待处理"}})],1)],1)])],1)],1)},[function(){var t=this.$createElement,a=this._self._c||t;return a("div",{staticClass:"ls-drawerform-header"},[a("i",{staticClass:"el-icon-notebook-1"}),this._v("   基本信息")])}],!1,null,"724341f0",null);l.options.__file="DrawerDetail.vue";a.default=l.exports}}]);