<template>
  <div>
    <div class="wrap-bottom-men wanl-chat-mini flex f-a-c f-j-c"  @click="handleIm">
      <div class="water1"></div>
      <div class="water2"></div>
      <div class="water3"></div>
      <img src="./chat_mini.png">
    </div>

    <el-dialog v-el-drag-dialog title="" :visible.sync="imDialog" :modal="false" :close-on-click-modal="false" width="850px" height="640px">
      <lemon-imui
      ref='IMUI'
      :user='this.user'
      :hideMenu="true"
      :hideMessageName="true"
      :hideMessageTime="true"
      :contact-contextmenu="contactContextmenu">
          <template #sidebar-message-fixedtop>
            <span></span>
          </template>
      </lemon-imui>
    </el-dialog>
  </div>


</template>

<script>
  import elDragDialog from '@/directive/el-drag-dialog' // base on element-ui
  import EmojiData from "./database/emoji";

  export default {
    directives: { elDragDialog },
    components: {
    },
    data() {
      return {
        user:{id:1,displayName:'June',avatar:''},
        imDialog: false,
        contactContextmenu: [
          {
            text: "删除聊天",
            click: (e, instance, hide) => {
              const { IMUI, contact } = instance;
              IMUI.updateContact({
                id: contact.id,
                lastContent: null,
              });
              if (IMUI.currentContactId == contact.id) IMUI.changeContact(null);
              hide();
            },
          },
        ],
      }
    },
    methods: {
      handleIm() {
      	this.imDialog = true;
        this.$nextTick(()=>{
          this.getContect();
        })
      },
      getContect(){
        const IMUI= this.$refs.IMUI;
          //初始化表情包。
          IMUI.initEmoji(EmojiData);
          //从后端请求联系人数据，包装成下面的样子
          const contacts = [{
            id: 2,
            displayName: '丽安娜',
            avatar:'',
            index: 'L',
            unread: 9,
            //最近一条消息的内容，如果值为空，不会出现在“聊天”列表里面。
            //lastContentRender 函数会将 file 消息转换为 '[文件]', image 消息转换为 '[图片]'，对 text 会将文字里的表情标识替换为img标签,
            lastContent: IMUI.lastContentRender({type:'text',content:'你在干嘛呢？'}),
            //最近一条消息的发送时间
            lastSendTime: 1566047865417,
          }];
          IMUI.initContacts(contacts);
      },
    },
    created() {

    }
  }
</script>

<style lang="scss" scoped>
  ::v-deep .el-dialog__header  {
    padding: 25px;
  }
  ::v-deep .el-dialog__headerbtn {
    top: 14px;
    right: 15px;
  }
  ::v-deep .el-dialog {
    background:#1d232a;
    box-shadow: unset;
  }
  ::v-deep .el-dialog__body {
    color:#000;
    padding: 0;
  }
  ::v-deep .lemon-wrapper *{
    box-sizing: content-box;
  }
  .wrap-bottom-men {
    position: fixed;
    bottom: 0;
    right: 0;
    width: 120px;
    height: 120px;
    z-index: 9999;

    img {
      position: relative;
      width: 55px;
      height: 55px;
      z-index: 11;
      border-radius: 50%;
    }
  }

  .wanl-chat-mini .water1,
  .wanl-chat-mini .water2,
  .wanl-chat-mini .water3 {
    padding: 20%;
    position: absolute;
    left: 35px;
    top: 36px;
    box-shadow: 0 0 100px 10px #ff1010 inset;
    border-radius: 100%;
    z-index: 1;
    opacity: 0;
  }

  .wanl-chat-mini .water1 {
    animation: wateranimate 4s 3s ease-out infinite;
  }

  .wanl-chat-mini .water2 {
    animation: wateranimate 4s 2s ease-out infinite;
  }

  .wanl-chat-mini .water3 {
    animation: wateranimate 4s 1s ease-out infinite;
  }



  @-webkit-keyframes wateranimate {
    0% {
      -webkit-transform: scale(0);
      opacity: 0.8;
    }

    100% {
      -webkit-transform: scale(2);
      opacity: 0;
    }
  }

  @keyframes wateranimate {
    0% {
      -webkit-transform: scale(0);
      transform: scale(0);
      opacity: 0.8;
    }

    100% {
      -webkit-transform: scale(2);
      transform: scale(2);
      opacity: 0;
    }
  }
</style>
