/*
 * Decompiled with CFR 0.152.
 */
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipFile;
import org.fusesource.jansi.a;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class Builder {
    private static final List<String> a = List.of("Builder.class", "FontGenerator.class", "module-info.class");
    private static final List<String> b = List.of("lombok/", "generated/");
    private static final String a = org.fusesource.jansi.a.e().a(0, 255, 0).toString();
    private static final String b = org.fusesource.jansi.a.e().a(255, 0, 0).toString();
    private static final String c = org.fusesource.jansi.a.e().a(255, 255, 255).toString();
    private final String d;
    private final String e;

    public Builder(String string, String string2) {
        this.d = string;
        this.e = string2;
    }

    public static void main(String[] stringArray) {
        if (stringArray.length == 0) {
            System.err.println("Usage: java -jar builder.jar in.jar");
            return;
        }
        String string = stringArray[0];
        String string2 = "temp.jar";
        Builder builder = new Builder(string, string2);
        System.out.println(a + "\u041f\u0440\u043e\u0446\u0435\u0441\u0441 \u0441\u0431\u043e\u0440\u043a\u0438 \u043d\u0430\u0447\u0430\u043b\u0441\u044f!");
        builder.cleanup();
        builder.a();
        System.out.println(a + "\u0421\u0431\u043e\u0440\u043a\u0430 \u0437\u0430\u0432\u0435\u0440\u0448\u0435\u043d\u0430!");
    }

    private void cleanup() {
        this.a(this.e);
    }

    private void a(String string) {
        Path path = Paths.get(string, new String[0]);
        try {
            if (Files.exists(path, new LinkOption[0])) {
                Files.delete(path);
                System.out.println(b + "\u0423\u0434\u0430\u043b\u0435\u043d \u0444\u0430\u0439\u043b: " + string);
            }
        }
        catch (IOException iOException) {
            System.err.println(b + "\u041e\u0448\u0438\u0431\u043a\u0430 \u043f\u0440\u0438 \u0443\u0434\u0430\u043b\u0435\u043d\u0438\u0438 \u0444\u0430\u0439\u043b\u0430: " + string);
        }
    }

    public void a() {
        Object object;
        try {
            object = new JarFile(this.d);
            try (JarOutputStream jarOutputStream = new JarOutputStream(new FileOutputStream(this.e));){
                Enumeration<JarEntry> enumeration = ((JarFile)object).entries();
                int n2 = ((ZipFile)object).size();
                int n3 = 0;
                while (enumeration.hasMoreElements()) {
                    JarEntry jarEntry = enumeration.nextElement();
                    if (!jarEntry.isDirectory() && this.a(jarEntry)) {
                        this.a((JarFile)object, jarEntry, jarOutputStream);
                    }
                    this.a(++n3, n2);
                }
                System.out.println();
            }
            finally {
                ((ZipFile)object).close();
            }
        }
        catch (IOException iOException) {
            System.err.println(b + "\u041e\u0448\u0438\u0431\u043a\u0430 \u043f\u0440\u0438 \u0441\u0431\u043e\u0440\u043a\u0435 " + this.d);
        }
        try {
            object = Paths.get(this.d, new String[0]);
            Files.deleteIfExists((Path)object);
            Files.move(Paths.get(this.e, new String[0]), (Path)object, StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException iOException) {
            System.err.println(b + "\u041e\u0448\u0438\u0431\u043a\u0430 \u043f\u0440\u0438 \u0443\u0434\u0430\u043b\u0435\u043d\u0438\u0438 \u0438\u043b\u0438 \u043f\u0435\u0440\u0435\u0437\u0430\u043f\u0438\u0441\u0438 " + this.d);
        }
    }

    private boolean a(JarEntry jarEntry) {
        return !this.b(jarEntry) && !this.c(jarEntry);
    }

    private void a(double d2, int n2) {
        double d3 = d2 / (double)n2 * 100.0;
        int n3 = 20;
        int n4 = (int)((double)n3 * d3 / 100.0);
        StringBuilder stringBuilder = new StringBuilder("[");
        for (int i2 = 0; i2 < n3; ++i2) {
            stringBuilder.append(i2 < n4 ? a + "|" : b + ".");
        }
        stringBuilder.append(c).append("] ").append((int)d3).append("%");
        System.out.printf("\r%s", stringBuilder);
    }

    private void a(JarFile jarFile, JarEntry jarEntry, JarOutputStream jarOutputStream) {
        try (InputStream inputStream = jarFile.getInputStream(jarEntry);){
            int n2;
            byte[] byArray = new byte[16384];
            JarEntry jarEntry2 = new JarEntry(jarEntry.getName());
            jarOutputStream.putNextEntry(jarEntry2);
            while ((n2 = inputStream.read(byArray)) != -1) {
                jarOutputStream.write(byArray, 0, n2);
            }
        }
    }

    private boolean b(JarEntry jarEntry) {
        String string = jarEntry.getName().toLowerCase();
        return b.stream().anyMatch(string::startsWith);
    }

    private boolean c(JarEntry jarEntry) {
        return a.contains(jarEntry.getName());
    }
}

