import { request2 } from '@/utils/request2.ts'
import { PageData, ResponseData } from '@/api'
import { SCategoryPageType } from '@/api/scategory/type.ts'

enum API {
    SCATEGORY_PAGE = '/admin/category2/page',
    SCATEGORY_BASE = '/admin/category2',
    SCATEGORY_STATUS = '/admin/category2/status',
    SCATEGORY_LIST = '/admin/category2/list',
}

/**
 * 获取全部的二级分类
 */
export const getSCategoryList = async (id: number) => {
    return await request2.get<any, ResponseData<PageData<SCategoryPageType>>>(
        API.SCATEGORY_LIST + `?id=${id}`,
    )
}
export const deleteSCategoryById = async (id: number) => {
    return await request2.delete<any, ResponseData<any>>(
        API.SCATEGORY_BASE + `?id=${id}`,
    )
}

export const putSCategoryStatus = async (id: number, status: number) => {
    return await request2.post<any, ResponseData<any>>(
        API.SCATEGORY_STATUS + `/${status}?id=${id}`,
    )
}

export const putSCategoryEdit = async (
    id: number,
    category1Id: number,
    name: string,
) => {
    return await request2.put<any, ResponseData<any>>(API.SCATEGORY_BASE, {
        id: id,
        category1Id: category1Id,
        name: name,
    })
}

export const postSCategoryAdd = async (category1Id: number, name: string) => {
    return await request2.post<any, ResponseData<any>>(API.SCATEGORY_BASE, {
        category1Id: category1Id,
        name: name,
    })
}

export const getSCategoryPageData = async (
    page: number,
    pageSize: number,
    name?: string,
    category1Id?: number,
) => {
    return await request2.get<any, ResponseData<PageData<SCategoryPageType>>>(
        API.SCATEGORY_PAGE +
            `?page=${page}&pageSize=${pageSize}&name=${name}&category1Id=${category1Id}`,
    )
}
