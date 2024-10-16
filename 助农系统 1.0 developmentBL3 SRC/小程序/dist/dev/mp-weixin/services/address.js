"use strict";
const utils_http = require("../utils/http.js");
const postMemberAddressAPI = (data) => {
  return utils_http.http2({
    method: "POST",
    url: "/user/addressBook",
    data
  });
};
const getMemberAddressAPI = () => {
  return utils_http.http2({
    method: "GET",
    url: "/user/addressBook"
  });
};
const getMemberAddressByIdAPI = (id) => {
  return utils_http.http2({
    method: "GET",
    url: `/user/addressBook/${id}`
  });
};
const putMemberAddressByIdAPI = (id, data) => {
  return utils_http.http2({
    method: "PUT",
    url: `/user/addressBook/${id}`,
    data
  });
};
const deleteMemberAddressByIdAPI = (id) => {
  return utils_http.http2({
    method: "DELETE",
    url: `/user/addressBook/${id}`
  });
};
exports.deleteMemberAddressByIdAPI = deleteMemberAddressByIdAPI;
exports.getMemberAddressAPI = getMemberAddressAPI;
exports.getMemberAddressByIdAPI = getMemberAddressByIdAPI;
exports.postMemberAddressAPI = postMemberAddressAPI;
exports.putMemberAddressByIdAPI = putMemberAddressByIdAPI;
//# sourceMappingURL=address.js.map
