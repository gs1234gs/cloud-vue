<template>
  <div class="log-container" :class="{ 'dark-mode': isDarkMode }">
    <!-- 三维动态背景 -->
    <div class="particle-background"></div>

    <!-- 控制面板 -->
    <div class="control-panel glass-card">
      <div class="header">
        <h1 class="title">🌌 Stellar Logs</h1>
        <div class="controls">
          <el-switch
            v-model="isDarkMode"
            :active-icon="Moon"
            :inactive-icon="Sunny"
            class="theme-switch"
          />
          <el-tooltip content="星云脉冲" placement="bottom">
            <el-button circle @click="pulseNebula">
              <i class="fas fa-meteor"></i>
            </el-button>
          </el-tooltip>
        </div>
      </div>

      <!-- 过滤控制 -->
      <div class="filters">
        <el-input
          v-model="searchQuery"
          placeholder="星际搜索..."
          class="search-input"
          :prefix-icon="Search"
          clearable
        >
          <template #prefix>
            <i class="fas fa-star"></i>
          </template>
        </el-input>

        <el-select
          v-model="selectedLevels"
          multiple
          collapse-tags
          placeholder="日志等级"
          class="level-select"
        >
          <el-option
            v-for="level in logLevels"
            :key="level.value"
            :label="level.label"
            :value="level.value"
          >
            <span :class="['level-dot', level.value]"></span>
            {{ level.label }}
          </el-option>
        </el-select>
      </div>
    </div>

    <!-- 日志展示区 -->
    <div class="log-viewer glass-card">
      <div class="log-scroll" @scroll="handleScroll">
        <transition-group name="log-item" tag="div">
          <div
            v-for="(log, index) in filteredLogs"
            :key="log.id"
            :class="['log-entry', log.level]"
            :style="{ '--index': index }"
          >
            <div class="log-glow"></div>
            <div class="log-content">
              <span class="timestamp">{{ formatTime(log.timestamp) }}</span>
              <span :class="['level-badge', log.level]">
                {{ levelLabels[log.level] }}
              </span>
              <span class="message">{{ log.message }}</span>
            </div>
            <div class="log-meta">
              <span class="service">{{ log.service }}</span>
              <span class="source">@{{ log.source }}</span>
            </div>
          </div>
        </transition-group>
      </div>
    </div>

    <!-- 统计面板 -->
    <div class="stats-panel glass-card">
      <div class="stats-header">
        <h3>📊 星云统计</h3>
        <el-progress
          :percentage="errorPercentage"
          status="exception"
          :stroke-width="12"
          :format="() => `ERROR ${errorPercentage}%`"
        />
      </div>
      <div class="chart-container">
        <div class="level-chart">
          <div
            v-for="level in logLevels"
            :key="level.value"
            :class="['chart-bar', level.value]"
             :style="{ height: levelStats[level.value as keyof typeof levelStats] * 2 + 'px' }"
          >
          <span class="chart-label">{{ levelStats[level.value as keyof typeof levelStats] }}</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, onMounted } from 'vue'
import { Sunny, Moon, Search } from '@element-plus/icons-vue'

interface LogEntry {
  id: string
  timestamp: number
  level: 'debug' | 'info' | 'warn' | 'error' | 'fatal'
  message: string
  service: string
  source: string
}

// 生成星空日志数据
const generateLogs = () => {
  const services = ['Stellar-Core', 'Nebula-Gateway', 'Galaxy-DB', 'Cosmos-API']
  const levels = ['debug', 'info', 'warn', 'error', 'fatal']
  const messages = [
    '发现新的星云脉冲信号',
    '引力波传感器校准完成',
    '超新星爆发警告阈值突破',
    '维度折叠引擎过热',
    '暗物质缓存异常',
    '时空曲率计算完成'
  ]

  return Array.from({ length: 200 }, (_, i) => ({
    id: `log-${Date.now()}-${i}`,
    timestamp: Date.now() - i * 60000,
    level: levels[i % levels.length] as LogEntry['level'],
    message: messages[i % messages.length],
    service: services[i % services.length],
    source: `node-${Math.floor(Math.random() * 16).toString(16)}`
  }))
}

