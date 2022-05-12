<template>
  <freight-detail :is-edit="true" />
</template>

<script>
import FreightDetail from './components/FreightDetail'

export default {
  name: 'EditForm',
  components: { FreightDetail }
}
</script>

