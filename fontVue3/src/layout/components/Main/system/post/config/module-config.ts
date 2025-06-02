import type { IModuleConfig } from "@/components/base-module-form/config-module/type";
import { h } from "vue";
export const moduleConfig:IModuleConfig = {
 pageName: "role",
  header: {
    newTitle: "新建岗位",
    editTitle: "编辑岗位"
  } ,
   formItem: [
    {
      type: "input",
      label: "岗位名称",
      prop: "postName",
      width: "150px",
      placeholder: "请输入岗位名称",
    },
    {
      type: "input",
      label: "岗位代号",
      prop: "postCode",
      placeholder: "请输入岗位代号",
    }
  ],

}