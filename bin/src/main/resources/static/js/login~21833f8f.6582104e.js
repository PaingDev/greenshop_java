(window.webpackJsonp=window.webpackJsonp||[]).push([["login~21833f8f"],{4562:function(t,n,s){"use strict";var i=s("ec46");s.n(i).a},4951:function(t,n,s){"use strict";var i=s("4bd7");s.n(i).a},"4bd7":function(t,n,s){},a55b:function(t,n,s){"use strict";s.r(n);var i=s("5530"),o=s("cc11"),a=s("2f62"),e={computed:Object(i.a)({},Object(a.b)(["isLogin"])),components:{"fb-login":o.a}},c=s("2877"),l=Object(c.a)(e,(function(){var t=this,n=t.$createElement,s=t._self._c||n;return s("div",[s("b-container",[s("div",{staticClass:"text-left mt-n5"},[s("i",{staticClass:"fa fa-home btn btn-outline-info",attrs:{"aria-hidden":"true"},on:{click:function(n){return t.$router.go(-1)}}})]),s("fb-login"),t.isLogin?s("b-row",[s("b-col")],1):t._e()],1)],1)}),[],!1,null,null,null);n.default=l.exports},cc11:function(t,n,s){"use strict";var i=s("5530"),o=s("2d01"),a=s("2f62"),e=s("f121"),c={mounted:function(){window.fbAsyncInit=function(){FB.init({appId:"3254483804579476",cookie:!0,xfbml:!0,version:"v6.0"}),this.checkLoginState()}.bind(this)},data:function(){return{baseUrl:e.a.defaults.baseURL}},computed:Object(i.a)({},Object(a.b)(["userToken","isLogin","type"])),methods:{login:function(){FB.login(function(t){var n=this;if("unknown"==t.status);else if("connected"==t.status){var s=t.authResponse;o.a.login(s.accessToken).then((function(t){e.a.defaults.headers.common.Authorization=" Bearer ".concat(t.userToken),n.$store.dispatch("app/login",t)}))}else t.status}.bind(this),{scope:"public_profile,email"})},logout:function(){"phone"==this.type?this.$store.dispatch("app/logout",{}):(this.$store.dispatch("app/logout",{}),FB.logout(function(t){}.bind(this)))},checkLoginState:function(){FB.getLoginStatus(function(t){var n=this;if("connected"===t.status){t.authResponse.userID;var s=t.authResponse.accessToken;o.a.login(s).then((function(t){e.a.defaults.headers.common.Authorization=" Bearer ".concat(t.userToken),n.$store.dispatch("app/login",t.userToken)}))}else t.status}.bind(this),{scope:"public_profile,email"})},fbInfo:function(){FB.api("/me",(function(t){}))}}},l=(s("4951"),s("4562"),s("2877")),u=Object(l.a)(c,(function(){var t=this,n=t.$createElement,s=t._self._c||n;return s("div",{staticClass:"container-flud"},[s("div",{staticClass:"row justify-content-md-center"},[s("div",{staticClass:"col col-md-5"},[t.isLogin?s("h5",[t._v("Logout ထွက်မှာ သေချာပါသလား။")]):s("h5",{staticClass:"product-title",domProps:{innerHTML:t._s(t.$t("freshText"))}})])]),s("div",{staticClass:"row justify-content-md-center"},[s("div",{staticClass:"col col-md-5"},[s("img",{staticClass:"img-fluid",attrs:{src:t.baseUrl+"/img/fresh_bg.jpg",alt:"Fresh Myanmar"}})])]),t.isLogin?[s("div",{staticClass:"row justify-content-md-center"},[s("div",{staticClass:"col col-md-3 my-1"},[s("b-link",{staticClass:"btn btn-social btn-block btn-logout",on:{click:t.logout}},[s("i",{staticClass:"fa fa-sign-out",attrs:{"aria-hidden":"true"}}),t._v("Logout ")])],1)])]:[s("div",{staticClass:"row justify-content-md-center"},[s("div",{staticClass:"col col-md-3 my-1"},[t.isLogin?s("a",{staticClass:"btn btn-social btn-facebook btn-block",on:{click:t.logout}},[s("span",{staticClass:"fa fa-facebook"}),t._v(" Logout ")]):s("a",{staticClass:"btn btn-social btn-facebook btn-block",on:{click:t.login}},[s("span",{staticClass:"fa fa-facebook"}),t._v(" Sign in with Facebook ")])])]),s("div",{staticClass:"row justify-content-md-center"},[s("div",{staticClass:"col col-md-3 my-1"},[s("b-link",{staticClass:"btn btn-social btn-block btn-phone",attrs:{to:"/phoneLogin"}},[s("i",{staticClass:"fa fa-phone",attrs:{"aria-hidden":"true"}}),t._v("Sign in with Phone ")])],1)])]],2)}),[],!1,null,"692ab878",null);n.a=u.exports},ec46:function(t,n,s){}}]);