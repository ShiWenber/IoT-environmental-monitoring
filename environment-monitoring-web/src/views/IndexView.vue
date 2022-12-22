<script setup lang="ts">
import axios from 'axios';
import { reactive } from 'vue'
import { dataType } from 'element-plus/es/components/table-v2/src/common';
import TheWelcome from '../components/TheWelcome.vue'
import { tsMappedType } from '@babel/types';
// const ENV = import.meta.env
// export default {
//   name: 'HomeView',
//   setup() {
//     console.log(ENV)
//   }
// }
// 将上述代码转化为setup模式
const ENV = import.meta.env
console.log("ENV" + ENV.BASE_URL)
console.log("ENV" + ENV.VITE_BASE_URL)
console.log("ENV" + ENV.TEST)
import { onMounted, onUnmounted } from 'vue'

const state = reactive({
  total: 0,
  today: 0,
  runningTime: 0
})
/**
 * 时间戳转换为时间函数
 */
function timestampToTimeInterval(timestamp) {
  timestamp = timestamp ? timestamp : null;
  let D = Math.floor(timestamp / 1000 / 60 / 60 / 24);
  let h = Math.floor(timestamp / 1000 / 60 / 60 % 24);
  let m = Math.floor(timestamp / 1000 / 60 % 60);
  let s = Math.floor(timestamp / 1000 % 60);
  return D + "天" + h + "时" + m + "分" + s + "秒";
}



let myChart = null

function initChart(res, myChart) {
  var today = new Date();
  var todaynum = Date.parse(new Date(today.toLocaleDateString()).toString());
  var min = null
  var max = null

  let lightIntensities = [];
  let temperatures = [];
  let airHumidities = [];
  let soilMoistures = [];
  for (let i = 0; i < res.data.length; i++) {
    let now = new Date(res.data[i].time);
    // 取补使得数值和物理意义正相关,并映射到0-200
    lightIntensities.push([+now, (1024 - res.data[i].lightIntensity)/5]);
    temperatures.push([+now, res.data[i].temperature]);
    airHumidities.push([+now, res.data[i].airHumidity]);
    soilMoistures.push([+now,(1024 - res.data[i].soilMoisture)/5]);

    if (Date.parse(new Date(res.data[i].time).toString()) - todaynum >= 0) {
      state.today += 1;
    }
    // 找到第一个不为空的时间赋值给min和max
    if (min == null && res.data[i].time != null) {
      min = res.data[i].time
    }
    if (max == null && res.data[i].time != null) {
      max = res.data[i].time
    }
    // 找最大最小值
    for (let j = 0; j < res.data.length; j++) {
      if ((Date.parse(new Date(res.data[i].time).toString()) < Date.parse(new Date(min).toString())) && res.data[i].time != null) {
        min = res.data[i].time
      }
      if (Date.parse(new Date(res.data[i].time).toString()) > Date.parse(new Date(max).toString())) {
        max = res.data[i].time
      }
    }


  }
  // 求出最大最小时间戳的差转化为
  state.runningTime = timestampToTimeInterval((Date.parse(new Date(max).toString()) - Date.parse(new Date(min).toString())))
  // 指定图表的配置项和数据
  state.total = res.data.length
  const option = {
    title: {
      text: '数据折线图'
    },
    tooltip: {
      trigger: 'axis',
      position: function (pt) {
        return [pt[0], '10%'];
      }
    },
    legend: {
      data: ['光照强度', '温度', '湿度', '土壤湿度']
    },
    toolbox: {
      feature: {
        dataZoom: {
          yAxisIndex: 'none'
        },
        restore: {},
        saveAsImage: {}
      }
    },
    grid: {
      left: '3%',
      right: '4%',
      bottom: '3%',
      containLabel: true
    },
    xAxis:
    {
      type: 'time',
      boundaryGap: false,
    },
    yAxis:
    {
      type: 'value',
      boundaryGap: [0, '100%']
    },
    dataZoom: [
      {
        type: 'inside',
        start: 0,
        end: 100
      },
      {
        start: 0,
        end: 20,
      }
    ],
    series: [
      {
        name: '光照强度',
        type: 'line',
        // smooth: true,
        areaStyle: {},
        emphasis: {
          focus: 'series'
        },
        data: lightIntensities
      },
      {
        name: '温度',
        type: 'line',
        areaStyle: {},
        emphasis: {
          focus: 'series'
        },
        data: temperatures
      },
      {
        name: '湿度',
        type: 'line',
        areaStyle: {},
        emphasis: {
          focus: 'series'
        },
        data: airHumidities
      },
      {
        name: '土壤湿度',
        type: 'line',
        areaStyle: {},
        emphasis: {
          focus: 'series'
        },
        data: soilMoistures
      }
    ]
  }
  // 使用刚指定的配置项和数据显示图表。
  myChart.setOption(option)
}

// setInterval(() => {
//   state.today = 0;
//   state.runningTime = 0;
//   state.total = 0;



//   if (window.echarts) {
//     // 基于准备好的dom，初始化echarts实例
//     myChart = window.echarts.init(document.getElementById('zoom'))

//     axios.get('/api/environmentData/all').then(res => {
//       initChart(res, myChart);
//     })
//   }

// }, 10000)

onMounted(() => {
  if (window.echarts) {
    // 基于准备好的dom，初始化echarts实例
    myChart = window.echarts.init(document.getElementById('zoom'))

    axios.get('/api/environmentData/queryAllOrderByTime').then(res => {
      initChart(res, myChart);
    })
  }
})
onUnmounted(() => {
  myChart.dispose()
})

</script>

<template>
  <el-card class="introduce">
    <div class="order">
      <el-card class="order-item">
        <template #header>
          <div class="card-header">
            <span>历史记录总数</span>
          </div>
        </template>
        <div class="item">{{ state.total }}</div>
      </el-card>
      <el-card class="order-item">
        <template #header>
          <div class="card-header">
            <span>今日数据</span>
          </div>
        </template>
        <div class="item">{{ state.today }}</div>
      </el-card>
      <el-card class="order-item">
        <template #header>
          <div class="card-header">
            <span>运行时间</span>
          </div>
        </template>
        <div class="item">{{ state.runningTime }}</div>
      </el-card>
    </div>
    <div id="zoom"></div>
  </el-card>
</template>


<style>
.introduce .order {
  display: flex;
  margin-bottom: 50px;
}

.introduce .order .order-item {
  flex: 1;
  margin-right: 20px;
}

.introduce .order .order-item:last-child {
  margin-right: 0;
}

#zoom {
  min-height: 500px;
}
</style>