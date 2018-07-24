package com.xulihuazj.pms.utils.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.MessageFormat;

/**
 * 日志类 所有日志记录使用该类 该类日志后续会用于监控 统计等
 */
public class LogHelper {

	private static final transient Logger  logger = LogManager.getLogger(LogHelper.class);

    public static String getMessage(String template, Object... parameters) {
        try {
            return MessageFormat.format(template, parameters);
        } catch (Exception e) {
            return template + "【日志打印异常】" + e.getMessage();
        }
    }

    /**
     * debug日志
     *
     * @param logger
     * @param template   带参数的日志模版 a={0},b={1}
     * @param parameters 参数 需对应
     */
    public static void debug(final Logger logger, String template, Object... parameters) {
        if (logger.isDebugEnabled()) {
            logger.debug(getMessage(template, parameters));
        }
    }

    /**
     * info日志
     *
     * @param logger
     * @param template   带参数的日志模版 a={0},b={1}
     * @param parameters 参数 需对应
     */
    public static void info(final Logger logger, String template, Object... parameters) {
        if (logger.isInfoEnabled()) {
            logger.info(getMessage(template, parameters));
        }
    }

    /**
     * warn日志
     *
     * @param logger
     * @param template   带参数的日志模版 a={0},b={1}
     * @param parameters 参数 需对应
     */
    public static void warn(final Logger logger, String template, Object... parameters) {
        if (logger.isWarnEnabled()) {
            logger.warn(getMessage(template, parameters));
        }
    }

    /**
     * error日志
     *
     * @param logger
     * @param template   带参数的日志模版 a={0},b={1}
     * @param parameters 参数 需对应
     */
    public static void error(final Logger logger, String template, Object... parameters) {
        if (logger.isErrorEnabled()) {
            logger.error(getMessage(template, parameters));
        }
    }

    /**
     * exception日志
     *
     * @param logger
     * @param template   带参数的日志模版 a={0},b={1}
     * @param parameters 参数 需对应
     */
    public static void exception(Throwable e, Logger logger, String template, Object... parameters) {
        logger.error(getMessage(template, parameters), e);
    }


}
