<template>
    <main>
<h1>Vue popups</h1>
<button @click="( ) => TogglePopups('buttonTrigger')"> Open popup</button>

<Popups v-if="popupsTrigger.buttonTrigger" 
:TogglePopup="() => TogglePopups('buttonTrigger')">
    <h2> My button popups</h2>
</Popups>
<Popups v-if="popupsTrigger.timedTrigger" 
:TogglePopup="() => TogglePopups('timedtTrigger')">

    <h2> My timed popups</h2>
    <p> helloooo</p>
</Popups>
    </main>
</template>

<script> 
import {ref} from 'vue';
import Popups from './Popups.vue';
export default {
    setup(){
        const popupsTrigger=ref({ 
            buttonTrigger:false,

        timedTrigger:false
    });

    const TogglePopups = (trigger) => {
    popupsTrigger.value[trigger] = !popupsTrigger.value
    [trigger]

    }

    setTimeout(() => {
        popupsTrigger.value.timedTrigger = true;

    },3000);
        return{
            Popups, 
            popupsTrigger,
            TogglePopups
        }
       
    }
}
</script>

<style lang="scss">
//  #app{
//     font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
//     text-align: center;
//     color: aqua;
//     margin-top: 60px;
// }

</style>