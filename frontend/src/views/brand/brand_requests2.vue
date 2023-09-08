<template>
      <div class="main">
    <h1>Welcome, Tony</h1>
    <h2>Review voucher requests</h2>
    
    <div class="request-cards">
        <v-card v-for="request in requests" class="mx-auto request-card" min-width="200">
        <v-img class="align-end text-white" height="200" :src="request.image" cover>
        </v-img>
        <v-card-subtitle class="pt-4">
          <span class="headline">{{ request.name }}</span>
        </v-card-subtitle>
       <v-card-text>
         <div> Product: {{ request.product }}</div>
         <div> Date Requested: {{ request.date }}</div>
         <!-- <div> {{  }}</div> -->
       </v-card-text>
        <v-card-actions>
          <v-btn color="orange">
            Review  
            </v-btn>
            </v-card-actions>
    
    </v-card>

    


    </div>
    
      </div>

</template>
<script setup>

import { ref } from 'vue'

const requests = ref([
    {
        name: 'Tony',
        image: 'https://images.unsplash.com/photo-1625698457101-fec2f565a8f0?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1964&q=80',
        product: 'Nike Airmax 90s',
        date: '2021-05-01'
    },
    {
        name: 'Jane',
        image: 'https://images.unsplash.com/photo-1562572159-4efc207f5aff?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1935&q=80',
        product: 'Nike Lebron 18s',
        date: '2021-05-01'
    },
    {
        name: 'Ben',
        image: 'https://images.unsplash.com/photo-1601896258776-e5675f9ef3c3?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1887&q=80',
        product: 'Nike Dunk Lows',
        date: '2021-05-01'
    }
])

</script>

<style scoped lang="scss">

@import url('https://fonts.googleapis.com/css2?family=Archivo:wght@100;400&family=DM+Serif+Display&family=Trocchi&family=Vesper+Libre&display=swap');

.main {
  display: flex;
  flex-direction: column;
  align-items: center;
}

h1 {
  font-size: 3rem;
  margin: 2rem 0;
//  use Trocchi
  font-family: 'Trocchi', serif;
}

h2 {
  font-size: 2rem;
  margin: 1rem 0;
  // Trocchi
  font-family: 'Trocchi', serif;
}

.request-cards {
  display: flex;
  flex-direction: row;
  // align-items: center;
  // justify-content: center;
  align-items: baseline;
  justify-content: flex-start;
  gap: 1rem;
  flex-wrap: wrap;
  width: 67%;
}

.request-card {
  box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.2);
}

</style>