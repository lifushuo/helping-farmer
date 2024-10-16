// import request from "@/utils/request.ts";
import {
    EmployeePage,
    EmployeeData,
    EmployeeDetailData,
    EditDataType,
} from '@/api/employee/type.ts'

import { ResponseData } from '@/api'

import { request2 } from '@/utils/request2.ts'

enum API {
    EMPLOYEE_PAGE = '/admin/employee/page',
    EMPLOYEE_ADMIN_URL = '/admin/employee',
    EMPLOYEE_STATUS = '/admin/employee/status/',
}

export const getAllEmployeePage = async (
    page: number,
    pageSize: number,
    name?: string,
) => {
    return await request2.get<any, ResponseData<EmployeePage>>(
        API.EMPLOYEE_PAGE + `?page=${page}&pageSize=${pageSize}&name=${name}`,
    )
}

export const postEmployeeAdd = async (param: EmployeeData) => {
    return await request2.post<any, ResponseData<any>>(
        API.EMPLOYEE_ADMIN_URL,
        param,
    )
}

export const getEmployeeDetailById = async (employeeId: number) => {
    return await request2.get<any, ResponseData<EmployeeDetailData>>(
        API.EMPLOYEE_ADMIN_URL + '/' + employeeId,
    )
}

/**
 * 更新用户信息
 * @param params
 */
export const putEmployeeEdit = async (params: EditDataType) => {
    return request2.put<any, ResponseData<any>>(API.EMPLOYEE_ADMIN_URL, params)
}

/**
 * 启用或者禁用 用户状态
 * @param employeeId 用户id
 */
export const postEmployeeStatus = async (employeeId: number) => {
    return await request2.post<any, ResponseData<any>>(
        API.EMPLOYEE_STATUS + employeeId,
    )
}
