interface Response {
    code: number
    message: string
    ok: boolean
}

export interface Trademark {
    id?: number | null
    tmName: string
    logoUrl: string
    createTime?: string | null
    updateTime?: string | null
}

// 已有品牌管理的分页数据
export interface BaseTrademarkResponse extends Response {
    data: {
        records: Trademark[]
        total: number
        size: 5
        current: 1
        orders: []
        optimizeCountSql: boolean
        hitCount: boolean
        countId: any
        maxLimit: any
        searchCount: boolean
        pages: number
    }
}
export interface TrademarkResponse extends Response {
    data: Trademark
}
export interface TrademarkListResponse extends Response {
    data: Trademark[]
}
