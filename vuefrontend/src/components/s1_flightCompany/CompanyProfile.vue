<template>
    <div id = "CompanyProfile">
        <navbar itype="1"></navbar>
        <h1>{{company.name}}</h1>
        <br>

        <div class = "centered" style="margin-left:8.4%; margin-right:8.4%" >
             <nav>
                <div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
                    <a @click="selectTab(1)" id="flights" class="nav-item nav-link active" data-toggle="tab" role="tab" aria-controls="nav-home" aria-selected="true">Flights</a>
                    <a @click="selectTab(2)" id="quick" class="nav-item nav-link"  data-toggle="tab" role="tab" aria-controls="nav-profile" aria-selected="false">Quick Reservations</a>
                    <a @click="selectTab(3)" id="info" class="nav-item nav-link"  data-toggle="tab" role="tab" aria-controls="nav-profile" aria-selected="false">Information</a>
                </div>
            </nav>
        </div>
        
        <div  class="tab-content py-3 px-3 px-sm-0 container" id="nav-tabContent" style="min-height: 450px;">

            <div v-if="currentTab == 1">
                <allFlights :idCompany = "this.companyId"></allFlights>
            </div>

            <div  v-if="currentTab == 2" id = "quickReservations" class="centered col-lg-10" role="tabpanel" aria-labelledby="quick">
                <quickReservations :idCompany = "this.companyId"></quickReservations>
            </div>

            <div v-if="currentTab == 3" id = "info" class="centered col-lg-10" role="tabpanel" aria-labelledby="info">
                <br><br><br>
                <div class ="mapDiv" ></div>
                <p >{{company.description}}</p>  
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
        company: {},
        currentTab: 1
    }
},
created: function(){

        this.companyId = localStorage.getItem("idCompany");
        var getJwtToken = function() {
            return localStorage.getItem('jwtToken');
        };
        axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
        axios.get("http://localhost:8080/api/getFlightCompanyProfileRegitered/"+ this.companyId)
        .then(response => {
            this.company = response.data
        });    

        
    },
methods:{
        selectTab: function(tabId){
            if (tabId == 1){
                document.getElementById("flights").className="nav-item nav-link active";
                document.getElementById("quick").className="nav-item nav-link";
                document.getElementById("info").className="nav-item nav-link";
            }
            else if (tabId == 2){
                document.getElementById("quick").className="nav-item nav-link active";
                document.getElementById("flights").className="nav-item nav-link";
                document.getElementById("info").className="nav-item nav-link";
            }
            else if (tabId == 3){
                document.getElementById("info").className="nav-item nav-link active";
                document.getElementById("quick").className="nav-item nav-link";
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
.mapDiv{
    width: 300px;
    height: 300px; 
    background-color: gray; 
    float: right;
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
