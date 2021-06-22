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
public class Silindir extends GeometrikNesne //GeometrikNesne sinfindan kalitim ile Silindir sinifi yaziliyor
{

    private double yaricap;  //silindirin yaricapini ifade etmek icin double tipinde degiskne
    private double uzunluk;  //silindirin uzunlugunu ifade etmek icin double tipinde degiskne

    public Silindir() //Parametresiz yapıcı metot 
    {
        super();  //ust sinifin ogelerine erismek icin kullaniliyor
        yaricap = 1.0;
        uzunluk = 1.0;
    }

    public Silindir(double yaricap, double uzunluk, String etiket, Date tarih) //gerekli tum degiskenleri parametre olarak icine alan yapici metot
    {
        super(etiket, tarih);  //ust sinifin ogelerine erismek icin kullaniliyor
        this.yaricap = yaricap;
        this.uzunluk = uzunluk;
    }

    public Silindir(Silindir originalObject)  //Bu sinifda baska nesne kullanilarak copy constructor yaziliyor
    {
        super(originalObject);  //ust sinifin ogelerine erismek icin kullaniliyor
        yaricap = originalObject.yaricap;
        uzunluk = originalObject.uzunluk;
    }

    //uzunluk ve yaricap icin SEt ve Get metotlari yaziliyor
    public double getYaricap() {
        return yaricap;
    }

    public void setYaricap(double yaricap) {
        this.yaricap = yaricap;
    }

    public double getUzunluk() {
        return uzunluk;
    }

    public void setUzunluk(double uzunluk) {
        this.uzunluk = uzunluk;
    }

    @Override
    public String toString() //nesneyi bir string olarak gostermek icin toString metotu yaziliyor
    {
        return String.format("%s \n%s: %.1f \n%s: %.1f  ", super.toString(), "Yaricap ", yaricap,"Uzunluk ", uzunluk);

    }

    //silindirin hacmini hesaplamak icin bu metot yaziliyor
    public double hacimHesapla() {
        return PI * yaricap * yaricap * uzunluk;
    }

    //ust sınıftaki soyut metotların gerçekleştirimleri yapiliyor
    @Override
    public double alanHesabla() //silindirin alani hesaplaniyor
    {
        return (2 * yaricap * PI * (yaricap + uzunluk));
    }

    @Override
    public double cevreHesabla() //silindirin cevresi hesaplaniyor
    {
        return (2 * 2 * yaricap * PI) + (2 * uzunluk);
    }

    //verilen nesnelerin bilgilerini karsilastriran metot
    @Override
    public int compareTo(Object o) {
        Silindir oHAcim = (Silindir) o;

        if (hacimHesapla() > oHAcim.hacimHesapla()) //eğer metodun üzerinde çağrıldığı silindir nesnesinin hacmi parametrede verilen silindir nesnesinin hacminden büyük ise 1 dondur
        {
            return 1;
        } else if (hacimHesapla() < oHAcim.hacimHesapla()) //eğer metodun üzerinde çağrıldığı silindir nesnesinin hacmi parametrede verilen silindir nesnesinin hacminden kucuk ise -1 dondur
        {
            return -1;
        } else //eğer metodun üzerinde çağrıldığı silindir nesnesinin hacmi parametrede verilen silindir nesnesinin hacmine esit ise 0 dondur
        {
            return 0;
        }
    }

}
