<template>
  <div class="quantum-loader" :class="{ 'active': isLoading }">
    <!-- 量子场域容器 -->
    <div class="quantum-field">
      <!-- 核心量子球 -->
      <div class="quantum-core">
        <div class="core-orb"></div>
        <div class="energy-ring"></div>
        <div class="energy-ring delay-1"></div>
        <div class="energy-ring delay-2"></div>
      </div>

      <!-- 动态弦理论粒子 -->
      <div
        v-for="(particle, index) in quantumParticles"
        :key="index"
        class="quantum-particle"
        :style="particleStyle(particle)"
      ></div>

      <!-- 全息数据流 -->
      <div class="holographic-stream">
        <div
          v-for="(stream, sIndex) in dataStreams"
          :key="sIndex"
          class="data-stream"
          :style="streamStyle(stream)"
        ></div>
      </div>
    </div>

    <!-- 多维进度系统 -->
    <div class="quantum-progress">
      <div class="dimension-progress">
        <div class="dimension-bar" :style="{ width: `${progress}%` }"></div>
        <div class="dimension-label">量子态初始化</div>
      </div>
      <div class="progress-fractal">
        <div
          v-for="(segment, segIndex) in fractalSegments"
          :key="segIndex"
          class="fractal-segment"
          :style="segmentStyle(segIndex)"
        ></div>
      </div>
    </div>

    <!-- 动态信息矩阵 -->
    <div class="quantum-matrix">
      <div
        v-for="(row, rIndex) in matrixRows"
        :key="rIndex"
        class="matrix-row"
      >
        <div
          v-for="(cell, cIndex) in row"
          :key="cIndex"
          class="matrix-cell"
          :style="{ animationDelay: `${Math.random() * 2}s` }"
        >{{ cell }}</div>
      </div>
    </div>

    <!-- 环境光场 -->
    <div class="ambient-light"></div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, onUnmounted, computed } from 'vue';

interface QuantumParticle {
  x: number;
  y: number;
  size: number;
  speed: number;
  angle: number;
  orbit: number;
  color: string;
}

interface DataStream {
  x: number;
  y: number;
  width: number;
  speed: number;
  opacity: number;
  direction: number;
}

interface Props {
  isLoading?: boolean;
}

const props = withDefaults(defineProps<Props>(), {
  isLoading: true
});

// 量子粒子系统
const quantumParticles = ref<QuantumParticle[]>([]);
const dataStreams = ref<DataStream[]>([]);
const matrixRows = ref<string[][]>([]);
const progress = ref<number>(0);
const fractalSegments = ref<number>(16);

// 生成量子粒子
const generateQuantumParticles = (): void => {
  const particles: QuantumParticle[] = [];
  for (let i = 0; i < 128; i++) {
    particles.push({
      x: Math.random() * 100,
      y: Math.random() * 100,
      size: Math.random() * 4 + 2,
      speed: Math.random() * 0.5 + 0.2,
      angle: Math.random() * Math.PI * 2,
      orbit: 30 + Math.random() * 70,
      color: `hsla(${Math.floor(Math.random() * 60 + 200)}, 90%, 60%, ${Math.random() * 0.3 + 0.7})`
    });
  }
  quantumParticles.value = particles;
};

// 生成数据流
const generateDataStreams = (): void => {
  const streams: DataStream[] = [];
  for (let i = 0; i < 12; i++) {
    streams.push({
      x: Math.random() * 100,
      y: Math.random() * 100,
      width: Math.random() * 3 + 1,
      speed: Math.random() * 2 + 1,
      opacity: Math.random() * 0.3 + 0.1,
      direction: Math.random() > 0.5 ? 1 : -1
    });
  }
  dataStreams.value = streams;
};

// 生成信息矩阵
const generateMatrix = (): void => {
  const rows: string[][] = [];
  const chars = ['天', '畅', '风', '华', '0', '1', '◊', '∆', '∇', '∞'];

  for (let r = 0; r < 8; r++) {
    const row: string[] = [];
    for (let c = 0; c < 16; c++) {
      row.push(chars[Math.floor(Math.random() * chars.length)]);
    }
    rows.push(row);
  }
  matrixRows.value = rows;
};

// 粒子样式计算
const particleStyle = (p: QuantumParticle): Record<string, string> => {
  return {
    left: `${p.x}%`,
    top: `${p.y}%`,
    width: `${p.size}px`,
    height: `${p.size}px`,
    background: p.color,
    transform: `rotate(${p.angle}rad)`
  };
};

// 数据流样式
const streamStyle = (s: DataStream): Record<string, string | number> => {
  return {
    left: `${s.x}%`,
    top: `${s.y}%`,
    width: `${s.width}px`,
    opacity: s.opacity,
    transform: `rotate(${s.direction * 45}deg)`
  };
};

