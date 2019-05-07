<template>
    <div id = "CompanyProfile">

        <div class="row">
            <ul class="nav nav-tabs">
                <li class="nav-item">
                    <a  class="nav-link active" href="#" @click="selectTab(1)" id = 'flights'>Flights</a>
                </li>
                <li class="nav-item">
                    <a  class="nav-link" href="#" @click="selectTab(2)" id = 'quick'>Quick Reservations</a>     
                 </li>
            </ul>
        </div>

        <div class = "row" v-if="currentTab == 1" id = "editProfile">
            <allFlights :idCompany = "this.companyId"></allFlights>
        </div>
        <div class = "row" v-if="currentTab == 2" id = "flightCompany">
            <quickReservations :idCompany = "this.companyId"></quickReservations>
        </div>

    </div>
</template>


<script>

import allFlights from '../allFlights.vue'
import QuickReservations from '../quickReservations.vue'


export default {
  name: 'CompanyProfile',
  components: {

      allFlights: allFlights,
      quickReservations: QuickReservations
  },
  data: function () {
    return {

        companyId: 0,
        currentTab: 1,
    }
},
created: function(){

        this.companyId = localStorage.getItem("idCompany");
        
    },
methods:{
        selectTab: function(tabId){
            if (tabId == 1){
                document.getElementById("flights").className="nav-link active";
                document.getElementById("quick").className="nav-link";
            }
            else if (tabId == 2){
                document.getElementById("quick").className="nav-link active";
                document.getElementById("flights").className="nav-link";
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
