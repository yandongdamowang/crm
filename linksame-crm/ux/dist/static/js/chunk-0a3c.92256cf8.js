(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-0a3c"],{"8gwX":function(e,t,a){"use strict";var i=a("YrMO"),n=a("Ew9n"),l=a("F03V"),r=a("U05J"),s=a("C+sD"),o=a("8DbX"),c=a("EgJF"),d=a("4+0Q"),u={name:"ProductDetail",components:{SlideView:n.a,CRMDetailHead:l.a,CRMBaseInfo:r.a,RelativeFiles:s.a,RelativeHandle:o.a,CRMCreateView:c.default},mixins:[d.a],props:{id:[String,Number],listenerIDs:{type:Array,default:function(){return["crm-main-container"]}},noListenerIDs:{type:Array,default:function(){return[]}},noListenerClass:{type:Array,default:function(){return["el-table__body"]}}},data:function(){return{loading:!1,crmType:"product",detailData:{},headDetails:[{title:"产品类别",value:""},{title:"产品单位",value:""},{title:"产品价格",value:""},{title:"产品编码",value:""}],tabnames:[{label:"基本信息",name:"basicinfo"},{label:"附件",name:"file"},{label:"操作记录",name:"operationlog"}],tabCurrentName:"basicinfo",isCreate:!1}},computed:{tabName:function(){return"basicinfo"==this.tabCurrentName?"c-r-m-base-info":"file"==this.tabCurrentName?"relative-files":"operationlog"==this.tabCurrentName?"relative-handle":""}},mounted:function(){},methods:{getDetial:function(){var e=this;this.loading=!0,Object(i.g)({productId:this.id}).then(function(t){e.loading=!1,e.detailData=t.data,e.headDetails[0].value=t.data.categoryName,e.headDetails[1].value=t.data.单位,e.headDetails[2].value=e.moneyFormat(t.data.price),e.headDetails[3].value=t.data.num}).catch(function(){e.loading=!1})},hideView:function(){this.$emit("hide-view")},handleClick:function(e,t){},editSaveSuccess:function(){this.$emit("handle",{type:"save-success"}),this.getDetial()}}},h=(a("FYRF"),a("ZrdR")),m=Object(h.a)(u,function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("slide-view",{directives:[{name:"empty",rawName:"v-empty",value:!e.canShowDetail,expression:"!canShowDetail"}],attrs:{"listener-ids":e.listenerIDs,"no-listener-ids":e.noListenerIDs,"no-listener-class":e.noListenerClass,"body-style":{padding:0,height:"100%"},"xs-empty-icon":"nopermission","xs-empty-text":"暂无权限"},on:{"side-close":e.hideView}},[e.canShowDetail?a("flexbox",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],staticClass:"d-container",attrs:{direction:"column",align:"stretch"}},[a("c-r-m-detail-head",{attrs:{detail:e.detailData,"head-details":e.headDetails,id:e.id,"crm-type":"product"},on:{handle:e.detailHeadHandle,close:e.hideView}}),e._v(" "),a("div",{staticClass:"tabs"},[a("el-tabs",{on:{"tab-click":e.handleClick},model:{value:e.tabCurrentName,callback:function(t){e.tabCurrentName=t},expression:"tabCurrentName"}},e._l(e.tabnames,function(e,t){return a("el-tab-pane",{key:t,attrs:{label:e.label,name:e.name}})}))],1),e._v(" "),a("div",{staticClass:"t-loading-content"},[a("keep-alive",[a(e.tabName,{tag:"component",attrs:{detail:e.detailData,id:e.id,"crm-type":"product"}})],1)],1)],1):e._e(),e._v(" "),e.isCreate?a("c-r-m-create-view",{attrs:{action:{type:"update",id:e.id,batchId:e.detailData.batchId},"crm-type":"product"},on:{"save-success":e.editSaveSuccess,"hiden-view":function(t){e.isCreate=!1}}}):e._e()],1)},[],!1,null,"bcdf22de",null);m.options.__file="ProductDetail.vue";t.a=m.exports},FYRF:function(e,t,a){"use strict";var i=a("V5ku");a.n(i).a},"V+Dy":function(e,t,a){},V5ku:function(e,t,a){},hDFS:function(e,t,a){"use strict";var i=a("V+Dy");a.n(i).a},zmAU:function(e,t,a){"use strict";a.r(t);var i=a("8gwX"),n=a("kegP"),l={name:"ProductIndex",components:{ProductDetail:i.a},mixins:[n.a],data:function(){return{crmType:"product"}},computed:{},mounted:function(){},methods:{cellStyle:function(e){e.row;var t=e.column;e.rowIndex,e.columnIndex;return"name"===t.property?{color:"#3E84E9",cursor:"pointer"}:""}}},r=(a("hDFS"),a("ZrdR")),s=Object(r.a)(l,function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",[i("c-r-m-list-head",{ref:"listHead",attrs:{search:e.search,"crm-type":e.crmType,title:"产品管理",placeholder:"请输入产品名称","main-title":"新建产品"},on:{"update:search":function(t){e.search=t},"on-handle":e.listHeadHandle,"on-search":e.crmSearch,"on-export":e.exportInfos}}),e._v(" "),i("div",{directives:[{name:"empty",rawName:"v-empty",value:!e.crm.product.index,expression:"!crm.product.index"}],staticClass:"crm-container",attrs:{"xs-empty-icon":"nopermission","xs-empty-text":"暂无权限"}},[i("c-r-m-table-head",{ref:"crmTableHead",attrs:{"crm-type":e.crmType},on:{exportData:e.exportData,filter:e.handleFilter,handle:e.handleHandle,scene:e.handleScene}}),e._v(" "),i("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],staticClass:"n-table--border",staticStyle:{width:"100%"},attrs:{id:"crm-table",data:e.list,height:e.tableHeight,"cell-style":e.cellStyle,stripe:"",border:"","highlight-current-row":""},on:{"row-click":e.handleRowClick,"sort-change":e.sortChange,"header-dragend":e.handleHeaderDragend,"selection-change":e.handleSelectionChange}},[i("el-table-column",{attrs:{"show-overflow-tooltip":"",type:"selection",align:"center",width:"55"}}),e._v(" "),e._l(e.fieldList,function(t,a){return i("el-table-column",{key:a,attrs:{fixed:0==a,prop:t.prop,label:t.label,width:t.width,formatter:e.fieldFormatter,sortable:"custom","show-overflow-tooltip":""},scopedSlots:e._u([{key:"header",fn:function(t){return[i("div",{staticClass:"table-head-name"},[e._v(e._s(t.column.label))])]}}])})}),e._v(" "),i("el-table-column"),e._v(" "),i("el-table-column",{attrs:{fixed:"right",width:"36"},scopedSlots:e._u([{key:"header",fn:function(t){return[i("img",{staticClass:"table-set",attrs:{src:a("zbTZ")},on:{click:e.handleTableSet}})]}}])})],2),e._v(" "),i("div",{staticClass:"p-contianer"},[i("el-pagination",{staticClass:"p-bar",attrs:{"current-page":e.currentPage,"page-sizes":e.pageSizes,"page-size":e.pageSize,total:e.total,layout:"total, sizes, prev, pager, next, jumper"},on:{"update:pageSize":function(t){e.pageSize=t},"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}})],1)],1),e._v(" "),e.showDview?i("product-detail",{staticClass:"d-view",attrs:{id:e.rowID},on:{handle:e.handleHandle,"hide-view":function(t){e.showDview=!1}}}):e._e(),e._v(" "),i("fields-set",{attrs:{"crm-type":e.crmType,"dialog-visible":e.showFieldSet},on:{"update:dialogVisible":function(t){e.showFieldSet=t},"set-success":e.setSave}})],1)},[],!1,null,"2c08d0e6",null);s.options.__file="ProductIndex.vue";t.default=s.exports}}]);