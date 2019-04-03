import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/about',
      name: 'about',

      component: () => import('./views/About.vue')
    },
    {
      path: '/searchVehicle',
      name: 'searchVehicle',
      component: () => import('./components/searchVehicle.vue')
    },
    {
      path: '/userProfile',
      name: 'userProfile',
      component: () => import('./components/userProfile.vue')
    },
    {
      path: '/flightCompanyProfile',
      name: 'flightCompanyProfile',
      component: () => import('./components/flightCompanyProfile.vue')
    },
    {
      path: '/rentacarprofile',
      name: 'rentacarprofile',
      component: () => import('./components/rentacarprofile.vue')
    },
    {
      path: '/flight',
      name: 'flight',
      component: () => import('./components/flight.vue')
    },
    {
      path: '/searchRentACarCompany',
      name: 'searchRentACarCompany',
      component: () => import('./components/searchRentACarCompany.vue')
    }
  ]
})
