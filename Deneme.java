/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proje;

//dosya okumak icin paketler import ediliyor
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 *
 * @author leman
 */
public class Deneme {

    static int sonDaireIndexi, sonDikdortgenIndexi, sonSilindirIndexi, ilkDaireIndexi, ilkDikdortgenIndexi, ilkSilindirIndexi;

    public static final int MAX_ELEMAN = 50;  //figur listesi olusturuken kullanilacak eleman sayisi

    public static void main(String[] args) {

        Scanner fileIn = null; // fileIn'i bos bir nesneye baslatir

        //dosya okuma zamani karsilasa bilecek hatalar icin try catch blogu kullaniyoruz
        try {

            fileIn = new Scanner(new FileInputStream("input.txt"));

        } catch (FileNotFoundException e) {

            System.out.println("Dosya bulunamadi.");
            System.exit(0);
        }

        GeometrikNesne[] figurler = new GeometrikNesne[MAX_ELEMAN]; // txt dosyasinda nesneleri yerlestirmek icin GeometrikNesne tipinde figurler nesnesi yaratiliyor
        int index = 0;  //nesnekeri yerlestirecegimiz indexi belirtir

        while (fileIn.hasNext()) {
            
            //txt dosyasindan ilk olarak figur tipi ve etiket okunur
            String sekilAdi = fileIn.next();  
            String etiket = fileIn.next();

            //figurun hangi sinifdan olmasina gore farkli veriler dosyadan okunur,o sinifta yeni nesne yaratilit ve figurler listenise yerlestirilir
            if (sekilAdi.equals("daire")) {
                
                double daireYaricap = fileIn.nextDouble(); //daire sinfinda olan nesnenin yaricapi okunuyor

                Date tarih = Deneme.dosyadanVeriOku(fileIn);  //kod tekrari olmamasi icin yazdigim metotla veriler okunuyor, fazla bilgi ilgili metotla aciklanmistir
                figurler[index] = new Daire(daireYaricap, etiket, tarih); //yeni daire nesnesi yaratilarak figurler listesine ekleniyor
                sonDaireIndexi = index; //copy constructor yaratirken gerekli olan son daire nesnesinin indexi'ni elde ediyoruz

            } else if (sekilAdi.equals("dikdortgen")) {

                //dikdortgen sinfinda olan nesnenin eni ve boyu okunuyor
                double en = fileIn.nextDouble();  
                double boy = fileIn.nextDouble();

                Date tarih = Deneme.dosyadanVeriOku(fileIn); //kod tekrari olmamasi icin yazdigim metotla veriler okunuyor, fazla bilgi ilgili metotla aciklanmistir
                figurler[index] = new Dikdortgen(en, boy, etiket, tarih);  //yeni dikdortgen nesnesi yaratilarak figurler listesine ekleniyor
                sonDikdortgenIndexi = index;  //copy constructor yaratirken gerekli olan son dikdortgen nesnesinin indexi'ni elde ediyoruz

            } else {
                
                //silindir sinfinda olan nesnenin yaricap ve uzunlugu okunuyor
                double yaricap = fileIn.nextDouble();
                double uzunluk = fileIn.nextDouble();

                Date tarih = Deneme.dosyadanVeriOku(fileIn);  //kod tekrari olmamasi icin yazdigim metotla veriler okunuyor, fazla bilgi ilgili metotla aciklanmistir
                figurler[index] = new Silindir(yaricap, uzunluk, etiket, tarih);  //yeni silindir nesnesi yaratilarak figurler listesine ekleniyor
                sonSilindirIndexi = index;  //copy constructor yaratirken gerekli olan son silindir nesnesinin indexi'ni elde ediyoruz
            }

            index++;  //her satir okuduktan sonra siradaki index'e geciliyor
        }

        Daire sonDaire = new Daire((Daire) figurler[sonDaireIndexi]);  //dosyada en son okunan daire nesnesinden copy constructor ile yeni nesne yaratiliyor 
        Dikdortgen sonDikdortgen = new Dikdortgen((Dikdortgen) figurler[sonDikdortgenIndexi]);  //dosyada en son okunan dikdortgen nesnesinden copy constructor ile yeni nesne yaratiliyor
        Silindir sonSilindir = new Silindir((Silindir) figurler[sonSilindirIndexi]);  //dosyada en son okunan silindir nesnesinden copy constructor ile yeni nesne yaratiliyor

        figurler[index] = sonDaire; //copy constructor ile olusturulan yeni daire nesnesi figurler listesine ekleniyor
        figurler[index + 1] = sonDikdortgen;  //copy constructor ile olusturulan yeni dikdortgen nesnesi figurler listesine ekleniyor
        figurler[index + 2] = sonSilindir;  //copy constructor ile olusturulan yeni silindir nesnesi figurler listesine ekleniyor

        double topCevre = 0, topAlan = 0,topHacim = 0; //top alan,hacim ve cevreni ifade etmek icin degiskenler
        int silindirSay = 0;  //silind nesnesinin sayini bulmak icin degisken, ortalama hacim bulmak icin gerekli
        double maxAlan = 0, minAlan = 0, maxCevre = 0, minCevre = 0, maxHacim = 0, minHacim = 0;  //max ve min alan, cevre, hacim degerlerini ifade eden degiskenler

        for (int i = 0; i < index + 3; i++) {

            Deneme.polymorphicYazdir(figurler[i]);  //polymorphiYazdir metotu kullanarak listenin icinde olan tum nesnelerinin bilgileri ekrana yazdiriliyor, metot ile ilgili fazla bilgi asagida verilmistir

            if (figurler[i].getEtiket().equals("dai1")) {
                ilkDaireIndexi = i;  //ilk kullanilan daire nesnesinin indexi elde ediliyor, karsilastir metotu icin gerekli
            } else if (figurler[i].getEtiket().equals("dik1")) {
                ilkDikdortgenIndexi = i;  //ilk kullanilan dikdortgen nesnesinin indexi elde ediliyor, karsilastir metotu icin gerekli
            } else if (figurler[i].getEtiket().equals("sil1")) {
                ilkSilindirIndexi = i;  //ilk kullanilan slindir nesnesinin indexi elde ediliyor, karsilastir metotu icin gerekli

            }
            if (figurler[i] instanceof Silindir) {
                topHacim += ((Silindir) figurler[i]).hacimHesapla();  //silindir nesnesinin toplam hacmi hesaplaniyor
                silindirSay++;  //silindir sinfinda kullanilan nesnelerin sayi hesablaniyor
                minHacim = maxHacim = ((Silindir) figurler[i]).hacimHesapla();  //max ve min hacim ilk gelen nesnenin hacmine esitleniyor karsilastirma yapilmasi icin
            }

            topCevre += figurler[i].cevreHesabla();  //toplam cevre hesaplaniyor
            topAlan += figurler[i].alanHesabla();    //toplam alan hesaplaniyor

            minCevre = maxCevre = figurler[i].cevreHesabla();  //max ve min cevre ilk gelen nesnenin cevresine esitleniyor karsilastirma yapilmasi icin
            maxAlan = minAlan = figurler[i].alanHesabla();   //max ve min alan ilk gelen nesnenin alanina esitleniyor karsilastirma yapilmasi icin

            for (int a = 0; a < index + 3; a++) 
            {
                //max ve min alan hesablaniyor
                if (figurler[a].alanHesabla() > maxAlan) {
                    maxAlan = figurler[a].alanHesabla();

                } else if (figurler[a].alanHesabla() < minAlan) {
                    minAlan = figurler[a].alanHesabla();
                }

                if (figurler[a].cevreHesabla() > maxCevre) 
                {
                    //max ve min cevre hesablaniyor
                    maxCevre = figurler[a].cevreHesabla();
                } else if (figurler[a].cevreHesabla() < minCevre) {
                    minCevre = figurler[a].cevreHesabla();
                }
                
                //yalniz silindir nesnesi icin max ve min hacim hesablaniyor
                if (figurler[a] instanceof Silindir){
                    if (((Silindir) figurler[a]).hacimHesapla() >= maxHacim) {
                        
                        maxHacim = ((Silindir) figurler[a]).hacimHesapla();
                    }
                    if (((Silindir) figurler[a]).hacimHesapla() < minHacim) {
                        minHacim = ((Silindir) figurler[a]).hacimHesapla();
                    }
                 
                    
                }

            }

        }

        //karsilastir metotu kullanilarak elde edilen sonuclar ekrana yazdiriliyor
        System.out.print("Ilk ve son daire nesnelerinin karşılaştırılması: ");
        Deneme.karsilartir(figurler[ilkDaireIndexi], sonDaire);

        System.out.print("Son daire ve ondan bir önceki daire nesnelerinin karşılaştırılması: ");
        Deneme.karsilartir(sonDaire, figurler[sonDaireIndexi]);

        System.out.print("Ilk ve son dikdörtgen nesnelerinin karşılaştırılması: ");
        Deneme.karsilartir(figurler[ilkDikdortgenIndexi], sonDikdortgen);

        System.out.print("Son dikdörtgen ve ondan bir önceki dikdörtgen nesnelerinin karşılaştırılması: ");
        Deneme.karsilartir(sonDikdortgen, figurler[sonDikdortgenIndexi]);

        System.out.print("Ilk ve son silindir nesnelernin karşılaştırılması: ");
        Deneme.karsilartir(figurler[ilkSilindirIndexi], sonSilindir);

        System.out.print("Son silindir ve ondan bir önceki silindir nesnelerinin karşılaştırılması: ");
        Deneme.karsilartir(sonSilindir, figurler[sonSilindirIndexi]);

        //max, min ve ortalama degerler icin bir boyutlu dizi tanimlaniyor
        double genelFigurBilgileri[] = new double[MAX_ELEMAN];

        genelFigurBilgileri[0] = topCevre / (index + 3); //tum geometrik nesneler goze alinarak cevre ortalamsi bulunuyor ve diziye yerlestiriliyor
        genelFigurBilgileri[1] = topAlan / (index + 3);  //tum geometrik nesneler goze alinarak alan ortalamsi bulunuyor ve diziye yerlestiriliyor
        genelFigurBilgileri[2] = topHacim / silindirSay; //silindir nesneleri goze alinarak hacim ortalamsi bulunuyor ve diziye yerlestiriliyor 
        genelFigurBilgileri[3] = minCevre; //tum geometrik nesneler goze alinarak daha once bulunan min cevre diziye yerlestiriliyor
        genelFigurBilgileri[4] = maxCevre; //tum geometrik nesneler goze alinarak daha once bulunan max cevre diziye yerlestiriliyor
        genelFigurBilgileri[5] = minAlan; //tum geometrik nesneler goze alinarak daha once bulunan min alan diziye yerlestiriliyor
        genelFigurBilgileri[6] = maxAlan; //tum geometrik nesneler goze alinarak daha once bulunan max aln diziye yerlestiriliyor
        genelFigurBilgileri[7] = minHacim; //silindir nesneler goze alinarak daha once bulunan min hacim diziye yerlestiriliyor
        genelFigurBilgileri[8] = maxHacim; //silindir nesneler goze alinarak daha once bulunan max hacim diziye yerlestiriliyor

        //diziye yerlestirmek bittikten sonra bilgiler sirasiyla ekrana yazdiriliyor
        System.out.printf("\n%s: %.2f\n ", "Ortalama Çevre ", genelFigurBilgileri[0]);
        System.out.printf("\n%s: %.2f\n ", "Ortalama Alan ", genelFigurBilgileri[1]);
        System.out.printf("\n%s: %.2f\n ", "Ortalama Hacim ", genelFigurBilgileri[2]);
        System.out.printf("\n%s: %.2f\n ", "En Küçük Çevre ", genelFigurBilgileri[3]);
        System.out.printf("\n%s: %.2f\n ", "En Büyük Çevre ", genelFigurBilgileri[4]);
        System.out.printf("\n%s: %.2f\n ", "En Küçük Alan ", genelFigurBilgileri[5]);
        System.out.printf("\n%s: %.2f\n ", "En Büyük Alan ", genelFigurBilgileri[6]);
        System.out.printf("\n%s: %.2f\n ", "En Küçük Hacim ", genelFigurBilgileri[7]);
        System.out.printf("\n%s: %.2f\n ", "En Büyük Hacim ", genelFigurBilgileri[8]);

        fileIn.close();
    }

