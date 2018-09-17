package top.douruanliang.fragmentlazyapp.testw;

import top.douruanliang.fragmentlazyapp.Employee;

/**
 * author: dourl
 * created on: 2018/9/13 下午7:52
 * description:
 */
public   class Salary extends Employee {
    public Salary(String name, String address) {
        super(name, address);
    }

    @Override
    public double computePay() {
        return 0;
    }

    @Override
    public String getAddress() {
        return super.getAddress();
    }
}
