const searchConfig = {

  pageName: 'role',
  formItem: [{
    type : 'input',
    label : '角色名称',
    prop : 'roleName',
    placeholder : '请输入查询角色名称',
    initValue : '',

  },
  {
    type : 'input',
    label : '权限介绍',
    prop : 'remark',
    placeholder : '请输入要查询的权限介绍',

  },
  {
    type : 'date-picker',
    label : '创建时间',
    prop :'createTime',
  }
  ]

}

export default searchConfig