package com.xulihuazj.pms.utils.emoji;


import com.xulihuazj.pms.utils.log.LogHelper;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 聊天表情字符串互转
 */
public class EmojiUtils {

    private static Logger logger = LogManager.getLogger(EmojiUtils.class);


    /**
     * @param
     * @return 转换后字符串
     * @throws UnsupportedEncodingException exception
     * @Description 将字符串中的emoji表情转换成可以在utf-8字符集数据库中保存的格式（表情占4个字节，需要utf8mb4字符集）
     */
    public static String emojiConvert(String emjiStr) {
        if (StringUtils.isBlank(emjiStr)) {
            return null;
        }
        String patternString = "([\\x{10000}-\\x{10ffff}\ud800-\udfff])";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(emjiStr);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            try {
                matcher.appendReplacement(stringBuffer, "[<[" + URLEncoder.encode(matcher.group(1), "UTF-8") + "]>]");
            } catch (UnsupportedEncodingException e) {
                LogHelper.exception(e, logger, "转换表情异常");
            }
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    /**
     * @return 转换前的字符串
     * @throws UnsupportedEncodingException exception
     * @Description 还原utf8数据库中保存的含转换后emoji表情的字符串
     * 转换后的字符串
     */
    public static String emojiRecovery(String emjiStr) {
        if (StringUtils.isBlank(emjiStr)) {
            return null;
        }
        String patternString = "\\[\\<\\[(.*?)\\]\\>\\]";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(emjiStr);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            try {
                matcher.appendReplacement(stringBuffer, URLDecoder.decode(matcher.group(1), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                LogHelper.exception(e, logger, "转换表情异常");
            }
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

}