<template>
  <div>
    <a-drawer
        :title="!this.appId?'创建应用':'编辑应用'"
        :width="720"
        :visible="visible"
        :body-style="{ paddingBottom: '80px' }"
        @close="onClose"
    >
      <a-form :form="form" layout="vertical" hide-required-mark>
        <a-row :gutter="16">
          <a-col :span="12">
            <a-form-item label="应用名称">
              <a-input
                  v-decorator="[
                  'appName',
                  {
                    rules: [{ required: true, message: '请输入应用名称' }],
                  },
                ]"
                  placeholder="请输入应用名称"
              />
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="类别">
              <a-select
                  v-decorator="[
                  'appKind',
                  {
                    rules: [{ required: true, message: '请选择应用类别' }],
                  },
                ]"
                  placeholder="请选择应用类别"
              >
                <a-select-option value="spring_boot">
                  SPRING_BOOT
                </a-select-option>
              </a-select>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row :gutter="16">
          <a-col :span="12">
            <a-form-item label="应用中文名称">
              <a-input
                  v-decorator="[
                  'appNameCn',
                  {
                    rules: [{ required: true, message: '请输入应用中文名称' }],
                  },
                ]"
                  placeholder="请输入应用中文名称"
              />
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="应用logo">
              <a-upload
                  v-decorator="[
                    'logoFile',
                    {
                      valuePropName: 'fileList',
                      getValueFromEvent: normFile,
                    },
                  ]"
                  :before-upload="beforeUpload"
                  name="file"
                  :action="uploadLogoUrl"
                  list-type="picture"
              >
                <a-button>
                  <a-icon type="upload"/>
                  选择logo
                </a-button>
              </a-upload>
            </a-form-item>
          </a-col>
        </a-row>

        <a-row :gutter="16">
          <a-col :span="24">
            <a-form-item label="描述信息">
              <a-textarea
                  v-decorator="[
                  'appDesc',
                  {
                    rules: [{ required: true, message: '请输入应用描述' }],
                  },
                ]"
                  :rows="4"
                  placeholder="请输入应用描述"
              />
            </a-form-item>
          </a-col>
        </a-row>
      </a-form>
      <div
          :style="{
          position: 'absolute',
          right: 0,
          bottom: 0,
          width: '100%',
          borderTop: '1px solid #e9e9e9',
          padding: '10px 16px',
          background: '#fff',
          textAlign: 'right',
          zIndex: 1,
        }"
      >
        <a-button :style="{ marginRight: '8px' }" @click="onClose">
          取消
        </a-button>
        <a-button type="primary" @click="onSubmit">
          提交
        </a-button>
      </div>
    </a-drawer>
  </div>
</template>
<script>

import $api from '../../api/$api'
import appApi from '../../api/appApi'

export default {
  data () {
    return {
      form: this.$form.createForm(this),
      uploadLogoUrl: $api.baseURL + '/file/upload/logo',
      visible: false,
      appId: null
    }
  },
  methods: {
    normFile (e) {
      if (Array.isArray(e)) {
        return e
      }
      return e && e.fileList
    },
    beforeUpload (file) {
      const isJpgOrPng = file.type === 'image/jpeg' || file.type === 'image/png'
      if (!isJpgOrPng) {
        this.$message.error('图片格式不合法,' + file.type)
        return false
      }
      const isLt2M = file.size / 1024 / 1024 < 2
      if (!isLt2M) {
        this.$message.error('Image must smaller than 2MB!')
      }
      return isJpgOrPng && isLt2M
    },
    showDrawer (appId) {
      let that = this
      this.form.resetFields()
      this.visible = true
      this.appId = appId
      if (appId) {
        appApi.detail(appId).then(function (app) {
          that.form.setFieldsValue(app)
          that.form.setFieldsValue({'logoFile': [
            {
              uid: -appId, // 文件唯一标识，建议设置为负数，防止和内部产生的 id 冲突
              name: app.appLogoUrl, // 文件名
              status: 'done' // 状态有：uploading done error removed
            }
          ]})
        })
      }
    },
    onClose () {
      this.visible = false
    },
    onSubmit () {
      let that = this
      this.form.validateFields((err, values) => {
        if (!err) {
          if (!values.logoFile || !values.logoFile[0]) {
            this.$message.info('请上传应用logo!')
            return
          }
          if (values.logoFile[0].response) {
            values.logoUrl = values.logoFile[0].response.data
          } else {
            values.logoUrl = values.logoFile[0].name
          }
          values.id = that.appId
          appApi.save(values).then(function (data) {
            that.$message.info('应用创建成功！')
            that.onClose()
          })
        }
      })
    }
  }
}
</script>
