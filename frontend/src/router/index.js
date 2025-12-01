import { createRouter, createWebHistory } from 'vue-router'

import HomeView from '@/views/HomeView.vue'
/*import LoginView from '@/views/LoginView.vue'
import ProdutosView from '@/views/ProdutosView.vue'
import ProdutoDetalheView from '@/views/ProdutoDetalheView.vue'*/
import NotFoundView from '@/views/NotFoundView.vue'
import {HOME, NOT_FOUND} from "@/constants.js";   // opcional 404

const routes = [
    { path: '/',                  name: HOME,       component: HomeView },
    /*{ path: '/login',             name: 'login',      component: LoginView },
    { path: '/produtos',          name: 'produtos',   component: ProdutosView },
    {
        path: '/produtos/:id',
        name: 'produto-detalhe',
        component: ProdutoDetalheView,
        props: true                                   // passa o :id como prop
    },
    { path: '/admin',             name: 'admin',      component: () => import('@/views/AdminView.vue') }, // lazy load*/

    // 404 – tem que ficar sempre por último!
    { path: '/:pathMatch(.*)*',   name: NOT_FOUND,  component: NotFoundView }
]

const router = createRouter({
    history: createWebHistory(),      // usa URL limpa (sem #)
    routes,
    scrollBehavior(to, from, savedPosition) {
        return { top: 0 }               // sempre rola pro topo ao mudar de página
    }
})

export default router