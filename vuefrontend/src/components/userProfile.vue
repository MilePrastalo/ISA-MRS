<template>
   <div id = "userProfile">

       <div class = "profile">
           <br><br>
            <h2> Your profile: </h2>
            <br>
        <div class="centered">
            <table class= "centered" style="text-align: left">
                <tr>
                    <td> First name: </td>
                    <td>  <input  class="rounded" type="text" name="firstName" v-model="firstName" > </td>
                </tr>
                <tr>
                    <td> Last name: </td>
                    <td>  <input  class="rounded" type="text" name="lastName" v-model="lastName" > </td>
                </tr>
                <tr>
                    <td> Email: </td>
                    <td >  <input  class="rounded" type="text" name="email" v-model="email" > </td>
                </tr>
                <tr>
                    <td> Password: </td>
                    <td>  <input  class="form-control" type="password" name="password" v-model="password" > </td>
                </tr>
                 <tr>
                    <td> Confirm password: </td>
                    <td>  <input   type="password" name="password" v-model="repeatedPassword"> </td>
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

export default {
  name: 'userProfile',
  components: {
  },
  data: function () {
  return {
    firstName: "",
    lastName: "",
    email: "",
    password: "",
    repeatedPassword: "" 
  }
},
mounted(){
    var getJwtToken = function() {
            return localStorage.getItem('jwtToken');
        };
    axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
    axios.get("http://localhost:8080/api/getLogUser")
        .then(response => {
            this.firstName = response.data.firstName
            this.lastName = response.data.lastName
            this.email = response.data.email
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
                    return localStorage.getItem('jwtToken');
                };
            axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
                axios.post("http://localhost:8080/api/updateUserProfile",{firstName : this.firstName, lastName: this.lastName, email:this.email, password:this.password, repeatedPass: this.password})
            .then(response => {
                this.firstName = response.data.firstName
                this.lastName = response.data.lastName
                this.email = response.data.email
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
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
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
.rounded{
    border-radius: 25px;
    padding: 2px;
    height: 30px;
}

</style>
