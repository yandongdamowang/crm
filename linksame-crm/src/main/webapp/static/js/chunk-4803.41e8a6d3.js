(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-4803"],{ZOAi:function(e,t,i){"use strict";var n=i("xze5");i.n(n).a},oMpN:function(e,t,i){"use strict";i.r(t);var n={name:"App",components:{AddProject:i("b06H").a},data:function(){return{fileTypeList:[],isCreate:!1,pageCurrent:1,pageTotal:1,pageSize:10,dialogCreate:!1,form:{typeName:void 0,typeCode:void 0}}},watch:{},created:function(){},mounted:function(){this.retriveTypeList()},methods:{retriveTypeList:function(){var e=this;this.$request.post("/type/queryList?pageType=1",{page:this.pageCurrent,limit:this.papgeSize}).then(function(t){console.log("附件类型列表",t),e.fileTypeList=t.data.list,e.pageTotal=t.data.totalRow}).catch(function(e){console.log("retriveTypeList err",e)})},createFileType:function(){var e=this;this.$request.post("/type/setType?typeName="+this.form.typeName+"&typeCode="+this.form.typeCode,{}).then(function(t){console.log(t),e.dialogCreate=!1,e.retriveTypeList()}).catch(function(e){console.log(e)})},deleteFileType:function(e){var t=this;this.$confirm("此操作将永久删除该文件类型, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){t.$request.post("/type/delType?typeId="+e.typeId,{}).then(function(e){t.retriveTypeList()}).catch(function(e){console.log(e)}),t.$message({type:"success",message:"删除成功!"})}).catch(function(){t.$message({type:"info",message:"已取消删除"})})},handleCurrentChange:function(e){console.log("当前页: "+e),this.pageCurrent=e,this.retriveTypeList()},handleSizeChange:function(e){console.log("每页 "+e+" 条"),this.pageSize=e,this.retriveTypeList()}}},s=(i("ZOAi"),i("KHd+")),o=Object(s.a)(n,function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",{staticStyle:{height:"100%"}},[e._m(0),e._v(" "),i("div",{staticClass:"ls-box"},[i("div",[i("span",[i("el-button",{attrs:{type:"primary"},on:{click:function(t){e.isCreate=!0}}},[e._v("新建")])],1)]),e._v(" "),i("div",[e.isCreate?i("add-project",{on:{close:function(t){e.isCreate=!1}}}):e._e()],1)])])},[function(){var e=this.$createElement,t=this._self._c||e;return t("div",{staticClass:"ls-header"},[this._v("\n    全部项目\n    "),t("span",{staticClass:"grep"})])}],!1,null,"04cb5d9e",null);o.options.__file="index.vue";t.default=o.exports},xze5:function(e,t,i){}}]);