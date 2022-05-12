<template>
  <freight-detail :is-edit="false" />
</template>

<script>
import FreightDetail from './components/FreightDetail'

export default {
  name: 'AddForm',
  components: { FreightDetail }
}
</script>

