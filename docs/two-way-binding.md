# 双向绑定
Jarvis 中的双向绑定体现为 `Presenter <-> Xml` 之间的绑定，通过 DataBinding 实现。

Presenter 的声明中，填写 xml 对应的 Binding 类。xml 中使用名字为 `presenter` 类型为对应 Presenter 的变量。
然后运行时，Presenter 与 UI 将会绑定起来。修改 Presenter 将自动修改 UI。

绑定语法参考 DataBinding 语法。

```java
public class AboutActivity extends JarvisActivity<AboutPresenter>{
}
```
```xml
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">

    <data>
        <variable name="presenter" type="com.jude.sample.presentation.AboutPresenter"/>
    </data>
    ……
</layout>
```
```java
public class AboutPresenter extends JarvisPresenter<ActivityAboutBinding> {

}
```

注意 xml 中的data变量名只能为 `presenter` 。Presenter 中也不用再填写setContent 与 findViewById .
