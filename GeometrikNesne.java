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


//Comparable interfacesinde implement edilerek abstract class yaziliyor
public abstract class GeometrikNesne implements Comparable<Object> {

        public static final double PI = Math.PI; //classlarda kullanilabilmesi icin static PI degiskeni tanimlaniyor


    private String etiket; // nesneye verilen etiketi tutmak için String tipinde değişken 
    private Date tarih;    //şeklin oluşturulma tarihini tutmak için Date sınıfı tipinde degisken

    public GeometrikNesne() //Parametresiz yapıcı metot
    {
        etiket = "Ettiket yok";
        tarih = new Date(); 
    }

    public GeometrikNesne(String etiket, Date tarih) //etiket ve tarih degiskenlerini parametre olarak alan yapici metot
    {
        this.etiket = etiket;
        this.tarih = new Date(tarih);
    }

    public GeometrikNesne(GeometrikNesne originalObject) //Bu sinifda baska nesne kullanilarak copy constructor yaziliyor
    {
        if (originalObject == null) {
            System.out.println("Error: null Geometrik Nesne.");
            System.exit(0);
        }

        etiket = originalObject.etiket;
        tarih = new Date(originalObject.tarih);  //"privacy leak" engellenecek şekilde yazılmaga calisilmistir
    }

    //etiket ve tarih degiskenleri icin Get ve Set metotlari yaziliyor
    public String getEtiket() {
        return etiket;
    }

    public void setEtiket(String etiket) {
        this.etiket = etiket;
    }

    public Date getTarih() {
        return new Date(tarih); //"privacy leak" engellenecek şekilde yazılmaga calisilmistir
    }

    public void setTarih(Date tarih) {
        this.tarih = tarih;
    }

    @Override
    public String toString() //nesneyi bir string olarak gostermek icin toString metotu yaziliyor
    {
        return ("Etiket: " + etiket +"\nTarih: " + tarih.toString() ); //Date sinfinin toString metotu kullanilmistir
    }

    //soyut metotlatin imzalari yaziliyor
    public abstract double alanHesabla();

    public abstract double cevreHesabla();

    @Override
    public abstract int compareTo(Object o);

}
