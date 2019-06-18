<template>
   <div id = "hotelAdminProfile">
        <navbar />
       <div class="container">
          
                <ha-hotel :hotel="hotelAdmin.hotel" class="container"></ha-hotel>
        </div>
        </div>
</template>

<script>

import haHotel from "./haHotel.vue"
import navbar from ".././navbar.vue";
import axios from "axios";

export default {
  name: "hotelAdminProfile",
  components: {
      haHotel: haHotel,
      navbar
  },
  data: function () {
  return {
    hotelAdmin : {},
    oldPassword: "",
    newPassword: "",
    repeatPassword: "",
    currentTab: 1
  }
},
mounted(){
        var getJwtToken = function() {
            return localStorage.getItem("jwtToken");
        };
        axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
        axios.get("/api/getHotelAdminProfile")
            .then(response => {
                this.hotelAdmin = response.data;
            });
    },
    methods:{
        selectTab: function(tabId){
            this.currentTab = tabId;
        },
        logOut: function() {
            this.$router.push("/");
        },
        changePassword: function() {
            if(this.hotelAdmin.password == this.oldPassword && this.newPassword == this.repeatPassword) {
                this.hotelAdmin.password = this.newPassword;
                axios.post("/api/updateHotelAdmin",this.hotelAdmin)
                .then(response => {
                alert(response.data)
            });
            }
        },
        updateHotelAdminProfile: function() {
            axios.post("/api/updateHotelAdmin",this.hotelAdmin)
                .then(response => {
                alert(response.data)
            });
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
</style>
