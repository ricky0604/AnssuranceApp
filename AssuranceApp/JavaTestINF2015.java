/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javatestinf2015;


import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

/**
 *
 * @author Compaq_Propriétaire
 */
public class JavaTestINF2015 {

    /**
     * @param args the command line arguments
     * 
     */
    public static Boolean estBonNombreArgument(int nombreArgumentVoulu, String[] args){
        if( nombreArgumentVoulu == args.length) return true;
        return false;
    }
    
    
    public static int calculDuNombreDAnnee(String dateISO8601){
        
        GregorianCalendar dateDuMoment = new GregorianCalendar();
        String anneeDateISO8601 = "";
        String moisDateISO8601 = "";
        String jourDateISO8601 = "";
        int compteurPourString = 1;
        final int jourX = 1;
        final int moisX = 2;
        final int anneeX = 3;
        
        int tailleDateISO8601 = dateISO8601.length();
        
        for (int i = 0; i < tailleDateISO8601; i++) {
            
            char lettre = dateISO8601.charAt(i);
            
            if(lettre == '-') compteurPourString++;
                     
            if (lettre != '-' ){
                switch(compteurPourString){

                    case jourX: anneeDateISO8601 += Character.toString(lettre);
                        break;
                    case moisX: moisDateISO8601 += Character.toString(lettre);
                        break;
                    case anneeX : jourDateISO8601 += Character.toString(lettre);
                        break;
                }
            }
        }
        
        return dateDuMoment.get(Calendar.YEAR) - Integer.parseInt(anneeDateISO8601) ;
    }
    
    public static void writeJsonFile(boolean assurable, double montantAnnuel, double mensualite, String FichierJSONOutput) throws IOException{
        
        JSONObject jsonFinal = new JSONObject();
        jsonFinal.element("assurable", assurable);
        jsonFinal.element("montant_annuel", montantAnnuel);
        jsonFinal.element("mensualite", mensualite);
        
        WriteReader.writeFileIntoString(jsonFinal.toString(), FichierJSONOutput, "UTF-8");
        
        System.out.println(jsonFinal);
        
    }
    
    
    
    public static double calculAvecConditionCAA(double montantPrime, boolean isCAA){
        
        if(isCAA) return montantPrime - 100.00;
        return montantPrime;
    }
    
    public static double calculAvecConditionHomme35ans(double montantPrime,String sexeConducteur, int ageConducteur ){
        
        if (ageConducteur < 35 && sexeConducteur.equals("M")) return montantPrime + 1000.00;
        
        return montantPrime;
    }
    
    public static double calculAvecConditionPremierContrat(double montantPrime, boolean estPremierContrat){
        
        if(estPremierContrat) return montantPrime + 2000.00;
        
        return montantPrime;
    }
    
    public static double calculAvecConducteurExperience(double montantPrime, int experienceDuConducteur){
        
        if(experienceDuConducteur > 15 ) return montantPrime - 400.00;
        
        return montantPrime;
    }
    
    public static void main(String[] args) throws Exception {
        
        final int nombreArgumentVoulu = 2;
        
        if (estBonNombreArgument(nombreArgumentVoulu, args)){
            final String FICHIER_JSON = args[0];
            final String FICHIER_SORTIE_JSON = args[1];

            String jsonString = FileReader.loadFileIntoString(FICHIER_JSON, "UTF-8");
            JSONObject root = (JSONObject) JSONSerializer.toJSON(jsonString);

           
            // extraction des elements du json
            
            JSONObject voitureJSON = root.getJSONObject("voiture");
            JSONObject conducteurJSON = root.getJSONObject("conducteur");
            int dureeContrat = root.getInt("duree_contrat");
            
            //creation Voiture
            
            int anneeVoiture = voitureJSON.getInt("annee");
            String marqueVoiture = voitureJSON.getString("marque");
            String modeleVoiture = voitureJSON.getString("modele");
            double valeurDesOptions = voitureJSON.getDouble("valeur_des_options");
            String burinage = voitureJSON.getString("burinage");
            boolean garageInterieur = voitureJSON.getBoolean("garage_interieur");
            boolean systemeDAlarme = voitureJSON.getBoolean("systeme_alarme");
            
            //creation conducteur
            
            
            String dateDeNaissanceConducteur = conducteurJSON.getString("date_de_naissance");
            String provinceConducteur = conducteurJSON.getString("province");
            String villeConducteur = conducteurJSON.getString("ville");
            String sexeConducteur = conducteurJSON.getString("sexe");
            String dateFinDeConduite = conducteurJSON.getString("date_fin_cours_de_conduite");
            boolean coursDeConduiteReconnusParCAA = conducteurJSON.getBoolean("cours_de_conduite_reconnus_par_CAA");
            boolean premierContratConducteur = conducteurJSON.getBoolean("premier_contrat");
            
            //calculDuNombreDAnnee("2000-12-01");
            System.out.println(calculDuNombreDAnnee("1935-12-01"));
            writeJsonFile(true, 8437.34, 713.66, FICHIER_SORTIE_JSON);
      
        }
        
          
        
    }
}
