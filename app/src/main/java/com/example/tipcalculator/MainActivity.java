/*
* Srinivas Bharadwaj Chintalapati
* */

package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bill Label & Input Fields
        TextView billTotal;
        billTotal = findViewById(R.id.billTotalLabel);
        EditText billInput;
        billInput = findViewById(R.id.bill_Input);
        // Tip Label & Input Fields
        TextView tipLabel;
        tipLabel = findViewById(R.id.tipLabel);
        RadioGroup getPercentGrp;
        getPercentGrp = findViewById(R.id.TipbtnGrp);
        RadioButton radioBtn10;
        radioBtn10 = findViewById(R.id.radioButton10percent);
        RadioButton radioBtn15;
        radioBtn15 = findViewById(R.id.radioButton15percent);
        RadioButton radioBtn18;
        radioBtn18 = findViewById(R.id.radioButton18percent);
        RadioButton radioBtnCustom;
        radioBtnCustom = findViewById(R.id.radioButtoncustom);
        // Seekbar Label & Input fields
        TextView customLabel;
        customLabel = findViewById(R.id.CustomLabel);
        SeekBar customSeekbar;
        customSeekbar = findViewById(R.id.seekBarCustom);
        TextView seekbarProgressRes;
        seekbarProgressRes = findViewById(R.id.SeekProgressResult);
        // Tip Label & Selection Result
        TextView tipDetailsLabel;
        tipDetailsLabel = findViewById(R.id.TipDetailsLabel);
        TextView tipResultLabel;
        tipResultLabel = findViewById(R.id.TipSelectedResult);
        // Total Label & Result
        TextView totalLabel;
        totalLabel = findViewById(R.id.TotalObtainedLabel);
        TextView totalResult;
        totalResult = findViewById(R.id.TotalObtainedResult);
        // Split By Section
        TextView splitByLabel;
        splitByLabel = findViewById(R.id.SplitByLabel);
        RadioGroup splitByGrp;
        splitByGrp = findViewById(R.id.SplitRadioGroup);
        RadioButton radioBtnOne;
        radioBtnOne = findViewById(R.id.radioButtonOne);
        RadioButton radioBtnTwo;
        radioBtnTwo = findViewById(R.id.radioButtonTwo);
        RadioButton radioBtnThree;
        radioBtnThree = findViewById(R.id.radioButtonThree);
        RadioButton radioBtnFour;
        radioBtnFour = findViewById(R.id.radioButtonFour);
        // Final Section
        TextView finalTotalLabel;
        finalTotalLabel = findViewById(R.id.TotalPerPersonLabel);
        TextView finalTotalResult;
        finalTotalResult = findViewById(R.id.TotalPerPersonResult);
        // Button
        Button clearBtn;
        clearBtn = findViewById(R.id.buttonClear);



        try {
            // Set the defaults
            radioBtn10.setChecked(true);
            radioBtnOne.setChecked(true);

            billInput.addTextChangedListener(new TextWatcher() {
                String radioButtonValue;
                double result = 0.00;
                double total;
                double condRes;
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if(billInput.getText().equals("")) {
                        result = 0.00;
                    }
                    int selectedId = getPercentGrp.getCheckedRadioButtonId();
                    RadioButton radioButton = (RadioButton) findViewById(selectedId);
                    radioButtonValue = radioButton.getText().toString().split("%")[0];

                    getPercentGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup radioGroup, int i) {
                            int selectedId = getPercentGrp.getCheckedRadioButtonId();
                            RadioButton radioButton = (RadioButton) findViewById(selectedId);
                            boolean isChecked = true;
                            if(isChecked) {
                                radioButtonValue = radioButton.getText().toString().split("%")[0];
                                if(radioButtonValue.equals("Custom")) {
                                    radioButtonValue = String.valueOf(customSeekbar.getProgress());
                                    customSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                        @Override
                                        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                                            seekbarProgressRes.setText(String.valueOf(i) + "%");
                                            radioButtonValue = String.valueOf(seekBar.getProgress());
                                            try {
                                                result = Double.parseDouble(billInput.getText().toString()) * (Double.parseDouble(radioButtonValue) / 100);
                                                total = Integer.parseInt(String.valueOf(billInput.getText())) + result;
                                            } catch(NumberFormatException e) {
                                                result = 0.00;
                                                total = 0.00;
                                                finalTotalResult.setText("$" + total);
                                            }
                                            int defaultSelectedId = splitByGrp.getCheckedRadioButtonId();
                                            RadioButton selectedBtn = (RadioButton) findViewById(defaultSelectedId);
                                            if(selectedBtn.getText().equals("One")) {
                                                finalTotalResult.setText("$" + String.valueOf(total));
                                            }else if(selectedBtn.getText().equals("Two")) {
                                                finalTotalResult.setText("$" + String.valueOf(total / 2));
                                            }else if(selectedBtn.getText().equals("Three")) {
                                                finalTotalResult.setText("$" + String.valueOf(total / 3));
                                            }else if(selectedBtn.getText().equals("Four")) {
                                                finalTotalResult.setText("$" + String.valueOf(total / 4));
                                            }
                                            splitByGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                                @Override
                                                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                                                    int selectedId = splitByGrp.getCheckedRadioButtonId();
                                                    RadioButton selectedBtn = (RadioButton) findViewById(selectedId);
                                                    if(selectedBtn.getText().equals("One")) {
                                                        finalTotalResult.setText("$" + total);
                                                    }else if(selectedBtn.getText().equals("Two")) {
                                                        finalTotalResult.setText("$" + (total / 2));
                                                    }else if(selectedBtn.getText().equals("Three")) {
                                                        finalTotalResult.setText("$" + (total / 3));
                                                    }else if(selectedBtn.getText().equals("Four")) {
                                                        finalTotalResult.setText("$" + (total / 4));
                                                    }
                                                }
                                            });
                                            tipResultLabel.setText("$" + Math.round(result));
                                            totalResult.setText("$" + total);
                                        }

                                        @Override
                                        public void onStartTrackingTouch(SeekBar seekBar) {

                                        }

                                        @Override
                                        public void onStopTrackingTouch(SeekBar seekBar) {

                                        }
                                    });

                                }
                                try {
                                    result = Double.parseDouble(billInput.getText().toString()) * (Double.parseDouble(radioButtonValue) / 100);
                                    total = Integer.parseInt(String.valueOf(billInput.getText())) + result;
                                    condRes = total;
                                } catch(NumberFormatException e) {
                                    result = 0.00;
                                    total = 0.00;
                                    finalTotalResult.setText("$" + total);
                                }
                                int defaultSelectedId = splitByGrp.getCheckedRadioButtonId();
                                RadioButton selectedBtn = (RadioButton) findViewById(defaultSelectedId);

                                if(selectedBtn.getText().equals("One")) {
                                    finalTotalResult.setText("$" + String.valueOf(total));
                                }else if(selectedBtn.getText().equals("Two")) {
                                    condRes = total / 2;
                                    finalTotalResult.setText("$" + condRes);
                                }else if(selectedBtn.getText().equals("Three")) {
                                    condRes = total / 3;
                                    finalTotalResult.setText("$" + condRes);
                                }else if(selectedBtn.getText().equals("Four")) {
                                    condRes = total / 4;
                                    finalTotalResult.setText("$" + condRes);
                                }

                                splitByGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                    @Override
                                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                                        int selectedId = splitByGrp.getCheckedRadioButtonId();
                                        RadioButton selectedBtn = (RadioButton) findViewById(selectedId);
                                        if(selectedBtn.getText().equals("One")) {
                                            finalTotalResult.setText("$" + total);
                                        }else if(selectedBtn.getText().equals("Two")) {
                                            condRes = total / 2;
                                            finalTotalResult.setText("$" + condRes);
                                        }else if(selectedBtn.getText().equals("Three")) {
                                            condRes = total / 3;
                                            finalTotalResult.setText("$" + condRes);
                                        }else if(selectedBtn.getText().equals("Four")) {
                                            condRes = total / 4;
                                            finalTotalResult.setText("$" + condRes);
                                        }
                                    }
                                });
                                tipResultLabel.setText("$" + result);
                                totalResult.setText("$" + total);
                                finalTotalResult.setText("$" + condRes);
                            }
                        }
                    });

                    splitByGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup radioGroup, int i) {
                            int selectedId = splitByGrp.getCheckedRadioButtonId();
                            RadioButton selectedBtn = (RadioButton) findViewById(selectedId);
                            if(selectedBtn.getText().equals("One")) {
                                finalTotalResult.setText("$" + total);
                            }else if(selectedBtn.getText().equals("Two")) {
                                finalTotalResult.setText("$" + (total / 2));
                            }else if(selectedBtn.getText().equals("Three")) {
                                finalTotalResult.setText("$" + (total / 3));
                            }else if(selectedBtn.getText().equals("Four")) {
                                finalTotalResult.setText("$" + (total / 4));
                            }
                        }
                    });
                    try {
                        result = Double.parseDouble(billInput.getText().toString()) * (Double.parseDouble(radioButtonValue) / 100);
                        total = Integer.parseInt(String.valueOf(billInput.getText())) + result;
                    } catch(NumberFormatException e) {
                        result = 0.00;
                        total = 0.00;
                        finalTotalResult.setText("$" + total);
                    }
                    tipResultLabel.setText("$" + result);
                    totalResult.setText("$" + total);
                    finalTotalResult.setText("$" + total);
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            customSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    seekbarProgressRes.setText(String.valueOf(i) + "%");
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

            clearBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        billInput.setText(null);
                    }catch (NumberFormatException e) {
                        billInput.getText().clear();
                    }
                    radioBtn10.setChecked(true);
                    radioBtnOne.setChecked(true);
                    tipResultLabel.setText("$0.00");
                    totalResult.setText("$0.00");
                    finalTotalResult.setText("$0.00");
                    seekbarProgressRes.setText("40%");
                    customSeekbar.setProgress(40);

                }
            });
        }catch (NumberFormatException e) {
            Toast.makeText(getBaseContext(), "Please select valid inputs", Toast.LENGTH_SHORT).show();
        }
    }
}