<template>
  <div class="user-management-container" :class="{ 'dark-mode': isDarkMode }">
    <!-- 顶部导航 -->
    <nav class="glass-nav">
      <h1 class="logo">👑 User Admin</h1>
      <div class="nav-controls">
        <el-switch
          v-model="isDarkMode"
          active-text="暗夜魅影"
          inactive-text="晨曦之光"
          class="theme-switch"
        />
      </div>
    </nav>

    <!-- 主内容区 -->
    <main class="content-wrapper">
      <!-- 操作栏 -->
      <div class="action-bar">
        <el-input
          v-model="searchQuery"
          placeholder="搜索用户..."
          class="search-input"
          :prefix-icon="Search"
          clearable
        />
        <el-button type="primary" class="add-btn" @click="showAddDialog">
          <i class="fas fa-magic"></i>
          创建新用户
        </el-button>
      </div>

      <!-- 用户表格 -->
      <el-table
        :data="filteredUsers"
        style="width: 100%"
        class="user-table"
        :row-class-name="tableRowClassName"
      >
        <el-table-column label="用户信息" width="300">
          <template #default="{ row }">
            <div class="user-info">
              <el-avatar :size="45" :src="row.avatar" class="user-avatar" />
              <div class="user-details">
                <h3 class="username">{{ row.name }}</h3>
                <p class="email">{{ row.email }}</p>
              </div>
            </div>
          </template>
        </el-table-column>

        <el-table-column prop="role" label="角色" width="150">
          <template #default="{ row }">
            <el-tag :type="roleTagType(row.role)" effect="dark">
              {{ row.role }}
            </el-tag>
          </template>
        </el-table-column>

        <el-table-column label="状态" width="120">
          <template #default="{ row }">
            <div class="status-indicator">
              <div :class="['status-dot', row.status]" />
              <span>{{ statusLabel[row.status as 'active' | 'inactive' | 'suspended'] }}</span>
            </div>
          </template>
        </el-table-column>

        <el-table-column prop="lastLogin" label="最后登录" width="180" />

        <el-table-column label="操作" width="200">
          <template #default="{ row }">
            <div class="action-buttons">
              <el-button
                type="info"
                circle
                @click="viewDetails(row)"
                class="btn-hover"
              >
                <i class="fas fa-eye"></i>
              </el-button>
              <el-button
                type="warning"
                circle
                @click="editUser(row)"
                class="btn-hover"
              >
                <i class="fas fa-pen"></i>
              </el-button>
              <el-button
                type="danger"
                circle
                @click="confirmDelete(row)"
                class="btn-hover"
              >
                <i class="fas fa-trash"></i>
              </el-button>
            </div>
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页 -->
      <div class="pagination-wrapper">
        <el-pagination
          background
          layout="prev, pager, next"
          :total="filteredUsers.length"
          class="glass-pagination"
        />
      </div>
    </main>
  </div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'

interface User {
  id: number
  avatar: string
  name: string
  email: string
  role: string
  status: 'active' | 'inactive' | 'suspended'
  lastLogin: string
}

// 示例数据
const users = ref<User[]>([
  {
    id: 1,
    avatar: 'https://picsum.photos/50/50?1',
    name: '南宫婉',
    email: 'wan@cloudrealm.cn',
    role: '管理员',
    status: 'active',
    lastLogin: '2024-02-20 14:30'
  },
  {
    id: 2,
    avatar: 'https://picsum.photos/50/50?2',
    name: '凌霄',
    email: 'xiao@starcity.com',
    role: '开发者',
    status: 'inactive',
    lastLogin: '2024-02-19 09:15'
  },
  // 更多示例数据...
])

const searchQuery = ref('')
const isDarkMode = ref(true)

const statusLabel = {
  active: '在线',
  inactive: '离线',
  suspended: '已禁用'
}

const filteredUsers = computed(() => {
  return users.value.filter(user =>
    user.name.toLowerCase().includes(searchQuery.value.toLowerCase()) ||
    user.email.toLowerCase().includes(searchQuery.value.toLowerCase()))
})

const roleTagType = (role: string) => {
  const types: Record<string, string> = {
    '管理员': 'danger',
    '开发者': 'warning',
    '编辑': 'success',
    '用户': 'info'
  }
  return types[role] || 'info'
}

