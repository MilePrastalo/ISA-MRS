<template>
   <div id = "flightDetails">
        
        <navbar></navbar>
        <div class = "container">
            <br>
            <h2>Flight details: </h2>
        <br><br>

        <div>
            <table style="text-align: left" class = "centered">
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
                    <td><button v-if="role == 'REGISTERED'" class="btn btn-primary" @click="makeReservation(flight.id)">Make reservation</button> </td>
                </tr>
            </table>
            <br><br>
            <a  href = "searchHotels">Search for hotels</a>
            <br>
            <a href = "/searchVehicle">Search for rent a car services</a>
        </div> 
        </div>
    </div>
</template>

<script>
import navbar from "./navbar.vue";
export default {
  name: 'flightDetails',
  components: {
      navbar: navbar
  },
  data: function () {
  return {
      
    role: "",
    flight: {}
  }
},
mounted(){

    var getJwtToken = function() {
        return localStorage.getItem('jwtToken');
    };
    axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
    axios.get("http://localhost:8080/api/getUserRole")
    .then(response => {
        this.role = response.data;   
    });    
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
}

.centered {
    margin-left: auto;
    margin-right: auto;

}
</style>
