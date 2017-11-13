package com.github.nopparatMkw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {
    static Logger logger = LoggerFactory.getLogger(DateUtil.class);
    public static final String TH = "th_TH";
    public static final String EN = "en_US";

    public static final Locale thLocale = new Locale("th", "TH");
    public static final Locale enLocale = new Locale("en", "US");

    protected final static long ONEDAY = 10 * 10 * 10 * 24 * 60 * 60;
    static long w_oneDay = (long) 1000.0 * 60 * 60 * 24;
    // public static final Locale LOCALE_TH = new Locale("th", "TH");
    public static final Locale LOCALE_TH = new Locale("TH", "TH");
    public static final Locale LOCALE_US = new Locale("US", "US");
    public static final SimpleDateFormat yearThFormated = new SimpleDateFormat("yyyy", LOCALE_TH);
    public static final SimpleDateFormat yearEnFormated = new SimpleDateFormat("yyyy", Locale.US);
    public static final SimpleDateFormat monthEnFormated = new SimpleDateFormat("MM", Locale.US);

    public static final String DEFAULT_DATE_FORMATED = "dd/MM/yyyy";
    public static final String DEFAULT_DATE_FORMATED_TH = "dd MMMMM yyyy";
    public static final String DEFAULT_DATE_FORMATED_EN = "DDMMYYYY";
    public static final String DEFAULT_TIME_FORMATED = "HH:mm:ss";
    public static final String DEFAULT_MINUTE_TIME_FORMATED = "HH:mm";
    public static final String DEFAULT_DATE_TIME_FORMATED = "dd/MM/yyyy HH:mm";
    public static final String DEFAULT_FULL_DATE_TIME_FORMATED = "dd/MM/yyyy HH:mm:ss";
    public static final String DEFAULT_FULL_MMDDYYYYHHMMSS = "MM/dd/yyyy HH:mm:ss";
    public static final String DEFAULT_YEAR_MONTH_FORMATED = "yyyyMM";
    public static final String DEFAULT_XML_DATE_FORMATED = "yyyyMMdd";
    public static final String DEFAULT_DATETIME_FORMATED = DEFAULT_DATE_FORMATED + " " + DEFAULT_TIME_FORMATED;
    public static final String DEFAULT_DATE_THAI_FORMATED = "dd MMMM yyyy";
    public static final String DEFAULT_DATE_SEARCH_FORMATED = "yyyy-MM-dd";
    public static final String DEFAULT_DATE_SEARCH_BPM_FORMATED = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_FULL_DATE_FORMATED = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String DEFAULT_NO_DATE_FORMATED = "MM/yyyy";
    public static final String DEFAULT_DATE_REPORT_FORMATED_EN = "MMMM dd, yyyy";
    public static final String dd_MM_yyyy = "dd-MM-yyyy";

    public static Timestamp addMinute(String startTime, String pattern, int minute) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(convertStringToDate(startTime, pattern));
        calendar.add(Calendar.MINUTE, minute);

        return new Timestamp(calendar.getTimeInMillis());
    }

    public static Date getTime(Object theDateObj) {
        java.sql.Time w_TempDate;
        if (theDateObj == null) {
            w_TempDate = null;
        } else {
            Date theDateDate = (Date) theDateObj;
            System.out.print(theDateDate);
            w_TempDate = new java.sql.Time(theDateDate.getTime());
        }

        return w_TempDate;

    }

    @SuppressWarnings("deprecation")
    public static int getYearBudget(Date d) {
        int year = d.getYear();
        if (!(d.getDate() < 30 && d.getMonth() <= 8)) {
            year = year + 1;
        }

        return year + 2443;
    }

    @SuppressWarnings("unused")
    public static Date getDateTime(Object theDateObj) {
        java.sql.Time w_TempDate;
        Date theDateDate = null;
        if (theDateObj == null) {
            w_TempDate = null;
        } else {
            theDateDate = (Date) theDateObj;
            System.out.print(theDateDate);
        }

        return theDateDate;

    }

    /**
     * Convert from java.lang.String to java.util.Date.
     *
     * @param theDateStr java.util.Date
     * @return java.lang.String
     */
    public static Date getDate(String theDateStr) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMATED, LOCALE_US);
            return dateFormat.parse(theDateStr);
        } catch (ParseException ex) {
            return null;
        }
    }

    public static Date getDateEn(String theDateStr) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMATED, LOCALE_US);
            return dateFormat.parse(theDateStr);
        } catch (ParseException ex) {
            return null;
        }
    }

    public static Date getDateTH(String theDateStr) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMATED, LOCALE_TH);
            return dateFormat.parse(theDateStr);
        } catch (ParseException ex) {
            return null;
        }
    }

    public static Date getDateEN(Date theDate) {
        try {
            if (theDate != null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", LOCALE_US);
                String dateStr = dateFormat.format(theDate);
                Date dateResult = dateFormat.parse(dateStr);
                return dateResult;
            } else {
                return null;
            }
        } catch (ParseException ex) {
            return null;
        }
    }

    public static Date getDateDefaultEN(Date theDate) {
        try {
            if (theDate != null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMATED, LOCALE_US);
                String dateStr = dateFormat.format(theDate);
                Date dateResult = dateFormat.parse(dateStr);
                return dateResult;
            } else {
                return null;
            }
        } catch (ParseException ex) {
            return null;
        }
    }

    public static Date getDateTH(Date theDate) {
        try {
            if (theDate != null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", new Locale("TH", "TH"));
                String dateStr = dateFormat.format(theDate);
                Date dateResult = dateFormat.parse(dateStr);
                return dateResult;
            } else {
                return null;
            }
        } catch (ParseException ex) {
            return null;
        }
    }

    /**
     * Convert from java.lang.String to java.util.Calendar.
     *
     * @param theDateStr java.util.Date
     * @return java.util.Calendar
     */
    public static Calendar getCalendar(String theDateStr) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMATED, LOCALE_TH);
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateFormat.parse(theDateStr));
            return cal;
        } catch (ParseException ex) {
            return null;
        }
    }

    /**
     * Convert from java.lang.String to java.util.Calendar.
     *
     * @param sourceDate java.util.Date
     * @return java.util.Calendar
     */
    public static Calendar getCalendar(Object sourceDate) {
        try {
            // SimpleDateFormat dateFormat = new
            // SimpleDateFormat(DEFAULT_DATE_FORMATED, LOCALE_TH);
            if (sourceDate != null) {
                Calendar cal = Calendar.getInstance();

                if (sourceDate instanceof java.sql.Date) {
                    cal.setTime((java.sql.Date) sourceDate);
                } else if (sourceDate instanceof Date) {
                    cal.setTime((Date) sourceDate);
                } else if (sourceDate instanceof Timestamp) {
                    cal.setTime((Timestamp) sourceDate);
                } else if (sourceDate instanceof Calendar) {
                    cal = (Calendar) sourceDate;
                }
                return cal;
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    public static GregorianCalendar getGregorianCalendar(Object sourceDate) {
        try {
            // SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy",
            // new Locale("TH", "TH"));
            if (sourceDate != null) {
                GregorianCalendar cal = new GregorianCalendar(new Locale("TH", "TH"));

                if (sourceDate instanceof java.sql.Date) {
                    cal.setTime((java.sql.Date) sourceDate);
                } else if (sourceDate instanceof Date) {
                    cal.setTime((Date) sourceDate);
                } else if (sourceDate instanceof Timestamp) {
                    cal.setTime((Timestamp) sourceDate);
                } else if (sourceDate instanceof Calendar) {
                    cal.setTimeInMillis(((Calendar) sourceDate).getTimeInMillis());
                }
                return cal;
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    public static Date getDate(Object sourceDate) {
        try {
            return getCalendar(sourceDate) == null ? null : getCalendar(sourceDate).getTime();
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Convert from java.lang.String to java.util.Date.
     *
     * @param theDateStr java.util.Date
     * @return java.lang.String
     */
    public static Date getDate(String theDateStr, Locale local, String format) {
        try {
            if (theDateStr != null && !"".equals(theDateStr)) {
                SimpleDateFormat dateFormat = new SimpleDateFormat(format, local);
                return dateFormat.parse(theDateStr);
            } else {
                return null;
            }
        } catch (ParseException ex) {
            return null;
        }
    }

    public static Date addDate(Date theDate, boolean add, int amount) throws Exception {
        if (theDate == null) {
            throw new Exception("theDate is null");
        }
        GregorianCalendar calendar = new GregorianCalendar(LOCALE_TH);
        calendar.setTime(theDate);
        if (add) {
            calendar.add(GregorianCalendar.DATE, amount);
        } else {
            calendar.add(GregorianCalendar.DATE, (-1) * amount);
        }

        return calendar.getTime();
    }

    public static Date addYear(Date theDate, boolean add, int amount) throws Exception {
        if (theDate == null) {
            throw new Exception("theDate is null");
        }
        GregorianCalendar calendar = new GregorianCalendar(LOCALE_TH);
        calendar.setTime(theDate);
        if (add) {
            calendar.add(GregorianCalendar.YEAR, amount);
        } else {
            calendar.add(GregorianCalendar.YEAR, (-1) * amount);
        }

        return calendar.getTime();
    }

    public static Date addMonth(Date theDate, boolean add, int amount) throws Exception {
        if (theDate == null) {
            throw new Exception("theDate is null");
        }
        GregorianCalendar calendar = new GregorianCalendar(LOCALE_TH);
        calendar.setTime(theDate);
        if (add) {
            calendar.add(GregorianCalendar.MONTH, amount);
        } else {
            calendar.add(GregorianCalendar.MONTH, (-1) * amount);
        }

        return calendar.getTime();
    }

    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); // minus number would decrement the days
        return cal.getTime();
    }

    public static int diffDate(Date d1, Date d2) throws Exception {
        int diffDate = 0;
        long diffLong = 0;
        if (d1 != null && d2 != null) {
            GregorianCalendar x1 = new GregorianCalendar(LOCALE_TH);
            x1.setTime(d1);
            GregorianCalendar x2 = new GregorianCalendar(LOCALE_TH);
            x2.setTime(d2);

            long dLong1 = x1.getTime().getTime();// x1.UTC(x1.get(x1.YEAR),x1.get(x1.MONTH),x1.get(x1.DATE),x1.get(x1.HOUR_OF_DAY),x1.get(x1.MINUTE),x1.get(x1.SECOND));
            long dLong2 = x2.getTime().getTime();// x2.UTC(x2.get(x1.YEAR),x2.get(x1.MONTH),x2.get(x1.DATE),x2.get(x1.HOUR_OF_DAY),x2.get(x1.MINUTE),x2.get(x1.SECOND));

            if (dLong1 > dLong2) {
                diffLong = dLong1 - dLong2;
            } else {
                diffLong = dLong2 - dLong1;
            }

            diffLong = diffLong / ONEDAY;
            diffDate = (int) diffLong;

        } else {
            throw new Exception("D1 or D2 is null");
        }

        return diffDate;
    }

    public static int diffDateSpecial(Date d1, Date d2) throws Exception {
        int diffDate = 0;
        long diffLong = 0;
        if (d1 != null && d2 != null) {
            GregorianCalendar x1 = new GregorianCalendar(LOCALE_TH);
            x1.setTime(d1);
            GregorianCalendar x2 = new GregorianCalendar(LOCALE_TH);
            x2.setTime(d2);

            long dLong1 = x1.getTime().getTime();// x1.UTC(x1.get(x1.YEAR),x1.get(x1.MONTH),x1.get(x1.DATE),x1.get(x1.HOUR_OF_DAY),x1.get(x1.MINUTE),x1.get(x1.SECOND));
            long dLong2 = x2.getTime().getTime();// x2.UTC(x2.get(x1.YEAR),x2.get(x1.MONTH),x2.get(x1.DATE),x2.get(x1.HOUR_OF_DAY),x2.get(x1.MINUTE),x2.get(x1.SECOND));
            //
            // if (dLong1 > dLong2) {
            // diffLong = dLong1 - dLong2;
            // } else {
            diffLong = dLong2 - dLong1;
            // }

            diffLong = diffLong / ONEDAY;
            diffDate = (int) diffLong;

        } else {
            throw new Exception("D1 or D2 is null");
        }

        return diffDate;
    }

    public static int getYear(Date d) {
        GregorianCalendar temp = new GregorianCalendar(LOCALE_TH);
        temp.setTime(d);
        return temp.get(GregorianCalendar.YEAR);
    }

    public static int getYearEn(Date d) {
        GregorianCalendar temp = new GregorianCalendar(LOCALE_US);
        temp.setTime(d);
        return temp.get(GregorianCalendar.YEAR);
    }

    /**
     * Insert the method's description here.
     *
     * @param d java.util.Date
     * @return int
     */
    public static int getMonth(Date d) {
        GregorianCalendar temp = new GregorianCalendar(LOCALE_TH);
        temp.setTime(d);
        int v_month = temp.get(GregorianCalendar.MONTH) + 1;
        return v_month;
    }

    public static int getDayInMonth(Date d) {
        GregorianCalendar temp = new GregorianCalendar(LOCALE_TH);
        temp.setTime(d);
        int v_date = temp.get(GregorianCalendar.DAY_OF_MONTH);
        return v_date;
    }

    public static java.sql.Date getYesterday(java.sql.Date theDate) {
        return diffTomorrowYesterday(theDate, true);
    }

    public static java.sql.Date getTomorrow(java.sql.Date theDate) {
        return diffTomorrowYesterday(theDate, false);
    }

    private static java.sql.Date diffTomorrowYesterday(java.sql.Date theDate, boolean theYesterday) {
        java.sql.Date w_tmpDate = null;

        if (theYesterday) {
            w_tmpDate = new java.sql.Date(theDate.getTime() - w_oneDay);
        } else {
            w_tmpDate = new java.sql.Date(theDate.getTime() + w_oneDay);
        }

        return w_tmpDate;
    }

    public static Date getCurrentDate() {
        return new GregorianCalendar(LOCALE_TH).getTime();
    }

    public static Date getCurrentDate(Locale locale) {

        return new GregorianCalendar(locale).getTime();
    }

    /**
     * @param days number of days
     * @return previously date revers {days}
     */
    public static Date getPrevious(int days) {
        GregorianCalendar cale = new GregorianCalendar(LOCALE_TH);
        cale.add(Calendar.DATE, -days);
        return cale.getTime();
    }

    public static Date getPreviousDate(int days) {
        GregorianCalendar cale = new GregorianCalendar(LOCALE_TH);
        cale.add(Calendar.DATE, -days);
        return cale.getTime();
    }

    public static Timestamp getCurrentDateTime() {
        return new Timestamp(new GregorianCalendar(LOCALE_TH).getTime().getTime());
    }

    public static Calendar getCurrentCalendar() {
        return new GregorianCalendar(LOCALE_TH);
    }

    public static int getCurrentYearTh() {
        Date date = new Date();
        return new Integer(yearThFormated.format(date));
    }

    public static int getCurrentYearEn() {
        Date date = new Date();
        return new Integer(yearEnFormated.format(date));
    }

    public static int getCurrentMonthEn() {
        Date date = new Date();
        return new Integer(monthEnFormated.format(date));
    }

    public static List<Integer> getListAllYear(int currentYear) {

        int beforCurYear = 0;
        int afterCurYear = 0;

        List<Integer> listYear = new ArrayList<Integer>();

        try {
            if (currentYear > 0) {

                beforCurYear = currentYear - 5;
                afterCurYear = currentYear + 10;

                for (int i = beforCurYear; i <= afterCurYear; i++) {
                    listYear.add(i);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listYear;
    }

    public static int dateDiffInHours(Date toDate, Date fromDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(toDate);
        long ms = cal.getTimeInMillis();
        cal.setTime(fromDate);
        ms -= cal.getTimeInMillis();
        final long msPerHour = 1000L * 60L * 60L;
        int hours = (int) (ms / msPerHour);
        return hours;
    }

    public static int dateDiffInMinute(Date toDate, Date fromDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(toDate);
        long ms = cal.getTimeInMillis();
        cal.setTime(fromDate);
        ms -= cal.getTimeInMillis();
        final long msPerHour = 1000L * 60L;
        int hours = (int) (ms / msPerHour);
        return hours;
    }

    public static int dateDiffInSecond(Date toDate, Date fromDate) {
        if (toDate == null || fromDate == null) return 0;
        Calendar cal = Calendar.getInstance();
        cal.setTime(toDate);
        long ms = cal.getTimeInMillis();
        cal.setTime(fromDate);
        ms -= cal.getTimeInMillis();
        final long msPerSecond = 1000L;
        int hours = (int) (ms / msPerSecond);
        return hours;
    }

    //
    // public static Date convertToBE(Date theDate){
    // String w_strDate = null;
    // int w_year_input = 0;
    // try{
    // if(theDate != null){
    // w_strDate = StringFormat.getLocaleDateNoTime(theDate);
    // w_year_input = NumberFormat.getInt(w_strDate.substring(6));
    // if(w_year_input!=0){
    // w_strDate =
    // w_strDate.substring(0,6)+StringFormat.getString((w_year_input-543));
    // theDate = getDate(w_strDate);
    // }
    // }
    //
    // }catch(Exception e){
    // e.getMessage();
    // }
    // return theDate;
    // }
    // public static Date convertToAD(Date theDate){
    // String w_strDate = null;
    // int w_year_input = 0;
    // try{
    // if(theDate != null){
    // w_strDate = StringFormat.getLocaleDateNoTime(theDate);
    // w_year_input = NumberFormat.getInt(w_strDate.substring(6));
    // if(w_year_input!=0){
    // w_strDate =
    // w_strDate.substring(0,6)+StringFormat.getString((w_year_input+543));
    // theDate = getDate(w_strDate);
    // }
    // }
    //
    // }catch(Exception e){
    // e.getMessage();
    // }
    // return theDate;
    // }
    public static String converUtilDateToSqlDate(Date utilDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sqlDate = sdf.format(utilDate);
        return sqlDate;
    }

    public static long diffMinuteCalculate(Date startDate, Date endDate) throws Exception {
        long endL = endDate.getTime();
        long startL = startDate.getTime();
        return ((endL - startL) / 1000 / 60) % 60;
    }

    public static long diffHoursCalculate(Date startDate, Date endDate) throws Exception {
        long endL = endDate.getTime();
        long startL = startDate.getTime();
        return ((endL - startL) / 1000L / 60L / 60L) % 24;
    }

    @SuppressWarnings("deprecation")
    public static long diffMinuteCalculateDate(Date startDate, Date endDate) throws Exception {
        long endL = endDate.getDate();
        long startL = startDate.getDate();
        return endL - startL;
    }

    /**
     * @param a
     * @throws Exception
     */
    public static void main(String[] a) throws Exception {

        // System.out.println(">>>> "+getListAllYear(0));
        // SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd", enLocale);
        // Date startDate = format.parse("20131109");
        // Date endDate = format.parse("20140110");
        System.out.println("xxx");
        System.out.println(getStrDateByFormat(new Date(), "ddMMyyyy"));

        // String x = totalDayTime("28/12/2013 00:00", "31/12/2013 00:00");
        // Date d = convertStringToDate("20131109", DEFAULT_XML_DATE_FORMATED);
        // System.out.print(StringFormat.getDateString(d, enLocale,
        // DEFAULT_DATE_FORMATED));
        // if("20131109" == null) return null;
        // SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd", enLocale);
        // Date rtnDate = format.parse("20131109");
        // System.out.print(rtnDate);
        // Date startDate = dateFormat.parse("29/12/2013 14:59");
        // Date endDate = dateFormat.parse("31/12/2013 18:15");
        // Double duration = totalMinTime("20/12/2013 08:00",
        // "21/12/2013 10:30");
        // System.out.println(duration);
        // System.out.println(duration/60);
        // System.out.println((duration/60)/24);
        // System.out.println((duration/60)%24);
        // System.out.println(duration%60);
        // System.out.println(duration+duration%60);
        System.out.println("==================");
        // long durationHour = totalHourTime("20/12/2013 08:00",
        // "20/12/2013 10:35");
        // System.out.println(durationHour);
        // long diffDay = duration/24;
        // System.out.println(diffDay);
        // long diffHour = duration%24;
        // System.out.println(diffHour);
        // System.out.println(startDate);
        // System.out.println(endDate);
        // long day = diffMinuteCalculateDate(startDate, endDate);
        // System.out.println(day);
        // long hour = diffHoursCalculate(startDate, endDate);
        // System.out.println(hour);
        // long min = diffMinuteCalculate(startDate, endDate);
        // System.out.println(min);
        //
        // System.out.println((day*24)+hour+(min%60));

        // compareToDateWithoutTime(new Date(), getCurrentBudgetDate());
        // XMLGregorianCalendar xmlcal = new XMLGregorianCalendar();
        // Date d = xmlcal.toGregorianCalendar().getTime();
        // Date dt = new Date();
        // System.out.println(dt);
        // System.out.print(diffMinuteCalculateDate(getPreviousDate(6),
        // getCurrentDate()));
        //
        // Interval interval1 = new Interval(c1.getTimeInMillis(),
        // c2.getTimeInMillis());
        // Interval interval2 = new Interval(c3.getTimeInMillis(),
        // c4.getTimeInMillis());
        // Interval interval3 = new Interval(c5.getTimeInMillis(),
        // c6.getTimeInMillis());
        // Interval interval4 = new Interval(c7.getTimeInMillis(),
        // c8.getTimeInMillis());
        //
        // List<Interval> listIntervals = new ArrayList<Interval>();
        // listIntervals.add(interval3);
        // listIntervals.add(interval2);
        // listIntervals.add(interval1);
        // listIntervals.add(interval4);
        //
        //
        // if (listIntervals != null && !listIntervals.isEmpty()) {
        //
        // List<Interval> tmpListIntervals = new ArrayList<Interval>();
        //
        // for (Interval interval : listIntervals) {
        //
        // if (tmpListIntervals != null && !tmpListIntervals.isEmpty()) {
        //
        // boolean isOverLap = false;
        //
        // for (Interval tmpInterval : tmpListIntervals) {
        //
        // isOverLap = tmpInterval.overlaps(interval);
        //
        // if (isOverLap) {
        // System.out.println("isOverlap : " + interval.getEnd() + " " +
        // interval.getEnd());
        // break;
        // }
        // }
        //
        // if (isOverLap) {
        // break;
        //
        // } else {
        // tmpListIntervals.add(interval);
        // }
        //
        // } else {
        // tmpListIntervals.add(interval);
        // }
        // }
        // }
        // SimpleDateFormat dateFormat2 = new
        // SimpleDateFormat(DEFAULT_DATE_FORMATED, LOCALE_TH);
        // System.out.println(dateFormat2.format(getCurrentBudgetDate()));
        // System.out.println(dateFormat2.format(getCurrentFinishBudgetDate()));
        // create original ArrayList object
        // List<TrainReqExpInfoBO> aListOriginal = new
        // ArrayList<TrainReqExpInfoBO>();
        // TrainReqExpInfoBO t1 = new TrainReqExpInfoBO();
        // ExpensesBO ex1 = new ExpensesBO();
        // ex1.setAmount(100.0);
        // t1.setExpenses(ex1);
        // aListOriginal.add(t1);
        //
        // TrainReqExpInfoBO cloneT1 =
        // (TrainReqExpInfoBO)SerializationUtils.clone(t1);
        // cloneT1.getExpenses().setAmount(50.0);
        // //to clone ArrayList use clone method
        // ArrayList<TrainReqExpInfoBO> aListCloned = new
        // ArrayList<TrainReqExpInfoBO>();
        // aListCloned = (ArrayList<TrainReqExpInfoBO>)aListOriginal.clone();
        // Change the object in original ArrayList
        // System.out.println("Original ArrayList " +
        // t1.getExpenses().getAmount());
        // System.out.println("Cloned ArrayList " +
        // cloneT1.getExpenses().getAmount());
        // // List<Date> listData = new ArrayList<Date>();
        // SimpleDateFormat dateFormat = new
        // SimpleDateFormat(DEFAULT_DATE_FORMATED, LOCALE_TH);
        // Date startDate = dateFormat.parse("09/06/2557");
        //
        // Date resultDate = addDate(startDate, false, 1);
        // System.out.println(resultDate);
        // // listData.add(startDate);
        // startDate = dateFormat.parse("20/12/2556");
        // listData.add(startDate);
        // startDate = dateFormat.parse("21/12/2556");
        // listData.add(startDate);
        // startDate = dateFormat.parse("25/12/2556");
        // listData.add(startDate);
        // startDate = dateFormat.parse("23/12/2556");
        // listData.add(startDate);
        // System.out.println(listData.size());
        // HashSet hs = new HashSet();
        // hs.addAll(listData);
        // listData.clear();
        // listData.addAll(hs);
        //
        // Collections.sort(listData);
        // for(Date d:listData){
        // System.out.println(GESSDateUtil.toStringThaiDateTime(d,
        // GESSDateUtil.DEFAULT_DATE_FORMATED));
        // }
        // System.out.println(listData.size());
    }

    /*
     * String Thai Date Format with pattern dd/MM/yyyy hh:mm:ss
     */
    public static String toStringThaiDateTimeSimpleFormat(Date date) throws Exception {

        String dateStr = null;
        SimpleDateFormat thFormat = new SimpleDateFormat(DEFAULT_DATE_FORMATED, thLocale);
        if (date != null) {
            dateStr = thFormat.format(date);
        }
        return dateStr;
    }

    /*
     * String Eng Date Format with pattern dd/MM/yyyy
     */
    public static String toStringEngDateSimpleFormat(Date date) throws Exception {

        String dateStr = null;
        SimpleDateFormat engFormat = new SimpleDateFormat(DEFAULT_DATE_FORMATED, enLocale);
        if (date != null) {
            dateStr = engFormat.format(date);
        }
        return dateStr;
    }

    public static String toStringEngDateSearchFormat(Date date) throws Exception {
        SimpleDateFormat engFormat = new SimpleDateFormat(DEFAULT_DATE_SEARCH_FORMATED, enLocale);
        return engFormat.format(date);
    }

    public static String toStringEngDateSearchBPMFormat(Date date) throws Exception {
        SimpleDateFormat engFormat = new SimpleDateFormat(DEFAULT_DATE_SEARCH_BPM_FORMATED, enLocale);
        return engFormat.format(date);
    }

    public static String toStringThaiDateTime(Date date, String format) {
        String dateStr = null;

        if (date != null && format != null && !format.equals("")) {
            try {
                SimpleDateFormat thFormat = new SimpleDateFormat(format, thLocale);
                dateStr = thFormat.format(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dateStr;
    }

    public static String toStringEngDateTime(Date date, String format) {
        String dateStr = null;

        if (date != null && format != null && !format.equals("")) {
            try {
                SimpleDateFormat thFormat = new SimpleDateFormat(format, enLocale);
                dateStr = thFormat.format(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dateStr;
    }

    public static String toStringTodayDateTime(Date date, String format) {
        String dateStr = null;

        if (date != null && format != null && !format.equals("")) {
            try {

                if (diffDate(date, new Date()) == 0) {
                    SimpleDateFormat thFormat = new SimpleDateFormat(DEFAULT_MINUTE_TIME_FORMATED, enLocale);
                    dateStr = "Today " + thFormat.format(date);
                } else {
                    SimpleDateFormat thFormat = new SimpleDateFormat(format, enLocale);
                    dateStr = thFormat.format(date);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dateStr;
    }

    public static String toStringDateTime(Date date, String format, Locale locale) {
        String dateStr = "";

        if (date != null && format != null && !format.equals("")) {
            try {
                SimpleDateFormat thFormat = new SimpleDateFormat(format, locale);
                dateStr = thFormat.format(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dateStr;
    }

    public static String toStringEngTimestampSearchGessFormat(Date date) throws Exception {
        SimpleDateFormat engFormat = new SimpleDateFormat(DEFAULT_FULL_DATE_TIME_FORMATED, enLocale);
        return engFormat.format(date);
    }

    public static String toStringThaiTimestampSearchGessFormat(Date date) throws Exception {
        SimpleDateFormat engFormat = new SimpleDateFormat(DEFAULT_FULL_DATE_TIME_FORMATED, thLocale);
        return engFormat.format(date);
    }

    // FUNCTION CONVERSE FROM FORMAT "ENG Local" TO FORMAT "THAI Local"
    public static String converseLocalDateText(String dateLeadStr) {

        // Supot edit 2005-10-21
        String returnDate = null;
        DateFormat formatter1 = new SimpleDateFormat(DEFAULT_FULL_DATE_TIME_FORMATED, enLocale);
        DateFormat formatter2 = new SimpleDateFormat(DEFAULT_FULL_DATE_TIME_FORMATED, thLocale);

        if (dateLeadStr != null && !dateLeadStr.equals("")) {
            try {
                Date dateParse = formatter1.parse(dateLeadStr);
                returnDate = formatter2.format(dateParse);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return returnDate;
    }

    // FUNCTION CONVERSE FROM FORMAT "DD/MM/YYYY ENG Local" TO FORMAT
    // "THAI Local"
    public static String converseLocalDateToTimeText(String dateLeadStr) {

        // Supot edit 2005-10-21
        String returnDate = null;
        DateFormat formatter1 = new SimpleDateFormat(DEFAULT_DATE_FORMATED, enLocale);
        DateFormat formatter2 = new SimpleDateFormat(DEFAULT_DATE_FORMATED, thLocale);

        if (dateLeadStr != null && !dateLeadStr.equals("")) {
            try {
                Date dateParse = formatter1.parse(dateLeadStr);
                returnDate = formatter2.format(dateParse);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return returnDate;
    }

    public static int daysBetween(Date d1, Date d2) {
        final long ONE_HOUR = 60 * 60 * 1000L;
        return (int) ((d2.getTime() - d1.getTime() + ONE_HOUR) / (ONE_HOUR * 24));
    }

    public static String getDay(int day) {
        String dayStr = "";
        switch (day) {
            case 1:
                dayStr = "SUNDAY";
                break;
            case 2:
                dayStr = "MONDAY";
                break;
            case 3:
                dayStr = "TUESDAY";
                break;
            case 4:
                dayStr = "WEDNESDAY";
                break;
            case 5:
                dayStr = "THURSDAY";
                break;
            case 6:
                dayStr = "FRIDAY";
                break;
            case 7:
                dayStr = "SATURDAY";
                break;
            default:
                dayStr = "";
                break;
        }
        return dayStr;
    }

    public static String getDayLocale(int day, String locale) {
        String dayStr = "";
        if (locale.equals("en")) {
            switch (day) {
                case 1:
                    dayStr = "SUNDAY";
                    break;
                case 2:
                    dayStr = "MONDAY";
                    break;
                case 3:
                    dayStr = "TUESDAY";
                    break;
                case 4:
                    dayStr = "WEDNESDAY";
                    break;
                case 5:
                    dayStr = "THURSDAY";
                    break;
                case 6:
                    dayStr = "FRIDAY";
                    break;
                case 7:
                    dayStr = "SATURDAY";
                    break;
                default:
                    dayStr = "";
                    break;
            }
        } else {
            switch (day) {
                case 1:
                    dayStr = "อาทิตย์";
                    break;
                case 2:
                    dayStr = "จันทร์";
                    break;
                case 3:
                    dayStr = "อังคาร";
                    break;
                case 4:
                    dayStr = "พุธ";
                    break;
                case 5:
                    dayStr = "พฤหัส";
                    break;
                case 6:
                    dayStr = "ศุกร์";
                    break;
                case 7:
                    dayStr = "เสาร์";
                    break;
                default:
                    dayStr = "";
                    break;
            }
        }

        return dayStr;
    }

    public static boolean isDateBetween(Date fromDate, Date toDate, Date checkDate) {
        boolean result = false;

        if (checkDate != null && fromDate != null && toDate != null) {
            if (checkDate.compareTo(fromDate) >= 0 && checkDate.compareTo(toDate) <= 0) {
                result = true;
            }
        }
        return result;
    }

    public static Date getStartOfDay(Date date) {
        GregorianCalendar calendar = new GregorianCalendar(LOCALE_US);
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        calendar.set(year, month, day, 0, 0, 0);
        return calendar.getTime();
    }

    public static Date getEndOfDay(Date date) {
        GregorianCalendar calendar = new GregorianCalendar(LOCALE_US);
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        calendar.set(year, month, day, 23, 59, 59);
        return calendar.getTime();
    }

    public static Date convertCalendarToDate(Calendar calendar) throws Exception {
        Date rtnDate = null;
        if (calendar != null) {
            long timeInMillis = calendar.getTimeInMillis();
            if (timeInMillis > 0) {
                rtnDate = new Date(timeInMillis);
            }
        }
        return rtnDate;
    }

    public static Timestamp convertCalendarToTimestamp(Calendar calendar) throws Exception {
        Timestamp timestamp = null;
        if (calendar != null) {
            long timeInMillis = calendar.getTimeInMillis();
            if (timeInMillis > 0) {
                timestamp = new Timestamp(timeInMillis);
            }
        }
        return timestamp;
    }

    public static XMLGregorianCalendar convertDateToXMLGregorianCalendar(Date date) throws DatatypeConfigurationException {
        GregorianCalendar gCalendar = new GregorianCalendar();
        gCalendar.setTime(date);

        return DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
    }

    /*
     * Converts XMLGregorianCalendar to java.util.Date in Java
     */
    public static Date convertXMLGregorianCalendarToDate(XMLGregorianCalendar calendar) {
        if (calendar == null) {
            return null;
        }
        return calendar.toGregorianCalendar().getTime();
    }

    public static Timestamp convertStringToTimestamp(String stringDate) throws Exception {
        Timestamp timestamp = null;

        if (stringDate != null && !"".equals(stringDate)) {
            SimpleDateFormat format = new SimpleDateFormat();
            format.setCalendar(new GregorianCalendar(enLocale));
            format.applyPattern(DEFAULT_FULL_DATE_TIME_FORMATED);

            Date date = format.parse(stringDate);
            if (date != null) {
                timestamp = new Timestamp(date.getTime());
            }
        }

        return timestamp;
    }

    public static Timestamp convertStringToTimestamp(String stringDate, String pattern, Locale locale) throws Exception {
        Timestamp timestamp = null;

        if (stringDate != null && !"".equals(stringDate)) {
            SimpleDateFormat format = new SimpleDateFormat();
            format.setCalendar(new GregorianCalendar(locale));
            format.applyPattern(pattern);

            Date date = format.parse(stringDate);
            if (date != null) {
                timestamp = new Timestamp(date.getTime());
            }
        }

        return timestamp;
    }

    public static Timestamp getTimeStamp(Date date) {
        Timestamp timestamp = null;

        if (date != null) {
            timestamp = new Timestamp(date.getTime());
        }

        return timestamp;
    }

    public static boolean validateMonthYear(String fromMonth, String fromYear, String toMonth, String toYear) {
        if (fromMonth != null && fromYear != null && toMonth != null && toYear != null) {
            Date from = DateUtil.getDateEn("1/" + fromMonth + "/" + fromYear);
            Date to = DateUtil.getDateEn("1/" + toMonth + "/" + toYear);
            System.out.println("from = " + from + ", to = " + to);
            if (from.compareTo(to) < 0) {
                return true;
            } else if (from.compareTo(to) != 0) {
                return false;
            }
        }
        return false;
    }

    @SuppressWarnings("deprecation")
    public static Date convertStringToDate(String stringDate) throws ParseException {
        Date date = null;

        if (stringDate != null && !"".equals(stringDate)) {
            SimpleDateFormat format = new SimpleDateFormat();
            format.setCalendar(new GregorianCalendar(enLocale));
            format.applyPattern(DEFAULT_DATE_FORMATED);
            date = format.parse(stringDate);
            if (date != null) {
                int year = date.getYear();
                if (year > 500) {
                    year = year - 543;

                    date.setYear(year);
                }
            }
        }

        return date;
    }

    @SuppressWarnings("deprecation")
    public static Date convertStringToDate2(String stringDate) throws ParseException {
        Date date = null;

        if (stringDate != null && !"".equals(stringDate)) {
            SimpleDateFormat format = new SimpleDateFormat();
            format.setCalendar(new GregorianCalendar(enLocale));
            format.applyPattern(DEFAULT_DATE_SEARCH_BPM_FORMATED);
            date = format.parse(stringDate);
            if (date != null) {
                int year = date.getYear();
                if (year > 500) {
                    year = year - 543;

                    date.setYear(year);
                }
            }
        }

        return date;
    }

    @SuppressWarnings("deprecation")
    public static Date convertStringToDate(String stringDate, String pattern) throws ParseException {
        Date date = null;

        if (stringDate != null && !"".equals(stringDate)) {
            SimpleDateFormat format = new SimpleDateFormat();
            format.setCalendar(new GregorianCalendar(enLocale));
            format.applyPattern(pattern);
            date = format.parse(stringDate);
            if (date != null) {
                int year = date.getYear();
                if (year > 500) {
                    year = year - 543;

                    date.setYear(year);
                }
            }
        }

        return date;
    }

    public static int diffDateBetween(Date startDate, Date endDate) {

        int difInDays = (int) ((endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60L * 24));

        return difInDays;
    }

    public static int diffDateNotTime(Date startDate, Date endDate) {

        SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMATED);

        Date dst = getFormatDate(dateFormat.format(startDate));
        Date dsp = getFormatDate(dateFormat.format(endDate));

        int difInDays = (int) ((dsp.getTime() - dst.getTime()) / (1000 * 60L * 60 * 24));
        return difInDays;
    }

    public static Date dateToDate(Date dd) {
        String d1 = getStrDate(dd);
        return getFormatDate(d1);
    }

    public static String getStrDate(Date d) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMATED, thLocale);
        String dd = dateFormat.format(new Date());
        try {
            dd = dateFormat.format(d);
        } catch (Exception e) {
            dd = "";
        }
        return dd;
    }

    public static Date getFormatDate(String dateStr) {
        Date date = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMATED);
        try {
            date = dateFormat.parse(dateStr);
        } catch (Exception e) {
            date = null;
        }
        return date;
    }

    public static Double getActualMaximumDateForMonth(Date dt) throws Exception {
        Integer result = 0;
        if (dt != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(dt);
            result = cal.getActualMaximum(Calendar.DATE);
        }
        return Double.valueOf(result);
    }

    public static String getStrDateByFormat(Date d, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, enLocale);
        String dd = dateFormat.format(new Date());
        try {
            dd = dateFormat.format(d);
        } catch (Exception e) {
            dd = "";
        }
        return dd;
    }

    public static String getMonthFullNameThaiByMounthNo(int monthNo) {

        int mountNumber = monthNo - 1;
        // int mountNumber = monthNo;

        String monthShortNameThai = "";
        // 1
        if (Calendar.JANUARY == mountNumber) {
            monthShortNameThai = "\u0E21\u0E01\u0E23\u0E32\u0E04\u0E21";

            // 2
        } else if (Calendar.FEBRUARY == mountNumber) {
            monthShortNameThai = "\u0E01\u0E38\u0E21\u0E20\u0E32\u0E1E\u0E31\u0E19\u0E18\u0E4C";

            // 3
        } else if (Calendar.MARCH == mountNumber) {
            monthShortNameThai = "\u0E21\u0E35\u0E19\u0E32\u0E04\u0E21";

            // 4
        } else if (Calendar.APRIL == mountNumber) {
            monthShortNameThai = "\u0E40\u0E21\u0E29\u0E32\u0E22\u0E19";

            // 5
        } else if (Calendar.MAY == mountNumber) {
            monthShortNameThai = "\u0E1E\u0E24\u0E29\u0E20\u0E32\u0E04\u0E21";

            // 6
        } else if (Calendar.JUNE == mountNumber) {
            monthShortNameThai = "\u0E21\u0E34\u0E16\u0E38\u0E19\u0E32\u0E22\u0E19";

            // 7
        } else if (Calendar.JULY == mountNumber) {
            monthShortNameThai = "\u0E01\u0E23\u0E01\u0E0E\u0E32\u0E04\u0E21";

            // 8
        } else if (Calendar.AUGUST == mountNumber) {
            monthShortNameThai = "\u0E2A\u0E34\u0E07\u0E2B\u0E32\u0E04\u0E21";

            // 9
        } else if (Calendar.SEPTEMBER == mountNumber) {
            monthShortNameThai = "\u0E01\u0E31\u0E19\u0E22\u0E32\u0E22\u0E19";

            // 10
        } else if (Calendar.OCTOBER == mountNumber) {
            monthShortNameThai = "\u0E15\u0E38\u0E25\u0E32\u0E04\u0E21";

            // 11
        } else if (Calendar.NOVEMBER == mountNumber) {
            monthShortNameThai = "\u0E1E\u0E24\u0E28\u0E08\u0E34\u0E01\u0E32\u0E22\u0E19";

            // 12
        } else if (Calendar.DECEMBER == mountNumber) {
            monthShortNameThai = "\u0E18\u0E31\u0E19\u0E27\u0E32\u0E04\u0E21";
        }

        return monthShortNameThai;
    }

    @SuppressWarnings("unused")
    private static Calendar getPeriodStartDate(Calendar cal) {
        cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
        return cal;
    }

    @SuppressWarnings("unused")
    private static Calendar getPeriodEndDate(Calendar cal, int scalar) {

        while (scalar-- > 0) {
            cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
            if (scalar > 0) {
                cal.add(Calendar.DATE, 1);
            }
        }

        return cal;
    }

    public static List<Date> getDate(String start_date, String end_date) throws Exception {
        List<Date> dates = new ArrayList<Date>();

        // String str_date ="27/08/2010";
        // String end_date ="02/09/2010";

        DateFormat formatter;

        formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = (Date) formatter.parse(start_date);
        Date endDate = (Date) formatter.parse(end_date);
        long interval = 24 * 1000 * 60 * 60; // 1 hour in millis
        long endTime = endDate.getTime(); // create your endtime here, possibly
        // using Calendar or Date
        long curTime = startDate.getTime();
        while (curTime <= endTime) {
            dates.add(new Date(curTime));
            curTime += interval;
        }
        for (int i = 0; i < dates.size(); i++) {
            Date lDate = (Date) dates.get(i);
            String ds = formatter.format(lDate);
            logger.debug(" Date is ..." + ds + " " + toStringEngDateTime(lDate, "EEEE"));
        }
        return dates;
    }

    public static List<Date> getDatesBetweenDates(String start_date, String end_date) throws Exception {
        List<Date> dates = new ArrayList<Date>();

        // String str_date ="27/08/2010";
        // String end_date ="02/09/2010";

        DateFormat formatter;

        formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = (Date) formatter.parse(start_date);
        Date endDate = (Date) formatter.parse(end_date);
        long interval = 24 * 1000 * 60 * 60; // 1 hour in millis
        long endTime = endDate.getTime(); // create your endtime here, possibly
        // using Calendar or Date
        long curTime = startDate.getTime();
        while (curTime <= endTime) {
            dates.add(new Date(curTime));
            curTime += interval;
        }
        for (int i = 0; i < dates.size(); i++) {
            Date lDate = (Date) dates.get(i);
            String ds = formatter.format(lDate);
            logger.debug(" Date is ..." + ds + " " + toStringEngDateTime(lDate, "EEEE"));
        }
        return dates;
    }

    public static Date add235959999(Date date) {
        Date result = new Date();
        Calendar dueDateCal = Calendar.getInstance();
        dueDateCal.setTime(date);
        dueDateCal.set(Calendar.HOUR_OF_DAY, 23);
        dueDateCal.set(Calendar.MINUTE, 59);
        dueDateCal.set(Calendar.SECOND, 59);
        dueDateCal.set(Calendar.MILLISECOND, 999);
        result = dueDateCal.getTime();
        return result;
    }

    public static String getZeroNumber(int digi) throws Exception {
        String result = "";
        if (digi < 10 && digi > -1) {
            result = "0" + digi;
        } else {
            result = String.valueOf(digi);
        }
        return result;
    }
}
