(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-4f7d3238"],{"0cba":function(e,t,a){"use strict";a.r(t);var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"addVehicle"}},[a("form",{attrs:{action:""}},[a("div",{staticClass:"form-label-group"},[a("label",[e._v("Vehicle name")]),a("input",{directives:[{name:"model",rawName:"v-model",value:e.name,expression:"name"}],attrs:{type:"text",required:"",autofocus:""},domProps:{value:e.name},on:{input:function(t){t.target.composing||(e.name=t.target.value)}}})]),a("div",{staticClass:"form-label-group"},[a("label",[e._v("Maker ")]),a("select",{directives:[{name:"model",rawName:"v-model",value:e.maker,expression:"maker"}],attrs:{required:""},on:{change:function(t){var a=Array.prototype.filter.call(t.target.options,function(e){return e.selected}).map(function(e){var t="_value"in e?e._value:e.value;return t});e.maker=t.target.multiple?a:a[0]}}},e._l(e.makers,function(t){return a("option",{key:t.id,domProps:{value:t}},[e._v(e._s(t))])}),0)]),a("div",{staticClass:"form-label-group"},[a("label",[e._v("Type")]),a("select",{directives:[{name:"model",rawName:"v-model",value:e.type,expression:"type"}],attrs:{name:"",id:"",required:""},on:{change:function(t){var a=Array.prototype.filter.call(t.target.options,function(e){return e.selected}).map(function(e){var t="_value"in e?e._value:e.value;return t});e.type=t.target.multiple?a:a[0]}}},e._l(e.types,function(t){return a("option",{key:t.id,domProps:{value:t}},[e._v(e._s(t))])}),0)]),a("div",{staticClass:"form-label-group"},[a("label",[e._v("Price")]),a("input",{directives:[{name:"model",rawName:"v-model",value:e.price,expression:"price"}],attrs:{type:"number",required:""},domProps:{value:e.price},on:{input:function(t){t.target.composing||(e.price=t.target.value)}}})]),a("div",{staticClass:"form-label-group"},[a("label",[e._v("Year")]),a("input",{directives:[{name:"model",rawName:"v-model",value:e.year,expression:"year"}],attrs:{type:"number",required:""},domProps:{value:e.year},on:{input:function(t){t.target.composing||(e.year=t.target.value)}}})]),a("div",{staticClass:"form-label-group"},[a("label",[e._v("Date from")]),a("input",{directives:[{name:"model",rawName:"v-model",value:e.datefrom,expression:"datefrom"}],attrs:{type:"date",required:""},domProps:{value:e.datefrom},on:{input:function(t){t.target.composing||(e.datefrom=t.target.value)}}})]),a("div",{staticClass:"form-label-group"},[a("label",[e._v("Date to")]),a("input",{directives:[{name:"model",rawName:"v-model",value:e.dateto,expression:"dateto"}],attrs:{type:"date",required:""},domProps:{value:e.dateto},on:{input:function(t){t.target.composing||(e.dateto=t.target.value)}}})]),a("div",{staticClass:"form-label-group"},[a("button",{on:{click:e.add}},[e._v("Add")])])])])},o=[],n=(a("7f7f"),a("bc3a")),i=a.n(n),l={name:"addvehicle",components:{},data:function(){return{name:"",maker:"",type:"",price:"",year:"",dateto:"",datefrom:"",makers:[],types:[]}},mounted:function(){var e=this,t=function(){return localStorage.getItem("jwtToken")};i.a.defaults.headers.common["Authorization"]="Bearer "+t(),i.a.get("/api/getProducers").then(function(t){e.makers=t.data}),i.a.get("/api/getTypes").then(function(t){e.types=t.data})},methods:{add:function(e){e.preventDefault();var t=function(){return localStorage.getItem("jwtToken")};i.a.defaults.headers.common["Authorization"]="Bearer "+t(),i.a.post("/api/addCar",{name:this.name,maker:this.maker,type:this.type,year:this.year,price:this.price,dateFrom:this.datefrom,dateTo:this.dateto}).then(function(){alert("Vehicle has been added"),this.$emit("added")})}}},u=l,s=(a("5187"),a("2877")),d=Object(s["a"])(u,r,o,!1,null,null,null);t["default"]=d.exports},"40e2":function(e,t,a){},5187:function(e,t,a){"use strict";var r=a("40e2"),o=a.n(r);o.a},"7f7f":function(e,t,a){var r=a("86cc").f,o=Function.prototype,n=/^\s*function ([^ (]*)/,i="name";i in o||a("9e1e")&&r(o,i,{configurable:!0,get:function(){try{return(""+this).match(n)[1]}catch(e){return""}}})}}]);
//# sourceMappingURL=chunk-4f7d3238.ca7c7513.js.map