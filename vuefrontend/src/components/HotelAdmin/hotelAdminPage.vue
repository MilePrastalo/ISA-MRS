<template>
   <div id = "hotelAdminProfile">

            <br>
             <div class="row"> 
                <h1>Hotel Admin Profile</h1>
            </div>
             <br>
             <div class="row">
                <ul class="nav nav-tabs">
                    <li class="nav-item">
                        <a  class="nav-link active" href="#" @click="selectTab(1)">Profile</a>
                    </li>
                    <li class="nav-item">
                        <a  class="nav-link" href="#" @click="selectTab(2)">Hotel</a>
                        
                    </li>
                    <li class="nav-item">
                        <a  class="nav-link active" href="#" @click="selectTab(3)">Change Password</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#" @click="selectTab(4)">Log Out</a>
                    </li>
                </ul>
            </div>
            
            <div v-if="currentTab == 1" id = "profile">
                <br>
                <br>
                <table>
                    <tr>
                        <td> Username </td>
                        <td>  <input type="text" name="hotelAdmin.username" v-model="hotelAdmin.username"> </td>
                    </tr>
                    <tr>
                        <td> First name: </td>
                        <td>  <input type="text" name="hotelAdmin.firstName" v-model="hotelAdmin.firstName" > </td>
                    </tr>
                    <tr>
                        <td> Last name: </td>
                        <td> <input type="text" name="hotelAdmin.lastName"  v-model="hotelAdmin.lastName" > </td>        
                    </tr>
                    <tr>
                        <td> Email: </td>
                        <td> <input type="text" name="hotelAdmin.email"  v-model="hotelAdmin.email" > </td>        
                    </tr>
                    <tr>
                        <td>  </td>
                        <td><button v-on:click="updateSystemAdminProfile()">Edit</button> </td>        
                    </tr>
                </table>
            </div>

            <div  v-if="currentTab == 2" id = "hotel"> 
                <ha-hotel :hotel="hotelAdmin.hotel"></ha-hotel>
            </div> 
            <div  v-if="currentTab == 3" id = "Change Password">
                <table>
                    <tr>
                        <td> Enter old password: </td>
                        <td>  <input type="text" name="oldPassword" v-model="oldPassword"> </td>
                    </tr>
                    <tr>
                        <td> Enter new password: </td>
                        <td>  <input type="text" name="newPassword" v-model="newPassword" > </td>
                    </tr>
                    <tr>
                        <td> Repeat new password: </td>
                        <td> <input type="text" name="repeatPassword"  v-model="repeatPassword" > </td>        
                    </tr>
                    <tr>
                        <td>  </td>
                        <td><button v-on:click="changePassword()">Change Password</button> </td>        
                    </tr>
                </table>
            </div>
            <div  v-if="currentTab == 4" id = "Log Out" v-on="logOut()">
            </div>
        </div>
</template>

<script>

import haHotel from "./haHotel.vue"

export default {
  name: 'hotelAdminProfile',
  components: {
      haHotel: haHotel
  },
  data: function () {
  return {
    hotelAdmin : [],
    oldPassword: "",
    newPassword: "",
    repeatPassword: "",
    currentTab: 1
  }
},
mounted(){
        var getJwtToken = function() {
            return localStorage.getItem('jwtToken');
        };
        axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
        axios.get("http://localhost:8080/api/getHotelAdminProfile")
            .then(response => {
                this.hotelAdmin = response.data;
            });
    },
    methods:{
        selectTab: function(tabId){
            this.currentTab = tabId;
        },
        logOut: function() {
            this.$router.push('/');
        },
        changePassword: function() {
            if(this.hotelAdmin.password == this.oldPassword && this.newPassword == this.repeatPassword) {
                this.hotelAdmin.password = this.newPassword;
                axios.post("http://localhost:8080/api/updateHotelAdmin",this.hotelAdmin)
                .then(response => {
                this.hotelAdmin = response.data;
            });
            }
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
  margin-left: 5%;
}
</style>
