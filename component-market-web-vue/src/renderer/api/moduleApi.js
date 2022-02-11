import $api from './$api'

export default {
  'listByApp': function (appId) {
    return $api({
      method: 'get',
      url: '/module/listByApp?appId=' + appId
    })
  },
  'detail': function (id) {
    return $api({
      method: 'get',
      url: '/module/detail?id=' + id
    })
  }
}
