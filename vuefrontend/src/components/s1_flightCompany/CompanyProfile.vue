<template>
    <div id = "CompanyProfile">
        <navbar></navbar>
        <h1>Company Profile</h1>
        <br>
        <div class = "centered" >
             <nav>
                <div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
                    <a @click="selectTab(1)" id="flights" class="nav-item nav-link active" data-toggle="tab" role="tab" aria-controls="nav-home" aria-selected="true">Flights</a>
                    <a @click="selectTab(2)" id="quick" class="nav-item nav-link"  data-toggle="tab" role="tab" aria-controls="nav-profile" aria-selected="false">Quick Reservations</a>
                </div>
                </nav>
        </div>
        <div class = "container">
          

        <div class = "row" v-if="currentTab == 1" id = "editProfile">
            <allFlights :idCompany = "this.companyId"></allFlights>
        </div>
        <div class = "row" v-if="currentTab == 2" id = "flightCompany">
            <quickReservations :idCompany = "this.companyId"></quickReservations>
        </div>
        </div>

    </div>
</template>


<script>

import allFlights from '../allFlights.vue'
import QuickReservations from '../quickReservations.vue'

import navbar from "../navbar.vue";
export default {
  name: 'CompanyProfile',
  components: {

      allFlights: allFlights,
      quickReservations: QuickReservations,
      navbar: navbar
  },
  data: function () {
    return {

        companyId: 0,
        currentTab: 1
    }
},
created: function(){

        this.companyId = localStorage.getItem("idCompany");
        
    },
methods:{
        selectTab: function(tabId){
            if (tabId == 1){
                document.getElementById("flights").className="nav-item nav-link active";
                document.getElementById("quick").className="nav-item nav-link";
            }
            else if (tabId == 2){
                document.getElementById("quick").className="nav-item nav-link active";
                document.getElementById("flights").className="nav-item nav-link";
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

centered{
    margin-right: auto;
    margin-left: auto;
}
.clicked{
    color: orange;
}
#nav-tab{
    margin-left:10%;
    margin-right:10%;
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
