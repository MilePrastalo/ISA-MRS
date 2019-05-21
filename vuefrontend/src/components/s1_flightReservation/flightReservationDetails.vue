<template>
    <div id = "flightReservationDetails">
         <navbar/>
        <div class = "container">
            <div class = "row">

                <table style="text-align: left">
                    <tr>
                        <td style="font-weight: bold">Created: </td>
                        <td>{{reservation.created}}</td>
                    </tr>
                    <tr>
                        <td style="font-weight: bold">Price: </td>
                        <td>{{reservation.price}}</td>
                    </tr>
                </table>
            </div>
            <br>
             <div class = "row">
                <h2>Flight Info:</h2>
            </div>
            <br>
            <div class = "row">
                <table style="text-align: left">

                    <tr>
                        <td>From: </td>
                        <td >{{reservation.flight.startDestination.name}}</td>
                    </tr>
                    <tr>
                        <td>To: </td>
                        <td >{{reservation.flight.endDestination}}</td>
                    </tr>
                    <tr>
                        <td>Start date: </td>
                        <td > {{reservation.flight.startDate_str}} </td>
                    </tr>
                    <tr>
                        <td>End date: </td>
                        <td > {{reservation.flight.endDate_str}} </td>
                    </tr>
                    <tr>
                        <td>Flight duration: </td>
                        <td > {{reservation.flight.flightDuration}} </td>
                    </tr>
                    <tr>
                        <td>Flight length: </td>
                        <td > {{reservation.flight.flightLength}} </td>
                    </tr>
                </table>
            </div>
            <br>
            <div class = "row">
                 <h2>Passangers:</h2>
            </div>
            <br>
            <div class = "row">
                <table style="text-align: left; margin-right: 5%" v-for="passanger in reservation.passangers_" :key="passanger.id" border = "1">
                    <tr>
                        <th colspan="2">{{passanger.firstName}}</th>
                    </tr>
                    <tr>
                        <td>Seat: </td>
                        <td >({{passanger.seat.seatRow}}, {{passanger.seat.seatColumn}})</td>
                    </tr>
                    <tr>
                        <td>Class: </td>
                        <td >{{passanger.seat.travelClassa}}</td>
                    </tr>
                    <tr>
                        <td>Passport: </td>
                        <td >{{passanger.passport}}</td>
                    </tr>
                </table>
            </div>
            <br>
             <div class = "row">
                <h2>Hotel Reservations:</h2>
            </div>
            <br>
            <div class = "row">
                <table style="text-align: left">
                </table>
            </div>
             <div class = "row">
                <h2>Vehicle Reservations:</h2>
            </div>
            <div class = "row">
                <table style="text-align: left">
                </table>
            </div>
        </div>
    </div>
</template>


<script>
import axios from 'axios'
import navbar from ".././navbar.vue";
export default {
    name: 'flightReservationDetails',
    components: {
        navbar: navbar
    },

    data: function () {
        return{
          reservation: {},
          reservationId: this.$route.params.reservationId
        }
    },

    created(){
        
        var getJwtToken = function() {
            return localStorage.getItem('jwtToken');
        };
        axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
        axios.get("http://localhost:8080/api/getFlightReservation/" + this.reservationId)
        .then(response => {
            this.reservation = response.data
        });
    },



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

.centered{
    margin-left: auto;
    margin-right: auto;
    width: 1100px;
}

</style>
