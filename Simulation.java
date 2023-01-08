/*Elise Garrouty et Michèle Mbohoazy */

import java.util.ArrayList;

public class Simulation{
    private Terrain terrain;
    private ArrayList<Agents> nosAgents;
    private ArrayList<Ressource> nosRessources;

    public Simulation( Terrain t){
        this.terrain = t;
        this.nosAgents = new ArrayList<Agents>();
        this.nosRessources = new ArrayList<Ressource>();
    }
    

    public void placeRessource(){
        for(Ressource r : this.nosRessources){
            if(terrain.caseEstVide(r.getX(), r.getY())){
                int x=(int)(Math.random()*this.terrain.nbLignes);
                int y=(int)(Math.random()*this.terrain.nbColonnes);
                int i = 0;
                while(!terrain.caseEstVide(x,y)){
                    x=(int)(Math.random()*this.terrain.nbLignes);
                    y=(int)(Math.random()*this.terrain.nbColonnes);
                    i++;
                    if(i==200){break;}
                }
                terrain.setCase(x,y,r);
            }
        }
    }
    
    //La chenille se transforme en Papillon
    public void setransforme(Agents a){
        Papillon p = new Papillon(terrain, a.getPosx(), a.getPosy());
        System.out.println("La Chenille " + a.getId() + " en (" + a.getPosx() + "," + a.getPosy() + ") s'est transformée!!");
        a.meurt(this);
        nosAgents.remove(a);
        ajouterAgent(p);  
    }

    //Le Papillon ponds des Oeufs
    public void pondre(Agents a){
        Oeuf o = new Oeuf();
        nosRessources.add(o);
        terrain.lesRessources().add(o);
        terrain.setCase(a.getPosx(), a.getPosy(), o);
        System.out.println("Le Papillon " + a.getId() + " en (" + a.getPosx() + ", " + a.getPosy() + ") a pondu un oeuf!");  
    }

    //L'oeuf éclot pour devenir une chenille
    public void eclot(Ressource r){
        if(!(r.getX() == -1)){
            r.setQuantite(r.getQuantite() + 1);
            if(r.getQuantite() == 3){
                Chenille c = new Chenille(terrain, r.getX(), r.getY());
                ajouterAgent(c);
                System.out.println("L'oeuf " + r.ident + " en (" + r.getX() + "," + r.getY() + ") a éclot!!!");
                nosRessources.remove(r);
                terrain.lesRessources().remove(r);
                terrain.videCase(r.getX(), r.getY()); 
            }
            else{
                System.out.println("L'oeuf " + r.ident+ " en (" + r.getX() + "," + r.getY()+ ") eclot dans " + (3-r.getQuantite()) +" jours!");
            }
        }
    } 

    public void rafraichir(){
        for(int i=0; i<5; i++){
            Fleur f = new Fleur();
            Feuille v = new Feuille();
            nosRessources.add(f);
            nosRessources.add(v);
            terrain.lesRessources().add(f);
            terrain.lesRessources().add(v);
        }
        placeRessource();
    }

    public void simuler(int tour){
        ArrayList<Agents> nosAgentsVivant = new ArrayList<Agents>();
        ArrayList<Agents> nosChenilles = new ArrayList<Agents>();
        ArrayList<Agents> nosPapillons = new ArrayList<Agents>();
        ArrayList<Ressource> nosOeufs = new ArrayList<Ressource>();

        //Similation pour nos agents
        for(Agents a : nosAgents){
            if(a.estVivant()){
                nosAgentsVivant.add(a);
            }else{
                nosAgents.remove(a);
            }
        }

        for(Agents a : nosAgentsVivant){
            a.mange(this);
            a.vieillir(this);
        }
        System.out.println("-----------------------------------------");

        
        //nos agents
        
        for (Agents a : nosAgentsVivant){
            if(a instanceof Chenille && ((a.getJours()==5))){
                nosChenilles.add(a);
            }
            if(a instanceof Papillon){
                nosPapillons.add(a);
            }
        }
        for (Agents c : nosChenilles){
            setransforme(c);
        }
        for(Agents p : nosPapillons){
            pondre(p);
        }

        //Simulation pour nos oeufs      
        for (Ressource r : nosRessources){
            if(r instanceof Oeuf){
                nosOeufs.add(r);
                }
        }

        for (Ressource o : nosOeufs){
            eclot(o);
        }

        sontMort();
        sontMangés();
        System.out.println("-----------------------------------------");

    }

    public void ajouterAgent(Agents a){
        this.nosAgents.add(a);
    }

    public void ajouteRessource(Ressource r){
        nosRessources.add(r);
        terrain.lesRessources().add(r);
    }

    public ArrayList<Agents> getAgents(){
        return nosAgents;
    }

    public ArrayList<Ressource> getRessources(){
        return terrain.lesRessources();
    }

    //Enlève l'agent qui a été tué de la liste de nos agents
    public void sontMort(){
        nosAgents.removeIf(a -> (!a.estVivant()));   
    }

    //Enlève les ressources qui ont été mangé et vide la case où se trouve les ressources
    public void sontMangés(){
        nosRessources.removeIf(r -> (terrain.caseEstVide(r.getX(), r.getY())));
        terrain.lesRessources().removeIf(r -> (terrain.caseEstVide(r.getX(), r.getY())));
    }

    public String toString() {
        String s = "";
        for (Agents a : nosAgents) {
            s += a.toString() + "\n";
        }
        return s;
    }
}