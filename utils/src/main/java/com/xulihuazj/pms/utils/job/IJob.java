package com.xulihuazj.pms.utils.job;

public interface IJob {

    /**
     * 启动一个Job
     *
     * @return
     * @throws Exception
     */
    Boolean start() throws Exception;

    /**
     * 停止一个Job
     *
     * @return
     * @throws Exception
     */
    Boolean stop() throws Exception;

    /**
     * 暂停一个执行中的JOb
     *
     * @return
     * @throws Exception
     */
    Boolean pause() throws Exception;

    /**
     * 恢复一个Job的执行
     *
     * @return
     * @throws Exception
     */
    Boolean resume() throws Exception;
}
