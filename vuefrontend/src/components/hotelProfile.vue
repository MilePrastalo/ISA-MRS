<template>
<div>
  <div v-if="currentPage === 1" class="DescDiv">
    <div class="hpHotelDesc" v-if="hotel">
    <h2>{{hotel.name}}</h2>
    <br>
    <table class="table">
      <tr>
        <td>
          <table class="table">
            <thead class="thead-dark">
            <th>Hotel Informations: </th>
            <th> </th>
            </thead>
      <tr>
        <td>City:</td>
        <td>{{hotel.cityName}}</td>
      </tr>
      <tr>
        <td>Hotel Description:</td>
        <td>{{hotel.description}}</td>
      </tr>
      <tr>
        <td>Hotel Rating:</td>
        <td>{{hotel.rating}}</td>
      </tr>
    </table >
        </td>
        <td>
          <yandex-map
      :coords="[this.hotel.latitude,this.hotel.longitude]"
      zoom="14"
      style="width:350px;height:250px;"
      :controlss="['zoomControl']"
      map-type="hybrid"
    >
      <ymap-marker
        marker-id="1"
        marker-type="placemark"
        :coords="[this.hotel.latitude,this.hotel.longitude]"
        :marker-fill="{color: '#0E4779', opacity: 0.5}"
        :marker-stroke="{color: '#0E4779',width: 4}"
      ></ymap-marker>
    </yandex-map>
        </td>
      </tr>
    
    </table >
    </div>
    <br>
    <br>
    <table class="SeperateTable">
      <tr>
        <td>
          <h3>Rooms</h3>
          <table class="table">
            <thead class="thead-dark">
      <tr>
        <th>Room Number</th>
        <th>Number Of Beds</th>
        <th>Price Per Day</th>
        <th>Rating</th>
        <th>Options</th>
      </tr>
            </thead>
      <tr v-for="r in hotel.rooms" :key="r.id">
        <td>{{r.roomNumber}}</td>
        <td>{{r.numberOfBeds}}</td>
        <td>{{r.pricePerDay}}</td>
        <td>{{r.rating}}</td>
        <td>
          <button @click="showDetails(r.roomNumber)" class="btn-primary">Details</button>
        </td>
      </tr>
    </table>
        </td>
        <td>
          <h3>Quick Reservations</h3>
          <table class="table">
            <thead class="thead-dark">
      <tr>
        <th>Room Number</th>
        <th>Number Of Beds</th>
        <th>First Day</th>
        <th>Last Day</th>
        <th>Original Price</th>
        <th>Discount</th>
        <th>Discounted Price</th>
        <th>Options</th>
      </tr>
      </thead>
      <tr v-for="r in quickReservations" :key="r.id">
        <td>{{r.roomNumber}}</td>
        <td>{{r.numberOfBeds}}</td>
        <td>{{r.fDay +"."+r.fMonth +"."+r.fYear+"."}}</td>
        <td>{{r.lDay +"."+r.lMonth +"."+r.lYear+"."}}</td>
        <td>{{r.paidPrice}}</td>
        <td>{{r.discount}}</td>
        <td>{{parseFloat(r.paidPrice) - parseFloat(r.paidPrice) * (parseFloat(r.discount) / 100)}}</td>
        <td>
          <button @click="reserve(r)" class="btn-primary">Buy</button>
        </td>
      </tr>
    </table>
        </td>
      </tr>
    </table>
    </div>
   <div v-if="currentPage === 2">
     <hotelRoom :hotelName="hotelName" :roomNumber="roomNum" :firstDay="firstDay" v-on:hr="reservedHotel"/>
      <br>
     <button @click="back()" class="btn-primary">Back</button>
  </div>

</div>
</template>

<script>
import axios from "axios";
import hotelRoom from "./hotelRoom.vue";
export default {
  name: "hotelProfile",
  props:["hotelName","firstDay"],
  components: {
    hotelRoom:hotelRoom
  },
  data: function () {
  return {
    hotel : [],
    quickReservations: [],
    currentPage: 1,
    roomNum: 0
  }
},
mounted(){
         var getJwtToken = function() {
            return localStorage.getItem("jwtToken");
        };
        axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
         axios.get("http://localhost:8080/api/getHotel/" + this.hotelName)
        .then(response => {
            this.hotel = response.data;

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
        this.roomNum = chosenRoom;
          this.currentPage = 2;
        },
        back: function() {
        this.currentPage = 1;
        },
        reserve: function(room) {
          axios.post("http://localhost:8080/api/buyQuickHotelReservation",{hotelName: room.hotelName,fYear:room.fYear,fMonth: room.fMonth,fDay: room.fDay,lYear: room.lYear,lMonth: room.lMonth,lDay: room.lDay, roomNumber: room.roomNumber})
        .then(response => {
            if(response.data != null) {
              alert("Your reservation is successful.");
              this.quickReservations.splice(room.index);
              console.log(response.data)
              this.$emit("rr",response.data);
            
            } else {
              alert("Your reservation failed.");
            }
          });
        },
        reservedHotel: function(id) {
          this.$emit("rr",id);
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
.Tables{
    border: 1px solid black;
    margin:auto;
    margin-top: 2%;
}
.Tables td{
    border:1px solid black;
}
.Tables th{
    border:1px solid black;
}
.SeperateTable {
  border-spacing: 70px;
  border-collapse: separate;
}
</style>
