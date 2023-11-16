import java.util.Vector;
public class Tp1{
	static Vector intersection(Vector e1,Vector e2){
		Vector rep = new Vector();
		for (int i=0; i<e1.size();i++ ) {
			for (int a=0;a<e2.size();a++ ) {
				if (e1.get(i)==e2.get(a)) {
					rep.add(e1.get(i));
				}
			}
			
		}
		return rep;
	}

	static Vector union(Vector e1,Vector e2){
		Vector rep = new Vector();
		for (int i=0; i<e1.size();i++ ) {
			rep.add(e1.get(i));
			}
		for (int a =0; a<e2.size();a++) {
			rep.add(e2.get(a));
		}
		return rep;
	}
  static Vector difference(Vector e1,Vector e2){
  	Vector rep = new Vector();

  	for (int i=0; i<e1.size();i++ ) {
  		int x = 0;
			for (int a=0;a<e2.size();a++ ) {
				if (e1.get(i)==e2.get(a)) {
				       x +=1;	
				}
			}
			if (x==0){
			   rep.add(e1.get(i));
			}
			
		}
		return rep;

  }
	
	public static void main(String[] args) {
		Vector e1 = new Vector();
		Vector e2 = new Vector();
        Vector intersection = new Vector();
        Vector union  = new Vector();
        Vector difference = new Vector();
        e1.add(4);e1.add(7);e1.add(12);e1.add(9);e1.add(5);e1.add(1);
        e2.add(6); e2.add(9); e2.add(13); e2.add(1); e2.add(4); e2.add(3); e2.add(2);
        intersection = intersection(e1,e2);
        union = union(e1,e2);
        difference = difference(e1,e2);
        System.out.println("ensemble 1= " +e1+ " et ensemble 2= "+e2);
        	
        	System.out.println("intersection => "+ intersection);
        	System.out.println("Union => " + union);
            System.out.println("Difference => " + difference);


	}
}