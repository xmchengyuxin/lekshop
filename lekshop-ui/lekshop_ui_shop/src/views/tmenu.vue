<template>
  <div>
    <keep-alive :include="cachedViews" :max="2">
      <router-view />
    </keep-alive>
  </div>
</template>

<script>
export default {
  name: 'AlllocationManage',
  computed:{
    cachedViews(){
      return this.$store.state.tagsView.cachedViews
    }
  }
}
</script>