<template>
  <div>
    <a-drawer
        :title="!this.modelDetail.id?'创建模型':'编辑模型'"
        width="90%"
        :visible="visible"
        :body-style="{ paddingBottom: '80px' }"
        @close="onClose"
    >
      <a-steps :current="current">
        <a-step v-for="item in steps" :key="item.title" :title="item.title" />
      </a-steps>
      <div class="steps-content" style="padding: 20px">
          <model-basic ref="refModelBasic" v-if="current === 0" />
          <model-attr ref="refModelAttr" v-else-if="current === 1"/>
      </div>
      <div class="steps-action">
        <a-button v-if="current < steps.length - 1" type="primary" @click="next">
          下一步
        </a-button>
        <a-button
            v-if="current === steps.length - 1"
            type="primary"
            @click="$message.success('Processing complete!')"
        >
          提交
        </a-button>
        <a-button v-if="current > 0" style="margin-left: 8px" @click="prev">
          上一步
        </a-button>
      </div>
    </a-drawer>
  </div>
</template>
<script>

import modelBasic from './modelBasic'
import ModelAttr from './ModelAttr'

export default {
  name: 'model-create',
  components: {modelBasic, ModelAttr},
  data () {
    return {
      form: this.$form.createForm(this),
      visible: false,
      modelDetail: {},
      current: 0,
      steps: [
        {
          title: '基本信息',
          refKey: 'refModelBasic',
          content: 'First-content'
        },
        {
          title: '属性',
          refKey: 'refModelAttr',
          content: 'Second-content'
        },
        {
          title: '关联关系',
          content: 'Second-content'
        },
        {
          title: '影响范围',
          content: 'Last-content'
        }
      ]
    }
  },
  methods: {
    onClose () {
      this.visible = false
    },
    showDrawer (modelDetail) {
      this.visible = true
      this.current = 0
      this.modelDetail = modelDetail
      this.showStepView()
    },
    next () {
      let key = this.steps[this.current].refKey
      if (key) {
        let that = this
        this.$refs[key].saveForm(function (id) {
          that.modelDetail.id = id
          that.current++
          that.showStepView()
        })
      }
    },
    prev () {
      this.current--
      this.showStepView()
    },
    showStepView () {
      let key = this.steps[this.current].refKey
      if (key) {
        this.$nextTick(function () {
          this.$refs[key].initForm(this.modelDetail)
        })
      }
    }
  }
}
</script>
