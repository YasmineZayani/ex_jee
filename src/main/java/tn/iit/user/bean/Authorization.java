package tn.iit.user.bean;

import java.util.Calendar;
import java.util.Date;

public class Authorization {
    private int id;
    private User user;
    private Date currentDate;
    private Date authorizationDate;
    private int maximumHours;

    public Authorization(int id, User user, Date currentDate, Date authorizationDate, int maximumHours) {
        this.id = id;
        this.user = user;
        this.currentDate = currentDate;
        this.authorizationDate = authorizationDate;
        this.maximumHours = maximumHours;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public Date getAuthorizationDate() {
        return authorizationDate;
    }

    public int getMaximumHours() {
        return maximumHours;
    }

    public void setMaximumHours(int maximumHours) {
        this.maximumHours = maximumHours;
    }

    public static int calculateRemainingWeeks() {
        Calendar calendar = Calendar.getInstance();
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        int totalWeeks = calendar.getActualMaximum(Calendar.WEEK_OF_YEAR);
        int remainingWeeks = totalWeeks - currentWeek + 1; // Adding 1 to include the current week
        return remainingWeeks;
    }

    public static int calculateMaximumAuthorizedHours() {
        int remainingWeeks = calculateRemainingWeeks();
        int maximumHours = remainingWeeks * 4;
        return maximumHours;
    }
}