const logs = ref<LogEntry[]>(generateLogs())
const searchQuery = ref('')
const selectedLevels = ref<string[]>([])
const isDarkMode = ref(true)
const logLevels = [
  { value: 'debug', label: '调试' },
  { value: 'info', label: '信息' },
  { value: 'warn', label: '警告' },
  { value: 'error', label: '错误' },
  { value: 'fatal', label: '致命' }
]

const levelLabels = {
  debug: '🛠️ DEBUG',
  info: 'ℹ️ INFO',
  warn: '⚠️ WARN',
  error: '🚨 ERROR',
  fatal: '💀 FATAL'
}

// 过滤逻辑
const filteredLogs = computed(() => {
  return logs.value.filter(log => {
    const matchesSearch = log.message.toLowerCase().includes(searchQuery.value.toLowerCase())
    const matchesLevel = selectedLevels.value.length ? selectedLevels.value.includes(log.level) : true
    return matchesSearch && matchesLevel
  })
})

// 统计计算
const levelStats = computed(() => {
  const counts = { debug: 0, info: 0, warn: 0, error: 0, fatal: 0 }
  logs.value.forEach(log => counts[log.level]++)
  return counts
})

const errorPercentage = computed(() => {
  const total = logs.value.length
  const errors = levelStats.value.error + levelStats.value.fatal
  return Math.round((errors / total) * 100)
})

// 时间格式化
const formatTime = (timestamp: number) => {
  return new Date(timestamp).toLocaleTimeString('en-US', {
    hour12: false,
    hour: '2-digit',
    minute: '2-digit',
    second: '2-digit'
  })
}

// 动态效果
const pulseNebula = () => {
  document.documentElement.style.setProperty('--nebula-pulse', '1')
  setTimeout(() => {
    document.documentElement.style.setProperty('--nebula-pulse', '0')
  }, 1000)
}

// 滚动优化
const handleScroll = (e: Event) => {
  const container = e.target as HTMLElement
  const cards = container.querySelectorAll('.log-entry')
  cards.forEach((card, index) => {
    const rect = card.getBoundingClientRect()
    const opacity = 1 - Math.abs(rect.top - window.innerHeight / 2) / (window.innerHeight / 2)
    ;(card as HTMLElement).style.opacity = Math.max(opacity, 0.3).toString()
  })
}

// 初始化粒子背景
onMounted(() => {
  const createParticle = () => {
    const particle = document.createElement('div')
    particle.className = 'particle'
    particle.style.left = `${Math.random() * 100}%`
    particle.style.top = `${Math.random() * 100}%`
    document.querySelector('.particle-background')?.appendChild(particle)
  }

  Array.from({ length: 50 }).forEach(createParticle)
})
</script>

<style lang="scss" scoped>
@keyframes nebulaPulse {
  0% { opacity: 0.2; }
  50% { opacity: 0.8; }
  100% { opacity: 0.2; }
}

