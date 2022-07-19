package com.dicoding.jlegends.setmodes

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.dicoding.jlegends.R
import com.google.android.material.switchmaterial.SwitchMaterial

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class ThemesActivity :  AppCompatActivity() {

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_theme)

        supportActionBar?.title = "Set Mode"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val switchTheme = findViewById<SwitchMaterial>(R.id.switchMode)
        val pref = SettingPreferences.getInstance(dataStore)
        val ThemesViewModel = ViewModelProvider(this, ViewModelFactory(pref)).get(
            ThemesViewModel::class.java
        )
        ThemesViewModel.getThemeSettings().observe(this,
            { isDarkModeActive: Boolean ->
                if (isDarkModeActive) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    switchTheme.isChecked = true
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    switchTheme.isChecked = false
                }
            })

        switchTheme.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            ThemesViewModel.saveThemeSetting(isChecked)
        }
     }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}


