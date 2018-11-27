package com.xulihuazj.pms.mapper.pmsdb.ip;

import com.xulihuazj.pms.config.annotation.PMSDB;
import com.xulihuazj.pms.object.pmsdb.ip.IpWhiteListDO;

import java.util.List;

@PMSDB
public interface IpWhiteListDOMapper {

    int deleteByPrimaryKey(Long ipLocationId);

    int insertSelective(IpWhiteListDO record);

    IpWhiteListDO selectByPrimaryKey(Long ipLocationId);

    int updateByPrimaryKeySelective(IpWhiteListDO record);

    /**
     * 只查询前100条
     *
     * @return
     */
    List<IpWhiteListDO> selectAll();
}