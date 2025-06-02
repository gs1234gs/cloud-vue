import dayjs from "dayjs";

export const formatDate = (dateTime: string,format:string = "YYYY年MM月DD日 HH:mm:ss") => {
  return dayjs(dateTime).format(format);
};