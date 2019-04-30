<template>
   <div id = "flightReservation">

        <div v-if="currentStep == 1">
            <button @click="goToNextStep(2)"> Next >> </button>
            <seat_selection></seat_selection>
        </div>

        <div v-if="currentStep == 2">
             <button> Next >> </button>
            <calledFriends></calledFriends>
        </div>		
         
    </div>
</template>

<script>
import SeatSelection from './seatsSelection.vue';
import CalledFriends from './callFriends.vue';

export default {
    name: 'flightReservation',
    components: {

        seat_selection: SeatSelection,
        calledFriends: CalledFriends
    },
    data: function () {
        return {

            currentStep: 1,
            selected: [],
            total: 0,
            calledFriends: [],
            }
    },
    mounted(){

        var flightID = 6;
        localStorage.setItem("selected_seats",[]);
        localStorage.setItem("called_friends",[]);
    }, 
    methods: {

        goToNextStep: function(option){
            if (option == 2){
                this.selected = localStorage.getItem('selected_seats');
                if (this.selected.length == 0  ){
                    alert("You must choose at least one seat!");
                }
                else{
                    this.currentStep = option;
                }
            }
        },
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
  margin: 5%
}
</style>
