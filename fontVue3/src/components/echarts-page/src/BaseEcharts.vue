<script setup lang="ts">
import { onMounted, ref } from 'vue';
import * as echarts from 'echarts';
import { de } from 'element-plus/es/locales.mjs';
import type { EChartsOption } from 'echarts';

const echartsRef = ref<HTMLElement | null>(null);


interface IProps  {
 option:EChartsOption
}
  const props = defineProps<IProps>()



onMounted(() => {
  if (!echartsRef.value) return;

  const myChart = echarts.init(echartsRef.value, 'light', { renderer: 'canvas' });

  myChart.setOption(props.option);

  // 可选：添加窗口大小变化时的响应式调整
  window.addEventListener('resize', () => {
    myChart.resize();
  });
});
</script>

<template>
  <div class="echarts-container">
    <div ref="echartsRef" class="echarts-base"></div>
  </div>
</template>

<style scoped lang="scss">
.echarts-container {
  width: 100%;
  height: 100%;
}

.echarts-base {
  width: 100%;
  height: 500px; /* 设置一个固定高度或使用视窗单位 */
}
</style>