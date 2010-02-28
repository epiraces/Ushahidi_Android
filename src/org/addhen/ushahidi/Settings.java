package org.addhen.ushahidi;


import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.widget.ArrayAdapter;

public class Settings extends PreferenceActivity {
	private EditTextPreference ushahidiInstancePref;
	private EditTextPreference firstNamePref;
	private EditTextPreference lastNamePref;
	private EditTextPreference emailAddressPref;
	private CheckBoxPreference autoFetchCheckBoxPref;
	private CheckBoxPreference clearCacheCheckBoxPref;
	private ListPreference autoUpdateTimePref;
	private ListPreference saveItemsPref;
	private ListPreference totalReportsPref;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		ushahidiInstancePref = new EditTextPreference(this);
		firstNamePref = new EditTextPreference(this);
		lastNamePref = new EditTextPreference(this);
		emailAddressPref = new EditTextPreference(this);
		autoFetchCheckBoxPref = new CheckBoxPreference(this);
		clearCacheCheckBoxPref = new CheckBoxPreference(this);
		autoUpdateTimePref = new ListPreference(this);
		saveItemsPref = new ListPreference(this);
		totalReportsPref = new ListPreference(this);
		
		setPreferenceScreen(createPreferenceHierarchy());
		this.saveSettings();
	}
	
	private PreferenceScreen createPreferenceHierarchy() {
		//ROOT element
		PreferenceScreen root = getPreferenceManager().createPreferenceScreen(this);
		
		//Basic preferences
		PreferenceCategory basicPrefCat = new PreferenceCategory(this);
		basicPrefCat.setTitle(R.string.basic_settings);
		root.addPreference(basicPrefCat);
		
		//URL entry field
		ushahidiInstancePref.setDialogTitle(R.string.txt_domain);
		ushahidiInstancePref.setKey("ushahidi_instance_preference");
		ushahidiInstancePref.setTitle(R.string.txt_domain);
		ushahidiInstancePref.setDefaultValue("http://");
		ushahidiInstancePref.setSummary(R.string.hint_domain);
		basicPrefCat.addPreference(ushahidiInstancePref);
		
		//First name entry field
		firstNamePref.setDialogTitle(R.string.txt_first_name);
		firstNamePref.setKey("first_name_preference");
		firstNamePref.setTitle(R.string.txt_first_name);
		firstNamePref.setSummary(R.string.hint_first_name);
		basicPrefCat.addPreference(firstNamePref);
		
		//Last name entry field
		lastNamePref.setDialogTitle(R.string.txt_last_name);
		lastNamePref.setKey("last_name_preference");
		lastNamePref.setTitle(R.string.txt_last_name);
		lastNamePref.setSummary(R.string.hint_last_name);
		basicPrefCat.addPreference(lastNamePref);
		
		//Email name entry field
		emailAddressPref.setDialogTitle(R.string.txt_email);
		emailAddressPref.setKey("email_address_preference");
		emailAddressPref.setTitle(R.string.txt_email);
		emailAddressPref.setSummary(R.string.hint_email);
		basicPrefCat.addPreference(emailAddressPref);
		
		//Advanced Preferences
		PreferenceCategory advancedPrefCat = new PreferenceCategory(this);
		advancedPrefCat.setTitle(R.string.advanced_settings);
		root.addPreference(advancedPrefCat);
		
		PreferenceScreen advancedScreenPref = getPreferenceManager().createPreferenceScreen(this);
		advancedScreenPref.setKey("advanced_screen_preference");
		advancedScreenPref.setTitle(R.string.advanced_settings);
		advancedScreenPref.setSummary(R.string.hint_advanced_settings);
	    advancedPrefCat.addPreference(advancedScreenPref);
	    
	    //Auto fetch reports
        autoFetchCheckBoxPref.setKey("auto_fetch_preference");
        autoFetchCheckBoxPref.setTitle(R.string.chk_auto_fetch);
        autoFetchCheckBoxPref.setSummary(R.string.hint_auto_fetch);
        advancedScreenPref.addPreference(autoFetchCheckBoxPref);
		
        // Auto update reports time interval
        //set list values
        CharSequence[] autoUpdateEntries = {"Off", "5 Minutes", "10 Minutes", "15 Minutes", "30 Minutes", "60 Minutes"}; 
        CharSequence[] autoUpdateValues = {"0","5","10","15","30","60"};
        autoUpdateTimePref.setEntries(autoUpdateEntries);
        autoUpdateTimePref.setEntryValues(autoUpdateValues);
        autoUpdateTimePref.setDefaultValue(autoUpdateValues[0]);
        autoUpdateTimePref.setDialogTitle(R.string.txt_auto_update_delay);
        autoUpdateTimePref.setKey("auto_update_time_preference");
        autoUpdateTimePref.setTitle(R.string.txt_auto_update_delay);
        autoUpdateTimePref.setSummary(R.string.hint_auto_update_delay);
        advancedScreenPref.addPreference(autoUpdateTimePref);
        
        //location of storage
        //set list values
        CharSequence[] saveItemsEntries = {"On Phone", "On SD Card"}; 
        CharSequence[] saveItemsValues = {"phone","card"};
        
        saveItemsPref.setEntries(saveItemsEntries);
        saveItemsPref.setEntryValues(saveItemsValues);
        saveItemsPref.setDefaultValue(saveItemsValues[0]);
        saveItemsPref.setDialogTitle(R.string.option_location);
        saveItemsPref.setKey("save_items_preference");
        saveItemsPref.setTitle(R.string.option_location);
        saveItemsPref.setSummary(R.string.hint_option_location);
        advancedScreenPref.addPreference(saveItemsPref);
        
        //Total reports to fetch at a time
        //set list values
        CharSequence[] totalReportsEntries = {"20 Recent Reports", "40 Recent Reports", "60 Recent Reports", "80 Recent Reports", "100 Recent Reports"}; 
        CharSequence[] totalReportsValues = {"20","40","60","80","100"};
        
        totalReportsPref.setEntries(totalReportsEntries);
        totalReportsPref.setEntryValues(totalReportsValues);
        totalReportsPref.setDefaultValue(totalReportsValues[0]);
        totalReportsPref.setDialogTitle(R.string.total_reports);
        totalReportsPref.setKey("total_reports_preference");
        totalReportsPref.setTitle(R.string.total_reports);
        totalReportsPref.setSummary(R.string.hint_total_reports);
        advancedScreenPref.addPreference(totalReportsPref);
        
        //clear cache
        clearCacheCheckBoxPref.setKey("clear_cache_preference");
        clearCacheCheckBoxPref.setTitle(R.string.txt_clear_cache);
        clearCacheCheckBoxPref.setSummary(R.string.hint_clear_cache);
        advancedScreenPref.addPreference(clearCacheCheckBoxPref);
        
		return root;
	}
	
	protected void saveSettings(){
		UshahidiService.domain = ushahidiInstancePref.getText().toString();
		UshahidiService.firstname = firstNamePref.getText();
		UshahidiService.lastname = lastNamePref.getText();
		UshahidiService.email = emailAddressPref.getText();
		UshahidiService.AutoFetch = autoFetchCheckBoxPref.isChecked();
		String autoUpdate = autoUpdateTimePref.getValue();
		String saveItems = saveItemsPref.getValue();
		String totalReports = totalReportsPref.getValue();
		
		//"5 Minutes", "10 Minutes", "15 Minutes", "c", "60 Minutes" 
		if(autoUpdate.matches("Off")){
			UshahidiService.AutoUpdateDelay = 0;
		} else if(autoUpdate.matches("5")){
			UshahidiService.AutoUpdateDelay = 5;
		} else if(autoUpdate.matches("10")){
			UshahidiService.AutoUpdateDelay = 10;
		} else if(autoUpdate.matches("15")){
			UshahidiService.AutoUpdateDelay = 15;
		} else if(autoUpdate.matches("30")){
			UshahidiService.AutoUpdateDelay = 30;
		} else if(autoUpdate.matches("60")){
			UshahidiService.AutoUpdateDelay = 60;
		}
		String newSavePath = "";
		if( saveItems.equalsIgnoreCase("phone")){
			newSavePath = "/data/data/org.addhen.ushahidi/files/";
		
		} else {	//means on sd is checked
			
			newSavePath = "/sdcard" + "ushahidi";
		}
		UshahidiService.savePath = newSavePath;
		
		//Total reports
		UshahidiService.totalReports = totalReports;
		
		UshahidiService.saveSettings(this);
	}
	
	/**
	 * Clear stored data
	 */
	private void clearCache() {
		//TODO write necessary code to achieve that.
	}
}
