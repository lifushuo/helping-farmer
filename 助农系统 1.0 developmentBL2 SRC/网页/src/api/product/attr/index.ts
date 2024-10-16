// <!-- 属性相关的API -->
import request from '@/utils/request.ts'
import requset from '@/utils/request.ts'
import {
    Attr,
    AttrInfoResponse,
    C1Response,
    C2Response,
    C3Response,
} from './type'

enum API {
    // 获取一级分类接口地址
    C1_URL = '/admin/product/getCategory1',
    // 获取二级分类接口地址
    // GET /admin/product/getCategory2/{category1Id}
    C2_URL = '/admin/product/getCategory2/',
    // 获取三级分类接口地址
    // /admin/prohduct/getCategory3/{category2Id}
    C3_URL = '/admin/product/getCategory3/',
    // 获取分类下已有的属性与属性值
    // /admin/product/attrInfoList/{category1Id}/{category2Id}/{category3Id}
    GETATTRINFOLIST_URL = '/admin/product/attrInfoList/',
    // 添加或者修改已有的属性的接口
    ADDORUPDATEATTR_URL = '/admin/product/saveAttrInfo',
    // 删除一个属性
    DELETEATTR_URL = '/admin/product/deleteAttr/',
}

export const reqC1 = () => {
    return requset.get<any, C1Response>(API.C1_URL)
}

export const reqC2 = (c1Id: number) => {
    return requset.get<any, C2Response>(API.C2_URL + c1Id)
}
// 获取三级分类接口地址
export const reqC3 = (c2Id: number) => {
    return requset.get<any, C3Response>(API.C3_URL + c2Id)
}
// 获取分类下已有的属性与属性值
export const reqAttrInfoList = (c1Id: number, c2Id: number, c3Id: number) => {
    return request.get<any, AttrInfoResponse>(
        API.GETATTRINFOLIST_URL + `${c1Id}/${c2Id}/${c3Id}`,
    )
}
export const reqAddOrUpdateAtrr = (data: Attr) => {
    return requset.post<any, any>(API.ADDORUPDATEATTR_URL, data)
}
export const reqDeleteAttr = (attrId: number) => {
    return requset.delete<any, any>(API.DELETEATTR_URL + attrId)
}
