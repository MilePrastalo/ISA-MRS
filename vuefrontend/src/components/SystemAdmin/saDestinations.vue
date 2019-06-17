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
                <div v-if="editing == false">
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
                <td><button class="btn-primary" v-on:click="editDestination(d)">Edit</button></td>
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
            <div v-if="editing == true">
                <table>
                    <tr>
                    <td><b> Destination Name: </b></td>
                    <td>  <input type="text" v-model="ed.name" > </td>
                </tr>
                <tr>
                    <td><b> Coordinates: </b></td>
                    <td>  <input type="text" v-model="ed.coordinates" > </td>
                </tr>
                <tr>
                    <td><b>Description: </b></td>
                    <td> <textarea  rows="5" cols="22" name="description"  v-model="ed.description" style="overflow:scroll;"></textarea> </td>        
                </tr>
                    <tr>
                        <td><button v-on:click="saveDestination()" class="btn-primary">Save</button></td>
                        <td><button v-on:click="cancelEditing()" class="btn-primary">Cancel</button></td>
                    </tr>
                </table>
            </div>
            </div>
        </div>
</template>

<script>

export default {
  name: "saDestinations",
  components: {
  },
  data: function () {
  return {
    destinations: [],
    newDestination: {},
    ed: {},
    cancelED: {},
    currentTab: 1,
    editing: false
  }
},
mounted(){
        var getJwtToken = function() {
            return localStorage.getItem("jwtToken");
        };
        axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
        axios.get("/api/getAllDestinations")
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

            axios.post("/api/sysAdminAddDestination",this.newDestination)
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
        },
        editDestination: function(id) {
            this.ed = id;
            this.cancelED.name = id.name;
            this.cancelED.coordinates = id.coordinates;
            this.cancelED.description = id.description;
            this.editing = true;
        },
        saveDestination: function(){
            if(this.ed.name == "") {
                alert("You must enter destination name");
                return;
            }
            if(this.ed.coordinates == "") {
                alert("You must enter coordinates");
                return;
            }

            for(let d in this.destinations) {
                if(this.destinations[d].name == this.ed.name && this.destinations[d].id != this.ed.id) {
                    alert("Destination with given name already exists here.");
                        return;
                    }
                if(this.destinations[d].coordinates == this.ed.coordinates && this.destinations[d].id != this.ed.id) {
                    alert("Destination with given coordinates already exists.");
                        return;
                    }
            }

            axios.post("/api/sysAdminEditDestination",this.ed)
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
                } else if(response.data == -6) {
                    alert("Destination with given ID does not exist.");
                return;
                } else {

                    alert("Destination has been successfully edited.");
                    return;
                }
            })

            this.editing = false;
        },
        cancelEditing: function() {
            this.ed.name = this.cancelED.name;
            this.ed.coordinates = this.cancelED.coordinates;
            this.ed.description = this.cancelED.description;
            this.editing = false;
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
</style>
