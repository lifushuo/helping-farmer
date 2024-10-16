"use strict";
const utils_http = require("../utils/http.js");
const getMemberProfileAPI = () => {
  return utils_http.http2({
    method: "GET",
    url: "/user/user"
  });
};
const putMemberProfileAPI = (data) => {
  return utils_http.http2({
    method: "PUT",
    url: "/user/user",
    data
  });
};
exports.getMemberProfileAPI = getMemberProfileAPI;
exports.putMemberProfileAPI = putMemberProfileAPI;
//# sourceMappingURL=profile.js.map
