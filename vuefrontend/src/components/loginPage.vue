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
                          localStorage.setItem('jwtToken',response.data.accessToken)
                        }
                        else{
                          alert("Wrong username or password");
                        }
                    }); 
      }else{
        alert("All fields must be filled");
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
}
</style>