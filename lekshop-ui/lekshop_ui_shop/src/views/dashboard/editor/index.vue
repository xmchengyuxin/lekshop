<template>
  <div class="dashboard-editor-container">
    <div class=" clearfix">
      <pan-thumb :image="userData.headImg" style="float: left">
      </pan-thumb>
      <github-corner style="position: absolute; top: 0px; border: 0; right: 0;" />
      <div class="info-container">
        <span class="display_name">{{ userData.realname }}</span>
      </div>
    </div>
    <div>
      <!-- <img :src="emptyGif" class="emptyGif"> -->
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import PanThumb from '@/components/PanThumb'
import GithubCorner from '@/components/GithubCorner'

export default {
  name: 'DashboardEditor',
  components: { PanThumb, GithubCorner },
  data() {
    return {
      emptyGif: 'https://qiniuimg.wurun.net/1662277162361.jpg'
    }
  },
  computed: {
    ...mapGetters([
      'name',
      'avatar',
      'roles',
      'userData'
    ])
  }
}
</script>

<style lang="scss" scoped>
  .emptyGif {
    display: block;
    width: 100%;
    margin: 0 auto;
  }

  .dashboard-editor-container {
    background:url(empty.jpg) center no-repeat;
    background-size: cover;
    overflow: hidden;
    background-color: #e3e3e3;
    min-height: 100vh;
    padding: 50px 60px 0px;
    .pan-info-roles {
      font-size: 12px;
      font-weight: 700;
      color: #333;
      display: block;
    }
    .info-container {
      position: relative;
      margin-left: 190px;
      height: 150px;
      line-height: 200px;
      .display_name {
        font-size: 48px;
        line-height: 48px;
        color: #212121;
        position: absolute;
        top: 25px;
      }
    }
  }
</style>
