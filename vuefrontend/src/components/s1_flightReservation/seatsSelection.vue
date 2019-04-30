<template>
   <div id = "seatSelection">

            <div class = "row">
                <h2>Choose seat(s) </h2>
            </div>
            <br>
            <div class = "row"> 
                <table class = "seats">
                    <caption style="caption-side: top; font-weight: bold">Economic class</caption>
                    <tr v-for="r in seatsE.rows" :key ="r"> 
                        <td  v-for="c in seatsE.columns" :key ="c" v-bind:class = "{active: seatsE.seats[(c-1)+(r-1)*seatsE.columns].active, booked: seatsE.seats[(c-1)+(r-1)*seatsE.columns].taken}" @click="addSeat(seatsE.seats[(c-1)+(r-1)*seatsE.columns])">
                            ( {{ seatsE.seats[(c-1)+(r-1)*seatsE.columns].seatRow}} , {{ seatsE.seats[(c-1)+(r-1)*seatsE.columns].seatColumn}} )	
                        </td> 
                    </tr>
                </table>
                <table class = "seats">
                    <caption style="caption-side: top; font-weight: bold">Business class</caption>
                    <tr v-for="r in seatsB.rows" :key ="r"> 
                        <td  v-for="c in seatsB.columns" :key ="c" v-bind:class = "{active: seatsB.seats[(c-1)+(r-1)*seatsB.columns].active, booked: seatsB.seats[(c-1)+(r-1)*seatsB.columns].taken}" @click="addSeat(seatsB.seats[(c-1)+(r-1)*seatsB.columns])">
                            ( {{ seatsB.seats[(c-1)+(r-1)*seatsB.columns].seatRow}} , {{ seatsB.seats[(c-1)+(r-1)*seatsB.columns].seatColumn}} )	
                        </td>
                    </tr>
                </table>
                <table class = "seats">
                    <caption style="caption-side: top; font-weight: bold">First class</caption>
                    <tr v-for="r in seatsF.rows" :key ="r"> 
                        <td  v-for="c in seatsF.columns" :key ="c" v-bind:class = "{active: seatsF.seats[(c-1)+(r-1)*seatsF.columns].active, booked: seatsF.seats[(c-1)+(r-1)*seatsF.columns].taken}" @click="addSeat(seatsF.seats[(c-1)+(r-1)*seatsF.columns])">
                            ( {{ seatsF.seats[(c-1)+(r-1)*seatsF.columns].seatRow}} ,{{ seatsF.seats[(c-1)+(r-1)*seatsF.columns].seatColumn}} )	
                        </td>
                    </tr>
                </table>       
            </div>

            <div v-if="selected_seats.length != 0" class = "row" id = "infoArea">
                <div>
                    <h3> Selected Seats (<span id="counter"> {{selected_seats.length}} </span>):</h3>
                    <ul  id = "selectedSeats">
                        <li  v-for="seat in selected_seats" :key ="seat.id"> ({{seat.seatRow}},{{seat.seatColumn}}) : {{seat.travelClassa}} </li>
                    </ul>
                </div> 
                <div style="margin-left: 10%">
                    <h3> Total price</h3>
                    <span>{{total}}</span>
                </div>
            </div>  
   
    </div>	
         
    
</template>

<script>

export default {

    name: 'seatSelection',
    components: {},
    data: function () {
        return {

            flight: {},
            seatsE: {},
            seatsB: {},
            seatsF: {},
            selected_seats: [],
            total: 0,
            }
    },

    mounted(){
        
        var flightID = 7;
        var getJwtToken = function() {
            return localStorage.getItem('jwtToken');
        };
        axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
        axios.get("http://localhost:8080/api/getFlight/" + flightID)
        .then(response => {
            this.flight = response.data
        });
        axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
        axios.get("http://localhost:8080/api/getSeatsOnFlight/" + flightID + "/economic")
        .then(response => {
            this.seatsE = response.data;
        });;
        axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
        axios.get("http://localhost:8080/api/getSeatsOnFlight/" + flightID + "/business")
        .then(response => {
            this.seatsB = response.data;
        });;
        axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
        axios.get("http://localhost:8080/api/getSeatsOnFlight/" + flightID + "/first class")
        .then(response => {
            this.seatsF = response.data;
        });;       
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

        addSeat: function(seat){

            if (seat.active == false){
                seat.active = true;
                if (seat.taken == false){
                    var str = "#" + seat.id;
                    this.selected_seats.push(seat);
                    localStorage.setItem("selected_seats",this.selected_seats);
                     localStorage.setItem("total",this.total);
                    this.total += this.getPrice(seat.travelClassa);
                }
            }
            else{

                seat.active = false;
                var s;
                for (s in this.selected_seats){
                    if (this.selected_seats[s].id == seat.id ){
                        break;
                    }
                }
                this.total -= this.getPrice(seat.travelClassa);
                this.selected_seats.splice(s, 1);
                localStorage.setItem("selected_seats",this.selected_seats);
                localStorage.setItem("total",this.total);
            }		
        },      
    } 
}
    
</script>

<style>
#infoArea{
     padding: 2%;
}

#selectedSeats{
    max-height: 60%;
    width: 90%;
	overflow-y: scroll;	
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

.seats .active {
    background-color: mediumspringgreen;
    border-radius: 5px;
}

.seats .booked {
    background-color: salmon;
}

#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin: 5%
}
</style>
