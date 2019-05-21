<template>
   <div id = "allFlights">

        <br><br>

        <div class = "row">
            <table class = "centered" style="text-align: left">
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
                    <td><button class="btn btn-primary" @click="search()">Search</button> </td> 
                </tr>
            </table>
        </div>

        <br>
            <h2> All flights: </h2>
        <br>

        <div class = "row">
            <table border="1" class = 'table'>
                <thead class="thead-dark">
                    <tr>
                    <th scope="col">Start destination</th>
                    <th scope="col">End destination</th>
                    <th scope="col">Start date</th>
                    <th scope="col">End date</th>
                    <th scope="col">Flight Duration</th>
                    <th scope="col">Flight Length</th>
                    <th scope="col">Economic price</th>
                    <th scope="col">Business price</th>
                    <th scope="col">First class price</th>
                     <th></th>
                    <th v-if="role == 'FLIGHT_ADMIN'"></th>
                    </tr>
                </thead>
                <tbody>
                <tr v-for="(flight,index) in flights" :key="index">  
                    <td>{{flight.startDestination}}</td>
                    <td>{{flight.endDestination}}</td>
                    <td>{{flight.startDate_str}}</td>
                    <td>{{flight.endDate_str}}</td>
                    <td>{{flight.flightDuration}}</td>
                    <td>{{flight.flightLength}}</td>
                    <td>{{flight.economicPrice}}</td>
                    <td>{{flight.businessPrice}}</td>
                    <td>{{flight.firstClassPrice}}</td>
                    <td v-if="role == 'FLIGHT_ADMIN'"> <Button class="btn btn-outline-primary" @click="goToDetails(flight.id)" >Details</Button></td>
                    <td  v-if="role == 'FLIGHT_ADMIN'"><button class="btn btn-outline-primary" @click="removeFlight(flight, index)">Delete</button></td>
                    <td  v-if="role == 'REGISTERED'"><button class="btn btn-outline-primary" @click="goToDetailsForReservation(flight.id)">Details</button></td>
                </tr>
                </tbody>              
            </table>  
        </div>   
    </div>
</template>

<script>
export default {
  name: 'allFlights',
  components: {},
  props: ["idCompany"],
  data: function () {
  return {

    role: "",
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
created: function(){
    var getJwtToken = function() {
            return localStorage.getItem('jwtToken');
        };
        axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
        axios.get("http://localhost:8080/api/getUserRole")
        .then(response => {
            this.role = response.data
            if (response.data == 'FLIGHT_ADMIN'){
                    axios.get("http://localhost:8080/api/getFlights")
                    .then(response2 => {
                        this.flights = response2.data
                    });  
                }
                else if (response.data == 'REGISTERED') {
                    axios.get("http://localhost:8080/api/getFlightsCompany/" + this.idCompany)
                    .then(response3 => {
                        this.flights = response3.data
                    }); 
                }   
        });
},

mounted(){
         
        
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
            flightDuration: this.flightDuration, flightLength:  this.flightLength, companyId: -1 }
            var getJwtToken = function() {
                return localStorage.getItem('jwtToken');
            };
            if (this.role == 'REGISTERED') {

                flightForSearch.companyId = this.idCompany;
            }
            axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
            axios.post("http://localhost:8080/api/flightsInCompany",flightForSearch)
            .then(response => {
                this.flights = response.data
            });
        },

        
        goToDetails : function(flightID){
            localStorage.setItem("flightID",flightID)
            window.location = "/flightForAdmin"
        },

        goToDetailsForReservation: function(flightID){
            localStorage.setItem("flightID",flightID)
            window.location = "/flight"
        },
        
        removeFlight: function(flight, index){
            var getJwtToken = function() {
                return localStorage.getItem('jwtToken');
            };
            axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
            axios.get("http://localhost:8080/api/removeFlight/" + flight.id)
            
            .then(response => {
                if (response.data == "success"){
                    
                    this.flights.splice( index,1);
                    alert("Flight is removed.");

                }
                else{
                    alert(response.data);
                }
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
