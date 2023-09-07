<template>
    <div>
      <v-expansion-panels variant="popout" class="my-4">
        <v-expansion-panel v-for="(voucher, index) in vouchers" :key="index" :title="`Voucher ${index + 1}`">
          <div>
            
            <p>Influencer: {{ voucher.influencer.name }}</p>
            <p>Brand: {{ voucher.brand.name }}</p>
            <p>Supplier: {{ voucher.supplier.name }}</p>
            <button @click="toggleVoucherDetails(index)">Click here to see Voucher details</button>
            <div v-if="voucherDetailsVisible[index]">
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


  
  <style scoped lang="scss">

.buttons {
    display: flex;
    gap: 20px;
}

.button {
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 1em;
    transition: background-color 0.3s;
}

  </style>