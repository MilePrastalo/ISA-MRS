<template>
  <div id="searchRentACarCompany">
    <div id="inputfields">
        <h2>Search Rent a Car companies</h2>
        <table>
            <tr>
                <td>Rent a company name:</td>
                <td><input v-model="name" type="text"></td>
                <td>Location</td>
                <td><input v-model="location" type="text"></td>
            </tr>
            <tr>
                <td>Date from</td>
                <td><input v-model="datefrom" type="date"></td>
                <td>Date to</td>
                <td><input v-model="dateTo" type="date"></td>
                <td><Button v-on:click="search">Search</Button></td>
            </tr>
        </table>
    </div>
    <div>
        <table id="DataTable" >
            <tr>
                <th>Name</th>
                <th>Offices</th>
                <th>Rating</th>
            </tr>
            <tr v-for="company in companies" :key="company.name">  
                <td>{{company.name}}</td>
                <td><table>
                    <tr><th>Office name</th>
                    <th>Office address</th>
                    <th>Office destination</th></tr>
                    <tr v-for="office in company.offices" :key="office">
                        <td>{{office.name}}</td>
                        <td>{{office.address}}</td>
                        <td>{{office.destination}}</td>
                    </tr>
                    </table></td>
                <td>{{company.rating}}</td>
                <td><Button @click="select(company)">Select</Button></td>
            </tr>
        </table>
    </div>
  </div>
</template>

<script>

export default {
  name: 'searchRentACarCompany',
  components: {
  },
  data: function () {
  return {
    companies:[],
    name:"",
    location:"",
    datefrom:"",
    dateTo:""
  }
},
mounted(){
    
    },
    methods:{
           search:function(){
               axios.post("http://localhost:8080/api/getRentACarCompanies",{name : this.name, location: this.location, dateFrom:this.datefrom, dateTo:this.dateTo})
                .then(response => {
                    console.log(response.data);
                    this.companies = response.data
                }); 
           },
           select:function(company){
               var o = {};
               o.id = company.id;
               o.dateFrom = this.datefrom;
               o.dateTo = this.dateTo;
               o.offices = company.offices
               this.$emit('selected',o);
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
#DataTable{
    border: 1px solid black;
    margin:auto;
    margin-top: 2%;
}
#DataTable td{
    border:1px solid black;
}
#DataTable th{
    border:1px solid black;
}
h2{
    margin: auto;
}
#inputfields table{
    margin-left: auto;
    margin-right: auto;
    margin-top: 2%;
}
</style>
