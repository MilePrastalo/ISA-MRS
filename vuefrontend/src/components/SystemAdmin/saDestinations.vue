<template>
   <div id = "saDestinations" class="container">

            <br>
             <div class="row"> 
            </div>
             <br>
             <div class="row">
                <ul class="nav nav-tabs">
                    <li class="nav-item">
                        <a  class="nav-link" href="#" @click="selectTab(1)">All Destinations</a>
                    </li>
                    <li class="nav-item">
                        <a  class="nav-link" href="#" @click="selectTab(2)">Add Destination</a>
                        
                    </li>
                </ul>
            </div>
            
            <div v-if="currentTab == 1">
                <br>
                <br>
                <table class="table">
                    <thead class="thead-dark">
                    
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Coordinates</th>
                        <th>Description</th>
                        <th>Options</th>
                    </tr>
                    </thead>
            <tr v-for="d in this.destinations" :key="d.id">  
                <td>{{d.id}}</td>
                <td>{{d.name}}</td>
                <td>{{d.coordinates}}</td>
                <td>{{d.description}}</td>
                <td><button class="btn-primary">Edit</button></td>
            </tr>
            </table>
            </div>
            <div v-if="currentTab == 2">
                <table>
                <tr>
                    <td><b> Destination Name: </b></td>
                    <td>  <input type="text" v-model="newDestination.name" > </td>
                </tr>
                <tr>
                    <td><b> Coordinates: </b></td>
                    <td>  <input type="text" v-model="newDestination.coordinates" > </td>
                </tr>
                <tr>
                    <td><b>Description: </b></td>
                    <td> <textarea  rows="5" cols="22" name="description"  v-model="newDestination.description" style="overflow:scroll;"></textarea> </td>        
                </tr>
                <tr>
                    <td>  </td>
                    <td><button v-on:click="addDestination()" class="btn-primary">Add Destination</button> </td>      
                </tr>
            </table>   
            </div>
        </div>
</template>

<script>

export default {
  name: 'saDestinations',
  components: {
  },
  data: function () {
  return {
    destinations: [],
      newDestination: {},
    currentTab: 1
  }
},
mounted(){
        var getJwtToken = function() {
            return localStorage.getItem('jwtToken');
        };
        axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
        axios.get("http://localhost:8080/api/getAllDestinations")
            .then(response => {
                this.destinations = response.data;
            })
    },
    methods:{
        selectTab: function(tabId){
            this.currentTab = tabId;
        },
        addDestination: function() {
            if(this.newDestination.name == "") {
                alert("You must enter destination name");
                return;
            }
            if(this.newDestination.coordinates == "") {
                alert("You must enter coordinates");
                return;
            }
            for(let d in this.destinations) {
                if(this.destinations[d].name == this.newDestination.name) {
                    alert("Destination with given name already exists.");
                    return;
                }
                if(this.destinations[d].coordinates == this.newDestination.coordinates) {
                    alert("Destination with given coordinates already exists.");
                    return;
                }
            }
            axios.post("http://localhost:8080/api/sysAdminAddDestination",this.newDestination)
            .then(response => {
                if(response.data == -1) {
                    alert("Destination with given name already exists.");
                    return;
                } else if(response.data == -2) {
                    alert("Destination with given coordinates already exists.");
                    return;
                } else if(response.data == -3) {
                    alert("There was an error with authority.");
                    return;
                } else if(response.data == -4) {
                    alert("You must enter destination name");
                return;
                } else if(response.data == -5) {
                    alert("You must enter coordinates");
                return;
                } else {
                    this.newDestination.id = response.data;
                    this.destinations.push(this.newDestination);
                    this.newDestination = {};
                    alert("Destination has been successfully added.");
                    return;
                }
            })
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
