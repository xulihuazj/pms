package com.xulihuazj.pms.impl.test;

/**
 * PayrollDay2.java 1.0.0 2018/08/09  16:31
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 * 1. 2018/08/09  16:31 created by xulihua
 */
public enum PayrollDay2 {

    MONDAY(PayType.WEEKDAY),

    TUESDAY(PayType.WEEKDAY),

    WEDENESDAY(PayType.WEEKDAY),

    THURSDAY(PayType.WEEKDAY),

    FRIDAY(PayType.WEEKDAY),

    SATURDAY(PayType.WEEKEND),

    SUNDAY(PayType.WEEKEND);

    private final PayType payType;

    PayrollDay2(PayType payType) {
        this.payType = payType;
    }

    double pay(double hoursWorked, double payRate) {
        return this.payType.pay(hoursWorked, payRate);

    }

    private enum PayType {
        WEEKDAY {
            double overtimePay(double hours, double payRate) {
                return hours <= HOURS_PER_SHIFT ? 0 : (hours - HOURS_PER_SHIFT) * payRate / 2;
            }
        },

        WEEKEND {
            double overtimePay(double hours, double payRate) {
                return hours * payRate / 2;
            }
        };

        private static final int HOURS_PER_SHIFT = 8;

        abstract double overtimePay(double hours, double payRate);

        double pay(double hoursWorked, double payRate) {
            double basePay = hoursWorked * payRate;
            return basePay + overtimePay(hoursWorked, payRate);
        }

    }

}
