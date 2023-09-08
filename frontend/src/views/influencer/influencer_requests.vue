<template>
  <div class="main">
    <h1>Welcome, Chris</h1>
    <h2 v-if="!selectedBrand">What brand would you like to work with?</h2>
    <transition name="fade">
    <div v-if="!selectedBrand" class="brand-cards">
      <v-card v-for="brand in brands" class="mx-auto brand-card" min-width="200">
        <v-img class="align-end text-white" height="200" :src="brand.image" cover>
        </v-img>
        <v-card-subtitle class="pt-4">
          <span class="headline">{{ brand.name }}</span>
        </v-card-subtitle>
<!--        <v-card-text>-->
<!--          <div>Whitehaven Beach</div>-->
<!--          <div>Whitsunday Island, Whitsunday Islands</div>-->
<!--        </v-card-text>-->
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
      <v-card class="mx-auto brand-card" min-width="200">
        <v-img class="align-end text-white" height="200" :src="selectedBrand.image" cover>
        </v-img>
        <v-card-subtitle class="pt-4">
          <span class="headline">{{ selectedBrand.name }}</span>
        </v-card-subtitle>
<!--        <v-card-text>-->
<!--          <div>Whitehaven Beach</div>-->
<!--          <div>Whitsunday Island, Whitsunday Islands</div>-->
<!--        </v-card-text>-->
        <v-card-actions>
          <v-btn color="red" @click="deselectBrand">
            Undo
          </v-btn>
        </v-card-actions>
      </v-card>
    </div>
  </transition>

    <h2 v-if="selectedBrand">What product would you like to promote?</h2>

    <div v-if="selectedBrand && !selectedProduct" class="product-cards">
      <v-card v-for="product in products[selectedBrand.name]" :key="product.id" class="mx-auto product-card" min-width="250">
        <v-img class="align-end text-white" height="200" :src="product.image" cover="">
        </v-img>
        <v-card-subtitle class="pt-4">
          <span class="headline">{{ product.name }}</span>
        </v-card-subtitle>
<!--        <v-card-text>-->
<!--          <div>Whitehaven Beach</div>-->
<!--          <div>Whitsunday Island, Whitsunday Islands</div>-->
<!--        </v-card-text>-->
        <v-card-actions>
          <v-btn color="orange" @click="selectProduct(product)">
            Select
          </v-btn>
        </v-card-actions>
      </v-card>
    </div>


  <transition name="fade">
    <div v-if="selectedProduct">
      <v-card class="mx-auto product-card" min-width="200">
        <v-img class="align-end text-white" height="200" :src="selectedProduct.image" cover>
        </v-img>
        <v-card-subtitle class="pt-4">
          <span class="headline">{{ selectedProduct.name }}</span>
        </v-card-subtitle>
<!--        <v-card-text>-->
<!--          <div>Whitehaven Beach</div>-->
<!--          <div>Whitsunday Island, Whitsunday Islands</div>-->
<!--        </v-card-text>-->
        <v-card-actions>
          <v-btn color="red" @click="deselectProduct">
            Undo
          </v-btn>
        </v-card-actions>
      </v-card>
    </div>
  </transition>

<!--    display request button if selected producted and selected brand-->

    <div v-if="selectedProduct && selectedBrand">
      <v-btn color="orange" @click="deselectProduct" class="request-btn">
        Request
      </v-btn>
    </div>

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
]


)

// products should be a list of brands and each brand contains a list of products

const products = ref(
  {
    'Nike': [
      {
        name: 'Nike Air Max 90',
        image: 'https://static.nike.com/a/images/t_PDP_1280_v1/f_auto,q_auto:eco/99486859-0ff3-46b4-949b-2d16af2ad421/custom-nike-dunk-high-by-you-shoes.png',
      },
      {
        name: 'Nike Air Max 95',
        image: 'https://static.nike.com/a/images/t_PDP_1280_v1/f_auto,q_auto:eco/2eff461f-f3ac-4285-9c6a-2f22173aac42/custom-nike-air-force-1-low-by-you.png',
      },
      {
        name: 'Nike Air Max 97',
        image: 'https://cdna.lystit.com/520/650/n/photos/footpatrol/22ec2730/nike-Pink-Air-Zoom-Tempo-Next.jpeg',
      }
    ],
  'Adidas': [
      {
        name: 'Adidas Superstar',
        image: 'https://www.adidas.co.za/dw/image/v2/BFNL_PRD/on/demandware.static/-/Library-Sites-AdidasSharedLibrary/en_ZA/dw5e84a441/LocalActivations/za-running-fw23-switchfwd-launch-hp-tc.jpg',
      },
      {
        name: 'Adidas Stan Smith',
        image: 'https://www.urbanathletics.com.ph/cdn/shop/products/adidaswhitebrownshoes.jpg?v=1659939665',
      },
      {
        name: 'Adidas Yeezy',
        image: 'https://www.kershkicks.co.uk/cdn/shop/products/62b7205b4ec942a0406c9d0a0198c14185123222.jpg?v=1613748315',
      }
    ],
    Puma:[
      {
        name: 'Puma Suede',
        image: 'https://images.puma.com/image/upload/f_auto,q_auto,b_rgb:fafafa/global/393325/01/sv01/fnd/ZAF/w/1000/h/1000/fmt/png'
      },
      {
        name: 'Puma Suede Classic',
        image: 'https://ir.ozone.ru/s3/multimedia-d/c1000/6618197737.jpg',
      },
      {
        name: 'Puma Suede Classic XXI',
        image: 'https://www.soletrader.co.uk/cdn-cgi/image/width=700,height=700,fit=contain/assets/4d/49/4d490dc2-aa59-4301-9ae1-1af1c3c2f431/SUEDEMBK_AV1.jpg',
      }
    ]
  }
)

const selectedBrand = ref(null);

const selectedProduct = ref(null);

function selectBrand(brand) {
  selectedBrand.value = brand;
}

function deselectBrand() {
  selectedBrand.value = null;
}

function selectProduct(product) {
  selectedProduct.value = product;
  console.log(selectedProduct.value)
}

function deselectProduct() {
  selectedProduct.value = null;
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

.product-cards {
  display: flex;
  flex-direction: row;
  // align-items: center;
  // justify-content: center;
  align-items: baseline;
  justify-content: flex-start;
  gap: 1rem;
  flex-wrap: wrap;
  width: 67%;
  margin-bottom: 20px;
}

.product-card {
  box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.2);
}

// glow on hover
.brand-card:hover {
  box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.4);
}

.product-card:hover {
  box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.4);
}

.request-btn {
  margin-top: 20px;
}

.fade-enter-active, .fade-leave-active {
  transition: opacity 0.25s;
}
.fade-enter, .fade-leave-to {
  opacity: 0;
}
</style>