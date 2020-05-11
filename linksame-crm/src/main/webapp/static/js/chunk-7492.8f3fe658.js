(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-7492"],{"6R3Q":function(e,t,i){"use strict";var n={name:"Reminder",components:{},props:{content:{type:String,default:"内容"},fontSize:{type:String,default:"13"}},data:function(){return{}},computed:{},mounted:function(){},destroyed:function(){},methods:{}},a=(i("kLyV"),i("KHd+")),o=Object(a.a)(n,function(){var e=this.$createElement,t=this._self._c||e;return t("flexbox",{staticClass:"reminder-wrapper"},[t("flexbox",{staticClass:"reminder-body",attrs:{align:"stretch"}},[t("i",{staticClass:"wukong wukong-reminder reminder-icon"}),this._v(" "),t("div",{staticClass:"reminder-content",style:{"font-size":this.fontSize+"px"}},[this._v("\n      "+this._s(this.content)+"\n    ")])])],1)},[],!1,null,"0e2a0d66",null);o.options.__file="reminder.vue";t.a=o.exports},FSvo:function(e,t,i){"use strict";i.r(t);var n=i("t3Un");var a={name:"JurisdictionCreate",components:{},props:{show:{type:Boolean,default:!1},action:{type:Object,default:function(){return{type:"save"}}}},data:function(){return{loading:!1,roleName:"",remark:"",showTreeData:[],defaultProps:{children:"childMenu",label:"menuName"}}},computed:{diaTitle:function(){return"save"==this.action.type?"新建":"编辑"}},watch:{show:function(e){e&&this.initInfo()}},mounted:function(){},methods:{initInfo:function(){"update"==this.action.type?(this.roleName=this.action.data.roleName,this.remark=this.action.data.remark):(this.roleName="",this.remark="",this.$refs.tree&&this.$refs.tree.setCheckedKeys([])),0==this.showTreeData.length?this.getRulesList():this.checkTreeByUpdateInfo()},getRulesList:function(){var e=this;this.loading=!0,function(e){return Object(n.a)({url:"system/menu/getWorkMenuList",method:"post",data:e})}().then(function(t){e.showTreeData=[t.data],e.checkTreeByUpdateInfo(),e.loading=!1}).catch(function(){e.loading=!1})},checkTreeByUpdateInfo:function(){var e=this;"update"==this.action.type&&this.$nextTick(function(){e.$refs.tree&&e.$refs.tree.setCheckedKeys(e.getUserModuleRules(e.action.data.rules))})},sureClick:function(){var e=this;if(this.roleName){this.loading=!0;var t=this.$refs.tree.getCheckedKeys(),i={roleName:this.roleName,remark:this.remark,rules:t};"update"==this.action.type&&(i.roleId=this.action.data.roleId),function(e){return Object(n.a)({url:"system/role/setWorkRole",method:"post",headers:{"Content-Type":"application/json;charset=UTF-8"},data:e})}(i).then(function(t){e.loading=!1,e.$emit("submite"),e.closeView()}).catch(function(){e.loading=!1})}else this.$message.error("请填写权限名称")},closeView:function(){this.$emit("update:show",!1)},getUserModuleRules:function(e){e||(e=[]);for(var t=this.showTreeData[0],i=!1,n=this.copyItem(e),a=0;a<t.childMenu.length;a++){var o=t.childMenu[a];o.childMenu||(o.childMenu=[]);for(var s=0;s<e.length;s++){for(var r=e[s],l=[],c=0;c<o.childMenu.length;c++){var d=o.childMenu[c];d.menuId==r&&l.push(d)}l.length!=o.childMenu.length&&(i=!0,this.removeItem(n,o.menuId))}}i&&this.removeItem(n,t.menuId);for(var u=[],h=0;h<n.length;h++){var m=n[h];m&&u.push(parseInt(m))}return u},copyItem:function(e){for(var t=[],i=0;i<e.length;i++)t.push(e[i]);return t},removeItem:function(e,t){for(var i=-1,n=0;n<e.length;n++)if(t==e[n]){i=n;break}i>0&&e.splice(i,1)},containItem:function(e,t){for(var i=0;i<e.length;i++)if(t==e[i])return!0;return!1}}},o=(i("RrFu"),i("KHd+")),s=Object(o.a)(a,function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("el-dialog",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],staticClass:"create-dialog",attrs:{title:e.diaTitle,visible:e.show,"modal-append-to-body":!0,"append-to-body":!0,"close-on-click-modal":!1,width:"700px"},on:{close:e.closeView}},[i("div",{staticClass:"label-input"},[i("label",{staticClass:"label-title"},[e._v("权限名称")]),e._v(" "),i("el-input",{attrs:{placeholder:"请输入权限名称"},model:{value:e.roleName,callback:function(t){e.roleName=t},expression:"roleName"}})],1),e._v(" "),i("div",{staticClass:"label-input"},[i("label",{staticClass:"label-title"},[e._v("权限描述")]),e._v(" "),i("el-input",{attrs:{rows:2,type:"textarea",placeholder:"请输入权限描述"},model:{value:e.remark,callback:function(t){e.remark=t},expression:"remark"}})],1),e._v(" "),i("label",{staticClass:"label-title"},[e._v("权限配置")]),e._v(" "),i("div",{staticClass:"jurisdiction-content-checkbox"},[i("el-tree",{ref:"tree",staticStyle:{height:"0"},attrs:{data:e.showTreeData,indent:0,"expand-on-click-node":!1,props:e.defaultProps,"show-checkbox":"","node-key":"menuId","empty-text":"","default-expand-all":""}})],1),e._v(" "),i("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[i("el-button",{attrs:{type:"primary"},on:{click:e.sureClick}},[e._v("确 定")]),e._v(" "),i("el-button",{on:{click:e.closeView}},[e._v("取 消")])],1)])},[],!1,null,"178bdfe8",null);s.options.__file="jurisdictionCreate.vue";var r={name:"SystemProject",components:{JurisdictionCreate:s.exports,Reminder:i("6R3Q").a},mixins:[],data:function(){return{loading:!1,tableHeight:document.documentElement.clientHeight-196,list:[],createAction:{type:"save"},jurisdictionCreateShow:!1}},computed:{},mounted:function(){var e=this;window.onresize=function(){e.tableHeight=document.documentElement.clientHeight-196},this.getList()},methods:{getList:function(){var e=this;this.loading=!0,function(e){return Object(n.a)({url:"system/role/queryProjectRoleList",method:"post",data:e})}().then(function(t){e.list=t.data,e.loading=!1}).catch(function(){e.loading=!1})},addJurisdiction:function(){this.createAction={type:"save"},this.jurisdictionCreateShow=!0},handleRowClick:function(e,t,i){},handleClick:function(e,t){var i=this;"edit"===e?(this.createAction={type:"update",data:t.row},this.jurisdictionCreateShow=!0):"delete"===e&&this.$confirm("您确定要删除吗?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){(function(e){return Object(n.a)({url:"system/role/deleteWorkRole",method:"post",data:e})})({roleId:t.row.roleId}).then(function(e){i.list.splice(t.$index,1),i.$message({type:"success",message:"操作成功"})}).catch(function(){})}).catch(function(){i.$message({type:"info",message:"已取消删除"})})}}},l=(i("RNBC"),Object(o.a)(r,function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",{staticClass:"se-container"},[i("div",{staticClass:"se-header"},[e._v("自定义项目权限")]),e._v(" "),i("div",{staticClass:"se-body"},[i("div",{staticClass:"se-table-header"},[i("reminder",{staticClass:"project-reminder",attrs:{content:"为不同场景下的项目成员所需的权限设置匹配的项目、任务列表、任务的操作权限"}}),e._v(" "),i("el-button",{staticClass:"se-table-header-button",attrs:{type:"primary"},on:{click:e.addJurisdiction}},[e._v("新建权限")])],1),e._v(" "),i("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],staticClass:"main-table",staticStyle:{width:"100%"},attrs:{id:"examine-table",data:e.list,height:e.tableHeight,"highlight-current-row":""},on:{"row-click":e.handleRowClick}},[i("el-table-column",{attrs:{"show-overflow-tooltip":"",prop:"roleName",width:"150",label:"项目权限"}}),e._v(" "),i("el-table-column",{attrs:{"show-overflow-tooltip":"",prop:"remark",label:"项目描述"}}),e._v(" "),i("el-table-column",{attrs:{fixed:"right",label:"操作",width:"100"},scopedSlots:e._u([{key:"default",fn:function(t){return[i("el-button",{attrs:{disabled:5==t.row.roleType,type:"text",size:"small"},on:{click:function(i){e.handleClick("edit",t)}}},[e._v("编辑")]),e._v(" "),i("el-button",{attrs:{disabled:5==t.row.roleType,type:"text",size:"small"},on:{click:function(i){e.handleClick("delete",t)}}},[e._v("删除")])]}}])})],1)],1),e._v(" "),i("jurisdiction-create",{attrs:{show:e.jurisdictionCreateShow,action:e.createAction},on:{"update:show":function(t){e.jurisdictionCreateShow=t},submite:e.getList}})],1)},[],!1,null,"c2dd54d8",null));l.options.__file="index.vue";t.default=l.exports},RNBC:function(e,t,i){"use strict";var n=i("SOvT");i.n(n).a},RrFu:function(e,t,i){"use strict";var n=i("cVo9");i.n(n).a},SOvT:function(e,t,i){},cVo9:function(e,t,i){},kLyV:function(e,t,i){"use strict";var n=i("wx7R");i.n(n).a},wx7R:function(e,t,i){}}]);