<script lang="ts" setup name="LoginViews">
  import { ref, reactive } from 'vue'
  import { type FormInstance } from 'element-plus'
  import { UserFilled, Message} from '@element-plus/icons-vue'
  import axios from 'axios'

  // 定义后端返回数据的类型
  interface LoginResponse {
    code: number
    msg?: string
    data?: { id: number; phone: string }[]
  }

  const ruleFormRef = ref<FormInstance>()
  const loading = ref(false)
  const isSendingCode = ref(false) // 是否正在发送验证码
  const countdown = ref(0) // 验证码发送倒计时

  const rules = reactive({
    phone: [
      { required: true, message: '请输入手机号码', trigger: 'blur' },
      { pattern: /^1[3-9]\d{9}$/, message: '请输入正确的手机号码', trigger: 'blur' },
    ],
    code: [
      { required: true, message: '请输入验证码', trigger: 'blur' },
      { len: 6, message: '验证码为6位数字', trigger: 'blur' },
    ],
  })

  // 表单数据
  const ruleForm = reactive({
    phone: '',
    code: '',
  })

  // 发送验证码
  const sendCode = async () => {
    if (!ruleForm.phone || !/^1[3-9]\d{9}$/.test(ruleForm.phone)) {
      alert('请输入正确的手机号码')
      return
    }

    if (isSendingCode.value || countdown.value > 0) return

    isSendingCode.value = true
    try {
      // 发起请求发送验证码
      const response = await axios.post('/api/send-code', {
        phone: ruleForm.phone,
      })

      if (response.data.code === 200) {
        alert('验证码已发送')
        countdown.value = 60 // 开始倒计时
        const timer = setInterval(() => {
          countdown.value--
          if (countdown.value <= 0) {
            clearInterval(timer)
            isSendingCode.value = false
          }
        }, 1000)
      } else {
        alert(`发送验证码失败: ${response.data.msg}`)
      }
    } catch (error) {
      console.error('请求失败:', error)
      alert('请求失败，请检查网络或联系管理员')
    } finally {
      isSendingCode.value = false
    }
  }

  // 提交表单
  const submitLoginForm = (formEl: FormInstance | undefined) => {
    if (!formEl) return
    formEl.validate(async (valid) => {
      if (valid) {
        loading.value = true // 开始加载
        try {
          // 发起 POST 请求，将表单数据发送到后端
          const response = await axios.post<LoginResponse>('/api/login-with-code', {
            phone: ruleForm.phone,
            code: ruleForm.code,
          })

          // 检查后端返回的 code 字段
          if (response.data.code === 200) {
            console.log('登录成功:', response.data)
            alert('登录成功')
            // 在这里处理登录成功后的逻辑，比如跳转页面或存储 token
          } else {
            console.error('登录失败:', response.data.code)
            alert(`登录失败: ${response.data.msg}`)
          }
        } catch (error) {
          console.error('请求失败:', error)
          alert('请求失败，请检查网络或联系管理员')
        } finally {
          loading.value = false // 无论成功或失败，都结束加载状态
        }
      } else {
        console.log('验证失败')
      }
    })
  }
</script>

<template>
  <!-- 手机号码表单 -->
  <el-form ref="ruleFormRef" :model="ruleForm" :rules="rules">
    <el-form-item label="" prop="phone" >
      <el-input
        v-model="ruleForm.phone"
        placeholder="请输入手机号码"
        auto-complete="on"
        style="position: relative"
        @keyup.enter="submitLoginForm(ruleFormRef)"
      >
        <!-- 手机号码前缀图标 -->
        <template #prefix>
          <el-icon class="el-input__icon"><UserFilled /></el-icon>
        </template>
      </el-input>
    </el-form-item>

    <!-- 验证码输入框 -->
    <el-form-item label="" prop="code">
      <el-input
        v-model="ruleForm.code"
        placeholder="请输入验证码"
        auto-complete="on"
        style="position: relative"
        @keyup.enter="submitLoginForm(ruleFormRef)"
      >
        <!-- 验证码前缀图标 -->
        <template #prefix>
          <el-icon class="el-input__icon"><Message /></el-icon>
        </template>
        <!-- 发送验证码按钮 -->
        <template #suffix>
          <el-button
            :disabled="isSendingCode || countdown > 0"
            @click="sendCode"
            class="send-code-btn"
          >
            {{ countdown > 0 ? `${countdown}s` : '发送验证码' }}
          </el-button>
        </template>
      </el-input>
    </el-form-item>

    <!-- 登录按钮 -->
    <el-form-item style="width: 100%">
      <el-button :loading="loading" class="login-btn" type="primary" @click="submitLoginForm(ruleFormRef)">
        登录
      </el-button>
    </el-form-item>
  </el-form>
</template>

<style lang="scss" scoped>
 @use '../../login-index.scss';
  .send-code-btn {
    margin-left: 10px;
  }

  .login-btn {
    width: 100%;
  }
</style>