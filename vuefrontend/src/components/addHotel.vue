<template>
   <div id = "addHotel">
            <table>
                <tr>
                    <td> Input hotel name: </td>
                    <td>  <input type="text" name="name" v-model="name" > </td>
                </tr>
                <tr>
                    <td> Input hotel address: </td>
                    <td>  <input type="text" name="address" v-model="address" > </td>
                </tr>
                <tr>
                    <td> Input hotel description: </td>
                    <td> <textarea  rows="5" cols="22" name="description"  v-model="description" style="overflow:scroll;"></textarea> </td>        
                </tr>
                <tr>
                    <td> Input hotel longitude: </td>
                    <td> <input type="number" v-model="longitude" > </td>        
                </tr>
                <tr>
                    <td> Input hotel latitude: </td>
                    <td> <input type="number"  v-model="latitude" > </td>        
                </tr>
                <tr>
                    <td>  </td>
                    <td><button v-on:click="addHotel()">Add hotel</button> </td>   
                </tr>
            </table>      
        </div>
</template>

<script>

export default {
  name: 'addHotel',
  components: {
  },
  data: function () {
  return {
    name: "",
    address: "",
    description: "",
    longitude: 0,
    latitude: 0
  }
},
    methods:{
        addHotel: function(){
             axios.post("/api/addHotel",{name:this.name, address: this.address, description: this.description,longitude:this.longitude,latitude:this.latitude})
            .then(response => {
                this.name = response.data.name
                this.address = response.data.address
                this.description = response.data.description
                this.longitude = response.data.longitude
                this.latitude = response.data.latitude
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
