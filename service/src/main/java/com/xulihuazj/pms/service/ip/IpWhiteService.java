package com.xulihuazj.pms.service.ip;

import com.xulihuazj.pms.object.pmsdb.ip.IpWhiteListDO;

import java.util.Map;

/**
 * 白名单
 */
public interface IpWhiteService {

    /**
     * 检查路径及IP是否可以放行
     * @param url 路径
     * @param ip ip地址
     * @return 是否放行
     */
    boolean checkIpWhite(String url, String ip);


    /**
     * 加载ip白名单配置，并存储到redis中
     * @return
     */
    Map<String,Map<String,IpWhiteListDO>> loadConfiguration();

}
