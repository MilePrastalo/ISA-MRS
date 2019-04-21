<template>
   <div id = "addOffice">
            <table>
                <tr>
                    <td> Name: </td>
                    <td>  <input type="text" name="name" v-model="name" > </td>
                </tr>
                <tr>
                    <td> Address: </td>
                    <td>  <input type="text" name="address" v-model="address" > </td>
                </tr>
                <tr>
                    <td> Destination: </td>
                    <td> <textarea  rows="5" cols="22" name="destination"  v-model="destination" style="overflow:scroll;"></textarea> </td>        
                </tr>
                <tr>
                    <td>  </td>
                    <td><button v-on:click="addOffice()">Add Office</button> </td>   
                </tr>
            </table>      
        </div>
</template>

<script>

export default {
  name: 'addOffice',
  components: {
  },
  data: function () {
  return {
    name: "",
    address: "",
    destination: ""
  }
},
    methods:{
        addOffice: function(){
            var getJwtToken = function() {
            return localStorage.getItem('jwtToken');
            };
            axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
             axios.post("http://localhost:8080/api/addOffice",{name:this.name, destination: this.destination,address:this.address})
            .then(response => {
                alert("Office added");
            });  
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
