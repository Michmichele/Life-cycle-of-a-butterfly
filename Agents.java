/*Elise Garrouty et Michèle Mbohoazy */

public abstract class Agents{
    protected static int cpt=0;
    private int id;
    private Terrain terrain;
    private int posx, posy;
    private int jours;
    private int energie;
    private String type;

    public Agents(Terrain t, int x, int y, String type){
        id = cpt;
        cpt++;
        terrain = t;
        posx = x;
        posy = y;
        jours = 0;
        energie = 30;
        this.type = type;
    }
    
    public int distance(int x, int y){
        return (int)(Math.sqrt((posx-x)*(posx-x) + (posy-y)*(posy-y)));
    }

    public boolean estVivant(){
        if (posx == -1 && posy == -1 || energie <= 0){
            return false; 
        }
        return true;
    }
    
    public void meurt(Simulation s){
        jours = -1;
        setPosx(-1);
        setPosy(-1);
        s.getAgents().removeIf(a ->(!this.estVivant()));
    }

    public String toString(){
        return id + " à " + jours + " jours, " + energie + " d'énergie";
    }

    //Tous les accesseurs
    public int getEnergie() {
        return energie;
    }

    public int getJours() {
        return jours;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public int getPosx() {
        return posx;
    }

    public int getPosy() {
        return posy;
    }
    
    public int getId(){
        return id;
    }
    public String getType(){
        return type;
    }

    //Tous les mutateurs
    public void setEnergie(int energie) {
        this.energie = energie;
    }
    
    public void setJours(int jours) {
        this.jours = jours;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }

    public abstract void mange(Simulation s);
    public abstract void vieillir(Simulation s);
    public abstract void seDeplacer(int xnew, int ynew);

}