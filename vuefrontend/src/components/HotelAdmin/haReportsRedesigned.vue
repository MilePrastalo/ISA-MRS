<template>
    <div id="flightCompanyReport">

        <br>
        <div class="reports">
            <button class="btn-primary space" @click="selected(0)">Rooms</button>
            <button class="btn-primary space" @click="selected(1)">Rating</button>
            <button class="btn-primary space" @click="selected(2)">Reservations</button>
            <button class="btn-primary space" @click="selected(3)">Earnings</button>
        </div>
        <br><br>
         <div class="container" v-if="tabSelected==0">
             <table tyle="text-align: left" class = "centered">
                 <tr>
                            <td>From:</td>
                            <td><input type="date" v-model="dateFrom"></td>
                        </tr>
                        <tr>
                            <td>To:</td>
                            <td><input type="date" v-model="dateTo"></td>
                        </tr>
                        <tr>
                            <td><button @click="checkAvailability()">Check</button></td>
                        </tr>
             </table>
             <br>
            <table class = 'table' v-if="available == true">
                <thead class="thead-dark">
                    <tr>
                    <th scope="col">Room Number: </th>
                    <th scope="col">Beds: </th>
                    <th scope="col">Price Per Day: </th>
                    <th scope="col">Rating: </th>
                    <th scope="col">Available</th>
                    </tr>
                </thead>
                <tbody>
                <tr v-for="r in hotel.rooms" :key="r.id">  
                    <td>{{r.roomNumber}}</td>
                    <td>{{r.numberOfBeds}}</td>
                    <td>{{r.pricePerDay}}</td>
                    <td>{{r.rating}}</td>
                    <td>{{r.available}}</td>
                </tr>
                </tbody>              
            </table>  
        </div>

        <div id="Ratings">
            <h2>Average rate for Flight Company: {{averageRate}}</h2>
            <br>
            <div class = "centered" id="ratingsCanvasArea" >
                <canvas ref="referencedElement" id="myChart" style="responsive:true;"></canvas>
            </div>
        </div>

        <div  class="row" id="SoldTickets">
            
            <div class="reports" >
                <form   @submit="onSubmitSoldTickets">
                    <table style="text-align: left" class = "centered">
                        <tr>
                            <td>From:</td>
                            <td><input type="date" v-model="dateFrom"></td>
                        </tr>
                        <tr>
                            <td>To:</td>
                            <td><input type="date" v-model="dateTo"></td>
                        </tr>
                        <tr style="text-align: center">
                            <td><div class="custom-control custom-radio custom-control-inline">
                                <input type="radio" class="custom-control-input" id="eco" checked @click="checkedClassTicket('daily')" name="inlineDefaultRadiosExample">
                                <label class="custom-control-label" for="eco">Daily</label>
                                </div>
                            </td>
                            <td><div class="custom-control custom-radio custom-control-inline">
                                <input type="radio" class="custom-control-input" id="bus" @click="checkedClassTicket('weekly')" name="inlineDefaultRadiosExample">
                                <label class="custom-control-label" for="bus">Weekly</label>
                                </div>
                            </td>
                            <td><div class="custom-control custom-radio custom-control-inline">
                                <input type="radio" class="custom-control-input" id="first" @click="checkedClassTicket('monthly')" name="inlineDefaultRadiosExample">
                                <label class="custom-control-label" for="first">Monthly</label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td><input type="submit" value="Apply"></td>
                        </tr>
                    </table>
                </form>
                 <br>
            </div>
            <div id = "ticketCanvasArea" class="row">
                <canvas ref="referencedElement" id="soldTicketsChart" style="responsive:true;"></canvas>
            </div>
        </div>

        <div  class="reports" id="Earnings">
                <form @submit="earningsReport">
                    <table style="text-align: left" class="centered">
                        <tr>
                            <td>From:</td>
                            <td><input type="date" v-model="dateFrom" required></td>
                        </tr>
                        <tr>
                            <td>To:</td>
                            <td><input type="date" v-model="dateTo" required></td>
                        </tr>
                        <tr>
                            <td><input type="submit" value="Apply"></td>
                        </tr>
                    </table>
                </form>
               <br>
               <div v-if="earningsVisible" >
                   <h2>Incomes from given period = {{this.earnings}}</h2>
               </div>
        </div>

    </div>
</template>

<script>
import Chart from "chart.js";


