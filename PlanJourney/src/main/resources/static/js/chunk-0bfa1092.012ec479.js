(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-0bfa1092"],{"24d9":function(e,a,t){"use strict";var s=t("bebf"),r=t.n(s);r.a},bebf:function(e,a,t){},c3e7:function(e,a,t){"use strict";t.r(a);var s=function(){var e=this,a=e.$createElement,t=e._self._c||a;return t("div",{attrs:{id:"hotelAdminProfile"}},[t("table",[t("tr",[t("td",[e._v(" First name: ")]),t("td",[t("input",{directives:[{name:"model",rawName:"v-model",value:e.firstName,expression:"firstName"}],attrs:{type:"text",name:"firstName"},domProps:{value:e.firstName},on:{input:function(a){a.target.composing||(e.firstName=a.target.value)}}})])]),t("tr",[t("td",[e._v(" Last name: ")]),t("td",[t("input",{directives:[{name:"model",rawName:"v-model",value:e.lastName,expression:"lastName"}],attrs:{type:"text",name:"lastName"},domProps:{value:e.lastName},on:{input:function(a){a.target.composing||(e.lastName=a.target.value)}}})])]),t("tr",[t("td",[e._v(" Email: ")]),t("td",[t("input",{directives:[{name:"model",rawName:"v-model",value:e.email,expression:"email"}],attrs:{type:"text",name:"email"},domProps:{value:e.email},on:{input:function(a){a.target.composing||(e.email=a.target.value)}}})])]),t("tr",[t("td",[e._v(" Password: ")]),t("td",[t("input",{directives:[{name:"model",rawName:"v-model",value:e.password,expression:"password"}],attrs:{type:"password",name:"password"},domProps:{value:e.password},on:{input:function(a){a.target.composing||(e.password=a.target.value)}}})])]),t("tr",[t("td",[e._v(" Confirm password: ")]),t("td",[t("input",{directives:[{name:"model",rawName:"v-model",value:e.repeatedPassword,expression:"repeatedPassword"}],attrs:{type:"password",name:"password"},domProps:{value:e.repeatedPassword},on:{input:function(a){a.target.composing||(e.repeatedPassword=a.target.value)}}})])]),t("tr",[t("td"),t("td",[t("button",{on:{click:function(a){return e.updateHotelAdminProfile(e.password,e.repeatedPassword)}}},[e._v("Edit")])])])])])},r=[],o=t("bc3a"),i=t.n(o),d={name:"hotelAdminProfile",components:{},data:function(){return{firstName:"",lastName:"",email:"",password:"",repeatedPassword:""}},mounted:function(){var e=this;i.a.get("/api/getHotelAdmin").then(function(a){e.firstName=a.data.firstName,e.lastName=a.data.lastName,e.email=a.data.email,e.password=a.data.password,e.repeatedPassword=e.password})},methods:{updateHotelAdminProfile:function(e,a){var t=this;e==a?(i.a.post("/api/updateHotelAdminProfile",{firstName:this.firstName,lastName:this.lastName,email:this.email,password:this.password}).then(function(e){t.firstName=e.data.firstName,t.lastName=e.data.lastName,t.email=e.data.email,t.password=e.data.password,t.repeatedPassword=t.password}),alert("Your profile has been successfuly updated!")):alert("Not valid confirmation of the password!")}}},n=d,m=(t("24d9"),t("2877")),l=Object(m["a"])(n,s,r,!1,null,null,null);a["default"]=l.exports}}]);
//# sourceMappingURL=chunk-0bfa1092.012ec479.js.map