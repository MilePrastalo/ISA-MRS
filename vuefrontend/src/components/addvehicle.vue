<template>
  <div id="addVehicle">
      <form action="">
          <div class="form-label-group">
            <label>Vehicle name</label>
            <input type="text" v-model="name" required autofocus>
        </div>
        <div class="form-label-group">
            <label>Maker </label>
            <select v-model="maker" required><option v-for="maker in makers" v-bind:value=maker :key="maker.id">{{maker}}</option></select>
        </div>
        <div class="form-label-group">
            <label>Type</label>
            <select  v-model="type" name="" id="" required><option v-for="type in types" v-bind:value=type :key="type.id">{{type}}</option></select>
        </div>
        <div class="form-label-group">
            <label>Price</label>
            <input v-model="price" type="number" required>
        </div>
        <div class="form-label-group">
            <label>Year</label>
            <input v-model="year" type="number" required>
        </div>
        <div class="form-label-group">
            <label>Date from</label>
            <input v-model="datefrom" type="date" required>
        </div>
        <div class="form-label-group">
            <label>Date to</label>
            <input v-model="dateto" type="date" required>
        </div>
        <div class="form-label-group">
            <button @click="add">Add</button>
        </div>
    </form>    
  </div>
</template>

<script>

export default {
  name: "addvehicle",
  components: {
  },
    data: function () {
        return {
            name:"",
            maker:"",
            type:"",
            price:"",
            year:"",
            dateto:"",
            datefrom:"",
            makers:[],
            types:[]
        }
    },
    mounted(){
        var getJwtToken = function() {
            return localStorage.getItem("jwtToken");
        };
        axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
        var responseData;
        axios.get("/api/getProducers")
            .then(response => {
                this.makers = response.data
            }); 
        axios.get("/api/getTypes")
            .then(response => {
                this.types = response.data
            }); 
    },
    methods:{
        add: function(e){
            e.preventDefault();
            var getJwtToken = function() {
                return localStorage.getItem("jwtToken");
            };
            axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
            axios.post("/api/addCar",{name:this.name,maker:this.maker,type:this.type,year:this.year,price:this.price,dateFrom:this.datefrom,dateTo:this.dateto})
            .then(function(){
              alert("Vehicle has been added");
            }); 
        } 
    }
}

</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
.centeredh2{
  margin-left: auto;
  margin-right: auto;
}
.form-signin {
  width: 100%;
}

.form-signin .btn {
  font-size: 80%;
  border-radius: 5rem;
  letter-spacing: .1rem;
  font-weight: bold;
  padding: 1rem;
  transition: all 0.2s;
}

.form-label-group {
  position: relative;
  margin-bottom: 1rem;
}

.form-label-group input {
  height: auto;
  border-radius: 2rem;
}

label{
  width: 20%;
}
.card-signin {
  border: 0;
  border-radius: 1rem;
  box-shadow: 0 0.5rem 1rem 0 rgba(0, 0, 0, 0.1);
}

.card-signin .card-title {
  margin-bottom: 2rem;
  font-weight: 300;
  font-size: 1.5rem;
}

.card-signin .card-body {
  padding: 2rem;
}

.form-signin {
  width: 100%;
}

.form-signin .btn {
  font-size: 80%;
  border-radius: 5rem;
  letter-spacing: .1rem;
  font-weight: bold;
  padding: 1rem;
  transition: all 0.2s;
}

.form-label-group {
  position: relative;
  margin-bottom: 1rem;
}

.form-label-group input {
  height: auto;
  border-radius: 2rem;
}

.form-label-group>input,
.form-label-group>label {
  padding: var(--input-padding-y) var(--input-padding-x);
}
</style>
