package com.example.alanj.jingming_subbook;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import static java.lang.Integer.parseInt;

/**
 * https://stackoverflow.com/questions/45729852/android-check-if-back-button-was-pressed
 * https://stackoverflow.com/questions/6451837/how-do-i-set-the-current-date-in-a-datepicker
 * https://stackoverflow.com/questions/920306/sending-data-back-to-the-main-activity-in-android
 * Created by AlanJ on 2018-02-04.
 */
public class EditActivity extends AppCompatActivity {
    private EditText editName;
    private DatePicker datePicker;
    private EditText editMonthlyCharges;
    private EditText editComments;
    Subscription subscription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intent intent = getIntent();
        this.subscription = intent.getParcelableExtra("SUBSCRIPTION");

        this.editName = findViewById(R.id.editName);
        this.datePicker = findViewById(R.id.editStartedDate);
        this.editMonthlyCharges = findViewById(R.id.editMonthlyCost);
        this.editComments = findViewById(R.id.editComments);

        this.editName.setText(this.subscription.getName());
        String[] date = this.subscription.getStartedDate().split("-");
        this.datePicker.updateDate(parseInt(date[0]), parseInt(date[1]), parseInt(date[2]));
        this.editMonthlyCharges.setText(this.subscription.getMonthlyCharges());
        this.editComments.setText(this.subscription.getComment());
    }

    public void ConfirmButton_OnClick(View view) {
        Subscription subscription = new Subscription(this);
        if (!subscription.setName(editName.getText().toString())) {
            return;
        }
        if (!subscription.setStartedDate(String.valueOf(datePicker.getYear()), String.valueOf(datePicker.getMonth() + 1), String.valueOf(datePicker.getDayOfMonth()))) {
            return;
        }
        if (!subscription.setMonthlyCharges(editMonthlyCharges.getText().toString())) {
            return;
        }
        if (!subscription.setComment(editComments.getText().toString())) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("SUBSCRIPTION", subscription);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("SUBSCRIPTION", this.subscription);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
