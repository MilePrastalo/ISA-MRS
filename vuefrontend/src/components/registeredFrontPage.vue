<template>
    <div id="registeredUserFrontPage">
        <div class="container">

            <navbar/>
            <div class="row "> 
                <button style="border-radius: 20px;" class="col-sm btn btn-info btn-lg" @click="airlines">Airlines</button>
                <button style="border-radius: 20px;" class="col-sm btn btn-info btn-lg" @click="hotels">Hotels</button>
                <button style="border-radius: 20px;" class="col-sm btn btn-info btn-lg" @click="rentACar">Rent a car</button>
                <button style="border-radius: 20px;" class="col-sm btn btn-info btn-lg" @click="friends">Friends</button>
            </div>
            <div class="row" id="title"> 
                <h2>Reservations</h2>
            </div>
            <div class="row">
                <ul class="nav nav-tabs col-lg-10">
                    <li class="nav-item">
                        <a id="flights" class="nav-link active" href="#" @click="showFlights">Flights</a>
                    </li>
                    <li class="nav-item">
                        <a id="hotels" class="nav-link" href="#" @click="showHotels">Hotels</a>
                        
                    </li>
                    <li class="nav-item">
                        <a id="cars" class="nav-link" href="#" @click="showCars">Cars</a>
                    </li>
                </ul>
            </div>
            <div class="row">
                <div id="FlightsReservations" class="centered col-lg-10">
                    <table class="table">
                        <tr>
                            <th>Start destination</th>
                            <th>End destination</th>
                            <th>Start date</th>
                            <th>End date</th>
                            <th>Seat</th>
                            <th>Price</th>
                            <th>Passangers count</th>
                            <th>Details</th>
                            <th>Review</th>
                        </tr>
                        <tr v-for="flightReservation in flightReservations" :key="flightReservation.id">
                            <td>{{flightReservation.flight.startDestination.name}}</td>
                            <td>{{flightReservation.flight.endDestination.name}}</td>
                            <td>{{flightReservation.flight.startDate}}</td>
                            <td>{{flightReservation.flight.endDate}}</td>
                            <td> {{flightReservation.seat.travelClassa}} : ({{flightReservation.seat.seatRow}},{{flightReservation.seat.seatColumn}})</td>
                            <td>{{flightReservation.price}}</td>
                            <td v-if="flightReservation.passangers.length != 0">{{flightReservation.passangers.length}} + (1)</td>
                            <td v-else>1</td>
                             <td><button @click="flightReservationDetails(flightReservation.id)">Details</button></td>
                             <td class="ratingtd">
                                <span class="fa fa-star over clicked" v-if="getRating(flightReservation,5)" @click="reviewFlight(flightReservation,5)" :id="flightReservation.ratings[4]"></span>
                                    <span class="fa fa-star over" v-else @click="review(flightReservation,5)" :id="flightReservation.ratings[4]"></span>
                                <span class="fa fa-star over clicked" v-if="getRating(flightReservation,4)" @click="reviewFlight(flightReservation,4)" :id="flightReservation.ratings[3]"></span>
                                    <span class="fa fa-star over" v-else @click="review(flightReservation,4)" :id="flightReservation.ratings[3]"></span>
                                <span class="fa fa-star over clicked" v-if="getRating(flightReservation,3)" @click="reviewFlight(flightReservation,3)" :id="flightReservation.ratings[2]"></span>
                                    <span class="fa fa-star over" v-else @click="review(flightReservation,3)" :id="flightReservation.ratings[2]"></span>
                                <span class="fa fa-star over clicked" v-if="getRating(flightReservation,2)" @click="reviewFlight(flightReservation,2)" :id="flightReservation.ratings[1]"></span>
                                    <span class="fa fa-star over" v-else @click="review(flightReservation,2)" :id="flightReservation.ratings[1]"></span>
                                <span class="fa fa-star over clicked" v-if="getRating(flightReservation,1)" @click="reviewFlight(flightReservation,1)" :id="flightReservation.ratings[0]"></span>
                                    <span class="fa fa-star over" v-else @click="review(flightReservation,1)" :id="flightReservation.ratings[0]"></span>
                            </td>
                        </tr>
                    </table>
                </div>
                <div id="HotelsReservations" class="centered col-lg-10" hidden="true">
                    <table class="table">
                        <tr>
                            <th>Hotel Name</th>
                            <th>Room Number</th>
                            <th>First Day</th>
                            <th>Last Day</th>
                            <th>Details</th>
                            <th>Cancel</th>
                            <th>review</th>
                        <tr v-for="r in hotelReservations" :key="r.id">  
                        <td>{{r.hotelName}}</td>
                        <td>{{r.roomNumber}}</td>
                        <td>{{r.fDay + "-" + r.fMonth + "-" + r.fYear}}</td>
                        <td>{{r.lDay + "-" + r.lMonth + "-" + r.lYear}}</td>
                        <td><button @click="showDetails(r.hotelName,r.roomNumber)">Details</button></td>
                        <td></td>
                        <td class="ratingtd">
                                <span class="fa fa-star over clicked" v-if="getRating(r,5)" @click="reviewHotel(r,5)" :id="r.ratings[4]"></span>
                                    <span class="fa fa-star over" v-else @click="reviewHotel(r,5)" :id="r.ratings[4]"></span>
                                <span class="fa fa-star over clicked" v-if="getRating(r,4)" @click="reviewHotel(r,4)" :id="r.ratings[3]"></span>
                                    <span class="fa fa-star over" v-else @click="reviewHotel(r,4)" :id="r.ratings[3]"></span>
                                <span class="fa fa-star over clicked" v-if="getRating(r,3)" @click="reviewHotel(r,3)" :id="r.ratings[2]"></span>
                                    <span class="fa fa-star over" v-else @click="reviewHotel(r,3)" :id="r.ratings[2]"></span>
                                <span class="fa fa-star over clicked" v-if="getRating(r,2)" @click="reviewHotel(r,2)" :id="r.ratings[1]"></span>
                                    <span class="fa fa-star over" v-else @click="reviewHotel(r,2)" :id="r.ratings[1]"></span>
                                <span class="fa fa-star over clicked " v-if="getRating(r,1)" @click="reviewHotel(r,1)" :id="r.ratings[0]"></span>
                                    <span class="fa fa-star over" v-else @click="reviewHotel(r,1)" :id="r.ratings[0]"></span>

                            </td>
                    </tr>
                    </table>
                </div>
                <div id="CarsReservations" class="centered col-lg-10" hidden="true">
                    <table class="table">
                        <tr>
                            <th>Location pick</th>
                            <th>Location return</th>
                            <th>Name</th>
                            <th>Date from</th>
                            <th>Date to</th>
                            <th>Price</th>
                            <th>Cancel</th>
                        </tr>
                        <tr v-for="reservation in vehiclereservations" :key="reservation.id">
                            <td>{{reservation.locationPick}}</td>
                            <td>{{reservation.locationReturn}}</td>
                            <td>{{reservation.vehicleName}}</td>
                            <td>{{reservation.dateFrom}}</td>
                            <td>{{reservation.dateTo}}</td>
                            <td>{{reservation.price}}</td>
                            <td><Button v-if="reservation.status == 0" @click="cancel(reservation)">Cancel</Button></td>
                            <td class="ratingtd" v-if="reservation.status == 2" >
                                <span class="fa fa-star over clicked" v-if="getRating(reservation,5)==true" @click="review(reservation,5)" :id="reservation.ratings[4]"></span>
                                    <span class="fa fa-star over" v-else @click="review(reservation,5)" :id="reservation.ratings[4]"></span>
                                <span class="fa fa-star over clicked" v-if="getRating(reservation,4)" @click="review(reservation,4)" :id="reservation.ratings[3]"></span>
                                    <span class="fa fa-star over" v-else @click="review(reservation,4)" :id="reservation.ratings[3]"></span>
                                <span class="fa fa-star over clicked" v-if="getRating(reservation,3)" @click="review(reservation,3)" :id="reservation.ratings[2]"></span>
                                    <span class="fa fa-star over" v-else @click="review(reservation,3)" :id="reservation.ratings[2]"></span>
                                <span class="fa fa-star over clicked" v-if="getRating(reservation,2)" @click="review(reservation,2)" :id="reservation.ratings[1]"></span>
                                    <span class="fa fa-star over" v-else @click="review(reservation,2)" :id="reservation.ratings[1]"></span>
                                <span class="fa fa-star over clicked" v-if="getRating(reservation,1)" @click="review(reservation,1)" :id="reservation.ratings[0]"></span>
                                    <span class="fa fa-star over" v-else @click="review(reservation,1)" :id="reservation.ratings[0]"></span>

                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { setTimeout } from 'timers';
