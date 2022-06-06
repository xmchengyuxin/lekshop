<template>
  <div>
    <div class="wrap-bottom-men wanl-chat-mini flex f-a-c f-j-c"  @click="handleIm()">
      <div class="water1"></div>
      <div class="water2"></div>
      <div class="water3"></div>
      <img src="./chat_mini.png">
      <span v-if="totalUnReadNum > 0" class="chat-num flex f-a-c f-j-c padding-lr2 f10-size t-color-w bg-color-r b-radius-30">{{totalUnReadNum}}</span>
    </div>

    <el-dialog v-el-drag-dialog title="" :visible.sync="imDialog" :modal="false" :close-on-click-modal="false" width="850px" height="640px">
      <lemon-imui
      ref='IMUI'
      :user="user"
      :hideMenu="true"
      :hideMessageName="true"
      :hideMessageTime="true"
      :contact-contextmenu="contactContextmenu"
      :contextmenu="contextmenu"
      @pull-messages="handlePullMessages"
      @send="handleSend"
      @change-contact="handleChangeContact">
          <template #sidebar-message-fixedtop>
            <span></span>
          </template>
      </lemon-imui>
    </el-dialog>

    <audio ref="chataudio"  src="./ding.mp3"></audio>
  </div>

</template>

<script>
  import elDragDialog from '@/directive/el-drag-dialog' // base on element-ui
  import EmojiData from "./database/emoji";
  import {getChatSessionList, deleteChatSession, getChatList, sendMsg, readBySession, countUnReadNum} from '@/api/chat'
  import {upload} from '@/api/system'
  import axios from 'axios'
  import Vue from 'vue';
  import LemonMessageGoods from './lemon-message-goods';
  import LemonMessageOrder from './lemon-message-order';
  Vue.component(LemonMessageGoods.name,LemonMessageGoods);
  Vue.component(LemonMessageOrder.name,LemonMessageOrder);

  let IMUI;
  export default {
    directives: { elDragDialog },
    components: {
    },
    data() {
      return {
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
              deleteChatSession({sessionId: contact.sessionId}).then((response) => {
              })
            },
          },
        ],
        contextmenu: [
          {
            click: (e, instance, hide) => {
              const { message } = instance;
              window.open(message.content);
              hide();
            },
            visible: instance => {
              return instance.message.type == "image";
            },
            text: "查看大图",
          },
        ],
        chatSessionList: [],
        chatList: [],
        user: {
          id: this.$store.state.user.wsId.replace("member-", ""),
          displayName: this.$store.state.user.wsNickname,
          avatar: this.$store.state.user.wsAvatar,
        },
        page: 1,
        totalUnReadNum: 0
      }
    },
    methods: {
      handleIm(memberId) {
        this.totalUnReadNum = 0
      	this.imDialog = true;
        this.$nextTick(()=>{
          this.getContect(memberId);
        })
      },
      getContect(memberId){
          if(!IMUI){
            IMUI= this.$refs.IMUI;
          }
          IMUI.initEditorTools([
            {
              name: "emoji",
            },
            {
              name: "uploadImage",
            },
          ]);
          //初始化表情包。
          IMUI.initEmoji(EmojiData);
          //从后端请求联系人数据，包装成下面的样子
          let contacts = []
          getChatSessionList({targetMemberId:memberId, page:1, pageSize:9999}).then(response => {
            this.chatSessionList = response.data.list
            if(this.chatSessionList && this.chatSessionList.length > 0){
              this.chatSessionList.forEach(item=>{
                if(!item.lastMsg){
                  item.lastMsg = '   ';
                }
                let lastMsg ='';
                if(item.msgType == 'text'){
                  lastMsg = item.lastMsg
                }else if(item.msgType == 'image'){
                  lastMsg = '[图片]'
                }else if(item.msgType == 'file'){
                  lastMsg = '[文件]'
                }else if(item.msgType == 'goods'){
                  lastMsg = '[咨询商品]';
                }else if(item.msgType == 'order'){
                  lastMsg = '[咨询订单]';
                }
                contacts.push({
                  sessionId: item.id,
                  id: item.targetId,
                  displayName: item.targetNickname,
                  avatar: item.targetHeadImg,
                  unread: item.unReadNum,
                  //最近一条消息的内容，如果值为空，不会出现在“聊天”列表里面。
                  //lastContentRender 函数会将 file 消息转换为 '[文件]', image 消息转换为 '[图片]'，对 text 会将文字里的表情标识替换为img标签,
                  lastContent: lastMsg,
                  //最近一条消息的发送时间
                  lastSendTime: item.updTime,
                })
              })

              IMUI.initContacts(contacts);
              if(contacts.length > 0){
                setTimeout(() => {
                  if(memberId){
                    IMUI.changeContact(memberId);
                  }else{
                    IMUI.changeContact(contacts[0].id);
                  }
                }, 500);
              }

            }
          })
      },
      //加载聊天记录
      handlePullMessages(contact, next, instance) {
        let messages = []
        let total = 0;
        getChatList({targetMemberId:contact.id, page:this.page, pageSize:50}).then(response => {
          this.chatList = response.data.list
          total = response.data.total
          if(this.page <= response.data.totalPage){
            this.page++;
          }
          if(this.chatList && this.chatList.length > 0){
            this.chatList.forEach(item=>{
              messages.push({
                id: item.id,
                status: 'succeed',
                type: item.msgType,
                sendTime: item.sendTime,
                content: item.msgContent,
                toContactId: item.sendType == 1 ? item.targetId : item.memberId,
                fromUser: {
                  id: item.sendType == 1 ? item.memberId : item.targetId,
                  displayName: item.sendType == 1 ? item.memberNickname : item.targetNickname,
                  avatar: item.sendType == 1 ? item.memberHeadImg : item.targetHeadImg
                }
              })
            })
          }
          setTimeout(() => {
            let isEnd = false;
            if((this.page-1) >= response.data.totalPage){
              isEnd = true;
            }
            next(messages, isEnd);
          }, 500);
        })
      },
      handleSend(message, next, file) {
        const self= this;
        let sendForm = {
          targetMemberId: message.toContactId,
          msgType: message.type,
          content: message.content
        }
        if(file){
          //上传到服务器
          self.addImg({file: file, success(res){
            sendForm.content = res;
            self.sendMsg(sendForm, next);
          }})
        }else{
          self.sendMsg(sendForm, next);
        }
      },
      sendMsg(sendForm, next){
        sendMsg(sendForm).then((response) => {
          setTimeout(() => {
            next();
          }, 500);
        })
      },
      handleChangeContact(contact, instance) {
        this.page = 1;
        instance.updateContact({
          id: contact.id,
          unread: 0,
        });
        if(contact.sessionId){
          readBySession({sessionId: contact.sessionId}).then((response) => {
          })
        }
      },
      watchSocket(){
        const self= this;
        self.socket.onMessage = function(res) {
          //将接收到的数据包装成下面的样子
          let info = JSON.parse(res);
           if(info.cmd == 5) {
             let data = JSON.parse(info.data);
             if(data.type && data.type == 5){
               let item = JSON.parse(data.content);
                const msg = {
                  id: item.id,
                  status: 'succeed',
                  type: item.msgType,
                  sendTime: item.sendTime,
                  content: item.msgContent,
                  toContactId: item.sendType == 2 ? item.targetId : item.memberId,
                  fromUser: {
                    id: item.sendType == 1 ? item.memberId : item.targetId,
                    displayName: item.sendType == 1 ? item.memberNickname : item.targetNickname,
                    avatar: item.sendType == 1 ? item.memberHeadImg : item.targetHeadImg
                  }
              };
              self.playAudio();
              let msgContent;
              if(msg.type == 'image'){
                msgContent = '[图片]';
              }else if(msg.type == 'file'){
                msgContent = '[文件]';
              }else if(msg.type == 'goods'){
                msgContent = '[咨询商品]';
              }else if(msg.type == 'order'){
                msgContent = '[咨询订单]';
              }else{
                msgContent = msg.content;
              }
              if(!IMUI || !self.imDialog){
                //未打开聊天窗口
                self.totalUnReadNum++;
                self.$notify({
                  title: '',
                  dangerouslyUseHTMLString: true,
                  // message: '<img style="width:50px;height:50px;border-radius:20%;" src="'+msg.fromUser.avatar+'"></img> :'+msg.content,
                  message: '<div class="flex">'
                            +'<img class="flex f-s-0 margin-r8 margin-t8" style="width:40px;height:40px;border-radius:50%;" '
                              +'size="small"'
                               +'src="'+msg.fromUser.avatar+'"></img>'
                            +'<div class="flex flex-1 f-c margin-t4">'
                              +'<span style="font-size: 13px;font-weight: bold;">'+msg.fromUser.displayName+'</span>'
                              +'<span style=" font-size: 13px;">'+msgContent+'</span>'
                            +'</div>'
                          +'</div>',
                  position: 'bottom-right',
                  duration: 5000,
                  offset: 100
                });
              }
              if(IMUI){
                IMUI.appendMessage(msg, true);
                IMUI.setLastContentRender(msg.type, msg => {
                  return msgContent;
                });
              }
             }
           }
        };
      },
      playAudio() {
      	const self = this;
      	let audio = this.$refs.chataudio;
      		let play = audio.play();
      		if (play !== undefined) {
      		  play.then(function(res) {
      		    // Automatic playback started!
      		  }).catch(function(error) {
      			   self.$alert('有新的聊天信息,请及时查看', '消息提醒', {
                  confirmButtonText: '确定',
                  callback: action => {
                   self.playAudio();
                  }
                });
      		  });
      		}
      },
      addImg(options) {
        const self = this;
        var formdata = new FormData();
        formdata.append("file", options.file);
        var postData = {  // 设置axios的参数
           url: process.env.VUE_APP_BASE_API+'system/upload',
           data: formdata,
           method: 'post',
           headers: {
            'Content-Type': 'multipart/form-data'
           }
        }
        axios(postData).then((res) => {
          if(options.success){
            options.success(res.data.data.imgUrl);
          }
        })
       },
       countUnReadNum(){
         countUnReadNum().then(response => {
           this.totalUnReadNum = response.data
         })
       }

    },
    created() {
      this.countUnReadNum();
      this.$nextTick(()=> {
        this.watchSocket();
      })
    },
    mounted(){
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
  .chat-num {
    position: absolute;
    right: 24px;
    top: 20px;
    height: 18px;
    min-width: 18px;
    background-color: #000;
    border-radius: 30px;
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
