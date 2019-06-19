package shiq.com.common.utils;

import android.text.TextUtils;
import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author HeDongDong
 * @Title: TimeUtil
 * @Package: com.rongchuang.logistics.utils
 * @Description: 日期处理工具类
 * @Date: 2015-11-12 上午11:19:58
 * @Version: 1.0
 */
public class TimeUtil {

    public static final long weekMills = 1000 * 60 * 60 * 24 * 7 ;

    //时间格式
    public static final String YMDHMS = "yyyy-MM-dd HH:mm:ss";
    public static final String YMD_HM = "yyyy-MM-dd HH:mm";
    public static final String YMD = "yyyy-MM-dd";
    public static final String MDHM = "MM-dd HH:mm";
    public static final String MD = "MM/dd";
    public static final String YMD_DOT = "yyyy.MM.dd";
    public static final String HMS = "HH:mm:ss";
    public static final String HM = "HH:mm";

    /**
     * @param time  时间戳
     * @param match 想要的时间格式
     * @return 格式化后的时间
     * @Title: formatTime
     * @Description: TODO 把时间戳转换成自己想要的格式
     */
    public static String formatTime(String time, String match) {
        if (TextUtils.isEmpty(time)) {
            return "";
        }
        Date date = null;
        String reTime = "";
        try {
            if (time.length() == 10) {
                date = new Date(Long.parseLong(time) * 1000);
            } else if (time.length() == 13) {
                date = new Date(Long.parseLong(time));
            } else {
                return time;
            }
            reTime = new SimpleDateFormat(match).format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reTime;
    }

    public static long formatMillTime(String time, String match) {

        long thistime = 0;
        try {
            SimpleDateFormat df = new SimpleDateFormat(match);
            Date d2 = df.parse(time);// 系统
            thistime = d2.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return thistime;
    }

    public static SimpleDateFormat YMDHM = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    /**
     * 获取时间 年月日
     */
    public static String getTimeYMD() {
        return DateFormat.format("yyyy-MM-dd", System.currentTimeMillis()).toString();

    }

    /**
     * 获取时间 （时分秒）
     */
    public static String getTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        return formatter.format(curDate);

    }

    public static String dateToString(Date date, String pattern) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }


    /**
     * 获取时间 年月
     */
    public static String getTimeYM() {
        return DateFormat.format("yyyy-MM", System.currentTimeMillis()).toString();

    }

    /**
     * 将时间MM月dd日 改为yyyy-mm-dd 格式
     */

    public static String strToTime(String str) {
        String[] split = str.split(" ");
        String strDate = split[0];

        if (TextUtils.isEmpty(strDate)){

            return "暂无配送时间" ;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
        Date d = null;
        try {
            d = (Date) sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sdf = new SimpleDateFormat("MM-dd");
        String strFormat = sdf.format(d);

        //得到当前的年份
        int year = Calendar.getInstance().get(Calendar.YEAR);

        String format = year + "-" + strFormat;
	           /* System.out.println(sdf.format(d));//获取
	            System.out.println(d.getTime());//获取毫秒*/
        return format;
    }



    /**
     * 获取时间 年 月 日 时 分 秒时间差
     */
    public static String gettimeYMDkkmspoor(String etime) {
        String thistime = "0";
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
            Date d2 = df.parse(etime);// 系统
            thistime = (curDate.getTime() - d2.getTime()) + "";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return thistime;
    }

    /**
     * 获取时间 年 月 日 时 分 秒时间差
     */
    public static String gettimeYMDkkmspoorto(String diff) {
        String thistime = gettimeYMDkkms();
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
            long thistimea = curDate.getTime() + Long.parseLong(diff);
            thistime = df.format(thistimea);
        } catch (Exception e) {
            e.printStackTrace();
//			System.out.println("===时间差" + e);
        }
        return thistime;
    }

    /**
     * 获取时间 年 月 日 时 分 秒
     */
    public static String gettimeYMDkkms() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        return formatter.format(curDate);

    }

