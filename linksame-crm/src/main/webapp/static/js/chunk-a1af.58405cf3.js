(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-a1af"],{JSWZ:function(t,e,a){"use strict";a.r(e);var l={name:"ContractIndex",components:{DialogPayment:function(){return a.e("chunk-ab52").then(a.bind(null,"Tch4"))},DialogCreate:function(){return a.e("chunk-6426").then(a.bind(null,"qf+I"))}},data:function(){return{dialogComponents:"",dialogTitle:"",dialogVisible:!1,tableData:[{id:100,name:"测试"}],formData:{a:"",b:"",c:""}}},computed:{},mounted:function(){},methods:{dialogPayment:function(){this.dialogVisible=!0,this.dialogTitle="支付比例调整",this.dialogComponents="DialogPayment"},dialogCreate:function(){this.dialogVisible=!0,this.dialogTitle="新建合同",this.dialogComponents="DialogCreate"},operationDetail:function(t){}}},o=(a("M2ab"),a("ZrdR")),i=Object(o.a)(l,function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"box"},[a("el-form",{ref:"form",attrs:{model:t.formData,inline:!0}},[a("el-form-item",{attrs:{label:"合同编号："}},[a("el-input",{attrs:{placeholder:"请输入内容"},model:{value:t.formData.a,callback:function(e){t.$set(t.formData,"a",e)},expression:"formData.a"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"客户/承包商："}},[a("el-input",{attrs:{placeholder:"请输入内容"},model:{value:t.formData.b,callback:function(e){t.$set(t.formData,"b",e)},expression:"formData.b"}})],1),t._v(" "),a("el-form-item",[a("span",[a("el-button",{attrs:{type:"primary"}},[t._v("查询")])],1),t._v(" "),a("span",[a("el-button",[t._v("重置")])],1)])],1),t._v(" "),a("div",[a("span",[a("el-button",{attrs:{type:"primary"},on:{click:t.dialogCreate}},[t._v("新建")])],1),t._v(" "),a("span",[a("el-button",[t._v("批量操作")])],1),t._v(" "),a("span",[a("el-button",[t._v("导出")])],1),t._v(" "),a("span",[a("el-button",{attrs:{type:"primary"},on:{click:t.dialogPayment}},[t._v("批量修改支付比例")])],1)]),t._v(" "),a("el-table",{staticStyle:{width:"100%"},attrs:{data:t.tableData,border:""}},[a("el-table-column",{attrs:{type:"selection",width:"55"}}),t._v(" "),a("el-table-column",{attrs:{prop:"id",label:"合同编号",width:"150"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-button",{attrs:{type:"text",size:"small"},on:{click:function(a){t.operationDetail(e.row)}}},[t._v(t._s(e.row.id))])]}}])}),t._v(" "),a("el-table-column",{attrs:{prop:"name",label:"合同名称",width:"120"}}),t._v(" "),a("el-table-column",{attrs:{prop:"province",label:"客户/承包商",width:"120"}}),t._v(" "),a("el-table-column",{attrs:{prop:"city",label:"合同总金额",width:"120"}}),t._v(" "),a("el-table-column",{attrs:{prop:"address",label:"要求签署时间",width:"300"}}),t._v(" "),a("el-table-column",{attrs:{prop:"zip",label:"实际签署时间",width:"120"}}),t._v(" "),a("el-table-column",{attrs:{prop:"zip",label:"DRP采购单号",width:"120"}}),t._v(" "),a("el-table-column",{attrs:{prop:"zip",label:"经办人",width:"120"}})],1),t._v(" "),a("el-dialog",{attrs:{visible:t.dialogVisible,title:t.dialogTitle,fullscreen:!0,width:"30%"},on:{"update:visible":function(e){t.dialogVisible=e}}},[a(t.dialogComponents,{tag:"component"}),t._v(" "),a("span",{attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(e){t.dialogVisible=!1}}},[t._v("取 消")]),t._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:function(e){t.dialogVisible=!1}}},[t._v("确 定")])],1)],1)],1)},[],!1,null,"aee57a7e",null);i.options.__file="Main.vue";e.default=i.exports},M2ab:function(t,e,a){"use strict";var l=a("z00s");a.n(l).a},z00s:function(t,e,a){}}]);