import java.util.ArrayList;
import java.util.List;

public class MinimumSnippet {

	// Instance Variables
	private List <String> terms;
	private ArrayList<String> newDocument= new ArrayList<>();
	private ArrayList<String> temArray= new ArrayList<>();
	private ArrayList<String> minArray= new ArrayList<>();
	private int starting;
	private int ending;
	private int position;
	private boolean adding;
	
	public MinimumSnippet(Iterable<String> document, List<String> terms) {
	
		// If there is no term
		if(terms.isEmpty()) {
			
			throw new IllegalArgumentException();
		}
		
		this.terms= terms;
		
		// Copying the document into an ArrayList
		for(String element : document ) {
			
			newDocument.add(element);
		}
		
		if(!(terms.isEmpty())&& foundAllTerms()==true) {
			
			for( int length = terms.size();temArray.size()!=length;) {
				
				for( int index = position; index<newDocument.size(); index++ ) {
					
					if(!(temArray.containsAll(terms))) {
						
						for(String element: terms) {
						
							if(element.equals(newDocument.get(index))&&adding==false) {
								
								adding=true;
							}
							
							if(adding==true) {
								
								temArray.add(newDocument.get(index));
								
								if(temArray.size()==1) {
									starting=index;
								}
							  
								if(temArray.size()==length) {
									 
									ending=index;
								}
								
								break;
							}
						}
						
						
					}
				}
				
				if(!(length==temArray.size()&&temArray.containsAll(terms))) {
					
					position++;
					temArray=new ArrayList<String>();
				}
				
				if(position==newDocument.size()) {
					
					length++;
					position=0;
				}
				
				if(length==temArray.size()&&temArray.containsAll(terms)) {
					
					minArray=temArray;
					
				}			
			}
		}		
	}

	// Returns whether or not all terms were found in the document
	
	public boolean foundAllTerms() {

		if(newDocument.containsAll(terms)==false) {
			
			return false;
			
		} else {
			
			return true;
		}
	}
	
	//Return the starting position of the snippet
	
	public int getStartingPos() {
	
		return starting;
	}

	// Return the ending position of the snippet
	
	public int getEndingPos() {
		
		return ending;
	}

	// Return total number of elements contained in the snippet
	
	public int getLength() {
	
		return minArray.size();
	}

	//  Returns the position of one of the search terms as it appears in the original document
	
	public int getPos(int index) {
		
		int position =0; 
		
		for(int count= starting; count <= ending; count++)
		{
			if(terms.get(index).equals(newDocument.get(count))) {
				
				position=count;
				break;
			}
		}
		return position;

	}

}
