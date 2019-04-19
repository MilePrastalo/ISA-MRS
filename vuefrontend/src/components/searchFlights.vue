<template>
   <div id = "searchFlights">
       <h1>Flight Search</h1>
       <br>
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
                <td>Flight company:</td>
                <td><input v-model="flightCompany" type="number"></td>
            </tr>
            <tr>
                <td>Flight duration:</td>
                <td><input v-model="flightDuration" type="number"></td>
            </tr>
            <tr>
                <td>Flight length:</td>
                <td><input v-model="flightLength" type="number"></td>
            </tr>
            <tr>
                <td>Economic class: min price</td>
                <td><input v-model="MineconomicPrice" type="text"></td>
                <td>max price:</td>
                <td><input v-model="MaxeconomicPrice" type="number"></td>
            </tr>
            <tr>
                <td>Buisiness class: min price</td>
                <td><input v-model="MinbuisinesssPrice" type="number"></td>
                <td>max price:</td>
                <td><input v-model="MaxbuisinesssPrice" type="number"></td>
            </tr>
            <tr>
                <td>First  class: min price</td>
                <td><input v-model="MinfirstClassPrice" type="number"></td>
                <td>max price:</td>
                <td><input v-model="MaxfirstClassPrice" type="number"></td>
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
                        <td>Flight company</td>
                        <td>Start destination</td>
                        <td>End destination</td>
                        <td>Start date</td>
                        <td>End date</td>
                        <td>Flight duration</td>
                        <td>Flight Length</td>
                        <td>Economic price</td>
                        <td>Buisiness price</td>
                        <td>First class price</td>
                    </tr>
            <tr v-for="flight in flights" :key="flight.id"> 
                <td>xxx</td> 
                <td>{{flight.startDestination.name}}</td>
                <td>{{flight.endDestination.name}}</td>
                <td>{{flight.startDate}}</td>
                <td>{{flight.endDate}}</td>
                <td>{{flight.flightDuration}}</td>
                <td>{{flight.flightLength}}</td>
                <td>{{flight.economicPrice}}</td>
                <td>{{flight.businessPrice}}</td>
                <td>{{flight.firstClassPrice}}</td>
                <td> <Button>Details</Button></td>
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
     MinbuisinesssPrice: "",
     MinfirstClassPrice: "",
     MineconomicPrice: "",
     MaxbuisinesssPrice: "",
     MaxfirstClassPrice: "",
     MaxeconomicPrice: "",
     flightCompany: "",
     flights:[]
  }
},
mounted(){
    },
    methods:{
        search: function(){
            console.log(this.startDestination + " , " + this.MineconomicPrice + " , " + this.startDate);
            var flightForSearch = {startDestination: this.startDestination, endDestination: this.endDestination, startDate: this.startDate, endDate: this.endDate,
            minEconomic : this.MineconomicPrice, minBusiness: this.MinbuisinesssPrice, minFirstClass :this.MinfirstClassPrice,
            maxEconomic: this.MaxeconomicPrice, maxBusiness: this.MaxbuisinesssPrice, maxFirstClass:this.MaxfirstClassPrice,
            flightDuration: this.flightDuration, flightLength:  this.flightLength }
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
