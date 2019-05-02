<template>
   <div id = "flightReservation">

        <div v-if="currentStep == 1">
            <button @click="goBack(1)"> << Back </button>&nbsp
            <button @click="goToNextStep(2)"> Next >> </button>
            <seat_selection :iid= "id"></seat_selection>
        </div>

        <div v-if="currentStep == 2">
            <button @click="goBack(2)"> << Back </button> &nbsp
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
            id : 0
            }
    },
    created:function(){

        this.id = localStorage.getItem("flightID");
    },
    mounted(){
        
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

        goBack: function(current){
            if (current == 1){
                window.location = '/flight'
            }
            else if (current == 2){
                this.currentStep = 1;
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
