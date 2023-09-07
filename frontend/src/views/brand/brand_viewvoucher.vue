<template>
    <div>
      <v-expansion-panels variant="popout" class="my-4">
        <v-expansion-panel v-for="(voucher, index) in vouchers" :key="index">
          <template v-slot:title>
            <div class="panel-header">
              Voucher {{ index + 1 }}
              <button @click="toggleVoucherDetails(index)">
                {{ voucherDetailsVisible[index] ? 'Hide Details' : 'Show Details' }}
              </button>
            </div>
          </template>
          <div v-if="voucherDetailsVisible[index]">
            <div>
                <p>Influencer: {{ voucher.influencer.name }}</p>
            <p>Brand: {{ voucher.brand.name }}</p>
            <p>Supplier: {{ voucher.supplier.name }}</p>
            <p>Product: {{ voucher.product.name }}</p>
            <p>Created Date: {{ voucher.createdDate }}</p>
            <p>Expiry Date: {{ voucher.expiryDate }}</p>
            <p>Redeemed: {{ voucher.redeemed ? 'Yes' : 'No' }}</p>
            <p>Blockchain URL: {{ voucher.blockchainUrl }}</p>

            </div>
            
          </div>
        </v-expansion-panel>
      </v-expansion-panels>
    </div>
  </template>
  
  <script>
  import { ref } from 'vue';
  
  export default {
    setup() {
      const vouchers = ref([
      {
    influencer: { name: 'Influencer 1' },
    brand: { name: 'Brand 1' },
    supplier: { name: 'Supplier 1' },
    product: { name: 'Product 1' },
    createdDate: '2023-05-20',
    expiryDate: '2023-06-20',
    redeemed: false,
    blockchainUrl: 'https://example.com/blockchain-url-1',
  },
  {
    influencer: { name: 'Influencer 2' },
    brand: { name: 'Brand 2' },
    supplier: { name: 'Supplier 2' },
    product: { name: 'Product 2' },
    createdDate: '2023-05-15',
    expiryDate: '2023-06-15',
    redeemed: true,
    blockchainUrl: 'https://example.com/blockchain-url-2',
  },
      ]);
  
      const voucherDetailsVisible = ref(new Array(vouchers.value.length).fill(false));
  
      function toggleVoucherDetails(index) {
        voucherDetailsVisible[index] = !voucherDetailsVisible[index];
      }
  
      return {
        vouchers,
        toggleVoucherDetails,
        voucherDetailsVisible,
      };
    },
  };
  </script>
  
  <style scoped>
  .panel-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
}
  </style>
  
  