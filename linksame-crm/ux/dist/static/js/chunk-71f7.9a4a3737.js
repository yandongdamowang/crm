(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-71f7","chunk-83a1"],{"04o0":function(t,e,a){"use strict";var i=a("Mi++");a.n(i).a},"7HRL":function(t,e,a){"use strict";var i=a("hjaF");a.n(i).a},"8ZnH":function(t,e,a){"use strict";var i=a("bmjN");a.n(i).a},"G1+B":function(t,e,a){},Ma0E:function(t,e,a){"use strict";var i=a("G1+B");a.n(i).a},"Mi++":function(t,e,a){},NoVw:function(t,e,a){"use strict";a.r(e);var i=a("ViDN"),n=a("utuE"),o=a("d8QR"),s=a("7Qib"),l={components:{CreateView:i.a,membersDep:n.a},data:function(){var t=this,e=function(e,a,i){"startTime"==e.field&&!t.formData.startTime&&t.formData.endTime||"endTime"==e.field&&!t.formData.endTime&&t.formData.startTime?i(new Error("请完善时间")):t.formData.startTime&&t.formData.endTime&&Object(s.e)(t.formData.startTime)>=Object(s.e)(t.formData.endTime)&&i(new Error("开始时间必须小于结束时间")),i()};return{formList:[{label:"公告标题",field:"title"},{label:"通知部门",field:"dep",type:"plus"},{label:"开始时间",field:"startTime",type:"date"},{label:"结束时间",field:"endTime",type:"date"},{label:"公告正文",field:"content",type:"textarea"}],formData:{dep:{staff:[],dep:[]}},rules:{title:[{required:!0,message:"公告标题不能为空",trigger:"blur"},{max:50,message:"公告标题长度最多为50个字符",trigger:"blur"}],content:[{required:!0,message:"公告正文不能为空",trigger:"blur"}],startTime:[{required:!0,message:"不能为空",trigger:"blur"},{validator:e,trigger:"blur"}],endTime:[{required:!0,message:"不能为空",trigger:"blur"},{validator:e,trigger:"blur"}]},loading:!1}},methods:{onSubmit:function(){var t=this;this.$refs.form.validate(function(e){if(!e)return!1;t.loading=!0,Object(o.a)({title:t.formData.title,content:t.formData.content,startTime:t.formData.startTime,endTime:t.formData.endTime,deptIds:t.formData.dep.dep.map(function(t){return t.id}).join(","),ownerUserIds:t.formData.dep.staff.map(function(t){return t.userId}).join(",")}).then(function(e){t.$message.success("新建公告成功"),1==t.$route.query.routerKey?t.$router.push("notice"):t.$emit("onSubmit"),t.loading=!1}).catch(function(){t.$message.error("新建公告失败"),t.loading=!1})})},close:function(){1==this.$route.query.routerKey?this.$router.go(-1):this.$emit("close")},popoverSubmit:function(t,e){this.$set(this.formData,"dep",{staff:t,dep:e})},deleteuser:function(t){this.formData.dep.staff.splice(t,1)},deleteDepuser:function(t){this.formData.dep.dep.splice(t,1)}}},r=(a("7HRL"),a("ZrdR")),c=Object(r.a)(l,function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("create-view",{attrs:{"body-style":{height:"100%"}}},[i("div",{directives:[{name:"loading",rawName:"v-loading",value:t.loading,expression:"loading"}],staticClass:"details-box"},[i("div",{staticClass:"header",attrs:{slot:"header"},slot:"header"},[i("span",{staticClass:"text"},[t._v("新建公告")]),t._v(" "),i("img",{staticClass:"el-icon-close rt",attrs:{src:a("cjwK"),alt:""},on:{click:t.close}})]),t._v(" "),i("div",{staticClass:"content"},[i("el-form",{ref:"form",attrs:{model:t.formData,rules:t.rules}},t._l(t.formList,function(e,a){return i("el-form-item",{key:a,class:"el-form-item"+e.field,attrs:{label:e.label,prop:e.field}},["date"==e.type?[i("el-date-picker",{attrs:{type:"date","value-format":"yyyy-MM-dd",placeholder:"选择日期"},model:{value:t.formData[e.field],callback:function(a){t.$set(t.formData,e.field,a)},expression:"formData[item.field]"}})]:"textarea"==e.type?[i("el-input",{attrs:{autosize:{minRows:6},type:"textarea",placeholder:"请输入内容"},model:{value:t.formData[e.field],callback:function(a){t.$set(t.formData,e.field,a)},expression:"formData[item.field]"}})]:"plus"==e.type?[i("members-dep",{attrs:{"popover-display":"block",title:"通知部门","user-checked-data":t.formData[e.field].staff,"dep-checked-data":t.formData[e.field].dep},on:{popoverSubmit:t.popoverSubmit}},[i("flexbox",{staticClass:"user-container",attrs:{slot:"membersDep",wrap:"wrap"},slot:"membersDep"},[t._l(t.formData[e.field].staff,function(e,a){return i("div",{key:"user"+a,staticClass:"user-item",on:{click:function(e){e.stopPropagation(),t.deleteuser(a)}}},[t._v(t._s(e.realname)+"\n                  "),i("i",{staticClass:"delete-icon el-icon-close"})])}),t._v(" "),t._l(t.formData[e.field].dep,function(e,a){return i("div",{key:"dep"+a,staticClass:"user-item",on:{click:function(e){e.stopPropagation(),t.deleteDepuser(a)}}},[t._v(t._s(e.name)+"\n                  "),i("i",{staticClass:"delete-icon el-icon-close"})])}),t._v(" "),i("div",{staticClass:"add-item"},[t._v("+添加")])],2)],1)]:i("el-input",{model:{value:t.formData[e.field],callback:function(a){t.$set(t.formData,e.field,a)},expression:"formData[item.field]"}})],2)}))],1),t._v(" "),i("div",{staticClass:"btn-box"},[i("el-button",{attrs:{type:"primary"},on:{click:t.onSubmit}},[t._v("提交")]),t._v(" "),i("el-button",{on:{click:t.close}},[t._v("取消")])],1)])])},[],!1,null,"5711e75e",null);c.options.__file="newDialog.vue";e.default=c.exports},PXfi:function(t,e,a){},Tipw:function(t,e,a){"use strict";var i=a("PXfi");a.n(i).a},bW7A:function(t,e,a){"use strict";a.r(e);var i=a("rerW"),n=a.n(i),o=a("bS4n"),s=a.n(o),l=a("iz3W"),r=a("NoVw"),c=a("d8QR"),d={components:{},props:{data:Object,cellIndex:Number},data:function(){return{parentTarget:null,awaitMoment:!1}},mounted:function(){var t=this;0==this.data.isRead&&(this.$bus.on("notice-list-box-scroll",function(e){t.observePreview(e)}),this.observePreview(document.getElementById("notice-cell"+this.cellIndex).parentNode))},beforeDestroy:function(){this.$bus.off("notice-list-box-scroll")},methods:{observePreview:function(t){var e=this;if(0==this.data.isRead){t&&(this.parentTarget=t);var a=this.whetherPreview();!this.awaitMoment&&a&&(this.awaitMoment=!0,setTimeout(function(){e.awaitMoment=!1,e.whetherPreview()&&e.submiteIsRead()},3e3))}},whetherPreview:function(){var t=this.parentTarget.children[this.cellIndex];if(this.parentTarget.getBoundingClientRect()){var e=this.parentTarget.getBoundingClientRect().top-t.getBoundingClientRect().top,a=!1;return e<=0&&Math.abs(e)<this.parentTarget.clientHeight?a=!0:e>0&&e<t.clientHeight&&(a=!0),a}return!1},submiteIsRead:function(){var t=this;Object(c.c)({announcementId:this.data.announcementId}).then(function(e){t.$store.dispatch("GetOAMessageNum","announcement"),t.data.isRead=1}).catch(function(){})},rowFun:function(t){this.$emit("handle",{type:"detail",value:t})},loadMoreBtn:function(t){this.$set(t,"preShow",!0),this.$set(t,"loadMore",!0)}}},u=(a("Tipw"),a("ZrdR")),m=Object(u.a)(d,function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"list",attrs:{id:"notice-cell"+t.cellIndex}},[a("div",{staticClass:"header"},[a("div",{directives:[{name:"photo",rawName:"v-photo",value:t.data,expression:"data"},{name:"lazy",rawName:"v-lazy:background-image",value:t.$options.filters.filterUserLazyImg(t.data.img),expression:"$options.filters.filterUserLazyImg(data.img)",arg:"background-image"}],staticClass:"div-photo"}),t._v(" "),a("div",{staticClass:"name-time"},[a("p",{staticClass:"name"},[t._v(t._s(t.data.realname))]),t._v(" "),a("p",{staticClass:"time"},[t._v(t._s(t._f("moment")(t.data.createTime,"YYYY-MM-DD HH:mm")))])])]),t._v(" "),a("div",{staticClass:"title",on:{click:function(e){t.rowFun(t.data)}}},[t._v(t._s(t.data.title))]),t._v(" "),t.data.preShow?a("div",{staticClass:"data-content"},[t._v(t._s(t.data.content))]):a("div",{staticClass:"data-content"},[t._v(t._s(t.data.contentSub))]),t._v(" "),t.data.contentSub.length<t.data.content.length?a("div",{staticClass:"load-more"},[t.data.loadMore?a("span",{on:{click:function(e){t.data.loadMore=!1,t.data.preShow=!1}}},[t._v("收起全文")]):a("span",{on:{click:function(e){t.loadMoreBtn(t.data)}}},[t._v("展开全文")])]):t._e()])},[],!1,null,"2870c091",null);m.options.__file="noticeCell.vue";var f=m.exports,p=a("8t5x"),v={components:{VDetails:l.a,newDialog:r.default,NoticeCell:f},data:function(){return{activeName:"first",options:[{value:"1",label:"公示中"},{value:"2",label:"已结束"}],optionsValue:"1",listData:[],dialog:!1,titleList:{},showNewDialog:!1,loading:!0,pageNum:1,loadText:"加载更多",loadMoreLoading:!0,isPost:!0}},computed:s()({},Object(p.b)(["oa"]),{permissionSave:function(){return this.oa&&this.oa.announcement&&this.oa.announcement.save}}),watch:{$route:function(t,e){this.$router.go(0)}},mounted:function(){var t=this;this.noticeDataFun(1,this.pageNum),document.getElementsByClassName("content")[0].onscroll=function(){var e=document.getElementsByClassName("content")[0],a=e.scrollTop+e.clientHeight-e.scrollHeight;Math.abs(a)<10&&1==t.loadMoreLoading&&(t.isPost?t.loadMoreLoading=!1:(t.isPost=!0,t.pageNum++,t.noticeDataFun(t.optionsValue,t.pageNum)))}},methods:{noticeDataFun:function(t,e){var a=this;Object(c.d)({type:t,page:e,limit:15}).then(function(t){var e=!0,i=!1,o=void 0;try{for(var s,l=n()(t.data.list);!(e=(s=l.next()).done);e=!0){var r=s.value;r.contentSub=r.content.substring(0,150)}}catch(t){i=!0,o=t}finally{try{!e&&l.return&&l.return()}finally{if(i)throw o}}a.listData=a.listData.concat(t.data.list),0==t.data.list.length||15!=t.data.list.length?(a.loadText="没有更多了",a.loadMoreLoading=!1):(a.loadText="加载更多",a.loadMoreLoading=!0),a.loading=!1,a.isPost=!1}).catch(function(){a.loadText="",a.loading=!1,a.isPost=!1})},noticeHandle:function(t){"detail"==t.type&&(this.titleList=t.value,this.dialog=!0)},close:function(){this.dialog=!1},deleteFun:function(){for(var t in this.listData)this.listData[t].announcementId==this.titleList.announcementId&&this.listData.splice(t,1);this.close()},newBtn:function(){this.showNewDialog=!0},newClose:function(){this.showNewDialog=!1},onSubmit:function(t){this.selectChange(this.optionsValue),this.newClose()},editSubmit:function(t){this.selectChange(this.optionsValue),this.close()},selectChange:function(t){this.loading=!0,this.listData=[],this.pageNum=1,this.noticeDataFun(t,this.pageNum)}}},h=(a("04o0"),Object(u.a)(v,function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{directives:[{name:"loading",rawName:"v-loading",value:t.loading,expression:"loading"}],staticClass:"notice oa-bgcolor"},[t.permissionSave?a("el-button",{staticClass:"new-btn",attrs:{type:"primary"},on:{click:t.newBtn}},[t._v("新建公告")]):t._e(),t._v(" "),a("el-tabs",{model:{value:t.activeName,callback:function(e){t.activeName=e},expression:"activeName"}},[a("el-tab-pane",{attrs:{label:"公告",name:"first"}},[a("div",{staticClass:"text-top"},[a("label",{staticClass:"text"},[t._v("公告状态")]),t._v(" "),a("el-select",{attrs:{placeholder:"请选择",size:"small"},on:{change:t.selectChange},model:{value:t.optionsValue,callback:function(e){t.optionsValue=e},expression:"optionsValue"}},t._l(t.options,function(t){return a("el-option",{key:t.value,attrs:{label:t.label,value:t.value}})}))],1),t._v(" "),a("div",{staticClass:"content",attrs:{id:"notice-list-box"}},[a("div",{staticClass:"list-box"},[t._l(t.listData,function(e,i){return a("notice-cell",{key:i,attrs:{data:e,"cell-index":i},on:{handle:t.noticeHandle}})}),t._v(" "),a("p",{staticClass:"load"},[a("el-button",{attrs:{loading:t.loadMoreLoading,type:"text"}},[t._v(t._s(t.loadText))])],1)],2)])])],1),t._v(" "),t.dialog?a("v-details",{attrs:{"title-list":t.titleList},on:{editSubmit:t.editSubmit,deleteFun:t.deleteFun,close:t.close}}):t._e(),t._v(" "),t.showNewDialog?a("new-dialog",{on:{onSubmit:t.onSubmit,close:t.newClose}}):t._e()],1)},[],!1,null,"41dd5013",null));h.options.__file="index.vue";e.default=h.exports},bmjN:function(t,e,a){},d8QR:function(t,e,a){"use strict";a.d(e,"d",function(){return n}),a.d(e,"a",function(){return o}),a.d(e,"b",function(){return s}),a.d(e,"c",function(){return l});var i=a("t3Un");function n(t){return Object(i.a)({url:"OaAnnouncement/queryList",method:"post",data:t})}function o(t){return Object(i.a)({url:"OaAnnouncement/saveAndUpdate",method:"post",data:t})}function s(t){return Object(i.a)({url:"OaAnnouncement/delete",method:"post",data:t})}function l(t){return Object(i.a)({url:"OaAnnouncement/readAnnouncement",method:"post",data:t})}},hjaF:function(t,e,a){},iz3W:function(t,e,a){"use strict";var i=a("6ZY3"),n=a.n(i),o=a("bS4n"),s=a.n(o),l=a("ViDN"),r=a("7Qib"),c={components:{CreateView:l.a},props:{formData:Object,loading:Boolean},data:function(){var t=this,e=function(e,a,i){"startTime"==e.field&&!t.formData.startTime&&t.formData.endTime||"endTime"==e.field&&!t.formData.endTime&&t.formData.startTime?i(new Error("请完善时间")):t.formData.startTime&&t.formData.endTime&&Object(r.e)(t.formData.startTime)>=Object(r.e)(t.formData.endTime)&&i(new Error("开始时间必须小于结束时间")),i()};return{formList:[{label:"公告标题",field:"title"},{label:"开始时间",field:"startTime",type:"date"},{label:"结束时间",field:"endTime",type:"date"},{label:"公告正文",field:"content",type:"textarea"}],rules:{title:[{required:!0,message:"公告标题不能为空",trigger:"blur"},{max:50,message:"公告标题长度最多为50个字符",trigger:"blur"}],content:[{required:!0,message:"公告正文不能为空",trigger:"blur"}],startTime:[{required:!0,message:"不能为空",trigger:"blur"},{validator:e,trigger:"blur"}],endTime:[{required:!0,message:"不能为空",trigger:"blur"},{validator:e,trigger:"blur"}]}}},methods:{onSubmit:function(){var t=this;this.$refs.form.validate(function(e){if(!e)return!1;t.$emit("editSubmit")})},close:function(){this.$emit("editClose")},inputChange:function(){this.popoverVisible=!0}}},d=(a("8ZnH"),a("ZrdR")),u=Object(d.a)(c,function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("create-view",{attrs:{"body-style":{height:"100%"}}},[i("div",{directives:[{name:"loading",rawName:"v-loading",value:t.loading,expression:"loading"}],staticClass:"details-box"},[i("div",{staticClass:"header",attrs:{slot:"header"},slot:"header"},[i("span",{staticClass:"text"},[t._v("编辑公告")]),t._v(" "),i("img",{staticClass:"el-icon-close rt",attrs:{src:a("cjwK"),alt:""},on:{click:t.close}})]),t._v(" "),i("div",{staticClass:"content"},[i("el-form",{ref:"form",attrs:{model:t.formData,rules:t.rules}},t._l(t.formList,function(e,a){return i("el-form-item",{key:a,class:"el-form-item"+e.field,attrs:{label:e.label,prop:e.field}},["date"==e.type?[i("el-date-picker",{attrs:{type:"date","value-format":"yyyy-MM-dd",placeholder:"选择日期"},model:{value:t.formData[e.field],callback:function(a){t.$set(t.formData,e.field,a)},expression:"formData[item.field]"}})]:"textarea"==e.type?[i("el-input",{attrs:{type:"textarea",autosize:"",placeholder:"请输入内容"},model:{value:t.formData[e.field],callback:function(a){t.$set(t.formData,e.field,a)},expression:"formData[item.field]"}})]:i("el-input",{model:{value:t.formData[e.field],callback:function(a){t.$set(t.formData,e.field,a)},expression:"formData[item.field]"}})],2)}))],1),t._v(" "),i("div",{staticClass:"btn-box"},[i("el-button",{attrs:{type:"primary"},on:{click:t.onSubmit}},[t._v("提交")]),t._v(" "),i("el-button",{on:{click:t.close}},[t._v("取消")])],1)])])},[],!1,null,"9c60c070",null);u.options.__file="edit.vue";var m=u.exports,f=a("d8QR"),p=a("8t5x"),v={components:{CreateView:l.a,VEdit:m},props:{titleList:Object,btnShow:{type:Boolean,default:!0}},data:function(){return{showEdit:!1,formData:{},loading:!1}},computed:s()({},Object(p.b)(["oa"]),{permissionUpdate:function(){return this.oa&&this.oa.announcement&&this.oa.announcement.update},permissionDelete:function(){return this.oa&&this.oa.announcement&&this.oa.announcement.delete}}),methods:{onEdit:function(){this.formData=n()({},this.titleList),this.showEdit=!0},close:function(){this.$emit("close")},deleteFun:function(){var t=this;this.$confirm("确定删除?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){Object(f.b)({id:t.titleList.announcementId}).then(function(e){t.$emit("deleteFun"),t.$message({type:"success",message:"删除成功!"})})}).catch(function(){t.$message({type:"info",message:"已取消删除"})})},editClose:function(){this.showEdit=!1},editSubmit:function(){var t=this;this.loading=!0,Object(f.a)({announcementId:this.formData.announcementId,title:this.formData.title,content:this.formData.content,startTime:this.formData.startTime,endTime:this.formData.endTime}).then(function(e){t.$emit("editSubmit",t.formData),t.editClose(),t.$message.success("公告编辑成功"),t.loading=!1}).catch(function(){t.loading=!1,t.$message.error("公告编辑失败")})}}},h=(a("Ma0E"),Object(d.a)(v,function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("create-view",{attrs:{"body-style":{height:"100%"}}},[a("div",{staticClass:"details-box"},[a("div",{staticClass:"header",attrs:{slot:"header"},slot:"header"},[a("span",{staticClass:"text"},[t._v("公告详情")]),t._v(" "),a("span",{staticClass:"el-icon-close rt",on:{click:t.close}})]),t._v(" "),a("div",{staticClass:"content"},[a("div",{staticClass:"title"},[t._v(t._s(t.titleList.title))]),t._v(" "),a("div",{staticClass:"time"},[t._v(t._s(t.titleList.createTime))]),t._v(" "),a("div",{staticClass:"text"},[t._v(t._s(t.titleList.content))])]),t._v(" "),t.btnShow?a("div",{staticClass:"btn-box"},[t.permissionUpdate?a("el-button",{attrs:{type:"primary"},on:{click:t.onEdit}},[t._v("编辑")]):t._e(),t._v(" "),t.permissionDelete?a("el-button",{attrs:{type:"danger"},on:{click:t.deleteFun}},[t._v("删除")]):t._e()],1):t._e()]),t._v(" "),t.showEdit?a("v-edit",{attrs:{"form-data":t.formData,loading:t.loading},on:{editSubmit:t.editSubmit,editClose:t.editClose}}):t._e()],1)},[],!1,null,"f2a6e704",null));h.options.__file="details.vue";e.a=h.exports}}]);