<template>
  <div id="project" style="background-color: #ffffff;height: 100%">
    <div style="padding: 20px">
      <a-row>
        <a-col :span="20">
          <a-breadcrumb>
            <a-breadcrumb-item>主页</a-breadcrumb-item>
            <a-breadcrumb-item>
              <router-link to="/">应用列表</router-link>
            </a-breadcrumb-item>
          </a-breadcrumb>
          <h2 style="margin-top: 10px">{{ appDetail.appName + '[' + appDetail.appNameCn + ']' }}</h2>
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

    <div class="card-container">
      <a-tabs type="card">
        <a-tab-pane key="1" tab="模块管理">
          <a-table :columns="moduleColumns" :data-source="moduleData">
            <span slot="moduleNameSlot" slot-scope="text,record">
              <router-link :to="'/module/'+record.id">
                {{ text }}[{{ record.moduleNameCn }}]
              </router-link>
            </span>
            <span slot="moduleDescSlot" slot-scope="text">
             <TextEllipsis :text="text" height="30" v-width="200">
                <template slot="more">...</template>
              </TextEllipsis>
            </span>
            <span slot="operateSlot" slot-scope="text,record">
                <a-button type="link" icon="edit"/>
                <a-button type="link" icon="delete"/>
            </span>
          </a-table>
        </a-tab-pane>
        <a-tab-pane key="2" tab="配置管理">
          <p>Content of Tab Pane 2</p>
          <p>Content of Tab Pane 2</p>
          <p>Content of Tab Pane 2</p>
        </a-tab-pane>
        <a-tab-pane key="3" tab="成员管理">
          <p>Content of Tab Pane 3</p>
          <p>Content of Tab Pane 3</p>
          <p>Content of Tab Pane 3</p>
        </a-tab-pane>
        <a-tab-pane key="4" tab="依赖管理">
          <p>Content of Tab Pane 3</p>
          <p>Content of Tab Pane 3</p>
          <p>Content of Tab Pane 3</p>
        </a-tab-pane>
        <a-tab-pane key="5" tab="发布管理">
          <p>Content of Tab Pane 3</p>
          <p>Content of Tab Pane 3</p>
          <p>Content of Tab Pane 3</p>
        </a-tab-pane>
        <a-tab-pane key="6" tab="变更记录">
          <p>Content of Tab Pane 3</p>
          <p>Content of Tab Pane 3</p>
          <p>Content of Tab Pane 3</p>
        </a-tab-pane>
      </a-tabs>
    </div>
  </div>
</template>

<script>
import appApi from '../api/appApi'
import moduleApi from '../api/moduleApi'

export default {
  name: 'project',
  data: function () {
    return {
      moduleData: [],
      moduleColumns: [
        {
          dataIndex: 'moduleName',
          title: '模块名称',
          key: 'moduleName',
          scopedSlots: {customRender: 'moduleNameSlot'}
        },
        {
          dataIndex: 'moduleOwner',
          title: '负责人',
          key: 'moduleOwner'
        },
        {
          dataIndex: 'moduleDesc',
          title: '描述',
          key: 'moduleDesc',
          scopedSlots: {customRender: 'moduleDescSlot'}
        },
        {
          dataIndex: 'operate',
          title: '操作',
          key: 'operate',
          scopedSlots: {customRender: 'operateSlot'}
        }
      ],
      appDetail: {}
    }
  },
  components: {},
  methods: {
    loadAppDetail: function (appId) {
      let that = this
      appApi.detail(appId).then(function (data) {
        that.appDetail = data
      })
      moduleApi.listByApp(appId).then(function (data) {
        that.moduleData = data
      })
    }
  },
  mounted () {
    this.loadAppDetail(this.$route.params.id)
  },
  beforeRouteUpdate (to, from, next) {
    // react to route changes...
    // don't forget to call next()
    this.loadAppDetail(to)
    next()
  }
}
</script>

<style>
.card-container {
  background: #f5f5f5;
  overflow: hidden;
  padding: 0px;
}

.card-container > .ant-tabs-card > .ant-tabs-content {
  height: 120px;
  margin-top: -16px;
}

.card-container > .ant-tabs-card > .ant-tabs-content > .ant-tabs-tabpane {
  background: #fff;
  padding: 16px;
}

.card-container > .ant-tabs-card > .ant-tabs-bar {
  border-color: #fff;
}

.card-container > .ant-tabs-card > .ant-tabs-bar .ant-tabs-tab {
  border-color: transparent;
  background: transparent;
}

.card-container > .ant-tabs-card > .ant-tabs-bar .ant-tabs-tab-active {
  border-color: #fff;
  background: #fff;
}
</style>
