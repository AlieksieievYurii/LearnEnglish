package Alert.test.setting;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.yurii.alieksieiev.learenglish.R;


public class AlertTestSetting
{
    private Context context;
    private AlertDialog alertDialog;
    private EditText edtCount;
    private ToggleButton engOrRus;
    private int sizeSeekBar;

    public AlertTestSetting(Context context)
    {
        this.context = context;
    }

    public void show(int sizeSeekBar)
    {
        this.sizeSeekBar = sizeSeekBar;

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View alertView = inflater.inflate(R.layout.alert_test_setting,null);
        edtCount = alertView.findViewById(R.id.edt_count);
        SeekBar seekBarCount = alertView.findViewById(R.id.seek_bar_count_words);
        engOrRus = alertView.findViewById(R.id.toggleButton);
        Button btnStart = alertView.findViewById(R.id.alert_setting_btn_start);

        btnStart.setOnClickListener(new ButtonListener());

        seekBarCount.setMax(sizeSeekBar);
        seekBarCount.setOnSeekBarChangeListener(new SeekBarListener(edtCount));

        builder.setView(alertView);
        alertDialog = builder.create();
        alertDialog.show();
    }

    private void startTest(ITestSetting testSetting,int toCountWords,boolean angToRus)
    {
        testSetting.startTest(toCountWords,angToRus);
    }

    private boolean getChoise()
    {
        return engOrRus.getText().toString()
                .equals(context.getResources().getString(R.string.toggle_button_end_to_rus));
    }

    private class ButtonListener implements View.OnClickListener
    {

        @Override
        public void onClick(View v)
        {
            if(edtCount.getText().toString().isEmpty() || Integer.parseInt(edtCount.getText().toString()) > sizeSeekBar)
            {
                Toast.makeText(context,"Wrong limit!",Toast.LENGTH_SHORT).show();
                return;
            }

            startTest(
                    (ITestSetting) context,
                    Integer.parseInt(edtCount.getText().toString()),
                    getChoise());

            alertDialog.hide();
        }
    }

    private class SeekBarListener implements SeekBar.OnSeekBarChangeListener
    {
        private EditText edtCountWord;

        private SeekBarListener(EditText edtCountWord)
        {
            this.edtCountWord = edtCountWord;
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            edtCountWord.setText(String.valueOf(progress));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }
}
