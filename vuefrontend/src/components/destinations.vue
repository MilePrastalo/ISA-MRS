<template>
   <div id = "destinations">
                <br>
                <div class = "row">
                <h2>New destination: </h2>
                </div>
                <br>
                <table  style="text-align: left">
                    <tr>
                        <td> Name: </td>
                        <td> <input type="text" name="DestName" v-model="DestName" > </td>
                    </tr>
                    <tr>
                        <td> Coordinates: </td>
                        <td > <input type="text" name="DestCoordinates" v-model="DestCoordinates" > </td>
                    </tr>
                    <tr>
                        <td> Description: </td>
                        <td> <textarea  rows="5" cols="22" name="DestDescription"  v-model="DestDescription" style="overflow:scroll;"></textarea> </td> 

                    </tr>
                    <tr>
                        <td></td>
                        <td><button v-on:click="addNewDestination()">Add destination</button> </td>      
                    </tr>
                </table>
                <br>
                <div class = "row">
                <h2>All destinations: </h2>
                </div>
                <br>

                <div class = "row">
                <table border="1">
                    <tr>
                        <td>Name</td>
                        <td>Coordinates</td>
                        <td>Description</td>
                        <td></td>
                    </tr>
                    <tr v-for="(destination,index ) in destinations" :key="index">  
                        <td v-if="editing == true"> <input type="text" name="DestName" v-model="destination.name" > </td>
                        <td v-else>{{destination.name}}</td>
                        <td v-if="editing == true"> <input type="text" name="DestCoordinates" v-model="destination.coordinates" > </td>
                        <td v-else> {{destination.coordinates}} </td>
                        <td v-if="editing == true"> <textarea  rows="5" cols="22" name="DestDescription"  v-model="destination.description" style="overflow:scroll;"></textarea> </td> 
                        <td v-else>{{destination.description}}</td>
                        <td  v-if="editing == false" @click="editDestination"><button>Edit</button></td>
                        <td v-if="editing == true"><button @click="saveChanges(destination)">Save</button></td>
                        <td><button @click="deleteDestination(index, destination)">Delete</button></td>
                    </tr>
                </table>
                </div>  
        </div>
</template>

<script>
export default {
  name: 'flightCompanyProfile',
  components: { },
  data: function () {
  return {
    DestName: "",
    DestCoordinates: "",
    DestDescription: "",
    destinations:[],
    editing: false
  }
},
mounted(){
    var getJwtToken = function() {
         return localStorage.getItem('jwtToken');
    };
    axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
    axios.get("http://localhost:8080/api/getDestinations")
        .then(response => {
            this.destinations = response.data
          });  
    },
    methods:{
        addNewDestination: function(){
            if ( this.DestName == "" ||  this.DestCoordinates == "" || this.DestDescription == ""){
                alert("Fields can not be empty!");
                return;
            }
            var newDestination = {name:this.DestName, description: this.DestDescription, coordinates: this.DestCoordinates}
            var getJwtToken = function() {
                return localStorage.getItem('jwtToken');
            };
            console.log(newDestination);
            axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
             axios.post("http://localhost:8080/api/addDestination",newDestination)
            .then(response => {
                alert("New destination added!")
                this.destinations.push(response.data);
                this.DestName = this.DestCoordinates = this.DestDescription = "";
            });
            
        },
        editDestination: function(){
            this.editing = true;
        },
        
        saveChanges: function(destination){

            if ( this.DestName == "" ||  this.DestCoordinates == "" || this.DestDescription == ""){
                alert("Fields can not be empty!");
                return;
            }
            var getJwtToken = function() {
                return localStorage.getItem('jwtToken');
            };
            axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
            axios.post("http://localhost:8080/api/editDestination",destination)
            .then(response => {
                alert(response.data)
            });
            this.editing = false;
        },

        deleteDestination: function(index, destination){
            var getJwtToken = function() {
                return localStorage.getItem('jwtToken');
            };
            axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
            axios.get("http://localhost:8080/api/removeDestination" + "/" + destination.id)
            .then(response => {
                alert(response.data)
            });
            this.destinations.splice(index,1);
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
</style>
