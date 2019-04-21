<template>
   <div id = "allFlights">

        <br><br>

        <div class = "row">
            <table style="text-align: left">
                <tr>
                    <td>Start destination:</td>
                    <td><input v-model="startDestination" type="text"></td>
                </tr>
                <tr>
                    <td>End destination:</td>
                    <td><input v-model="endDestination" type="text"></td>
                </tr>
                <tr>
                    <td>Depart time:</td>
                    <td><input v-model="startDate" type="date"></td>
                </tr>
                 <tr>
                    <td>Return time:</td>
                    <td><input v-model="endDate" type="date"></td>
                </tr>
                 <tr>
                    <td>Min price:</td>
                    <td><input v-model="MinPrice" type="number"></td>
                </tr>
                 <tr>
                    <td>Max price:</td>
                    <td><input v-model="MaxPrice" type="number"></td>
                </tr>
                 <tr style="text-align: center">
                     <td><div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" class="custom-control-input" id="eco" checked @click="checkedClass(1)" name="inlineDefaultRadiosExample">
                        <label class="custom-control-label" for="eco">Economic class</label>
                        </div>
                     </td>
                     <td><div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" class="custom-control-input" id="bus" @click="checkedClass(2)" name="inlineDefaultRadiosExample">
                        <label class="custom-control-label" for="bus">Business class</label>
                        </div>
                     </td>
                     <td><div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" class="custom-control-input" id="first" @click="checkedClass(3)" name="inlineDefaultRadiosExample">
                        <label class="custom-control-label" for="first">First class</label>
                        </div>
                     </td>
                </tr>
                <tr>
                    <td><button @click="search()">Search</button> </td> 
                </tr>
            </table>
        </div>

        <br>
        <div class="row"> 
            <h2> All flights: </h2>
        </div>
        <br>

        <div class = "row">
            <table border="1">
                <tr>
                    <td>Start destination</td>
                    <td>End destination</td>
                    <td>Start date</td>
                    <td>End date</td>
                    <td>Flight duration</td>
                    <td>Flight Length</td>
                    <td>Economic price</td>
                    <td>Buisiness price</td>
                    <td>First class price</td>
                    <td></td>
                </tr>
                <tr v-for="flight in flights" :key="flight.id">  
                    <td>{{flight.startDestination.name}}</td>
                    <td>{{flight.endDestination.name}}</td>
                    <td>{{flight.startDate}}</td>
                    <td>{{flight.endDate}}</td>
                    <td>{{flight.flightDuration}}</td>
                    <td>{{flight.flightLength}}</td>
                    <td>{{flight.economicPrice}}</td>
                    <td>{{flight.buisinessPrice}}</td>
                    <td>{{flight.firstClassPrice}}</td>
                    <td><button>Edit</button></td>
                    <td><button>Delete</button></td>
                </tr>              
            </table>  
        </div>   
    </div>
</template>

<script>
export default {
  name: 'allFlights',
  components: {},
  data: function () {
  return {
      startDestination: "",
     endDestination: "",
     startDate: "",
     endDate: "",
     flightDuration: "",
     flightLength: "",
     MinPrice: "",
     MaxPrice: "",
      check: 1,
      flights: []
  }
},
mounted(){
        var getJwtToken = function() {
                return localStorage.getItem('jwtToken');
            };
        axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
        axios.get("http://localhost:8080/api/getFlights")
        .then(response => {
            this.flights = response.data
          });  
    },
    methods:{ 
        checkedClass: function(option){
            this.check = option;
        },
        search: function(){
            var MineconomicPrice = 0;
            var MaxeconomicPrice = 0;
            var MinbuisinesssPrice = 0;
            var MaxbuisinesssPrice = 0;
            var MinfirstClassPrice = 0;
            var MaxfirstClassPrice = 0;
            if (this.check == 1){
                MineconomicPrice = this.MinPrice;
                MaxeconomicPrice = this.MaxPrice;
            }
            if (this.check == 2){
                MinbuisinesssPrice = this.MinPrice;
                MaxbuisinesssPrice = this.MaxPrice;
            }
            if (this.check == 3) {
                MinfirstClassPrice = this.MinPrice;
                MaxfirstClassPrice = this.MaxPrice;
            }
            var flightForSearch = {startDestination: this.startDestination, endDestination: this.endDestination, startDate: this.startDate, endDate: this.endDate,
            minEconomic : MineconomicPrice, minBusiness: MinbuisinesssPrice, minFirstClass :MinfirstClassPrice,
            maxEconomic: MaxeconomicPrice, maxBusiness: MaxbuisinesssPrice, maxFirstClass:MaxfirstClassPrice,
            flightDuration: this.flightDuration, flightLength:  this.flightLength }
            var getJwtToken = function() {
                return localStorage.getItem('jwtToken');
            };
            axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
            axios.post("http://localhost:8080/api/flightsInCompany",flightForSearch)
            .then(response => {
                this.flights = response.data
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
