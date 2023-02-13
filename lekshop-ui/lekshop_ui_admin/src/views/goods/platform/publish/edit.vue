<template>
  <goods-detail :is-edit="true" />
</template>

<script>
import GoodsDetail from './components/GoodsDetail'

export default {
  name: 'editGoods',
  components: { GoodsDetail }
}
</script>
