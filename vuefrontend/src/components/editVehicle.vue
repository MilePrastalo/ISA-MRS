<template>
  <div id="editVehicle">
    <table>
        <tr>
            <td>Vehicle name</td>
            <td><input type="text" v-model="name"></td>
        </tr>
        <tr>
            <td>Maker </td>
            <td><select v-model="maker"><option v-for="maker in makers" v-bind:value=maker :key="maker.id">{{maker}}</option></select></td>
        </tr>
        <tr>
            <td>Type</td>
            <td><select v-model="type" name="" id=""><option v-for="type in types" v-bind:value=type :key="type.id">{{type}}</option></select></td>
        </tr>
        <tr>
            <td>Price</td>
            <td><input v-model="price" type="number"></td>
        </tr>
        <tr>
            <td>Year</td>
            <td><input v-model="year" type="number"></td>
        </tr>
        <tr>
            <td><button @click="back">Back</button></td>
            <td><button @click="edit">Edit</button></td>
        </tr>
    </table>
  </div>
</template>

<script>

export default {
  name: 'editVehicle',
  components: {
  },
  props: [
  'iid' ,
  'iname',
  'imaker',
  'itype',
  'iyear',
  'iprice'],
  created:function(){
  },
    data: function () {
        return {
            name:this.iname,
            maker:this.imaker,
            type:this.itype,
            price:this.iprice,
            year:this.iyear,
            id:this.iid,
            makers:[],
            types:[]
        }
    },
    mounted(){
        console.log(this.maker);
        var getJwtToken = function() {
            return localStorage.getItem('jwtToken');
        };
        axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
        var responseData;
        axios.get("http://localhost:8080/api/getProducers")
            .then(response => {
                this.makers = response.data;
            }); 
        axios.get("http://localhost:8080/api/getTypes")
            .then(response => {
                this.types = response.data;
            }); 
    },
    methods:{
        edit: function(){
            var a = this;
            var getJwtToken = function() {
            return localStorage.getItem('jwtToken');
            };
            axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
            axios.post("http://localhost:8080/api/editCar",{id:this.id,name:this.name,maker:this.maker,type:this.type,year:this.year,price:this.price})
            .then(function(){
              alert("Vehicle has been edited");
              a.$emit('vedited');
            });
            ///this.$emit('vedited'); 
        },
        back:function(){
            this.$emit('back');
        } 
    },
    
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