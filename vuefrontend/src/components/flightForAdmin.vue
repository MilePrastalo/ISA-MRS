<template>
   <div id = "flightForAdmin">
        <a style="float: left" href = "/flightAdmin">Back to profile</a>
        <br><br>
        <div class = "row">
            <h2>Flight details: </h2>
        </div>
        <br>

        <div class = "row">
            <table style="text-align: left">
                <tr>
                    <td>Flight company: </td>
                    <td> <input type = "text" v-model= "flight.flightCompany"  > </td>
                </tr>
                <tr>
                    <td>From: </td>
                    <td> <input class = "edit" type = "text" v-model= "flight.startDestination"> </td>
                </tr>
                <tr>
                    <td>To: </td>
                    <td> <input class = "edit" type = "text" v-model= "flight.endDestination" > </td>
                </tr>
                <tr>
                    <td>Start date: </td>
                    <td> <input class = "edit" type = "text" v-model= "flight.startDate" > </td>
                </tr>
                <tr>
                    <td>End date: </td>
                    <td> <input class = "edit" type = "text" v-model= "flight.endDate" > </td>
                </tr>
                <tr>
                    <td>Flight duration: </td>
                    <td> <input class = "edit" type = "text" v-model= "flight.flightDuration" > </td>
                </tr>
                <tr>
                    <td>Flight length: </td>
                    <td> <input class = "edit" type = "text" v-model= "flight.flightLength" > </td>
                </tr>
                <tr>
                    <td>Eco class price: </td>
                    <td> <input class = "edit" type = "text" v-model= "flight.economicPrice" > </td>
                </tr>
                <tr>
                    <td>Business class price: </td>
                    <td> <input class = "edit" type = "text" v-model= "flight.businessPrice" > </td>
                </tr>
                <tr>
                    <td>First class price: </td>
                    <td> <input class = "edit" type = "text" v-model= "flight.firstClassPrice" > </td>
                </tr>
                <tr>
                    <td><button>Edit</button> </td>
                </tr>
            </table>
        </div>
        <br><br>

        <div class = "row">
            
            <table border="1"  style="margin-right: 5%">
                <caption style="caption-side: top;">Business class: seats</caption>
                <tr v-for="r in seatsE.rows" :key ="r"> 
                    <td v-for="c in seatsE.columns" :key ="c" >
                    <div v-if="seatsE.seats[(c-1)+(r-1)*seatsE.columns].taken == false" style="background-color: palegreen;"  >
                         ({{ seatsE.seats[(c-1)+(r-1)*seatsE.columns].seatRow}},{{ seatsE.seats[(c-1)+(r-1)*seatsE.columns].seatColumn}} )
                        </div>
                        <div v-if="seatsE.seats[(c-1)+(r-1)*seatsE.columns].taken == true" style="background-color: salmon;"  >
                              ({{ seatsE.seats[(c-1)+(r-1)*seatsE.columns].seatRow}},{{ seatsE.seats[(c-1)+(r-1)*seatsE.columns].seatColumn}} )
                    </div>
                    </td> 
                </tr>
            </table>
            <table border="1"  style="margin-right: 5%">
                <caption style="caption-side: top;">Business class: seats</caption>
                <tr v-for="r in seatsB.rows" :key ="r"> 
                    <td v-for="c in seatsB.columns" :key ="c" >
                    <div v-if="seatsB.seats[(c-1)+(r-1)*seatsB.columns].taken == false" style="background-color: palegreen;"  >
                         ({{ seatsB.seats[(c-1)+(r-1)*seatsB.columns].seatRow}},{{ seatsB.seats[(c-1)+(r-1)*seatsB.columns].seatColumn}} )
                        </div>
                        <div v-if="seatsB.seats[(c-1)+(r-1)*seatsB.columns].taken == true" style="background-color: salmon;"  >
                              ({{ seatsB.seats[(c-1)+(r-1)*seatsB.columns].seatRow}},{{ seatsB.seats[(c-1)+(r-1)*seatsB.columns].seatColumn}} )
                    </div>
                    </td> 
                </tr>
            </table>
            <table border="1"  style="margin-right: 5%">
                <caption style="caption-side: top;">Business class: seats</caption>
                <tr v-for="r in seatsF.rows" :key ="r"> 
                    <td v-for="c in seatsF.columns" :key ="c" >
                    <div v-if="seatsF.seats[(c-1)+(r-1)*seatsF.columns].taken == false" style="background-color: palegreen;"  >
                         ({{ seatsF.seats[(c-1)+(r-1)*seatsF.columns].seatRow}},{{ seatsF.seats[(c-1)+(r-1)*seatsF.columns].seatColumn}} )
                        </div>
                        <div v-if="seatsF.seats[(c-1)+(r-1)*seatsF.columns].taken == true" style="background-color: salmon;"  >
                              ({{ seatsF.seats[(c-1)+(r-1)*seatsF.columns].seatRow}},{{ seatsF.seats[(c-1)+(r-1)*seatsF.columns].seatColumn}} )
                    </div>
                    </td> 
                </tr>
            </table>
             
        </div> 
         
    </div>
</template>

<script>

export default {
  name: 'flightForAdmin',
  components: {},
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
    seatsE: {},
    seatsB: {},
    seatsF: {},
  }
},
mounted(){
    var flightID = localStorage.getItem("flightID");
     var getJwtToken = function() {
        return localStorage.getItem('jwtToken');
    };
    axios.get("http://localhost:8080/api/getFlight/" + flightID)
        .then(response => {
            this.flight = response.data
          });
    axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
    axios.get("http://localhost:8080/api/getSeatsOnFlight/" + flightID + "/economic")
        .then(response => {
            this.seatsE = response.data;
          });; 
     axios.get("http://localhost:8080/api/getSeatsOnFlight/" + flightID + "/business")
        .then(response => {
            this.seatsB = response.data;
          });
     axios.get("http://localhost:8080/api/getSeatsOnFlight/" + flightID + "/first class")
        .then(response => {
            this.seatsF = response.data;
          });
    },
methods: {
    goToProfile: function(){

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
  margin: 5%
}
</style>
