(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-d1d0"],{"/mRb":function(t,e,s){"use strict";var i=s("dxXo");s.n(i).a},"/zI9":function(t,e,s){},"9KCS":function(t,e,s){"use strict";var i=s("qTcG");s.n(i).a},AqoG:function(t,e,s){},B6tz:function(t,e,s){"use strict";var i=s("/zI9");s.n(i).a},Csel:function(t,e){t.exports="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABEAAAARCAYAAAA7bUf6AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMTQyIDc5LjE2MDkyNCwgMjAxNy8wNy8xMy0wMTowNjozOSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTggKFdpbmRvd3MpIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOjU0OTlCOUZDRERCMjExRTg4MjVEQ0RGOThFMUY2OTEyIiB4bXBNTTpEb2N1bWVudElEPSJ4bXAuZGlkOjU0OTlCOUZERERCMjExRTg4MjVEQ0RGOThFMUY2OTEyIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6NTQ5OUI5RkFEREIyMTFFODgyNURDREY5OEUxRjY5MTIiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6NTQ5OUI5RkJEREIyMTFFODgyNURDREY5OEUxRjY5MTIiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz5iPiS9AAAAY0lEQVR42mI8c+aMLQMDw0IgVmQgHdwH4jgmIDEHiNOBmJEMDNI3lxHokv9QAXLBfyYGKoBRQzABC7bQJkIfIyFDGEdjZxAZcguIXcjUD9J3CxTFKUC8CIgVyDDkARDHAgQYANTlEN4TaBTMAAAAAElFTkSuQmCC"},DIbM:function(t,e,s){"use strict";var i=s("AqoG");s.n(i).a},GBpN:function(t,e,s){"use strict";s.r(e);var i=s("conU"),n={name:"FollowLogTypeSet",components:{},data:function(){return{loading:!1,list:[]}},created:function(){this.getDetail()},methods:{getDetail:function(){var t=this;this.loading=!0,Object(i.o)().then(function(e){t.loading=!1,t.list=e.data.map(function(t){return{value:t}})}).catch(function(){t.loading=!1})},addItem:function(){this.list.push({value:""})},deleteItem:function(t,e){this.list.splice(e,1)},save:function(){for(var t=this,e=[],s=0;s<this.list.length;s++){var n=this.list[s];n.value&&e.push(n.value)}this.loading=!0,Object(i.n)({value:e}).then(function(e){t.loading=!1,t.$message.success("操作成功")}).catch(function(){t.loading=!1})}}},a=(s("TMTT"),s("KHd+")),o=Object(a.a)(n,function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{directives:[{name:"loading",rawName:"v-loading",value:t.loading,expression:"loading"}]},[s("div",{staticClass:"content-title"},[s("span",[t._v("跟进记录类型设置")]),t._v(" "),s("el-button",{staticClass:"rt",attrs:{type:"primary",size:"medium"},on:{click:t.save}},[t._v("保存")])],1),t._v(" "),s("div",{staticClass:"content-body"},[t._l(t.list,function(e,i){return s("div",{key:i,staticClass:"input-item"},[s("el-input",{model:{value:e.value,callback:function(s){t.$set(e,"value",s)},expression:"item.value"}}),t._v(" "),s("i",{staticClass:"el-icon-remove",on:{click:function(s){t.deleteItem(e,i)}}})],1)}),t._v(" "),s("el-button",{attrs:{type:"text"},on:{click:t.addItem}},[t._v("+添加类型")])],2)])},[],!1,null,"993494c8",null);o.options.__file="followLogTypeSet.vue";var l=o.exports,c=s("KTTK"),r={props:{businessDialogVisible:Boolean,businessTitle:String,infoData:{type:Object,default:function(){return{}}}},data:function(){return{optionsDep:[],businessDep:[],settingList:[],name:"",winSingle:100,loseSingle:0,invalidSingle:0}},watch:{infoData:function(t){this.name=t.name,this.businessDep=t.businessDep.map(function(t,e){return t.id}),this.settingList=t.settingList,this.settingList&&0!=this.settingList.length||(this.settingList=[{name:"",rate:""}])}},mounted:function(){var t=this;Object(c.g)().then(function(e){t.optionsDep=e.data})},methods:{businessClose:function(){this.$emit("businessClose")},businessSubmit:function(){if(this.name)if(this.businessDep){for(var t=!0,e=0;e<this.settingList.length;e++){var s=this.settingList[e];if(!s.name||!s.rate){t=!1,this.$message.error("请完善阶段信息");break}}t&&this.$emit("businessSubmit",this.name,this.businessDep,this.settingList,this.businessTitle,this.infoData.typeId)}else this.$message.error("请关联应用部门");else this.$message.error("请填写商机组名称")},addIcon:function(){this.settingList.push({name:"",rate:""})},removeIcon:function(t){this.settingList.splice(t,1),this.changeInput()},changeInput:function(t,e){}}},u=(s("DIbM"),Object(a.a)(r,function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("el-dialog",{attrs:{title:t.businessTitle,visible:t.businessDialogVisible,"before-close":t.businessClose,width:"600px"},on:{"update:visible":function(e){t.businessDialogVisible=e}}},[s("div",{staticClass:"business-list"},[s("div",{staticClass:"business-label"},[t._v("商机组名称")]),t._v(" "),s("el-input",{staticStyle:{width:"60%"},model:{value:t.name,callback:function(e){t.name=e},expression:"name"}})],1),t._v(" "),s("div",{staticClass:"business-list"},[s("div",{staticClass:"business-label"},[t._v("应用部门")]),t._v(" "),s("el-select",{staticStyle:{width:"60%"},attrs:{placeholder:"请选择",multiple:""},model:{value:t.businessDep,callback:function(e){t.businessDep=e},expression:"businessDep"}},t._l(t.optionsDep,function(t){return s("el-option",{key:t.id,attrs:{label:t.label,value:t.id}})})),t._v(" "),s("div",{staticClass:"business-des"},[t._v("默认全公司")])],1),t._v(" "),s("div",{staticClass:"business-setting"},[s("div",[t._v("阶段设置")]),t._v(" "),s("div",{staticClass:"business-setting-content"},[s("div",{staticClass:"business-setting-table-header"},[s("span",[t._v("阶段")]),t._v(" "),s("span",[t._v("阶段名称")]),t._v(" "),s("span",[t._v("赢单率")])]),t._v(" "),s("div",{staticClass:"business-setting-table-body"},[t._l(t.settingList,function(e,i){return s("div",{key:i,staticClass:"business-setting-table"},[s("span",[t._v(t._s("阶段"+(i+1)))]),t._v(" "),s("span",[s("el-input",{attrs:{size:"mini"},model:{value:e.name,callback:function(s){t.$set(e,"name",s)},expression:"item.name"}})],1),t._v(" "),s("span",{staticClass:"icon-span"},[s("el-input",{attrs:{size:"mini",type:"number"},on:{change:t.changeInput},model:{value:e.rate,callback:function(s){t.$set(e,"rate",s)},expression:"item.rate"}}),t._v(" %\n            "),s("span",{staticClass:"icon-box"},[s("span",{staticClass:"el-icon-circle-plus",on:{click:t.addIcon}}),t._v(" "),0!=i?s("span",{staticClass:"el-icon-remove",on:{click:function(e){t.removeIcon(i)}}}):t._e()])],1)])}),t._v(" "),s("div",{staticClass:"business-setting-table"},[s("span",[t._v("结束")]),t._v(" "),s("span",[t._v("赢单")]),t._v(" "),s("span",[t._v(t._s(t.winSingle)+" %")])]),t._v(" "),s("div",{staticClass:"business-setting-table"},[s("span",[t._v("结束")]),t._v(" "),s("span",[t._v("输单")]),t._v(" "),s("span",[t._v(t._s(t.loseSingle)+" %")])]),t._v(" "),s("div",{staticClass:"business-setting-table"},[s("span",[t._v("结束")]),t._v(" "),s("span",[t._v("无效")]),t._v(" "),s("span",[t._v(t._s(t.invalidSingle)+" %")])])],2)])]),t._v(" "),s("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[s("el-button",{attrs:{type:"primary"},on:{click:t.businessSubmit}},[t._v("确 定")]),t._v(" "),s("el-button",{on:{click:t.businessClose}},[t._v("取 消")])],1)])},[],!1,null,"1f32e327",null));u.options.__file="businessDialog.vue";var d={name:"BusinessGroupSet",components:{BusinessDialog:u.exports},data:function(){return{loading:!1,tableHeight:document.documentElement.clientHeight-320,businessObj:{name:"",businessDep:[],settingList:""},businessData:[],businessList:[{label:"商机组名称",field:"name"},{label:"应用部门",field:"deptName"},{label:"创建时间",field:"createTime"},{label:"创建人",field:"createName"}],businessDialogVisible:!1,businessTitle:"添加商机组",currentPage:1,pageSize:10,pageSizes:[10,20,30,40],total:0}},created:function(){var t=this;window.onresize=function(){t.tableHeight=document.documentElement.clientHeight-320},this.getBusinessGroupList()},methods:{headerCellStyle:function(t,e){return{background:"#F2F2F2"}},handleSizeChange:function(t){this.pageSize=t,this.getBusinessGroupList()},handleCurrentChange:function(t){this.currentPage=t,this.getBusinessGroupList()},getBusinessGroupList:function(){var t=this;this.loading=!0,Object(i.c)({page:this.currentPage,limit:this.pageSize}).then(function(e){t.loading=!1,t.businessData=e.data.list,t.total=e.data.totalRow}).catch(function(){t.loading=!1})},fieldFormatter:function(t,e){if("deptName"==e.property){var s=t.deptIds,i="";if(s)for(var n=0;n<s.length;n++)i=i+s[n].name+(n===s.length-1?"":"、");return i||"全公司"}return t[e.property]},businessEdit:function(t){var e=this;Object(i.d)({id:t.typeId}).then(function(t){var s=t.data.statusList||[];e.businessObj={typeId:t.data.typeId,name:t.data.name,businessDep:t.data.deptIds||[],settingList:s},e.businessDialogVisible=!0,e.businessTitle="编辑商机组"}).catch(function(){})},businessDelect:function(t){var e=this;this.$confirm("确定删除?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){Object(i.b)({id:t.row.typeId}).then(function(s){e.businessData.splice(t.$index,1),e.$message.success("删除成功")}).catch(function(){})}).catch(function(){e.$message({type:"info",message:"已取消删除"})})},addBusiness:function(){this.businessObj={name:"",businessDep:[],settingList:""},this.businessDialogVisible=!0,this.businessTitle="添加商机组"},businessClose:function(){this.businessDialogVisible=!1},businessSubmit:function(t,e,s,n,a){var o=this,l={crmBusinessType:{name:t,typeId:a||null},deptIds:e,crmBusinessStatus:s};("添加商机组"==n?i.a:i.e)(l).then(function(t){o.$message.success("操作成功"),o.getBusinessGroupList(),o.businessClose()}).catch(function(){})}}},p=(s("LUPh"),Object(a.a)(d,function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{directives:[{name:"loading",rawName:"v-loading",value:t.loading,expression:"loading"}]},[s("div",{staticClass:"content-title"},[s("span",[t._v("商机组设置")]),t._v(" "),s("el-button",{staticClass:"rt",attrs:{type:"primary",size:"medium"},on:{click:t.addBusiness}},[t._v("添加商机组")])],1),t._v(" "),s("div",{staticClass:"business-table"},[s("el-table",{staticStyle:{width:"100%"},attrs:{data:t.businessData,height:t.tableHeight,"header-cell-style":t.headerCellStyle,stripe:""}},[t._l(t.businessList,function(e,i){return s("el-table-column",{key:i,attrs:{prop:e.field,label:e.label,formatter:t.fieldFormatter,"show-overflow-tooltip":""}})}),t._v(" "),s("el-table-column",{attrs:{fixed:"right",label:"操作",width:"140"},scopedSlots:t._u([{key:"default",fn:function(e){return[s("el-button",{attrs:{type:"text",size:"small"},on:{click:function(s){t.businessEdit(e.row)}}},[t._v("编 辑")]),t._v(" "),s("el-button",{attrs:{type:"text",size:"small"},on:{click:function(s){t.businessDelect(e)}}},[t._v("删 除")])]}}])})],2),t._v(" "),s("div",{staticClass:"p-contianer"},[s("el-pagination",{staticClass:"p-bar",attrs:{"current-page":t.currentPage,"page-sizes":t.pageSizes,"page-size":t.pageSize,total:t.total,layout:"total, sizes, prev, pager, next, jumper"},on:{"update:pageSize":function(e){t.pageSize=e},"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}})],1)],1),t._v(" "),s("business-dialog",{attrs:{"info-data":t.businessObj,"business-dialog-visible":t.businessDialogVisible,"business-title":t.businessTitle},on:{businessClose:t.businessClose,businessSubmit:t.businessSubmit}})],1)},[],!1,null,"914df5a4",null));p.options.__file="businessGroupSet.vue";var m=p.exports,h={name:"ProductCategorySet",components:{},data:function(){return{loading:!1,treeData:[],treeSetTypes:[],productHandleDialog:!1,productForm:{name:"",type:"",pid:"",categoryId:""},defaultProps:{children:"children",label:"label"}}},created:function(){this.getProductCategoryIndex()},methods:{getChild:function(t){this.treeSetTypes=[{type:"create-child",name:"新建子分类"},{type:"create-brother",name:"新建平级分类"},{type:"edit",name:"编辑分类"},{type:"delete",name:"删除分类"}].map(function(e,s,i){return e.node=t,e})},handleTreeSetDrop:function(t){var e=this;"create-one"==t.type&&(this.productForm.type=t.type,this.productForm.name="",this.productHandleDialog=!0),"create-child"==t.type?(this.productForm.type=t.type,this.productForm.pid=t.node.data.categoryId,this.productForm.name="",this.productHandleDialog=!0):"create-brother"==t.type?(this.productForm.type=t.type,this.productForm.pid=t.node.data.pid,this.productForm.name="",this.productHandleDialog=!0):"edit"==t.type?(this.productForm.type=t.type,this.productForm.name=t.node.data.name,this.productForm.categoryId=t.node.data.categoryId,this.productForm.pid=t.node.data.pid,this.productHandleDialog=!0):"delete"==t.type&&this.$confirm("确定删除?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){e.loading=!0,Object(i.t)({id:t.node.data.categoryId}).then(function(t){e.$message({type:"success",message:"删除成功!"}),e.getProductCategoryIndex(),e.loading=!1}).catch(function(){e.loading=!1})}).catch(function(){e.$message({type:"info",message:"已取消删除"})})},handleProduct:function(){var t=this;0!=this.productForm.name.length?(this.productHandleDialog=!1,"create-one"==this.productForm.type&&(this.loading=!0,Object(i.v)({name:this.productForm.name}).then(function(e){t.getProductCategoryIndex(),t.$message.success("新增成功"),t.loading=!1}).catch(function(){t.loading=!1})),"create-child"==this.productForm.type?(this.loading=!0,Object(i.v)({pid:this.productForm.pid,name:this.productForm.name}).then(function(e){t.getProductCategoryIndex(),t.$message.success("新建成功"),t.loading=!1}).catch(function(){t.loading=!1})):"create-brother"==this.productForm.type?(this.loading=!0,Object(i.v)({pid:this.productForm.pid,name:this.productForm.name}).then(function(e){t.getProductCategoryIndex(),t.$message.success("新建成功"),t.loading=!1}).catch(function(){t.loading=!1})):"edit"==this.productForm.type&&(this.loading=!0,Object(i.v)({categoryId:this.productForm.categoryId,pid:this.productForm.pid,name:this.productForm.name}).then(function(e){t.getProductCategoryIndex(),t.$message.success("编辑成功"),t.loading=!1}).catch(function(){t.loading=!1}))):this.$message({message:"请填写名称",type:"warning"})},getProductCategoryIndex:function(){var t=this;this.loading=!0,Object(i.u)({type:"tree"}).then(function(e){t.loading=!1,t.treeData=e.data}).catch(function(){t.loading=!1})}}},g=(s("/mRb"),Object(a.a)(h,function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{directives:[{name:"loading",rawName:"v-loading",value:t.loading,expression:"loading"}]},[t._m(0),t._v(" "),i("div",{staticClass:"product-setting"},[i("div",{staticClass:"product-setting-con"},[i("div",[i("span",{staticClass:"el-icon-plus",staticStyle:{color:"#409EFF"}}),t._v(" "),i("el-button",{attrs:{type:"text"},nativeOn:{click:function(e){t.handleTreeSetDrop({type:"create-one"})}}},[t._v("新增一级分类")])],1),t._v(" "),i("div",{staticClass:"tree-box"},[i("el-tree",{attrs:{data:t.treeData,props:t.defaultProps,"default-expand-all":""},scopedSlots:t._u([{key:"default",fn:function(e){var n=e.node;return e.data,i("flexbox",{staticClass:"node-data"},[n.expanded?i("img",{staticClass:"node-img",attrs:{src:s("Csel")}}):t._e(),t._v(" "),n.expanded?t._e():i("img",{staticClass:"node-img",attrs:{src:s("zXZL")}}),t._v(" "),i("div",{staticClass:"node-label"},[t._v(t._s(n.label))]),t._v(" "),i("el-dropdown",{attrs:{trigger:"click"},on:{command:t.handleTreeSetDrop}},[i("div",{staticClass:"node-label-set",on:{click:function(e){e.stopPropagation(),t.getChild(n)}}},[t._v("设置")]),t._v(" "),i("el-dropdown-menu",{attrs:{slot:"dropdown"},slot:"dropdown"},t._l(t.treeSetTypes,function(e,s){return i("el-dropdown-item",{key:s,attrs:{command:e}},[t._v(t._s(e.name))])}))],1)],1)}}])})],1)])]),t._v(" "),i("el-dialog",{attrs:{visible:t.productHandleDialog,title:"提示",width:"400px"},on:{"update:visible":function(e){t.productHandleDialog=e}}},[i("el-form",{attrs:{model:t.productForm}},[i("el-form-item",{attrs:{label:"类别名称","label-width":"80"}},[i("el-input",{attrs:{autocomplete:"off"},model:{value:t.productForm.name,callback:function(e){t.$set(t.productForm,"name",e)},expression:"productForm.name"}})],1)],1),t._v(" "),i("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[i("el-button",{on:{click:function(e){t.productHandleDialog=!1}}},[t._v("取 消")]),t._v(" "),i("el-button",{attrs:{type:"primary"},on:{click:t.handleProduct}},[t._v("确 定")])],1)],1)],1)},[function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticClass:"content-title"},[e("span",[this._v("产品类别设置")])])}],!1,null,"7f6b12e7",null));g.options.__file="productCategorySet.vue";var v=g.exports,b={name:"ContractExpireSet",components:{},data:function(){return{loading:!1,contractDay:0,contractConfig:0}},created:function(){this.getDetail()},methods:{getDetail:function(){var t=this;this.loading=!0,this.$store.dispatch("CRMSettingConfig").then(function(e){t.loading=!1,t.contractDay=e.data.contractDay,t.contractConfig=parseInt(e.data.contractConfig)}).catch(function(){t.loading=!1})},save:function(){var t=this;this.loading=!0;var e={};1==this.contractConfig?(e.contractDay=this.contractDay,e.status=1):e.status=0,Object(i.j)(e).then(function(e){t.loading=!1,t.$message.success("操作成功")}).catch(function(){t.loading=!1})}}},f=(s("UEDG"),Object(a.a)(b,function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{directives:[{name:"loading",rawName:"v-loading",value:t.loading,expression:"loading"}]},[s("div",{staticClass:"content-title"},[s("span",[t._v("合同到期提醒设置")]),t._v(" "),s("el-button",{staticClass:"rt",attrs:{type:"primary",size:"medium"},on:{click:t.save}},[t._v("保存")])],1),t._v(" "),s("div",{staticClass:"content-body"},[s("div",{staticClass:"tips"},[t._v("设置提前提醒天数之后，根据合同的”合同到期时间”计算提醒时间")]),t._v(" "),s("div",{staticClass:"set-content"},[s("el-radio",{attrs:{label:0},model:{value:t.contractConfig,callback:function(e){t.contractConfig=e},expression:"contractConfig"}},[t._v("不提醒")]),t._v(" "),s("el-radio",{attrs:{label:1},model:{value:t.contractConfig,callback:function(e){t.contractConfig=e},expression:"contractConfig"}},[t._v("提前提醒天数")]),t._v(" "),1==t.contractConfig?s("div",{staticClass:"time-set"},[s("el-input",{model:{value:t.contractDay,callback:function(e){t.contractDay=e},expression:"contractDay"}}),s("span",[t._v("天")])],1):t._e()],1)])])},[],!1,null,"11d75d93",null));f.options.__file="contractExpireSet.vue";var y=f.exports,_={name:"EditCustomerLimit",components:{XhStrucUserCell:s("8GhS").m},props:{types:[String,Number],visible:{type:Boolean,default:!1},action:{type:Object,default:function(){return{type:"save"}}}},data:function(){return{customerDeal:1,customerNum:"",users:[],strucs:[]}},computed:{valueLabel:function(){return{1:"拥有客户数上限（个）",2:"锁定客户数上限（个）"}[this.types]},dealLabel:function(){return{1:"成交客户是否占有拥有客户数：",2:"成交客户是否占有锁定客户数："}[this.types]},title:function(){return"update"==this.action.type?"编辑规则":"添加规则"},showDeal:function(){return 1==this.types}},watch:{visible:function(t){var e=this;if(t)if("save"==this.action.type)this.clearInfo();else if("update"==this.action.type){var s=this.action.data;this.customerDeal=s.customerDeal,this.customerNum=s.customerNum,this.$nextTick(function(){e.users=s.userIds,e.strucs=s.deptIds})}}},mounted:function(){},methods:{close:function(){this.$emit("update:visible",!1)},strcUserChange:function(t){this.users=t.value.users,this.strucs=t.value.strucs},sure:function(){var t=this;if((this.users.length||this.strucs.length)&&this.customerNum){var e={userList:this.users.map(function(t){return t.userId}),deptList:this.strucs.map(function(t){return t.id}),customerNum:this.customerNum,type:this.types};this.showDeal&&(e.customerDeal=this.customerDeal),"update"==this.action.type&&(e.settingId=this.action.data.settingId),Object(i.m)(e).then(function(e){t.$emit("success"),t.close()}).catch(function(){})}else this.$message.error("请完善信息")},clearInfo:function(){this.users=[],this.strucs=[],this.customerDeal=1,this.customerNum=""}}},C=(s("iVDB"),Object(a.a)(_,function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("el-dialog",{attrs:{visible:t.visible,title:t.title,"append-to-body":!0,"before-close":t.close,width:"550px"},on:{"update:visible":function(e){t.visible=e}}},[s("div",{staticClass:"position-relative"},[s("flexbox",{staticClass:"handle-item",attrs:{align:"stretch"}},[s("div",{staticClass:"handle-item-name",staticStyle:{"margin-top":"8px"}},[t._v("适用范围：")]),t._v(" "),s("xh-struc-user-cell",{staticStyle:{width:"100%"},attrs:{users:t.users,strucs:t.strucs},on:{"value-change":t.strcUserChange}})],1),t._v(" "),s("flexbox",{staticClass:"handle-item",attrs:{align:"stretch"}},[s("div",{staticClass:"handle-item-name",staticStyle:{"margin-top":"8px"}},[t._v(t._s(t.valueLabel))]),t._v(" "),s("el-input",{attrs:{placeholder:"请输入内容"},model:{value:t.customerNum,callback:function(e){t.customerNum=e},expression:"customerNum"}})],1),t._v(" "),t.showDeal?s("flexbox",{staticClass:"handle-item"},[s("div",{staticClass:"handle-item-name"},[t._v(t._s(t.dealLabel))]),t._v(" "),s("el-radio-group",{model:{value:t.customerDeal,callback:function(e){t.customerDeal=e},expression:"customerDeal"}},[s("el-radio",{attrs:{label:1}},[t._v("是")]),t._v(" "),s("el-radio",{attrs:{label:0}},[t._v("否")])],1)],1):t._e()],1),t._v(" "),s("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[s("el-button",{nativeOn:{click:function(e){return t.close(e)}}},[t._v("取消")]),t._v(" "),s("el-button",{attrs:{type:"primary"},on:{click:t.sure}},[t._v("确 定")])],1)])},[],!1,null,"897e3df0",null));C.options.__file="editCustomerLimit.vue";var I={name:"CustomerLimitSet",components:{EditCustomerLimit:C.exports},props:{types:[String,Number]},data:function(){return{loading:!1,tableHeight:document.documentElement.clientHeight-320,list:[],showAddEdit:!1,action:{},currentPage:1,pageSize:10,pageSizes:[10,20,30,40],total:0}},computed:{title:function(){return{1:"拥有客户数限制",2:"锁定客户数限制"}[this.types]},fieldList:function(){var t=[{label:"适用范围",field:"range"},{label:{1:"拥有客户数上限",2:"锁定客户数上限"}[this.types],field:"customerNum"}];return 1==this.types&&t.push({label:{1:"成交客户是否占有拥有客户数",2:"成交客户是否占有锁定客户数"}[this.types],field:"customerDeal"}),t}},watch:{types:function(){this.list=[],this.getList()}},created:function(){var t=this;window.onresize=function(){t.tableHeight=document.documentElement.clientHeight-320},this.getList()},methods:{headerCellStyle:function(t,e){return{background:"#F2F2F2"}},handleSizeChange:function(t){this.pageSize=t,this.getList()},handleCurrentChange:function(t){this.currentPage=t,this.getList()},getList:function(){var t=this;this.loading=!0,Object(i.l)({page:this.currentPage,limit:this.pageSize,type:this.types}).then(function(e){t.loading=!1,t.list=e.data.list,t.total=e.data.totalRow}).catch(function(){t.loading=!1})},fieldFormatter:function(t,e){return"customerDeal"==e.property?1==t.customerDeal?"是":"否":t[e.property]},handleEdit:function(t){this.action={type:"update",data:t},this.showAddEdit=!0},addRule:function(){this.action={type:"save"},this.showAddEdit=!0},handleDelete:function(t){var e=this;this.$confirm("确定删除?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){e.loading=!0,Object(i.k)({settingId:t.row.settingId}).then(function(s){e.list.splice(t.$index,1),e.$message.success("删除成功"),e.loading=!1}).catch(function(){e.loading=!1})}).catch(function(){e.$message({type:"info",message:"已取消删除"})})}}},D=(s("9KCS"),Object(a.a)(I,function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",[s("div",{staticClass:"content-title"},[s("span",[t._v(t._s(t.title))]),t._v(" "),s("el-button",{staticClass:"rt",attrs:{type:"primary",size:"medium"},on:{click:t.addRule}},[t._v("添加规则")])],1),t._v(" "),s("div",{staticClass:"customer-table"},[s("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.loading,expression:"loading"}],staticStyle:{width:"100%"},attrs:{data:t.list,height:t.tableHeight,"header-cell-style":t.headerCellStyle,stripe:""}},[t._l(t.fieldList,function(e,i){return s("el-table-column",{key:i,attrs:{prop:e.field,label:e.label,formatter:t.fieldFormatter,"show-overflow-tooltip":""}})}),t._v(" "),s("el-table-column",{attrs:{fixed:"right",label:"操作",width:"120"},scopedSlots:t._u([{key:"default",fn:function(e){return[s("el-button",{attrs:{type:"text",size:"small"},on:{click:function(s){t.handleEdit(e.row)}}},[t._v("编 辑")]),t._v(" "),s("el-button",{attrs:{type:"text",size:"small"},on:{click:function(s){t.handleDelete(e)}}},[t._v("删 除")])]}}])})],2),t._v(" "),s("div",{staticClass:"p-contianer"},[s("el-pagination",{staticClass:"p-bar",attrs:{"current-page":t.currentPage,"page-sizes":t.pageSizes,"page-size":t.pageSize,total:t.total,layout:"total, sizes, prev, pager, next, jumper"},on:{"update:pageSize":function(e){t.pageSize=e},"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}})],1)],1),t._v(" "),s("edit-customer-limit",{attrs:{visible:t.showAddEdit,types:t.types,action:t.action},on:{"update:visible":function(e){t.showAddEdit=e},success:t.getList}})],1)},[],!1,null,"0d056b3d",null));D.options.__file="customerLimitSet.vue";var E={name:"BizParam",components:{BusinessGroupSet:m,ProductCategorySet:v,FollowLogTypeSet:l,ContractExpireSet:y,CustomerLimitSet:D.exports},data:function(){return{menuList:[{label:"跟进记录类型设置",key:"follow-log-type-set"},{label:"商机组设置",key:"business-group-set"},{label:"产品类别设置",key:"product-category-set"},{label:"合同到期提醒设置",key:"contract-expire-set"},{label:"拥有客户数限制",key:"own"},{label:"锁定客户数限制",key:"lock"}],menuIndex:"follow-log-type-set",types:""}},methods:{menuSelect:function(t,e){"own"==t||"lock"==t?(this.types={own:1,lock:2}[t],this.menuIndex="customer-limit-set"):this.menuIndex=t}}},S=(s("B6tz"),Object(a.a)(E,function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"system-customer"},[s("div",{staticClass:"title"},[t._v("业务参数设置")]),t._v(" "),s("div",{staticClass:"customer-content"},[s("div",{staticClass:"system-view-nav"},[s("el-menu",{attrs:{"default-active":"follow-log-type-set"},on:{select:t.menuSelect}},t._l(t.menuList,function(e,i){return s("el-menu-item",{key:i,attrs:{index:e.key}},[s("span",{attrs:{slot:"title"},slot:"title"},[t._v(t._s(e.label))])])}))],1),t._v(" "),s("keep-alive",[s(t.menuIndex,{tag:"component",staticClass:"system-view-content",attrs:{types:t.types}})],1)],1)])},[],!1,null,"12a1b009",null));S.options.__file="index.vue";e.default=S.exports},Joag:function(t,e,s){},KNp9:function(t,e,s){},LUPh:function(t,e,s){"use strict";var i=s("ky0L");s.n(i).a},Ocis:function(t,e,s){},TMTT:function(t,e,s){"use strict";var i=s("Ocis");s.n(i).a},UEDG:function(t,e,s){"use strict";var i=s("KNp9");s.n(i).a},dxXo:function(t,e,s){},iVDB:function(t,e,s){"use strict";var i=s("Joag");s.n(i).a},ky0L:function(t,e,s){},qTcG:function(t,e,s){},zXZL:function(t,e){t.exports="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABEAAAARCAYAAAA7bUf6AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMTQyIDc5LjE2MDkyNCwgMjAxNy8wNy8xMy0wMTowNjozOSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTggKFdpbmRvd3MpIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOjVENzhCOEIzRERCMjExRTg4QjUxREJEMjVGMDBFOUEyIiB4bXBNTTpEb2N1bWVudElEPSJ4bXAuZGlkOjVENzhCOEI0RERCMjExRTg4QjUxREJEMjVGMDBFOUEyIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6NUQ3OEI4QjFEREIyMTFFODhCNTFEQkQyNUYwMEU5QTIiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6NUQ3OEI4QjJEREIyMTFFODhCNTFEQkQyNUYwMEU5QTIiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz5zKmg/AAAAcElEQVR42mI8c+aMLQMDw0IgVmQgHdwH4jgmIDEHiNOBmJEMDNI3lxHokv9QAXLBfyYGKgBiDPlPDUMYho4hLESGAboYIyFDGLEYwDiCApYYQxjp5pJbQOxCpn6QvlugKE4B4kVArECGIQ+AOBYgwAB2pxPdB9NdPQAAAABJRU5ErkJggg=="}}]);