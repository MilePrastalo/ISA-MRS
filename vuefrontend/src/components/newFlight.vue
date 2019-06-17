<template>
   <div id = "addFlight">

        
        <div class = "newFlightContainer">
            <br><br>
            <h2>New Flight: </h2>
            <br>
            <form @submit="addFlight">
                <table class = "centered" style="text-align: left">
                    <th  colspan="2" style="text-align: center">Basic Info</th>
                    <tr>
                        <td> Start destination: </td>
                        <td><select v-model="startDestination" name="startDestination" id="startDestination" required>
                            <option v-for="startDestination in destinations" v-bind:value= startDestination :key="startDestination.id">{{startDestination.name}}</option>
                        </select></td>
                    </tr>
                    <tr>
                        <td> End destination: </td>
                        <td><select v-model="endDestination" name="endDestination" id="endDestination"  required>
                            <option v-for="endDestination in destinations" v-bind:value=endDestination :key="endDestination.id">{{endDestination.name}}</option>
                        </select></td>
                    </tr>
                    <tr>
                        <td> Start date: </td>
                        <td>  <input type="date" name="startDate" v-model="startDate"  required> </td>
                        <td>  <input type="time" name="time_startDate" v-model="startDate_time"  placeholder="hh:mm"  required> </td>
                    </tr>
                    <tr>
                        <td> End date: </td>
                        <td>  <input type="date" name="endDate" v-model="endDate" required > </td>
                        <td>  <input type="time" name="time_endDate" v-model="endDate_time"  placeholder="hh:mm" required> </td>
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
                        <td> Transitions: </td>
                        <td>  
                            <select   v-model="oneTransition"  data-live-search="true" >
                                <option v-for="dest in this.destinations" :key=dest.id >{{dest.name}}</option>
                            </select> 
                        </td> 
                        <td><button @click="addTransition">Add</button></td>      
                    </tr>
                    <tr>
                        <td></td>
                        <td> <input  type="text" name="transitions"  v-model="transitions"> </td>   
                    </tr>
                    <tr>
                        <td> Additional services: </td>
                        <td> <input type="text"  v-model="additionalServices" > </td>        
                    </tr>
                    <th colspan="2" style="text-align: center">Price</th>                
                    <tr>
                        <td> Economic class: </td>
                        <td> <input type="text" name="economicPrice" v-model="economicPrice" required > </td>        
                    </tr>
                    <tr>
                        <td> Buisiness class: </td>
                        <td> <input type="text" name="buisinesssPrice" v-model="buisinesssPrice" required >  </td>        
                    </tr>
                    <tr>
                        <td> First class: </td>
                        <td> <input type="text" name="firstClassPrice" v-model="firstClassPrice" required>  </td>        
                    </tr>
                    <th colspan="2" style="text-align: center">Capacity</th>
                    <tr>
                        <td> Economic class:</td>
                        <td> <input type="number"  v-model="economicCapacity_rows" required  placeholder="Rows" >  </td>
                        <td> <input type="number"  v-model="economicCapacity_columns" required placeholder="Columns" >  </td>       
                    </tr>
                    <tr>
                        <td> Buisiness class:</td>
                        <td> <input type="number"  v-model="buisinesssCapacity_rows" required  placeholder="Rows" >  </td>
                        <td> <input type="number"  v-model="buisinesssCapacity_columns" required placeholder="Columns" >  </td>       
                    </tr>
                    <tr>
                        <td> First class:</td>
                        <td> <input type="number"  v-model="firstClassCapacity_rows" required  placeholder="Rows" >  </td>
                        <td> <input type="number"  v-model="firstClassCapacity_columns" required placeholder="Columns" >  </td>       
                    </tr>
                    <th  colspan="2" style="text-align: center"></th>
                    <br>
                    <tr>
                        <td></td>
                        <td><input class="btn btn-primary" type="submit" value = "Add flight" > </td>      
                    </tr>
                </table>
            </form>

        </div>      
    </div>
</template>

<script>

export default {
  name: "addFlight",
  components: {
  },
  data: function () {
  return {
     startDestination: "",
     endDestination: "",
     startDate: "",
     startDate_time: "",
     endDate: "",
     endDate_time: "",
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
    destinations:[],
    transitions: "",
    oneTransition: "",
    additionalServices: "",
  }
},
mounted(){
     var getJwtToken = function() {
        return localStorage.getItem("jwtToken");
    };
    axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
    axios.get("http://localhost:8080/api/getDestinations")
        .then(response => {
            this.destinations = response.data
          }); 
    },
    methods:{
        addFlight: function(e){
            
            e.preventDefault()
            if (this.checkTransitions() == false){
                return
            }
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

            var startDateTime = this.startDate + " " + this.startDate_time;
            var endDateTime = this.endDate +  " " + this.endDate_time;
            var newFlight = {startDestination: this.startDestination.id, endDestination: this.endDestination.id,
                startDate_str: startDateTime, endDate_str: endDateTime, 
                flightDuration: this.flightDuration, flightLength: this.flightLength, 
                businessPrice: this.buisinesssPrice, economicPrice: this.economicPrice, firstClassPrice: this.firstClassPrice,
                economicRows: this.economicCapacity_rows, economicColumns: this.economicCapacity_columns, 
                businessRows:this.buisinesssCapacity_rows, businessColumns: this.buisinesssCapacity_columns,
                firstClassRows: this.firstClassCapacity_rows, firstClassColumns: this.firstClassCapacity_columns,
                additionalServices: this.additionalServices, transitions: this.transitions }
            var getJwtToken = function() {
                return localStorage.getItem("jwtToken");
            };
            axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
            axios.post("http://localhost:8080/api/addFlight", newFlight)
            .then(response => {
                alert(response.data);
            });; 
        },
        
        addTransition: function(e){
            e.preventDefault();
            if (this.transitions == "0" || this.transitions == ""){
                this.transitions = this.oneTransition;
                this.oneTransition = "";
                return;
            }
            this.transitions = this.transitions + ","+ this.oneTransition;
            this.oneTransition = "";
        },
        checkTransitions: function(){
            if (this.transitions == "0" || this.transitions == ""){
                this.transitions = "0"
                return true;
            }addFlight
            var list;
            try{
                
                list = this.transitions.split(",");
            }
            catch{
                alert("Transitions are not valid!");
                return false;
            }
            var idx1, idx2;
                var found = false;
                for (idx1 in list){
                    found = false;
                    for (idx2 in this.destinations){
                        if (this.destinations[idx2].name == list[idx1] && this.startDestination.name != list[idx1] && this.endDestination.name != list[idx1]){
                            found = true;
                            break;
                        }
                    }
                    if (found == false){
                        alert("Transitions are not valid!");
                        return false;
                    }
                }
                return true;
            
            
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

.newFlightContainer{
    margin-left: auto;
    margin-right: auto;
    width: 1100px;
}
</style>
