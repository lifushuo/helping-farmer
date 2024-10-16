"use strict";
const common_vendor = require("../../common/vendor.js");
require("../../stores/index.js");
const composables_index = require("../../composables/index.js");
const stores_modules_member = require("../../stores/modules/member.js");
const _sfc_main = /* @__PURE__ */ common_vendor.defineComponent({
  __name: "my",
  setup(__props) {
    const { safeAreaInsets } = common_vendor.index.getSystemInfoSync();
    const orderTypes = [
      { type: 1, text: "待付款", icon: "icon-currency" },
      { type: 2, text: "待发货", icon: "icon-gift" },
      { type: 3, text: "待收货", icon: "icon-check" },
      { type: 4, text: "待评价", icon: "icon-comment" }
    ];
    const memberStore = stores_modules_member.useMemberStore();
    const { guessRef, onScrolltolower } = composables_index.useGuessList();
    return (_ctx, _cache) => {
      var _a, _b, _c, _d, _e, _f;
      return common_vendor.e({
        a: common_vendor.unref(memberStore).profile
      }, common_vendor.unref(memberStore).profile ? {
        b: common_vendor.unref(memberStore).profile.avatar,
        c: common_vendor.t(((_b = (_a = common_vendor.unref(memberStore)) == null ? void 0 : _a.profile) == null ? void 0 : _b.nickname) ? (_d = (_c = common_vendor.unref(memberStore)) == null ? void 0 : _c.profile) == null ? void 0 : _d.nickname : (_f = (_e = common_vendor.unref(memberStore)) == null ? void 0 : _e.profile) == null ? void 0 : _f.account)
      } : {}, {
        d: common_vendor.unref(safeAreaInsets).top + "px",
        e: common_vendor.f(orderTypes, (item, k0, i0) => {
          return {
            a: common_vendor.t(item.text),
            b: item.type,
            c: common_vendor.n(item.icon),
            d: `/pageOrder/list/list?type=${item.type}`
          };
        }),
        f: common_vendor.o(
          //@ts-ignore
          (...args) => common_vendor.unref(onScrolltolower) && common_vendor.unref(onScrolltolower)(...args)
        )
      });
    };
  }
});
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "D:/OneDrive/桌面/uniapp小程序小兔/erabbit-uni-app-vue3-ts-master/src/pages/my/my.vue"]]);
wx.createPage(MiniProgramPage);
//# sourceMappingURL=my.js.map
