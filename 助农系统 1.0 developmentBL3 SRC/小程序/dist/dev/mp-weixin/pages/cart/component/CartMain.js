"use strict";
const common_vendor = require("../../../common/vendor.js");
require("../../../stores/index.js");
const services_cart = require("../../../services/cart.js");
const stores_modules_member = require("../../../stores/modules/member.js");
require("../../../utils/http.js");
if (!Array) {
  const _easycom_vk_data_input_number_box2 = common_vendor.resolveComponent("vk-data-input-number-box");
  const _easycom_uni_swipe_action_item2 = common_vendor.resolveComponent("uni-swipe-action-item");
  const _easycom_uni_swipe_action2 = common_vendor.resolveComponent("uni-swipe-action");
  (_easycom_vk_data_input_number_box2 + _easycom_uni_swipe_action_item2 + _easycom_uni_swipe_action2)();
}
const _easycom_vk_data_input_number_box = () => "../../../components/vk-data-input-number-box/vk-data-input-number-box.js";
const _easycom_uni_swipe_action_item = () => "../../../node-modules/@dcloudio/uni-ui/lib/uni-swipe-action-item/uni-swipe-action-item.js";
const _easycom_uni_swipe_action = () => "../../../node-modules/@dcloudio/uni-ui/lib/uni-swipe-action/uni-swipe-action.js";
if (!Math) {
  (_easycom_vk_data_input_number_box + _easycom_uni_swipe_action_item + _easycom_uni_swipe_action)();
}
const _sfc_main = /* @__PURE__ */ common_vendor.defineComponent({
  __name: "CartMain",
  setup(__props) {
    const memberStore = stores_modules_member.useMemberStore();
    const cartList = common_vendor.ref([]);
    const getMemberCartData = async () => {
      const res = await services_cart.getMemberCartAPI();
      cartList.value = res.data;
      console.log(res.data);
      console.log(cartList.value);
    };
    const onChangeSelected = (item) => {
      item.is_selected = !item.is_selected;
      services_cart.putMemberCartBySkuIdAPI(item.skuId, { selected: item.is_selected == true ? 1 : 0 });
    };
    common_vendor.onShow(() => {
      if (memberStore.profile) {
        getMemberCartData();
      }
    });
    const gotoPayment = () => {
      if (selectedCartListCount.value === 0) {
        return common_vendor.index.showToast({
          icon: "none",
          title: "请选择商品"
        });
      }
      common_vendor.index.navigateTo({ url: "/pageOrder/create/create" });
    };
    const onDeleteCart = (skuId) => {
      common_vendor.index.showModal({
        content: "是否删除",
        success: async (res) => {
          if (res.confirm) {
            await services_cart.deleteMemberCartAPI({ ids: [skuId] });
            getMemberCartData();
          }
        }
      });
    };
    const isSelectedAll = common_vendor.computed(() => {
      return cartList.value.length && cartList.value.every((v) => v.is_selected);
    });
    const onChangeSelectedAll = () => {
      const _isSelectedAll = !isSelectedAll.value;
      cartList.value.forEach((item) => {
        item.is_selected = _isSelectedAll;
      });
      services_cart.putMemberCartSelectedAPI({ selected: _isSelectedAll });
    };
    const onChangeCount = (ev) => {
      console.log(ev);
      services_cart.putMemberCartBySkuIdAPI(ev.index, { count: ev.value });
    };
    let selectedCartList = common_vendor.computed(() => {
      return cartList.value.filter((v) => v.is_selected);
    });
    const selectedCartListCount = common_vendor.computed(() => {
      return selectedCartList.value.reduce((sum, item) => sum + item.count, 0);
    });
    const selectedCartListMoney = common_vendor.computed(() => {
      return selectedCartList.value.reduce((sum, item) => sum + item.count * item.price, 0).toFixed(2);
    });
    return (_ctx, _cache) => {
      var _a, _b;
      return common_vendor.e({
        a: (_a = common_vendor.unref(memberStore).profile) == null ? void 0 : _a.id
      }, ((_b = common_vendor.unref(memberStore).profile) == null ? void 0 : _b.id) ? common_vendor.e({
        b: common_vendor.f(cartList.value, (item, k0, i0) => {
          return {
            a: common_vendor.o(($event) => onChangeSelected(item), item.skuId),
            b: item.is_selected ? 1 : "",
            c: item.picture,
            d: common_vendor.t(item.skuName),
            e: common_vendor.t(item.attrsText),
            f: common_vendor.t(item.price),
            g: `/pages/goods/goods?id=${item.spuId}`,
            h: common_vendor.o(onChangeCount, item.skuId),
            i: "f47646e6-2-" + i0 + "," + ("f47646e6-1-" + i0),
            j: common_vendor.o(($event) => item.count = $event, item.skuId),
            k: common_vendor.p({
              min: 1,
              max: item.stock,
              index: item.skuId,
              ["model-value"]: item.count
            }),
            l: common_vendor.o(($event) => onDeleteCart(item.skuId), item.skuId),
            m: item.skuId,
            n: "f47646e6-1-" + i0 + ",f47646e6-0"
          };
        })
      }, {
        c: common_vendor.o(onChangeSelectedAll),
        d: common_vendor.unref(isSelectedAll) ? 1 : "",
        e: common_vendor.t(common_vendor.unref(selectedCartListMoney)),
        f: common_vendor.t(common_vendor.unref(selectedCartListCount)),
        g: common_vendor.unref(selectedCartListCount) == 0 ? 1 : "",
        h: common_vendor.o(gotoPayment)
      }) : {});
    };
  }
});
const Component = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "D:/OneDrive/桌面/uniapp小程序小兔/erabbit-uni-app-vue3-ts-master/src/pages/cart/component/CartMain.vue"]]);
wx.createComponent(Component);
//# sourceMappingURL=CartMain.js.map
