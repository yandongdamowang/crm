(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-73a1"],{DlH3:function(e,t,a){"use strict";var i=a("Zsns");a.n(i).a},GFJN:function(e,t,a){},IXWR:function(e,t,a){"use strict";var i=a("XZvS");a.n(i).a},K6D0:function(e,t,a){"use strict";var i=a("ooHJ"),n=a("Ew9n"),s=a("F03V"),l=a("U05J"),o=a("8DbX"),c=a("EgJF"),r=a("3biH"),d=a("4+0Q"),u={name:"MoneyDetail",components:{SlideView:n.a,CRMDetailHead:s.a,CRMBaseInfo:l.a,RelativeHandle:o.a,ExamineInfo:r.a,CRMCreateView:c.default},mixins:[d.a],props:{id:[String,Number],listenerIDs:{type:Array,default:function(){return["crm-main-container"]}},noListenerIDs:{type:Array,default:function(){return[]}},noListenerClass:{type:Array,default:function(){return["el-table__body"]}}},data:function(){return{loading:!1,crmType:"receivables",name:"",detailData:{},headDetails:[{title:"客户名称",value:""},{title:"合同金额",value:""},{title:"合同名称",value:""},{title:"回款日期",value:""},{title:"回款金额",value:""},{title:"负责人",value:""}],tabnames:[{label:"基本信息",name:"basicinfo"},{label:"操作记录",name:"operationlog"}],tabCurrentName:"basicinfo",isCreate:!1}},computed:{tabName:function(){return"basicinfo"==this.tabCurrentName?"c-r-m-base-info":"operationlog"==this.tabCurrentName?"relative-handle":""}},mounted:function(){},methods:{getDetial:function(){var e=this;this.loading=!0,Object(i.f)({receivablesId:this.id}).then(function(t){e.loading=!1,e.name=t.data.number,e.detailData=t.data,e.headDetails[0].value=t.data.customerName,e.headDetails[1].value=e.moneyFormat(t.data.contractMoney),e.headDetails[2].value=t.data.contractName,e.headDetails[3].value=t.data.returnTime,e.headDetails[4].value=e.moneyFormat(t.data.money),e.headDetails[5].value=t.data.ownerUserName}).catch(function(){e.loading=!1})},hideView:function(){this.$emit("hide-view")},handleClick:function(e,t){},editSaveSuccess:function(){this.$emit("handle",{type:"save-success"}),this.getDetial()}}},m=(a("XaJ6"),a("ZrdR")),v=Object(m.a)(u,function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("slide-view",{directives:[{name:"empty",rawName:"v-empty",value:!e.canShowDetail,expression:"!canShowDetail"}],attrs:{"listener-ids":e.listenerIDs,"no-listener-ids":e.noListenerIDs,"no-listener-class":e.noListenerClass,"body-style":{padding:0,height:"100%"},"xs-empty-icon":"nopermission","xs-empty-text":"暂无权限"},on:{"side-close":e.hideView}},[e.canShowDetail?a("flexbox",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],staticClass:"d-container",attrs:{direction:"column",align:"stretch"}},[a("c-r-m-detail-head",{attrs:{detail:e.detailData,"head-details":e.headDetails,id:e.id,"crm-type":"receivables"},on:{handle:e.detailHeadHandle,close:e.hideView}}),e._v(" "),a("div",{staticClass:"examine-info"},[a("examine-info",{staticClass:"examine-info-border",attrs:{id:e.id,"record-id":e.detailData.examineRecordId,"owner-user-id":e.detailData.ownerUserId,"examine-type":"crm_receivables"}})],1),e._v(" "),a("div",{staticClass:"tabs"},[a("el-tabs",{on:{"tab-click":e.handleClick},model:{value:e.tabCurrentName,callback:function(t){e.tabCurrentName=t},expression:"tabCurrentName"}},e._l(e.tabnames,function(e,t){return a("el-tab-pane",{key:t,attrs:{label:e.label,name:e.name}})}))],1),e._v(" "),a("div",{staticClass:"t-loading-content"},[a("keep-alive",[a(e.tabName,{tag:"component",attrs:{detail:e.detailData,id:e.id,"crm-type":"receivables"}})],1)],1)],1):e._e(),e._v(" "),e.isCreate?a("c-r-m-create-view",{attrs:{action:{type:"update",id:e.id,batchId:e.detailData.batchId},"crm-type":"receivables"},on:{"save-success":e.editSaveSuccess,"hiden-view":function(t){e.isCreate=!1}}}):e._e()],1)},[],!1,null,"52d0be64",null);v.options.__file="MoneyDetail.vue";t.a=v.exports},OY0y:function(e,t,a){"use strict";var i=a("rJhZ");a.n(i).a},PS1M:function(e,t,a){"use strict";var i=a("qbf4"),n=a("Ew9n"),s=a("F03V"),l=a("UEWd"),o=a("QJC7"),c=a("8KPp"),r=a("gd5k"),d=a("aQg/"),u=a("y9lt"),m=a("i68b"),v={name:"ContactsFollow",components:{MixAdd:l.a,RecordLog:o.a,JournalLog:c.a,ExamineLog:r.a,TaskLog:d.a,ScheduleLog:u.a},mixins:[m.a],props:{id:[String,Number],crmType:{type:String,default:""}},data:function(){return{sendLoading:!1,nextTime:"",isEvent:!1,logType:"record"}},computed:{logTypes:function(){return this.oa?[{type:"record",name:"跟进记录"},{type:"log",name:"日志"},{type:"examine",name:"审批"},{type:"task",name:"任务"},{type:"schedule",name:"日程"}]:[{type:"record",name:"跟进记录"}]},componentsName:function(){return"record"==this.logType?"RecordLog":"log"==this.logType?"JournalLog":"examine"==this.logType?"ExamineLog":"task"==this.logType?"TaskLog":"schedule"==this.logType?"ScheduleLog":""}},watch:{},mounted:function(){},activated:function(){},deactivated:function(){},methods:{handleTypeDrop:function(e){this.followType=e},logTabsClick:function(e,t){this.logType=e.type},sendInfo:function(){this.$refs.mixadd.$emit("submit-info")},submitInfo:function(e){var t=this;if(!this.isEvent||this.nextTime)if(e.content){var a={};a.typesId=this.id,a.content=e.content,a.category=this.followType,a.batchId=e.batchId,a.isEvent=this.isEvent?1:0,a.nextTime=this.nextTime||"",this.sendLoading=!0,Object(i.k)(a).then(function(e){t.sendLoading=!1,t.$message.success("发布成功"),t.$refs.mixadd.resetInfo(),t.isEvent=!1,t.nextTime="",t.$bus.emit("follow-log-refresh",{type:"record-log"})}).catch(function(){t.sendLoading=!1})}else this.$message.error("请输入跟进内容");else this.$message.error("请选择下次联系时间")}}},h=(a("OY0y"),a("ZrdR")),p=Object(h.a)(v,function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"f-container"},[a("div",{directives:[{name:"loading",rawName:"v-loading",value:e.sendLoading,expression:"sendLoading"}]},[a("mix-add",{ref:"mixadd",attrs:{"crm-type":e.crmType,id:e.id},on:{"mixadd-info":e.submitInfo}}),e._v(" "),a("flexbox",{staticClass:"se-section"},[a("div",{staticClass:"se-name"},[e._v("记录类型")]),e._v(" "),a("el-dropdown",{staticStyle:{"margin-right":"20px"},attrs:{trigger:"click"},on:{command:e.handleTypeDrop}},[a("flexbox",{staticClass:"se-select"},[a("div",{staticClass:"se-select-name"},[e._v(e._s(e.followType?e.followType:"请选择"))]),e._v(" "),a("i",{staticClass:"el-icon-arrow-down el-icon--right",staticStyle:{color:"#ccc"}})]),e._v(" "),a("el-dropdown-menu",{attrs:{slot:"dropdown"},slot:"dropdown"},e._l(e.followTypes,function(t,i){return a("el-dropdown-item",{key:i,attrs:{command:t.type}},[e._v(e._s(t.name))])}))],1),e._v(" "),a("div",{staticClass:"se-name"},[e._v("下次联系时间")]),e._v(" "),a("el-date-picker",{staticClass:"se-datepicker",attrs:{"default-value":new Date,editable:!1,type:"datetime",placeholder:"选择日期","value-format":"yyyy-MM-dd HH:mm:ss"},model:{value:e.nextTime,callback:function(t){e.nextTime=t},expression:"nextTime"}}),e._v(" "),e.showOAPermission?a("el-checkbox",{model:{value:e.isEvent,callback:function(t){e.isEvent=t},expression:"isEvent"}},[e._v("添加到日程提醒")]):e._e(),e._v(" "),a("el-button",{staticClass:"se-send",attrs:{type:"primary"},nativeOn:{click:function(t){return e.sendInfo(t)}}},[e._v("发布")])],1)],1),e._v(" "),a("div",{staticClass:"log-cont"},[e.logTypes.length>0?a("flexbox",{staticStyle:{"border-bottom":"1px solid #E6E6E6"}},e._l(e.logTypes,function(t,i){return a("flexbox",{key:i,staticStyle:{width:"auto"},nativeOn:{click:function(a){e.logTabsClick(t,i)}}},[a("div",{staticClass:"log-tabs-item",style:{color:e.logType==t.type?"#F18C70":"#777"}},[e._v(e._s(t.name))]),e._v(" "),e.logTypes.length-1!=i?a("div",{staticClass:"log-tabs-line"}):e._e()])})):e._e(),e._v(" "),a("keep-alive",[a(e.componentsName,{tag:"component",attrs:{id:e.id,"crm-type":e.crmType}})],1)],1)])},[],!1,null,"cce69650",null);p.options.__file="ContactsFollow.vue";var f=p.exports,g=a("U05J"),b=a("5j45"),y=a("8DbX"),C=a("C+sD"),w=a("EgJF"),x=a("4+0Q"),_={name:"ContactsDetail",components:{SlideView:n.a,CRMDetailHead:s.a,ContactsFollow:f,CRMBaseInfo:g.a,RelativeBusiness:b.a,RelativeHandle:y.a,RelativeFiles:C.a,CRMCreateView:w.default},mixins:[x.a],props:{id:[String,Number],listenerIDs:{type:Array,default:function(){return["crm-main-container"]}},noListenerIDs:{type:Array,default:function(){return[]}},noListenerClass:{type:Array,default:function(){return["el-table__body"]}}},data:function(){return{loading:!1,crmType:"contacts",detailData:{},headDetails:[{title:"客户名称",value:""},{title:"职务",value:""},{title:"手机",value:""},{title:"创建时间",value:""}],tabCurrentName:"followlog",isCreate:!1}},computed:{tabName:function(){return"followlog"==this.tabCurrentName?"contacts-follow":"basicinfo"==this.tabCurrentName?"c-r-m-base-info":"business"==this.tabCurrentName?"relative-business":"file"==this.tabCurrentName?"relative-files":"operationlog"==this.tabCurrentName?"relative-handle":""},tabnames:function(){var e=[];return e.push({label:"跟进记录",name:"followlog"}),this.crm.contacts&&this.crm.contacts.read&&e.push({label:"基本信息",name:"basicinfo"}),this.crm.business&&this.crm.business.index&&e.push({label:"商机",name:"business"}),e.push({label:"附件",name:"file"}),e.push({label:"操作记录",name:"operationlog"}),e}},mounted:function(){},methods:{getDetial:function(){var e=this;this.loading=!0,Object(i.i)({contactsId:this.id}).then(function(t){e.loading=!1,e.detailData=t.data,e.headDetails[0].value=t.data.customerName,e.headDetails[1].value=t.data.post,e.headDetails[2].value=t.data.mobile,e.headDetails[3].value=t.data.createTime}).catch(function(){e.loading=!1})},hideView:function(){this.$emit("hide-view")},handleClick:function(e,t){},editSaveSuccess:function(){this.$emit("handle",{type:"save-success"}),this.getDetial()}}},D=(a("IXWR"),Object(h.a)(_,function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("slide-view",{directives:[{name:"empty",rawName:"v-empty",value:!e.canShowDetail,expression:"!canShowDetail"}],attrs:{"listener-ids":e.listenerIDs,"no-listener-ids":e.noListenerIDs,"no-listener-class":e.noListenerClass,"body-style":{padding:0,height:"100%"},"xs-empty-icon":"nopermission","xs-empty-text":"暂无权限"},on:{"side-close":e.hideView}},[e.canShowDetail?a("flexbox",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],staticClass:"d-container",attrs:{direction:"column",align:"stretch"}},[a("c-r-m-detail-head",{attrs:{detail:e.detailData,"head-details":e.headDetails,id:e.id,"crm-type":"contacts"},on:{handle:e.detailHeadHandle,close:e.hideView}}),e._v(" "),a("div",{staticClass:"tabs"},[a("el-tabs",{on:{"tab-click":e.handleClick},model:{value:e.tabCurrentName,callback:function(t){e.tabCurrentName=t},expression:"tabCurrentName"}},e._l(e.tabnames,function(e,t){return a("el-tab-pane",{key:t,attrs:{label:e.label,name:e.name}})}))],1),e._v(" "),a("div",{staticClass:"t-loading-content",attrs:{id:"follow-log-content"}},[a("keep-alive",[a(e.tabName,{tag:"component",attrs:{detail:e.detailData,id:e.id,"crm-type":"contacts"}})],1)],1)],1):e._e(),e._v(" "),e.isCreate?a("c-r-m-create-view",{attrs:{action:{type:"update",id:e.id,batchId:e.detailData.batchId},"crm-type":"contacts"},on:{"save-success":e.editSaveSuccess,"hiden-view":function(t){e.isCreate=!1}}}):e._e()],1)},[],!1,null,"2619461e",null));D.options.__file="ContactsDetail.vue";t.a=D.exports},QhIg:function(e,t,a){},Vcvm:function(e,t,a){"use strict";var i=a("zfMT");a.n(i).a},XZvS:function(e,t,a){},XaJ6:function(e,t,a){"use strict";var i=a("QhIg");a.n(i).a},YrTl:function(e,t,a){"use strict";var i=a("rAnB");a.n(i).a},Zsns:function(e,t,a){},mqlf:function(e,t,a){"use strict";var i=a("5tgW"),n=a("Ew9n"),s=a("F03V"),l=a("UEWd"),o=a("QJC7"),c=a("i68b"),r={name:"ClueFollow",components:{MixAdd:l.a,RecordLog:o.a},mixins:[c.a],props:{id:[String,Number],crmType:{type:String,default:""}},data:function(){return{sendLoading:!1,nextTime:"",isEvent:!1,logType:"record",logTypes:[]}},computed:{componentsName:function(){return"record"==this.logType?"RecordLog":""}},watch:{},mounted:function(){},activated:function(){},deactivated:function(){},methods:{handleTypeDrop:function(e){this.followType=e},logTabsClick:function(e,t){this.logType=e.type},sendInfo:function(){this.$refs.mixadd.$emit("submit-info")},submitInfo:function(e){var t=this;if(!this.isEvent||this.nextTime)if(e.content){var a={};a.typesId=this.id,a.content=e.content,a.category=this.followType,a.batchId=e.batchId,a.isEvent=this.isEvent?1:0,a.nextTime=this.nextTime||"",this.sendLoading=!0,Object(i.j)(a).then(function(e){t.sendLoading=!1,t.$message.success("发布成功"),t.$refs.mixadd.resetInfo(),t.isEvent=!1,t.nextTime="",t.$bus.emit("follow-log-refresh",{type:"record-log"})}).catch(function(){t.sendLoading=!1})}else this.$message.error("请输入跟进内容");else this.$message.error("请选择下次联系时间")}}},d=(a("Vcvm"),a("ZrdR")),u=Object(d.a)(r,function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"f-container"},[a("div",{directives:[{name:"loading",rawName:"v-loading",value:e.sendLoading,expression:"sendLoading"}]},[a("mix-add",{ref:"mixadd",attrs:{"crm-type":e.crmType,id:e.id},on:{"mixadd-info":e.submitInfo}}),e._v(" "),a("flexbox",{staticClass:"se-section"},[a("div",{staticClass:"se-name"},[e._v("记录类型")]),e._v(" "),a("el-dropdown",{staticStyle:{"margin-right":"20px"},attrs:{trigger:"click"},on:{command:e.handleTypeDrop}},[a("flexbox",{staticClass:"se-select"},[a("div",{staticClass:"se-select-name"},[e._v(e._s(e.followType?e.followType:"请选择"))]),e._v(" "),a("i",{staticClass:"el-icon-arrow-down el-icon--right",staticStyle:{color:"#ccc"}})]),e._v(" "),a("el-dropdown-menu",{attrs:{slot:"dropdown"},slot:"dropdown"},e._l(e.followTypes,function(t,i){return a("el-dropdown-item",{key:i,attrs:{command:t.type}},[e._v(e._s(t.name))])}))],1),e._v(" "),a("div",{staticClass:"se-name"},[e._v("下次联系时间")]),e._v(" "),a("el-date-picker",{staticClass:"se-datepicker",attrs:{"default-value":new Date,editable:!1,type:"datetime",placeholder:"选择日期","value-format":"yyyy-MM-dd HH:mm:ss"},model:{value:e.nextTime,callback:function(t){e.nextTime=t},expression:"nextTime"}}),e._v(" "),e.showOAPermission?a("el-checkbox",{model:{value:e.isEvent,callback:function(t){e.isEvent=t},expression:"isEvent"}},[e._v("添加到日程提醒")]):e._e(),e._v(" "),a("el-button",{staticClass:"se-send",attrs:{type:"primary"},nativeOn:{click:function(t){return e.sendInfo(t)}}},[e._v("发布")])],1)],1),e._v(" "),a("div",{staticClass:"log-cont"},[e.logTypes.length>0?a("flexbox",{staticStyle:{"border-bottom":"1px solid #E6E6E6"}},e._l(e.logTypes,function(t,i){return a("flexbox",{key:i,staticStyle:{width:"auto"},nativeOn:{click:function(a){e.logTabsClick(t,i)}}},[a("div",{staticClass:"log-tabs-item",style:{color:e.logType==t.type?"#F18C70":"#777"}},[e._v(e._s(t.name))]),e._v(" "),e.logTypes.length-1!=i?a("div",{staticClass:"log-tabs-line"}):e._e()])})):e._e(),e._v(" "),a(e.componentsName,{tag:"component",attrs:{id:e.id,"crm-type":e.crmType}})],1)])},[],!1,null,"85599a58",null);u.options.__file="ClueFollow.vue";var m=u.exports,v=a("U05J"),h=a("C+sD"),p=a("8DbX"),f=a("EgJF"),g=a("4+0Q"),b={name:"ClueDetail",components:{SlideView:n.a,CRMDetailHead:s.a,ClueFollow:m,CRMBaseInfo:v.a,RelativeFiles:h.a,RelativeHandle:p.a,CRMCreateView:f.default},mixins:[g.a],props:{id:[String,Number],listenerIDs:{type:Array,default:function(){return["crm-main-container"]}},noListenerIDs:{type:Array,default:function(){return[]}},noListenerClass:{type:Array,default:function(){return["el-table__body"]}}},data:function(){return{loading:!1,crmType:"leads",detailData:{},headDetails:[{title:"姓名",value:""},{title:"线索来源",value:""},{title:"手机",value:""},{title:"负责人",value:""},{title:"创建时间",value:""}],tabnames:[{label:"跟进记录",name:"followlog"},{label:"基本信息",name:"basicinfo"},{label:"附件",name:"file"},{label:"操作记录",name:"operationlog"}],tabCurrentName:"followlog",isCreate:!1}},computed:{tabName:function(){return"followlog"==this.tabCurrentName?"clue-follow":"basicinfo"==this.tabCurrentName?"c-r-m-base-info":"file"==this.tabCurrentName?"relative-files":"operationlog"==this.tabCurrentName?"relative-handle":""}},mounted:function(){},methods:{getDetial:function(){var e=this;this.loading=!0,Object(i.h)({leadsId:this.id}).then(function(t){e.detailData=t.data,e.headDetails[0].value=t.data.name,e.headDetails[1].value=t.data.线索来源,e.headDetails[2].value=t.data.mobile,e.headDetails[3].value=t.data.ownerUserName,e.headDetails[4].value=t.data.createTime,e.loading=!1}).catch(function(){e.loading=!1})},hideView:function(){this.$emit("hide-view")},handleClick:function(e,t){},editSaveSuccess:function(){this.$emit("handle",{type:"save-success"}),this.getDetial()}}},y=(a("x1Ir"),Object(d.a)(b,function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("slide-view",{directives:[{name:"empty",rawName:"v-empty",value:!e.canShowDetail,expression:"!canShowDetail"}],attrs:{"listener-ids":e.listenerIDs,"no-listener-ids":e.noListenerIDs,"no-listener-class":e.noListenerClass,"body-style":{padding:0,height:"100%"},"xs-empty-icon":"nopermission","xs-empty-text":"暂无权限"},on:{"side-close":e.hideView}},[e.canShowDetail?a("flexbox",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],staticClass:"d-container",attrs:{direction:"column",align:"stretch"}},[a("c-r-m-detail-head",{attrs:{detail:e.detailData,"head-details":e.headDetails,id:e.id,"crm-type":"leads"},on:{handle:e.detailHeadHandle,close:e.hideView}}),e._v(" "),a("div",{staticClass:"tabs"},[a("el-tabs",{on:{"tab-click":e.handleClick},model:{value:e.tabCurrentName,callback:function(t){e.tabCurrentName=t},expression:"tabCurrentName"}},e._l(e.tabnames,function(e,t){return a("el-tab-pane",{key:t,attrs:{label:e.label,name:e.name}})}))],1),e._v(" "),a("div",{staticClass:"t-loading-content",attrs:{id:"follow-log-content"}},[a("keep-alive",[a(e.tabName,{tag:"component",attrs:{detail:e.detailData,id:e.id,"crm-type":"leads"}})],1)],1)],1):e._e(),e._v(" "),e.isCreate?a("c-r-m-create-view",{attrs:{action:{type:"update",id:e.id,batchId:e.detailData.batchId},"crm-type":"leads"},on:{"save-success":e.editSaveSuccess,"hiden-view":function(t){e.isCreate=!1}}}):e._e()],1)},[],!1,null,"65003652",null));y.options.__file="ClueDetail.vue";t.a=y.exports},n1dw:function(e,t,a){"use strict";var i=a("UcQx"),n=a("Ew9n"),s=a("F03V"),l=a("UEWd"),o=a("QJC7"),c=a("8KPp"),r=a("gd5k"),d=a("aQg/"),u=a("y9lt"),m=a("i68b"),v={name:"BusinessFollow",components:{MixAdd:l.a,RecordLog:o.a,JournalLog:c.a,ExamineLog:r.a,TaskLog:d.a,ScheduleLog:u.a},mixins:[m.a],props:{id:[String,Number],crmType:{type:String,default:""}},data:function(){return{sendLoading:!1,nextTime:"",isEvent:!1,logType:"record"}},computed:{logTypes:function(){return this.oa?[{type:"record",name:"跟进记录"},{type:"log",name:"日志"},{type:"examine",name:"审批"},{type:"task",name:"任务"},{type:"schedule",name:"日程"}]:[{type:"record",name:"跟进记录"}]},componentsName:function(){return"record"==this.logType?"RecordLog":"log"==this.logType?"JournalLog":"examine"==this.logType?"ExamineLog":"task"==this.logType?"TaskLog":"schedule"==this.logType?"ScheduleLog":""}},watch:{},mounted:function(){},activated:function(){},deactivated:function(){},methods:{handleTypeDrop:function(e){this.followType=e},logTabsClick:function(e,t){this.logType=e.type},sendInfo:function(){this.$refs.mixadd.$emit("submit-info")},submitInfo:function(e){var t=this;if(!this.isEvent||this.nextTime)if(e.content){var a={};a.typesId=this.id,a.content=e.content,a.category=this.followType,a.batchId=e.batchId,a.isEvent=this.isEvent?1:0,a.nextTime=this.nextTime||"",this.sendLoading=!0,Object(i.k)(a).then(function(e){t.sendLoading=!1,t.$message.success("发布成功"),t.$refs.mixadd.resetInfo(),t.isEvent=!1,t.nextTime="",t.$bus.emit("follow-log-refresh",{type:"record-log"})}).catch(function(){t.sendLoading=!1})}else this.$message.error("请输入跟进内容");else this.$message.error("请选择下次联系时间")}}},h=(a("YrTl"),a("ZrdR")),p=Object(h.a)(v,function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"f-container"},[a("div",{directives:[{name:"loading",rawName:"v-loading",value:e.sendLoading,expression:"sendLoading"}]},[a("mix-add",{ref:"mixadd",attrs:{"crm-type":e.crmType,id:e.id},on:{"mixadd-info":e.submitInfo}}),e._v(" "),a("flexbox",{staticClass:"se-section"},[a("div",{staticClass:"se-name"},[e._v("记录类型")]),e._v(" "),a("el-dropdown",{staticStyle:{"margin-right":"20px"},attrs:{trigger:"click"},on:{command:e.handleTypeDrop}},[a("flexbox",{staticClass:"se-select"},[a("div",{staticClass:"se-select-name"},[e._v(e._s(e.followType?e.followType:"请选择"))]),e._v(" "),a("i",{staticClass:"el-icon-arrow-down el-icon--right",staticStyle:{color:"#ccc"}})]),e._v(" "),a("el-dropdown-menu",{attrs:{slot:"dropdown"},slot:"dropdown"},e._l(e.followTypes,function(t,i){return a("el-dropdown-item",{key:i,attrs:{command:t.type}},[e._v(e._s(t.name))])}))],1),e._v(" "),a("div",{staticClass:"se-name"},[e._v("下次联系时间")]),e._v(" "),a("el-date-picker",{staticClass:"se-datepicker",attrs:{"default-value":new Date,editable:!1,type:"datetime",placeholder:"选择日期","value-format":"yyyy-MM-dd HH:mm:ss"},model:{value:e.nextTime,callback:function(t){e.nextTime=t},expression:"nextTime"}}),e._v(" "),e.showOAPermission?a("el-checkbox",{model:{value:e.isEvent,callback:function(t){e.isEvent=t},expression:"isEvent"}},[e._v("添加到日程提醒")]):e._e(),e._v(" "),a("el-button",{staticClass:"se-send",attrs:{type:"primary"},nativeOn:{click:function(t){return e.sendInfo(t)}}},[e._v("发布")])],1)],1),e._v(" "),a("div",{staticClass:"log-cont"},[a("flexbox",{staticStyle:{"border-bottom":"1px solid #E6E6E6"}},e._l(e.logTypes,function(t,i){return a("flexbox",{key:i,staticStyle:{width:"auto"},nativeOn:{click:function(a){e.logTabsClick(t,i)}}},[a("div",{staticClass:"log-tabs-item",style:{color:e.logType==t.type?"#F18C70":"#777"}},[e._v(e._s(t.name))]),e._v(" "),e.logTypes.length-1!=i?a("div",{staticClass:"log-tabs-line"}):e._e()])})),e._v(" "),a("keep-alive",[a(e.componentsName,{tag:"component",attrs:{id:e.id,"crm-type":e.crmType}})],1)],1)])},[],!1,null,"88a44dde",null);p.options.__file="BusinessFollow.vue";var f=p.exports,g=a("U05J"),b=a("cYid"),y=a("/U9q"),C=a("8DbX"),w=a("DUlf"),x=a("SE13"),_=a("C+sD"),D=a("EgJF"),T=a("4+0Q"),I={name:"BusinessDetail",components:{SlideView:n.a,CRMDetailHead:s.a,BusinessFollow:f,CRMBaseInfo:g.a,RelativeContract:b.a,RelativeContacts:y.a,RelativeHandle:C.a,RelativeTeam:w.a,RelativeProduct:x.a,RelativeFiles:_.a,CRMCreateView:D.default},mixins:[T.a],props:{id:[String,Number],listenerIDs:{type:Array,default:function(){return["crm-main-container"]}},noListenerIDs:{type:Array,default:function(){return[]}},noListenerClass:{type:Array,default:function(){return["el-table__body"]}}},data:function(){return{loading:!1,crmType:"business",detailData:{},headDetails:[{title:"客户名称",value:""},{title:"商机金额（元）",value:""},{title:"商机状态",value:""},{title:"负责人",value:""},{title:"创建时间",value:""}],tabCurrentName:"followlog",status:[],statuHandleItems:[{name:"赢单",type:1,value:"100%",img:a("y4JV")},{name:"输单",type:2,value:"0%",img:a("migV")},{name:"无效",type:3,value:"0%",img:a("x8Bg")}],isCreate:!1}},computed:{tabName:function(){return"followlog"==this.tabCurrentName?"business-follow":"basicinfo"==this.tabCurrentName?"c-r-m-base-info":"team"==this.tabCurrentName?"relative-team":"contract"==this.tabCurrentName?"relative-contract":"operationlog"==this.tabCurrentName?"relative-handle":"product"==this.tabCurrentName?"relative-product":"file"==this.tabCurrentName?"relative-files":"contacts"==this.tabCurrentName?"relative-contacts":""},tabnames:function(){var e=[];return e.push({label:"跟进记录",name:"followlog"}),this.crm.business&&this.crm.business.read&&e.push({label:"基本信息",name:"basicinfo"}),this.crm.contacts&&this.crm.contacts.index&&e.push({label:"联系人",name:"contacts"}),this.crm.contract&&this.crm.contract.index&&e.push({label:"合同",name:"contract"}),this.crm.product&&this.crm.product.index&&e.push({label:"产品",name:"product"}),e.push({label:"相关团队",name:"team"}),e.push({label:"附件",name:"file"}),e.push({label:"操作记录",name:"operationlog"}),e}},mounted:function(){},methods:{getDetial:function(){var e=this;this.loading=!0,this.getBusinessStatusById(),Object(i.i)({businessId:this.id}).then(function(t){e.loading=!1,e.detailData=t.data,e.headDetails[0].value=t.data.customerName,e.headDetails[1].value=e.moneyFormat(t.data.money),e.headDetails[2].value=t.data.typeName,e.headDetails[3].value=t.data.ownerUserName,e.headDetails[4].value=t.data.createTime}).catch(function(){e.loading=!1})},getBusinessStatusById:function(){var e=this;this.loading=!0,Object(i.p)({businessId:this.id}).then(function(t){e.loading=!1;var a=t.data;e.handleBusinessStatus(a.isEnd,a.statusId,a.statusList,a.statusRemark)}).catch(function(){e.loading=!1})},hideView:function(){this.$emit("hide-view")},handleClick:function(e,t){},handleBusinessStatus:function(e,t,a,i){if(this.status=[],a&&a.length>0){for(var n=0==e,s=-1,l=0;l<a.length;l++){var o=a[l];n?o.statusId==t?(s=l,o.class="state-suc"):o.class=s>=0?"state-undo":"state-suc":o.statusId==t?(s=l,o.class="state-suc"):o.class=s>=0?"state-invalid":"state-suc",this.status.push(o)}var c={type:e};0==e?(c.name="结束",c.overIcon=["el-icon-arrow-down","el-icon--right"],s==a.length-1?c.class="state-doing":(this.status.length>0&&0!=t&&(this.status[s+1].class="state-doing"),c.class="state-undo")):1==e?(c.name="赢单",c.title="赢单",c.detail="赢单率100%",c.overIcon=["el-icon-check","el-icon--right"],c.class="state-suc"):2==e?(c.name="输单",c.title="赢单率0%",c.detail=i,c.overIcon=["el-icon-circle-close","el-icon--right"],c.class="state-fail"):3==e&&(c.name="无效",c.title="赢单率0%",c.detail=i,c.overIcon=["el-icon-remove-outline","el-icon--right"],c.class="state-invalid"),this.status.push(c)}},handleStatuChange:function(e,t){var a=this;if(0==this.detailData.isEnd&&!e.isdoing){var n="确定进入"+e.name+"阶段";this.$confirm(n,"提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){a.loading=!0,Object(i.a)({businessId:a.id,statusId:e.statusId}).then(function(e){a.loading=!1,a.$message.success("操作成功"),a.getDetial()}).catch(function(){a.loading=!1})}).catch(function(){a.$message({type:"info",message:"已取消操作"})})}},handleStatuResult:function(e,t){var a=this;if(0==this.detailData.isEnd)if(2==e.type||3==e.type){var n="请填写"+e.name+"原因：",s=e.name+"原因";this.$prompt(n,s,{confirmButtonText:"确定",cancelButtonText:"取消"}).then(function(t){var n=t.value;a.loading=!0,Object(i.a)({businessId:a.id,statusId:e.statusId,isEnd:e.type,statusRemark:n}).then(function(e){a.loading=!1,a.$message.success("操作成功"),a.getDetial()}).catch(function(){a.loading=!1})}).catch(function(){a.$message({type:"info",message:"取消输入"})})}else{n="确定将当前商机设为"+e.name+"吗?";this.$confirm(n,"提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){a.loading=!0,Object(i.a)({businessId:a.id,statusId:e.statusId,isEnd:e.type}).then(function(e){a.loading=!1,a.$message.success("操作成功"),a.getDetial()}).catch(function(){a.loading=!1})}).catch(function(){a.$message({type:"info",message:"已取消操作"})})}},editSaveSuccess:function(){this.$emit("handle",{type:"save-success"}),this.getDetial()}}},N=(a("DlH3"),Object(h.a)(I,function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("slide-view",{directives:[{name:"empty",rawName:"v-empty",value:!e.canShowDetail,expression:"!canShowDetail"}],attrs:{"listener-ids":e.listenerIDs,"no-listener-ids":e.noListenerIDs,"no-listener-class":e.noListenerClass,"body-style":{padding:0,height:"100%"},"xs-empty-icon":"nopermission","xs-empty-text":"暂无权限"},on:{"side-close":e.hideView}},[e.canShowDetail?a("flexbox",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],staticClass:"d-container",attrs:{direction:"column",align:"stretch"}},[a("c-r-m-detail-head",{attrs:{detail:e.detailData,"head-details":e.headDetails,id:e.id,"crm-type":"business"},on:{handle:e.detailHeadHandle,close:e.hideView}},[a("div",{staticClass:"busi-line"})]),e._v(" "),e.status.length>0?a("div",{staticStyle:{padding:"10px 50px"}},[a("flexbox",{staticClass:"busi-state",style:{opacity:(e.detailData.isEnd,1)}},e._l(e.status,function(t,i){return a("a",{key:i},[a("el-popover",{attrs:{title:t.name,content:"赢单率"+t.rate+"%",placement:"bottom",width:"150",trigger:"hover"}},[e.status.length-1!=i?a("div",{staticClass:"busi-state-item",class:t.class,attrs:{slot:"reference"},on:{click:function(a){e.handleStatuChange(t,i)}},slot:"reference"},[e._v("\n              "+e._s(t.name)+"\n              "),0==i?a("div",{staticClass:"state-circle circle-left"}):e._e(),e._v(" "),0!=i?a("div",{staticClass:"state-arrow arrow-left"}):e._e(),e._v(" "),a("div",{staticClass:"state-arrow arrow-right"})]):e._e()]),e._v(" "),e.status.length-1==i?a("el-popover",{attrs:{title:0==t.type?"":t.title,content:0==t.type?"":t.detail,placement:"bottom",width:"150",trigger:"hover"}},[0==t.type?a("div",{staticClass:"state-handel-cont"},e._l(e.statuHandleItems,function(t,i){return a("flexbox",{key:i,staticClass:"state-handel-item",nativeOn:{click:function(a){e.handleStatuResult(t,i)}}},[a("img",{staticClass:"state-handel-item-img",attrs:{src:t.img}}),e._v(" "),a("div",{staticClass:"state-handel-item-name"},[e._v(e._s(t.name))]),e._v(" "),a("div",{staticClass:"state-handel-item-value"},[e._v(e._s(t.value))])])})):e._e(),e._v(" "),a("div",{staticClass:"busi-state-item",class:t.class,attrs:{slot:"reference"},slot:"reference"},[a("i",{class:t.overIcon,staticStyle:{"margin-right":"8px"}}),e._v("\n              "+e._s(t.name)+"\n              "),a("div",{staticClass:"state-arrow arrow-left"}),e._v(" "),a("div",{staticClass:"state-circle circle-right"})])]):e._e()],1)}))],1):e._e(),e._v(" "),a("div",{staticClass:"tabs"},[a("el-tabs",{on:{"tab-click":e.handleClick},model:{value:e.tabCurrentName,callback:function(t){e.tabCurrentName=t},expression:"tabCurrentName"}},e._l(e.tabnames,function(e,t){return a("el-tab-pane",{key:t,attrs:{label:e.label,name:e.name}})}))],1),e._v(" "),a("div",{staticClass:"t-loading-content",attrs:{id:"follow-log-content"}},[a("keep-alive",[a(e.tabName,{tag:"component",attrs:{detail:e.detailData,id:e.id,"crm-type":"business"}})],1)],1)],1):e._e(),e._v(" "),e.isCreate?a("c-r-m-create-view",{attrs:{action:{type:"update",id:e.id,batchId:e.detailData.batchId},"crm-type":"business"},on:{"save-success":e.editSaveSuccess,"hiden-view":function(t){e.isCreate=!1}}}):e._e()],1)},[],!1,null,"2df9f9fe",null));N.options.__file="BusinessDetail.vue";t.a=N.exports},rAnB:function(e,t,a){},rJhZ:function(e,t,a){},x1Ir:function(e,t,a){"use strict";var i=a("GFJN");a.n(i).a},x8Bg:function(e,t){e.exports="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAUCAYAAACNiR0NAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMTQyIDc5LjE2MDkyNCwgMjAxNy8wNy8xMy0wMTowNjozOSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTggKFdpbmRvd3MpIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOkJGQkE4RTNGNDE2NTExRTlBREMyQzM3QjE2QkFDODFCIiB4bXBNTTpEb2N1bWVudElEPSJ4bXAuZGlkOkJGQkE4RTQwNDE2NTExRTlBREMyQzM3QjE2QkFDODFCIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6QkZCQThFM0Q0MTY1MTFFOUFEQzJDMzdCMTZCQUM4MUIiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6QkZCQThFM0U0MTY1MTFFOUFEQzJDMzdCMTZCQUM4MUIiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz4PPbYiAAABIklEQVR42mI8c+YMAxbABsQBUGwJxFJA/AuInwDxeSBeD8QboWIogAWLYYFA3AXEKlgs0YDiSCC+A8TlQLwOWRETEpsZiDuhClQYCAOQmrVQPczYXNgGxGUMpAOYnnJkF4aQaRiyoSEwA0Fh089AOZgAMgtkYCgQy1DBQGkgDmOCJg1qgQCQgaZUNNAEFMsSyCLq6uoMPDw8ROn+8uULw82bN5GFJJmwpXZKAMiFz4GYFyaAZiOp4DnIhZeo6MAzIAM3UNHADSADV0NLEUrBU5BZsEgpooKBhUD8E5aXQa7spsCwbqgZKMVXJRBPIcOwqVC9GOXhXyDOhZYad4gw6A60HMiB6sVZYoMKzc1QxaDS2xia8WEBfxZaBazGlikAAgwA3vc6LEJJ7+0AAAAASUVORK5CYII="},zfMT:function(e,t,a){}}]);