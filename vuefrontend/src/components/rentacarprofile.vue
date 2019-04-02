<template>
  <div id="rentacarprofile">
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
    axios.get("http://localhost:8080/api/getRentACarCompany")
            .then(response => {
                this.name = response.data.name;
                this.address = response.data.address;
                this.description = response.data.description;
          });  
    },
    methods:{
        update: function(){
            axios.post("http://localhost:8080/api/updateRentACarProfile",{name : this.name, address: this.address, description:this.description});
            ; 
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