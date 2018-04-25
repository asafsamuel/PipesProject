package searchable;

import java.util.List;

public interface Searchable<T>
{
	State<T> getInitializeState();
	State<T> getGoalState();
	List<State<T>> getAllPosibleStates(State<T> s);
}
