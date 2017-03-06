package com.example.designpatterstudy.bridgePattern;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017-02-20 13:22 GMT+8
 * @email 邮箱： 574583006@qq.com
 * @content 说明：一个类存在两个（或多个）独立变化的维度，且这两个（或多个）维度都需要独立进行扩展。
 * 桥接模式是一种很实用的结构型设计模式，如果软件系统中某个类存在多个独立变化的维度，
 * 通过该模式可以将这多个维度分离出来，使他们可以独立扩展，让系统更加符合“单一职责原则”
 * 本例是两个维度，
 * <p>
 * 桥接模式优点如下：
 * <p>
 * 分离抽象接口及其实现部分。桥接模式使用“对象间的关联关系”解耦了抽象和实现之间固有的绑定关系，使得抽象和实现可以沿着各自的维度来变化。所谓抽象和实现沿着各自维度的变化，也就是说抽象和实现不再在同一个继承层次结构中，而是“子类化”它们，使它们各自都具有自己的子类，以便任何组合子类，从而获得多维度组合对象。
 * <p>
 * 很多情况下桥接模式可以取代多层继承方案，多层继承方案违背了“单一职责原则”，复用性较差，且类的个数非常多，桥接模式是比多层继承方案更好的解决方法，它极大减少了子类的个数。
 * <p>
 * 桥接模式提高了系统的可扩展性，在两个变化维度中任意扩展一个维度，都不需要修改原有系统，符合“开闭原则”。
 * <p>
 * 桥接模式缺点如下：
 * <p>
 * 桥接模式的使用会增加系统的理解与设计难度，由于关联关系建立在抽象层，要求开发者一开始就针对抽象层进行设计与编程。
 * <p>
 * 桥接模式要求正确识别出系统中两个独立变化的维度，因此其使用范围具有一定的局限性，如何正确识别两个独立维度也需要一定的经验积累
 */
public class BridgePatternStudy {
    abstract class Platform {
        protected Monkey monkey;

        public abstract void program();

    }

    abstract class Monkey {
        public abstract void type();
    }

    class WindowPlatform extends Platform {
        public WindowPlatform(Monkey monkey) {
            this.monkey = monkey;
        }

        @Override
        public void program() {
            monkey.type();
            System.out.println("使用Windoew平台");
        }
    }

    class LinuxPlatfOrm extends Platform {
        public LinuxPlatfOrm(Monkey monkey) {
            this.monkey = monkey;
        }

        @Override
        public void program() {
            monkey.type();
            System.out.println("使用Linux平台");
        }
    }

    class PHPMonkey extends Monkey {

        @Override
        public void type() {
            System.out.println("PHP程序员");
        }
    }

    class MobileMonkey extends Monkey {

        @Override
        public void type() {
            System.out.println("移动端程序员");
        }
    }

    public void main() {
        Monkey mMonkey = new MobileMonkey();
        Monkey pMonkey = new PHPMonkey();
        WindowPlatform windowPlatform = new WindowPlatform(mMonkey);
        windowPlatform.program();
        WindowPlatform windowPlatform1 = new WindowPlatform(pMonkey);
        LinuxPlatfOrm linuxPlatfOrm = new LinuxPlatfOrm(mMonkey);
        LinuxPlatfOrm linuxPlatfOrm1 = new LinuxPlatfOrm(pMonkey);
    }
}
