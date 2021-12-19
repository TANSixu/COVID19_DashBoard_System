<template>
  <div id = "line-chart-page">
    <div id = "line-chart">
      <router-view />
    </div>
  </div>
</template>

<script>
import * as echarts from "echarts"
//import * as worldMap from "../assets/world.json"
import * as worldMap from "../assets/world_en.json"
import axios from 'axios'
function onClickButton() {

}
export default {
  name: "LineChart",
  data() {
    return {
      option: {
        title: {
          text: 'New cases, deaths or VACs of                     in 7 days from',
          left: 0,
          textStyle: {
            fontSize: 24
          }
        },
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          data: ['New Cases', 'New Deaths', 'New VACs'],
          right: 25,
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
          data: []
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            name: 'New Deaths',
            type: 'line',
            data: []
          },
          {
            name: 'New VACs',
            type: 'line',
            data: []
          },
          {
            name: 'New Cases',
            type: 'line',
            data: []
          }
        ]
      }
    }
  },
  mounted() {
    var _this = this
    var lineChart = echarts.init(document.getElementById("line-chart"))
    var select_country = document.getElementById("select-country")
    if (select_country == null) {
      select_country = document.createElement("select")
      select_country.id = "select-country"
      select_country.title = "country"
      document.getElementById("line-chart").appendChild(select_country)
      select_country.style.position = "absolute"
      select_country.style.left = "365px"
      select_country.style.top = "0px"
      select_country.style.width = "140px"
      select_country.style.height = "28px"
      select_country.style.fontSize = "20px"
    }
    for (let i = 0; i < worldMap.dataArr.length; i++) {
      var op = document.getElementsByName(worldMap.dataArr[i].name)
      //if (op == null) {
        op = document.createElement("option")
        op.name = worldMap.dataArr[i].name
        op.text = worldMap.dataArr[i].name
        select_country.add(op, null)
        if (worldMap.dataArr[i].name == "China") {
          op.selected = true
        }
      //}
    }
    var from_year = document.getElementById("from-year")
    if (from_year == null) {
      from_year = document.createElement("select")
      from_year.id = "from-year"
      from_year.title = "year"
      document.getElementById("line-chart").appendChild(from_year)
      from_year.style.position = "absolute"
      from_year.style.left = "688px"
      from_year.style.top = "0px"
      from_year.style.width = "72px"
      from_year.style.height = "28px"
      from_year.style.fontSize = "20px"
      for (let i = 2019; i <= new Date().getFullYear(); i++) {
        var op = document.createElement("option")
        op.text = i
        op.value = i
        from_year.add(op, null)
      }
      from_year.selectedIndex = from_year.options.length - 1
    }
    var from_month = document.getElementById("from-month")
    if (from_month == null) {
      from_month = document.createElement("select")
      from_month.id = "from-month"
      from_month.title = "month"
      document.getElementById("line-chart").appendChild(from_month)
      from_month.style.position = "absolute"
      from_month.style.left = "763px"
      from_month.style.top = "0px"
      from_month.style.width = "50px"
      from_month.style.height = "28px"
      from_month.style.fontSize = "20px"
      _this.setMonth()
      from_month.selectedIndex = from_month.options.length - 1
    }
    var from_day = document.getElementById("from-day")
    if (from_day == null) {
      from_day = document.createElement("select")
      from_day.id = "from-day"
      from_day.title = "day"
      document.getElementById("line-chart").appendChild(from_day)
      from_day.style.position = "absolute"
      from_day.style.left = "817px"
      from_day.style.top = "0px"
      from_day.style.width = "50px"
      from_day.style.height = "28px"
      from_day.style.fontSize = "20px"
      _this.setDay()
      from_day.selectedIndex = from_day.options.length - 1
    }
    from_year.onchange = _this.setMonth
    from_month.onchange = _this.setDay
    var query = document.getElementById("line-chart-query")
    if (query == null) {
      query = document.createElement("button")
      document.getElementById("line-chart").appendChild(query)
      query.style.position = "absolute"
      query.style.left = "871px"
      query.style.top = "0px"
      query.style.width = "70px"
      query.style.height = "30px"
      query.title = "Query"
      query.innerHTML = "Query"
      query.style.fontSize = "20px"
      query.style.border = "3px solid #0000ff"
      query.onclick = _this.onClickButton
    }
    let d = new Date(new Date().getTime() - 24*60*60*1000 * 6)
    let date = `${d.getFullYear()}-${d.getMonth() + 1}-${d.getDate()}`
    this.changeOption("China", date)
    lineChart.setOption(this.option)
  },
  methods: {
    setMonth() {
      let from_year = document.getElementById("from-year")
      let from_month = document.getElementById("from-month")
      from_month.innerHTML = ""
      let start, end
      let index = from_year.selectedIndex
      if (from_year.options[index].value == 2019) {
        start = 10
        end = 12
      } else if (from_year.options[index].value == new Date().getFullYear()) {
        start = 1
        end = new Date(new Date().getTime() - 24*60*60*1000 * 6).getMonth() + 1
      } else {
        start = 1
        end = 12
      }
      for (let i = start; i <= end; i++) {
        var op = document.createElement("option")
        op.text = i
        op.value = i
        from_month.add(op, null)
      }
    },
    setDay() {
      let from_year = document.getElementById("from-year")
      let from_month = document.getElementById("from-month")
      let from_day = document.getElementById("from-day")
      from_day.innerHTML = ""
      let start, end
      let indexYear = from_year.selectedIndex
      let year = from_year.options[indexYear].value
      let indexMonth = from_month.selectedIndex
      let month = from_month.options[indexMonth].value
      let days = [0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
      if (year % 4 == 0 && year % 400 != 0) {
        days[2] = 29
      }
      if (year == 2019) {
        start = 10
      } else {
        start = 1
      }
      let date = new Date(new Date().getTime() - 24*60*60*1000 * 6)
      if (month == date.getMonth() + 1) {
        end = date.getDate()
      } else {
        end = days[month]
      }
      for (let i = start; i <= end; i++) {
        var op = document.createElement("option")
        op.text = i
        op.value = i
        from_day.add(op, null)
      }
    },
    onClickButton() {
      let element_country = document.getElementById("select-country")
      let element_year = document.getElementById("from-year")
      let element_month = document.getElementById("from-month")
      let element_day = document.getElementById("from-day")
      let country = element_country.options[element_country.selectedIndex].value
      let year = element_year.options[element_year.selectedIndex].value
      let month = element_month.options[element_month.selectedIndex].value
      let day = element_day.options[element_day.selectedIndex].value
      this.changeOption(country, `${year}-${month}-${day}`)
    },
    changeOption(country, date) {
      axios.get("http://10.26.144.47:8080/data/country",
      {
        params: {
          countryName: country,
          date: date
        }
      })
      .then(response => {
        let data = response.data
        let date = data.date
        let total_cases = data.total_cases
        let total_deaths = data.total_deaths
        let total_Vacs = data.total_Vacs
        this.option.xAxis.data = date
        this.option.series[0].data = total_deaths
        this.option.series[1].data = total_Vacs
        this.option.series[2].data = total_cases
        let chart = echarts.getInstanceByDom(document.getElementById('line-chart'))
        chart.setOption(this.option)
      })
      .catch(function (error) {
        console.log(error)
      })
    }
  },
  updated() {
    this.setMonth()
    this.setDay()
  },
}
</script>

<style scoped>
#line-chart-page {
    width: 1280px;
    height: 640px;
    margin: auto;
    position: absolute;
    left: 0;
    right: 0;
}
#line-chart {
  width: 100%;
  height: 640px;
}
#select-country {
  position: absolute;
  left: 300px;
  top: 100px;
}
</style>
