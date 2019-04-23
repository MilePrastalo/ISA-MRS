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
                    <td><Button @click="backAdd()">Back to adding</Button>  </td>
                    <td><button v-on:click="edit()">Edit Office</button> </td>   
                </tr>
            </table>      
        </div>
</template>

<script>

export default {
  name: 'addOffice',
  components: {
  },
  props: [
  'iid' ,
  'iname',
  'idestination',
  'iaddress'],
  data: function () {
  return {
    id : this.iid,
    name: this.iname,
    destination: this.idestination,
    address:this.iaddress
  } 
},
    methods:{
        edit: function(){
            var a = this;
            var getJwtToken = function() {
            return localStorage.getItem('jwtToken');
            };
            axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
            axios.post("http://localhost:8080/api/editOffice",{id:this.id,name:this.name,destination:this.destination,address:this.address})
            .then(function(){
              alert("Office has been edited");
              a.$emit('vedited');
            });
            ///this.$emit('vedited'); 
        },
        backAdd:function(){
            this.$emit('backadd');
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
</style>
