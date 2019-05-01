<template>
   <div id = "flightDetails">
        
        <br>
        <div class = "row">
            <h2>Flight details: </h2>
        </div>
        <br>

        <div class = "row">
            <table style="text-align: left">
                <tr>
                    <td>Flight company: </td>
                    <td> {{flight.flightCompany}}</td>
                </tr>
                <tr>
                    <td>From: </td>
                    <td> {{flight.startDestination}}</td>
                </tr>
                <tr>
                    <td>To: </td>
                    <td> {{flight.endDestination}}</td>
                </tr>
                <tr>
                    <td>Start date: </td>
                    <td> {{flight.startDate_str}}</td>
                </tr>
                <tr>
                    <td>End date: </td>
                    <td> {{flight.endDate_str}}</td>
                </tr>
                <tr>
                    <td>Flight duration: </td>
                    <td> {{flight.flightDuration}}</td>
                </tr>
                <tr>
                    <td>Flight length: </td>
                    <td> {{flight.flightLength}}</td>
                </tr>
                <tr>
                    <td>Eco class price: </td>
                    <td> {{flight.economicPrice}}</td>
                </tr>
                <tr>
                    <td>Business class price: </td>
                    <td> {{flight.businessPrice}}</td>
                </tr>
                <tr>
                    <td>First class price: </td>
                    <td> {{flight.firstClassPrice}}</td>
                </tr>
                <tr>
                    <td><button @click="makeReservation(flight.id)">Make reservation</button> </td>
                </tr>
            </table>
        </div>
        <br><br>
        <div class = "row">
            <a href = "searchHotels">Search for hotels</a>
        </div>

        <div class = "row">
            <a href = "/searchVehicle">Search for rent a car services</a>
        </div>    
    </div>
</template>

<script>

export default {
  name: 'flightDetails',
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
     flight: {},
     items: [
      { message: 'Foo' },
       { message: 'Aoo' },
        { message: 'Boo' },
         { message: 'Doo' },
      { message: 'Gar' }
    ]
  }
},
mounted(){
    var flightID = localStorage.getItem("flightID");
    axios.get("http://localhost:8080/api/getFlight/" + flightID)
        .then(response => {
            this.flight = response.data
          }); 
    },
    methods:{

        makeReservation: function(id){
            localStorage.setItem('flightID',id);
            window.location = '/flightReservation'
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
  margin: 5%;
}
</style>
