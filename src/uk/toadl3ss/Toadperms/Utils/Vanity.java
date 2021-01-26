package uk.toadl3ss.Toadperms.Utils;

public class Vanity {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    public static void printVainity() {
        String vanity = "\n \n" +
                "g       .    r _______ _____                          g__ _ _\n" +
                "g       /\\\\  r|__   __|  __ \\                         g\\ \\ \\ \\\n" +
                "g      ( ( )    r| |  | |__) |__ _ __ _ __ ___  ___    g\\ \\ \\ \\\n" +
                "g       \\\\/     r| |  |  ___/ _ \\ '__| '_ ` _ \\/ __|    g) ) ) )\n" +
                "g        '      r| |  | |  |  __/ |  | | | | | \\__ \\   g/ / / /\n" +
                "\tg       r|_|  |_|   \\___|_|  |_| |_| |_|___/  g/_/_/_/ \n" +
                "g     d======================================================   ";
        String green = vanity.replace("g", ANSI_GREEN);
        String red = green.replace("r", ANSI_RED);
        String VanityFinish = red.replace("d", ANSI_RESET);
        System.out.println(VanityFinish);
    }
}
