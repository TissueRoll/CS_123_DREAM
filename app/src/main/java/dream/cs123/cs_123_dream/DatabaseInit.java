package dream.cs123.cs_123_dream;

import android.app.Application;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import dream.cs123.cs_123_dream.models.Migration;

public class DatabaseInit extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name("dream.realm")
                //.schemaVersion(0)
                //.migration(new Migration())
                .deleteRealmIfMigrationNeeded()
                .build();
        //Realm.deleteRealm(realmConfig);
        Realm.setDefaultConfiguration(realmConfig);
    }
}
