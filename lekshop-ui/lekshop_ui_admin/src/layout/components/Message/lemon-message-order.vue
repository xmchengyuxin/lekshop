<script>
export default {
  name: "lemonMessageOrder",
  inheritAttrs: false,
  inject: ["IMUI"],
  render() {
    return (
      <lemon-message-basic
        class="lemon-message-order"
        props={{ ...this.$attrs }}
        scopedSlots={{
          content: props => {
            let msg = JSON.parse(props.content)
            return <div class="f-c f-a-s  bg-color-w">
                  <div class="flex f-a-c  w100 f11-size t-color-3 margin-t6 f-w-b">您正在咨询的订单</div>
                   <div class="flex margin-t6">
                   <img class="flex f-s-0 w-70 h-70 bg-img margin-r10" src={msg.mainImg} />
                    <div class="flex flex-1 f-c f-j-s h100">
                    <div class="w-200">
                     <div class="line2 f12-size t-color-6">{msg.title}</div>
                     </div>
                     <div class="flex f-a-c margin-t6 f12-size t-color-9">
                      <div class="margin-r4">合计</div>
                      <div class="text-price">{msg.price}.00</div>
                     </div>
                     <div class="flex f-a-c  w100 f12-size t-color-9 margin-t2">订单号: {msg.orderNo}</div>
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
.lemon-message.lemon-message-order {
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
