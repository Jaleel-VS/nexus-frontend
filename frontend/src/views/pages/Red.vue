<!-- Vue 3 component with red background -->

<template>
  <div class="main">
    <h1>Welcome, Admin</h1>
    <h2>Please review influencer requests</h2>
    <div class="brand-cards">
      <!-- Loop through and render 6 cards -->
      <v-card v-for="i in 6" :key="i" class="mx-auto brand-card" max-width="400">
        <v-img class="align-end text-white" height="200" src="https://cdn.vuetifyjs.com/images/cards/docks.jpg" cover>
        </v-img>
        <v-card-subtitle class="pt-4">
          Selena Gomez
        </v-card-subtitle>
        <v-card-text>
          <div>SelenaGs09</div>
          <!-- Use router-link to navigate to another page -->
          <router-link :to="{ name: 'popUp' }">
            <v-btn color="orange">
              Select
            </v-btn>
          </router-link>
        </v-card-text>
      </v-card>
    </div>
  </div>
</template>



<script setup>
</script>

<style scoped lang="scss">
.main {
  display: flex;
  flex-direction: column;
  align-items: center;
}

h1 {
  font-size: 3rem;
  margin: 2rem 0;
}

h2 {
  font-size: 2rem;
  margin: 1rem 0;
}

.brand-cards {
  display: flex;
  flex-direction: row;
  // align-items: center;
  // justify-content: center;
  align-items: baseline;
  justify-content: flex-start;
  gap: 1rem;
  flex-wrap: wrap;
  width: 50%;
}

// add light shadow to cards
.brand-card {
  box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.2);
}

// glow on hover
.brand-card:hover {
  box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.4);
}
</style>