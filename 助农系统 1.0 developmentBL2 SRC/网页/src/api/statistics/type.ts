/**
 * SalesTop10ReportVO
 */
export type SalesTop10ReportVO = {
    /**
     * 商品名称列表，以逗号分隔
     */
    nameList: string
    /**
     * 销量列表，以逗号分隔
     */
    numberList: string
    [property: string]: any
}

/**
 * UserReportVO
 */
export type UserStaReportVO = {
    /**
     * 日期列表，以逗号分隔
     */
    dateList: string
    /**
     * 新增用户数列表，以逗号分隔
     */
    newUserList: string
    /**
     * 总用户量列表，以逗号分隔
     */
    totalUserList: string
    [property: string]: any
}

/**
 * TurnoverReportVO
 */
export type TurnoverStaReportVO = {
    /**
     * 日期列表，日期之间以逗号分隔
     */
    dateList: string
    /**
     * 营业额列表，营业额之间以逗号分隔
     */
    turnoverList: string
    [property: string]: any
}

/**
 * OrderReportVO
 */
export type OrderStaReportVO = {
    /**
     * 日期列表，以逗号分隔
     */
    dateList: string
    /**
     * 订单完成率
     */
    orderCompletionRate: number
    /**
     * 订单数列表，以逗号分隔
     */
    orderCountList: string
    /**
     * 订单总数
     */
    totalOrderCount: number
    /**
     * 有效订单数
     */
    validOrderCount: number
    /**
     * 有效订单数列表，以逗号分隔
     */
    validOrderCountList: string
    [property: string]: any
}
