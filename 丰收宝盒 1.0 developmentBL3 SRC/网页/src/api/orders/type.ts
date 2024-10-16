/**
 * 数量统计
 */
export type OrderStatisticsType = {
    /**
     * 待派送数量
     */
    confirmed: number
    /**
     * 派送中数量
     */
    deliveryInProgress: number
    /**
     * 待接单数量
     */
    toBeConfirmed: number
    [property: string]: any
}

export type OrdersDetailsType = {
    /**
     * 地址信息
     */
    address: string
    /**
     * 地址id，地址id
     */
    addressBookId: number
    /**
     * 订单取消原因
     */
    cancelReason: string
    /**
     * 取消时间
     */
    cancelTime: string
    /**
     * 支付时间
     */
    checkoutTime: null | string
    /**
     * 收货人
     */
    consignee: string
    /**
     * 订单创建时间
     */
    createTime: string
    /**
     * 订单开始配送时间
     */
    deliveryTime: string
    /**
     * 优惠金额
     */
    discountMoney: number
    /**
     * 最终送达时间
     */
    estimatedDeliveryTime: string
    /**
     * 订单主键
     */
    id: number
    /**
     * 订单号，订单号
     */
    number: string
    /**
     * 订单详情数组
     */
    orderDetails: OrderDetail[]
    /**
     * 运费
     */
    payAmount: number
    /**
     * 支付的编号
     */
    payId: number
    /**
     * 支付方式，支付方法
     */
    payMethod: number
    /**
     * 实付款
     */
    payMoney: number
    /**
     * 支付状态，支付状态
     */
    payStatus: number
    /**
     * 电话号码，电话号码
     */
    phone: string
    /**
     * 商家拒绝原因
     */
    rejectionReason: string
    /**
     * 备注，备注
     */
    remark: string
    /**
     * 状态，订单状态
     */
    status: number
    /**
     * 用户id，用户id
     */
    userId: number
    /**
     * 用户名，用户名
     */
    userName: string
    [property: string]: any
}

/**
 *供查询使用的
 */
export type OrdersInfoDataType = {
    /**
     * 地址信息
     */
    address: string
    /**
     * 地址id，地址id
     */
    addressBookId: number
    /**
     * 订单取消原因
     */
    cancelReason: string
    /**
     * 取消时间
     */
    cancelTime: string
    /**
     * 支付时间
     */
    checkoutTime?: null | string
    /**
     * 收货人
     */
    consignee: string
    /**
     * 订单创建时间
     */
    createTime: string
    /**
     * 订单开始配送时间
     */
    deliveryTime: string
    /**
     * 优惠金额
     */
    discountMoney: number
    /**
     * 最终送达时间
     */
    estimatedDeliveryTime: string
    /**
     * 订单主键
     */
    id: number
    /**
     * 订单号，订单号
     */
    number: string
    orderDetails: OrderDetail[]
    /**
     * 运费
     */
    payAmount: number
    /**
     * 支付的编号
     */
    payId: number
    /**
     * 支付方式，支付方法
     */
    payMethod: number
    /**
     * 实付款
     */
    payMoney: number
    /**
     * 支付状态，支付状态
     */
    payStatus: number
    /**
     * 电话号码，电话号码
     */
    phone: string
    /**
     * 商家拒绝原因
     */
    rejectionReason: string
    /**
     * 备注，备注
     */
    remark: string
    /**
     * 状态，订单状态
     */
    status: number
    /**
     * 用户id，用户id
     */
    userId: number
    /**
     * 用户名，用户名
     */
    userName: string
    [property: string]: any
}

export type OrderDetail = {
    /**
     * 订单详情id
     */
    id: number
    skuAttrs: SkuAttr[]
    /**
     * skuID
     */
    skuId: number
    /**
     * sku名字
     */
    skuName: string
    /**
     * sku原价
     */
    skuOldPrice: number
    /**
     * sku图片
     */
    skuPicture: string
    /**
     * sku价格
     */
    skuPrice: number
    /**
     * spu折扣（比例）
     */
    spuDiscount: number
    /**
     * spuId
     */
    spuId: number
    /**
     * spu名称
     */
    spuName: string
    [property: string]: any
}

type SkuAttr = {
    /**
     * 属性名字
     */
    attrName: string
    /**
     * attr属性值
     */
    attrValue: string
    [property: string]: any
}
