package main.order.sys;

public class ThreadLocalUse {
    private static ThreadLocal<Object> threadLocalCase = new ThreadLocal<Object>(); //使用ThreadLocal对象来操作Thread中的ThreadMap,以存储当前线程所对应的数据,我使用此类用于实现用户权限的验证

    public static ThreadLocal<Object> getThreadLocalCase() {
        return threadLocalCase;
    }

    public static void setThreadLocalCase(ThreadLocal<Object> threadLocalCase) {
        ThreadLocalUse.threadLocalCase = threadLocalCase;
    }

}
