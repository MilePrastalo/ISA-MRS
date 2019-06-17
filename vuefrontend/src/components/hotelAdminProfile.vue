<template>
   <div id = "hotelAdminProfile">
      
            <table>
                <tr>
                    <td> First name: </td>
                    <td>  <input type="text" name="firstName" v-model="firstName" > </td>
                </tr>
                <tr>
                    <td> Last name: </td>
                    <td>  <input type="text" name="lastName" v-model="lastName" > </td>
                </tr>
                <tr>
                    <td> Email: </td>
                    <td>  <input type="text" name="email" v-model="email" > </td>
                </tr>
                <tr>
                    <td> Password: </td>
                    <td>  <input type="password" name="password" v-model="password" > </td>
                </tr>
                 <tr>
                    <td> Confirm password: </td>
                    <td>  <input type="password" name="password" v-model="repeatedPassword"> </td>
                </tr>
                <tr>
                    <td>  </td>
                    <td><button v-on:click="updateHotelAdminProfile(password,repeatedPassword)">Edit</button> </td>      
                </tr>
                  
            </table>
        </div>
</template>

<script>

export default {
  name: "hotelAdminProfile",
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
    axios.get("http://localhost:8080/api/getHotelAdmin")
        .then(response => {
            this.firstName = response.data.firstName
            this.lastName = response.data.lastName
            this.email = response.data.email
            this.password = response.data.password
            this.repeatedPassword = this.password
          });   
    },
    methods:{
        updateHotelAdminProfile: function(pass, repeated_pass){
            if (pass == repeated_pass){
                axios.post("http://localhost:8080/api/updateHotelAdminProfile",{firstName : this.firstName, lastName: this.lastName, email:this.email, password:this.password})
            .then(response => {
                this.firstName = response.data.firstName
                this.lastName = response.data.lastName
                this.email = response.data.email
                this.password = response.data.password
                this.repeatedPassword = this.password
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

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
