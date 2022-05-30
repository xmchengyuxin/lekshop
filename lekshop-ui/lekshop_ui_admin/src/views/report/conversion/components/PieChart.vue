<template>
  <div :class="className" :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import { debounce } from '@/utils'

export default {
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '100%'
    },
    height: {
      type: String,
      default: '300px'
    },
    pieData: {
      type: Object,
      required: true
    }
  },
  watch: {
    pieData: {
      deep: true,
      handler(val) {
        this.initChart(val)
      }
    }
  },
  data() {
    return {
      chart: null
    }
  },
  mounted() {
    this.initChart(this.pieData)
    this.__resizeHandler = debounce(() => {
      if (this.chart) {
        this.chart.resize()
      }
    }, 100)
    window.addEventListener('resize', this.__resizeHandler)
  },
  beforeDestroy() {
    if (!this.chart) {
      return
    }
    window.removeEventListener('resize', this.__resizeHandler)
    this.chart.dispose()
    this.chart = null
  },
  methods: {
    initChart(pieData) {
      this.chart = echarts.init(this.$el, 'macarons')

      this.chart.setOption({
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
          left: 'center',
          bottom: '10',
          data: ['小程序次数', 'APP次数', 'H5次数']
        },
        calculable: true,
        series: [
          {
            name: '下单转化率',
            type: 'pie',
            roseType: 'radius',
            radius: [15, 95],
            center: ['50%', '48%'],
            data: [
              { value: pieData.xcxOpenNum, name: '小程序' },
              { value: pieData.appOpenNum, name: 'APP' },
              { value: pieData.linkOpenNum, name: 'H5' },
            ],
            animationEasing: 'cubicInOut',
            animationDuration: 2600
          }
        ]
      })
    }
  }
}
</script>
