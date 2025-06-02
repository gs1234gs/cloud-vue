// 定义 formItem 的类型
interface FormItem {
  type: 'input' | 'select' | 'date-picker'; // 限制类型为 input, select 或 date-picker
  label: string; // 字段标签
  prop: string; // 字段绑定的属性名
  placeholder?: string; // 占位符（可选）
  initValue?: string; // 初始值（可选）
}

const searchConfig = {
  pageName: 'menu',
  formItem: [{
    type : 'input',
    label : '菜单名称',
    prop : 'deptName',
    placeholder : '请输入查询菜单名称',
    initValue : '',

  },
  {
    type : 'date-picker',
    label : '创建时间',
    prop :'createTime',
  }
  ]
}

export default searchConfig