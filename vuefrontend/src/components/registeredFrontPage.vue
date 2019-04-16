<template>
    <div id="registeredUserFrontPage">
        <div class="container">
            <div class="row"> 
                <button>Pozivnice</button>
                <button>Profil</button>
                <button>Log Out</button>
            </div>
            <div class="row"> 
                <button>Airlines</button>
                <button>Hotels</button>
                <button>Rent a car</button>
                <button>Friends</button>
            </div>
            <div class="row"> 
                <h2>Reservations</h2>
            </div>
            <div class="row">
                <ul class="nav nav-tabs">
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
                <div id="FlightsReservations">
                    <table>
                        <tr>
                            <th>Origin</th>
                            <th>Destination</th>
                            <th>Date</th>
                            <th>Price</th>
                            <th>Cancel</th>
                        </tr>
                    </table>
                </div>
                <div id="HotelsReservations" hidden="true">Hotel reservations</div>
                <div id="CarsReservations" hidden="true">Rent a car rezervations</div>
            </div>
        </div>
    </div>
</template>

<script>


export default {
  name: 'registeredUserFrontPage',
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
}
th{
    border: 1px solid darkgray;
}
ul{
    align-self: center;
    align-content: center;
    align-items: center;
}
.row{
    margin-left: 30%;
}
</style>