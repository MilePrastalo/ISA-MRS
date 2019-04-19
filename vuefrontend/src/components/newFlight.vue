<template>
   <div id = "addFlight">
            <br>
            <div class = "row">
            <h1>New Flight: </h1>
            </div>
            <br>
            <div class = "row">
            <table>

                <tr>
                    <td> Start destination: </td>
                     <td><select v-model="startDestination" name="startDestination" id="startDestination" required>
                        <option v-for="startDestination in destinations" v-bind:value= startDestination :key="startDestination.id">{{startDestination.name}}</option>
                    </select></td>
                </tr>
                <tr>
                    <td> End destination: </td>
                    <td><select v-model="endDestination" name="endDestination" id="endDestination" required>
                        <option v-for="endDestination in destinations" v-bind:value=endDestination :key="endDestination.id">{{endDestination.name}}</option>
                    </select></td>
                </tr>
                <tr>
                    <td> Start date: </td>
                    <td>  <input type="date" name="startDate" v-model="startDate" required> </td>
                </tr>
                <tr>
                    <td> End date: </td>
                    <td>  <input type="date" name="endDate" v-model="endDate" required > </td>
                </tr>
                 <tr>
                    <td> Flight duration: </td>
                    <td> <input type="text" name="flightDuration" v-model="flightDuration" required >  </td>        
                </tr>
                 <tr>
                    <td> Flight length: </td>
                    <td> <input type="text" name="flightLength" v-model="flightLength" required >  </td>        
                </tr>
                <tr>
                    <td> Buisiness class price: </td>
                    <td> <input type="text" name="buisinesssPrice" v-model="buisinesssPrice" required >  </td>        
                </tr>
                <tr>
                    <td> First class price: </td>
                    <td> <input type="text" name="firstClassPrice" v-model="firstClassPrice" required>  </td>        
                </tr>
                <tr>
                    <td> Economic class price: </td>
                    <td> <input type="text" name="economicPrice" v-model="economicPrice" required > </td>        
                </tr>
                <br>
                <tr>
                    <td> Economic class capacity </td>       
                </tr>
                <tr>
                    <td> Rows: </td> 
                    <td> <input type="number"  v-model="economicCapacity_rows" required >  </td>
                    <td> Columns: </td> 
                    <td> <input type="number"  v-model="economicCapacity_columns" required >  </td>        
                </tr>
                <tr>
                    <td> Buisiness class capacity </td>       
                </tr>
                <tr>
                    <td> Rows: </td> 
                     <td> <input type="number"  v-model="buisinesssCapacity_rows" required >  </td>
                     <td> Columns: </td> 
                     <td> <input type="number"  v-model="buisinesssCapacity_columns" required >  </td>       
                </tr>
                <tr>
                    <td> First class capacity </td>       
                </tr>
                <tr>
                    <td> Rows: </td>
                    <td> <input type="number"  v-model="firstClassCapacity_rows" required >  </td> 
                    <td> Columns: </td>
                    <td> <input type="number"  v-model="firstClassCapacity_columns" required >  </td>       
                </tr>
                <tr>
                    <td>  </td>
                    <td><button v-on:click="addFlight()">Add</button> </td>      
                </tr>
            </table>
            </div>      
        </div>
</template>

<script>

export default {
  name: 'addFlight',
  components: {
  },
  data: function () {
  return {
     startDestination: "",
     endDestination: "",
     startDate: "",
     endDate: "",
     flightDuration: "",
     flightLength: "",
     buisinesssPrice: "",
     firstClassPrice: "",
     economicPrice: "",
     economicCapacity_rows: "",
     economicCapacity_columns: "",
     buisinesssCapacity_rows: "",
     buisinesssCapacity_columns: "",
     firstClassCapacity_rows: "",
    firstClassCapacity_columns: "",
     destinations:[]
  }
},
mounted(){
     var getJwtToken = function() {
        return localStorage.getItem('jwtToken');
    };
    axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
    axios.get("http://localhost:8080/api/getDestinations")
        .then(response => {
            this.destinations = response.data
          }); 
    },
    methods:{
        addFlight: function(){
            if (this.startDestination == this.endDestination){
                alert("Start and end destinations can not be the same!");
                return
            }
            if( isNaN(this.buisinesssPrice) || isNaN(this.firstClassPrice) || isNaN(this.economicPrice)  ){
                alert("All prices must be numbers!");
                return
            }
            if( this.buisinesssPrice < 0 || this.economicPrice < 0 || this.firstClassPrice < 0 ){
                alert("Price can not have negative value!");
                return
            }
            if( isNaN(this.flightDuration) || isNaN(this.flightLength) || isNaN(this.economicPrice)  ){
                alert("Both, duration and length of flihts must be numbers!");
                return
            }
            if( this.flightDuration < 0 || this.flightLength < 0){
                alert("Both, duration and length of flihts can not have negative value!");
            }
            var economicCapacity = this.economicCapacity_rows + "|" + this.economicCapacity_columns;
            var buisinesssCapacity = this.buisinesssCapacity_rows + "|" + this.buisinesssCapacity_columns;
            var firstClassCapacity = this.firstClassCapacity_rows + "|" + this.firstClassCapacity_columns;
            alert(this.startDestination);
            var newFlight = {startDestination: this.startDestination.name, endDestination: this.endDestination.name,
                startDate: this.startDate, endDate: this.endDate, 
                flightDuration: this.flightDuration, flightLength: this.flightLength, 
                businessPrice: this.buisinesssPrice, economicPrice: this.economicPrice, firstClassPrice: this.firstClassPrice,
                economicCapacity: economicCapacity, buisinesssCapacity: buisinesssCapacity, firstClassCapacity: firstClassCapacity }
            var getJwtToken = function() {
                return localStorage.getItem('jwtToken');
            };
            axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
            axios.post("http://localhost:8080/api/addFlight", newFlight);
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
