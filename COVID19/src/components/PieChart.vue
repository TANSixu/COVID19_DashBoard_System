<template>
  <div id = "pie-chart-page">
    <div id = "pie-chart">
      <router-view/>
    </div>
  </div>
</template>

<script>
import * as echarts from 'echarts'
import axios from 'axios'
export default {
  name: "PieChart",
  data() {
    return {
      option: {
        title: {
          text: 'Total Cases of Each Continent',
          subtext: '6 continents',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          left: 'left'
        },
        series: [
          {
            name: 'Access From',
            type: 'pie',
            radius: '50%',
            data: [
              { value: 1048, name: 'Search Engine' },
              { value: 735, name: 'Direct' },
              { value: 580, name: 'Email' },
              { value: 484, name: 'Union Ads' },
              { value: 300, name: 'Video Ads' }
            ],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      }
    }
  },
  mounted() {
    let chart = echarts.getInstanceByDom(document.getElementById('pie-chart'))
    if (chart == null) {
      chart = echarts.init(document.getElementById('pie-chart'))
    }
    axios.get("http://10.26.144.47:8080/data/continent")
    .then(response => {
      let data = response.data
      console.log(`data = ${data}`)
      this.option.series[0].data =
      [
        {
          name: data[0].name,
          value: data[0].value
        },
        {
          name: data[1].name,
          value: data[1].value
        },
        {
          name: data[2].name,
          value: data[2].value
        },
        {
          name: data[3].name,
          value: data[3].value
        },
        {
          name: data[4].name,
          value: data[4].value
        },
        {
          name: data[5].name,
          value: data[5].value
        }
      ]
      chart.setOption(this.option)
    })
    .catch(function(error) {
      console.log(error)
    })

  },
  methods: {

  }
}
</script>

<style>
#pie-chart-page {
  width: 1280px;
  height: 640px;
  margin: auto;
  position: absolute;
  left: 0;
  right: 0;
}
#pie-chart {
  width: 100%;
  height: 640px;
}
</style>
