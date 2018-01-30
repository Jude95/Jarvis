# 双向绑定
Jarvis 中的双向绑定体现为 `Presenter <-> Xml` 之间的绑定，通过 DataBinding 实现。

Presenter 的声明中，填写 xml 对应的 Binding 类。
xml 中使用名字为 `presenter` 类型为对应 Presenter 的变量。
然后运行时，Presenter 与 UI 将会绑定起来。修改 Presenter 将自动修改 UI。

绑定语法参考 DataBinding 语法。