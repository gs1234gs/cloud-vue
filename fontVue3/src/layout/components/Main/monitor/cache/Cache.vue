<template>
  <div class="cache-monitor-container">
    <h2 class="title">系统缓存监控</h2>
    <div class="chart-container">
      <v-chart class="chart" :option="chartOption" autoresize />
    </div>
    <div class="info-cards">
      <div class="info-card" v-for="(item, index) in infoItems" :key="index">
        <div class="info-icon" :style="{ backgroundColor: item.color }">
          <i :class="item.icon"></i>
        </div>
        <div class="info-content">
          <h3>{{ item.title }}</h3>
          <p>{{ item.value }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue';
import { use } from 'echarts/core';
import { CanvasRenderer } from 'echarts/renderers';
import { PieChart } from 'echarts/charts';
import {
  TitleComponent,
  TooltipComponent,
  LegendComponent,
  GridComponent
} from 'echarts/components';
import VChart from 'vue-echarts';

use([
  CanvasRenderer,
  PieChart,
  TitleComponent,
  TooltipComponent,
  LegendComponent,
  GridComponent
]);

// 响应式数据
const cacheData = ref(null);
const isLoading = ref(true);
const error = ref(null);

// 默认数据
const defaultData = {
  used: 45,
  free: 55,
  hitRate: 78.5,
  missRate: 21.5,
  totalItems: 1250,
  activeItems: 890
};

// 信息卡片配置
const infoItems = ref([
  {
    title: '缓存命中率',
    value: `${defaultData.hitRate}%`,
    icon: 'fas fa-bullseye',
    color: '#4CAF50'
  },
  {
    title: '缓存未命中率',
    value: `${defaultData.missRate}%`,
    icon: 'fas fa-times-circle',
    color: '#F44336'
  },
  {
    title: '总缓存项',
    value: defaultData.totalItems.toLocaleString(),
    icon: 'fas fa-database',
    color: '#2196F3'
  },
  {
    title: '活跃缓存项',
    value: defaultData.activeItems.toLocaleString(),
    icon: 'fas fa-bolt',
    color: '#FFC107'
  }
]);

// 计算属性：图表配置
const chartOption = computed(() => {
  const data = cacheData.value || defaultData;

  return {
    title: {
      text: '系统缓存使用情况',
      subtext: '实时监控',
      left: 'center'
    },
    tooltip: {
      trigger: 'item',
      formatter: '{a} <br/>{b}: {c}% ({d}%)'
    },
    legend: {
      orient: 'vertical',
      left: 'left',
      data: ['已使用', '剩余']
    },
    series: [
      {
        name: '缓存使用情况',
        type: 'pie',
        radius: ['50%', '70%'],
        avoidLabelOverlap: false,
        itemStyle: {
          borderRadius: 10,
          borderColor: '#fff',
          borderWidth: 2
        },
        label: {
          show: false,
          position: 'center'
        },
        emphasis: {
          label: {
            show: true,
            fontSize: '18',
            fontWeight: 'bold'
          }
        },
        labelLine: {
          show: false
        },
        data: [
          { value: data.used, name: '已使用' },
          { value: data.free, name: '剩余' }
        ]
      }
    ],
    color: ['#4CAF50', '#E0E0E0']
  };
});

// 方法：获取缓存数据
const fetchCacheData = async () => {
  try {
    isLoading.value = true;
    error.value = null;

    // 这里应该是实际的API调用
    // const response = await axios.get('/api/cache-stats');
    // cacheData.value = response.data;

    // 模拟API延迟
    await new Promise(resolve => setTimeout(resolve, 1500));

    // 模拟数据
    const mockData = {
      used: Math.floor(Math.random() * 30) + 20,
      free: 100 - (Math.floor(Math.random() * 30) + 20),
      hitRate: parseFloat((Math.random() * 20 + 60).toFixed(1)),
      missRate: parseFloat((Math.random() * 20 + 20).toFixed(1)),
      totalItems: Math.floor(Math.random() * 1000) + 1000,
      activeItems: Math.floor(Math.random() * 500) + 500
    };

    cacheData.value = mockData;

    // 更新信息卡片
    infoItems.value = [
      {
        title: '缓存命中率',
        value: `${mockData.hitRate}%`,
        icon: 'fas fa-bullseye',
        color: '#4CAF50'
      },
      {
        title: '缓存未命中率',
        value: `${mockData.missRate}%`,
        icon: 'fas fa-times-circle',
        color: '#F44336'
      },
      {
        title: '总缓存项',
        value: mockData.totalItems.toLocaleString(),
        icon: 'fas fa-database',
        color: '#2196F3'
      },
      {
        title: '活跃缓存项',
        value: mockData.activeItems.toLocaleString(),
        icon: 'fas fa-bolt',
        color: '#FFC107'
      }
    ];

  } catch (err) {
    console.error('获取缓存数据失败:', err);
    error.value = err;
    cacheData.value = null;
  } finally {
    isLoading.value = false;
  }
};

// 生命周期钩子
onMounted(() => {
  fetchCacheData();
});
</script>

<style scoped>
.cache-monitor-container {
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  max-width: 1000px;
  margin: 0 auto;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 12px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
}

.title {
  text-align: center;
  color: #333;
  margin-bottom: 30px;
  font-weight: 500;
}

.chart-container {
  width: 100%;
  height: 400px;
  margin-bottom: 30px;
}

.chart {
  width: 100%;
  height: 100%;
}

.info-cards {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 20px;
}

.info-card {
  display: flex;
  background-color: white;
  border-radius: 10px;
  padding: 20px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
  transition: transform 0.3s ease;
}

.info-card:hover {
  transform: translateY(-5px);
}

.info-icon {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-right: 15px;
  color: white;
  font-size: 20px;
}

.info-content h3 {
  margin: 0 0 5px 0;
  font-size: 16px;
  color: #666;
  font-weight: 500;
}

.info-content p {
  margin: 0;
  font-size: 24px;
  font-weight: 600;
  color: #333;
}
</style>