"use strict";
const common_vendor = require("../../common/vendor.js");
const services_goods = require("../../services/goods.js");
const services_cart = require("../../services/cart.js");
require("../../utils/http.js");
require("../../stores/index.js");
require("../../stores/modules/member.js");
if (!Array) {
  const _easycom_vk_data_goods_sku_popup2 = common_vendor.resolveComponent("vk-data-goods-sku-popup");
  const _easycom_uni_popup2 = common_vendor.resolveComponent("uni-popup");
  (_easycom_vk_data_goods_sku_popup2 + _easycom_uni_popup2)();
}
const _easycom_vk_data_goods_sku_popup = () => "../../components/vk-data-goods-sku-popup/vk-data-goods-sku-popup.js";
const _easycom_uni_popup = () => "../../node-modules/@dcloudio/uni-ui/lib/uni-popup/uni-popup.js";
if (!Math) {
  (_easycom_vk_data_goods_sku_popup + PageSkeleton + AddressPanel + ServicePanel + _easycom_uni_popup)();
}
const AddressPanel = () => "./components/AddressPanel.js";
const ServicePanel = () => "./components/ServicePanel.js";
const PageSkeleton = () => "./components/PageSkeleton.js";
const _sfc_main = /* @__PURE__ */ common_vendor.defineComponent({
  __name: "goods",
  props: {
    id: null
  },
  setup(__props) {
    const query = __props;
    const { safeAreaInsets } = common_vendor.index.getSystemInfoSync();
    const goods = common_vendor.ref();
    const getGoodsByIdData = async () => {
      const res = await services_goods.getGoodsByIdAPI(query.id);
      goods.value = res.data;
      localdata.value = {
        _id: res.data.id,
        name: res.data.name,
        goods_thumb: res.data.mainPictures[0],
        spec_list: res.data.specs.map((v) => {
          return {
            name: v.name,
            list: v.specValues
          };
        }),
        sku_list: res.data.skus.map((v) => {
          return {
            _id: v.id,
            goods_id: res.data.id,
            goods_name: res.data.name,
            image: v.picture,
            price: v.price * 100,
            stock: v.inventory,
            sku_name_arr: v.specs.map((vv) => vv.value)
          };
        })
      };
    };
    const pictureIndex = common_vendor.ref(0);
    const isFinished = common_vendor.ref(false);
    common_vendor.onLoad(async () => {
      isFinished.value = false;
      await getGoodsByIdData();
      isFinished.value = true;
    });
    const onChange = (ev) => {
      pictureIndex.value = ev == null ? void 0 : ev.detail.current;
    };
    const onTapImage = (url) => {
      common_vendor.index.previewImage({
        current: url,
        urls: goods.value.mainPictures
      });
    };
    const popup = common_vendor.ref();
    const popupName = common_vendor.ref();
    const openPopup = (name) => {
      var _a;
      popupName.value = name;
      (_a = popup.value) == null ? void 0 : _a.open();
    };
    const closePopup = () => {
      var _a;
      (_a = popup.value) == null ? void 0 : _a.close();
    };
    const isShowSku = common_vendor.ref(false);
    const localdata = common_vendor.ref({});
    const skuPopupRef = common_vendor.ref();
    const selectAttrText = common_vendor.computed(() => {
      var _a, _b;
      return ((_b = (_a = skuPopupRef.value) == null ? void 0 : _a.selectArr) == null ? void 0 : _b.join(" ").trim()) || "请选择商品规格";
    });
    const mode = common_vendor.ref(
      1
      /* Both */
    );
    const onAddCart = async (ev) => {
      await services_cart.postMemberCartAPI({ skuId: ev._id, count: ev.buy_num });
      common_vendor.index.showToast({ title: "添加成功" });
      isShowSku.value = false;
    };
    const onBuyNow = (ev) => {
      common_vendor.index.navigateTo({ url: `/pageOrder/create/create?skuId=${ev._id}&count=${ev.buy_num}` });
    };
    return (_ctx, _cache) => {
      var _a, _b, _c, _d, _e, _f, _g, _h, _i, _j;
      return common_vendor.e({
        a: common_vendor.sr(skuPopupRef, "7ddf1ca0-0", {
          "k": "skuPopupRef"
        }),
        b: common_vendor.o(onAddCart),
        c: common_vendor.o(onBuyNow),
        d: common_vendor.o(($event) => isShowSku.value = $event),
        e: common_vendor.p({
          localdata: localdata.value,
          mode: mode.value,
          modelValue: isShowSku.value
        }),
        f: isFinished.value == false
      }, isFinished.value == false ? {} : {
        g: common_vendor.f((_a = goods.value) == null ? void 0 : _a.mainPictures, (item, index, i0) => {
          return {
            a: common_vendor.o(($event) => onTapImage(item), index),
            b: item,
            c: index
          };
        }),
        h: common_vendor.o(onChange),
        i: common_vendor.t(pictureIndex.value + 1),
        j: common_vendor.t((_c = (_b = goods.value) == null ? void 0 : _b.mainPictures) == null ? void 0 : _c.length),
        k: common_vendor.t((_d = goods.value) == null ? void 0 : _d.price),
        l: common_vendor.t((_e = goods.value) == null ? void 0 : _e.name),
        m: common_vendor.t((_f = goods.value) == null ? void 0 : _f.desc),
        n: common_vendor.t(common_vendor.unref(selectAttrText)),
        o: common_vendor.o(($event) => isShowSku.value = true),
        p: common_vendor.o(($event) => openPopup("address")),
        q: common_vendor.o(($event) => openPopup("service")),
        r: common_vendor.f((_g = goods.value) == null ? void 0 : _g.details.properties, (item, index, i0) => {
          return {
            a: common_vendor.t(item.name),
            b: common_vendor.t(item.value),
            c: index
          };
        }),
        s: common_vendor.f((_h = goods.value) == null ? void 0 : _h.details.picture, (item, k0, i0) => {
          return {
            a: item,
            b: item
          };
        }),
        t: common_vendor.f((_i = goods.value) == null ? void 0 : _i.similarProducts, (item, index, i0) => {
          return {
            a: item.picture,
            b: common_vendor.t(item.name),
            c: common_vendor.t(item.price),
            d: index
          };
        }),
        v: `/pages/goods/goods?id=`
      }, {
        w: common_vendor.o(($event) => {
          isShowSku.value = true, mode.value = 2;
        }),
        x: common_vendor.o(($event) => {
          isShowSku.value = true, mode.value = 3;
        }),
        y: ((_j = common_vendor.unref(safeAreaInsets)) == null ? void 0 : _j.bottom) + "px",
        z: popupName.value == "address"
      }, popupName.value == "address" ? {
        A: common_vendor.o(closePopup)
      } : {}, {
        B: popupName.value == "service"
      }, popupName.value == "service" ? {
        C: common_vendor.o(closePopup)
      } : {}, {
        D: common_vendor.sr(popup, "7ddf1ca0-2", {
          "k": "popup"
        }),
        E: common_vendor.p({
          type: "bottom"
        })
      });
    };
  }
});
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "D:/OneDrive/桌面/uniapp小程序小兔/erabbit-uni-app-vue3-ts-master/src/pages/goods/goods.vue"]]);
wx.createPage(MiniProgramPage);
//# sourceMappingURL=goods.js.map
