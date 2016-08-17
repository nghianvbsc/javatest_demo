package vinaedu.javatest.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import vinaedu.javatest.model.objects.TopScore;

/**
 * Created by bscenter on 11/08/2016
 */
public class SharedPreference {
    private static final String NAME_PREFS = "JAVA-TEST";
    private SharedPreferences prefs;
    public static SharedPreference pref;

    public SharedPreference(Context context) {
        prefs = context.getSharedPreferences(NAME_PREFS, Context.MODE_PRIVATE);
    }

    public static SharedPreference getInstance(Context context) {
        if (pref == null) {
            pref = new SharedPreference(context);
        }
        return pref;
    }

    public void setPlayed() {
        SharedPreferences.Editor e = prefs.edit();
        e.putBoolean(Constances.IS_PLAYED, true);
        e.apply();
    }

    public boolean isPlayed() {
        return prefs.getBoolean(Constances.IS_PLAYED, false);
    }

    public int getBestScore() {
        return prefs.getInt(Constances.BEST_SCORE, 0);
    }

    public void setBestScore(int score) {
        SharedPreferences.Editor e = prefs.edit();
        e.putInt(Constances.BEST_SCORE, score);
        e.apply();
    }

    public void setTopScore(TopScore topScore) {
        List<TopScore> topScores = new ArrayList<>();
        String json = prefs.getString(Constances.TOP_SCORE, null);
        if (json != null) {
            Type type = new TypeToken<List<TopScore>>() {
            }.getType();
            List<TopScore> topScoresGet = new Gson().fromJson(json, type);
            topScores.addAll(topScoresGet);
            topScores.add(0, topScore);
            if (topScores.size() > 5) {
                topScores.remove(5);
            }
        } else {
            topScores.add(topScore);
        }

        String jsonSave = new Gson().toJson(topScores);
        SharedPreferences.Editor e = prefs.edit();
        e.putString(Constances.TOP_SCORE, jsonSave);
        e.apply();

    }

    public List<TopScore> getTopScore() {
        String json = prefs.getString(Constances.TOP_SCORE, null);
        Type type = new TypeToken<List<TopScore>>() {
        }.getType();
        return new Gson().fromJson(json, type);
    }

}
