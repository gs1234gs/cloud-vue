<script lang="ts" setup>
import AvatarLogo from '@/assets/images/user/avatar.png'
import { cacheLocal } from '@/utils/cache'
import { useRouter } from 'vue-router'

const router = useRouter()
//退出登录，清空缓存
const handleLogout = () => {
  // cacheLocal.removeCache(LOGIN_TOKEN);
  cacheLocal.clearCache()
  console.log('退出登录')
  router.push('/login')
}

</script>

<template>

  <div class="user-dropdown">
    <el-dropdown>
      <span class="el-dropdown-link">
        <el-avatar :size="30"  class="avatar" :src="AvatarLogo" />
        <span class="username">admin</span>
        <el-icon class="header-icon">
          <arrow-down />
        </el-icon>
      </span>
      <template #dropdown>
        <el-dropdown-menu class="user-menu">

          <el-dropdown-item :command="0">
            <el-icon class="menu-icon"><UserFilled /></el-icon>
            <span class="menu-item">管理员</span>
          </el-dropdown-item>

          <el-dropdown-item :command="1" divided>
             <el-icon class="menu-icon"><User /></el-icon>
            <span class="menu-item">普通用户</span>
          </el-dropdown-item>

          <el-dropdown-item :command="2" divided>
            <el-icon class="menu-icon"><Promotion /></el-icon>
            <span class="menu-item">免密登录</span>
          </el-dropdown-item>

          <el-dropdown-item :command="2" divided>
            <el-icon class="menu-icon"><InfoFilled /></el-icon>
            <span class="menu-item">个人信息</span>
          </el-dropdown-item>

          <el-dropdown-item :command="3" divided>
            <el-icon class="menu-icon"><Lock /></el-icon>
            <span class="menu-item">修改密码</span>
          </el-dropdown-item>

          <el-dropdown-item :command="4" divided @click="handleLogout">
            <el-icon class="menu-icon"><SwitchButton /></el-icon>
            <span class="menu-item">退出登录</span>
          </el-dropdown-item>

        </el-dropdown-menu>
      </template>
    </el-dropdown>
  </div>
</template>

<style lang="scss" scoped>
@use "./avatar/avatar.scss"
</style>