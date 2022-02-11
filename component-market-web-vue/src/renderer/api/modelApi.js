import $api from './$api'

export default {
  'modelGroups': function (moduleId) {
    return $api({
      method: 'get',
      url: '/model/groups?moduleId=' + moduleId
    })
  },
  'detail': function (id) {
    return $api({
      method: 'get',
      url: '/model/detail?id=' + id
    })
  },
  'saveBaseInfo': function (param) {
    return $api({
      method: 'post',
      url: '/model/saveBaseInfo',
      data: param
    })
  },
}
