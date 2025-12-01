import type { Router } from 'vue-router'

let routerInstance: Router | null = null

// Esta função será chamada uma única vez no main.ts
export const setRouter = (router: Router) => {
    routerInstance = router
}

// Esta é a função que você vai usar no api.ts
export const navigateTo = (path: string) => {
    if (routerInstance) {
        routerInstance.push(path)
    } else {
        // fallback caso ainda não tenha sido setado (nunca deve acontecer)
        window.location.href = path
    }
}