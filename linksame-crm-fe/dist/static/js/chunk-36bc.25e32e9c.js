(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-36bc"],{OnVn:function(e,t,a){"use strict";var o=a("dcn9");a.n(o).a},SUos:function(e,t,a){"use strict";a.r(t);var o=a("QbLZ"),l=a.n(o),r=a("S/HW"),n={name:"DialogPayment",components:{},props:{status:{type:Boolean,default:void 0},data:{type:Object,default:void 0}},data:function(){return{tableData:[{date:"2016-05-03",address:"设计清单"}],dialogVisible:!1,dialogVisibleMilestone:!1,form:{contractId:1,receivableType:"",customerId:1,receivableAmount:void 0,remark:"",state:"1",paymentMethod:"支付收款",collectingCompany:"收款公司名",collectionBank:"收款开户行",collectionAccount:"收款账户",collectingTime:"",agent:"2",batchId:"批次号"}}},computed:{},mounted:function(){console.log("rebateDetailData",this.data),void 0!=this.data&&(this.form={receivableRecordsId:this.data.receivableRecordsId,contractId:this.data.contractId,receivableType:this.data.receivableType,customerId:this.data.customerId,receivableAmount:parseInt(this.data.receivableAmount),remark:this.data.remark,state:this.data.state,paymentMethod:this.data.paymentMethod,collectingCompany:this.data.collectingCompany,collectionBank:this.data.collectionBank,collectionAccount:this.data.collectionAccount,collectingTime:this.data.collectingTime,agent:this.data.agent,batchId:this.data.batchId})},methods:{createContact:function(){var e=this;console.log(this.form),Object(r.a)(l()({},this.form)).then(function(t){console.log(t),e.$message({type:"success",message:"添加成功!"}),e.$router.push("/contract/rebate")}).catch(function(e){console.log(e)})}}},i=(a("OnVn"),a("KHd+")),c=Object(i.a)(n,function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-divider",{attrs:{"content-position":"left"}},[e._v("账户信息")]),e._v(" "),a("el-form",{ref:"form",attrs:{inline:!0}},[a("el-form-item",{attrs:{label:"收款账户："}},[e._v(e._s("机器人公司"))]),e._v(" "),a("el-form-item",{attrs:{label:"开户行："}},[e._v(e._s("中国银行"))]),e._v(" "),a("el-form-item",{attrs:{label:"收款账号："}},[e._v(e._s("44353401040012174"))])],1),e._v(" "),a("el-divider",{attrs:{"content-position":"left"}},[e._v("回款信息")]),e._v(" "),a("el-form",{ref:"form",attrs:{inline:!0}},[a("el-form-item",{attrs:{label:"款项："}},[a("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.form.receivableType,callback:function(t){e.$set(e.form,"receivableType",t)},expression:"form.receivableType"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"客户："}},[a("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.form.customerId,callback:function(t){e.$set(e.form,"customerId",t)},expression:"form.customerId"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"回款时间："}},[a("el-date-picker",{attrs:{type:"date","value-format":"yyyy-MM-dd"},model:{value:e.form.collectingTime,callback:function(t){e.$set(e.form,"collectingTime",t)},expression:"form.collectingTime"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"回款金额："}},[a("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.form.receivableAmount,callback:function(t){e.$set(e.form,"receivableAmount",e._n(t))},expression:"form.receivableAmount"}},[a("template",{slot:"prepend"},[e._v("￥")])],2)],1),e._v(" "),a("el-form-item",{attrs:{label:"付款方式："}},[a("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.form.paymentMethod,callback:function(t){e.$set(e.form,"paymentMethod",t)},expression:"form.paymentMethod"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"经办人："}},[a("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.form.agent,callback:function(t){e.$set(e.form,"agent",t)},expression:"form.agent"}})],1),e._v(" "),a("div",[a("el-form-item",{attrs:{label:"备注："}},[a("el-input",{attrs:{autosize:{minRows:2,maxRows:4},type:"textarea",placeholder:"请输入内容"},model:{value:e.form.remark,callback:function(t){e.$set(e.form,"remark",t)},expression:"form.remark"}})],1)],1),e._v(" "),a("el-divider",{attrs:{"content-position":"left"}},[e._v("关联业务")])],1)],1)},[],!1,null,"9bda4190",null);c.options.__file="DialogCreate.vue";t.default=c.exports},dcn9:function(e,t,a){}}]);