<template>
    <div id="registerPage">
      <h2>Register</h2>
        <table>
          <tr>
            <td>Username</td>
            <td><input type="text" v-model="username"></td>
          </tr>
          <tr>
            <td>First Name</td>
            <td><input type="text" v-model="firstName"></td>
          </tr>
          <tr>
            <td>Last Name</td>
            <td><input type="text" v-model="lastName"></td>
          </tr>
          <tr>
            <td>E-mail</td>
            <td><input type="text" v-model="email"></td>
          </tr>
          <tr>
            <td>Password</td>
            <td><input type="password" v-model="password"></td>
          </tr>
          <tr>
            <td>Confirm password</td>
            <td><input type="password" v-model="confpassword"></td>
          </tr>
          <tr>
            
            <td><button><a href="./">Back</a></button></td>
            <td><button @click="register"  type="submit">Register</button></td>
          </tr>
        </table>
        
    </div>
</template>

<script>

export default {
  name: 'registerPage',
  data:function(){
    return{
      username:"",
      password : "",
      firstName:"",
      lastName:"",
      email:"",
      confpassword:""
    }
  },
  methods:{
    register:function(){
      if (this.confpassword !== this.password){
        alert("Passwords don't match");
      }else{
      if(this.username != "" && this.password != "" && this.firstName != "" && this.lastName != ""&& this.email!=""){
          axios.post("http://localhost:8080/auth/register",{username : this.username, password: this.password,
          firstName : this.firstName, lastName: this.lastName, email: this.email})
                      .then(response => {
                        console.log(response);
                          if(response.data =="OK"){
                            alert("Registration is successfull");
                            window.location="/";
                          }
                          else{
                            alert("User with that username already exists");
                          }
                      }); 
        }else{
          alert("All fields must be filled");
        }
      
      }
    }
  }
}

</script>

<style scoped>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
table{
  margin-left: auto;
    margin-right: auto;
  margin-top: 5%
}
h2{
  margin: auto;
  margin-bottom: 2%;
}
</style>