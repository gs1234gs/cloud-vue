const searchConfig = {

  pageName: 'role',
  formItem: [{
    type : 'input',
    label : '角色名称',
    prop : 'deptName',
    placeholder : '请输入查询角色名称',
    initValue : '',

  },
  {
    type : 'input',
    label : '权限介绍',
    prop : 'leader',
    placeholder : '请输入要查询的权限介绍',

  },
  {
    type : 'data-picker',
    label : '创建时间',
    prop :'createTime',
  }
  ]

}

export default searchConfig