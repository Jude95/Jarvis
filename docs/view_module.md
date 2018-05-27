# 视图模块化
Jarvis 中 Presenter 是包含了视图，并与其双向绑定的，所以Presenter是一个包含部分页面UI展示和与之相关的业务逻辑的模块。

这些模块按线性的方式排布在页面中，可以很灵活地调换位置且互不影响，甚至在 View/Activity/Fragment 之间变换。

每个模块都有自己独立的生命周期，可以单独通过网络获取数据、渲染视图等等。

```java
// 将视图模块绑定到 Activity 上
public class AboutActivity extends JarvisActivity<AboutPresenter>{
}

// 将视图模块绑定到 Fragment 上
public class AboutFragment extends JarvisFragment<AboutPresenter>{
}

// 将视图模块绑定到 ViewGroup 上
ViewBinder.createView(AboutPresenter.class, parentView);

```
