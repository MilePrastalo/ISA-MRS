<template>
    <div id="registeredUserFrontPage">
        <div class="container">
            <div class="row" id="firstrow"> 
                <button class="col-lg-4">Invites</button>
                <button @click="profile" class="col-lg-4">Profil</button>
                <button @click="logout" id="logoutbt" class="col-lg-2">Log Out</button>
            </div>
            <div class="row"> 
                <button class="col-lg-2">Airlines</button>
                <button class="col-lg-2">Hotels</button>
                <button class="col-lg-2" @click="rentACar">Rent a car</button>
                <button class="col-lg-2">Friends</button>
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
                    <table>
                        <tr>
                            <th>Origin</th>
                            <th>Destination</th>
                            <th>Date</th>
                            <th>Price</th>
                            <th>Details</th>
                            <th>Cancel</th>
                        </tr>
                    </table>
                </div>
                <div id="HotelsReservations" class="centered col-lg-10" hidden="true"><table>
                        <tr>
                            <th>Destination</th>
                            <th>Date</th>
                            <th>Price</th>
                            <th>Details</th>
                            <th>Cancel</th>
                        </tr>
                    </table>
                </div>
                <div id="CarsReservations" class="centered col-lg-10" hidden="true">
                    <table>
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
                            <td><Button @click="cancel(reservation)">Cancel</Button></td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</template>

<script>


export default {
  name: 'registeredUserFrontPage',
  data: function(){
      return{
          vehiclereservations:[]
      }
  },
  mounted(){
      var getJwtToken = function() {
                    return localStorage.getItem('jwtToken');
                };
        axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
        axios.get("http://localhost:8080/api/geteReservations")
            .then(response => {
                console.log(response);
                this.vehiclereservations = response.data;
            }); 
  },
  methods : {
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
    cancel:function(reservation){
        var getJwtToken = function() {
                    return localStorage.getItem('jwtToken');
                };
        axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
        axios.post("http://localhost:8080/api/cancelVehicleReservation",reservation)
            .then(response => {
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

</style>