<template>
  <div class="particle-loader" v-show="isLoading">
    <div class="particle-container">
      <!-- 粒子画布 -->
      <canvas ref="canvas" class="particle-canvas"></canvas>

      <!-- 中心Logo -->
      <div class="logo-container">
        <div class="logo-circle">
          <img src="@/assets/images/login/logo.png" alt="Logo" class="logo-img">
        </div>
      </div>
    </div>

    <!-- 加载文字 -->
    <div class="loading-text">
      <span class="loading-word" v-for="(char, index) in loadingText" :key="index"
            :style="{ animationDelay: `${index * 0.1}s` }">
        {{ char }}
      </span>
    </div>

    <!-- 进度条 -->
    <div class="progress-container">
      <div class="progress-bar" :style="{ width: `${progress}%` }"></div>
      <div class="progress-text">{{ progress }}%</div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { ref, onMounted, onUnmounted } from 'vue';

const props = defineProps({
  isLoading: {
    type: Boolean,
    default: true
  }
});

const canvas = ref<HTMLCanvasElement | null>(null);
const progress = ref(0);
const loadingText = '天畅风华系统加载中'.split('');

// 粒子系统参数
const PARTICLE_COUNT = 150;
const particles = ref<any[]>([]);
let animationFrameId: number;

// 初始化粒子
const initParticles = () => {
  if (!canvas.value) return;

  const ctx = canvas.value.getContext('2d');
  if (!ctx) return;

  canvas.value.width = window.innerWidth;
  canvas.value.height = window.innerHeight;

  // 创建粒子
  particles.value = Array.from({ length: PARTICLE_COUNT }, () => ({
    x: Math.random() * canvas.value!.width,
    y: Math.random() * canvas.value!.height,
    size: Math.random() * 3 + 1,
    speedX: Math.random() * 2 - 1,
    speedY: Math.random() * 2 - 1,
    color: `hsl(${Math.random() * 60 + 200}, 80%, 60%)`
  }));

  // 开始动画
  animateParticles();
};

// 粒子动画
const animateParticles = () => {
  if (!canvas.value) return;

  const ctx = canvas.value.getContext('2d');
  if (!ctx) return;

  ctx.clearRect(0, 0, canvas.value.width, canvas.value.height);

  // 绘制粒子
  particles.value.forEach(p => {
    ctx.beginPath();
    ctx.arc(p.x, p.y, p.size, 0, Math.PI * 2);
    ctx.fillStyle = p.color;
    ctx.fill();

    // 更新位置
    p.x += p.speedX;
    p.y += p.speedY;

    // 边界检查
    if (p.x < 0 || p.x > canvas.value!.width) p.speedX *= -1;
    if (p.y < 0 || p.y > canvas.value!.height) p.speedY *= -1;

    // 中心引力效果
    const dx = canvas.value!.width / 2 - p.x;
    const dy = canvas.value!.height / 2 - p.y;
    const distance = Math.sqrt(dx * dx + dy * dy);

    if (distance < 150) {
      p.speedX += dx * 0.0002;
      p.speedY += dy * 0.0002;
    }
  });

  // 连接粒子
  connectParticles(ctx);

  animationFrameId = requestAnimationFrame(animateParticles);
};

// 连接粒子
const connectParticles = (ctx: CanvasRenderingContext2D) => {
  for (let i = 0; i < particles.value.length; i++) {
    for (let j = i + 1; j < particles.value.length; j++) {
      const p1 = particles.value[i];
      const p2 = particles.value[j];

      const dx = p1.x - p2.x;
      const dy = p1.y - p2.y;
      const distance = Math.sqrt(dx * dx + dy * dy);

      if (distance < 100) {
        ctx.beginPath();
        ctx.strokeStyle = `rgba(100, 200, 255, ${1 - distance / 100})`;
        ctx.lineWidth = 0.5;
        ctx.moveTo(p1.x, p1.y);
        ctx.lineTo(p2.x, p2.y);
        ctx.stroke();
      }
    }
  }
};

// 模拟加载进度
const simulateLoading = () => {
  if (progress.value < 100) {
    progress.value += Math.random() * 5;
    setTimeout(simulateLoading, 50);
  }
};

// 替换simulateLoading方法为真实进度监听
// const updateProgress = (realProgress: number) => {
//   progress.value = realProgress;
// };

onMounted(() => {
  initParticles();
  simulateLoading();
});

onUnmounted(() => {
  cancelAnimationFrame(animationFrameId);
});
</script>

<style lang="scss" scoped>
.particle-loader {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background: linear-gradient(135deg, #1a1a2e 0%, #16213e 100%);
  z-index: 9999;
  overflow: hidden;
}

.particle-container {
  position: relative;
  width: 100%;
  height: 60vh;
}

.particle-canvas {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}

.logo-container {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 2;
}

.logo-circle {
  width: 120px;
  height: 120px;
  border-radius: 50%;
  background: rgba(255, 255, 255, 0.1);
  backdrop-filter: blur(5px);
  display: flex;
  justify-content: center;
  align-items: center;
  box-shadow: 0 0 30px rgba(100, 200, 255, 0.3);
  animation: pulse 2s infinite alternate;
}

.logo-img {
  width: 70px;
  height: 70px;
}

.loading-text {
  margin-top: 20px;
  font-size: 24px;
  color: rgba(255, 255, 255, 0.8);
  letter-spacing: 2px;
  display: flex;
}

.loading-word {
  opacity: 0;
  animation: fadeIn 0.5s forwards;
}

@keyframes fadeIn {
  to {
    opacity: 1;
  }
}

@keyframes pulse {
  from {
    box-shadow: 0 0 30px rgba(100, 200, 255, 0.3);
  }
  to {
    box-shadow: 0 0 50px rgba(100, 200, 255, 0.5);
  }
}

.progress-container {
  width: 300px;
  height: 4px;
  background: rgba(255, 255, 255, 0.1);
  border-radius: 2px;
  margin-top: 40px;
  position: relative;
}

.progress-bar {
  height: 100%;
  border-radius: 2px;
  background: linear-gradient(90deg, #4facfe 0%, #00f2fe 100%);
  transition: width 0.3s ease;
}

.progress-text {
  position: absolute;
  right: -40px;
  top: -8px;
  color: rgba(255, 255, 255, 0.7);
  font-size: 14px;
}

/* 响应式调整 */
@media (max-width: 768px) {
  .loading-text {
    font-size: 18px;
  }

  .logo-circle {
    width: 90px;
    height: 90px;
  }

  .logo-img {
    width: 50px;
    height: 50px;
  }

  .progress-container {
    width: 200px;
  }
}
</style>