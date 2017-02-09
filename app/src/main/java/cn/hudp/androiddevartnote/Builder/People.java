package cn.hudp.androiddevartnote.Builder;

/**
 * Created by HuDP on 2017/2/5.
 */

public class People {
    private String name;
    private int age;
    private int sex;
    private String interest;

    private People(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.sex = builder.sex;
        this.interest = builder.interest;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", interest='" + interest + '\'' +
                '}';
    }

    public static class Builder {
        private String name;
        private int age;
        private int sex;
        private String interest;

        public Builder(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public Builder setSex(int sex) {
            this.sex = sex;
            return this;
        }

        public Builder setInterest(String interest) {
            this.interest = interest;
            return this;
        }

        public People build() {
            return new People(this);
        }

    }
}
