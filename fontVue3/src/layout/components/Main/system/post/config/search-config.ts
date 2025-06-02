const searchConfig = {

  pageName: 'role',
  formItem: [{
    type : 'input',
    label : '岗位名称',
    prop : 'postName',
    placeholder : '请输入查询岗位名称',
    initValue : '',

  },
  {
    type : 'input',
    label : '岗位代号',
    prop : 'leader',
    placeholder : '请输入要查询的岗位代号'

  },
  {
    type : 'date-picker',
    label : '创建时间',
    prop :'createTime',
  }
  ]

}

export default searchConfig