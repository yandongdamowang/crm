(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-6c9f"],{"4V3i":function(t,e,a){},ACGY:function(t,e,a){"use strict";var l=a("4V3i");a.n(l).a},JSWZ:function(t,e,a){"use strict";a.r(e);var l={name:"ContractIndex",components:{DialogPayment:function(){return a.e("chunk-1277").then(a.bind(null,"Tch4"))},DialogCreate:function(){return a.e("chunk-2534").then(a.bind(null,"qf+I"))},DrawerDetail:function(){return a.e("chunk-3335").then(a.bind(null,"VWNw"))}},data:function(){return{dialogComponents:"",dialogTitle:"",dialogwidth:"",dialogVisible:!1,drawerComponents:"",drawer:!1,drawerTitle:"",drawerData:"123",tableData:[{id:100,b:"测试合同",c:"上海测试公司",d:"50.000.00",e:"2017-10-01",f:"2017-10-01",g:"191111-REC-工程-S1-珠海一键-智能化改造",h:"胡彦斌"}],formData:{a:"",b:"",c:""}}},computed:{},mounted:function(){},methods:{dialogPayment:function(){this.dialogVisible=!0,this.dialogTitle="支付比例调整",this.dialogComponents="DialogPayment",this.dialogwidth="50%"},dialogCreate:function(){this.dialogVisible=!0,this.dialogTitle="新建合同",this.dialogComponents="DialogCreate",this.dialogwidth="90%"},drawerDetail:function(t){this.drawer=!0,this.drawerTitle="",this.drawerComponents="DrawerDetail",this.drawerData=t.g}}},i=(a("ACGY"),a("ZrdR")),o=Object(i.a)(l,function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"box"},[a("el-form",{ref:"form",attrs:{model:t.formData,inline:!0}},[a("el-form-item",{attrs:{label:"合同编号："}},[a("el-input",{attrs:{placeholder:"请输入内容"},model:{value:t.formData.a,callback:function(e){t.$set(t.formData,"a",e)},expression:"formData.a"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"客户/承包商："}},[a("el-input",{attrs:{placeholder:"请输入内容"},model:{value:t.formData.b,callback:function(e){t.$set(t.formData,"b",e)},expression:"formData.b"}})],1),t._v(" "),a("el-form-item",[a("span",[a("el-button",{attrs:{type:"primary"}},[t._v("查询")])],1),t._v(" "),a("span",[a("el-button",[t._v("重置")])],1)])],1),t._v(" "),a("div",[a("span",[a("el-button",{attrs:{type:"primary"},on:{click:t.dialogCreate}},[t._v(" + 新建")])],1),t._v(" "),a("span",[a("el-button",[t._v("批量操作")])],1),t._v(" "),a("span",[a("el-button",[t._v("导出")])],1),t._v(" "),a("span",[a("el-button",{attrs:{type:"primary"},on:{click:t.dialogPayment}},[t._v("批量修改支付比例")])],1)]),t._v(" "),a("el-table",{staticStyle:{width:"100%",margin:"20px 0 0 0"},attrs:{data:t.tableData,border:""}},[a("el-table-column",{attrs:{type:"selection",width:"55"}}),t._v(" "),a("el-table-column",{attrs:{prop:"id",label:"合同编号",width:"150"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-button",{attrs:{type:"text",size:"small"},on:{click:function(a){t.drawerDetail(e.row)}}},[t._v(t._s(e.row.id))])]}}])}),t._v(" "),a("el-table-column",{attrs:{prop:"b",label:"合同名称",width:"120"}}),t._v(" "),a("el-table-column",{attrs:{prop:"c",label:"客户/承包商",width:"120"}}),t._v(" "),a("el-table-column",{attrs:{prop:"d",label:"合同总金额",width:"120"}}),t._v(" "),a("el-table-column",{attrs:{prop:"e",label:"要求签署时间",width:"120"}}),t._v(" "),a("el-table-column",{attrs:{prop:"f",label:"实际签署时间",width:"120"}}),t._v(" "),a("el-table-column",{attrs:{prop:"g",label:"DRP采购单号",width:"120"}}),t._v(" "),a("el-table-column",{attrs:{prop:"h",label:"经办人",width:"120"}})],1),t._v(" "),a("el-dialog",{attrs:{visible:t.dialogVisible,title:t.dialogTitle,width:t.dialogwidth},on:{"update:visible":function(e){t.dialogVisible=e}}},[a(t.dialogComponents,{tag:"component"}),t._v(" "),a("span",{attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(e){t.dialogVisible=!1}}},[t._v("取 消")]),t._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:function(e){t.dialogVisible=!1}}},[t._v("确 定")])],1)],1),t._v(" "),a("el-drawer",{attrs:{visible:t.drawer,title:t.drawerTitle,size:"70%"},on:{"update:visible":function(e){t.drawer=e},open:function(e){t.drawerDetail()}}},[a(t.drawerComponents,{tag:"component",attrs:{"drawer-data":t.drawerData}})],1)],1)},[],!1,null,"7497f69c",null);o.options.__file="Main.vue";e.default=o.exports}}]);