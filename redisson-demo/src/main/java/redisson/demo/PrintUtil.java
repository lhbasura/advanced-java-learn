package redisson.demo;

public class PrintUtil {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final int YELLOW = 31;
    public static void colorPrint(String str,int color){
        System.out.format("\u001B[%dm"+str+"\n"+ ANSI_RESET, color);
    }
    public static void printRed(String str){
        colorPrint(str,YELLOW);
    }
}
