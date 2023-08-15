import { defineStore } from 'pinia'

export const useVoucherRequestStore = defineStore({
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