.log-container {
  --primary-color: #4a90e2;
  --nebula-pulse: 0;
  min-height: 100vh;
  padding: 2rem;
  background: radial-gradient(circle at 50% 50%, #0f0c29, #302b63);
  position: relative;
  overflow: hidden;

  &.dark-mode {
    background: radial-gradient(circle at 50% 50%, #000428, #004e92);
  }

  .particle-background {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    pointer-events: none;

    .particle {
      position: absolute;
      width: 2px;
      height: 2px;
      background: rgba(255, 255, 255, 0.5);
      border-radius: 50%;
      animation: float 20s infinite linear;

      @keyframes float {
        0% { transform: translateY(0) translateX(0); }
        100% { transform: translateY(-100vh) translateX(100vw); }
      }
    }
  }

  .glass-card {
    background: rgba(255, 255, 255, 0.05);
    backdrop-filter: blur(12px);
    border-radius: 16px;
    border: 1px solid rgba(255, 255, 255, 0.1);
    box-shadow: 0 8px 32px rgba(0, 0, 0, 0.1);
    margin-bottom: 1.5rem;
    padding: 1.5rem;
    position: relative;
    transition: all 0.3s ease;

    &::before {
      content: '';
      position: absolute;
      top: 0;
      left: 0;
      right: 0;
      bottom: 0;
      border-radius: inherit;
      background: linear-gradient(
        45deg,
        rgba(255, 255, 255, calc(var(--nebula-pulse) * 0.1)),
        rgba(74, 144, 226, calc(var(--nebula-pulse) * 0.2))
      );
      pointer-events: none;
      animation: nebulaPulse 2s infinite;
    }
  }

  .control-panel {
    .header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: 1.5rem;

      .title {
  background: linear-gradient(45deg, #00b4d8, #90e0ef);
  background-clip: text; // 标准属性
  -webkit-background-clip: text; // WebKit 特定属性
  -webkit-text-fill-color: transparent;
  font-family: 'Orbitron', sans-serif;
  letter-spacing: 1px;
}
    }

    .filters {
      display: grid;
      grid-template-columns: 1fr auto;
      gap: 1rem;

      .search-input {
        :deep(.el-input__inner) {
          background: rgba(0, 0, 0, 0.3);
          border: 1px solid rgba(255, 255, 255, 0.1);
          color: #fff;
        }
      }
    }
  }

  .log-viewer {
    height: 60vh;
    overflow: hidden;
    position: relative;

    .log-scroll {
      height: 100%;
      overflow-y: auto;
      padding-right: 10px;

      &::-webkit-scrollbar {
        width: 8px;
      }

      &::-webkit-scrollbar-thumb {
        background: rgba(255, 255, 255, 0.2);
        border-radius: 4px;
      }
    }

    .log-entry {
      padding: 1rem;
      margin-bottom: 1rem;
      border-radius: 8px;
      position: relative;
      transition: all 0.3s ease;
      opacity: calc(1 - (var(--index) * 0.002));

      &::before {
        content: '';
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background: currentColor;
        opacity: 0.1;
        border-radius: inherit;
      }

      .log-glow {
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        border-radius: inherit;
        box-shadow: inset 0 0 15px currentColor;
        opacity: 0.3;
      }

      &.debug { color: #4a90e2; }
      &.info { color: #00b894; }
      &.warn { color: #fdcb6e; }
      &.error { color: #ff7675; }
      &.fatal { color: #d63031; }

      .log-content {
        display: flex;
        align-items: center;
        gap: 1rem;
        font-size: 0.9em;

        .timestamp {
          opacity: 0.7;
          min-width: 80px;
        }

        .level-badge {
          font-weight: bold;
          padding: 2px 8px;
          border-radius: 4px;
          min-width: 80px;
          text-align: center;
        }
      }

      .log-meta {
        margin-top: 0.5rem;
        font-size: 0.8em;
        opacity: 0.7;
        display: flex;
        gap: 1rem;

        .service {
          &::before {
            content: '🛰️ ';
          }
        }
      }
    }
  }

  .stats-panel {
    .chart-container {
      height: 200px;
      display: flex;
      align-items: flex-end;
      gap: 1rem;
      padding: 1rem;

      .level-chart {
        display: flex;
        gap: 1rem;
        width: 100%;
        height: 100%;

        .chart-bar {
          flex: 1;
          background: rgba(255, 255, 255, 0.1);
          border-radius: 4px 4px 0 0;
          transition: height 0.5s ease;
          position: relative;

          &::before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: currentColor;
            opacity: 0.3;
          }

          &.debug { color: #4a90e2; }
          &.info { color: #00b894; }
          &.warn { color: #fdcb6e; }
          &.error { color: #ff7675; }
          &.fatal { color: #d63031; }

          .chart-label {
            position: absolute;
            bottom: 100%;
            width: 100%;
            text-align: center;
            font-size: 0.8em;
            opacity: 0.7;
          }
        }
      }
    }
  }
}

.log-item-enter-active,
.log-item-leave-active {
  transition: all 0.5s ease;
}
.log-item-enter-from,
.log-item-leave-to {
  opacity: 0;
  transform: translateX(30px);
}
</style>