    /**
     * 获取时间 年 月 日 时 分 秒
     */
    public static String gettimeYMDkkmmss() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        return formatter.format(curDate);

    }

    /**
     * 获取空时间 年 月 日 时 分 秒
     */
    public static String getNullTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = null;
        try {
            curDate = formatter.parse("2001-11-11 11:11:11");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatter.format(curDate);

    }

    /**
     * 获取时间 年 月 日 时 分
     */
    public static String gettimeYMDkkm() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        return formatter.format(curDate);

    }

    /**
     * 获取时间 年
     */
    public static String getTimeY() {
        return DateFormat.format("yyyy", System.currentTimeMillis()).toString();

    }

    /**
     * 获取时间 月
     */
    public static String getTimeM() {
        return DateFormat.format("MM", System.currentTimeMillis()).toString();

    }

     /**
     * 获取上个月 月
     */
    public static String getTimeLastM() {

        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        return DateFormat.format("MM", c.getTime()).toString();

    }



    /**
     * 获取时间 天
     */
    public static String getTimeD() {
        return DateFormat.format("dd", System.currentTimeMillis()).toString();

    }

    /**
     * 获取时间 时
     */
    public static String getTimeK() {
        // return DateFormat.format("kk",
        // System.currentTimeMillis()).toString();
        SimpleDateFormat formatter = new SimpleDateFormat("HH");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        return formatter.format(curDate);

    }

    /**
     * 获取时间 分
     */
    public static String getTimem() {
        return DateFormat.format("mm", System.currentTimeMillis()).toString();

    }

    /**
     * 获取时间 秒
     */
    public static String getTimes() {
        return System.currentTimeMillis() + "";
    }

    /**
     * 起始日期大于结束日期
     *
     * @param birthtime
     * @return
     */
    public static boolean isbig(String birthtime) {
        return java.sql.Date.valueOf(birthtime).after(java.sql.Date.valueOf(getTimeYMD()));

    }

    /**
     * 日期对比
     *
     * @param starttime
     * @param endtime
     * @return
     */
    public static boolean istime(String starttime, String endtime) {
        return java.sql.Date.valueOf(starttime).after(java.sql.Date.valueOf(endtime));

    }

    /**
     * 起始日期大于结束日期
     *
     * @param birthtime
     * @return
     */
    public static boolean isbigtime(String birthtime) {
        // String a="2013-12-28 15:18";
        // SimpleDateFormat st = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        // String b = st.format(System.currentTimeMillis());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        String b = formatter.format(curDate);
        try {
            return formatter.parse(birthtime).after(formatter.parse(b));
        } catch (ParseException e) {
            return false;
        }
    }

    // a integer to xx:xx:xx
    public static String secToTime(int time) {
        int secondnd = time / 60;
        int million = time % 60;
        return secondnd + "分" + million;

    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param birthDay
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(String birthDay) {
        if (birthDay.equals("") || birthDay == null) {
            return 0;
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date d1 = sdf.parse(birthDay);
                Date d2 = sdf.parse(TimeUtil.getTimeYMD());
                Calendar cal = Calendar.getInstance();
                cal.setTime(d1);
                long time1 = cal.getTimeInMillis();
                cal.setTime(d2);
                long time2 = cal.getTimeInMillis();
                long between_days = (time2 - time1) / (1000 * 3600 * 24);

                return Integer.parseInt(String.valueOf(between_days));
            } catch (ParseException e) {
                e.printStackTrace();
                return 0;
            }
        }

    }

    /**
     * 获取年龄
     *
     * @param birthDay
     * @return
     */
    public static int getyear(String birthDay) {
        if (birthDay.equals("") || birthDay == null) {
            return 0;
        } else {

            Calendar cal = Calendar.getInstance();

            if (cal.before(birthDay)) {
                throw new IllegalArgumentException("出生时间大于当前时间!");
            }
            /**
             * 系统日期
             */
            int yearNow = cal.get(Calendar.YEAR);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = sdf.parse(birthDay);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            cal.setTime(date);
            /**
             * 输入日期
             */
            int yearBirth = cal.get(Calendar.YEAR);
            /***
             * current:2014-5-20 birthday: 2013-6-20 11个月 2012-6-20 1岁 2012-4-20
             * 2岁 按出生日期算 出生未满一年 按 月 天 算，满一年后按年算
             *
             *
             */

            int age = yearNow - yearBirth;
            return age;

        }
    }


    /**
     * 当月有多少天
     *
     * @return
     */
    public static int getMday(int year, int month) {
        // 添加大小月月份并将其转换为list,方便之后的判断
        int day = 0;
        String[] months_big = {"1", "3", "5", "7", "8", "10", "12"};
        String[] months_little = {"4", "6", "9", "11"};

        final List<String> list_big = Arrays.asList(months_big);
        final List<String> list_little = Arrays.asList(months_little);

        // 判断大小月及是否闰年,用来确定"日"的数据
        if (list_big.contains(String.valueOf(month))) {

            day = 31;
        } else if (list_little.contains(String.valueOf(month))) {
            day = 30;
        } else {
            // 闰年
            if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
                day = 29;
            else
                day = 28;
        }

        return day;
    }

    public static int getindex(String str) {
        String[] wmy = {"1&周", "2&周", "3&周", "4&周", "1&月", "2&月", "3&月", "4&月", "5&月", "6&月", "7&月", "8&月", "9&月", "10&月", "11&月", "12&月", "13&月",
                "14&月", "15&月", "16&月", "17&月", "18&月", "19&月", "20&月", "21&月", "22&月", "23&月", "24&月", "25&月", "26&月", "27&月", "28&月", "29&月",
                "30&月", "31&月", "32&月", "33&月", "34&月", "35&月", "3&岁", "4&岁", "5&岁", "6&岁"};
        int index = 0;
        for (int i = 0; i < wmy.length; i++) {
            if (wmy[i].equals(str)) {
                index = i;
            }
        }
        return index;

    }

    /**
     * 获取当前日期的前段时间
     *
     * @param time
     * @return
     */
    public static String getShortTime(String time) {
        String shortstring = null;
        long now = Calendar.getInstance().getTimeInMillis();
        Date date = getDateByString(time);
        if (date == null)
            return shortstring;
        long deltime = (now - date.getTime()) / 1000;
        if (deltime > 365 * 24 * 60 * 60) {
            // shortstring = (int)(deltime/(365*24*60*60)) +"年前";
            shortstring = time.substring(0, 10);
        } else if (deltime > 24 * 60 * 60) {
            shortstring = time.substring(0, 10);
            // shortstring = (int)(deltime/(24*60*60)) +"天前";
        } else if (deltime > 60 * 60) {
            shortstring = (int) (deltime / (60 * 60)) + "小时前";
        } else if (deltime > 60) {
            shortstring = (int) (deltime / (60)) + "分钟前";
        } else if (deltime > 1) {
            shortstring = deltime + "秒前";
        } else {
            shortstring = "1秒前";
        }
        return shortstring;
    }

    public static Date getDateByString(String time) {
        Date date = null;
        if (time == null || time.equals(""))
            return date;
        String date_format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat(date_format);
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 截取年月日星期
     *
     * @param timer
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String getYuyueUtileTime(String timer) {

        String result = "";

        if (timer == null || timer.length() == 0) {
            return result;
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format2 = new SimpleDateFormat("EEEE");
        Date date = null;
        try {
            date = format.parse(timer);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null) {
            int month = date.getMonth() + 1;
            int day = date.getDate();
            String weekday = format2.format(date);
            int hour = date.getHours();
            int minutes = date.getMinutes();
            int year = date.getYear() + 1900;

            result = year + "年" + month + "月" + day + "日" + " " + "(" + weekday + ")";
        }
        return result;
    }

    /**
     * 截取小时 分钟
     *
     * @param timer
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String getYuyueHMUtileTime(String timer, String timer1) {

        String result = "";

        if (timer == null || timer.length() == 0) {
            return result;
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        Date date1 = null;
        try {
            date = format.parse(timer);
            date1 = format.parse(timer1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null || date1 != null) {
            String hour = date.getHours() + "";
            String minutes = date.getMinutes() + "";
            String hour1 = date1.getHours() + "";
            String minutes1 = date1.getMinutes() + "";
            if (hour.length() < 2) {
                hour = "0" + hour;
            }
            if (minutes.length() < 2) {
                minutes = "0" + minutes;
            }
            if (hour1.length() < 2) {
                hour1 = "0" + hour1;
            }
            if (minutes1.length() < 2) {
                minutes1 = "0" + minutes1;
            }

            result = hour + ":" + minutes + "—" + hour1 + ":" + minutes1;
        }
        return result;
    }

    /**
     * 计算多天前或者多少天后是几日
     *
     * @param days
     * @return
     */
    public static Date dateAdd(int days) {
        // 日期处理模块 (将日期加上某些天或减去天数)返回字符串
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, days); // 日期减 如果不够减会将月变动
        return canlendar.getTime();
    }

    /**
     * 获取当前时间月份第一天
     */
    public static String getFirstDay(int monthType){

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // 获取前月的第一天
        Calendar cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, monthType);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        return format.format(cale.getTime());
    }

     /**
     * 获取上月的第一天
     */
    public static String getLastFirstDay(){

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // 获取前月的第一天
        Calendar cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, -1);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        return format.format(cale.getTime());
    }



    /**
     * 获取当前时间的最后一天
     */
    public static String getLastDay(int monthType){

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // 获取前月的最后一天
        Calendar cale = Calendar.getInstance();
        if (monthType == 0){
            cale.add(Calendar.MONTH, 1);
        }
        cale.set(Calendar.DAY_OF_MONTH, 0);
        return format.format(cale.getTime());
    }


    /**
     * 计算固定时间往前推多少天的日期 及往后推多少天的日期
     *
     * @param date 要计算的时间
     * @param day  前后推的天数 正数往后推 负数往前推
     * @return
     */
    public static Date getDay(String date, int day) {

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            c2.setTime(sdf.parse(date));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        c1.set(Calendar.YEAR, c2.get(Calendar.YEAR));
        c1.set(Calendar.MONTH, c2.get(Calendar.MONTH));
        c1.set(Calendar.DATE, c2.get(Calendar.DATE));
        // c1.add(Calendar.MONDAY, -1);
        c1.add(Calendar.DATE, day);

        return c1.getTime();

    }

    /**
     * 日期转换成字符串：
     */
    public static String getStringtoDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public static String formatDate(String date, String formatStr) {
        Date dt = java.sql.Date.valueOf(date);
        String datestr = String.format(formatStr, dt.getYear() + 1900, dt.getMonth() + 1, dt.getDate());
        return datestr;
    }

    public static String formatDate(String dt, int monthNum, String formatStr) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = java.sql.Date.valueOf(dt);
        Calendar calendar = Calendar.getInstance();//日历对象
        calendar.setTime(date);//设置当前日期
        calendar.add(Calendar.MONTH, monthNum);//月份
        return TimeUtil.formatDate(df.format(calendar.getTime()), formatStr);
    }

    /**
     * 获取某一天是星期几
     *
     * @param date
     * @return
     */
    public static String getDayofweek(String date) {
        String[] weeks = new String[]{"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        if (date.equals("")) {
            cal.setTime(new Date(System.currentTimeMillis()));
        } else {
            cal.setTime(new Date(getDateByStr2(date).getTime()));
        }
        int week = cal.get(Calendar.DAY_OF_WEEK);
        return weeks[week - 1];
    }

    private static Date getDateByStr2(String dd) {

        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = sd.parse(dd);
        } catch (ParseException e) {
            date = null;
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取2个日期的天数差 日期格式yyyy-MM-dd
     *
     * @param time1
     * @param time2
     * @return
     */
    public static int getdates(String time1, String time2) {
        String str1 = time1; // "yyyyMMdd"格式 如 20131022
        String str2 = time2; // "yyyyMMdd"格式 如 20131022
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");// 输入日期的格式
        Date date1 = null;
        try {
            date1 = simpleDateFormat.parse(str1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date date2 = null;
        try {
            date2 = simpleDateFormat.parse(str2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        GregorianCalendar cal1 = new GregorianCalendar();
        GregorianCalendar cal2 = new GregorianCalendar();
        cal1.setTime(date1);
        cal2.setTime(date2);
        double dayCount = (cal2.getTimeInMillis() - cal1.getTimeInMillis()) / (1000 * 3600 * 24);// 从间隔毫秒变成间隔天数
        return (int) dayCount;
    }

    /**
     * 获取2个日期间的秒数差
     *
     * @param time1
     * @param time2
     * @return
     */
    public static int getSeconds(String time1, String time2) {
        String str1 = time1; // "yyyyMMdd"格式 如 20131022
        String str2 = time2; // "yyyyMMdd"格式 如 20131022
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 输入日期的格式
        Date date1 = null;
        try {
            date1 = simpleDateFormat.parse(str1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date date2 = null;
        try {
            date2 = simpleDateFormat.parse(str2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        GregorianCalendar cal1 = new GregorianCalendar();
        GregorianCalendar cal2 = new GregorianCalendar();
        cal1.setTime(date1);
        cal2.setTime(date2);
        double secondCount = (cal2.getTimeInMillis() - cal1.getTimeInMillis()) / (1000);// 从间隔毫秒变成间隔天数
        return (int) secondCount;
    }
}
