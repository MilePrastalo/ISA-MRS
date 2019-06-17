<template>
   <div id = "seatSelection">

            <br>
                <h2>Choose seat(s) </h2>
            <div class="my-legend" style="margin-left: 5%;">
                <div class="legend-title"></div>
                <div class="legend-scale">
                    <ul class="legend-labels">
                        <li><span style="background:mediumspringgreen;"></span>Active</li>
                        <li><span style="background:salmon;"></span>Booked</li>
                        <li><span style="background:yellow;"></span>Quick reservation</li>
                        <li><span style="background:orange;"></span>Unavailable</li>
                    </ul>
                </div>
                <br><br>
            </div>
            <br><br><br>
            <div class = "row" style="margin-left: 5%;"> 
                <table class = "seats">
                    <caption style="caption-side: top; font-weight: bold">Economic class</caption>
                    <tr v-for="r in seatsE.rows" :key ="r"> 
                        <td  v-for="c in seatsE.columns" :key ="c" v-bind:class = "{active: seatsE.seats[(c-1)+(r-1)*seatsE.columns].active, booked: seatsE.seats[(c-1)+(r-1)*seatsE.columns].taken,  unavailable: seatsE.seats[(c-1)+(r-1)*seatsE.columns].unavailable, quick: seatsE.seats[(c-1)+(r-1)*seatsE.columns].quick}" @click="addSeat(seatsE.seats[(c-1)+(r-1)*seatsE.columns])">
                            ( {{ seatsE.seats[(c-1)+(r-1)*seatsE.columns].seatRow}} , {{ seatsE.seats[(c-1)+(r-1)*seatsE.columns].seatColumn}} )	
                        </td> 
                    </tr>
                </table>
                <table class = "seats">
                    <caption style="caption-side: top; font-weight: bold">Business class</caption>
                    <tr v-for="r in seatsB.rows" :key ="r"> 
                        <td  v-for="c in seatsB.columns" :key ="c" v-bind:class = "{active: seatsB.seats[(c-1)+(r-1)*seatsB.columns].active, booked: seatsB.seats[(c-1)+(r-1)*seatsB.columns].taken, unavailable: seatsB.seats[(c-1)+(r-1)*seatsB.columns].unavailable, quick: seatsB.seats[(c-1)+(r-1)*seatsB.columns].quick}" @click="addSeat(seatsB.seats[(c-1)+(r-1)*seatsB.columns])">
                            ( {{ seatsB.seats[(c-1)+(r-1)*seatsB.columns].seatRow}} , {{ seatsB.seats[(c-1)+(r-1)*seatsB.columns].seatColumn}} )	
                        </td>
                    </tr>
                </table>
                <table class = "seats">
                    <caption style="caption-side: top; font-weight: bold">First class</caption>
                    <tr v-for="r in seatsF.rows" :key ="r"> 
                        <td  v-for="c in seatsF.columns" :key ="c" v-bind:class = "{active: seatsF.seats[(c-1)+(r-1)*seatsF.columns].active, booked: seatsF.seats[(c-1)+(r-1)*seatsF.columns].taken, unavailable: seatsF.seats[(c-1)+(r-1)*seatsF.columns].unavailable, quick: seatsF.seats[(c-1)+(r-1)*seatsF.columns].quick}" @click="addSeat(seatsF.seats[(c-1)+(r-1)*seatsF.columns])">
                            ( {{ seatsF.seats[(c-1)+(r-1)*seatsF.columns].seatRow}} ,{{ seatsF.seats[(c-1)+(r-1)*seatsF.columns].seatColumn}} )	
                        </td>
                    </tr>
                </table>       
            </div>

            <div v-if="selected_seats.length != 0" class = "row" id = "infoArea" style="margin-left: 5%;">
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

    name: "seatSelection",
    components: {},
    props: ["iid"],
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
        
        var flightID = this.iid;
        var getJwtToken = function() {
            return localStorage.getItem("jwtToken");
        };
        axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
        axios.get("/api/getFlight/" + flightID)
        .then(response => {
            this.flight = response.data
        });
        axios.get("/api/getSeatsOnFlight/" + flightID + "/economic")
        .then(response => {
            this.seatsE = response.data;
        });
        axios.get("/api/getSeatsOnFlight/" + flightID + "/business")
        .then(response => {
            this.seatsB = response.data;
        });
        axios.get("/api/getSeatsOnFlight/" + flightID + "/first class")
        .then(response => {
            this.seatsF = response.data;
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

        addSeat: function(seat){

            if (seat.active == false && seat.taken == false && seat.unavailable == false && seat.quick == false){
                seat.active = true;
                var str = "#" + seat.id;
                this.selected_seats.push(seat);
                this.total += this.getPrice(seat.travelClassa);
                localStorage.setItem("selected_seats",JSON.stringify(this.selected_seats));
                localStorage.setItem("total",this.total);  
            }
            else if (seat.active == true) {
                seat.active = false;
                var s;
                for (s in this.selected_seats){
                    if (this.selected_seats[s].id == seat.id ){
                        break;
                    }
                }
                this.total -= this.getPrice(seat.travelClassa);
                this.selected_seats.splice(s, 1);
                localStorage.setItem("selected_seats",JSON.stringify(this.selected_seats));
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

.seats .quick {
    background-color: yellow;
}

.seats .unavailable {
    background-color: orange;
}

.my-legend .legend-title {
    text-align: left;
    margin-bottom: 5px;
    font-weight: bold;
    font-size: 90%;
    }
  .my-legend .legend-scale ul {
    margin: 0;
    margin-bottom: 5px;
    padding: 0;
    float: left;
    list-style: none;
    }
  .my-legend .legend-scale ul li {
    font-size: 80%;
    list-style: none;
    margin-left: 0;
    line-height: 18px;
    margin-bottom: 2px;
    }
  .my-legend ul.legend-labels li span {
    display: block;
    float: left;
    height: 16px;
    width: 30px;
    margin-right: 5px;
    margin-left: 0;
    border: 1px solid #999;
    }
  .my-legend .legend-source {
    font-size: 70%;
    color: #999;
    clear: both;
    }
  .my-legend a {
    color: #777;
    }


#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
