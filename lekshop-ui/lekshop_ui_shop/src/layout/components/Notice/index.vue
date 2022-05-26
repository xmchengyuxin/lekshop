<template>
	<div >
		<div @click="showList = !showList;readAll()" class="wrap-notice-icon cursor">
			<span v-if="msgCount > 0" class="count-num f10-size t-color-w b-radius flex f-a-c f-j-c">{{msgCount}}</span>
			<!-- <img class="w-80" src="./notice_icon.png" alt=""> -->
      <i class="el-icon-message-solid hover-effect " id="message-icon" style="line-height: 50px;" ></i>
		</div>
		<div v-if="showList" class="wrap-notice-content animate fadeInDown" :style="{'bottom': show ? '150px' : '20px'}" >
			<div @click="closeNotices" class="flex f-a-c f-j-e padding-tb6">
				<span class="el-icon-close f18-size t-color-9 cursor"></span>
				<div class="w-12"></div>
			</div>
			<div class="wrap-notice-scroll" style="height: 480px;overflow-y: scroll;color: #000000;">
        <div style="font-size: 15px; font-weight: 600;border-bottom: 1px solid #ece5e5;">今天</div>
        <div  v-if="todayList.length > 0" @click="clickItem(item,index)" v-for="(item,index) in todayList" :key="index" class="flex padding-tb15 b-bottom cursor" style="line-height: 20px; color: #000000;">
          <div class="flex f-a-s f-s-0">
            <img style="width: 24px;" class="margin-r8" src="./notice_chat_icon.png" alt="">
          </div>
          <div class="flex f-c">
            <div class="flex f-a-c">
              <p class="f16-size f-w-b t-color-3 margin-r6">{{titleType[item.type]}}</p>
              <span v-if="item.status != 1" class="bg-color-r b-radius" style="width: 6px;height: 6px;"></span>
            </div>
            <p class="f13-size margin-t8">{{item.content}}</p>
            <p class="f10-size t-color-9 margin-t8">{{item.addTime | parseTime}}</p>
          </div>
          <div class="flex f-s-0 w-20"></div>
        </div>
        <div v-if="todayList.length <= 0" class="t-color-9 flex f-j-c padding-15 margin-t30">暂无消息</div>

        <div style="font-size: 15px; font-weight: 600;border-bottom: 1px solid #ece5e5;">更早之前</div>
        <div  v-if="earlierList.length > 0" @click="clickItem(item,index)" v-for="(item,index) in earlierList" :key="index" class="flex padding-tb15 b-bottom cursor" style="line-height: 20px; color: #000000;">
          <div class="flex f-a-s f-s-0">
            <img style="width: 24px;" class="margin-r8" src="./notice_chat_icon.png" alt="">
          </div>
          <div class="flex f-c">
            <div class="flex f-a-c">
              <p class="f16-size f-w-b t-color-3 margin-r6">{{titleType[item.type]}}</p>
              <span v-if="item.status != 1" class="bg-color-r b-radius" style="width: 6px;height: 6px;"></span>
            </div>
            <p class="f13-size margin-t8">{{item.content}}</p>
            <p class="f10-size t-color-9 margin-t8">{{item.addTime | parseTime}}</p>
          </div>
          <div class="flex f-s-0 w-20"></div>
        </div>
        <div v-if="earlierList.length <= 0" class="t-color-9 flex f-j-c padding-15 margin-t30">暂无消息</div>

        <!-- <div  v-if="list.length > 0" @click="clickItem(item,index)" v-for="(item,index) in list" :key="index" class="flex padding-tb15 b-bottom cursor" style="line-height: 20px; color: #000000;">
          <div class="flex f-s-0 w-20" style="font-size: 15px; font-weight: 600;border-bottom: 1px solid #ece5e5;" v-if="item.addTime < today && index == 0">更早之前</div>
          <div class="flex f-a-s f-s-0">
            <img style="width: 24px;" class="margin-r8" src="./notice_chat_icon.png" alt="">
          </div>
          <div class="flex f-c">
            <div class="flex f-a-c">
              <p class="f16-size f-w-b t-color-3 margin-r6">{{titleType[item.type]}}</p>
              <span v-if="item.status != 1" class="bg-color-r b-radius" style="width: 6px;height: 6px;"></span>
            </div>
            <p class="f13-size margin-t8">{{item.content}}</p>
            <p class="f10-size t-color-9 margin-t8">{{item.addTime | parseTime}}</p>
          </div>
          <div class="flex f-s-0 w-20"></div>
        </div>
        <div v-if="list.length <= 0" class="t-color-9 flex f-j-c padding-15 margin-t30">暂无消息</div> -->
			</div>
		</div>
		<div v-if="list.length > 0 && show" class="wrap-notice-content animate fadeInRight cursor" style="bottom: 20px;line-height: 20px; color: #000000;">
			<div @click="show=!show" class="wrap-close-notice">
				<span class="el-icon-close f18-size t-color-9 cursor"></span>
			</div>
			<div  @click="clickItem(list[0],0)" class="flex padding-tb15 ">
				<div class="flex f-a-s f-s-0">
					<img  class="w-30 margin-r12" src="./notice_chat_icon.png" alt="">
				</div>
				<div class="flex f-c f-j-c">
					<p class="f16-size f-w-b t-color-3">{{titleType[list[0].type]}}</p>
					<p class="f13-size margin-t8">{{list[0].content}}</p>
					<p class="f10-size t-color-9 margin-t8">{{list[0].addTime | parseTime}}</p>
				</div>
				<div class="flex f-s-0 w-20"></div>
			</div>
		</div>
		<audio ref="chataudio"  src="./ding.mp3"></audio>
	</div>
