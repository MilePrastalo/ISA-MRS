<template>
   <div id = "addOffice" class='row'>  
     <div class="col">
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
          <label> Latitude: </label>
          <input type="number" step="0.001" name="latitude" v-model="latitude" >
      </div>
      <div class="form-label-group">
          <label> Longitude: </label>
          <input type="number" step="0.001" name="longitude" v-model="longitude" >
      </div>
      <div class="form-label-group">
          <button @click="addOffice()">Add Office</button>
      </div>
     </div>
            
     <div class="col">
        <yandex-map
        :coords="[this.latitude,this.longitude]"
        zoom="14"
        style="width:350px;height:250px;"
        :controlss="['zoomControl']"
        map-type="hybrid"
      >
        <ymap-marker
          marker-id="1"
          marker-type="placemark"
          :coords="[this.latitude,this.longitude]"
          :marker-fill="{color: '#0E4779', opacity: 0.5}"
          :marker-stroke="{color: '#0E4779',width: 4}"
        ></ymap-marker>
      </yandex-map>
     </div>
  </div>     
</template>

<script>
import axios from "axios";

export default {
  name: 'addOffice',
  components: {
  },
  data: function () {
  return {
    name: "",
    address: "",
    destination: "",
    longitude:0.00,
    latitude:0.00,
    map:""
  }
},
mounted(){
},
    methods:{
        addOffice: function(){
            var getJwtToken = function() {
            return localStorage.getItem("jwtToken");
            };
            axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
             axios.post("/api/addOffice",{name:this.name, destination: this.destination,address:this.address,longitude:this.longitude,latitude:this.latitude})
            .then(response => {
                alert("Office added");
                this.$emit("added",response.data);
            });  
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
.centeredmap{
   margin-left: 25%;
    margin-right: auto;
}
</style>
