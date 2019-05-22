<template>
   <div id = "systemAdminProfile">
       <navbar/>
            <br>
             <div class="row"> 
                <h1>System Admin Profile</h1>
            </div>
             <br>
             <div class="row">
                <ul class="nav nav-tabs">
                    <li class="nav-item">
                        <a  class="nav-link active" href="#" @click="selectTab(1)">Profile</a>
                    </li>
                    <li class="nav-item">
                        <a  class="nav-link" href="#" @click="selectTab(2)">Hotels</a>
                        
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#" @click="selectTab(3)">Flight Companies</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#" @click="selectTab(4)">Rent A Cars</a>
                    </li>
                </ul>
            </div>
            
            <div v-if="currentTab == 1" id = "profile">
                <br>
                <br>
                <table>
                    <tr>
                        <td> Username </td>
                        <td>  <input type="text" name="systemAdmin.username" v-model="systemAdmin.username"> </td>
                    </tr>
                    <tr>
                        <td> First name: </td>
                        <td>  <input type="text" name="systemAdmin.firstName" v-model="systemAdmin.firstName" > </td>
                    </tr>
                    <tr>
                        <td> Last name: </td>
                        <td> <input type="text" name="systemAdmin.lastName"  v-model="systemAdmin.lastName" > </td>        
                    </tr>
                    <tr>
                        <td> Email: </td>
                        <td> <input type="text" name="systemAdmin.email"  v-model="systemAdmin.email" > </td>        
                    </tr>
                </table>
            </div>

            <div  v-if="currentTab == 2" id = "hotels"> 
                <sa-hotels></sa-hotels>
            </div> 
            <div  v-if="currentTab == 3" id = "flight companies">
                <sa-flight-companies></sa-flight-companies>
            </div>
            <div  v-if="currentTab == 4" id = "rent a cars">
                <sa-rent-a-car></sa-rent-a-car>
            </div>
            <div  v-if="currentTab == 5" id = "change password">
            </div>
            <div  v-if="currentTab == 6" id = "log out" v-on="logOut()">
            </div>
        </div>
</template>

<script>
import saHotels from  './saHotels.vue'
import saFlightCompanies from "./saFlightCompanies"
import saRentACar from "./saRentACar"
import navbar from ".././navbar.vue";

export default {
  name: 'systemAdminProfile',
  components: {
      saHotels: saHotels,
      saFlightCompanies: saFlightCompanies,
      saRentACar: saRentACar,
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
            return localStorage.getItem('jwtToken');
        };
        axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
        axios.get("http://localhost:8080/api/getSystemAdminProfile")
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
              return localStorage.getItem('jwtToken');
            };
            axios.defaults.headers.post['Authorization'] = "Bearer " + getJwtToken();
            axios.post("http://localhost:8080/api/updateFlightCompanyProfile",{name:this.name, address: this.address, description: this.description})
            .then(response => {
                this.name = response.data.name
                this.address = response.data.address
                this.description = response.data.description
            });
            alert("Successfuly updated!")
        },
        loadHotels: function() {
            axios.get("http://localhost:8080/api/getAllHotels")
            .then(response => {
                this.hotels = response.data;
            })
        },
        logOut: function() {
            this.$router.push('/');
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
  margin-left: 5%;
}
</style>
