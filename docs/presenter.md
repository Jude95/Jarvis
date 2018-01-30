# Presenter
Jarvis 的 Presenter 是 MVVM 中的 ViewModel 的概念。因为在实际开发中，ViewModel这一层还要做一些业务逻辑，并且需要处理一些页面生命周期。所以借鉴 MVP 模式的设计，将 ViewModel 的概念改为 Presenter。

## 生命周期
Presenter 简化了各个 UI 组件复杂的生命周期。提供了最简单的3个生命周期。

### onCreate
这个生命周期在 Presenter 被创建时回调，是最先回调的生命周期。
可以在这个生命周期中进行一些 Presenter 属性的初始化。
这个时候 View 并没有准备好。
这个生命周期只会被回调一次。

### onViewAttached
这个生命周期是在 Presenter 对应的 View 被添加到 Window 的时候被调用。
比如:
 + Activity `onCreate` 时
 + Fragment `onAttach` 时
 + View `onViewAttachedToWindow` 时

可以在这里对 View 进行一些初始化操作。


### onViewDetached
这个生命周期是在 Presenter 对应的 View 被移除 Window 的时候被调用。
比如
 + Activity `onDestroy` 时
 + Fragment `onDetach` 时
 + View `onViewDetachedFromWindow` 时

可以在这里进行一些 UI 相关订阅的解除操作。

## 实用模板
Jarvis 提供了2个 Presenter 子类的实现，来处理2个模板场景。
你也可以对 Presenter 制作更多的定制模板。

