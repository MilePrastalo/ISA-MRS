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
    <table border="1">
      <tr>
        <td>Room Number</td>
        <td>Number Of Beds</td>
        <td>Price Per Day</td>
        <td>Options</td>
      </tr>
      <tr v-for="r in hotel.rooms" :key="r.id">
        <td>{{r.roomNumber}}</td>
        <td>{{r.numberOfBeds}}</td>
        <td>{{r.pricePerDay}}</td>
        <td>
          <button @click="showDetails(r.roomNumber)">Details</button>
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
    destinationName: "",
    destintionDesc: "",
    zoom: 15,
    center: [0,0],
    loaded: 0,
    rotation: 0,
  }
},
mounted(){
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
          });
       
    },
    methods:{  
      showDetails: function(chosenRoom) {
        this.$router.push("/hotelRoom/"+ this.$route.params.hotelName+ "/" + chosenRoom);
        },
        back: function() {
        this.$router.push('/');
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
