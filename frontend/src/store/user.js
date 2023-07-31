// store/user.js
import { defineStore } from 'pinia'

export const useUserStore = defineStore({
    id: 'user',
    state: () => ({
        details: null
    }),
    actions: {
        setUserDetails(details) {
            this.details = details
        }
    }
})
