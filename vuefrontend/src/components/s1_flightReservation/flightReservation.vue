<template>
   <div id = "flightReservation">
       <navbar/>
        <div v-if="currentStep == 1">
            
            <button @click="goToNextStep(2)"  class="btn btn-success"> Next >> </button>
            <seat_selection :iid= "id"></seat_selection>

        </div>

        <div v-if="currentStep == 2">
            <button @click="goToNextStep(3)"  class="btn btn-success"> Next >> </button>
            <passangers :iid = "id"></passangers>
        </div>

        <div v-if="currentStep == 3">

            <button @click="goToNextStep(4)"> Next >> </button>
            <hotelReservation v-on:roomReserved="hotelReserved" :location="destination" :passengers="passangers.length" :firstDay="flight.endDate_str"/>
        </div>
        <div v-if="currentStep == 4">
            <button @click="goToNextStep(5)"> Finish >> </button>
            <rentACarReservation :iflightDateArrive="flight.startDate_str" :iflightDateLeaving="flight.endDate_str" v-on:vehicleReserved="carReserved" :ilocation="destination"/>
        </div>
        		
         
    </div>
</template>

<script>
import SeatSelection from "./seatsSelection.vue";
import Passangers from "./passangers.vue";
import rentACarReservation from ".././rentACarReservation.vue";
import navbar from ".././navbar.vue";
import hotelReservation from ".././hotelReservation";

export default {

    name: "flightReservation",
    components: {

        seat_selection: SeatSelection,
        passangers: Passangers,
        rentACarReservation,
        navbar,
        hotelReservation
    },
    data: function () {
        return {

            selected_seats: [],
            destination:"",
            total: 0,
            passangers: [],
            id : 0,
            flight: {},
            hotelReservations: [],
            vehicleReservations: [],
            currentStep: 1
        }
    },
    created:function(){

        this.id = localStorage.getItem("flightID");
        localStorage.setItem("selected_seats",JSON.stringify([]));
        localStorage.setItem("passangers",JSON.stringify([]));
    },
    mounted(){

        var flightID = localStorage.getItem("flightID");
        axios.get("/api/getFlight/" + flightID)
        .then(response => {
            this.flight = response.data
            console.log(this.flight);
            this.destination = this.flight.endDestination;
          }); 
    }, 
    methods: {

         getPrice: function( travelClass ){

            if (travelClass == "economic"){
                return this.flight.economicPrice;
            }
            else if (travelClass == "business"){
                return this.flight.businessPrice;
            }
            else{
                return this.flight.firstClassPrice;
            }
        },
        carReserved:function(id){
            this.vehicleReservations.push(id);
            console.log(id);
        },
        hotelReserved: function(id) {
            this.hotelReservations.push(id);
            console.log(id);
        },
        makeReservation: function(){
           
            var idx;
            var passangerSeat = [];
            for (idx in this.passangers){
                var price = this.getPrice(this.passangers[idx].seat.travelClass);
                var obj = {price: price, seatId: this.passangers[idx].seat.id, 
                           firstName: this.passangers[idx].firstName, lastName: this.passangers[idx].lastName,
                           passport: this.passangers[idx].passport, friendId: this.passangers[idx].friendID};
                passangerSeat.push(obj);     
            }
            if (passangerSeat.length == 0){
                 var price = this.getPrice(this.selected_seats[0].travelClass);
                 var obj = {price: price, seatId: this.selected_seats[0].id, firstName: "", lastName: "", passport: "", friendId: -1};
                passangerSeat.push(obj);
            }
            var getJwtToken = function() {
                return localStorage.getItem("jwtToken");
            };
            axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
            axios.post("/api/makeFlightReservation", {flightId: this.id, passangers: passangerSeat,
                        hotelReservations: this.hotelReservations, rentReservations: this.vehicleReservations})
            .then(response => {
                 alert(response.data);
            });
            window.location = "/front/flight"
        },

        goToNextStep: function(option){
            
            this.selected_seats = JSON.parse(localStorage.getItem("selected_seats"));
            this.passangers = JSON.parse(localStorage.getItem("passangers"));

            if (option == 2){

                if (this.selected_seats.length == 0  ){
                    alert("You must choose at least one seat!");
                }
                else if (this.selected_seats.length == 1){
                    this.currentStep = option+1;
                }
                else{
                    this.currentStep = option;
                }  
            } 
            if (option == 3){

                this.passangers_count = this.selected_seats.length   - (this.passangers).length;
                if (this.passangers_count != 0){
                    alert("You must fill all data and then click 'Next'.");
                }
                else{
                    this.currentStep = option;
                }
            }
            if (option == 4){
                this.currentStep = option;
            }
            if (option == 5){
                this.makeReservation();
            }
            
        },

        goBack: function(current){
            if (current == 1){
                window.location = "/front/flight"
            }
            else if (current == 2){
                this.currentStep = 1;
            }
            else if (current == 3){
                this.currentStep = 2;
            }
        },
    },
} 

    
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-left: 5%;
  margin-right:5%;
  margin-top:0%;
}
</style>
