# 列表页面
Jarvis 提供列表页面的模板。
Jarvis 使用 EasyRecyclerView 来制作列表界面。

## ListPresenter
```java
ListPresenter<D extends ViewDataBinding,M>
```
2个参数，第一个是 Presenter 对应视图的 Binding 类，第二个是列表的数据类型。

### 事件
```java
    @Override
    public void onRefresh() {
    }

    @Override
    public void onLoadMore() {

    }
```
提供2个行为的回调，足以覆盖大部分场景。

### 添加数据
Jarvis 全面支持 Rxjava2, 所以数据添加都是以 Consumer 的形式提供入口。
```java
    public Consumer<List<M>> getRefreshConsumer();
    public Consumer<List<M>> getMoreConsumer();
    public Consumer<Throwable> getErrorConsumer();

    // 进行 DisposableTransformer 后，这条链路会在视图销毁时自动断开
    public <T> DisposableTransformer<T> getDisposableTransformer()
```

### 设置ItemView
在 xml 中直接配置 itemPresenter 。
注意 `id` 一定要为 `recycler` 才能被识别。
```java
    <com.jude.easyrecyclerview.EasyRecyclerView
        android:id="@+id/recycler"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        itemPresenter='@{"me.ele.grandstore.presentation.item.CIItemPresenter"}'
        dividerColor='@{@color/colorPrimary}'
        dividerHeight='@{@dimen/divider_height}'/>
```
然后无需再写 Adapter 。本列表页面开发完成。

### ItemPresenter
```java
class ListItemPresenter<D extends ViewDataBinding,M>
```
ItemPresenter 代表了一块 itemView. 开发更为简单，参数 D 为 xml 的 bindding 类. M 为 Model 类型。
ItemPresenter 的任务就是将 Model 应用到 UI, 这里直接将 Model 作为绑定源, 比如 xml 中填写属性:
```xml
        <ImageView
            android:layout_width="48dp"
            android:layout_height="48dp"
            app:image="@{presenter.data.image}"/>

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            style="@style/TextAppearance.AppCompat.Subhead"
            android:text="@{presenter.data.name}"/>
```
直接使用 `presenter.data` 作为 ViewModel.

## 异常处理
当 `ErrorConsumer` 收到异常的时候，如果没有数据会展示 `ViewDecorator` 的 Error Page.
如果已经有数据，则会在列表底部展示错误。

## 列表效果
ListViewConfig 提供列表效果控制，包括：
```java
    // 启动时自动开启加载界面
    setStartWithProgress(boolean startWithProgress);
    // 启动时自动触发刷新
    setRefreshWhenAttach(boolean refreshWhenAttach);
    // 开启刷新功能
    setRefreshAble(boolean refreshAble);
    // 开启加载更多功能
    setLoadMoreAble(boolean loadMoreAble);
    // 开启展示空白页面功能
    setShowEmptyView(boolean showEmptyView);
    // 异常 Footer 点击自动恢复
    setErrorClickToResume(boolean errorClickToResume);
    // 开启展示没有更多的 Footer
    setShowFooterEndView(boolean showFooterEndView);
    // 开启展示加载异常的 Footer
    setShowFooterErrorView(boolean showFooterErrorView);
```
ListViewAdapter 提供列表效果的视图，包括：
```java
    // 列表没有数据
    @LayoutRes int getEmptyView();
    // 列表没有更多
    @LayoutRes int getFooterEndView();
    // 更多加载中
    @LayoutRes int getFooterLoadingView();
    // 更多加载错误
    @LayoutRes int getFooterErrorView();
```

可以通过 `JarvisSetting.setListViewAdapterFactory(new ListViewAdapter.Factory());` 设置全局默认效果配置。
也可以通过重写：
```java
    protected ListViewAdapter getListViewAdapter(){
    }

    protected ListViewConfig getListConfig(){
    }
```
来自定义单个页面的效果。