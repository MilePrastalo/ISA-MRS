<template>
   <div id = "addOffice">  
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
          <button type="submit" v-on:click="addOffice()">Add Office</button>
      </div>     
     </form>
  </div>     
</template>

<script>

export default {
  name: 'addOffice',
  components: {
  },
  data: function () {
  return {
    name: "",
    address: "",
    destination: "",
    map:""
  }
},
mounted(){
    this.map = new ol.Map({
        target: 'map',
        layers: [
          new ol.layer.Tile({
            source: new ol.source.OSM()
          })
        ],
        view: new ol.View({
          center: ol.proj.fromLonLat([37.41, 8.82]),
          zoom: 4
        })
      });
    this.map.getView().setCenter ([40,40]);
},
    methods:{
        addOffice: function(e){
            e.preventDefault();
            var getJwtToken = function() {
            return localStorage.getItem('jwtToken');
            };
            axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
             axios.post("http://localhost:8080/api/addOffice",{name:this.name, destination: this.destination,address:this.address})
            .then(response => {
                alert("Office added");
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
</style>
