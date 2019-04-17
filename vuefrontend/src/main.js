import Vue from 'vue'
import App from './App.vue'
import router from './router'

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
function setHeader(val){
  axios.defaults.headers.post['Authorization'] = "Bearer " + val;
  axios.defaults.headers.get['Authorization'] = "Bearer " + val;
  console.log("Header set");
}