(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-7f33"],{VYqi:function(t,e,a){"use strict";var i=a("dGmj");a.n(i).a},dGmj:function(t,e,a){},kJzg:function(t,e,a){"use strict";a.r(e);var i=a("I4nk"),n=a.n(i),l=(a("0rNk"),a("qSeR")),o={name:"CustomerAddressStatistics",data:function(){return{loading:!1,allOption:null,dealOption:null,allChart:null,dealChart:null}},computed:{},mounted:function(){this.initAxis(),this.getDataList()},methods:{getDataList:function(){var t=this;this.loading=!0,Object(l.a)().then(function(e){t.loading=!1;for(var a=[],i=[],n=0;n<e.data.length;n++){var l=e.data[n];l.allCustomer&&a.push({name:l.address,value:l.allCustomer}),l.dealCustomer&&i.push({name:l.address,value:l.dealCustomer})}t.allOption.series[0].data=a,t.dealOption.series[0].data=i,t.allChart.setOption(t.allOption,!0),t.dealChart.setOption(t.dealOption,!0)}).catch(function(){t.loading=!1})},initAxis:function(){this.allChart=n.a.init(document.getElementById("allChart")),this.dealChart=n.a.init(document.getElementById("dealChart")),this.allOption=this.getChartOptione("全部客户"),this.dealOption=this.getChartOptione("成交客户"),this.allChart.setOption(this.allOption,!0),this.dealChart.setOption(this.dealOption,!0)},getChartOptione:function(t){return{title:{text:t,left:"center",bottom:0},tooltip:{trigger:"item",formatter:function(t){if(t.value)return t.name+"<br/>"+(t.value||"-")+"（个）"}},legend:{orient:"vertical",left:"left",data:["客户数"]},visualMap:{min:0,max:50,left:"left",top:"bottom",text:["多","少"],calculable:!0,inRange:{color:["lightskyblue","yellow","orangered"]}},toolbox:{show:!0,orient:"vertical",left:"right",top:"center",feature:{mark:{show:!0},dataView:{show:!0,readOnly:!1,optionToContent:function(t){for(var e=t.series[0].data,a='<table style="width:100%;text-align:center"><tbody><tr><td>城市</td><td>客户数</td></tr>',i=0,n=e.length;i<n;i++)a+="<tr><td>"+e[i].name+"</td><td>"+e[i].value+"</td></tr>";return a+="</tbody></table>"}},restore:{show:!0},saveAsImage:{show:!0}}},series:[{name:"",type:"map",mapType:"china",showLegendSymbol:!1,itemStyle:{normal:{label:{show:!0},borderColor:"#ccc"},emphasis:{label:{show:!0}}},data:[]}]}}}},s=(a("VYqi"),a("ZrdR")),r=Object(s.a)(o,function(){var t=this.$createElement,e=this._self._c||t;return e("div",{directives:[{name:"loading",rawName:"v-loading",value:this.loading,expression:"loading"}],staticClass:"main-container"},[e("flexbox",{staticClass:"content"},[e("flexbox-item",[e("div",{staticClass:"axis-content"},[e("div",{staticClass:"axismain",attrs:{id:"allChart"}})])]),this._v(" "),e("flexbox-item",[e("div",{staticClass:"axis-content"},[e("div",{staticClass:"axismain",attrs:{id:"dealChart"}})])])],1)],1)},[],!1,null,"637b3664",null);r.options.__file="CustomerAddressStatistics.vue";e.default=r.exports},qSeR:function(t,e,a){"use strict";a.d(e,"a",function(){return n}),a.d(e,"b",function(){return l});var i=a("t3Un");function n(t){return Object(i.a)({url:"biRanking/addressAnalyse",method:"post",data:t})}function l(t){var e=t.type_analyse;return"industry"===e?Object(i.a)({url:"biRanking/portrait",method:"post",data:t}):"level"===e?Object(i.a)({url:"biRanking/portraitLevel",method:"post",data:t}):"source"===e?Object(i.a)({url:"biRanking/portraitSource",method:"post",data:t}):void 0}}}]);