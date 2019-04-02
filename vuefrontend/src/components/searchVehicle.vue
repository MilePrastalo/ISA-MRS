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
                <td>Price from</td>
                <td><input v-model="priceFrom" type="number" id="priceFrom"></td>
                <td>Price to</td>
                <td><input v-model="priceTo" type="number" id="priceTo"></td>
                <td><Button v-on:click="search">Search</Button></td>
            </tr>
        </table>
    </div>
    <div>
        <table >
            <tr v-for="car in cars" :key="car.name">  
                <td>{{car.name}}</td>
                <td>{{car.maker}}</td>
                <td>{{car.year}}</td>
                <td>{{car.price}}</td>
            </tr>
        </table>
    </div>
  </div>
</template>

<script>

export default {
  name: 'searchVehicle',
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
    producers:["Mercedes","Audi","BMW"],
    types:["Sedan","Jeep"]
  }
},
mounted(){
    var responseData;
    axios.get("http://localhost:8080/api/getProducers")
        .then(response => {
            this.producers = response.data
          }); 
    axios.get("http://localhost:8080/api/getTypes")
        .then(response => {
            this.types = response.data
          });  
    },
    methods:{
        search: function(){
            axios.post("http://localhost:8080/api/vehicleSearch",{producer : this.producer, priceFrom: this.priceFrom, priceTo:this.priceTo, newer:this.newer, older:this.older,type:this.type})
            .then(response => {
                this.cars = response.data
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
