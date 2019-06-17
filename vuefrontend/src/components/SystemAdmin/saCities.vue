<template>
   <div id = "saCities" class="container">

            <br>
             <div class="row"> 
            </div>
             <br>
             <div class="row">
                <ul class="nav nav-tabs">
                    <li class="nav-item">
                        <a  class="nav-link" href="#" @click="selectTab(1)">All Cities</a>
                    </li>
                    <li class="nav-item">
                        <a  class="nav-link" href="#" @click="selectTab(2)">Add City</a>
                        
                    </li>
                </ul>
            </div>
            
            <div v-if="currentTab == 1">
                <div v-if="editing == false">
                <br>
                <br>
                <table class="table">
                    <thead class="thead-dark">
                    
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Options</th>
                    </tr>
                    </thead>
            <tr v-for="d in this.cities" :key="d.id">  
                <td>{{d.id}}</td>
                <td>{{d.name}}</td>
                <td><button class="btn-primary" v-on:click="editCity(d)">Edit</button></td>
            </tr>
            </table>
            </div>
            </div>
            <div v-if="currentTab == 2">
                <br>
                <table>
                <tr>
                    <td><b> City Name: </b></td>
                    <td>  <input type="text" v-model="newCity.name" > </td>
                    <td><button v-on:click="addCity()" class="btn-primary">Add City</button> </td>     
                </tr>
            </table>
            </div>   
            <div v-if="editing == true">
                <table>
                    <tr>
                    <td><b> Old Name: </b></td>
                    <td>  {{ed.oldName}} </td>
                    </tr>
                    <tr>
                    <td><b> New Name: </b></td>
                    <td>  <input type="text" v-model="ed.newName" > </td>
                </tr>
                    <tr>
                        <td><button v-on:click="saveCity()" class="btn-primary">Save</button></td>
                        <td><button v-on:click="cancelEditing()" class="btn-primary">Cancel</button></td>
                    </tr>
                </table>
            </div>
            </div>
</template>

<script>

export default {
  name: "saCities",
  components: {
  },
  data: function () {
  return {
    cities: [],
    newCity: {},
    ed: {},
    cancelED: {},
    currentTab: 1,
    editing: false
  }
},
mounted(){
        var getJwtToken = function() {
            return localStorage.getItem("jwtToken");
        };
        axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
        axios.get("/api/getAllCities")
            .then(response => {
                this.cities = response.data;
            })
    },
    methods:{
        selectTab: function(tabId){
            this.currentTab = tabId;
        },
        addCity: function() {
            if(this.newCity.name == "") {
                alert("You must enter city name");
                return;
            }

            for(let c in this.cities) {
                if(this.cities[c].name == this.newCity.name) {
                    alert("City with given name already exists.");
                    return;
                }
            }

            axios.post("/api/addCity",this.newCity)
            .then(response => {
                if(response.data == false) {
                    alert("City with given name already exists.");
                } else {
                    alert("City has been successfully added.");
                    this.cities.push(this.newCity);
                    this.newCity = {};
                }
            });
        },
        editCity: function(city) {
            this.ed.oldName = city.name;
            this.cancelED = city;
            this.editing = true;
        },
        saveCity: function() {
            if(this.ed.newName == "") {
                alert("You must enter new city name");
                return;
            }

            for(let c in this.cities) {
                if(this.cities[c].name == this.ed.newName) {
                    alert("City with given name already exists.");
                    return;
                }
            }

            axios.post("/api/editCity",this.ed)
            .then(response => {
                if(response.data == false) {
                    alert("City with given name already exists.");

                } else {
                    alert("City has been successfully edited.");
                    this.editing = false;
                    this.cancelED.name = this.ed.newName;
                    return;
                }
            });
        },
        cancelEditing: function() {
            this.ed.newName = "";
            this.editing = false;
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
  margin-left: 5%;
}
</style>
