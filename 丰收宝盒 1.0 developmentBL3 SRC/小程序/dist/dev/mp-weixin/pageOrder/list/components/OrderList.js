"use strict";
const common_vendor = require("../../../common/vendor.js");
const services_order = require("../../../services/order.js");
const services_constants = require("../../../services/constants.js");
require("../../../utils/http.js");
require("../../../stores/index.js");
require("../../../stores/modules/member.js");
const _sfc_main = /* @__PURE__ */ common_vendor.defineComponent({
  __name: "OrderList",
  props: {
    orderStates: null
  },
  setup(__props) {
    const props = __props;
    const { safeAreaInsets } = common_vendor.index.getSystemInfoSync();
    const queryParams = {
      page: 1,
      pageSize: 5,
      orderState: props.orderStates
    };
    const orderList = common_vendor.ref([]);
    const getMemberOrderData = async () => {
      const res = await services_order.getMemberOrderAPI(queryParams);
      orderList.value = res.data.records;
      console.log(orderList.value);
    };
    const onOrderPay = async (id) => {
      await services_order.getPayMockAPI(Number(id));
      common_vendor.index.showToast({ title: "支付成功" });
      const order = orderList.value.find((ev) => ev.id == id);
      order.orderState = services_constants.OrderState.DaiFaHuo;
    };
    common_vendor.onMounted(() => {
      getMemberOrderData();
    });
    return (_ctx, _cache) => {
      var _a;
      return {
        a: common_vendor.f(orderList.value, (item, k0, i0) => {
          return common_vendor.e({
            a: common_vendor.t(item.createTime),
            b: item.orderState >= common_vendor.unref(services_constants.OrderState).DaiPingJia
          }, item.orderState >= common_vendor.unref(services_constants.OrderState).DaiPingJia ? {} : {}, {
            c: common_vendor.f(item.skus, (sku, k1, i1) => {
              return {
                a: sku.image,
                b: common_vendor.t(sku.name),
                c: common_vendor.t(sku.attrsText),
                d: sku.id
              };
            }),
            d: `/pageOrder/detail/detail?id=${item.id}`,
            e: common_vendor.t(item.totalNum),
            f: common_vendor.t(item.payMoney),
            g: item.orderState == common_vendor.unref(services_constants.OrderState).DaiFuKuan
          }, item.orderState == common_vendor.unref(services_constants.OrderState).DaiFuKuan ? {
            h: common_vendor.o(($event) => onOrderPay(item.id), item.id)
          } : common_vendor.e({
            i: `/pageOrder/create/create?orderId=${item.id}`
          }, {}), {
            j: item.id
          });
        }),
        b: common_vendor.t(common_vendor.unref(services_constants.orderStateList)[props.orderStates].text),
        c: common_vendor.t("没有更多数据~"),
        d: ((_a = common_vendor.unref(safeAreaInsets)) == null ? void 0 : _a.bottom) + "px"
      };
    };
  }
});
const Component = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__scopeId", "data-v-3234fb18"], ["__file", "D:/OneDrive/桌面/uniapp小程序小兔/erabbit-uni-app-vue3-ts-master/src/pageOrder/list/components/OrderList.vue"]]);
wx.createComponent(Component);
//# sourceMappingURL=OrderList.js.map
