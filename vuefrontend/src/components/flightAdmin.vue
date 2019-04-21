<template>
    <div id = "flightAdmin">

        <div class="row">
            <ul class="nav nav-tabs">
                <li class="nav-item">
                    <a  class="nav-link active" href="#" @click="selectTab(1)">Profile</a>
                </li>
                <li class="nav-item">
                    <a  class="nav-link" href="#" @click="selectTab(2)">Flight Company profile</a>     
                 </li>
                <li class="nav-item">
                    <a  class="nav-link" href="#" @click="selectTab(3)">Flights</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#" @click="selectTab(4)">New Flight</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#" @click="selectTab(5)">Destinations</a>
                </li>
            </ul>
        </div>

        <div class = "row" v-if="currentTab == 1" id = "editProfile">
            <userProfile></userProfile>
        </div>
        <div class = "row" v-if="currentTab == 2" id = "flightCompany">
            <flightCompanyProfile></flightCompanyProfile>
        </div>
        <div class = "row" v-if="currentTab == 3" id = "flights">
            <allFlights></allFlights>
        </div>
        <div class = "row" v-if="currentTab == 4" id = "newFlight">
            <newFlight></newFlight>
        </div>
        <div class = "row" v-if="currentTab == 5" id = "destinations">
            <destinations></destinations>
        </div>

    </div>
</template>


<script>
import userProfile from  './userProfile.vue'
import newFlight from  './newFlight.vue'
import destinations from './destinations.vue'
import allFlights from './allFlights.vue'
import flightCompanyProfile from './flightCompanyProfile.vue'

export default {
  name: 'flightAdmin',
  components: {
      userProfile: userProfile,
      flightCompanyProfile: flightCompanyProfile,
      allFlights: allFlights,
      newFlight: newFlight,
      destinations: destinations
  },
  data: function () {
    return {
        currentTab: 1,
    }
},
methods:{
        selectTab: function(tabId){
            this.currentTab = tabId;
        }, 
    }
}

</script>
<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-left: 5%;
}
</style>
