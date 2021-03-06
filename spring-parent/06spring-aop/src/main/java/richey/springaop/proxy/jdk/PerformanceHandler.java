package richey.springaop.proxy.jdk;

import richey.springaop.proxy.PerformanceMonitor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class PerformanceHandler implements InvocationHandler{
    private Object target; //目标业务类

    public PerformanceHandler(Object target) {
        this.target = target;
    }

    /**
     * @param proxy   目标对象
     * @param method  目标类的方法（执行点）
     * @param args    目标类方法的入参
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        PerformanceMonitor.begin(target.getClass().getName()+"."+method.getName());
        Object obj = method.invoke(target, args);
        PerformanceMonitor.end();
        return obj;
    }
}