</template>
<style scoped="scoped">
	.wrap-notice-icon {
		/* position: fixed;
		right: 0px;
		top: 50%; */
		z-index: 11;
	}
	.wrap-notice-content {
		position: fixed;
		right: 0px;
		bottom: 150px;
		width: 281px;
		background: #FFFFFF;
		box-shadow: 0px 0px 13px 0px rgba(208, 208, 208, 0.5);
		border-radius: 10px 0px 0px 10px;
		padding: 0 0 0 14px;
		z-index: 15;
	}
	.wrap-notice-scroll::-webkit-scrollbar { width: 0 !important }
	.wrap-notice-scroll { -ms-overflow-style: none; }
	.wrap-notice-scroll { overflow: -moz-scrollbars-none; }
	.wrap-close-notice {
		position: absolute;
		right: 0;
		top: 6px;
		padding: 0 10px 0 20px;
	}
	.count-num {
		position: absolute;
		top: 10px;
		/* left: 45px; */
    right: 90px;
		padding: 0px 2px;
		height: 16px;
		min-width: 16px;
		background-color: #18bc9c;
	}
</style>
<script>
  import {getRemindList, count, readAll} from '@/api/remind'
  import { parseTime, renderTime, moneyFormat} from '@/utils'

  let keepAliveTimer;
  let errTimer;
	export default {
		data() {
			return {
				titleType: ['','提现通知','新订单通知','申请售后通知','买家退货通知'],
				list: [],
				showList: false,
				show: false,
				msgCount: 0,
        ws: {}, //websocket对象
        delay: 5000, //重连延迟，单位：毫秒
        count: 0,//重连次数
        limitCount: 9999,//重来最多次数
        isAuth: false,
        uid: this.$store.state.user.wsId,
        socketUrl: process.env.VUE_APP_SOCKET_API,
        today: new Date(new Date().toDateString()),
        todayList: [],
        earlierList:[],
			};
		},
		methods: {
			closeNotices() {
				const self = this;
				this.showList = false;
				if(this.list.length > 0) {
					this.list.forEach((ele,index) => {
						ele.status = 1;
						self.$set(self.list,index,ele);
					})
				}
			},
			clickItem(info,index) {
				if(info.type == 1) {
					this.$router.push('/fund/withdraw')
				}
				if(info.type == 2) {
					this.$router.push('/order/list')
				}
				if(info.type == 3 || info.type == 4 ) {
					this.$router.push('/order/refund')
				}
				info.status = 1;
				this.$set(this.list,index,info);
				this.show = false;
			},
			playAudio() {
				const self = this;
				let audio = this.$refs.chataudio;
					let play = audio.play();
					if (play !== undefined) {
					  play.then(function(res) {
					    // Automatic playback started!
					  }).catch(function(error) {
						   self.$alert('您有一条新的消息', '消息提醒', {
                  confirmButtonText: '确定',
                  callback: action => {
                   self.playAudio();
                  }
                });
					  });
					}
			},
			getList() {
				const self = this;
        getRemindList({page: 1, pageSize: 50}).then(res => {
          self.list = res.data.list ? res.data.list : [];
          if(self.list && self.list.length > 0){
            self.list.forEach((ele,index) => {
              if(ele.addTime < this.today){
                this.earlierList.push(ele);
              }else{
                this.todayList.push(ele);
              }
            })
          }
        })
			},
			readAll() {
				const self = this;
        readAll().then((res) => {
        	self.msgCount = 0;
        })
			},
			getCount() {
				const self = this;
        count().then(res => {
         self.msgCount = res.data ? res.data.num : 0;
        })
			},
			init() {
				const self= this;
				this.getCount();
				this.getList();
         self.socket.onMessage = function(res) {
           // console.log(res,'onmessage');
           let info = JSON.parse(res);
            if(info.cmd == 5) {
              let data = JSON.parse(info.data);
              if(data.type && data.type != 5){
                self.msgCount += 1;
                self.playAudio();
                self.list.unshift(data);
                self.todayList.unshift(data);
                self.show = true;
              }
            }
         };
			},
		},
		created() {
			this.init();
		},
		/* watch: {
			$route: {
				handler: function(val, oldVal) {
					if(val.path == '/index/index') {
						this.getCount();
						this.getList();
					}
				},
				// 深度观察监听
				deep: true
			}
		}, */
		mounted() {},
		destroyed() {},
		components: {}
	}
</script>
