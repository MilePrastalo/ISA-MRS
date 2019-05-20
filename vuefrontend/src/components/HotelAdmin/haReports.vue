<template>
  <div id="haReports">
    <h1>Reports</h1>
    <br>
    <br>
    <table>
        <tr>
            <td>Choose Report: </td>
            <td>
                <select v-model="reportName">
                    <option value="WEEK">Weekly Report</option>
                    <option value="MONTH">Montly Report</option>
                    <option value="YEAR">Annual Report</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Choose starting date: </td>
            <td><input v-model="firstDay" type="date"></td>
         </tr>
         <tr>
            <td><button @click="getReport()">Get Report</button></td>
        </tr>
    </table>

    <div v-if="loaded==1">
        <br>
        <br>
        <table>
            <th>
                <td>{{report.reportName}}</td>
                <td> REPORT</td> 
            </th>
            <tr>
                <td>Starting Date: </td>
                <td>{{report.fDay + "." + report.fMonth + "." + report.fYear + "."}}</td>
            </tr>
            <tr>
                <td>Number Of Reservations: </td>
                <td>{{report.numberOfReservations}}</td>
                <td>Total Earnings: </td>
                <td>{{report.totalEarnings}}</td>
            </tr>
        </table>
        <canvas ref="referencedElement" id="earningsChart" width="800" height="450"></canvas>
    </div>
  </div>
</template>

<script>

import Chart from "chart.js";

export default {
  name: 'haReports',
  props: {
      hotel: Object
  },
  components: {

  },
  data: function () {
  return {
      report: [],
      reportName: "",
      firstDay: "",
      fYear: 0,
      fMonth: 0,
      fDay: 0,
      loaded: 0,
      data: [],
      labels: []
        }
    },
    methods:{  
        splitDate: function() {
        var splitFirstDay = this.firstDay.split("-");
        this.fYear = parseInt(splitFirstDay[0]);
        this.fMonth = parseInt(splitFirstDay[1]);
        this.fDay = parseInt(splitFirstDay[2]);
      },
      getReport: function() {
          if(this.reportName == "") {
              alert("Please Choose Report.");
              return;
          }
          if(this.firstDay == "") {
              alert("Please eneter starting day.");
              return;
          }
          this.splitDate();
          var getJwtToken = function() {
            return localStorage.getItem('jwtToken');
        };
        axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();

        axios.post("http://localhost:8080/api/getHotelReport/",{reportName:this.reportName,fYear:this.fYear,fMonth:this.fMonth,fDay:this.fDay})
            .then(response => {
                this.report = response.data;
                this.loaded = 1;
                this.data = [];
                this.labels = [];
                for(let d in response.data.dailyReports) {
                    this.data.push(response.data.dailyReports[d].numberOfReservations);
                    this.labels.push(response.data.dailyReports[d].earnings )
                }
                this.showChart();
            })
        
        },
        showChart: function() {
            var ctx = document.getElementById('earningsChart');
            console.log(ctx)
            var earningsChart = new Chart(ctx, {
            type: 'bar',
            data: {
            labels: this.labels,
            datasets: [
                {
                label: "Reservations",
                data: this.data,
                backgroundColor: "#3e95cd"
                }
            ]
            },
            options: {
            legend: { display: false },
            title: {
                display: true,
                text: 'Reservations Per Day'
            }
      
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
