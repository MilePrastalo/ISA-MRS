<template>
   <div id = "confirmationPage">
        
        <h2>Reservation request</h2>
        <table style="text-align: left">
            <tr>
                <td>From: </td>
                <td>{{request.passangers[0].firstName}} {{request.passangers[0].lastName}}</td>
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
                <td>Enter your username: </td>
                <td><input type="text" v-model="username"></td>
            </tr>
            <tr>
                <td>Enter your passwors: </td>
                <td><input type="text" v-model="password"></td>
            </tr>
        </table>
        <button v-if="this.selected == false" @click="confirmRequest">Confirm</button>
        <button  v-if="this.selected == false" @click="refuseRequest">Reject</button>
         
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
            username : "",
            password: "",
            request: "",
            requestId: this.$route.params.requestId,
            selected: false
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
           if (this.username == "" || this.password == ""){
                alert("You mast fill in username and password!");
                return;
            }
            this.selected = true;
        },

        refuseRequest: function(){
            if (this.username == "" || this.password == ""){
                alert("You mast fill in username and password!");
                return;
            }
            var getJwtToken = function() {
            return localStorage.getItem('jwtToken');
            };
            axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
            axios.post("http://localhost:8080/api/refuseReservationRequest" ,{requestId:  this.requestId, username: this.username, password: this.password })
            .then(response => {
                alert(response.data)
                if (response.data == 'success'){
                     this.selected = true;
                }
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
