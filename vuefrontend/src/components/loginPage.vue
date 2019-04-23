<template>
    <div id="loginPage">
        <table>
          <tr>
            <td>Username</td>
            <td><input type="text" id="username" v-model="username"></td>
          </tr>
          <tr>
            <td>Password</td>
            <td><input type="password" id="password" v-model="password"></td>
          </tr>
            <tr>
              <td><button><a href="./unregisteredUserFrontPage">Back</a></button></td>
              <td><button @click="login" type="submit">Log in</button></td>
            </tr>
        </table>
    </div>
</template>

<script>

export default {
  name: 'loginPage',
  data:function(){
    return {
      username:"",
      password:""
    }
  },
  methods:{
    login:function(){
      if(this.username != "" && this.password != ""){
        axios.post("http://localhost:8080/auth/login",{username : this.username, password: this.password})
                    .then(response => {
                        if(response.status == 200){
                          console.log(response.data.accessToken);
                          localStorage.setItem('jwtToken',response.data.accessToken);
                          this.getRole();
                        }
                        else{
                          alert("Wrong username or password");
                        }
                    }); 
      }else{
        alert("All fields must be filled");
      }
     
    },
    getRole : function(){
      console.log("GET ROLE");
      var getJwtToken = function() {
            return localStorage.getItem('jwtToken');
            };
      axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
      axios.get("http://localhost:8080/api/getUserRole")
            .then(response => {
                console.log(response);
                if(response.data == "RENT_ADMIN"){
                  window.location ="./RAindex";
                }else if (response.data == "FLIGHT_ADMIN"){
                  window.location ="./flightAdmin";
                }else if (response.data == "SYS_ADMIN"){
                  window.location ="./systemAdminPage";
                }else if (response.data == "REGISTERED"){
                  window.location ="./index";
                }else if (response.data == "HOTEL_ADMIN"){
                  window.location ="./hotelAdminPage";
                }else{
                  window.location ="./";
                }
                
            });
        
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
  margin-left:auto;
  margin-right:auto;
  margin-top:10%;
  padding: 2%;
}
</style>