(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-6684"],{"2SaN":function(e,t,i){},"6R3Q":function(e,t,i){"use strict";var n={name:"Reminder",components:{},props:{content:{type:String,default:"内容"},fontSize:{type:String,default:"13"}},data:function(){return{}},computed:{},mounted:function(){},destroyed:function(){},methods:{}},l=(i("kLyV"),i("ZrdR")),o=Object(l.a)(n,function(){var e=this.$createElement,t=this._self._c||e;return t("flexbox",{staticClass:"reminder-wrapper"},[t("flexbox",{staticClass:"reminder-body",attrs:{align:"stretch"}},[t("i",{staticClass:"wukong wukong-reminder reminder-icon"}),this._v(" "),t("div",{staticClass:"reminder-content",style:{"font-size":this.fontSize+"px"}},[this._v("\n      "+this._s(this.content)+"\n    ")])])],1)},[],!1,null,"0e2a0d66",null);o.options.__file="reminder.vue";t.a=o.exports},DWe6:function(e,t,i){"use strict";var n=i("RPWt");i.n(n).a},FkPX:function(e,t,i){},IDwW:function(e,t,i){"use strict";i.r(t);var n=i("sGAf"),l={name:"RelateEmpoyee",components:{XhUserCell:i("8GhS").p},mixins:[],props:{visible:{type:Boolean,required:!0,default:!1},roleId:[Number,String]},data:function(){return{loading:!0,selectUsers:[]}},computed:{},watch:{visible:function(e){e&&(this.selectUsers=[])}},mounted:function(){},methods:{handleCancel:function(){this.$emit("update:visible",!1)},changeCheckout:function(e){this.selectUsers=e.value},handleConfirm:function(){var e=this;0==this.selectUsers.length?this.$message.error("请选择员工"):Object(n.j)({userIds:this.selectUsers.map(function(e){return e.userId}).join(","),roleIds:this.roleId}).then(function(t){e.$message.success("操作成功"),e.$emit("save")}).catch(function(){})}}},o=(i("fIQt"),i("ZrdR")),s=Object(o.a)(l,function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("el-dialog",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],attrs:{visible:e.visible,"append-to-body":!0,title:"关联员工",width:"600px"},on:{close:e.handleCancel}},[i("div",{staticClass:"handle-box"},[i("flexbox",{staticClass:"handle-item",attrs:{align:"stretch"}},[i("div",{staticClass:"handle-item-name",staticStyle:{"margin-top":"8px"}},[e._v("选择员工：")]),e._v(" "),i("xh-user-cell",{staticClass:"handle-item-content",attrs:{radio:!1,value:e.selectUsers},on:{"value-change":e.changeCheckout}})],1)],1),e._v(" "),i("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[i("el-button",{nativeOn:{click:function(t){return e.handleCancel(t)}}},[e._v("取消")]),e._v(" "),i("el-button",{attrs:{type:"primary"},nativeOn:{click:function(t){return e.handleConfirm(t)}}},[e._v("保存")])],1)])},[],!1,null,"b1066116",null);s.options.__file="relateEmpoyee.vue";var a=s.exports,r=i("KTTK"),c={components:{RelateEmpoyee:a,Reminder:i("6R3Q").a},data:function(){return{pid:"",title:"",tableData:[],tableHeight:document.documentElement.clientHeight-305,treeHeight:document.documentElement.clientHeight-230,currentPage:1,pageSize:15,pageSizes:[15,30,45,60],total:0,tableList:[{label:"姓名",field:"realname"},{label:"部门",field:"deptName"},{label:"职位",field:"post"},{label:"角色",field:"roleName"}],newRoleVisible:!1,role:{},roleList:[],mainMenuIndex:"user",ruleMenuIndex:"data",ruleMenuList:[],defaultProps:{children:"childMenu",label:"menuName"},relateEmpoyeeShow:!1,roleActive:null,dropdownHandleRole:null,roleTitle:"",roleMenuLoading:!1,ruleLoading:!1,userLoading:!1}},computed:{roleId:function(){return this.roleActive?this.roleActive.roleId:""},showRuleSet:function(){return!!this.roleActive&&("admin"!=this.roleActive.remark&&"project"!=this.roleActive.remark)},showReminder:function(){return!!this.roleActive&&"project"==this.roleActive.remark}},watch:{},mounted:function(){var e=this;window.onresize=function(){e.tableHeight=document.documentElement.clientHeight-305,e.treeHeight=document.documentElement.clientHeight-230},this.pid=this.$route.params.pid,this.title=this.$route.params.title,this.getRulesList(),this.getRoleList()},beforeRouteUpdate:function(e,t,i){this.pid=e.params.pid,this.title=e.params.title,this.roleActive=null,this.roleList=[],this.currentPage=1,this.total=0,this.tableData=[],this.getRulesList(),this.getRoleList(),i()},methods:{getRulesList:function(){var e=this;Object(n.g)({roleType:this.pid}).then(function(t){t.data.data?(e.ruleMenuList=[{label:"模块功能",index:"data",type:"tree",value:[],data:[t.data.data]}],t.data.bi&&e.ruleMenuList.push({label:"数据分析",index:"bi",type:"tree",value:[],data:[t.data.bi]})):e.ruleMenuList=[],e.getRoleRulesInfo()})},getRoleList:function(){var e=this;this.roleMenuLoading=!0,Object(n.f)({roleType:this.pid}).then(function(t){e.roleList=t.data;var i=!1;if(e.roleActive)for(var n=0;n<e.roleList.length;n++){var l=e.roleList[n];if(l.roleId==e.roleActive.roleId){e.roleActive=l,e.getRoleRulesInfo(),i=!0;break}}!i&&e.roleList.length&&(e.roleActive=e.roleList[0],e.getRoleRulesInfo()),e.getUserList(e.roleActive),e.roleMenuLoading=!1}).catch(function(){e.roleMenuLoading=!1})},addEmployees:function(){this.relateEmpoyeeShow=!0},employeesSave:function(e){this.relateEmpoyeeShow=!1,this.getUserList(this.roleActive)},delectEmployees:function(e){var t=this;this.$confirm("此操作将永久删除是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){t.userLoading=!0,Object(n.i)({userId:e.userId,roleId:t.roleActive.roleId}).then(function(e){t.userLoading=!0,t.getUserList(t.roleActive),t.$message.success("删除成功")})}).catch(function(){t.$message({type:"info",message:"已取消删除"})})},newRoleClose:function(){this.newRoleVisible=!1},newRoleBtn:function(){this.roleTitle="新建角色",this.newRoleVisible=!0,this.role={}},roleDropdownClick:function(e){this.dropdownHandleRole=e},roleHandleClick:function(e){"edit"==e?this.roleEditBtn(this.dropdownHandleRole):"copy"==e?this.ticketsBtn(this.dropdownHandleRole):"delete"==e&&this.roleDelect(this.dropdownHandleRole)},getReminderContent:function(){return this.roleActive&&"project"==this.roleActive.remark?"项目管理员拥有“项目管理”模块所有权限，能看到并维护所有项目信息":""},roleEditBtn:function(e){this.roleTitle="编辑角色",this.role={title:e.roleName,pid:e.roleType,id:e.roleId},this.newRoleVisible=!0},ticketsBtn:function(e){var t=this;this.$confirm("确定此操作?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){Object(n.c)({roleId:e.roleId}).then(function(e){t.$message.success("复制成功"),t.getRoleList()})}).catch(function(){t.$message({type:"info",message:"已取消删除"})})},roleDelect:function(e){var t=this;this.$confirm("此操作将永久删除是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){Object(n.d)({roleId:e.roleId}).then(function(e){t.roleList.length&&(t.roleActive=t.roleList[0],t.getRoleRulesInfo()),t.getRoleList(),t.$message.success("删除成功")})}).catch(function(){t.$message({type:"info",message:"已取消删除"})})},newRoleSubmit:function(){var e=this;this.role.title?"新建角色"==this.roleTitle?Object(n.b)({roleName:this.role.title,roleType:this.pid}).then(function(t){e.getRoleList(),e.$message.success("添加成功"),e.newRoleClose()}):Object(n.e)({roleName:this.role.title,roleType:this.role.pid,roleId:this.role.id}).then(function(t){e.getRoleList(),e.$message.success("编辑成功"),e.newRoleClose()}):this.$message.error("角色名称不能为空")},roleMenuSelect:function(e){this.roleActive=e,"rule"!=this.mainMenuIndex||this.showRuleSet||(this.mainMenuIndex="user"),this.getRoleRulesInfo(),this.getUserList(this.roleActive)},getRoleRulesInfo:function(){var e=this;if(this.roleActive&&this.ruleMenuList.length){if(2==this.pid)"data"!=this.ruleMenuList[this.ruleMenuList.length-1].type&&this.ruleMenuList.push({label:"数据权限",index:"info",type:"data",value:this.roleActive.dataType});for(var t=function(t){var i=e.ruleMenuList[t];"tree"==i.type?(i.rules=e.getRoleRules(e.roleActive.rules[i.index],i.data[0]),e.$nextTick(function(){var t=e.$refs["tree"+i.index];t&&("[object Array]"==Object.prototype.toString.call(t)?t.length&&t[0].setCheckedKeys(i.rules):t.setCheckedKeys(i.rules))})):i.value=e.roleActive.dataType},i=0;i<this.ruleMenuList.length;i++)t(i)}},getRoleRules:function(e,t){e||(e=[]);for(var i=!1,n=this.copyItem(e),l=0;l<t.childMenu.length;l++){var o=t.childMenu[l];if(!o.hasOwnProperty("children"))return o.length+1!=n.length&&this.removeItem(n,t.id),n;for(var s=0;s<e.length;s++){for(var a=e[s],r=[],c=0;c<o.childMenu.length;c++){var u=o.childMenu[c];u.id==a&&r.push(u)}r.length!=o.childMenu.length&&(i=!0,this.removeItem(n,o.id))}}i&&this.removeItem(n,t.id);for(var d=[],h=0;h<n.length;h++){var v=n[h];v&&d.push(parseInt(v))}return d},copyItem:function(e){for(var t=[],i=0;i<e.length;i++)t.push(e[i]);return t},removeItem:function(e,t){for(var i=-1,n=0;n<e.length;n++)if(t==e[n]){i=n;break}i>0&&e.splice(i,1)},containItem:function(e,t){for(var i=0;i<e.length;i++)if(t==e[i])return!0;return!1},getUserList:function(e,t){var i=this;e&&(t||(this.currentPage=1),this.userLoading=!0,Object(r.j)({page:this.currentPage,limit:this.pageSize,roleId:e.roleId}).then(function(e){i.tableData=e.data.list,i.total=e.data.totalRow,i.userLoading=!1}).catch(function(){i.userLoading=!1}))},handleSizeChange:function(e){this.pageSize=e,this.getUserList(this.roleActive,!0)},handleCurrentChange:function(e){this.currentPage=e,this.getUserList(this.roleActive,!0)},ruleSubmit:function(){var e=this;this.ruleLoading=!0;for(var t=[],i="",l=0;l<this.ruleMenuList.length;l++){var o=this.ruleMenuList[l];if("tree"==o.type){var s=this.$refs["tree"+o.index];s&&(t="[object Array]"==Object.prototype.toString.call(s)?t.concat(s[0].getCheckedKeys()):t.concat(s.getCheckedKeys()))}else i=o.value}Object(n.h)({rules:t,type:i,id:this.roleActive.roleId,roleName:this.roleActive.roleName}).then(function(t){e.getRoleList(),e.$message.success("编辑成功"),e.ruleLoading=!1}).catch(function(){e.ruleLoading=!1})}}},u=(i("DWe6"),Object(o.a)(c,function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",{staticClass:"role-authorization"},[i("p",{ref:"title",staticClass:"title"},[e._v(" "+e._s(e.title)+" ")]),e._v(" "),i("div",{staticClass:"role-box"},[i("div",{directives:[{name:"loading",rawName:"v-loading",value:e.roleMenuLoading,expression:"roleMenuLoading"}],staticClass:"nav"},[i("div",{staticClass:"nav-new-btn"},[i("el-button",{attrs:{size:"medium"},on:{click:e.newRoleBtn}},[e._v(" 新建角色 ")])],1),e._v(" "),i("div",{staticClass:"role-nav-box"},e._l(e.roleList,function(t,n){return i("div",{key:n,staticClass:"item-list",class:{"item-list-hover":t.roleId==e.roleActive.roleId},on:{click:function(i){e.roleMenuSelect(t)}}},[e._v("\n          "+e._s(t.roleName)+"\n          "),"admin"!=t.remark&&"project"!=t.remark?i("div",{staticClass:"icon-close"},[i("el-dropdown",{attrs:{trigger:"click"},on:{command:e.roleHandleClick}},[i("i",{staticClass:"el-icon-arrow-down",on:{click:function(i){e.roleDropdownClick(t)}}}),e._v(" "),i("el-dropdown-menu",{attrs:{slot:"dropdown"},slot:"dropdown"},[i("el-dropdown-item",{attrs:{command:"copy"}},[e._v("复制")]),e._v(" "),i("el-dropdown-item",{attrs:{command:"edit"}},[e._v("编辑")]),e._v(" "),i("el-dropdown-item",{attrs:{command:"delete"}},[e._v("删除")])],1)],1)],1):e._e()])}))]),e._v(" "),i("el-dialog",{attrs:{title:e.roleTitle,visible:e.newRoleVisible,"before-close":e.newRoleClose,width:"30%"},on:{"update:visible":function(t){e.newRoleVisible=t}}},[i("label",{staticClass:"label-title"},[e._v("角色名称")]),e._v(" "),i("el-input",{staticClass:"input-role",model:{value:e.role.title,callback:function(t){e.$set(e.role,"title",t)},expression:"role.title"}}),e._v(" "),i("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[i("el-button",{attrs:{type:"primary"},on:{click:e.newRoleSubmit}},[e._v("确 定")]),e._v(" "),i("el-button",{on:{click:e.newRoleClose}},[e._v("取 消")])],1)],1),e._v(" "),i("div",{staticClass:"content-box"},[i("el-tabs",{model:{value:e.mainMenuIndex,callback:function(t){e.mainMenuIndex=t},expression:"mainMenuIndex"}},[i("el-tab-pane",{attrs:{label:"角色员工",name:"user"}},[i("div",{directives:[{name:"loading",rawName:"v-loading",value:e.userLoading,expression:"userLoading"}],staticClass:"content-table"},[i("flexbox",{staticClass:"content-table-header"},[i("div",{staticClass:"content-table-header-reminder"},[e.showReminder?i("reminder",{attrs:{content:e.getReminderContent()}}):e._e()],1),e._v(" "),i("el-button",{attrs:{size:"medium",type:"primary"},on:{click:e.addEmployees}},[e._v(" 关联员工 ")])],1),e._v(" "),i("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData,height:e.tableHeight}},[e._l(e.tableList,function(t,n){return i("el-table-column",{key:n,attrs:{prop:t.field,label:t.label,"show-overflow-tooltip":""},scopedSlots:e._u([{key:"header",fn:function(t){return[i("div",{staticClass:"table-head-name"},[e._v(e._s(t.column.label))])]}}])})}),e._v(" "),i("el-table-column",{attrs:{label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[i("span",{staticClass:"el-icon-delete content-table-span",on:{click:function(i){e.delectEmployees(t.row)}}})]}}])})],2),e._v(" "),i("div",{staticClass:"p-contianer"},[i("el-pagination",{staticClass:"p-bar",attrs:{"current-page":e.currentPage,"page-sizes":e.pageSizes,"page-size":e.pageSize,total:e.total,layout:"total, sizes, prev, pager, next, jumper"},on:{"update:pageSize":function(t){e.pageSize=t},"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}})],1)],1)]),e._v(" "),e.roleActive&&e.showRuleSet?i("el-tab-pane",{attrs:{label:"角色权限",name:"rule"}},[i("div",{directives:[{name:"loading",rawName:"v-loading",value:e.ruleLoading,expression:"ruleLoading"}],staticClass:"jurisdiction-box"},[e.roleActive?i("el-button",{staticClass:"jurisdiction-edit",attrs:{size:"medium",type:"primary"},on:{click:e.ruleSubmit}},[e._v(" 保存 ")]):e._e(),e._v(" "),i("el-tabs",{model:{value:e.ruleMenuIndex,callback:function(t){e.ruleMenuIndex=t},expression:"ruleMenuIndex"}},e._l(e.ruleMenuList,function(t,n){return i("el-tab-pane",{key:n,attrs:{label:t.label,name:t.index}},["tree"==t.type?i("div",{staticClass:"jurisdiction-content",style:{height:e.treeHeight+"px"}},[i("div",{staticClass:"jurisdiction-content-checkbox"},[i("el-tree",{ref:"tree"+t.index,refInFor:!0,staticStyle:{height:"0"},attrs:{data:t.data,indent:0,"expand-on-click-node":!1,props:e.defaultProps,"show-checkbox":"","node-key":"menuId","empty-text":"","default-expand-all":""}})],1)]):i("div",{staticClass:"jurisdiction-content"},[i("div",{staticClass:"data-radio"},[i("el-radio-group",{model:{value:t.value,callback:function(i){e.$set(t,"value",i)},expression:"item.value"}},[i("el-radio",{attrs:{label:1}},[e._v("本人")]),e._v(" "),i("el-radio",{attrs:{label:2}},[e._v("本人及下属")]),e._v(" "),i("el-radio",{attrs:{label:3}},[e._v("本部门")]),e._v(" "),i("el-radio",{attrs:{label:4}},[e._v("本部门及下属部门")]),e._v(" "),i("el-radio",{attrs:{label:5}},[e._v("全部")])],1)],1)])])}))],1)]):e._e()],1)],1)],1),e._v(" "),i("relate-empoyee",{attrs:{visible:e.relateEmpoyeeShow,"role-id":e.roleId},on:{"update:visible":function(t){e.relateEmpoyeeShow=t},save:e.employeesSave}})],1)},[],!1,null,"1521ea3d",null));u.options.__file="index.vue";t.default=u.exports},RPWt:function(e,t,i){},fIQt:function(e,t,i){"use strict";var n=i("FkPX");i.n(n).a},kLyV:function(e,t,i){"use strict";var n=i("2SaN");i.n(n).a}}]);