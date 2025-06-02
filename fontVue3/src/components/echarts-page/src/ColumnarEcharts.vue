<script setup lang="ts">
import { ref } from 'vue';
import BaseEcharts from './BaseEcharts.vue';
import { option1 } from './options';
const map : any = ref({})
// if(map.value.title){
//   option1.title = map.value.title
// }
// if(map.value.yAxis){
//   option1.yAxis = map.value.yAxis
// }


</script>


<template>
  <div class="echarts-page">
    <BaseEcharts :option="option1" />
  </div>
</template>
<style scoped lang="scss">
.echarts-page{
  width: 100%;
  left: 0;
}
</style>
