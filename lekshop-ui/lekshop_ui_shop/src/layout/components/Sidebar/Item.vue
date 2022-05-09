<script>
export default {
  name: 'MenuItem',
  functional: true,
  props: {
    icon: {
      type: String,
      default: ''
    },
    title: {
      type: String,
      default: ''
    },
    num: {
      type: Number,
      default: null
    }
  },
  render(h, context) {
    const { icon, title, num } = context.props
    const vnodes = []

    if (icon) {
      vnodes.push(<svg-icon icon-class={icon}/>)
    }

    if (title) {
      if(num && Number(num) > 0) {
        vnodes.push(<span slot='title' style="font-size:13px;">{(title)}<span style="color:#ff4949;"> ({(num)})</span></span>)
      }else{
        vnodes.push(<span slot='title' style="font-size:13px;">{(title)}</span>)
      }
    }


    return vnodes
  }
}
</script>
