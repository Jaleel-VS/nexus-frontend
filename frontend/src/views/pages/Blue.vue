<template>
  <div class="blue">
    <h1>Blue</h1>
    <div class="voucher-grid">
      <!-- Use v-for to render multiple VoucherCard components -->
      
      <div v-for="voucher in voucherData">
        <VoucherCard :voucherdetails="voucher"   />
      </div>
    </div>
  </div>
</template>

<script setup>
import VoucherCard from '../components/VoucherCard.vue';

import { ref } from 'vue';

const voucherData = ref([
  {
    supplierId: 'Supplier 1',
    product: 'Product 1',
    expiryDate: '2023-12-31',
    amount: '$50',
  },
  {
    supplierId: 'Supplier 2',
    product: 'Product 2',
    expiryDate: '2023-11-30',
    amount: '$30',
  },
  {
    supplierId: 'Supplier 3',
    product: 'Product 3',
    expiryDate: '2023-11-30',
    amount: '$30',
  },
  {
    supplierId: 'Supplier 4',
    product: 'Product 4',
    expiryDate: '2023-11-30',
    amount: '$30',
  },
  // Add more voucher data as needed
]);
</script>

<style scoped lang="scss">
.blue {
  background-color: blue;
}

/* Style for the voucher grid */
.voucher-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr); /* Adjust the number of columns as needed */
  gap: 15px; /* Adjust the gap between vouchers */
  padding: 15px;
}
</style>
