/*
 * Decompiled with CFR 0.152.
 */
import java.util.Arrays;
import net.minecraft.client.main.Main;

public class Start {
    public static void main(String[] stringArray) {
        String string = System.getenv().containsKey("assetDirectory") ? System.getenv("assetDirectory") : "assets";
        Main.main(Start.a(new String[]{"--version", "mcp", "--accessToken", "0", "--assetsDir", string, "--assetIndex", "1.16", "--userProperties", "{}"}, stringArray));
    }

    public static <T> T[] a(T[] TArray, T[] TArray2) {
        T[] TArray3 = Arrays.copyOf(TArray, TArray.length + TArray2.length);
        System.arraycopy(TArray2, 0, TArray3, TArray.length, TArray2.length);
        return TArray3;
    }
}

