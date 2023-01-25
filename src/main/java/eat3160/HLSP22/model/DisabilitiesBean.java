package eat3160.HLSP22.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class is a container for a list of disabilities, providing setters and getters for the ArrayList.
 * This is intended to be used as a bean to enable a JSTL foreach loop to be used in a view to list the disabilities, 
 * instead of manually typing the HTML to show them.
 * 
 * In addition, it acts a single-source of information if these details need to be used in the future, e.g., update this once to 
 * update all views using this.
 * 
 * @author alf-s
 *
 */
public class DisabilitiesBean implements Serializable {
	ArrayList<String> disabilitesList = null;
	
	public DisabilitiesBean() {
		disabilitesList = new ArrayList<>();
		
		disabilitesList.add("Vision Impairment Disability");
		disabilitesList.add("Paraplegia");
		disabilitesList.add("Quadriplegia");
		disabilitesList.add("Hemiplegia");
		disabilitesList.add("Multiple Sclerosis (MS)");
		disabilitesList.add("Cerebral Palsy");
		disabilitesList.add("Dystrophy");
	}

	public ArrayList<String> getDisabilitesList() {
		return disabilitesList;
	}

	public void setDisabilitesList(ArrayList<String> disabilitesList) {
		this.disabilitesList = disabilitesList;
	}
}
