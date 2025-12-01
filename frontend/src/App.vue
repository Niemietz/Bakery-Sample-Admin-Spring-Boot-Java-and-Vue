<script setup>
import { onMounted } from 'vue'

import HelloWorld from './components/HelloWorld.vue'
import Head from './components/Head.vue'
import Footer from "./components/Footer.vue";

onMounted(async () => {
  document.querySelectorAll(".fade-in").forEach((el, idx) => {
    el.style.animationDelay = `${0.12 * idx}s`;
  })
})
</script>

<template>
  <!DOCTYPE html>
  <html>
  <Head />
  <body class="brown lighten-5">

  <nav class="brown lighten-1">
    <div class="nav-wrapper container">
      <RouterLink to="/" class="brand-logo">Your Bakery Admin</RouterLink>
      <ul class="right hide-on-med-and-down">
        <li><RouterLink to="/site">View Site</RouterLink></li>
      </ul>
    </div>
  </nav>

  <!-- Aqui é onde a página atual é renderizada -->
  <RouterView />

  <!-- <HelloWorld msg="Vite + Vue" /> -->
  <Footer />
  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>
</template>

<style scoped>

</style>
