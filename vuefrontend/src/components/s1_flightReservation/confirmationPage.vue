<template>
   <div id = "confirmationPage">
        
        <h2>Reservation request</h2>
        <table style="text-align: left">
            <tr>
                <td>Request from: </td>
            </tr>
             <tr>
                <td>From: </td>
            </tr>
             <tr>
                <td>To: </td>
            </tr>
             <tr>
                <td>Start date: </td>
            </tr>
            <tr>
                <td>Seat: </td>
            </tr>
            <tr>
                <td>Price: </td>
            </tr>
        </table>

        <button>Confirm</button>
        <button>Reject</button>
         
    </div>
</template>

<script>


export default {
    name: 'confirmationPage',
    components: {
    },
    data: function () {
        return {
            }
    },
    mounted(){
    }, 
    methods: {

} 

}    
</script>

<style>
#confirmationPage{
    background-color: mediumspringgreen;
    width:50%;
    height: 50%;
}
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin: 5%
}
</style>
