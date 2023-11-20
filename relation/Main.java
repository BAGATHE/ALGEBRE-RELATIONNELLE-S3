package affichage;
import java.text.AttributedCharacterIterator.Attribute;
import java.util.Scanner;
import java.util.Vector;

import utilitaire.*;
public class Main {
    public static void main(String[] args) {
        try{
        Fonction function = new Fonction();
        Scanner scanner = new Scanner(System.in);
        String exit = " ";
       /*  String [] info = {"nom","prenom","numeros"};
        function.createTable("personne",info);
        Relation tab = null;
        tab = function.getTableContent("personne");
        System.out.println(tab.getAttributs()[0]);
        System.out.println(tab.getAttributs()[1]);
        System.out.println(tab.getAttributs()[2]);
*/
/* 
System.out.println("connected"); 
        while (exit.equals(" ")) {
            System.out.print("=> : ");
            String rep = scanner.nextLine();
            if (!rep.equals("exit")) {
                function.traitementRequest(rep.toString());
                
            }else{
exit = "exit";
            }
        }
       System.out.println("bye bye");
        scanner.close();
*/

//System.out.println(function.verifExpression("alaivo anaty personne(dtn,age,nom) izay dtn=12-10-2000 ary nom=lolo na nom=baba"));
  /* 
String input="alaivo anaty personne(prenom,tache,lolo) where nom=lolo and nom=lolo";
 
String content = input.replaceFirst("alaivo anaty","").trim();
String[] parts = content.split("\\(");
String tableName = parts[0].trim();
String argumentsString = parts[1].replaceAll("\\)", "").trim();
//System.out.println(argumentsString);
String[] arguments = argumentsString.split(" ");

for (int i = 0; i < arguments.length; i++) {
    System.out.println(arguments[i]+ "==> indice " + i);
}
*/
//System.out.println(function.getContentInRelation("alaivo anaty personne(age,nom)").getAttributs().toString());
//System.out.println(function.getIndiceColonne("personne","dtngj"));
//System.out.println(function.getStructureRelation("personne").toString());
//System.out.println(function.verifTypeOfData("gh4"));
//System.out.println(function.getStructuredata(data).toString()); 
//System.out.println(function.compareStructure(function.getStructureRelation("personne"),function.getStructuredata(data)));
//function.insertInto(function.addDataRelation("ampidiro anaty personne(lolo,44,12-05-2000)"));  
 
Attribut one = new Attribut("string","nom");
Attribut two = new Attribut("string","ville");

    Vector<Attribut> attributs = new Vector();
    attributs.add(one);
    attributs.add(two);
    String data1a="danny, tana";
    String data2a="mec,blabla";
    String data3a="kan,fene";

    Vector data = new Vector();
    data.add(data1a);
    data.add(data2a);
    data.add(data3a);
    Relation a = new Relation("olona", attributs, data);

Attribut ones = new Attribut("string","nom");
Attribut twos = new Attribut("nombre","age");

    Vector<Attribut> attributss = new Vector();
    attributss.add(ones);
    attributss.add(twos);
     String data1b="lolo,22";
      String data2b="kan,22";
      String data3b="danny,20";
    Vector datas = new Vector();
     datas.add(data1b);
    datas.add(data2b);
    datas.add(data3b);
    Relation b = new Relation("personne", attributss, datas);

    String cola ="nom";
    String colb = "nom";
    Vector<String> colonne = new Vector<String>();
    colonne.add(cola); ;colonne.add(colb);
   // function.displayAllcontent(function.naturalJoin(a,b));
    //function.displayAllcontent(function.tetha_join(a, b, colonne));
 //function.displayAllcontent(function.naturalJoin(a, b));


//Relation r = function.tetha_join(a, b, colonne);


   // function.jointureNaturelle(a, b);
    //String chaine = "[1,A, 1,A,B]";
   // System.out.println(chaine.substring(1,chaine.length()-1));

//function.displayAllcontent(function.cartesianProduct(a, b));
//System.out.println(function.cartesianProduct(a, b).getDonnees().get(0));
//function.displayAll(function.naturalJoin(a, b));

//Relation r = function.getTableContentAll("alaivo daoly personne");
//function.displayAllcontent(r);


//function.traitementRequest("alaivo daoly personne");
function.traitementRequest("ataovy jointure (personne:nom,olona:ville)");
    }catch(Exception e){
        System.out.println(e.getMessage());
    }    
    }
    
}
