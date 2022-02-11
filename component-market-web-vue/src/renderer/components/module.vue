<template>
  <div id="module" style="background-color: #ffffff;height: 100%">
    <div style="padding: 20px">
      <a-row>
        <a-col :span="20">
          <a-breadcrumb>
            <a-breadcrumb-item>主页</a-breadcrumb-item>
            <a-breadcrumb-item>
              <router-link to="/">应用列表</router-link>
            </a-breadcrumb-item>
            <a-breadcrumb-item>
              <router-link :to="'/project/'+moduleDetail.appId">应用详情</router-link>
            </a-breadcrumb-item>
          </a-breadcrumb>
          <h2 style="margin-top: 10px">{{moduleDetail.moduleName}}[{{moduleDetail.moduleNameCn}}]</h2>
        </a-col>
        <a-col :span="4" style="line-height: 70px;text-align: center">
          <a-space>
            <a-avatar size="large" style="color: #f56a00; backgroundColor: #fde3cf">
              Y
            </a-avatar>
            杨光跃
            <a-tooltip>
              <template slot="title">
                <span>退出</span>
              </template>
              <a-icon type="poweroff" style="color: coral"/>
            </a-tooltip>
          </a-space>
        </a-col>
      </a-row>
    </div>

    <a-tabs type="card" >
      <a-tab-pane key="1" tab="模型">
        <model :moduleId="$route.params.id"/>
      </a-tab-pane>
      <a-tab-pane key="2" tab="逻辑">
        Content of Tab Pane 2
      </a-tab-pane>
      <a-tab-pane key="3" tab="页面">
        Content of Tab Pane 3
      </a-tab-pane>
    </a-tabs>
  </div>
</template>

<script>
import moduleApi from '../api/moduleApi'
import model from './module/model/model'

export default {
  name: 'module',
  data: function () {
    return {
      moduleDetail: {}
    }
  },
  components: {model},
  methods: {
    loadModuleDetail: function (id) {
      let that = this
      moduleApi.detail(id).then(function (data) {
        that.moduleDetail = data
      })
    }
  },
  mounted () {
    this.loadModuleDetail(this.$route.params.id)
  }
}
</script>
