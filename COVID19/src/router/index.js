import Vue from 'vue'
import Router from 'vue-router'
import Table from '@/components/Table'
import LineChart from '@/components/LineChart'
import PieChart from '@/components/PieChart'
import WorldMap from '@/components/WorldMap'
import DynamicTrend from '@/components/DynamicTrend'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/lineChart'
    },
    {
      path: '/table',
      name: 'table',
      component: Table
    },
    {
      path: '/lineChart',
      name: 'LineChart',
      component: LineChart
    },
    {
      path: '/worldMap',
      name: 'WorldMap',
      component: WorldMap
    },
    {
      path: '/pieChart',
      name: 'PieChart',
      component: PieChart
    },
    {
      path: '/dynamicTrend',
      name: 'DynamicTrend',
      component: DynamicTrend
    }
  ]
})
