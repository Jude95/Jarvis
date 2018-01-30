# 单个数据页面
Jarvis 提供单个数据页面的模板。

## DataPresenter
```java
DataPresenter<D extends ViewDataBinding,M>
```
2个参数，第一个是 Presenter 对应视图的 Binding 类，第二个是要展示的数据类型。

## 更新数据
Jarvis 全面支持 Rxjava2, 所以数据添加都是以 Consumer 的形式提供入口。
```java
    public Consumer<M> getDataConsumer();
    public Consumer<Throwable> getErrorConsumer();

    // 进行 DisposableTransformer 后，这条链路会在视图销毁时自动断开
    public <T> DisposableTransformer<T> getDisposableTransformer()

```

## 异常处理
当 `ErrorConsumer` 收到异常的时候，会展示 `ViewDecorator` 的 Error Page.
