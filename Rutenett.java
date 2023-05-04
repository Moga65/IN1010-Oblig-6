import java.util.Random;

class Rutenett {
    
    int antRader;
    int antKolonner;
    Celle [][] rutene;

    public Rutenett(int antRader, int antKolonner){
        this.antRader = antRader;
        this.antKolonner = antKolonner;
        this.rutene = new Celle[antRader][antKolonner]; //antRader = x | antKolonner = y
    }

    public void lagCelle(int rad, int kol) {
        Celle nycelle = new Celle();
        Random rand = new Random();
        int sjanse = rand.nextInt(3);
        if (sjanse == 0){
            nycelle.settLevende(); 
        }
        //rutene[rad - 1][kol - 1] = nycelle;
        rutene[rad][kol] = nycelle;
    }

    public void fyllMedTilfeldigeCeller() {
        for (int rad = 0; rad < antRader; rad++) {
            for (int kol = 0; kol < antKolonner; kol++) {
                lagCelle(rad, kol);
            }
        }
    }

    public Celle hentCelle(int rad,int kol) {
        try {
            return rutene[rad][kol];
        } catch (Exception e) {
            return null;
        }
    }

    public void tegnRutenett() {
        for (int rad = 0; rad < antRader; rad++) {
            for (int kol = 0; kol < antKolonner; kol++) {
                System.out.print(hentCelle(rad, kol).hentStatusTegn());    
            }
        System.out.println();    
        }
    }

    public void settNaboer(int rad,int kol) {
        Celle celle = hentCelle(rad, kol);
        for (int naborad = rad - 1; naborad < rad + 2; naborad++) {
            for (int nabokol = kol - 1; nabokol < kol + 2; nabokol++) {
                Celle nabo = hentCelle(naborad, nabokol);
                if (nabo != null && (naborad != rad || nabokol != kol)){
                    celle.leggTilNabo(nabo);
                }
            }            
        }
    }

    public void kobleAlleCeller() {
        for (int rad = 0; rad < this.antRader; rad++) {
            for (int kol = 0; kol < this.antKolonner; kol++) {
                settNaboer(rad, kol);
            }
        }
    }

    public int antallLevende(){
        int antall = 0;
        for (int rad = 0; rad < this.antRader; rad++) {
            for (int kol = 0; kol < this.antKolonner; kol++) {
                if (hentCelle(rad, kol).erLevende()){
                    antall++;
                }
            }
        }
        return antall;
    }
}