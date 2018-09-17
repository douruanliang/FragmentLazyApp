package top.douruanliang.fragmentlazyapp;

/**
 * author: dourl
 * created on: 2018/9/13 下午7:47
 * description:
 */
public abstract class Employee {
    private String name;
    private String address;

    public Employee(String name, String address) {
        this.name = name;
        this.address = address;
    }
    public double computePays(){
        return 0.0;
    }
    public abstract double computePay();
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