// 分形进度段样式
const segmentStyle = (index: number): Record<string, string> => {
  const active = progress.value > (index * 100 / fractalSegments.value);
  return {
    background: active ? `hsla(${200 + index * 5}, 90%, 60%, 0.8)` : 'rgba(255,255,255,0.1)',
    transform: `rotate(${index * 22.5}deg)`
  };
};

// 动画循环
let animationFrame: number;
const animate = (): void => {
  // 更新量子粒子
  quantumParticles.value.forEach(p => {
    p.angle += p.speed * 0.02;
    p.x = 50 + Math.cos(p.angle) * p.orbit;
    p.y = 50 + Math.sin(p.angle) * p.orbit * 0.6;
  });

  // 更新数据流
  dataStreams.value.forEach(s => {
    s.y = (s.y + s.speed) % 100;
  });

  // 随机更新矩阵单元格
  if (Math.random() > 0.7) {
    const r = Math.floor(Math.random() * matrixRows.value.length);
    const c = Math.floor(Math.random() * matrixRows.value[0].length);
    const chars = ['天', '畅', '风', '华', '0', '1', '◊', '∆', '∇', '∞'];
    matrixRows.value[r][c] = chars[Math.floor(Math.random() * chars.length)];
  }

  animationFrame = requestAnimationFrame(animate);
};
// 进度标签文本
const dimensionLabel = ref('准备加载');

/**
 * 智能量子加载器（自动检测请求）
 * 使用示例：
 * const quantumLoader = useQuantumLoader();
 * quantumLoader.watch(fetch('/api/data'));
 */
//  const useQuantumLoader = () => {
//   const isLoading = ref(false);
//   let currentAnimation: { stop: () => void } | null = null;

//   // 生成随机科技感阶段名称
//   const getRandomStage = () => {
//     const prefixes = ['量子', '纳米', '全息', '超维', '粒子'];
//     const suffixes = ['初始化', '校准', '同步', '传输', '验证'];
//     return `${prefixes[Math.floor(Math.random() * prefixes.length)]}${
//       suffixes[Math.floor(Math.random() * suffixes.length)]
//     }`;
//   };

//   // 启动加载动画
//   const startLoading = () => {
//     isLoading.value = true;
//     progress.value = 0;
//     dimensionLabel.value = "系统启动中...";

//     const stages = Array.from({ length: 5 }, getRandomStage);
//     let lastUpdate = Date.now();

//     const update = () => {
//       if (!isLoading.value) return;

//       const now = Date.now();
//       const delta = now - lastUpdate;
//       lastUpdate = now;

//       // 渐进式速度（越接近100%越慢）
//       const remaining = 100 - progress.value;
//       const speed = Math.min(0.3 + remaining * 0.015, 2);

//       progress.value = Math.min(progress.value + (delta * speed) / 100, 99.9);

//       // 更新阶段标签
//       const stageIndex = Math.floor(progress.value / 20);
//       dimensionLabel.value = `${stages[stageIndex]} ${Math.floor(progress.value)}%`;

//       requestAnimationFrame(update);
//     };

//     currentAnimation = {
//       stop: () => {
//         isLoading.value = false;
//         progress.value = 100;
//         dimensionLabel.value = "量子连接就绪";
//         setTimeout(() => {
//           progress.value = 0;
//           dimensionLabel.value = "待命状态";
//         }, 1500);
//       }
//     };

//     update();
//   };

//   // 监视Promise状态
//   const watch = async <T>(promise: Promise<T>): Promise<T> => {
//     startLoading();
//     try {
//       const result = await promise;
//       currentAnimation?.stop();
//       return result;
//     } catch (error) {
//       dimensionLabel.value = "量子连接中断";
//       progress.value = 0;
//       isLoading.value = false;
//       throw error;
//     }
//   };

//   return { watch, isLoading };
// };

// // 在组件中初始化
// const quantumLoader = useQuantumLoader();

// // 修改onMounted调用方式
// onMounted(() => {
//   generateQuantumParticles();
//   generateDataStreams();
//   generateMatrix();
//   animate();

//   // 示例：自动检测fetch请求
//   // quantumLoader.watch(fetch('/api/data'));
// });
// 包装任意Promise，自动处理加载状态
// quantumLoader.watch(fetch('/api/data'))
//   .then(data => console.log('成功:', data))
//   .catch(err => console.error('失败:', err));
// 模拟进度
const simulateProgress = (): void => {
  if (progress.value < 100) {
    progress.value += Math.random() * 0.5;
    setTimeout(simulateProgress, 50);
  }
};

onMounted(() => {
  generateQuantumParticles();
  generateDataStreams();
  generateMatrix();
  animate();
  simulateProgress();
});

onUnmounted(() => {
  cancelAnimationFrame(animationFrame);
});
</script>

