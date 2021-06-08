
package smurfs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/*
 * Karakter Sinifi
Bu sinifta bulunmasi gereken ozellikler ve fonksiyonlar:
�? Karakterlerin adını tutacak ID, Ad, Türünü (oyuncu/dusman) tutacak.
�? Karakterlerin ilerlediği koordinatlari tutacak Lokasyon degiskenleri olmalidir. 
�? Constructor, Get, Set ve En KisaYol metotlari yer almalidir. 
 * 
 */

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JLabel;

public class karakter extends locasyon {
	String ad;
	String tur;
	int id;
	protected ArrayList<Integer> en_kisa_yol = new ArrayList<Integer>();

	public karakter() {
		// TODO Auto-generated constructor stub
	}

	public karakter(String ad, String tur, int id) {
		// TODO Auto-generated constructor stub
		this.ad = ad;
		this.tur = tur;
		this.id = id;
	}

	int en_kisa_yol() {
		return 0;
	}

	void setAd(String ad) {
		this.ad = ad;
	}

	void setTur(String tur) {
		this.tur = tur;
	}

	void setId(int id) {
		this.id = id;
	}

	String getAd() {
		return ad;
	}

	String getTur() {
		return tur;
	}

	int getId() {
		return id;
	}

	public void karakteri_hareket_ettir() {

		if (this.en_kisa_yol.size() >= 4) {

			this.x = this.en_kisa_yol.get(3);
			this.y = this.en_kisa_yol.get(4);

		}

		System.out.println(this.en_kisa_yol.toString());
	}

	public void setEn_kisa_yol(ArrayList<Integer> enKisaYol) {
		this.en_kisa_yol = enKisaYol;
	}

	public ArrayList<Integer> getEn_kisa_yol() {
		return this.en_kisa_yol;
	}

	class Node {
		short row, col;
		int uzunluk = -1;
		Node[] komsu = new Node[4];// r l d u
		ArrayList<String> yol = new ArrayList<String>();
	}

	static short satir = 11;
	static short sutun = 13;

	public void en_kisa_yol(int oyuncu_x, int oyuncu_y, int[][] map) {
		ArrayList<Integer> yeni_deneme = new ArrayList<Integer>();
		ArrayList<Node> nodes = new ArrayList<Node>();
		for (short i = 0; i < satir; i++) {
			for (short j = 0; j < sutun; j++) {
				Node node = new Node();
				if (map[i][j] == 1) {
					node.row = i;
					node.col = j;
					nodes.add(node);
				}
			}
		}
		for (var i : nodes) {
			for (var j : nodes) {
				if (i.row == j.row && i.col + 1 == j.col) {
					i.komsu[0] = j;
				} else if (i.row == j.row && i.col - 1 == j.col) {
					i.komsu[1] = j;
				} else if (i.row + 1 == j.row && i.col == j.col) {
					i.komsu[2] = j;
				} else if (i.row - 1 == j.row && i.col == j.col) {
					i.komsu[3] = j;
				}
			}
		}

		int c = 0;
		int bas_konum;
		for (var i : nodes) {
			if (i.col == this.getX() && this.getY() == i.row) {
				bas_konum = c;
				break;
			}
			c++;
		}

		Node bizim = nodes.get(c);
		bizim.uzunluk = 0;
		mesafe_hesaplama(bizim);
		ArrayList<String> yeni = new ArrayList<String>();
		int[] dizi = new int[2];
		String[] dizi1 = new String[2];
		for (var i : nodes) {
			if (i.col == oyuncu_x && i.row == oyuncu_y) {
				yeni = i.yol;
				for (int d = 0; d < i.yol.size(); d++) {
					dizi1 = yeni.get(d).split(",");
					yeni_deneme.add(Integer.parseInt(dizi1[1]));
					yeni_deneme.add(Integer.parseInt(dizi1[0]));

				}
			}
		}

		this.en_kisa_yol = yeni_deneme;

	}

