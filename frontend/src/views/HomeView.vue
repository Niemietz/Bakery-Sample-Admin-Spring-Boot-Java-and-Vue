<script setup>
import { navigateTo } from '@/router/navigation.ts'
import { onMounted, ref } from 'vue'
import api from '@/services/api'
import Swal from 'sweetalert2'

const data = ref(null)

onMounted(async () => {
  await api.get('/data').then(response => {
    console.log("API Response", response.data)

    data.value = response.data
  }).catch(error => {
    // Só entra aqui se quiser tratar algo específico além do toast global
    if (error.response?.status === 404) {
      // ex: redirecionar para lista
      // navigateTo('/produtos')
    }
  })

  const urlParams = new URLSearchParams(window.location.search);
  if (urlParams.get('success') === 'true') {
    Swal.fire({
      title: '✅ Changes Saved!',
      text: 'The new content has been successfully updated.',
      icon: 'success',
      confirmButtonColor: '#795548',
      confirmButtonText: 'OK'
    }).then(() => {
      // Remove the query param from URL after showing alert
      window.history.replaceState({}, document.title, window.location.pathname);
    });
  } else if (urlParams.get('error') === 'true') {
    Swal.fire({
      title: '⚠️ Error!',
      text: 'There was a problem saving the data. Please try again.',
      icon: 'error',
      confirmButtonColor: '#795548'
    });
  }
})
</script>

<template>
  <div v-if="data">
    <!-- <RouterLink :to="{ name: '' }">← Voltar</RouterLink> -->
    <div class="container" style="margin-top: 40px;">
      <h4 class="center-align brown-text text-darken-2">Edit Site Content</h4>
      <form method="POST" action="/api" class="card-panel white z-depth-2">

        <h5 class="brown-text text-darken-2">Hero Section</h5>
        <div class="input-field">
          <input id="heroTitle" type="text" name="heroTitle" :value="data.hero.title" required>
          <label for="heroTitle" class="active">Title</label>
        </div>

        <div class="input-field">
          <textarea id="heroSubtitle" class="materialize-textarea" name="heroSubtitle" required>{{ data.hero.subtitle }}</textarea>
          <label for="heroSubtitle" class="active">Subtitle</label>
        </div>

        <div class="input-field">
          <input id="heroCTA" type="text" name="heroCTA" :value="data.hero.ctaText" required>
          <label for="heroCTA" class="active">Button Text</label>
        </div>

        <div class="divider" style="margin: 30px 0;"></div>

        <h5 class="brown-text text-darken-2">Why Choose Us Section</h5>
        <div class="input-field">
          <input id="whyChooseUsTitle" type="text" name="whyChooseUsTitle" :value="data.sections.whyChooseUs.title" required>
          <label for="whyChooseUsTitle" class="active">Title</label>
        </div>

        <div class="input-field">
          <input id="whyChooseUsDescription" type="text" name="whyChooseUsDescription" :value="data.sections.whyChooseUs.description" required>
          <label for="whyChooseUsDescription" class="active">Description</label>
        </div>

        <div class="divider" style="margin: 30px 0;"></div>

        <h5 class="brown-text text-darken-2">Footer Section</h5>
        <div class="input-field">
          <input id="visitUs" type="text" name="visitUs" :value="data.footer.visitUs" required>
          <label for="visitUs" class="active">Title</label>
        </div>

        <div class="input-field">
          <input id="visitUsDescription" type="text" name="visitUsDescription" :value="data.footer.visitUsDescription" required>
          <label for="visitUsDescription" class="active">Description</label>
        </div>

        <div class="input-field">
          <input id="visitUsCTA" type="text" name="visitUsCTA" :value="data.footer.visitUsCta_text" required>
          <label for="visitUsCTA" class="active">Button Text</label>
        </div>

        <div class="input-field">
          <input id="address" type="text" name="address" :value="data.footer.address" required>
          <label for="address" class="active">Address</label>
        </div>

        <div class="input-field">
          <input id="phone" type="text" name="phone" :value="data.footer.phone" required>
          <label for="phone" class="active">Phone</label>
        </div>

        <div class="center-align" style="margin-top: 30px;">
          <button type="submit" class="btn-large waves-effect waves-light brown lighten-1">
            <i class="material-icons left">save</i> Save Changes
          </button>
        </div>
      </form>
    </div>
  </div>
  <div v-else>Loading…</div>
</template>

<style scoped>

</style>