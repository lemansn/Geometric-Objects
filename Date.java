/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proje;

/**
 *
 * @author narmin
 */
import java.util.Scanner;

public class Date  
{
    private String month; //string tipinde ay degiskeni
    private int day;  // integer tipinde ay gunu
    private int year; //4 numarali rakam 

    public Date( ) //Parametresiz yapıcı metot
    {
        month = "January";
        day = 1;
        year = 1000;
    }

    public Date(int monthInt, int day, int year)  //ay numarasi, ay gunu ve yili parametre olarak alan yapici metot
    {
        setDate(monthInt, day, year);  //hata kontrolu set icinde yapilarak buraya ataniyor
    }

    public Date(String monthString, int day, int year) ////ay ismini, ay gunu ve yili parametre olarak alan yapici metot
    {
        setDate(monthString, day, year);   //hata kontrolu set icinde yapilarak buraya ataniyor
    }

    public Date(int year)  //yili parametre olarak alan yapici metot
    {
        setDate(1, 1, year); // ay ve gun 1 olarak ataniyor ve set icinde hata kontrolu yapiliyor
    }

    public Date(Date aDate) //Date sinfinda baska nesne kullanilarak copy constructor yaziliyor
    {
        if (aDate == null)//real olmayan tarih verilirse hata bildirimi verilerek sistemden cikmasi saglaniyor
        {
             System.out.println("Fatal Error.");
             System.exit(0);
        }

        //herhangi bir hata yoksa bu nesnenin bilgileri parametrede verilen nesnenin bilgilerine atanarak kopyalaniyor
        month = aDate.month;
        day = aDate.day;
        year = aDate.year;
    }

    public void setDate(int monthInt, int day, int year) //ay ismi rakam olarak verilen nesnenin set metodu yaziliyor
    {
        if (dateOK(monthInt, day, year))  // dateOk metotu icinde hata kontrolu yapiliyor
        {
            this.month = monthString(monthInt);
            this.day = day;
            this.year = year;
        }
        else // hata olusursa hata mesaji vererek sistemdem cikiliyor
        {
            System.out.println("Fatal Error");
            System.exit(0);
        }
    }

    public void setDate(String monthString, int day, int year) //ay ismi string olarak verilen nesnenin set metodu yaziliyor
    {
        if (dateOK(monthString, day, year)) // dateOk metotu icinde hata kontrolu yapiliyor
        {
            this.month = monthString;
            this.day = day;
            this.year = year;
        }
        else  // hata olusursa hata mesaji vererek sistemdem cikiliyor
        {
            System.out.println("Fatal Error");
            System.exit(0);
        }
    }

    public void setDate(int year) 
    {
        setDate(1, 1, year);
    }

    public void setYear(int year) //yil degiskeni icin hata kontrolu yapiliyor
    {
        if ( (year < 1000) || (year > 9999) )
        {
            System.out.println("Fatal Error");
            System.exit(0);
        }
        else
            this.year = year;
    }
    public void setMonth(int monthNumber) //int tipinde ay degiskeni icin hata kontrolu yapiliyor
    {
        if ((monthNumber <= 0) || (monthNumber > 12))
        {
            System.out.println("Fatal Error");
            System.exit(0);
        }
        else
            month = monthString(monthNumber);
    }

    public void setDay(int day) ////yil degiskeni icin hata kontrolu yapiliyor
    {
        if ((day <= 0) || (day > 31))
        {
            System.out.println("Fatal Error");
            System.exit(0);
        }
        else
            this.day = day;
    }

    public int getMonth( ) //string tipinde olan ay isimlerini numara olarak geri donduruyor
    {
        if (month.equals("January"))
            return 1;
        else if (month.equals("February"))
            return 2;
        else if (month.equalsIgnoreCase("March"))
            return 3;
        else if (month.equalsIgnoreCase("April"))
            return 4;
        else if (month.equalsIgnoreCase("May"))
            return 5;
        else if (month.equals("June"))
            return 6;
        else if (month.equalsIgnoreCase("July"))
            return 7;
        else if (month.equalsIgnoreCase("August"))
            return 8;
        else if (month.equalsIgnoreCase("September"))
            return 9;
        else if (month.equalsIgnoreCase("October"))
            return 10;
        else if (month.equals("November"))
            return 11;
        else if (month.equals("December"))
            return 12;
        else
        {
            System.out.println("Fatal Error");
            System.exit(0);
            return 0; //Needed to keep the compiler happy
        }
    }

