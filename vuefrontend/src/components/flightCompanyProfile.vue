<template>
   <div id = "flightCompanyProfile">

       <div class = "companyProfile">
           <br><br>
            <h2> Company information: </h2>
            <br>
            <div class = "centered">
                    
                <table class = "centered"  style="text-align: left">
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
                        <td><button class="btn btn-primary" v-on:click="updateFlightCompanyProfile()">Edit</button> </td>      
                    </tr>
                </table>
            </div>
       </div>
    </div>
</template>

<script>
import axios from "axios";

export default {
  name: "flightCompanyProfile",
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
            return localStorage.getItem("jwtToken");
        };
        axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
        axios.get("/api/getFlightCompanyProfile")
            .then(response => {
                this.name = response.data.name
                this.address = response.data.address
                this.description = response.data.description
            });
    },
    methods:{
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
        }    
    }
}

</script>

<style scopped>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

.companyProfile{
    margin-left: auto;
    margin-right: auto;
}
.centered{
    margin-left: auto;
    margin-right: auto;
}
</style>
