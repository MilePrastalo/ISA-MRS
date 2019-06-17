<template>
   <div id = "addQuickReservation">

        <br><br>

        <div v-if="choosen == false">
                <br>
                <h2>Choose flight:</h2>
                <br>
            <div class = 'addQuickContainer'>
                <table style="text-align: left" class="centered">
                    <tr>
                        <td>Start destination:</td>
                        <td><input v-model="startDestination" type="text"></td>
                    </tr>
                    <tr>
                        <td>End destination:</td>
                        <td><input v-model="endDestination" type="text"></td>
                    </tr>
                    <tr>
                        <td>Depart date:</td>
                        <td><input v-model="startDate" type="date"></td>
                    </tr>
                    <tr>
                        <td>Return date:</td>
                        <td><input v-model="endDate" type="date"></td>
                    </tr>
                    <tr>
                        <td><button class="btn btn-primary" @click="search()">Search</button> </td> 
                    </tr>
                </table>
            </div>
            <br>
            <div class = "container">
                <table border="1" class ="table">
                    <thead>
                    <tr>
                        <th scope="col">Start destination</th>
                        <th scope="col">End destination</th>
                        <th scope="col">Start date</th>
                        <th scope="col">End date</th>
                        <th scope="col">Flight duration</th>
                        <th scope="col">Flight duration</th>
                        <th scope="col">Flight Length</th>
                        <th scope="col">Economic price</th>
                        <th scope="col">Buisiness price</th>
                        <th scope="col">First class price</th>
                    </tr>
                    </thead>
                    <tr v-for="flight in flights" :key="flight.id">  
                        <td>{{flight.startDestination}}</td>
                        <td>{{flight.endDestination}}</td>
                        <td>{{flight.startDate_str}}</td>
                        <td>{{flight.endDate_str}}</td>
                        <td>{{flight.flightDuration}}</td>
                        <td>{{flight.flightLength}}</td>
                        <td>{{flight.economicPrice}}</td>
                        <td>{{flight.businessPrice}}</td>
                        <td>{{flight.firstClassPrice}}</td>
                        <td> <Button class="btn btn-outline-primary" @click="choseFlight(flight.id)" >Choose </Button></td>
                    </tr>              
                </table>  
            </div>   
        </div>

        <div  v-if="choosen == true && seatChosen == false" class = "container">
                <h2>Choose seat:</h2>
            <br>
            <div class = "row" style="margin-left: 5%;">
                <table class = "seats">
                    <caption style="caption-side: top; font-weight: bold">Economic class</caption>
                    <tr v-for="r in seatsE.rows" :key ="r"> 
                        <td  v-for="c in seatsE.columns" :key ="c" v-bind:class = "{unavailable: seatsE.seats[(c-1)+(r-1)*seatsE.columns].unavailable, booked: seatsE.seats[(c-1)+(r-1)*seatsE.columns].taken, quick: seatsE.seats[(c-1)+(r-1)*seatsE.columns].quick}" @click="modify(seatsE.seats[(c-1)+(r-1)*seatsE.columns])">
                            ( {{ seatsE.seats[(c-1)+(r-1)*seatsE.columns].seatRow}} , {{ seatsE.seats[(c-1)+(r-1)*seatsE.columns].seatColumn}} )	
                        </td> 
                    </tr>
                </table>
                <table class = "seats">
                    <caption style="caption-side: top; font-weight: bold">Business class</caption>
                    <tr v-for="r in seatsB.rows" :key ="r"> 
                        <td  v-for="c in seatsB.columns" :key ="c" v-bind:class = "{unavailable: seatsB.seats[(c-1)+(r-1)*seatsB.columns].unavailable, booked: seatsB.seats[(c-1)+(r-1)*seatsB.columns].taken, quick: seatsB.seats[(c-1)+(r-1)*seatsB.columns].quick}" @click="modify(seatsB.seats[(c-1)+(r-1)*seatsB.columns])">
                            ( {{ seatsB.seats[(c-1)+(r-1)*seatsB.columns].seatRow}} , {{ seatsB.seats[(c-1)+(r-1)*seatsB.columns].seatColumn}} )	
                        </td>
                    </tr>
                </table>
                <table class = "seats">
                    <caption style="caption-side: top; font-weight: bold">First class</caption>
                    <tr v-for="r in seatsF.rows" :key ="r"> 
                        <td  v-for="c in seatsF.columns" :key ="c" v-bind:class = "{unavailable: seatsF.seats[(c-1)+(r-1)*seatsF.columns].unavailable, booked: seatsF.seats[(c-1)+(r-1)*seatsF.columns].taken, quick: seatsF.seats[(c-1)+(r-1)*seatsF.columns].quick}" @click="modify(seatsF.seats[(c-1)+(r-1)*seatsF.columns])">
                            ( {{ seatsF.seats[(c-1)+(r-1)*seatsF.columns].seatRow}} ,{{ seatsF.seats[(c-1)+(r-1)*seatsF.columns].seatColumn}} )	
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div v-if="choosen == true && seatChosen == true" class = "centered" >
            <form @submit="addQuickReservation">
                <table class = "centered">
                    <th colspan="2">Seat: ({{this.seat.seatRow}}, {{this.seat.seatColumn}})</th>
                    <tr>
                        <td>Discount:</td>
                        <td><input type="text" v-model="discount"></td>
                    </tr>
                    <tr>
                        <input type="submit">
                    </tr>
                </table>
            </form>
        </div>
    </div>
