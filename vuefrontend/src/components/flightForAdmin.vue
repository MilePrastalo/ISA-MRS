<template>
   <div id = "flightForAdmin">
        <a style="float: left" href = "/flightAdmin">Back to profile</a>
        <br><br>
        <div class = "row">
            <h2>Flight details: </h2>
        </div>
        <br>

        <div class = "row">
            <form>
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
                        <td> <input class = "edit" type = "text" v-model= "flight.startDate_str" > </td>
                    </tr>
                    <tr>
                        <td>End date: </td>
                        <td> <input class = "edit" type = "text" v-model= "flight.endDate_str" > </td>
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
                        <td><input type="submit" value="Edit"></td>
                    </tr>
                </table>
            </form>
            
        </div><br>

        <editSeats :iid = "id"></editSeats>
        

        
         
    </div>
</template>

<script>
import EditSeats from "./editSeatsOnFlight.vue";

export default {
    name: 'flightForAdmin',
    components: {
        editSeats:  EditSeats
    },
    data: function () {
        return {
                flight: {},
                seatsE: {},
                seatsB: {},
                seatsF: {},
                id: 0,
            }
    },
    created:function(){

        this.id = localStorage.getItem("flightID");
    },
    mounted(){
        
            var getJwtToken = function() {
                return localStorage.getItem('jwtToken');
            };
            axios.get("http://localhost:8080/api/getFlight/" + this.id)
            .then(response => {
                this.flight = response.data
            });
    },
    methods: {

        modify: function(seat){
            
            var getJwtToken = function() {
            return localStorage.getItem('jwtToken');
            };
            axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
            axios.get("http://localhost:8080/api/editSeat/" + seat.id)
            .then(response => {
                if (response.data != null){
                    if (seat.unavailable == true){
                        seat.unavailable = false;
                    }
                    else{
                        seat.unavailable = true;
                    }
                }
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
  margin: 5%
}
</style>
