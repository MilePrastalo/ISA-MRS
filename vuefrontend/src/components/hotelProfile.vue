<template>
  <div id="hotelProfile">
    <h1>{{hotel.name}}</h1>
    <table>
      <tr>
        <td>City:</td>
        <td>{{destinationName}}</td>
      </tr>
      <tr>
        <td>Destination Description:</td>
        <td>{{destintionDesc}}</td>
      </tr>
      <tr>
        <td>Hotel Description:</td>
        <td>{{hotel.description}}</td>
      </tr>
      <tr>
        <td>Hotel Rating:</td>
        <td>{{hotel.rating}}</td>
      </tr>
    </table>
    <br>
    <br>
    <table>
      <tr>
        <td>
          <h3>Rooms</h3>
          <table border="1">
      <tr>
        <td>Room Number</td>
        <td>Number Of Beds</td>
        <td>Price Per Day</td>
        <td>Rating</td>
        <td>Options</td>
      </tr>
      <tr v-for="r in hotel.rooms" :key="r.id">
        <td>{{r.roomNumber}}</td>
        <td>{{r.numberOfBeds}}</td>
        <td>{{r.pricePerDay}}</td>
        <td>{{r.rating}}</td>
        <td>
          <button @click="showDetails(r.roomNumber)">Details</button>
        </td>
      </tr>
    </table>
        </td>
        <td>
          <h3>Quick Reservations</h3>
          <table border="1">
      <tr>
        <td>Room Number</td>
        <td>Number Of Beds</td>
        <td>First Day</td>
        <td>Last Day</td>
        <td>Original Price</td>
        <td>Discount</td>
        <td>Discounted Price</td>
        <td>Options</td>
      </tr>
      <tr v-for="r in quickReservations" :key="r.id">
        <td>{{r.roomNumber}}</td>
        <td>{{r.numberOfBeds}}</td>
        <td>{{r.fDay +"."+r.fMonth +"."+r.fYear+"."}}</td>
        <td>{{r.lDay +"."+r.lMonth +"."+r.lYear+"."}}</td>
        <td>{{r.paidPrice}}</td>
        <td>{{r.discount}}</td>
        <td>{{parseFloat(r.paidPrice) - parseFloat(r.paidPrice) * (parseFloat(r.discount) / 100)}}</td>
        <td>
          <button @click="reserve(r)">Details</button>
        </td>
      </tr>
    </table>
        </td>
      </tr>
    </table>
    

    <h2>My Map</h2>
    <vl-map v-if="loaded = 1" style="height: 400px">
      <vl-view :zoom.sync="zoom" :center.sync="center" :rotation.sync="rotation" projection="EPSG:4326"></vl-view>
  
      <vl-layer-tile id="osm">
          <vl-source-osm></vl-source-osm>
      </vl-layer-tile>

      </vl-map>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'hotelProfile',
  components: {
  },
  data: function () {
  return {
    hotel : [],
    quickReservations: [],
    destinationName: "",
    destintionDesc: "",
    zoom: 15,
    center: [0,0],
    loaded: 0,
    rotation: 0,
  }
},
mounted(){
         var getJwtToken = function() {
            return localStorage.getItem('jwtToken');
        };
        axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();

         axios.get("http://localhost:8080/api/getHotel/"+this.$route.params.hotelName)
        .then(response => {
            this.hotel = response.data;
            this.destinationName = response.data.destination.name
            this.destintionDesc = response.data.destination.description
            var destCoordSplit = response.data.description.split(",");
            console.log(destCoordSplit);
            var falseCenter = [];
            falseCenter[0] = parseFloat(destCoordSplit[1]);
            falseCenter[1] = parseFloat(destCoordSplit[0]);
            this.center = falseCenter;
            this.loaded = 1;
          // Checks for quick reservations.
          var index = 0;
          for(let r in response.data.reservations) {
            if(response.data.reservations[r].username === null) {
              response.data.reservations[r].index = index;
              this.quickReservations.push(response.data.reservations[r]);
              index += 1;
            }
          }
          });
          
       
    },
    methods:{  
      showDetails: function(chosenRoom) {
        this.$router.push("/hotelRoom/"+ this.$route.params.hotelName+ "/" + chosenRoom);
        },
        back: function() {
        this.$router.push('/');
        },
        reserve: function(room) {
          axios.post("http://localhost:8080/api/buyQuickHotelReservation",{hotelName: room.hotelName,fYear:room.fYear,fMonth: room.fMonth,fDay: room.fDay,lYear: room.lYear,lMonth: room.lMonth,lDay: room.lDay, roomNumber: room.roomNumber})
        .then(response => {
            if(response.data === true) {
              alert("Your reservation is successful.");
              this.quickReservations.splice(room.index);
            
            } else {
              alert("Your reservation failed.");
            }
          });
        }      
    }
}
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
