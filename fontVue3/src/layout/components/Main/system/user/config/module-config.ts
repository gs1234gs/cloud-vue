import type { IModuleConfig } from "@/components/base-module-form/config-module/type";

const moduleConfig:IModuleConfig = {
 pageName: "user",
  header: {
    newTitle: "新建用户",
    editTitle: "编辑用户",
  },
  formItem: [
    {
      type: "input",
      label: "用户名",
      prop: "userName",
      width: "120px",
      initValue: "",
    },{
      type: "input",
      label: "昵称",
      prop: "nickName",
      width: "100px",
      initValue: "",
    },{
      type: "input",
      label: "性别",
      prop: "gender",
      width: "60px",
      initValue: "",
    },{
      type: "input",
      label: "状态",
      prop: "status",
      width: "60px",
      initValue: "",
    },{
      type: "select",
      label: "用户类型",
      prop: "userType",
      width: "100px",
      initValue: "",
    },{
      type: "input",
      label: "手机号",
      prop: "phoneNumber",
      width: "150px",
      initValue: "",
    },{
      type: "input",
      label: "用户描述",
      prop: "remark",
      width: "100px",
    }

  ]

};

export default moduleConfig;
