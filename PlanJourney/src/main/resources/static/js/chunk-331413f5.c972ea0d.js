(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-331413f5"],{5524:function(t,e,a){"use strict";var s=a("55a1"),n=a.n(s);n.a},"55a1":function(t,e,a){},"66be":function(t,e,a){},7315:function(t,e,a){"use strict";a.r(e);var s=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{attrs:{id:"userProfile"}},[a("navbar",{attrs:{itype:"1"}}),a("div",{staticClass:"profile"},[a("br"),a("br"),a("h2",[t._v(" Your profile: ")]),a("br"),a("div",{staticClass:"container"},[a("table",{staticClass:"centered",staticStyle:{"text-align":"left"}},[a("tr",[a("td",[t._v(" First name: ")]),a("td",[a("input",{directives:[{name:"model",rawName:"v-model",value:t.firstName,expression:"firstName"}],attrs:{type:"text",name:"firstName"},domProps:{value:t.firstName},on:{input:function(e){e.target.composing||(t.firstName=e.target.value)}}})])]),a("tr",[a("td",[t._v(" Last name: ")]),a("td",[a("input",{directives:[{name:"model",rawName:"v-model",value:t.lastName,expression:"lastName"}],attrs:{type:"text",name:"lastName"},domProps:{value:t.lastName},on:{input:function(e){e.target.composing||(t.lastName=e.target.value)}}})])]),a("tr",[a("td",[t._v(" Email: ")]),a("td",[a("input",{directives:[{name:"model",rawName:"v-model",value:t.email,expression:"email"}],attrs:{type:"text",name:"email"},domProps:{value:t.email},on:{input:function(e){e.target.composing||(t.email=e.target.value)}}})])]),t.isRegistered?a("tr",[a("td",[t._v(" City: ")]),a("td",[a("input",{directives:[{name:"model",rawName:"v-model",value:t.city,expression:"city"}],attrs:{type:"text",name:"city"},domProps:{value:t.city},on:{input:function(e){e.target.composing||(t.city=e.target.value)}}})])]):t._e(),t.isRegistered?a("tr",[a("td",[t._v(" Phone: ")]),a("td",[a("input",{directives:[{name:"model",rawName:"v-model",value:t.phone,expression:"phone"}],attrs:{type:"text",name:"phone"},domProps:{value:t.phone},on:{input:function(e){e.target.composing||(t.phone=e.target.value)}}})])]):t._e(),a("tr",[a("td",[t._v(" Password: ")]),a("td",[a("input",{directives:[{name:"model",rawName:"v-model",value:t.password,expression:"password"}],attrs:{type:"password",name:"password"},domProps:{value:t.password},on:{input:function(e){e.target.composing||(t.password=e.target.value)}}})])]),a("tr",[a("td",[t._v(" Confirm password: ")]),a("td",[a("input",{directives:[{name:"model",rawName:"v-model",value:t.repeatedPassword,expression:"repeatedPassword"}],attrs:{name:"password"},domProps:{value:t.repeatedPassword},on:{input:function(e){e.target.composing||(t.repeatedPassword=e.target.value)}}})])]),a("tr",[a("td"),a("td",[a("button",{staticClass:"btn btn-primary",on:{click:function(e){return t.updateUserProfile(t.password,t.repeatedPassword)}}},[t._v("Edit")])])])])])])],1)},n=[],i=a("9d8d"),o=a("bc3a"),r=a.n(o),l={name:"userProfile",components:{navbar:i["a"]},data:function(){return{firstName:"",lastName:"",email:"",password:"",repeatedPassword:"",city:"",phone:"",isRegistered:!1}},mounted:function(){var t=this,e=function(){return localStorage.getItem("jwtToken")};r.a.defaults.headers.common["Authorization"]="Bearer "+e(),r.a.get("/api/getLogUser").then(function(e){t.firstName=e.data.firstName,t.lastName=e.data.lastName,t.email=e.data.email,t.city=e.data.city,t.phone=e.data.phone}).catch(function(t){t.response&&console.log(t.response.data)}),r.a.get("/api/getUserRole").then(function(e){"REGISTERED"==e.data?t.isRegistered=!0:t.isRegistered=!1}).catch(function(t){t.response&&console.log(t.response.data)})},methods:{updateUserProfile:function(t,e){var a=this;if(t==e){var s=function(){return localStorage.getItem("jwtToken")};r.a.defaults.headers.common["Authorization"]="Bearer "+s(),r.a.post("/api/updateUserProfile",{firstName:this.firstName,lastName:this.lastName,email:this.email,password:this.password,repeatedPass:this.password,city:this.city,phone:this.phone}).then(function(t){a.firstName=t.data.firstName,a.lastName=t.data.lastName,a.email=t.data.email,a.city=t.data.city,a.phone=t.data.phone}),alert("Your profile has been successfuly updated!")}else alert("Not valid confirmation of the password!")}}},d=l,c=(a("5524"),a("2877")),m=Object(c["a"])(d,s,n,!1,null,null,null);e["default"]=m.exports},"9d8d":function(t,e,a){"use strict";var s=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{attrs:{id:"navbar"}},[a("nav",{staticClass:"navbar navbar-expand-lg bg-dark navbar-dark static-top"},[a("div",{staticClass:"container"},[t._m(0),t._m(1),a("div",{staticClass:"collapse navbar-collapse",attrs:{id:"navbarResponsive"}},[a("ul",{staticClass:"navbar-nav ml-auto"},[0!=t.type?a("li",{staticClass:"nav-item active"},[a("a",{staticClass:"nav-link",attrs:{href:t.homePage}},[t._v("Home\n                        "),a("span",{staticClass:"sr-only"},[t._v("(current)")])])]):t._e(),0!=t.type?a("li",{staticClass:"nav-item"},[a("a",{staticClass:"nav-link",attrs:{href:"/front/userProfile"}},[t._v("Profile")])]):t._e(),0!=t.type&&t.invites?a("li",{staticClass:"nav-item"},[a("a",{staticClass:"nav-link",attrs:{href:"/front/invites"}},[t._v("Invites")])]):t._e(),0!=t.type?a("li",{staticClass:"nav-item"},[a("a",{staticClass:"nav-link",on:{click:t.logout}},[t._v("Logout")])]):t._e(),0==t.type?a("li",{staticClass:"nav-item active"},[a("a",{staticClass:"nav-link",on:{click:t.goToLogin}},[t._v("Login\n                        "),a("span",{staticClass:"sr-only"},[t._v("(current)")])])]):t._e(),0==t.type?a("li",{staticClass:"nav-item"},[a("a",{staticClass:"nav-link",attrs:{href:"/front/registerPage"}},[t._v("Register")])]):t._e()])])])])])},n=[function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("a",{staticClass:"navbar-brand",attrs:{href:"#"}},[a("img",{attrs:{src:"http://placehold.it/150x50?text=Logo",alt:""}})])},function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("button",{staticClass:"navbar-toggler",attrs:{type:"button","data-toggle":"collapse","data-target":"#navbarResponsive","aria-controls":"navbarResponsive","aria-expanded":"false","aria-label":"Toggle navigation"}},[a("span",{staticClass:"navbar-toggler-icon"})])}],i=a("bc3a"),o=a.n(i),r={name:"navbar",data:function(){return{type:0,homePage:"",invites:!1}},mounted:function(){var t=this;console.log(this.type);var e=function(){return localStorage.getItem("jwtToken")};o.a.defaults.headers.common["Authorization"]="Bearer "+e(),o.a.get("/api/getUserRole").then(function(e){console.log(e.data),t.type=1,console.log(e),"RENT_ADMIN"==e.data?t.homePage="/front/RAindex":"FLIGHT_ADMIN"==e.data?t.homePage="/front/flightAdmin":"SYS_ADMIN"==e.data?t.homePage="/front/systemAdminPage":"REGISTERED"==e.data?(t.homePage="/front/index",t.invites=!0):"HOTEL_ADMIN"==e.data?t.homePage="/front/hotelAdminPage":(t.homePage="/",t.type=0)})},methods:{logout:function(){localStorage.setItem("jwtToken",""),window.location="/"},goToLogin:function(){this.$router.push("/front/loginPage")}}},l=r,d=(a("e056"),a("2877")),c=Object(d["a"])(l,s,n,!1,null,"434d11d6",null);e["a"]=c.exports},e056:function(t,e,a){"use strict";var s=a("66be"),n=a.n(s);n.a}}]);
//# sourceMappingURL=chunk-331413f5.c972ea0d.js.map