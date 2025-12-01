import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import { setRouter } from './router/navigation.ts'   // ← esta linha
import router from './router'

createApp(App)
    .use(router)
    .mount('#app')

setRouter(router)