(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-3997"],{"4kPI":function(e,t,o){"use strict";var a=o("wMdg");o.n(a).a},"SP+9":function(e,t,o){"use strict";o.d(t,"d",function(){return n}),o.d(t,"c",function(){return r}),o.d(t,"b",function(){return l}),o.d(t,"a",function(){return i}),o.d(t,"f",function(){return c}),o.d(t,"e",function(){return s});var a=o("t3Un");function n(e){return Object(a.a)({url:"pmpContract/queryList",method:"post",data:e,headers:{"Content-Type":"application/json;charset=UTF-8"}})}function r(e){return Object(a.a)({url:"pmpContract/queryById",method:"post",data:e})}function l(e){return Object(a.a)({url:"pmpContract/delete",method:"post",data:e})}function i(e){return Object(a.a)({url:"pmpContract/add",method:"post",data:e,headers:{"Content-Type":"application/json;charset=UTF-8"}})}function c(e){return Object(a.a)({url:"pmpContractPayment/queryPaymentByMonthly",method:"post",data:e})}function s(e){return Object(a.a)({url:"pmpContract/contractDashboard",method:"post",data:e})}},hk1Z:function(e,t,o){"use strict";o.r(t);var a=o("gDS+"),n=o.n(a),r=o("SP+9"),l={name:"DialogPayment",components:{},props:{status:{type:Boolean,default:void 0}},data:function(){return{tableData:[{date:"2016-05-03",address:"设计清单"}],options:[{value:"zhinan",label:"DP设计类",children:[{value:"shejiyuanze",label:"建筑设计"},{value:"daohang",label:"精装修设计"},{value:"daohang",label:"燃气设计"}]}],dialogVisible:!1,dialogVisibleMilestone:!1,moneyMax:0,form:{contractType:"",contractName:"",contractNumber:"",contractPurchaseStartTime:"",contractPurchaseEndTime:"",beginTime:"",endTime:"",supplierId:11,agent:12,drpNumber:"",money:1e6,pmpContractPayments:[{payment_name:"",cost_percentage:50,money:void 0,payment_node:""}]}}},computed:{},watch:{moneyMax:function(){console.log(this.moneyMax)}},mounted:function(){},methods:{computeMoney:function(e){this.moneyMax+=parseInt(e)},createContact:function(){var e=this;console.log(this.form),Object(r.a)(n()(this.form)).then(function(t){console.log(t),e.$message({type:"success",message:"添加成功!"}),e.$router.push("/contract/contract")}).catch(function(){})},detailpmpContractPayments:function(e,t){console.log(e),"delete"==e?this.form.pmpContractPayments.splice(t,1):this.form.pmpContractPayments.push({payment_name:"",cost_percentage:50,money:void 0,payment_node:""})}}},i=(o("4kPI"),o("KHd+")),c=Object(i.a)(l,function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("div",[o("el-divider",{attrs:{"content-position":"left"}},[e._v("基本信息")]),e._v(" "),o("el-form",{ref:"form",attrs:{model:e.form,inline:!0,"label-width":"120px"}},[o("el-form-item",{attrs:{label:"所属合同类型"}},[o("el-select",{attrs:{placeholder:"请选合同类型"},model:{value:e.form.contractType,callback:function(t){e.$set(e.form,"contractType",t)},expression:"form.contractType"}},[o("el-option",{attrs:{label:"类型一",value:"shanghai"}}),e._v(" "),o("el-option",{attrs:{label:"类型二",value:"beijing"}})],1)],1),e._v(" "),o("el-form-item",{attrs:{label:"合同名称"}},[o("el-input",{model:{value:e.form.contractName,callback:function(t){e.$set(e.form,"contractName",t)},expression:"form.contractName"}})],1),e._v(" "),o("el-form-item",{attrs:{label:"合同编号"}},[o("el-input",{attrs:{placeholder:"请输入合同编号"},model:{value:e.form.contractNumber,callback:function(t){e.$set(e.form,"contractNumber",t)},expression:"form.contractNumber"}})],1),e._v(" "),o("el-form-item",{attrs:{label:"合同采购时间"}},[o("el-date-picker",{attrs:{type:"date","value-format":"yyyy-MM-dd",placeholder:"选择日期",align:"right"},model:{value:e.form.contractPurchaseStartTime,callback:function(t){e.$set(e.form,"contractPurchaseStartTime",t)},expression:"form.contractPurchaseStartTime"}})],1),e._v(" "),o("el-form-item",{attrs:{label:"合同开始日期"}},[o("el-date-picker",{attrs:{type:"date","value-format":"yyyy-MM-dd",placeholder:"选择日期",align:"right"},model:{value:e.form.beginTime,callback:function(t){e.$set(e.form,"beginTime",t)},expression:"form.beginTime"}})],1),e._v(" "),o("el-form-item",{attrs:{label:"合同结束日期"}},[o("el-date-picker",{attrs:{type:"date","value-format":"yyyy-MM-dd",placeholder:"选择日期",align:"right"},model:{value:e.form.endTime,callback:function(t){e.$set(e.form,"endTime",t)},expression:"form.endTime"}})],1),e._v(" "),o("el-form-item",{attrs:{label:"承包商"}},[o("el-select",{attrs:{placeholder:"请选合同承包商"},model:{value:e.form.supplierId,callback:function(t){e.$set(e.form,"supplierId",t)},expression:"form.supplierId"}},[o("el-option",{attrs:{label:"承包商一",value:"shanghai"}}),e._v(" "),o("el-option",{attrs:{label:"承包商二",value:"beijing"}})],1)],1),e._v(" "),o("el-form-item",{attrs:{label:"经办人"}},[o("el-select",{attrs:{placeholder:"请选合同经办人"},model:{value:e.form.agent,callback:function(t){e.$set(e.form,"agent",t)},expression:"form.agent"}},[o("el-option",{attrs:{label:"经办人一",value:"shanghai"}}),e._v(" "),o("el-option",{attrs:{label:"经办人二",value:"beijing"}})],1)],1),e._v(" "),o("el-form-item",{attrs:{label:"DRP采购单号"}},[o("el-input",{attrs:{placeholder:"请输入单号"},model:{value:e.form.drpNumber,callback:function(t){e.$set(e.form,"drpNumber",t)},expression:"form.drpNumber"}})],1),e._v(" "),o("el-form-item",{attrs:{label:"合同金额"}},[o("el-input",{attrs:{placeholder:"请输入金额"},model:{value:e.form.money,callback:function(t){e.$set(e.form,"money",t)},expression:"form.money"}})],1)],1),e._v(" "),o("el-divider",{attrs:{"content-position":"left"}},[e._v("支付方式")]),e._v(" "),e._l(e.form.pmpContractPayments,function(t,a){return o("el-form",{key:a,ref:"form",refInFor:!0,attrs:{model:e.form,inline:!0,"label-width":" 25px"}},[o("el-form-item",{attrs:{label:""}},[o("el-input",{attrs:{placeholder:"款项"},model:{value:e.form.pmpContractPayments[a].payment_name,callback:function(t){e.$set(e.form.pmpContractPayments[a],"payment_name",t)},expression:"form.pmpContractPayments[index].payment_name"}})],1),e._v(" "),o("el-form-item",{attrs:{label:"--"}},[o("el-input",{attrs:{placeholder:"支付比例"},model:{value:e.form.pmpContractPayments[a].cost_percentage,callback:function(t){e.$set(e.form.pmpContractPayments[a],"cost_percentage",t)},expression:"form.pmpContractPayments[index].cost_percentage"}})],1),e._v(" "),o("el-form-item",{attrs:{label:"--"}},[o("el-input",{attrs:{placeholder:"支付金额"},on:{input:function(t){e.computeMoney(e.form.pmpContractPayments[a].money)}},model:{value:e.form.pmpContractPayments[a].money,callback:function(t){e.$set(e.form.pmpContractPayments[a],"money",t)},expression:"form.pmpContractPayments[index].money"}})],1),e._v(" "),o("el-form-item",{attrs:{label:"--"}},[o("el-date-picker",{attrs:{type:"date","value-format":"yyyy-MM-dd",placeholder:"选择日期",align:"right"},model:{value:e.form.pmpContractPayments[a].payment_node,callback:function(t){e.$set(e.form.pmpContractPayments[a],"payment_node",t)},expression:"form.pmpContractPayments[index].payment_node"}})],1),e._v(" "),o("el-form-item",{attrs:{label:""}},[o("el-button",{attrs:{type:"danger",icon:"el-icon-delete",circle:""},on:{click:function(t){e.detailpmpContractPayments("delete",a)}}})],1)],1)}),e._v(" "),o("el-button",{attrs:{type:"primary"},on:{click:function(t){e.detailpmpContractPayments("add")}}},[e._v("增加付款方式")]),e._v(" "),o("el-divider",{attrs:{"content-position":"left"}},[e._v("关联业务")]),e._v(" "),o("el-button",{attrs:{type:"text"},on:{click:function(t){e.dialogVisible=!0}}},[e._v("添加关联")]),e._v(" "),o("el-dialog",{attrs:{visible:e.dialogVisible,"before-close":e.handleClose,title:"关联业务",data:"",width:"30%","append-to-body":""},on:{"update:visible":function(t){e.dialogVisible=t}}},[o("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.form.o,callback:function(t){e.$set(e.form,"o",t)},expression:"form.o"}}),e._v(" "),o("el-button",{attrs:{type:"primary"}},[e._v("查询")]),e._v(" "),o("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[o("el-button",{on:{click:function(t){e.dialogVisible=!1}}},[e._v("取 消")]),e._v(" "),o("el-button",{attrs:{type:"primary"},on:{click:function(t){e.dialogVisible=!1}}},[e._v("确 定")])],1)],1),e._v(" "),o("el-divider",{attrs:{"content-position":"left"}},[e._v("选择任务模板")]),e._v(" "),o("el-cascader",{attrs:{options:e.options,clearable:!0,placeholder:"选择里程碑节点",filterable:""}}),e._v(" "),o("el-button",{attrs:{type:"text"},on:{click:function(t){e.dialogVisibleMilestone=!0}}},[e._v("+")]),e._v(" "),o("el-dialog",{attrs:{visible:e.dialogVisibleMilestone,"before-close":e.handleClose,title:"新建里程碑节点",data:"",width:"300px","append-to-body":""},on:{"update:visible":function(t){e.dialogVisibleMilestone=t}}},[o("el-input",{attrs:{placeholder:"请输入节点名称"},model:{value:e.form.p,callback:function(t){e.$set(e.form,"p",t)},expression:"form.p"}}),e._v(" "),o("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[o("el-button",{attrs:{type:"primary"},on:{click:function(t){e.dialogVisible=!1}}},[e._v("创 建")])],1)],1)],2)},[],!1,null,"05b6009e",null);c.options.__file="DialogCreate.vue";t.default=c.exports},wMdg:function(e,t,o){}}]);