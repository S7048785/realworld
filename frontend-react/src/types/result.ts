export type Data<T> = {
	code: number;
	msg: string;
	data: T;
};

export type PageData<T> = Promise<{
	page: number;
	page_size: number;
	total: number;
	list: T[];
}>;

export type Result<T> = Promise<Data<T>>;