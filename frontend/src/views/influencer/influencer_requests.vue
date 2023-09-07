<template>
  <div class="main">
    <h1>Welcome, Jack</h1>
    <h2 v-if="!selectedBrand">What brand would you like to work with?</h2>
    <transition name="fade">
    <div v-if="!selectedBrand" class="brand-cards">
      <v-card v-for="brand in brands" :key="brand.id" class="mx-auto brand-card" max-width="400">
        <v-img class="align-end text-white" height="200" :src="brand.image" cover>
        </v-img>
        <v-card-subtitle class="pt-4">
          <span class="headline">{{ brand.name }}</span>
        </v-card-subtitle>
        <v-card-text>
          <div>Whitehaven Beach</div>
          <div>Whitsunday Island, Whitsunday Islands</div>
        </v-card-text>
        <v-card-actions>
          <v-btn color="orange" @click="selectBrand(brand)">
            Select
          </v-btn>
        </v-card-actions>
      </v-card>
    </div>
    </transition>

    <!-- Display the selected card and undo button -->
    <transition name="fade">
    <div v-if="selectedBrand">
      <v-card class="mx-auto brand-card" max-width="400">
        <v-img class="align-end text-white" height="200" :src="selectedBrand.image" cover>
        </v-img>
        <v-card-subtitle class="pt-4">
          <span class="headline">{{ selectedBrand.name }}</span>
        </v-card-subtitle>
        <v-card-text>
          <div>Whitehaven Beach</div>
          <div>Whitsunday Island, Whitsunday Islands</div>
        </v-card-text>
        <v-card-actions>
          <v-btn color="red" @click="deselectBrand">
            Undo
          </v-btn>
        </v-card-actions>
      </v-card>

      <h2>What product would you like to promote?</h2>

      <div class="brand-cards">
        <v-card v-for="product in products" :key="product.id" class="mx-auto brand-card" max-width="400">
          <v-img class="align-end text-white" height="200" :src="product.image" cover>
          </v-img>
          <v-card-subtitle class="pt-4">
            <span class="headline">{{ product.name }}</span>
          </v-card-subtitle>
          <v-card-text>
            <div>Whitehaven Beach</div>
            <div>Whitsunday Island, Whitsunday Islands</div>
          </v-card-text>
          <v-card-actions>
            <v-btn color="orange" @click="selectBrand(product)">
              Select
            </v-btn>
          </v-card-actions>
        </v-card>
      </div>




    </div>
  </transition>
  </div>
</template>


<script setup>

import { ref } from 'vue'

const brands = ref([
  {
    name: 'Nike',
    image: 'https://wallpapers.com/images/hd/nike-galaxy-7by2ih0s8aop7uc0.jpg',
  },
  {
    name: 'Adidas',
    image: 'https://w0.peakpx.com/wallpaper/148/542/HD-wallpaper-adidas-adidas-galaxy.jpg',
  },
  {
    name: 'Puma',
    image: 'https://wallpaperaccess.com/full/1560138.jpg',
  },
  {
    name: 'Reebok',
    image: 'https://w0.peakpx.com/wallpaper/870/479/HD-wallpaper-reebok-logo-marca-rebook-sport.jpg',
  },
  {
    name: 'Under Armour',
    image: 'https://i.pinimg.com/550x/d1/44/7c/d1447c8316d301d058f17bd5e9f0c52d.jpg',
  },
  {
    name: 'New Balance',
    image: 'https://is.zobj.net/image-server/v1/images?r=Srwd4OKP-Ea61co7blpvETea5BFm_BP6q4_oCtlcj0FZA5RTp96uWQ6pWRrRhQ8ggx6dwtDyR9TVITMSanwtCCaYUm1adzcvGkFmHPn8As6DMcY_zdOhXnl1Ks9otS5ervT3HwWw1-yetd-boOewcS8UVaPuVlsE5HFWTVQMJuJXP3E4o-J1uRPL1aiwl_9S0Y1bt1ekR7oQKBcFPUhHHqjhfUZ8dSaOvDBHjk7qKltSXHhF4g9M77FM4fM',
  },
]


)

const products = ref([
{
    name: 'Nike',
    image: 'https://wallpapers.com/images/hd/nike-galaxy-7by2ih0s8aop7uc0.jpg',
  },
  {
    name: 'Adidas',
    image: 'https://w0.peakpx.com/wallpaper/148/542/HD-wallpaper-adidas-adidas-galaxy.jpg',
  },
  {
    name: 'Puma',
    image: 'https://wallpaperaccess.com/full/1560138.jpg',
  },
  {
    name: 'Reebok',
    image: 'https://w0.peakpx.com/wallpaper/870/479/HD-wallpaper-reebok-logo-marca-rebook-sport.jpg',
  },
  {
    name: 'Under Armour',
    image: 'https://i.pinimg.com/550x/d1/44/7c/d1447c8316d301d058f17bd5e9f0c52d.jpg',
  },
  {
    name: 'New Balance',
    image: 'https://is.zobj.net/image-server/v1/images?r=Srwd4OKP-Ea61co7blpvETea5BFm_BP6q4_oCtlcj0FZA5RTp96uWQ6pWRrRhQ8ggx6dwtDyR9TVITMSanwtCCaYUm1adzcvGkFmHPn8As6DMcY_zdOhXnl1Ks9otS5ervT3HwWw1-yetd-boOewcS8UVaPuVlsE5HFWTVQMJuJXP3E4o-J1uRPL1aiwl_9S0Y1bt1ekR7oQKBcFPUhHHqjhfUZ8dSaOvDBHjk7qKltSXHhF4g9M77FM4fM',
  },
])

const selectedBrand = ref(null);

function selectBrand(brand) {
  selectedBrand.value = brand;
}

function deselectBrand() {
  selectedBrand.value = null;
}


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
  width: 67%;
}

// add light shadow to cards
.brand-card {
  box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.2);
}

// glow on hover
.brand-card:hover {
  box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.4);
}

.fade-enter-active, .fade-leave-active {
  transition: opacity 0.25s;
}
.fade-enter, .fade-leave-to {
  opacity: 0;
}
</style>