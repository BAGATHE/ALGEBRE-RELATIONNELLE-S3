package utilitaire;

import java.lang.reflect.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.management.Attribute;

import utilitaire.*;
import java.io.*;
import java.util.*;

public class Fonction {

    public Vector intersection(Vector e1,Vector e2){
		Vector rep = new Vector();
		for (int i=0; i<e1.size();i++ ) {
			for (int a=0;a<e2.size();a++ ) {
				if (e1.get(i).equals(e2.get(a))) {
					rep.add(e1.get(i));
				}
			}
			
		}
		return rep;
	}


    public Vector union(Vector e1,Vector e2){
		Vector rep = new Vector();
		for (int i=0; i<e1.size();i++ ) {
			rep.add(e1.get(i));
			}
		for (int a =0; a<e2.size();a++) {
			rep.add(e2.get(a));
		}
		return rep;
	}




      
 public boolean verifType(String type){
    String[] types = {"string", "nombre", "date"};

    for (String valide : types) {
        if (type.equalsIgnoreCase(valide)) {
            return true;
        }
    }
    return false;

}

//fonction qui met dans un vector les infomation necessaire a une relation nom type nomattribut
public  Relation getRelationInfo(String input) {
    try {
        Vector<Attribut> attributs = new Vector<>();
        Vector donnees = new Vector();

        // Supprime le préfixe "mamorona"
        String content = input.replaceFirst("mamorona", "").trim();

        // J'extrait le nom et les arguments
        String[] parts = content.split("\\(");
        String tableName = parts[0].trim();
        String argumentsString = parts[1].replaceAll("\\)", "").trim();

        // Mettre les arguments dans un tableau
        String[] arguments = argumentsString.split(",");
        for (int i = 0; i < arguments.length; i++) {
            String[] metadata = arguments[i].trim().split("\\s+");
            if (metadata.length == 2 && verifType(metadata[0])) {
                Attribut attribut = new Attribut(metadata[0], metadata[1]);
                attributs.add(attribut);
            }else{
                System.out.println("Les types de données autorisés sont String, nombre et date. Veuillez vérifier votre requête.");
            }
        }

        Relation infoRelation = new Relation(tableName,attributs,donnees);

        return infoRelation;

    } catch (Exception e) {
        
        System.out.println("Erreur : " + e.getMessage());
        return null;
    }
}
public void createTable(Relation relation) {
    try {
        String chemin = "/home/emadaly/backupLinux/LECONS/S3/tsinjo/relation/";
        File fichier = new File(chemin + relation.getName() + ".txt");

        if (!fichier.exists()) {
            fichier.createNewFile();

            try (BufferedWriter ecrire = new BufferedWriter(new FileWriter(fichier, true))) {
                Vector<Attribut> attributs = relation.getAttributs();
                for (Attribut attribut : attributs) {
                    ecrire.write(attribut.toString());

                    // Ajoutez une virgule si ce n'est pas le dernier attribut
                    if (attributs.indexOf(attribut) < attributs.size() - 1) {
                        ecrire.write(", ");
                    }
                }
                ecrire.write("\n");
                System.out.println("Création réussie");
            }
        } else {
            System.out.println("La relation existe déjà");
        }
    } catch (IOException e) {
        System.out.println(e.getMessage());
    }
}
public int verifExpression(String sqlRequete) {
        String[] modeles = new String[4];
        modeles[0] = "mamorona\\s+([a-zA-Z_]\\w*)\\s*\\(([^)]*)\\)+$"; //mamorona personne(string nom,nombre age)
        modeles[1] ="ampidiro anaty\\s+([a-zA-Z_]\\w*)\\s*\\(([^)]*)\\)";//ampidiro anaty personne(lolo,21);
        modeles[2] ="^alaivo daoly\\s+[a-zA-Z]+$"; //alaivo daoly personne
        modeles[3]="alaivo anaty\\s+([a-zA-Z_]\\w*)\\s*\\(([^)]*)\\)(?:\\s+izay\\s+([^=]+)=([^=]+)(?:\\s+(ary|na)\\s+([^=]+)=(\\w+|'[^']*'))*)?$";
        //alaivo anaty personne(nom,age) izay nom=xxx ary age=xxx;
        modeles[4]="ataovy jointure naturaly\\s+([a-zA-Z_]\\w*)\\s*\\(([^)]*)\\)";
        for (int i = 0; i < modeles.length; i++) {
            Pattern expression = Pattern.compile(modeles[i]);
            Matcher matcher = expression.matcher(sqlRequete);
            
            if (matcher.find()) {
               // String nomTable = matcher.group(1);
                //if (nomTable.matches("[a-zA-Z_]+")) {
                    return i;
                //}
            }
        }

        return -1; // Retourne -1 si aucune correspondance n'est trouvée
    }
//fonction qui me donne la structure de la relation
public Vector<String> getStructureRelation(String nom) {
        Vector<String> attributs = new Vector<>();

        try {
            String chemin = "/home/emadaly/backupLinux/LECONS/S3/tsinjo/relation/";
            File fichier = new File(chemin + nom + ".txt");

            if (fichier.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(fichier));
                String premiereLigne = reader.readLine();

                if (premiereLigne != null) {
                    String[] arguments = premiereLigne.split(",");
                    for (int i = 0; i < arguments.length; i++) {
                        String[] metadata = arguments[i].trim().split("\\s+");
                        if (metadata.length == 2) {
                            attributs.add(metadata[0]);
                        }
                    }
                }
                 reader.close();
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        } 
        return attributs;
    }
//fonction qui me retourne name attribut
public Vector<String> getNameAttributRelation(String nom) {
        Vector<String> attributs = new Vector<>();

        try {
            String chemin = "/home/emadaly/backupLinux/LECONS/S3/tsinjo/relation/";
            File fichier = new File(chemin + nom + ".txt");

            if (fichier.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(fichier));
                String premiereLigne = reader.readLine();

                if (premiereLigne != null) {
                    String[] arguments = premiereLigne.split(",");
                    for (int i = 0; i < arguments.length; i++) {
                        String[] metadata = arguments[i].trim().split("\\s+");
                        if (metadata.length == 2) {
                            attributs.add(metadata[1]);
                        }
                    }
                }
                 reader.close();
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        } 
        return attributs;
    }
public Vector<Attribut> getAttributRelation(String relationName) {
        Vector<Attribut> attributs = new Vector<>();

        try {
            String chemin = "/home/emadaly/backupLinux/LECONS/S3/tsinjo/relation/";
            File fichier = new File(chemin + relationName + ".txt");

            if (fichier.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(fichier));
                String premiereLigne = reader.readLine();

                if (premiereLigne != null) {
                    String[] arguments = premiereLigne.split(",");
                    for (int i = 0; i < arguments.length; i++) {
                        String[] metadata = arguments[i].trim().split("\\s+");
                        if (metadata.length == 2) {
                            Attribut attri = new Attribut(metadata[0],metadata[1]);  
                            attributs.add(attri);
                        }
                    }
                }
                 reader.close();
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        } 
        return attributs;
    }

/*foncton qui verifie le type de donnée */
private static boolean isAstring(String chaine) {
    return chaine.matches("[a-zA-Z0-9]+");
}
private static boolean estSeulementChiffres(String chaine) {
        return chaine.matches("\\d+");
}

private static boolean estFormatDate(String chaine) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(false);

