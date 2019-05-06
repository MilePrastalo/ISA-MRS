<template>
   <div id = "flightReservation">

        <div v-if="currentStep == 1">
            
            <button @click="goToNextStep(2)"> Next >> </button>
            <seat_selection :iid= "id"></seat_selection>

        </div>

        <div v-if="currentStep == 2">

            <button @click="goToNextStep(3)"> Next >> </button>
            <passangers :iid = "id"></passangers>

        </div>		
         
    </div>
</template>

<script>
import SeatSelection from './seatsSelection.vue';
import Passangers from './passangers.vue';

export default {

    name: 'flightReservation',
    components: {

        seat_selection: SeatSelection,
        passangers: Passangers
    },
    data: function () {
        return {

            selected_seats: [],
            total: 0,
            passangers: [],
            id : 0,
            flight: {},
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
        axios.get("http://localhost:8080/api/getFlight/" + flightID)
        .then(response => {
            this.flight = response.data
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

        makeReservation: function(){
           
            var idx;
            var passangerSeat = [];
            for (idx in this.passangers){
                if (this.passangers[idx].friend == false){
                    var price = this.getPrice(this.passangers[idx].seat.travelClass);
                    var obj = {price: price, seatId: this.passangers[idx].seat.id, firstName: this.passangers[idx].firstName, lastName: this.passangers[idx].lastName, passport: this.passangers[idx].passport};
                    passangerSeat.push(obj);
                }     
            }
            if (passangerSeat.length == 0){
                 var price = this.getPrice(this.selected_seats[0].travelClass);
                 var obj = {price: price, seatId: this.selected_seats[0].id, firstName: "", lastName: "", passport: ""};
                passangerSeat.push(obj);
            }
            var getJwtToken = function() {
                return localStorage.getItem('jwtToken');
            };
            axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
            axios.post("http://localhost:8080/api/makeFlightReservation", {flightId: this.id, passangers: passangerSeat})
            .then(response => {
                this.flight = response.data
                 alert("Rezervacija je napravljena");
            });
            alert("Reservation is successfuly made!");
            window.location = '/flight'
        },

        goToNextStep: function(option){
            
            this.selected_seats = JSON.parse(localStorage.getItem('selected_seats'));
            this.passangers = JSON.parse(localStorage.getItem('passangers'));

            if (option == 2){

                if (this.selected_seats.length == 0  ){
                    alert("You must choose at least one seat!");
                }
                else if (this.selected_seats.length == 1){
                    this.makeReservation();
                }
                else{
                    this.currentStep = option;
                }  
            } 
            if (option == 3){

                this.passangers_count = this.selected_seats.length   - (this.passangers).length;
                if (this.passangers_count != 0){
                    alert("You must fill all data and then click 'Finish'.");
                }
                else{
                    this.makeReservation();
                }
            }
            
        },

        goBack: function(current){
            if (current == 1){
                window.location = '/flight'
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
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin: 5%
}
</style>
