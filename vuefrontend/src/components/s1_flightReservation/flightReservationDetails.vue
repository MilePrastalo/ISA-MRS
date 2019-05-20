<template>
    <div id = "flightReservationDetails">

        <div class = "row">
            <h2>Flight Info</h2>
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
        <div class = "row">
            <h2>Passangers</h2>
            <table style="text-align: left" v-for="passanger in reservation.passangers_" :key="passanger.id">
                <tr>
                    <th>{{passanger.firstName}} {{passanger.lastName}}</th>
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
        <div class = "row">
            <h2>Hotel Reservations</h2>
            <table style="text-align: left">
            </table>
        </div>
        <div class = "row">
            <h2>Vehicle Reservations</h2>
            <table style="text-align: left">
            </table>
        </div>
    </div>
</template>


<script>
import axios from 'axios'
export default {
    name: 'flightReservationDetails',
    components: {
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
  margin-left: 5%;
}

</style>
