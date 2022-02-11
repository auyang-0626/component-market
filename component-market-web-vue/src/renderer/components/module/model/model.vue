<template>
  <div id="model" >
    <model-create ref="refModelCreate"/>
    <a-layout>
      <a-layout-sider style="background-color: #ffffff">
        <a-directory-tree default-expand-all >
          <a-tree-node v-for="item in entityTree" :key="item.groupKey" >
            <template slot="title" slot-scope="node">
              <span @mouseover="showNodeOperate(false,item.groupKey,true)" @mouseleave="showNodeOperate(false,item.groupKey,false)">
                {{item.groupName}}
                <span style="margin-left: 10px" v-show="item.mouseoverFlag">
                  <a-icon type="plus"  @click="showModelEdit(item.groupKey)"/>
                </span>
              </span>
            </template>
            <a-tree-node v-for="modelItem in item.models" :key="modelItem.id"  is-leaf>
              <template slot="title" slot-scope="node">
              <span @mouseover="showNodeOperate(true,modelItem.id,true)" @mouseleave="showNodeOperate(true,modelItem.id,false)">
                {{modelItem.modelNameCn}}
                <span style="margin-left: 10px" v-show="modelItem.mouseoverFlag">
                  <a-icon type="edit"  @click="showModelEdit(item.groupKey,modelItem.id)"/>
                </span>
              </span>
              </template>
            </a-tree-node>
          </a-tree-node>
        </a-directory-tree>
      </a-layout-sider>
      <a-layout>
        <a-layout-content>{{moduleId}}</a-layout-content>
      </a-layout>
    </a-layout>
  </div>
</template>

<script>

import modelApi from '../../../api/modelApi'
import modelCreate from './modelCreate'

export default {
  name: 'model',
  props: ['moduleId'],
  data: function () {
    return {
      entityTree: []
    }
  },
  components: {modelCreate},
  methods: {
    modelGroups (moduleId) {
      let that = this
      modelApi.modelGroups(moduleId).then(function (data) {
        that.entityTree = data
      })
    },
    showNodeOperate (isLeaf, key, flag) {
      this.entityTree = this.entityTree.map(function (view) {
        if (!isLeaf) {
          if (view.groupKey === key) {
            view.mouseoverFlag = flag
          }
        } else {
          view.models = view.models.map(function (model) {
            if (model.id === key) {
              model.mouseoverFlag = flag
            }
            return model
          })
        }
        return view
      })
    },
    showModelEdit (groupKey, id) {
      let that = this
      this.$refs.refModelCreate.showDrawer({
        moduleId: that.$route.params.id,
        modelKind: groupKey,
        id: id
      })
    }
  },
  mounted () {
    this.modelGroups(this.$route.params.id)
  }
}
</script>
