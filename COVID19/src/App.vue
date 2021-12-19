<template>
  <div id = "app">
    <h1 style="position: relative; text-align: center; font-size: 40px;">COVID19 DashBoard</h1>
    <br/>
    <tbody style = "position: relative; left: 90px;">
      <tr>
        <td><router-link to = "/table" @click.native = "routerRefresh"><h1>&emsp;Table&emsp;</h1></router-link></td>
        <td><router-link to = "/lineChart" @click.native = "routerRefresh"><h1>&emsp;Line Chart&emsp;</h1></router-link></td>
        <td><router-link to = "/worldMap" @click.native = "routerRefresh"><h1>&emsp;World Map&emsp;</h1></router-link></td>
        <td><router-link to = "/pieChart" @click.native = "routerRefresh"><h1>&emsp;Pie Chart&emsp;</h1></router-link></td>
        <td><router-link to = "/dynamicTrend" @click.native = "routerRefresh"><h1>&emsp;Dynamic Trend&emsp;</h1></router-link></td>
      </tr>
    </tbody>
    <router-view v-if = "routerAlive"></router-view>
  </div>
</template>

<script>
export default {
  name: 'App',
  data() {
    return {
      isLineChartActive: false,
      isWorldMapActive: false,
      routerAlive: true
    }
  },
  /***/
  created() {
    this.isLineChartActive = false,
    this.isWorldMapActive = false
  },
  mounted() {
    this.init
  },
  provide() {

  },
  methods: {
    init() {
      this.isLineChartActive = false
      this.isWorldMapActive = true
    },
    onClickLink(item) {
      if (item == "LineChart") {
        this.isLineChartActive = true
      } else {
        this.isLineChartActive = false
      }
      if (item == "WorldMap") {
        this.isWorldMapActive = true
      } else {
        this.isWorldMapActive = false
      }
    },
    routerRefresh() {
      this.routerAlive = false,
      this.$nextTick(() => {
        this.routerAlive = true
      })
    }
  }

}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  width: 1280px;
  height: 720px;
  margin: auto;
  position: absolute;
  left: 0;
  right: 0;
}
</style>
