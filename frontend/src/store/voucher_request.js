import { defineStore } from 'pinia'

export const voucherRequestStore = defineStore({
    id: 'voucherRequest',
    state: () => ({
        details: null
    }),
    actions: {
        setVoucherRequestDetails(details) {
            this.details = details
        },
        clearVoucherRequestDetails() {
            this.details = null
        }
    }
})
