<template>
   <div id = "destinations">
                <br>
                <br>
                <table>
                    <tr>
                        <td> Name: </td>
                        <td>  <input type="text" name="DestName" v-model="DestName" > </td>
                    </tr>
                    <tr>
                        <td> Coordinates: </td>
                        <td>  <input type="text" name="DestCoordinates" v-model="DestCoordinates" > </td>
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
                <h1>Destinations: </h1>
                </div>

                <div class = "row">
                <table border="1">
                    <tr>
                        <td>Name</td>
                        <td>Coordinates</td>
                        <td>Description</td>
                        <td></td>
                    </tr>
                    <tr v-for="destination in destinations" :key="destination.id">  
                    <td>{{destination.name}}</td>
                    <td>{{destination.coordinates}}</td>
                    <td>{{destination.description}}</td>
                    <td><button>Delete</button></td>
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
            var newDestination = {name:this.DestName, description: this.DestDescription, coordinates: this.DestCoordinates}
            var getJwtToken = function() {
                return localStorage.getItem('jwtToken');
            };
            alert(this.DestName);
            console.log(newDestination);
            axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
             axios.post("http://localhost:8080/api/addDestination",newDestination)
            .then(response => {
                alert("New destination added!")
            });
            
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
