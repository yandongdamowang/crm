(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-66f1"],{"/VHx":function(t,a,e){"use strict";var i=e("cS1D");e.n(i).a},"J+AY":function(t,a,e){"use strict";e.r(a);var i=e("MT78"),s=e.n(i),n=e("SP+9"),o={name:"Main",components:{},filters:{},data:function(){return{headerGrep:{datetime:["2020-01-01","2020-05-29"],project:"",type:[]},contractDashboardData:{},workbenchTableData:[{a:"中核华兴工程有限公司",b:"24",c:"1,284"},{a:"中核华兴工程有限公司",b:"24",c:"1,284"},{a:"中核华兴工程有限公司",b:"24",c:"1,284"}]}},computed:{},watch:{datetime:function(t){this.retriveContractDashboard()}},mounted:function(){this.$store.commit("SET_HEADERNAME","仪表盘"),this.retriveContractDashboard(),this.initAxis()},methods:{retriveContractDashboard:function(){var t=this;Object(n.e)({startTime:this.headerGrep.datetime[0],endTime:this.headerGrep.datetime[1]}).then(function(a){console.log(a),t.contractDashboardData=a}).catch(function(){})},initAxis:function(){var t=s.a.init(document.getElementById("axismain")),a={title:{text:"付款金额对比",textStyle:{fontWeight:"normal",fontSize:16}},color:["#6ca2ff","#6ac9d7","#ff7474"],tooltip:{trigger:"axis",axisPointer:{type:"shadow"}},legend:{data:["计划付款金额","实际付款金额","XXX"],bottom:"0px",itemWidth:14},grid:{top:"80px",left:"20px",right:"20px",bottom:"20px",containLabel:!0,borderColor:"#fff"},xAxis:[{type:"category",data:["1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"],axisTick:{alignWithLabel:!0,lineStyle:{width:0}},axisLabel:{color:"#BDBDBD"},axisLine:{lineStyle:{color:"#BDBDBD"}},splitLine:{show:!1}}],yAxis:[{type:"value",name:"合同金额",data:["1月","2月","3月","4月"],axisTick:{alignWithLabel:!0,lineStyle:{width:0}},axisLabel:{color:"#BDBDBD",formatter:"{value} 元"},axisLine:{lineStyle:{color:"#BDBDBD"}},splitLine:{show:!1}},{type:"value",name:"完成率",axisTick:{alignWithLabel:!0,lineStyle:{width:0}},position:"right",axisLabel:{color:"#BDBDBD",formatter:"{value} %"},axisLine:{lineStyle:{color:"#BDBDBD"}},splitLine:{show:!1}}],series:[{name:"合同金额",type:"bar",yAxisIndex:0,barWidth:15,data:[1,2,3,4,5]},{name:"目标",type:"bar",yAxisIndex:0,barWidth:15,data:[2,3,4,5,6]},{name:"完成率",type:"line",yAxisIndex:1,data:[6,7,8,9,10]}]};t.setOption(a,!0),this.axisOption=a,this.axisChart=t}}},r=(e("/VHx"),e("KHd+")),l=Object(r.a)(o,function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("div",{staticStyle:{height:"100%"}},[e("div",{staticClass:"ls-header"},[t._v(" 仪表盘\n    "),e("div",{staticClass:"ls-header-r"},[e("el-date-picker",{attrs:{type:"daterange","start-placeholder":"开始日期","end-placeholder":"结束日期","value-format":"yyyy-MM-dd"},model:{value:t.headerGrep.datetime,callback:function(a){t.$set(t.headerGrep,"datetime",a)},expression:"headerGrep.datetime"}})],1),t._v(" "),e("div",{staticClass:"ls-header-r"},[e("el-radio-group",{model:{value:t.headerGrep.type,callback:function(a){t.$set(t.headerGrep,"type",a)},expression:"headerGrep.type"}},[e("el-radio-button",{attrs:{label:"今日"}},[t._v("今日")]),t._v(" "),e("el-radio-button",{attrs:{label:"本周"}}),t._v(" "),e("el-radio-button",{attrs:{label:"本月"}}),t._v(" "),e("el-radio-button",{attrs:{label:"全年"}})],1)],1),t._v(" "),e("div",{staticClass:"ls-header-r"},[e("el-select",{attrs:{placeholder:"选择项目"},model:{value:t.headerGrep.project,callback:function(a){t.$set(t.headerGrep,"project",a)},expression:"headerGrep.project"}},[e("el-option",{attrs:{label:"项目一",value:"shanghai"}}),t._v(" "),e("el-option",{attrs:{label:"项目二",value:"beijing"}})],1)],1)]),t._v(" "),e("div",{staticClass:"ls-box"},[e("el-row",{attrs:{gutter:12}},[e("el-col",{attrs:{span:24}},[e("el-card",{attrs:{shadow:"hover"}},[e("div",{staticClass:"ls-datashow"},[e("span",{staticStyle:{"font-size":"28px"}},[t._v("数据概览")])]),t._v(" "),e("div",{staticClass:"content"},[e("span",{staticClass:"logo",staticStyle:{color:"rgb(164,204,100)"}},[e("i",{staticClass:"el-icon-document-copy"})]),t._v(" "),e("span",{staticStyle:{"font-size":"26px"}},[t._v(" "+t._s(t.contractDashboardData.contractCount)+" ")]),t._v(" "),e("span",[t._v(" 个 ")]),t._v(" "),e("span",{staticStyle:{"font-size":"14px"}},[t._v(" 总合同数 ")])]),t._v(" "),e("div",{staticClass:"content"},[e("span",{staticClass:"logo",staticStyle:{color:"rgb(0,183,255)"}},[e("i",{staticClass:"el-icon-document-checked"})]),t._v(" "),e("span",{staticStyle:{"font-size":"30px"}},[t._v(" "+t._s(t.contractDashboardData.contractCount))]),t._v(" "),e("span",[t._v(" 个 ")]),t._v(" "),e("span",{staticStyle:{"font-size":"14px"}},[t._v(" 完成合同数 ")])]),t._v(" "),e("div",{staticClass:"content"},[e("span",{staticClass:"logo",staticStyle:{color:"rgb(226,100,115)"}},[e("i",{staticClass:"el-icon-money"})]),t._v(" "),e("span",{staticStyle:{"font-size":"30px"}},[t._v(" "+t._s(t.contractDashboardData.waitPayment)+" ")]),t._v(" "),e("span",[t._v(" 元 ")]),t._v(" "),e("span",{staticStyle:{"font-size":"14px"}},[t._v(" 预计付款金额 ")])]),t._v(" "),e("div",{staticClass:"content"},[e("span",{staticClass:"logo",staticStyle:{color:"rgb(205,205,73)"}},[e("i",{staticClass:"el-icon-bank-card"})]),t._v(" "),e("span",{staticStyle:{"font-size":"30px"}},[t._v(" "+t._s(t.contractDashboardData.cumulativePayment))]),t._v(" "),e("span",[t._v(" 元 ")]),t._v(" "),e("span",{staticStyle:{"font-size":"14px"}},[t._v(" 总支付累计付款金额 ")])])])],1)],1),t._v(" "),e("div",{staticStyle:{margin:"10px 0 0 0"}},[e("el-row",{attrs:{gutter:12}},[e("el-col",{attrs:{span:16}},[e("el-card",{attrs:{shadow:"hover"}},[e("div",{staticStyle:{width:"600px",height:"300px"},attrs:{id:"axismain"}})])],1),t._v(" "),e("el-col",{attrs:{span:8}},[e("el-card",{attrs:{shadow:"hover"}},[e("div",{staticClass:"table-title"},[e("span",[t._v("参与的企业")]),t._v(" "),e("span",{staticStyle:{float:"right",display:"inline-block"}},[t._v("16 个")])]),t._v(" "),e("el-divider"),t._v(" "),e("el-table",{staticStyle:{width:"100%"},attrs:{data:t.workbenchTableData}},[e("el-table-column",{attrs:{type:"index",label:"排名",width:"80"}}),t._v(" "),e("el-table-column",{attrs:{prop:"a",label:"供应商",width:"180"}}),t._v(" "),e("el-table-column",{attrs:{prop:"b",label:"合同数"}}),t._v(" "),e("el-table-column",{attrs:{prop:"c",label:"合同金额"}})],1)],1)],1)],1)],1)],1)])},[],!1,null,"abfa6b20",null);l.options.__file="Main.vue";a.default=l.exports},"SP+9":function(t,a,e){"use strict";e.d(a,"d",function(){return s}),e.d(a,"c",function(){return n}),e.d(a,"b",function(){return o}),e.d(a,"a",function(){return r}),e.d(a,"f",function(){return l}),e.d(a,"e",function(){return c});var i=e("t3Un");function s(t){return Object(i.a)({url:"pmpContract/queryList",method:"post",data:t,headers:{"Content-Type":"application/json;charset=UTF-8"}})}function n(t){return Object(i.a)({url:"pmpContract/queryById",method:"post",data:t})}function o(t){return Object(i.a)({url:"pmpContract/delete",method:"post",data:t})}function r(t){return Object(i.a)({url:"pmpContract/add",method:"post",data:t,headers:{"Content-Type":"application/json;charset=UTF-8"}})}function l(t){return Object(i.a)({url:"pmpContractPayment/queryPaymentByMonthly",method:"post",data:t})}function c(t){return Object(i.a)({url:"pmpContract/contractDashboard",method:"post",data:t})}},cS1D:function(t,a,e){}}]);