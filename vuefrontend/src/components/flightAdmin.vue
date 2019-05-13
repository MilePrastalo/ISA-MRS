<template>
    <div id = "flightAdmin">

        <div class="row">
            <ul class="nav nav-tabs">
                <li class="nav-item">
                    <a  class="nav-link active" id = "profile" href="#" @click="selectTab(1)">Profile</a>
                </li>
                <li class="nav-item">
                    <a  class="nav-link" id = "companyProfile" href="#" @click="selectTab(2)">Flight Company profile</a>     
                 </li>
                <li class="nav-item">
                    <a  class="nav-link" id = "flights" href="#" @click="selectTab(3)">Flights</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id = "newFlight" href="#" @click="selectTab(4)">New Flight</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id = "destinations" href="#" @click="selectTab(5)">Destinations</a>
                </li>
                 <li class="nav-item">
                    <a class="nav-link" id = "quickTickets" href="#" @click="selectTab(6)">Quick Tickets</a>
                </li>
                 <li class="nav-item">
                    <a class="nav-link" id = "addQuickTicket" href="#" @click="selectTab(7)">Add Quick Ticket</a>
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
        <div class = "row" v-if="currentTab == 6" id = "quickReservations">
            <quickReservations></quickReservations>
        </div>
        <div class = "row" v-if="currentTab == 7" id = "addQuickReservation">
            <addQuickReservation></addQuickReservation>
        </div>

    </div>
</template>


<script>
import userProfile from  './userProfile.vue'
import newFlight from  './newFlight.vue'
import destinations from './destinations.vue'
import allFlights from './allFlights.vue'
import flightCompanyProfile from './flightCompanyProfile.vue'
import AddQuickReservation from './addQuickReservation.vue'
import QuickReservations from './quickReservations.vue'


export default {
  name: 'flightAdmin',
  components: {
      userProfile: userProfile,
      flightCompanyProfile: flightCompanyProfile,
      allFlights: allFlights,
      newFlight: newFlight,
      destinations: destinations,
      addQuickReservation: AddQuickReservation,
      quickReservations: QuickReservations
  },
  data: function () {
    return {
        currentTab: 1,
    }
},
methods:{
        selectTab: function(tabId){

            document.getElementById("profile").className="nav-link";
            document.getElementById("companyProfile").className="nav-link";
            document.getElementById("flights").className="nav-link";
            document.getElementById("newFlight").className="nav-link";
            document.getElementById("destinations").className="nav-link";
            document.getElementById("quickTickets").className="nav-link";
            document.getElementById("addQuickTicket").className="nav-link";
            if (tabId == 1){
                document.getElementById("profile").className="nav-link active";
            }
            else if (tabId == 2){
                document.getElementById("companyProfile").className="nav-link active";
            }
            else if (tabId == 3){
                document.getElementById("flights").className="nav-link active";
            }
            else if (tabId == 4){
                document.getElementById("newFlight").className="nav-link active";
            }
            else if (tabId == 5){
                document.getElementById("destinations").className="nav-link active";
            }
            else if (tabId == 6){
                document.getElementById("quickTickets").className="nav-link active";
            }
            else if (tabId == 7){
                document.getElementById("addQuickTicket").className="nav-link active";
            }
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
