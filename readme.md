
# mini-spring
## 关于
**mini-spring**是简化版的spring,希望可以通过复现此项目加深本人对spring源码的理解

## 参考
- [《mini-spring》](https://github.com/DerekYRC/mini-spring)

## notes

### ApplicationContext实现思路
> 前情提要 
>
>   上一节实现了BeanFactoryPostProcessor和BeanPostProcessor 从而实现了容器机制的扩展 
>   
>   在BeanFactoryPostProcessor中 可以在bean实例化之前对BeanDefinition的属性进行修改
>   
>   在BeanPostProcessor中可以在bean实例化之后对bean对象进行替换

  而在ApplicationContext上下文机制中 将实现前两者的自动识别装配 该机制的核心为`refresh()`

  在此梳理ClassPathXmlApplicationContext初始化时做了些什么

- 构造阶段
  - 接收参数配置的路径 调用另一个构造—>`public ClassPathXmlApplicationContext(String[] configLocations)`
  - 将传入的配置文件参数赋值给成员`configurations` 同时调用`refresh()` 意味着进一步加载配置文件中的内容
- `refresh()`实现细节
  - refresh声明在`ConfigurableApplicationContext` 实现在`AbstractApplicationContext`
  - 首先调用`refreshBeanFactory()` 在`AbstractRefreshableApplicationContext`中实现 作用是创建`beanFactory`并加载`BeanDefinition`
  - 接着创建一个BeanPostProcessor加入beanFactory 
    - 这里创建的BeanPostProcessor实际上是一个ApplicationContextAwareProcessor对象 这个对象实现了BeanPostProcessor
    - 传递进去当前对象(上下文对象 注意到它本质是ResourceLoader和ConfigurableApplicationContext) 
    - 这里说是使得继承ApplicationContextAware的bean可感知？ 后面再看
  - 调用`invokeBeanFactoryPostProcessors(beanFactory)`
    - 具体的 从bean工厂中获取类型为BeanFactoryPostProcessor的bean 调用他们的`postProcessBeanFactory()`执行预置操作
  - 执行`registerBeanPostProcessors(beanFactory)`
    - 获取所有的BeanPostProcessor 将他们加入AbstractBeanFactory中的对应列表中
    - 提前其他的bean注册
  - 初始化事件发布者`initApplicationEventMulticaster()`
    - 创建SimpleApplicationEventMulticaster对象(AbstractApplicationContext中的成员applicationEventMulticaster)
    - 将此对象加入工厂中
  - `registerListeners()`注册事件监听器
    - 从工厂中拿出ApplicationListener对象 将它们加入applicationEventMulticaster中
  - `finishBeanFactoryInitialization(beanFactory)` 注册类型转换器和提前实例化单例bean
    - 如果存在则从仓库中取出ConversionService赋值给AbstractBeanFactory
    - beanFactory.preInstantiateSingletons()提前实例化单例的bean
  - 刷新完成`finishRefresh()`
    - `publishEvent(new ContextRefreshedEvent(this))`
    - `applicationEventMulticaster.multicastEvent(event)` 即调用监听器中对该事件感兴趣的对应方法
      - 感兴趣: 该事件是监听器的泛型参数的类型
