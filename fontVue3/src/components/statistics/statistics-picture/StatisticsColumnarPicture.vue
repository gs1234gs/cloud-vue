<!-- src/App.vue 或其他组件 -->
<script setup lang="ts">
import BarEchart from '@/components/echarts-page/src/BarEchart.vue';
</script>

<template>
  <div class="app-container">
   <BarEchart />
  </div>
</template>

<style>
.app-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}
</style>