import navbar from "./navbar.vue";


export default {
  name: 'registeredUserFrontPage',
  data: function(){
      return{
          flightReservations: [],
          vehiclereservations:[],
          hotelReservations:[]
      }
  },
  components: {
      navbar
    },
  created(){
      var getJwtToken = function() {
                    return localStorage.getItem('jwtToken');
                };
        axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
        axios.get("http://localhost:8080/api/geteReservations")
            .then(response => {
                this.vehiclereservations = response.data;
                this.vehiclereservations.forEach(element => {
                    element.ratings = [element.id + "1",element.id + "2",element.id + "3",element.id + "4",element.id + "5"];
                });
            }); 
        axios.get("http://localhost:8080/api/getUserHotelReservations")
            .then(response => {
                this.hotelReservations = response.data;
                this.hotelReservations.forEach(element => {
                    element.ratings = [element.id + "1",element.id + "2",element.id + "3",element.id + "4",element.id + "5"];
                });
            });  

        axios.get("http://localhost:8080/api/getMyFlightReservations")
            .then(response => {
                this.flightReservations = response.data;
                this.flightReservations.forEach(element => {
                    element.ratings = [element.id + "1",element.id + "2",element.id + "3",element.id + "4",element.id + "5"];
                    });
                });
  },
  mounted(){
  },
  methods : {
      friends:function(){
          window.location = "./friends";
      },
      hotels:function(){
          console.log("");
      },
      getRating:function(res,rating){
          console.log(res);
          if(res.rating>=rating){
              console.log("true");
              return true;
          }
          return false;
      },
      showFlights : function(){
            var flightDiv = document.getElementById("FlightsReservations").hidden=false;
            var hoteltDiv = document.getElementById("HotelsReservations").hidden=true;
            var carDiv = document.getElementById("CarsReservations").hidden=true;
            document.getElementById("flights").className="nav-link active";
            document.getElementById("hotels").className="nav-link";
            document.getElementById("cars").className="nav-link";
      },
      showHotels : function(){
            var flightDiv = document.getElementById("FlightsReservations").hidden=true;
            var hoteltDiv = document.getElementById("HotelsReservations").hidden=false;
            var carDiv = document.getElementById("CarsReservations").hidden=true;
            document.getElementById("flights").className="nav-link";
            document.getElementById("hotels").className="nav-link active";
            document.getElementById("cars").className="nav-link";
      },
      showCars : function(){
          
            var flightDiv = document.getElementById("FlightsReservations").hidden=true;
            var hoteltDiv = document.getElementById("HotelsReservations").hidden=true;
            var carDiv = document.getElementById("CarsReservations").hidden=false;
            document.getElementById("flights").className="nav-link";
            document.getElementById("hotels").className="nav-link";
            document.getElementById("cars").className="nav-link active";
                
      },
    logout:function(){
      window.location="./";
    },
    profile:function(){
        window.location="./userProfile";
    },
    rentACar:function(){
        window.location="./rentacar";
    },
    airlines:function(){
        window.location="./searchFlightCompany";
    },
    cancel:function(reservation){
        var getJwtToken = function() {
                    return localStorage.getItem('jwtToken');
                };
        axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
        axios.post("http://localhost:8080/api/cancelVehicleReservation",reservation)
            .then(response => {
                console.log(response);
                alert("success");
            }); 
    },
    review:function(reservation,num){
        var getJwtToken = function() {
                    return localStorage.getItem('jwtToken');
                };
        axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
        console.log(reservation);
        axios.post("http://localhost:8080/api/reviewVehicle",{reservationId:reservation.id,rating:num})
            .then(response => {
                alert("success");
            });
        reservation.rating = num;
        this.setStars(reservation,num);
    },
    reviewHotel:function(reservation,num){
        var getJwtToken = function() {
                    return localStorage.getItem('jwtToken');
                };
        axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
        console.log(reservation);
        axios.post("http://localhost:8080/api/reviewHotel",{reservationId:reservation.id,rating:num})
            .then(response => {
                alert("success");
            });
        reservation.rating = num;
        this.setStars(reservation,num);
    },
    reviewFlight:function(reservation,num){
        var getJwtToken = function() {
                    return localStorage.getItem('jwtToken');
                };
        axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
        console.log(reservation);
        axios.post("http://localhost:8080/api/reviewFlight",{reservationId:reservation.id,rating:num})
            .then(response => {
                alert("success");
            });
        reservation.rating = num;
        this.setStars(reservation,num);
    },
    showDetails: function(hotelName,chosenRoom) {
        this.$router.push("/hotelRoom/"+ hotelName + "/" + chosenRoom);
    },
    showHotelSearch: function() {
        this.$router.push("/searchHotels");
    },
    flightReservationDetails(id){
        this.$router.push("/flightReservationDetails/"+ id );
    },
    setStars:function(reservation,num){
        for(var i = 1;i<=5;i++){
            var element = document.getElementById(reservation.ratings[i-1]);
            if(i<=num){
                element.className = "fa fa-star over clicked"
            }
            else{
                element.className = "fa fa-star over"
            }
        }
        
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
    margin-left: auto;
    margin-right: auto;
    min-width: 70%;
}
th{
    border: 1px solid darkgray;
    
}
ul{
    align-self: center;
    align-content: center;
    align-items: center;
    margin-right: 2%;
    margin:auto;
}
li{
    margin: auto;
    min-width: 30%;
}
button{
    padding-left: 2%;
    padding-right: 2%;
    margin:2%;
    margin-top: 0%;
}

#title{
    text-align: center;
    align-content: center;
    margin-bottom: 2%;
}
#title h2{
    text-align: center;
    margin:auto;
}
.row{
    text-align: center;
    align-content: center;
    margin-left: auto;
    margin-right: auto;
}
.centered{
    margin-left:auto;
    margin-right: auto;
}
#FlightsReservations table{
    margin-left:auto;
    margin-right: auto;
    margin-top: 2%;
}
#HotelsReservations table{
    margin-left:auto;
    margin-right: auto;
    margin-top: 2%;
}
#CarsReservations table{
    margin-left:auto;
    margin-right: auto;
    margin-top: 2%;
}
.ratingtd{
    unicode-bidi:bidi-override;
    direction:rtl;
}
.ratingtd > .over:hover{
    color: orange;
}
.ratingtd > .over:hover ~ span:before{
    color: orange;
}
.clicked{
    color: orange;
}
</style>