    //gun ve yil icin Get metotu yaziliyor
    public int getDay( )
    {
        return day;
    }

    public int getYear( )
    {
        return year;
    }

    public String toString( ) //nesneyi bir string olarak gostermek icin toString metotu yaziliyor
    {
        return (month + " " + day + ", " + year);
    }

    public boolean equals(Date otherDate) //Date tipinde olan iki nesneni karsilastiriyor 
	{
	    if (otherDate == null) //gecersiz object is false dondurur
	        return false;
	    else
	        return ( (month.equals(otherDate.month)) &&
	            (day == otherDate.day) && (year == otherDate.year) ); //esitse True, farkli nesne oldugunda False donduruyor
    }

    public boolean precedes(Date otherDate) ////Date tipinde olan iki nesneden hangisinin once geldigini buluyor
    {
        return ( (year < otherDate.year) ||
           (year == otherDate.year && getMonth( ) < otherDate.getMonth( )) ||
           (year == otherDate.year && month.equals(otherDate.month)
                                         && day < otherDate.day) ); //True veya False dondurur
    }

    public void readInput( ) //kullanicidan bilgileri alarak veriler set ediliyor
    {
        boolean tryAgain = true;
        Scanner keyboard = new Scanner(System.in);
        while (tryAgain) // gecerli veri girisi yapilana kadar donmesini saglaniliyor
        {
            System.out.println("Enter month, day, and year.");
              System.out.println("Do not use a comma.");
            String monthInput = keyboard.next( );
            int dayInput = keyboard.nextInt( );
            int yearInput = keyboard.nextInt( );
            if (dateOK(monthInput, dayInput, yearInput) )
            {
                setDate(monthInput, dayInput, yearInput);
                tryAgain = false;
            }
            else
                System.out.println("Illegal date. Reenter input.");
         }
    }

    private boolean dateOK(int monthInt, int dayInt, int yearInt) //ay numarasi, gun ve yil icin hata kontrolleri yapiliyor
    {
        return ( (monthInt >= 1) && (monthInt <= 12) &&
                 (dayInt >= 1) && (dayInt <= 31) &&
                 (yearInt >= 1000) && (yearInt <= 9999) );
    }

    private boolean dateOK(String monthString, int dayInt, int yearInt)   //ay ismi, gun ve yil icin hata kontrolleri yapiliyor
    {
        return ( monthOK(monthString) &&
                 (dayInt >= 1) && (dayInt <= 31) &&
                 (yearInt >= 1000) && (yearInt <= 9999) );
    }

    private boolean monthOK(String month) //ay isimlerinin dogru yazilimi saglaniliyor
    {
        return (month.equals("January") || month.equals("February") ||
                month.equals("March") || month.equals("April") ||
                month.equals("May") || month.equals("June") ||
                month.equals("July") || month.equals("August") ||
                month.equals("September") || month.equals("October") ||
                month.equals("November") || month.equals("December") );
    }

    private String monthString(int monthNumber) //verilen ay numrasina karsi ay isimleri donduruyor
    {
        switch (monthNumber)
        {
        case 1:
            return "January";
        case 2:
            return "February";
        case 3:
            return "March";
        case 4:
            return "April";
        case 5:
            return "May";
        case 6:
            return "June";
        case 7:
            return "July";
        case 8:
            return "August";
        case 9:
            return "September";
        case 10:
            return "October";
        case 11:
            return "November";
        case 12:
            return "December";
        default:
            System.out.println("Fatal Error");
            System.exit(0);
            return "Error"; //to keep the compiler happy
        }
    }
}
