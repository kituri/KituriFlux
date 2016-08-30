# 《Kituri Flux》项目

flux也看了一段时间了，是时候撸个项目模板代码给大家一起玩玩了，独乐乐不如众乐乐。

不知道flux是个神马的筒子可以去看这里来进行一个入门
↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

AndroidFlux项目一览-Flux架构的Android移植
http://www.jianshu.com/p/896ce1a8e4ed

项目介绍与剖析：

流传递信息用了eventbus 3.0 来解耦和分发事件。

起了一根service来进行跨界分发。

Dispatcher：在应用中只能有一个，作为总调度，使用ActionsCreator作为其业务代理。

ActionsCreator：每个"UI"需要1个，用作当前"UI"的总调度代理，负责协调代理"UI"层的业务逻辑调用。(适用范围：Activity、
Fragment、Service，说是UI层面，但是涉及业务逻辑的都可以使用。)

Store：每个"UI"可能有N个，很好理解比如1个activity里有N个业务逻辑。

Action：传递信息的介质，且携带数据。


====================================================================================================

apk下载地址：
http://fir.im/kituriflux


使用开源库：

bottom navigation (material design)
https://github.com/roughike/BottomBar

马总的LiteOrm(马总的库超好用的，不去弄一个玩玩嘛？)
https://github.com/litesuits/android-lite-orm

api使用的是家家的gank.io。(gank超棒的不去看看嘛？)
http://gank.io/api

