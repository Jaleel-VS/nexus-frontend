<template>
  <div class="main">
    <h1>Welcome, Jack</h1>
    <h2>What brand would you like to work with?</h2>
    <div class="brand-cards">
      <!-- Loop through and render 15 cards -->
      <v-card v-for="i in 6" :key="i" class="mx-auto brand-card" max-width="400">
        <v-img class="align-end text-white" height="200" src="https://cdn.vuetifyjs.com/images/cards/docks.jpg" cover>
        </v-img>
        <v-card-subtitle class="pt-4">
          Number 10
        </v-card-subtitle>
        <v-card-text>
          <div>Whitehaven Beach</div>
          <div>Whitsunday Island, Whitsunday Islands</div>
        </v-card-text>
        <v-card-actions>
          <v-btn color="orange">
            Select
          </v-btn>
        </v-card-actions>
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