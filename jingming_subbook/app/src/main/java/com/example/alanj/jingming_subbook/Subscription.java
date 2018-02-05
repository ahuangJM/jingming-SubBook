package com.example.alanj.jingming_subbook;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * https://stackoverflow.com/questions/3500197/how-to-display-toast-in-android
 * Created by AlanJ on 2018-02-04.
 */

public class Subscription implements Parcelable {
    private String name;
    private String startedDate;
    private String monthlyCharges;
    private String comment;
    private Context context;
    public Subscription(Context context) {
        this.context = context;
    }

    /**
     * Parcelable
     * @param in
     */
    protected Subscription(Parcel in) {
        name = in.readString();
        startedDate = in.readString();
        monthlyCharges = in.readString();
        comment = in.readString();
    }

    /**
     * Parcelable
     */
    public static final Creator<Subscription> CREATOR = new Creator<Subscription>() {
        @Override
        public Subscription createFromParcel(Parcel in) {
            return new Subscription(in);
        }

        @Override
        public Subscription[] newArray(int size) {
            return new Subscription[size];
        }
    };

    /**
     * Parcelable
     * @return
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Parcelable
     * @param parcel
     * @param i
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(startedDate);
        parcel.writeString(monthlyCharges);
        parcel.writeString(comment);
    }

    /**
     *
     * @param name
     */
    public boolean setName(String name) {
        if (name.isEmpty() || name == null ) {
            Toast.makeText(this.context, "Name Must Not Be Empty!",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        if (name.length() > 20) {
            Toast.makeText(this.context, "Name Must Not Be Longer Than 20 Characters!",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        this.name = name;
        return true;
    }
    public String getName() {
        return this.name;
    }
    /**
     *
     * @param StartedDate
     */
    public boolean setStartedDate(Date StartedDate) {
        if (StartedDate == null) {
            Toast.makeText(this.context, "Date Cannot Be Null!",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        this.startedDate = String.valueOf(calendar.get(Calendar.YEAR)) + "-" +
                String.valueOf(calendar.get(Calendar.MONTH) + 1) + "-" +
                String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        return true;
    }

    /**
     *
     * @param StartedDate
     * @return
     */
    public boolean setStartedDate(String StartedDate) {
        if (StartedDate == null || StartedDate.isEmpty()) {
            Toast.makeText(this.context, "Date Cannot Be Null!",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        this.startedDate = StartedDate;
        return true;
    }
    /**
     *
     * @param year
     * @param month
     * @param day_of_month
     */
    public boolean setStartedDate(String year, String month, String day_of_month) {
        if (Integer.parseInt(month) > 12 || Integer.parseInt(month) < 1) {
            Toast.makeText(this.context, "Month Must Be In The Range Of 1 and 12 (Inclusive)!",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        if (Integer.parseInt(day_of_month) > 31 || Integer.parseInt(day_of_month) < 1) {
            Toast.makeText(this.context, "Day Of Month Must Be In The Range Of 1 and 31 (Inclusive)!",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        this.startedDate = String.valueOf(year + "-" + month + "-" + day_of_month);
        return true;
    }
    public String getStartedDate() {
        if (startedDate == null || startedDate.isEmpty()) {
            Date todayDate = Calendar.getInstance().getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            return formatter.format(todayDate);
        }
        return this.startedDate;
    }

    /**
     *
     * @param monthlyCharges
     * @return
     */
    public boolean setMonthlyCharges(String monthlyCharges) {
        if (monthlyCharges.isEmpty() || monthlyCharges == null ) {
            Toast.makeText(this.context, "Monthly Charges Must Not Be Empty!",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        if (Double.parseDouble(monthlyCharges) < 0) {
            Toast.makeText(this.context, "Monthly Charges Must Not Be Negative!",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        this.monthlyCharges = monthlyCharges;
        return true;
    }
    public String getMonthlyCharges() {
        return this.monthlyCharges;
    }

    /**
     *
     * @param comment
     * @return
     */
    public boolean setComment(String comment) {
        if (comment.isEmpty() || comment == null ) {
            Toast.makeText(this.context, "Comment Must Not Be Empty!",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        if (comment.length() > 30) {
            Toast.makeText(this.context, "Comment Must Not Be Negative!",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        this.comment = comment;
        return true;
    }
    public String getComment() {
        return this.comment;
    }
    /**
     *
     * @return
     */
    public String toStringForFile() {
        return this.name + "|" + this.startedDate + "|" + this.monthlyCharges + "|" + this.comment + "\n";
    }
    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return this.name + "\n" + this.startedDate + "\n" + this.monthlyCharges + "\n" + this.comment;
    }
}
