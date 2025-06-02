<script setup lang="ts">
import { onMounted, onUnmounted, ref } from 'vue';
import * as echarts from 'echarts';

const pieChart = ref<HTMLElement | null>(null);
const lineChart = ref<HTMLElement | null>(null);

let pieInstance: echarts.ECharts | null = null;
let lineInstance: echarts.ECharts | null = null;

// 硬编码的示例数据
const cacheData = {
  pie: {
    used: 68,
    free: 32
  },
  line: {
    days: ['周一', '周二', '周三', '周四', '周五', '周六', '周日'],
    values: [65, 58, 75, 82, 68, 72, 79]
  }
};

onMounted(() => {
  if (pieChart.value) {
    pieInstance = echarts.init(pieChart.value);
    pieInstance.setOption({
      title: {
        text: '缓存使用率',
        left: 'center',
        textStyle: {
          color: '#666',
          fontSize: 18
        }
      },
      tooltip: {
        trigger: 'item'
      },
      legend: {
        orient: 'vertical',
        right: 10,
        top: 'middle'
      },
      series: [{
        type: 'pie',
        radius: ['50%', '70%'],
        avoidLabelOverlap: false,
        itemStyle: {
          borderRadius: 10,
          borderColor: '#fff',
          borderWidth: 2
        },
        label: {
          show: false
        },
        color: [
          new echarts.graphic.LinearGradient(0, 0, 0, 1, [
            { offset: 0, color: '#36a3eb' },
            { offset: 1, color: '#6f58e9' }
          ]),
          new echarts.graphic.LinearGradient(0, 0, 0, 1, [
            { offset: 0, color: '#fdd835' },
            { offset: 1, color: '#ff9e3d' }
          ])
        ],
        data: [
          { value: cacheData.pie.used, name: '已用缓存' },
          { value: cacheData.pie.free, name: '空闲缓存' }
        ]
      }]
    });
  }

  if (lineChart.value) {
    lineInstance = echarts.init(lineChart.value);
    lineInstance.setOption({
      title: {
        text: '缓存趋势分析',
        left: 'center',
        textStyle: {
          color: '#666',
          fontSize: 18
        }
      },
      tooltip: {
        trigger: 'axis',
        formatter: '{b0}<br />使用率: {c0}%'
      },
      xAxis: {
        type: 'category',
        data: cacheData.line.days,
        axisLine: {
          lineStyle: {
            color: '#999'
          }
        }
      },
      yAxis: {
        type: 'value',
        axisLabel: {
          formatter: '{value}%'
        }
      },
      series: [{
        data: cacheData.line.values,
        type: 'line',
        smooth: true,
        symbol: 'circle',
        symbolSize: 8,
        itemStyle: {
          color: '#6f58e9'
        },
        areaStyle: {
          color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
            { offset: 0, color: 'rgba(111,88,233,0.4)' },
            { offset: 1, color: 'rgba(111,88,233,0.05)' }
          ])
        },
        lineStyle: {
          width: 3
        }
      }]
    });
  }

  // 窗口自适应
  window.addEventListener('resize', () => {
    pieInstance?.resize();
    lineInstance?.resize();
  });
});

// 组件卸载时销毁实例
onUnmounted(() => {
  pieInstance?.dispose();
  lineInstance?.dispose();
});
</script>


<template>
  <div class="dashboard">
    <!-- 环形统计图 -->
    <div class="chart-card" ref="pieChart" style="height: 400px"></div>

    <!-- 折线图 -->
    <div class="chart-card" ref="lineChart" style="height: 400px"></div>
  </div>
</template>



<style scoped>
.dashboard {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(400px, 1fr));
  gap: 20px;
  padding: 20px;
  max-width: 1200px;
  margin: 0 auto;
}

.chart-card {
  background: white;
  border-radius: 16px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.05);
  transition: transform 0.2s;
}

.chart-card:hover {
  transform: translateY(-4px);
}
</style>