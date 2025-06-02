<script setup lang="ts">
import { DataBoard, CaretTop } from '@element-plus/icons-vue'
import { CountUp } from 'countup.js'
import { onMounted, ref } from 'vue'

interface IProps {
  productType?: string
  productAmount?: number
  productWeek?: string
  piece?:  string
  time?: string
}

const props = withDefaults (defineProps<IProps>(), {
  productType: '中国移动',
  productAmount: 8848,
  productWeek: '12.5%',
  piece: '件',
  time: '周同比'
})

//创建countup实例
const countProductAmount  = ref<HTMLElement>()
  onMounted (() => {
    // 创建countup实例
    if (countProductAmount.value) {
      //参数1 为dom元素，参数2 为目标值
      const countup = new CountUp(countProductAmount.value, props.productAmount)
      countup.start()
    }
  })
// console.log(props)

</script>

<template>
  <div class="data-card">
    <div class="card-decor"></div>

    <div class="card-header">
      <div class="icon-wrapper">
        <el-tooltip content="所有商品销量" placement="top" >
          <el-icon class="animated-icon"><DataBoard /></el-icon>
  </el-tooltip>
      </div>
      <h3 class="card-title">{{ props.productType }}</h3>
    </div>

    <div class="card-body">
      <div class="main-value">
        <span class="number" ref="countProductAmount">{{ props.productAmount }}</span>
        <span class="unit">{{ props.piece }}</span>
      </div>
      <div class="comparison">
        <span class="trend up">
          <el-icon><CaretTop /></el-icon>
          {{ props.productWeek }}
        </span>
        <span class="time-range">{{ props.time }}</span>
      </div>
    </div>

    <div class="card-wave">
      <svg viewBox="0 0 500 50" preserveAspectRatio="none">
        <path d="M0,25 C150,50 350,0 500,25 L500,50 L0,50 Z" fill="currentColor"/>
      </svg>
    </div>
  </div>
</template>

<style scoped lang="scss">
@use 'sass:color';

$primary: #5B8BF7;
$secondary: #6C5B7B;
$success: #3ECF8E;
$background: linear-gradient(135deg, #f8f9fd 0%, #f1f3fa 100%);

.data-card {
  position: relative;
  background: $background;
  border-radius: 20px;
  padding: 2rem;
  overflow: hidden;
  transition: transform 0.4s cubic-bezier(0.34, 1.56, 0.64, 1),
              box-shadow 0.4s ease;
  box-shadow: 0 15px 35px rgba(0,0,0,0.05);

  &:hover {
    transform: translateY(-8px);
    box-shadow: 0 25px 60px rgba(91, 139, 247, 0.15);

    .animated-icon {
      transform: rotate(8deg) scale(1.1);
    }
  }
}

.card-decor {
  position: absolute;
  top: -30%;
  right: -30%;
  width: 200px;
  height: 200px;
  background: radial-gradient(circle at 50% 50%,
    rgba($primary, 0.1) 0%,
    rgba($primary, 0) 70%);
}

.card-header {
  display: flex;
  align-items: center;
  margin-bottom: 1.8rem;
  position: relative;
}

.icon-wrapper {
  width: 48px;
  height: 48px;
  background: rgba($primary, 0.1);
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-right: 1rem;
  transition: background 0.3s ease;

  .animated-icon {
    color: $primary;
    font-size: 1.8rem;
    transition: all 0.4s cubic-bezier(0.68, -0.55, 0.27, 1.55);
  }

}
.card-title {
  font-size: 1.1rem;
  color: rgba(#2C3A47, 0.8);
  font-weight: 600;
  letter-spacing: 0.5px;
  margin: 0;
}

.card-body {
  position: relative;
  z-index: 2;
}

.main-value {
  display: flex;
  align-items: baseline;
  margin-bottom: 1.5rem;

  .number {
    font-size: 2.8rem;
    font-weight: 800;
    color: $primary;
    font-family: 'Inter', sans-serif;
    letter-spacing: -1px;
    margin-right: 0.5rem;
    background: linear-gradient(45deg, $primary, color.adjust($primary, $lightness: -10%)); // 使用 color.adjust 替代 darken
    -webkit-background-clip: text; // WebKit前缀
    background-clip: text; // 标准属性
    -webkit-text-fill-color: transparent;
  }

  .unit {
    font-size: 1.2rem;
    color: rgba(#2C3A47, 0.6);
    font-weight: 500;
  }
}

.comparison {
  display: flex;
  align-items: center;
  gap: 0.8rem;

  .trend {
    display: inline-flex;
    align-items: center;
    padding: 6px 12px;
    border-radius: 6px;
    font-size: 0.95rem;
    font-weight: 500;

    &.up {
      color: $success;
      background: rgba($success, 0.12);
    }

    .el-icon {
      margin-right: 4px;
      font-size: 0.9rem;
    }
  }

  .time-range {
    color: rgba(#2C3A47, 0.5);
    font-size: 0.9rem;
  }
}

.card-wave {
  position: absolute;
  bottom: 0;
  left: 0;
  width: 100%;
  height: 40px;
  color: rgba($primary, 0.06);

  svg {
    width: 100%;
    height: 100%;
  }
}

@media (max-width: 768px) {
  .data-card {
    padding: 1.5rem;
  }

  .main-value .number {
    font-size: 2.2rem;
  }

  .icon-wrapper {
    width: 42px;
    height: 42px;
  }
}
</style>