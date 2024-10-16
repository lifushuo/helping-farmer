"use strict";
const common_vendor = require("../../common/vendor.js");
if (!Math) {
  OrderList();
}
const OrderList = () => "./components/OrderList.js";
const _sfc_main = /* @__PURE__ */ common_vendor.defineComponent({
  __name: "list",
  props: {
    type: null
  },
  setup(__props) {
    const prop = __props;
    common_vendor.index.getSystemInfoSync();
    const orderTabs = common_vendor.ref([
      { orderState: 0, title: "全部" },
      { orderState: 1, title: "待付款" },
      { orderState: 2, title: "待接单" },
      { orderState: 4, title: "待收货" },
      { orderState: 5, title: "待评价" }
    ]);
    const activeIndex = common_vendor.ref(orderTabs.value.findIndex((v) => v.orderState == Number(prop.type)));
    return (_ctx, _cache) => {
      return {
        a: common_vendor.f(orderTabs.value, (item, k0, i0) => {
          return {
            a: common_vendor.t(item.title),
            b: item.orderState,
            c: common_vendor.o(($event) => activeIndex.value = item.orderState, item.orderState)
          };
        }),
        b: activeIndex.value * 20 + "%",
        c: common_vendor.f(orderTabs.value, (item, k0, i0) => {
          return {
            a: "592de37d-0-" + i0,
            b: common_vendor.p({
              ["order-states"]: item.orderState
            }),
            c: item.orderState
          };
        }),
        d: activeIndex.value,
        e: common_vendor.o(($event) => activeIndex.value = $event.detail.current)
      };
    };
  }
});
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "D:/OneDrive/桌面/uniapp小程序小兔/erabbit-uni-app-vue3-ts-master/src/pageOrder/list/list.vue"]]);
wx.createPage(MiniProgramPage);
//# sourceMappingURL=list.js.map
