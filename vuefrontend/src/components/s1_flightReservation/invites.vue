<template>
   <div id = "invites">

        <navbar  itype="1"/><br>
        <h1>Invites</h1><br>
        <div class = "container">

            <div class = "centered" >
                <nav>
                    <div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist" style="width: 100%; margin: 0;">
                        <a @click="selectTab(1)" id="reservations" class="nav-item nav-link active"  data-toggle="tab" role="tab" aria-controls="nav-profile" aria-selected="false">Reservation Requests</a>
                        <a @click="selectTab(2)" id="friends" class="nav-item nav-link" data-toggle="tab" role="tab" aria-controls="nav-home" aria-selected="true">Friend Requests</a>
                    </div>
                </nav>
            </div>

            <div class="tab-content py-3 px-3 px-sm-0 container" id="nav-tabContent" style="min-height: 650px;"><br>

                <div v-if="this.currentTab == 1">
                    <br><br>
                    <table class="table">
                        <tr>
                            <th>From</th>
                            <th>Start destination</th>
                            <th>End destination</th>
                            <th>Start date</th>
                            <th>End date</th>
                            <th>Seat</th>
                            <th>Price</th>
                            <th>Details</th>
                        </tr>
                        <tr v-for="(flightReservation, index) in reservationRequests" :key="index">
                             <td>{{flightReservation.creator}}</td>
                            <td>{{flightReservation.startDestination}}</td>
                            <td>{{flightReservation.endDestination}}</td>
                            <td>{{flightReservation.startDate}}</td>
                            <td>{{flightReservation.endDate}}</td>
                            <td> {{flightReservation.travelClassa}} : ({{flightReservation.seatRow}},{{flightReservation.seatColumn}})</td>
                            <td>{{flightReservation.price}}</td>
                            <td><button class="btn btn-outline-success" @click="confirm(flightReservation.id,index)">Confirm</button></td>
                            <td ><button class="btn btn-outline-danger" @click="refuse(flightReservation.id, index)">Refuse</button></td>
                            <td><button class="btn btn-outline-primary" @click="flightReservationDetails(flightReservation.id)">Details</button></td>
                        </tr>
                    </table>
                </div>
                <div v-if="this.currentTab == 2">
                    <friendRequests></friendRequests>
                </div>

            </div>

        </div>
         
    </div>
</template>

<script>
import navbar from "../navbar.vue";
import FriendRequests from "../s1_friends/friendRequests.vue";

export default {
  name: "invites",
  components: {
      navbar,
      friendRequests: FriendRequests
  },
  data: function () {
  return {
      
    reservationRequests: [],
    currentTab: 1
  }
},

created(){ 

    var getJwtToken = function() {
            return localStorage.getItem("jwtToken");
        };
    axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
    axios.get("http://localhost:8080/api/getMyReservationRequests")
            .then(response => {
                this.reservationRequests = response.data;
                });   
    },

    methods:{ 
        
        selectTab: function(tabId){
            if (tabId == 1){
                document.getElementById("reservations").className="nav-item nav-link active";
                document.getElementById("friends").className="nav-item nav-link";
            }
            else if (tabId == 2){
                document.getElementById("friends").className="nav-item nav-link active";
                document.getElementById("reservations").className="nav-item nav-link";
            }
            this.currentTab = tabId;
        },

        confirm: function(id, idx){
            var getJwtToken = function() {
            return localStorage.getItem("jwtToken");
            };
            axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
            axios.get("http://localhost:8080/api/confirmReservationRequest/" + id )
            .then(response => {
                alert(response.data)
                if (response.data == "success"){
                    this.reservationRequests.splice(idx,1)
                }
            });
        },

        refuse: function(id, idx){
            var getJwtToken = function() {
            return localStorage.getItem("jwtToken");
            };
            axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
            axios.get("http://localhost:8080/api/refuseReservationRequest/" + id )
            .then(response => {
                alert(response.data)
                if (response.data == "success"){
                    this.reservationRequests.splice(idx,1)
                }
            });
        },

         flightReservationDetails: function(id){
            window.location = "/confirmationPage/" + id;
        }
    }
}
</script>
<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav-tab{
    margin-left: 5%;
    margin-right: 5%;
}

nav > .nav.nav-tabs{

  border: none;
    color:#fff;
    background:#272e38;
    border-radius:0;

}
nav > div a.nav-item.nav-link,
nav > div a.nav-item.nav-link.active
{
  border: none;
    padding: 18px 25px;
    color:#fff;
    background:#272e38;
    border-radius:0;
}

nav > div a.nav-item.nav-link.active:after
 {
  content: "";
  position: relative;
  bottom: -60px;
  left: -10%;
  border: 15px solid transparent;
  border-top-color: #428bca ;
}
.tab-content{
  background: #fdfdfd;
    line-height: 25px;
    border: 1px solid #ddd;
    border-top:5px solid #428bca;
    border-bottom:5px solid #428bca;
    padding:30px 25px;
}

nav > div a.nav-item.nav-link:hover,
nav > div a.nav-item.nav-link:focus
{
  border: none;
    background: #428bca;
    color:#fff;
    border-radius:0;
    transition:background 0.20s linear;
}


</style>
