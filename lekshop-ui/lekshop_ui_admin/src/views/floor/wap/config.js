/**
 * drawer:true   广告右侧打开抽屉中显示
 * drawerPromotions:true   广告右侧打开抽屉中显示
 * ad_drawer:true 活动魔方右侧显示
 * notAdd: true 没有添加按钮
 * notLink: true 没有链接功能
 * notImg: true 没有选择图片功能
 * close:true 右侧关闭按钮
 */
export const modelData = [
  {
    type: "carousel",
    name: "图片轮播",
    img: "md-image",
    notTitle: false,
    close: true,
    options: {
      list: [
        {
          img: "https://qiniu.chengyuwb.com/1654590690132.jpg",
          url: "",
          link: "",
          size: "750*350"
        },
        {
          img: "https://qiniu.chengyuwb.com/1655346787375.jpg",
          url: "",
          link: "",
          size: "750*350"
        },
      ]
    }
  },
  {
    type: "leftOneRightTwo",
    name: "左一右二",
    notAdd: true,
    drawer: true,
    view: "tpl_ad_list",
    img: "md-image",
    options: {
      list: [
        {
          img: "https://qiniu.chengyuwb.com/1655360218844.webp",
          url: "",
          link: "",
          size: "335*335"
        },
        {
          img: "https://qiniu.chengyuwb.com/1655360218534.webp",
          url: "",
          link: "",
          size: "335*177"
        },
        {
          img: "https://qiniu.chengyuwb.com/1655360218500.webp",
          url: "",
          link: "",
          size: "335*177"
        }
      ]
    }
  },
  {
    type: "title",
    name: "标题栏",
    img: "md-image",
    // notTitle:true,
    notAdd: true,
    notImg: true,
    options: {
      list: [
        {
          title: "标题",
          color: "#fff",
          title1: '领取',
          color1: "#fff",
          bk_color: "#FF0000",
          textAlign: "center",
          link: "",
          url: ''
        }
      ]
    }
  },
  {
    type: "leftTwoRightOne",
    name: "左二右一",
    notAdd: true,
    drawer: true,

    img: "md-image",
    options: {
      list: [
        {
          img: "https://qiniu.chengyuwb.com/1655360218534.webp",
          url: "",
          link: "",
          size: "335*177"
        },
        {
          img: "https://qiniu.chengyuwb.com/1655360218500.webp",
          url: "",
          link: "",
          size: "335*177"
        },
        {
          img: "https://qiniu.chengyuwb.com/1655360218844.webp",
          url: "",
          link: "",
          size: "335*335"
        }
      ]
    }
  },
  {
    type: "flexThree",
    name: "三列单行",
    drawer: true,
    notAdd: true,

    img: "md-image",
    options: {
      list: [
        {
          img: "https://qiniu.chengyuwb.com/1655360218511.webp",
          url: "",
          link: "",
          size: "226*226 (1:1)"
        },
        {
          img: "https://qiniu.chengyuwb.com/1655360218526.webp",
          url: "",
          link: "",
          size: "226*226 (1:1)"
        },
        {
          img: "https://qiniu.chengyuwb.com/1655360218489.webp",
          url: "",
          link: "",
          size: "226*226 (1:1)"
        }
      ]
    }
  },
  {
    type: "flexOne",
    name: "一张大图",
    drawer: true,
    notAdd: true,

    img: "md-image",
    options: {
      list: [
        {
          img: "https://qiniu.chengyuwb.com/1655360218895.webp",
          url: "",
          link: "",
          size: "750*280"
        }
      ]
    }
  },
  {
    type: "flexTwo",
    name: "两张横图",
    drawer: true,
    notAdd: true,

    img: "md-image",
    options: {
      list: [
        {
          img: "https://qiniu.chengyuwb.com/1655361963167.webp",
          url: "",
          link: "",
          size: "335*220"
        },
        {
          img: "https://qiniu.chengyuwb.com/1655361963249.webp",
          url: "",
          link: "",
          size: "335*220"
        }
      ]
    }
  },
  {
    type: "topOneBottomTwo",
    name: "上一下二",
    drawer: true,
    notAdd: true,

    img: "md-image",
    options: {
      list: [
        {
          img: "https://qiniu.chengyuwb.com/1655362247094.webp",
          url: "",
          link: "",
          size: "710*170"
        },
        {
          img: "https://qiniu.chengyuwb.com/1655362247077.webp",
          url: "",
          link: "",
          size: "335*170"
        },
        {
          img: "https://qiniu.chengyuwb.com/1655362560973.webp",
          url: "",
          link: "",
          size: "335*170"
        }
      ]
    }
  },
  {
    type: "topTwoBottomOne",
    name: "上二下一",
    drawer: true,
    notAdd: true,

    img: "md-image",
    options: {
      list: [
        {
          img: "https://qiniu.chengyuwb.com/1655362247077.webp",
          url: "",
          link: "",

          size: "335*170"
        },
        {
          img: "https://qiniu.chengyuwb.com/1655362560973.webp",
          url: "",
          link: "",
          size: "335*170"
        },
        {
          img: "https://qiniu.chengyuwb.com/1655362247094.webp",
          url: "",
          link: "",
          size: "710*170"
        }
      ]
    }
  },
  {
    type: "flexFive",
    name: "五列单行",
    drawer: true,
    notAdd: true,

    img: "md-image",
    options: {
      list: [
        {
          img: "https://qiniu.chengyuwb.com/1655362790013.webp",
          url: "",
          link: "",
          size: "75*75（1:1）"
        },
        {
          img: "https://qiniu.chengyuwb.com/1655362776220.webp",
          url: "",
          link: "",
          size: "75*75（1:1）"
        },
        {
          img: "https://qiniu.chengyuwb.com/1655362776245.webp",
          url: "",
          link: "",
          size: "75*75（1:1）"
        },
        {
          img: "https://qiniu.chengyuwb.com/1655362790013.webp",
          url: "",
          link: "",
          size: "75*75（1:1）"
        },
        {
          img: "https://qiniu.chengyuwb.com/1655362776245.webp",
          url: "",
          link: "",
          size: "75*75（1:1）"
        }
      ]
    }
  },
  {
    type: "flexFour",
    name: "四列单行",
    drawer: true,
    notAdd: true,

    img: "md-image",
    options: {
      list: [
        {
          img: "https://qiniu.chengyuwb.com/1655362790013.webp",
          url: "",
          link: "",
          size: "88*88（1:1）"
        },
        {
          img: "https://qiniu.chengyuwb.com/1655362776220.webp",
          url: "",
          link: "",
          size: "88*88（1:1）"
        },
        {
          img: "https://qiniu.chengyuwb.com/1655362776245.webp",
          url: "",
          link: "",
          size: "88*88（1:1）"
        },
        {
          img: "https://qiniu.chengyuwb.com/1655362790013.webp",
          url: "",
          link: "",
          size: "88*88（1:1）"
        }
      ]
    }
  },
  {
    type: "textPicture",
    name: "文字图片",
    drawer: true,
    notAdd: true,

    img: "md-image",
    options: {
      list: [
        {
          img: "https://qiniu.chengyuwb.com/1655362790013.webp",
          url: "",
          link: "",
          title: "新品榜",
          size: "88*88（1:1）"
        },
        {
          img: "https://qiniu.chengyuwb.com/1655362776245.webp",
          url: "",
          link: "",
          title: "护肤榜",
          size: "88*88（1:1）"
        },
        {
          img: "https://qiniu.chengyuwb.com/1655362776220.webp",
          url: "",
          link: "",
          title: "底妆榜",
          size: "88*88（1:1）"
        },
        {
          img: "https://qiniu.chengyuwb.com/1655362790013.webp",
          url: "",
          link: "",
          title: "数码榜",
          size: "88*88（1:1）"
        }
      ]
    }
  },

  {
    type: "tpl_ad_list",
    name: "广告魔方",
    img: "md-image",
    options: {
      list: [
        {
          bg_img: "",
          name: "",
          url: ""
        }
      ]
    }
  },
  {
    type: "menu",
    name: "宫格导航",
    img: "md-image",
    close: true,
    options: {
      list: [
        {
          img: "https://i.loli.net/2020/12/05/SoGAv7gYybuWzED.png",
          url: "",
          link: "",
          title: "标题",
          size: "88*88 (1:1)"
        },
        {
          img: "https://i.loli.net/2020/12/05/JXR5K3sbIeENjqH.png",
          url: "",
          link: "",
          title: "标题",
          size: "88*88 (1:1)"
        },
        {
          img: "https://i.loli.net/2020/12/05/KlZfnCFIdEV231Y.png",
          url: "",
          link: "",
          title: "标题",
          size: "88*88 (1:1)"
        },
        {
          img: "https://i.loli.net/2020/12/05/GfmMyN2wrUVIlci.png",
          url: "",
          link: "",
          title: "标题",
          size: "88*88 (1:1)"
        },
        {
          img: "https://i.loli.net/2020/12/05/eznDa3iNby5FkYL.png",
          url: "",
          link: "",
          title: "标题",
          size: "88*88 (1:1)"
        }
      ]
    }
  },
  {
    type: "search",
    name: "搜索",
    img: "md-image",
    notAdd: true,
    notLink: true,
    notImg: true,
    options: {
      list: [
        {
          title: "搜索"
        }
      ]
    }
  },
  {
    type: "goods",
    name: "商品分类",
    img: "md-image",
    notAdd: true,
    notLink: true,
    notImg: true,
    options: {
      list: [
        {
          /**
           * 2021/12/9
           * 新增索引（‘index’）判断商品归属分类
           * 之前代码没有配置index也不会收到印象
           * 新建的楼层将采用索引判断分类
           */
          titleWay: [
            {
              title: "精选",
              desc: "电子推荐",
              ___index: 0,
            },
            {
              title: "实惠",
              desc: "便宜好货",
              ___index: 1,
            },
            {
              title: "进口",
              desc: "国际自营",
              ___index: 2,
            },
            {
              title: "推荐",
              desc: "喂奶推荐",
              ___index: 3,
            }
          ],
          listWay: [
            {
              mainImg: "https://i.loli.net/2021/05/14/KTLSrOVJmEdX12A.png",
              price: "120",
              title:
                " 微软 (Microsoft) Xbox 无线控制器/手柄 湛蓝色 | 3.5mm耳机接口蓝牙连接 Xbox主机电脑平板通用",
              type: "精选",
              ___index: 0
            },
            {
              mainImg: "https://i.loli.net/2020/12/05/c9mptI5Pg8qJ6ny.png",
              title:
                "宏碁(acer) DP高清线1.2版 2K*4KDisplayPort公对公接线笔记本电脑显卡连接显示器视频线1.5米",
              price: "190",
              type: "精选",
              ___index: 0
            },

          ]
        }
      ]
    }
  },
  {
    type: "notice",
    name: "公告",
    img: "md-image",
    notAdd: false,
    notLink: true,
    notImg: true,
    options: {
      list: [
        {
          title: [{context: "这是一条公告"}],
          color: "#666",
          bk_color: "#FFF",
          img: 'http://files.sxcfx.cn/upload/20220318/71e5d27aca82aeedf647052e1e4ceaaa.png'
        }
      ]
    }
  },
  {
    type: "group",
    name: "拼团活动",
    img: "md-image",
    notAdd: true,
    notLink: true,
    notImg: true,
    close: true,
    options: {
      list: [
        {
          titleWay: [
            {
              title: "热门拼团",
              desc: "拼着买，更便宜~",
              ___index: 0,
            },
          ],
          listWay: [
            {
              mainImg: "https://i.loli.net/2021/05/14/KTLSrOVJmEdX12A.png",
              price: "120",
              title:
                " 微软 (Microsoft) Xbox 无线控制器/手柄 湛蓝色 | 3.5mm耳机接口蓝牙连接 Xbox主机电脑平板通用",
                 ___index: 0,
            },
            {
              mainImg: "https://i.loli.net/2020/12/05/c9mptI5Pg8qJ6ny.png",
              title:
                "宏碁(acer) DP高清线1.2版 2K*4KDisplayPort公对公接线笔记本电脑显卡连接显示器视频线1.5米",
              price: "190",
               ___index: 0,
            },
            {
              mainImg: "https://i.loli.net/2020/12/05/c9mptI5Pg8qJ6ny.png",
              title:
                "宏碁(acer) DP高清线1.2版 2K*4KDisplayPort公对公接线笔记本电脑显卡连接显示器视频线1.5米",
              price: "190",
               ___index: 0,
            },

          ]
        }
      ]
    }
  },
  {
    type: "seckill",
    name: "秒杀活动",
    img: "md-image",
    notAdd: true,
    notLink: true,
    notImg: true,
    close: true,
    options: {
      list: [
        {
          titleWay: [
            {
              title: "限时抢购",
              desc: "8点-12点",
              ___index: 0,
            },
          ],
          listWay: [
            {
              mainImg: "https://i.loli.net/2021/05/14/KTLSrOVJmEdX12A.png",
              price: "120",
              title:
                " 微软 (Microsoft) Xbox 无线控制器/手柄 湛蓝色 | 3.5mm耳机接口蓝牙连接 Xbox主机电脑平板通用",
                 ___index: 0,
            },
            {
              mainImg: "https://i.loli.net/2020/12/05/c9mptI5Pg8qJ6ny.png",
              title:
                "宏碁(acer) DP高清线1.2版 2K*4KDisplayPort公对公接线笔记本电脑显卡连接显示器视频线1.5米",
              price: "190",
               ___index: 0,
            },
            {
              mainImg: "https://i.loli.net/2020/12/05/c9mptI5Pg8qJ6ny.png",
              title:
                "宏碁(acer) DP高清线1.2版 2K*4KDisplayPort公对公接线笔记本电脑显卡连接显示器视频线1.5米",
              price: "190",
               ___index: 0,
            },

          ]
        }
      ]
    }
  },
  /* {
    type: "promotions",
    name: "促销活动",
    img: "md-image",
    notAdd: true,
    notLink: true,
    notImg: true,
    close: true,
    options: {
      list: []
    }
  },
  {
    notAdd: true,
    notImg: true,
    notLink: true,
    drawerPromotions: true,
    type: "promotionDetail",
    promotionsType: "PINTUAN",
    name: "拼团活动",
    subBkColor: "#e1212b",
    subName: "惊喜折扣",
    subColor: "#fff",
    img: "md-image",
    options: {
      list: []
    }
  },
  {
    notAdd: true,
    notImg: true,
    notLink: true,
    drawerPromotions: true,
    type: "promotionDetail",
    promotionsType: "SECKILL",
    name: "秒杀活动",
    subBkColor: "#e1212b",
    subName: "x点场",
    subColor: "#fff",
    img: "md-image",
    options: {
      list: []
    }
  }, */
];
