<template>
  <div id="rentacarprofile" class="centered">
    <h2>Company profile</h2>
    <form class="form-signin centered" >
      <div class="form-label-group centered">
        <label for="name">Name</label>
        <input id="name" v-model="name" type="text" required autofocus>
      </div>
      <div class="form-label-group">
        <label for="address">Address</label>
        <input id="address" v-model="address" type="text" required>    
      </div>
      <div class="form-label-group">
          <label for="desc">Description</label>
          <textarea  rows="5" cols="22" name="description"  v-model="description" style="overflow:scroll;" required></textarea> 
      </div>
      <div class="form-label-group">
          <Button type="submit" v-on:click="update" class="btn-primary">Update</Button>
      </div>
    </form>
  </div>
</template>

<script>

export default {
  name: "rentacarprofile",
  components: {
  },
  data: function () {
  return {
    name:"",
    address:"",
    description:""
  }
},
mounted(){
  var getJwtToken = function() {
    return localStorage.getItem("jwtToken");
  };
  axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
    axios.get("/api/getRentACarCompany")
            .then(response => {
                this.name = response.data.name;
                this.address = response.data.address;
                this.description = response.data.description;
          });  
    },
    methods:{
        update: function(e){
          e.preventDefault();
          var getJwtToken = function() {
    return localStorage.getItem("jwtToken");
  };
            axios.defaults.headers.post['Authorization'] = "Bearer " + getJwtToken();
            axios.post("/api/updateRentACarProfile",{name : this.name, address: this.address, description:this.description});
            ; 
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
:root {
  --input-padding-x: 1.5rem;
  --input-padding-y: .75rem;
}

body {
  background: #007bff;
  background: linear-gradient(to right, #0062E6, #33AEFF);
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
label{
  width: 20%;
}
.centered{
  margin-left: auto;
  margin-right: auto;
  align-content: center;
  align-items: center;
  align-self: center;
  width: 800px;

}
</style>