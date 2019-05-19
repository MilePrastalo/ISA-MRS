<template>
    <div id="vehicleAdmin">
        <h2>Vehicles</h2>
        <addvehicle v-if="selected == 1"></addvehicle>
        <editVehicle v-on:back="back" v-on:vedited="veditedDraw" v-if="selected == 2" :iid="id" v-bind:iname="name" v-bind:imaker="maker" v-bind:itype="type" :iyear="year" v-bind:iprice="price" v-bind:idateto="dateTo" v-bind:idatefrom="dateFrom"></editVehicle>
        <table class="table">
            <tr>
                <th>Name</th>
                <th>Maker</th>
                <th>Type</th>
                <th>Year</th>
                <th>Price</th>
                <th>Avaiable from</th>
                <th>Avaiable to</th>
                <th>Edit</th>
                <th>Remove</th>
            </tr>
            <tr v-for="car in cars" :key="car.id">
                <td>{{car.name}}</td>
                <td>{{car.maker}}</td>
                <td>{{car.type}}</td>
                <td>{{car.year}}</td>
                <td>{{car.price}}</td>
                <td>{{car.dateFrom}}</td>
                <td>{{car.dateTo}}</td>
                <td><Button @click="editVehicleMethod(car.id,car.name,car.maker,car.type,car.year,car.price,car.dateFrom,car.dateTo)">Edit</Button></td>
                <td><Button @click="removeVehicle(car.id)">Remove</Button></td>
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
          dateFrom:"",
          dateTo:"",
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
      editVehicleMethod : function(id_,name_,maker_,type_,year_,price_,dateFrom_,dateTo_){    
          this.selected=2;  
          this.id = id_;
          this.name = name_;
          this.maker = maker_;
          this.type = type_;
          this.year = year_;
          this.price = price_;
          this.dateFrom = dateFrom_;
          this.dateTo = dateTo_;
          console.log(this.name);
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
      },
      back:function(){
        this.selected=1;
        this.id = "";
        this.name = "";
        this.maker = "";
        this.type = "";
        this.year = "";
        this.price = "";
        this.dateFrom = "";
        this.dateTo = "";
      },
      removeVehicle:function(id){
          var getJwtToken = function() {
            return localStorage.getItem('jwtToken');
        };
        axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
        axios.delete("http://localhost:8080/api/removeCar/"+id)
            .then(response => {
                alert("Success");
                console.log(response);
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
</style>