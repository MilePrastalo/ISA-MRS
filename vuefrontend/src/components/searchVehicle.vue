<template>
  <div id="searchVehicle">
    <div>
        <table>
            <tr>
                <td>Producer</td>
                <td><select v-model="producer" name="Producer" id="producer">
                        <option v-for="producer in producers" v-bind:value=producer :key="producer.id">{{producer}}</option>
                    </select></td>
                <td>Type</td>
                <td><select v-model="type" name="Type" id="type">
                        <option v-for="type in types" v-bind:value=type :key="type.id">{{type}}</option>
                    </select></td>
            </tr>
            <tr>
                <td>Newer than</td>
                <td><input v-model="newer" type="number" id="newer"></td>
                <td>Older than</td>
                <td><input v-model="older" type="number" id="older"></td>
            </tr>
            <tr>
                <td>Number of seats</td>
                <td><input v-model="seats" type="number" id="older"></td>
            </tr>
            <tr>
                <td>Price from</td>
                <td><input v-model="priceFrom" type="number" id="priceFrom"></td>
                <td>Price to</td>
                <td><input v-model="priceTo" type="number" id="priceTo"></td>
                <td><Button v-on:click="search">Search</Button></td>
            </tr>
        </table>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "searchVehicle",
  props:["iCompany","idateFrom","idateTo"],
  components: {
  },
  data: function () {
  return {
    cars:[],
    producer:"",
    priceFrom:"",
    priceTo:"",
    newer:"",
    older:"",
    type:"",
    seats:0,
    producers:["Mercedes","Audi","BMW"],
    types:["Sedan","Jeep"],
    company : this.iCompany,
    dateFrom:this.idateFrom,
    dateTo:this.idateTo
  }
},
mounted(){
    var responseData;
      var getJwtToken = function() {
    return localStorage.getItem("jwtToken");
  };
    axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
    axios.get("/api/getProducers",)
        .then(response => {
            this.producers = response.data
            this.producers.push("");
          }); 
    axios.get("/api/getTypes")
        .then(response => {
            this.types = response.data
            this.types.push("");
          });  
    },
    methods:{
        search: function(){
          var data = {};
          var parent = this;
          console.log(this.company);
            axios.post("/api/vehicleSearch",{seats:this.seats,dateFrom:this.dateFrom,dateTo:this.dateTo,company:this.company, producer : this.producer, priceFrom: this.priceFrom, priceTo:this.priceTo, newer:this.newer, older:this.older,type:this.type})
            .then(response => {
              console.log(response.data);
                parent.$emit("searched",response.data);
            }); 
        }     
    }
}

</script>

<style scoped>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
