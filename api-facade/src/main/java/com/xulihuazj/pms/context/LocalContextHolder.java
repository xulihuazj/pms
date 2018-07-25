package com.xulihuazj.pms.context;

/**
 * @Description
 * @author xulihua
 * @Date 2017年8月24日上午9:37:31
 */
public class LocalContextHolder {

    private static final ThreadLocal<GlobalLocalContext> localContextHolder = new ThreadLocal<>();

    /**
     * 清除线程中的context
     */
    public void clearContext() {
        localContextHolder.remove();
    }

    /**
     * 获取当前线程Context
     */
    public static  GlobalLocalContext getContext() {
        GlobalLocalContext ctx = localContextHolder.get();
        if (ctx == null) {
            ctx = createEmptyContext();
            localContextHolder.set(ctx);
        }
        return ctx;
    }

    public static  void setContext(GlobalLocalContext context) {
        localContextHolder.set(context);
    }

    /**
     * 创建空context
     * @return
     */
    public static  GlobalLocalContext createEmptyContext() {
        return new GlobalLocalContext();
    }
}
