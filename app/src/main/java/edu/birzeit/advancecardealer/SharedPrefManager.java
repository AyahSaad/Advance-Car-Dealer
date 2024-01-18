package edu.birzeit.advancecardealer;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
        private static final String SHARED_PREF_NAME = "My Shared Preference";
        private static final int SHARED_PREF_PRIVATE = Context.MODE_PRIVATE;
        private static SharedPrefManager ourInstance = null;
        private static SharedPreferences sharedPreferences = null;
        private SharedPreferences.Editor editor = null;
        static SharedPrefManager getInstance(Context context) {
            if (ourInstance != null) {
                return ourInstance;
            }
            ourInstance=new SharedPrefManager(context);
            return ourInstance;
        }
        private SharedPrefManager(Context context) {
            sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, SHARED_PREF_PRIVATE);
            editor = sharedPreferences.edit();
        }
        public boolean writeString(String key, String value) {
            editor.putString(key, value);
            return editor.commit();
        }
        public String readString(String key, String defaultValue) {
            return sharedPreferences.getString(key, defaultValue);
        }
         //  method to remove a specific entry from SharedPreferences
        public void removeEntry(String key) {
            editor.remove(key);
            editor.apply();
        }

       //  method to clear all entries from SharedPreferences
        public void clearAllEntries() {
            editor.clear();
            editor.apply();
       }

    public boolean putBoolean(String key, boolean b) {
            editor.putBoolean(key,b);
            return editor.commit();
    }
    public Boolean readBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

}


