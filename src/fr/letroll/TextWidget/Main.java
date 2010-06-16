package fr.letroll.TextWidget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.util.Log;

public class Main extends PreferenceActivity implements ColorPickerDialog.OnColorChangedListener, OnPreferenceClickListener {
    /** Called when the activity is first created. */
	int mAppWidgetId,mCouleur;
	Context mContext;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("tuuuuuuuuuut","main lancé");
        addPreferencesFromResource(R.xml.preferences);
        Log.i("tuuuuuuuuuut","main lancé préf ajouté");
        mContext=this.getBaseContext();
        Preference couleur = (Preference)findPreference("couleur"); 
        couleur.setOnPreferenceClickListener(this);
        
        final ListPreference listPref = (ListPreference)findPreference("listPref");  
        listPref.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {  
            public boolean onPreferenceChange(Preference preference, Object newValue) {  
                int index = listPref.findIndexOfValue(newValue.toString());  
                if (index != -1) {  
                	//Toast.makeText(mContext, listPref.getEntries()[index], Toast.LENGTH_LONG).show();
                	SharedPreferences settings = getSharedPreferences("taille", 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("taille", (String) listPref.getEntries()[index]);
                    editor.commit();
                }  
                return true;  
            }  
        });  


        setResult(RESULT_CANCELED);
        
        Bundle extras = getIntent().getExtras();
        if(extras != null)
        	mAppWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,AppWidgetManager.INVALID_APPWIDGET_ID);
        
        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID)
        	finish();
        
        Intent resultvalue = new Intent();
        resultvalue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
        
        setResult(RESULT_OK,resultvalue);
    }
    
    @Override
    protected void onDestroy() {
    	Widget.Update(this, AppWidgetManager.getInstance(this),new int[]{mAppWidgetId});
    	super.onDestroy();
    }
    
	@Override
	public boolean onPreferenceClick(Preference preference) {
		if(preference.getKey().equals("couleur")){
		new ColorPickerDialog(this, this, mCouleur).show();}
		return true;
	}
    public void colorChanged(int color) {
	    PreferenceManager.getDefaultSharedPreferences(this).edit().putInt("couleur", color).commit();
	   	    
    }
}