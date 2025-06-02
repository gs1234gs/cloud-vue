
<script lang="ts" setup>
  import { useRoute, useRouter } from 'vue-router'
  import { mapHamburger } from '@/utils/map-menus'
  import { cacheLocal } from '@/utils/cache'
  import { USER_MENUS } from '@/global/constants'
import { computed } from 'vue'

  const route = useRoute()
  const router = useRouter()
  const userMenus = cacheLocal.getCache(USER_MENUS)
  const mapHambargers = computed (()=>{
    return mapHamburger(route.path,userMenus)
  })


</script>


<template>
      <div>
        <!-- <el-icon><Promotion />ArrowRight</el-icon> -->
        <!-- <el-icon><ArrowRightBold /></el-icon> -->
        <el-breadcrumb separator-icon="ArrowRightBold">
          <template v-for="item in mapHambargers" :key="item.menuName">

            <el-breadcrumb-item :to="{ path: item.path }">{{ item.menuName }}</el-breadcrumb-item>
          </template>
    <!-- <el-breadcrumb-item :to="{ path: '/' }">homepage</el-breadcrumb-item>
    <el-breadcrumb-item>promotion management</el-breadcrumb-item>
    <el-breadcrumb-item>promotion list</el-breadcrumb-item>
    <el-breadcrumb-item>promotion detail</el-breadcrumb-item> -->
  </el-breadcrumb>
      </div>

</template>

