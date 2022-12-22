<template>
    <el-card class="guest-container">
        <template #header>
            <div class="header">
                <!-- <el-button type="primary" size="small"  @click="handleSolve">
                <el-icon style="vertical-align: middle;">
                <Plus/>
                </el-icon>
                修改数据记录</el-button> -->
                <el-button type="danger" size="small" @click="deleteData">
                 <el-icon style="vertical-align: middle;">
                <Delete/>
                </el-icon>
                删除数据记录</el-button>
            </div>
        </template>
        <Table action='/environmentData/pageQueryOrderByTime' ref="table">
            <template #column>
                <el-table-column type="selection" width="55">
                </el-table-column>
                <el-table-column prop="time" label="测量时间">
                </el-table-column>
                <el-table-column prop="lightIntensity" label="光照强度">
                </el-table-column>
                <el-table-column prop="temperature" label="温度">
                </el-table-column>
                <el-table-column prop="airHumidity" label="湿度">
                </el-table-column>
                <el-table-column prop="soilMoisture" label="土壤湿度">
                </el-table-column>
                <el-table-column label="是否删除">
                    <template #default="scope">
                        <span :style="scope.row.lockedFlag == 0 ? 'color: green;' : 'color: red;'">
                            {{ scope.row.lockedFlag == 0 ? '调整' : '删除' }}
                        </span>
                    </template>
                </el-table-column>
                <!-- <el-table-column prop="createTime" label="注册时间">
                </el-table-column> -->
            </template>
        </Table>
    </el-card>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import Table from '@/components/Table.vue'
import { ElMessage } from 'element-plus'
import { Plus, Delete } from '@element-plus/icons-vue'
import axios from 'axios'

const table = ref(null) // 绑定 Table  的 ref 属性
// 删除方法
const deleteData = () => {
  // 可以通过 table 获取到组件内部的 multipleSelection 值
  if (!table.value.state.multipleSelection.length) {
    ElMessage.error('请选择项')
    return
  }
  var idsStr: string = "ids=" + (table.value.state.multipleSelection.map(item => item.id)).join('&ids=')
  console.log(idsStr)
  var config = {
    method: 'delete',
    url: '/api/environmentData/batch' + '?' + idsStr,
    headers: {}
  };
  axios(config).then(() => {
    ElMessage.success('删除成功')
    // 通过 table 获取组件内部的 getList 方法
    table.value.getList()
  });
}
</script>