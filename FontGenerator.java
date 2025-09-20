/*
 * Decompiled with CFR 0.152.
 */
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FontGenerator {
    public static void main(String[] stringArray) {
        String string = "mtsdf-font/";
        Path path = FontGenerator.a(string);
        if (path == null) {
            // empty if block
        }
        FontGenerator.a(string, path, "pena336");
    }

    private static void a(String string2, Path path, String string3) {
        File file2 = new File(string2 + string3);
        File[] fileArray = file2.listFiles((file, string) -> string.toLowerCase().endsWith(".ttf"));
        if (fileArray != null && fileArray.length > 0) {
            ExecutorService executorService = Executors.newFixedThreadPool(fileArray.length);
            ArrayList arrayList = new ArrayList();
            for (File file3 : fileArray) {
                executorService.execute(() -> {
                    try {
                        String string2 = file3.getName().replaceFirst("[.][^.]+$", "");
                        String string3 = String.format("%s/atlas-gen.exe -font %s -charset %s/charset.txt -type mtsdf -format png -imageout %s.png -json %s.json -size 64 -square4 -pxrange 12", string2, file3.getAbsolutePath(), string2, path.resolve(string2.toLowerCase().replaceAll("-", "_")), path.resolve(string2.toLowerCase().replaceAll("-", "_")));
                        Process process = Runtime.getRuntime().exec(string3);
                        arrayList.add(process);
                        int n2 = process.waitFor();
                        if (n2 == 0) {
                            System.out.println("\u0410\u0442\u043b\u0430\u0441 \u0434\u043b\u044f \u0448\u0440\u0438\u0444\u0442\u0430 " + string2 + " \u0443\u0441\u043f\u0435\u0448\u043d\u043e \u0441\u043e\u0437\u0434\u0430\u043d.");
                        } else {
                            System.out.println("\u041e\u0448\u0438\u0431\u043a\u0430 \u043f\u0440\u0438 \u0441\u043e\u0437\u0434\u0430\u043d\u0438\u0438 \u0430\u0442\u043b\u0430\u0441\u0430 \u0434\u043b\u044f \u0448\u0440\u0438\u0444\u0442\u0430 " + string2);
                        }
                    }
                    catch (IOException | InterruptedException exception) {
                        System.err.println("\u041e\u0448\u0438\u0431\u043a\u0430 \u043f\u0440\u0438 \u0432\u044b\u043f\u043e\u043b\u043d\u0435\u043d\u0438\u0438 \u043a\u043e\u043c\u0430\u043d\u0434\u044b \u0434\u043b\u044f \u0448\u0440\u0438\u0444\u0442\u0430 " + file3.getName() + ": " + exception.getMessage());
                    }
                });
            }
            executorService.shutdown();
            try {
                if (executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS)) {
                    System.out.println("\u041f\u0440\u043e\u0446\u0435\u0441\u0441 \u0437\u0430\u0432\u0435\u0440\u0448\u0451\u043d.");
                }
            }
            catch (InterruptedException interruptedException) {
                System.err.println("\u041e\u0448\u0438\u0431\u043a\u0430 \u043f\u0440\u0438 \u043e\u0436\u0438\u0434\u0430\u043d\u0438\u0438 \u0437\u0430\u0432\u0435\u0440\u0448\u0435\u043d\u0438\u044f \u043f\u043e\u0442\u043e\u043a\u043e\u0432: " + interruptedException.getMessage());
            }
            for (Process process : arrayList) {
                process.destroy();
            }
        } else {
            System.out.println("\u041d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d\u044b \u0444\u0430\u0439\u043b\u044b \u0448\u0440\u0438\u0444\u0442\u043e\u0432 \u0432 \u0443\u043a\u0430\u0437\u0430\u043d\u043d\u043e\u0439 \u043f\u0430\u043f\u043a\u0435.");
        }
    }

    private static Path a(String string) {
        Path path;
        Path path2 = Path.of(string + "out", new String[0]);
        if (Files.notExists(path2, new LinkOption[0])) {
            try {
                Files.createDirectories(path2, new FileAttribute[0]);
            }
            catch (IOException iOException) {
                System.err.println("\u041e\u0448\u0438\u0431\u043a\u0430 \u043f\u0440\u0438 \u0441\u043e\u0437\u0434\u0430\u043d\u0438\u0438 \u043f\u0430\u043f\u043a\u0438: " + iOException.getMessage());
                return null;
            }
        }
        if (Files.notExists(path = Path.of(string + "charset.txt", new String[0]), new LinkOption[0])) {
            try {
                Files.createFile(path, new FileAttribute[0]);
                Files.write(path, "\"\\\" \u00a1\u2030\u00b7\u20b4\u2260\u00bf\u00d7\u00d8\u00f8\u0410\u0411\u0412\u0413\u0414\u0415\u0401\u0416\u0417\u0418\u0419\u041a\u041b\u041c\u041d\u041e\u041f\u0420\u0421\u0422\u0423\u0424\u0425\u0426\u0427\u0428\u0429\u042a\u042b\u042c\u042d\u042e\u042f\u0430\u0431\u0432\u0433\u0434\u0435\u0451\u0436\u0437\u0438\u0439\u043a\u043b\u043c\u043d\u043e\u043f\u0440\u0441\u0442\u0443\u0444\u0445\u0446\u0447\u0448\u0449\u044a\u044b\u044c\u044d\u044e\u044f\u0454\u2013\u2014\u2018\u2019\u201c\u201d\u201e\u2026\u2190\u2191\u2192\u2193\u02bb\u02cc\u037e\u2070\u00b9\u00b3\u2074\u2075\u2076\u2077\u2078\u2079\u207a\u207b\u207c\u207d\u207e\u2071\u2122\u0294\u0295\u00a4\u00a5\u00a9\u00ae\u00b5\u00b6\u00bc\u00bd\u00be\u0387\u2010\u201a\u2020\u2021\u2022\u2032\u2033\u2034\u2039\u203a\u203d\u2042\u2117\u2212\u221e\u0404\u2660\u2663\u2665\u2666\u266d\u266e\u266f\u2680\u2681\u2682\u2683\u2684\u2685\u02ac\u2744\u23cf\u23fb\u23fc\u23fd\u2b58\u25b2\u25b6\u25bc\u25c0\u25cf\u25e6\ufffd\u00a6\u1d00\u0299\u1d04\u1d05\u1d07\ua730\u0262\u029c\u1d0a\u1d0b\u029f\u1d0d\u0274\u1d0f\u1d18\ua7af\u0280\ua731\u1d1b\u1d1c\u1d20\u1d21\u028f\u1d22\u00a7\u02a1\u02a2\u0298\u01c0\u01c3\u01c2\u01c1\u2602\u2664\u2667\u2661\u2662\u2194\u2211\u25a1\u25b3\u25b7\u25bd\u25c1\u25cb\u2606\u2605\u2080\u2081\u2082\u2083\u2084\u2085\u2086\u2087\u2088\u2089\u208a\u208b\u208c\u208d\u208e\u222b\u2300\u2318\u26a0\u24ea\u2460\u2461\u2462\u2463\u2464\u2465\u2466\u2467\u2468\u2469\u246a\u246b\u246c\u246d\u246e\u246f\u2470\u2471\u2472\u2473\u24b6\u24b7\u24b8\u24b9\u24ba\u24bb\u24bc\u24bd\u24be\u24bf\u24c0\u24c1\u24c2\u24c3\u24c4\u24c5\u24c6\u24c7\u24c8\u24c9\u24ca\u24cb\u24cc\u24cd\u24ce\u24cf\u24d0\u24d1\u24d2\u24d3\u24d4\u24d5\u24d6\u24d7\u24d8\u24d9\u24da\u24db\u24dc\u24dd\u24de\u24df\u24e0\u24e1\u24e2\u24e3\u24e4\u24e5\u24e6\u24e7\u24e8\u24e9\u2611\u2612!#$%&'()*+,-./0123456789:;<=>[\\\\]^_`?@ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz{|}~\u00a3\u0192\u00aa\u00ba\u00ac\u00ab\u00bb\u2261\u00b1\u2265\u2264\u2320\u2321\u00f7\u2248\u00b0\u2219\u221a\u207f\u00b2\u25a0\"".getBytes(), new OpenOption[0]);
            }
            catch (IOException iOException) {
                System.err.println("\u041e\u0448\u0438\u0431\u043a\u0430 \u043f\u0440\u0438 \u0441\u043e\u0437\u0434\u0430\u043d\u0438\u0438 charset.txt: " + iOException.getMessage());
                return null;
            }
        }
        return path2;
    }
}

