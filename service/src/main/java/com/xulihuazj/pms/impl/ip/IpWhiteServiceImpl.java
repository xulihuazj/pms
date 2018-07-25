package com.xulihuazj.pms.impl.ip;

import com.xulihuazj.pms.object.pmsdb.ip.IpWhiteListDO;
import com.xulihuazj.pms.service.ip.IpWhiteService;

import java.util.Map;

/**
 * IP 白名单实现
 */
public class IpWhiteServiceImpl implements IpWhiteService {

    @Override
    public boolean checkIpWhite(String url, String ip) {
        return false;

    }

    @Override
    public Map<String, Map<String, IpWhiteListDO>> loadConfiguration() {
        return null;
    }
}
