(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-7a12"],{JSWZ:function(t,e,a){"use strict";a.r(e);var n=a("GQeE"),o=a.n(n),i=a("QbLZ"),r=a.n(i),l=a("SP+9"),c={name:"ContractIndex",components:{DialogPayment:function(){return a.e("chunk-3069").then(a.bind(null,"Tch4"))},DialogCreate:function(){return a.e("chunk-19d4").then(a.bind(null,"qf+I"))},DrawerDetail:function(){return a.e("chunk-2e8f").then(a.bind(null,"VWNw"))}},data:function(){return{dialogComponents:"",dialogTitle:"",dialogwidth:"",dialogVisible:!1,drawerComponents:"",drawer:!1,drawerTitle:"",drawerData:"",contactListData:[],contactListSubData:[],pageCurrent:1,pageTotal:1,pageSize:10,grep:"",search:{contractNumber:"",supplierId:""},deleteData:void 0}},computed:{},mounted:function(){this.retriveContactList()},methods:{selectTable:function(t){var e=[];t.forEach(function(t,a){e.push(t.contractId)}),this.deleteData=e.join(",")},drawerStatus:function(t){this.drawer=t},handleCurrentChange:function(t){console.log("当前页: "+t),this.pageCurrent=t,this.retriveContactList()},handleSizeChange:function(t){console.log("每页 "+t+" 条"),this.pageSize=t,this.retriveContactList()},deleteContract:function(){var t=this;this.$confirm("此操作将永久选中合同, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){Object(l.b)({contractIds:t.deleteData}).then(function(e){t.$message({type:"success",message:"删除成功!"}),console.log(e),t.retriveContactList()}).catch(function(){})}).catch(function(){t.$message({type:"info",message:"已取消操作"})})},retriveContactList:function(){var t=this;Object(l.d)(r()({page:this.pageCurrent,limit:this.pageSize},this.search)).then(function(e){console.log(e.data),t.contactListData=e.data.list,t.pageTotal=e.data.totalRow,e.data.list.forEach(function(e){t.contactListSubData=o()(e.billList)}),console.log(t.contactListSubData)}).catch(function(){})},searchReset:function(){this.search={contractNumber:"",supplierId:""},this.retriveContactList()},commitData:function(){this.$refs.child.createContact(),this.dialogVisible=!1},dialogPayment:function(){this.dialogVisible=!0,this.dialogTitle="支付比例调整",this.dialogComponents="DialogPayment",this.dialogwidth="50%"},dialogCreate:function(){this.dialogVisible=!0,this.dialogTitle="新建合同",this.dialogComponents="DialogCreate",this.dialogwidth="90%"},drawerDetail:function(t){this.drawer=!0,this.drawerTitle=t.contractName,this.drawerComponents="DrawerDetail",console.log(t),this.drawerData=t.contractId}}},s=(a("WAOp"),a("KHd+")),u=Object(s.a)(c,function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticStyle:{height:"100%"}},[a("div",{staticClass:"ls-header"},[t._v("\n    合同管理\n    "),a("div",{staticClass:"ls-header-r"},[a("el-select",{attrs:{placeholder:"选择项目"},model:{value:t.grep,callback:function(e){t.grep=e},expression:"grep"}},[a("el-option",{attrs:{label:"项目一",value:"shanghai"}}),t._v(" "),a("el-option",{attrs:{label:"项目二",value:"beijing"}})],1)],1)]),t._v(" "),a("div",{staticClass:"ls-box"},[a("el-form",{ref:"form",attrs:{inline:!0}},[a("el-form-item",{attrs:{label:"合同编号："}},[a("el-input",{attrs:{placeholder:"请输入内容"},model:{value:t.search.contractNumber,callback:function(e){t.$set(t.search,"contractNumber",e)},expression:"search.contractNumber"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"客户/承包商："}},[a("el-input",{attrs:{placeholder:"请输入内容"},model:{value:t.search.supplierId,callback:function(e){t.$set(t.search,"supplierId",e)},expression:"search.supplierId"}})],1),t._v(" "),a("el-form-item",[a("span",[a("el-button",{attrs:{type:"primary"},on:{click:t.retriveContactList}},[t._v("查询")])],1),t._v(" "),a("span",[a("el-button",{on:{click:t.searchReset}},[t._v("重置")])],1)])],1),t._v(" "),a("div",[a("span",[a("el-button",{attrs:{type:"primary"},on:{click:t.dialogCreate}},[t._v("新建合同")])],1),t._v(" "),a("span",[a("el-dropdown",[a("el-button",[t._v("批量操作")]),t._v(" "),a("el-dropdown-menu",{attrs:{slot:"dropdown"},slot:"dropdown"},[a("el-dropdown-item",[a("div",{on:{click:t.deleteContract}},[t._v("批量删除")])]),t._v(" "),a("el-dropdown-item",[a("div",{on:{click:t.dialogPayment}},[t._v("批量修改支付比例")])])],1)],1)],1),t._v(" "),a("span",[a("el-button",[t._v("导出")])],1),t._v(" "),a("span",[a("el-button",{attrs:{icon:"el-icon-refresh-right"},on:{click:t.retriveContactList}})],1)]),t._v(" "),a("el-table",{staticStyle:{margin:"20px 0 0 0"},attrs:{data:t.contactListData,"header-cell-style":{background:"#F5F7FA",color:"#606266"},border:"",height:"75%"},on:{"selection-change":t.selectTable}},[a("el-table-column",{attrs:{type:"selection",width:"55"}}),t._v(" "),a("el-table-column",{attrs:{prop:"contractNumber",label:"合同编号",width:"150"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-button",{attrs:{type:"text",size:"small"},on:{click:function(a){t.drawerDetail(e.row)}}},[t._v(t._s(e.row.contractNumber))])]}}])}),t._v(" "),a("el-table-column",{attrs:{prop:"contractName",label:"合同名称",width:"120"}}),t._v(" "),a("el-table-column",{attrs:{prop:"supplierId",label:"客户/承包商",width:"120"}}),t._v(" "),a("el-table-column",{attrs:{prop:"money",label:"合同总金额(￥)",width:"120"}}),t._v(" "),a("el-table-column",{attrs:{prop:"requiredSigningTime",label:"要求签署时间",width:"150"}}),t._v(" "),a("el-table-column",{attrs:{prop:"actualSigningTime",label:"实际签署时间",width:"150"}}),t._v(" "),a("el-table-column",{attrs:{prop:"purchaseOrderNumber",label:"DRP采购单号",width:"120"}}),t._v(" "),a("el-table-column",{attrs:{prop:"agent",label:"经办人",width:"120"}}),t._v(" "),t._l(t.contactListSubData,function(e,n){return a("el-table-column",{key:n,attrs:{label:e,align:"center"}},[a("el-table-column",{attrs:{label:"支付比例",width:"120"},scopedSlots:t._u([{key:"default",fn:function(n){return[a("div",[t._v(t._s(n.row.billList[e].paymentRatio+"%"))])]}}])}),t._v(" "),a("el-table-column",{attrs:{label:"支付金额",width:"120"},scopedSlots:t._u([{key:"default",fn:function(n){return[a("div",[t._v(t._s("￥"+n.row.billList[e].paymentAmount))])]}}])}),t._v(" "),a("el-table-column",{attrs:{label:"累计支付比例",width:"120"},scopedSlots:t._u([{key:"default",fn:function(n){return[a("div",[t._v(t._s(n.row.billList[e].cumulativePaymentRatio+"%"))])]}}])}),t._v(" "),a("el-table-column",{attrs:{label:"累计支付金额",width:"120"},scopedSlots:t._u([{key:"default",fn:function(n){return[a("div",[t._v(t._s("￥"+n.row.billList[e].cumulativepayment))])]}}])})],1)})],2),t._v(" "),a("div",{staticClass:"ls-pagination"},[a("el-pagination",{attrs:{"current-page":t.pageCurrent,"page-sizes":[2,10,20],"page-size":t.pageSize,total:t.pageTotal,layout:"total, sizes, prev, pager, next, jumper"},on:{"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}})],1),t._v(" "),a("el-dialog",{attrs:{visible:t.dialogVisible,title:t.dialogTitle,width:t.dialogwidth},on:{"update:visible":function(e){t.dialogVisible=e}}},[a("keep-alive",{attrs:{exclude:"DialogCreate,DialogPayment"}},[a(t.dialogComponents,{ref:"child",tag:"component"})],1),t._v(" "),a("span",{attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(e){t.dialogVisible=!1}}},[t._v("取 消")]),t._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:t.commitData}},[t._v("确 定")])],1)],1),t._v(" "),a("el-drawer",{attrs:{title:t.drawerTitle,visible:t.drawer,"with-header":!1,"destroy-on-close":!0,size:"70%"},on:{"update:visible":function(e){t.drawer=e},open:function(e){t.drawerDetail()}}},[a(t.drawerComponents,{tag:"component",attrs:{"drawer-data":t.drawerData},on:{drawerStatus:t.drawerStatus}})],1)],1)])},[],!1,null,"0295ed3b",null);u.options.__file="Main.vue";e.default=u.exports},"SP+9":function(t,e,a){"use strict";a.d(e,"d",function(){return o}),a.d(e,"c",function(){return i}),a.d(e,"b",function(){return r}),a.d(e,"a",function(){return l}),a.d(e,"f",function(){return c}),a.d(e,"e",function(){return s});var n=a("t3Un");function o(t){return Object(n.a)({url:"pmpContract/queryList",method:"post",data:t,headers:{"Content-Type":"application/json;charset=UTF-8"}})}function i(t){return Object(n.a)({url:"pmpContract/queryById",method:"post",data:t})}function r(t){return Object(n.a)({url:"pmpContract/delete",method:"post",data:t})}function l(t){return Object(n.a)({url:"pmpContract/add",method:"post",data:t,headers:{"Content-Type":"application/json;charset=UTF-8"}})}function c(t){return Object(n.a)({url:"pmpContractPayment/queryPaymentByMonthly",method:"post",data:t})}function s(t){return Object(n.a)({url:"pmpContract/contractDashboard",method:"post",data:t})}},WAOp:function(t,e,a){"use strict";var n=a("w8Gk");a.n(n).a},w8Gk:function(t,e,a){}}]);