import Message.ConsoleMessage;
import Message.IMessage;
import Message.WindowMessage;

/**
 * Created by QUOCVIET on 12/23/2020.
 */
public class TestDriver {
    public static void main(String[] args)
    {
        IMessage a= new WindowMessage();
        a.notify("FullName","ĐỘ DÀI TỐI ĐA 32 KÍ TỰ");
        // --------------
        a= new ConsoleMessage();
        a.notify("FullName", "ĐỘ DÀI TỐI ĐA 32 KÍ TỰ");
    }
}
