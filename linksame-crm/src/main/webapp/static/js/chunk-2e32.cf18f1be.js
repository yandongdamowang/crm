(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-2e32"],{"+JiW":function(e,t,i){"use strict";var a=i("P/2M");i.n(a).a},"1pAj":function(e,t,i){},"3yi6":function(e,t,i){"use strict";var a=i("7Qib"),s=i("KzkL"),n={name:"FollowRecordCell",components:{CRMFullScreenDetail:function(){return Promise.all([i.e("chunk-66f9"),i.e("chunk-1e2f"),i.e("chunk-38ea"),i.e("chunk-3f3ab3")]).then(i.bind(null,"uMjE"))}},props:{item:{type:Object,default:function(){return{}}},crmType:{type:String,default:""},index:[String,Number]},data:function(){return{showFullDetail:!1,relationID:"",relationCrmType:""}},computed:{},mounted:function(){},methods:{previewImg:function(e,t){this.$bus.emit("preview-image-bus",{index:t,data:e.map(function(e,t,i){return e.url=e.filePath,e})})},downloadFile:function(e){Object(a.b)({path:e.filePath,name:e.name})},handleCommand:function(e){var t=this;this.$confirm("确定删除?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){Object(s.p)({recordId:t.item.recordId}).then(function(i){t.$emit("on-handle",{type:e,data:{item:t.item,index:t.index}}),t.$message.success("操作成功")}).catch(function(){})}).catch(function(){t.$message({type:"info",message:"已取消操作"})})},checkRelationDetail:function(e,t){this.relationID=t,this.relationCrmType=e,this.showFullDetail=!0}}},l=(i("MEpo"),i("KHd+")),o=Object(l.a)(n,function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"fl-c"},[a("flexbox",{staticClass:"fl-h"},[a("div",{directives:[{name:"photo",rawName:"v-photo",value:{img:e.item.userImg,realname:e.item.realname},expression:"{img: item.userImg, realname: item.realname}"},{name:"lazy",rawName:"v-lazy:background-image",value:e.$options.filters.filterUserLazyImg(e.item.userImg),expression:"$options.filters.filterUserLazyImg(item.userImg)",arg:"background-image"}],staticClass:"div-photo fl-h-img"}),e._v(" "),a("div",{staticClass:"fl-h-b"},[a("div",{staticClass:"fl-h-name"},[e._v(e._s(e.item.realname))]),e._v(" "),a("div",{staticClass:"fl-h-time"},[e._v(e._s(e.item.createTime))])]),e._v(" "),a("flexbox",{staticClass:"fl-h-mark"},[a("img",{staticClass:"fl-h-mark-img",attrs:{src:i("N0PQ")}}),e._v(" "),a("div",{staticClass:"fl-h-mark-name"},[e._v("跟进记录")])]),e._v(" "),a("el-dropdown",{attrs:{trigger:"click"},on:{command:e.handleCommand}},[a("i",{staticClass:"el-icon-arrow-down el-icon-more",staticStyle:{color:"#CDCDCD","margin-left":"8px"}}),e._v(" "),a("el-dropdown-menu",{attrs:{slot:"dropdown"},slot:"dropdown"},[a("el-dropdown-item",{attrs:{command:"delete"}},[e._v("删除")])],1)],1)],1),e._v(" "),a("div",{staticClass:"fl-b"},[a("div",{staticClass:"fl-b-content"},[e._v(e._s(e.item.content))]),e._v(" "),e.item.img&&e.item.img.length>0?a("flexbox",{staticClass:"fl-b-images",attrs:{wrap:"wrap"}},e._l(e.item.img,function(t,i){return a("div",{directives:[{name:"lazy",rawName:"v-lazy:background-image",value:t.filePath,expression:"file.filePath",arg:"background-image"}],key:t.filePath,staticClass:"fl-b-img-item",on:{click:function(t){e.previewImg(e.item.img,i)}}})})):e._e(),e._v(" "),e.item.file&&e.item.file.length>0?a("div",{staticClass:"fl-b-files"},e._l(e.item.file,function(t,s){return a("flexbox",{key:s,staticClass:"cell"},[a("img",{staticClass:"cell-head",attrs:{src:i("6KEE")}}),e._v(" "),a("div",{staticClass:"cell-body"},[e._v(e._s(t.name)),a("span",{staticStyle:{color:"#ccc"}},[e._v("（"+e._s(t.size)+"）")])]),e._v(" "),a("el-button",{attrs:{type:"primary",icon:"el-icon-download"},on:{click:function(i){e.downloadFile(t)}}},[e._v("下载")])],1)})):e._e(),e._v(" "),e.item.category||e.item.nextTime?a("div",{staticClass:"follow"},[e.item.category?a("span",{staticClass:"follow-info"},[e._v(e._s(e.item.category))]):e._e(),e._v(" "),e.item.nextTime?a("span",{staticClass:"follow-info"},[e._v(e._s(e.item.nextTime))]):e._e()]):e._e(),e._v(" "),e.item.contactsList&&e.item.contactsList.length>0?a("div",{staticClass:"fl-b-other"},[a("div",{staticClass:"fl-b-other-name"},[e._v("关联联系人")]),e._v(" "),a("div",e._l(e.item.contactsList,function(t,i){return a("flexbox",{key:i,staticClass:"cell",nativeOn:{click:function(i){e.checkRelationDetail("contacts",t.contactsId)}}},[a("i",{staticClass:"wukong wukong-contacts cell-head crm-type",style:{opacity:0==i?1:0}}),e._v(" "),a("div",{staticClass:"cell-body",staticStyle:{color:"#6394E5",cursor:"pointer"}},[e._v(e._s(t.name))])])}))]):e._e(),e._v(" "),e.item.businessList&&e.item.businessList.length>0?a("div",{staticClass:"fl-b-other"},[a("div",{staticClass:"fl-b-other-name"},[e._v("关联商机")]),e._v(" "),a("div",e._l(e.item.businessList,function(t,i){return a("flexbox",{key:i,staticClass:"cell",nativeOn:{click:function(i){e.checkRelationDetail("business",t.businessId)}}},[a("i",{staticClass:"wukong wukong-business cell-head crm-type",style:{opacity:0==i?1:0}}),e._v(" "),a("div",{staticClass:"cell-body",staticStyle:{color:"#6394E5",cursor:"pointer"}},[e._v(e._s(t.businessName))])])}))]):e._e(),e._v(" "),e._t("default")],2),e._v(" "),a("c-r-m-full-screen-detail",{attrs:{visible:e.showFullDetail,"crm-type":e.relationCrmType,id:e.relationID},on:{"update:visible":function(t){e.showFullDetail=t}}})],1)},[],!1,null,"6af54424",null);o.options.__file="FollowRecordCell.vue";t.a=o.exports},"6vqz":function(e,t,i){},"7acB":function(e,t,i){},MEpo:function(e,t,i){"use strict";var a=i("7acB");i.n(a).a},N0PQ:function(e,t){e.exports="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMTQyIDc5LjE2MDkyNCwgMjAxNy8wNy8xMy0wMTowNjozOSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTggKFdpbmRvd3MpIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOjZGRjBCRTYxRTBBNzExRTg5QkREOEQxRjlDMkUxOUExIiB4bXBNTTpEb2N1bWVudElEPSJ4bXAuZGlkOjZGRjBCRTYyRTBBNzExRTg5QkREOEQxRjlDMkUxOUExIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6NkZGMEJFNUZFMEE3MTFFODlCREQ4RDFGOUMyRTE5QTEiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6NkZGMEJFNjBFMEE3MTFFODlCREQ4RDFGOUMyRTE5QTEiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz704RrgAAABwUlEQVR42uSXz0sCQRTH36h1sGNlaHjpVJoHC0KCOnVJOwSdqmM/6NZ/EF071rluQaduGQWd9BZEqWBhQQQmHYIQikDU6bs6G8Oy4q7sskYPPuzOzFu+++a9mZ1lnHNywlzkkDkm7JEb7/m8rWIDoVBHEc+DEuAaSmLMtqk+An6dfr8Ys0T4BHxJUbUzJq6q/yc47kR4CXg1fevgTcf3Faxp+vrAsuHikqxH054A5y2mWvYxnEaPwZTc/Kl1zJPJBt27gbR667YlnUjYMNWMORMxi8edETZhH+AZ9IIx4LZb+A5sieskGALfYMWW4hIFdQtmwJyIVt5gruEziucfrF5OVbAKZsGpzq42BVIQH+6suFovlRRQojnTzWethl73IO52wYaVEd+DcTDSaFUqRPV6c6RYJJ5ON8WJFqwuLkWl/7cWMhmE4SLm8xHP5YhFIkrEJPsYibhqQDgKCur3moXDROUy8Wy2KRoMqn5PZoQLBoSnxTf7otHyeonFYsSiUVlUsUMzwttAOfnV2my3e2ATvKjiFAjIPpdg30yOr0DYRL6VpbMDFoEPPIpz2EGrl2fyn0S3Hm8tNfbv/p1+BBgA+Vdv2Iz9D6cAAAAASUVORK5CYII="},OPCT:function(e,t){e.exports="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAUCAYAAACNiR0NAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMTQyIDc5LjE2MDkyNCwgMjAxNy8wNy8xMy0wMTowNjozOSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTggKFdpbmRvd3MpIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOjVENDNERTkxRTA5QjExRThBQkRDQjRERjVBMjY3MzE0IiB4bXBNTTpEb2N1bWVudElEPSJ4bXAuZGlkOjVENDNERTkyRTA5QjExRThBQkRDQjRERjVBMjY3MzE0Ij4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6NUQ0M0RFOEZFMDlCMTFFOEFCRENCNERGNUEyNjczMTQiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6NUQ0M0RFOTBFMDlCMTFFOEFCRENCNERGNUEyNjczMTQiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz7c1/V2AAABIElEQVR42mL8//8/AwicPXuWgVxgbGwMZzMRUBsGxBeB+AXITigfL2DBIxcCxHlA7AnEz4CYB4gnQ+VW4dKEz4UNQBwNNQwEvgBxBhAn4XMhPgMFgPghmthPIP5NroF/cYgzkmsgMw7x/+Qa+AGIZbBEIgu5BjYC8VIgloLyQbE8E4gXkJtsVkMt3Ak1DOTiDiBeSa6BDFDNK0nJNdi8rArEa4H4IzQC0PFHqLwqMQaqA/ERID4IxIrQJIKOFaDyR6Dq8Xq5DYon4fHVe6g8J1RtMD4XugDxYiKDazYQu5GSbMgC6AbuAeJYIvWmAPEuQmFYDQ3w/1Cvv8dikCDU0mIgtifkwhtAbAdV+BBHsnkIlbeDqieYsG+ixxwpACDAAIZEQR7SM/O0AAAAAElFTkSuQmCC"},OPaj:function(e,t,i){"use strict";var a=i("SQOn");i.n(a).a},"P/2M":function(e,t,i){},QJC7:function(e,t,i){"use strict";var a=i("3yi6"),s=i("5tgW"),n=i("vG5h"),l=i("qbf4"),o=i("UcQx"),c=i("6zBA"),r={name:"RecordLog",components:{FollowRecordCell:a.a},props:{id:[String,Number],crmType:{type:String,default:""}},data:function(){return{loading:!1,loadMoreLoading:!0,isPost:!1,page:1,list:[]}},computed:{},watch:{id:function(e){this.refreshList()}},mounted:function(){var e=this;this.$bus.on("follow-log-refresh",function(t){"record-log"==t.type&&e.refreshList()});var t=document.getElementById("follow-log-content");t.onscroll=function(){var i=t.scrollTop+t.clientHeight-t.scrollHeight;Math.abs(i)<10&&1==e.loadMoreLoading&&(e.isPost?e.loadMoreLoading=!1:(e.isPost=!0,e.page++,e.getList()))},this.getList()},activated:function(){},deactivated:function(){},beforeDestroy:function(){this.$bus.off("follow-log-refresh")},methods:{getList:function(){var e=this;this.loading=!0;var t={customer:n.v,leads:s.i,contacts:l.j,business:o.j,contract:c.j}[this.crmType],i={page:this.page,limit:10};i[this.crmType+"Id"]=this.id,t(i).then(function(t){e.list=e.list.concat(t.data),t.data.length<10?e.loadMoreLoading=!1:e.loadMoreLoading=!0,e.loading=!1,e.isPost=!1}).catch(function(){e.isPost=!1,e.loading=!1})},refreshList:function(){this.page=1,this.list=[],this.getList()},cellHandle:function(e){"delete"==e.type&&this.list.splice(e.data.index,1)}}},d=(i("+JiW"),i("KHd+")),m=Object(d.a)(r,function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}]},[i("div",{directives:[{name:"empty",rawName:"v-empty",value:0===e.list.length,expression:"list.length === 0"}]},[i("div",{staticClass:"log-items"},[e._l(e.list,function(t,a){return i("follow-record-cell",{key:a,attrs:{item:t,index:a,"crm-type":e.crmType},on:{"on-handle":e.cellHandle}})}),e._v(" "),i("div",{staticClass:"load"},[i("el-button",{attrs:{loading:e.loadMoreLoading,type:"text"}},[e._v(e._s(e.loadMoreLoading?"加载更多":"没有更多了"))])],1)],2)])])},[],!1,null,"05b8da62",null);m.options.__file="RecordLog.vue";t.a=m.exports},SQOn:function(e,t,i){},UEWd:function(e,t,i){"use strict";var a=i("7Qib"),s=i("KTTK"),n={name:"MixAdd",components:{CrmRelative:i("mkuN").a},props:{showRelativeBusiness:{type:Boolean,default:!1},showRelativeContacts:{type:Boolean,default:!1},id:[String,Number],crmType:{type:String,default:""}},data:function(){return{content:"",inputAutosize:{minRows:1,maxRows:6},barItems:[{img:i("S1Xc"),title:"图片",type:"img",data:"image/*"},{img:i("rl4O"),title:"附件",type:"file",data:"*.*"}],imgFiles:[],files:[],business:[],contacts:[],showRelativeType:"",batchId:""}},computed:{},mounted:function(){this.showRelativeBusiness&&this.barItems.push({img:i("i91G"),title:"关联商机",type:"business",show:!1}),this.showRelativeContacts&&this.barItems.push({img:i("OPCT"),title:"关联联系人",type:"contacts",show:!1}),this.$on("submit-info",function(){this.$emit("mixadd-info",{content:this.content,files:this.files,images:this.imgFiles,business:this.business,contacts:this.contacts,batchId:this.batchId})})},beforeDestroy:function(){this.$off("submit-info")},methods:{resetInfo:function(){this.content="",this.imgFiles=[],this.files=[],this.business=[],this.contacts=[],this.showRelativeType="",this.batchId=""},checkRelativeInfos:function(e){"business"==this.showRelativeType?this.business=e.data:"contacts"==this.showRelativeType&&(this.contacts=e.data)},barClick:function(e){this.showRelativeType=e.type,"business"==e.type?e.show=!0:"contacts"==e.type&&(e.show=!0)},uploadFile:function(e){var t=this,i=e.target.files;if(i.length){for(var a=0;a<i.length;a++){if(-1==i[a].type.indexOf("image")&&"img"==this.showRelativeType)return void this.$message.error("请上传正确的文件类型")}var s="image/*"==e.target.accept?"img":"file",n=i[0];this.sendFileRequest(n,s,function(){for(var a=1;a<i.length;a++){var n=i[a];t.sendFileRequest(n,s)}e.target.value=""})}},sendFileRequest:function(e,t,i){var n=this,l={file:e,type:t};this.batchId&&(l.batchId=this.batchId),Object(s.d)(l).then(function(s){""==n.batchId&&(n.batchId=s.batchId),s.size=Object(a.d)(e.size),"img"==t?n.imgFiles.push(s):(s.icon=Object(a.g)(e),n.files.push(s)),i&&i()}).catch(function(){})},deleteAllImg:function(){this.imgFiles=[]},deleteImgOrFile:function(e,t,i){var a=this;Object(s.b)({id:t.fileId}).then(function(t){"image"==e?a.imgFiles.splice(i,1):a.files.splice(i,1),a.$message.success("操作成功")}).catch(function(){})},mouseImgOver:function(e,t){e.showDelete=!0,this.$set(this.imgFiles,t,e)},mouseImgLeave:function(e,t){e.showDelete=!1,this.$set(this.imgFiles,t,e)},inputFocus:function(){var e=this;this.inputAutosize={minRows:3,maxRows:6},this.$nextTick(function(){e.$refs.textarea.resizeTextarea()})}}},l=(i("kblT"),i("KHd+")),o=Object(l.a)(n,function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"mix-container"},[a("div",{staticClass:"i-cont"},[a("el-input",{ref:"textarea",attrs:{autosize:e.inputAutosize,type:"textarea",clearable:"",resize:"none",placeholder:"请输入内容"},on:{focus:e.inputFocus},model:{value:e.content,callback:function(t){e.content=t},expression:"content"}})],1),e._v(" "),e.imgFiles.length>0?a("section",{staticClass:"img-cont"},[a("flexbox",{attrs:{wrap:"wrap"}},[e._l(e.imgFiles,function(t,i){return a("div",{key:i,staticClass:"img-item",style:{"background-image":"url("+t.url+")"},on:{mouseover:function(a){e.mouseImgOver(t,i)},mouseleave:function(a){e.mouseImgLeave(t,i)}}},[t.showDelete?a("div",{staticClass:"img-delete",on:{click:function(a){e.deleteImgOrFile("image",t,i)}}},[e._v("×")]):e._e()])}),e._v(" "),a("div",{staticClass:"img-item-add"},[a("input",{staticClass:"img-item-iput",attrs:{type:"file",accept:"image/*",multiple:""},on:{change:e.uploadFile}})])],2),e._v(" "),a("div",{staticClass:"img-bar",on:{click:e.deleteAllImg}},[e._v("全部删除")])],1):e._e(),e._v(" "),e.files.length>0?a("section",{staticClass:"file-cont"},[a("flexbox",{staticClass:"f-header"},[a("img",{staticClass:"f-logo",attrs:{src:i("rl4O")}}),e._v(" "),a("div",{staticClass:"f-name"},[e._v("附件")])]),e._v(" "),a("div",{staticClass:"f-body"},e._l(e.files,function(t,i){return a("flexbox",{key:i,staticClass:"f-item"},[a("img",{staticClass:"f-img",attrs:{src:t.icon}}),e._v(" "),a("div",{staticClass:"f-name"},[e._v(e._s(t.name+"("+t.size+")"))]),e._v(" "),a("div",{staticClass:"close-button",on:{click:function(a){e.deleteImgOrFile("file",t,i)}}},[e._v("×")])])})),e._v(" "),a("div",{staticClass:"img-bar",on:{click:function(t){e.files=[]}}},[e._v("全部删除")])],1):e._e(),e._v(" "),e.business.length>0?a("section",{staticClass:"c-cont"},[a("flexbox",{staticClass:"c-header"},[a("img",{staticClass:"c-logo",attrs:{src:i("i91G")}}),e._v(" "),a("div",{staticClass:"c-name"},[e._v("商机")])]),e._v(" "),a("div",{staticClass:"c-body"},[a("flexbox",{attrs:{wrap:"wrap"}},e._l(e.business,function(t,i){return a("flexbox",{key:i,staticClass:"c-item"},[a("div",{staticClass:"c-item-name"},[e._v(e._s(t.businessName))]),e._v(" "),a("div",{staticClass:"c-item-close",on:{click:function(t){e.business.splice(i,1)}}},[e._v("×")])])}))],1)],1):e._e(),e._v(" "),e.contacts.length>0?a("section",{staticClass:"c-cont"},[a("flexbox",{staticClass:"c-header"},[a("img",{staticClass:"c-logo",attrs:{src:i("OPCT")}}),e._v(" "),a("div",{staticClass:"c-name"},[e._v("联系人")])]),e._v(" "),a("div",{staticClass:"c-body"},[a("flexbox",{attrs:{wrap:"wrap"}},e._l(e.contacts,function(t,i){return a("flexbox",{key:i,staticClass:"c-item"},[a("div",{staticClass:"c-item-name"},[e._v(e._s(t.name))]),e._v(" "),a("div",{staticClass:"c-item-close",on:{click:function(t){e.contacts.splice(i,1)}}},[e._v("×")])])}))],1)],1):e._e(),e._v(" "),a("flexbox",{staticClass:"bar-cont"},[e._l(e.barItems,function(t,i){return["img"==t.type||"file"==t.type?a("flexbox",{key:i,staticClass:"bar-item",nativeOn:{click:function(i){e.barClick(t)}}},[a("input",{staticClass:"bar-input",attrs:{accept:t.data,type:"file",multiple:""},on:{change:e.uploadFile}}),e._v(" "),a("img",{staticClass:"bar-img",attrs:{src:t.img}}),e._v(" "),a("div",{staticClass:"bar-title"},[e._v(e._s(t.title))])]):a("el-popover",{key:i,attrs:{placement:"bottom",width:"700","popper-class":"no-padding-popover",trigger:"click"},model:{value:t.show,callback:function(i){e.$set(t,"show",i)},expression:"item.show"}},[a("crm-relative",{ref:"crmrelative",refInFor:!0,attrs:{show:t.show,radio:!1,action:{type:"condition",data:{moduleType:e.crmType,customerId:e.id}},"selected-data":"business"==t.type?{business:e.business}:{contacts:e.contacts},"crm-type":t.type},on:{close:function(e){t.show=!1},changeCheckout:e.checkRelativeInfos}}),e._v(" "),a("flexbox",{staticClass:"bar-item",attrs:{slot:"reference"},nativeOn:{click:function(i){e.barClick(t)}},slot:"reference"},[a("img",{staticClass:"bar-img",attrs:{src:t.img}}),e._v(" "),a("div",{staticClass:"bar-title"},[e._v(e._s(t.title))])])],1)]})],2)],1)},[],!1,null,"e146eed2",null);o.options.__file="MixAdd.vue";t.a=o.exports},Vcvm:function(e,t,i){"use strict";var a=i("r/0U");i.n(a).a},beNh:function(e,t,i){"use strict";i.r(t);var a=i("mqlf"),s=i("kegP"),n={name:"ClueIndex",components:{ClueDetail:a.a},mixins:[s.a],data:function(){return{crmType:"leads"}},computed:{},mounted:function(){},methods:{cellStyle:function(e){e.row;var t=e.column;e.rowIndex,e.columnIndex;return"leadsName"===t.property?{color:"#3E84E9",cursor:"pointer"}:""}}},l=(i("OPaj"),i("KHd+")),o=Object(l.a)(n,function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("c-r-m-list-head",{attrs:{search:e.search,"crm-type":e.crmType,title:"线索管理",placeholder:"请输入线索名称/手机/电话","main-title":"新建线索"},on:{"update:search":function(t){e.search=t},"on-handle":e.listHeadHandle,"on-search":e.crmSearch,"on-export":e.exportInfos}}),e._v(" "),a("div",{directives:[{name:"empty",rawName:"v-empty",value:!e.crm.leads.index,expression:"!crm.leads.index"}],staticClass:"crm-container",attrs:{"xs-empty-icon":"nopermission","xs-empty-text":"暂无权限"}},[a("c-r-m-table-head",{ref:"crmTableHead",attrs:{"crm-type":e.crmType},on:{filter:e.handleFilter,exportData:e.exportData,handle:e.handleHandle,scene:e.handleScene}}),e._v(" "),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],staticClass:"n-table--border",staticStyle:{width:"100%"},attrs:{id:"crm-table",data:e.list,height:e.tableHeight,"cell-style":e.cellStyle,stripe:"",border:"","highlight-current-row":""},on:{"row-click":e.handleRowClick,"sort-change":e.sortChange,"header-dragend":e.handleHeaderDragend,"selection-change":e.handleSelectionChange}},[a("el-table-column",{attrs:{"show-overflow-tooltip":"",type:"selection",align:"center",width:"55"}}),e._v(" "),e._l(e.fieldList,function(t,i){return a("el-table-column",{key:i,attrs:{fixed:0==i,prop:t.prop,label:t.label,width:t.width,formatter:e.fieldFormatter,sortable:"custom","show-overflow-tooltip":""},scopedSlots:e._u([{key:"header",fn:function(t){return[a("div",{staticClass:"table-head-name"},[e._v(e._s(t.column.label))])]}}])})}),e._v(" "),a("el-table-column"),e._v(" "),a("el-table-column",{attrs:{fixed:"right",width:"36"},scopedSlots:e._u([{key:"header",fn:function(t){return[a("img",{staticClass:"table-set",attrs:{src:i("zbTZ")},on:{click:e.handleTableSet}})]}}])})],2),e._v(" "),a("div",{staticClass:"p-contianer"},[a("el-pagination",{staticClass:"p-bar",attrs:{"current-page":e.currentPage,"page-sizes":e.pageSizes,"page-size":e.pageSize,total:e.total,layout:"total, sizes, prev, pager, next, jumper"},on:{"update:pageSize":function(t){e.pageSize=t},"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}})],1)],1),e._v(" "),e.showDview?a("clue-detail",{staticClass:"d-view",attrs:{id:e.rowID},on:{handle:e.handleHandle,"hide-view":function(t){e.showDview=!1}}}):e._e(),e._v(" "),a("fields-set",{attrs:{"crm-type":e.crmType,"dialog-visible":e.showFieldSet},on:{"update:dialogVisible":function(t){e.showFieldSet=t},"set-success":e.setSave}})],1)},[],!1,null,"3f3a33fd",null);o.options.__file="ClueIndex.vue";t.default=o.exports},i68b:function(e,t,i){"use strict";var a=i("QbLZ"),s=i.n(a),n=i("KzkL"),l=i("L2JU");t.a={data:function(){return{followTypes:[],followType:""}},computed:s()({},Object(l.b)(["oa"]),{showOAPermission:function(){return this.oa}}),created:function(){this.getFollowLogType()},methods:{getFollowLogType:function(){var e=this;this.loading=!0,Object(n.x)().then(function(t){e.loading=!1,e.followTypes=t.data.map(function(e){return{type:e,name:e}}),e.followType=t.data.length>0?t.data[0]:""}).catch(function(){e.loading=!1})}},deactivated:function(){}}},i91G:function(e,t){e.exports="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAUCAYAAACNiR0NAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMTQyIDc5LjE2MDkyNCwgMjAxNy8wNy8xMy0wMTowNjozOSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTggKFdpbmRvd3MpIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOjU2RjI0NjM0RTA5QjExRTg4QTE5QjM2OEE1QUE5MUZBIiB4bXBNTTpEb2N1bWVudElEPSJ4bXAuZGlkOjU2RjI0NjM1RTA5QjExRTg4QTE5QjM2OEE1QUE5MUZBIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6NTZGMjQ2MzJFMDlCMTFFODhBMTlCMzY4QTVBQTkxRkEiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6NTZGMjQ2MzNFMDlCMTFFODhBMTlCMzY4QTVBQTkxRkEiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz4H2LzoAAAAnklEQVR42mI8c+YMA7WAsbExAxMDlQGygSuA+B8Q/8eD/0HV4QQsSOwwNAtwAZChEcS4kJFIXzES62WqhyFdDESOqH1ocjuwRRQLAQPxRZQHEvsvLKKYKIkAbD5FNvA/kZr/ExuGq5HC6wiauo1I4bWa2IQdDsXYgP+gTTZUN5DYiPpHbDqERRQjAUtXE2sgvojCnnD///8/uCMFIMAAVWsql9WKpTgAAAAASUVORK5CYII="},kblT:function(e,t,i){"use strict";var a=i("6vqz");i.n(a).a},mqlf:function(e,t,i){"use strict";var a=i("5tgW"),s=i("Ew9n"),n=i("F03V"),l=i("UEWd"),o=i("QJC7"),c=i("i68b"),r={name:"ClueFollow",components:{MixAdd:l.a,RecordLog:o.a},mixins:[c.a],props:{id:[String,Number],crmType:{type:String,default:""}},data:function(){return{sendLoading:!1,nextTime:"",isEvent:!1,logType:"record",logTypes:[]}},computed:{componentsName:function(){return"record"==this.logType?"RecordLog":""}},watch:{},mounted:function(){},activated:function(){},deactivated:function(){},methods:{handleTypeDrop:function(e){this.followType=e},logTabsClick:function(e,t){this.logType=e.type},sendInfo:function(){this.$refs.mixadd.$emit("submit-info")},submitInfo:function(e){var t=this;if(!this.isEvent||this.nextTime)if(e.content){var i={};i.typesId=this.id,i.content=e.content,i.category=this.followType,i.batchId=e.batchId,i.isEvent=this.isEvent?1:0,i.nextTime=this.nextTime||"",this.sendLoading=!0,Object(a.j)(i).then(function(e){t.sendLoading=!1,t.$message.success("发布成功"),t.$refs.mixadd.resetInfo(),t.isEvent=!1,t.nextTime="",t.$bus.emit("follow-log-refresh",{type:"record-log"})}).catch(function(){t.sendLoading=!1})}else this.$message.error("请输入跟进内容");else this.$message.error("请选择下次联系时间")}}},d=(i("Vcvm"),i("KHd+")),m=Object(d.a)(r,function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",{staticClass:"f-container"},[i("div",{directives:[{name:"loading",rawName:"v-loading",value:e.sendLoading,expression:"sendLoading"}]},[i("mix-add",{ref:"mixadd",attrs:{"crm-type":e.crmType,id:e.id},on:{"mixadd-info":e.submitInfo}}),e._v(" "),i("flexbox",{staticClass:"se-section"},[i("div",{staticClass:"se-name"},[e._v("记录类型")]),e._v(" "),i("el-dropdown",{staticStyle:{"margin-right":"20px"},attrs:{trigger:"click"},on:{command:e.handleTypeDrop}},[i("flexbox",{staticClass:"se-select"},[i("div",{staticClass:"se-select-name"},[e._v(e._s(e.followType?e.followType:"请选择"))]),e._v(" "),i("i",{staticClass:"el-icon-arrow-down el-icon--right",staticStyle:{color:"#ccc"}})]),e._v(" "),i("el-dropdown-menu",{attrs:{slot:"dropdown"},slot:"dropdown"},e._l(e.followTypes,function(t,a){return i("el-dropdown-item",{key:a,attrs:{command:t.type}},[e._v(e._s(t.name))])}))],1),e._v(" "),i("div",{staticClass:"se-name"},[e._v("下次联系时间")]),e._v(" "),i("el-date-picker",{staticClass:"se-datepicker",attrs:{"default-value":new Date,editable:!1,type:"datetime",placeholder:"选择日期","value-format":"yyyy-MM-dd HH:mm:ss"},model:{value:e.nextTime,callback:function(t){e.nextTime=t},expression:"nextTime"}}),e._v(" "),e.showOAPermission?i("el-checkbox",{model:{value:e.isEvent,callback:function(t){e.isEvent=t},expression:"isEvent"}},[e._v("添加到日程提醒")]):e._e(),e._v(" "),i("el-button",{staticClass:"se-send",attrs:{type:"primary"},nativeOn:{click:function(t){return e.sendInfo(t)}}},[e._v("发布")])],1)],1),e._v(" "),i("div",{staticClass:"log-cont"},[e.logTypes.length>0?i("flexbox",{staticStyle:{"border-bottom":"1px solid #E6E6E6"}},e._l(e.logTypes,function(t,a){return i("flexbox",{key:a,staticStyle:{width:"auto"},nativeOn:{click:function(i){e.logTabsClick(t,a)}}},[i("div",{staticClass:"log-tabs-item",style:{color:e.logType==t.type?"#F18C70":"#777"}},[e._v(e._s(t.name))]),e._v(" "),e.logTypes.length-1!=a?i("div",{staticClass:"log-tabs-line"}):e._e()])})):e._e(),e._v(" "),i(e.componentsName,{tag:"component",attrs:{id:e.id,"crm-type":e.crmType}})],1)])},[],!1,null,"85599a58",null);m.options.__file="ClueFollow.vue";var u=m.exports,h=i("U05J"),v=i("C+sD"),g=i("8DbX"),p=i("EgJF"),b=i("4+0Q"),f={name:"ClueDetail",components:{SlideView:s.a,CRMDetailHead:n.a,ClueFollow:u,CRMBaseInfo:h.a,RelativeFiles:v.a,RelativeHandle:g.a,CRMCreateView:p.default},mixins:[b.a],props:{id:[String,Number],listenerIDs:{type:Array,default:function(){return["crm-main-container"]}},noListenerIDs:{type:Array,default:function(){return[]}},noListenerClass:{type:Array,default:function(){return["el-table__body"]}}},data:function(){return{loading:!1,crmType:"leads",detailData:{},headDetails:[{title:"姓名",value:""},{title:"线索来源",value:""},{title:"手机",value:""},{title:"负责人",value:""},{title:"创建时间",value:""}],tabnames:[{label:"跟进记录",name:"followlog"},{label:"基本信息",name:"basicinfo"},{label:"附件",name:"file"},{label:"操作记录",name:"operationlog"}],tabCurrentName:"followlog",isCreate:!1}},computed:{tabName:function(){return"followlog"==this.tabCurrentName?"clue-follow":"basicinfo"==this.tabCurrentName?"c-r-m-base-info":"file"==this.tabCurrentName?"relative-files":"operationlog"==this.tabCurrentName?"relative-handle":""}},mounted:function(){},methods:{getDetial:function(){var e=this;this.loading=!0,Object(a.h)({leadsId:this.id}).then(function(t){e.detailData=t.data,e.headDetails[0].value=t.data.name,e.headDetails[1].value=t.data.线索来源,e.headDetails[2].value=t.data.mobile,e.headDetails[3].value=t.data.ownerUserName,e.headDetails[4].value=t.data.createTime,e.loading=!1}).catch(function(){e.loading=!1})},hideView:function(){this.$emit("hide-view")},handleClick:function(e,t){},editSaveSuccess:function(){this.$emit("handle",{type:"save-success"}),this.getDetial()}}},y=(i("x1Ir"),Object(d.a)(f,function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("slide-view",{directives:[{name:"empty",rawName:"v-empty",value:!e.canShowDetail,expression:"!canShowDetail"}],attrs:{"listener-ids":e.listenerIDs,"no-listener-ids":e.noListenerIDs,"no-listener-class":e.noListenerClass,"body-style":{padding:0,height:"100%"},"xs-empty-icon":"nopermission","xs-empty-text":"暂无权限"},on:{"side-close":e.hideView}},[e.canShowDetail?i("flexbox",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],staticClass:"d-container",attrs:{direction:"column",align:"stretch"}},[i("c-r-m-detail-head",{attrs:{detail:e.detailData,"head-details":e.headDetails,id:e.id,"crm-type":"leads"},on:{handle:e.detailHeadHandle,close:e.hideView}}),e._v(" "),i("div",{staticClass:"tabs"},[i("el-tabs",{on:{"tab-click":e.handleClick},model:{value:e.tabCurrentName,callback:function(t){e.tabCurrentName=t},expression:"tabCurrentName"}},e._l(e.tabnames,function(e,t){return i("el-tab-pane",{key:t,attrs:{label:e.label,name:e.name}})}))],1),e._v(" "),i("div",{staticClass:"t-loading-content",attrs:{id:"follow-log-content"}},[i("keep-alive",[i(e.tabName,{tag:"component",attrs:{detail:e.detailData,id:e.id,"crm-type":"leads"}})],1)],1)],1):e._e(),e._v(" "),e.isCreate?i("c-r-m-create-view",{attrs:{action:{type:"update",id:e.id,batchId:e.detailData.batchId},"crm-type":"leads"},on:{"save-success":e.editSaveSuccess,"hiden-view":function(t){e.isCreate=!1}}}):e._e()],1)},[],!1,null,"65003652",null));y.options.__file="ClueDetail.vue";t.a=y.exports},"r/0U":function(e,t,i){},rl4O:function(e,t){e.exports="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAUCAYAAACNiR0NAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMTQyIDc5LjE2MDkyNCwgMjAxNy8wNy8xMy0wMTowNjozOSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTggKFdpbmRvd3MpIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOjUzM0EzN0E0RTA5QjExRTg4MTZEOEM2ODYxNjE5QjM3IiB4bXBNTTpEb2N1bWVudElEPSJ4bXAuZGlkOjUzM0EzN0E1RTA5QjExRTg4MTZEOEM2ODYxNjE5QjM3Ij4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6NTMzQTM3QTJFMDlCMTFFODgxNkQ4QzY4NjE2MTlCMzciIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6NTMzQTM3QTNFMDlCMTFFODgxNkQ4QzY4NjE2MTlCMzciLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz7neeY0AAABL0lEQVR42syUv0tCURSA1Vzam5ya+h8ag5DG3GpqqsAiwVFoECJc+jGpCFZrLTpFUjQ7PPwjClxyiCY3+y6cB5fLud0rOXjhG965j++de865L5skSWaRK5dZ8JpHmIUG/MAA1v4jNLIWVKAO6/CmSWOFO3AAJbiCLVjVpLHCV9iAF3ke+6S5wDFr8Az78Ons29LHkNDImnAO3/AAZeU9I+3AZhrI/9EAU7Nd6egIbuEJJk5tL2RPzVCTpfGZ0qieZF/TMvTJzmT+TqzsbFnZ/pidYUORHcKNzF87JHMzPJXUB1ZsBY6gGyNzhUM4lhEYS6ztqZkqc4+8B1N4h4LSgH5I5gq/YFuRprL7kEwbm4kjrYrsLkbmuymp9AMu4VpGZhZz6fOeuJEWl+KP/SvAAHR5UyjSji44AAAAAElFTkSuQmCC"},x1Ir:function(e,t,i){"use strict";var a=i("1pAj");i.n(a).a}}]);