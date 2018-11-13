package edu.cnm.deepdive.sharedprefsapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.data_button)
  Button dataButton;

  @BindView(R.id.data_out_text)
  TextView dataOutText;

  @BindView(R.id.data_input)
  EditText dataInput;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    dataInput.setText(savedInstanceState.getString(getString(R.string.bundle_string_key), ""));

    dataButton.setOnClickListener((view) -> {
      saveToSharedPrefs(dataInput.getText().toString());
      dataOutText.setText(getFromSharedPrefs());

    });
    dataOutText.setText(getFromSharedPrefs());
  }

  private void saveToSharedPrefs(String s){
    SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putString(getString(R.string.string_key), s);
    editor.putInt("FOO LENGTH", s.length());
    editor.apply();
  }

  private String getFromSharedPrefs(){
    SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
    return sharedPreferences.getString(getString(R.string.string_key), "");
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    Log.v("MainActivity", "Saving State");
    outState.putString(getString(R.string.bundle_string_key), dataInput.getText().toString());
    super.onSaveInstanceState(outState);
  }
}