</template>

<script>
export default {
  name: "addQuickReservation",
  components: {},
  data: function () {
  return {
    startDestination: "",
    endDestination: "",
    startDate: "",
    endDate: "",
    flights: [],
    flightId: 0,
    choosen: false,
    seatsE: {},
    seatsB: {},
    seatsF: {},
    seatChosen: false,
    seat: {},
    discount: ""
  }
},
mounted(){
        var getJwtToken = function() {
                return localStorage.getItem("jwtToken");
            };
        axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
        axios.get("http://localhost:8080/api/getFlights")
        .then(response => {
            this.flights = response.data
          });
    },
    methods:{ 
        checkedClass: function(option){
            this.check = option;
        },
        search: function() {

            var flightForSearch = {startDestination: this.startDestination, endDestination: this.endDestination, startDate: this.startDate, endDate: this.endDate,
            minEconomic : 0, minBusiness: 0, minFirstClass :0,
            maxEconomic: 0, maxBusiness: 0, maxFirstClass:0,
            flightDuration: 0, flightLength: 0 }
            var getJwtToken = function() {
                return localStorage.getItem("jwtToken");
            };
            axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
            axios.post("http://localhost:8080/api/flightsInCompany",flightForSearch)
            .then(response => {
                this.flights = response.data
            }); 
        },

        choseFlight : function(flightID){
            this.flightId = flightID;
             var getJwtToken = function() {
                return localStorage.getItem("jwtToken");
            };
            axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
            axios.get("http://localhost:8080/api/getSeatsOnFlight/" +  flightID + "/economic")
            .then(response => {
                this.seatsE = response.data;
            });; 
            axios.get("http://localhost:8080/api/getSeatsOnFlight/" +  flightID + "/business" )
            .then(response => {
                this.seatsB = response.data;
            });
            axios.get("http://localhost:8080/api/getSeatsOnFlight/" +  flightID + "/first class" )
            .then(response => {
                this.seatsF = response.data;
            }); 
            this.choosen = true; 
            
        },
        
        modify: function(seat){
            if (seat.taken == false && seat.unavailable == false && seat.quick == false){
                this.seat = seat;
                this.seatChosen = true;
            }     
        },

        addQuickReservation: function(e){
            e.preventDefault();
            var bean = {flightId: this.flightId, seatId: this.seat.id, discount: this.discount}
            var getJwtToken = function() {
                return localStorage.getItem("jwtToken");
            };
            axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
            axios.post("http://localhost:8080/api/addQuickFlightReservation", bean)
            .then(response => {
                if (response.data != null){
                    alert("Quick reservation added!");
                    this.choosen = false;
                    this.seatChosen = false;
                }
            });

        }

        
    }
}

</script>

<style>

.addQuickContainer{

    margin-left: auto;
    margin-right: auto;
    width: 1100px;
}
#addQuickReservation{
    margin-left: auto;
    margin-right: auto;
}
.seats{
    border-collapse: separate;
    border-spacing: 5px 1rem;
    margin-right: 5%;
}
.seats td {
    border: 2px solid gray;
    border-radius: 20%;    
}

.seats td:visited {
    border: 2px solid gray;
}

.seats td:hover {
    cursor: default;
    background-color: gray;
}

.seats .unavailable {
    background-color: orange;
    border-radius: 5px;
}

.seats .quick {
    background-color: yellow;
    border-radius: 5px;
}

.seats .booked {
    background-color: salmon;
}

#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

</style>
