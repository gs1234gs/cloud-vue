<script lang="ts" setup name="LoginViews">
  import { ref, reactive ,watch,onMounted} from 'vue'
  import  {type FormInstance } from 'element-plus'
  import { UserFilled, GoodsFilled } from '@element-plus/icons-vue';
  import { useLoginStore } from '@/stores/modules/login/login';
  import {type loginUserType } from '@/types/loginType/loginType';
import { cacheLocal } from '@/utils/cache';
import CloverLoader from './components/CloverLoader.vue';
import LoaderBeautiful from './components/LoaderBeautiful.vue';
import LoaderBestBeauty from './components/LoaderBestBeauty.vue';

const isAppLoading = ref(true);
  const ruleFormRef = ref<FormInstance>()
  const passwordType = ref('password')
  const loading = ref(false)

  // 缓存相关
  const CACHE_NAME = 'userName'
  const CACHE_PASSWORD = 'password'

// 引入登录接口
  const loginStore = useLoginStore()
  const isRememberPwd = ref(<boolean>cacheLocal.getCache("isRememberPwd")??'false')

// 模拟应用加载
onMounted(() => {
  setTimeout(() => {
    isAppLoading.value = false;
  }, 2000); // 2秒后隐藏加载动画
});
  // 记住密码

  watch(isRememberPwd, (newValue) => {
    if (newValue) {
      cacheLocal.setCache("isRememberPwd",newValue)
    } else {
      cacheLocal.removeCache("isRememberPwd")
    }
  })
  // 表单验证规则
  const rules = reactive({
    userName : [{ required: true, message: '请输入用户名', trigger: 'blur' },
                { min: 3, max: 100, message: '用户名在 4 到 30位之间', trigger: 'blur' }],
    password : [ { required: true, message: '请输入密码', trigger: 'blur' },
                { min: 4, max: 30, message: '密码在 4 到 30位之间', trigger: 'blur' }],
  })

  // 表单数据
  const ruleForm = reactive<loginUserType>({
    userName: cacheLocal.getCache(CACHE_NAME)??'admin',
    password:cacheLocal.getCache(CACHE_PASSWORD)?? '123456',
  })

  // 显示密码图标
  const showPwd = () => {
    passwordType.value = passwordType.value === 'password' ? '' : 'password'
  }
  // 延迟函数
  function sleep(ms: number): Promise<void> {
  return new Promise((resolve) => setTimeout(resolve, ms));
}
// 提交表单
const submitLoginForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.validate(async (valid) => {
    if (valid) {
      loading.value = true; // 开始加载

      console.log("Sending request with:", ruleForm); // 添加日志
       // 准备登录参数
  const loginData = {
    userName: ruleForm.userName, // 替换为实际的用户名
    password: ruleForm.password,  // 替换为实际的密码
  };
      try {
        await sleep(1000); // 模拟延迟请求
          loginStore.loginAccountAction(loginData).then(() => {
            if (isRememberPwd.value){
              cacheLocal.setCache(CACHE_NAME,loginData.userName)
              cacheLocal.setCache(CACHE_PASSWORD,loginData.password)
            }
            else{
              cacheLocal.removeCache(CACHE_NAME)
              cacheLocal.removeCache(CACHE_PASSWORD)
            }
          });

      } catch (error) {
        console.error('请求失败:', error);
        // alert('请求失败，请检查网络或联系管理员');
      } finally {
        loading.value = false; // 无论成功或失败，都结束加载状态
      }
    } else {
      console.log('验证失败');
    }
  });
};



const submitForget = () => {
  alert('忘记密码')
}

defineExpose({
  submitLoginForm
})

</script>



<template>
  <!-- 登录表单容器，添加过渡效果 -->
 <!-- 添加四叶草加载组件 -->
 <!-- <CloverLoader :isLoading="isAppLoading" /> -->
  <LoaderBeautiful :isLoading="isAppLoading" />
   <!-- <LoaderBestBeauty :isLoading="isAppLoading" /> -->
  <!-- 登录表单容器，添加过渡效果 -->
  <transition name="fade-slide">
  <!-- 用户名表单 -->
  <el-form ref="ruleFormRef" :model="ruleForm" :rules="rules">
    <el-form-item label="" prop="userName">
      <el-input
        v-model="ruleForm.userName"
        placeholder="请输入用户名"
        auto-complete="on"
        style="position: relative"
        @keyup.enter="submitLoginForm(ruleFormRef)"
      >
      <!-- 用户名前缀图标 -->
        <template #prefix>
          <el-icon class="el-input__icon"><UserFilled /></el-icon>
        </template>
      </el-input>
    </el-form-item>
<!-- 密码输入框 -->
    <el-form-item label="" prop="password">
      <el-input
        v-model="ruleForm.password"
        placeholder="请输入密码"
        auto-complete="on"
        :type="passwordType"
        @keyup.enter="submitLoginForm(ruleFormRef)"
      >
      <!-- 密码前缀图标 -->
        <template #prefix>
          <el-icon class="el-input__icon"><GoodsFilled /></el-icon>
        </template>
        <!-- 显示/隐藏密码后缀图标 -->
        <template #suffix>
          <div class="show-pwd" @click="showPwd">
            <!-- <base-icon :name="passwordType === 'password' ? 'eye' : 'eye-open'" /> -->
            <!-- <el-icon><View /></el-icon> -->
            <el-icon v-if="passwordType === 'password'"><View /></el-icon>
            <el-icon v-else><Hide /></el-icon>
          </div>
        </template>
      </el-input>
    </el-form-item>
<!-- 登录按钮 -->
    <el-form-item style="width: 100%">
      <el-button :loading="loading" class="login-btn" type="primary" @click="submitLoginForm(ruleFormRef)">立即登录</el-button>

    </el-form-item>
    <el-form-item>
      <el-checkbox class="remember-pwd" v-model="isRememberPwd" label="记住密码" size="large" />
      <el-link class="forget-btn" type="primary" @click="submitForget()">忘记密码</el-link>
    </el-form-item>
<!-- 忘记密码按钮 -->

      <!-- <el-button  class="forget-btn" type="primary" @click="submitForget()">

      </el-button> -->


  </el-form>
</transition>
</template>

<style lang="scss" scoped>
 @use '../../login-index.scss';

 /* 登录容器过渡效果 */
.fade-slide-enter-active,
.fade-slide-leave-active {
  transition: all 0.8s ease;
}

.fade-slide-enter-from,
.fade-slide-leave-to {
  opacity: 0;
  transform: translateY(30px);
}

</style>
