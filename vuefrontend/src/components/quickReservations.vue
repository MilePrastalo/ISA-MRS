<template>
   <div id = "allFlights">

        <div class = "quickContainer">
            <br><br><br>
            <h2>Quick Tickets: </h2>
            <br>
            <table  border="1"  class = "table">
                <thead>
                    <tr>
                    <th scope="col">Start destination</th>
                    <th scope="col">End destination</th>
                    <th scope="col">Start date</th>
                    <th scope="col">End date</th>
                    <th scope="col">Seat</th>
                    <th scope="col">Origin price</th>
                    <th scope="col">Discount</th>
                    <th></th>
                    </tr>
                </thead>
                <tbody>
                <tr v-for="(res,index) in quickReservations" :key="index">  
                    <td>{{res.startDestination}}</td>
                    <td>{{res.endDestination}}</td>
                    <td>{{res.startDate}}</td>
                    <td>{{res.endDate}}</td>
                    <td>{{res.travelClass}} ({{res.seatRow}},{{res.seatColumn}})</td>
                    <td>{{res.originPrice}}</td>
                    <td>{{res.discount}}</td>
                    <td v-if="role == 'FLIGHT_ADMIN' && res.taken == false"> <Button class="btn btn-outline-primary" @click="remove(res.id, index)">Delete</Button></td>
                    <td v-if="role == 'FLIGHT_ADMIN' && res.taken == true" style="color: green; font-weight: bold"> Reserved</td>
                    <td v-if="role == 'REGISTERED' && res.taken == false" @click="bookQuickTicket(res)"> <Button class="btn btn-outline-primary">Reserve</Button></td>
                     <td v-if="role == 'REGISTERED'  && res.taken == true" @click="bookQuickTicket(res)" style="color: green; font-weight: bold"> Reserved</td>
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
  props: ["idCompany"],
  data: function () {
  return {
    role: "",
    quickReservations: []
  }
},

mounted(){

        var getJwtToken = function() {
            return localStorage.getItem("jwtToken");
        };
        axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
        axios.get("http://localhost:8080/api/getUserRole")
        .then(response => {

            this.role = response.data;
            if (response.data == "FLIGHT_ADMIN"){
                axios.get("http://localhost:8080/api/getQuickReservations")
                .then(response2 => {
                    this.quickReservations = response2.data
                });
            }
            else if (response.data == "REGISTERED") {
                axios.get("http://localhost:8080/api/getQuickReservationsCompany/" + this.idCompany)
                .then(response3 => {
                    this.quickReservations = response3.data
                });
            }  
        });    
    },
    methods:{ 
        
        bookQuickTicket: function(res){
            if (res.taken == true){
                alert("Sorry, it's already taken!");
                return;
            }
             var obj = {id: res.id, flightId: res.flightId, seatId: res.seatId, originPrice: res.originPrice, discount: res.discount};
            axios.post("http://localhost:8080/api/makeQuickReservation", obj)
            .then(response => {
                if (response.data == "success"){
                    alert("Reservation was successful! You will get e-mail about it.");
                }
                else{
                    alert(response.data);
                }
                 res.taken = true;
            });
        },

        remove: function(id, index){
            alert(id);
            var getJwtToken = function() {
                return localStorage.getItem("jwtToken");
            };
            axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
            axios.get("http://localhost:8080/api/removeQuickFlightReservation/" + id)
            .then(response => {
                alert(response.data);
                this.quickReservations.splice(index,1);
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

.quickContainer{
    margin-left: auto;
    margin-right: auto;
    width: 1100px;
}

</style>
