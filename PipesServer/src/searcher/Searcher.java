package searcher;

import searchable.Searchable;

public interface Searcher<T>
{
	public Solution search(Searchable<T> searchable);
	public int getNumberOfNodesEvaluated();
}
