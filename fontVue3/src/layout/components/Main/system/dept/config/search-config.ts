// 定义 formItem 的类型
interface FormItem {
  type: 'input' | 'select' | 'date-picker'; // 限制类型为 input, select 或 date-picker
  label: string; // 字段标签
  prop: string; // 字段绑定的属性名
  placeholder?: string; // 占位符（可选）
  initValue?: string; // 初始值（可选）
}

const searchConfig = {
  pageName: 'dept',
  formItem: [{
    type : 'input',
    label : '部门名称',
    prop : 'deptName',
    placeholder : '请输入查询部门名称',
    initValue : '',

  },
  {
    type : 'input',
    label : '部门领导',
    prop : 'leader',
    placeholder : '请输入要查询的领导姓名',

  },
  {
    type : 'date-picker',
    label : '创建时间',
    prop :'createTime',
  }
  ]
}

export default searchConfig