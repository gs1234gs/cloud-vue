<script setup lang="ts">
import { ref, computed } from 'vue'

// 定义公告类型枚举
type NoticeType = '紧急' | '公告' | '通知';

// 硬编码数据
const notices = ref([
  {
    id: 1,
    type: '紧急' as NoticeType,
    title: '系统维护通知',
    date: '2024-03-15 14:00',
    content: '将于2024年3月20日 00:00-06:00进行系统维护升级，届时将暂停所有服务...',
    author: '系统管理员',
    expand: false
  },
  {
    id: 2,
    type: '公告' as NoticeType,
    title: '新功能上线通知',
    date: '2024-03-14 09:30',
    content: '本次更新新增数据看板模块、优化消息中心交互体验...',
    author: '产品团队',
    expand: false
  },
  {
    id: 3,
    type: '通知' as NoticeType,
    title: '安全升级提醒',
    date: '2024-03-13 16:45',
    content: '请所有用户尽快完成密码强度升级，建议使用字母+数字+特殊字符组合...',
    author: '安全中心',
    expand: false
  }
])

const tabs = ['全部', '公告', '通知', '紧急']
const activeTab = ref('全部')
const hoverIndex = ref(-1)
const colors = ['#7238d1', '#00b4d8', '#2b8c6c', '#f59f00']

// 计算属性过滤通知
const filteredNotices = computed(() => {
  return activeTab.value === '全部'
    ? notices.value
    : notices.value.filter(n => n.type === (activeTab.value as NoticeType))
})

// 交互方法
const toggleExpand = (notice: any) => {
  notice.expand = !notice.expand
}

// 样式计算
const icons: Record<NoticeType, string> = {
  '紧急': 'fa fa-exclamation-circle',
  '公告': 'fa fa-bullhorn',
  '通知': 'fa fa-bell'
};

const styles: Record<NoticeType, string> = {
  '紧急': 'danger',
  '公告': 'primary',
  '通知': 'warning'
};

const noticeIcon = (type: NoticeType): string => {
  return icons[type];
};

const noticeTypeStyle = (type: NoticeType): string => {
  return styles[type];
};

const cardHoverStyle = (index: number): string => {
  return hoverIndex.value === index
    ? 'transform: translateY(-5px); box-shadow: 0 8px 20px rgba(0,0,0,0.12);'
    : '';
}
</script>
<template>
  <div class="notice-container">
    <!-- 分类标签 -->
    <div class="filter-tabs">
      <span
        v-for="tab in tabs"
        :key="tab"
        :class="['tab-item', { 'active': activeTab === tab }]"
        @click="activeTab = tab"
      >
        {{ tab }}
      </span>
    </div>

    <!-- 公告时间线 -->
    <el-timeline>
      <el-timeline-item
        v-for="(notice, index) in filteredNotices"
        :key="notice.id"
        :timestamp="notice.date"
        placement="top"
        :color="colors[index % colors.length]"
      >
        <el-card
          class="notice-card"
          @mouseenter="hoverIndex = index"
          @mouseleave="hoverIndex = -1"
          :style="cardHoverStyle(index)"
        >
          <div class="card-header">
            <span class="title">
              <i :class="noticeIcon(notice.type)" class="icon"></i>
              {{ notice.title }}
            </span>
            <el-tag
              :type="noticeTypeStyle(notice.type)"
              effect="dark"
              size="small"
            >
              {{ notice.type }}
            </el-tag>
          </div>

          <div class="content" :class="{ 'collapsed': !notice.expand }">
            {{ notice.content }}
          </div>

          <div class="card-footer">
            <span class="read-more" @click="toggleExpand(notice)">
              {{ notice.expand ? '收起' : '展开' }}
              <i :class="notice.expand ? 'fa fa-chevron-up' : 'fa fa-chevron-down'"></i>
            </span>
            <span class="author">{{ notice.author }}</span>
          </div>
        </el-card>
      </el-timeline-item>
    </el-timeline>
  </div>
</template>



<style scoped>
.notice-container {
  max-width: 800px;
  margin: 20px auto;
  padding: 20px;
}

.filter-tabs {
  margin-bottom: 30px;
  text-align: center;
}

.tab-item {
  display: inline-block;
  padding: 8px 20px;
  margin: 0 10px;
  cursor: pointer;
  border-radius: 20px;
  transition: all 0.3s;
  color: #666;
}

.tab-item.active {
  background: #7238d1;
  color: white;
  box-shadow: 0 4px 12px rgba(114,56,209,0.3);
}

.notice-card {
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
  border-radius: 12px;
  margin: 10px 0;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 12px;
}

.title {
  font-size: 16px;
  font-weight: 600;
  color: #333;
}

.title .icon {
  margin-right: 8px;
  color: #7238d1;
}

.content {
  color: #666;
  line-height: 1.6;
  max-height: 3em;
  overflow: hidden;
  transition: all 0.3s;
}

.content.collapsed {
  max-height: 3em;
}

.card-footer {
  margin-top: 15px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  color: #999;
  font-size: 0.9em;
}

.read-more {
  cursor: pointer;
  color: #7238d1;
  transition: color 0.3s;
}

.read-more:hover {
  color: #5a2ca8;
}

.author {
  font-style: italic;
}

/* 时间线样式自定义 */
:deep(.el-timeline-item__timestamp) {
  color: #666;
  font-size: 0.9em;
}

:deep(.el-timeline-item__node) {
  background: #7238d1;
  width: 16px;
  height: 16px;
}
</style>