import { request2 } from '@/utils/request2.ts'
import { ResponseData } from '@/api'
import {
    OrderStaReportVO,
    SalesTop10ReportVO,
    TurnoverStaReportVO,
    UserStaReportVO,
} from '@/api/statistics/type.ts'

enum API {
    STA_ORDERS = '/admin/report/ordersStatistics',
    STA_TURNOVER = '/admin/report/turnoverStatistics',
    STA_USER = '/admin/report/userStatistics',
    STA_LOVE = '/admin/report/love/top10',
    STA_SPU = '/admin/report/spu/top10',
    STA_SKU = '/admin/report/sku/top10',
}

export const getStaOrders = async (beginTime: string, endTime: string) => {
    return await request2.get<any, ResponseData<OrderStaReportVO>>(
        API.STA_ORDERS + `?begin=${beginTime}&end=${endTime}`,
    )
}
export const getStaTurnover = async (beginTime: string, endTime: string) => {
    return await request2.get<any, ResponseData<TurnoverStaReportVO>>(
        API.STA_TURNOVER + `?begin=${beginTime}&end=${endTime}`,
    )
}
export const getStaUser = async (beginTime: string, endTime: string) => {
    return await request2.get<any, ResponseData<UserStaReportVO>>(
        API.STA_USER + `?begin=${beginTime}&end=${endTime}`,
    )
}
export const getStaLove = async (beginTime: string, endTime: string) => {
    return await request2.get<any, ResponseData<SalesTop10ReportVO>>(
        API.STA_LOVE + `?begin=${beginTime}&end=${endTime}`,
    )
}
export const getStaSpu = async (beginTime: string, endTime: string) => {
    return await request2.get<any, ResponseData<SalesTop10ReportVO>>(
        API.STA_SPU + `?begin=${beginTime}&end=${endTime}`,
    )
}
export const getStaSku = async (beginTime: string, endTime: string) => {
    return await request2.get<any, ResponseData<SalesTop10ReportVO>>(
        API.STA_SKU + `?begin=${beginTime}&end=${endTime}`,
    )
}