        try {
            sdf.parse(chaine);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

public String verifTypeOfData(String chaine) {
        String rep = "";
    
        if (estSeulementChiffres(chaine)) {
            rep = "nombre";
        } else if (isAstring(chaine)) {
            rep = "string";
        } else if (estFormatDate(chaine)) {
            rep = "date";
        }
    
        return rep;
    }
    
//fonction qui me retourne un vector de type de donne
public Vector<String> getStructuredata(String[] data){
        Vector<String> dataStructure = new Vector<>();
        for (int index = 0; index < data.length; index++) {
            dataStructure.add(verifTypeOfData(data[index]));
        }
        return dataStructure;
    }

//fonction qui compare le type de donne des attribut et type de donne des data entrée par user

public boolean compareStructure(Vector<String> relationStructure,Vector<String> dataStructure){
    int compte = 0;
    if (relationStructure.size()!=dataStructure.size()) {
        return false;
    }
    else{
    for (int i = 0; i < relationStructure.size(); i++) {
        if (relationStructure.get(i).equals(dataStructure.get(i))) {
            compte+=1;
        }
    }
    if (compte==relationStructure.size()) {
        return true;
    }else{
        return false;
    }
}
}

 
public Relation addDataRelation(String input) {
    try {
        Vector<Attribut> attributs = new Vector<>();
        Vector<String> typeAttribut = new Vector<>();
        Vector<String> typeOfData = new Vector<>();
        Vector<String> donnees = new Vector<>();

        String content = input.replaceFirst("ampidiro anaty", "").trim();
        String[] parts = content.split("\\(");
        String tableName = parts[0].trim();
        String argumentsString = parts[1].replaceAll("\\)", "").trim();
        String[] arguments = argumentsString.split(",");

        // Comparaison du type de données de la relation avec celui des données à insérer
        typeAttribut = getStructureRelation(tableName);
        typeOfData = getStructuredata(arguments);

        if (compareStructure(typeAttribut, typeOfData)) {
            for (int index = 0; index < typeAttribut.size(); index++) {
                donnees.add(arguments[index]);
                Attribut atrb = new Attribut(typeAttribut.get(index), donnees.get(index));
                attributs.add(atrb);
            }

            Relation relation = new Relation(tableName, attributs, donnees);
            return relation;
        } else {
            System.out.println("---> Erreur dans l'insertion : Vérifiez le type de données. Le nombre de données insérées doit être égal au nombre de colonnes attribut. Vérifiez si la relation existe vraiment.");
            return null;
        }
    } catch (Exception e) {
        System.out.println("Erreur : " + e.getMessage());
        return null;
    }
}

//fonction qui insert dans une table
public void insertInto(Relation relation) {
    try {
        String chemin = "/home/emadaly/backupLinux/LECONS/S3/tsinjo/relation/";
        File fichier = new File(chemin + relation.getName() + ".txt");

        if (fichier.exists()) {
            try (BufferedWriter ecrire = new BufferedWriter(new FileWriter(fichier, true))) {
                Vector<String> data = relation.getDonnees();
                for (String donnee : data) {
                    ecrire.write(donnee);

                    // Ajoutez une virgule si ce n'est pas la dernière donnée
                    if (data.indexOf(donnee) < data.size() - 1) {
                        ecrire.write(", ");
                    }
                }
                ecrire.write("\n");
                System.out.println("Insertion réussie");
            }
        } else {
            System.out.println("erreru au niveau de l'insertion");
        }
    } catch (IOException e) {
        System.out.println(e.getMessage());
    }
}

public boolean relationIsExist(Relation relation){
    if (relation!=null) {
        String chemin = "/home/emadaly/backupLinux/LECONS/S3/tsinjo/relation/";
        File fichier = new File(chemin + relation.getName() + ".txt");
        if (fichier.exists()) {
           return true;
        }
        return false;
    }
       return false;
}



 public void traitementRequest(String requette)throws Exception{
           if (verifExpression(requette)==0) {
            Relation relation = getRelationInfo(requette);
             
            if (relation.getAttributs().size()>0) {
                createTable(relation);
            }        
           }else if(verifExpression(requette)==1){

            if(relationIsExist(addDataRelation(requette))){
               insertInto(addDataRelation(requette));
            }else{
                System.out.println("la relation n'existe pas");
            }
           }else if(verifExpression(requette)==2){
             Relation relation = getTableContentAll(requette);
                if (relation.getDonnees().size()>0) {
                   displayAllcontent(relation);}
           }else if(verifExpression(requette)==3){
                  Relation relation = getContentInRelation(requette);
                  if (relation.getAttributs().size()>0) {
                    displayAllcontent(relation);
                  } 
           }else{
            System.out.println("(1)--->stucture requette inexacte");
           }
    }

   public Relation getTableContentAll(String input){
    Vector<String> lignes = new Vector<>();
    Relation table = null;
    Vector <Attribut> attri = new Vector();
    Vector data = new Vector<>();
    String tableName = input.replaceFirst("alaivo daoly","").trim();
    try {
        String chemin = "/home/emadaly/backupLinux/LECONS/S3/tsinjo/relation/";
        File fichier = new File(chemin + tableName + ".txt");
        if (fichier.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(fichier));
            String ligne;
           while ((ligne = reader.readLine()) != null) {
                lignes.add(ligne);
            }
            reader.close();
            if (!lignes.isEmpty()) {
                //ajouter les attribut
                String[] attributs = lignes.get(0).split(",");
                for (int index = 0; index < attributs.length; index++) {
                    String[] attribut = attributs[index].trim().split(" ");
                    Attribut atr = new Attribut(attribut[0], attribut[1]);
                    attri.add(atr);
                }
                // Créer la table avec le nom et les attributs
                table = new Relation(tableName, attri, data);

                // Ajouter les données à la table
                for (int i = 1; i < lignes.size(); i++) {
                    String[] donnees = lignes.get(i).split(",");
                    String sousdata = String.join(",", donnees);
                    // Ajouter les données à la table
                    //table.getDonnees().add(new Vector(Arrays.asList(donnees)));
                    data.add(sousdata);
                }
                table = new Relation(tableName, attri, data);
            } else {
                System.out.println("Le fichier est vide.");
            }
        } else {
            System.out.println("La relation n'existe pas");
        }
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return table;
}


 
public Relation getContentInRelation(String input){

    Vector<String> lignes = new Vector<>();
    Relation table = null;
    Vector <Attribut> attri = new Vector();
    Vector donner = new Vector();
    String content = input.replaceFirst("alaivo anaty","").trim();
    String[] parts = content.split("\\(");
    String tableName = parts[0].trim();
    String argumentsString = parts[1].replaceAll("\\)", "").trim();
    String[] partOfRequette = argumentsString.split(" ");
   try {
        String chemin = "/home/emadaly/backupLinux/LECONS/S3/tsinjo/relation/";
        File fichier = new File(chemin + tableName + ".txt");
        if (fichier.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(fichier));
            String ligne;
           while ((ligne = reader.readLine()) != null) {
                lignes.add(ligne);
            }
            reader.close();
            if (!lignes.isEmpty()) {

                    String[] colonne = partOfRequette[0].split(",");
                       Vector <Attribut> allAttri = new Vector();
                allAttri = getAttributRelation(tableName);
                for (int index = 0; index < colonne.length; index++) {
                    if (getIndiceColonne(tableName,colonne[index])>=0) {
                      attri.add(allAttri.get(getIndiceColonne(tableName,colonne[index])));
                    }
                }
                // Créer la table avec le nom et les attributs
                //table = new Relation(tableName, attri, new Vector());

                // Ajouter les données à la table
                for (int i = 1; i < lignes.size(); i++) {
                    String[] allLinedonnees = lignes.get(i).split(",");
                    String [] data = new String[colonne.length];
                    for (int index = 0; index < colonne.length; index++) {
                    if (getIndiceColonne(tableName,colonne[index])>=0) {
                            
                            data[index]=allLinedonnees[getIndiceColonne(tableName,colonne[index])];
                    }
                }
                 String sousdata = String.join(",", data);
                
                    donner.add(sousdata);
                }
                if (partOfRequette.length<=2) {
                    table = new Relation(tableName, attri, donner);
                }else if (partOfRequette.length==3) {    
                           String[] condition = partOfRequette[2].split("=");
                           int indice = -1;
                           
                           for (int index = 0; index < attri.size(); index++) {
                            Attribut atrb = (Attribut)attri.get(index);
                                 if (atrb.getNom().equals(condition[0])) {
                                    indice = index;
                                 }
                        }
                            for (int i = donner.size() - 1; i >= 0; i--) {
                            
                                    String chaine = donner.get(i).toString();
                                    String[] tabChaine = chaine.split(","); 
                                    if (tabChaine[indice].trim().equals(condition[1])==false  ) {
                                    donner.remove(i);
                                }
                               
                            }
                          table = new Relation(tableName, attri, donner);
    
                        }else if (partOfRequette.length>3) {
                            int  indice = -1;
                            Vector majdata = new Vector<>();
                            Vector resultCondition = new Vector<>();
                            Vector resultwhereCondition = new Vector<>();
                            Vector resultAndCondition = new Vector<>();
                            Vector resultOrCondition = new Vector<>();

                            String[] tabcondition = new String[partOfRequette.length-1]; 
                            for (int index = 0; index < partOfRequette.length-1; index++) {
                                tabcondition[index] = partOfRequette[index+1];
                            }
                            
                           
                            
                            for (int index = 0; index < tabcondition.length/2; index++) {
                                int nombrePair = index * 2;
                                int nombreImpair = index * 2 + 1;

                                for (int ind = 0; ind < attri.size(); ind++) {
                                    Attribut atrb = (Attribut)attri.get(ind);
                                    if (atrb.getNom().equals(tabcondition[nombreImpair].split("=")[0])) {
                                    indice = ind;
                                    }
                        }
                        
                    
                                    if (index==0) {
                                        for (int i = donner.size() - 1; i >= 0; i--) {
                                    

                                    String chaine = donner.get(i).toString();
                                    
                                    String[] tabChaine = chaine.split(","); 
                                    if (tabChaine[indice].trim().equals(tabcondition[nombreImpair].split("=")[1])==true) {
                                    majdata.add(donner.get(i));
                                }
                                
                                
                            }
                                    }
                                     
                                    else if (tabcondition[nombrePair].toLowerCase().equals("ary")) {
                                           for (int i = donner.size() - 1; i >= 0; i--) {
                                            

                                    String chaine = donner.get(i).toString();

                                    String[] tabChaine = chaine.split(","); 
                                    if (tabChaine[indice].trim().equals(tabcondition[nombreImpair].split("=")[1])==true) {
                                      resultAndCondition.add((donner.get(i)));
                                }
                                
                            }
                            resultCondition.addAll(this.intersection(majdata,resultAndCondition));
                            majdata.clear();
                            majdata.addAll(resultCondition);
                            resultCondition.clear();
                                    } 
                                    else if (tabcondition[nombrePair].toLowerCase().equals("na")) {
                                        

                                         for (int i = donner.size() - 1; i >= 0; i--) {
                                             

                                    String chaine = donner.get(i).toString();
                                    
                                    String[] tabChaine = chaine.split(","); 
                                    if (tabChaine[indice].trim().equals(tabcondition[nombreImpair].split("=")[1])==true) {
                                    resultOrCondition.add(donner.get(i));
                                }

                                
                            }
                              resultCondition.addAll(this.union(majdata,resultOrCondition));
                              majdata.clear();
                              majdata.addAll(resultCondition);
                              resultCondition.clear();
                                    }

                                  } 
                            
                                  HashSet  uniqueElements = new HashSet<>(majdata);
                                  Vector vectorWithoutDuplicates = new Vector<>(uniqueElements);
                             table = new Relation(tableName, attri, vectorWithoutDuplicates);
                        }
          
                
            } else {
                System.out.println("Le fichier est vide.");
            }
        } else {
            System.out.println("La relation n'existe pas");
        }
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return table;
}

public void displayAllcontent(Relation relation){
  Vector data = relation.getDonnees();

  System.out.println(relation.getAttributs().toString());
  for (int i = 0; i < data.size(); i++) {
    System.out.println(data.get(i).toString());
  }  
}

//fonction qui retourne l'indice de la colonne
public int getIndiceColonne(String tableName,String nameColonne){
int indice = -1;
    Vector<String> attributs = new Vector<>();
attributs = getNameAttributRelation(tableName);
for (int i = 0; i < attributs.size(); i++) {
    if (attributs.get(i).equals(nameColonne)) {
        indice=i;
        return indice;
    }
}
return -1;
}

//produit cartesien de deux relation
public static Relation cartesianProduct(Relation a, Relation b) {
    // Créer de nouveaux attributs pour la relation résultante
    Vector<Attribut> nouveauxAttributs = new Vector<>();
    nouveauxAttributs.addAll(a.getAttributs());
    nouveauxAttributs.addAll(b.getAttributs());

    // Créer la relation résultante
    Relation resultat = new Relation("Resultat", nouveauxAttributs, new Vector());
Vector data_relationa = new Vector<>();
data_relationa.addAll(a.getDonnees());

Vector data_relationb = new Vector<>();
data_relationb.addAll(b.getDonnees());


    for (int i = 0; i < data_relationa.size(); i++) {
        

        for (int j = 0; j < data_relationb.size(); j++) {
            Vector nouvelleLigne = new Vector<>();
            //alaiko objet sous forme tab chaine avadika tab chaine d avadika sous chaine [l,c,v] =>[l,c,v]=> l,c,v
            String chaineA = data_relationa.get(i).toString();
            String chaineB = data_relationb.get(j).toString();
            // Créer une nouvelle ligne avec les données combinées
                nouvelleLigne.add(chaineA);
                nouvelleLigne.add(chaineB);
            String newline = nouvelleLigne.toString();
            String sousdata = newline.substring(1, newline.length()-1);
            //String [] tabSousData = sousdata.split(",");
            resultat.getDonnees().add(sousdata);
        }
    }

    return resultat;
}

//fonction maka indice ireo attribut roa mitovy anaty relation 1
ArrayList<int[]> getIndiceAttributSame(Relation r){
    ArrayList<int[]> indiceAttribut = new ArrayList<int[]>();
    for (int index = 0; index < r.getAttributs().size()-1; index++) {
        Attribut atr = r.getAttributs().get(index);
        for (int i = index +1; i < r.getAttributs().size(); i++) {
              if (atr.getNom().trim().equals(r.getAttributs().get(i).getNom().trim()) && atr.getType().trim().equals(r.getAttributs().get(i).getType().trim())) {
            int [] indice = new int[2];
            indice[0]=index;
            indice[1]=i;
            indiceAttribut.add(indice);
        }
        }
       
    }
    return indiceAttribut;
}

//maka unique element anaty liste element
public static String[] getElementsUniques(String[] tableau) {
    Set<String> set = new HashSet<>();
    for (String element : tableau) {
        set.add(element);
    }

    // Convertir le Set en tableau
    String[] resultat = new String[set.size()];
    set.toArray(resultat);

    return resultat;
}

// maka unique attribut anaty liste attribut 
public Vector<Attribut> getUniqueAttribut(Vector<Attribut> attr){
HashSet<Attribut> uniqueElements = new HashSet<Attribut>(attr);
Vector<Attribut> newAttributs = new Vector<Attribut>(uniqueElements);
return newAttributs;
}

//maka indice attribut unique anaty attribut produit cartesien 
public Vector getIndiceAttrubutUnique(Vector<Attribut> uniqueAtr,Vector<Attribut> productCartesian){
    Vector rep = new Vector();
    for (int i = 0; i < uniqueAtr.size(); i++) {
         for (int j = 0; j < productCartesian.size(); j++) {
            if (uniqueAtr.get(i).getType().trim().equals(productCartesian.get(j).getType().trim()) && uniqueAtr.get(i).getNom().trim().equals(productCartesian.get(j).getNom().trim())) {
             rep.add(j);    
             break;         
            }
         }
    }
    return rep;
   
}


//fonction mamerina doublon
public static Vector getDoublon(Vector vector) {
    Vector doublons = new Vector();
    HashSet set = new HashSet();

    for (Object element : vector) {
        if (!set.add(element)) {
            // L'élément est déjà présent dans set, donc c'est un doublon
            doublons.add((int)element);
        }
    }

    return doublons;
}


// Fonction pour obtenir les attributs communs entre deux relations
public static Vector<Attribut> getAttributsCommuns(Relation r, Relation s) {
    Vector<Attribut> attributsCommuns = new Vector<>();
    for (Attribut attributR : r.getAttributs()) {
        for (Attribut attributS : s.getAttributs()) {
            if (attributR.getNom().equals(attributS.getNom()) && attributR.getType().equals(attributS.getType())) {
                attributsCommuns.add(attributR);
                break; // Sortir de la boucle interne si un attribut en commun est trouvé
            }
        }
    }
    return attributsCommuns;
}





//FONCTION MANAO JOIUNTURE NATURELLE DE DEUX RELATION 
public Relation naturalJoin(Relation a ,Relation b){
    
    Vector<Attribut> newAttributs = new Vector<>();
    Relation resultproductCartesian  = cartesianProduct(a, b); //produit cartesien des deux relation
    Vector data = new Vector();
    data.addAll(resultproductCartesian.getDonnees());
    Vector tabindiceline =new Vector(); //vector qui va contenir  les indice de ligne ou il y a le donnéé identique de colone de meme type et nom 

ArrayList<int[]> indiceAttributSAme = getIndiceAttributSame(resultproductCartesian);   // je prend les indice des attribut parreil [1,2][indice r1,indice r2];

for (int i = 0; i < indiceAttributSAme.size(); i++) {
    for (int index = 0; index < resultproductCartesian.getDonnees().size(); index++) {
        String line = (String) data.get(index);
        String[]tabline = line.split(",");
    
        if (tabline[(int)indiceAttributSAme.get(i)[0]].trim().equals(tabline[(int)indiceAttributSAme.get(i)[1]].trim())) {
            tabindiceline.add(index);
        }
    }
}
System.out.println(tabindiceline.toString());
Vector doublon = getDoublon(tabindiceline);
Vector newdata  = new Vector(); 
Vector indiceUniquedata = getIndiceAttrubutUnique(getUniqueAttribut(resultproductCartesian.getAttributs()),resultproductCartesian.getAttributs());
for (Object indice : doublon) {
      String line = (String) data.get((int)indice);
      String[]tabline = line.split(",");
      String result="";
      for (int index = 0; index < indiceUniquedata.size() ; index++) {
        if (result.equals("")) {
             result=tabline[(int)indiceUniquedata.get(index)];
        }else{
        result= result+","+tabline[(int)indiceUniquedata.get(index)];
        }
      }
      newdata.add(result);
      
      
}

Vector<Attribut> newAttribut = getUniqueAttribut(resultproductCartesian.getAttributs());
Relation result = new Relation(a.getName()+" join " + b.getName(),newAttribut,newdata);

return result;
}

//fonction qui recupere les attribut par colonne souhaiter
public Vector<Attribut> getAttributbycol(Vector<Attribut> allAttributs ,Vector<String> col)
{
    Vector<Attribut> attributbycol = new Vector<Attribut>();
               
    for (Object colonne : col) {
        for (int i = 0; i < allAttributs.size(); i++) {
            if (colonne.toString().trim().equals(allAttributs.get(i).getNom().trim())) {
                Attribut atr = allAttributs.get(i);
                attributbycol.add(atr);
                break;
            }
        }
    }
    return attributbycol;
}

//fonction qui verifie si les attribut on le meme type de donner 
public boolean isSametype(Vector<Attribut> attribu){
      for (Attribut atr : attribu) {
          for (int i = 0; i < attribu.size(); i++) {
              if (!atr.getType().trim().equals(attribu.get(i).getType().trim())) {
                return false;
              }
          }
      }
      return true;
}



//function get indice attribut specify to colunm
public int getIndiceAttributToCompare(Vector<Attribut> attributs, String colonneName) {
    for (int index = 0; index < attributs.size(); index++) {
        if (attributs.get(index).getNom().trim().equals(colonneName.trim())) {
            return index; // Si la colonne est trouvée, retourne l'indice et sort de la méthode.
        }
    }
    return -1; // Si la colonne n'est pas trouvée, retourne -1.
}



//FONCTION MANAO TETHA JOINTURE
public void tetha_join(Relation a,Relation b,Vector<String>colonne){
     
// i get the colunm index that is compared
//checks if the two attributs have the same data type (if yes we continue algo if no we display error)
//i will make two loops to compare the index data (if the two relation have a same data we combine)
    
}

}

