import Vue from 'vue'
import Router from 'vue-router'
import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/antd.css'
import { install, TextEllipsis } from 'heyui'

Vue.use(Router)
Vue.use(Antd)
Vue.use(install, { components: { TextEllipsis } })

export default new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      component: require('@/components/home').default
    },
    {
      path: '/project/:id',
      name: 'project',
      component: require('@/components/project').default
    },
    {
      path: '/module/:id',
      name: 'module',
      component: require('@/components/module').default
    },
    {
      path: '*',
      redirect: '/'
    }
  ]
})
