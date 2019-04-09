<template>
   <div id = "allFlights">
                <br>
                <h1>All Flights</h1>
                <br>
                <br>
                <table border="1">
                    <tr>
                        <td>Start destination</td>
                        <td>End destination</td>
                        <td>Start date</td>
                        <td>End date</td>
                        <td>Flight duration</td>
                        <td>Flight Length</td>
                        <td>Economic price</td>
                        <td>Buisiness price</td>
                        <td>First class price</td>
                        <td></td>
                    </tr>
                     <tr v-for="flight in flights" :key="flight.id">  
                    <td>{{flight.startDestination.name}}</td>
                    <td>{{flight.endDestination.name}}</td>
                    <td>{{flight.startDate}}</td>
                    <td>{{flight.endDate}}</td>
                    <td>{{flight.flightDuration}}</td>
                    <td>{{flight.flightLength}}</td>
                    <td>{{flight.economicPrice}}</td>
                    <td>{{flight.buisinessPrice}}</td>
                    <td>{{flight.firstClassPrice}}</td>
                    <td><button>Delete</button></td>
                    </tr>
                    
                </table>  

            
    </div>
</template>

<script>
export default {
  name: 'allFlights',
  components: {},
  data: function () {
  return {
      flights: []
  }
},
mounted(){
    axios.get("http://localhost:8080/api/getFlights")
        .then(response => {
            this.flights = response.data
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
