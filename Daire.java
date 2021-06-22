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
public class Daire extends GeometrikNesne //GeometrikNesne sinfindan kalitim ile Daire sinifi yaziliyor
{

    private double yaricap; //Dairenin yazricapini ifade etmek icin double tipinde yaricap degiskeni

    public Daire() //Parametresiz yapıcı metot 
    {
        super(); //ust sinifin ogelerine erismek icin kullaniliyor
        yaricap = 1.0;
    }

    public Daire(double yaricap, String etiket, Date tarih)  //gerekli tum degiskenleri parametre olarak icine alan yapici metot
    {
        super(etiket, tarih);  //ust sinifin ogelerine erismek icin kullaniliyor
        this.yaricap = yaricap;
    }

    public Daire(Daire originalObject) //Bu sinifda baska nesne kullanilarak copy constructor yaziliyor
    {
        super(originalObject); //ust sinifin ogelerine erismek icin kullaniliyor
        yaricap = originalObject.yaricap;

    }

    //yaricap icin Get ve Set metotlati yaziliyor
    public double getYaricap() {
        return yaricap;
    }

    public void setYaricap(double yaricap) {
        this.yaricap = yaricap;
    }

    @Override
    public String toString()  //nesneyi bir string olarak gostermek icin toString metotu yaziliyor
    {
        return (super.toString() + "\nYarıçap: " + yaricap);
    }
   

    //ust sınıftaki soyut metotların gerçekleştirimleri yapiliyor
    @Override
    public double alanHesabla() //dairenin alani hesaplaniyor
    {
        return (PI * yaricap * yaricap);
    }

    @Override
    public double cevreHesabla() //dairenin cevresi hesaplaniyor
    {
        return ( 2 * PI* yaricap);
    }

    //verilen nesnelerin bilgilerini karsilastriran metot
    @Override
    public int compareTo(Object o) 
    {
        
        Daire oYaricap = (Daire) o;
        
        if (this.yaricap > oYaricap.yaricap) //eğer metodun üzerinde çağrıldığı daire nesnesinin yarıçapı parametrede verilen daire nesnesinin yarıçapından büyük ise 1 dondur
        {
                    return 1;
        }
        else if (this.yaricap < oYaricap.yaricap) //eğer metodun üzerinde çağrıldığı daire nesnesinin yarıçapı parametrede verilen daire nesnesinin yarıçapından kucuk ise -1 dondur
        {
                    return -1;
        }
        else  //eğer metodun üzerinde çağrıldığı daire nesnesinin yarıçapı parametrede verilen daire nesnesinin yarıçapına esit ise 0 dondur
            return 0;
            
        
    }

}
