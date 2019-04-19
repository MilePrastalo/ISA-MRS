<template>
   <div id = "flightCompanyProfile">

            <br>
             <div class="row"> 
                <h1>Flight Company Profile</h1>
            </div>
             <br>
             <div class="row">
                <ul class="nav nav-tabs">
                    <li class="nav-item">
                        <a  class="nav-link active" href="#" @click="selectTab(1)">Information</a>
                    </li>
                    <li class="nav-item">
                        <a  class="nav-link" href="#" @click="selectTab(2)">Destinations</a>
                        
                    </li>
                    <li class="nav-item">
                        <a  class="nav-link" href="#" @click="selectTab(3)">Flights</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#" @click="selectTab(4)">New Flight</a>
                    </li>
                </ul>
            </div>
            
            <div v-if="currentTab == 1" id = "editProfile">
                <br>
                <br>
                <table>
                    <tr>
                        <td> Name: </td>
                        <td>  <input type="text" name="name" v-model="name" > </td>
                    </tr>
                    <tr>
                        <td> Address: </td>
                        <td>  <input type="text" name="address" v-model="address" > </td>
                    </tr>
                    <tr>
                        <td> Description: </td>
                        <td> <textarea  rows="5" cols="22" name="description"  v-model="description" style="overflow:scroll;"></textarea> </td>        
                    </tr>
                    <tr>
                        <td>  </td>
                        <td><button v-on:click="updateFlightCompanyProfile()">Edit</button> </td>      
                    </tr>
                </table>
            </div>

            <div  v-if="currentTab == 2" id = "destinations"> 
                <destinations></destinations>
            </div> 

            <div  v-if="currentTab == 3" id = "flights">
                <all-flights></all-flights>
            </div>

            <div  v-if="currentTab == 4" id = "newFlight">
                <new-flight></new-flight>
            </div>
        </div>
</template>

<script>
import NewFlight from  './newFlight.vue'
import Destinations from './destinations.vue'
import AllFlights from './allFlights.vue'
export default {
  name: 'flightCompanyProfile',
  components: {
      newFlight: NewFlight,
      destinations: Destinations,
      allFlights: AllFlights
  },
  data: function () {
  return {
    name: "",
    address: "",
    description: "",
    currentTab: 1,
  }
},
mounted(){
        var getJwtToken = function() {
            return localStorage.getItem('jwtToken');
        };
        axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
        axios.get("http://localhost:8080/api/getFlightCompanyProfile")
            .then(response => {
                this.name = response.data.name
                this.address = response.data.address
                this.description = response.data.description
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
