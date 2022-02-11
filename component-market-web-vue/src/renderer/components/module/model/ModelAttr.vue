<template>
  <div>

    <a-row>
      <a-col :span="24">
        <a-button type="primary" icon="plus" style="float: right" @click="addAttr">
          新增属性
        </a-button>
      </a-col>
    </a-row>

    <div style="margin-top: 10px;position: relative">
      <a-table :columns="columns" :data-source="data" bordered>
        <template
            v-for="col in ['attrName', 'attrNameCn', 'attrValue', 'attrDesc']"
            :slot="col"
            slot-scope="text, record, index"
        >
          <div :key="col">
            <a-input
                v-if="record.editable"
                style="margin: -5px 0"
                :value="text"
                @change="e => handleChange(e.target.value, record.key, col)"
            />
            <template v-else>
              {{ text }}
            </template>
          </div>
        </template>

        <template slot="operation" slot-scope="text, record, index">
          <div class="editable-row-operations">
        <span v-if="record.editable">
          <a @click="() => save(record.key)">Save</a>
          <a-popconfirm title="Sure to cancel?" @confirm="() => cancel(record.key)">
            <a>Cancel</a>
          </a-popconfirm>
        </span>
            <span v-else>
          <a :disabled="editingKey !== ''" @click="() => edit(record.key)">Edit</a>
        </span>
          </div>
        </template>
      </a-table>
    </div>


  </div>
</template>

<script>

const columns = [
  {
    title: '属性名称',
    dataIndex: 'attrName',
    width: '15%',
    scopedSlots: {customRender: 'attrName'}
  },
  {
    title: '属性中文名',
    dataIndex: 'attrNameCn',
    width: '15%',
    scopedSlots: {customRender: 'attrNameCn'}
  },
  {
    title: '类型',
    dataIndex: 'attrKind',
    width: '10%',
    scopedSlots: {customRender: 'attrKind'}
  },
  {
    title: '类型值',
    dataIndex: 'attrValue',
    width: '15%',
    scopedSlots: {customRender: 'attrValue'}
  },
  {
    title: '主键',
    dataIndex: 'pk',
    width: '10%',
    scopedSlots: {customRender: 'pk'}
  },
  {
    title: '描述',
    dataIndex: 'attrDesc',
    width: '15%',
    scopedSlots: {customRender: 'attrDesc'}
  },
  {
    title: '操作',
    dataIndex: 'operation',
    scopedSlots: {customRender: 'operation'}
  }
]

const data = []
export default {
  data () {
    this.cacheData = data.map(item => ({...item}))
    return {
      data,
      columns,
      editingKey: ''
    }
  },
  methods: {
    initForm (modelDetail) {
      if (modelDetail.id) {
        console.info(modelDetail)
      }
    },
    saveForm (call) {
      let that = this
      call(data)
    },
    addAttr () {
      const newData = [...this.data]
      newData.push({
        key: newData.length.toString()
      })
      console.info(newData)
      this.data = newData
    },
    handleChange (value, key, column) {
      const newData = [...this.data]
      const target = newData.filter(item => key === item.key)[0]
      if (target) {
        target[column] = value
        this.data = newData
      }
    },
    edit (key) {
      const newData = [...this.data]
      const target = newData.filter(item => key === item.key)[0]
      this.editingKey = key
      if (target) {
        target.editable = true
        this.data = newData
      }
    },
    save (key) {
      const newData = [...this.data]
      const newCacheData = [...this.cacheData]
      const target = newData.filter(item => key === item.key)[0]
      const targetCache = newCacheData.filter(item => key === item.key)[0]
      if (target && targetCache) {
        delete target.editable
        this.data = newData
        Object.assign(targetCache, target)
        this.cacheData = newCacheData
      }
      this.editingKey = ''
    },
    cancel (key) {
      const newData = [...this.data]
      const target = newData.filter(item => key === item.key)[0]
      this.editingKey = ''
      if (target) {
        Object.assign(target, this.cacheData.filter(item => key === item.key)[0])
        delete target.editable
        this.data = newData
      }
    }
  }
}
</script>
<style scoped>
.editable-row-operations a {
  margin-right: 8px;
}
</style>
