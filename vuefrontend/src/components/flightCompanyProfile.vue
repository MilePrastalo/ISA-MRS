<template>
   <div id = "flightCompanyProfile">

        <br>
        <div class="row"> 
                <h2> Company information: </h2>
        </div>
        <br>

        <div class = "row">
                
            <table  style="text-align: left">
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

    </div>
</template>

<script>
export default {
  name: 'flightCompanyProfile',
  components: {},
  data: function () {
  return {
    name: "",
    address: "",
    description: "",
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
