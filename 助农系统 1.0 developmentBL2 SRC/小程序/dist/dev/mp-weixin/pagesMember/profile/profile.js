"use strict";
const common_vendor = require("../../common/vendor.js");
const services_profile = require("../../services/profile.js");
require("../../stores/index.js");
const stores_modules_member = require("../../stores/modules/member.js");
require("../../utils/http.js");
const _sfc_main = /* @__PURE__ */ common_vendor.defineComponent({
  __name: "profile",
  setup(__props) {
    const { safeAreaInsets } = common_vendor.index.getSystemInfoSync();
    let profile = common_vendor.ref({});
    const getMemberProfileData = async () => {
      const res = await services_profile.getMemberProfileAPI();
      profile.value = res.data;
    };
    const memberStore = stores_modules_member.useMemberStore();
    const onAvatarChange = () => {
      common_vendor.index.chooseMedia({
        //   文件个数
        count: 1,
        mediaType: ["image"],
        success: (res) => {
          const { tempFilePath } = res.tempFiles[0];
          common_vendor.index.uploadFile({
            url: "/member/profile/avatar",
            name: "file",
            filePath: tempFilePath,
            success: (res2) => {
              if (res2.statusCode == 200) {
                const avatar = JSON.parse(res2.data).result.avatar;
                profile.value.avatar = avatar;
                memberStore.profile.avatar = avatar;
                common_vendor.index.showToast({
                  title: "更新成功",
                  icon: "success"
                });
              } else {
                common_vendor.index.showToast({
                  icon: "error",
                  title: "上传图片失败"
                });
              }
            }
          });
        }
      });
    };
    const onGenderChange = (ev) => {
      profile.value.gender = ev.detail.value;
    };
    const onBirthdayChange = (ev) => {
      profile.value.birthday = ev.detail.value;
    };
    let fullLocationCode = ["", "", ""];
    const onFullLocationChange = (ev) => {
      profile.value.fullLocation = ev.detail.value.join(" ");
      fullLocationCode = ev.detail.code;
    };
    const onsubmit = async () => {
      const { nickname, gender, birthday, profession } = profile.value;
      const res = await services_profile.putMemberProfileAPI({
        nickname,
        gender,
        birthday,
        profession,
        provinceCode: fullLocationCode[0],
        cityCode: fullLocationCode[1],
        countyCode: fullLocationCode[2]
      });
      if (res.code == 1) {
        common_vendor.index.showToast({
          title: "保存成功",
          icon: "none"
        });
        setTimeout(() => {
          common_vendor.index.navigateBack();
        }, 500);
      }
    };
    common_vendor.onLoad(() => {
      getMemberProfileData();
    });
    return (_ctx, _cache) => {
      var _a, _b, _c, _d, _e, _f, _g, _h, _i, _j, _k, _l;
      return common_vendor.e({
        a: ((_a = common_vendor.unref(safeAreaInsets)) == null ? void 0 : _a.top) + "px",
        b: common_vendor.unref(profile).avatar,
        c: common_vendor.o(onAvatarChange),
        d: common_vendor.t(common_vendor.unref(profile).account),
        e: common_vendor.unref(profile).nickname,
        f: common_vendor.o(($event) => common_vendor.unref(profile).nickname = $event.detail.value),
        g: ((_b = common_vendor.unref(profile)) == null ? void 0 : _b.gender) == "1",
        h: ((_c = common_vendor.unref(profile)) == null ? void 0 : _c.gender) == "0",
        i: common_vendor.o(onGenderChange),
        j: (_d = common_vendor.unref(profile)) == null ? void 0 : _d.birthday
      }, ((_e = common_vendor.unref(profile)) == null ? void 0 : _e.birthday) ? {
        k: common_vendor.t((_f = common_vendor.unref(profile)) == null ? void 0 : _f.birthday)
      } : {}, {
        l: (_g = common_vendor.unref(profile)) == null ? void 0 : _g.birthday,
        m: /* @__PURE__ */ new Date(),
        n: common_vendor.o(onBirthdayChange),
        o: (_h = common_vendor.unref(profile)) == null ? void 0 : _h.fullLocation
      }, ((_i = common_vendor.unref(profile)) == null ? void 0 : _i.fullLocation) ? {
        p: common_vendor.t((_j = common_vendor.unref(profile)) == null ? void 0 : _j.fullLocation)
      } : {}, {
        q: (_l = (_k = common_vendor.unref(profile)) == null ? void 0 : _k.fullLocation) == null ? void 0 : _l.split(" "),
        r: common_vendor.o(onFullLocationChange),
        s: common_vendor.unref(profile).profession,
        t: common_vendor.o(($event) => common_vendor.unref(profile).profession = $event.detail.value),
        v: common_vendor.o(onsubmit)
      });
    };
  }
});
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "D:/OneDrive/桌面/uniapp小程序小兔/erabbit-uni-app-vue3-ts-master/src/pagesMember/profile/profile.vue"]]);
wx.createPage(MiniProgramPage);
//# sourceMappingURL=profile.js.map
