 let keepAliveTimer;
  let errTimer;
  import store from './store'

  export default {
  	ws: {}, //websocket对象
  	delay: 5000, //重连延迟，单位：毫秒
  	count: 0,//重连次数
  	limitCount: 9999,//重来最多次数
  	isAuth: false,
  	uid: store.state.user.wsId,
  	socketUrl: process.env.VUE_APP_SOCKET_API,
      //设置websocket对象方法
      setWs: function(newWs) {
      	this.ws = newWs
      },
      //设置延迟方法
      setDelay: function(newDelay) {
      	this.delay = newDelay
      },
      //发送websocket信息方法
      sendMsg: function(message) {
      	this.ws.send(JSON.stringify(message))
      },
      onMessage: function(message) {
        console.log(message, '111')
      },
      creatSocket(options) {
      	let self = this;
      	if ("WebSocket" in window) {
      		self.isAuth = false;
      		clearTimeout(errTimer);
      		console.log("您的浏览器支持 WebSocket!");
      		const url =  this.socketUrl;
      		if(url == ''){return;}
      		//实例化websocket
      		self.ws = new WebSocket(url);
      		//保存设置全局websocket对象
      		self.setWs(self.ws);
      		//监听websocket连接打开方法
      		self.ws.onopen = function(ev) {
      			console.log("打开websocket")
      			self.count = 0;

      			//调用keepalive方法（不一定都需要调用此方法，可注释）
      			self.keepAlive()
      		}
      		//监听websocket错误方法
      		self.ws.onerror = function(ev) {
      			console.log( "连接已出错...");
      			//延迟执行重连
      			if(self.count < self.limitCount) {
      				self.count += 1;
      				errTimer = setTimeout(() => {
      					self.creatSocket(options);
      				}, self.delay);
      			}

      		};
      		//监听websocket关闭方法
      		self.ws.onclose = function(ev) {
      			// 关闭 websocket
      			console.log( "连接已关闭...");
      			//延迟执行重连
      			if(self.count < self.limitCount) {
      				self.count += 1;
      				errTimer = setTimeout(() => {
      					self.creatSocket(options);
      				}, self.delay);
      			}
      		};

      		//监听websocket接收消息事件（接收来自服务器的实时消息）
      		self.ws.onmessage = function(res) {
      			// console.log("App.vue收到服务器内容", res.data);
      			if(options && options.onmessage) {
      				options.onmessage(res.data);
              this.onMessage(res.data)
      			}
      		};

      	} else {
      		// 浏览器不支持 WebSocket
      		console.log("您的浏览器不支持 WebSocket!");
      	}
      },
      keepAlive() {
      	let self = this;
      	clearTimeout(keepAliveTimer);
      	//判断当前webscokt状态
      	if (self.ws.readyState == 1) {
      			if(!self.isAuth) {
      				self.sendMsg({
      					"cmd": '1',
      					"clientId": this.uid
      				})
      				self.isAuth = true;
      			}else{
      				//调用发送方法
      				self.sendMsg({
      					"cmd": '2',
      					"clientId": this.uid
      				})
      			}

      		keepAliveTimer = setTimeout(() => {
      			self.keepAlive()
      		}, 10000);
      	}

      },
  }
