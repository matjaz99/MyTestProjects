package si.matjazcerkvenik.test.weathermetrics;

import javax.xml.bind.annotation.XmlAttribute;

public class Postaja {

    private String sifra;
    private double ge_dolzina;
    private double ge_sirina;
    private double kota;
    private String reka;
    private String merilno_mesto;
    private String ime_kratko;
    private String datum;
    private double vodostaj;
    private double pretok;
    private String pretok_znacilni;
    private double temp_vode;
    private double prvi_vv_pretok;
    private double drugi_vv_pretok;
    private double tretji_vv_pretok;

    public String getSifra() {
        return sifra;
    }

    @XmlAttribute
    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public double getGe_dolzina() {
        return ge_dolzina;
    }

    @XmlAttribute
    public void setGe_dolzina(double ge_dolzina) {
        this.ge_dolzina = ge_dolzina;
    }

    public double getGe_sirina() {
        return ge_sirina;
    }

    @XmlAttribute
    public void setGe_sirina(double ge_sirina) {
        this.ge_sirina = ge_sirina;
    }

    public double getKota() {
        return kota;
    }

    @XmlAttribute
    public void setKota(double kota) {
        this.kota = kota;
    }

    public String getReka() {
        return reka;
    }

    public void setReka(String reka) {
        this.reka = reka;
    }

    public String getMerilno_mesto() {
        return merilno_mesto;
    }

    public void setMerilno_mesto(String merilno_mesto) {
        this.merilno_mesto = merilno_mesto;
    }

    public String getIme_kratko() {
        return ime_kratko;
    }

    public void setIme_kratko(String ime_kratko) {
        this.ime_kratko = ime_kratko;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public double getVodostaj() {
        return vodostaj;
    }

    public void setVodostaj(double vodostaj) {
        this.vodostaj = vodostaj;
    }

    public double getPretok() {
        return pretok;
    }

    public void setPretok(double pretok) {
        this.pretok = pretok;
    }

    public String getPretok_znacilni() {
        return pretok_znacilni;
    }

    public void setPretok_znacilni(String pretok_znacilni) {
        this.pretok_znacilni = pretok_znacilni;
    }

    public double getTemp_vode() {
        return temp_vode;
    }

    public void setTemp_vode(double temp_vode) {
        this.temp_vode = temp_vode;
    }

    public double getPrvi_vv_pretok() {
        return prvi_vv_pretok;
    }

    public void setPrvi_vv_pretok(double prvi_vv_pretok) {
        this.prvi_vv_pretok = prvi_vv_pretok;
    }

    public double getDrugi_vv_pretok() {
        return drugi_vv_pretok;
    }

    public void setDrugi_vv_pretok(double drugi_vv_pretok) {
        this.drugi_vv_pretok = drugi_vv_pretok;
    }

    public double getTretji_vv_pretok() {
        return tretji_vv_pretok;
    }

    public void setTretji_vv_pretok(double tretji_vv_pretok) {
        this.tretji_vv_pretok = tretji_vv_pretok;
    }
}