export default {
  name: 'flightCompanyReport',
  props: {
      hotel: Object
  },
  data:function(){
    return {

        tabSelected:0,
        dataForCompanyRates: [],
        dataSoldTickets: [],
        averageRate: 0,
        dateFrom: "",
        dateTo: "",
        earnings: 0,
        earningsVisible: false,
        reportKindTickets: "daily",
        fYear: 0,
        fMonth: 0,
        fDay: 0,
        lYear: 0,
        lMonth: 0,
        lDay: 0,
        available: false
    }
  },
  mounted(){

    var getJwtToken = function() {
        return localStorage.getItem('jwtToken');
    };

    axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
    axios.get("/api/getHotelAverageRate")
    .then(response => {
            this.dataForCompanyRates = response.data;
    });
    document.getElementById('Ratings').hidden = true;
    document.getElementById('SoldTickets').hidden = true;
    document.getElementById('Earnings').hidden = true;
  },

  methods:{
        
    selected:function(val){
        
        this.tabSelected = val;
        if(this.tabSelected == 1){
            document.getElementById('SoldTickets').hidden = true;
            document.getElementById('Earnings').hidden = true;
            document.getElementById('Ratings').hidden = false;
            this.hotelRate();
        }
        else if (this.tabSelected == 2) {
            document.getElementById('Ratings').hidden = true;
            document.getElementById('Earnings').hidden = true;
            document.getElementById('SoldTickets').hidden = false;
        }
        else if (this.tabSelected == 3) {
            document.getElementById('Earnings').hidden = false;
            document.getElementById('Ratings').hidden = true;
            document.getElementById('SoldTickets').hidden = true;
           
        }
        else {
            document.getElementById('SoldTickets').hidden = true;
            document.getElementById('Ratings').hidden = true;
            document.getElementById('Earnings').hidden = true;
        }
    },

    hotelRate: function(){
        var suma = 0;
        var cnt = 0;
        var i;
        for (i = 0; i < this.dataForCompanyRates.length; i++){
            suma += (i+1) * this.dataForCompanyRates[i];
            cnt += this.dataForCompanyRates[i];
        }
        this.averageRate = suma/cnt;
        var ctx = document.getElementById('myChart').getContext('2d');
        var myChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ['1', '2', '3', '4', '5'],
                datasets: [{
                    label: '# of Votes',
                    data: this.dataForCompanyRates,
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)',
                        'rgba(75, 192, 192, 0.2)',
                        'rgba(153, 102, 255, 0.2)'
                    ],
                    borderColor: [
                        'rgba(255, 99, 132, 1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero: true
                        }
                    }]
                }
            }
        });
    },
    onSubmitSoldTickets: function(e){

        e.preventDefault();
        console.log(localStorage.getItem('jwtToken'));
        axios.defaults.headers.common['Authorization'] = "Bearer " + localStorage.getItem('jwtToken');
        axios.post("/api/getReservedRooms",{dateFrom: this.dateFrom, dateTo: this.dateTo, kind: this.reportKindTickets})
        .then(response => {
                this.dataSoldTickets = response.data;
                this.soldTicketReport();
        });
    },

    soldTicketReport: function(){
        document.getElementById('soldTicketsChart').remove(); // this is my <canvas> element
        document.getElementById('ticketCanvasArea').innerHTML = '<canvas ref="referencedElement" id="soldTicketsChart" style="responsive:true;"><canvas>';
        var ctx = document.getElementById('soldTicketsChart').getContext('2d');
        var myChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: this.dataSoldTickets.labels,
                datasets: [{
                    label: '# of sold tickets',
                    data:  this.dataSoldTickets.data,
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)',
                        'rgba(75, 192, 192, 0.2)',
                        'rgba(153, 102, 255, 0.2)'
                    ],
                    borderColor: [
                        'rgba(255, 99, 132, 1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero: true
                        }
                    }]
                }
            }
        });
    },

    earningsReport: function(e){

        e.preventDefault();
        var getJwtToken = function() {
            return localStorage.getItem('jwtToken');
        };
        axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
        axios.post("/api/getHotelEarningsReport",{dateFrom: this.dateFrom, dateTo: this.dateTo})
        .then(response => {
                this.earnings = response.data;
                this.earningsVisible = true;
        });
    },

    checkedClassTicket: function(kind){
        this.reportKindTickets = kind;
    },
    checkAvailability: function() {
        this.splitDate();
        this.checkReservations();
        this.available = true;
    },
    checkReservations: function() {
        for(let i in this.hotel.rooms) {
            this.hotel.rooms[i].available = "Yes";
          for(let r in this.hotel.reservations) {
              if(this.hotel.reservations[r].roomNumber == this.hotel.rooms[i].roomNumber && this.hotel.reservations[r].username != null && this.hotel.reservations[r].username != "") { 
                  // Date must be before or after existing reservation.

                  // Date is before:
                  if(this.lYear < this.hotel.reservations[r].fYear) {
                    return;
                  } else if (this.lYear === this.hotel.reservations[r].fYear && this.lMonth < this.hotel.reservations[r].fMonth) {
                    return;
                  }else if (this.lYear === this.hotel.reservations[r].fYear && this.lMonth === this.hotel.reservations[r].fMonth && this.lDay < this.hotel.reservations[r].fDay) {
                    return;
                  } // Date is after: 
                   else if (this.fYear > this.hotel.reservations[r].lYear) {
                    return;
                   } else if (this.fYear === this.hotel.reservations[r].lYear && this.fMonth > this.hotel.reservations[r].lMonth) {
                    return;
                   } else if(this.fYear === this.hotel.reservations[r].lYear && this.fMonth === this.hotel.reservations[r].lMonth && this.fDay > this.hotel.reservations[r].lDay) {
                    return;
                   } else {
                     this.hotel.rooms[i].available = "No";
                    return;
                   }
              }
          }
          }
          
          return;
      },
      splitDate: function() {
         if(this.dateFrom == "" || this.dateTo == "") {
              alert("Please eneter both dates.");
         }
        var splitFirstDay = this.dateFrom.split("-");
        this.fYear = parseInt(splitFirstDay[0]);
        this.fMonth = parseInt(splitFirstDay[1]);
        this.fDay = parseInt(splitFirstDay[2]);

        var splitLastDay = this.dateTo.split("-");
        this.lYear = parseInt(splitLastDay[0]);
        this.lMonth = parseInt(splitLastDay[1]);
        this.lDay = parseInt(splitLastDay[2]);
      }
  }
}

</script>

<style scopped>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

.reports{
    width: 1150px;
}

#ratingsCanvasArea{
    width: 700px;
    height: 500px;
}

#ticketCanvasArea{
    width: 800px;
    height: 700px;
    margin-left: auto;
    margin-right: auto;
}

.space{
    margin: 1%;
}
</style>