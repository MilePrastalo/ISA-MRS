<template>
   <div id = "unregisteredHotelRoom" v-if="hotel">   
            <div>
              <table class="table">
                <tr>
                  <td><b>Room Number: </b></td>
                  <td>{{this.room.roomNumber}}</td>
                </tr>
                <tr>
                  <td><b>Number Of Beds: </b></td>
                  <td>{{this.room.numberOfBeds}}</td>
                </tr>
                <tr>
                  <td><b>Price Per Day: </b></td>
                  <td>{{this.room.pricePerDay}}</td>
                </tr>
              </table>
              <br>
               <table class="table" >
                 <thead class="thead-dark">
                        <tr>
                            <th><b>Additional Charge</b></th>
                            <th><b>Price Per Day</b></th>
                            <th><b>Check</b></th>
                        </tr>
                 </thead>
                    <tr v-for="a in room.additionalCharges" :key="a.id">  
                        <td>{{a.name}}</td>
                        <td>{{a.pricePerDay}}</td>
                        <td><input type="checkbox" @click="aCChanged(a.name)"></td>
                    </tr>
                </table>
                <br>
                <br>
                <table>
                    <tr>
                        <td><b>First Day:</b></td>
                        <td><input v-model="firstDay" type="date" @click="changedDate()"></td>
                        <td><b>Last Day:</b></td>
                        <td><input v-model="lastDay" type="date" @click="changedDate()"></td>
                    </tr>
                    <tr>
                        <td> </td>
                        <td><b>Check if available: </b></td>
                        <td><button @click="splitDate()" class="btn-primary">Check</button></td>
                    </tr>
                    <tr v-if="this.available == 1">
                      <td> </td>
                      <td><b>Room is available</b> </td>
                    </tr>
                    <tr v-if="this.available == 2">
                      <td> </td>
                      <td><b>Room is taken</b> </td>
                    </tr>
                </table>
            </div>
    </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'unregisteredHotelRoom',
  props:["hotelName","roomNumber"],
  components: {
  },
  data: function () {
  return {
    hotel : [],
    room : [],
    available : 0,
    firstDay: "",
    lastDay: "",
    fYear: 0,
    fMonth: 0,
    fDay: 0,
    lYear: 0,
    lMonth: 0,
    lDay: 0,
    additionalCharges: []
  }
},
mounted(){
         axios.get("http://localhost:8080/api/getHotel/" + this.$route.params.hotelName)
        .then(response => {
            this.hotel = response.data
            for(let r in response.data.rooms) {

             if(response.data.rooms[r].roomNumber == this.roomNumber) {
                 this.room = response.data.rooms[r];
             }
         } 
          });      
    },
    methods:{  
        checkReservations: function() {
          for(let r in this.hotel.reservations) {
              if(this.hotel.reservations[r].roomNumber == this.room.roomNumber) { 
                  // Date must be before or after existing reservation.

                  // Date is before:
                  if(this.lYear < this.hotel.reservations[r].fYear) {
                    alert("Room is available.");
                    this.available = 1;
                    return;
                  } else if (this.lYear === this.hotel.reservations[r].fYear && this.lMonth < this.hotel.reservations[r].fMonth) {
                    this.available = 1;
                    alert("Room is available.");
                    return;
                  }else if (this.lYear === this.hotel.reservations[r].fYear && this.lMonth === this.hotel.reservations[r].fMonth && this.lDay < this.hotel.reservations[r].fDay) {
                    this.available = 1;
                    alert("Room is available.");
                    return;
                  } // Date is after: 
                   else if (this.fYear > this.hotel.reservations[r].lYear) {
                     this.available = 1;
                    alert("Room is available.");
                    return;
                   } else if (this.fYear === this.hotel.reservations[r].lYear && this.fMonth > this.hotel.reservations[r].lMonth) {
                     this.available = 1;
                    alert("Room is available.");
                    return;
                   } else if(this.fYear === this.hotel.reservations[r].lYear && this.fMonth === this.hotel.reservations[r].lMonth && this.fDay > this.hotel.reservations[r].lDay) {
                     this.available = 1;
                    alert("Room is available.");
                    return;
                   } else {
                     alert("Room is taken.");
                     this.available = 2;
                    return;
                   }
              }
          }
          this.available = 1;
          alert("Room is available.");
          return;
      },
      checkDate: function() {
          var today = new Date();
          var tYear = today.getFullYear();
          var tMonth = today.getMonth() + 1;
          var tDay = today.getDate();
          
          if(this.fYear - tYear >= 0 && this.lYear - tYear >= 0 && this.lYear - this.fYear >= 0) {
              if(this.fMonth - tMonth >= 0 && this.lMonth - tMonth >= 0 && this.lMonth - this.fMonth >= 0) {
                  if(this.lMonth - this.fMonth > 0 && this.lMonth - tMonth > 0) {
                      this.checkReservations();
                      return;
                  }
                  if(this.fMonth - tMonth > 0 && this.lMonth - this.fMonth >= 0 && this.lDay - this.fDay > 0) {
                      this.checkReservations();
                      return;
                  } else {
                    if(this.fDay - tDay > 0 && this.lDay - tDay > 0 && this.lDay - this.fDay > 0) {
                      this.checkReservations();
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
      splitDate: function() {
         if(this.firstDay == "" || this.lastDay == "") {
              alert("Please eneter both dates.");
         }
        var splitFirstDay = this.firstDay.split("-");
        this.fYear = parseInt(splitFirstDay[0]);
        this.fMonth = parseInt(splitFirstDay[1]);
        this.fDay = parseInt(splitFirstDay[2]);

        var splitLastDay = this.lastDay.split("-");
        this.lYear = parseInt(splitLastDay[0]);
        this.lMonth = parseInt(splitLastDay[1]);
        this.lDay = parseInt(splitLastDay[2]);
        this.checkDate();
      },
      reserve: function() {
        axios.post("http://localhost:8080/api/addHotelReservation",{username: this.user.username,hotelName: this.hotel.name,fYear: this.fYear,fMonth: this.fMonth,fDay: this.fDay,lYear: this.lYear,lMonth: this.lMonth,lDay: this.lDay, roomNumber: this.room.roomNumber,additionalCharges:this.additionalCharges})
        .then(response => {
            if(response.data != null)  {
              alert("Your reservation is successful.");
              this.available = 0;
              
              this.hotel.reservations.push({username: this.user.username,hotelName: this.hotel.name,fYear: this.fYear,fMonth: this.fMonth,fDay: this.fDay,lYear: this.lYear,lMonth: this.lMonth,lDay: this.lDay, roomNumber: this.room.roomNumber,additionalCharges:this.additionalCharges});
              this.additionalCharges = [];
              this.$emit("hr",response.data);
            } else {
              alert("Your reservation failed.");
              this.available = 0;
            }
          });
      },
      // Checks if Additional Charge exists, than adds it or removes it.
      aCChanged: function(aCName) {
        var index = -1;
        for(let a in this.additionalCharges) {
          if(this.additionalCharges[a] == aCName) {
            index = a;
            break;
          }
        }
        if(index != -1) {
          this.additionalCharges.splice(index,1);
        } else {
          this.additionalCharges.push(aCName);     
            }
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
