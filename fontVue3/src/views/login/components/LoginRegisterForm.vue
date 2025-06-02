<script lang="ts" setup name="LoginForm">
  import LoginViews from './loginViews/LoginViews.vue';
  import RegisterViews from './RegisterViews/RegisterViews.vue';

</script>

<template>

<div class="tabs-container">
      <!-- <el-tabs class="tabs custom-tabs" type="border-card" stretch> -->
    <!-- <el-tab-pane>
      <template #label>
          <el-icon style="width: auto;height: 30px;"><UserFilled /></el-icon>
          <span>账号登录</span>
      </template> -->
<LoginViews/>
     <!-- </el-tab-pane> -->

    <!-- <el-tab-pane label="用户注册">
      <template #label>
          <el-icon style="width: auto;"><Iphone /></el-icon>
          <span>手机登录</span>
      </template>
    <RegisterViews />
    </el-tab-pane> -->
  <!-- </el-tabs> -->

    </div>

</template>

<style lang="scss" scoped>
 @use '../login-index.scss';


/* 自定义 el-tabs 样式，去除边框 */
::v-deep(.custom-tabs) {
  .el-tabs__header {
    border: none; /* 移除 header 的边框 */
    border-radius: 12px 12px 0 0; /* 顶部圆角 */
    overflow: hidden; /* 确保子元素不会超出圆角边界 */
    background-color: #f5f5f5; /* 可选：设置背景颜色 */
  }

  .el-tabs__content {
    border: none; /* 移除 content 的边框 */
    border-radius: 0 0 12px 12px; /* 底部圆角 */
    overflow: hidden; /* 确保内容不会超出圆角边界 */
    background-color: #ffffff; /* 可选：设置内容背景颜色 */
  }

  /* 如果 el-tabs 本身有外边框，也一并移除 */
  &.el-tabs {
    border: none;
  }
}

/* 调整 el-tabs 的位置（如果需要） */
.tabs-container {
  margin-top: 79px; /* 往上移动 20px，可以根据需要调整数值 */
}
</style>