<style lang="scss" scoped>
.quantum-loader {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background: radial-gradient(ellipse at center, #0a0a1a 0%, #000000 100%);
  z-index: 9999;
  overflow: hidden;
  opacity: 0;
  pointer-events: none;
  transition: opacity 0.8s ease;

  &.active {
    opacity: 1;
    pointer-events: all;
  }
}

.quantum-field {
  position: relative;
  width: 100%;
  height: 60vh;
  perspective: 1000px;
}

.quantum-core {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 180px;
  height: 180px;

  .core-orb {
    position: absolute;
    width: 100%;
    height: 100%;
    border-radius: 50%;
    background: radial-gradient(circle at center,
      hsla(210, 100%, 60%, 0.8) 0%,
      hsla(210, 100%, 50%, 0.2) 70%);
    box-shadow: 0 0 60px hsla(210, 100%, 60%, 0.6);
    animation: quantumPulse 3s infinite alternate;
    filter: blur(1px);
  }

  .energy-ring {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    border: 2px solid hsla(210, 100%, 60%, 0.5);
    border-radius: 50%;
    animation: energyOrbit 6s infinite linear;

    &.delay-1 {
      width: 130%;
      height: 130%;
      top: -15%;
      left: -15%;
      animation-delay: -2s;
    }

    &.delay-2 {
      width: 160%;
      height: 160%;
      top: -30%;
      left: -30%;
      animation-delay: -4s;
    }
  }
}

.quantum-particle {
  position: absolute;
  border-radius: 50%;
  filter: blur(1px);
  transform-origin: center;
  will-change: transform, opacity;
  transition: all 0.3s ease-out;
  box-shadow: 0 0 10px currentColor;
}

.holographic-stream {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;

  .data-stream {
    position: absolute;
    height: 200%;
    background: linear-gradient(
      to bottom,
      transparent 0%,
      hsla(210, 100%, 60%, 0.3) 50%,
      transparent 100%
    );
    animation: dataFlow 8s infinite linear;
  }
}

.quantum-progress {
  width: 80%;
  max-width: 600px;
  margin-top: 40px;

  .dimension-progress {
    position: relative;
    width: 100%;
    height: 4px;
    background: rgba(255,255,255,0.1);
    border-radius: 2px;
    overflow: hidden;

    .dimension-bar {
      height: 100%;
      background: linear-gradient(
        90deg,
        hsla(200, 100%, 50%, 0.8),
        hsla(300, 100%, 50%, 0.8)
      );
      box-shadow: 0 0 10px hsla(200, 100%, 50%, 0.5);
      transition: width 0.3s ease;
    }

    .dimension-label {
      position: absolute;
      top: -25px;
      left: 0;
      color: hsla(210, 100%, 70%, 0.9);
      font-size: 14px;
      text-transform: uppercase;
      letter-spacing: 2px;
    }
  }

  .progress-fractal {
    position: relative;
    width: 120px;
    height: 120px;
    margin: 20px auto;

    .fractal-segment {
      position: absolute;
      top: 50%;
      left: 50%;
      width: 40px;
      height: 2px;
      background: rgba(255,255,255,0.1);
      transform-origin: left center;
      transition: background 0.5s ease;
    }
  }
}

.quantum-matrix {
  position: absolute;
  bottom: 30px;
  left: 0;
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  font-family: monospace;

  .matrix-row {
    display: flex;
    margin-bottom: 2px;

    .matrix-cell {
      width: 20px;
      height: 20px;
      display: flex;
      justify-content: center;
      align-items: center;
      color: hsla(120, 100%, 60%, 0.7);
      font-size: 16px;
      animation: matrixGlow 1.5s infinite alternate;
    }
  }
}

.ambient-light {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: radial-gradient(
    circle at center,
    hsla(210, 100%, 60%, 0.05) 0%,
    transparent 70%
  );
  pointer-events: none;
  z-index: -1;
}

/* 高级动画 */
@keyframes quantumPulse {
  0% { transform: translate(-50%, -50%) scale(1); opacity: 0.8; }
  50% { transform: translate(-50%, -50%) scale(1.05); opacity: 1; }
  100% { transform: translate(-50%, -50%) scale(1); opacity: 0.8; }
}

@keyframes energyOrbit {
  0% { transform: rotateX(60deg) rotateZ(0deg); }
  100% { transform: rotateX(60deg) rotateZ(360deg); }
}

@keyframes dataFlow {
  0% { transform: translateY(-100%) rotate(45deg); }
  100% { transform: translateY(100%) rotate(45deg); }
}

@keyframes matrixGlow {
  from { text-shadow: 0 0 5px hsla(120, 100%, 60%, 0.3); }
  to { text-shadow: 0 0 15px hsla(120, 100%, 60%, 0.8); }
}

/* 响应式设计 */
@media (max-width: 768px) {
  .quantum-core {
    width: 120px;
    height: 120px;
  }

  .quantum-progress {
    width: 90%;
  }

  .quantum-matrix {
    .matrix-row {
      .matrix-cell {
        width: 16px;
        height: 16px;
        font-size: 14px;
      }
    }
  }
}
</style>