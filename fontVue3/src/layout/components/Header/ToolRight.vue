<script setup lang="ts">

import Avatar from './components/right/Avatar.vue'
import GlobalComSize from './components/right/GlobalComSize.vue'
import HeaderSearch from './components/right/HeaderSearch.vue'
import Remind from './components/right/Remind.vue'
import ScreenFull from './components/right/ScreenFull.vue'
import Setting from './components/right/Setting.vue'

</script>
<!-- Header right -->

<template>
  <div class="m-tool-right">
    <GlobalComSize class="item-children" />
    <HeaderSearch class="item-children" />
    <Remind class="item-children" />
    <ScreenFull class="item-children" />
    <Setting class="item-children" />
    <Avatar />
  </div>
</template>

<style lang="scss" scroped>
  .m-tool-right {
    display: flex;
    align-items: center;

    .item-children {
      margin-right: 22px;
    }
  }

</style>