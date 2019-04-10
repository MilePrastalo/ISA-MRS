<template>
   <div id = "searchFlights">
       <div>
        <table>
           <tr>
                <td>Start destination:</td>
                <td><input v-model="startDestination" type="text"></td>
                <td>End destination:</td>
                <td><input v-model="endDestination" type="text"></td>
            </tr>
            <tr>
                <td>Depart time:</td>
                <td><input v-model="startDate" type="date"></td>
                <td>Return time:</td>
                <td><input v-model="endDate" type="date"></td>
            </tr>
             <tr>
                <td><Button v-on:click="search">Search</Button></td>
            </tr>  
        </table>
        </div>
        <br>
        <div>
            <table border="1" >
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
                <td>{{flight.buisinesssPrice}}</td>
                <td>{{flight.firstClassPrice}}</td>
            </tr>
            </table>
        </div>        
    </div>
</template>

<script>

export default {
  name: 'searchFlights',
  components: {
  },
  data: function () {
  return {
     startDestination: "",
     endDestination: "",
     startDate: "",
     endDate: "",
     flightDuration: "",
     flightLength: "",
     buisinesssPrice: "",
     firstClassPrice: "",
     economicPrice: "",
     flights:[]
  }
},
mounted(){
    },
    methods:{
        search: function(){
            var flightForSearch = {startDestination: this.startDestination, endDestination: this.endDestination, startDate: this.startDate, endDate: this.endDate }
            axios.post("http://localhost:8080/api/flightSearch",flightForSearch)
            .then(response => {
                this.flights = response.data
            }); 
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
