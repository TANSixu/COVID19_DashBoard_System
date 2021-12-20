<template>
  <div id = "dynamic-trend-page">
    <div id = "dynamic-trend">
      <router-view/>
    </div>
  </div>
</template>

<script>
import * as echarts from 'echarts'
import * as lifeExpectationTable from "../assets/life-expectation-table.json"
import axios from 'axios'
export default {
  name: "DynamicTrend",
  data() {
    return {
      updateFrequency: 500,
      dimension: 0,
      ROOT_PATH: 'https://cdn.jsdelivr.net/gh/apache/echarts-website@asf-site/examples',
      countryColors: {
        'Australia': '#00008b',
        'Canada': '#f00',
        'China': '#ffde00',
        'Cuba': '#002a8f',
        'Finland': '#003580',
        'France': '#ed2939',
        'Germany': '#000',
        'Iceland': '#003897',
        'India': '#f93',
        'Japan': '#bc002d',
        'North Korea': '#024fa2',
        'South Korea': '#000',
        'New Zealand': '#00247d',
        'Norway': '#ef2b2d',
        'Poland': '#dc143c',
        'Russia': '#d52b1e',
        'Turkey': '#e30a17',
        'United Kingdom': '#00247d',
        'United States': '#b22234',
        "South Africa": '#0000ff',
        'United Kingdom': '#485039',
        'Brazil': '#78f930',
        'United States': '#0345f7'
      },
      option: {}
    }
  },
  mounted() {
    var _this = this
    let chart = echarts.getInstanceByDom(document.getElementById('dynamic-trend'))
    if (chart == null) {
      chart = echarts.init(document.getElementById('dynamic-trend'))
    }
    axios.all([
      axios.get("https://cdn.jsdelivr.net/npm/emoji-flags@1.3.0/data.json"),
      axios.get("http://localhost:8888/data/dynamic")]
    ).then(axios.spread(function (res0, res1) {
      const flags = res0.data
      let temp = res1.data
      let data = []
      data.push["cases", "country", "date"]
      for (let i = 1; i < temp.length; i++) {
        data.push([temp[i].cases, temp[i].country, temp[i].data])
      }
      const dates = [];
      for (let i = 0; i < data.length; ++i) {
        if (dates.length === 0 || dates[dates.length - 1] !== data[i][2]) {
          dates.push(data[i][2]);
        }
      }
      function getFlag(countryName) {
        if (!countryName) {
          return '';
        }
        return (
          flags.find(function (item) {
            return item.name === countryName;
          }) || {}
        ).emoji;
      }
      let startIndex = 1;
      let startDate = dates[startIndex];
      _this.option = {
        grid: {
          top: 10,
          bottom: 30,
          left: 150,
          right: 80
        },
        xAxis: {
          max: 'dataMax',
          axisLabel: {
            formatter: function (n) {
              return Math.round(n) + '';
            }
          }
        },
        dataset: {
          source: data.slice(1).filter(function (d) {
            return d[2] === startDate;
          })
        },
        yAxis: {
          type: 'category',
          inverse: true,
          max: 10,
          axisLabel: {
            show: true,
            fontSize: 14,
            formatter: function (value) {
              return value + '{flag|' + getFlag(value) + '}';
            },
            rich: {
              flag: {
                fontSize: 25,
                padding: 5
              }
            }
          },
          animationDuration: 300,
          animationDurationUpdate: 300
        },
        toolbox: {
          feature: {
            saveAsImage: {}
          },
        },
        series: [
          {
            realtimeSort: true,
            seriesLayoutBy: 'column',
            type: 'bar',
            itemStyle: {
              color: function (param) {
                return _this.countryColors[param.value[1]] || '#5470c6';
              }
            },
            encode: {
              x: _this.dimension,
              y: 3
            },
            label: {
              show: true,
              precision: 1,
              position: 'right',
              valueAnimation: true,
              fontFamily: 'monospace'
            }
          }
        ],
        // Disable init animation.
        animationDuration: 0,
        animationDurationUpdate: _this.updateFrequency,
        animationEasing: 'linear',
        animationEasingUpdate: 'linear',
        graphic: {
          elements: [
            {
              type: 'text',
              right: 160,
              bottom: 60,
              style: {
                text: startDate,
                font: 'bolder 80px monospace',
                fill: 'rgba(100, 100, 100, 0.25)'
              },
              z: 100
            }
          ]
        }
      }
      chart.setOption(_this.option);
      for (let i = startIndex; i < dates.length - 1; ++i) {
        (function (i) {
          setTimeout(function () {
            updateDate(dates[i + 1]);
          }, (i - startIndex) * _this.updateFrequency);
        })(i);
      }
      function updateDate(date) {
        let source = data.slice(1).filter(function (d) {
          return d[2] === date;
        });
        _this.option.series[0].data = source;
        _this.option.graphic.elements[0].style.text = date;
        chart.setOption(_this.option);
      }
    }));
  }
}
</script>

<style>
#dynamic-trend-page {
  width: 1280px;
  height: 640px;
  margin: auto;
  position: absolute;
  left: 0;
  right: 0;
}
#dynamic-trend{
  widows: 100%;
  height: 640px;
}
</style>
