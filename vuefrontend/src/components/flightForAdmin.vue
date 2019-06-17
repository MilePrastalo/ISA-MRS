<template>
   <div id = "flightForAdmin">

        <navbar itype="1"/>
        <div class = "container">
            <br><br>
                <h2>Flight details: </h2>
            <br><br>

            <div class = "flightForAdminContainer">
                <form @submit="editFlight">
                    <table style="text-align: left" class = "centered" >
                        <tr>
                            <td>From: </td>
                            <td >{{flight.startDestination}}</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>To: </td>
                            <td >{{flight.endDestination}}</td>
                        </tr>
                        <tr>
                            <td>Transitions: </td>
                            <td >{{flight.transitions}}</td>
                        </tr>
                        <tr>
                            <td>Start date: </td>
                            <td > {{flight.startDate_str}} </td>
                            <td v-if="editing == true"> <input class = "edit" type = "date" v-model= "startDate" > </td>
                            <td v-if="editing == true && startDate != ''"> <input class = "edit" type = "time" v-model= "startTime" required > </td>
                        </tr>
                        <tr>
                            <td>End date: </td>
                            <td > {{flight.endDate_str}} </td>
                            <td v-if="editing == true"> <input class = "edit" type = "date" v-model= "endDate" > </td>
                            <td v-if="editing == true && endDate != ''"> <input class = "edit" type = "time" v-model= "endTime" required> </td>
                        </tr>
                        <tr>
                            <td>Flight duration: </td>
                            <td v-if="editing == false"> {{flight.flightDuration}} </td>
                            <td v-else> <input class = "edit" type = "text" v-model= "flight.flightDuration" required > </td>
                        </tr>
                        <tr>
                            <td>Flight length: </td>
                            <td v-if="editing == false"> {{flight.flightLength}} </td>
                            <td v-else> <input class = "edit" type = "text" v-model= "flight.flightLength" required> </td>
                        </tr>
                         <tr>
                            <td>Additional services: </td>
                            <td v-if="editing == false"> {{flight.additionalServices}} </td>
                            <td v-else> <input class = "edit" type = "text" v-model= "flight.additionalServices" > </td>
                        </tr>
                        <tr>
                            <td>Eco class price: </td>
                            <td v-if="editing == false"> {{flight.economicPrice}} </td>
                            <td v-else> <input class = "edit" type = "text" v-model= "flight.economicPrice" required > </td>
                        </tr>
                        <tr>
                            <td>Business class price: </td>
                            <td v-if="editing == false"> {{flight.businessPrice}} </td>
                            <td v-else> <input class = "edit" type = "text" v-model= "flight.businessPrice" required> </td>
                        </tr>
                        <tr>
                            <td>First class price: </td>
                            <td v-if="editing == false"> {{flight.firstClassPrice}} </td>
                            <td v-else> <input class = "edit" type = "text" v-model= "flight.firstClassPrice" required > </td>
                        </tr>
                        <br>
                        <tr>
                            <td v-if="editing == false"><button class="btn btn-outline-primary" @click="enableEditing">Edit</button></td>
                            <td v-else><input type="submit" value="Update"></td>
                            <td v-if="editing == false"><button class="btn btn-outline-danger" @click="deleteFlight(flight.id)">Delete</button></td>
                        </tr>
                    </table>
                </form>
                
            </div><br>

            <div class = "seatsContainer">
                <editSeats :iid = "id"></editSeats>
            </div>
        </div>     
    </div>
</template>

<script>
import EditSeats from "./editSeatsOnFlight.vue";
import navbar from "./navbar.vue";
export default {
    name: "flightForAdmin",
    components: {
        editSeats:  EditSeats,
        navbar: navbar
    },
    data: function () {
        return {
                flight: {},
                seatsE: {},
                seatsB: {},
                seatsF: {},
                editing: false,
                startDate: "",
                startTime: "",
                endDate: "",
                endTime: "",
                id: 0,
            }
    },
    created:function(){

        this.id = localStorage.getItem("flightID");
    },
    mounted(){
        
            var getJwtToken = function() {
                return localStorage.getItem("jwtToken");
            };
            axios.get("/api/getFlight/" + this.id)
            .then(response => {
                this.flight = response.data
            });
    },
    methods: {

        modify: function(seat){
            
            var getJwtToken = function() {
            return localStorage.getItem("jwtToken");
            };
            axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
            axios.get("/api/editSeat/" + seat.id)
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
        },

        enableEditing: function(){
            this.editing = true;
        },

        editFlight: function(e){

            e.preventDefault();
            var getJwtToken = function() {
                return localStorage.getItem("jwtToken");
            };
            if (this.startDate != ""){
                 this.flight.startDate_str = this.startDate + " " + this.startTime;
            }
            if (this.endDate != ""){
                 this.flight.endDate_str = this.endDate +  " " + this.endTime;
            }
            axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
            axios.post("/api/editFlight", this.flight)
            .then(response => {
                if (response.data == "success"){
                    alert("Successfuly updated!");
                     this.editing = false;
                }
                else{
                    alert(response.data);
                }
            });    
        },

        deleteFlight: function(id){
            var getJwtToken = function() {
                return localStorage.getItem("jwtToken");
            };
            axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
            axios.get("/api/removeFlight/" + id)
            
            .then(response => {
                if (response.data == "success"){
                    alert("Flight is removed.");
                    window.location = "/flightAdmin"
                }
                else{
                    alert(response.data);
                }
            });
        }
    }     
    
}

</script>

<style scopped>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

.flightForAdminContainer{
    margin-left: 35%;
}


</style>
