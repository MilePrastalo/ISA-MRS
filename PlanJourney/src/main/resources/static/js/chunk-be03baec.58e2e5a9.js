(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-be03baec"],{1861:function(t,a,e){"use strict";e.r(a);var i=function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("div",{attrs:{id:"flightDetails"}},[e("navbar"),e("div",{staticClass:"container"},[e("br"),e("h2",[t._v("Flight details: ")]),e("br"),e("br"),e("br"),e("div",{staticClass:"row"},[e("div",{staticClass:"col"},[e("table",{staticClass:"centered",staticStyle:{"text-align":"left"}},[e("tr",[e("td",[t._v("Flight company: ")]),e("td",[t._v(" "+t._s(t.flight.flightCompany))])]),e("tr",[e("td",[t._v("From: ")]),e("td",[t._v(" "+t._s(t.flight.startDestination))])]),e("tr",[e("td",[t._v("To: ")]),e("td",[t._v(" "+t._s(t.flight.endDestination))])]),e("tr",[e("td",[t._v("Start date: ")]),e("td",[t._v(" "+t._s(t.flight.startDate_str))])]),e("tr",[e("td",[t._v("End date: ")]),e("td",[t._v(" "+t._s(t.flight.endDate_str))])]),e("tr",[e("td",[t._v("Flight duration: ")]),e("td",[t._v(" "+t._s(t.flight.flightDuration))])]),e("tr",[e("td",[t._v("Flight length: ")]),e("td",[t._v(" "+t._s(t.flight.flightLength))])]),e("tr",[e("td",[t._v("Transitions: ")]),e("td",[t._v(" "+t._s(t.flight.transitions))])]),e("tr",[e("td",[t._v("Additional Services: ")]),e("td",[t._v(" "+t._s(t.flight.additionalServices))])]),e("tr",[e("td",[t._v("Eco class price: ")]),e("td",[t._v(" "+t._s(t.flight.economicPrice))])]),e("tr",[e("td",[t._v("Business class price: ")]),e("td",[t._v(" "+t._s(t.flight.businessPrice))])]),e("tr",[e("td",[t._v("First class price: ")]),e("td",[t._v(" "+t._s(t.flight.firstClassPrice))])]),e("tr",[e("td",["REGISTERED"==t.role?e("button",{staticClass:"btn btn-primary",on:{click:function(a){return t.makeReservation(t.flight.id)}}},[t._v("Make reservation")]):t._e()])]),e("br"),e("br"),e("button",{staticClass:"btn btn-outline-success",on:{click:t.goBack}},[t._v("Back")])])]),e("div",{staticClass:"col"},[e("yandex-map",{staticStyle:{width:"350px",height:"280px"},attrs:{coords:[this.flight.latitude,this.flight.longitude],zoom:"14",controlss:["zoomControl"],"map-type":"hybrid"}},[e("ymap-marker",{attrs:{"marker-id":"1","marker-type":"placemark",coords:[this.flight.latitude,this.flight.longitude],"marker-fill":{color:"#0E4779",opacity:.5},"marker-stroke":{color:"#0E4779",width:4}}})],1)],1)])])],1)},s=[],n=e("9d8d"),r=e("bc3a"),o=e.n(r),l={name:"flightDetails",components:{navbar:n["a"]},data:function(){return{role:"",flight:{}}},mounted:function(){var t=this,a=function(){return localStorage.getItem("jwtToken")};o.a.defaults.headers.common["Authorization"]="Bearer "+a(),o.a.get("/api/getUserRole").then(function(a){t.role=a.data});var e=localStorage.getItem("flightID");o.a.get("/api/getFlight/"+e).then(function(a){t.flight=a.data})},methods:{makeReservation:function(t){localStorage.setItem("flightID",t),this.$router.push("/front/flightReservation")},goBack:function(){"REGISTERED"!=this.role?this.$router.push("/"):this.$router.push("/front/index")}}},c=l,d=(e("2078"),e("2877")),v=Object(d["a"])(c,i,s,!1,null,null,null);a["default"]=v.exports},2078:function(t,a,e){"use strict";var i=e("a5a1"),s=e.n(i);s.a},"66be":function(t,a,e){},"9d8d":function(t,a,e){"use strict";var i=function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("div",{attrs:{id:"navbar"}},[e("nav",{staticClass:"navbar navbar-expand-lg bg-dark navbar-dark static-top"},[e("div",{staticClass:"container"},[t._m(0),t._m(1),e("div",{staticClass:"collapse navbar-collapse",attrs:{id:"navbarResponsive"}},[e("ul",{staticClass:"navbar-nav ml-auto"},[0!=t.type?e("li",{staticClass:"nav-item active"},[e("a",{staticClass:"nav-link",attrs:{href:t.homePage}},[t._v("Home\n                        "),e("span",{staticClass:"sr-only"},[t._v("(current)")])])]):t._e(),0!=t.type?e("li",{staticClass:"nav-item"},[e("a",{staticClass:"nav-link",attrs:{href:"/front/userProfile"}},[t._v("Profile")])]):t._e(),0!=t.type&&t.invites?e("li",{staticClass:"nav-item"},[e("a",{staticClass:"nav-link",attrs:{href:"/front/invites"}},[t._v("Invites")])]):t._e(),0!=t.type?e("li",{staticClass:"nav-item"},[e("a",{staticClass:"nav-link",on:{click:t.logout}},[t._v("Logout")])]):t._e(),0==t.type?e("li",{staticClass:"nav-item active"},[e("a",{staticClass:"nav-link",on:{click:t.goToLogin}},[t._v("Login\n                        "),e("span",{staticClass:"sr-only"},[t._v("(current)")])])]):t._e(),0==t.type?e("li",{staticClass:"nav-item"},[e("a",{staticClass:"nav-link",attrs:{href:"/front/registerPage"}},[t._v("Register")])]):t._e()])])])])])},s=[function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("a",{staticClass:"navbar-brand",attrs:{href:"#"}},[e("img",{attrs:{src:"http://placehold.it/150x50?text=Logo",alt:""}})])},function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("button",{staticClass:"navbar-toggler",attrs:{type:"button","data-toggle":"collapse","data-target":"#navbarResponsive","aria-controls":"navbarResponsive","aria-expanded":"false","aria-label":"Toggle navigation"}},[e("span",{staticClass:"navbar-toggler-icon"})])}],n=e("bc3a"),r=e.n(n),o={name:"navbar",data:function(){return{type:0,homePage:"",invites:!1}},mounted:function(){var t=this;console.log(this.type);var a=function(){return localStorage.getItem("jwtToken")};r.a.defaults.headers.common["Authorization"]="Bearer "+a(),r.a.get("/api/getUserRole").then(function(a){console.log(a.data),t.type=1,console.log(a),"RENT_ADMIN"==a.data?t.homePage="/front/RAindex":"FLIGHT_ADMIN"==a.data?t.homePage="/front/flightAdmin":"SYS_ADMIN"==a.data?t.homePage="/front/systemAdminPage":"REGISTERED"==a.data?(t.homePage="/front/index",t.invites=!0):"HOTEL_ADMIN"==a.data?t.homePage="/front/hotelAdminPage":(t.homePage="/",t.type=0)})},methods:{logout:function(){localStorage.setItem("jwtToken",""),window.location="/"},goToLogin:function(){this.$router.push("/front/loginPage")}}},l=o,c=(e("e056"),e("2877")),d=Object(c["a"])(l,i,s,!1,null,"434d11d6",null);a["a"]=d.exports},a5a1:function(t,a,e){},e056:function(t,a,e){"use strict";var i=e("66be"),s=e.n(i);s.a}}]);
//# sourceMappingURL=chunk-be03baec.58e2e5a9.js.map