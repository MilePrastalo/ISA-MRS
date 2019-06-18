<template>
    <div id="navbar">
        <nav class="navbar navbar-expand-lg bg-dark navbar-dark static-top">
            <div class="container">
                <a class="navbar-brand" href="#">
                    <img src="http://placehold.it/150x50?text=Logo" alt="">
                    </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                    </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav ml-auto">
                    <li v-if="type!=0" class="nav-item active">
                        <a class="nav-link" :href="homePage">Home
                            <span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li v-if="type!=0" class="nav-item">
                    <a class="nav-link" href="/front/userProfile">Profile</a>
                    </li>
                     <li v-if="type!=0" class="nav-item">
                    <a class="nav-link" href="/front/invites">Invites</a>
                    </li>
                    <li v-if="type!=0" class="nav-item">
                    <a @click="logout" class="nav-link">Logout</a>
                    </li>

                    <li v-if="type==0" class="nav-item active">
                        <a class="nav-link" @click="goToLogin" >Login
                            <span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li v-if="type==0" class="nav-item">
                    <a  class="nav-link" href="/front/registerPage" >Register</a>
                    </li>


                </ul>
                </div>
            </div>
        </nav>
    </div>
</template>
<script>
import axios from "axios";
export default {
  name: "navbar",
  data:function(){
      return{
          type:0,
          homePage:""
      }
  },
  mounted(){
       console.log(this.type);
      var getJwtToken = function() {
            return localStorage.getItem("jwtToken");
            };
      axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
      axios.get("/api/getUserRole")
            .then(response => {
              console.log(response.data);
                this.type = 1;
                console.log(response);
                if(response.data == "RENT_ADMIN"){
                  this.homePage ="/front/RAindex";
                }else if (response.data == "FLIGHT_ADMIN"){
                  this.homePage ="/front/flightAdmin";
                }else if (response.data == "SYS_ADMIN"){
                  this.homePage ="/front/systemAdminPage";
                }else if (response.data == "REGISTERED"){
                  this.homePage ="/front/index";
                }else if (response.data == "HOTEL_ADMIN"){
                  this.homePage ="/front/hotelAdminPage";
                }else{
                  this.homePage ="/";
                  this.type = 0;
                }
                
            });
  },
  methods:{
      logout:function(){
        localStorage.setItem("jwtToken","");
        window.location="/";
    },
    goToLogin(){
      this.$router.push("/front/loginPage");
    }
  }
}
</script>

<style scoped>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
#navbar{
    margin-bottom: 2%;
}

</style>
