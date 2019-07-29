import java.util.prefs.Preferences;

import org.junit.Test;

public class Starter {
    private static Preferences store = Preferences.userRoot().node("com.aleksandrovich.informationprotector");

    @Test
    public void removeLicense() {
        store.put("license", "");
    }

    @Test
    public void getSystemProperties(){
        System.out.println(System.getProperties());
    }
}
