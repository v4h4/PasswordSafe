package GUI_tableLogics;

public class JTableSorting {
	private boolean sortByIdReverse=false;
	private boolean sortByOrgReverse=false;
	private boolean sortByEmailReverse=false;
	private boolean sortByUsrReverse=false;
	private boolean sortByDateReverse=false;
	private boolean sortByTimeReverse=false;
	
	public String[][] sortByID(String[][] inputArr){
		if(sortByIdReverse==false){
			sortByIdReverse=true;
			return bubbleSort(inputArr);
		}else{
			sortByIdReverse=false;
			return reverseBubbleSort(inputArr);
		}
		
	}
	
	public String[][] sortByOrg(String[][] org){
		if(sortByOrgReverse==false){
			sortByOrgReverse=true;
			return sortAlphabetically(org, 1);
		}else{
			sortByOrgReverse=false;
			return reverseSortAlphabetically(org, 1);
		}
	}

	public String[][] sortByEmail(String[][] email){
		if(sortByEmailReverse==false){
			sortByEmailReverse=true;
			return sortAlphabetically(email, 2);
		}else{
			sortByEmailReverse=false;
			return reverseSortAlphabetically(email, 2);
		}
	}
	
	public String[][] sortByUsername(String[][] usr){
		if(sortByUsrReverse==false){
			sortByUsrReverse=true;
			return sortAlphabetically(usr, 3);
		}else{
			sortByUsrReverse=false;
			return reverseSortAlphabetically(usr, 3);
		}
	}
	
	public String[][] sortByDate(String[][] date){
		if(sortByDateReverse==false){
			sortByDateReverse=true;
			return sortAlphabetically(date, 5);
		}else{
			sortByDateReverse=false;
			return reverseSortAlphabetically(date, 5);
		}
	}
	
    public String[][] sortByTime(String[][] time){
    	if(sortByTimeReverse==false){
			sortByTimeReverse=true;
			return sortAlphabetically(time, 6);
		}else{
			sortByTimeReverse=false;
			return reverseSortAlphabetically(time, 6);
		}
	}
	
	
	
    private String[][] bubbleSort(String[][] array){
    	String[] tempArr = new String[array[1].length];
        int n= array.length;
       
        for(int i=0; i < n; i++){
        	for(int j=1; j < (n-i); j++){
        		if(Integer.parseInt(array[j-1][0]) > Integer.parseInt(array[j][0])){
        			//swap the elements!
        			for(int x=0;x<array[1].length;x++){
        				tempArr[x]=array[j-1][x];
        			}
        			for(int x=0;x<array[1].length;x++){
        				array[j-1][x]=array[j][x];
        			}
        			for(int x=0;x<array[1].length;x++){
        				array[j][x]=tempArr[x];
        			}
        		}              
        	}
        }
        return array;
    }
    
    private String[][] reverseBubbleSort(String[][] array){
    	String[] tempArr = new String[array[1].length];
    	for(int i=0; i < array.length-1; i++){
    		for(int j=1; j < array.length-i; j++){
    			if(Integer.parseInt(array[j-1][0]) < Integer.parseInt(array[j][0])){
    				for(int x=0;x<array[1].length;x++){
        				tempArr[x]=array[j-1][x];
        			}
        			for(int x=0;x<array[1].length;x++){
        				array[j-1][x]=array[j][x];
        			}
        			for(int x=0;x<array[1].length;x++){
        				array[j][x]=tempArr[x];
        			}
    			}
    		}
    	}
        return array;
   }
    
    private String[][] sortAlphabetically(String[][] arr,int index){
         int shortestStringIndex;
         for(int j = 0; j < arr.length - 1; j++) {
             shortestStringIndex = j;
             for(int i = j + 1; i < arr.length; i++) {
                 if(arr[i][index].trim().compareTo(arr[shortestStringIndex][index].trim()) < 0) {
                     shortestStringIndex = i;
                 }
             }
             if(shortestStringIndex != j) {
            	 String[] temp= new String[arr[1].length];
 				for(int x=0;x<temp.length;x++){
 					temp[x]=arr[j][x];
 				}
 				// arr[j] = arr[shortestStringIndex];
 				for(int x=0;x<temp.length;x++){
 					arr[j][x]=arr[shortestStringIndex][x];
 				}
 				//arr[shortestStringIndex]= temp;
 	            for(int x=0;x<temp.length;x++){
 					arr[shortestStringIndex][x]=temp[x];
 				}
             }
         }
    	return arr;
    }
    
    private String[][] reverseSortAlphabetically(String[][] arr,int index){
    	 int shortestStringIndex;
         for(int j = 0; j < arr.length - 1; j++) {
             shortestStringIndex = j;
             for(int i = j + 1; i < arr.length; i++) {
                 if(arr[i][index].trim().compareTo(arr[shortestStringIndex][index].trim()) > 0) {
                     shortestStringIndex = i;
                 }
             }
             if(shortestStringIndex != j) {
            	 String[] temp= new String[arr[1].length];
 				for(int x=0;x<temp.length;x++){
 					temp[x]=arr[j][x];
 				}
 				// arr[j] = arr[shortestStringIndex];
 				for(int x=0;x<temp.length;x++){
 					arr[j][x]=arr[shortestStringIndex][x];
 				}
 				//arr[shortestStringIndex]= temp;
 	            for(int x=0;x<temp.length;x++){
 					arr[shortestStringIndex][x]=temp[x];
 				}
             }
         }
    	return arr;
    }
}





