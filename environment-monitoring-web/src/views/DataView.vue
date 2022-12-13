<template>
    <el-card class="guest-container">
        <template #header>
            <div class="header">
                <el-button type="primary" size="small" icon="el-icon-plus" @click="handleSolve">修改数据记录</el-button>
                <el-button type="danger" size="small" icon="el-icon-delete" @click="handleForbid">删除数据记录</el-button>
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

<script>
import { ref } from 'vue'
import Table from '@/components/Table.vue'
import { ElMessage } from 'element-plus'
import { Plus, Delete } from '@element-plus/icons-vue'
import axios from 'axios'

const table = ref(null) // 绑定 Table  的 ref 属性
// 解禁方法
const handleSolve = () => {
  // 可以通过 table 获取到组件内部的 multipleSelection 值
  if (!table.value.state.multipleSelection.length) {
    ElMessage.error('请选择项')
    return
  }
  axios.put(`/users/0`, {
    ids: table.value.state.multipleSelection.map(item => item.userId)
  }).then(() => {
    ElMessage.success('解除成功')
    // 通过 table 获取组件内部的 getList 方法
    table.value.getList()
  })
}
// 禁用方法
const handleForbid = () => {
  // 可以通过 table 获取到组件内部的 multipleSelection 值
  if (!table.value.state.multipleSelection.length) {
    ElMessage.error('请选择项')
    return
  }
  axios.put(`/users/1`, {
    ids: table.value.state.multipleSelection.map(item => item.userId)
  }).then(() => {
    ElMessage.success('禁用成功')
    // 通过 table 获取组件内部的 getList 方法
    table.value.getList()
  })
}
</script>