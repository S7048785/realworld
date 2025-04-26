
export interface Result {
  code: number;
  msg: string;
  data: object;
}

export interface PageResult <T> {
  total: number;
  records: T[];
  page: number;
  pageSize: number;
}
