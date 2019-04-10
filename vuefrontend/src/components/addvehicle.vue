<template>
  <div id="addVehicle">
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
            <td><button>Back</button></td>
            <td><button @click="add">Add</button></td>
        </tr>
    </table>
  </div>
</template>

<script>

export default {
  name: 'addvehicle',
  components: {
  },
    data: function () {
        return {
            name:"",
            maker:"",
            type:"",
            price:"",
            year:"",
            makers:[],
            types:[]
        }
    },
    mounted(){
        var responseData;
        axios.get("http://localhost:8080/api/getProducers")
            .then(response => {
                this.makers = response.data
            }); 
        axios.get("http://localhost:8080/api/getTypes")
            .then(response => {
                this.types = response.data
            }); 
    },
    methods:{
        add: function(){
            axios.post("http://localhost:8080/api/addCar",{name:this.name,maker:this.maker,type:this.type,year:this.year,price:this.price})
            .then(function(){
              alert("Vehicle has been added");
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
