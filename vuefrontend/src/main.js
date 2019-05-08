import Vue from 'vue'
import VueLayers from 'vuelayers'
import 'vuelayers/lib/style.css' // needs css-loader

import App from './App.vue'
import router from './router'

Vue.config.productionTip = false

// register all VueLayers components
Vue.use(VueLayers, {
  // global data projection, see https://vuelayers.github.io/#/quickstart?id=global-data-projection
  // dataProjection: 'EPSG:4326',
})
new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
function setHeader(val){
  axios.defaults.headers.post['Authorization'] = "Bearer " + val;
  axios.defaults.headers.get['Authorization'] = "Bearer " + val;
  console.log("Header set");
}