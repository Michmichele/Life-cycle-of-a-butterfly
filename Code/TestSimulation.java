/*Elise Garrouty et Michèle Mbohoazy */


import java.util.*;

public class TestSimulation{
    public static void main(String[] args) throws InterruptedException{
        int x;

        Terrain t = new Terrain(10,10);
        Simulation s = new Simulation(t);

        //On crée nos agents présent au debut et on les ajoute dans la liste d'agent de simulation
        Chenille c = new Chenille(t, 1, 5);
        Papillon p = new Papillon(t, 2, 7);
        Araignee a = new Araignee(t, 8, 6);

        s.ajouterAgent(c);
        s.ajouterAgent(p);
        s.ajouterAgent(a);


        //On crée 2 oeufs pour le debut de la simulation et on les places dans la liste de ressource de simulation et terrain
        for(int i=0; i<2; i++){
            Oeuf o = new Oeuf();
            s.ajouteRessource(o);
            t.setCase((int)(Math.random()*t.nbLignes),(int)(Math.random()*t.nbColonnes),o);
        }

        try{
            Scanner scan = new Scanner(System.in);
            System.out.print("Entrer le nombre de tour à faire : ");
            x = scan.nextInt();
        
            System.out.println("Début de la simulation!\n" + s.toString());
            s.rafraichir();
            t.affiche(7);
            int tour = 1;
            while(tour <= 5){
                //On raffraichie tous les 2 tours
                Thread.sleep(1500);

                if(tour%2 == 0){s.rafraichir();}

                System.out.println(tour+" iéme tour :");
                s.simuler(tour);
                System.out.println(s.toString());
                t.affiche(7);
                tour++;
            }
        }catch(InputMismatchException e){
            System.out.println("Le nombre est mal écrit...");
        }

    }
}
