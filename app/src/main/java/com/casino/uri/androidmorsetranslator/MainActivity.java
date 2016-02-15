package com.casino.uri.androidmorsetranslator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

    Settings settings;
    EditText translate;
    TextView result;
    TextView text;
    TextView morse;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("TEXT to MORSE");
        setSupportActionBar(toolbar);
        settings = (Settings) getApplication();
        translate = (EditText) this.findViewById(R.id.ETtranslate);
        result = (TextView) this.findViewById(R.id.TVresult);
        text = (TextView) this.findViewById(R.id.TVtext);
        morse = (TextView) this.findViewById(R.id.TVmorse);
        translate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (settings.getValue().equals("TEXT_TO_MORSE"))
                {
                    result.setText(textToMorse(translate.getText().toString()));
                }
                else if (settings.getValue().equals("MORSE_TO_TEXT"))
                {
                    result.setText(morseToText(translate.getText().toString()));
                }
            }
        });
    }

    public String textToMorse(String toTranslate)
    {
        String toReturn = "";
        try
        {
            for (int x=0; x<toTranslate.length(); x++)
            {
                toReturn = toReturn + encode(String.valueOf(toTranslate.charAt(x)))+" ";
            }
            return toReturn;
        }
        catch(Exception lenght0)
        {
            return "";
        }

    }
    public String decode(String morse)
    {
        switch (morse)
        {
            case "-----":{return "0";}
            case ".----":{return "1";}
            case "..---":{return "2";}
            case "...--":{return "3";}
            case "....-":{return "4";}
            case ".....":{return "5";}
            case "-....":{return "6";}
            case "--...":{return "7";}
            case "---..":{return "8";}
            case "----.":{return "9";}
            case ".-":{return "a";}
            case "-...":{return "b";}
            case "-.-.":{return "c";}
            case "-..":{return "d";}
            case ".":{return "e";}
            case "..-.":{return "f";}
            case "--.":{return "g";}
            case "....":{return "h";}
            case "..":{return "i";}
            case ".---":{return "j";}
            case "-.-":{return "k";}
            case ".-..":{return "l";}
            case "--":{return "m";}
            case "-.":{return "n";}
            case "---":{return "o";}
            case ".--.":{return "p";}
            case "--.-":{return "q";}
            case ".-.":{return "r";}
            case "...":{return "s";}
            case "-":{return "t";}
            case "..-":{return "u";}
            case "...-":{return "v";}
            case ".--":{return "w";}
            case "-..-":{return "x";}
            case "-.--":{return "y";}
            case "--..":{return "z";}
            default: {return " ";}
        }
    }

    public String encode(String character)
    {
        switch (character.toUpperCase())
        {
            case "0":{return "-----";}
            case "1":{return ".----";}
            case "2":{return "..---";}
            case "3":{return "...--";}
            case "4":{return "....-";}
            case "5":{return ".....";}
            case "6":{return "-....";}
            case "7":{return "--...";}
            case "8":{return "---..";}
            case "9":{return "----.";}
            case "A":{return ".-";}
            case "B":{return "-...";}
            case "C":{return "-.-.";}
            case "D":{return "-..";}
            case "E":{return ".";}
            case "F":{return "..-.";}
            case "G":{return "--.";}
            case "H":{return "....";}
            case "I":{return "..";}
            case "J":{return ".---";}
            case "K":{return "-.-";}
            case "L":{return ".-..";}
            case "M":{return "--";}
            case "N":{return "-.";}
            case "O":{return "---";}
            case "P":{return ".--.";}
            case "Q":{return "--.-";}
            case "R":{return ".-.";}
            case "S":{return "...";}
            case "T":{return "-";}
            case "U":{return "..-";}
            case "V":{return "...-";}
            case "W":{return ".--";}
            case "X":{return "-..-";}
            case "Y":{return "-.--";}
            case "Z":{return "--..";}
            default: {return " ";}
        }
    }

    public String morseToText(String morseCode)
    {
        String toReturn = "";
        String[] codes = morseCode.split(" ");
        for (int x=0; x<codes.length; x++)
        {
            toReturn=toReturn+decode(codes[x]);
        }
        return toReturn;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_change)
        {
            if (settings.getValue().equals("MORSE_TO_TEXT"))
            {
                setValues();
                settings.setValue("TEXT_TO_MORSE");
                toolbar.setTitle("TEXT to MORSE");
                text.setText("TEXT");
                morse.setText("MORSE");
            }
            else if(settings.getValue().equals("TEXT_TO_MORSE"))
            {
                setValues();
                settings.setValue("MORSE_TO_TEXT");
                toolbar.setTitle("MORSE to TEXT");
                text.setText("MORSE");
                morse.setText("TEXT");
            }
            return true;
        }
        if (id == R.id.action_info)
        {
            showInformation();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void showInformation()
    {
        new AlertDialog.Builder(this)
                .setTitle("    INFORMATION")
                .setMessage("This application translates MORSE to TEXT & TEXT to MORSE.\n\nIf you enter invalid MORSE code, nothing will be shown.\n\nThe text of the translation is selectable, so you can copy & paste it.\n\nBy  uRi  ;)")
                .setPositiveButton("CLOSE", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {}
                })
                .setIcon(R.drawable.ic_information)
                .show();
    }
    public void setValues()
    {
        translate.setText("");
        translate.setHint("What to translate?");
        result.setText("");
        result.setHint("Result");
    }
}
