(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-44df"],{YpDP:function(e,t,a){},gYQv:function(e,t,a){"use strict";a.r(t);var r=a("QbLZ"),n=a.n(r),i=a("o6vM"),l={name:"ContractIndex",components:{DrawerDetail:function(){return a.e("chunk-7a71").then(a.bind(null,"i+gp"))}},data:function(){return{dialogComponents:"",dialogTitle:"",dialogwidth:"",dialogVisible:!1,drawerComponents:"",drawer:!1,drawerTitle:"",drawerData:"",rowData:"",pageCurrent:1,pageTotal:1,pageSize:10,paymentBillListData:[],search:{paymentNumber:"",supplierId:"",datetime:["2020-04-10","2020-10-10"]},deleteData:void 0}},computed:{},mounted:function(){this.retrivePaymentBillList()},methods:{handleCurrentChange:function(e){console.log("当前页: "+e),this.pageCurrent=e,this.retrivePaymentBillList()},handleSizeChange:function(e){console.log("每页 "+e+" 条"),this.pageSize=e,this.retrivePaymentBillList()},drawerStatus:function(e){this.drawer=e},retrivePaymentBillList:function(){var e=this;Object(i.b)(n()({startTime:this.search.datetime[0],endTime:this.search.datetime[1],page:this.pageCurrent,limit:this.pageSize},this.search)).then(function(t){console.log(t),e.paymentBillListData=t.data.list,e.pageTotal=t.data.totalRow}).catch(function(){})},searchReset:function(){this.search={contractNumber:"",supplierId:""},this.retrivePaymentBillList()},commitData:function(){this.$refs.child.createContact(),this.dialogVisible=!1},drawerDetail:function(e){this.drawer=!0,this.drawerComponents="DrawerDetail",this.drawerData=e.paymentRecordId,this.rowData=e}}},s=(a("vuGY"),a("KHd+")),o=Object(s.a)(l,function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticStyle:{height:"100%"}},[e._m(0),e._v(" "),a("div",{staticClass:"ls-box"},[a("el-form",{ref:"form",attrs:{inline:!0}},[a("el-form-item",{attrs:{label:"付款编号："}},[a("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.search.paymentNumber,callback:function(t){e.$set(e.search,"paymentNumber",t)},expression:"search.paymentNumber"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"客户："}},[a("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.search.supplierId,callback:function(t){e.$set(e.search,"supplierId",t)},expression:"search.supplierId"}})],1),e._v(" "),a("el-form-item",[a("span",[a("el-button",{attrs:{type:"primary"},on:{click:e.retrivePaymentBillList}},[e._v("查询")])],1),e._v(" "),a("span",[a("el-button",{on:{click:e.searchReset}},[e._v("重置")])],1)])],1),e._v(" "),a("el-table",{staticStyle:{margin:"20px 0 0 0"},attrs:{data:e.paymentBillListData,"header-cell-style":{background:"#F5F7FA",color:"#606266"},border:"",height:"80%"}},[a("el-table-column",{attrs:{type:"index",label:"序号",width:"55"}}),e._v(" "),a("el-table-column",{attrs:{prop:"paymentNumber",label:"付款编号",width:"150"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{type:"text",size:"small"},on:{click:function(a){e.drawerDetail(t.row)}}},[e._v(e._s(t.row.paymentNumber))])]}}])}),e._v(" "),a("el-table-column",{attrs:{prop:"supplierId",label:"客户/承包商"}}),e._v(" "),a("el-table-column",{attrs:{prop:"amountAdvanced",label:"预付金额"}}),e._v(" "),a("el-table-column",{attrs:{prop:"practicalRatio",label:"实付比例",width:"150"}}),e._v(" "),a("el-table-column",{attrs:{prop:"practicaAdvanced",label:"实付金额",width:"150"}}),e._v(" "),a("el-table-column",{attrs:{prop:"paymentTime",label:"实际付款时间",width:"150"}}),e._v(" "),a("el-table-column",{attrs:{prop:"paymentType",label:"付款方式"}}),e._v(" "),a("el-table-column",{attrs:{prop:"responsiblePerson",label:"经办人"}}),e._v(" "),a("el-table-column",{attrs:{prop:"remark",label:"备注"}})],1),e._v(" "),a("div",{staticClass:"ls-pagination"},[a("el-pagination",{attrs:{"current-page":e.pageCurrent,"page-sizes":[2,10,20],"page-size":e.pageSize,total:e.pageTotal,layout:"total, sizes, prev, pager, next, jumper"},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}})],1),e._v(" "),a("el-drawer",{attrs:{title:e.drawerTitle,"with-header":!1,visible:e.drawer,size:"70%"},on:{"update:visible":function(t){e.drawer=t},open:function(t){e.drawerDetail()}}},[a(e.drawerComponents,{tag:"component",attrs:{"drawer-data":e.drawerData,"row-data":e.rowData},on:{drawerStatus:e.drawerStatus}})],1)],1)])},[function(){var e=this.$createElement,t=this._self._c||e;return t("div",{staticClass:"ls-header"},[this._v("\n    付款账单\n    "),t("span",{staticClass:"grep"})])}],!1,null,"c5ed21fa",null);o.options.__file="index.vue";t.default=o.exports},o6vM:function(e,t,a){"use strict";a.d(t,"b",function(){return n}),a.d(t,"a",function(){return i});var r=a("t3Un");function n(e){return Object(r.a)({url:"pmpContractPaymentRecord/queryList",method:"post",data:e})}function i(e){return Object(r.a)({url:"pmpContractPaymentRecord/queryById",method:"post",data:e})}},vuGY:function(e,t,a){"use strict";var r=a("YpDP");a.n(r).a}}]);