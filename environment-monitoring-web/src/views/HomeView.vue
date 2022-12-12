<script setup lang="ts">
import TheWelcome from '../components/TheWelcome.vue'
// const ENV = import.meta.env
// export default {
//   name: 'HomeView',
//   setup() {
//     console.log(ENV)
//   }
// }
// 将上述代码转化为setup模式
const ENV = import.meta.env
console.log(ENV)
</script>

<template>
  <div>Index</div>
  <main>
    <TheWelcome />
  </main>
</template>


