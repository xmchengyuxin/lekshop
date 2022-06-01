<script>
export default {
  name: "lemonMessageGoods",
  inheritAttrs: false,
  inject: ["IMUI"],
  render() {
    return (
      <lemon-message-basic
        class="lemon-message-goods"
        props={{ ...this.$attrs }}
        scopedSlots={{
          content: props => {
            let msg = JSON.parse(props.content)
            return <div class=" flex f-a-s bg-color-w">
                    <img class="flex f-s-0 w-70 h-70 bg-img margin-r10" src={msg.mainImg} />
                     <div class="flex flex-1 f-c f-j-s h100">
                     <div class="w-200">
                      <div class="line2 f12-size t-color-6">{msg.title}</div>
                      </div>
                      <div class="text-price f12-size t-color-9 margin-t6">{msg.price}.00</div>
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
.lemon-message.lemon-message-goods {
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
