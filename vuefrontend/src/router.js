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
      path: '/hotelProfile/:hotelName',
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
      path: '/searchHotels',
      name: 'searchHotels',
      component: () => import('./components/searchHotels.vue')
    },
    {
      path: '/flightAdmin',
      name: 'flightAdmin',
      component: () => import('./components/flightAdmin.vue')
    },
    {
      path: '/flightForAdmin',
      name: 'flightForAdmin',
      component: () => import('./components/flightForAdmin.vue')
    }
    ,
    {
      path: '/systemAdminPage',
      name: 'systemAdminPage',
      component: () => import('./components/SystemAdmin/systemAdminPage.vue')
    },
    {
      path: '/RAindex',
      name: 'rentACarAdminIndex',
      component: () => import('./components/rentACarAdminIndex.vue')
    },
    {
      path: '/searchFlightCompany',
      name: 'searchFlightCompany',
      component: () => import('./components/searchFlightCompany.vue')

    },
    {
      path: '/hotelAdminPage',
      name: 'hotelAdminPage',
      component: () => import('./components/HotelAdmin/hotelAdminPage.vue')
    },
    {
      path:'/rentacar',
      name: 'rentACarReservation',
      component: () => import('./components/rentACarReservation.vue')
    },
    {
      path: '/friends',
      name: 'friends',
      component: () => import('./components/s1_friends/friends.vue')
    },
    {
      path: '/flightReservation',
      name: 'flightReservation',
      component: () => import('./components/s1_flightReservation/flightReservation.vue')
    },
    {
      path: '/confirmationPage/:requestId',
      name: 'confirmationPage',
      component: () => import('./components/s1_flightReservation/confirmationPage.vue')
    },
    {
      path: '/hotelRoom/:hotelName/:roomNumber',
      name: 'hotelRoom',
      component: () => import('./components/hotelRoom.vue')
    },
    {
      path: '/CompanyProfile',
      name: 'CompanyProfile',
      component: () => import('./components/s1_flightCompany/CompanyProfile.vue')
    },
    {
      path: '/flightReservationDetails/:reservationId',
      name: 'flightReservationDetails',
      component: () => import('./components/s1_flightReservation/flightReservationDetails.vue')
    },
    {
      path: '/FirstLogin',
      name: 'FirstLogin',
      component: () => import('./components/FirstLogin.vue')
    },
    {
      path: '/invites',
      name: 'invites',
      component: () => import('./components/s1_flightReservation/invites.vue')
    },
  ]
})
