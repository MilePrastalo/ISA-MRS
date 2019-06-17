<template>
   <div id = "destinations">

        <div class = "container">
            <br><br>
            <h2>New Airport: </h2><br><br>
            <div class = "row" style="margin-left: 20%">
                <br>
                    <form @submit="addNewDestination">
                        <table class = "centered" style="text-align: left">
                    <tr>
                        <td> City: </td>
                        <td> <select v-model="DestCity" required>
                                <option v-for="city in this.cities"  v-bind:value= city  :key=city.id>{{city.name}}</option>
                            </select>
                         </td>
                    </tr>
                     <tr>
                        <td> Name: </td>
                        <td> <input type = "text"   name="DestName"  v-model="DestName" required/> </td> 
                    </tr>
                    <tr>
                        <td> Address: </td>
                        <td> <input type = "text"   name="DestAddress"  v-model="DestAddress" required/> </td> 
                    </tr>
                    <tr>
                        <td> Description: </td>
                        <td> <textarea  rows="5" cols="22" name="DestDescription"  v-model="DestDescription" style="overflow:scroll;" required></textarea> </td> 
                    </tr>
                     <tr>
                        <td> Latitude: </td>
                        <td > <input type="text" name="DestLatitude" v-model="DestLatitude" required > </td>
                    </tr>
                    <tr>
                        <td> Longitude: </td>
                        <td > <input type="text" name="DestLongitude" v-model="DestLongitude" required> </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><button class="btn btn-primary">Add Airport</button> </td>      
                    </tr>
                    </table>
                </form>
                <div class="col">
                    <yandex-map
                    :coords="[this.DestLatitude,this.DestLongitude]"
                    zoom="14"
                    style="width:350px;height:250px;"
                    :controlss="['zoomControl']"
                    map-type="hybrid"
                    >
                    <ymap-marker
                    marker-id="1"
                    marker-type="placemark"
                    :coords="[this.DestLatitude,this.DestLongitude]"
                    :marker-fill="{color: '#0E4779', opacity: 0.5}"
                    :marker-stroke="{color: '#0E4779',width: 4}"
                    ></ymap-marker>
                </yandex-map>
                </div>
        </div>
        <div>
                <br><br>
                <h2>All Airports: </h2>
                <br>

                <div class = "row">
                <table class = "centered table" border="1">
                    <thead>
                    <tr>
                         <th scope="col">City</th>
                        <th scope="col">Name</th>
                        <th scope="col">Address</th>
                        <th scope="col">Description</th>
                        <th scope="col">Longitude</th>
                         <th scope="col">Latitude</th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tr v-for="(destination,index ) in destinations" :key="index"> 
                        <td >{{destination.cityName}}</td>
                        <td v-if="editing == true"> <input type="text" v-model="destination.name"> </td>
                        <td v-else>{{destination.name}}</td>
                        <td v-if="editing == true"> <textarea  rows="5" cols="22" name="DestAddress"  v-model="destination.address" style="overflow:scroll;"></textarea> </td> 
                        <td v-else>{{destination.address}}</td>
                        <td v-if="editing == true"> <textarea  rows="5" cols="22" name="DestDescription"  v-model="destination.description" style="overflow:scroll;"></textarea> </td> 
                        <td v-else>{{destination.description}}</td>
                        <td v-if="editing == true"> <input type="text" name="DestLongitude" v-model="destination.longitude" > </td>
                        <td v-else> {{destination.longitude}}</td>
                        <td v-if="editing == true"> <input type="text" name="DestLatitude" v-model="destination.latitude" > </td>
                        <td v-else> {{destination.latitude}} </td>
                        <td  v-if="editing == false" > <button class="btn btn-outline-primary" @click="editDestination">Edit</button></td>
                        <td v-if="editing == true"><button @click="saveChanges(destination)" class="btn btn-outline-primary">Save</button></td>
                        <td><button @click="deleteDestination(index, destination)" class="btn btn-outline-primary">Delete</button></td>
                    </tr>
                </table>
                </div>
        </div>

        </div>
          
    </div>
</template>

<script>
export default {
  name: "flightCompanyProfile",
  components: { },
  data: function () {
  return {
    DestCity: "",
    DestName: "",
    DestDescription: "",
    DestAddress: "",
    DestLatitude: "",
    DestLongitude: "",
    destinations:[],
    cities: [],
    editing: false
  }
},
mounted(){
    var getJwtToken = function() {
         return localStorage.getItem("jwtToken");
    };
    axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
    axios.get("/api/getDestinations")
        .then(response => {
            this.destinations = response.data
          }); 
    axios.get("/api/getAllCities")
        .then(response => {
            this.cities = response.data
          });  
    },
    methods:{
        addNewDestination: function(e){
            e.preventDefault()
            alert(JSON.stringify(this.DestCity));
            var newDestination = {name: this.DestName, cityId :this.DestCity.id, description: this.DestDescription, longitude: this.DestLongitude,
                                    latitude: this.DestLatitude, address: this.DestAddress};
            var getJwtToken = function() {
                return localStorage.getItem("jwtToken");
            };
            axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
             axios.post("/api/addDestination",newDestination)
            .then(response => {
                alert("New destination added!")
                this.destinations.push(newDestination);
                this.DestName = this.DestAddress = this.DestDescription = this.DestLongitude = this.DestLatitude = "";
            });
            
        },
        editDestination: function(){
            this.editing = true;
        },
        
        saveChanges: function(destination){

            if ( destination.name == "" ||  destination.coordinates == "" || destination.description == "" || destination.longitude == "" || destination.latitude == ""){
                alert("Fields can not be empty!");
                return;
            }
            var getJwtToken = function() {
                return localStorage.getItem("jwtToken");
            };
            axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
            axios.post("/api/editDestination",destination)
            .then(response => {
                alert(response.data)
            });
            this.editing = false;
        },

        deleteDestination: function(index, destination){
            var getJwtToken = function() {
                return localStorage.getItem("jwtToken");
            };
            axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
            axios.get("/api/removeDestination" + "/" + destination.id)
            .then(response => {
                alert(response.data)
                if (response.data == "success"){
                    this.destinations.splice(index,1);
                }
            });
            
        },
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
}

.destinationContainer{
    margin-left: auto;
    margin-right: auto;
    width: 1100px;
}
</style>
