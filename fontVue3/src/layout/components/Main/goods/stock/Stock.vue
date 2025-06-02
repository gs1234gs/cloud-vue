<script setup lang="ts">
import StatisticsHeader from '@/components/statistics/statistics-header/StatisticsHeader.vue';
import { storeToRefs } from 'pinia';
import { useStatisticsStore } from '@/stores/modules/goods/statistics/statistics';
import StatisticsColumnarPicture from '@/components/statistics/statistics-picture/StatisticsColumnarPicture.vue'
import { onMounted } from 'vue';
const statisticsStore = useStatisticsStore()
const {
  goodsStatistics
} = storeToRefs(statisticsStore)
const countGoods = goodsStatistics.value[1]
  console.log(countGoods)
  console.log(goodsStatistics.value)
  onMounted (() => {
  statisticsStore.getGoodsStatistics()
})
</script>

<template>

<div class="header">

  <el-row :gutter="10">
<template v-for="item in countGoods" :key="item.productType">

  <el-col :span="6">
        <StatisticsHeader v-bind="item" />
    </el-col>
</template>

  </el-row>
  <el-row :gutter="10">
    <el-col :span="12" >
 <StatisticsColumnarPicture />
    </el-col>
    <el-col :span="12" >
 <StatisticsColumnarPicture />
    </el-col>
  </el-row>



</div>

</template>