const confirmDelete = (user: User) => {
  ElMessageBox.confirm(
    `确定要永久删除用户 ${user.name} 吗？`,
    '删除确认',
    {
      confirmButtonText: '确认',
      cancelButtonText: '取消',
      type: 'warning',
      center: true,
      customClass: 'delete-confirm-dialog'
    }
  ).then(() => {
    users.value = users.value.filter(u => u.id !== user.id)
    ElMessage.success(`${user.name} 已删除`)
  })
}

const tableRowClassName = ({ row }: { row: User }) => {
  return row.status === 'suspended' ? 'suspended-row' : ''
}
const viewDetails = (user: User) => {
  ElMessage.info(`查看用户 ${user.name} 的详细信息`)
}
const editUser = (user: User) => {
  ElMessage.info(`编辑用户 ${user.name} 的信息`)
}
const showAddDialog = () => {
  ElMessage.info('创建新用户')
}
const toggleDarkMode = () => {
  isDarkMode.value = !isDarkMode.value
}

const Search = () => {
  // 搜索逻辑
}


</script>

<style lang="scss" scoped>
$primary-gradient: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
$glass-bg: rgba(255, 255, 255, 0.1);
$dark-bg: #0f172a;

.user-management-container {
  min-height: 100vh;
  transition: background 0.3s ease;

  &.dark-mode {
    background: $dark-bg;
    color: #fff;

    .glass-nav {
      background: rgba($dark-bg, 0.8);
    }
  }

  .glass-nav {
    backdrop-filter: blur(10px);
    background: $glass-bg;
    padding: 1rem 2rem;
    display: flex;
    justify-content: space-between;
    align-items: center;
    box-shadow: 0 4px 30px rgba(0, 0, 0, 0.1);
    border-bottom: 1px solid rgba(255, 255, 255, 0.1);

    .logo {
        font-family: 'Helvetica Neue', sans-serif;
        background: $primary-gradient;
        background-clip: text; // 添加标准属性
        -webkit-background-clip: text; // 保留WebKit前缀
        -webkit-text-fill-color: transparent;
        margin: 0;
      }
  }

  .content-wrapper {
    max-width: 1400px;
    margin: 2rem auto;
    padding: 0 2rem;

    .action-bar {
      display: flex;
      gap: 1rem;
      margin-bottom: 2rem;

      .search-input {
        flex: 1;
        :deep(.el-input__inner) {
          background: $glass-bg;
          border: none;
          backdrop-filter: blur(5px);
        }
      }

      .add-btn {
        background: $primary-gradient;
        border: none;
        transition: transform 0.2s ease;

        &:hover {
          transform: translateY(-2px);
        }
      }
    }

    .user-table {
      background: transparent !important;

      :deep(.el-table__body tr:hover) {
        td {
          background: rgba(255, 255, 255, 0.05) !important;
        }
      }

      .user-info {
        display: flex;
        align-items: center;
        gap: 1rem;

        .user-avatar {
          box-shadow: 0 0 15px rgba(255, 255, 255, 0.1);
        }

        .username {
          margin: 0;
          font-weight: 600;
        }

        .email {
          margin: 0;
          opacity: 0.8;
          font-size: 0.9em;
        }
      }

      .status-indicator {
        display: flex;
        align-items: center;
        gap: 0.5rem;

        .status-dot {
          width: 10px;
          height: 10px;
          border-radius: 50%;

          &.active { background: #10b981; }
          &.inactive { background: #64748b; }
          &.suspended { background: #ef4444; }
        }
      }

      .action-buttons {
        .btn-hover {
          transition: all 0.3s ease;

          &:hover {
            transform: scale(1.1);
            box-shadow: 0 0 15px currentColor;
          }
        }
      }
    }

    .pagination-wrapper {
      margin-top: 2rem;
      display: flex;
      justify-content: center;
    }
  }
}

// 自定义对话框样式
:deep(.delete-confirm-dialog) {
  background: $dark-bg !important;
  border: 1px solid rgba(255, 255, 255, 0.1) !important;
  color: white !important;

  .el-message-box__title {
    color: white !important;
  }
}
</style>