<template>
    <div id="FirstLogin">
      <div class="container">
        <div class="row">
          <div class="col-sm-9 col-md-7 col-lg-5 mx-auto blue-border">
            <div class="card card-signin my-5">
              <div class="card-body">
                <h5 class="card-title text-center">Password change</h5>
                <form class="form-signin">
                  <div class="form-label-group">
                    <input type="password" v-model="password" id="inputEmail" class="form-control" required autofocus>
                    <label for="inputEmail">Password</label>
                  </div>

                  <div class="form-label-group">
                    <input v-model="confirmedPassword" type="password" id="inputPassword" class="form-control" required>
                    <label  for="inputPassword">Confirm</label>
                  </div>
                  <button @click="ChangePassword" class="btn btn-lg btn-primary btn-block text-uppercase">Set Password</button>
                  </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
</template>

<script>

export default {
  name: "FirstLogin",
  data:function(){
    return {
      password:"",
      confirmedPassword:""
    }
  },
  methods:{
    ChangePassword:function(e){
        e.preventDefault();
        var getJwtToken = function() {
            return localStorage.getItem("jwtToken");
            };
      axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();

      axios.post("http://localhost:8080/api/firstLogin",{password:this.password,confirmed:this.confirmedPassword})
        .then(response => {
            if(response.data !=="OK"){
                alert("ERROR");
                window.location="./";
            }
            else{
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
        });
      
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
table{
  margin-left:auto;
  margin-right:auto;
  margin-top:2%;
}
h2{
  margin: auto;
  margin-bottom: 2%;
}
:root {
  --input-padding-x: 1.5rem;
  --input-padding-y: .75rem;
}

body {
  background: #007bff;
  background: linear-gradient(to right, #0062E6, #33AEFF);
}

.card-signin {
  border: 0;
  border-radius: 1rem;
  box-shadow: 0 0.5rem 1rem 0 rgba(0, 0, 0, 0.1);
}

.card-signin .card-title {
  margin-bottom: 2rem;
  font-weight: 300;
  font-size: 1.5rem;
}

.card-signin .card-body {
  padding: 2rem;
}

.form-signin {
  width: 100%;
}

.form-signin .btn {
  font-size: 80%;
  border-radius: 5rem;
  letter-spacing: .1rem;
  font-weight: bold;
  padding: 1rem;
  transition: all 0.2s;
}

.form-label-group {
  position: relative;
  margin-bottom: 1rem;
}

.form-label-group input {
  height: auto;
  border-radius: 2rem;
}

.form-label-group>input,
.form-label-group>label {
  padding: var(--input-padding-y) var(--input-padding-x);
}

.form-label-group>label {
  position: absolute;
  top: 0;
  left: 0;
  display: block;
  width: 100%;
  margin-bottom: 0;
  /* Override default `<label>` margin */
  line-height: 1.5;
  color: #495057;
  border: 1px solid transparent;
  border-radius: .25rem;
  transition: all .1s ease-in-out;
}

.form-label-group input::-webkit-input-placeholder {
  color: transparent;
}

.form-label-group input:-ms-input-placeholder {
  color: transparent;
}

.form-label-group input::-ms-input-placeholder {
  color: transparent;
}

.form-label-group input::-moz-placeholder {
  color: transparent;
}

.form-label-group input::placeholder {
  color: transparent;
}

.form-label-group input:not(:placeholder-shown) {
  padding-top: calc(var(--input-padding-y) + var(--input-padding-y) * (2 / 3));
  padding-bottom: calc(var(--input-padding-y) / 3);
}

.form-label-group input:not(:placeholder-shown)~label {
  padding-top: calc(var(--input-padding-y) / 3);
  padding-bottom: calc(var(--input-padding-y) / 3);
  font-size: 12px;
  color: #777;
}
.blue-border{
  border: 1px solid lightblue;
}
</style>