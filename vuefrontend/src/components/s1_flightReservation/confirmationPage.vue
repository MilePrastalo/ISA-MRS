<template>
   <div id = "confirmationPage">

       <div v-if="currentDiv == 1">
           <login :requestId="requestId"></login>   
       </div>

       <div v-else class="container">
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
            <br><h2>Flight Info</h2><br>
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
            <br><h2>Your Seat</h2><br>
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
             <br><h2>Passangers</h2><br>
             <tr>
                <td  v-for="passanger in request.passangersInfo" :key="passanger.id">
                    <table style="text-align: left;" border = "1">
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
                    </table>
                </td>
            </tr>
            <br><h2>Hotel Reservation</h2><br>
             <tr>
                <td   v-for="hotel in request.hotelsReservations" :key="hotel.hotelName">
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
            </tr>
            <br><h2>Rent-A-Car Reservation</h2><br>
            <tr>
                <td v-for="rent in request.rentReservations" :key="rent.id">
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
            </tr>
           
        </table>
        <button v-if="this.selected == false" class="btn btn-success" @click="confirmRequest">Confirm</button>
        <button  v-if="this.selected == false" class="btn btn-danger" @click="refuseRequest">Reject</button>
       </div>
        
        
         
    </div>
</template>

<script>
import axios from 'axios'
import loginPage from "../loginPage.vue";
export default {
    name: 'confirmationPage',
    components: {
        login: loginPage
    },
    data: function () {

        return {
            username : "",
            password: "",
            request: {},
            requestId: this.$route.params.requestId,
            selected: false,
            currentDiv: 2,
        }
    },
    created: function(){
         

            if (window.localStorage.getItem('currentDiv') == undefined){
                this.currentDiv = 1;
            }
            else{
                
                if (window.localStorage.getItem('currentDiv')  == 2){
                    axios.defaults.headers.common['Authorization'] = "Bearer " + localStorage.getItem('jwtToken');
                    axios.get("http://localhost:8080/api/getReservationRequest/" + this.requestId)
                    .then(response => {
                        this.request = response.data
                        if (response.data == ""){
                            alert("You did'n sign in or it's not your invitation")
                            this.currentDiv = 1;
                            return;
                        }else{
                             this.currentDiv = 2;
                        }
                    });
                }
                else{
                     this.currentDiv = window.localStorage.getItem('currentDiv');
                }
                window.localStorage.removeItem('currentDiv');
            }
           
      
        
        
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

#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin: 5%
}
</style>
