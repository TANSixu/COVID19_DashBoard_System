<template>
  <div id = "weekly-trend-page">
    <div id = "weekly-trend">
      <router-view />
    </div>
  </div>
</template>

<script>
import * as echarts from "echarts"

export default {
  name: "WeeklyTrend",
  data() {
    return {
      option: {
        title: {
          text: 'Stacked Line'
        },
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          data: ['Email', 'Union Ads', 'Video Ads', 'Direct', 'Search Engine']
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        toolbox: {
          feature: {
            saveAsImage: {}
          },
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            name: 'Email',
            type: 'line',
            stack: 'Total',
            data: [120, 132, 101, 134, 90, 230, 210]
          },
          {
            name: 'Union Ads',
            type: 'line',
            stack: 'Total',
            data: [220, 182, 191, 234, 290, 330, 310]
          },
          {
            name: 'Video Ads',
            type: 'line',
            stack: 'Total',
            data: [150, 232, 201, 154, 190, 330, 410]
          },
          {
            name: 'Direct',
            type: 'line',
            stack: 'Total',
            data: [320, 332, 301, 334, 390, 330, 320]
          },
          {
            name: 'Search Engine',
            type: 'line',
            stack: 'Total',
            data: [820, 932, 901, 934, 1290, 1330, 1320]
          }
        ]
      }
    }
  },
  mounted() {
    let chart = echarts.getInstanceByDom(document.getElementById('weekly-trend'))
    if (chart == null) {
      chart = echarts.init(document.getElementById('weekly-trend'))
    }
    chart.setOption(this.option)
  },
  methods: {

  }
}
</script>

<style scoped>
#weekly-trend-page {
  width: 1280px;
  height: 640px;
  margin: auto;
  position: absolute;
  left: 0;
  right: 0;
}
#weekly-trend {
  width: 100%;
  height: 640px;
}
</style>
