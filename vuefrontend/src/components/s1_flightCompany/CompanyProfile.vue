<template>
    <div id = "CompanyProfile">
        <navbar></navbar>
        <h1>{{company.name}}</h1>
        <br>

        <div class = "centered" style="margin-left:8.4%; margin-right:8.4%" >
             <nav>
                <div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
                      <a @click="selectTab(3)" id="info" class="nav-item nav-link active"  data-toggle="tab" role="tab" aria-controls="nav-profile" aria-selected="false">Information</a>
                    <a @click="selectTab(1)" id="flights" class="nav-item nav-link" data-toggle="tab" role="tab" aria-controls="nav-home" aria-selected="true">Flights</a>
                    <a @click="selectTab(2)" id="quick" class="nav-item nav-link"  data-toggle="tab" role="tab" aria-controls="nav-profile" aria-selected="false">Quick Reservations</a>
                </div>
            </nav>
        </div>
        
        <div  class="tab-content py-3 px-3 px-sm-0 container" id="nav-tabContent" style="min-height: 650px;">

            <div v-if="currentTab == 1">
                <allFlights :idCompany = "this.companyId"></allFlights>
            </div>

            <div  v-if="currentTab == 2" id = "quickReservations" class="centered col-lg-10" role="tabpanel" aria-labelledby="quick">
                <quickReservations :idCompany = "this.companyId"></quickReservations>
            </div>


            <div  v-if="currentTab == 3" id = "info" class="centered col-lg-10" role="tabpanel" aria-labelledby="info">
                <br><br>
                <div class = "row" style="width: 1100px">
                    <div class="span4" style=" width: 100%;">
                        <img style="height: 350px; width: 400px;" class="rounded float-right" src="../../assets/flightCompany.jpg"/>
                        <div class="content-heading"><h1>Rating: {{company.rating}}</h1></div>
                        <p>Address: {{company.address}}</p><br>
                        <p style="text-align: justify; padding-left: 2%; padding-right: 40%;">{{company.description}}</p>
                        <p style="text-align: justify; padding-left: 2%; padding-right: 40%;">{{company.laguageInfo}}</p>
                        <p style="text-align: justify; padding-left: 2%; padding-right: 40%;">{{company.seatsInfo}}</p>
                    </div>
                </div>
                <br>
                <h1>Airports</h1><br>
                <div class = "row">
                    <table class = "table table-hover col" style="width: 1000px" border="1">
                        <thead>
                        <tr>
                            <th scope="col">City</th>
                            <th scope="col">Name</th>
                            <th scope="col">Address</th>
                            <th scope="col">Description</th>
                            <th scope="col">Coordinates</th>
                        </tr>
                        </thead>
                        <tr v-for="destination in company.airports" :key="destination.id" @click="showOnMap(destination.longitude, destination.latitude)">  
                            <td >{{destination.cityName}}</td>
                            <td >{{destination.name}}</td>
                            <td>{{destination.address}}</td>
                            <td >{{destination.description}}</td>
                            <td> {{destination.latitude}}, {{destination.longitude}} </td>
                        </tr>
                    </table>
                     <div class="col-2">
                        <yandex-map
                        :coords="[this.latitude,this.longitude]"
                        zoom="14"
                        style="width:320px;height:280px;"
                        :controlss="['zoomControl']"
                        map-type="hybrid"
                        >
                        <ymap-marker
                        marker-id="1"
                        marker-type="placemark"
                        :coords="[this.latitude,this.longitude]"
                        :marker-fill="{color: '#0E4779', opacity: 0.5}"
                        :marker-stroke="{color: '#0E4779',width: 4}"
                        ></ymap-marker>
                    </yandex-map>
                    </div>
                </div>
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
        currentTab: 3,
        longitude: 0,
        latitude: 0
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
        showOnMap: function(y,x){
            this.latitude = x;
            this.longitude =y;
        } 
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

.span4 .img-right {
    float: right;
}
.span4 img {
    margin-right: 10px;
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