    //doyadan veri okurken kod tekrarini onlemek icin metot yazdim, burada veriler okunduktan sonra Date tipinde yeni nesne olusturuluyor, ve yaranan yeni Date tipinde 
    //nesneni geri döndürüyor. Dosyadan her sinifa ozel verileri okurken dosyadanVeriOku metotoundan gelen Date tipinde tarih nesnesini degiskene atayarak kullanabiliyoruz
    public static Date dosyadanVeriOku(Scanner fileIn)  
    {
        String date = fileIn.next();
        int ay = fileIn.nextInt();
        int gun = fileIn.nextInt();
        int yil = fileIn.nextInt();
        Date tarih = new Date(ay, gun, yil);
        return tarih;

    }

    //Bu metotta parametre olarak gelen nesnenin bilgilerini, cevre ve alan değerlerini yazdırıliyor
    public static void polymorphicYazdir(GeometrikNesne object) //parametre olarak gelen nesnenin sinfi kontrol ediliyor
    {
        if ((object instanceof Daire) || (object instanceof Dikdortgen)) {
            System.out.printf("%s \n%s: %.2f \n%s: %.2f\n\n", object.toString(), "Alan ", object.alanHesabla(), "Cevre: ", object.cevreHesabla());
        }
        if (object instanceof Silindir) //parametre olarak gelen nesne Silindir sinfi ise silindirin hacminin ekrana yazdirilmasi da saglaniyor
        {
            System.out.printf("%s \n%s: %.2f \n%s: %.2f \n%s: %.2f\n\n", object.toString(), "Alan ", object.alanHesabla(),
                    "Cevre ", object.cevreHesabla(), "Hacim ", ((Silindir) object).hacimHesapla());
        }

    }

    // Bu metot,daha önce her sınıf için yazdığımız compareTo metotlarını kullanarak her sinifa ozel iki nesne arasinda karsilastirma yazapaktir 
    public static void karsilartir(GeometrikNesne object1, GeometrikNesne object2) {
        System.out.println(object1.compareTo(object2));

    }

}
