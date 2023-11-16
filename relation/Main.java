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
/* *
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
 
Attribut one = new Attribut("a","A");
Attribut two = new Attribut("b","B");

    Vector<Attribut> attributs = new Vector();
    attributs.add(one);
    attributs.add(two);
    String data1a="1,A";
    String data2a="1,B";
    String data3a="4,A";

    Vector data = new Vector();
    data.add(data1a);
    data.add(data2a);
    data.add(data3a);
    Relation a = new Relation("personne", attributs, data);

Attribut ones = new Attribut("a","A");
Attribut twos = new Attribut("b","B");
Attribut threes = new Attribut("d","D");
    Vector<Attribut> attributss = new Vector();
    attributss.add(ones);
    attributss.add(twos);
    attributss.add(threes);
     String data1b="1,A,B";
      String data2b="2,C,B";
      String data3b="4,A,A";
    Vector datas = new Vector();
     datas.add(data1b);
    datas.add(data2b);
    datas.add(data3b);
    Relation b = new Relation("personne", attributss, datas);

    String cola ="B";
    String colb = "D";
    Vector<String> colonne = new Vector<String>();
    colonne.add(cola); colonne.add(colb);

    function.tetha_join(a, b, colonne);






   // function.jointureNaturelle(a, b);
    //String chaine = "[1,A, 1,A,B]";
   // System.out.println(chaine.substring(1,chaine.length()-1));

//function.displayAllcontent(function.cartesianProduct(a, b));
//System.out.println(function.cartesianProduct(a, b).getDonnees().get(0));
function.naturalJoin(a, b);

//Relation r = function.getTableContentAll("alaivo daoly personne");
//function.displayAllcontent(r);


//function.traitementRequest("alaivo daoly personne");
//System.out.println(function.verifExpression("alaivo anaty personne(nom) where nom=lolo or id=4 and mena=5"));
    }catch(Exception e){
        System.out.println(e.getMessage());
    }    
    }
    
}
