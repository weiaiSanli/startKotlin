# startKotlin
##### 导包

1. 使用kotlin,Arouter,以及kotlin的协程包coroutines

   * **注意: **kotlin或者java,kotlin混合开发中使用Arouter引用不能使用javaCompileOptions,而是使用kapt引用,否则会导致找不到路径的错误,可以通过配置全局截取获得错误信息

     ```
      //kotlin中不要使用
      javaCompileOptions {
                 annotationProcessorOptions {
                     arguments = [AROUTER_MODULE_NAME: project.getName()]
                 }
             }
      //正确的使用
      kapt {
         arguments {
             arg("AROUTER_MODULE_NAME", project.getName())
             arg("AROUTER_GENERATE_DOC", "enable")
         }
     }       
     ```

2. 在Arouter模块化中如果需要在module的Application中初始化数据,可以使用反射.示例中BaseApplication中modulesApplicationInit方法调取子模块的Application的onCreate()方法

   ```
   /**模块 Application 接口实现类:用于当前module的Application的初始化配置参数
    * created by shi on 2019/6/18/018
    */
   public class LoginApplicationLike implements IComponentApplication {
       private static final String TAG = "UserApplicationLike";
       @Override
       public void onCreate(BaseApplication application) {
           Log.d(TAG, "onCreate: LoginApplicationLike 创建并初始化");
       }
   
       @Override
       public Application getAppliaction() {
           return null;
       }
   }
   ```

##### 使用协程延迟获取数据

* 具体参考 common项目下的retrofit包下的LoginCoroutineScope中使用DSL(domain special language:领域特定语言,groovy书写的build.gradle文件也属于DSL)

* 使用launch(Main)...async...await同步请求网络数据,await数据在主线程中,注意在io线程有一个IOException,使用一个Main线程池返回到主线程中回调

  ```
   // io线程执行
  try {
     it.execute()
  } catch (e: IOException) {
      "我是IOException".log()
      val executor = MainThreadExecutor()
      executor.execute {
       retrofitCoroutine.onFailed?.invoke(ConstanceBase.NET_ERROR, -1)
        }
        null
  }
  ```

* retrifit的网络请求

  ```
   retrofit<LoginBean> { //LoginBean是login(map)的回调数据
              val map = ArrayMap<String, String>()
              map.apply {
                  this["userName"] = mvpView?.getUserName()
                  this["passWord"] = mvpView?.getPassword()
  
              }
              api = LoginApi.instance.login(map) //下方是retrofit
  
              onSuccess { 
                  mvpView?.loginSuccess(mvpView?.getUserName()!!)
              }
  
              onFailed { error, _ ->
                  mvpView?.loginError(error)
              }
  
          }
  ```

  

* 其他DSL可以查看utils包下的TextWatcherDsl ,NavigationCallbackDsl对Arouter拦截的封装,使用在接口过多可以动态添加需要的接口,不需要的可以不添加到回调中

  ```
  public interface NavigationCallback { //本身四个接口
      void onFound(Postcard postcard);
  
      void onLost(Postcard postcard);
  
      void onArrival(Postcard postcard);
  
      void onInterrupt(Postcard postcard);
  }
  //使用举例:
  ARouter.getInstance().build(ArouterConst.OrderModule.order_orderActivity)
              .navigation(this , navigationCallback { //扩展函数中想使用那个接口回调用哪个
                  onFound { 
                      "找到了界面".log() 
                  }
              })
  ```

* lazy延迟加载数据 查看 SPreferenceUtil类.只有在使用到数据的时候才会真正的去获取数据

  ```
  private var finger_type: Boolean by SPreferenceUtil(ConstanceBase.finger_type, false) //获取是否添加了指纹识别
  //修改
  finger_type = true //修改成true即可
  ```


##### groovy编辑build

* 由于上传隐私原因,对Base_url配置到本地,在common->build.gradle中更改:

  ```
   def url = new File("D://UI/test.txt").eachLine { //地址替换成你本地的文件即可
              return it.toString()
          }
  
          debug{
              minifyEnabled false
              proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
              buildConfigField("String", "BASE_URL", url)
              buildConfigField("boolean", "isDebug", "true")
          }
  ```
