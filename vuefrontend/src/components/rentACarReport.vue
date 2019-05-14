<template>
    <div id="rentACarReport">
      <div class="row">
          <button @click="selected(0)" >Vehicles</button>
          <button @click="selected(1)">Ratings</button>
          <button @click="selected(2)">Earnings</button>
      </div>
      <div class="row" id="Vehicless">
          <div class="container">
              <div class="row">
              From:
              <input type="date" name="" id="">
              To: 
              <input type="date" name="" id="">
              Avaiable
              <input name="avaiability" type="radio" value="Avaiable" v-model="avaiability"/>
              Reserved
              <input name="avaiability" type="radio" value="Reserved" v-model="avaiability"/>
              <button @click="searchVehicles">Search</button>
          </div>
          <div class="row">
              <table>
                  <tr>
                      <td>Vehicle name</td>
                      <td>Vehicle Maker</td>
                      <td>year</td>
                      <td>type</td>
                      <td>rating</td>
                  </tr>
                  <tr v-for="vehicle in vehicles" :key="vehicle.id">
                      <td>{{vehicle.name}}</td>
                      <td>{{vehicle.maker}}</td>
                      <td>{{vehicle.year}}</td>
                      <td>{{vehicle.type}}</td>
                      <td>{{vehicle.rating}}</td>
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
              <input type="date" name="" id="">
              To: 
              <input type="date" name="" id="">
              Weekly
              <input v-model="earningsType" name="earningspan" type="radio" value="Weekly"/>
              Monthly
              <input v-model="earningsType" name="earningspan" type="radio" value="Monthly"/>
              Yearly
              <input v-model="earningsType" name="earningspan" type="radio" value="Yearly"/>
              <button @click="searchEarnings">Search</button>
          </div>
          <div class="row">
              <canvas ref="referencedElement" id="myChart2" width="600" height="400" style="responsive:true;"></canvas>
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
      vehicles:[],
      avaiability:"",
      dateFromVehicle:"",
      dateToVehicle:"",
      dateFromEarnings:"",
      dateToEarnings:"",
      earningsType:"",
      earnings:[],
    }
  },
  mounted(){
     document.getElementById('Ratings').hidden = true;
     document.getElementById('Earnings').hidden = true;
     document.getElementById('Vehicless').hidden = false;
     var getJwtToken = function() {
                    return localStorage.getItem('jwtToken');
                };
        axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
        axios.post("http://localhost:8080/api/",{})
                .then(response => { 
                    this.ratingData = response.data.ratingData; 
                    this.avgRating = response.data.avgRating;
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
        axios.post("http://localhost:8080/api/",{})
                .then(response => { 
                    this.earnings = response.data; 
                }); 
    }, 
    selected:function(val){
        console.log(val);
        this.tabSelected = val;
        if(this.tabSelected == 1){
            document.getElementById('Ratings').hidden = false;
            document.getElementById('Vehicless').hidden = true;
            document.getElementById('Earnings').hidden = true;

            var ctx = document.getElementById('myChart').getContext('2d');
            var myChart = new Chart(ctx, {
            type: 'bar',
            data: {
            labels: ['1', '2', '3', '4', '5'],
            datasets: [{
                label: '# of Votes',
                data: this.ratingData,
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
        }
        else{
            document.getElementById('Ratings').hidden = true;
            document.getElementById('Vehicless').hidden = true;
            document.getElementById('Earnings').hidden = false;
            var ctx = document.getElementById('myChart2').getContext('2d');
            var myChart = new Chart(ctx, {
            type: 'bar',
            data: {
            labels: ['1', '2', '3', '4', '5'],
            datasets: [{
                label: '# of Votes',
                data: this.ratingData,
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