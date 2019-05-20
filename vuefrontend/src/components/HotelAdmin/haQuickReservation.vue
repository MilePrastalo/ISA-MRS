<template>
  <div id="haQuickReservation">
    <h1>Quick Reservations</h1>
    <br>
    <br>
    <table>
      <tr>
        <td>
          <h3>Choose Room</h3>
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
          <button @click="chooseRoom(r)">Choose</button>
        </td>
      </tr>
    </table>
        </td>
        <td border="1" v-if="showReservation == 1">
          <h3>Make Quick Reservation</h3>
        <table>
                <tr>
                  <td>Room Number: </td>
                  <td>{{room.roomNumber}}</td>
                </tr>
                <tr>
                  <td>Number Of Beds: </td>
                  <td>{{room.numberOfBeds}}</td>
                </tr>
                <tr>
                  <td>Price Per Day: </td>
                  <td>{{room.pricePerDay}}</td>
                </tr>
                <tr>
                  <td>Discount in percents: </td>
                  <td><input type="text" v-model="discount" ></td>
                </tr>
              </table>
               <table border="1" >
                        <tr>
                            <td>Additional Charge</td>
                            <td>Price Per Day</td>
                            <td>Check</td>

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
                        <td><button @click="splitDate()" >Check</button></td>
                    </tr>
                    <tr v-if="this.available == 1">
                      <td>Make reservation: </td>
                      <td><button @click="reserve()">Reserve Room</button></td>
                    </tr>
                </table>
        </td>
      </tr>
    </table>
  </div>
</template>

<script>

export default {
  name: 'haQuickReservation',
  props: {
      hotel: Object
  },
  components: {

  },
  data: function () {
  return {
    room: [],
    discount: 0,
    firstDay: "",
    lastDay: "",
    fYear: 0,
    fMonth: 0,
    fDay: 0,
    lYear: 0,
    lMonth: 0,
    lDay: 0,
    showReservation: 0,
    available: 0

  }
},
    methods:{  
      chooseRoom: function(chosenRoom) {
        this.room = chosenRoom;
        this.showReservation = 1;
        },
        checkReservations: function() {
          for(let r in this.hotel.reservations) {
              if(this.hotel.reservations[r].roomNumber == this.room.roomNumber) { 
                  // Date must be before or after existing reservation.

                  // Date is before:
                  if(this.lYear < this.hotel.reservations[r].fYear) {
                    this.available = 1;
                    return;
                  } else if (this.lYear === this.hotel.reservations[r].fYear && this.lMonth < this.hotel.reservations[r].fMonth) {
                    this.available = 1;
                    return;
                  }else if (this.lYear === this.hotel.reservations[r].fYear && this.lMonth === this.hotel.reservations[r].fMonth && this.lDay < this.hotel.reservations[r].fDay) {
                    this.available = 1;
                    return;
                  } // Date is after: 
                   else if (this.fYear > this.hotel.reservations[r].lYear) {
                     this.available = 1;
                    return;
                   } else if (this.fYear === this.hotel.reservations[r].lYear && this.fMonth > this.hotel.reservations[r].lMonth) {
                     this.available = 1;
                    return;
                   } else if(this.fYear === this.hotel.reservations[r].lYear && this.fMonth === this.hotel.reservations[r].lMonth && this.fDay > this.hotel.reservations[r].lDay) {
                     this.available = 1;
                    return;
                   } else {
                     alert("Room is taken.")
                     this.available = 0;
                    return;
                   }
              }
          }
          this.available = 1;
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
        if(this.discount <= 0) {
          alert("Discount must be grater than zero.");
          return;
        }
        axios.post("http://localhost:8080/api/addQuickHotelReservation",{hotelName: this.hotel.name,fYear: this.fYear,fMonth: this.fMonth,fDay: this.fDay,lYear: this.lYear,lMonth: this.lMonth,lDay: this.lDay, roomNumber: this.room.roomNumber,discount: this.discount})
        .then(response => {
            if(response.data === true) {
              alert("Your reservation is successful.");
              this.available = 0;
              
              this.hotel.reservations.push({hotelName: this.hotel.name,fYear: this.fYear,fMonth: this.fMonth,fDay: this.fDay,lYear: this.lYear,lMonth: this.lMonth,lDay: this.lDay, roomNumber: this.room.roomNumber,discount: this.discount});
            } else {
              alert("Your reservation failed.");
              this.available = 0;
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
