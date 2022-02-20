# 一.具体实现

### Fragment

​	应用总共有三个Fragment，HistoryFragment，RecommendFragment,SubsriptionFragment，三个Fragment都继承于BaseFragment基类，BaseFragment基类有onSubViewLoaded()预留方法让子类去实现，复写父类的onCreateView()方法返回了onSubViewLoaded()方法返回的值。

### UILoader

​	由于加载UI界面，错误UI界面，空白UI界面都是一样的，而成功获取数据的UI界面不是固定不变的，所以UILoader实现了getLoadingView(),getErrorView(),getEmptyView()三个方法，预留了getSuccessView()方法给实例化UILoader的类去实现，实现了UI界面的复用。

# 二.设计模式

### 观察者模式

用例1:

​	推荐模块获取页面数据。

​	RecommendPresenter类充当Subject角色，也就是被观察者，有一专门存储Obsever的容器Obsevers。

​	RecommendFragment实现Obsever接口，充当观察者角色。

​	RecommendFragment在RecommendPresenter注册进行监听，RecommendPresenter负责获取推荐的栏目信息，获取成功后会通知RecommendFragment将数据展示在UI界面中。

### 单例模式

用例1:

​		RecommendPresenter在使用观察者模式的同时，也使用了单例模式，将构造方法设置为了私有，全局只有唯一一个实例。

### 创建器模式

用例1：

​	应用总共有三个Fragment，HistoryFragment，RecommendFragment,SubsriptionFragment，都是由FragmentCreator进行创建，由客户MainContentAdapter(继承于FragmentPagerAdapter)调用。

### 模版方法模式

用例1:

​	尽管应用总共有三个Fragment，HistoryFragment，RecommendFragment,SubsriptionFragment，但是加载UI界面，错误UI界面，空白UI界面都是一样的，只有成功获取数据的UI界面是不一样的，所以前面提及到的UILoader类使用了模版方法，实现了getLoadingView(),getErrorView(),getEmptyView()三个方法，预留了getSuccessView()方法给三个Fragment去实现。

# 三.架构

​	喜马拉雅项目采用的是MVP架构。

​	Activity和Fragment承担View职责，负责UI界面的显示，和UI元素的初始化。

​	Presenter类负责与用户的复杂逻辑处理，本应由Model层通过本地持久化存储或者网络来获取数据，但鉴于项目已经导入喜马拉雅官方SDK，获取数据的代码寥寥数行，就不再新建Model类来负责数据获取，故Presenter也承担起获取数据的责任。

# 四.流程

### 推荐页面流程
<p align="center"><img src="https://user-images.githubusercontent.com/65336599/154831903-231adb7b-69e8-42fc-b1e8-f45ce5e5f406.png">  </p>

​	如上图所示，当用户选择并点击推荐页面RecommendFragment中的某个专辑时，就会进入相对应专辑的详情界面，也就是DetailActivty，当用户选择并点击详情页面的某一集时，则会进入对应集数的播放界面，也就是PlayerActivity。

​	需要注意的是，RecommendFragment和DetailActivty都使用了RecyclerView，所以部分事件和UI绘制都在相应的Adapter中。(RecommendFragment对应RecommendListAdapter，DetailActivty对应DetailListAdapter)

