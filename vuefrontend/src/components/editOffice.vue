<template>
   <div id = "editOffice">
       <form action="">
            <div class="form-label-group">
                <label> Name: </label>
                <input type="text" name="name" v-model="name" >
            </div>
            <div class="form-label-group">
                <label> Address: </label>
                <input type="text" name="address" v-model="address" >
            </div>
            <div class="form-label-group">
                <label> Destination: </label>
                <input type="text" name="destination" v-model="destination" >
            </div>
            <div class="form-label-group">
                <Button @click="backAdd()">Back to adding</Button>
                <button @click="edit()">Edit Office</button>  
            </div>
       </form> 
    </div>
</template>

<script>

export default {
  name: 'editOffice',
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
#editOffice{
  margin-left: auto;
  margin-right: auto;
}
</style>
