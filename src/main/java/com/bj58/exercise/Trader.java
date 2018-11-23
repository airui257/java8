package com.bj58.exercise;

/**
 * 交易员类
 *
 * @author liruifeng01
 */
public class Trader {

    /**
     * 交易员姓名
     */
    private String name;
    /**
     * 交易员所在城市
     */
    private String city;

    public Trader() {
    }

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Trader [name=" + name + ", city=" + city + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Trader trader = (Trader) o;

        return new org.apache.commons.lang3.builder.EqualsBuilder()
                .append(name, trader.name)
                .append(city, trader.city)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
                .append(name)
                .append(city)
                .toHashCode();
    }
}
