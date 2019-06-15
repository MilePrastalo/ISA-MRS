<template>
   <div id = "confirmationPage">

       <div v-if="currentDiv == 1 && this.previous == false">
           <login :requestId="requestId" v-on:currentDiv="changeDiv"></login>   
       </div>

       <div v-if="currentDiv == 2" >
          <navbar :itype="1"/>
        <div class="container">
        <h1>Reservation Request</h1><br><br>
        
        <table style="text-align: left; margin-left: auto; margin-right: auto">
            <tr>
                <td>From: </td>
                <td>{{request.creator}}</td>
            </tr>
             <tr>
                <td>Total: </td>
                <td>{{request.price}}</td>
            </tr>
        </table>
        <br><h2>Flight Info</h2><br>
        <table style="text-align: left; margin-left: auto; margin-right: auto">
            <tr>
                <td> Start destination: </td>
                <td> {{request.startDestination}} </td>
            </tr>
             <tr>
                <td>End destination: </td>
                <td>{{request.endDestination}}</td>
            </tr>
             <tr>
                <td>Start date: </td>
                <td>{{request.startDate}}</td>
            </tr>
            <tr>
                <td>End date: </td>
                <td>{{request.endDate}}</td>
            </tr>
            
        </table>
        <br><h2>Your Seat</h2><br>
        <table style="text-align: left; margin-left: auto; margin-right: auto">
             <tr>
                <td>Class: </td>
                <td>{{request.travelClassa}} </td>
            </tr>
            <tr>
                <td>Seat: </td>
                <td>row: {{request.seatRow}}, column: {{request.seatColumn}} </td>
            </tr>
            <tr>
                <td>Price: </td>
                <td>{{request.priceForSeat}} </td>
            </tr>
        </table>
           
        <br><h2>Passangers</h2><br>
            
        <table  border="1" style="text-align: left; margin-left: auto; margin-right: auto;" >
            <td  v-for="passanger in request.passangersInfo" :key="passanger.id" style="padding: 10px;"  >
                   
                <tr>
                    <th colspan="2">{{passanger.firstName}} {{passanger.lastName}}</th>
                    </tr>
                    <tr>
                        <td>Seat: </td>
                        <td >({{passanger.seatRow}}, {{passanger.seatColumn}})</td>
                    </tr>
                    <tr>
                        <td>Class: </td>
                        <td >{{passanger.travelClass}}</td>
                    </tr>
                    <tr>
                        <td>Passport: </td>
                        <td >{{passanger.passport}}</td>
                    </tr>
                    <tr>
                        <td>Status: </td>
                        <td v-if="passanger.status == 'Confirmed'" style="color: green;">{{passanger.status}}</td>
                        <td v-if="passanger.status == 'Waiting'" style="color:blue;">{{passanger.status}}</td>
                        <td v-if="passanger.status == 'Refused'" style="color: red;">{{passanger.status}}</td>
                    </tr>
            </td>
        </table>
        <br><h2>Hotel Reservation</h2><br>
             <table  border="1" style="text-align: left; margin-left: auto; margin-right: auto;" >
                <td   v-for="hotel in request.hotelsReservations" :key="hotel.hotelName" style="padding: 10px;">
                    <table style="text-align: left;" border = "1">
                        <tr>
                            <th colspan="2">{{hotel.hotelName}}</th>
                        </tr>
                        <tr>
                            <td>From: </td>
                            <td >{{hotel.firstDay}}</td>
                        </tr>
                        <tr>
                            <td>To: </td>
                            <td >{{hotel.lastDay}}</td>
                        </tr>
                        <tr>
                            <td>Paid price: </td>
                            <td> {{hotel.paidPrice}} </td>
                        </tr>
                        </table>
                </td>
            </table>
            <br><h2>Rent-A-Car Reservation</h2><br>
            <table  border="1" style="text-align: left; margin-left: auto; margin-right: auto;" >
                <td v-for="rent in request.rentReservations" :key="rent.id" style="padding: 10px">
                    <table style="text-align: left;" border = "1">
                        <tr>
                            <th colspan="2">{{rent.vehicleName}}</th>
                        </tr>
                        <tr>
                            <td>From: </td>
                            <td >{{rent.dateFrom}}</td>
                        </tr>
                        <tr>
                            <td>To: </td>
                            <td >{{rent.dateTo}}</td>
                        </tr>
                        <tr>
                            <td>Paid price: </td>
                            <td >{{rent.price}}</td>
                        </tr>
                    </table>
                </td>
            </table>
           
        <button v-if="this.selected == false" class="btn btn-success" @click="confirmRequest">Confirm</button>
        <button  v-if="this.selected == false" class="btn btn-danger" @click="refuseRequest">Reject</button>
        </div>
       </div>
        
        
      
    </div>
</template>

<script>
import axios from 'axios'
import loginPage from "../loginPage.vue";
import navbar from "../navbar.vue";
export default {
    name: 'confirmationPage',
    components: {
        login: loginPage,
        navbar: navbar
    },
    data: function () {

        return {
            username : "",
            password: "",
            request: {},
            requestId: this.$route.params.requestId,
            selected: false,
            currentDiv: 1,
            previous: false,
        }
    },
    created: function(){
        if (document.referrer == 'http://localhost:8081/invites'){
            axios.defaults.headers.common['Authorization'] = "Bearer " + localStorage.getItem('jwtToken');
                axios.get("http://localhost:8080/api/getReservationRequest/" + this.requestId)
                .then(response => {
                    if (response.data == ""){
                        alert("You did'n sign in or it's not your invitation")
                         this.currentDiv = 1;
                        return;
                    }
                    this.request = response.data
                    this.currentDiv = 2;
                    this.previous = true;
                });
        }
    },

    mounted(){
    }, 
    methods: {

        changeDiv: function(div){
            
            if (div == 2){
                axios.defaults.headers.common['Authorization'] = "Bearer " + localStorage.getItem('jwtToken');
                axios.get("http://localhost:8080/api/getReservationRequest/" + this.requestId)
                .then(response => {
                    if (response.data == ""){
                        alert("You did'n sign in or it's not your invitation")
                        this.currentDiv = 1;
                        return;
                    }
                    this.request = response.data
                    this.currentDiv = div;
                });
            }

        },

        confirmRequest: function(){
            var getJwtToken = function() {
            return localStorage.getItem('jwtToken');
            };
            axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
            axios.get("http://localhost:8080/api/confirmReservationRequest/" + this.requestId )
            .then(response => {
                alert(response.data)
                if (response.data == 'success'){
                     this.selected = true;
                }
            });
        },

        refuseRequest: function(){
            var getJwtToken = function() {
            return localStorage.getItem('jwtToken');
            };
            axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
            axios.get("http://localhost:8080/api/refuseReservationRequest/" + this.requestId )
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



#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
