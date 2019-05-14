<template>
    <div id="flightCompanyReport">


        <div class="row">
            <button @click="selected(0)">Flights</button>
            <button @click="selected(1)">Rating</button>
            <button @click="selected(2)">Sold Tickets</button>
            <button @click="selected(3)">Earnings</button>
        </div>


      
        <div class="row" id="Flights" v-if="tabSelected==0">
            Hello Flights
        </div>


        <div  class="row" id="Ratings">
            <h2>Average rate for Flight Company: {{averageRate}}</h2>
            <canvas ref="referencedElement" id="myChart"  style="responsive:true;"></canvas>
        </div>

        
        <div  class="row" id="SoldTickets">
            <div class = "row">
                <table style="text-align: left">
                    <tr>
                        <td>From:</td>
                        <td><input type="date"></td>
                    </tr>
                    <tr>
                        <td>To:</td>
                        <td><input type="date"></td>
                    </tr>
                    <tr style="text-align: center">
                        <td><div class="custom-control custom-radio custom-control-inline">
                            <input type="radio" class="custom-control-input" id="eco" checked @click="checkedClass(1)" name="inlineDefaultRadiosExample">
                            <label class="custom-control-label" for="eco">Daily</label>
                            </div>
                        </td>
                        <td><div class="custom-control custom-radio custom-control-inline">
                            <input type="radio" class="custom-control-input" id="bus" @click="checkedClass(2)" name="inlineDefaultRadiosExample">
                            <label class="custom-control-label" for="bus">Weekly</label>
                            </div>
                        </td>
                        <td><div class="custom-control custom-radio custom-control-inline">
                            <input type="radio" class="custom-control-input" id="first" @click="checkedClass(3)" name="inlineDefaultRadiosExample">
                            <label class="custom-control-label" for="first">Monthly</label>
                            </div>
                        </td>
                    </tr>
               </table>
               <br>
               <canvas ref="referencedElement" id="soldTicketsChart"  style="responsive:true;"></canvas>
            </div>
        </div>
        <div  class="row" id="Earnings">
            <div class = "row">
                <table style="text-align: left">
                    <tr>
                        <td>From:</td>
                        <td><input type="date"></td>
                    </tr>
                    <tr>
                        <td>To:</td>
                        <td><input type="date"></td>
                    </tr>
               </table>
               <br>
               <canvas ref="referencedElement" id="earningsReport" style="responsive:true;"></canvas>
            </div>
        </div>
    </div>
</template>

<script>
import Chart from "chart.js";


export default {
  name: 'flightCompanyReport',
  data:function(){
    return {
        tabSelected:0,
        dataForCompanyRates: [],
        averageRate: 0
    }
  },
  mounted(){

    var getJwtToken = function() {
        return localStorage.getItem('jwtToken');
    };
    axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
    axios.get("http://localhost:8080/api/getCompanyAverageRate")
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
            this.flightCompanyRate();
        }
        else if (this.tabSelected == 2) {
            document.getElementById('Ratings').hidden = true;
            document.getElementById('Earnings').hidden = true;
            document.getElementById('SoldTickets').hidden = false;
            this.soldTicketReport();
        }
        else if (this.tabSelected == 3) {
            document.getElementById('Ratings').hidden = true;
            document.getElementById('SoldTickets').hidden = true;
            document.getElementById('Earnings').hidden = false;
            this.earningsReport();
        }
        else {
            document.getElementById('SoldTickets').hidden = true;
            document.getElementById('Ratings').hidden = true;
            document.getElementById('Earnings').hidden = true;
        }
    },

    flightCompanyRate: function(){
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

    soldTicketReport: function(){
        var ctx = document.getElementById('soldTicketsChart').getContext('2d');
        var myChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ['Monday', 'Thusday', 'Wendsday', 'Thrusday', 'Friday', 'Saturday','Sunday'],
                datasets: [{
                    label: '# of sold tickets',
                    data: ['5', '5', '3', '10', '12'],
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
    earningsReport: function(){
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
  margin-left:auto;
  margin-right:auto;
  margin-top:2%;
}
h2{
  margin: auto;
  margin-bottom: 2%;
}

#flightCompanyReport{
    margin-left: 5%;
}

#Ratings{
    width: 90%;
    height: 5%;
}
#SoldTickets{
    width: 90%;
    height: 5%;
}
</style>