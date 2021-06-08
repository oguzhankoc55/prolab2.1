
package smurfs;

import java.util.ArrayList;

public class oyuncu extends karakter {

	puan Scor;
	int hiz;

	public oyuncu(String ad, String tur, int id) {
		super(ad, tur, id);
		hiz = 1;
	}
	
	public oyuncu() {
		super();
	}

	public int getHiz() {
		return hiz;
	}

	public void setHiz(int hiz) {
		this.hiz = hiz;
	}



	public puan getScor() {
		return Scor;
	}

	public void setScor(int deger) {
		puan y = new puan();
		y.setScor(deger);
		Scor = y;
	}

}

class gozlukluSirin extends oyuncu {

	public gozlukluSirin() {
		super("Gozluklu_sirin", "oyuncu", 1);
		this.ad = "gozluklu";
		this.tur = "oyuncu";
		this.id = 1;
	}
	
	public gozlukluSirin(String ad,String tur,int id) {
		super(ad, tur, id);
		this.ad = ad;
		this.tur = tur;
		this.id = id;
	}
	
	

	@Override
	public int getHiz() {
		// TODO Auto-generated method stub
		return 2;
	}

}

class tembelSirin extends oyuncu {

	public tembelSirin() {
		super("Tembel_sirin", "oyuncu", 2);
		this.ad = "tembel";
		this.tur = "oyuncu";
		this.id = 2;
	}
	
	public tembelSirin(String ad,String tur,int id) {
		super(ad, tur, id);
		this.ad = ad;
		this.tur = tur;
		this.id = id;
	}

	@Override
	public int getHiz() {
		// TODO Auto-generated method stub
		return 1;
	}

}

class puan extends oyuncu {
	int scor;

	public puan() {
		super();
		// TODO Auto-generated constructor stub
		scor = 20;
	}
	
	public puan(int deger) {
		super();
		// TODO Auto-generated constructor stub
		scor = deger;
	}

	public void setScor(int scor) {
		this.scor = scor;
	}

	public int puanGoster() {
		return scor;
	}

}