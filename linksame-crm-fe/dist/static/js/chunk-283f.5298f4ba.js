(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-283f"],{JHl8:function(e,t,a){"use strict";var i=a("pXmA");a.n(i).a},"nPa+":function(e,t,a){"use strict";a.r(t);var i=a("QbLZ"),l=a.n(i),n=a("t3Un");var r={name:"ContractIndex",components:{DialogPaymentApply:function(){return a.e("chunk-0fb3").then(a.bind(null,"hLBp"))},DialogCreate:function(){return a.e("chunk-13a6").then(a.bind(null,"oSGp"))},DrawerDetail:function(){return a.e("chunk-fca3").then(a.bind(null,"i+gp"))}},data:function(){return{dialogComponents:"",dialogTitle:"",dialogwidth:"",dialogVisible:!1,drawerComponents:"",drawer:!1,drawerTitle:"",drawerData:"",pageCurrent:1,pageTotal:1,pageSize:10,paymentBillListData:[],search:{paymentNumber:"",supplierId:"",datetime:["2020-04-10","2020-10-10"]},deleteData:void 0}},computed:{},mounted:function(){this.retrivePaymentBillList()},methods:{handleCurrentChange:function(e){console.log("当前页: "+e),this.pageCurrent=e,this.retrivePaymentBillList()},handleSizeChange:function(e){console.log("每页 "+e+" 条"),this.pageSize=e,this.retrivePaymentBillList()},retrivePaymentBillList:function(){var e=this;(function(e){return Object(n.a)({url:"pmpContractPaymentRecord/queryList",method:"post",data:e})})(l()({startTime:this.search.datetime[0],endTime:this.search.datetime[1],page:this.pageCurrent,limit:this.pageSize},this.search)).then(function(t){console.log(t),e.paymentBillListData=t.data.list,e.pageTotal=t.data.totalRow}).catch(function(){})},searchReset:function(){this.search={contractNumber:"",supplierId:""},this.retrivePaymentBillList()},commitData:function(){this.$refs.child.createContact(),this.dialogVisible=!1},dialogPaymentApply:function(){this.dialogVisible=!0,this.dialogTitle="申请付款",this.dialogComponents="DialogPaymentApply",this.dialogwidth="50%"},dialogCreate:function(){this.dialogVisible=!0,this.dialogTitle="新建合同",this.dialogComponents="DialogCreate",this.dialogwidth="90%"},drawerDetail:function(e){this.drawer=!0,this.drawerTitle=e.contractName,this.drawerComponents="DrawerDetail",console.log(e),this.drawerData=e.contractId}}},o=(a("JHl8"),a("KHd+")),s=Object(o.a)(r,function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticStyle:{height:"100%"}},[a("div",{staticClass:"ls-header"},[e._v(" 付款账单\n    "),a("span",{staticClass:"grep"},[a("el-date-picker",{staticStyle:{float:"right",margin:"8px 50px 0 0"},attrs:{type:"daterange","start-placeholder":"开始日期","end-placeholder":"结束日期","value-format":"yyyy-MM-dd"},model:{value:e.search.datetime,callback:function(t){e.$set(e.search,"datetime",t)},expression:"search.datetime"}})],1)]),e._v(" "),a("div",{staticClass:"ls-box"},[a("el-form",{ref:"form",attrs:{inline:!0}},[a("el-form-item",{attrs:{label:"付款编号："}},[a("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.search.paymentNumber,callback:function(t){e.$set(e.search,"paymentNumber",t)},expression:"search.paymentNumber"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"客户："}},[a("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.search.supplierId,callback:function(t){e.$set(e.search,"supplierId",t)},expression:"search.supplierId"}})],1),e._v(" "),a("el-form-item",[a("span",[a("el-button",{attrs:{type:"primary"},on:{click:e.retrivePaymentBillList}},[e._v("查询")])],1),e._v(" "),a("span",[a("el-button",{on:{click:e.searchReset}},[e._v("重置")])],1)])],1),e._v(" "),a("el-table",{staticStyle:{margin:"20px 0 0 0"},attrs:{data:e.paymentBillListData,"header-cell-style":{background:"#F5F7FA",color:"#606266"},border:"",height:"80%"},on:{"selection-change":e.selectTable}},[a("el-table-column",{attrs:{type:"selection",width:"55"}}),e._v(" "),a("el-table-column",{attrs:{prop:"paymentNumber",label:"付款编号"}}),e._v(" "),a("el-table-column",{attrs:{prop:"supplierId",label:"客户/承包商"}}),e._v(" "),a("el-table-column",{attrs:{prop:"amountAdvanced",label:"预付金额"}}),e._v(" "),a("el-table-column",{attrs:{prop:"practicalRatio",label:"实付比例",width:"150"}}),e._v(" "),a("el-table-column",{attrs:{prop:"practicaAdvanced",label:"实付金额",width:"150"}}),e._v(" "),a("el-table-column",{attrs:{prop:"paymentTime",label:"实际付款时间",width:"150"}}),e._v(" "),a("el-table-column",{attrs:{prop:"paymentType",label:"付款方式"}}),e._v(" "),a("el-table-column",{attrs:{prop:"responsiblePerson",label:"经办人"}}),e._v(" "),a("el-table-column",{attrs:{prop:"remark",label:"备注"}})],1),e._v(" "),a("div",{staticClass:"ls-pagination"},[a("el-pagination",{attrs:{"current-page":e.pageCurrent,"page-sizes":[2,10,20],"page-size":e.pageSize,total:e.pageTotal,layout:"total, sizes, prev, pager, next, jumper"},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}})],1),e._v(" "),a("el-dialog",{attrs:{visible:e.dialogVisible,title:e.dialogTitle,width:e.dialogwidth},on:{"update:visible":function(t){e.dialogVisible=t}}},[a(e.dialogComponents,{ref:"child",tag:"component"}),e._v(" "),a("span",{attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.dialogVisible=!1}}},[e._v("取 消")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:e.commitData}},[e._v("确 定")])],1)],1),e._v(" "),a("el-drawer",{attrs:{title:e.drawerTitle,visible:e.drawer,"destroy-on-close":!0,size:"70%"},on:{"update:visible":function(t){e.drawer=t},open:function(t){e.drawerDetail()}}},[a(e.drawerComponents,{tag:"component",attrs:{"drawer-data":e.drawerData}})],1)],1)])},[],!1,null,"07105269",null);s.options.__file="Main.vue";t.default=s.exports},pXmA:function(e,t,a){}}]);