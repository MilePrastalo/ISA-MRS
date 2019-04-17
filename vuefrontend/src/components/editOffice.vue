<template>
   <div id = "addOffice">
            <table>
                <tr>
                    <td> Name: </td>
                    <td>  <input type="text" name="name" v-model="name" > </td>
                </tr>
                <tr>
                    <td> <Address></Address>: </td>
                    <td>  <input type="text" name="address" v-model="address" > </td>
                </tr>
                <tr>
                    <td> Destination: </td>
                    <td> <textarea  rows="5" cols="22" name="destination"  v-model="description" style="overflow:scroll;"></textarea> </td>        
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
    id : 0,
    name: "",
    address: "",
    destination: ""
  } 
},
    methods:{
        addOffice: function(){
             axios.post("http://localhost:8080/api/editOffice",{name:this.name, destination: this.destination})
            .then(response => {
                alert("Office edited");
                this.name = response.name;
                this.destination = response.destination;
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
