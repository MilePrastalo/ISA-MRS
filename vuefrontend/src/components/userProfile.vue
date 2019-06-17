<template>
   <div id = "userProfile">

       <navbar  itype="1"/>
       <div class = "profile">
           <br><br>
            <h2> Your profile: </h2>
            <br>
        <div class="container">
            <table class= "centered" style="text-align: left">
                <tr>
                    <td> First name: </td>
                    <td>  <input   type="text" name="firstName" v-model="firstName" > </td>
                </tr>
                <tr>
                    <td> Last name: </td>
                    <td>  <input   type="text" name="lastName" v-model="lastName" > </td>
                </tr>
                <tr>
                    <td> Email: </td>
                    <td >  <input   type="text" name="email" v-model="email" > </td>
                </tr>
                <tr v-if="isRegistered">
                    <td> City: </td>
                    <td >  <input   type="text" name="city" v-model="city" > </td>
                </tr>
                <tr v-if="isRegistered">
                    <td> Phone: </td>
                    <td >  <input   type="text" name="phone" v-model="phone" > </td>
                </tr>
                <tr>
                    <td> Password: </td>
                    <td>  <input   type="password" name="password" v-model="password" > </td>
                </tr>
                 <tr>
                    <td> Confirm password: </td>
                    <td>  <input  name="password" v-model="repeatedPassword"> </td>
                </tr>
                <tr>
                    <td>  </td>
                    <td><button class="btn btn-primary" v-on:click="updateUserProfile(password,repeatedPassword)">Edit</button> </td>      
                </tr>  
            </table>
        </div>
       </div>
    </div>
</template>

<script>
import navbar from "./navbar.vue";
export default {
  name: "userProfile",
  components: {
       navbar: navbar
  },
  data: function () {
  return {
    firstName: "",
    lastName: "",
    email: "",
    password: "",
    repeatedPassword: "" ,
    city:"",
    phone:"",
    isRegistered:false
  }
},
mounted(){
    var getJwtToken = function() {
            return localStorage.getItem("jwtToken");
        };
    axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
    axios.get("http://localhost:8080/api/getLogUser")
        .then(response => {
            this.firstName = response.data.firstName
            this.lastName = response.data.lastName
            this.email = response.data.email
            this.city = response.data.city;
            this.phone = response.data.phone;
          }).catch(
    error =>{ if(error.response){
    console.log(error.response.data)
}}
); 
    axios.get("http://localhost:8080/api/getUserRole")
        .then(response => {
            if(response.data=="REGISTERED"){
                this.isRegistered = true;
            }
            else{
                this.isRegistered = false;
            }
          }).catch(
    error =>{ if(error.response){
    console.log(error.response.data)
}}
);
    },
    methods:{
        updateUserProfile: function(pass, repeated_pass){
            if (pass == repeated_pass){
                var getJwtToken = function() {
                    return localStorage.getItem("jwtToken");
                };
            axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
                axios.post("http://localhost:8080/api/updateUserProfile",{firstName : this.firstName, lastName: this.lastName, email:this.email, password:this.password, repeatedPass: this.password,city:this.city,phone:this.phone})
            .then(response => {
                this.firstName = response.data.firstName
                this.lastName = response.data.lastName
                this.email = response.data.email
                this.city = response.data.city;
                this.phone = response.data.phone;
            }); 
            alert("Your profile has been successfuly updated!");  
            }
            else{
                alert("Not valid confirmation of the password!");
            }
           
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
.profile{
    margin-left: auto;
    margin-right: auto;
    width: 100%;
}
.centered{
    margin-left: auto;
    margin-right: auto;
}


</style>
