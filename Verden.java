class Verden {
    int rad;
    int kol;
    int generasjon;
    Rutenett rutenett;

    public Verden(int rad, int kol){
        this.rutenett = new Rutenett(rad, kol);
        this.rad = rad; 
        this.kol = kol; 
        rutenett.fyllMedTilfeldigeCeller();
        rutenett.kobleAlleCeller();
        this.generasjon = 0; 
    }

    public void tegn(){
        System.out.println();
        this.rutenett.tegnRutenett();
        System.out.println("Generasjon: " + this.generasjon);
        System.out.println("Levende: " + this.rutenett.antallLevende());
    }
    
    public void oppdatering(){
        for (int rad = 0; rad < this.rad; rad++) {
            for (int kol = 0; kol < this.kol; kol++) {
                Celle celle = rutenett.hentCelle(rad, kol);
                celle.tellLevendeNaboer();
                celle.oppdaterStatus();
            }
        }
        generasjon++;
    }

}
