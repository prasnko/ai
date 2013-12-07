
import java.util.Comparator;

public class PriorityQueueComparator implements Comparator<PathInfo> {

	public int compare(PathInfo arg0, PathInfo arg1) {
		if(arg0.getFnValue() < arg1.getFnValue())
			return -1;
		else if(arg0.getFnValue() > arg1.getFnValue())
			return 1;
		else 
			return 0;
	}
}