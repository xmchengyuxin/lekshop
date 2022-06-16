<script>
export default {
  name: "lemonMessageNotice",
  inheritAttrs: false,
  inject: ["IMUI"],
  render() {
    return (
      <lemon-message-basic
        class="lemon-message-notice"
        props={{ ...this.$attrs }}
        scopedSlots={{
          content: props => {
            let data = JSON.parse(props.content)
            let msg = JSON.parse(data.content)
            return <div class="f-c f-a-s  bg-color-w">
                  <div class="flex f-a-c  w100 f11-size t-color-3 margin-t6 f-w-b">{data.title}</div>
                   <div class="flex margin-t6">
                   <img class="flex f-s-0 w-70 h-70 bg-img margin-r10" src={msg.goodsMainImg} />
                    <div class="flex flex-1 f-c f-j-s h100">
                    <div class="w-200">
                     <div class="line2 f12-size t-color-6">{msg.goodsName}</div>
                     </div>
                     <div class="flex f-a-c  w100 f12-size t-color-9 margin-t2">{data.type == 5 ? '退款单号:' : ''} {msg.refundNo}</div>
                    <div class="flex f-a-c w100 f12-size t-color-10 margin-t2">去处理>></div>
                    </div>
                   </div>
                  </div>;
          }
        }}
      />
    );
  }
};
</script>
<style scoped="scoped">
.lemon-message.lemon-message-notice {
  user-select: none;
}
  .lemon-message__content {
    border: 2px solid #000;
    font-size: 12px;
    cursor: pointer;
  }
   .lemon-message__content::before {
     display: none;
   }
</style>
