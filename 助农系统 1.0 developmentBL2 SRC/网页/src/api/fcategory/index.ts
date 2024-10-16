import { request2 } from '@/utils/request2.ts'
import { PageData, ResponseData } from '@/api'
import {
    FCategory,
    FCategoryAddDTO,
    FCategoryList,
    FCategoryPage,
} from '@/api/fcategory/type.ts'

enum API {
    FCATEGORY_PAGE = '/admin/category1/page',
    FCATEGORY_BASE = '/admin/category1',
    FCATEGORY_STATUS = '/admin/category1/status',
    FCATEGORY_LIST = '/admin/category1/list',
}

export const getFCategotyPage = async (
    page: number,
    pageSize: number,
    name?: string,
) => {
    return await request2.get<any, ResponseData<PageData<FCategoryPage>>>(
        API.FCATEGORY_PAGE + `?page=${page}&pageSize=${pageSize}&name=${name}`,
    )
}

export const getFCategotyDetailById = async (id: number) => {
    return await request2.get<any, ResponseData<FCategory>>(
        API.FCATEGORY_BASE + '/' + id,
    )
}

export const postFCategotyAdd = async (param: FCategoryAddDTO) => {
    return await request2.post<any, ResponseData<any>>(
        API.FCATEGORY_BASE,
        param,
    )
}
export const putFCategoryEdit = async (param: FCategoryAddDTO) => {
    return await request2.put<any, ResponseData<any>>(API.FCATEGORY_BASE, param)
}

export const delFCategoryDelete = async (id: number) => {
    return await request2.delete<any, ResponseData<any>>(
        API.FCATEGORY_BASE + '?id=' + id,
    )
}

export const postFCategoryStatus = async (id: number, status: number) => {
    return await request2.post<any, ResponseData<any>>(
        API.FCATEGORY_STATUS + `/${status}?id=${id}`,
    )
}

export const getFCategoryList = async () => {
    return await request2.get<any, ResponseData<FCategoryList>>(
        API.FCATEGORY_LIST,
    )
}
