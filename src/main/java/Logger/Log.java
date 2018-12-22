package Logger;

public class Log {
    private static int count = 0;

    public static void info(String str){
        print(0,str);
    }

    public static void error(String str){
        print(-1,str);
    }

    public static void success(String str){
        print(1,str);
    }

    private static void print(int level, String msg){
        count++;
        String strLevel = count+". ";

        switch(level){
            case 1:
                strLevel += " [Success] - ";
                break;
            case -1:
                strLevel += " [Error] - ";
            case 0:
                strLevel += " [Info] - ";
        }

        System.out.println(strLevel+msg);
    }
}
