package fr.letroll.TextWidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

public class Widget extends AppWidgetProvider {
	
	int couleur; //= 0x0204000e;
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {	
		Log.i("tuuuuuuuuuut","widget lancé");
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		Update(context, appWidgetManager, appWidgetIds);
	}
	
	public static void Update(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds){
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
		String text = settings.getString("text", "Hello");
		int couleur = settings.getInt("couleur", 0x0204000e);
		String taille = settings.getString("listPref", "petit");
		//Toast.makeText(context, ""+taille, Toast.LENGTH_SHORT).show();
		
		for(int i=0; i<appWidgetIds.length;i++){
			RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.main);
			
			views.setTextViewText(R.id.text, text);
			views.setTextColor(R.id.text, couleur);
			appWidgetManager.updateAppWidget(appWidgetIds[i],views);
		}
		
	}
}