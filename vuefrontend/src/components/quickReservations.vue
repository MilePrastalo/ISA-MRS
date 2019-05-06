<template>
   <div id = "allFlights">

        <br><br>
        <div class="row"> 
            <h2> All Quick Tickets: </h2>
        </div>
        <br>

        <div class = "row">
            <table  class = "table">
                <thead class="thead-dark">
                    <tr>
                    <th scope="col">Start destination</th>
                    <th scope="col">End destination</th>
                    <th scope="col">Start date</th>
                    <th scope="col">End date</th>
                    <th scope="col">Seat</th>
                    <th scope="col">Origin price</th>
                    <th scope="col">Discount</th>
                    </tr>
                </thead>
                <tbody>
                <tr v-for="res in quickReservations" :key="res.id">  
                    <td>{{res.flight.startDestination.name}}</td>
                    <td>{{res.flight.endDestination.name}}</td>
                    <td>{{res.flight.startDate}}</td>
                    <td>{{res.flight.endDate}}</td>
                    <td>({{res.seat.seatRow}},{{res.seat.seatColumn}})</td>
                    <td>{{res.originPrice}}</td>
                    <td>{{res.discount}}</td>
                    <td> <Button>Delete</Button></td>
                </tr> 
                </tbody>             
            </table>  
        </div>   
    </div>
</template>

<script>
export default {
  name: 'allFlights',
  components: {},
  data: function () {
  return {
    
    quickReservations: []
  }
},
mounted(){

        var getJwtToken = function() {
                return localStorage.getItem('jwtToken');
            };
        axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
        axios.get("http://localhost:8080/api/getQuickReservations")
        .then(response => {
            this.quickReservations = response.data
        });  
    },
    methods:{ 
        
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
