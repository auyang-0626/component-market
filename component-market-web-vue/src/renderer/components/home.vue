<template>
  <div id="home" style="background-color: #ececec;height: 100%">
    <app-create ref="refAppCreate"/>
    <div style="padding:20px;background-color: #ffffff">
      <a-space>
        <img src="../assets/app_factory_log.svg" width="30" height="30"/>
        <span style="font-weight: bold;line-height: 30px;font-size: 26px;color: #000000">
          component-market
        </span>
      </a-space>
      <p style="margin-top: 20px">
        component-market是用于快速构建前后端服务的低代码平台，目前正在快速建设中，敬请期待！！
      </p>
      <div>
        <a-space>
          <a-button type="link">
            <a-icon type="question-circle"/>
            5分钟快速了解
          </a-button>
          <a-button type="link">
            <a-icon type="unordered-list"/>
            产品文档
          </a-button>
          <a-button type="link">
            <a-icon type="exclamation-circle"/>
            咨询反馈
          </a-button>
        </a-space>
        <a-button type="primary" @click="showAppCreate(null)" style="float: right">
          <a-icon type="plus"/>
          创建应用
        </a-button>
      </div>
    </div>
    <div style="padding: 20px">
      <a-row :gutter="16">
        <a-col :span="8" v-for="app in appList" :key="app.id">
          <a-card style="height: 240px">
            <a-row :gutter="4">
              <a-col :span="20">
                <a-space align="center">
                  <a-avatar shape="square" size="large" icon="user" :src="baseURL+'/file/image?key='+app.appLogoUrl"/>
                  <router-link :to="'/project/'+app.id">
                    <span style="font-size: 24px;font-weight: bold;">{{ app.appName }}[{{ app.appNameCn }}]</span>
                  </router-link>

                </a-space>
              </a-col>
              <a-col :span="2" align="center">
                <a-button type="link" icon="edit" @click="showAppCreate(app.id)"/>
              </a-col>
              <a-col :span="2" align="center">
                <a-button type="link" icon="delete"/>
              </a-col>
            </a-row>
            <p style="margin-top: 20px">{{ app.appDesc }}</p>
          </a-card>
        </a-col>
      </a-row>
    </div>
  </div>
</template>

<script>
import appApi from '../api/appApi'
import AppCreate from './home/AppCreate'
import $api from '../api/$api'

export default {
  name: 'home',
  data: function () {
    return {
      baseURL: $api.baseURL,
      appList: [{'id': 1, 'appName': 'xxxx'}]
    }
  },
  components: {AppCreate},
  methods: {

    fetchApp: function () {
      let that = this
      appApi.page({
        appKind: 'spring_boot',
        page: {
          page: 0,
          size: 10
        }
      }).then(function (res) {
        that.appList = res.records
      })
    },
    showAppCreate: function (appId) {
      this.$refs.refAppCreate.showDrawer(appId)
    }
  },
  mounted () {
    this.fetchApp()
  }
}
</script>
