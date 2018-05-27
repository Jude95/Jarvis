# Jarvis Android MVVM框架
Jarvis 是一个 Android MVVM 框架。基于 DataBinding。
Jarvis 的目的并不是一个标准，完美，满足代码洁癖患者的框架，他的设计重心在于大量减少应用开发冗余代码与操作，让开发者能够快速构建应用,一切为了开发体验。



## Design
架空Activity，使逻辑交给Presenter，Presenter与View通过Databinding进行双向绑定。大量支持RxJava。

![](img/image.png)


## Install

#### 1. 依赖
```grovvy
    compile 'com.jude:jarvis:1.0.0'
```

#### 2. 使用 DataBinding

```groovy
android {
    dataBinding {
        enabled = true
    }
}
```

## Usage
#### 1. 继承Activity/Fragment
将所有的Activity均继承自`JarvisActivity<P>`。
```java
public class AboutActivity extends JarvisActivity<AboutPresenter>{
}
```
参数 P 为 Presenter 的类型。
Activity 一般情况下不用再写任何代码，只需要在泛型中指定此 Activity 所承载的 Presenter。

#### 2. 写页面xml
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
所有的 xml 中均声明一个 Presenter。 类名就填 xml 对应的 Presenter 的类名。与此 Presenter 进行双向绑定。

#### 3. 写Presenter
```java
public class AboutPresenter extends JarvisPresenter<ActivityAboutBinding> {

}
```
参数为 xml 对应的 Binding 类。
Presenter 与 xml 双向绑定，负责获取数据，更新UI即可。

## API
[双向绑定](./docs/tow-way-binding.md)
[Presenter 生命周期](./docs/presenter.md)
[视图模块化](./docs/view_module.md)
[Data 模板](./docs/data.md)
[List 模板页面](./docs/list.md)