
<template>
    <div id="registeredUserFrontPage">
        <navbar :itype="1"/>
        <div class="container">

            
            <div class="row "> 
                <button style="border-radius: 20px;" class="col-sm btn btn-info btn-lg" @click="airlines">Airlines</button>
                <button style="border-radius: 20px;" class="col-sm btn btn-info btn-lg" @click="hotels">Hotels</button>
                <button style="border-radius: 20px;" class="col-sm btn btn-info btn-lg" @click="rentACar">Rent a car</button>
                <button style="border-radius: 20px;" class="col-sm btn btn-info btn-lg" @click="friends">Friends</button>
            </div>
            <SearchFlights/>
            <div class="row" id="title"> 
                <h2>Reservations</h2>
            </div>
            <nav>
                <div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
                    <a @click="showFlights" id="flights" class="nav-item nav-link active" data-toggle="tab" role="tab" aria-controls="nav-home" aria-selected="true">Flights</a>
                    <a @click="showHotels" id="hotels" class="nav-item nav-link"  data-toggle="tab" role="tab" aria-controls="nav-profile" aria-selected="false">Hotels</a>
                    <a @click="showCars" id="cars" class="nav-item nav-link" data-toggle="tab"  role="tab" aria-controls="nav-contact" aria-selected="false">Cars</a>
                </div>
            </nav>
            
            <div  class="tab-content py-3 px-3 px-sm-0" id="nav-tabContent" >
                <div id="FlightsReservations" class="centered col-lg-10" role="tabpanel" aria-labelledby="flights">
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
                            <td>{{flightReservation.startDestination}}</td>
                            <td>{{flightReservation.endDestination}}</td>
                            <td>{{flightReservation.startDate}}</td>
                            <td>{{flightReservation.endDate}}</td>
                            <td> {{flightReservation.travelClassa}} : ({{flightReservation.seatRow}},{{flightReservation.seatColumn}})</td>
                            <td>{{flightReservation.price}}</td>
                            <td v-if="flightReservation.passangers != 0">{{flightReservation.passangers-1}} + (1)</td>
                            <td v-else>1</td>
                            <td><button @click="flightReservationDetails(flightReservation.id)">Details</button></td>
                            <td v-if="flightReservation.status == 0"><button @click="cancelFlightReservation(flightReservation.id)">Cancel</button></td>
                             <td colspan="2" v-if="flightReservation.status == 1" class="ratingtd" width="100px">
                                <span class="fa fa-star over clicked" v-if="getRating(flightReservation,5)" @click="reviewFlight(flightReservation,5)" :id="flightReservation.ratings[4]"></span>
                                    <span class="fa fa-star over" v-else @click="reviewFlight(flightReservation,5)" :id="flightReservation.ratings[4]"></span>
                                <span class="fa fa-star over clicked" v-if="getRating(flightReservation,4)" @click="reviewFlight(flightReservation,4)" :id="flightReservation.ratings[3]"></span>
                                    <span class="fa fa-star over" v-else @click="reviewFlight(flightReservation,4)" :id="flightReservation.ratings[3]"></span>
                                <span class="fa fa-star over clicked" v-if="getRating(flightReservation,3)" @click="reviewFlight(flightReservation,3)" :id="flightReservation.ratings[2]"></span>
                                    <span class="fa fa-star over" v-else @click="reviewFlight(flightReservation,3)" :id="flightReservation.ratings[2]"></span>
                                <span class="fa fa-star over clicked" v-if="getRating(flightReservation,2)" @click="reviewFlight(flightReservation,2)" :id="flightReservation.ratings[1]"></span>
                                    <span class="fa fa-star over" v-else @click="reviewFlight(flightReservation,2)" :id="flightReservation.ratings[1]"></span>
                                <span class="fa fa-star over clicked" v-if="getRating(flightReservation,1)" @click="reviewFlight(flightReservation,1)" :id="flightReservation.ratings[0]"></span>
                                    <span class="fa fa-star over" v-else @click="reviewFlight(flightReservation,1)" :id="flightReservation.ratings[0]"></span>
                            </td>
                        </tr>
                    </table>
                </div>
                <div id="HotelsReservations" class="centered col-lg-10" hidden="true" role="tabpanel" aria-labelledby="hotels">
                    <table class="table" v-if="showingHotel == false">
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
                        <td><button @click="showHotelDetails(r)">Details</button></td>
                        <td><button  v-if="r.status == 0" @click="cancelHotelReservation(r.hotelName,r.roomNumber)">Cancel</button></td>
                        <td v-if="r.status == 2" colspan="2" class="ratingtd">
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
                    <table v-if="showingHotel == true">
                        <tr>
                            <td><b>Hotel Name</b></td>
                            <td>{{hotelToShow.hotelName}}</td>
                        </tr>
                        <tr>
                            <td><b>Room Number</b></td>
                            <td>{{hotelToShow.roomNumber}}</td>
                        </tr>
                        <tr>
                            <td><b>First Day</b></td>
                            <td>{{hotelToShow.fDay + "-" + hotelToShow.fMonth + "-" + hotelToShow.fYear}}</td>
                        </tr>
                        <tr>
                            <td><b>Last Day</b></td>
                            <td>{{hotelToShow.lDay + "-" + hotelToShow.lMonth + "-" + hotelToShow.lYear}}</td>
                        </tr>      
                        <tr>
                            <td><b>Price Paid</b></td>
                            <td>{{hotelToShow.paidPrice}}</td>
                        </tr>  
                        <tr>
                            <td> </td>
                            <td><button @click="cancelShowingHotel()">Cancel</button></td>
                        </tr> 
                </table>
                </div>
                <div id="CarsReservations" class="centered col-lg-10" hidden="true" role="tabpanel" aria-labelledby="nav-about-tab">
                    <table class="table">
                        <tr>
                            <th>Location pick</th>
                            <th>Location return</th>
                            <th>Name</th>
                            <th>Date from</th>
                            <th>Date to</th>
                            <th>Price</th>
                            <th>Discount</th>
                            <th>Total</th>
                            <th>Cancel</th>
                        </tr>
                        <tr v-for="(reservation, index) in vehiclereservations" :key="index">
                            <td>{{reservation.locationPick}}</td>
                            <td>{{reservation.locationReturn}}</td>
                            <td>{{reservation.vehicleName}}</td>
                            <td>{{reservation.dateFrom}}</td>
                            <td>{{reservation.dateTo}}</td>
                            <td>{{reservation.price}}</td>
                            <td>{{reservation.discount}}</td>
                            <td>{{reservation.price-reservation.price*reservation.discount/100}}</td>
                            <td><Button v-if="reservation.status == 0" @click="cancel(reservation, index)">Cancel</Button></td>
                            <td width=100 colspan="2" class="ratingtd" v-if="reservation.status == 2" >
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
import { hostname } from 'os';
import SearchFlights from "./searchFlights";
import axios from "axios";


