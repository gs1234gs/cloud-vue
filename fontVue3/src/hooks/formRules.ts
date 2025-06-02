import { reactive } from "vue"

export const useFormRules = ()=>{
  // 添加验证规则
const formRules = reactive({
  deptName: [
    { required: true, message: '用户名不能为空', trigger: 'blur' },
    { min: 4, max: 60, message: '用户名长度为4-60个字符', trigger: 'blur' }
  ],
  leader: [
    { required: true, message: '姓名不能为空', trigger: 'blur' },
    { min: 1, max: 100, message: '姓名长度为1-100个字符', trigger: 'blur' }
  ],
  phoneNumber: [
    { required: true, message: '手机号不能为空', trigger: 'blur' },
    {
      pattern: /^1[3-9]\d{9}$/,
      message: '请输入正确的手机号码格式',
      trigger: 'blur'
    }
  ],
  // idCard: [
  //   { required: true, message: '身份证号不能为空', trigger: 'blur' },
  //   {
  //     pattern: /^[1-9]\d{5}(18|19|20)\d{2}(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])\d{3}[\dXx]$/,
  //     message: '请输入正确的身份证号码格式',
  //     trigger: 'blur'
  //   }
  // ],
  // deptId: [
  //   { required: true, validator: (_:any, value:any, callback:any) => {
  //     if (!selectedCompany.value) {
  //       callback(new Error('请先选择公司'))
  //     } else if (!value) {
  //       callback(new Error('请选择部门'))
  //     } else {
  //       callback()
  //     }
  //   }, trigger: 'change' }
  // ],
  // userType: [
  //   { required: true, message: '请选择用户类型', trigger: 'change' }
  // ]
})
return {
  formRules
}
}