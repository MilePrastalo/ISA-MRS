<template>
   <div id = "hotelRoom">
            <h1>{{hotel.name}}</h1>     

            <div>
              <table>
                <th>
                  <td>Hotel: </td>
                  <td>{{this.hotel.name}}</td>
                </th>
                <tr>
                  <td>Room Number: </td>
                  <td>{{this.room.roomNumber}}</td>
                </tr>
                <tr>
                  <td>Number Of Beds: </td>
                  <td>{{this.room.numberOfBeds}}</td>
                </tr>
                <tr>
                  <td>Price Per Day: </td>
                  <td>{{this.room.pricePerDay}}</td>
                </tr>
              </table>
               <table border="1" >
                        <tr>
                            <td>Additional Charge</td>
                            <td>Price Per Day</td>

                        </tr>
                    <tr v-for="a in room.additionalCharges" :key="a.id">  
                        <td>{{a.name}}</td>
                        <td>{{a.pricePerDay}}</td>
                    </tr>
                </table>
                <table>
                    <tr>
                        <td>First Day:</td>
                        <td><input v-model="firstDay" type="date" @click="changedDate()"></td>
                        <td>Last Day:</td>
                        <td><input v-model="lastDay" type="date" @click="changedDate()"></td>
                    </tr>
                    <tr>
                        <td>Check if available: </td>
                        <td><button @click="checkDate()" >Check</button></td>
                    </tr>
                    <tr v-if="this.available == 1 && this.user.name != null">
                      <td>Make reservation: </td>
                      <td><button @click="reserve()">Check</button></td>
                    </tr>
                </table>
            </div>
            <button @click="back()">Back</button>
    </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'hotelRoom',
  components: {
  },
  data: function () {
  return {
    hotel : [],
    room : [],
    user: [],
    available : 0,
    firstDay: "",
    lastDay: ""
  }
},
mounted(){
         var getJwtToken = function() {
            return localStorage.getItem('jwtToken');
        };
        axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
        axios.get("http://localhost:8080/api/getLogUser")
        .then(response => {
            this.user = response.data;
          });

         axios.get("http://localhost:8080/api/getHotel/"+this.$route.params.hotelName)
        .then(response => {
            this.hotel = response.data
            for(let r in response.data.rooms) {

             if(response.data.rooms[r].roomNumber == this.$route.params.roomNumber) {
                 this.room = response.data.rooms[r];
             }
         } 
          });      
    },
    methods:{  
      back: function() {
        this.$router.push("/hotelProfile/"+ this.$route.params.hotelName);
        },
        checkReservations: function(fYear,fMonth,fDay,lYear,lMonth,lDay) {
          for(let r in this.hotel.reservations) {
              if(this.hotel.reservations[r].room.roomNumber == this.room.roomNumber) {
                  var rSplitFirstDay = this.hotel.reservations[r].room.firstDay.split("-");
                  var rfYear = rSplitFirstDay[0];
                  var rfMonth = rSplitFirstDay[1];
                  var rfDay = rSplitFirstDay[2];

                  var rSplitLastDay = this.hotel.reservations[r].room.lastDay.split("-");
                  var rlYear = rSplitLastDay[0];
                  var rlMonth = rSplitLastDay[1];
                  var rlDay = rSplitLastDay[2];

                  if((rfYear - fYear == 0 && rfMonth - fMonth >= 0 && rfDay - fDay >= 0) && (lYear - rfYear == 0 && lMonth - rfMonth >= 0 && lDay - rfDay >= 0)) {
                      this.available = 0;
                      return;
                  }

                  if((fYear - rfYear == 0 && fMonth - rfMonth >= 0 && fDay - rfDay >= 0) && (rlYear - fYear == 0 && rlMonth - fMonth >= 0 && rlDay - fDay >= 0)) {
                      this.available = 0;
                      return;
                  }

                  this.available = 1;
                  return;

              }
          }
          this.available = 1;
          return;
      },
      checkDate: function() {
          if(this.firstDay == "" || this.lastDay == "") {
              alert("Please eneter both dates.");
          }
          
          var today = new Date();
          var tYear = today.getFullYear();
          var tMonth = today.getMonth() + 1;
          var tDay = today.getDate();

          var splitFirstDay = this.firstDay.split("-");
          var fYear = splitFirstDay[0];
          var fMonth = splitFirstDay[1];
          var fDay = splitFirstDay[2];

          var splitLastDay = this.lastDay.split("-");
          var lYear = splitLastDay[0];
          var lMonth = splitLastDay[1];
          var lDay = splitLastDay[2];
          
          if(fYear - tYear >= 0 && lYear - tYear >= 0 && lYear - fYear >= 0) {
              if(fMonth - tMonth >= 0 && lMonth - tMonth >= 0 && lMonth - fMonth >= 0) {
                  if(lMonth - fMonth > 0 && lMonth - tMonth > 0) {
                      this.checkReservations(fYear,fMonth,fDay,lYear,lMonth,lDay);
                      return;
                  }
                  if(fMonth - tMonth > 0 && lMonth - fMonth >= 0 && lDay - fDay > 0) {
                      this.checkReservations(fYear,fMonth,fDay,lYear,lMonth,lDay);
                      return;
                  } else {
                    if(fDay - tDay > 0 && lDay - tDay > 0 && lDay - fDay > 0) {
                      this.checkReservations(fYear,fMonth,fDay,lYear,lMonth,lDay);
                      return;
                  } else {
                      alert("Incorect date day.")
                    }
                  }
              } else {
                  alert("Incorect date month.")
              }
          } else {
              alert("Incorect date year.")
          }
      },
      changedDate: function() {
        this.available = 0;
      },
      reserve: function() {
        
      }
          
    }
}

</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
