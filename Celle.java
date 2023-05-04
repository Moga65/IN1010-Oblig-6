class Celle {
    boolean levende;
    Celle[] naboer;
    int antLevendeNaboer;
    int antNaboer;

    public Celle(){
        levende = false;
        naboer = new Celle[8];
        antLevendeNaboer = 0;
        antNaboer = 0;
    }

    public void settDoed(){
        levende = false;
    }

    public void settLevende(){
        levende = true; 
    }

    public boolean erLevende(){
        return levende;
    }

    public char hentStatusTegn(){
        if (erLevende()){
            return 'O'; 
        }
        return '.';
    }

    public void leggTilNabo(Celle nabo){
        naboer[antNaboer] = nabo;
        antNaboer++;
    }

    public void tellLevendeNaboer(){
        antLevendeNaboer = 0;
        for (Celle i : naboer) {
            if (i != null){
                if (i.erLevende()){
                    antLevendeNaboer++;
                }
            }
        }
    }

    public void oppdaterStatus(){
        if (antLevendeNaboer < 2){
            settDoed();
        }
        else if (antLevendeNaboer == 2){
            erLevende();
        }
        else if (antLevendeNaboer == 3){
            settLevende();
        }
        else if (antLevendeNaboer > 3){
            settDoed();
        }
    }
}