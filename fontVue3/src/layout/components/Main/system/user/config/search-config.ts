// 定义 formItem 的类型
interface FormItem {
  type: 'input' | 'select' | 'date-picker'; // 限制类型为 input, select 或 date-picker
  label: string; // 字段标签
  prop: string; // 字段绑定的属性名
  placeholder?: string; // 占位符（可选）
  initValue?: string; // 初始值（可选）
}

const searchConfig = {
  isPage: true,
  pageName: 'user',
  formItem: [{
    type : 'input',
    label : '用户名',
    prop : 'userName',
    placeholder : '请输入查询用户名',
    initValue : '',

  },
  {
    type : 'input',
    label : '手机号',
    prop : 'phoneNumber',
    placeholder : '请输入要查询的手机号',

  },
  {
    type : 'input',
    label : '身份证',
    prop : 'idCard',
    placeholder : '请输入要查询的身份证号',

  },
  {
    type : 'date-picker',
    label : '创建时间',
    prop :'createTime',
  }
  ]
}

export default searchConfig