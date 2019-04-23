<template>
  <div id="rentacarprofile">
    <h2>Company profile</h2>
    <table>
        <tr>
            <td>Name</td>
            <td><input v-model="name" type="text"></td>
        </tr>
        <tr>
            <td>Address</td>
            <td><input v-model="address" type="text"></td>
                
        </tr>
        <tr>
            <td>Description</td>
            <td><textarea  rows="5" cols="22" name="description"  v-model="description" style="overflow:scroll;"></textarea></td>
        </tr>
        <tr>
            <td><Button v-on:click="update">Update</Button></td>
        </tr>
    </table>
  </div>
</template>

<script>

export default {
  name: 'rentacarprofile',
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
    return localStorage.getItem('jwtToken');
  };
  axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
    axios.get("http://localhost:8080/api/getRentACarCompany")
            .then(response => {
                this.name = response.data.name;
                this.address = response.data.address;
                this.description = response.data.description;
          });  
    },
    methods:{
        update: function(){
          var getJwtToken = function() {
    return localStorage.getItem('jwtToken');
  };
            axios.defaults.headers.post['Authorization'] = "Bearer " + getJwtToken();
            axios.post("http://localhost:8080/api/updateRentACarProfile",{name : this.name, address: this.address, description:this.description});
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
h2{
  margin-top: 5%;
}
</style>