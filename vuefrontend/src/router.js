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
      path: '/front/about',
      name: 'about',

      component: () => import('./views/About.vue')
    },
    {
      path: '/front/searchVehicle',
      name: 'searchVehicle',
      component: () => import('./components/searchVehicle.vue')
    },
    {
      path: '/front/userProfile',
      name: 'userProfile',
      component: () => import('./components/userProfile.vue')
    },
    {
      path: '/front/flightCompanyProfile',
      name: 'flightCompanyProfile',
      component: () => import('./components/flightCompanyProfile.vue')
    },
    {
      path: '/front/rentacarprofile',
      name: 'rentacarprofile',
      component: () => import('./components/rentacarprofile.vue')
    },
    {
      path: '/front/flight',
      name: 'flight',
      component: () => import('./components/flight.vue')
    },
    {
      path: '/front/searchFlights',
      name: 'searchFlights',
      component: () => import('./components/searchFlights.vue')
    },
    {
      path: '/front/searchRentACarCompany',
      name: 'searchRentACarCompany',
      component: () => import('./components/searchRentACarCompany.vue')
    },
    {
      path: '/front/hotelProfile/:hotelName',
      name: 'hotelProfile',
      component: () => import('./components/hotelProfile.vue')
    },
    {
      path: '/front/unregisteredHotelProfile/:hotelName',
      name: 'unregisteredHotelProfile',
      component: () => import('./components/unregisteredHotelProfile.vue')
    },
    {
      path: '/front/unregisteredHotelRoom/:hotelName/:roomNumber',
      name: 'unregisteredHotelRoom',
      component: () => import('./components/unregisteredHotelRoom.vue')
    },
    {
      path: '/front/hotelAdminProfile',
      name: 'hotelAdminProfile',
      component: () => import('./components/hotelAdminProfile.vue')
    },
    {
      path: '/front/loginPage',
      name: 'loginPage',
      component: () => import('./components/loginPage.vue')
    },
    {
      path: '/front/registerPage',
      name: 'registerPage',
      component: () => import('./components/registerPage.vue')
    },
    {
      path: '/front/addHotel',
      name: 'addHotel',
      component: () => import('./components/addHotel.vue')
    },
    {
      path: '/front/addVehicle',
      name: 'addbehicle',
      component: () => import('./components/addvehicle.vue')
    },
    {
      path: '/front/index',
      name: 'registeredFrontPage',
      component: () => import('./components/registeredFrontPage.vue')
    },
    {
      path: '/front/searchHotels',
      name: 'searchHotels',
      component: () => import('./components/searchHotels.vue')
    },
    {
      path: '/front/flightAdmin',
      name: 'flightAdmin',
      component: () => import('./components/flightAdmin.vue')
    },
    {
      path: '/front/flightForAdmin',
      name: 'flightForAdmin',
      component: () => import('./components/flightForAdmin.vue')
    }
    ,
    {
      path: '/front/systemAdminPage',
      name: 'systemAdminPage',
      component: () => import('./components/SystemAdmin/systemAdminPage.vue')
    },
    {
      path: '/front/RAindex',
      name: 'rentACarAdminIndex',
      component: () => import('./components/rentACarAdminIndex.vue')
    },
    {
      path: '/front/searchFlightCompany',
      name: 'searchFlightCompany',
      component: () => import('./components/searchFlightCompany.vue')

    },
    {
      path: '/front/hotelAdminPage',
      name: 'hotelAdminPage',
      component: () => import('./components/HotelAdmin/hotelAdminPage.vue')
    },
    {
      path:'/front/rentacar',
      name: 'rentACarReservation',
      component: () => import('./components/rentACarReservation.vue')
    },
    {
      path: '/front/friends',
      name: 'friends',
      component: () => import('./components/s1_friends/friends.vue')
    },
    {
      path: '/front/flightReservation',
      name: 'flightReservation',
      component: () => import('./components/s1_flightReservation/flightReservation.vue')
    },
    {
      path: '/front/confirmationPage/:requestId',
      name: 'confirmationPage',
      component: () => import('./components/s1_flightReservation/confirmationPage.vue')
    },
    {
      path: '/front/hotelRoom/:hotelName/:roomNumber',
      name: 'hotelRoom',
      component: () => import('./components/hotelRoom.vue')
    },
    {
      path: '/front/CompanyProfile',
      name: 'CompanyProfile',
      component: () => import('./components/s1_flightCompany/CompanyProfile.vue')
    },
    {
      path: '/front/flightReservationDetails/:reservationId',
      name: 'flightReservationDetails',
      component: () => import('./components/s1_flightReservation/flightReservationDetails.vue')
    },
    {
      path: '/front/FirstLogin',
      name: 'FirstLogin',
      component: () => import('./components/FirstLogin.vue')
    },
    {
      path: '/front/invites',
      name: 'invites',
      component: () => import('./components/s1_flightReservation/invites.vue')
    },
  ]
})
