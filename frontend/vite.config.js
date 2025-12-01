// frontend/vite.config.js
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import path from 'node:path'

export default defineConfig({
    plugins: [vue()],
    resolve: {
        alias: {
            '@': path.resolve(__dirname, './src'),
        }
    },
    build: {
        outDir: 'dist',                   // padrão, não precisa mexer
        assetsDir: 'assets',              // padrão
        rollupOptions: {
            output: {
                // opcional: nomes mais bonitos em produção (mas perde cache longo)
                // chunkFileNames: 'assets/js/[name].js',
                // entryFileNames: 'assets/js/[name].js',
                // assetFileNames: 'assets/[ext]/[name].[ext]'
            }
        }
    },
    server: {
        port: 3002,
        proxy: {
            '/api': {
                target: 'http://localhost:3001',
                changeOrigin: true,
                // não reescreve o caminho (mantém /api)
            },
            '/ws': {  // se usares WebSocket/STOMP
                target: 'ws://localhost:3001',
                ws: true,
            }
        }
    }
})