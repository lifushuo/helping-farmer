"use strict";
const common_vendor = require("../../common/vendor.js");
const composables_index = require("../../composables/index.js");
const services_order = require("../../services/order.js");
const services_constants = require("../../services/constants.js");
require("../../utils/http.js");
require("../../stores/index.js");
require("../../stores/modules/member.js");
if (!Array) {
  const _component_PageSkeleton = common_vendor.resolveComponent("PageSkeleton");
  const _easycom_uni_popup2 = common_vendor.resolveComponent("uni-popup");
  (_component_PageSkeleton + _easycom_uni_popup2)();
}
const _easycom_uni_popup = () => "../../node-modules/@dcloudio/uni-ui/lib/uni-popup/uni-popup.js";
if (!Math) {
  _easycom_uni_popup();
}
const _sfc_main = /* @__PURE__ */ common_vendor.defineComponent({
  __name: "detail",
  props: {
    id: null
  },
  setup(__props) {
    const query = __props;
    const { safeAreaInsets } = common_vendor.index.getSystemInfoSync();
    const { guessRef, onScrolltolower } = composables_index.useGuessList();
    const popup = common_vendor.ref();
    const reasonList = common_vendor.ref([
      "商品无货",
      "不想要了",
      "商品信息填错了",
      "地址信息填写错误",
      "商品降价",
      "其它"
    ]);
    const reason = common_vendor.ref("");
    const onCopy = (id) => {
      common_vendor.index.setClipboardData({ data: id });
    };
    const pages = getCurrentPages();
    const pageInstance = pages.at(-1);
    const order = common_vendor.ref();
    const getMemberOrderByIdData = async () => {
      var _a;
      const res = await services_order.getMemberOrderByIdAPI(query.id);
      order.value = res.data;
      if ([services_constants.OrderState.DaiShouHuo, services_constants.OrderState.DaiPingJia, services_constants.OrderState.YiWanCheng].includes(
        (_a = order.value) == null ? void 0 : _a.orderState
      ))
        ;
    };
    common_vendor.ref();
    common_vendor.onLoad(() => {
      getMemberOrderByIdData();
    });
    const onOrderDelete = () => {
      common_vendor.index.showModal({
        content: "是否删除订单",
        success: async (result) => {
          if (result.confirm) {
            await services_order.deleteMemberOrderAPI({ ids: [query.id] });
            common_vendor.index.redirectTo({ url: "/pageOrder/list/list" });
          }
        }
      });
    };
    common_vendor.onReady(() => {
      pageInstance.animate(
        ".navbar",
        [{ background: "transparent" }, { background: "#f8f8f8" }],
        1e3,
        {
          scrollSource: "#scroller",
          timeRange: 1e3,
          startScrollOffset: 0,
          endScrollOffset: 50
        }
      );
      pageInstance.animate(".navbar .title", [{ color: "transparent" }, { color: "#000" }], 1e3, {
        scrollSource: "#scroller",
        timeRange: 1e3,
        startScrollOffset: 0,
        endScrollOffset: 50
      });
      pageInstance.animate(".navbar .back", [{ color: "#fff" }, { color: "#000" }], 1e3, {
        scrollSource: "#scroller",
        timeRange: 1e3,
        startScrollOffset: 0,
        endScrollOffset: 50
      });
    });
    const onOrderPay = async () => {
      await services_order.getPayMockAPI(Number(query.id));
      common_vendor.index.redirectTo({ url: `/pageOrder/payment/payment?id=${query.id}` });
    };
    const isDev = true;
    const onOrderSend = async () => {
      {
        await services_order.getMemberOrderConsignmentByIdAPI(query.id);
        common_vendor.index.showToast({ icon: "success", title: "模拟发货完成" });
        order.value.orderState = services_constants.OrderState.DaiShouHuo;
      }
    };
    const onOrderConfirm = () => {
      common_vendor.index.showModal({
        content: "为保障您的权益，请收到货并确认无误后，再确认收货",
        success: async (success) => {
          if (success.confirm) {
            await services_order.putMemberOrderReceiptByIdAPI(query.id);
            order.value.orderState = services_constants.OrderState.DaiPingJia;
          }
        }
      });
    };
    return (_ctx, _cache) => {
      var _a, _b, _c;
      return common_vendor.e({
        a: common_vendor.unref(pages).length > 1
      }, common_vendor.unref(pages).length > 1 ? {
        b: common_vendor.o(($event) => _ctx.uni.navigateBack())
      } : {}, {
        c: ((_a = common_vendor.unref(safeAreaInsets)) == null ? void 0 : _a.top) + "px",
        d: order.value
      }, order.value ? common_vendor.e({
        e: order.value.orderState == common_vendor.unref(services_constants.OrderState).DaiFuKuan
      }, order.value.orderState == common_vendor.unref(services_constants.OrderState).DaiFuKuan ? {
        f: common_vendor.t(order.value.payMoney),
        g: common_vendor.o(onOrderPay)
      } : common_vendor.e({
        h: common_vendor.t(common_vendor.unref(services_constants.orderStateList)[order.value.orderState].text),
        i: common_vendor.t(order.value.orderState),
        j: `/pagesOrder/create/create?orderId=${query.id}`,
        k: common_vendor.unref(isDev) && order.value.orderState == common_vendor.unref(services_constants.OrderState).DaiFaHuo
      }, common_vendor.unref(isDev) && order.value.orderState == common_vendor.unref(services_constants.OrderState).DaiFaHuo ? {
        l: common_vendor.o(onOrderSend)
      } : {}), {
        m: common_vendor.unref(safeAreaInsets).top + 20 + "px",
        n: order.value.orderState == common_vendor.unref(services_constants.OrderState).DaiShouHuo
      }, order.value.orderState == common_vendor.unref(services_constants.OrderState).DaiShouHuo ? {
        o: common_vendor.f(1, (item, k0, i0) => {
          return {
            a: item
          };
        })
      } : {}, {
        p: common_vendor.f(order.value.skus, (item, k0, i0) => {
          return {
            a: item.image,
            b: common_vendor.t(item.name),
            c: common_vendor.t(item.attrsText),
            d: common_vendor.t(item.curPrice),
            e: common_vendor.t(item.quantity),
            f: item.id,
            g: `/pages/goods/goods?id=${item.spuId}`
          };
        }),
        q: order.value.orderState == common_vendor.unref(services_constants.OrderState).DaiPingJia
      }, order.value.orderState == common_vendor.unref(services_constants.OrderState).DaiPingJia ? {} : {}, {
        r: common_vendor.t(order.value.totalMoney),
        s: common_vendor.t(order.value.postFee),
        t: common_vendor.t(order.value.payMoney),
        v: common_vendor.t(query.id),
        w: common_vendor.o(($event) => onCopy(query.id)),
        x: common_vendor.t(order.value.createTime),
        y: ((_b = common_vendor.unref(safeAreaInsets)) == null ? void 0 : _b.bottom) + "px",
        z: order.value.orderState == 1
      }, order.value.orderState == 1 ? {
        A: common_vendor.o(onOrderPay),
        B: common_vendor.o(($event) => {
          var _a2, _b2;
          return (_b2 = (_a2 = popup.value) == null ? void 0 : _a2.open) == null ? void 0 : _b2.call(_a2);
        })
      } : common_vendor.e({
        C: `/pageOrder/create/create?orderId=${query.id}`,
        D: order.value.orderState === common_vendor.unref(services_constants.OrderState).DaiShouHuo
      }, order.value.orderState === common_vendor.unref(services_constants.OrderState).DaiShouHuo ? {
        E: common_vendor.o(onOrderConfirm)
      } : {}, {
        F: order.value.orderState >= common_vendor.unref(services_constants.OrderState).DaiPingJia
      }, order.value.orderState >= common_vendor.unref(services_constants.OrderState).DaiPingJia ? {
        G: common_vendor.o(onOrderDelete)
      } : {}), {
        H: ((_c = common_vendor.unref(safeAreaInsets)) == null ? void 0 : _c.bottom) + "px"
      }) : {}, {
        I: common_vendor.o(
          //@ts-ignore
          (...args) => common_vendor.unref(onScrolltolower) && common_vendor.unref(onScrolltolower)(...args)
        ),
        J: common_vendor.f(reasonList.value, (item, k0, i0) => {
          return {
            a: common_vendor.t(item),
            b: item === reason.value ? 1 : "",
            c: item,
            d: common_vendor.o(($event) => reason.value = item, item)
          };
        }),
        K: common_vendor.o(($event) => {
          var _a2, _b2;
          return (_b2 = (_a2 = popup.value) == null ? void 0 : _a2.close) == null ? void 0 : _b2.call(_a2);
        }),
        L: common_vendor.sr(popup, "557258dd-1", {
          "k": "popup"
        }),
        M: common_vendor.p({
          type: "bottom",
          ["background-color"]: "#fff"
        })
      });
    };
  }
});
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "D:/OneDrive/桌面/uniapp小程序小兔/erabbit-uni-app-vue3-ts-master/src/pageOrder/detail/detail.vue"]]);
wx.createPage(MiniProgramPage);
//# sourceMappingURL=detail.js.map
