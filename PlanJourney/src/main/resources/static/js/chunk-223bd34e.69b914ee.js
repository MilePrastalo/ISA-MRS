(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-223bd34e"],{"403e":function(t,e,a){"use strict";var n=a("4774"),i=a.n(n);i.a},4774:function(t,e,a){},"7f7f":function(t,e,a){var n=a("86cc").f,i=Function.prototype,r=/^\s*function ([^ (]*)/,o="name";o in i||a("9e1e")&&n(i,o,{configurable:!0,get:function(){try{return(""+this).match(r)[1]}catch(t){return""}}})},e6c6:function(t,e,a){"use strict";a.r(e);var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{attrs:{id:"flightCompanyProfile"}},[a("div",{staticClass:"companyProfile"},[a("br"),a("br"),a("h2",[t._v(" Company information: ")]),a("br"),a("div",{staticClass:"centered"},[a("table",{staticClass:"centered",staticStyle:{"text-align":"left"}},[a("tr",[a("td",[t._v(" Name: ")]),a("td",[a("input",{directives:[{name:"model",rawName:"v-model",value:t.name,expression:"name"}],attrs:{type:"text",name:"name"},domProps:{value:t.name},on:{input:function(e){e.target.composing||(t.name=e.target.value)}}})])]),a("tr",[a("td",[t._v(" Address: ")]),a("td",[a("input",{directives:[{name:"model",rawName:"v-model",value:t.address,expression:"address"}],attrs:{type:"text",name:"address"},domProps:{value:t.address},on:{input:function(e){e.target.composing||(t.address=e.target.value)}}})])]),a("tr",[a("td",[t._v(" Description: ")]),a("td",[a("textarea",{directives:[{name:"model",rawName:"v-model",value:t.description,expression:"description"}],staticStyle:{overflow:"scroll"},attrs:{rows:"5",cols:"22",name:"description"},domProps:{value:t.description},on:{input:function(e){e.target.composing||(t.description=e.target.value)}}})])]),a("tr",[a("td"),a("td",[a("button",{staticClass:"btn btn-primary",on:{click:function(e){return t.updateFlightCompanyProfile()}}},[t._v("Edit")])])])])])])])},i=[],r=(a("7f7f"),a("bc3a")),o=a.n(r),s={name:"flightCompanyProfile",components:{},data:function(){return{name:"",address:"",description:""}},mounted:function(){var t=this,e=function(){return localStorage.getItem("jwtToken")};o.a.defaults.headers.common["Authorization"]="Bearer "+e(),o.a.get("/api/getFlightCompanyProfile").then(function(e){t.name=e.data.name,t.address=e.data.address,t.description=e.data.description})},methods:{updateFlightCompanyProfile:function(){var t=this,e=function(){return localStorage.getItem("jwtToken")};o.a.defaults.headers.post["Authorization"]="Bearer "+e(),o.a.post("/api/updateFlightCompanyProfile",{name:this.name,address:this.address,description:this.description}).then(function(e){t.name=e.data.name,t.address=e.data.address,t.description=e.data.description}),alert("Successfuly updated!")}}},d=s,c=(a("403e"),a("2877")),u=Object(c["a"])(d,n,i,!1,null,null,null);e["default"]=u.exports}}]);
//# sourceMappingURL=chunk-223bd34e.69b914ee.js.map