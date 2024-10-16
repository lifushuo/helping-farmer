// 书写品牌管理的模块接口
import request from '@/utils/request'
import type {
    BaseTrademarkResponse,
    Trademark,
    TrademarkResponse,
} from './type.ts'
import { TrademarkListResponse } from './type.ts'

// 品牌管理模块的接口地址
enum API {
    // 获取已有品牌的接口
    TRADEMARK_URL = 'admin/product/baseTrademark/',
    ADDTRADEMARK_URL = 'admin/product/baseTrademark/save',
    EDITTRADEMARK_URL = 'admin/product/baseTrademark/update',
    // 根据id 获取品牌信息 进行修改回显
    GETTRADEMARK_URL = 'admin/product/baseTrademark/get/',
    // 根据id 删除产品 /admin/product/baseTrademark/remove/{id}
    DELETETRADEMARK_URL = 'admin/product/baseTrademark/remove/',
    //获取全部的品牌信息
    GETALLTRADEMARK_URL = '/admin/product/baseTrademark/getTrademarkList',
}

// 获取已有品牌的接口方法
// page 获取第几页的的数据
// limit 获取几个已有品牌的数据
export const reqHasTrademark = (page: number, limit: number) => {
    return request.get<any, BaseTrademarkResponse>(
        API.TRADEMARK_URL + `${page}/${limit}`,
    )
}

export const reqAddOrEditHasTrademark = (data: Trademark) => {
    if (data.id) {
        // 返回的data 为null
        // 编辑
        return request.put<any, any>(API.EDITTRADEMARK_URL, data)
    } else {
        // 返回的data 为null
        // 添加
        return request.post<any, any>(API.ADDTRADEMARK_URL, data)
    }
}

// 根据id 获取品牌信息 进行修改回显
export const reqTrademarkById = (trademarkId: number) => {
    return request.get<any, TrademarkResponse>(
        `${API.GETTRADEMARK_URL}/${trademarkId}`,
    )
}

export const reqDeleteTrademarkById = (trademarkId: number) => {
    return request.delete<any, any>(API.DELETETRADEMARK_URL + `/${trademarkId}`)
}

export const reqGetTrademarkList = () => {
    return request.get<any, TrademarkListResponse>(API.GETALLTRADEMARK_URL)
}
