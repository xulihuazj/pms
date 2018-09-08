package com.xulihuazj.pms.impl.test;

public enum PayrollDay {

    MONDAY, TUESDAY, WEDENESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

    private static final int HOURS_PRE_SHIFT = 8;

    double pay(double hoursWorked, double payRate) {
        double basePay = hoursWorked * payRate;

        double overtimePay; //计算超时时间
        switch (this) {
            case SATURDAY:
            case SUNDAY:
                overtimePay = hoursWorked * payRate / 2;
            default:
                overtimePay = hoursWorked <= HOURS_PRE_SHIFT ? 0 : (hoursWorked - HOURS_PRE_SHIFT) * payRate / 2;
                break;
        }
        return basePay + overtimePay;
    }
}
