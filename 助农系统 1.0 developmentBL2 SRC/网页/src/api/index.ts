export type ResponseData<T> = {
    code: number
    data: T
    msg?: null
    [property: string]: any
}

export type PageData<T> = {
    records: T[]
    total: number
}
