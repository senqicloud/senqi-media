<script setup lang="ts">
const route = useRoute()

console.log(route.params.id)

</script>

<template>
  <a-button>{{ route.params.id}}</a-button>

</template>

<style scoped lang="less">

</style>