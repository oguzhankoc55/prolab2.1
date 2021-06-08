
package smurfs;

import java.util.ArrayList;

public class dusman extends karakter {
	
	public dusman(String ad, String tur, int id) {
		super(ad, tur, id);
		this.ad=ad;
		this.tur=tur;
		this.id=id;
	}

	public dusman() {

	}

	public boolean mesafe_kontrol() {
		if (this.en_kisa_yol.size() < 2) {
			return true;
		}
		return false;
	}

}

class azman extends dusman {

	public azman() {
		super("azman", "dusman", 1);
		// TODO Auto-generated constructor stub
	}
	
	public azman(String ad, String tur, int id) {
		super(ad, tur, id);
		this.ad=ad;
		this.tur=tur;
		this.id=id;
	}

	@Override
	public boolean mesafe_kontrol() {
		if (this.en_kisa_yol.size() <= 4) {
			return true;
		}
		return false;
	}

	@Override
	public void karakteri_hareket_ettir() {
		if (this.en_kisa_yol.size() > 4) {

			this.x = this.en_kisa_yol.get(3);
			this.y = this.en_kisa_yol.get(4);

		}
	}

}

class gargamel extends dusman {

	public gargamel() {
		super("gargamel", "dusman", 2);
		// TODO Auto-generated constructor stub
	}
	
	public gargamel(String ad, String tur, int id) {
		super(ad, tur, id);
		this.ad=ad;
		this.tur=tur;
		this.id=id;
	}

	@Override
	public boolean mesafe_kontrol() {
		if (this.en_kisa_yol.size() <= 6) {
			return true;
		}
		return false;
	}

	@Override
	public void karakteri_hareket_ettir() {
		// TODO Auto-generated method stub
		if (this.en_kisa_yol.size() > 6) {

			this.x = this.en_kisa_yol.get(5);
			this.y = this.en_kisa_yol.get(6);

		}

	}

}
