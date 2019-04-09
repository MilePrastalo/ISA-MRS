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
    description: ""
  }
},
    methods:{
        addHotel: function(){
             axios.post("http://localhost:8080/api/addHotel",{name:this.name, address: this.address, description: this.description})
            .then(response => {
                this.name = response.data.name
                this.address = response.data.address
                this.description = response.data.description
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
