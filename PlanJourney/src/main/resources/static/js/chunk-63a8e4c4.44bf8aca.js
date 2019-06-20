(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-63a8e4c4"],{"029e":function(t,a,e){"use strict";var s=e("9fd5"),r=e.n(s);r.a},"2add":function(t,a,e){"use strict";e.r(a);var s=function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("div",{attrs:{id:"flightReservationDetails"}},[e("navbar"),e("div",{staticClass:"container"},[e("div",{staticClass:"row"},[e("table",{staticStyle:{"text-align":"left"}},[e("tr",[e("td",{staticStyle:{"font-weight":"bold"}},[t._v("Created: ")]),e("td",[t._v(t._s(t.reservation.created))])]),e("tr",[e("td",{staticStyle:{"font-weight":"bold"}},[t._v("Price: ")]),e("td",[t._v(t._s(t.reservation.price))])]),e("tr",[e("td",{staticStyle:{"font-weight":"bold"}},[t._v("Discount: ")]),e("td",[t._v(t._s(t.reservation.discount)+"%")])])])]),e("br"),e("br"),e("div",{staticClass:"row"},[e("table",{staticStyle:{"text-align":"left"}},[e("h2",[t._v("Flight Info:")]),e("tr",[e("td",[t._v("From: ")]),e("td",[t._v(t._s(t.reservation.startDestination))])]),e("tr",[e("td",[t._v("To: ")]),e("td",[t._v(t._s(t.reservation.endDestination))])]),e("tr",[e("td",[t._v("Start date: ")]),e("td",[t._v(" "+t._s(t.reservation.startDate)+" ")])]),e("tr",[e("td",[t._v("End date: ")]),e("td",[t._v(" "+t._s(t.reservation.endDate)+" ")])]),e("tr",[e("td",[t._v("Flight duration: ")]),e("td",[t._v(" "+t._s(t.reservation.flightDuration)+" ")])]),e("tr",[e("td",[t._v("Flight length: ")]),e("td",[t._v(" "+t._s(t.reservation.flightLenght)+" ")])])]),e("div",{staticClass:"col",staticStyle:{"margin-left":"5%"}},[e("yandex-map",{staticStyle:{width:"370px",height:"230px"},attrs:{coords:[t.reservation.latitude,t.reservation.longitude],zoom:"14",controlss:["zoomControl"],"map-type":"hybrid"}},[e("ymap-marker",{attrs:{"marker-id":"1","marker-type":"placemark",coords:[this.DestLatitude,this.DestLongitude],"marker-fill":{color:"#0E4779",opacity:.5},"marker-stroke":{color:"#0E4779",width:4}}})],1)],1)]),e("br"),t._m(0),e("br"),e("div",{staticClass:"row"},t._l(t.reservation.passangersInfo,function(a){return e("table",{key:a.id,staticStyle:{"text-align":"left","margin-right":"5%"},attrs:{border:"1"}},[e("tr",[e("th",{attrs:{colspan:"2"}},[t._v(t._s(a.firstName)+" "+t._s(a.lastName))])]),e("tr",[e("td",[t._v("Seat: ")]),e("td",[t._v("("+t._s(a.seatRow)+", "+t._s(a.seatColumn)+")")])]),e("tr",[e("td",[t._v("Class: ")]),e("td",[t._v(t._s(a.travelClass))])]),e("tr",[e("td",[t._v("Passport: ")]),e("td",[t._v(t._s(a.passport))])]),e("tr",[e("td",[t._v("Status: ")]),"Confirmed"==a.status?e("td",{staticStyle:{color:"green"}},[t._v(t._s(a.status))]):t._e(),"Waiting"==a.status?e("td",{staticStyle:{color:"blue"}},[t._v(t._s(a.status))]):t._e(),"Refused"==a.status?e("td",{staticStyle:{color:"red"}},[t._v(t._s(a.status))]):t._e()])])}),0),e("br"),t._m(1),e("br"),e("div",{staticClass:"row"},t._l(t.reservation.hotelsReservations,function(a){return e("table",{key:a.hotelName,staticStyle:{"text-align":"left","margin-right":"5%"},attrs:{border:"1"}},[e("tr",[e("th",{attrs:{colspan:"2"}},[t._v(t._s(a.hotelName))])]),e("tr",[e("td",[t._v("From: ")]),e("td",[t._v(t._s(a.firstDay))])]),e("tr",[e("td",[t._v("To: ")]),e("td",[t._v(t._s(a.lastDay))])]),e("tr",[e("td",[t._v("Paid price: ")]),e("td",[t._v(" "+t._s(a.paidPrice)+" ")])])])}),0),t._m(2),e("div",{staticClass:"row"},[t._l(t.reservation.rentReservations,function(a){return e("table",{key:a.id,staticStyle:{"text-align":"left","margin-right":"5%"},attrs:{border:"1"}},[e("tr",[e("th",{attrs:{colspan:"2"}},[t._v(t._s(a.vehicleName))])]),e("tr",[e("td",[t._v("From: ")]),e("td",[t._v(t._s(a.dateFrom))])]),e("tr",[e("td",[t._v("To: ")]),e("td",[t._v(t._s(a.dateTo))])]),e("tr",[e("td",[t._v("Paid price: ")]),e("td",[t._v(t._s(a.price))])])])}),e("br"),e("br"),e("button",{staticClass:"btn btn-outline-danger",on:{click:t.cancelFlightReservation}},[t._v("Cancel Reservation")])],2)])],1)},r=[function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("div",{staticClass:"row"},[e("h2",[t._v("Passangers:")])])},function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("div",{staticClass:"row"},[e("h2",[t._v("Hotel Reservations:")])])},function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("div",{staticClass:"row"},[e("h2",[t._v("Vehicle Reservations:")])])}],n=e("bc3a"),i=e.n(n),o=e("9d8d"),l={name:"flightReservationDetails",components:{navbar:o["a"]},data:function(){return{reservation:{},reservationId:this.$route.params.reservationId}},created:function(){var t=this,a=function(){return localStorage.getItem("jwtToken")};i.a.defaults.headers.common["Authorization"]="Bearer "+a(),i.a.get("/api/getFlightReservation/"+this.reservationId).then(function(a){t.reservation=a.data})},methods:{cancelFlightReservation:function(){var t=this,a=function(){return localStorage.getItem("jwtToken")};i.a.defaults.headers.common["Authorization"]="Bearer "+a(),i.a.get("/api/cancelFlightReservation/"+this.reservationId).then(function(a){alert(a.data),"success"==a.data&&t.$router.push("/front/index")})}}},c=l,v=(e("029e"),e("2877")),d=Object(v["a"])(c,s,r,!1,null,null,null);a["default"]=d.exports},"66be":function(t,a,e){},"9d8d":function(t,a,e){"use strict";var s=function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("div",{attrs:{id:"navbar"}},[e("nav",{staticClass:"navbar navbar-expand-lg bg-dark navbar-dark static-top"},[e("div",{staticClass:"container"},[t._m(0),t._m(1),e("div",{staticClass:"collapse navbar-collapse",attrs:{id:"navbarResponsive"}},[e("ul",{staticClass:"navbar-nav ml-auto"},[0!=t.type?e("li",{staticClass:"nav-item active"},[e("a",{staticClass:"nav-link",attrs:{href:t.homePage}},[t._v("Home\n                        "),e("span",{staticClass:"sr-only"},[t._v("(current)")])])]):t._e(),0!=t.type?e("li",{staticClass:"nav-item"},[e("a",{staticClass:"nav-link",attrs:{href:"/front/userProfile"}},[t._v("Profile")])]):t._e(),0!=t.type&&t.invites?e("li",{staticClass:"nav-item"},[e("a",{staticClass:"nav-link",attrs:{href:"/front/invites"}},[t._v("Invites")])]):t._e(),0!=t.type?e("li",{staticClass:"nav-item"},[e("a",{staticClass:"nav-link",on:{click:t.logout}},[t._v("Logout")])]):t._e(),0==t.type?e("li",{staticClass:"nav-item active"},[e("a",{staticClass:"nav-link",on:{click:t.goToLogin}},[t._v("Login\n                        "),e("span",{staticClass:"sr-only"},[t._v("(current)")])])]):t._e(),0==t.type?e("li",{staticClass:"nav-item"},[e("a",{staticClass:"nav-link",attrs:{href:"/front/registerPage"}},[t._v("Register")])]):t._e()])])])])])},r=[function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("a",{staticClass:"navbar-brand",attrs:{href:"#"}},[e("img",{attrs:{src:"http://placehold.it/150x50?text=Logo",alt:""}})])},function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("button",{staticClass:"navbar-toggler",attrs:{type:"button","data-toggle":"collapse","data-target":"#navbarResponsive","aria-controls":"navbarResponsive","aria-expanded":"false","aria-label":"Toggle navigation"}},[e("span",{staticClass:"navbar-toggler-icon"})])}],n=e("bc3a"),i=e.n(n),o={name:"navbar",data:function(){return{type:0,homePage:"",invites:!1}},mounted:function(){var t=this;console.log(this.type);var a=function(){return localStorage.getItem("jwtToken")};i.a.defaults.headers.common["Authorization"]="Bearer "+a(),i.a.get("/api/getUserRole").then(function(a){console.log(a.data),t.type=1,console.log(a),"RENT_ADMIN"==a.data?t.homePage="/front/RAindex":"FLIGHT_ADMIN"==a.data?t.homePage="/front/flightAdmin":"SYS_ADMIN"==a.data?t.homePage="/front/systemAdminPage":"REGISTERED"==a.data?(t.homePage="/front/index",t.invites=!0):"HOTEL_ADMIN"==a.data?t.homePage="/front/hotelAdminPage":(t.homePage="/",t.type=0)})},methods:{logout:function(){localStorage.setItem("jwtToken",""),window.location="/"},goToLogin:function(){this.$router.push("/front/loginPage")}}},l=o,c=(e("e056"),e("2877")),v=Object(c["a"])(l,s,r,!1,null,"434d11d6",null);a["a"]=v.exports},"9fd5":function(t,a,e){},e056:function(t,a,e){"use strict";var s=e("66be"),r=e.n(s);r.a}}]);
//# sourceMappingURL=chunk-63a8e4c4.44bf8aca.js.map