(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-395b"],{"+9th":function(t,e,a){"use strict";a.d(e,"k",function(){return r}),a.d(e,"a",function(){return o}),a.d(e,"d",function(){return n}),a.d(e,"e",function(){return l}),a.d(e,"g",function(){return i}),a.d(e,"h",function(){return c}),a.d(e,"b",function(){return u}),a.d(e,"f",function(){return d}),a.d(e,"c",function(){return f}),a.d(e,"j",function(){return h}),a.d(e,"i",function(){return m});var s=a("t3Un");function r(t){return Object(s.a)({url:"task/queryTaskList",method:"post",data:t})}function o(t){return Object(s.a)({url:"task/setTask",method:"post",data:t})}function n(t){return Object(s.a)({url:"task/deleteTask",method:"post",data:t})}function l(t){return Object(s.a)({url:"task/queryTaskInfo",method:"post",data:t})}function i(t){return Object(s.a)({url:"task/setTask",method:"post",data:t})}function c(t){return Object(s.a)({url:"task/saveTaskRelation",method:"post",data:t})}function u(t){return Object(s.a)({url:"taskLabel/setLabel",method:"post",data:t})}function d(t){return Object(s.a)({url:"taskLabel/setLabel",method:"post",data:t})}function f(t){return Object(s.a)({url:"taskLabel/deleteLabel",method:"post",data:t})}function h(t){return Object(s.a)({url:"taskLabel/getLabelList",method:"post",data:t})}function m(t){return Object(s.a)({url:"task/queryWorkTaskLog",method:"post",data:t})}},"1RnH":function(t,e,a){"use strict";var s=a("QbLZ"),r=a.n(s),o=a("+9th"),n=a("mkuN"),l=a("Er7M"),i=a("7Qib"),c=a("L2JU"),u={components:{CrmRelative:n.a,RelatedBusinessCell:l.a},props:{marginLeft:{type:String,default:"20px"},allData:{type:Object,default:function(){return{contacts:[],customer:[],business:[],contract:[]}}},isTask:{type:Boolean,default:!1},taskID:Number,alterable:{type:Boolean,default:!0},alterableColor:{type:String,default:"#999"}},data:function(){return{showTypes:["customer","contacts","business","contract"],showPopover:!1,relevanceAll:{customerIds:[],contractIds:[],contactsIds:[],businessIds:[]},relatedListData:{},showRelative:!1,showTaskRelative:!1}},computed:r()({},Object(c.b)(["crm"]),{showCRMPermission:function(){return this.crm}}),watch:{allData:function(){this.relatedListData=this.allData}},mounted:function(){this.relatedListData=this.allData},methods:{crmrelativeClose:function(){this.showPopover=!1},checkInfos:function(t){var e=this;this.showPopover=!1,this.relatedListData=t.data;var a=function(a){var s=t.data[a];e.relevanceAll[a+"Ids"]=s.map(function(t,e,s){return t[a+"Id"]})};for(var s in t.data)a(s);this.$emit("checkInfos",this.relevanceAll)},delRelevance:function(t,e,a){var s=this;this.$confirm("确认取消关联?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning",customClass:"is-particulars"}).then(function(){var a={taskId:s.taskID},r=Object(i.k)(s.relatedListData);r[t].splice(e,1);for(var n=function(t){var e=s.showTypes[t],o=r[e]||[];a[e+"Ids"]=o.map(function(t){return t[e+"Id"]}).join(",")},l=0;l<s.showTypes.length;l++)n(l);Object(o.h)(a).then(function(a){s.relatedListData[t].splice(e,1),s.relatedListData=Object(i.k)(s.relatedListData),s.$message.success("关联取消成功")}).catch(function(){})}).catch(function(){s.$message.info("已取消操作")})},checkRelatedDetail:function(t,e){e.key=e[t+"Id"],this.$emit("checkRelatedDetail",t,e)}}},d=(a("VPPU"),a("KHd+")),f=Object(d.a)(u,function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"related-business",style:{"margin-left":t.marginLeft}},[!t.isTask&&t.alterable?s("el-popover",{attrs:{placement:"bottom",width:"800","popper-class":"no-padding-popover",trigger:"click"},model:{value:t.showPopover,callback:function(e){t.showPopover=e},expression:"showPopover"}},[t.showRelative?s("crm-relative",{ref:"crmrelative",attrs:{show:t.showPopover,radio:!1,"selected-data":t.relatedListData,"show-types":t.showTypes},on:{close:t.crmrelativeClose,changeCheckout:t.checkInfos}}):t._e(),t._v(" "),t.showCRMPermission?s("p",{staticClass:"add-file",attrs:{slot:"reference"},on:{click:function(e){t.showRelative=!0}},slot:"reference"},[s("img",{attrs:{src:a("AFlK"),alt:""}}),t._v("\n      关联业务\n    ")]):t._e()],1):t._e(),t._v(" "),t.alterable?t._e():s("p",{staticClass:"alterable-p",style:{color:t.alterableColor}},[t._v("关联业务")]),t._v(" "),t._l(t.relatedListData,function(e,a){return s("div",{key:a},t._l(e,function(e,r){return s("related-business-cell",{key:r,attrs:{data:e,"cell-index":r,type:a,"show-foot":t.isTask},on:{unbind:t.delRelevance,detail:function(s){t.checkRelatedDetail(a,e)}}})}))}),t._v(" "),t.isTask?s("el-popover",{attrs:{placement:"bottom",width:"800","popper-class":"no-padding-popover",trigger:"click"},model:{value:t.showPopover,callback:function(e){t.showPopover=e},expression:"showPopover"}},[t.showTaskRelative?s("crm-relative",{ref:"crmrelative",attrs:{radio:!1,show:t.showPopover,"selected-data":t.relatedListData,"show-types":t.showTypes},on:{close:t.crmrelativeClose,changeCheckout:t.checkInfos}}):t._e(),t._v(" "),t.showCRMPermission?s("p",{staticClass:"add-file",attrs:{slot:"reference"},on:{click:function(e){t.showTaskRelative=!0}},slot:"reference"},[s("img",{attrs:{src:a("AFlK"),alt:""}}),t._v("\n      关联业务\n    ")]):t._e()],1):t._e()],2)},[],!1,null,"e9d30324",null);f.options.__file="relatedBusiness.vue";e.a=f.exports},"4dUy":function(t,e,a){},BuGm:function(t,e,a){"use strict";a.r(e);var s=a("gDS+"),r=a.n(s),o=a("FyfS"),n=a.n(o),l=a("Dh/N"),i=a("1RnH"),c=a("0hym"),u=a("7Qib"),d={components:{relatedBusiness:i.a,XhUser:c.a},props:{formData:{type:Object,default:function(){return{color:""}}},text:{type:String,default:"创建日程"},appendToBody:{type:Boolean,default:!1}},data:function(){var t=this,e=function(e,a,s){t.formData.startTime&&t.formData.endTime&&Object(u.e)(t.formData.startTime)>=Object(u.e)(t.formData.endTime)&&s(new Error("开始时间必须小于结束时间")),s()};return{zIndex:Object(u.h)(),formList:[{label:"主题",field:"title",type:"color"},{label:"开始时间",field:"startTime",type:"time"},{label:"结束时间",field:"endTime",type:"time"},{label:"参与人",field:"ownerUserIds",type:"participant"},{label:"备注",field:"remark",type:"textarea",width:"100%"}],options:[{value:0,label:"无"},{value:1,label:"准时提醒"},{value:2,label:"5分钟前"},{value:3,label:"15分钟前"},{value:4,label:"30分钟前"},{value:5,label:"一个小时前"},{value:6,label:"二个小时前"},{value:7,label:"一天前"},{value:8,label:"二天前"},{value:9,label:"一周前"}],fileList:[],loading:!1,colorList:["#3E8EF7","#11C06C","#0BB2D4","#EB6709","#FF4C52","#526069","#9262F4"],rules:{title:[{required:!0,message:"主题不能为空",trigger:"blur"},{max:50,message:"主题长度最多为50个字符",trigger:"blur"}],startTime:[{required:!0,message:"开始时间不能为空",trigger:"blur"},{validator:e,trigger:"blur"}],endTime:[{required:!0,message:"结束时间不能为空",trigger:"blur"},{validator:e,trigger:"blur"}]},relevanceAll:{},allData:{},colleaguesList:[]}},created:function(){},mounted:function(){this.appendToBody&&document.body.appendChild(this.$el),this.allData.business=this.formData.businessList?this.formData.businessList:[],this.allData.contacts=this.formData.contactsList?this.formData.contactsList:[],this.allData.contract=this.formData.contractList?this.formData.contractList:[],this.allData.customer=this.formData.customerList?this.formData.customerList:[],this.formData.ownerList&&(this.colleaguesList=[].concat(this.formData.ownerList)),"创建日程"==this.text&&this.$set(this.formData,"color","#3E8EF7")},beforeDestroy:function(){this.appendToBody&&this.$el&&this.$el.parentNode&&this.$el.parentNode.removeChild(this.$el)},methods:{close:function(){1==this.$route.query.routerKey?this.$router.go(-1):this.$emit("closeDialog")},checkInfos:function(t){this.relevanceAll=t},onSubmit:function(){var t=this;this.$refs.form.validate(function(e){if(!e)return!1;t.loading=!0;var a=t.formData,s=[],o=!0,i=!1,c=void 0;try{for(var u,d=n()(t.colleaguesList);!(o=(u=d.next()).done);o=!0){var f=u.value;s.push(f.userId)}}catch(t){i=!0,c=t}finally{try{!o&&d.return&&d.return()}finally{if(i)throw c}}if("创建日程"==t.text)Object(l.a)({title:a.title,startTime:a.startTime,endTime:a.endTime,ownerUserIds:s.join(","),remark:a.remark,color:a.color,customerIds:t.relevanceAll.customerIds?t.relevanceAll.customerIds.join(","):[],contactsIds:t.relevanceAll.contactsIds?t.relevanceAll.contactsIds.join(","):[],businessIds:t.relevanceAll.businessIds?t.relevanceAll.businessIds.join(","):[],contractIds:t.relevanceAll.contractIds?t.relevanceAll.contractIds.join(","):[]}).then(function(e){1==t.$route.query.routerKey?t.$router.push("schedule"):t.$emit("onSubmit"),t.loading=!1}).catch(function(){t.loading=!1});else{var h={customerIds:[],contractIds:[],contactsIds:[],businessIds:[]};if(t.allData.customer){var m=!0,p=!1,v=void 0;try{for(var b,y=n()(t.allData.customer);!(m=(b=y.next()).done);m=!0){var k=b.value;h.customerIds.push(k.customerId)}}catch(t){p=!0,v=t}finally{try{!m&&y.return&&y.return()}finally{if(p)throw v}}}if(t.allData.contract){var D=!0,g=!1,I=void 0;try{for(var w,L=n()(t.allData.contract);!(D=(w=L.next()).done);D=!0){var _=w.value;h.contractIds.push(_.contractId)}}catch(t){g=!0,I=t}finally{try{!D&&L.return&&L.return()}finally{if(g)throw I}}}if(t.allData.contacts){var C=!0,j=!1,T=void 0;try{for(var x,O=n()(t.allData.contacts);!(C=(x=O.next()).done);C=!0){var $=x.value;h.contactsIds.push($.contactsId)}}catch(t){j=!0,T=t}finally{try{!C&&O.return&&O.return()}finally{if(j)throw T}}}if(t.allData.business){var R=!0,P=!1,A=void 0;try{for(var B,E=n()(t.allData.business);!(R=(B=E.next()).done);R=!0){var U=B.value;h.businessIds.push(U.businessId)}}catch(t){P=!0,A=t}finally{try{!R&&E.return&&E.return()}finally{if(P)throw A}}}var q="{}"==r()(t.relevanceAll)?h:t.relevanceAll;Object(l.c)({eventId:a.eventId,title:a.title,startTime:a.startTime,endTime:a.endTime,ownerUserIds:s.join(","),remark:a.remark,color:a.color,customerIds:q.customerIds.join(","),contactsIds:q.contactsIds.join(","),businessIds:q.businessIds.join(","),contractIds:q.contractIds.join(",")}).then(function(e){t.$emit("onSubmit"),t.loading=!1}).catch(function(){t.loading=!1})}})},httpRequest:function(t){this.fileList.push(t.file)},onRemove:function(t){for(var e in this.fileList)this.fileList[e].uid==t.uid&&this.fileList.splice(e,1)},changeColor:function(t){this.$set(this.formData,"color",t)},changeCheckout:function(t){this.colleaguesList=t.data},selectDelect:function(t,e){this.$refs.xhuser[0].cancelCheckItem(t),this.colleaguesList.splice(e,1)}}},f=(a("ycXd"),a("KHd+")),h=Object(f.a)(d,function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"create-schedule",style:{"z-index":t.zIndex}},[s("div",{directives:[{name:"loading",rawName:"v-loading",value:t.loading,expression:"loading"}],staticClass:"add-schedule"},[s("div",{staticClass:"header",attrs:{slot:"header"},slot:"header"},[s("span",{staticClass:"text"},[t._v(t._s(t.text))]),t._v(" "),s("img",{staticClass:"el-icon-close rt",attrs:{src:a("cjwK"),alt:""},on:{click:t.close}})]),t._v(" "),s("div",{staticClass:"content"},[s("el-form",{ref:"form",attrs:{model:t.formData,rules:t.rules}},t._l(t.formList,function(e,a){return s("el-form-item",{key:a,class:"el-form-item-"+e.field,style:{width:e.width},attrs:{prop:e.field,label:e.label}},["time"==e.type?[s("el-date-picker",{attrs:{type:"datetime","value-format":"yyyy-MM-dd HH:mm:ss",placeholder:"选择日期时间"},model:{value:t.formData[e.field],callback:function(a){t.$set(t.formData,e.field,a)},expression:"formData[item.field]"}})]:"textarea"==e.type?[s("el-input",{attrs:{autosize:{minRows:6},type:"textarea",placeholder:"请输入内容"},model:{value:t.formData[e.field],callback:function(a){t.$set(t.formData,e.field,a)},expression:"formData[item.field]"}})]:"participant"==e.type?[s("el-popover",{attrs:{placement:"bottom-end",width:"280",trigger:"click"}},[s("xh-user",{ref:"xhuser",refInFor:!0,attrs:{"selected-data":t.colleaguesList},on:{changeCheckout:t.changeCheckout}}),t._v(" "),s("div",{staticClass:"select-box",attrs:{slot:"reference"},slot:"reference"},[t._l(t.colleaguesList,function(e,a){return s("span",{key:a,staticClass:"select-box-span"},[t._v("\n                  "+t._s(e.realname)+"\n                  "),s("span",{staticClass:"el-icon-close",on:{click:function(s){s.stopPropagation(),t.selectDelect(e,a)}}})])}),t._v(" "),s("span",{staticClass:"el-icon-plus"})],2)],1)]:"select"==e.type?[s("el-select",{attrs:{placeholder:"请选择"},model:{value:t.formData[e.field],callback:function(a){t.$set(t.formData,e.field,a)},expression:"formData[item.field]"}},t._l(t.options,function(t){return s("el-option",{key:t.value,attrs:{label:t.label,value:t.value}})}))]:"color"==e.type?[s("el-input",{attrs:{placeholder:"请输入内容"},model:{value:t.formData[e.field],callback:function(a){t.$set(t.formData,e.field,a)},expression:"formData[item.field]"}},[s("i",{staticClass:"el-input__icon",attrs:{slot:"prefix"},slot:"prefix"},[s("span",{staticClass:"bg-color",style:{background:t.formData.color}})])]),t._v(" "),s("div",{staticClass:"color-box"},t._l(t.colorList,function(e,a){return s("span",{key:a,style:{background:e},on:{click:function(a){t.changeColor(e)}}})}))]:s("el-input",{model:{value:t.formData[e.field],callback:function(a){t.$set(t.formData,e.field,a)},expression:"formData[item.field]"}})],2)})),t._v(" "),s("related-business",{attrs:{"all-data":t.allData},on:{checkInfos:t.checkInfos}})],1),t._v(" "),s("div",{staticClass:"footer"},[s("el-button",{attrs:{type:"primary"},on:{click:t.onSubmit}},[t._v("保存")]),t._v(" "),s("el-button",{on:{click:t.close}},[t._v("取消")])],1)])])},[],!1,null,"6f2becb0",null);h.options.__file="createSchedule.vue";e.default=h.exports},"Dh/N":function(t,e,a){"use strict";a.d(e,"d",function(){return r}),a.d(e,"a",function(){return o}),a.d(e,"b",function(){return n}),a.d(e,"c",function(){return l});var s=a("t3Un");function r(t){return Object(s.a)({url:"OaEvent/queryList",method:"post",data:t})}function o(t){return Object(s.a)({url:"OaEvent/add",method:"post",data:t})}function n(t){return Object(s.a)({url:"OaEvent/delete",method:"post",data:t})}function l(t){return Object(s.a)({url:"OaEvent/update",method:"post",data:t})}},JZ4d:function(t,e,a){},VPPU:function(t,e,a){"use strict";var s=a("4dUy");a.n(s).a},ycXd:function(t,e,a){"use strict";var s=a("JZ4d");a.n(s).a}}]);