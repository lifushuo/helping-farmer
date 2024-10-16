export interface EmployeePage {
    records: Employee[]
    total: number
    [property: string]: any
}

export interface Employee {
    createTime: null | string
    createUser: number | null
    id: number
    idNumber: string
    name: string
    password: string
    phone: string
    sex: string
    status: number
    updateTime: string
    updateUser: number
    username: string
    [property: string]: any
}

export interface EmployeeData {
    /**
     * 员工id
     */
    id?: number
    /**
     * 身份证
     */
    idNumber: string
    /**
     * 姓名
     */
    name: string
    /**
     * 手机号
     */
    phone: string
    /**
     * 性别 女0 男 1
     */
    sex: string
    /**
     * 用户名
     */
    username: string
    [property: string]: any
}

/**
 * Employee
 */
export interface EmployeeDetailData {
    createTime?: Date
    createUser?: number
    id?: number
    idNumber?: string
    name?: string
    password?: string
    phone?: string
    sex?: string
    status?: number
    updateTime?: Date
    updateUser?: number
    username?: string
    [property: string]: any
}

export type EditDataType = {
    id: number
    idNumber: string
    name: string
    phone: string
    sex: string
    username: string
    [property: string]: any
}