export default {
  name: 'registeredUserFrontPage',
  data: function(){
      return{
          flightReservations: [],
          vehiclereservations:[],
          hotelReservations:[],
          showingHotel: false,
          hotelToShow: {}
      }
  },
  components: {
      navbar,SearchFlights
    },
  created(){
      var getJwtToken = function() {
                    return localStorage.getItem("jwtToken");
                };
        axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
        axios.get("/api/geteReservations")
            .then(response => {
                this.vehiclereservations = response.data;
                this.vehiclereservations.forEach(element => {
                    element.ratings = [element.id + "1",element.id + "2",element.id + "3",element.id + "4",element.id + "5"];
                });
            }); 
        axios.get("/api/getUserHotelReservations")
            .then(response => {
                this.hotelReservations = response.data;
                this.hotelReservations.forEach(element => {
                    element.ratings = [element.id + "1",element.id + "2",element.id + "3",element.id + "4",element.id + "5"];
                });
            });  

        axios.get("/api/getMyReservations")
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
          this.$router.push("/front/friends");
      },
      hotels:function(){
          this.$router.push("/front/registeredHotelSearch");
      },
      getRating:function(res,rating){
          if(res.rating>=rating){
              return true;
          }
          return false;
      },
      showFlights : function(){
            var flightDiv = document.getElementById("FlightsReservations").hidden=false;
            var hoteltDiv = document.getElementById("HotelsReservations").hidden=true;
            var carDiv = document.getElementById("CarsReservations").hidden=true;
            document.getElementById("flights").className="nav-item nav-link active";
            document.getElementById("hotels").className="nav-item nav-link";
            document.getElementById("cars").className="nav-item nav-link";
      },
      showHotels : function(){
            var flightDiv = document.getElementById("FlightsReservations").hidden=true;
            var hoteltDiv = document.getElementById("HotelsReservations").hidden=false;
            var carDiv = document.getElementById("CarsReservations").hidden=true;
            document.getElementById("flights").className="nav-item nav-link";
            document.getElementById("hotels").className="nav-item nav-link active";
            document.getElementById("cars").className="nav-item nav-link";

      },
      showCars : function(){
          
            var flightDiv = document.getElementById("FlightsReservations").hidden=true;
            var hoteltDiv = document.getElementById("HotelsReservations").hidden=true;
            var carDiv = document.getElementById("CarsReservations").hidden=false;
            document.getElementById("flights").className="nav-item nav-link";
            document.getElementById("hotels").className="nav-item nav-link ";
            document.getElementById("cars").className="nav-item nav-link active";
                
      },
    logout:function(){
      this.$router.push("/");
    },
    profile:function(){
        this.$router.push("/front/userProfile");
    },
    rentACar:function(){
        this.$router.push("/front/rentacar");
    },
    airlines:function(){
        this.$router.push("/front/searchFlightCompany");
    },
    cancel:function(reservation, index){
        var getJwtToken = function() {
                    return localStorage.getItem("jwtToken");
                };
        axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
        axios.post("/api/cancelVehicleReservation",reservation)
            .then(response => {
                for(var i = 0; i<this.vehiclereservations.length;i++){
                    if(this.vehiclereservations[i].id == reservation.id){
                        this.vehiclereservations.splice(i,1);
                    }
                }
            });
    },
    review:function(reservation,num){
        var getJwtToken = function() {
                    return localStorage.getItem("jwtToken");
                };
        axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
        axios.post("/api/reviewVehicle",{reservationId:reservation.id,rating:num})
            .then(response => {
                alert("success");
            });
        reservation.rating = num;
        this.setStars(reservation,num);
    },
    reviewHotel:function(reservation,num){
        var getJwtToken = function() {
                    return localStorage.getItem("jwtToken");
                };
        axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
        axios.post("/api/reviewHotel",{reservationId:reservation.id,rating:num})
            .then(response => {
                alert("success");
            });
        reservation.rating = num;
        this.setStars(reservation,num);
    },
    reviewFlight:function(reservation,num){
        var getJwtToken = function() {
                    return localStorage.getItem("jwtToken");
                };
        axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
        axios.post("/api/reviewFlight",{reservationId:reservation.id,rating:num})
            .then(response => {
                alert("success");
            });
        reservation.rating = num;
        this.setStars(reservation,num);
    },
    cancelFlightReservation: function(reservationId, index){
        var getJwtToken = function() {
                    return localStorage.getItem("jwtToken");
                };
        axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
        axios.get("/api/cancelFlightReservation/"+ reservationId)
            .then(response => {
                alert(response.data);
                 this.flightReservations.splice(index,1) 
        });
    },
    showDetails: function(hotelName,chosenRoom) {
        this.$router.push("/front/hotelRoom/"+ hotelName + "/" + chosenRoom);
    },
    showHotelSearch: function() {
        this.$router.push("/front/searchHotels");
    },
    flightReservationDetails(id){
        this.$router.push("/front/flightReservationDetails/"+ id );
    },
    friends(){
        this.$router.push("/front/friends" );
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
        
    },
    showHotelDetails: function(r) {
        console.log(r);
        this.hotelToShow = r;
        this.showingHotel = true;
    },
    cancelShowingHotel: function() {
        this.showingHotel = false;
    },
    cancelHotelReservation: function(hotelName,roomNumber) {
        var r;
        this.hotelReservations.forEach(element=> {
            if(element.hotelName == hotelName && element.roomNumber == roomNumber) {
                r = element;
                }
        });
        console.log(r)
        axios.post("/api/cancelHotelReservation",r)
            .then(response => {
                if(response.data == true) {
                    alert("Hotel reservation has been successfully canceled.");
                    this.hotelReservations.forEach(element=> {
                        if(element.hotelName == hotelName && element.roomNumber == roomNumber) {
                            this.hotelReservations.splice(element,1);
                        }
                    })
                } else {
                    alert("There was a problem with hotel cancelation.")
                }
            });
    }
  }
}

</script>
<style scoped>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
table{
    border: 1px solid darkgray;
    margin-left: auto;
    margin-right: auto;
    min-width: 80%;
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

nav > .nav.nav-tabs{

  border: none;
    color:#fff;
    background:#272e38;
    border-radius:0;

}
nav > div a.nav-item.nav-link,
nav > div a.nav-item.nav-link.active
{
  border: none;
    padding: 18px 25px;
    color:#fff;
    background:#272e38;
    border-radius:0;
}

nav > div a.nav-item.nav-link.active:after
 {
  content: "";
  position: relative;
  bottom: -60px;
  left: -10%;
  border: 15px solid transparent;
  border-top-color: #428bca ;
}
.tab-content{
  background: #fdfdfd;
    line-height: 25px;
    border: 1px solid #ddd;
    border-top:5px solid #428bca;
    border-bottom:5px solid #428bca;
    padding:30px 25px;
}

nav > div a.nav-item.nav-link:hover,
nav > div a.nav-item.nav-link:focus
{
  border: none;
    background: #428bca;
    color:#fff;
    border-radius:0;
    transition:background 0.20s linear;
}
</style>