/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proje;

/**
 *
 * @author leman
 */
public class Dikdortgen extends GeometrikNesne   //GeometrikNesne sinfindan kalitim ile Dikdortgen sinifi yaziliyor
{

    private double en;  //dikdortgenin enini ifade etmek icin double tipinde degisken
    private double boy; //dikdortgenin boyunu ifade etmek icin double tipinde degisken

    public Dikdortgen() //Parametresiz yapıcı metot 
    {

        super();  //ust sinifin ogelerine erismek icin kullaniliyor
        en = 1.0;
        boy = 1.0;
    }

    public Dikdortgen(double en, double boy, String etiket, Date tarih) //gerekli tum degiskenleri parametre olarak icine alan yapici metot
    {
        super(etiket, tarih); //ust sinifin ogelerine erismek icin kullaniliyor
        this.en = en;   
        this.boy = boy;
    }

    public Dikdortgen(Dikdortgen originalObject) //Bu sinifda baska nesne kullanilarak copy constructor yaziliyor
    {
        super(originalObject);   //ust sinifin ogelerine erismek icin kullaniliyor
        en = originalObject.en;
        boy = originalObject.boy;
    }
    
    //en ve boy degiskenleri icin Get ve Set metotlati yaziliyor
    public double getEn() {
        return en;
    }

    public void setEn(double en) {
        this.en = en;
    }

    public double getBoy() {
        return boy;
    }

    public void setBoy(double boy) {
        this.boy = boy;
    }

    @Override
    public String toString() //nesneyi bir string olarak gostermek icin toString metotu yaziliyor
    {
        return (super.toString() +"\nEn: " + en + "\nBoy: " + boy );
    }

    //ust sınıftaki soyut metotların gerçekleştirimleri yapiliyor
    @Override
    public double alanHesabla() //dikdortgenin alani hesaplaniyor
    {
        return en * boy;
    }

    @Override
    public double cevreHesabla() //dikdortgenin cevresi hesaplaniyor
    {
        return 2 * (en + boy);
    }

    //verilen nesnelerin bilgilerini karsilastriran metot
    @Override
    public int compareTo(Object o) {
        Dikdortgen oAlan = (Dikdortgen) o;

        if (alanHesabla() > oAlan.alanHesabla()) //eğer metodun üzerinde çağrıldığı dikdörtgen nesnesinin alanı parametrede verilen dikdörtgen nesnesinin alanından büyük ise 1 dondur
        {
            return 1;
        } else if (alanHesabla() < oAlan.alanHesabla()) ////eğer metodun üzerinde çağrıldığı dikdörtgen nesnesinin alanı parametrede verilen dikdörtgen nesnesinin alanından kucuk ise -1 dondur
        {
            return -1;
        } else //eğer metodun üzerinde çağrıldığı dikdörtgen nesnesinin alanı parametrede verilen dikdörtgen nesnesinin alanına esit ise 0 dondur
        {
            return 0;
        }
    }

}
