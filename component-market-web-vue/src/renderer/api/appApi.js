import $api from './$api'

export default {
  'page': function (param) {
    return $api({
      method: 'post',
      url: '/app/page',
      data: param
    })
  },
  'save': function (param) {
    return $api({
      method: 'post',
      url: '/app/save',
      data: param
    })
  },
  'detail': function (id) {
    return $api({
      method: 'get',
      url: '/app/detail?id=' + id
    })
  }
}
