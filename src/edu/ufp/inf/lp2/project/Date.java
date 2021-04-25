package edu.ufp.inf.lp2.project;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Date implements Comparable<Date>{

  public int hour;

  public int day;

  public int month;

  public int year;

  public LogsTB myLogsTB;


  public Date(int hour,int day, int month, int year) {
    this.hour=hour;
    this.day = day;
    this.month = month;
    this.year = year;
  }

  public Date() {
    GregorianCalendar c = new GregorianCalendar();
    this.hour=24;
    this.day = c.get(Calendar.DAY_OF_MONTH);
    this.month = c.get(Calendar.MONTH) + 1;
    this.year = c.get(Calendar.YEAR);
  }

  public Date(Date d) {
    this.hour=d.hour;
    this.day = d.day;
    this.month = d.month;
    this.year = d.year;
  }
  public int getHour() {
    return hour;
  }

  public void setHour(int hour) {
    if (hour < 0 || hour > 24) return;//caso seja erro
    this.hour = hour;
  }

  public int getDay() {
    return day;
  }

  public void setDay(int day) {
    if (day < 1 || day > 31) return;//caso seja erro
    this.day = day;
  }

  public int getMonth() {
    return month;
  }

  public void setMonth(int month) {
    if (month < 1 || month > 12) return;
    {//caso seja erro
      this.month = month;
    }
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    if (year < 2021) return;
    this.year = year;
  }

  public boolean beforeDate(Date d) { return this.compareTo(d) < 0;  }

  public boolean afterDate(Date d) {
    return this.compareTo(d) > 0;
  }

  public boolean isLeapYear(int year) {
    return ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0));
  }



  public int compareTo(Date d) {
    if (this.year == d.year && this.month == d.month && this.day == d.day && this.hour==d.hour) {
      return 0;
    } else if (this.year == d.year) {
      if (this.month == d.month) {
        if(this.day==d.day){
          return (this.hour - d.hour) / Math.abs(this.hour - d.hour);
        }else {
          return (this.day - d.day) / Math.abs(this.day - d.day);
        }
      } else {
        return (this.month - d.month) / Math.abs(this.month - d.month);
      }
    } else {
      return (this.year - d.year) / Math.abs(this.year - d.year);
    }
  }

  public int differenceYears(Date d) {
    if(this.year == d.year){
      return differenceMonths(this,d);
    }
    int ndias = differenceMonths(this,new Date(0,31,12,this.year));
    while(this.year <d.year){
      if(isLeapYear(this.year)){
        ndias+=366;
      }else{
        ndias+=365;
      }
      this.year+=1;

    }
    ndias+=differenceMonths(new Date(0,1,1,this.year),d);
    return ndias;
  }

  public int differenceMonths(Date begin,Date end) {
    if (begin.month == end.month) {
      return end.day - begin.day;
    }
    int ndias = begin.daysMonth(begin.month, begin.year);//retorna numero de dias daquele mes
    int dayCounter = ndias - begin.day;//numero de dias inicial
    begin.setMonth(begin.month + 1);
    begin.setDay(1);
    while (begin.month < end.month) {
      dayCounter = begin.daysMonth(begin.month, begin.year);
      begin.setMonth(begin.month + 1);
    }
    dayCounter += end.day;
    return dayCounter;
  }

  public int daysMonth(int m, int y) {
    switch (m) {
      case 4:
      case 6:
      case 9:
      case 11:
        return 30;
      case 2:
        if (this.isLeapYear(y)) {
          return 29;
        } else return 28;
      default:
        return 31;
    }
  }


  public String print() {
    return  "("+ hour +"h)(" + day + "," + month + "," + year + ")";
  }

  @Override
  public String toString() {
    return "Date{" +
            "day=" + day +
            ", month=" + month +
            ", year=" + year +
            ", hours=" + hour +
            '}';
  }
}