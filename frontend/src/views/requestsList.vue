<template>


    <table class="voucher-table">
        <thead>
            <tr>
                <th>VoucherID</th>
                <th>Influencer Name</th>
                <th>Supplier Name</th>
                <th>Product Name</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="voucher in vouchers" :key="voucher.VoucherID">
                <td>{{ voucher.VoucherID }}</td>
                <td>{{ voucher['Influencer Name'] }}</td>
                <td>{{ voucher['Supplier Name'] }}</td>
                <td>{{ voucher['Product Name'] }}</td>
                <td><router-link to="/reviewRequest">
        <button class="cta-button">Accept</button>
      </router-link></td>
            </tr>
        </tbody>
    </table>
</template>

<script>
export default {
    data() {
        return {
            vouchers: [
                {
                    'VoucherID': 'VCH12345',
                    'Influencer Name': 'Influencer A',
                    'Supplier Name': 'Supplier X',
                    'Product Name': 'Product 1'
                },
                {
                    'VoucherID': 'VCH67891',
                    'Influencer Name': 'Influencer B',
                    'Supplier Name': 'Supplier Y',
                    'Product Name': 'Product 2'
                }
                //... other voucher objects
            ]
        }
    },
    methods: {
        review(voucher) {
            // Place your review action here
            console.log(`Reviewing voucher with ID ${voucher.VoucherID}`);
        }
    }
}
</script>

<style scoped>
.requestsList {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: center;
  height: 100vh;
  padding: 2em;
  box-sizing: border-box;
  font-family: Arial, sans-serif;
  color: #333;
}

.requestsList h1 {
  font-size: 2.5em;
  margin-bottom: 1em;
}

 .cta-button {
  background-color: #3498db;
  color: white;
  border: none;
  border-radius: 5px;
  padding: 1em 2em;
  font-size: 1em;
  text-decoration: none;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.cta-button:hover {
  background-color: #2980b9;
}

.voucher-table {
    border-collapse: collapse;
    width: 100%;
}

.voucher-table th,
.voucher-table td {
    border: 1px solid #ccc;
    padding: 8px;
}

.voucher-table th {
    background-color: #f2f2f2;
}
</style>
