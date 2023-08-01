// store/metamask.js
import { defineStore } from 'pinia'

export const useMetamaskStore = defineStore({
    id: 'metamask',
    state: () => ({
        details: null
    }),
    actions: {
        setMetamaskDetails(details) {
            this.details = details
        }
    }
})