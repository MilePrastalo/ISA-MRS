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
      name: 'unregisteredUserFrontPage',
      component: () => import('./components/unregisteredUserFrontPage.vue')
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
      path: '/searchFlights',
      name: 'searchFlights',
      component: () => import('./components/searchFlights.vue')
    },
    {
      path: '/searchRentACarCompany',
      name: 'searchRentACarCompany',
      component: () => import('./components/searchRentACarCompany.vue')
    },
    {
      path: '/hotelProfile',
      name: 'hotelProfile',
      component: () => import('./components/hotelProfile.vue')
    },
    {
      path: '/hotelAdminProfile',
      name: 'hotelAdminProfile',
      component: () => import('./components/hotelAdminProfile.vue')
    },
    {
      path: '/loginPage',
      name: 'loginPage',
      component: () => import('./components/loginPage.vue')
    },
    {
      path: '/registerPage',
      name: 'registerPage',
      component: () => import('./components/registerPage.vue')
    },
    {
      path: '/addHotel',
      name: 'addHotel',
      component: () => import('./components/addHotel.vue')
    },
    {
      path: '/addVehicle',
      name: 'addbehicle',
      component: () => import('./components/addvehicle.vue')
    },
    {
      path: '/index',
      name: 'registeredFrontPage',
      component: () => import('./components/registeredFrontPage.vue')
    },
    {
      path: '/addHotelRooms',
      name: 'addHotelRooms',
      component: () => import('./components/addHotelRooms.vue')
    }
  ]
})
