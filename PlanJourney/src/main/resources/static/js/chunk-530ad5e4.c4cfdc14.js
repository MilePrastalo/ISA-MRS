(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-530ad5e4","chunk-16359966","chunk-107b6379"],{"02f4":function(t,e,a){var i=a("4588"),n=a("be13");t.exports=function(t){return function(e,a){var r,o,s=String(n(e)),c=i(a),l=s.length;return c<0||c>=l?t?"":void 0:(r=s.charCodeAt(c),r<55296||r>56319||c+1===l||(o=s.charCodeAt(c+1))<56320||o>57343?t?s.charAt(c):r:t?s.slice(c,c+2):o-56320+(r-55296<<10)+65536)}}},"0390":function(t,e,a){"use strict";var i=a("02f4")(!0);t.exports=function(t,e,a){return e+(a?i(t,e).length:1)}},"0bfb":function(t,e,a){"use strict";var i=a("cb7c");t.exports=function(){var t=i(this),e="";return t.global&&(e+="g"),t.ignoreCase&&(e+="i"),t.multiline&&(e+="m"),t.unicode&&(e+="u"),t.sticky&&(e+="y"),e}},"214f":function(t,e,a){"use strict";a("b0c5");var i=a("2aba"),n=a("32e9"),r=a("79e5"),o=a("be13"),s=a("2b4c"),c=a("520a"),l=s("species"),d=!r(function(){var t=/./;return t.exec=function(){var t=[];return t.groups={a:"7"},t},"7"!=="".replace(t,"$<a>")}),u=function(){var t=/(?:)/,e=t.exec;t.exec=function(){return e.apply(this,arguments)};var a="ab".split(t);return 2===a.length&&"a"===a[0]&&"b"===a[1]}();t.exports=function(t,e,a){var v=s(t),f=!r(function(){var e={};return e[v]=function(){return 7},7!=""[t](e)}),m=f?!r(function(){var e=!1,a=/a/;return a.exec=function(){return e=!0,null},"split"===t&&(a.constructor={},a.constructor[l]=function(){return a}),a[v](""),!e}):void 0;if(!f||!m||"replace"===t&&!d||"split"===t&&!u){var p=/./[v],h=a(o,v,""[t],function(t,e,a,i,n){return e.exec===c?f&&!n?{done:!0,value:p.call(e,a,i)}:{done:!0,value:t.call(a,e,i)}:{done:!1}}),g=h[0],_=h[1];i(String.prototype,t,g),n(RegExp.prototype,v,2==e?function(t,e){return _.call(t,this,e)}:function(t){return _.call(t,this)})}}},"28a5":function(t,e,a){"use strict";var i=a("aae3"),n=a("cb7c"),r=a("ebd6"),o=a("0390"),s=a("9def"),c=a("5f1b"),l=a("520a"),d=a("79e5"),u=Math.min,v=[].push,f="split",m="length",p="lastIndex",h=4294967295,g=!d(function(){RegExp(h,"y")});a("214f")("split",2,function(t,e,a,d){var _;return _="c"=="abbc"[f](/(b)*/)[1]||4!="test"[f](/(?:)/,-1)[m]||2!="ab"[f](/(?:ab)*/)[m]||4!="."[f](/(.?)(.?)/)[m]||"."[f](/()()/)[m]>1||""[f](/.?/)[m]?function(t,e){var n=String(this);if(void 0===t&&0===e)return[];if(!i(t))return a.call(n,t,e);var r,o,s,c=[],d=(t.ignoreCase?"i":"")+(t.multiline?"m":"")+(t.unicode?"u":"")+(t.sticky?"y":""),u=0,f=void 0===e?h:e>>>0,g=new RegExp(t.source,d+"g");while(r=l.call(g,n)){if(o=g[p],o>u&&(c.push(n.slice(u,r.index)),r[m]>1&&r.index<n[m]&&v.apply(c,r.slice(1)),s=r[0][m],u=o,c[m]>=f))break;g[p]===r.index&&g[p]++}return u===n[m]?!s&&g.test("")||c.push(""):c.push(n.slice(u)),c[m]>f?c.slice(0,f):c}:"0"[f](void 0,0)[m]?function(t,e){return void 0===t&&0===e?[]:a.call(this,t,e)}:a,[function(a,i){var n=t(this),r=void 0==a?void 0:a[e];return void 0!==r?r.call(a,n,i):_.call(String(n),a,i)},function(t,e){var i=d(_,t,this,e,_!==a);if(i.done)return i.value;var l=n(t),v=String(this),f=r(l,RegExp),m=l.unicode,p=(l.ignoreCase?"i":"")+(l.multiline?"m":"")+(l.unicode?"u":"")+(g?"y":"g"),y=new f(g?l:"^(?:"+l.source+")",p),b=void 0===e?h:e>>>0;if(0===b)return[];if(0===v.length)return null===c(y,v)?[v]:[];var k=0,x=0,S=[];while(x<v.length){y.lastIndex=g?x:0;var C,w=c(y,g?v:v.slice(x));if(null===w||(C=u(s(y.lastIndex+(g?0:x)),v.length))===k)x=o(v,x,m);else{if(S.push(v.slice(k,x)),S.length===b)return S;for(var T=1;T<=w.length-1;T++)if(S.push(w[T]),S.length===b)return S;x=k=C}}return S.push(v.slice(k)),S}]})},"40f2":function(t,e,a){"use strict";a.r(e);var i=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{attrs:{id:"searchRentACarCompany"}},[a("div",{attrs:{id:"inputfields"}},[a("h2",[t._v("Search Rent a Car companies")]),a("table",[a("tr",[a("td",[t._v("Rent a company name:")]),a("td",[a("input",{directives:[{name:"model",rawName:"v-model",value:t.name,expression:"name"}],attrs:{type:"text"},domProps:{value:t.name},on:{input:function(e){e.target.composing||(t.name=e.target.value)}}})]),a("td",[t._v("Location")]),a("td",[a("input",{directives:[{name:"model",rawName:"v-model",value:t.location,expression:"location"}],attrs:{id:"locationInput",type:"text",readonly:""},domProps:{value:t.location},on:{input:function(e){e.target.composing||(t.location=e.target.value)}}})])]),a("tr",[a("td",[t._v("Date from")]),a("td",[a("input",{directives:[{name:"model",rawName:"v-model",value:t.datefrom,expression:"datefrom"}],attrs:{type:"date"},domProps:{value:t.datefrom},on:{input:function(e){e.target.composing||(t.datefrom=e.target.value)}}})]),a("td",[t._v("Date to")]),a("td",[a("input",{directives:[{name:"model",rawName:"v-model",value:t.dateTo,expression:"dateTo"}],attrs:{type:"date"},domProps:{value:t.dateTo},on:{input:function(e){e.target.composing||(t.dateTo=e.target.value)}}})]),a("td",[a("Button",{on:{click:t.search}},[t._v("Search")])],1)])])]),a("div",[a("table",{staticClass:"table",attrs:{id:"DataTable"}},[t._m(0),t._l(t.companies,function(e){return a("tr",{key:e.name},[t._m(1,!0),a("td",[t._v(t._s(e.name))]),a("td",[a("table",{staticClass:"table"},[t._m(2,!0),t._l(e.offices,function(e){return a("tr",{key:e},[a("td",[t._v(t._s(e.name))]),a("td",[t._v(t._s(e.address))]),a("td",[t._v(t._s(e.destination))])])})],2)]),a("td",[t._v(t._s(e.rating))]),a("td",[a("Button",{on:{click:function(a){return t.details(e)}}},[t._v("Details")])],1),a("td",[a("Button",{on:{click:function(a){return t.select(e)}}},[t._v("Select")])],1)])})],2)])])},n=[function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("tr",[a("th"),a("th",[t._v("Name")]),a("th",[t._v("Offices")]),a("th",[t._v("Rating")]),a("th"),a("th")])},function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("td",[i("img",{attrs:{src:a("953f"),width:"50px",height:"50px",alt:""}})])},function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("tr",[a("th",[t._v("Office name")]),a("th",[t._v("Office address")]),a("th",[t._v("Office destination")])])}],r=(a("7f7f"),a("28a5"),a("bc3a")),o=a.n(r),s={name:"searchRentACarCompany",components:{},props:["ilocation","iflightDateArrive","iflightDateLeaving","ijustSearch"],data:function(){return{companies:[],name:"",location:this.ilocation,datefrom:"",dateTo:"",flightDateArrive:this.iflightDateArrive,flightDateLeaving:this.iflightDateLeaving,justSearch:this.ijustSearch}},mounted:function(){if(void 0==this.location&&(this.location="",document.getElementById("locationInput").readOnly=!1),void 0==this.flightDateArrive)this.flightDateArrive="",this.flightDateLeaving="";else{var t=this.flightDateArrive.split(" "),e=t[0].split("."),a=new Date(e[2],e[1]-1,e[0]),i=("0"+a.getDate()).slice(-2),n=("0"+(a.getMonth()+1)).slice(-2),r=a.getFullYear()+"-"+n+"-"+i;this.datefrom=r,t=this.flightDateLeaving.split(" "),e=t[0].split(".");var o=new Date(e[2],e[1]-1,e[0]);i=("0"+o.getDate()).slice(-2),n=("0"+(o.getMonth()+1)).slice(-2),r=o.getFullYear()+"-"+n+"-"+i,this.dateTo=r}},methods:{search:function(){var t=this;if(!this.justSearch){var e=new Date(this.datefrom);e.setHours(0);var a=new Date(this.dateTo);a.setHours(0);var i=this.flightDateArrive.split(" "),n=i[0].split("."),r=new Date(n[2],n[1]-1,n[0]);i=this.flightDateLeaving.split(" "),n=i[0].split(".");new Date(n[2],n[1]-1,n[0]);if(e<r)return void alert("Date must me after flight takes off")}o.a.post("/api/getRentACarCompanies",{name:this.name,location:this.location,dateFrom:this.datefrom,dateTo:this.dateTo}).then(function(e){t.companies=e.data})},select:function(t){var e={};e.id=t.id,e.dateFrom=this.datefrom,e.dateTo=this.dateTo,e.offices=t.offices,this.$emit("selected",e)},details:function(t){this.$emit("details",t)}}},c=s,l=(a("dc55"),a("2877")),d=Object(l["a"])(c,i,n,!1,null,"b254fbc4",null);e["default"]=d.exports},"41a4":function(t,e,a){"use strict";a.r(e);var i=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{attrs:{id:"searchVehicle"}},[a("div",[a("table",[a("tr",[a("td",[t._v("Producer")]),a("td",[a("select",{directives:[{name:"model",rawName:"v-model",value:t.producer,expression:"producer"}],attrs:{name:"Producer",id:"producer"},on:{change:function(e){var a=Array.prototype.filter.call(e.target.options,function(t){return t.selected}).map(function(t){var e="_value"in t?t._value:t.value;return e});t.producer=e.target.multiple?a:a[0]}}},t._l(t.producers,function(e){return a("option",{key:e.id,domProps:{value:e}},[t._v(t._s(e))])}),0)]),a("td",[t._v("Type")]),a("td",[a("select",{directives:[{name:"model",rawName:"v-model",value:t.type,expression:"type"}],attrs:{name:"Type",id:"type"},on:{change:function(e){var a=Array.prototype.filter.call(e.target.options,function(t){return t.selected}).map(function(t){var e="_value"in t?t._value:t.value;return e});t.type=e.target.multiple?a:a[0]}}},t._l(t.types,function(e){return a("option",{key:e.id,domProps:{value:e}},[t._v(t._s(e))])}),0)])]),a("tr",[a("td",[t._v("Newer than")]),a("td",[a("input",{directives:[{name:"model",rawName:"v-model",value:t.newer,expression:"newer"}],attrs:{type:"number",id:"newer"},domProps:{value:t.newer},on:{input:function(e){e.target.composing||(t.newer=e.target.value)}}})]),a("td",[t._v("Older than")]),a("td",[a("input",{directives:[{name:"model",rawName:"v-model",value:t.older,expression:"older"}],attrs:{type:"number",id:"older"},domProps:{value:t.older},on:{input:function(e){e.target.composing||(t.older=e.target.value)}}})])]),a("tr",[a("td",[t._v("Number of seats")]),a("td",[a("input",{directives:[{name:"model",rawName:"v-model",value:t.seats,expression:"seats"}],attrs:{type:"number",id:"older"},domProps:{value:t.seats},on:{input:function(e){e.target.composing||(t.seats=e.target.value)}}})])]),a("tr",[a("td",[t._v("Price from")]),a("td",[a("input",{directives:[{name:"model",rawName:"v-model",value:t.priceFrom,expression:"priceFrom"}],attrs:{type:"number",id:"priceFrom"},domProps:{value:t.priceFrom},on:{input:function(e){e.target.composing||(t.priceFrom=e.target.value)}}})]),a("td",[t._v("Price to")]),a("td",[a("input",{directives:[{name:"model",rawName:"v-model",value:t.priceTo,expression:"priceTo"}],attrs:{type:"number",id:"priceTo"},domProps:{value:t.priceTo},on:{input:function(e){e.target.composing||(t.priceTo=e.target.value)}}})]),a("td",[a("Button",{on:{click:t.search}},[t._v("Search")])],1)])])])])},n=[],r=a("bc3a"),o=a.n(r),s={name:"searchVehicle",props:["iCompany","idateFrom","idateTo"],components:{},data:function(){return{cars:[],producer:"",priceFrom:"",priceTo:"",newer:"",older:"",type:"",seats:0,producers:["Mercedes","Audi","BMW"],types:["Sedan","Jeep"],company:this.iCompany,dateFrom:this.idateFrom,dateTo:this.idateTo}},mounted:function(){var t=this,e=function(){return localStorage.getItem("jwtToken")};o.a.defaults.headers.common["Authorization"]="Bearer "+e(),o.a.get("/api/getProducers").then(function(e){t.producers=e.data}),o.a.get("api/getTypes").then(function(e){t.types=e.data})},methods:{search:function(){var t=this;console.log(this.company),o.a.post("/api/vehicleSearch",{seats:this.seats,dateFrom:this.dateFrom,dateTo:this.dateTo,company:this.company,producer:this.producer,priceFrom:this.priceFrom,priceTo:this.priceTo,newer:this.newer,older:this.older,type:this.type}).then(function(e){console.log(e.data),t.$emit("searched",e.data)})}}},c=s,l=(a("c3ec"),a("2877")),d=Object(l["a"])(c,i,n,!1,null,"4ab26f48",null);e["default"]=d.exports},"4c2d":function(t,e,a){"use strict";var i=a("7a6a"),n=a.n(i);n.a},"520a":function(t,e,a){"use strict";var i=a("0bfb"),n=RegExp.prototype.exec,r=String.prototype.replace,o=n,s="lastIndex",c=function(){var t=/a/,e=/b*/g;return n.call(t,"a"),n.call(e,"a"),0!==t[s]||0!==e[s]}(),l=void 0!==/()??/.exec("")[1],d=c||l;d&&(o=function(t){var e,a,o,d,u=this;return l&&(a=new RegExp("^"+u.source+"$(?!\\s)",i.call(u))),c&&(e=u[s]),o=n.call(u,t),c&&o&&(u[s]=u.global?o.index+o[0].length:e),l&&o&&o.length>1&&r.call(o[0],a,function(){for(d=1;d<arguments.length-2;d++)void 0===arguments[d]&&(o[d]=void 0)}),o}),t.exports=o},"5f1b":function(t,e,a){"use strict";var i=a("23c6"),n=RegExp.prototype.exec;t.exports=function(t,e){var a=t.exec;if("function"===typeof a){var r=a.call(t,e);if("object"!==typeof r)throw new TypeError("RegExp exec method returned something other than an Object or null");return r}if("RegExp"!==i(t))throw new TypeError("RegExp#exec called on incompatible receiver");return n.call(t,e)}},"7a6a":function(t,e,a){},"7dab":function(t,e,a){},"7f7f":function(t,e,a){var i=a("86cc").f,n=Function.prototype,r=/^\s*function ([^ (]*)/,o="name";o in n||a("9e1e")&&i(n,o,{configurable:!0,get:function(){try{return(""+this).match(r)[1]}catch(t){return""}}})},"953f":function(t,e,a){t.exports=a.p+"img/rent.bb9ea127.jpg"},aae3:function(t,e,a){var i=a("d3f4"),n=a("2d95"),r=a("2b4c")("match");t.exports=function(t){var e;return i(t)&&(void 0!==(e=t[r])?!!e:"RegExp"==n(t))}},ac6a:function(t,e,a){for(var i=a("cadf"),n=a("0d58"),r=a("2aba"),o=a("7726"),s=a("32e9"),c=a("84f2"),l=a("2b4c"),d=l("iterator"),u=l("toStringTag"),v=c.Array,f={CSSRuleList:!0,CSSStyleDeclaration:!1,CSSValueList:!1,ClientRectList:!1,DOMRectList:!1,DOMStringList:!1,DOMTokenList:!0,DataTransferItemList:!1,FileList:!1,HTMLAllCollection:!1,HTMLCollection:!1,HTMLFormElement:!1,HTMLSelectElement:!1,MediaList:!0,MimeTypeArray:!1,NamedNodeMap:!1,NodeList:!0,PaintRequestList:!1,Plugin:!1,PluginArray:!1,SVGLengthList:!1,SVGNumberList:!1,SVGPathSegList:!1,SVGPointList:!1,SVGStringList:!1,SVGTransformList:!1,SourceBufferList:!1,StyleSheetList:!0,TextTrackCueList:!1,TextTrackList:!1,TouchList:!1},m=n(f),p=0;p<m.length;p++){var h,g=m[p],_=f[g],y=o[g],b=y&&y.prototype;if(b&&(b[d]||s(b,d,v),b[u]||s(b,u,g),c[g]=v,_))for(h in i)b[h]||r(b,h,i[h],!0)}},b0c5:function(t,e,a){"use strict";var i=a("520a");a("5ca1")({target:"RegExp",proto:!0,forced:i!==/./.exec},{exec:i})},ba6f:function(t,e,a){},c1c9:function(t,e,a){t.exports=a.p+"img/kola.4987b8ae.jpg"},c3ec:function(t,e,a){"use strict";var i=a("7dab"),n=a.n(i);n.a},daae:function(t,e,a){},dc55:function(t,e,a){"use strict";var i=a("daae"),n=a.n(i);n.a},e7a4:function(t,e,a){"use strict";var i=a("ba6f"),n=a.n(i);n.a},f907:function(t,e,a){"use strict";a.r(e);var i=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{attrs:{id:"rentACarReservation"}},[t.rentProfile?a("div",[a("rentACarCompanyProfile",{attrs:{icompany:t.companyForDetails},on:{back:t.back}})],1):t._e(),0==t.rentProfile?a("searchRentACarCompany",{attrs:{ijustSearch:t.justSearch,ilocation:t.location,iflightDateArrive:t.flightDateArrive,iflightDateLeaving:t.flightDateLeaving},on:{details:t.companyDetails,selected:t.showVehicles}}):t._e(),0==t.rentProfile?a("div",{staticClass:"row centered"},[a("ul",{staticClass:"nav nav-tabs col-lg-10 fromTop"},[a("li",{staticClass:"nav-item centered bigTab"},[a("a",{staticClass:"nav-link active ",attrs:{id:"normal",href:"#"},on:{click:t.normal}},[t._v("Vehicles")])]),a("li",{staticClass:"nav-item centered bigTab"},[0==t.justSearch?a("a",{staticClass:"nav-link",attrs:{id:"quick",href:"#"},on:{click:t.quick}},[t._v("Quick Reservations")]):t._e()])])]):t._e(),0==t.tabselected&&0==t.rentProfile?a("div",{attrs:{id:"classic"}},[t.selected?a("searchVehicle",{attrs:{idateFrom:t.datefrom,idateTo:t.dateto,iCompany:t.companySelected},on:{searched:t.showFound}}):t._e(),a("table",{staticClass:"table"},[t._m(0),t._l(t.cars,function(e){return a("tr",{key:e.id},[t._m(1,!0),a("td",[t._v(t._s(e.name))]),a("td",[t._v(t._s(e.maker))]),a("td",[t._v(t._s(e.type))]),a("td",[t._v(t._s(e.year))]),a("td",[t._v(t._s(e.seats))]),a("td",[t._v(t._s(t.getCarPrice(e)))]),a("td",[t._v(t._s(e.rating))]),a("td",[0==t.justSearch?a("select",{directives:[{name:"model",rawName:"v-model",value:t.pickoffice,expression:"pickoffice"}],attrs:{name:"",id:""},on:{change:function(e){var a=Array.prototype.filter.call(e.target.options,function(t){return t.selected}).map(function(t){var e="_value"in t?t._value:t.value;return e});t.pickoffice=e.target.multiple?a:a[0]}}},t._l(t.offices,function(e){return a("option",{key:e.id,domProps:{value:e.id}},[t._v(t._s(e.name))])}),0):t._e()]),a("td",[0==t.justSearch?a("select",{directives:[{name:"model",rawName:"v-model",value:t.returnoffice,expression:"returnoffice"}],attrs:{name:"",id:""},on:{change:function(e){var a=Array.prototype.filter.call(e.target.options,function(t){return t.selected}).map(function(t){var e="_value"in t?t._value:t.value;return e});t.returnoffice=e.target.multiple?a:a[0]}}},t._l(t.offices,function(e){return a("option",{key:e.id,domProps:{value:e.id}},[t._v(t._s(e.name))])}),0):t._e()]),a("td",[0==t.justSearch?a("Button",{on:{click:function(a){return t.reserve(e.id)}}},[t._v("Reserve")]):t._e()],1)])})],2)],1):t._e(),1==t.tabselected&&0==t.rentProfile?a("div",{attrs:{id:"quick"}},[0==t.justSearch?a("table",{staticClass:"table",attrs:{border:"1"}},[t._m(2),a("tbody",t._l(t.quickReservations,function(e){return a("tr",{key:e.id},[a("td",[t._v(t._s(e.locationPick))]),a("td",[t._v(t._s(e.locationReturn))]),a("td",[t._v(t._s(e.dateFrom))]),a("td",[t._v(t._s(e.dateTo))]),a("td",[t._v(t._s(e.vehicleName))]),a("td",[t._v(t._s(e.price))]),a("td",[t._v(t._s(e.discount))]),a("td",[t._v(t._s(e.price*(100-e.discount)/100))]),a("td",[a("button",{on:{click:function(a){return t.reserveQuick(e)}}},[t._v("Reserve")])])])}),0)]):t._e()]):t._e()],1)},n=[function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("tr",[a("td"),a("td",[t._v("Name")]),a("td",[t._v("Maker")]),a("td",[t._v("Type")]),a("td",[t._v("Year")]),a("td",[t._v("Seats")]),a("td",[t._v("Price")]),a("td",[t._v("Rating")])])},function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("td",[i("img",{attrs:{src:a("c1c9"),width:"50px",height:"50px"}})])},function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("thead",{staticClass:"thead-dark"},[a("tr",[a("th",{attrs:{scope:"col"}},[t._v("Office to pick up")]),a("th",{attrs:{scope:"col"}},[t._v("Office to return")]),a("th",{attrs:{scope:"col"}},[t._v("Start date")]),a("th",{attrs:{scope:"col"}},[t._v("End date")]),a("th",{attrs:{scope:"col"}},[t._v("Vehicle name")]),a("th",{attrs:{scope:"col"}},[t._v("Origin price")]),a("th",{attrs:{scope:"col"}},[t._v("Discount")]),a("th",{attrs:{scope:"col"}},[t._v("Total price")]),a("th",{attrs:{scope:"col"}},[t._v("Reserve")])])])}],r=(a("ac6a"),a("40f2")),o=a("41a4"),s=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{attrs:{id:"rentACarCompanyProfile"}},[a("div",{staticClass:"row"},[a("button",{on:{click:t.back}},[t._v("Back")])]),a("nav",[a("div",{staticClass:"nav nav-tabs nav-fill big",attrs:{id:"nav-tab",role:"tablist"}},[a("a",{staticClass:"nav-item nav-link active",attrs:{id:"info","data-toggle":"tab",role:"tab","aria-controls":"nav-home","aria-selected":"true"},on:{click:t.showCompanyProfile}},[t._v("Info")]),a("a",{staticClass:"nav-item nav-link",attrs:{id:"offices","data-toggle":"tab",role:"tab","aria-controls":"nav-profile","aria-selected":"false"},on:{click:t.showOffices}},[t._v("Offices")]),a("a",{staticClass:"nav-item nav-link",attrs:{id:"Vehicles","data-toggle":"tab",role:"tab","aria-controls":"nav-contact","aria-selected":"false"},on:{click:t.showVehicles}},[t._v("Vehicles")])])]),a("div",{staticClass:"row"},[1==t.tabSelected?a("div",{attrs:{id:"HotelsReservations centered"}},[a("table",[t._m(0),a("tr",[a("td",[t._v("Name: ")]),a("td",[t._v(t._s(t.name))])]),a("tr",[a("td",[t._v("Address: ")]),a("td",[t._v(t._s(t.address))])]),a("tr",[a("td",[t._v("Description: ")]),a("td",[t._v(t._s(t.description))])]),a("tr",[a("td",[t._v("Rating: ")]),a("td",[t._v(t._s(t.rating))])])])]):t._e(),2==t.tabSelected?a("div",{attrs:{id:"office centered"}},[a("div",{staticClass:"row"},[a("table",{staticClass:"table centered col"},[t._m(1),t._l(t.offices,function(e){return a("tr",{key:e.id,on:{click:function(a){return t.officeMap(e)}}},[a("td",[t._v(t._s(e.name))]),a("td",[t._v(t._s(e.destination))]),a("td",[t._v(t._s(e.address))])])})],2),a("div",{staticClass:"col mapa"},[a("yandex-map",{staticStyle:{width:"350px",height:"250px"},attrs:{coords:[this.latitude,this.longitude],zoom:"14",controlss:["zoomControl"],"map-type":"hybrid"}},[a("ymap-marker",{attrs:{"marker-id":"1","marker-type":"placemark",coords:[this.latitude,this.longitude],"marker-fill":{color:"#0E4779",opacity:.5},"marker-stroke":{color:"#0E4779",width:4}}})],1)],1)])]):t._e(),3==t.tabSelected?a("div",{attrs:{id:"CarsReservations centered"}},[a("table",{staticClass:"table"},[t._m(2),t._l(t.vehicles,function(e){return a("tr",{key:e.id},[t._m(3,!0),a("td",[t._v(t._s(e.name))]),a("td",[t._v(t._s(e.maker))]),a("td",[t._v(t._s(e.type))]),a("td",[t._v(t._s(e.year))]),a("td",[t._v(t._s(e.seats))]),a("td",[t._v(t._s(e.price))]),a("td",[t._v(t._s(e.rating))])])})],2)]):t._e()])])},c=[function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("tr",[a("td",{attrs:{colspan:"2"}},[a("h2",[t._v("Company profile")])])])},function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("tr",[a("th",[t._v("Name")]),a("th",[t._v("Destination")]),a("th",[t._v("Address")])])},function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("tr",[a("td"),a("td",[t._v("Name")]),a("td",[t._v("Maker")]),a("td",[t._v("Type")]),a("td",[t._v("Year")]),a("td",[t._v("Seats")]),a("td",[t._v("Price")]),a("td",[t._v("Rating")])])},function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("td",[i("img",{attrs:{src:a("c1c9"),width:"50px",height:"50px"}})])}],l=(a("7f7f"),a("bc3a")),d=a.n(l),u={name:"rentACarCompanyProfile",components:{},props:["icompany"],data:function(){return{company:this.icompany,tabSelected:1,offices:[],vehicles:[],name:"",description:"",address:"",rating:"",longitude:0,latitude:0}},mounted:function(){var t=this;d.a.get("/api/getRentACarCompanyInfo/"+this.company.id).then(function(e){console.log(e.data),t.name=e.data.name,t.address=e.data.address,t.description=e.data.description,t.vehicles=e.data.vehicles,t.offices=e.data.offices,t.rating=e.data.rating})},methods:{showCompanyProfile:function(){this.tabSelected=1,document.getElementById("info").className="nav-item nav-link active",document.getElementById("offices").className="nav-item nav-link",document.getElementById("Vehicles").className="nav-item nav-link"},showOffices:function(){this.tabSelected=2,document.getElementById("info").className="nav-item nav-link",document.getElementById("offices").className="nav-item nav-link active",document.getElementById("Vehicles").className="nav-item nav-link"},showVehicles:function(){this.tabSelected=3,document.getElementById("info").className="nav-item nav-link",document.getElementById("offices").className="nav-item nav-link",document.getElementById("Vehicles").className="nav-item nav-link active"},back:function(){this.$emit("back")},officeMap:function(t){this.longitude=t.longitude,this.latitude=t.latitude}}},v=u,f=(a("4c2d"),a("2877")),m=Object(f["a"])(v,s,c,!1,null,"7ec78c17",null),p=m.exports,h={name:"rentACarReservation",components:{searchRentACarCompany:r["default"],searchVehicle:o["default"],rentACarCompanyProfile:p},props:["ilocation","iflightDateArrive","iflightDateLeaving"],data:function(){return{location:this.ilocation,cars:[],offices:[],id:"",datefrom:"",dateto:"",returnoffice:"",pickoffice:"",quickReservations:[],flightDateArrive:this.iflightDateArrive,flightDateLeaving:this.iflightDateLeaving,tabselected:0,justSearch:!1,companySelected:"",selected:!1,rentProfile:!1,companyForDetails:{}}},mounted:function(){void 0==this.location?this.justSearch=!0:this.justSearch=!1},methods:{normal:function(){this.tabselected=0,document.getElementById("normal").className="nav-link active ",document.getElementById("quick").className="nav-link "},getCarPrice:function(t){if(""!=this.datefrom&&""!=this.dateto){var e=Date.parse(this.datefrom),a=Date.parse(this.dateto),i=Math.ceil((a-e)/1e3/3600/24);return i*t.price}return t.price},quick:function(){this.tabselected=1,document.getElementById("normal").className="nav-link  ",document.getElementById("quick").className="nav-link active"},showFound:function(t){this.cars=t,this.cars.forEach(function(t,e){console.log(t),-1==t.rating&&(t.rating="No ratings")})},showVehicles:function(t){var e=this;if(this.companySelected=t.id,this.selected=!0,this.offices=t.offices,this.id=t.id,this.datefrom=t.dateFrom,this.dateto=t.dateTo,!this.justSearch){var a=function(){return localStorage.getItem("jwtToken")};d.a.defaults.headers.common["Authorization"]="Bearer "+a(),d.a.post("/api/getQuickReservations",{id:this.id,dateFrom:this.datefrom,dateTo:this.dateto}).then(function(t){console.log(t.data),e.quickReservations=t.data})}},reserve:function(t){var e=this,a=function(){return localStorage.getItem("jwtToken")};d.a.defaults.headers.common["Authorization"]="Bearer "+a(),d.a.post("/api/reserveVehicle",{id:t,dateFrom:this.datefrom,dateTo:this.dateto,officePick:this.pickoffice,officeReturn:this.returnoffice}).then(function(t){console.log(t),alert("Success"),e.$emit("vehicleReserved",t.data)})},reserveQuick:function(t){var e=this;d.a.post("/api/quickReserveVehicle",{id:t.id}).then(function(t){console.log(t),alert("Success"),e.$emit("vehicleReserved",t.data)})},companyDetails:function(t){this.companyForDetails=t,this.rentProfile=!0},back:function(){this.rentProfile=!1}}},g=h,_=(a("e7a4"),Object(f["a"])(g,i,n,!1,null,"cdc61b58",null));e["default"]=_.exports}}]);
//# sourceMappingURL=chunk-530ad5e4.c4cfdc14.js.map