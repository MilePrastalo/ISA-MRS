<template>
   <div id = "editOffice" class="row">
     <div class="col">
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
                <label> Latitude: </label>
                <input type="number" step="0.001" name="latitude" v-model="latitude" >
            </div>
            <div class="form-label-group">
                <label> Longitude: </label>
                <input type="number" step="0.001" name="longitude" v-model="longitude" >
            </div>
            <div class="form-label-group">
                <Button @click="backAdd()">Back to adding</Button>
                <button @click="edit()">Edit Office</button>  
            </div>
       </form> 
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

export default {
  name: "editOffice",
  components: {
  },
  props: [
  "iid" ,
  "iname",
  "idestination",
  "iaddress", "ilongitude","ilatitude"],
  data: function () {
  return {
    id : this.iid,
    name: this.iname,
    destination: this.idestination,
    address:this.iaddress,
    longitude:this.ilongitude,
    latitude:this.ilatitude,
  } 
},
    methods:{
        edit: function(){
            var a = this;
            var getJwtToken = function() {
            return localStorage.getItem("jwtToken");
            };
            axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
            axios.post("http://localhost:8080/api/editOffice",{id:this.id,name:this.name,destination:this.destination,address:this.address,longitude:this.longitude, latitude:this.latitude})
            .then(function(){
              alert("Office has been edited");
              a.$emit("vedited");
            });
            ///this.$emit('vedited'); 
        },
        backAdd:function(){
            this.$emit("backadd");
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
.centeredmap{
   margin-left: 25%;
    margin-right: auto;
}
</style>
