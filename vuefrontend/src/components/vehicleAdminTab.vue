<template>
    <div id="vehicleAdmin">
        <addvehicle v-if="selected == 1"></addvehicle>
        <editVehicle v-on:vedited="veditedDraw" v-if="selected == 2" :iid="id" v-bind:iname="name" v-bind:imaker="maker" v-bind:itype="type" :iyear="year" v-bind:iprice="price"></editVehicle>
        <table>
            <tr>
                <th>Name</th>
                <th>Maker</th>
                <th>Type</th>
                <th>Year</th>
                <th>Price</th>
                <th>Edit</th>
                <th>Remove</th>
            </tr>
            <tr v-for="car in cars" :key="car.id">
                <td>{{car.name}}</td>
                <td>{{car.maker}}</td>
                <td>{{car.type}}</td>
                <td>{{car.year}}</td>
                <td>{{car.price}}</td>
                <td><Button @click="editVehicleMethod(car.id,car.name,car.maker,car.type,car.year,car.price)">Edit</Button></td>
                <td><Button>Remove</Button></td>
            </tr>
        </table>
    </div>
</template>

<script>

import addvehicle from "./addvehicle.vue";
import editVehicle from "./editVehicle.vue";

export default {
  name: 'vehicleAdmin',
  components: {
    addvehicle,
    editVehicle
  },
  data: function(){
      return {
          selected : 1,
          cars:[],
          id:"",
          name:"aa",
          maker:"aaa",
          type:"",
          year:"",
          price:0
      }
  },
  mounted(){
      var getJwtToken = function() {
            return localStorage.getItem('jwtToken');
        };
        axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
        axios.get("http://localhost:8080/api/getCarsByAdmin")
            .then(response => {
                this.cars = response.data;
            }); 
  },
  methods : {
      editVehicleMethod : function(id_,name_,maker_,type_,year_,price_){
          this.selected=2;
          this.id = id_;
          this.name = name_;
          this.maker = maker_;
          this.type = type_;
          this.year = year_;
          this.price = price_;
      },
      veditedDraw : function(){
          console.log("Called redraw");
          var getJwtToken = function() {
            return localStorage.getItem('jwtToken');
        };
        axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
        axios.get("http://localhost:8080/api/getCarsByAdmin")
            .then(response => {
                this.cars = response.data;
            }); 
      }
  }
}

</script>

<style scoped>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
table{
    border: 1px solid darkgray;
}
th{
    border: 1px solid darkgray;
}
</style>