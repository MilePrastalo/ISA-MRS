<template>
    <div id = "flightAdmin">

        <navbar  itype="1"/>
        <br>
        <div class = "centered" style="margin-left:8.4%; margin-right:8.4%" >
             <nav>
                <div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
                    <a @click="selectTab(2)" id="companyProfile" class="nav-item nav-link active"  data-toggle="tab" role="tab" aria-controls="nav-profile" aria-selected="false">Flight Company profile</a>
                    <a @click="selectTab(3)" id="flights" class="nav-item nav-link"  data-toggle="tab" role="tab" aria-controls="nav-profile" aria-selected="false">Flights</a>
                    <a @click="selectTab(4)" id="newFlight" class="nav-item nav-link"  data-toggle="tab" role="tab" aria-controls="nav-profile" aria-selected="false">New Flight</a>
                    <a @click="selectTab(5)" id="destinations" class="nav-item nav-link"  data-toggle="tab" role="tab" aria-controls="nav-profile" aria-selected="false">Destinations</a>
                    <a @click="selectTab(6)" id="quickTickets" class="nav-item nav-link"  data-toggle="tab" role="tab" aria-controls="nav-profile" aria-selected="false">Quick Tickets</a>
                    <a @click="selectTab(7)" id="addQuickTicket" class="nav-item nav-link"  data-toggle="tab" role="tab" aria-controls="nav-profile" aria-selected="false">Add Quick Ticket</a>
                    <a @click="selectTab(8)" id="reports" class="nav-item nav-link"  data-toggle="tab" role="tab" aria-controls="nav-profile" aria-selected="false">Reports</a>

                </div>
            </nav>
        </div>
         <div  class="tab-content py-3 px-3 px-sm-0 container" id="nav-tabContent" style="min-height: 450px;">
            <div  class="centered col-lg-10" v-if="currentTab == 2" >
                <flightCompanyProfile></flightCompanyProfile>
            </div>
            <div  v-if="currentTab == 3" >
                <allFlights></allFlights>
            </div>
            <div v-if="currentTab == 4" >
                <newFlight></newFlight>
            </div>
            <div  v-if="currentTab == 5" >
                <destinations></destinations>
            </div>
            <div v-if="currentTab == 6" >
                <quickReservations></quickReservations>
            </div>
            <div  v-if="currentTab == 7" >
                <addQuickReservation></addQuickReservation>
            </div>
            <div  v-if="currentTab == 8" >
                <flightCompanyReport></flightCompanyReport>
            </div>
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
import FlightCompanyReport from './s1_flightCompany/flightCompanyReports.vue'
import navbar from "./navbar.vue";

export default {
  name: 'flightAdmin',
  components: {
      userProfile: userProfile,
      flightCompanyProfile: flightCompanyProfile,
      allFlights: allFlights,
      newFlight: newFlight,
      destinations: destinations,
      addQuickReservation: AddQuickReservation,
      quickReservations: QuickReservations,
      flightCompanyReport: FlightCompanyReport,
      navbar: navbar
  },
  data: function () {
    return {
        currentTab: 2,
    }
},
methods:{
        selectTab: function(tabId){
            document.getElementById("companyProfile").className="nav-item nav-link";
            document.getElementById("flights").className="nav-item nav-link";
            document.getElementById("newFlight").className="nav-item nav-link";
            document.getElementById("destinations").className="nav-item nav-link";
            document.getElementById("quickTickets").className="nav-item nav-link";
            document.getElementById("addQuickTicket").className="nav-item nav-link";
            document.getElementById("reports").className="nav-item nav-link";
            if (tabId == 2){
                document.getElementById("companyProfile").className="nav-item nav-link active";
            }
            else if (tabId == 3){
                document.getElementById("flights").className="nav-item nav-link active";
            }
            else if (tabId == 4){
                document.getElementById("newFlight").className="nav-item nav-link active";
            }
            else if (tabId == 5){
                document.getElementById("destinations").className="nav-item nav-link active";
            }
            else if (tabId == 6){
                document.getElementById("quickTickets").className="nav-item nav-link active";
            }
            else if (tabId == 7){
                document.getElementById("addQuickTicket").className="nav-item nav-link active";
            }
            else if (tabId == 8){
                document.getElementById("reports").className="nav-item nav-link active";
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
}

.centered{
    margin-right: auto;
    margin-right: auto;
}
#nav-tab{
    margin-left: 5%;
    margin-right: 5%;
}

nav > .nav.nav-tabs{

  border: none;
    color:#fff;
    background:#272e38;
    border-radius:0;

}
nav > div a.nav-item.nav-link,
nav > div a.nav-item.nav-link.active
{
  border: none;
    padding: 18px 25px;
    color:#fff;
    background:#272e38;
    border-radius:0;
}

nav > div a.nav-item.nav-link.active:after
 {
  content: "";
  position: relative;
  bottom: -60px;
  left: -10%;
  border: 15px solid transparent;
  border-top-color: #428bca ;
}
.tab-content{
  background: #fdfdfd;
    line-height: 25px;
    border: 1px solid #ddd;
    border-top:5px solid #428bca;
    border-bottom:5px solid #428bca;
    padding:30px 25px;
}

nav > div a.nav-item.nav-link:hover,
nav > div a.nav-item.nav-link:focus
{
  border: none;
    background: #428bca;
    color:#fff;
    border-radius:0;
    transition:background 0.20s linear;
}
</style>
