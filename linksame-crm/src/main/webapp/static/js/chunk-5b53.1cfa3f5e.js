(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-5b53"],{"0e0W":function(t,a,e){"use strict";var i=e("MO4f");e.n(i).a},"J+AY":function(t,a,e){"use strict";e.r(a);var i=e("I4nk"),s=e.n(i),l={name:"Main",components:{},filters:{},data:function(){return{workbenchTableData:[{a:"中核华兴工程有限公司",b:"24",c:"1,284"},{a:"中核华兴工程有限公司",b:"24",c:"1,284"},{a:"中核华兴工程有限公司",b:"24",c:"1,284"}]}},computed:{},mounted:function(){this.initAxis()},methods:{initAxis:function(){var t=s.a.init(document.getElementById("axismain")),a={title:{text:"付款金额对比",textStyle:{fontWeight:"normal",fontSize:16}},color:["#6ca2ff","#6ac9d7","#ff7474"],tooltip:{trigger:"axis",axisPointer:{type:"shadow"}},legend:{data:["计划付款金额","实际付款金额","XXX"],bottom:"0px",itemWidth:14},grid:{top:"80px",left:"20px",right:"20px",bottom:"20px",containLabel:!0,borderColor:"#fff"},xAxis:[{type:"category",data:["1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"],axisTick:{alignWithLabel:!0,lineStyle:{width:0}},axisLabel:{color:"#BDBDBD"},axisLine:{lineStyle:{color:"#BDBDBD"}},splitLine:{show:!1}}],yAxis:[{type:"value",name:"合同金额",data:["1月","2月","3月","4月"],axisTick:{alignWithLabel:!0,lineStyle:{width:0}},axisLabel:{color:"#BDBDBD",formatter:"{value} 元"},axisLine:{lineStyle:{color:"#BDBDBD"}},splitLine:{show:!1}},{type:"value",name:"完成率",axisTick:{alignWithLabel:!0,lineStyle:{width:0}},position:"right",axisLabel:{color:"#BDBDBD",formatter:"{value} %"},axisLine:{lineStyle:{color:"#BDBDBD"}},splitLine:{show:!1}}],series:[{name:"合同金额",type:"bar",yAxisIndex:0,barWidth:15,data:[1,2,3,4,5]},{name:"目标",type:"bar",yAxisIndex:0,barWidth:15,data:[2,3,4,5,6]},{name:"完成率",type:"line",yAxisIndex:1,data:[6,7,8,9,10]}]};t.setOption(a,!0),this.axisOption=a,this.axisChart=t}}},n=(e("0e0W"),e("ZrdR")),o=Object(n.a)(l,function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("div",[e("div",{staticClass:"box"},[e("el-row",{attrs:{gutter:12}},[e("el-col",{attrs:{span:8}},[e("el-card",{attrs:{shadow:"hover"}},[e("div",{staticClass:"logo",staticStyle:{background:"rgb(164,204,100)"}},[e("i",{staticClass:"el-icon-circle-check"})]),t._v(" "),e("div",{staticClass:"content"},[e("div",[t._v(" 合同数量 ")]),t._v(" "),e("span",{staticStyle:{"font-size":"30px"}},[t._v(" 1,236 ")]),t._v(" "),e("span",[t._v(" 个 ")])])])],1),t._v(" "),e("el-col",{attrs:{span:8}},[e("el-card",{attrs:{shadow:"hover"}},[e("div",{staticClass:"logo",staticStyle:{background:"rgb(226,100,115)"}},[e("i",{staticClass:"el-icon-alarm-clock"})]),t._v(" "),e("div",{staticClass:"content"},[e("div",[t._v(" 预计付款金额 ")]),t._v(" "),e("span",{staticStyle:{"font-size":"30px"}},[t._v(" 1,236 ")]),t._v(" "),e("span",[t._v(" 万 ")])])])],1),t._v(" "),e("el-col",{attrs:{span:8}},[e("el-card",{attrs:{shadow:"hover"}},[e("div",{staticClass:"logo",staticStyle:{background:"rgb(205,205,73)"}},[e("i",{staticClass:"el-icon-document-add"})]),t._v(" "),e("div",{staticClass:"content"},[e("div",[t._v(" 总支付累计付款金额 ")]),t._v(" "),e("span",{staticStyle:{"font-size":"30px"}},[t._v(" 1,236 ")]),t._v(" "),e("span",[t._v(" 万 ")])])])],1)],1),t._v(" "),e("div",{staticStyle:{margin:"10px 0 0 0"}},[e("el-row",{attrs:{gutter:12}},[e("el-col",{attrs:{span:16}},[e("el-card",{attrs:{shadow:"hover"}},[e("div",{staticStyle:{width:"600px",height:"300px"},attrs:{id:"axismain"}})])],1),t._v(" "),e("el-col",{attrs:{span:8}},[e("el-card",{attrs:{shadow:"hover"}},[e("div",{staticClass:"table-title"},[e("span",[t._v("参与的企业")]),t._v(" "),e("span",{staticStyle:{float:"right",display:"inline-block"}},[t._v("16 个")])]),t._v(" "),e("el-divider"),t._v(" "),e("el-table",{staticStyle:{width:"100%"},attrs:{data:t.workbenchTableData}},[e("el-table-column",{attrs:{type:"index",label:"排名",width:"80"}}),t._v(" "),e("el-table-column",{attrs:{prop:"a",label:"供应商",width:"180"}}),t._v(" "),e("el-table-column",{attrs:{prop:"b",label:"合同数"}}),t._v(" "),e("el-table-column",{attrs:{prop:"c",label:"合同金额"}})],1)],1)],1)],1)],1)],1)])},[],!1,null,"b8d9cbfa",null);o.options.__file="Main.vue";a.default=o.exports},MO4f:function(t,a,e){}}]);