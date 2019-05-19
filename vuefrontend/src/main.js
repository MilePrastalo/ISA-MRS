import Vue from 'vue'


import App from './App.vue'
import router from './router'
import YandexMapPlugin from 'vue-yandex-maps'
Vue.config.productionTip = false

// Translates Yandex maps to english.
const options = {
  lang: 'en_US',
  version: '2.1'
}
Vue.use(YandexMapPlugin,options)

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
function setHeader(val){
  axios.defaults.headers.post['Authorization'] = "Bearer " + val;
  axios.defaults.headers.get['Authorization'] = "Bearer " + val;
  console.log("Header set");
}