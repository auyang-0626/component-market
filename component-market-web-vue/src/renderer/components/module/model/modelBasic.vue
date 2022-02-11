<template>
  <div>
    <a-form :form="form" layout="vertical" hide-required-mark >
      <a-row :gutter="16">
        <a-col :span="12">
          <a-form-item label="模型名称">
            <a-input
                v-decorator="[
                  'modelName',
                  {
                    rules: [{ required: true, message: '请输入模型名称' }],
                  },
                ]"
                placeholder="请输入模型名称"
            />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="模型中文名称">
            <a-input
                v-decorator="[
                  'modelNameCn',
                  {
                    rules: [{ required: true, message: '请输入模型中文名称' }],
                  },
                ]"
                placeholder="请输入模型中文名称"
            />
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="16">
        <a-col :span="12">
          <a-form-item label="类别">
            <a-select
                v-decorator="[
                  'modelKind',
                  {
                    rules: [{ required: true, message: '请选择模型类别' }],
                  },
                ]"
                :disabled="true"
                placeholder="请选择模型类别"
            >
              <a-select-option value="entity">
                数据库实体
              </a-select-option>
              <a-select-option value="enum">
                枚举
              </a-select-option>
              <a-select-option value="dto">
                传输实体
              </a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="描述信息">
            <a-textarea
                v-decorator="[
                  'modelDesc',
                  {
                    rules: [{ required: true, message: '请输入模型描述' }],
                  },
                ]"
                :rows="4"
                placeholder="请输入模型描述"
            />
          </a-form-item>
        </a-col>
      </a-row>
    </a-form>
  </div>
</template>

<script>
import modelApi from '../../../api/modelApi'

export default {
  name: 'model-basic',
  data () {
    return {
      form: this.$form.createForm(this),
      modelDetail: {}
    }
  },
  methods: {
    setFormValues (param) {
      this.modelDetail = param
      let keys = Object.keys(this.form.getFieldsValue())
      let value = {}
      keys.forEach(function (key) {
        value[key] = param[key]
      })
      this.form.setFieldsValue(value)
    },
    initForm (modelDetail) {
      if (modelDetail.id) {
        let that = this
        modelApi.detail(modelDetail.id).then(function (data) {
          that.setFormValues(data)
        })
      } else {
        this.setFormValues(modelDetail)
      }
    },
    saveForm (call) {
      let that = this
      this.form.validateFields((err, values) => {
        if (!err) {
          values.id = that.modelDetail.id
          values.moduleId = that.modelDetail.moduleId
          modelApi.saveBaseInfo(values).then(function (data) {
            that.$message.info('保存成功！')
            call(data)
          })
        }
      })
    }
  }
}
</script>
