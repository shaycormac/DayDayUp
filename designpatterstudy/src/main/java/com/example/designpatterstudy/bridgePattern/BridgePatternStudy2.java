package com.example.designpatterstudy.bridgePattern;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017-02-20 13:38 GMT+8
 * @email 邮箱： 574583006@qq.com
 * @content 说明：刺激一点，三个维度，添加一个在哪家公司工作。
 */
public class BridgePatternStudy2 
{
    //新加的维度
    abstract class Company
    {
        protected Platform platform;

        public abstract void worker();
    }
    //原有的二维代码
    abstract class Platform {
        protected Monkey monkey;

        public abstract void program();

    }

    abstract class Monkey {
        public abstract void type();
    }
    class GoogleCompany extends Company
    {
        public GoogleCompany(Platform platform)
        {
            this.platform = platform;
        }

        @Override
        public void worker() 
        {
            System.out.println("在谷歌公司上班");
            platform.program();
        }
    }
    
    class TaoBaoCompany extends Company
    {
        public TaoBaoCompany(Platform platform)
        {
            this.platform = platform;
        }

        @Override
        public void worker()
        {
            System.out.println("在淘宝公司上班");
            platform.program();
        }
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
        GoogleCompany googleCompany = new GoogleCompany(windowPlatform);
        googleCompany.worker();
        WindowPlatform windowPlatform1 = new WindowPlatform(pMonkey);
        windowPlatform1.program();
        GoogleCompany googleCompany1 = new GoogleCompany(windowPlatform1);
        googleCompany1.worker();
        LinuxPlatfOrm linuxPlatfOrm = new LinuxPlatfOrm(mMonkey);
        LinuxPlatfOrm linuxPlatfOrm1 = new LinuxPlatfOrm(pMonkey);
    }
}
