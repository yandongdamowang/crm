(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-850f"],{"0GEW":function(e,t,a){"use strict";var n=a("wW83");a.n(n).a},AT4C:function(e,t,a){},Ew9n:function(e,t,a){"use strict";var n=a("7Qib"),i={name:"SlideView",components:{},props:{bodyStyle:{type:Object,default:function(){return{padding:0}}},listenerIDs:{type:Array,default:function(){return[]}},noListenerIDs:{type:Array,default:function(){return[]}},noListenerClass:{type:Array,default:function(){return[]}},appendToBody:{type:Boolean,default:!1}},data:function(){return{zIndex:Object(n.h)()}},computed:{},watch:{},mounted:function(){var e=this;this.appendToBody&&document.body.appendChild(this.$el),this.listenerIDs.forEach(function(t){document.getElementById(t)&&document.getElementById(t).addEventListener("click",e.handleDocumentClick,!1)})},beforeDestroy:function(){this.appendToBody&&this.$el&&this.$el.parentNode&&this.$el.parentNode.removeChild(this.$el)},methods:{handleDocumentClick:function(e){var t=!0;this.noListenerIDs.forEach(function(a){document.getElementById(a)&&document.getElementById(a).contains(e.target)&&(t=!1)}),this.noListenerClass.forEach(function(a){var n=document.getElementsByClassName(a);if(n&&t)for(var i=0;i<n.length;i++){if(n[i].contains(e.target)){t=!1;break}}}),document.getElementById("slide")&&document.getElementById("slide").contains(e.target)&&(t=!1),t&&this.$emit("side-close")},afterEnter:function(){this.$emit("afterEnter")}}},s=(a("0GEW"),a("KHd+")),l=Object(s.a)(i,function(){var e=this.$createElement,t=this._self._c||e;return t("transition",{attrs:{name:"slide-fade"},on:{"after-enter":this.afterEnter}},[t("el-card",{ref:"slide",staticClass:"slide-detail-card-container",style:{"z-index":this.zIndex},attrs:{id:"slide","body-style":this.bodyStyle}},[this._t("default")],2)],1)},[],!1,null,"0f708744",null);l.options.__file="SlideView.vue";t.a=l.exports},KHzP:function(e,t,a){"use strict";var n=a("s4a3");a.n(n).a},REbi:function(e,t,a){},Yzx7:function(e,t,a){"use strict";var n=a("REbi");a.n(n).a},qtIl:function(e,t,a){"use strict";a.r(t);var n=a("ViDN"),i=a("t3Un");function s(e){return Object(i.a)({url:"examine/updateStatus",method:"post",data:e})}var l=a("8GhS"),o=a("Ktth"),r=a.n(o),c={name:"CreateSystemExamine",components:{CreateView:n.a,XhInput:l.g,XhTextarea:l.o,XhSelect:l.l,XhUserCell:l.p,XhStrucUserCell:l.m},filters:{typeToComponentName:function(e){return"text"==e?"XhInput":"textarea"==e?"XhTextarea":"select"==e?"XhSelect":"structure"==e?"XhStrucUserCell":void 0},numberToZh:function(e){return r.a.encodeS(e)}},props:{handle:{type:Object,default:function(){return{type:"examineflow",action:"save",id:"",data:null}}}},data:function(){return{loading:!1,crmRules:{},crmForm:{crmFields:[]},currentPage:1,examineType:1,examineList:[{type:1,value:[],show:!1,options:[{name:"负责人主管",value:1},{name:"指定用户（任意一人）",value:2},{name:"指定用户（多人会签）",value:3}]}]}},computed:{title:function(){if("examineflow"===this.handle.type){if("save"===this.handle.action)return"新建审批流程";if("update"===this.handle.action)return"编辑审批流程"}return""}},mounted:function(){if(document.body.appendChild(this.$el),this.getField(),this.handle.data)if(this.handle.data.examineType&&1===this.handle.data.examineType){this.examineType=1,this.examineList=[];for(var e=0;e<this.handle.data.stepList.length;e++){var t=this.handle.data.stepList[e],a={};a.type=t.stepType,2===t.stepType||3===t.stepType?(a.show=!0,a.value=t.userList):(a.show=!1,a.value=[]),a.options=0===e?[{name:"负责人主管",value:1},{name:"指定用户（任意一人）",value:2},{name:"指定用户（多人会签）",value:3}]:[{name:"负责人主管",value:1},{name:"指定用户（任意一人）",value:2},{name:"指定用户（多人会签）",value:3},{name:"上一级审批人主管",value:4}],this.examineList.push(a)}}else this.examineType=2},destroyed:function(){this.$el&&this.$el.parentNode&&this.$el.parentNode.removeChild(this.$el)},methods:{fieldValueChange:function(e){this.crmForm.crmFields[e.index].value=e.value},getField:function(){var e=[];e.push({field:"name",formType:"text",isNull:1,name:"审批流名称",setting:[],inputTips:"",value:this.handle.data?this.handle.data.name:""}),e.push({field:"categoryType",formType:"select",isNull:0,name:"关联对象",setting:[{name:"合同",value:1},{name:"回款",value:2}],value:this.handle.data?this.handle.data.categoryType:1}),e.push({field:"dept",formType:"structure",isNull:0,name:"应用部门",setting:[],inputTips:"默认全公司",value:{users:this.handle.data?this.handle.data.userIds:[],strucs:this.handle.data?this.handle.data.deptIds:[]}}),e.push({field:"remarks",formType:"textarea",isNull:0,name:"流程说明",setting:[],inputTips:"请填写相关注意事项，方便员工在申请时查阅，限制输入2000字",value:this.handle.data?this.handle.data.remarks:""}),this.getcrmRulesAndModel(e)},getcrmRulesAndModel:function(e){for(var t=0;t<e.length;t++){var a=e[t],n=[];1==a.isNull&&n.push({required:!0,message:a.name+"不能为空",trigger:["blur","change"]}),this.crmRules[a.field]=n;var i={};i.value=a.value,i.key=a.field,i.data=a,"textarea"==a.formType&&(i.showblock=!0),this.crmForm.crmFields.push(i)}},saveField:function(){var e=this;this.$refs.crmForm.validate(function(t){if(!t)return e.$message.error("请完善必填信息"),!1;e.validStepsInfo()&&e.submiteParams(e.crmForm.crmFields)})},validStepsInfo:function(){for(var e=0;e<this.examineList.length;e++){var t=this.examineList[e];if((2===t.type||3===t.type)&&0===t.value.length)return this.$message.error("请添加员工"),!1}return!0},submiteParams:function(e){var t=this;this.loading=!0;var a=this.getSubmiteParams(e);"update"==this.handle.action&&(a.examineId=this.handle.data.examineId),function(e){return Object(i.a)({url:"examine/saveExamine",method:"post",headers:{"Content-Type":"application/json;charset=UTF-8"},data:e})}(a).then(function(e){t.loading=!1,t.hidenView(),t.$message.success("操作成功"),t.$emit("save")}).catch(function(){t.loading=!1})},getSubmiteParams:function(e){for(var t={},a=0;a<e.length;a++){var n=e[a];"dept"===n.key?(t.userIds=n.value.users.map(function(e){return e.userId}),t.deptIds=n.value.strucs.map(function(e){return e.id})):t[n.key]=n.value}for(var i=[],s=0;s<this.examineList.length;s++){var l=this.examineList[s];i.push({stepType:l.type,checkUserId:l.value.map(function(e){return e.userId})})}return t.examineType=this.examineType,t.step=i,t},nextPage:function(){var e=this;this.$refs.crmForm.validate(function(t){t&&(e.currentPage=2)})},selectOptionsChange:function(e){2==e.type||3==e.type?e.show=!0:e.show=!1},selectOptionsFocus:function(e,t){if(this.examineList.length>1){var a=t>0?this.examineList[t-1]:null,n=null;if(t<this.examineList.length-1)n=this.examineList[t+1];var i=!1,s=!1,l=!1;!a||2!==a.type&&3!==a.type||(l=!0),n&&4===n.type&&(i=!0,s=!0);var o=[{name:"负责人主管",value:1}];i||o.push({name:"指定用户（任意一人）",value:2}),s||o.push({name:"指定用户（多人会签）",value:3}),l||o.push({name:"上一级审批人主管",value:4}),e.options=o}},examineItemsAdd:function(){this.examineList.push({type:1,value:[],show:!1,options:[{name:"负责人主管",value:1},{name:"指定用户（任意一人）",value:2},{name:"指定用户（多人会签）",value:3},{name:"上一级审批人主管",value:4}]})},flowUserSelect:function(e){this.examineList[e.index].value=e.value},deleteExamineItems:function(e){this.examineList.splice(e,1)},hidenView:function(){this.$emit("hiden-view")},getPaddingLeft:function(e,t){return e.showblock&&1==e.showblock?"0":t%2==0?"0":"25px"},getPaddingRight:function(e,t){return e.showblock&&1==e.showblock?"0":t%2==0?"25px":"0"}}},d=(a("sy8W"),a("KHd+")),u=Object(d.a)(c,function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("create-view",{attrs:{loading:e.loading,"body-style":{height:"100%"}}},[n("flexbox",{staticClass:"crm-create-container",attrs:{direction:"column",align:"stretch"}},[n("flexbox",{staticClass:"crm-create-header"},[n("div",{staticStyle:{flex:"1","font-size":"17px",color:"#333"}},[e._v(e._s(e.title))]),e._v(" "),n("img",{staticClass:"close",attrs:{src:a("cjwK")},on:{click:e.hidenView}})]),e._v(" "),n("flexbox",{staticClass:"crm-create-flex",attrs:{direction:"column",align:"stretch"}},[n("div",{directives:[{name:"show",rawName:"v-show",value:1==e.currentPage,expression:"currentPage == 1"}],staticClass:"crm-create-body"},[n("div",{staticClass:"create-name"},[e._v("基本信息")]),e._v(" "),n("el-form",{ref:"crmForm",staticClass:"crm-create-box",attrs:{model:e.crmForm,"label-position":"top"}},e._l(e.crmForm.crmFields,function(t,a){return n("el-form-item",{key:t.key,class:{"crm-create-block-item":t.showblock,"crm-create-item":!t.showblock},style:{"padding-left":e.getPaddingLeft(t,a),"padding-right":e.getPaddingRight(t,a)},attrs:{prop:"crmFields."+a+".value",rules:e.crmRules[t.key]}},[n("div",{staticStyle:{display:"inline-block"},attrs:{slot:"label"},slot:"label"},[n("div",{staticStyle:{margin:"5px 0","font-size":"12px","word-wrap":"break-word","word-break":"break-all"}},[e._v("\n                "+e._s(t.data.name)+"\n                "),n("span",{staticStyle:{color:"#999"}},[e._v("\n                  "+e._s(t.data.inputTips?"（"+t.data.inputTips+"）":"")+"\n                ")])])]),e._v(" "),n(e._f("typeToComponentName")(t.data.formType),{tag:"component",attrs:{value:t.value,index:a,item:t,radio:!1},on:{"value-change":e.fieldValueChange}})],1)}))],1),e._v(" "),n("div",{directives:[{name:"show",rawName:"v-show",value:2==e.currentPage,expression:"currentPage == 2"}],staticClass:"crm-create-body"},[n("div",{staticStyle:{padding:"0 20px","font-size":"12px"}},[n("el-radio",{attrs:{label:1},model:{value:e.examineType,callback:function(t){e.examineType=t},expression:"examineType"}},[e._v("固定审批流")]),e._v(" "),n("div",{staticClass:"examine-items"},e._l(e.examineList,function(t,a){return n("flexbox",{key:a,staticClass:"examine-item"},[n("div",{staticClass:"examine-item-name"},[e._v("第"+e._s(e._f("numberToZh")(a+1))+"级")]),e._v(" "),n("el-select",{staticClass:"examine-item-select",attrs:{placeholder:"请选择"},on:{focus:function(n){e.selectOptionsFocus(t,a)},change:function(a){e.selectOptionsChange(t)}},model:{value:t.type,callback:function(a){e.$set(t,"type",a)},expression:"item.type"}},e._l(t.options,function(e){return n("el-option",{key:e.value,attrs:{label:e.name,value:e.value}})})),e._v(" "),t.show?n("xh-user-cell",{staticClass:"examine-item-user",attrs:{radio:!1,index:a,value:t.value},on:{"value-change":e.flowUserSelect}}):e._e(),e._v(" "),n("i",{staticClass:"el-icon-remove examine-item-delete",on:{click:function(t){e.deleteExamineItems(a)}}})],1)})),e._v(" "),n("div",{staticClass:"examine-items-add"},[n("span",{on:{click:e.examineItemsAdd}},[e._v("+ 添加审批层级")])]),e._v(" "),n("div",{staticClass:"examine-add-des"},[n("p",[n("span",{staticClass:"examine-add-required"},[e._v("*")]),e._v("当选择“负责人主管”审批时。系统仅会通知负责人主管。")]),e._v(" "),n("p",[n("span",{staticClass:"examine-add-required"},[e._v("*")]),e._v("当选择多个“指定用户”审批时。如果指定用户没有权限查看对应的合同，系统会通知其审批，但是他无法查看此数据信息。 ")]),e._v(" "),n("p",[n("span",{staticClass:"examine-add-required"},[e._v("*")]),e._v("当选择“指定用户（任意一人）”表示指定用户中任意一人审批即可。当选择“指定用户（多人会签）”表示 指定用户中所有人都要审批。")])]),e._v(" "),n("el-radio",{attrs:{label:2},model:{value:e.examineType,callback:function(t){e.examineType=t},expression:"examineType"}},[e._v("授权审批人")])],1)])]),e._v(" "),1==e.currentPage?n("div",{staticClass:"handle-bar"},[n("el-button",{staticClass:"handle-button",nativeOn:{click:function(t){return e.hidenView(t)}}},[e._v("取消")]),e._v(" "),n("el-button",{staticClass:"handle-button",attrs:{type:"primary"},nativeOn:{click:function(t){return e.nextPage(t)}}},[e._v("下一页")])],1):e._e(),e._v(" "),2==e.currentPage?n("div",{staticClass:"handle-bar"},[n("el-button",{staticClass:"handle-button",nativeOn:{click:function(t){return e.hidenView(t)}}},[e._v("取消")]),e._v(" "),n("el-button",{staticClass:"handle-button",attrs:{type:"primary"},nativeOn:{click:function(t){return e.saveField(t)}}},[e._v("保存")]),e._v(" "),n("el-button",{staticClass:"handle-button",attrs:{type:"primary"},nativeOn:{click:function(t){e.currentPage=1}}},[e._v("上一页")])],1):e._e()],1)],1)},[],!1,null,"98e9e9d2",null);u.options.__file="CreateSystemExamine.vue";var m=u.exports,h=a("P2sY"),p=a.n(h),v=a("Ew9n"),f=a("EP+0"),g={name:"SystemExamineDetail",components:{SlideView:v.a,CreateSections:f.a},filters:{formatedScopeInfo:function(e){for(var t="",a=e.deptIds?e.deptIds:[],n=0;n<a.length;n++){t=t+a[n].name+"、"}for(var i=e.userIds?e.userIds:[],s=0;s<i.length;s++){t=t+i[s].realname+(s===i.length-1?"":"、")}return t||"全公司"},toRowName:function(e){return"第"+r.a.encodeS(e)+"级"},toRowNameDes:function(e){return 1==e.stepType?"负责人主管":2==e.stepType?e.userList.length+"人或签":3==e.stepType?e.userList.length+"人会签":4==e.stepType?"上一级审批人主管":""}},props:{data:Object},data:function(){return{showData:{},examineStatus:!1}},computed:{},watch:{data:function(e){this.getShowData()}},mounted:function(){this.getShowData()},methods:{deleteClick:function(){var e=this;this.$confirm("您确定要删除该审批流?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){s({examineId:e.data.examineId,status:2}).then(function(t){e.$emit("refresh"),e.hideView(),e.$message({type:"success",message:"操作成功"})}).catch(function(){})}).catch(function(){e.$message({type:"info",message:"已取消删除"})})},getShowData:function(){this.showData=p()({},this.data),this.examineStatus=0!=this.showData.status},examineStatusChange:function(e){var t=this;this.showData.status=e?1:0,this.$confirm("您确定要"+(e?"启用":"停用")+"该审批流?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){s({examineId:t.data.examineId,status:e?1:0}).then(function(e){t.$emit("refresh"),t.$message({type:"success",message:"操作成功"})}).catch(function(){t.cancelStatusChange()})}).catch(function(){t.cancelStatusChange(),t.$message({type:"info",message:"已取消删除"})})},cancelStatusChange:function(){this.examineStatus=!this.examineStatus,this.showData.status=this.examineStatus?1:0},hideView:function(){this.$emit("hide-view")}}},y=(a("KHzP"),Object(d.a)(g,function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("slide-view",{staticClass:"d-view",attrs:{"listener-ids":["manager-main-container"],"no-listener-ids":["examine-table"],"append-to-body":!0,"body-style":{padding:"10px 30px",height:"100%"}},on:{"side-close":e.hideView}},[n("flexbox",{staticStyle:{height:"100%"},attrs:{orient:"vertical"}},[n("div",{staticClass:"header"},[n("flexbox",{staticClass:"detail-header"},[n("div",{staticClass:"header-name"},[e._v(e._s(e.showData.name)),n("i",{staticClass:"el-icon-delete delete",on:{click:e.deleteClick}})]),e._v(" "),n("img",{staticClass:"header-close",attrs:{src:a("cjwK")},on:{click:e.hideView}})]),e._v(" "),n("div",{staticClass:"detail-header-des"},[n("div",{staticClass:"status"},[e._v("状态："+e._s(0==e.showData.status?"停用":"启用"))]),e._v(" "),n("div",{staticClass:"status-handle"},[e._v("\n          启用\n          "),n("el-switch",{on:{change:e.examineStatusChange},model:{value:e.examineStatus,callback:function(t){e.examineStatus=t},expression:"examineStatus"}})],1)])],1),e._v(" "),n("div",{staticClass:"detail-body"},[n("create-sections",{staticClass:"create-sections",attrs:{title:"基本信息"}},[n("div",{staticClass:"create-sections-content"},[n("div",{staticClass:"dialog-content"},[n("flexbox",{staticClass:"content-items"},[n("div",{staticClass:"content-items-name"},[e._v("审批名称")]),e._v(" "),n("div",{staticClass:"content-items-value"},[e._v(e._s(e.showData.name))])]),e._v(" "),n("flexbox",{staticClass:"content-items"},[n("div",{staticClass:"content-items-name"},[e._v("适用范围")]),e._v(" "),n("div",{staticClass:"content-items-value"},[e._v(e._s(e._f("formatedScopeInfo")(e.showData)))])]),e._v(" "),n("flexbox",{staticClass:"content-items"},[n("div",{staticClass:"content-items-name"},[e._v("审批说明")]),e._v(" "),n("div",{staticClass:"content-items-value"},[e._v(e._s(e.showData.remarks))])])],1)])]),e._v(" "),n("create-sections",{staticClass:"create-sections",attrs:{title:"流程"}},[1==e.showData.examineType?n("div",{staticClass:"create-sections-content"},e._l(e.showData.stepList,function(t,a){return n("flexbox",{key:a,staticClass:"examine-flow",attrs:{align:"stretch"}},[n("div",{staticClass:"examine-flow-header"},[n("div",{staticClass:"mark-circle"}),e._v(" "),0!=a?n("div",{staticClass:"mark-top-line"}):e._e(),e._v(" "),a<e.showData.stepList.length-1?n("div",{staticClass:"mark-bottom-line"}):e._e()]),e._v(" "),n("div",{staticClass:"examine-flow-body"},[n("div",{staticClass:"body-header"},[n("span",{staticClass:"body-header-name"},[e._v(e._s(e._f("toRowName")(a+1)))]),n("span",{staticClass:"body-header-des"},[e._v("（"+e._s(e._f("toRowNameDes")(t))+"）")])]),e._v(" "),t.userList.length>0?n("flexbox",{staticClass:"examine-users"},e._l(t.userList,function(t,a){return n("div",{key:a,staticClass:"examine-users-item"},[n("div",{directives:[{name:"photo",rawName:"v-photo",value:t,expression:"userItem"},{name:"lazy",rawName:"v-lazy:background-image",value:e.$options.filters.filterUserLazyImg(t.img),expression:"$options.filters.filterUserLazyImg(userItem.img)",arg:"background-image"}],staticClass:"div-photo"}),e._v(" "),n("div",{staticClass:"name"},[e._v(e._s(t.realname))])])})):e._e()],1)])})):n("div",{staticClass:"create-sections-content"},[e._v("授权审批人")])])],1)])],1)},[],!1,null,"59a3b0f8",null));y.options.__file="systemExamineDetail.vue";var x={name:"SystemExamine",components:{CreateSystemExamine:m,SystemExamineDetail:y.exports},mixins:[],data:function(){return{loading:!1,tableHeight:document.documentElement.clientHeight-240,list:[],fieldList:[{prop:"name",label:"审批流名称",width:150},{prop:"examineType",label:"流程类型",width:150},{prop:"categoryType",label:"关联对象",width:100},{prop:"userIds",label:"适用范围",width:150},{prop:"updateUserName",label:"最后修改人",width:150},{prop:"updateTime",label:"最后修改时间",width:150},{prop:"status",label:"状态",width:100}],currentPage:1,pageSize:10,pageSizes:[10,20,30,40],total:0,showHandleView:!1,createHandleInfo:{action:"save",type:"examineflow",id:""},showDetail:!1,detailData:{}}},computed:{},mounted:function(){var e=this;window.onresize=function(){e.tableHeight=document.documentElement.clientHeight-240},this.getList()},methods:{getList:function(){var e=this;this.loading=!0,function(e){return Object(i.a)({url:"examine/queryAllExamine",method:"post",data:e})}({page:this.currentPage,limit:this.pageSize}).then(function(t){e.list=t.data.list,e.total=t.data.totalRow,e.loading=!1}).catch(function(){e.loading=!1})},fieldFormatter:function(e,t){if("examineType"===t.property)return 1===e[t.property]?"固定审批流":2===e[t.property]?"授权审批人":"";if("categoryType"===t.property)return{1:"合同",2:"回款"}[e[t.property]];if("userIds"===t.property){var a=(e.deptIds||[]).map(function(e){return e.name}).join("、");return a&&(a+="、"),a+(e.userIds||[]).map(function(e){return e.realname}).join("、")||"全公司"}return"status"===t.property?0===e[t.property]?"停用":"启用":e[t.property]},addExamine:function(){this.createHandleInfo={action:"save",type:"examineflow",id:""},this.showHandleView=!0},handleRowClick:function(e,t,a){t.property&&(this.detailData=e,this.showDetail=!0)},handleSizeChange:function(e){this.pageSize=e,this.getList()},handleCurrentChange:function(e){this.currentPage=e,this.getList()},handleClick:function(e,t){var a=this;"edit"===e?(this.createHandleInfo.action="update",this.createHandleInfo.id=t.row.examineId,this.createHandleInfo.data=t.row,this.showHandleView=!0):"delete"===e?this.$confirm("您确定要删除该审批流?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){s({examineId:t.row.examineId}).then(function(e){a.list.splice(t.$index,1),a.getList(),a.$message({type:"success",message:"操作成功"})}).catch(function(){})}).catch(function(){a.$message({type:"info",message:"已取消删除"})}):"change"===e?this.$confirm("您确定要"+(0===t.row.status?"启用":"停用")+"该审批流?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){s({examineId:t.row.examineId,status:0===t.row.status?1:0}).then(function(e){t.row.status=0===t.row.status?1:0,a.$message({type:"success",message:"操作成功"})}).catch(function(){})}).catch(function(){a.$message({type:"info",message:"已取消删除"})}):"copy"===e&&(this.createHandleInfo.action="save",this.createHandleInfo.id="",this.createHandleInfo.data=t.row,this.showHandleView=!0)}}},w=(a("Yzx7"),Object(d.a)(x,function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"se-container"},[a("div",{staticClass:"se-header"},[e._v("审批流程管理")]),e._v(" "),a("div",{staticClass:"se-body"},[a("div",{staticClass:"se-table-header"},[a("el-button",{staticClass:"se-table-header-button",attrs:{type:"primary"},on:{click:e.addExamine}},[e._v("添加审批流程")])],1),e._v(" "),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],staticClass:"main-table",staticStyle:{width:"100%"},attrs:{id:"examine-table",data:e.list,height:e.tableHeight,"highlight-current-row":""},on:{"row-click":e.handleRowClick}},[e._l(e.fieldList,function(t,n){return a("el-table-column",{key:n,attrs:{formatter:e.fieldFormatter,prop:t.prop,width:t.width,label:t.label,"show-overflow-tooltip":""},scopedSlots:e._u([{key:"header",fn:function(t){return[a("div",{staticClass:"table-head-name"},[e._v(e._s(t.column.label))])]}}])})}),e._v(" "),a("el-table-column"),e._v(" "),a("el-table-column",{attrs:{fixed:"right",label:"操作",width:"250"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{type:"text",size:"small"},on:{click:function(a){e.handleClick("edit",t)}}},[e._v("编辑")]),e._v(" "),a("el-button",{attrs:{type:"text",size:"small"},on:{click:function(a){e.handleClick("delete",t)}}},[e._v("删除")]),e._v(" "),a("el-button",{attrs:{type:"text",size:"small"},on:{click:function(a){e.handleClick("change",t)}}},[e._v(e._s(0===t.row.status?"启用":"停用"))]),e._v(" "),a("el-button",{attrs:{type:"text",size:"small"},on:{click:function(a){e.handleClick("copy",t)}}},[e._v("复制并新建")])]}}])})],2),e._v(" "),a("div",{staticClass:"p-contianer"},[a("el-pagination",{staticClass:"p-bar",attrs:{"current-page":e.currentPage,"page-sizes":e.pageSizes,"page-size":e.pageSize,total:e.total,layout:"total, sizes, prev, pager, next, jumper"},on:{"update:pageSize":function(t){e.pageSize=t},"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}})],1)],1),e._v(" "),e.showHandleView?a("create-system-examine",{attrs:{handle:e.createHandleInfo},on:{save:e.getList,"hiden-view":function(t){e.showHandleView=!1}}}):e._e(),e._v(" "),e.showDetail?a("system-examine-detail",{attrs:{data:e.detailData},on:{refresh:e.getList,"hide-view":function(t){e.showDetail=!1}}}):e._e()],1)},[],!1,null,"3890a1dc",null));w.options.__file="SystemExamine.vue";t.default=w.exports},s4a3:function(e,t,a){},sy8W:function(e,t,a){"use strict";var n=a("AT4C");a.n(n).a},wW83:function(e,t,a){}}]);