(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-fd8f"],{"3oVh":function(e,t,o){"use strict";o.r(t);var i=o("4peS"),l=o.n(i),a={name:"App",components:{},props:{versionData:{type:Object,default:void 0},folderId:{type:Number,default:void 0},batchId:{type:Number,default:void 0}},data:function(){return{dialogLocalStatus:!1,dialogNetdiskStatus:!1,fileTypeId:void 0,fileTypeList:[],fileRemark:"",uploadAction:"",uploadActionVersion:"",uploadHeaders:{"Admin-Token":l.a.get("Admin-Token")}}},watch:{},created:function(){},mounted:function(){console.log("versionData",this.versionData),this.retriveTypeList()},methods:{retriveTypeList:function(){var e=this;this.$request.post("/type/queryList?pageType=0").then(function(t){console.log("附件类型列表",t),e.fileTypeList=t.data}).catch(function(e){console.log("retriveTypeList err",e)})},localUpload:function(){void 0===this.versionData?this.uploadAction="//file/upload?folderId="+this.folderId+"&typeId="+this.fileTypeId:this.uploadAction="//file/changeVersion?fileId="+this.versionData.fileId+"&folderId="+this.versionData.folderId+"&fileRemark="+this.fileRemark,this.dialogLocalStatus=!0,console.log("上传地址",this.uploadAction)},beforeUpload:function(e){return console.log(e),null==this.folderId?(this.$message.error("请选择上传的文件夹"),!1):void 0==this.fileTypeId?(this.$message.error("请选择文件类型"),!1):void 0},dialogClose:function(){},selectChanged:function(e){this.fileTypeId=e,this.localUpload()}}},s=(o("TdGi"),o("KHd+")),n=Object(s.a)(a,function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("div",{staticClass:"ls-upload"},[o("span",[o("el-button",{attrs:{type:"primary"},on:{click:function(t){e.dialogLocalStatus=!0}}},[e._v(e._s(void 0===e.versionData?"上传文件":"替换版本"))])],1),e._v(" "),o("el-dialog",{attrs:{visible:e.dialogLocalStatus,"append-to-body":!0,title:"本地上传",width:"30%"},on:{"update:visible":function(t){e.dialogLocalStatus=t},close:e.dialogClose}},[o("el-form",{ref:"form",attrs:{"label-width":"80px"}},[o("el-form-item",{attrs:{label:"文件类型："}},[o("el-select",{attrs:{placeholder:"请选择文件类型"},on:{change:e.selectChanged},model:{value:e.fileTypeId,callback:function(t){e.fileTypeId=t},expression:"fileTypeId"}},e._l(e.fileTypeList,function(e,t){return o("el-option",{key:t,attrs:{label:e.typeName,value:e.typeId}})}))],1),e._v(" "),void 0!=e.versionData?o("el-form-item",{attrs:{label:"版本备注："}},[o("el-input",{attrs:{placeholder:"请输入备注"},model:{value:e.fileRemark,callback:function(t){e.fileRemark=t},expression:"fileRemark"}})],1):e._e(),e._v(" "),o("el-upload",{staticClass:"upload-demo",attrs:{headers:e.uploadHeaders,action:e.uploadAction,"before-upload":e.beforeUpload,drag:"",multiple:""}},[o("i",{staticClass:"el-icon-upload"}),e._v(" "),o("div",{staticClass:"el-upload__text"},[e._v("\n          将文件拖到此处，或\n          "),o("em",[e._v("点击上传")])])])],1)],1)],1)},[],!1,null,"786de736",null);n.options.__file="upload.vue";t.default=n.exports},TdGi:function(e,t,o){"use strict";var i=o("V2HN");o.n(i).a},V2HN:function(e,t,o){}}]);