package com.xulihuazj.pms.mapper.pmsdb.sms;

import com.xulihuazj.pms.config.annotation.PMSDB;
import com.xulihuazj.pms.object.pmsdb.sms.SmsLogDO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

@PMSDB
public interface SmsLogDOMapper {

    int deleteByPrimaryKey(Long smsId);

    int insertSelective(SmsLogDO record);

    SmsLogDO selectByPrimaryKey(Long smsId);

    int updateByPrimaryKeySelective(SmsLogDO record);

    int sendCount(@Param("mobile") Long mobile, @Param("serviceType") int serviceType);

    /**
     *
     * @Function: SmsLogDOMapper.java
     * @Description: 查询验证码是否正确
     * @return：SmsLogDO
     * @date: 2018年4月10日 下午7:23:42
     *
     */
    SmsLogDO selectSmsCode(@Param("mobile") Long mobile, @Param("smsCode") String smsCode,
                           @Param("serviceType") int serviceType);

    /**
     *
     * @Function: SmsLogDOMapper.java
     * @Description: 使用完验证码后让期失效
     * @return：int
     * @date: 2018年4月11日 下午12:23:31
     *
     */
    int updateSmsCodeValidationStatus(@Param("mobile") Long mobile, @Param("serviceType") int serviceType);

    /**
     * 查询指定日期发送短信的次数
     * @param mobile
     * @param date
     * @param serviceType
     * @param serviceConditions 业务条件
     * @return
     */
    int selectByMobileAndType(@Param("mobile") Long mobile, @Param("createTime") Date date, @Param("serviceType") int serviceType, @Param("serviceConditions") String serviceConditions);

    /**
     * 查询这个账期时间内发送短信的次数
     *
     * @param mobile
     * @param serviceType
     * @param serviceConditions
     * @param billStartTime
     * @param billEndTime
     * @return
     */
    int selectSentFrequency(
            @Param("mobile") Long mobile, @Param("serviceType") int serviceType,
            @Param("serviceConditions") String serviceConditions, @Param("start") Date billStartTime,
            @Param("end") Date billEndTime);

    /**
     * 统计该房间该手机号一分钟内发短信次数
     * @param parseLong
     * @param parseInt
     * @param serviceConditions
     * @return
     */
    int sendCountOfRoom(@Param("mobile") long parseLong, @Param("serviceType") int parseInt, @Param("serviceConditions") String serviceConditions);

}