	static void mesafe_hesaplama(Node baslangic) {
		if (baslangic.komsu[0] != null) {
			if (baslangic.komsu[0].uzunluk == -1) {
				for (var i : baslangic.yol) {
					baslangic.komsu[0].yol.add(i);
				}
				baslangic.komsu[0].yol.add(baslangic.col + "," + baslangic.row);
				baslangic.komsu[0].uzunluk = baslangic.komsu[0].yol.size();
				mesafe_hesaplama(baslangic.komsu[0]);
			} else if (baslangic.komsu[0].uzunluk > (baslangic.uzunluk + 1)) {
				ArrayList<String> metin = new ArrayList<String>();
				for (var i : baslangic.yol) {
					metin.add(i);
				}
				metin.add(baslangic.col + "," + baslangic.row);
				baslangic.komsu[0].yol = metin;
				baslangic.komsu[0].uzunluk = baslangic.komsu[0].yol.size();
				mesafe_hesaplama(baslangic.komsu[0]);
			}

		}
		if (baslangic.komsu[1] != null) {
			if (baslangic.komsu[1].uzunluk == -1) {
				for (var i : baslangic.yol) {
					baslangic.komsu[1].yol.add(i);
				}
				baslangic.komsu[1].yol.add(baslangic.col + "," + baslangic.row);
				baslangic.komsu[1].uzunluk = baslangic.komsu[1].yol.size();
				mesafe_hesaplama(baslangic.komsu[1]);
			} else if (baslangic.komsu[1].uzunluk > (baslangic.uzunluk + 1)) {
				ArrayList<String> metin = new ArrayList<String>();
				for (var i : baslangic.yol) {
					metin.add(i);
				}
				metin.add(baslangic.col + "," + baslangic.row);
				baslangic.komsu[1].yol = metin;
				baslangic.komsu[1].uzunluk = baslangic.komsu[1].yol.size();
				mesafe_hesaplama(baslangic.komsu[1]);
			}
		}
		if (baslangic.komsu[2] != null) {
			if (baslangic.komsu[2].uzunluk == -1) {
				for (var i : baslangic.yol) {
					baslangic.komsu[2].yol.add(i);
				}
				baslangic.komsu[2].yol.add(baslangic.col + "," + baslangic.row);
				baslangic.komsu[2].uzunluk = baslangic.komsu[2].yol.size();
				mesafe_hesaplama(baslangic.komsu[2]);
			} else if (baslangic.komsu[2].uzunluk > (baslangic.uzunluk + 1)) {
				ArrayList<String> metin = new ArrayList<String>();
				for (var i : baslangic.yol) {
					metin.add(i);
				}
				metin.add(baslangic.col + "," + baslangic.row);
				baslangic.komsu[2].yol = metin;
				baslangic.komsu[2].uzunluk = baslangic.komsu[2].yol.size();
				mesafe_hesaplama(baslangic.komsu[2]);
			}
		}
		if (baslangic.komsu[3] != null) {
			if (baslangic.komsu[3].uzunluk == -1) {
				for (var i : baslangic.yol) {
					baslangic.komsu[3].yol.add(i);
				}
				baslangic.komsu[3].yol.add(baslangic.col + "," + baslangic.row);
				baslangic.komsu[3].uzunluk = baslangic.komsu[3].yol.size();
				mesafe_hesaplama(baslangic.komsu[3]);
			} else if (baslangic.komsu[3].uzunluk > (baslangic.uzunluk + 1)) {
				ArrayList<String> metin = new ArrayList<String>();
				for (var i : baslangic.yol) {
					metin.add(i);
				}
				metin.add(baslangic.col + "," + baslangic.row);
				baslangic.komsu[3].yol = metin;
				baslangic.komsu[3].uzunluk = baslangic.komsu[3].yol.size();
				mesafe_hesaplama(baslangic.komsu[3]);
			}
		}

	}

}
