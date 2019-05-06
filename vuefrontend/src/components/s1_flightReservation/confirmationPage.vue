<template>
   <div id = "confirmationPage">
        
        <h2>Reservation request</h2>
        <table style="text-align: left">
            <tr>
                <td>From: </td>
                <td>{{request.callerInfo}}</td>
            </tr>
            <tr>
                <td> Start destination: </td>
                <td> {{request.flight.startDestination.name}} </td>
            </tr>
             <tr>
                <td>End destination: </td>
                <td>{{request.flight.endDestination.name}}</td>
            </tr>
             <tr>
                <td>Start date: </td>
                <td>{{request.flight.startDate}}</td>
            </tr>
            <tr>
                <td>End date: </td>
                <td>{{request.flight.endDate}}</td>
            </tr>
            <tr>
                <td>Seat: </td>
                <td>red: {{request.seat.seatRow}}, kolona: {{request.seat.seatColumn}} </td>
            </tr>
            <tr>
                <td>Price: </td>
            </tr>
        </table>

        <button v-if="request.confirmed == false" @click="confirmRequest">Confirm</button>
        <button  v-if="request.confirmed == false" @click="refuseRequest">Reject</button>
         
    </div>
</template>

<script>
import axios from 'axios'
export default {
    name: 'confirmationPage',
    components: {
    },
    data: function () {

        return {
            requestId: this.$route.params.requestId,
            request: {}
        }
    },
    created: function(){

        axios.get("http://localhost:8080/api/getReservationRequest/" + this.requestId)
        .then(response => {
            this.request = response.data
        }); 
    },
    mounted(){
    }, 
    methods: {

        confirmRequest: function(){
            axios.get("http://localhost:8080/api/confirmReservationRequest/" + this.requestId)
            .then(response => {
                this.request = response.data
            });
        },

        refuseRequest: function(){
            alert("dsds");
            axios.get("http://localhost:8080/api/refuseReservationRequest/" + this.requestId)
            .then(response => {
                this.request = response.data
            });
        }

    } 

}    
</script>

<style>
#confirmationPage{
    background-color: mediumspringgreen;
    width:50%;
    height: 50%;
}
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin: 5%
}
</style>
