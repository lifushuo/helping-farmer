import { request2 } from '@/utils/request2.ts'
import { PageData, ResponseData } from '@/api'
import {
    OrdersInfoDataType,
    OrdersDetailsType,
    OrderStatisticsType,
} from './type.ts'
enum API {
    ORDERS_BASE = '/admin/order',
    ORDERS_SEARCH = '/admin/order/conditionSearch',
    ORDERS_DETAIL = '/admin/order/details/',
    ORDERS_STATISTICS = '/admin/order/statistics',
    ORDERS_CANCEL = '/admin/order/cancel',
    ORDERS_COMPLETE = '/admin/order/complete/',
    ORDERS_REJECTION = '/admin/order/rejection',
    ORDERS_CONFIRM = '/admin/order/confirm/',
    ORDERS_DELIVERY = '/admin/order/delivery/',
}

export const putOrderDelivery = async (id: number) => {
    return request2.put<any, ResponseData<any>>(API.ORDERS_DELIVERY + `${id}`)
}

/**
 * 接单
 * @param id
 */
export const putOrderConfirm = async (id: number) => {
    return request2.put<any, ResponseData<any>>(API.ORDERS_CONFIRM + `${id}`)
}

export const putOrderRejection = async (
    id: number,
    rejectionReason: string,
) => {
    const params: { id: number; rejectionReason: string } = {
        id: id,
        rejectionReason: rejectionReason,
    }
    return request2.put<any, ResponseData<any>>(API.ORDERS_REJECTION, params)
}

export const putOrderComplete = async (id: number) => {
    return request2.put<any, ResponseData<any>>(API.ORDERS_COMPLETE + `${id}`)
}
export const putOrderCancel = async (id: number, cancelReason: string) => {
    const params: { id: number; cancelReason: string } = {
        id: id,
        cancelReason: cancelReason,
    }
    return request2.put<any, ResponseData<any>>(API.ORDERS_CANCEL, params)
}

export const getOrderStatistics = async () => {
    return await request2.get<any, ResponseData<OrderStatisticsType>>(
        API.ORDERS_STATISTICS,
    )
}

export const getOrderDetails = async (id: number) => {
    return await request2.get<any, ResponseData<OrdersDetailsType>>(
        API.ORDERS_DETAIL + `${id}`,
    )
}

export const getOrdersConditionSearch = async (
    page: number,
    pageSize: number,
    status: number,
    phone?: string,
    number?: string,
    beginTime?: string,
    endTime?: string,
) => {
    return await request2.get<any, ResponseData<PageData<OrdersInfoDataType>>>(
        API.ORDERS_SEARCH +
            `?page=${page}&pageSize=${pageSize}&status=${status}&phone=${phone}&beginTime=${beginTime}&end=${endTime}&number=${number}`,
    )
}
