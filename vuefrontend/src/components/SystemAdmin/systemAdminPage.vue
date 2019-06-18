<template>
   <div id = "systemAdminProfile">
       <navbar/>
       <div class="container">
            <br>
             <div class="row centered"> 
                <h1>System Admin Profile</h1>
            </div>
             <br>
             <div class="row centered">
                <ul class="nav nav-tabs centered">
                    <li class="nav-item">
                        <a  class="nav-link" href="#" @click="selectTab(1)">Hotels</a>
                        
                    </li>
                    <li class="nav-item centered">
                        <a class="nav-link" href="#" @click="selectTab(2)">Flight Companies</a>
                    </li>
                    <li class="nav-item centered">
                        <a class="nav-link" href="#" @click="selectTab(3)">Rent A Cars</a>
                    </li>
                    <li class="nav-item centered">
                        <a class="nav-link" href="#" @click="selectTab(4)">Cities</a>
                    </li>
                    <li class="nav-item centered">
                        <a class="nav-link" href="#" @click="selectTab(5)">Add System Admin</a>
                    </li>
                </ul>
            </div>

            <div  v-if="currentTab == 1" id = "hotels"> 
                <sa-hotels class="container"></sa-hotels>
            </div> 
            <div  v-if="currentTab == 2" id = "flight companies">
                <sa-flight-companies class="container"></sa-flight-companies>
            </div>
            <div  v-if="currentTab == 3" id = "rent a cars">
                <sa-rent-a-car class="container"></sa-rent-a-car>
            </div>
            <div  v-if="currentTab == 4" id = "destinations">
                <sa-cities class="container"></sa-cities>
            </div>
            <div  v-if="currentTab == 5" id = "destinations">
                <sa-add-system-admin class="container"></sa-add-system-admin>
            </div>
        </div>
    </div>
</template>

<script>
import saHotels from  "./saHotels.vue"
import saFlightCompanies from "./saFlightCompanies"
import saRentACar from "./saRentACar"
import saCities from "./saCities"
import saAddSystemAdmin from "./saAddSystemAdmin"
import navbar from ".././navbar.vue";
import axios from "axios";

export default {
  name: "systemAdminProfile",
  components: {
      saHotels: saHotels,
      saFlightCompanies: saFlightCompanies,
      saRentACar: saRentACar,
      saCities: saCities,
      saAddSystemAdmin: saAddSystemAdmin,
      navbar
  },
  data: function () {
  return {
    systemAdmin : [],
    hotels: [],
    currentTab: 1
  }
},
mounted(){
        var getJwtToken = function() {
            return localStorage.getItem("jwtToken");
        };
        axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
        axios.get("/api/getSystemAdminProfile")
            .then(response => {
                this.systemAdmin = response.data;
            });
    },
    methods:{
        selectTab: function(tabId){
            this.currentTab = tabId;
        },
        updateFlightCompanyProfile: function(){
            var getJwtToken = function() {
              return localStorage.getItem("jwtToken");
            };
            axios.defaults.headers.post["Authorization"] = "Bearer " + getJwtToken();
            axios.post("/api/updateFlightCompanyProfile",{name:this.name, address: this.address, description: this.description})
            .then(response => {
                this.name = response.data.name
                this.address = response.data.address
                this.description = response.data.description
            });
            alert("Successfuly updated!")
        },
        loadHotels: function() {
            axios.get("/api/getAllHotels")
            .then(response => {
                this.hotels = response.data;
            })
        },
        logOut: function() {
            this.$router.push("/");
        }    
    }
}

</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-left: 5%;
}

.centered{
    margin-right: auto;
    margin-right: auto;
}
</style>
