<template>
    <div id="rentACarReport">
      <div class="row">
          <button @click="selected(0)" >Vehicles</button>
          <button @click="selected(1)">Ratings</button>
          <button @click="selected(2)">Earnings</button>
          <button @click="selected(3)">Reservations</button>

      </div>
      <div class="row" id="Vehicless">
          <div class="container">
              
            <div class="row">
                <table>
            <tr>
                <th>Name</th>
                <th>Maker</th>
                <th>Type</th>
                <th>Year</th>
                <th>Price</th>
                <th>Avaiable from</th>
                <th>Avaiable to</th>
                <th>Rating</th>
            </tr>
            <tr v-for="car in cars" :key="car.id">
                <td>{{car.name}}</td>
                <td>{{car.maker}}</td>
                <td>{{car.type}}</td>
                <td>{{car.year}}</td>
                <td>{{car.price}}</td>
                <td>{{car.dateFrom}}</td>
                <td>{{car.dateTo}}</td>
                <td>{{car.rating}}</td>
            </tr>
        </table>
            </div>
          </div>
          
              
      </div>
      <div  class="row" id="Ratings">
          <div class="container">
              <div class="row">
            <h2>Average rating of company:{{avgRating}}</h2>
          </div>
          <div class="row">
              <canvas ref="referencedElement" id="myChart" width="600" height="400" style="responsive:true;"></canvas>
          </div>
          </div>
          
      </div>
      <div  class="row" id="Earnings">
          <div class="contai">
              <div class="row">
               From:
              <input v-model="dateFromEarnings" type="date" name="" id="">
              To: 
              <input v-model="dateToEarnings" type="date" name="" id="">

              <button @click="searchEarnings">Search</button>
          </div>
          <div class="row">
              <h2>Earnings for selected period:{{earnings}}</h2>
          </div>
        </div>
      </div>
      <div  class="row" id="Reservations">
          <div class="contai">
              <div class="row">
               From:
              <input v-model="dateFromReservation" type="date" name="" id="">
              To: 
              <input v-model="dateToReservation" type="date" name="" id="">
              Daily
              <input v-model="earningsType" name="earningspan" type="radio" value="0"/>
              Weekly
              <input v-model="earningsType" name="earningspan" type="radio" value="1"/>
              Monthly
              <input v-model="earningsType" name="earningspan" type="radio" value="2"/>
              <button @click="searchReservations">Search</button>
          </div>
          <div class="row">
              <canvas ref="referencedElement" id="myChart3" width="600" height="400" style="responsive:true;"></canvas>
          </div>
        </div>
      </div>
          
    </div>
</template>

<script>
import Chart from "chart.js";


export default {
  name: 'rentACarReport',
  data:function(){
    return {
      tabSelected:0, 
      visible  :false ,
      ratingData:[5,5,1,2,3],
      avgRating:4.21,
      cars:[],
      avaiability:"",
      dateFromVehicle:"",
      dateToVehicle:"",
      dateFromEarnings:"",
      dateToEarnings:"",
      dateFromReservation:"",
      dateToReservation:"",
      earningsType:"",
      earnings:[],
      reservations:[]
    }
  },
  mounted(){
     document.getElementById('Ratings').hidden = true;
     document.getElementById('Earnings').hidden = true;
     document.getElementById('Vehicless').hidden = false;
     document.getElementById('Reservations').hidden = true;
     var getJwtToken = function() {
                    return localStorage.getItem('jwtToken');
                };
        axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
        axios.get("http://localhost:8080/api/getCompanyRatingReport",{})
                .then(response => { 
                    console.log(response.data);
                    this.ratingData = response.data.ratings; 
                    this.avgRating = response.data.avgRating;
                });

        axios.get("http://localhost:8080/api/getCarsByAdmin")
            .then(response => {
                console.log(response);
                this.cars = response.data;
                this.cars.forEach(element => {
                    if(element.rating<=0){
                        element.rating = "No rating";
                    }
                });
            }); 
  },

  methods:{
    searchVehicles:function(){
        var getJwtToken = function() {
                    return localStorage.getItem('jwtToken');
                };
        axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
        axios.post("http://localhost:8080/api/",{})
                .then(response => { 
                    this.vehicles = response.data; 
                }); 
    },
    searchEarnings:function(){
        var getJwtToken = function() {
                    return localStorage.getItem('jwtToken');
                };
        axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
        axios.post("http://localhost:8080/api/getCompanyEarningsReport",{dateFrom:this.dateFromEarnings,dateTo:this.dateToEarnings})
                .then(response => { 
                    this.earnings = response.data; 
                }); 
    }, 
    searchReservations:function(){
        console.log("Called");
        var getJwtToken = function() {
                    return localStorage.getItem('jwtToken');
                };
        axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
        axios.post("http://localhost:8080/api/getCompanyReservationsReport",{dateFrom:this.dateFromReservation,dateTo:this.dateToReservation,type:2})
                .then(response => { 
                    console.log(response);
                    this.reservations = response.data;
                    document.getElementById('Reservations').hidden = true;
                    this.selected(3);

                }); 
    }, 
    selected:function(val){
        console.log(val);
        this.tabSelected = val;
        if(this.tabSelected == 1){
            document.getElementById('Ratings').hidden = false;
            document.getElementById('Vehicless').hidden = true;
            document.getElementById('Earnings').hidden = true;
            document.getElementById('Reservations').hidden = true;
            var vdata = this.ratingData;
            console.log(vdata);
            var ctx = document.getElementById('myChart').getContext('2d');
            var myChart = new Chart(ctx, {
            type: 'bar',
            data: {
            labels: ['1', '2', '3', '4', '5'],
            datasets: [{
                label: '# of Votes',
                data: vdata,
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
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
        }
        else if(this.tabSelected == 0){
            document.getElementById('Ratings').hidden = true;
            document.getElementById('Vehicless').hidden = false;
            document.getElementById('Earnings').hidden = true;
            document.getElementById('Reservations').hidden = true;

        }
        else if (this.tabSelected == 2){
            document.getElementById('Ratings').hidden = true;
            document.getElementById('Vehicless').hidden = true;
            document.getElementById('Earnings').hidden = false;
            document.getElementById('Reservations').hidden = true;

            
        }
    else{
        document.getElementById('Ratings').hidden = true;
        document.getElementById('Vehicless').hidden = true;
        document.getElementById('Earnings').hidden = true;
        document.getElementById('Reservations').hidden = false;
        var vlabels = this.reservations.dates;
        var vcounts = this.reservations.counts;
        console.log(vlabels);
        console.log(vcounts);
        var ctx = document.getElementById('myChart3').getContext('2d');
            var myChart = new Chart(ctx, {
            type: 'bar',
            data: {
            labels: vlabels,
            datasets: [{
                label: '# of Votes',
                data: vcounts,
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
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
    }
        
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
  border :1px solid lightblue;
}
th,td{
    border: 1px solid lightblue;
}
h2{
  margin: auto;
  margin-bottom: 2%;
}
</style>