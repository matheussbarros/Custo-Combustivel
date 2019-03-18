package br.com.custocombustivel;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import java.text.NumberFormat;
import android.util.Log;


public class MainActivity extends AppCompatActivity {


    private TextView gasolinaTextView;
    private TextView precoGasolinaTextView;
    private SeekBar gasolinaSeekBar;
    private TextView etanolTextView;
    private TextView precoEtanolTextView;
    private SeekBar etanolSeekBar;
    private TextInputLayout resultTextInputEdit;
    private TextInputEditText resultInputEditText;
    private ImageView resultImageView;
    private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private NumberFormat  percentFormat = NumberFormat.getPercentInstance();

    private double precoEtanol = 1.00;
    private double precogasolina = 1.00;
    private double calculo = 1;

    private class SeekBarChangeListner implements SeekBar.OnSeekBarChangeListener{
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            double value = progress / 10.0;
            switch (seekBar.getId()) {

                case R.id.gasolinaSeekBar:
                    //Altera o preço do gasolina conforme SeekBar
                    precogasolina = value;
                    precoGasolinaTextView.setText(currencyFormat.format(precogasolina));
                    break;

                case R.id.etanolSeekBar:
                    //Altera o preço do Etanol conforme SeekBar
                    precoEtanol = value;
                    precoEtanolTextView.setText(currencyFormat.format(precoEtanol));
                    break;
            }

            Log.v("", "preço etanol: " + precoEtanol);
            Log.v("", "preço gasolina: " + precogasolina);

            calculo = precoEtanol / precogasolina;

            if(calculo >= 0.7){
                resultInputEditText.setText(R.string.gasolina);
                resultImageView.setImageResource(R.drawable.gasolina);
            }else{
                resultInputEditText.setText(R.string.etanol);
                resultImageView.setImageResource(R.drawable.etanol);
            }


            Log.v("", "calculo: " + calculo);


        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }


    private TextWatcher amountEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

//            try {
//                resultInputEditText.setText("Gasolina");
//                resultInputEditText.setText("Etanol");
//
//            }catch(NullPointerException e){
//                resultInputEditText.setText("..");
//                resultInputEditText.setText("..");
//            }


        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gasolinaTextView = findViewById(R.id.gasolinaTextView);
        precoGasolinaTextView = findViewById(R.id.precoGasolinaTextView);
        gasolinaSeekBar = findViewById(R.id.gasolinaSeekBar);
        etanolTextView = findViewById(R.id.etanolTextView);
        precoEtanolTextView = findViewById(R.id.precoEtanolTextView);
        etanolSeekBar = findViewById(R.id.etanolSeekBar);
        resultInputEditText = findViewById(R.id.resultInputEditText);
        resultImageView = findViewById(R.id.resultImageView);


        precoEtanolTextView.setText(currencyFormat.format(precoEtanol));
        precoGasolinaTextView.setText(currencyFormat.format(precogasolina));

        etanolSeekBar.setOnSeekBarChangeListener(new SeekBarChangeListner());
        gasolinaSeekBar.setOnSeekBarChangeListener(new SeekBarChangeListner());


    }
}
